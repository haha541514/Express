package kyle.leis.eo.operation.housewaybill.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.tp.SaveIssueCollectionTrans;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.corewaybillpieces.tp.SaveCWBPiecesTransaction;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;

import kyle.leis.hi.TopBatchwaybill;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopHousewaybill;

public class SaveWaybillTransaction extends AbstractTransaction {
	private HousewaybillColumns m_objHwColumns;
	private List m_listCWPieces;
	private String m_strOperId;
	private String m_strWeightCheckBWCode;
	private TopCorewaybill m_objCoreWayBill;
	private Long m_lNewCwcode;
	private List m_listIssueColumns;
	private boolean m_isCheckweight = false;
	
	public void setSignOutParam(HousewaybillColumns objHwbColumns,
			String strBwcodeDeparture,
			List listCWPieces,
			String strOperId,
			String strSelfLabelcode) {
		objHwbColumns.setDbwbwcode(Long.parseLong(strBwcodeDeparture));
		boolean isAllPiecesSignout = true;
		if (!StringUtility.isNull(strSelfLabelcode)) {
			isAllPiecesSignout = CorewaybillpiecesDemand.isAllSignout(listCWPieces);
		}
		if (isAllPiecesSignout) {
			objHwbColumns.setCwscwscode(ICorewaybillBasicData.COREWAYBILL_STATUS_SIGNOUT);
			objHwbColumns.setHwhwsignoutdate(DateFormatUtility.getSysdate());
			objHwbColumns.setHwhwopidsignout(Long.parseLong(strOperId));
		}
		m_objHwColumns = objHwbColumns;
		m_strOperId = strOperId;
		m_listCWPieces = listCWPieces;
	}
	
	public void setSignInParam(HousewaybillColumns objHwColumns,
			List listCWPieces,
			List listIssueColumns,
			String strOperId) {
		m_objHwColumns = objHwColumns;
		m_listCWPieces = listCWPieces;
		m_strOperId = strOperId;
		m_listIssueColumns = listIssueColumns;
	}
	
	/**
	 * 收货出货
	 * @param objHwColumns
	 * @param strBwcodeDeparture
	 * @param listCWPieces
	 * @param listIssueColumns
	 * @param strOperId
	 */
	public void setSignInSignOutParam(HousewaybillColumns objHwColumns,
			String strBwcodeDeparture,
			List listCWPieces,
			List listIssueColumns,
			String strOperId,
			boolean isAutoSignout) {
		setSignInParam(objHwColumns,
				listCWPieces,
				listIssueColumns,
				strOperId);
		if (isAutoSignout) {
			setSignOutParam(m_objHwColumns, 
					strBwcodeDeparture,
					listCWPieces,
					strOperId,
					"");
		}
		objHwColumns.setHwhwsignindate(DateFormatUtility.getSysdate());
		objHwColumns.setHwhwsignoutdate(DateFormatUtility.getSysdate());
	}
	
	public void setCheckweight(HousewaybillColumns objHwColumns,
			List listCWPieces,
			List listIssueColumns,
			String strWeightCheckBWCode,
			String strOperId){
		m_objHwColumns = objHwColumns;
		m_listCWPieces = listCWPieces;
		m_strOperId = strOperId;
		m_listIssueColumns = listIssueColumns;
		m_strWeightCheckBWCode = strWeightCheckBWCode;
		m_isCheckweight = true;
	}
	
	public Long getNewCwcode() {
		return m_lNewCwcode;
	}
	
	public TopCorewaybill getSavedCorewaybill() {
		return m_objCoreWayBill;
	}
	
