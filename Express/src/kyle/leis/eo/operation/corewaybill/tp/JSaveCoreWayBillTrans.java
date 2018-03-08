package kyle.leis.eo.operation.corewaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.da.TopcorewaybillTR;
import kyle.leis.eo.operation.corewaybill.dax.CoreWayBillSequence;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;

public class JSaveCoreWayBillTrans extends AbstractTransaction {

	private ForinputallColumns m_objFIAColumns;
	private String m_strOperId;
	private String m_strBwcodeArrival;
	private String m_strNewCwcode;
	private TopcorewaybillTR m_objTopcorewaybillTR;
	
	public String getNewCwcode() {
		return m_strNewCwcode;
	}
	
	public TopcorewaybillTR getSavedCorewaybillTR() {
		return m_objTopcorewaybillTR;
	}
	
	public void setParam(String strOperId,
			String strBwcodeArrival,
			ForinputallColumns objFIAColumns) {
		m_objFIAColumns = objFIAColumns;
		m_strOperId = strOperId;
		m_strBwcodeArrival = strBwcodeArrival;
	}
	
	public void transaction(Session objSession) throws Exception {
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		TopcorewaybillTR objTopcorewaybillTR = new TopcorewaybillTR();
		if (StringUtility.isNull(m_objFIAColumns.getCwcode())) {
			objTopcorewaybillTR.setCw_op_id_creator(m_strOperId);
			objTopcorewaybillTR.setCw_createdate(DateFormatUtility.getStandardSysdate());
		} else {
			objTopcorewaybillTR.setCw_codeCondition(m_objFIAColumns.getCwcode());
			//objTableAccess.selectRecord(objTopcorewaybillTR);
		}
		objTopcorewaybillTR.setCw_op_id_modifier(m_strOperId);
		// 新增主运单
		String strOriginCustomerewbcode = m_objFIAColumns.getCwcustomerewbcode();
		int iOrginCEWBIndex = strOriginCustomerewbcode.indexOf(",");
		if (iOrginCEWBIndex > 0) {
			m_objFIAColumns.setCwcustomerewbcode(strOriginCustomerewbcode.substring(0, iOrginCEWBIndex));
		}
		CorewaybillDemand.setCWBByInputAllColumns(objTopcorewaybillTR, 
				m_objFIAColumns, 
				m_strOperId, 
				objSession);
		if (StringUtility.isNull(objTopcorewaybillTR.getCw_batchwaybillsign()))
			objTopcorewaybillTR.setCw_batchwaybillsign("N");
		
		objTopcorewaybillTR.setBw_code_arrival(m_strBwcodeArrival);
		// 设置客户重量
		if (StringUtility.isNull(m_objFIAColumns.getCwcustomerchargeweight()))
			objTopcorewaybillTR.setCw_customerchargeweight(objTopcorewaybillTR.getCw_chargeweight());
		else
			objTopcorewaybillTR.setCw_customerchargeweight(m_objFIAColumns.getCwcustomerchargeweight());
		// 设置收货国家
		objTopcorewaybillTR.setDt_code_signin(objTopcorewaybillTR.getDt_code_destination());
		// 新增
		if (StringUtility.isNull(m_objFIAColumns.getCwcode())) {
			CoreWayBillSequence objCWBSequence = new CoreWayBillSequence();
			objTopcorewaybillTR.setCw_code(objCWBSequence.getNewSequencecode());
			objTableAccess.insertRecord(objTopcorewaybillTR);
			m_strNewCwcode = objTopcorewaybillTR.getCw_code();
		} else {
			objTableAccess.updateRecord(objTopcorewaybillTR);
			m_strNewCwcode = m_objFIAColumns.getCwcode();
		}
		m_objTopcorewaybillTR = objTopcorewaybillTR;
	}

}
