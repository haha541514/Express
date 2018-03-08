package kyle.leis.es.businessrule.manifest.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ManifestefcolumnQuery extends HGeneralQuery {
	
	public ManifestefcolumnQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.manifest.da.ManifestefcolumnColumns(mefc.comp_id.mefcId, mefc.comp_id.mefCode, msc.mscCode, msc.mscColumnname, msc.mscSqlcolumnname, mefc.mefcCaptionname, mefc.mefcStructruecode, mefc.mefcFixedcolumnformula) FROM TbrManifestefcolumn as mefc left join mefc.tdiManifeststandardcolumn as msc inner join mefc.tbrManifestexportformat as mef";
	    m_strWhereClause = "";
	    m_strOrderByClause = "mefc.mefcStructruecode";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "mefc.comp_id.mefCode = '~~'", "mefc.comp_id.mefcId = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
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

	public void setMefcid(String mefcId) {
		this.setField(1, mefcId);
	}

	public String getMefcid() {
		return this.getField(1);
	}

}
