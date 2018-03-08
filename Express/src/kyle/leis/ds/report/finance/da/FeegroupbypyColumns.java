package kyle.leis.ds.report.finance.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeegroupbypyColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeegroupbypyColumns() {
		m_astrColumns = new String[14];
	}
	
	public FeegroupbypyColumns(String pyPy_occurdate, 
            String pyCk_code, String cwCw_customerewbcode, 
            String cwCw_serverewbcode, String cdtDt_name, 
            String pkPk_name, String coCo_name, 
            String fkFk_name, String pyPy_unitprice, 
            String pyPy_unitnumber, String pyPy_actualtotal, 
            String pyPy_currencyrate, String ccoCo_name,
            String eeEe_sname){
		m_astrColumns = new String[14];
		setPypy_occurdate(pyPy_occurdate);
		setPyck_code(pyCk_code);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCdtdt_name(cdtDt_name);
		setPkpk_name(pkPk_name);
		setCoco_name(coCo_name);
		setFkfk_name(fkFk_name);
		setPypy_unitprice(pyPy_unitprice);
		setPypy_unitnumber(pyPy_unitnumber);
		setPypy_actualtotal(pyPy_actualtotal);
		setPypy_currencyrate(pyPy_currencyrate);
		setCcoco_name(ccoCo_name);
		setEeee_sname(eeEe_sname);		
	}

	public void setPypy_occurdate(String pyPy_occurdate) {
		this.setField(0, pyPy_occurdate);
	}

	public String getPypy_occurdate() {
		return this.getField(0);
	}

	public void setPyck_code(String pyCk_code) {
		this.setField(1, pyCk_code);
	}

	public String getPyck_code() {
		return this.getField(1);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(2, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(2);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(3, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(3);
	}

	public void setCdtdt_name(String cdtDt_name) {
		this.setField(4, cdtDt_name);
	}

	public String getCdtdt_name() {
		return this.getField(4);
	}

	public void setPkpk_name(String pkPk_name) {
		this.setField(5, pkPk_name);
	}

	public String getPkpk_name() {
		return this.getField(5);
	}

	public void setCoco_name(String coCo_name) {
		this.setField(6, coCo_name);
	}

	public String getCoco_name() {
		return this.getField(6);
	}

	public void setFkfk_name(String fkFk_name) {
		this.setField(7, fkFk_name);
	}

	public String getFkfk_name() {
		return this.getField(7);
	}

	public void setPypy_unitprice(String pyPy_unitprice) {
		this.setField(8, pyPy_unitprice);
	}

	public String getPypy_unitprice() {
		return this.getField(8);
	}

	public void setPypy_unitnumber(String pyPy_unitnumber) {
		this.setField(9, pyPy_unitnumber);
	}

	public String getPypy_unitnumber() {
		return this.getField(9);
	}

	public void setPypy_actualtotal(String pyPy_actualtotal) {
		this.setField(10, pyPy_actualtotal);
	}

	public String getPypy_actualtotal() {
		return this.getField(10);
	}

	public void setPypy_currencyrate(String pyPy_currencyrate) {
		this.setField(11, pyPy_currencyrate);
	}

	public String getPypy_currencyrate() {
		return this.getField(11);
	}

	public void setCcoco_name(String ccoCo_name) {
		this.setField(12, ccoCo_name);
	}

	public String getCcoco_name() {
		return this.getField(12);
	}
	
	public void setEeee_sname(String eeEe_sname) {
		this.setField(13, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(13);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
