package kyle.leis.eo.operation.housewaybill.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwbvalueColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpackageColumns;
import kyle.leis.hi.TopBatchwaybill;
import kyle.leis.hi.TopBatchwaybillvalue;
import kyle.leis.hi.TopCorewaybill;

public class SavePackageWaybillTrans extends AbstractTransaction {
	
	private HousewaybillColumns m_objHwColumns;
	private List m_listCWPieces;
	private String m_strOperId;
	private Long m_lNewCwcode;
	private List m_listIssueColumns;
	private String m_strCwcode;
	private String m_strOldBrvid;
	private WaybillforpackageColumns m_objWFPColumns;
	
	public Long getNewCwcode() {
		return m_lNewCwcode;
	}	
	
	public void setParseParam(WaybillforpackageColumns objWFPColumns,
			HousewaybillColumns objHwColumns,
			List listCWPieces,
			List listIssueColumns,
			String strCwcode,
			String strOldBrvid,
			String strOperId) {
		m_objHwColumns = objHwColumns;
		m_listCWPieces = listCWPieces;
		m_strOperId = strOperId;
		m_listIssueColumns = listIssueColumns;
		m_objWFPColumns = objWFPColumns;
		m_strCwcode = strCwcode;
		m_strOldBrvid = strOldBrvid;
	}	
	
	
	
	public void setParam(WaybillforpackageColumns objWFPColumns,
			HousewaybillColumns objHwColumns,
			List listCWPieces,
			List listIssueColumns,
			String strOperId) {
		m_objHwColumns = objHwColumns;
		m_listCWPieces = listCWPieces;
		m_strOperId = strOperId;
		m_listIssueColumns = listIssueColumns;
		m_objWFPColumns = objWFPColumns;
	}

	public void transaction(Session objSession) throws Exception {
		// 保存运单
		SaveWaybillTransaction objSignInTrans = new SaveWaybillTransaction();
		objSignInTrans.setSignInParam(m_objHwColumns, 
				m_listCWPieces, 
				m_listIssueColumns, 
				m_strOperId);
		objSignInTrans.transaction(objSession);
		m_lNewCwcode = objSignInTrans.getNewCwcode();
		// 保存主单信息
		TopBatchwaybillvalue objTBWBV = null;
		String strCwcode = m_objHwColumns.getHwcwcode();
		String strBwcode = m_objHwColumns.getAbwbwcode();
		if (StringUtility.isNull(strBwcode))
			strBwcode = m_objHwColumns.getDbwbwcode();
		if (StringUtility.isNull(strCwcode)) {
			objTBWBV = new TopBatchwaybillvalue();
		} else {
			objTBWBV = (TopBatchwaybillvalue)objSession.load(TopBatchwaybillvalue.class, 
					Long.parseLong(m_objWFPColumns.getBwvbwbvid()));
		}
		objTBWBV.setBwbvBaglabelcode(m_objWFPColumns.getBwvbwbvbaglabelcode());
		objTBWBV.setBwbvIssuecontent(m_objWFPColumns.getBwvbwbvissuecontent());
		objTBWBV.setBwbvSerialno(m_objWFPColumns.getBwvbwbvserialno());
		objTBWBV.setBwbvZonename(m_objWFPColumns.getBwvbwbvzonename());
			
		objTBWBV.setTopBatchwaybill((TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
				Long.parseLong(strBwcode)));
		objTBWBV.setTopCorewaybill(objSignInTrans.getSavedCorewaybill());
			
		objSession.save(objTBWBV);
		// 保存拆分信息
		if (!StringUtility.isNull(m_strCwcode) && 
				!StringUtility.isNull(m_strOldBrvid)) {
			String[] astr = m_strCwcode.split(",");
			if (astr != null && astr.length > 0) {
				for (String str : astr) {
					if (!StringUtility.isNull(str)) {
						TopCorewaybill objCoreWayBill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
								Long.parseLong(str));
						objCoreWayBill.setTopBatchwaybillvalueByBwbvIdArrival(objTBWBV);
						objSession.save(objCoreWayBill);
					}
				}
			}
		}
		if (!StringUtility.isNull(m_strOldBrvid)) {
			// 原小包号码需要修改重量、票数
			SimplebatchwbvalueColumns objSBVC = BatchWayBillDemand.loadBWVCorewaybill(m_strOldBrvid);
			if (objSBVC == null) return;
			TopCorewaybill objCoreWayBill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
					Long.parseLong(objSBVC.getBwvcw_code()));
			BigDecimal objChargeweight = objCoreWayBill.getCwChargeweight().add(
					new BigDecimal("-1").multiply(new BigDecimal(m_objHwColumns.getCwcwchargeweight())));
			BigDecimal objBillcounts = new BigDecimal(objCoreWayBill.getCwBillcounts()).add(
					new BigDecimal("-1").multiply(new BigDecimal(m_objHwColumns.getCwcwbillcounts())));
			
			objCoreWayBill.setCwGrossweight(objChargeweight);
			objCoreWayBill.setCwChargeweight(objChargeweight);
			objCoreWayBill.setCwTransferchargeweight(objChargeweight);
			objCoreWayBill.setCwServerchargeweight(objChargeweight);
			
			objCoreWayBill.setCwBillcounts(objBillcounts.intValue());
		}
	}
	
	
}
