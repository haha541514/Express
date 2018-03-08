package kyle.leis.es.company.shipperblacklist.dax;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.shipperblacklist.da.ShipperblacklistColumns;
import kyle.leis.es.company.shipperblacklist.da.ShipperblacklistCondition;
import kyle.leis.es.company.shipperblacklist.da.ShipperblacklistQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcoShipperblacklist;
import kyle.leis.hi.TdiServerstructuregroup;
import net.sf.hibernate.Session;

public class ShipperblacklistDemand {

	public static void setShipperblacklistByColumns(
			TcoShipperblacklist objShipperblacklist,
			ShipperblacklistColumns objSBLColumns, String strOperId,
			Session objSession) throws Exception {
		objShipperblacklist.setSblCompanyname(objSBLColumns.getSblsblcompanyname());
		objShipperblacklist.setSblModifydate(DateFormatUtility.getStandardDate(objSBLColumns.getSblsblmodifydate()));
		objShipperblacklist.setTdiOperatorByOpIdModifier(TdiOperatorDC.loadByKey(strOperId));
		objShipperblacklist.setTdiServerstructuregroup((TdiServerstructuregroup) objSession.load(TdiServerstructuregroup.class, 
				objSBLColumns.getSsgssgcode()));
	}

	public static List query(ShipperblacklistCondition objSBLCondition)
			throws Exception {
		ShipperblacklistQuery objSBLQuery = new ShipperblacklistQuery();
		objSBLQuery.setCondition(objSBLCondition);
		return objSBLQuery.getResults();
	}

	public static ShipperblacklistColumns loadByCompanyname(String strSsgCode,
			String strCompanyname) throws Exception {
		ShipperblacklistQuery objSBLQuery = new ShipperblacklistQuery();
		objSBLQuery.setSblcompanyname(strCompanyname);
		objSBLQuery.setSsgcode(strSsgCode);
		List listResults = objSBLQuery.getResults();
		if (listResults == null || listResults.size() < 1)
			return null;
		return (ShipperblacklistColumns)listResults.get(0);
	}
	
	
	
	public static ShipperblacklistColumns queryBySblcode(String strSblcode)
			throws Exception {
		ShipperblacklistCondition objSBLCondition = new ShipperblacklistCondition();
		objSBLCondition.setSblcode(strSblcode);
		return (ShipperblacklistColumns) query(objSBLCondition).get(0);
	}
}
