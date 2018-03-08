package kyle.leis.es.price.currency.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.currency.da.CurrencyColumns;
import kyle.leis.es.price.currency.dax.CurrencyPriceDate;
import kyle.leis.es.price.currency.dax.CurrencyPriceDemand;
import kyle.leis.es.price.currency.dax.LoadCurrencyResult;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TepCurrency;
import kyle.leis.hi.TepExpressprice;

public class SaveCurrencyTransaction extends AbstractTransaction {
	private CurrencyColumns m_objCurrencyColumns;
	private List m_listCurrencyValue;
	private String m_strOperId;
	private String m_strEpcode;
	private ModifyCYPriceDateTrans m_objMCYPDTrans;
	
    public ArrayList<RuleCheckReturn> setConflictParam(LoadCurrencyResult objLoadResult,
			String strOperId,
			String strChangedStartDate,
			String strChangedEndDate,
			boolean isCheckDateConflic) throws Exception {
    	CurrencyColumns objCurrencyColumns = objLoadResult.getCurrencycolumns();
    	objCurrencyColumns.setCuepcode(null);
    	objCurrencyColumns.setEpepstartdate(DateFormatUtility.getStandardDate(strChangedStartDate));
    	objCurrencyColumns.setEpependdate(DateFormatUtility.getStandardDate(strChangedEndDate));    	
    	
    	return setParam(objCurrencyColumns,
    			objLoadResult.getCurrencyvalue(),
    			strOperId,
    			isCheckDateConflic);
    }
	
	public ArrayList<RuleCheckReturn> setParam(CurrencyColumns objCurrencyColumns,
			List listCurrencyValue,
			String strOperId, 
			boolean isCheckDateConflict) throws Exception {
		m_objCurrencyColumns = objCurrencyColumns;
		m_listCurrencyValue = listCurrencyValue;
		m_strOperId = strOperId;
		// 检查日期是否冲突
		if (isCheckDateConflict) {
			ARuleDate objRuleDate = new CurrencyPriceDate();
			ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objCurrencyColumns);
			if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
				m_objMCYPDTrans = new ModifyCYPriceDateTrans();
				m_objMCYPDTrans.setParam(alRuleCheckReturn);
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
		TepCurrency objTepCurrency = null;
		if (m_objCurrencyColumns == null) return;
		// 新增快递价格
		if (StringUtility.isNull(m_objCurrencyColumns.getCuepcode())) {
			objTepExpressprice = new TepExpressprice();
			objTepCurrency = new TepCurrency();
			
			TdiOperator objTOP = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTepExpressprice.setTdiOperatorByEpOpIdCreate(objTOP);
			objTepExpressprice.setEpCreatedate(DateFormatUtility.getSysdate());
			m_objCurrencyColumns.setPspscode(IExpressPriceBasicData.PRICE_STATUS_NEW);
		}
		// 修改价格
		else {
			objTepExpressprice = (TepExpressprice)objSession.load(TepExpressprice.class, 
					Long.parseLong(m_objCurrencyColumns.getCuepcode()));
			objTepCurrency = (TepCurrency)objSession.load(TepCurrency.class, 
					Long.parseLong(m_objCurrencyColumns.getCuepcode()));
		}
		// 保存
		CurrencyPriceDemand.setExpressPriceByColumns(objTepExpressprice,
				m_objCurrencyColumns, 
				m_strOperId, 
				objSession);
		objSession.save(objTepExpressprice);
		m_strEpcode = String.valueOf(objTepExpressprice.getEpCode());
		// 新增汇率价格表
		CurrencyPriceDemand.setCurrencyByColumns(objTepCurrency, 
				m_objCurrencyColumns, 
				objSession);
		objTepCurrency.setEpCode(objTepExpressprice.getEpCode());
		objTepCurrency.setTepExpressprice(objTepExpressprice);
		objSession.save(objTepCurrency);
		// 新增汇率价格值
		SaveCYValueTrans objSaveCYValueTrans = new SaveCYValueTrans();
		objSaveCYValueTrans.setParam(m_listCurrencyValue, 
				objTepCurrency);
		objSaveCYValueTrans.transaction(objSession);
		// 修改日期冲突的价格表
		if (m_objMCYPDTrans != null)
			m_objMCYPDTrans.transaction(objSession);		
	}

}
