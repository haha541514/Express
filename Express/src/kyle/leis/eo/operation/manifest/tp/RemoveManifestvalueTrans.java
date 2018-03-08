package kyle.leis.eo.operation.manifest.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.manifest.dax.ManifestDemand;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TopManifest;

public class RemoveManifestvalueTrans extends AbstractTransaction {
	private String[] m_astrCwcode;
	private String m_strMfcode;
	private String m_strOperId;
	
	public void setParam(String[] astrCwcode, 
			String strMfCode,
			String strOperId) {
		m_strMfcode = strMfCode;
		m_astrCwcode = astrCwcode;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_astrCwcode == null || m_astrCwcode.length < 1)
			return;
		if (StringUtility.isNull(m_strMfcode))
			return;
		ManifestDemand objManifestDemand = new ManifestDemand();
		List objList = objManifestDemand.loadManifestvalue(m_strMfcode);
		if (objList == null || objList.size() < 1) return;
		// 移除运单
		for (int i = 0; i < m_astrCwcode.length; i++) {
			String strCwcode = m_astrCwcode[i];
			objSession.delete(" from TopManifestvalue mfv where mfv.topCorewaybill.cwCode = " + strCwcode + 
					" and mfv.comp_id.mfCode = " + m_strMfcode);
		}
		// 修改Manifest
		TopManifest objTopManifest = (TopManifest)objSession.load(TopManifest.class, 
				Long.parseLong(m_strMfcode));
		objTopManifest.setMfModifydate(DateFormatUtility.getSysdate());		
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTopManifest.setTdiOperatorByMfOpIdModify(objTdiOperator);
		}
		// 修改状态标记
		if (objList.size() == m_astrCwcode.length) {
			TdiSimplestatus objTdiSimplestatus = (TdiSimplestatus)objSession.load(TdiSimplestatus.class, "OFF");
			objTopManifest.setTdiSimplestatus(objTdiSimplestatus);
		}
		objSession.save(objTopManifest);
	}

}
