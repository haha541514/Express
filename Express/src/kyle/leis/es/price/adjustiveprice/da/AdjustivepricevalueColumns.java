package kyle.leis.es.price.adjustiveprice.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class AdjustivepricevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public AdjustivepricevalueColumns() {
		m_astrColumns = new String[10];
	}
	
	public AdjustivepricevalueColumns(Long apvcomp_idEpcode, 
            Integer apvcomp_idApvid, String apvApvweekday, 
            BigDecimal apvApvpricevalue, String ctCtcode, 
            String ctCtname, String dtDtcode, 
            String dtDtname, String utUtcode, 
            String utUtname){
		m_astrColumns = new String[10];
		setApvcomp_idepcode(apvcomp_idEpcode);
		setApvcomp_idapvid(apvcomp_idApvid);
		setApvapvweekday(apvApvweekday);
		setApvapvpricevalue(apvApvpricevalue);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setDtdtcode(dtDtcode);
		setDtdtname(dtDtname);
		setUtutcode(utUtcode);
		setUtutname(utUtname);
	}

	public void setApvcomp_idepcode(Long apvcomp_idEpcode) {
		this.setField(0, apvcomp_idEpcode);
	}

	public String getApvcomp_idepcode() {
		return this.getField(0);
	}

	public void setApvcomp_idapvid(Integer apvcomp_idApvid) {
		this.setField(1, apvcomp_idApvid);
	}

	public String getApvcomp_idapvid() {
		return this.getField(1);
	}

	public void setApvapvweekday(String apvApvweekday) {
		this.setField(2, apvApvweekday);
	}

	public String getApvapvweekday() {
		return this.getField(2);
	}

	public void setApvapvpricevalue(BigDecimal apvApvpricevalue) {
		this.setField(3, apvApvpricevalue);
	}

	public String getApvapvpricevalue() {
		return this.getField(3);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(4, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(4);
	}

	public void setCtctname(String ctCtname) {
		this.setField(5, ctCtname);
	}

	public String getCtctname() {
		return this.getField(5);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(6, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(6);
	}

	public void setDtdtname(String dtDtname) {
		this.setField(7, dtDtname);
	}

	public String getDtdtname() {
		return this.getField(7);
	}

	public void setUtutcode(String utUtcode) {
		this.setField(8, utUtcode);
	}

	public String getUtutcode() {
		return this.getField(8);
	}

	public void setUtutname(String utUtname) {
		this.setField(9, utUtname);
	}

	public String getUtutname() {
		return this.getField(9);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
