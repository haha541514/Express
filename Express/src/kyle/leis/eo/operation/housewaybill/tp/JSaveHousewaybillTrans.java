package kyle.leis.eo.operation.housewaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.da.TopcorewaybillTR;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.TophousewaybillTR;
import kyle.leis.eo.operation.housewaybill.dax.InputDemand;

public class JSaveHousewaybillTrans extends AbstractTransaction {

	private ForinputallColumns m_objFIAColumns;
	private String m_strOperId;
	private String m_strCwcode;	
	private TopcorewaybillTR m_objTopcorewaybillTR;
	
	public void setParam(String strOperId,
			String strCwcode,
			TopcorewaybillTR objTopcorewaybillTR,
			ForinputallColumns objFIAColumns) {
		m_objFIAColumns = objFIAColumns;
		m_strOperId = strOperId;
		m_strCwcode = strCwcode;
		m_objTopcorewaybillTR = objTopcorewaybillTR;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		// 新增运单表
		TophousewaybillTR objTophousewaybillTR = new TophousewaybillTR();
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		if (!StringUtility.isNull(m_objFIAColumns.getCwcode())) {
			objTophousewaybillTR.setCw_codeCondition(m_strCwcode);
		} else {
			objTophousewaybillTR.setCw_code(m_strCwcode);
		}
		InputDemand.setHWBByInputAllColumns(objTophousewaybillTR, 
				m_objFIAColumns, 
				m_strOperId);
		// 网上下单需要设置制单人
		if (m_objTopcorewaybillTR.getCws_code().equals("CTS") &&
				!StringUtility.isNull(objTophousewaybillTR.getHw_consigneeaddress1())) {
			objTophousewaybillTR.setHw_op_id_record(m_strOperId);
		}	
		if (StringUtility.isNull(objTophousewaybillTR.getHw_signindate())) {
			if (StringUtility.isNull(m_objFIAColumns.getAdddate()))
				objTophousewaybillTR.setHw_signindate(DateFormatUtility.getStandardSysdate());
			else
				objTophousewaybillTR.setHw_signindate(m_objFIAColumns.getAdddate());
		}
		String strCwscode = m_objTopcorewaybillTR.getCws_code();
		if (!StringUtility.isNull(strCwscode) && 
				!"CTS".equals(strCwscode) && 
				!"CHD".equals(strCwscode) && 
				!"CHP".equals(strCwscode)) {
			if (StringUtility.isNull(objTophousewaybillTR.getHw_signoutdate())) {
				objTophousewaybillTR.setHw_signoutdate(m_objFIAColumns.getAdddate());
			}
		}
		if (StringUtility.isNull(m_objFIAColumns.getCwcode())) {
			objTableAccess.insertRecord(objTophousewaybillTR);
		} else {
			objTableAccess.updateRecord(objTophousewaybillTR);
		}	
	}

}
