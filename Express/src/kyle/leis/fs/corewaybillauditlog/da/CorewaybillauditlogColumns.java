package kyle.leis.fs.corewaybillauditlog.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CorewaybillauditlogColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorewaybillauditlogColumns() {
		m_astrColumns = new String[6];
	}
	
	public CorewaybillauditlogColumns(Date cwalCwalcreatedate, 
            String faltFaltcode, String faltFaltcontent, 
            Long cwCwcode, Long copOpid, 
            String copOpname){
		m_astrColumns = new String[6];
		setCwalcwalcreatedate(cwalCwalcreatedate);
		setFaltfaltcode(faltFaltcode);
		setFaltfaltcontent(faltFaltcontent);
		setCwcwcode(cwCwcode);
		setCopopid(copOpid);
		setCopopname(copOpname);
	}

	public void setCwalcwalcreatedate(Date cwalCwalcreatedate) {
		this.setField(0, cwalCwalcreatedate);
	}

	public String getCwalcwalcreatedate() {
		return this.getField(0);
	}

	public void setFaltfaltcode(String faltFaltcode) {
		this.setField(1, faltFaltcode);
	}

	public String getFaltfaltcode() {
		return this.getField(1);
	}

	public void setFaltfaltcontent(String faltFaltcontent) {
		this.setField(2, faltFaltcontent);
	}

	public String getFaltfaltcontent() {
		return this.getField(2);
	}

	public void setCwcwcode(Long cwCwcode) {
		this.setField(3, cwCwcode);
	}

	public String getCwcwcode() {
		return this.getField(3);
	}

	public void setCopopid(Long copOpid) {
		this.setField(4, copOpid);
	}

	public String getCopopid() {
		return this.getField(4);
	}

	public void setCopopname(String copOpname) {
		this.setField(5, copOpname);
	}

	public String getCopopname() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
