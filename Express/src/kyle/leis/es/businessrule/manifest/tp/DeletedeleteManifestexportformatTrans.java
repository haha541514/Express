package kyle.leis.es.businessrule.manifest.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeletedeleteManifestexportformatTrans extends AbstractTransaction {
	private Long m_mefcode;
	public void setParam(Long mefcode) {
		m_mefcode = mefcode;
	}
	public void transaction(Session objSession) throws Exception {
		//��ɾ���ӱ�
		objSession.delete(" from TbrManifestefcolumn mefc where mefc.comp_id.mefCode = " + m_mefcode);
		//��ɾ������
		objSession.delete(" from TbrManifestexportformat mef where mef.mefCode = " + m_mefcode);
	}

}
