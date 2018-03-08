package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CorporationeeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorporationeeColumns() {
		m_astrColumns = new String[5];
	}
	
	public CorporationeeColumns(String coCocode, 
            String coCosname, String coCosename, 
            String cstCstcode, String csCscode){
		m_astrColumns = new String[5];
		setCococode(coCocode);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setCstcstcode(cstCstcode);
		setCscscode(csCscode);
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

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
