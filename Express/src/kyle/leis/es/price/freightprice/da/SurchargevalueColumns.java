package kyle.leis.es.price.freightprice.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class SurchargevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SurchargevalueColumns() {
		m_astrColumns = new String[12];
	}
	
	public SurchargevalueColumns(Long svcomp_idEpcode, 
            Integer svcomp_idSvid, long svZnvid, 
            String svSvreversesign, BigDecimal Value, 
            BigDecimal svSvminimumvalue, BigDecimal svSvpricevalue, 
            String fkFkcode, String fkFkname, 
            String utUtcode, String utUtname, 
            BigDecimal svSvmaximumvalue){
		m_astrColumns = new String[12];
		setSvcomp_idepcode(svcomp_idEpcode);
		setSvcomp_idsvid(svcomp_idSvid);
		setSvznvid(svZnvid);
		setSvsvreversesign(svSvreversesign);
		setValue(Value);
		setSvsvminimumvalue(svSvminimumvalue);
		setSvsvpricevalue(svSvpricevalue);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
		setUtutcode(utUtcode);
		setUtutname(utUtname);
		setSvsvmaximumvalue(svSvmaximumvalue);
	}

	public void setSvcomp_idepcode(Long svcomp_idEpcode) {
		this.setField(0, svcomp_idEpcode);
	}

	public String getSvcomp_idepcode() {
		return this.getField(0);
	}

	public void setSvcomp_idsvid(Integer svcomp_idSvid) {
		this.setField(1, svcomp_idSvid);
	}

	public String getSvcomp_idsvid() {
		return this.getField(1);
	}

	public void setSvznvid(long svZnvid) {
		this.setField(2, svZnvid);
	}

	public String getSvznvid() {
		return this.getField(2);
	}

	public void setSvsvreversesign(String svSvreversesign) {
		this.setField(3, svSvreversesign);
	}

	public String getSvsvreversesign() {
		return this.getField(3);
	}

	public void setValue(BigDecimal Value) {
		this.setField(4, Value);
	}

	public String getValue() {
		return this.getField(4);
	}

	public void setSvsvminimumvalue(BigDecimal svSvminimumvalue) {
		this.setField(5, svSvminimumvalue);
	}

	public String getSvsvminimumvalue() {
		return this.getField(5);
	}

	public void setSvsvpricevalue(BigDecimal svSvpricevalue) {
		this.setField(6, svSvpricevalue);
	}

	public String getSvsvpricevalue() {
		return this.getField(6);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(7, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(7);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(8, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(8);
	}

	public void setUtutcode(String utUtcode) {
		this.setField(9, utUtcode);
	}

	public String getUtutcode() {
		return this.getField(9);
	}

	public void setUtutname(String utUtname) {
		this.setField(10, utUtname);
	}

	public String getUtutname() {
		return this.getField(10);
	}

	public void setSvsvmaximumvalue(BigDecimal svSvmaximumvalue) {
		this.setField(11, svSvmaximumvalue);
	}

	public String getSvsvmaximumvalue() {
		return this.getField(11);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
