package kyle.leis.fs.dictionary.district.dax;

import kyle.leis.fs.dictionary.district.da.DhldistrictColumns;

public class DhldistrictColumnsEX extends DhldistrictColumns {
	private static final long serialVersionUID = 1L;
	
	public DhldistrictColumnsEX() {
		m_astrColumns = new String[5];
	}
	
	public DhldistrictColumnsEX(String dhlDd_cityname, 
            String dhlDd_hubcode, String dhlDd_startpostcode, 
            String dhlDd_endtpostcode, String dhlDD_citycname){
		m_astrColumns = new String[5];
		setDhldd_citycname(dhlDd_cityname);
		setDhldd_hubcode(dhlDd_hubcode);
		setDhldd_startpostcode(dhlDd_startpostcode);
		setDhldd_endtpostcode(dhlDd_endtpostcode);
		setDhldd_citycname(dhlDD_citycname);
	}
	
	public void setDhldd_citycname(String dhlDD_citycname){
		this.setField(4, dhlDD_citycname);
	}
	
	public String getDhldd_citycname(){
		return this.getField(4);
	}
}
