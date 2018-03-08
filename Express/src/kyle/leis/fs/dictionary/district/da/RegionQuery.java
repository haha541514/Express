package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class RegionQuery extends HGeneralQuery {
	
	public RegionQuery(){   
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.district.da.RegionColumns( tdr.rgCode, tdr.rgName, tdr.rgEname, tdrc.ctCode) FROM TdiRegion as tdr left join tdr.tdiCity as tdrc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "  tdr.rgCode ='~~'", "  tdr.rgName ='~~'", "  tdr.rgEname ='~~'", "  tdrc.ctCode ='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setRgcode(String rgCode) {
		this.setField(0, rgCode);
	}

	public String getRgcode() {
		return this.getField(0);
	}

	public void setRgname(String rgName) {
		this.setField(1, rgName);
	}

	public String getRgname() {
		return this.getField(1);
	}

	public void setRgename(String rgEname) {
		this.setField(2, rgEname);
	}

	public String getRgename() {
		return this.getField(2);
	}

	public void setCtcode(String ctCode) {
		this.setField(3, ctCode);
	}

	public String getCtcode() {
		return this.getField(3);
	}

}
