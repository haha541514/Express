package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OperatorColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OperatorColumns() {
		m_astrColumns = new String[9];
	}
	
	public OperatorColumns(Long strOpid, String strOpname, 
			String strOpename, String strOpsname, 
			String strOpcode, String strFccode, 
			String strCocode, String strOscode,
			String strOsname){
		m_astrColumns = new String[9];
		setOpid(strOpid);
		setOpname(strOpname);
		setOpename(strOpename);
		setOpsname(strOpsname);
		setOpcode(strOpcode);
		setFccode(strFccode);
		setCocode(strCocode);
		setOscode(strOscode);
		setOsname(strOsname);
	}

	public void setOpid(Long strOpid) {
		this.setField(0, strOpid);
	}

	public String getOpid() {
		return this.getField(0);
	}


	public void setOpname(String strOpname) {
		this.setField(1, strOpname);
	}

	public String getOpname() {
		return this.getField(1);
	}


	public void setOpename(String strOpename) {
		this.setField(2, strOpename);
	}

	public String getOpename() {
		return this.getField(2);
	}


	public void setOpsname(String strOpsname) {
		this.setField(3, strOpsname);
	}

	public String getOpsname() {
		return this.getField(3);
	}


	public void setOpcode(String strOpcode) {
		this.setField(4, strOpcode);
	}

	public String getOpcode() {
		return this.getField(4);
	}
	
	public String getFccode() {
		return this.getField(5);
	}	
	
	public void setFccode(String strFccode) {
		this.setField(5, strFccode);
	}
	
	public String getCocode(){
		return this.getField(6);
	}
	
	public void setCocode(String strCocode){
		this.setField(6, strCocode);
	}
	
	public String getOscode(){
		return this.getField(7);
	}
	
	public void setOscode(String strOscode){
		this.setField(7, strOscode);
	}	

	public String getOsname(){
		return this.getField(8);
	}
	
	public void setOsname(String strOsname){
		this.setField(8, strOsname);
	}		
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
