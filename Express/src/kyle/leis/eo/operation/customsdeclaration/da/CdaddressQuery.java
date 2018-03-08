package kyle.leis.eo.operation.customsdeclaration.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CdaddressQuery extends JGeneralQuery {
	
	public CdaddressQuery(){
	    m_strSelectClause = "SELECT cdsca.CDSCA_Code,cdsca.CDSCA_ShipperAddress,cdsca.CDSCA_ConsigneeAddress FROM T_DI_CDSCADDRESS cdsca";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cdsca.CDSCA_Code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CdaddressColumns();
	}
	
	public void setCdscacode(String cdscacode) {
		this.setField(0, cdscacode);
	}

	public String getCdscacode() {
		return this.getField(0);
	}

}
