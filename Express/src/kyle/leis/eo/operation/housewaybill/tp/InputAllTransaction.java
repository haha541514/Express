package kyle.leis.eo.operation.housewaybill.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.tp.SaveBatchwaybillTrans;
import kyle.leis.eo.operation.cargoinfo.tp.AddCargoInfoTransaction;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillcode.tp.SaveCorewaybillcodeTrans;
import kyle.leis.eo.operation.corewaybillpieces.tp.SaveCWBPiecesTransaction;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.dax.InputDemand;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPredictwaybillstatusDC;
import kyle.leis.hi.TopBatchwaybill;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopHousewaybill;
import kyle.leis.hi.TopPredictwaybill;

public class InputAllTransaction extends AbstractTransaction {
	private ForinputallColumns m_objFIAColumns;
	private String m_strOperId;
	private String m_strSavedCwcode;
	private List m_listCargo;
	private List m_listWaybillPieces;
	private PredictwaybillColumns m_objPredictwaybillColumns;
	
	public void setParam(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces,
			PredictwaybillColumns objPredictwaybillColumns) 
	throws Exception {
		// 创建到货总单
		setParam(strOperId, objFIAColumns, listCargo, listWaybillPieces);
		m_objPredictwaybillColumns = objPredictwaybillColumns;
	}	
	
	public void setParam(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces) 
	throws Exception {
		// 创建到货总单
		m_objFIAColumns = objFIAColumns;
		m_strOperId = strOperId;
		m_listCargo = listCargo;
		m_listWaybillPieces = listWaybillPieces;
	}
	
