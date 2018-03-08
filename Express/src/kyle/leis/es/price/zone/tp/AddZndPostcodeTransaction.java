package kyle.leis.es.price.zone.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

import kyle.leis.es.price.zone.da.ZonedistrictpostcodeColumns;
import kyle.leis.hi.TepZonedistrictpostcode;
import kyle.leis.hi.TepZonedistrictpostcodePK;
import kyle.leis.hi.TepZonevaluedistrict;

public class AddZndPostcodeTransaction extends AbstractTransaction {
	private List<ZonedistrictpostcodeColumns> m_listZndPostcode;
	private TepZonevaluedistrict m_objTZnvDistrict;
	
	public void setParam(List<ZonedistrictpostcodeColumns> listZndPostcode, 
			TepZonevaluedistrict objTZnvDistrict) {
		m_listZndPostcode = listZndPostcode;
		m_objTZnvDistrict = objTZnvDistrict;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listZndPostcode == null || m_listZndPostcode.size() < 1) return;
		// 保存城市邮编数据
		for (int i = 0; i < m_listZndPostcode.size(); i++) {
			ZonedistrictpostcodeColumns objZdpColumns = m_listZndPostcode.get(i);
			
			TepZonedistrictpostcode objTZndPostcode = new TepZonedistrictpostcode();
			
			TepZonedistrictpostcodePK objTepZdpPK = new TepZonedistrictpostcodePK();
			objTepZdpPK.setDtCode(m_objTZnvDistrict.getComp_id().getDtCode());
			objTepZdpPK.setZdpId(i);
			objTepZdpPK.setZnCode(m_objTZnvDistrict.getComp_id().getZnCode());
			objTepZdpPK.setZnvId(m_objTZnvDistrict.getComp_id().getZnvId());
			
			objTZndPostcode.setComp_id(objTepZdpPK);
			objTZndPostcode.setTepZonevaluedistrict(m_objTZnvDistrict);
			objTZndPostcode.setZdpStartpostcode(objZdpColumns.getZndpzdpstartpostcode());
			objTZndPostcode.setZdpEndpostcode(objZdpColumns.getZndpzdpendpostcode());
			objSession.save(objTZndPostcode);
		}
	}
}
