package kyle.leis.es.price.pricegroup.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDate;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDemand;
import kyle.leis.es.price.pricegroup.dax.LoadCustomergroupResult;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TepCustomerpricegroup;
import kyle.leis.hi.TepExpressprice;

public class SaveCustomerPricegroupTrans extends AbstractTransaction {
	private CustomerpricegroupColumns m_objCPGColumns;
	private List m_listCPGValueColumns;
	private String m_strOperId;
	private String m_strEpcode;
	private ModifyCustomerPriceDateTrans m_objMCPDTrans;
	
	public ArrayList<RuleCheckReturn> setConflictParam(LoadCustomergroupResult objLCPGResult,
			String strOperId, 
			String strChangedStartDate,
			String strChangedEndDate,
			boolean isCheckDateConflict) throws Exception {
		CustomerpricegroupColumns objCPGColumns = objLCPGResult.getCPGColumns();
		objCPGColumns.setCpgepcode(null);
		objCPGColumns.setEpepstartdate(DateFormatUtility.getStandardDate(strChangedStartDate));
		objCPGColumns.setEpependdate(DateFormatUtility.getStandardDate(strChangedEndDate));
		return setParam(objCPGColumns,
				objLCPGResult.getCPGValueColumns(),
				strOperId,
				isCheckDateConflict);
	}
	
	public ArrayList<RuleCheckReturn> setParam(CustomerpricegroupColumns objCPGColumns,
			List listCPGValueColumns,
			String strOperId, 
			boolean isCheckDateConflict) throws Exception {
		m_objCPGColumns = objCPGColumns;
		m_listCPGValueColumns = listCPGValueColumns;
		m_strOperId = strOperId;
		// ��������Ƿ��ͻ
		if (isCheckDateConflict) {
			ARuleDate objRuleDate = new CustomerPricegroupDate();
			ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(m_objCPGColumns);
			if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
				m_objMCPDTrans = new ModifyCustomerPriceDateTrans();
				m_objMCPDTrans.setParam(alRuleCheckReturn);
			}
			return alRuleCheckReturn;
		}
		return null;		
	}
	
	public String getEpcode() {
		return m_strEpcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objCPGColumns == null) return;
		
		String strEpcode = m_objCPGColumns.getCpgepcode();
		TepExpressprice objTepExpressprice = null;
		TepCustomerpricegroup objTCustomerpricegroup = null;
		
		// �����۸�������޸�
		if (StringUtility.isNull(strEpcode)) {
			objTepExpressprice = new TepExpressprice();
			objTCustomerpricegroup = new TepCustomerpricegroup();
			
			TdiOperator objTOP = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTepExpressprice.setTdiOperatorByEpOpIdCreate(objTOP);
			objTepExpressprice.setEpCreatedate(DateFormatUtility.getSysdate());
			//m_objCPGColumns.setPspscode(IExpressPriceBasicData.PRICE_STATUS_NEW);
		} else {
			objTepExpressprice = (TepExpressprice)objSession.load(TepExpressprice.class, 
					Long.parseLong(strEpcode));
			objTCustomerpricegroup = (TepCustomerpricegroup)objSession.load(TepCustomerpricegroup.class, 
					Long.parseLong(strEpcode));				
		}
		// ���ü۸������
		CustomerPricegroupDemand.setExpressPriceByColumns(objTepExpressprice, 
				m_objCPGColumns, 
				m_strOperId, 
				objSession);
		// ���ÿͻ��۸�������
		CustomerPricegroupDemand.setCustomerPricegroupByColumns(objTCustomerpricegroup, 
				m_objCPGColumns, 
				objSession);
		
		// ����۸��
		objSession.save(objTepExpressprice);
		m_strEpcode = String.valueOf(objTepExpressprice.getEpCode());
		// ����ͻ��۸���
		objTCustomerpricegroup.setEpCode(Long.parseLong(m_strEpcode));
		objTCustomerpricegroup.setTepExpressprice(objTepExpressprice);
		objSession.save(objTCustomerpricegroup);
		// ����ͻ��۸�ֵ
		AddCustomerPricevalueTrans objAddCPVTrans = new AddCustomerPricevalueTrans();
		objAddCPVTrans.setParam(m_listCPGValueColumns, objTCustomerpricegroup);
		objAddCPVTrans.transaction(objSession);
		// �޸�����
		if (m_objMCPDTrans != null)
			m_objMCPDTrans.transaction(objSession);
	}
	
}
