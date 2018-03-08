package kyle.leis.es.company.shipperconsignee.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ShipperconsigneeQuery extends HGeneralQuery {
	
	public ShipperconsigneeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns(sc.scCode,sc.scLabelcode,sc.scName,sc.scCompanyname,sc.scAddress1,sc.scAddress2,sc.scAddress3,sc.scPostcode,sc.scCitycode,sc.scTelephone,sc.scFax,sc.scShipperconsigneetype,chn.chnCode,cm.coCode) FROM TcoShipperconsigneeinfo as sc left join sc.tchnChannel as chn left join sc.tcoCustomer as cm";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sc.scLabelcode = '~~'", "sc.scShipperconsigneetype = '~~'", "chn.chnCode = '~~'", "sc.scCode = '~~'", "cm.coCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSclabelcode(String scLabelcode) {
		this.setField(0, scLabelcode);
	}

	public String getSclabelcode() {
		return this.getField(0);
	}

	public void setScshipperconsigneetype(String scShipperconsigneetype) {
		this.setField(1, scShipperconsigneetype);
	}

	public String getScshipperconsigneetype() {
		return this.getField(1);
	}

	public void setChncode(String chnCode) {
		this.setField(2, chnCode);
	}

	public String getChncode() {
		return this.getField(2);
	}

	public void setSccode(String scCode) {
		this.setField(3, scCode);
	}

	public String getSccode() {
		return this.getField(3);
	}

	public void setCmcocode(String cmcocode) {
		this.setField(4, cmcocode);
	}

	public String getCmcocode() {
		return this.getField(4);
	}

}
