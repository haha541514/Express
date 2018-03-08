package kyle.leis.es.price.zone.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.zone.da.ZonedistrictpostcodeColumns;
import kyle.leis.es.price.zone.da.ZonevaluedistrictColumns;
import kyle.leis.es.price.zone.dax.ZoneDemand;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TepZonevalue;
import kyle.leis.hi.TepZonevaluedistrict;
import kyle.leis.hi.TepZonevaluedistrictPK;

public class AddZnvDistrictTransaction extends AbstractTransaction {
	private List<ZonevaluedistrictColumns> m_listZnvDistrict;
	private List m_listZndpostcodeCol;
	private TepZonevalue m_objTepZonevalue;
	
	public void setParam(List<ZonevaluedistrictColumns> listZnvDistrict, 
			TepZonevalue objTepZonevalue, 
			List listZndpostcodeCol) {
		m_listZnvDistrict = listZnvDistrict;
		m_objTepZonevalue = objTepZonevalue;
		m_listZndpostcodeCol = listZndpostcodeCol;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listZnvDistrict == null || m_listZnvDistrict.size() < 1) return;
		// 保存分区城市数据
		for (int i = 0; i < m_listZnvDistrict.size(); i++) {
			ZonevaluedistrictColumns objZnvdColumns = m_listZnvDistrict.get(i);
			TepZonevaluedistrict objTZnvDistrict = new TepZonevaluedistrict();
			if (!StringUtility.isNull(objZnvdColumns.getDtdtcode())) {
				TdiDistrict objTdiDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
						objZnvdColumns.getDtdtcode());
				objTZnvDistrict.setTdiDistrict(objTdiDistrict);		
			}
			
			TepZonevaluedistrictPK objTepZvdPK = new TepZonevaluedistrictPK();
			objTepZvdPK.setDtCode(objZnvdColumns.getDtdtcode());
			objTepZvdPK.setZnvId(m_objTepZonevalue.getComp_id().getZnvId());
			objTepZvdPK.setZnCode(m_objTepZonevalue.getComp_id().getZnCode());
			objTZnvDistrict.setComp_id(objTepZvdPK);
			
			objTZnvDistrict.setTepZonevalue(m_objTepZonevalue);
			objSession.save(objTZnvDistrict);
			// 保存邮编
			List<ZonedistrictpostcodeColumns> listZndPostcode = ZoneDemand.getZvdistrictPostcode(objZnvdColumns.getZnvznvname(),
					objZnvdColumns.getDtdtcode(),
					m_listZndpostcodeCol);
			AddZndPostcodeTransaction objAddZndPostcodeT = new AddZndPostcodeTransaction();
			objAddZndPostcodeT.setParam(listZndPostcode, objTZnvDistrict);
			objAddZndPostcodeT.transaction(objSession);
		}
	}
}
