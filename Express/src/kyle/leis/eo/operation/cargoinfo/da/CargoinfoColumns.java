package kyle.leis.eo.operation.cargoinfo.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class CargoinfoColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CargoinfoColumns() {
		m_astrColumns = new String[12];
	}
	
	public CargoinfoColumns(Integer cicomp_idCiid, 
            Long cicomp_idCwcode, String ciCiname, 
            String ciCiename, int ciCipieces, 
            BigDecimal ciCiunitprice, BigDecimal ciCitotalprice, 
            String ciCihscode, String ckCkcode, 
            String ciCiattacheinfo, String ciCiremark, 
            BigDecimal ciCiweight){
		m_astrColumns = new String[12];
		setCicomp_idciid(cicomp_idCiid);
		setCicomp_idcwcode(cicomp_idCwcode);
		setCiciname(ciCiname);
		setCiciename(ciCiename);
		setCicipieces(ciCipieces);
		setCiciunitprice(ciCiunitprice);
		setCicitotalprice(ciCitotalprice);
		setCicihscode(ciCihscode);
		setCkckcode(ckCkcode);
		setCiciattacheinfo(ciCiattacheinfo);
		setCiciremark(ciCiremark);
		setCiciweight(ciCiweight);
	}

	public void setCicomp_idciid(Integer cicomp_idCiid) {
		this.setField(0, cicomp_idCiid);
	}

	public String getCicomp_idciid() {
		return this.getField(0);
	}

	public void setCicomp_idcwcode(Long cicomp_idCwcode) {
		this.setField(1, cicomp_idCwcode);
	}

	public String getCicomp_idcwcode() {
		return this.getField(1);
	}

	public void setCiciname(String ciCiname) {
		this.setField(2, ciCiname);
	}

	public String getCiciname() {
		return this.getField(2);
	}

	public void setCiciename(String ciCiename) {
		this.setField(3, ciCiename);
	}

	public String getCiciename() {
		return this.getField(3);
	}

	public void setCicipieces(int ciCipieces) {
		this.setField(4, ciCipieces);
	}

	public String getCicipieces() {
		return this.getField(4);
	}

	public void setCiciunitprice(BigDecimal ciCiunitprice) {
		this.setField(5, ciCiunitprice);
	}

	public String getCiciunitprice() {
		return this.getField(5);
	}

	public void setCicitotalprice(BigDecimal ciCitotalprice) {
		this.setField(6, ciCitotalprice);
	}

	public String getCicitotalprice() {
		return this.getField(6);
	}

	public void setCicihscode(String ciCihscode) {
		this.setField(7, ciCihscode);
	}

	public String getCicihscode() {
		return this.getField(7);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(8, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(8);
	}

	public void setCiciattacheinfo(String ciCiattacheinfo) {
		this.setField(9, ciCiattacheinfo);
	}

	public String getCiciattacheinfo() {
		return this.getField(9);
	}

	public void setCiciremark(String ciCiremark) {
		this.setField(10, ciCiremark);
	}

	public String getCiciremark() {
		return this.getField(10);
	}

	public void setCiciweight(BigDecimal ciCiweight) {
		this.setField(11, ciCiweight);
	}

	public String getCiciweight() {
		return this.getField(11);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
