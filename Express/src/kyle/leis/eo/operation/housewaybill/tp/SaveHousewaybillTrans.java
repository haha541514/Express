package kyle.leis.eo.operation.housewaybill.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.corewaybillpieces.tp.SaveCWBPiecesTransaction;
import kyle.leis.es.price.zone.da.ZonevalueColumns;
import kyle.leis.es.price.zone.da.ZonevalueCondition;
import kyle.leis.es.price.zone.dax.ZoneDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCorewaybillstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPaymentmodeDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiCorewaybillstatus;
import kyle.leis.hi.TdiPaymentmode;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopHousewaybill;

public class SaveHousewaybillTrans extends AbstractTransaction {
	private TopHousewaybill m_objTHousewaybill;
	private TopCorewaybill m_objTopCorewaybill;
	private List m_listCorewaybillpieces;
	
	/**
	 * 作废运单
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setEliminateParam(String strCwcode, 
			String strOperId) throws Exception {
		setCWStatusParam(strCwcode, "EL", strOperId);
	}
	
	
	public void setCWStatusParam(String strCwcode,
			String strCwscode,
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		TdiCorewaybillstatus objTCWBS = TdiCorewaybillstatusDC.loadByKey(strCwscode);
		m_objTopCorewaybill.setTdiCorewaybillstatus(objTCWBS);
		m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
		if (strCwscode.equals("CHP")) {
			m_objTHousewaybill.setHwCustomerlabelprintdate(DateFormatUtility.getSysdate());
		}
		if (strCwscode.equals("CHD")) {
			m_objTHousewaybill.setHwCustomerdeclaredate(DateFormatUtility.getSysdate());
		}			
	}	
	
	
	/**
	 * 撤销出货
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setUndoSignOutParam(String strCwcode, 
			String strOperId,
			boolean bResetChangeEWBSign) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		// 撤销袋号
		m_listCorewaybillpieces = CorewaybillpiecesDemand.load(strCwcode);
		if (m_listCorewaybillpieces != null && m_listCorewaybillpieces.size() > 0) {
			for (int i = 0; i < m_listCorewaybillpieces.size(); i++) {
				CorewaybillpiecesColumns objCWPC = (CorewaybillpiecesColumns)m_listCorewaybillpieces.get(i);
				objCWPC.setCpcpbaglabelcode("");
				objCWPC.setCwscwscode("");
			}
		}
		
		if (m_objTopCorewaybill.getTopBatchwaybillByBwCodeDeparture() != null) {
			// 运单状态为制单
			TdiCorewaybillstatus objTCWBS = TdiCorewaybillstatusDC.loadByKey("IP");
			m_objTopCorewaybill.setTdiCorewaybillstatus(objTCWBS);			
			m_objTopCorewaybill.setTopBatchwaybillByBwCodeDeparture(null);
			m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
			
			m_objTHousewaybill.setHwSignoutdate(null);
			m_objTHousewaybill.setHwOpIdSignout(null);
			
			if (m_objTopCorewaybill.getTopBatchwaybillvalueByBwbvIdDeparture() != null) {
				m_objTopCorewaybill.setTopBatchwaybillvalueByBwbvIdDeparture(null);
			}
		}
		if (bResetChangeEWBSign) {
			m_objTHousewaybill.setHwServerewbchangedsign("N");
			m_objTHousewaybill.setHwChannelmasteraccount(null);
			m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
		}
	}
	
	
	/**
	 * 清空服务渠道
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setServerChannelNullParam(String strCwcode, 
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		// 只有未出货且服务渠道不为空时才清空渠道
		if (m_objTopCorewaybill.getTopBatchwaybillByBwCodeDeparture() == null &&
				m_objTopCorewaybill.getTchnChannelByChnCodeSupplier() != null) {
			m_objTopCorewaybill.setTchnChannelByChnCodeSupplier(null);
			m_objTopCorewaybill.setTcoCorporationByCoCodeSupplier(null);
			m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
		} else {
			m_objTopCorewaybill = null;
			m_objTHousewaybill = null;
		}
	}
	
	/**
	 * 修改服务渠道
	 * @param strCwcode
	 * @param strChncode
	 * @param strOperId
	 */
	public void setModifyServerChannel(String strCwcode, 
			String strChncode, 
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		// 只有未出货时才允许修改服务渠道
		if (m_objTopCorewaybill.getTopBatchwaybillByBwCodeDeparture() == null) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
			m_objTopCorewaybill.setTchnChannelByChnCodeSupplier(objTchnChannel);
			m_objTopCorewaybill.setTcoCorporationByCoCodeSupplier(objTchnChannel.getTcoCorporation());
			m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
		} else {
			m_objTopCorewaybill = null;
			m_objTHousewaybill = null;
		}		
	}
	
	
	/**
	 * 打印Label
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setPrintlabelParam(String strCwcode,
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		Integer iLabelprinttimes = m_objTHousewaybill.getHwLabelprinttimes();
		if (iLabelprinttimes == null)
			iLabelprinttimes = 0;
		m_objTHousewaybill.setHwLabelprinttimes(iLabelprinttimes + 1);
		m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
	}
	
	public void setAttacheinfosignParam(String strCwcode,
			String strAttacheinfosign,
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		
		m_objTHousewaybill.setHwAttacheinfosign(strAttacheinfosign);
		m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
	}	
	
	
	
	/**
	 * 打印海关条码
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setPrintCustomslabelParam(String strCwcode,
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		m_objTHousewaybill.setHwCustomslabelprintsign("Y");
		m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
	}
	
	/**
	 * 打印发票次数
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	
	public void setPrintCargoprinttimesParam(String strCwcode,
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		Integer iCargoprinttimes = m_objTHousewaybill.getHwCargoprinttimes();
		if (iCargoprinttimes == null)
			iCargoprinttimes = 0;
		m_objTHousewaybill.setHwCargoprinttimes(iCargoprinttimes + 1);
		m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));
	}
	/**
	 * 更新分区
	 * @param strZncode
	 * @param iZnvId
	 * @throws Exception
	 */
	public void setModifyZnvIdParam(String strCwcode,
			String strZncode, 
			int iZnvId) throws Exception {
		// 查询分区ID对应的名称
		ZonevalueCondition objZnvCondition = new ZonevalueCondition();
		objZnvCondition.setUseCacheSign(true);
		objZnvCondition.setZncode(strZncode);
		objZnvCondition.setZnvid(String.valueOf(iZnvId));
		List objList = ZoneDemand.queryZoneValue(objZnvCondition);
		if (objList == null || objList.size() < 1) return;
		// 分区名称
		ZonevalueColumns objZonevalueColumns = (ZonevalueColumns)objList.get(0);
		// 设置分区属性
		loadByCwcode(strCwcode);
		m_objTopCorewaybill.setZnvName(objZonevalueColumns.getZnvznvname());
	}
	
	
	private void loadByCwcode(String strCwcode) throws Exception {
		m_objTHousewaybill = (TopHousewaybill)HSingleQuery.load(TopHousewaybill.class, 
				Long.parseLong(strCwcode));
		m_objTopCorewaybill = (TopCorewaybill)HSingleQuery.load(TopCorewaybill.class, 
				Long.parseLong(strCwcode));		
	}
	
	/**
	 * 撤销退件
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setUndoBackpriceParam(String strCwcode,
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);		
		TdiPaymentmode objTPM = TdiPaymentmodeDC.loadByKey("APP");
		m_objTopCorewaybill.setTdiPaymentmode(objTPM);		
		m_objTopCorewaybill.setTdiIssueholdstatus(null);
		m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));		
			
	}
	
	
	public void setUndoToCustomerPrintParam(String strCwcode,
			String strOperId) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		loadByCwcode(strCwcode);
		TdiCorewaybillstatus ts = TdiCorewaybillstatusDC.loadByKey("CHP");
		m_objTopCorewaybill.setTdiCorewaybillstatus(ts);
		m_objTopCorewaybill.setCwOpIdModifier(new BigDecimal(strOperId));		
			
	}	
	
	/**
	 * 执行事务
	 */
	public void transaction(Session objSession) throws Exception {
		if (m_objTHousewaybill == null || 
				m_objTHousewaybill.getCwCode() == null)
			return;
		if (m_objTopCorewaybill == null || 
				m_objTopCorewaybill.getCwCode() == null)
			return;
		
		objSession.update(m_objTopCorewaybill);
		objSession.update(m_objTHousewaybill);
		
		if (m_listCorewaybillpieces != null && m_listCorewaybillpieces.size() > 0) {
			SaveCWBPiecesTransaction objSCWPT = new SaveCWBPiecesTransaction();
			objSCWPT.setParam(m_listCorewaybillpieces, m_objTopCorewaybill);
			objSCWPT.transaction(objSession);
		}
	}
}
