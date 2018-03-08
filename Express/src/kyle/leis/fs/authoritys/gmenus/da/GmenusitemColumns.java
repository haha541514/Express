package kyle.leis.fs.authoritys.gmenus.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class GmenusitemColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public GmenusitemColumns() {
		m_astrColumns = new String[14];
	}
	
	public GmenusitemColumns(String gmGm_code, 
            String gmGm_name, String gmGm_parameter, 
            String gmGm_link, String gmGm_icon, 
            String gmGm_level, String gmGm_structurecode, 
            String gmGm_shortcutkey, String gmGm_groupcode, 
            String gmGm_maxusecount, String gmGm_showtoolbar, 
            String gmGm_pinyinname, String gmiGmi_content, 
            String gmIsk_code){
		m_astrColumns = new String[14];
		setGmgm_code(gmGm_code);
		setGmgm_name(gmGm_name);
		setGmgm_parameter(gmGm_parameter);
		setGmgm_link(gmGm_link);
		setGmgm_icon(gmGm_icon);
		setGmgm_level(gmGm_level);
		setGmgm_structurecode(gmGm_structurecode);
		setGmgm_shortcutkey(gmGm_shortcutkey);
		setGmgm_groupcode(gmGm_groupcode);
		setGmgm_maxusecount(gmGm_maxusecount);
		setGmgm_showtoolbar(gmGm_showtoolbar);
		setGmgm_pinyinname(gmGm_pinyinname);
		setGmigmi_content(gmiGmi_content);
		setGmisk_code(gmIsk_code);
	}

	public void setGmgm_code(String gmGm_code) {
		this.setField(0, gmGm_code);
	}

	public String getGmgm_code() {
		return this.getField(0);
	}

	public void setGmgm_name(String gmGm_name) {
		this.setField(1, gmGm_name);
	}

	public String getGmgm_name() {
		return this.getField(1);
	}

	public void setGmgm_parameter(String gmGm_parameter) {
		this.setField(2, gmGm_parameter);
	}

	public String getGmgm_parameter() {
		return this.getField(2);
	}

	public void setGmgm_link(String gmGm_link) {
		this.setField(3, gmGm_link);
	}

	public String getGmgm_link() {
		return this.getField(3);
	}

	public void setGmgm_icon(String gmGm_icon) {
		this.setField(4, gmGm_icon);
	}

	public String getGmgm_icon() {
		return this.getField(4);
	}

	public void setGmgm_level(String gmGm_level) {
		this.setField(5, gmGm_level);
	}

	public String getGmgm_level() {
		return this.getField(5);
	}

	public void setGmgm_structurecode(String gmGm_structurecode) {
		this.setField(6, gmGm_structurecode);
	}

	public String getGmgm_structurecode() {
		return this.getField(6);
	}

	public void setGmgm_shortcutkey(String gmGm_shortcutkey) {
		this.setField(7, gmGm_shortcutkey);
	}

	public String getGmgm_shortcutkey() {
		return this.getField(7);
	}

	public void setGmgm_groupcode(String gmGm_groupcode) {
		this.setField(8, gmGm_groupcode);
	}

	public String getGmgm_groupcode() {
		return this.getField(8);
	}

	public void setGmgm_maxusecount(String gmGm_maxusecount) {
		this.setField(9, gmGm_maxusecount);
	}

	public String getGmgm_maxusecount() {
		return this.getField(9);
	}

	public void setGmgm_showtoolbar(String gmGm_showtoolbar) {
		this.setField(10, gmGm_showtoolbar);
	}

	public String getGmgm_showtoolbar() {
		return this.getField(10);
	}

	public void setGmgm_pinyinname(String gmGm_pinyinname) {
		this.setField(11, gmGm_pinyinname);
	}

	public String getGmgm_pinyinname() {
		return this.getField(11);
	}

	public void setGmigmi_content(String gmiGmi_content) {
		this.setField(12, gmiGmi_content);
	}

	public String getGmigmi_content() {
		return this.getField(12);
	}

	public void setGmisk_code(String gmIsk_code) {
		this.setField(13, gmIsk_code);
	}

	public String getGmisk_code() {
		return this.getField(13);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
