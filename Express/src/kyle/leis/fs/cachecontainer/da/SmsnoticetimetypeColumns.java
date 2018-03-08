package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SmsnoticetimetypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SmsnoticetimetypeColumns() {
		m_astrColumns = new String[6];
	}
	
	public SmsnoticetimetypeColumns(String snttSnttcode, 
            String snttSnttname, String snttSnttename, 
            String snttSnttstarttime, String snttSnttendtime, 
            String snttSnttrestrictsign){
		m_astrColumns = new String[6];
		setSnttsnttcode(snttSnttcode);
		setSnttsnttname(snttSnttname);
		setSnttsnttename(snttSnttename);
		setSnttsnttstarttime(snttSnttstarttime);
		setSnttsnttendtime(snttSnttendtime);
		setSnttsnttrestrictsign(snttSnttrestrictsign);
	}

	public void setSnttsnttcode(String snttSnttcode) {
		this.setField(0, snttSnttcode);
	}

	public String getSnttsnttcode() {
		return this.getField(0);
	}

	public void setSnttsnttname(String snttSnttname) {
		this.setField(1, snttSnttname);
	}

	public String getSnttsnttname() {
		return this.getField(1);
	}

	public void setSnttsnttename(String snttSnttename) {
		this.setField(2, snttSnttename);
	}

	public String getSnttsnttename() {
		return this.getField(2);
	}

	public void setSnttsnttstarttime(String snttSnttstarttime) {
		this.setField(3, snttSnttstarttime);
	}

	public String getSnttsnttstarttime() {
		return this.getField(3);
	}

	public void setSnttsnttendtime(String snttSnttendtime) {
		this.setField(4, snttSnttendtime);
	}

	public String getSnttsnttendtime() {
		return this.getField(4);
	}

	public void setSnttsnttrestrictsign(String snttSnttrestrictsign) {
		this.setField(5, snttSnttrestrictsign);
	}

	public String getSnttsnttrestrictsign() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
