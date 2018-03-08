package kyle.leis.eo.operation.corewaybillpieces.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class CorewaybillpiecesColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorewaybillpiecesColumns() {
		m_astrColumns = new String[13];
	}
	
	public CorewaybillpiecesColumns(Integer cpcomp_idCpid, 
            Long cpcomp_idCwcode, BigDecimal cpCpgrossweight, 
            BigDecimal cpCplength, BigDecimal cpCpwidth, 
            BigDecimal cpCpheight, String cpCplabelcode, 
            String cpCpbaglabelcode, String cpCpsibaglabelcode, 
            String cpCpbarcodelabelcode, String cpCpselflabelcode, 
            String cwsCwscode, String cwsCwsname){
		m_astrColumns = new String[13];
		setCpcomp_idcpid(cpcomp_idCpid);
		setCpcomp_idcwcode(cpcomp_idCwcode);
		setCpcpgrossweight(cpCpgrossweight);
		setCpcplength(cpCplength);
		setCpcpwidth(cpCpwidth);
		setCpcpheight(cpCpheight);
		setCpcplabelcode(cpCplabelcode);
		setCpcpbaglabelcode(cpCpbaglabelcode);
		setCpcpsibaglabelcode(cpCpsibaglabelcode);
		setCpcpbarcodelabelcode(cpCpbarcodelabelcode);
		setCpcpselflabelcode(cpCpselflabelcode);
		setCwscwscode(cwsCwscode);
		setCwscwsname(cwsCwsname);
	}

	public void setCpcomp_idcpid(Integer cpcomp_idCpid) {
		this.setField(0, cpcomp_idCpid);
	}

	public String getCpcomp_idcpid() {
		return this.getField(0);
	}

	public void setCpcomp_idcwcode(Long cpcomp_idCwcode) {
		this.setField(1, cpcomp_idCwcode);
	}

	public String getCpcomp_idcwcode() {
		return this.getField(1);
	}

	public void setCpcpgrossweight(BigDecimal cpCpgrossweight) {
		this.setField(2, cpCpgrossweight);
	}

	public String getCpcpgrossweight() {
		return this.getField(2);
	}

	public void setCpcplength(BigDecimal cpCplength) {
		this.setField(3, cpCplength);
	}

	public String getCpcplength() {
		return this.getField(3);
	}

	public void setCpcpwidth(BigDecimal cpCpwidth) {
		this.setField(4, cpCpwidth);
	}

	public String getCpcpwidth() {
		return this.getField(4);
	}

	public void setCpcpheight(BigDecimal cpCpheight) {
		this.setField(5, cpCpheight);
	}

	public String getCpcpheight() {
		return this.getField(5);
	}

	public void setCpcplabelcode(String cpCplabelcode) {
		this.setField(6, cpCplabelcode);
	}

	public String getCpcplabelcode() {
		return this.getField(6);
	}

	public void setCpcpbaglabelcode(String cpCpbaglabelcode) {
		this.setField(7, cpCpbaglabelcode);
	}

	public String getCpcpbaglabelcode() {
		return this.getField(7);
	}

	public void setCpcpsibaglabelcode(String cpCpsibaglabelcode) {
		this.setField(8, cpCpsibaglabelcode);
	}

	public String getCpcpsibaglabelcode() {
		return this.getField(8);
	}

	public void setCpcpbarcodelabelcode(String cpCpbarcodelabelcode) {
		this.setField(9, cpCpbarcodelabelcode);
	}

	public String getCpcpbarcodelabelcode() {
		return this.getField(9);
	}

	public void setCpcpselflabelcode(String cpCpselflabelcode) {
		this.setField(10, cpCpselflabelcode);
	}

	public String getCpcpselflabelcode() {
		return this.getField(10);
	}

	public void setCwscwscode(String cwsCwscode) {
		this.setField(11, cwsCwscode);
	}

	public String getCwscwscode() {
		return this.getField(11);
	}

	public void setCwscwsname(String cwsCwsname) {
		this.setField(12, cwsCwsname);
	}

	public String getCwscwsname() {
		return this.getField(12);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
