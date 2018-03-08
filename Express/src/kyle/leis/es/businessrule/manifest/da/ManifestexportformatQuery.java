package kyle.leis.es.businessrule.manifest.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ManifestexportformatQuery extends HGeneralQuery {
	
	public ManifestexportformatQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.manifest.da.ManifestexportformatColumns(mef.mefCode, mef.mefName, mef.mefEname, mef.mefTemplatepath, mef.mefBeginrow, mef.mefBegincolumn, mef.mefCreatedate, mef.mefModifydate, mef.mefExportfilesuffix, opc.opId, opc.opName, opm.opId, opm.opName) FROM TbrManifestexportformat as mef inner join mef.tdiOperatorByMefCreator as opc inner join mef.tdiOperatorByMefModifier as opm";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "mef.mefCode = '~~'", "mef.mefName like '%~~%'", "opc.opId = '~~'", "opc.opName = '~~'", "opm.opId = '~~'", "opm.opName = '~~'", " mef.mefCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= mef.mefCreatedate", " mef.mefModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= mef.mefModifydate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMefcode(String mefCode) {
		this.setField(0, mefCode);
	}

	public String getMefcode() {
		return this.getField(0);
	}

	public void setMefname(String mefName) {
		this.setField(1, mefName);
	}

	public String getMefname() {
		return this.getField(1);
	}

	public void setOpcid(String opcId) {
		this.setField(2, opcId);
	}

	public String getOpcid() {
		return this.getField(2);
	}

	public void setOpcname(String opcName) {
		this.setField(3, opcName);
	}

	public String getOpcname() {
		return this.getField(3);
	}

	public void setOpmid(String opmId) {
		this.setField(4, opmId);
	}

	public String getOpmid() {
		return this.getField(4);
	}

	public void setOpmname(String opmName) {
		this.setField(5, opmName);
	}

	public String getOpmname() {
		return this.getField(5);
	}

	public void setStartmefcreatedate(String StartMefCreatedate) {
		this.setField(6, StartMefCreatedate);
	}

	public String getStartmefcreatedate() {
		return this.getField(6);
	}

	public void setEndmefcreatedate(String EndMefCreatedate) {
		this.setField(7, EndMefCreatedate);
	}

	public String getEndmefcreatedate() {
		return this.getField(7);
	}

	public void setStartmefmodifydate(String StartMefModifydate) {
		this.setField(8, StartMefModifydate);
	}

	public String getStartmefmodifydate() {
		return this.getField(8);
	}

	public void setEndmefmodifydate(String EndMefModifydate) {
		this.setField(9, EndMefModifydate);
	}

	public String getEndmefmodifydate() {
		return this.getField(9);
	}

}
