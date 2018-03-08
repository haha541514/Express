package kyle.leis.fs.waybillcode.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WaybillcodeQuery extends HGeneralQuery {
	
	public WaybillcodeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.waybillcode.da.WaybillcodeColumns(bc.bcId,bc.bcStartcode,bc.bcEndcode,bc.bcPrefix,bc.bcSuffix,bc.bcCreatedate,bc.bcModifydate,bc.bcCurrentlabelcode,bc.bcRemark,bck.bckCode,bck.bckName,bck.bckGroupcode,bck.bckBuildvaluesign,cop.opId,cop.opName,mop.opId,mop.opName,bcs.csCode,bcs.csName) FROM TfsWaybillcode as bc inner join bc.tdiWaybillcodekind as bck inner join bc.tdiOperatorByOpIdCreator as cop inner join bc.tdiOperatorByOpIdModifier as mop inner join bc.tdiWaybillcodestatus as bcs";
	    m_strWhereClause = "";
	    m_strOrderByClause = "bc.bcModifydate";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bc.bcId = ~~", "bc.bcEndcode >= '~~'", "'~~' >= bc.bcStartcode", "bc.bcModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bc.bcModifydate", "bcs.csCode in (~~)", "bc.bcPrefix = '~~'", "bc.bcSuffix = '~~'", "bck.bckCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBcid(String bcId) {
		this.setField(0, bcId);
	}

	public String getBcid() {
		return this.getField(0);
	}

	public void setValidcode(String ValidCode) {
		this.setField(1, ValidCode);
	}

	public String getValidcode() {
		return this.getField(1);
	}

	public void setValidcode2(String ValidCode) {
		this.setField(2, ValidCode);
	}

	public String getValidcode2() {
		return this.getField(2);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(3, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(3);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(4, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(4);
	}

	public void setCscode(String csCode) {
		this.setField(5, csCode);
	}

	public String getCscode() {
		return this.getField(5);
	}

	public void setBcprefix(String bcPrefix) {
		this.setField(6, bcPrefix);
	}

	public String getBcprefix() {
		return this.getField(6);
	}

	public void setBcsuffix(String bcSuffix) {
		this.setField(7, bcSuffix);
	}

	public String getBcsuffix() {
		return this.getField(7);
	}

	public void setBckcode(String bckCode) {
		this.setField(8, bckCode);
	}

	public String getBckcode() {
		return this.getField(8);
	}

}
