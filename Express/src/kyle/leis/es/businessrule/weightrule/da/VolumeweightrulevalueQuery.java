package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class VolumeweightrulevalueQuery extends HGeneralQuery {
	
	public VolumeweightrulevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.weightrule.da.VolumeweightrulevalueColumns(vwrv.comp_id.vwrvId,vwrv.comp_id.brId,vwrv.znId,vwrv.znvId,vwrv.vwrvValue) FROM TbrVolumeweightrulevalue as vwrv inner join vwrv.tbrWeightrule as wr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "vwrv.comp_id.brId = ~~", "vwrv.comp_id.vwrvId = ~~", "vwrv.znId = ~~", "vwrv.znvId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setVwrvid(String vwrvId) {
		this.setField(1, vwrvId);
	}

	public String getVwrvid() {
		return this.getField(1);
	}

	public void setZnid(String znId) {
		this.setField(2, znId);
	}

	public String getZnid() {
		return this.getField(2);
	}

	public void setZnvid(String znvId) {
		this.setField(3, znvId);
	}

	public String getZnvid() {
		return this.getField(3);
	}

}
