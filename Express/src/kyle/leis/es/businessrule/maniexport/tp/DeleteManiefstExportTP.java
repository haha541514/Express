package kyle.leis.es.businessrule.maniexport.tp;

import java.awt.event.ActionEvent;






import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteManiefstExportTP extends AbstractTransaction{
	private String m_mefcode;
	private String isdelte;
	public void setParams(String mefcode){
		m_mefcode = mefcode.trim();//去除空格
	}
	public String getIsdelte(){
		return isdelte;
	}
	/**20160905
	 * setTbrManifestExportformat 
	 * by wukaiquan
	 * 来个批量删除
	 * **/
	public void transaction(Session objSession) throws Exception {
		//设置ss_code
		//先删除子表
		//objSession.delete(" from TbrManifestefcolumn mefc where mefc.comp_id.mefCode = " + m_mefcode);
		//再删除主表
		//objSession.delete(" from TbrManifestexportformat mef where mef.mefCode = " + m_mefcode);
		if(StringUtility.isNull(m_mefcode)){
			//m_mefcode为空，不能删除
			isdelte = null;
			return;
		}
		
		String deleteSql = "update  t_br_Manifestexportformat mef set mef.ss_code = 'OFF' where mef.mef_Code = '"+m_mefcode+"'";
		execute(objSession, deleteSql);
		isdelte = "sucee";
	}

}
