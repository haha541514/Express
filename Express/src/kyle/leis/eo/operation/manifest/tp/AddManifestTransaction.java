package kyle.leis.eo.operation.manifest.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.manifest.dax.ManifestDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.hi.TcoSupplier;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopManifest;
import kyle.leis.hi.TopManifestvalue;
import kyle.leis.hi.TopManifestvaluePK;

public class AddManifestTransaction extends AbstractTransaction {
	private String m_strOp_id_create;
	private String m_strMfRemark;
	private String[] m_astrCwCode;
	private String m_strMfcode;
	private String m_strEecode;
	
	public void setParam(String strOp_id_create,
			String strMfRemark,
			String[] astrCwCode,
			String strEecode) {
		if (astrCwCode == null || astrCwCode.length < 1) 
			return;
		m_strOp_id_create = strOp_id_create;
		m_strMfRemark = strMfRemark;
		m_astrCwCode = astrCwCode;
		m_strEecode = strEecode;
	}
	
	public String getMfcode() {
		return m_strMfcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		
		ManifestDemand objManifestDemand = new ManifestDemand();
		TopManifest objTopManifest = objManifestDemand.buildManifest(m_strOp_id_create, 
				m_strMfRemark, objSession);
		
		if (objTopManifest == null) return;
		objTopManifest.setTdiEnterpriseelement(TdiEnterpriseelementDC.loadByKey(m_strEecode));
		// 保存Manifest的主记录
		objSession.save(objTopManifest);
		m_strMfcode = String.valueOf(objTopManifest.getMfCode());
		// 修改服务商对应的清单
		// 保存清单值
		String strSupplierCocode = null;
		for (int i = 0; i < m_astrCwCode.length; i++) {	
			String strCwCode = (String)m_astrCwCode[i];
			if (StringUtility.isNull(strCwCode)) continue;
			TopCorewaybill objCoreWayBill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
					Long.parseLong(strCwCode));	
			if (objCoreWayBill == null) continue;
			strSupplierCocode = objCoreWayBill.getTcoCorporationByCoCodeSupplier().getCoCode();
			TopManifestvalue objManifestValue = new TopManifestvalue();
			TopManifestvaluePK objManifestPK = new TopManifestvaluePK();
			objManifestPK.setMfCode(objTopManifest.getMfCode());
			objManifestPK.setMvId(i + 1);
			objManifestValue.setComp_id(objManifestPK);
			objManifestValue.setTopCorewaybill(objCoreWayBill);
			objSession.save(objManifestValue);	
		}
		// 保存清单号
		if (!StringUtility.isNull(strSupplierCocode)) {
			TcoSupplier objTcoSupplier = (TcoSupplier)objSession.load(TcoSupplier.class, 
					strSupplierCocode);
			String strOldManifestseriesnumber = objTcoSupplier.getSpManifestseriesnumber();
			if (strOldManifestseriesnumber.length() < 6) 
				strOldManifestseriesnumber = "0";
			else
				strOldManifestseriesnumber = strOldManifestseriesnumber.substring(5);
			String strManifestseriesnumber = "SAA01" + String.valueOf((Long.parseLong(strOldManifestseriesnumber) + 1));
			objTcoSupplier.setSpManifestseriesnumber(strManifestseriesnumber);
			objSession.save(objTcoSupplier);
		}
	}

}
