package kyle.leis.fs.authoritys.gmenus.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class GmenusitemQuery extends JGeneralQuery {
	
	public GmenusitemQuery(){
	    m_strSelectClause = "SELECT gm.gm_code,gm.gm_name,gm.gm_parameter,gm.gm_link,gm.gm_icon,gm.gm_level,gm.gm_structurecode,gm.gm_shortcutkey,gm.gm_groupcode,gm.gm_maxusecount,gm.gm_showtoolbar,gm.gm_pinyinname,gmi.gmi_content,gm.isk_code FROM T_FS_GUIMENU gm,t_Fs_Guimenuitem gmi";
	    m_strWhereClause = "gm.gm_code=gmi.gm_code(+)";
	    m_strOrderByClause = "gm.gm_structurecode";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "gm.gm_name like '~~'", "gm.gm_code = '~~' ", "gm.gm_structurecode like '%~~%'", "gm.gm_groupcode = '~~'", "gm.gm_level = '~~'", "gm.isk_code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new GmenusitemColumns();
	}
	
	public void setGmname(String gmName) {
		this.setField(0, gmName);
	}

	public String getGmname() {
		return this.getField(0);
	}

	public void setGmcode(String gmCode) {
		this.setField(1, gmCode);
	}

	public String getGmcode() {
		return this.getField(1);
	}

	public void setGmstructurecode(String gmStructurecode) {
		this.setField(2, gmStructurecode);
	}

	public String getGmstructurecode() {
		return this.getField(2);
	}

	public void setGmgroupcode(String gmGroupcode) {
		this.setField(3, gmGroupcode);
	}

	public String getGmgroupcode() {
		return this.getField(3);
	}

	public void setGmlevel(String gmLevel) {
		this.setField(4, gmLevel);
	}

	public String getGmlevel() {
		return this.getField(4);
	}

	public void setIskcode(String iskCode) {
		this.setField(5, iskCode);
	}

	public String getIskcode() {
		return this.getField(5);
	}

}
