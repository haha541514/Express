package kyle.leis.es.businessrule.manifestexportformat.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ManifestexportformatQuery extends HGeneralQuery {
	
	public ManifestexportformatQuery(){   
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatColumns( mef.mefCode,mef.mefName, mef.mefEname, mef.mefTemplatepath, mef.mefBeginrow, mef.mefBegincolumn,    mef.mefCreatedate, mef.mefModifydate, mef.mefExportfilesuffix, opc.opId, opc.opName,   opm.opId,opm.opName,si.ssCode,si.ssName) FROM TbrManifestexportformat as mef    inner join mef.tdiOperatorByMefCreator as opc    inner join mef.tdiOperatorByMefModifier as opm   inner join mef.tdiSimplestatus as si";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "mef.mefCode = '~~'", "mef.mefName = '~~'", "opc.opId = '~~'", "opc.opName = '~~'", "opm.opId = '~~'", "opm.opName = '~~'", "si.ssCode='~~'", "mef.mefEname='~~'", "mef.mefTemplatepath = '~~'", "mef.mefBeginrow = '~~'", "mef.mefBegincolumn='~~'", "mef.mefExportfilesuffix='~~'", "mef.mefCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= mef.mefCreatedate", "mef.mefModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= mef.mefModifydate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setOpcid(String opcid) {
		this.setField(2, opcid);
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

	public void setSscode(String ssCode) {
		this.setField(6, ssCode);
	}

	public String getSscode() {
		return this.getField(6);
	}

	public void setMefename(String mefEname) {
		this.setField(7, mefEname);
	}

	public String getMefename() {
		return this.getField(7);
	}

	public void setMeftemplatepath(String mefTemplatepath) {
		this.setField(8, mefTemplatepath);
	}

	public String getMeftemplatepath() {
		return this.getField(8);
	}

	public void setMefbeginrow(String mefBeginrow) {
		this.setField(9, mefBeginrow);
	}

	public String getMefbeginrow() {
		return this.getField(9);
	}

	public void setMefbegincolumn(String mefBegincolumn) {
		this.setField(10, mefBegincolumn);
	}

	public String getMefbegincolumn() {
		return this.getField(10);
	}

	public void setMefexportfilesuffix(String mefExportfilesuffix) {
		this.setField(11, mefExportfilesuffix);
	}

	public String getMefexportfilesuffix() {
		return this.getField(11);
	}

	public void setStartmefcreatedate(String Startmefcreatedate) {
		this.setField(12, Startmefcreatedate);
	}

	public String getStartmefcreatedate() {
		return this.getField(12);
	}

	public void setEndmefcreatedate(String Endmefcreatedate) {
		this.setField(13, Endmefcreatedate);
	}

	public String getEndmefcreatedate() {
		return this.getField(13);
	}

	public void setStartmefmodifydate(String Startmefmodifydate) {
		this.setField(14, Startmefmodifydate);
	}

	public String getStartmefmodifydate() {
		return this.getField(14);
	}

	public void setEndmefmodifydate(String Endmefmodifydate) {
		this.setField(15, Endmefmodifydate);
	}

	public String getEndmefmodifydate() {
		return this.getField(15);
	}

}
