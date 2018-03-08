package kyle.leis.es.businessrule.weightrulekind.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WeightrulekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WeightrulekindColumns() {
		
	}
	
	public WeightrulekindColumns(Long wrkWrkid, 
            String wrkWrkname, String wrkWrkename, 
            String wrkWrkdefaultsign, String pdPdcode, 
            String pdPdname, String ssSscode, 
            String ssSsname, String pkPkcode, 
            String pkPkname, String pkPksname){
		m_astrColumns = new String[11];
		setWrkwrkid(wrkWrkid);
		setWrkwrkname(wrkWrkname);
		setWrkwrkename(wrkWrkename);
		setWrkwrkdefaultsign(wrkWrkdefaultsign);
		setPdpdcode(pdPdcode);
		setPdpdname(pdPdname);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
		setPkpksname(pkPksname);
	}

	public void setWrkwrkid(Long wrkWrkid) {
		this.setField(0, wrkWrkid);
	}

	public String getWrkwrkid() {
		return this.getField(0);
	}

	public void setWrkwrkname(String wrkWrkname) {
		this.setField(1, wrkWrkname);
	}

	public String getWrkwrkname() {
		return this.getField(1);
	}

	public void setWrkwrkename(String wrkWrkename) {
		this.setField(2, wrkWrkename);
	}

	public String getWrkwrkename() {
		return this.getField(2);
	}

	public void setWrkwrkdefaultsign(String wrkWrkdefaultsign) {
		this.setField(3, wrkWrkdefaultsign);
	}

	public String getWrkwrkdefaultsign() {
		return this.getField(3);
	}

	public void setPdpdcode(String pdPdcode) {
		this.setField(4, pdPdcode);
	}

	public String getPdpdcode() {
		return this.getField(4);
	}

	public void setPdpdname(String pdPdname) {
		this.setField(5, pdPdname);
	}

	public String getPdpdname() {
		return this.getField(5);
	}

	public void setSssscode(String ssSscode) {
		this.setField(6, ssSscode);
	}

	public String getSssscode() {
		return this.getField(6);
	}

	public void setSsssname(String ssSsname) {
		this.setField(7, ssSsname);
	}

	public String getSsssname() {
		return this.getField(7);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(8, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(8);
	}

	public void setPkpkname(String pkPkname) {
		this.setField(9, pkPkname);
	}

	public String getPkpkname() {
		return this.getField(9);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(10, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
