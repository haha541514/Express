package kyle.leis.es.bulletin.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WechatitemColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WechatitemColumns() {
		m_astrColumns = new String[7];
	}
	
	public WechatitemColumns(Integer wccomp_idWciid, 
            Long blBlid, String wcWcititle, 
            String wcWcidescription, String wcWcipicurl, 
            String wcWciurl, String wcWcicontent){
		m_astrColumns = new String[7];
		setWccomp_idwciid(wccomp_idWciid);
		setBlblid(blBlid);
		setWcwcititle(wcWcititle);
		setWcwcidescription(wcWcidescription);
		setWcwcipicurl(wcWcipicurl);
		setWcwciurl(wcWciurl);
		setWcwcicontent(wcWcicontent);
	}

	public void setWccomp_idwciid(Integer wccomp_idWciid) {
		this.setField(0, wccomp_idWciid);
	}

	public String getWccomp_idwciid() {
		return this.getField(0);
	}

	public void setBlblid(Long blBlid) {
		this.setField(1, blBlid);
	}

	public String getBlblid() {
		return this.getField(1);
	}

	public void setWcwcititle(String wcWcititle) {
		this.setField(2, wcWcititle);
	}

	public String getWcwcititle() {
		return this.getField(2);
	}

	public void setWcwcidescription(String wcWcidescription) {
		this.setField(3, wcWcidescription);
	}

	public String getWcwcidescription() {
		return this.getField(3);
	}

	public void setWcwcipicurl(String wcWcipicurl) {
		this.setField(4, wcWcipicurl);
	}

	public String getWcwcipicurl() {
		return this.getField(4);
	}

	public void setWcwciurl(String wcWciurl) {
		this.setField(5, wcWciurl);
	}

	public String getWcwciurl() {
		return this.getField(5);
	}

	public void setWcwcicontent(String wcWcicontent) {
		this.setField(6, wcWcicontent);
	}

	public String getWcwcicontent() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
