package kyle.leis.fs.waybillcode.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillcodekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillcodekindColumns() {
		
	}
	
	public WaybillcodekindColumns(String wbckBckcode, 
            String wbckBckname, String wbckBckgroupcode, 
            String wbckBckbuildvaluesign){
		m_astrColumns = new String[4];
		setWbckbckcode(wbckBckcode);
		setWbckbckname(wbckBckname);
		setWbckbckgroupcode(wbckBckgroupcode);
		setWbckbckbuildvaluesign(wbckBckbuildvaluesign);
	}

	public void setWbckbckcode(String wbckBckcode) {
		this.setField(0, wbckBckcode);
	}

	public String getWbckbckcode() {
		return this.getField(0);
	}

	public void setWbckbckname(String wbckBckname) {
		this.setField(1, wbckBckname);
	}

	public String getWbckbckname() {
		return this.getField(1);
	}

	public void setWbckbckgroupcode(String wbckBckgroupcode) {
		this.setField(2, wbckBckgroupcode);
	}

	public String getWbckbckgroupcode() {
		return this.getField(2);
	}

	public void setWbckbckbuildvaluesign(String wbckBckbuildvaluesign) {
		this.setField(3, wbckBckbuildvaluesign);
	}

	public String getWbckbckbuildvaluesign() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
