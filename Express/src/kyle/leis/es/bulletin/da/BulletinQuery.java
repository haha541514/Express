package kyle.leis.es.bulletin.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BulletinQuery extends HGeneralQuery {
	
	public BulletinQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.bulletin.da.BulletinColumns(bl.blId,bl.blHeading,bl.blContent,bl.blContentindex,bl.blLink,bl.blValiddate,bl.blSignname,bl.blCreatedate,bl.blModifydate,cop.opId,cop.opName,mop.opId,mop.opName,bk.bkCode,bk.bkName,bll.blCode,bll.blName) FROM TesBulletin as bl inner join bl.tdiOperatorByOpIdCreator as cop inner join bl.tdiOperatorByOpIdModifier as mop inner join bl.tdiBulletinkind as bk inner join bl.tdiBulletinlevel as bll";
	    m_strWhereClause = "";
	    m_strOrderByClause = "bl.blCreatedate desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bl.blHeading like '%~~%'", "bl.blContentindex like '%~~%'", "bl.blValiddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bl.blValiddate", "mop.opId = ~~", "bk.bkCode = '~~'", "bll.blCode = '~~'", "bl.blId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBlheading(String blHeading) {
		this.setField(0, blHeading);
	}

	public String getBlheading() {
		return this.getField(0);
	}

	public void setBlcontentindex(String blContentindex) {
		this.setField(1, blContentindex);
	}

	public String getBlcontentindex() {
		return this.getField(1);
	}

	public void setStartvaliddate1(String StartValiddate1) {
		this.setField(2, StartValiddate1);
	}

	public String getStartvaliddate1() {
		return this.getField(2);
	}

	public void setEndvaliddate(String EndValiddate) {
		this.setField(3, EndValiddate);
	}

	public String getEndvaliddate() {
		return this.getField(3);
	}

	public void setOpid(String opId) {
		this.setField(4, opId);
	}

	public String getOpid() {
		return this.getField(4);
	}

	public void setBkcode(String bkCode) {
		this.setField(5, bkCode);
	}

	public String getBkcode() {
		return this.getField(5);
	}

	public void setBlcode(String blCode) {
		this.setField(6, blCode);
	}

	public String getBlcode() {
		return this.getField(6);
	}

	public void setBlid(String blId) {
		this.setField(7, blId);
	}

	public String getBlid() {
		return this.getField(7);
	}

}
