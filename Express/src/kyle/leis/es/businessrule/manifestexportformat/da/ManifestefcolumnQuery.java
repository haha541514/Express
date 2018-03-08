package kyle.leis.es.businessrule.manifestexportformat.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ManifestefcolumnQuery extends HGeneralQuery {
	
	public ManifestefcolumnQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnColumns( mec.comp_id.mefcId,  mec.comp_id.mefCode, mec.mefcCaptionname, mec.mefcStructruecode, mec.mefcFixedcolumnformula, msc.mscCode, msc.mscColumnname, msc.mscSqlcolumnname, msc.mscColumnename, mef.mefCode) FROM TbrManifestefcolumn as mec inner join mec.tbrManifestexportformat as mef left join mec.tdiManifeststandardcolumn as msc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "mec.mefcStructruecode";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { " mec.mefcCaptionname ='~~'", " mec.mefcStructruecode='~~'", " mec.mefcFixedcolumnformula='~~'", " mef.mefCode='~~'", " msc.mscCode='~~'", "msc.mscColumnename='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
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

	public void setMefcstructruecode(String mefcStructruecode) {
		this.setField(1, mefcStructruecode);
	}

	public String getMefcstructruecode() {
		return this.getField(1);
	}

	public void setMefcfixedcolumnformula(String mefcFixedcolumnformula) {
		this.setField(2, mefcFixedcolumnformula);
	}

	public String getMefcfixedcolumnformula() {
		return this.getField(2);
	}

	public void setMefcode(String mefCode) {
		this.setField(3, mefCode);
	}

	public String getMefcode() {
		return this.getField(3);
	}

	public void setMsccode(String mscCode) {
		this.setField(4, mscCode);
	}

	public String getMsccode() {
		return this.getField(4);
	}

	public void setMsccolumnename(String mscColumnename) {
		this.setField(5, mscColumnename);
	}

	public String getMsccolumnename() {
		return this.getField(5);
	}

}
