package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ChannelforproductQuery extends JGeneralQuery {
	
	public ChannelforproductQuery(){
	    m_strSelectClause = "SELECT distinct chn.chn_sname,pk.pk_sname,pk.pk_name FROM t_br_productrule pr,t_br_businessrule br,t_br_channelproductrule cpr,t_di_productkind pk,t_chn_channel chn";
	    m_strWhereClause = "pr.br_id = cpr.br_id and pr.br_id = br.br_id and pr.pk_code = pk.pk_code and cpr.chn_code = chn.chn_code and pk.ss_code = 'ON' and br.ss_code = 'ON'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.br_startdate", "br.br_enddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "cpr.chn_code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ChannelforproductColumns();
	}
	
	public void setStartdate(String StartDate) {
		this.setField(0, StartDate);
	}

	public String getStartdate() {
		return this.getField(0);
	}

	public void setEnddate(String EndDate) {
		this.setField(1, EndDate);
	}

	public String getEnddate() {
		return this.getField(1);
	}

	public void setChncode(String chncode) {
		this.setField(2, chncode);
	}

	public String getChncode() {
		return this.getField(2);
	}

}
