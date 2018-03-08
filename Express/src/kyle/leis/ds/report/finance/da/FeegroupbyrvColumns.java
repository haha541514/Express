package kyle.leis.ds.report.finance.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeegroupbyrvColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeegroupbyrvColumns() {
		m_astrColumns = new String[14];
	}
	
	public FeegroupbyrvColumns(String rvRv_occurdate, 
            String rvCk_code, String cwCw_customerewbcode, 
            String cwCw_serverewbcode, String cdtDt_name, 
            String pkPk_name, String coCo_name, 
            String fkFk_name, String rvRv_unitprice, 
            String rvRv_unitnumber, String rvRv_actualtotal, 
            String rvRv_currencyrate, String scoCo_name,
            String eeEe_sname){
		m_astrColumns = new String[14];
		setRvrv_occurdate(rvRv_occurdate);
		setRvck_code(rvCk_code);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCdtdt_name(cdtDt_name);
		setPkpk_name(pkPk_name);
		setCoco_name(coCo_name);
		setFkfk_name(fkFk_name);
		setRvrv_unitprice(rvRv_unitprice);
		setRvrv_unitnumber(rvRv_unitnumber);
		setRvrv_actualtotal(rvRv_actualtotal);
		setRvrv_currencyrate(rvRv_currencyrate);
		setScoco_name(scoCo_name);
		setEeee_sname(eeEe_sname);
	}

	public void setRvrv_occurdate(String rvRv_occurdate) {
		this.setField(0, rvRv_occurdate);
	}

	public String getRvrv_occurdate() {
		return this.getField(0);
	}

	public void setRvck_code(String rvCk_code) {
		this.setField(1, rvCk_code);
	}

	public String getRvck_code() {
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

	public void setRvrv_unitprice(String rvRv_unitprice) {
		this.setField(8, rvRv_unitprice);
	}

	public String getRvrv_unitprice() {
		return this.getField(8);
	}

	public void setRvrv_unitnumber(String rvRv_unitnumber) {
		this.setField(9, rvRv_unitnumber);
	}

	public String getRvrv_unitnumber() {
		return this.getField(9);
	}

	public void setRvrv_actualtotal(String rvRv_actualtotal) {
		this.setField(10, rvRv_actualtotal);
	}

	public String getRvrv_actualtotal() {
		return this.getField(10);
	}

	public void setRvrv_currencyrate(String rvRv_currencyrate) {
		this.setField(11, rvRv_currencyrate);
	}

	public String getRvrv_currencyrate() {
		return this.getField(11);
	}

	public void setScoco_name(String scoCo_name) {
		this.setField(12, scoCo_name);
	}

	public String getScoco_name() {
		return this.getField(12);
	}
	
	public void setEeee_sname(String eeEe_sname) {
		this.setField(12, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(12);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
