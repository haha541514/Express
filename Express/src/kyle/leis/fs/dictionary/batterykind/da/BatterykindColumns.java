package kyle.leis.fs.dictionary.batterykind.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class BatterykindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BatterykindColumns() {
		m_astrColumns = new String[7];
	}
	
	public BatterykindColumns(String bkBkcode, 
            String bkBkname, String bkBkename, 
            String siSscode, String siSsname, 
            String ckCgkcode, String ckCgkname){
		m_astrColumns = new String[7];
		setBkbkcode(bkBkcode);
		setBkbkname(bkBkname);
		setBkbkename(bkBkename);
		setSisscode(siSscode);
		setSissname(siSsname);
		setCkcgkcode(ckCgkcode);
		setCkcgkname(ckCgkname);
	}

	public void setBkbkcode(String bkBkcode) {
		this.setField(0, bkBkcode);
	}

	public String getBkbkcode() {
		return this.getField(0);
	}

	public void setBkbkname(String bkBkname) {
		this.setField(1, bkBkname);
	}

	public String getBkbkname() {
		return this.getField(1);
	}

	public void setBkbkename(String bkBkename) {
		this.setField(2, bkBkename);
	}

	public String getBkbkename() {
		return this.getField(2);
	}

	public void setSisscode(String siSscode) {
		this.setField(3, siSscode);
	}

	public String getSisscode() {
		return this.getField(3);
	}
	
	/**
	 * 第四位是cgkcode,而不是sissname
	 * **/

	public void setSissname(String siSsname) {
		this.setField(4, siSsname);
	}

	public String getSissname() {
		return this.getField(4);
	}

	public void setCkcgkcode(String ckCgkcode) {
		this.setField(5, ckCgkcode);
	}

	public String getCkcgkcode() {
		return this.getField(5);
	}

	public void setCkcgkname(String ckCgkname) {
		this.setField(6, ckCgkname);
	}

	public String getCkcgkname() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
