package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SimplebatchwaybillQuery extends HGeneralQuery {
	
	public SimplebatchwaybillQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillColumns(bw.bwCode,bw.addDate,bw.bwLabelcode,bw.bwBatchnumber,bws.bwsCode,chn.chnCode,chn.chnSname,chn.chnSename,ee.eeCode,ee.eeSname,ee.eeEsname,co.coCode,co.coSname,co.coSename) FROM TopBatchwaybill as bw inner join bw.tdiBatchwaybillstatus as bws left join bw.tchnChannel as chn inner join bw.tdiEnterpriseelement as ee inner join bw.tdiArrivaldeparturetype as adt inner join bw.tcoCorporation as co";
	    m_strWhereClause = "";
	    m_strOrderByClause = "bw.bwBatchnumber desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bw.bwCode = ~~", "bw.addDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.addDate", "bw.bwLabelcode = '~~'", "bws.bwsCode = '~~'", "chn.chnCode = '~~'", "ee.eeCode = '~~'", "adt.adtCode = '~~'", "co.coCode = '~~'", "bws.bwsCode not in (~~)", "exists (select bwv.bwbvId from TopBatchwaybillvalue bwv where bwv.topBatchwaybill.bwCode = bw.bwCode and bwv.bwbvId = ~~)", "ee.eeStructurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBwcode(String bwCode) {
		this.setField(0, bwCode);
	}

	public String getBwcode() {
		return this.getField(0);
	}

	public void setStartadddate(String StartAddDate) {
		this.setField(1, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(1);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(2, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(2);
	}

	public void setBwlabelcode(String bwLabelcode) {
		this.setField(3, bwLabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(3);
	}

	public void setBwscode(String bwsCode) {
		this.setField(4, bwsCode);
	}

	public String getBwscode() {
		return this.getField(4);
	}

	public void setChncode(String chnCode) {
		this.setField(5, chnCode);
	}

	public String getChncode() {
		return this.getField(5);
	}

	public void setEecode(String eeCode) {
		this.setField(6, eeCode);
	}

	public String getEecode() {
		return this.getField(6);
	}

	public void setAdtcode(String adtCode) {
		this.setField(7, adtCode);
	}

	public String getAdtcode() {
		return this.getField(7);
	}

	public void setCocode(String coCode) {
		this.setField(8, coCode);
	}

	public String getCocode() {
		return this.getField(8);
	}

	public void setNotinbwscode(String NotInbwsCode) {
		this.setField(9, NotInbwsCode);
	}

	public String getNotinbwscode() {
		return this.getField(9);
	}

	public void setBwbvid(String Bwbvid) {
		this.setField(10, Bwbvid);
	}

	public String getBwbvid() {
		return this.getField(10);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(11, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(11);
	}

}
