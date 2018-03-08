package kyle.leis.es.price.zone.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.zone.da.ZoneColumns;
import kyle.leis.es.price.zone.dax.ZoneDemand;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TepZone;

public class SaveZoneTransaction extends AbstractTransaction {
	private String m_strOperId;
	private ZoneColumns m_objZoneCol;
	private List m_listZnvalueCol;
	private List m_listZnvdistrictCol;
	private List m_listZndpostcodeCol;
	private String m_strZncode;
	
	public void setParam(ZoneColumns objZoneCol, 
			List listZnvalueCol, 
			List listZnvdistrictCol, 
			List listZndpostcodeCol, 
			String strOperId) {
		m_objZoneCol = objZoneCol;
		m_listZnvalueCol = listZnvalueCol;
		m_listZnvdistrictCol = listZnvdistrictCol;
		m_listZndpostcodeCol = listZndpostcodeCol;
		m_strOperId = strOperId;
	}
	
	public String getZncode() {
		return m_strZncode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objZoneCol == null) 
			return;
		// 修改分区
		TepZone objTepZone = null;
		if (!StringUtility.isNull(m_objZoneCol.getZnzncode())) {
			objTepZone = (TepZone)objSession.load(TepZone.class, Long.parseLong(m_objZoneCol.getZnzncode()));
			ZoneDemand.setZoneByColumns(objTepZone, m_objZoneCol, m_strOperId, objSession);
			objSession.save(objTepZone);
		}
		// 新增分区
		else {
			objTepZone = new TepZone();
			if (!StringUtility.isNull(m_strOperId)) {
				TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
						Long.parseLong(m_strOperId));
				objTepZone.setTdiOperatorByZnOpIdCreate(objTdiOperator);
				objTepZone.setZnCreatedate(DateFormatUtility.getSysdate());
			}
			m_objZoneCol.setSssscode("NW");
			ZoneDemand.setZoneByColumns(objTepZone, m_objZoneCol, m_strOperId, objSession);
			objSession.save(objTepZone);			
		}
		if (objTepZone == null) return;
		// 先删除分区值再新增
		AddZoneValueTransaction objAddZnvT = new AddZoneValueTransaction();
		objAddZnvT.setParam(objTepZone, 
				m_listZnvalueCol, 
				m_listZnvdistrictCol, 
				m_listZndpostcodeCol);
		objAddZnvT.transaction(objSession);
		m_strZncode = String.valueOf(objTepZone.getZnCode());
	}

}
