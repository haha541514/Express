package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ExisttrackQuery extends JGeneralQuery {
	
	public ExisttrackQuery(){
	    m_strSelectClause = "SELECT wbt.wbt_id,wbt.cw_code,wbt.wbt_district,wbt.co_code,wbt.wbts_code,wbt.wbt_description,wbt.wbt_location,wbt.wbt_occurdate FROM t_cs_waybilltrack wbt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wbt.cw_code = ~~", "wbt.co_code = '~~'", "upper(wbt.wbt_description) = upper('~~')", "upper(wbt.wbt_location) = upper('~~')", "to_char(wbt.wbt_occurdate,'yyyy-mm-dd hh24:mi:ss') = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ExisttrackColumns();
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setWbtdescription(String wbtdescription) {
		this.setField(2, wbtdescription);
	}

	public String getWbtdescription() {
		return this.getField(2);
	}

	public void setWbtlocation(String wbtlocation) {
		this.setField(3, wbtlocation);
	}

	public String getWbtlocation() {
		return this.getField(3);
	}

	public void setWbtoccurdate(String wbtoccurdate) {
		this.setField(4, wbtoccurdate);
	}

	public String getWbtoccurdate() {
		return this.getField(4);
	}

}
