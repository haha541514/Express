package kyle.leis.fs.waybillcode.dax;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybillcodekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybillcodestatusDC;
import kyle.leis.fs.waybillcode.bl.AWaybillcode;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodeCondition;
import kyle.leis.fs.waybillcode.da.WaybillcodeQuery;
import kyle.leis.fs.waybillcode.da.WaybillcodekindQuery;
import kyle.leis.fs.waybillcode.da.WaybillcodevalueColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodevalueCondition;
import kyle.leis.fs.waybillcode.da.WaybillcodevalueQuery;
import kyle.leis.hi.TfsWaybillcode;

public class WaybillcodeDemand {
	public static List query(WaybillcodeCondition objWBCCondition)
	throws Exception {
		WaybillcodeQuery objWBCQuery = new WaybillcodeQuery();
		objWBCQuery.setCondition(objWBCCondition);
		return objWBCQuery.getResults();
	}
	
	public static WaybillcodeColumns queryByBcId(String strBcId) throws Exception
	{
		WaybillcodeCondition objWaybillcodeCon = new WaybillcodeCondition();
		objWaybillcodeCon.setBcid(strBcId);
		List objList = query(objWaybillcodeCon);
		if(!CollectionUtility.isNull(objList) && objList.size()==1)
			return (WaybillcodeColumns)objList.get(0);
		return null;
	}
	
	
	public static WaybillcodevalueColumns load(String strLablecode) 
	throws Exception {
		WaybillcodevalueQuery objWBVQuery = new WaybillcodevalueQuery();
		objWBVQuery.setBcvlabelcode(strLablecode);
		List objList = objWBVQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (WaybillcodevalueColumns)objList.get(0);
	}
	
	public static List queryBCValue(WaybillcodevalueCondition objBCVCondition) 
	throws Exception {
		WaybillcodevalueQuery objBCVQuery = new WaybillcodevalueQuery();
		objBCVQuery.setCondition(objBCVCondition);
		return objBCVQuery.getResults();
	}
	
	public static List queryWaybillcodekind() throws Exception {
		WaybillcodekindQuery objWBCKindQuery = new WaybillcodekindQuery();
		return objWBCKindQuery.getResults();
	}
	
	
	public static void setWaybillcodeByCol(TfsWaybillcode objTfsWaybillcode,
			WaybillcodeColumns objWaybillcodeCol,
			AWaybillcode m_objWaybillcode,
			String strOperId) throws Exception
	{
		String strBcprefix = objWaybillcodeCol.getBcbcprefix();
		String strBcsuffix = objWaybillcodeCol.getBcbcsuffix();
		objTfsWaybillcode.setBcEndcode(objWaybillcodeCol.getBcbcendcode());
		objTfsWaybillcode.setBcModifydate(DateFormatUtility.getSysdate());
		objTfsWaybillcode.setBcStartcode(objWaybillcodeCol.getBcbcstartcode());
		
		if(!StringUtility.isNull(strBcprefix))
			objTfsWaybillcode.setBcPrefix(strBcprefix);
		else 
			objTfsWaybillcode.setBcPrefix("");
		if(!StringUtility.isNull(objWaybillcodeCol.getBcbcremark()))
			objTfsWaybillcode.setBcRemark(objWaybillcodeCol.getBcbcremark());
		else
			objTfsWaybillcode.setBcRemark("");
		if(!StringUtility.isNull(strBcsuffix))
			objTfsWaybillcode.setBcSuffix(strBcsuffix);
		else
			objTfsWaybillcode.setBcSuffix("");
		
		objTfsWaybillcode.setTdiOperatorByOpIdModifier(TdiOperatorDC.loadByKey(strOperId));
		objTfsWaybillcode.setTdiWaybillcodekind(TdiWaybillcodekindDC.loadByKey(objWaybillcodeCol.getBckbckcode()));
		if(StringUtility.isNull(objWaybillcodeCol.getBcscscode()))
			objTfsWaybillcode.setTdiWaybillcodestatus(TdiWaybillcodestatusDC.loadByKey("RG"));
		else
			objTfsWaybillcode.setTdiWaybillcodestatus(TdiWaybillcodestatusDC.loadByKey(objWaybillcodeCol.getBcscscode()));
	}
	
}
