package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class TestCityColumns extends GeneralColumns implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265683618927785401L;

	public TestCityColumns() {
		m_astrColumns = new String[4];
	}
	//国家、城市名称、州名称、城市三字代码
	public TestCityColumns(String diDtcode,String diDtname, String diDtstatename){
		
	}
	
	
}
