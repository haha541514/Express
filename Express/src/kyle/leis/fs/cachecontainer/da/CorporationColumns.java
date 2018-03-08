package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CorporationColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorporationColumns() {
		m_astrColumns = new String[8];
	}
	
	public CorporationColumns(String coCocode, 
            String coCosname, String coCosename, 
            String cstCstcode, String csCscode,
            String coCarryoversign, Date coCarryoverdate,
            String eeStructurecode){
		m_astrColumns = new String[8];
		setCococode(coCocode);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setCstcstcode(cstCstcode);
		setCscscode(csCscode);
		setCarryoversign(coCarryoversign);
		setCarryoverdate(coCarryoverdate);
		setEestructurecode(eeStructurecode);
	}

	public void setCococode(String coCocode) {
		this.setField(0, coCocode);
	}

	public String getCococode() {
		return this.getField(0);
	}

	public void setCocosname(String coCosname) {
		this.setField(1, coCosname);
	}

	public String getCocosname() {
		return this.getField(1);
	}

	public void setCocosename(String coCosename) {
		this.setField(2, coCosename);
	}

	public String getCocosename() {
		return this.getField(2);
	}

	public void setCstcstcode(String cstCstcode) {
		this.setField(3, cstCstcode);
	}

	public String getCstcstcode() {
		return this.getField(3);
	}

	public void setCscscode(String csCscode) {
		this.setField(4, csCscode);
	}

	public String getCscscode() {
		return this.getField(4);
	}

	public void setCarryoversign(String coCarryoversign) {
		this.setField(5, coCarryoversign);
	}

	public String getCarryoversign() {
		return this.getField(5);
	}

	public void setCarryoverdate(Date coCarryoverdate) {
		this.setField(6, coCarryoverdate);
	}

	public String getCarryoverdate() {
		return this.getField(6);
	}
	
	public void setEestructurecode(String eeStructurecode) {
		this.setField(7, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(7);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
