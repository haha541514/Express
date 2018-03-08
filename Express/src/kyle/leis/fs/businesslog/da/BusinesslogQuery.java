package kyle.leis.fs.businesslog.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BusinesslogQuery extends HGeneralQuery {
	
	public BusinesslogQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.businesslog.da.BusinesslogColumns(blog.blId, blog.blCreatedate, blog.blBusinesscode, blog.blContent, op.opId, op.opName, blk.blkCode, blk.blkName, ak.akCode, ak.akName) FROM TfsBusinesslog as blog inner join blog.tdiOperator as op inner join blog.tdiBusinesslogkind as blk inner join blog.tdiActionkind as ak";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "blog.blId = ~~", "ak.akCode = '~~'", "op.opId = ~~", "blog.blCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= blog.blCreatedate", "blk.blkCode in (~~)", "blog.blBusinesscode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBlid(String blId) {
		this.setField(0, blId);
	}

	public String getBlid() {
		return this.getField(0);
	}

	public void setAkcode(String akCode) {
		this.setField(1, akCode);
	}

	public String getAkcode() {
		return this.getField(1);
	}

	public void setOpid(String opId) {
		this.setField(2, opId);
	}

	public String getOpid() {
		return this.getField(2);
	}

	public void setStartcreatedate(String startCreatedate) {
		this.setField(3, startCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(3);
	}

	public void setEndcreatedate(String endCreatedate) {
		this.setField(4, endCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(4);
	}

	public void setBlkcode(String blkCode) {
		this.setField(5, blkCode);
	}

	public String getBlkcode() {
		return this.getField(5);
	}

	public void setBlbusinesscode(String blBusinesscode) {
		this.setField(6, blBusinesscode);
	}

	public String getBlbusinesscode() {
		return this.getField(6);
	}

}
