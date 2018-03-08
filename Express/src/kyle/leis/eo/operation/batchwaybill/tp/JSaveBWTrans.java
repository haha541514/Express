package kyle.leis.eo.operation.batchwaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.da.TopbatchwaybillTR;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillSequence;
import kyle.leis.eo.operation.batchwaybill.dax.IBatchWayBillBasicData;

public class JSaveBWTrans extends AbstractTransaction {

	private BatchwaybillColumns m_objBWColumns;
	private String m_strOperId;	
	private String m_strNewBwcode;
	
	public String getNewBwcode() {
		return m_strNewBwcode;
	}
	
	public void setArrivalParam(String strCo_code,
			String strChn_code,
			String strAdd_date,
			String strEE_Code,
			String strOperId,
			String strBW_Remark) throws Exception {
		String strAdt_code = "A";
		this.setParam(strAdt_code, 
				strChn_code, 
				strCo_code, 
				strAdd_date, 
				strEE_Code, 
				strOperId,
				null);
	}

	public void setParam(String strAdt_code,
			String strChn_code,
			String strCo_code,
			String strAdd_date,
			String strEE_Code,
			String strOperId,
			String strBW_Remark) throws Exception {
		BatchwaybillColumns objBWColumns = new BatchwaybillColumns();
		
		objBWColumns.setAdtadtcode(strAdt_code);
		objBWColumns.setChnchncode(strChn_code);
		objBWColumns.setCococode(strCo_code);
		objBWColumns.setBwadddate(DateFormatUtility.getStandardDate(strAdd_date));
		objBWColumns.setEeeecode(strEE_Code);
		objBWColumns.setBwbwremark(strBW_Remark);
		// 获得批次号码
		String strBwbatchnumber = BatchWayBillDemand.getNewBatchnumber(strChn_code, 
				strCo_code, 
				strEE_Code, 
				strAdd_date, 
				strAdt_code);
		objBWColumns.setBwbwbatchnumber(strBwbatchnumber);
		
		setParam(objBWColumns, strOperId);
	}
	
	public void setParam(BatchwaybillColumns objBWColumns, 
			String strOperId) {
		m_strOperId = strOperId;
		m_objBWColumns = objBWColumns;
	}	
	
	
	public void transaction(Session objSession) throws Exception {
		if (m_objBWColumns == null) return;
		TopbatchwaybillTR objTopbatchwaybillTR = null;
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		
		if (StringUtility.isNull(m_objBWColumns.getBwbwcode())) {
			objTopbatchwaybillTR = new TopbatchwaybillTR();
			// 创建人和创建日期
			objTopbatchwaybillTR.setBw_createdate(DateFormatUtility.getStandardSysdate());
			objTopbatchwaybillTR.setBw_op_id_create(m_strOperId);
			objTopbatchwaybillTR.setBws_code(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_NEW);
			objTopbatchwaybillTR.setBw_batchnumber(m_objBWColumns.getBwbwbatchnumber());
			
			BatchWayBillSequence objBWBS = new BatchWayBillSequence();
			objTopbatchwaybillTR.setBw_code(objBWBS.getNewSequencecode());
		}
		else {
			objTopbatchwaybillTR.setBw_codeCondition(m_objBWColumns.getBwbwcode());
			objTableAccess.selectRecord(objTopbatchwaybillTR);
		}
		BatchWayBillDemand.setBatchwaybillByColumns(m_objBWColumns, 
				objTopbatchwaybillTR, 
				m_strOperId, 
				objSession);
		// 新增
		if (StringUtility.isNull(m_objBWColumns.getBwbwcode())) {
			objTopbatchwaybillTR.setBw_labelcode(BatchWayBillDemand.getBWLabelCode(objTopbatchwaybillTR, objSession));
			objTableAccess.insertRecord(objTopbatchwaybillTR);
		} else {
			objTableAccess.updateRecord(objTopbatchwaybillTR);
		}
		// 返回值
		m_strNewBwcode = objTopbatchwaybillTR.getBw_code();
		
	}

}
