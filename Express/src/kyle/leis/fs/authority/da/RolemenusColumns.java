package kyle.leis.fs.authority.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class RolemenusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public RolemenusColumns() {
		
	}
	
	public RolemenusColumns(String strGmcode, String strGmname, 
			String strGmparameter, String strGmlink, 
			String strGmicon, int iGmlevel, 
			String strGmstructurecode, String strGmshortcutkey, 
			String strGmgroupcode, int iGmmaxusecount, 
			String strGmshowtoolbar, String strGoscode,
			String strGmpinyinname){
		m_astrColumns = new String[13];
		setGmcode(strGmcode);
		setGmname(strGmname);
		setGmparameter(strGmparameter);
		setGmlink(strGmlink);
		setGmicon(strGmicon);
		setGmlevel(iGmlevel);
		setGmstructurecode(strGmstructurecode);
		setGmshortcutkey(strGmshortcutkey);
		setGmgroupcode(strGmgroupcode);
		setGmmaxusecount(iGmmaxusecount);
		setGmshowtoolbar(strGmshowtoolbar);
		setGoscode(strGoscode);
		setGmpinyinname(strGmpinyinname);
	}

	public void setGmcode(String strGmcode) {
		this.setField(0, strGmcode);
	}

	public String getGmcode() {
		return this.getField(0);
	}


	public void setGmname(String strGmname) {
		this.setField(1, strGmname);
	}

	public String getGmname() {
		return this.getField(1);
	}


	public void setGmparameter(String strGmparameter) {
		this.setField(2, strGmparameter);
	}

	public String getGmparameter() {
		return this.getField(2);
	}


	public void setGmlink(String strGmlink) {
		this.setField(3, strGmlink);
	}

	public String getGmlink() {
		return this.getField(3);
	}


	public void setGmicon(String strGmicon) {
		this.setField(4, strGmicon);
	}

	public String getGmicon() {
		return this.getField(4);
	}


	public void setGmlevel(int iGmlevel) {
		this.setField(5, iGmlevel);
	}

	public String getGmlevel() {
		return this.getField(5);
	}


	public void setGmstructurecode(String strGmstructurecode) {
		this.setField(6, strGmstructurecode);
	}

	public String getGmstructurecode() {
		return this.getField(6);
	}


	public void setGmshortcutkey(String strGmshortcutkey) {
		this.setField(7, strGmshortcutkey);
	}

	public String getGmshortcutkey() {
		return this.getField(7);
	}


	public void setGmgroupcode(String strGmgroupcode) {
		this.setField(8, strGmgroupcode);
	}

	public String getGmgroupcode() {
		return this.getField(8);
	}


	public void setGmmaxusecount(int iGmmaxusecount) {
		this.setField(9, iGmmaxusecount);
	}

	public String getGmmaxusecount() {
		return this.getField(9);
	}


	public void setGmshowtoolbar(String strGmshowtoolbar) {
		this.setField(10, strGmshowtoolbar);
	}

	public String getGmshowtoolbar() {
		return this.getField(10);
	}


	public void setGoscode(String strGoscode) {
		this.setField(11, strGoscode);
	}

	public String getGoscode() {
		return this.getField(11);
	}

	public void setGmpinyinname(String strPinyinname) {
		this.setField(12, strPinyinname);
	}

	public String getGmpinyinname() {
		return this.getField(12);
	}
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
