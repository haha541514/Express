package kyle.leis.fs.authoritys.gmenus.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class GmenusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public GmenusColumns() {
		m_astrColumns = new String[18];
	}
	
	public GmenusColumns(String gmGmcode, 
            String gmGmname, String gmGmparameter, 
            String gmGmlink, String gmGmicon, 
            int gmGmlevel, String gmGmstructurecode, 
            String gmGmshortcutkey, String gmGmgroupcode, 
            int gmGmmaxusecount, String gmGmshowtoolbar, 
            String gmGmpinyinname, String gosGoscode, 
            String gosGosname, String gosGosename, 
            String iskIskcode, String iskIskname, 
            String iskIskename){
		m_astrColumns = new String[18];
		setGmgmcode(gmGmcode);
		setGmgmname(gmGmname);
		setGmgmparameter(gmGmparameter);
		setGmgmlink(gmGmlink);
		setGmgmicon(gmGmicon);
		setGmgmlevel(gmGmlevel);
		setGmgmstructurecode(gmGmstructurecode);
		setGmgmshortcutkey(gmGmshortcutkey);
		setGmgmgroupcode(gmGmgroupcode);
		setGmgmmaxusecount(gmGmmaxusecount);
		setGmgmshowtoolbar(gmGmshowtoolbar);
		setGmgmpinyinname(gmGmpinyinname);
		setGosgoscode(gosGoscode);
		setGosgosname(gosGosname);
		setGosgosename(gosGosename);
		setIskiskcode(iskIskcode);
		setIskiskname(iskIskname);
		setIskiskename(iskIskename);
	}

	public void setGmgmcode(String gmGmcode) {
		this.setField(0, gmGmcode);
	}

	public String getGmgmcode() {
		return this.getField(0);
	}

	public void setGmgmname(String gmGmname) {
		this.setField(1, gmGmname);
	}

	public String getGmgmname() {
		return this.getField(1);
	}

	public void setGmgmparameter(String gmGmparameter) {
		this.setField(2, gmGmparameter);
	}

	public String getGmgmparameter() {
		return this.getField(2);
	}

	public void setGmgmlink(String gmGmlink) {
		this.setField(3, gmGmlink);
	}

	public String getGmgmlink() {
		return this.getField(3);
	}

	public void setGmgmicon(String gmGmicon) {
		this.setField(4, gmGmicon);
	}

	public String getGmgmicon() {
		return this.getField(4);
	}

	public void setGmgmlevel(int gmGmlevel) {
		this.setField(5, gmGmlevel);
	}

	public String getGmgmlevel() {
		return this.getField(5);
	}

	public void setGmgmstructurecode(String gmGmstructurecode) {
		this.setField(6, gmGmstructurecode);
	}

	public String getGmgmstructurecode() {
		return this.getField(6);
	}

	public void setGmgmshortcutkey(String gmGmshortcutkey) {
		this.setField(7, gmGmshortcutkey);
	}

	public String getGmgmshortcutkey() {
		return this.getField(7);
	}

	public void setGmgmgroupcode(String gmGmgroupcode) {
		this.setField(8, gmGmgroupcode);
	}

	public String getGmgmgroupcode() {
		return this.getField(8);
	}

	public void setGmgmmaxusecount(int gmGmmaxusecount) {
		this.setField(9, gmGmmaxusecount);
	}

	public String getGmgmmaxusecount() {
		return this.getField(9);
	}

	public void setGmgmshowtoolbar(String gmGmshowtoolbar) {
		this.setField(10, gmGmshowtoolbar);
	}

	public String getGmgmshowtoolbar() {
		return this.getField(10);
	}

	public void setGmgmpinyinname(String gmGmpinyinname) {
		this.setField(11, gmGmpinyinname);
	}

	public String getGmgmpinyinname() {
		return this.getField(11);
	}

	public void setGosgoscode(String gosGoscode) {
		this.setField(12, gosGoscode);
	}

	public String getGosgoscode() {
		return this.getField(12);
	}

	public void setGosgosname(String gosGosname) {
		this.setField(13, gosGosname);
	}

	public String getGosgosname() {
		return this.getField(13);
	}

	public void setGosgosename(String gosGosename) {
		this.setField(14, gosGosename);
	}

	public String getGosgosename() {
		return this.getField(14);
	}

	public void setIskiskcode(String iskIskcode) {
		this.setField(15, iskIskcode);
	}

	public String getIskiskcode() {
		return this.getField(15);
	}

	public void setIskiskname(String iskIskname) {
		this.setField(16, iskIskname);
	}

	public String getIskiskname() {
		return this.getField(16);
	}

	public void setIskiskename(String iskIskename) {
		this.setField(17, iskIskename);
	}

	public String getIskiskename() {
		return this.getField(17);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
