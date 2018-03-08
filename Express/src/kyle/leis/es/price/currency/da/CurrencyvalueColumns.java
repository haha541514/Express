package kyle.leis.es.price.currency.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class CurrencyvalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CurrencyvalueColumns() {
		m_astrColumns = new String[7];
	}
	
	public CurrencyvalueColumns(Integer cvcomp_idCvid, 
            Long cvcomp_idEpcode, BigDecimal cvCvcurrencyrate, 
            String ckbCkcode, String ckbCkname, 
            String ckcCkcode, String ckcCkname){
		m_astrColumns = new String[7];
		setCvcomp_idcvid(cvcomp_idCvid);
		setCvcomp_idepcode(cvcomp_idEpcode);
		setCvcvcurrencyrate(cvCvcurrencyrate);
		setCkbckcode(ckbCkcode);
		setCkbckname(ckbCkname);
		setCkcckcode(ckcCkcode);
		setCkcckname(ckcCkname);
	}

	public void setCvcomp_idcvid(Integer cvcomp_idCvid) {
		this.setField(0, cvcomp_idCvid);
	}

	public String getCvcomp_idcvid() {
		return this.getField(0);
	}

	public void setCvcomp_idepcode(Long cvcomp_idEpcode) {
		this.setField(1, cvcomp_idEpcode);
	}

	public String getCvcomp_idepcode() {
		return this.getField(1);
	}

	public void setCvcvcurrencyrate(BigDecimal cvCvcurrencyrate) {
		this.setField(2, cvCvcurrencyrate);
	}

	public String getCvcvcurrencyrate() {
		return this.getField(2);
	}

	public void setCkbckcode(String ckbCkcode) {
		this.setField(3, ckbCkcode);
	}

	public String getCkbckcode() {
		return this.getField(3);
	}

	public void setCkbckname(String ckbCkname) {
		this.setField(4, ckbCkname);
	}

	public String getCkbckname() {
		return this.getField(4);
	}

	public void setCkcckcode(String ckcCkcode) {
		this.setField(5, ckcCkcode);
	}

	public String getCkcckcode() {
		return this.getField(5);
	}

	public void setCkcckname(String ckcCkname) {
		this.setField(6, ckcCkname);
	}

	public String getCkcckname() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
