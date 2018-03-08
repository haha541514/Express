package kyle.leis.es.company.shipperconsignee.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeCondition;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeQuery;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeseqColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeseqQuery;

public class ShipperconsigneeDemand {
	public static List query(ShipperconsigneeCondition objSCCondition) throws Exception {
		ShipperconsigneeQuery objSCQuery = new ShipperconsigneeQuery();
		objSCQuery.setCondition(objSCCondition);
		return objSCQuery.getResults();
	}
	
	public static ShipperconsigneeColumns load(String strSccode) throws Exception {
		ShipperconsigneeQuery objSCQuery = new ShipperconsigneeQuery();
		objSCQuery.setSccode(strSccode);
		List objList = objSCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (ShipperconsigneeColumns)objList.get(0);
	}
	
	public static ShipperconsigneeColumns loadByCustomer(String strCustomercode) throws Exception {
		ShipperconsigneeQuery objSCQuery = new ShipperconsigneeQuery();
		objSCQuery.setCmcocode(strCustomercode);
		List objList = objSCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (ShipperconsigneeColumns)objList.get(0);
	}	
	
	
	public static ShipperconsigneeColumns loadBySclabelcode(String strSclabelcode) throws Exception {
		if (StringUtility.isNull(strSclabelcode))
			return null;
		ShipperconsigneeQuery objSCQuery = new ShipperconsigneeQuery();
		objSCQuery.setSclabelcode(strSclabelcode);
		List objList = objSCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (ShipperconsigneeColumns)objList.get(0);
	}		
	
	
	public static String getNewSCcode() throws Exception {
		ShipperconsigneeseqQuery objSCSQuery = new ShipperconsigneeseqQuery();
		List objList = objSCSQuery.getResults();
		if (objList == null || objList.size() < 1) {
			throw (new Exception("无法取得新收发件人账号主键"));
		}
		return ((ShipperconsigneeseqColumns)objList.get(0)).getShipperconsigneeseq();
	}
	
}
