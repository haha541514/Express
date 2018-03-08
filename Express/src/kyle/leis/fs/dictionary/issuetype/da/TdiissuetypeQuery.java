package kyle.leis.fs.dictionary.issuetype.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TdiissuetypeQuery extends HGeneralQuery {
	
	public TdiissuetypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.issuetype.da.TdiissuetypeColumns(isut.isutCode,isut.isutName,isut.isutEname,  isut.isutGroup,isut.isutCustomervisiblesign,isut.isutNoticeinfo,ss.ssCode,ss.ssName) FROM TdiIssuetype as isut inner join isut.tdiSimplestatus as ss";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "isut.isutCode= '~~'", "isut.isutName like '%~~%'", "isut.isutEname= '~~'", "isut.isutGroup= '~~'", "isut.isutCustomervisiblesign = '~~'", "isut.isutNoticeinfo = '~~'", "ss.ssCode = '~~'", "ss.ssName = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setIsutcode(String isutCode) {
		this.setField(0, isutCode);
	}

	public String getIsutcode() {
		return this.getField(0);
	}

	public void setIsutname(String isutName) {
		this.setField(1, isutName);
	}

	public String getIsutname() {
		return this.getField(1);
	}

	public void setIsutename(String isutEname) {
		this.setField(2, isutEname);
	}

	public String getIsutename() {
		return this.getField(2);
	}

	public void setIsutgroup(String isutGroup) {
		this.setField(3, isutGroup);
	}

	public String getIsutgroup() {
		return this.getField(3);
	}

	public void setIsutcustomervisiblesign(String isutCustomervisiblesign) {
		this.setField(4, isutCustomervisiblesign);
	}

	public String getIsutcustomervisiblesign() {
		return this.getField(4);
	}

	public void setIsutnoticeinfo(String isutNoticeinfo) {
		this.setField(5, isutNoticeinfo);
	}

	public String getIsutnoticeinfo() {
		return this.getField(5);
	}

	public void setSscode(String ssCode) {
		this.setField(6, ssCode);
	}

	public String getSscode() {
		return this.getField(6);
	}

	public void setSsname(String ssName) {
		this.setField(7, ssName);
	}

	public String getSsname() {
		return this.getField(7);
	}

}
