package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class EparceldistrictQuery extends JGeneralQuery {
	
	public EparceldistrictQuery(){
	    m_strSelectClause = "SELECT epd.EPD_HUBCODE,epd.EPD_STARTPOSTCODE,epd.EPD_ENDTPOSTCODE FROM T_DI_EPARCELDISTRICT epd";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "'~~' >= epd.EPD_STARTPOSTCODE", "epd.EPD_ENDTPOSTCODE >= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new EparceldistrictColumns();
	}
	
	public void setStartpostcode(String startpostcode) {
		this.setField(0, startpostcode);
	}

	public String getStartpostcode() {
		return this.getField(0);
	}

	public void setEndpostcode(String endpostcode) {
		this.setField(1, endpostcode);
	}

	public String getEndpostcode() {
		return this.getField(1);
	}

}
