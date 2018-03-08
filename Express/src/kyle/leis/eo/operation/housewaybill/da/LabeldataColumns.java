package kyle.leis.eo.operation.housewaybill.da;
import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;
public class LabeldataColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public LabeldataColumns() {
		m_astrColumns = new String[11];
	}
	
	public LabeldataColumns(String plLcn, 
            String plProduct, String plMailtype, 
            String plZipcode, String plE1, 
            String plE2, String plE3, 
            String plE4, String plE5, 
            String plE6, String plE7){
		m_astrColumns = new String[11];
		setPllcn(plLcn);
		setPlproduct(plProduct);
		setPlmailtype(plMailtype);
		setPlzipcode(plZipcode);
		setPle1(plE1);
		setPle2(plE2);
		setPle3(plE3);
		setPle4(plE4);
		setPle5(plE5);
		setPle6(plE6);
		setPle7(plE7);
	}

	public void setPllcn(String plLcn) {
		this.setField(0, plLcn);
	}

	public String getPllcn() {
		return this.getField(0);
	}

	public void setPlproduct(String plProduct) {
		this.setField(1, plProduct);
	}

	public String getPlproduct() {
		return this.getField(1);
	}

	public void setPlmailtype(String plMailtype) {
		this.setField(2, plMailtype);
	}

	public String getPlmailtype() {
		return this.getField(2);
	}

	public void setPlzipcode(String plZipcode) {
		this.setField(3, plZipcode);
	}

	public String getPlzipcode() {
		return this.getField(3);
	}

	public void setPle1(String plE1) {
		this.setField(4, plE1);
	}

	public String getPle1() {
		return this.getField(4);
	}

	public void setPle2(String plE2) {
		this.setField(5, plE2);
	}

	public String getPle2() {
		return this.getField(5);
	}

	public void setPle3(String plE3) {
		this.setField(6, plE3);
	}

	public String getPle3() {
		return this.getField(6);
	}

	public void setPle4(String plE4) {
		this.setField(7, plE4);
	}

	public String getPle4() {
		return this.getField(7);
	}

	public void setPle5(String plE5) {
		this.setField(8, plE5);
	}

	public String getPle5() {
		return this.getField(8);
	}

	public void setPle6(String plE6) {
		this.setField(9, plE6);
	}

	public String getPle6() {
		return this.getField(9);
	}
	
	public void setPle7(String plE7) {
		this.setField(10, plE7);
	}

	public String getPle7() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
