package kyle.leis.es.businessrule.maniexport.tp;

import java.awt.event.ActionEvent;






import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteManiefstExportTP extends AbstractTransaction{
	private String m_mefcode;
	private String isdelte;
	public void setParams(String mefcode){
		m_mefcode = mefcode.trim();//ȥ���ո�
	}
	public String getIsdelte(){
		return isdelte;
	}
	/**20160905
	 * setTbrManifestExportformat 
	 * by wukaiquan
	 * ��������ɾ��
	 * **/
	public void transaction(Session objSession) throws Exception {
		//����ss_code
		//��ɾ���ӱ�
		//objSession.delete(" from TbrManifestefcolumn mefc where mefc.comp_id.mefCode = " + m_mefcode);
		//��ɾ������
		//objSession.delete(" from TbrManifestexportformat mef where mef.mefCode = " + m_mefcode);
		if(StringUtility.isNull(m_mefcode)){
			//m_mefcodeΪ�գ�����ɾ��
			isdelte = null;
			return;
		}
		
		String deleteSql = "update  t_br_Manifestexportformat mef set mef.ss_code = 'OFF' where mef.mef_Code = '"+m_mefcode+"'";
		execute(objSession, deleteSql);
		isdelte = "sucee";
	}

}
