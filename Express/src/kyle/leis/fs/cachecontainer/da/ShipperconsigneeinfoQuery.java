package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ShipperconsigneeinfoQuery extends HGeneralQuery {
	
	public ShipperconsigneeinfoQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.ShipperconsigneeinfoColumns(sc.scCode, sc.scLabelcode, sc.scName, sc.scCompanyname,sc.scAddress1, sc.scAddress2, sc.scAddress3, sc.scCitycode,sc.scTelephone, sc.scFax, sc.scShipperconsigneetype, sc.scPostcode, chn.chnCode) FROM TcoShipperconsigneeinfo as sc left join sc.tchnChannel as chn";
	    m_strWhereClause = "sc.scShipperconsigneetype = 'S'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
