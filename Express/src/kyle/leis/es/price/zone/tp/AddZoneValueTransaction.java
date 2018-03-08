package kyle.leis.es.price.zone.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.price.zone.da.ZonevalueColumns;
import kyle.leis.es.price.zone.da.ZonevaluedistrictColumns;
import kyle.leis.es.price.zone.dax.ZoneDemand;
import kyle.leis.hi.TepZone;
import kyle.leis.hi.TepZonevalue;
import kyle.leis.hi.TepZonevaluePK;

public class AddZoneValueTransaction extends AbstractTransaction{
	private TepZone m_objTepZone;
	private List m_listZnvalueCol;
	private List m_listZnvdistrictCol;
	private List m_listZndpostcodeCol;
	
	public void setParam(TepZone objTepZone, 			
			List listZnvalueCol, 
			List listZnvdistrictCol, 
			List listZndpostcodeCol) {
		m_objTepZone = objTepZone;
		m_listZnvalueCol = listZnvalueCol;
		m_listZnvdistrictCol = listZnvdistrictCol;
		m_listZndpostcodeCol = listZndpostcodeCol;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listZnvalueCol == null || m_listZnvalueCol.size() < 1) return;
        // 首先删除分区邮编城市等数据
		objSession.delete(" from TepZonedistrictpostcode as zndp where zndp.comp_id.znCode = " + 
				String.valueOf(m_objTepZone.getZnCode()));
		objSession.delete(" from TepZonevaluedistrict as zvd where zvd.comp_id.znCode = " + 
				String.valueOf(m_objTepZone.getZnCode()));
		objSession.delete(" from TepZonevalue as znv where znv.tepZone.znCode = " + 
				String.valueOf(m_objTepZone.getZnCode()));
		// 新增分区值
		for (int i = 0; i < m_listZnvalueCol.size(); i++) {
			ZonevalueColumns objZonevalueColumns = (ZonevalueColumns)m_listZnvalueCol.get(i);
			
			TepZonevalue objTepZonevalue = new TepZonevalue();
			
			TepZonevaluePK objTepZonevaluePK = new TepZonevaluePK();
			objTepZonevaluePK.setZnCode(m_objTepZone.getZnCode());
			objTepZonevaluePK.setZnvId(Long.parseLong(objZonevalueColumns.getZnvcomp_idznvid()));
			objTepZonevalue.setComp_id(objTepZonevaluePK);
			
			objTepZonevalue.setZnvEname(objZonevalueColumns.getZnvznvename());
			objTepZonevalue.setZnvName(objZonevalueColumns.getZnvznvname());
			objTepZonevalue.setZnvStructurecode(objZonevalueColumns.getZnvznvstructurecode());
			objTepZonevalue.setTepZone(m_objTepZone);
			objSession.save(objTepZonevalue);
			// 新增分区城市、邮编等数据
			List<ZonevaluedistrictColumns> listZnvDistrict = ZoneDemand.getZoneValueDistrict(objZonevalueColumns.getZnvznvname(), 
					m_listZnvdistrictCol);
			AddZnvDistrictTransaction objAddZnvDistrictT = new AddZnvDistrictTransaction();
			objAddZnvDistrictT.setParam(listZnvDistrict, objTepZonevalue, m_listZndpostcodeCol);
			objAddZnvDistrictT.transaction(objSession);
		}
	}
}
