package kyle.leis.es.price.freightprice.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.expressprice.tp.SaveEnterpriseTransaction;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.dax.FreightPriceDate;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.es.price.freightprice.dax.LoadResult;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TepExpressprice;
import kyle.leis.hi.TepFreightprice;

public class SaveTransaction extends AbstractTransaction {
    private FreightpriceColumns m_objFPColumns;
    private List m_listFreightpricevalue;
    private List m_listSurchargevalue;
    private List m_listSurchargevaluebase;
    private List m_listEnterprise;
    private String m_strOperId;
    private String m_strEpcode;
    private ModifyFreightPriceDateTrans m_objMFPDTrans;
    
    public ArrayList<RuleCheckReturn> setConflictParam(LoadResult objLoadResult,
			String strOperId,
			String strChangedStartDate,
			String strChangedEndDate,
			boolean isCheckDateConflic) throws Exception {
    	List listFPrice = objLoadResult.getFreightPriceColumns();
    	if (listFPrice == null || listFPrice.size() < 1) return null;
    	
    	FreightpriceColumns objFreightpriceColumns = (FreightpriceColumns)listFPrice.get(0);
    	objFreightpriceColumns.setFpepcode(null);
    	objFreightpriceColumns.setEpepstartdate(DateFormatUtility.getStandardDate(strChangedStartDate));
    	objFreightpriceColumns.setEpependdate(DateFormatUtility.getStandardDate(strChangedEndDate));    	
    	
    	return setParam(objFreightpriceColumns,
    			objLoadResult.getFreightValueColumns(),
    			objLoadResult.getSurchargeValueColumns(),
    			objLoadResult.getSurchargeBaseColumns(),
    			objLoadResult.getEnterprise(),
    			strOperId,
    			isCheckDateConflic);
    }
    
	public ArrayList<RuleCheckReturn> setParam(FreightpriceColumns objFPColumns,
			List listFreightpricevalue, 
			List listSurchargevalue, 
			List listSurchargevaluebase, 
			List listEnterprise,
			String strOperId, 
			boolean isCheckDateConflict) throws Exception {
		if (StringUtility.isNull(objFPColumns.getFpepcode()))
			objFPColumns.setPspscode(IExpressPriceBasicData.PRICE_STATUS_NEW);
		m_objFPColumns = objFPColumns;
		m_listFreightpricevalue = listFreightpricevalue;
		m_listSurchargevalue = listSurchargevalue;
		m_listSurchargevaluebase = listSurchargevaluebase;
		m_listEnterprise = listEnterprise;
		m_strOperId = strOperId;
		// 检查日期是否冲突
		if (isCheckDateConflict) {
			ARuleDate objRuleDate = new FreightPriceDate();
			ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objFPColumns);
			if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
				m_objMFPDTrans = new ModifyFreightPriceDateTrans();
				m_objMFPDTrans.setParam(alRuleCheckReturn);
			}
			return alRuleCheckReturn;
		}
		return null;
	}
	
	public String getEpcode() {
		return m_strEpcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		TepExpressprice objTepExpressprice = null;
		TepFreightprice objTepFreightprice = null;
		if (m_objFPColumns == null) return;
		// 新增快递价格
		if (StringUtility.isNull(m_objFPColumns.getFpepcode())) {
			objTepExpressprice = new TepExpressprice();
			objTepFreightprice = new TepFreightprice();
			
			TdiOperator objTOP = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTepExpressprice.setTdiOperatorByEpOpIdCreate(objTOP);
			objTepExpressprice.setEpCreatedate(DateFormatUtility.getSysdate());
			m_objFPColumns.setPspscode(IExpressPriceBasicData.PRICE_STATUS_NEW);
		}
		// 修改价格
		else {
			objTepExpressprice = (TepExpressprice)objSession.load(TepExpressprice.class, 
					Long.parseLong(m_objFPColumns.getFpepcode()));
			objTepFreightprice = (TepFreightprice)objSession.load(TepFreightprice.class, 
					Long.parseLong(m_objFPColumns.getFpepcode()));
		}
		// 保存
		FreightPriceDemand.setExpressPriceByColumns(objTepExpressprice,
				m_objFPColumns, 
				m_strOperId, 
				objSession);
		objSession.save(objTepExpressprice);
		m_strEpcode = String.valueOf(objTepExpressprice.getEpCode());
		// 新增运费价格
		FreightPriceDemand.setFreightPriceByColumns(objTepFreightprice, 
				m_objFPColumns, 
				objSession);
		objTepFreightprice.setEpCode(objTepExpressprice.getEpCode());
		objTepFreightprice.setTepExpressprice(objTepExpressprice);
		objSession.save(objTepFreightprice);
		// 新增价格值
		SaveFreightPriceValueTrans objSFPVTrans = new SaveFreightPriceValueTrans();
		objSFPVTrans.setParam(m_listFreightpricevalue, 
				m_listSurchargevalue, 
				m_listSurchargevaluebase, 
				objTepFreightprice);
		objSFPVTrans.transaction(objSession);
		// 新增分公司
		SaveEnterpriseTransaction objSETrans = new SaveEnterpriseTransaction();
		objSETrans.setParam(m_listEnterprise, 
				objTepExpressprice);
		objSETrans.transaction(objSession);
		// 修改日期冲突的价格表
		if (m_objMFPDTrans != null)
			m_objMFPDTrans.transaction(objSession);
	}

}