	public void transaction(Session objSession) throws Exception {
		String strCwcode = m_objHwColumns.getHwcwcode();
		TopCorewaybill objCoreWayBill = null;
		TopHousewaybill objHouseWayBill = null;
		// 新增或修改
		if (StringUtility.isNull(strCwcode)) {
			objCoreWayBill = new TopCorewaybill();
			objHouseWayBill = new TopHousewaybill();
			// 创建人、签入人等
			objCoreWayBill.setCwOpIdCreator(new BigDecimal(m_strOperId));
			objCoreWayBill.setCwCreatedate(DateFormatUtility.getSysdate());
			objHouseWayBill.setHwOpIdSignin(Long.parseLong(m_strOperId));
			objHouseWayBill.setHwSignindate(DateFormatUtility.getSysdate());			
		} else {
			objCoreWayBill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
					Long.parseLong(strCwcode));
			objHouseWayBill = (TopHousewaybill)objSession.load(TopHousewaybill.class, 
					Long.parseLong(strCwcode));
			if (objCoreWayBill.getTdiCorewaybillstatus().getCwsCode().equals("CHP")) {
				objHouseWayBill.setHwOpIdSignin(Long.parseLong(m_strOperId));
				objHouseWayBill.setHwSignindate(DateFormatUtility.getSysdate());				
			}
		}
		// 保存主单
		CorewaybillDemand.setCWBByHouseColumns(objCoreWayBill, 
				m_objHwColumns, 
				m_strOperId, 
				objSession);
		if (!StringUtility.isNull(m_strWeightCheckBWCode)) {
			TopBatchwaybill objBWBWeightCheck = (TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
					Long.parseLong(m_strWeightCheckBWCode));
			objCoreWayBill.setTopBatchwaybillByBwCodeWeightcheck(objBWBWeightCheck);			
			
		}		
		// 保存
		objSession.save(objCoreWayBill);
		m_lNewCwcode = objCoreWayBill.getCwCode();
		m_objCoreWayBill = objCoreWayBill;
		// 设置数据
		if (!StringUtility.isNull(m_objHwColumns.getHwhwopidweighting()))
			objHouseWayBill.setHwOpIdWeighting(Long.valueOf(m_objHwColumns.getHwhwopidweighting()));
		// 设置重量核查信息
		if (m_isCheckweight) {
			objHouseWayBill.setHwOpIdWeightcheck(Long.valueOf(m_objHwColumns.getHwhwopidweightcheck()));
			objHouseWayBill.setHwWeightcheckdate(DateFormatUtility.getSysdate());
			objHouseWayBill.setHwWeightcheckkind(m_objHwColumns.getHwhwweightcheckkind());
		}
		objHouseWayBill.setTopCorewaybill(objCoreWayBill);
		objHouseWayBill.setCwCode(objCoreWayBill.getCwCode());
		// 保存出货信息
		if (!StringUtility.isNull(m_objHwColumns.getHwhwsignoutdate())) {
			if (m_objHwColumns.getHwhwsignoutdate().startsWith("1900"))
				objHouseWayBill.setHwSignoutdate(null);
			else
				objHouseWayBill.setHwSignoutdate(DateFormatUtility.getStandardDate(m_objHwColumns.getHwhwsignoutdate()));
		}
		
		String strHwArrearsignout = "N";
		if (!StringUtility.isNull(m_objHwColumns.getHwhwarrearsignout()) &&
				m_objHwColumns.getHwhwarrearsignout().equals("Y")) {
			strHwArrearsignout = "Y";
		}
		objHouseWayBill.setHwArrearsignout(strHwArrearsignout);
		
		if (!StringUtility.isNull(m_objHwColumns.getHwhwopidsignout()))
			objHouseWayBill.setHwOpIdSignout(Long.parseLong(m_objHwColumns.getHwhwopidsignout()));
		// 保存分单
		objSession.save(objHouseWayBill);
		// 保存件数等
		SaveCWBPiecesTransaction objSaveCPTrans = new SaveCWBPiecesTransaction();
		objSaveCPTrans.setParam(m_listCWPieces, objCoreWayBill);
		objSaveCPTrans.transaction(objSession);
		if (m_listIssueColumns == null || m_listIssueColumns.size() < 1)
			return;
		String strSignInRemark = "";
		for (int i = 0; i < m_listIssueColumns.size(); i++) {
			IssueColumns objIssueColumns = (IssueColumns)m_listIssueColumns.get(i);
			objIssueColumns.setCwcwcode(m_lNewCwcode);
			strSignInRemark = strSignInRemark + objIssueColumns.getIsuiscontent();
			if (i < m_listIssueColumns.size() - 1)
				strSignInRemark = strSignInRemark + ";";
		}
		if (!StringUtility.isNull(strSignInRemark)) {
			objHouseWayBill.setHwRemark(strSignInRemark);
			objSession.update(objHouseWayBill);
		}
		// 保存问题
		SaveIssueCollectionTrans objSICTrans = new SaveIssueCollectionTrans();
		objSICTrans.setParam(m_listIssueColumns, m_strOperId);
		objSICTrans.transaction(objSession);
	}
}
