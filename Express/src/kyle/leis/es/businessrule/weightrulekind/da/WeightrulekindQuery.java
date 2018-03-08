package kyle.leis.es.businessrule.weightrulekind.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WeightrulekindQuery extends HGeneralQuery {
	
	public WeightrulekindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindColumns(wrk.wrkId,wrk.wrkName,wrk.wrkEname,wrk.wrkDefaultsign,pd.pdCode,pd.pdName,ss.ssCode,ss.ssName,pk.pkCode,pk.pkName,pk.pkSname) FROM TbrWeightrulekind as wrk inner join wrk.tdiPricedomain as pd inner join wrk.tdiSimplestatus as ss inner join wrk.tdiProductkind as pk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wrk.wrkId = ~~", "wrk.wrkName like '%~~%'", "wrk.wrkEname like '%~~%'", "wrk.wrkDefaultsign = '~~'", "pd.pdCode = '~~'", "ss.ssCode = '~~'", "pk.pkCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setWrkid(String wrkId) {
		this.setField(0, wrkId);
	}

	public String getWrkid() {
		return this.getField(0);
	}

	public void setWrkname(String wrkName) {
		this.setField(1, wrkName);
	}

	public String getWrkname() {
		return this.getField(1);
	}

	public void setWrkename(String wrkEname) {
		this.setField(2, wrkEname);
	}

	public String getWrkename() {
		return this.getField(2);
	}

	public void setWrkdefaultsign(String wrkDefaultsign) {
		this.setField(3, wrkDefaultsign);
	}

	public String getWrkdefaultsign() {
		return this.getField(3);
	}

	public void setPdcode(String pdCode) {
		this.setField(4, pdCode);
	}

	public String getPdcode() {
		return this.getField(4);
	}

	public void setSscode(String ssCode) {
		this.setField(5, ssCode);
	}

	public String getSscode() {
		return this.getField(5);
	}

	public void setPkcode(String pkCode) {
		this.setField(6, pkCode);
	}

	public String getPkcode() {
		return this.getField(6);
	}

}
