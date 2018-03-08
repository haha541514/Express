package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class UpsremotedistrictColumns extends GeneralColumns implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public UpsremotedistrictColumns() {
		m_astrColumns = new String[6];
	}
	
	public UpsremotedistrictColumns(String urdUrd_code, 
            String urdUrd_nationcode, String urdUrd_nationname, 
            String urdUrd_cityname, String urdUrd_startpostcode, 
            String urdUrd_endpostcode){
		m_astrColumns = new String[6];
		setUrdurd_code(urdUrd_code);
		setUrdurd_nationcode(urdUrd_nationcode);
		setUrdurd_nationname(urdUrd_nationname);
		setUrdurd_cityname(urdUrd_cityname);
		setUrdurd_startpostcode(urdUrd_startpostcode);
		setUrdurd_endpostcode(urdUrd_endpostcode);
	}

	public void setUrdurd_code(String urdUrd_code) {
		this.setField(0, urdUrd_code);
	}

	public String getUrdfrd_code() {
		return this.getField(0);
	}

	public void setUrdurd_nationcode(String urdUrd_nationcode) {
		this.setField(1, urdUrd_nationcode);
	}

	public String getUrdurd_nationcode() {
		return this.getField(1);
	}

	public void setUrdurd_nationname(String urdUrd_nationname) {
		this.setField(2, urdUrd_nationname);
	}

	public String getUrdurd_nationname() {
		return this.getField(2);
	}

	public void setUrdurd_cityname(String urdUrd_cityname) {
		this.setField(3, urdUrd_cityname);
	}

	public String getUrdurd_cityname() {
		return this.getField(3);
	}

	public void setUrdurd_startpostcode(String urdUrd_startpostcode) {
		this.setField(4, urdUrd_startpostcode);
	}

	public String getUrdurd_startpostcode() {
		return this.getField(4);
	}

	public void setUrdurd_endpostcode(String urdUrd_endpostcode) {
		this.setField(5, urdUrd_endpostcode);
	}

	public String getUrdurd_endpostcode() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}