	public String getSavedCwcode() {
		return m_strSavedCwcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		TopBatchwaybill objArrivalBatchwaybill = null;
		if (StringUtility.isNull(m_objFIAColumns.getBwcode())) {
			SimplebatchwaybillColumns objSBWBColumns = BatchWayBillDemand.getLatestUnCompleteBatchwaybill(m_objFIAColumns.getChncode_Cwcuschn(), 
					m_objFIAColumns.getCocode(), 
					m_objFIAColumns.getEecode(), 
					m_objFIAColumns.getAdddate(), 
					"A");
			if (objSBWBColumns != null &&
					!StringUtility.isNull(objSBWBColumns.getBwbwcode())) {
				objArrivalBatchwaybill = (TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
						Long.parseLong(objSBWBColumns.getBwbwcode()));
			} else {
				// 保存
				SaveBatchwaybillTrans objAddBWT = new SaveBatchwaybillTrans();
				objAddBWT.setArrivalParam(m_objFIAColumns.getCocode(), 
						m_objFIAColumns.getChncode_Cwcuschn(), 
						m_objFIAColumns.getAdddate(), 
						m_objFIAColumns.getEecode(), 
						m_strOperId, 
						null);
				objAddBWT.transaction(objSession);
				objArrivalBatchwaybill = objAddBWT.getNewBatchWayBill();
			}
		} else {
			// 如果存在到货主单则使用此主单
			objArrivalBatchwaybill = (TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
					Long.parseLong(m_objFIAColumns.getBwcode()));
		}
		if (m_objFIAColumns != null) {
			// 新增运单主表
			BigDecimal cwOpIdModifier = new BigDecimal(m_strOperId);
			TopCorewaybill objCoreWayBill = null;
			TopHousewaybill objHouseWayBill = null;
			String strOrignCwscode = "";
			if (StringUtility.isNull(m_objFIAColumns.getCwcode())) {
				objCoreWayBill = new TopCorewaybill();
				objHouseWayBill = new TopHousewaybill();
				objCoreWayBill.setCwOpIdCreator(cwOpIdModifier);
				objCoreWayBill.setCwCreatedate(DateFormatUtility.getSysdate());
			} else {
				objCoreWayBill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
						Long.parseLong(m_objFIAColumns.getCwcode()));
				objHouseWayBill = (TopHousewaybill)objSession.load(TopHousewaybill.class, 
						Long.parseLong(m_objFIAColumns.getCwcode()));
				strOrignCwscode = objCoreWayBill.getTdiCorewaybillstatus().getCwsCode();
			}
			objCoreWayBill.setCwOpIdModifier(cwOpIdModifier);
			// 新增主运单
			String strOriginCustomerewbcode = m_objFIAColumns.getCwcustomerewbcode();
			int iOrginCEWBIndex = strOriginCustomerewbcode.indexOf(",");
			if (iOrginCEWBIndex > 0) {
				m_objFIAColumns.setCwcustomerewbcode(strOriginCustomerewbcode.substring(0, iOrginCEWBIndex));
			}
			CorewaybillDemand.setCWBByInputAllColumns(objCoreWayBill, 
					m_objFIAColumns, 
					m_strOperId, 
					objSession);
			if (StringUtility.isNull(objCoreWayBill.getCwBatchwaybillsign()))
				objCoreWayBill.setCwBatchwaybillsign("N");
			
			objCoreWayBill.setTopBatchwaybillByBwCodeArrival(objArrivalBatchwaybill);
			
			if (objCoreWayBill.getTcoCorporationByCoCodeCustomer() == null)
				objCoreWayBill.setTcoCorporationByCoCodeCustomer(objArrivalBatchwaybill.getTcoCorporation());
			// 设置客户重量
			if (StringUtility.isNull(m_objFIAColumns.getCwcustomerchargeweight()))
				objCoreWayBill.setCwCustomerchargeweight(objCoreWayBill.getCwChargeweight());
			else
				objCoreWayBill.setCwCustomerchargeweight(new BigDecimal(m_objFIAColumns.getCwcustomerchargeweight()));
			// 设置收货国家
			objCoreWayBill.setTdiDistrictByDtCodeSignin(objCoreWayBill.getTdiDistrictByDtCodeDestination());
			objSession.save(objCoreWayBill);
			// 新增运单表
			m_strSavedCwcode = String.valueOf(objCoreWayBill.getCwCode());
			
			objHouseWayBill.setCwCode(objCoreWayBill.getCwCode());
			objHouseWayBill.setTopCorewaybill(objCoreWayBill);
			InputDemand.setHWBByInputAllColumns(objHouseWayBill, 
					m_objFIAColumns, 
					m_strOperId, 
					objSession);
			// 网上下单需要设置制单人
			if (objCoreWayBill.getTdiCorewaybillstatus().getCwsCode().equals("CTS") &&
					!StringUtility.isNull(objHouseWayBill.getHwConsigneeaddress1())) {
				objHouseWayBill.setHwOpIdRecord(Long.parseLong(m_strOperId));
			}	
			// 制单日期
			if (("CTS".equals(strOrignCwscode) || 
					"CHD".equals(strOrignCwscode) || 
					StringUtility.isNull(strOrignCwscode) ||
					"CHP".equals(strOrignCwscode)) && 
					"IP".equals(objCoreWayBill.getTdiCorewaybillstatus().getCwsCode())) {
				objHouseWayBill.setHwRecorddate(DateFormatUtility.getSysdate());
				objHouseWayBill.setHwOpIdRecord(Long.parseLong(m_strOperId));
				objHouseWayBill.setHwOpIdSignin(Long.parseLong(m_strOperId));
			}
			if (objHouseWayBill.getHwSignindate() == null || 
					objHouseWayBill.getHwSignindate().compareTo(DateFormatUtility.getStandardDate("1900-01-01 00:00:00")) <= 0) {
				if (StringUtility.isNull(m_objFIAColumns.getAdddate()))
					objHouseWayBill.setHwSignindate(DateFormatUtility.getSysdate());
				else
					objHouseWayBill.setHwSignindate(DateFormatUtility.getStandardDate(m_objFIAColumns.getAdddate()));
			}
			if (!StringUtility.isNull(strOrignCwscode) && 
					!"CTS".equals(strOrignCwscode) && 
					!"CHD".equals(strOrignCwscode) && 
					!"CHP".equals(strOrignCwscode)) {
				if (objHouseWayBill.getHwSignoutdate() == null || 
						objHouseWayBill.getHwSignoutdate().compareTo(DateFormatUtility.getStandardDate("1900-01-01 00:00:00")) <= 0) {
					objHouseWayBill.setHwSignoutdate(DateFormatUtility.getStandardDate(m_objFIAColumns.getAdddate()));
				}
			}
			objSession.save(objHouseWayBill);
			
			// 保存件数等
			SaveCWBPiecesTransaction objSaveCPTrans = new SaveCWBPiecesTransaction();
			objSaveCPTrans.setParam(m_listWaybillPieces, objCoreWayBill);
			objSaveCPTrans.transaction(objSession);			
			
			// 新增发票信息
			if (m_listCargo != null && m_listCargo.size() > 0) {
				AddCargoInfoTransaction objAddCIT = new AddCargoInfoTransaction();
				objAddCIT.setParam(m_listCargo, 
						String.valueOf(objCoreWayBill.getCwCode()), 
						objHouseWayBill);
				objAddCIT.transaction(objSession);
			}
			// 保存多个运单号
			String strSplitCustomerewbcode = strOriginCustomerewbcode.substring(iOrginCEWBIndex + 1);
			if (iOrginCEWBIndex > 0 && !StringUtility.isNull(strSplitCustomerewbcode)) {
				SaveCorewaybillcodeTrans objSCCT = new SaveCorewaybillcodeTrans();
				objSCCT.setParam(objCoreWayBill, strSplitCustomerewbcode);
				objSCCT.transaction(objSession);				
			}
			// 保存预报信息
			if (m_objPredictwaybillColumns != null) {
				TopPredictwaybill objTopPredictwaybill = (TopPredictwaybill)objSession.load(TopPredictwaybill.class, 
						Long.parseLong(m_objPredictwaybillColumns.getPwbpwb_code()));
				objTopPredictwaybill.setTopCorewaybill(objCoreWayBill);
				objTopPredictwaybill.setTdiPredictwaybillstatus(TdiPredictwaybillstatusDC.loadByKey("CHU"));
				objTopPredictwaybill.setTdiOperatorByOpIdDeclarer(TdiOperatorDC.loadByKey(m_strOperId));
				objTopPredictwaybill.setPwbDeclaredate(DateFormatUtility.getSysdate());				
				objSession.save(objTopPredictwaybill);
				m_objPredictwaybillColumns.setPwbspwbs_code("CHU");
				m_objPredictwaybillColumns.setPwbspwbs_name("已上传");
				m_objPredictwaybillColumns.setPwbpwb_orderid(objCoreWayBill.getCwServerewbcode());
			}
		}
	}
}
