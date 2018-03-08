package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ServerwaybillQuery extends JGeneralQuery {
	
	public ServerwaybillQuery(){
	    m_strSelectClause = "SELECT swb.SWB_CODE,swb.CHN_CODE,swb.SWB_ServerEwbcode,swb.SWB_CustomerEwbcode,swb.SWB_Pieces,swb.SWB_Chargeweight,swb.SWB_REFERENCECODE,swb.SWB_TOTALFREIGHTCHARGE,swb.SWB_TOTALINCIDENTALCHARGE,swb.SWB_TOTALSURCHARGE FROM T_FI_SERVERWAYBILL swb";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "swb.SWB_CODE = ~~", "swb.CHN_CODE = '~~'", "swb.SWB_ServerEwbcode = '~~'", "swb.SWB_REFERENCECODE = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ServerwaybillColumns();
	}
	
	public void setSwbcode(String swbCode) {
		this.setField(0, swbCode);
	}

	public String getSwbcode() {
		return this.getField(0);
	}

	public void setChncode(String chnCode) {
		this.setField(1, chnCode);
	}

	public String getChncode() {
		return this.getField(1);
	}

	public void setSbdserverewbcode(String sbdServerEwbcode) {
		this.setField(2, sbdServerEwbcode);
	}

	public String getSbdserverewbcode() {
		return this.getField(2);
	}

	public void setSbdreferencecode(String sbdReferencecode) {
		this.setField(3, sbdReferencecode);
	}

	public String getSbdreferencecode() {
		return this.getField(3);
	}

}
