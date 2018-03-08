package kyle.leis.eo.operation.batchwaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.dax.IBatchWayBillBasicData;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TopBatchwaybill;

public class SaveBatchwaybillTrans extends AbstractTransaction{
	private BatchwaybillColumns m_objBWColumns;
	private String m_strOperId;
	
	private TopBatchwaybill m_objNewBatchWayBill;
	private Long m_lNewBwcode;
	
	/**
	 * 设置到货批次的参数
	 * @param strComp_id
	 * @param strChn_code
	 * @param strAdd_date
	 * @param strEE_Code
	 * @param strOperId
	 */
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
	
	public void setDepartureParam(String strCo_code,
			String strChn_code,
			String strAdd_date,
			String strEE_Code,
			String strOperId,
			String strBW_Remark) throws Exception {
		String strAdt_code = "D";
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
	
	public TopBatchwaybill getNewBatchWayBill() {
		return m_objNewBatchWayBill;
	}
	
	public Long getNewBwcode() {
		return m_lNewBwcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objBWColumns == null) return;
		// 新增或修改
		TopBatchwaybill objTopBatchwaybill = null;
		if (StringUtility.isNull(m_objBWColumns.getBwbwcode())) {
			objTopBatchwaybill = new TopBatchwaybill();
			// 创建人和创建日期
			objTopBatchwaybill.setBwCreatedate(DateFormatUtility.getSysdate());
			TdiOperator objOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTopBatchwaybill.setTdiOperatorByBwOpIdCreate(objOperator);
			m_objBWColumns.setBwsbwscode(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_NEW);
			objTopBatchwaybill.setBwBatchnumber(m_objBWColumns.getBwbwbatchnumber());
		}
		else {
			objTopBatchwaybill = (TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
					Long.parseLong(m_objBWColumns.getBwbwcode()));
		}
		BatchWayBillDemand.setBatchwaybillByColumns(m_objBWColumns, 
				objTopBatchwaybill, 
				m_strOperId, 
				objSession);
		if (StringUtility.isNull(m_objBWColumns.getBwbwcode()))
			objTopBatchwaybill.setBwLabelcode(BatchWayBillDemand.getBWLabelCode(objTopBatchwaybill));
		// 保存
		objSession.save(objTopBatchwaybill);
		// 返回值
		m_objNewBatchWayBill = objTopBatchwaybill;
		m_lNewBwcode = objTopBatchwaybill.getBwCode();
	}
}