package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CachecontainerQuery extends HGeneralQuery {
	
	public CachecontainerQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CachecontainerColumns(cc.comp_id.ccCode, cc.ccName, cc.ccBatchnumber, cc.ccTotalsign, cc.ccSql, isk.iskCode, isk.iskName) FROM TfsCachecontainer as cc inner join cc.tdiInfomationsystemkind as isk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cc.comp_id.ccCode = '~~'", "cc.ccName = '~~'", "isk.iskCode = '~~'", "cc.ccBatchnumber = '~~'", "cc.ccTotalsign = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCccode(String CcCode) {
		this.setField(0, CcCode);
	}

	public String getCccode() {
		return this.getField(0);
	}

	public void setCcname(String ccName) {
		this.setField(1, ccName);
	}

	public String getCcname() {
		return this.getField(1);
	}

	public void setIskcode(String IskCode) {
		this.setField(2, IskCode);
	}

	public String getIskcode() {
		return this.getField(2);
	}

	public void setCcbatchnumber(String ccBatchnumber) {
		this.setField(3, ccBatchnumber);
	}

	public String getCcbatchnumber() {
		return this.getField(3);
	}

	public void setCctotalsign(String ccTotalsign) {
		this.setField(4, ccTotalsign);
	}

	public String getCctotalsign() {
		return this.getField(4);
	}

}
