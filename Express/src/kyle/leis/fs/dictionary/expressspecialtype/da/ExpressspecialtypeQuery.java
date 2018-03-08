package kyle.leis.fs.dictionary.expressspecialtype.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ExpressspecialtypeQuery extends HGeneralQuery {
	
	public ExpressspecialtypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeColumns(est.estCode, ss.ssCode, est.estName,est.estEname,est.estStructurecode,est.estEndsign,est.estExcludesign, est.estPeculiarlychannelsign) FROM TdiExpressspecialtype as est inner join est.tdiSimplestatus as ss";
	    m_strWhereClause = "ss.ssCode = 'ON'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "est.estCode = '~~'", "est.estStructurecode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEstcode(String EstCode) {
		this.setField(0, EstCode);
	}

	public String getEstcode() {
		return this.getField(0);
	}

	public void setEststructurecode(String EstStructurecode) {
		this.setField(1, EstStructurecode);
	}

	public String getEststructurecode() {
		return this.getField(1);
	}

}
