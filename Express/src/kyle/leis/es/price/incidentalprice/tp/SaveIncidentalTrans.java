package kyle.leis.es.price.incidentalprice.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.expressprice.tp.AModifyPriceDateTrans;
import kyle.leis.es.price.expressprice.tp.SaveEnterpriseTransaction;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDate;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDemand;
import kyle.leis.es.price.incidentalprice.dax.LoadIncidentalResult;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TepExpressprice;
import kyle.leis.hi.TepIncidentalprice;

public class SaveIncidentalTrans extends AbstractTransaction {
	private IncidentalpriceColumns m_objIPColumns;
	private List m_listIPValueColumns;
	private List m_listIPVBaseColumns;
	private List m_listIPVChannelColumns;
	private List m_listEnterprise;
	private String m_strOperId;
	private String m_strEpcode;
	private AModifyPriceDateTrans m_objMFPDTrans;
	
	public ArrayList<RuleCheckReturn> setConflictParam(LoadIncidentalResult objLIResult, 
			String strOperId,
			String strChangedStartDate,
			String strChangedEndDate,
			boolean isCheckDateConflict) throws Exception {
		IncidentalpriceColumns objIPColumns = objLIResult.getIPriceColumns();
		List listIPValueColumns = objLIResult.getIPValueColumns();
		List listIPVBaseColumns = objLIResult.getIPVBaseColumns();
		List listIPVChannelColumns = objLIResult.getIPVChannelColumns();
		objIPColumns.setEpepcode(null);
		objIPColumns.setEpepstartdate(DateFormatUtility.getStandardDate(strChangedStartDate));
		objIPColumns.setEpependdate(DateFormatUtility.getStandardDate(strChangedEndDate));
		
		return setParam(objIPColumns, 
				listIPValueColumns, 
				listIPVBaseColumns,
				listIPVChannelColumns,
				objLIResult.getEnterprise(),
				strOperId, 
				isCheckDateConflict);
	}
	
	public ArrayList<RuleCheckReturn> setParam(IncidentalpriceColumns objIPColumns,
			List listIPValueColumns,
			List listIPVBaseColumns,
			List listIPVChannelColumns,
			List listEnterprise,
			String strOperId, 
			boolean isCheckDateConflict) throws Exception {
		if (StringUtility.isNull(objIPColumns.getEpepcode()))
			objIPColumns.setPspscode(IExpressPriceBasicData.PRICE_STATUS_NEW);		
		m_objIPColumns = objIPColumns;
		m_listIPValueColumns = listIPValueColumns;
		m_listIPVBaseColumns = listIPVBaseColumns;
		m_listIPVChannelColumns = listIPVChannelColumns;
		m_listEnterprise = listEnterprise;
		
		m_strOperId = strOperId;
		// 检查日期是否冲突
		if (isCheckDateConflict) {
			ARuleDate objRuleDate = new IncidentalPriceDate();
			ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(m_objIPColumns);
			if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
				m_objMFPDTrans = new ModifyIncidentalPriceDateTrans();
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
		if (m_objIPColumns == null) return;
		
		TepExpressprice objTepExpressprice;
		TepIncidentalprice objTepIncidentalprice;
		// 新增
		if (StringUtility.isNull(m_objIPColumns.getEpepcode())) {
			objTepIncidentalprice = new TepIncidentalprice();
			objTepExpressprice = new TepExpressprice();
			
			TdiOperator objTOP = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTepExpressprice.setTdiOperatorByEpOpIdCreate(objTOP);
			objTepExpressprice.setEpCreatedate(DateFormatUtility.getSysdate());
			m_objIPColumns.setPspscode(IExpressPriceBasicData.PRICE_STATUS_NEW);
		}
		// 修改
		else {
			objTepExpressprice = (TepExpressprice)objSession.load(TepExpressprice.class, 
					Long.parseLong(m_objIPColumns.getEpepcode()));
			objTepIncidentalprice = (TepIncidentalprice)objSession.load(TepIncidentalprice.class, 
					Long.parseLong(m_objIPColumns.getEpepcode()));			
		}
		// 新增杂费价格表
		IncidentalPriceDemand.setExpressPriceByColumns(objTepExpressprice, 
				m_objIPColumns, 
				m_strOperId, 
				objSession);
		IncidentalPriceDemand.setIncidentalPriceByColumns(objTepIncidentalprice, 
				m_objIPColumns, 
				objSession);
		// 保存价格表
		objSession.save(objTepExpressprice);
		// 保存杂费价格表
		objTepIncidentalprice.setTepExpressprice(objTepExpressprice);
		objTepIncidentalprice.setEpCode(objTepExpressprice.getEpCode());
		objSession.save(objTepIncidentalprice);
		m_strEpcode = String.valueOf(objTepExpressprice.getEpCode());
		// 新增杂费价格值
		AddIncidentalPriceValueTrans objAddIPVTrans = new AddIncidentalPriceValueTrans();
		objAddIPVTrans.setParam(m_listIPValueColumns, 
				m_listIPVBaseColumns,
				m_listIPVChannelColumns,
				objTepIncidentalprice);
		objAddIPVTrans.transaction(objSession);
		// 新增分公司
		SaveEnterpriseTransaction objSETrans = new SaveEnterpriseTransaction();
		objSETrans.setParam(m_listEnterprise, 
				objTepExpressprice);
		objSETrans.transaction(objSession);		
		// 修改价格表日期
		if (m_objMFPDTrans != null)
			m_objMFPDTrans.transaction(objSession);
	}
	
}
