package kyle.leis.fs.dictionary.transporttool.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiTransportwaybillkindDC;
import kyle.leis.fs.dictionary.transporttool.da.TransporttoolColumns;
import kyle.leis.fs.dictionary.transporttool.da.TransporttoolCondition;
import kyle.leis.fs.dictionary.transporttool.da.TransporttoolQuery;
import kyle.leis.hi.TdiTrasporttool;

public class TransporttoolDemand {
	
	public static List query(TransporttoolCondition objTransporttoolCon) throws Exception
	{
		TransporttoolQuery objTransporttoolQue = new TransporttoolQuery();
		objTransporttoolQue.setCondition(objTransporttoolCon);
		return objTransporttoolQue.getResults();
	}
	
	public static TransporttoolColumns queryByTtcode(String ttCode) throws Exception
	{
		TransporttoolCondition objTransporttoolCon = new TransporttoolCondition();
		objTransporttoolCon.setTtcode(ttCode);
		List objList = query(objTransporttoolCon);
		if(objList.size()<1) return null;
		return (TransporttoolColumns)objList.get(0);
	}
	
	public static void setTransporttoolByCol(TdiTrasporttool objTdiTransporttool,TransporttoolColumns objTransporttoolCol) throws Exception
	{
		objTdiTransporttool.setTdiDistrictByDtCodeArrival(TdiDistrictDC.loadByKey(objTransporttoolCol.getDtardtcode()));
		objTdiTransporttool.setTdiDistrictByDtCodeDeparture(TdiDistrictDC.loadByKey(objTransporttoolCol.getDtdpdtcode()));
		objTdiTransporttool.setTdiTransportwaybillkind(TdiTransportwaybillkindDC.loadByKey(objTransporttoolCol.getTwbktwbkcode()));
		objTdiTransporttool.setTtArrivaltime(objTransporttoolCol.getTtttarrivaltime());
		objTdiTransporttool.setTtDeparturetime(objTransporttoolCol.getTtttdeparturetime());
		objTdiTransporttool.setTtDrivername(objTransporttoolCol.getTtttdrivername());
		if(!StringUtility.isNull(objTransporttoolCol.getTtttlabelcode()))
			objTdiTransporttool.setTtLabelcode(objTransporttoolCol.getTtttlabelcode());
		if(!StringUtility.isNull(objTransporttoolCol.getChnchncode()))
			objTdiTransporttool.setTchnChannel(TchnChannelDC.loadByKey(objTransporttoolCol.getChnchncode()));
	}
}
