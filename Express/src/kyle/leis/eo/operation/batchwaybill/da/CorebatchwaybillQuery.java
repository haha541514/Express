package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CorebatchwaybillQuery extends JGeneralQuery {
	
	public CorebatchwaybillQuery(){
	    m_strSelectClause = "SELECT bw.add_date,bw.bw_labelcode,bw.bw_code,chn.chn_sename,sum(cw.cw_chargeweight),sum(cw.cw_billcounts),fun_get_weightandcount(bw.bw_code) FROM  T_OP_BATCHWAYBILLVALUE bwv,T_OP_BATCHWAYBILL bw,T_OP_COREWAYBILL cw,T_CHN_CHANNEL chn ";
	    m_strWhereClause = " bwv.bw_code = bw.bw_code    and bwv.cw_code = cw.cw_code    and cw.chn_code_supplier = chn.chn_code    and cw.cw_batchwaybillsign = 'Y'    and bw.adt_code = 'D'    and cw.cws_code != 'EL'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "bw.add_date,bw.bw_labelcode,bw.bw_code,chn.chn_sename";
	    m_astrConditionWords = new String[] { "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "bw.bw_code=~~", "bw.bw_labelcode='~~'", "chn.chn_sename='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CorebatchwaybillColumns();
	}
	
	public void setStartadddate(String StartAddDate) {
		this.setField(0, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(0);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(1, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(1);
	}

	public void setBwcode(String bwCode) {
		this.setField(2, bwCode);
	}

	public String getBwcode() {
		return this.getField(2);
	}

	public void setBwlabelcode(String bwlabelcode) {
		this.setField(3, bwlabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(3);
	}

	public void setChnsename(String chnsename) {
		this.setField(4, chnsename);
	}

	public String getChnsename() {
		return this.getField(4);
	}

}
