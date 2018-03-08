package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumserverwaybillQuery extends JGeneralQuery {
	
	public SumserverwaybillQuery(){
	    m_strSelectClause = "SELECT nvl(sum(round(spy.spy_totalcharge,2)),0) as Totalcharge FROM t_fi_serverwaybill swb,t_fi_serverpayable spy";
	    m_strWhereClause = "swb.swb_code = spy.swb_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "swb.swb_serverewbcode = '~~'", "spy.fk_code in (~~)", "spy.fk_code not in (~~)", "swb.SWB_REFERENCECODE = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumserverwaybillColumns();
	}
	
	public void setSwbserverewbcode(String swbServerewbcode) {
		this.setField(0, swbServerewbcode);
	}

	public String getSwbserverewbcode() {
		return this.getField(0);
	}

	public void setInfkcode(String InFkcode) {
		this.setField(1, InFkcode);
	}

	public String getInfkcode() {
		return this.getField(1);
	}

	public void setNotinfkcode(String NotInFkcode) {
		this.setField(2, NotInFkcode);
	}

	public String getNotinfkcode() {
		return this.getField(2);
	}
	
	public void setSwbreferencecode(String swbReferencecode) {
		this.setField(3, swbReferencecode);
	}

	public String getSwbreferencecode() {
		return this.getField(3);
	}	
}
