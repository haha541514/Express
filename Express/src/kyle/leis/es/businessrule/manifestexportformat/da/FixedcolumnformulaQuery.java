package kyle.leis.es.businessrule.manifestexportformat.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FixedcolumnformulaQuery extends HGeneralQuery {
	
	public FixedcolumnformulaQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.manifestexportformat.da.FixedcolumnformulaColumns( mec.mefcCaptionname, mec.mefcFixedcolumnformula, mec.comp_id.mefcId,  mec.mefcStructruecode, mef.mefCode) FROM TbrManifestefcolumn as mec inner join mec.tbrManifestexportformat as mef left join mec.tdiManifeststandardcolumn as msc";
	    m_strWhereClause = "msc.mscCode is null";
	    m_strOrderByClause = "mec.mefcStructruecode";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { " mec.mefcCaptionname ='~~'", " mec.mefcFixedcolumnformula='~~'", " mef.mefCode='~~'", " mec.mefcStructruecode='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMefccaptionname(String mefcCaptionname) {
		this.setField(0, mefcCaptionname);
	}

	public String getMefccaptionname() {
		return this.getField(0);
	}

	public void setMefcfixedcolumnformula(String mefcFixedcolumnformula) {
		this.setField(1, mefcFixedcolumnformula);
	}

	public String getMefcfixedcolumnformula() {
		return this.getField(1);
	}

	public void setMefcode(String mefCode) {
		this.setField(2, mefCode);
	}

	public String getMefcode() {
		return this.getField(2);
	}

	public void setMefcstructruecode(String mefcStructruecode) {
		this.setField(3, mefcStructruecode);
	}

	public String getMefcstructruecode() {
		return this.getField(3);
	}

}
