package kyle.leis.eo.operation.manifest.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ManifestQuery extends HGeneralQuery {
	
	public ManifestQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.manifest.da.ManifestColumns(mf.mfCode, mf.mfCreatedate, mf.mfModifydate, mf.mfRemark, mop.opId, mop.opName, cop.opId, cop.opName, ss.ssCode, ss.ssName, ee.eeCode, ee.eeSname) FROM TopManifest as mf inner join mf.tdiOperatorByMfOpIdModify as mop inner join mf.tdiOperatorByMfOpIdCreate as cop inner join mf.tdiSimplestatus as ss inner join mf.tdiEnterpriseelement as ee";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "mf.mfCode = ~~", "mop.opId = ~~", "ss.ssCode = '~~'", "mf.mfModifydate >= to_date('~~', 'yyyy-mm-dd hh24:mi:ss')", "to_date('~~', 'yyyy-mm-dd hh24:mi:ss') >= mf.mfModifydate", "exists (select mfv.comp_id.mfCode from TopManifestvalue as mfv inner join mfv.topCorewaybill as cw where mfv.comp_id.mfCode = mf.mfCode and cw.cwServerewbcode = '~~')", "ee.eeStructurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMfcode(String mfCode) {
		this.setField(0, mfCode);
	}

	public String getMfcode() {
		return this.getField(0);
	}

	public void setOpidmodify(String OpIdModify) {
		this.setField(1, OpIdModify);
	}

	public String getOpidmodify() {
		return this.getField(1);
	}

	public void setSscode(String SsCode) {
		this.setField(2, SsCode);
	}

	public String getSscode() {
		return this.getField(2);
	}

	public void setModifystartdate(String ModifyStartdate) {
		this.setField(3, ModifyStartdate);
	}

	public String getModifystartdate() {
		return this.getField(3);
	}

	public void setModifyenddate(String ModifyEnddate) {
		this.setField(4, ModifyEnddate);
	}

	public String getModifyenddate() {
		return this.getField(4);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(5, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(5);
	}
	
	public void setEe_structurecode(String Ee_structurecode) {
		this.setField(6, Ee_structurecode);
	}

	public String getEe_structurecode() {
		return this.getField(6);
	}	
	
}
