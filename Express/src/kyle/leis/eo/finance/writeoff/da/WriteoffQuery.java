package kyle.leis.eo.finance.writeoff.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WriteoffQuery extends HGeneralQuery {
	
	public WriteoffQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.finance.writeoff.da.WriteoffColumns(wo.woId,wo.woTotal,wo.woCreatedate,wo.woLabelcode,wo.woRemark,ck.ckCode,ck.ckName,op.opId,op.opName,ss.ssCode,ss.ssName) FROM TfiWriteoff as wo inner join wo.tdiCurrencykind as ck inner join wo.tdiOperator as op inner join wo.tdiSimplestatus as ss";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wo.woId = ~~", "wo.woLabelcode = '~~'", "ck.ckCode = '~~'", "op.opId = ~~", "wo.woCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= wo.woCreatedate", "ss.ssCode in (~~)", "ss.ssCode not in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setWoid(String woId) {
		this.setField(0, woId);
	}

	public String getWoid() {
		return this.getField(0);
	}

	public void setWolabelcode(String woLabelcode) {
		this.setField(1, woLabelcode);
	}

	public String getWolabelcode() {
		return this.getField(1);
	}

	public void setCkcode(String ckCode) {
		this.setField(2, ckCode);
	}

	public String getCkcode() {
		return this.getField(2);
	}

	public void setOpid(String opId) {
		this.setField(3, opId);
	}

	public String getOpid() {
		return this.getField(3);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(4, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(4);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(5, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(5);
	}

	public void setSscode(String ssCode) {
		this.setField(6, ssCode);
	}

	public String getSscode() {
		return this.getField(6);
	}

	public void setNotinsscode(String NotInSsCode) {
		this.setField(7, NotInSsCode);
	}

	public String getNotinsscode() {
		return this.getField(7);
	}

}
