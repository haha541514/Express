package kyle.leis.fs.waybillcode.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WaybillcodevalueQuery extends HGeneralQuery {
	
	public WaybillcodevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.waybillcode.da.WaybillcodevalueColumns(bcv.comp_id.bcvId,bcv.comp_id.bcId,bcv.bcvLabelcode,bcv.bcvPrefix,bcv.bcvSuffix,bck.bckCode,bck.bckName,bck.bckGroupcode,bck.bckBuildvaluesign,bcs.csCode,bcs.csName) FROM TfsWaybillcodevalue as bcv inner join bcv.tdiWaybillcodekind as bck inner join bcv.tdiWaybillcodestatus as bcs";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bcv.comp_id.bcvId = ~~", "bcv.comp_id.bcId = ~~", "bck.bckCode = '~~'", "bcv.bcvLabelcode = '~~'", "bcs.csCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBcvid(String bcvId) {
		this.setField(0, bcvId);
	}

	public String getBcvid() {
		return this.getField(0);
	}

	public void setBcid(String bcId) {
		this.setField(1, bcId);
	}

	public String getBcid() {
		return this.getField(1);
	}

	public void setBckcode(String bckCode) {
		this.setField(2, bckCode);
	}

	public String getBckcode() {
		return this.getField(2);
	}

	public void setBcvlabelcode(String bcvLabelcode) {
		this.setField(3, bcvLabelcode);
	}

	public String getBcvlabelcode() {
		return this.getField(3);
	}
	
	public void setBcscscode(String bcsCscode) {
		this.setField(4, bcsCscode);
	}

	public String getBcscscode() {
		return this.getField(4);
	}	
}
