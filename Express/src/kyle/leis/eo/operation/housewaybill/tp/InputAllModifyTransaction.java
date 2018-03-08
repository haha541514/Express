package kyle.leis.eo.operation.housewaybill.tp;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.tp.AddCargoInfoTransaction;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.tp.SaveCWBPiecesTransaction;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.dax.InputDemand;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopHousewaybill;
import net.sf.hibernate.Session;

public class InputAllModifyTransaction extends AbstractTransaction {
	private ForinputallColumns m_objFIAColumns;
	private String m_strOperId;
	private List<Object> m_listCargo;
	private List m_listWaybillpieces;
	private Long m_lNewCwcode;
	private TopCorewaybill m_objCoreWayBill;
	private TopHousewaybill m_objHouseWayBill;
	
	public void setParam(String strOperId, 
			ForinputallColumns objFIAColumns, 
			List<Object> listCargo,
			List listWaybillpieces) throws Exception {
		m_strOperId = strOperId;
		m_objFIAColumns = objFIAColumns;
		m_listCargo = listCargo;
		m_listWaybillpieces = listWaybillpieces;
	}
	
	public Long getNewCwcode() {
		return m_lNewCwcode;
	}
	
	public TopCorewaybill getSavedCoreWayBill() {
		return m_objCoreWayBill;
	}
	
	public TopHousewaybill getSavedHouseWayBill() {
		return m_objHouseWayBill;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objFIAColumns != null && !StringUtility.isNull(m_objFIAColumns.getCwcode())) {
			// 运单主表更新
			TopCorewaybill objCoreWayBill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
					Long.parseLong(m_objFIAColumns.getCwcode()));
			BigDecimal cwOpIdModifier = new BigDecimal(m_strOperId);
			objCoreWayBill.setCwOpIdModifier(cwOpIdModifier);
			CorewaybillDemand.setCWBByInputAllColumns(objCoreWayBill, 
					m_objFIAColumns, 
					m_strOperId, 
					objSession);
			// 运单表更新
			TopHousewaybill objHouseWayBill = (TopHousewaybill)objSession.load(TopHousewaybill.class, 
					Long.parseLong(m_objFIAColumns.getCwcode()));
			if (objHouseWayBill.getHwOpIdRecord() == null) {
				objHouseWayBill.setHwOpIdRecord(Long.parseLong(m_strOperId));
				objHouseWayBill.setHwRecorddate(DateFormatUtility.getSysdate());				
			} else if (objHouseWayBill.getHwOpIdRecord() != null &&
					objHouseWayBill.getHwRecorddate() == null) {
				objHouseWayBill.setHwRecorddate(DateFormatUtility.getSysdate());
			}
			InputDemand.setHWBByInputAllColumns(objHouseWayBill, 
					m_objFIAColumns, 
					m_strOperId, 
					objSession);
			objSession.save(objCoreWayBill);
			objSession.save(objHouseWayBill);
			m_lNewCwcode = objCoreWayBill.getCwCode();
			m_objCoreWayBill = objCoreWayBill;
			m_objHouseWayBill = objHouseWayBill;
			// 更新发票信息
			AddCargoInfoTransaction objAddCIT = new AddCargoInfoTransaction();
			objAddCIT.setParam(m_listCargo, 
					m_objFIAColumns.getCwcode(), 
					objHouseWayBill);
			objAddCIT.transaction(objSession);
			// 更新子单
			SaveCWBPiecesTransaction objSaveCWBPiecesTrans = new SaveCWBPiecesTransaction();
			objSaveCWBPiecesTrans.setParam(m_listWaybillpieces, objCoreWayBill);
			objSaveCWBPiecesTrans.transaction(objSession);
		}
	}

}
