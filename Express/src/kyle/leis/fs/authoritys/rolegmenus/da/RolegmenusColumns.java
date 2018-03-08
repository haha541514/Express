package kyle.leis.fs.authoritys.rolegmenus.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class RolegmenusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public RolegmenusColumns() {
		m_astrColumns = new String[25];
	}
	
	public RolegmenusColumns(String rgmcomp_idGmcode, 
            String rgmcomp_idRlcode, int rgmRmopidcreator, 
            Date rgmRmcreatedate, int rgmRmopidmodifier, 
            Date rgmRmmodifydate, String rlRlstructurecode, 
            String rlRlname, String rlRlename, 
            String rlRladministratorsign, String gmGmcode, 
            String gmGmname, String gmGmparameter, 
            String gmGmlink, String gmGmicon, 
            int gmGmlevel, String gmGmstructurecode, 
            String gmGmshortcutkey, String gmGmgroupcode, 
            int gmGmmaxusecount, String gmGmshowtoolbar, 
            String gosGoscode, String iskIskcode, 
            String iskIskname, String iskIskename){
		m_astrColumns = new String[25];
		setRgmcomp_idgmcode(rgmcomp_idGmcode);
		setRgmcomp_idrlcode(rgmcomp_idRlcode);
		setRgmrmopidcreator(rgmRmopidcreator);
		setRgmrmcreatedate(rgmRmcreatedate);
		setRgmrmopidmodifier(rgmRmopidmodifier);
		setRgmrmmodifydate(rgmRmmodifydate);
		setRlrlstructurecode(rlRlstructurecode);
		setRlrlname(rlRlname);
		setRlrlename(rlRlename);
		setRlrladministratorsign(rlRladministratorsign);
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
		setGosgoscode(gosGoscode);
		setIskiskcode(iskIskcode);
		setIskiskname(iskIskname);
		setIskiskename(iskIskename);
	}

	public void setRgmcomp_idgmcode(String rgmcomp_idGmcode) {
		this.setField(0, rgmcomp_idGmcode);
	}

	public String getRgmcomp_idgmcode() {
		return this.getField(0);
	}

	public void setRgmcomp_idrlcode(String rgmcomp_idRlcode) {
		this.setField(1, rgmcomp_idRlcode);
	}

	public String getRgmcomp_idrlcode() {
		return this.getField(1);
	}

	public void setRgmrmopidcreator(int rgmRmopidcreator) {
		this.setField(2, rgmRmopidcreator);
	}

	public String getRgmrmopidcreator() {
		return this.getField(2);
	}

	public void setRgmrmcreatedate(Date rgmRmcreatedate) {
		this.setField(3, rgmRmcreatedate);
	}

	public String getRgmrmcreatedate() {
		return this.getField(3);
	}

	public void setRgmrmopidmodifier(int rgmRmopidmodifier) {
		this.setField(4, rgmRmopidmodifier);
	}

	public String getRgmrmopidmodifier() {
		return this.getField(4);
	}

	public void setRgmrmmodifydate(Date rgmRmmodifydate) {
		this.setField(5, rgmRmmodifydate);
	}

	public String getRgmrmmodifydate() {
		return this.getField(5);
	}

	public void setRlrlstructurecode(String rlRlstructurecode) {
		this.setField(6, rlRlstructurecode);
	}

	public String getRlrlstructurecode() {
		return this.getField(6);
	}

	public void setRlrlname(String rlRlname) {
		this.setField(7, rlRlname);
	}

	public String getRlrlname() {
		return this.getField(7);
	}

	public void setRlrlename(String rlRlename) {
		this.setField(8, rlRlename);
	}

	public String getRlrlename() {
		return this.getField(8);
	}

	public void setRlrladministratorsign(String rlRladministratorsign) {
		this.setField(9, rlRladministratorsign);
	}

	public String getRlrladministratorsign() {
		return this.getField(9);
	}

	public void setGmgmcode(String gmGmcode) {
		this.setField(10, gmGmcode);
	}

	public String getGmgmcode() {
		return this.getField(10);
	}

	public void setGmgmname(String gmGmname) {
		this.setField(11, gmGmname);
	}

	public String getGmgmname() {
		return this.getField(11);
	}

	public void setGmgmparameter(String gmGmparameter) {
		this.setField(12, gmGmparameter);
	}

	public String getGmgmparameter() {
		return this.getField(12);
	}

	public void setGmgmlink(String gmGmlink) {
		this.setField(13, gmGmlink);
	}

	public String getGmgmlink() {
		return this.getField(13);
	}

	public void setGmgmicon(String gmGmicon) {
		this.setField(14, gmGmicon);
	}

	public String getGmgmicon() {
		return this.getField(14);
	}

	public void setGmgmlevel(int gmGmlevel) {
		this.setField(15, gmGmlevel);
	}

	public String getGmgmlevel() {
		return this.getField(15);
	}

	public void setGmgmstructurecode(String gmGmstructurecode) {
		this.setField(16, gmGmstructurecode);
	}

	public String getGmgmstructurecode() {
		return this.getField(16);
	}

	public void setGmgmshortcutkey(String gmGmshortcutkey) {
		this.setField(17, gmGmshortcutkey);
	}

	public String getGmgmshortcutkey() {
		return this.getField(17);
	}

	public void setGmgmgroupcode(String gmGmgroupcode) {
		this.setField(18, gmGmgroupcode);
	}

	public String getGmgmgroupcode() {
		return this.getField(18);
	}

	public void setGmgmmaxusecount(int gmGmmaxusecount) {
		this.setField(19, gmGmmaxusecount);
	}

	public String getGmgmmaxusecount() {
		return this.getField(19);
	}

	public void setGmgmshowtoolbar(String gmGmshowtoolbar) {
		this.setField(20, gmGmshowtoolbar);
	}

	public String getGmgmshowtoolbar() {
		return this.getField(20);
	}

	public void setGosgoscode(String gosGoscode) {
		this.setField(21, gosGoscode);
	}

	public String getGosgoscode() {
		return this.getField(21);
	}

	public void setIskiskcode(String iskIskcode) {
		this.setField(22, iskIskcode);
	}

	public String getIskiskcode() {
		return this.getField(22);
	}

	public void setIskiskname(String iskIskname) {
		this.setField(23, iskIskname);
	}

	public String getIskiskname() {
		return this.getField(23);
	}

	public void setIskiskename(String iskIskename) {
		this.setField(24, iskIskename);
	}

	public String getIskiskename() {
		return this.getField(24);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
