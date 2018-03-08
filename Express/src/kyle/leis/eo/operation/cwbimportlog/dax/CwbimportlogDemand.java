package kyle.leis.eo.operation.cwbimportlog.dax;

import java.util.Date;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportdataColumns;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportlogColumns;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportlogQuery;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowColumns;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowQuery;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TopCwbimportdata;
import kyle.leis.hi.TopCwbimportdataPK;
import kyle.leis.hi.TopCwbimportlog;
import kyle.leis.hi.TopCwbimportrow;
import kyle.leis.hi.TopCwbimportrowPK;

public class CwbimportlogDemand {
	
	public static void setCwbimportlogByColumns (TopCwbimportlog objCwbimportlog,
			CwbimportlogColumns Cwbimportlog,Long opId)throws Exception{
		objCwbimportlog.setCwlCreatedate(DateFormatUtility.getStandardDate(Cwbimportlog.getToccwlcreatedate()));
		objCwbimportlog.setCwlTotalrecords(Integer.parseInt(Cwbimportlog.getToccwltotalrecords()));
		objCwbimportlog.setCwlUnsuccessfulrecords(Integer.parseInt(Cwbimportlog.getToccwlunsuccessfulrecords()));
		objCwbimportlog.setCwlFilepath(Cwbimportlog.getToccwlfilepath());
		objCwbimportlog.setCwlRemark(Cwbimportlog.getToccwlremark());
		objCwbimportlog.setCwlStatus(Cwbimportlog.getToccwlstatus());
		if (!StringUtility.isNull(Cwbimportlog.getTocpotid())) {
			objCwbimportlog.setPotId(Long.valueOf(Cwbimportlog.getTocpotid()));
		}
		TdiOperator objTdiOperator=TdiOperatorDC.loadByKey(Cwbimportlog.getToctdioperatoropid());
		objCwbimportlog.setTdiOperator(objTdiOperator);
		
	}
	public static void setCwbimportrowByColumns(TopCwbimportrow objTopCwbimportrow,CwbimportrowColumns Cwbimportrow){
		objTopCwbimportrow.setCwbrSuccesssign(Cwbimportrow.getCwbrcwbrsuccesssign());
		objTopCwbimportrow.setCwbrOperatetype(Cwbimportrow.getCwbrcwbroperatetype());
		objTopCwbimportrow.setCwbrRemark(Cwbimportrow.getCwbrcwbrremark());
		TopCwbimportrowPK compId=new TopCwbimportrowPK();
		compId.setCwlId(Long.parseLong(Cwbimportrow.getCwbrtopcwbimportlogcwlid()));
		compId.setCwbrId(Long.parseLong(Cwbimportrow.getCwbrcomp_idcwbrid()));
		objTopCwbimportrow.setComp_id(compId);
		
	}
	public static void setCwbimportdataByColumns(TopCwbimportdata objTopCwbimportdata,CwbimportdataColumns Cwbimportdata){
		TopCwbimportdataPK objTCDPK=new TopCwbimportdataPK();
		objTCDPK.setCwbrId(Long.parseLong(Cwbimportdata.getCwbdcomp_idcwbrid()));
		objTCDPK.setCwlId(Long.parseLong(Cwbimportdata.getCwbdtopcwbimportrowtopcwbimportlogcwlid()));
		objTCDPK.setCwbdColumnname(Cwbimportdata.getCwbdcomp_idcwbdcolumnname());
		objTopCwbimportdata.setComp_id(objTCDPK);
		objTopCwbimportdata.setCwbdValue(Cwbimportdata.getCwbdcwbdvalue());
		
		
		
		
	}
	public static CwbimportlogColumns load(Long cwlId) throws Exception {
		CwbimportlogQuery objCILQuery = new CwbimportlogQuery();
		objCILQuery.setCwlid(cwlId.toString());
		List objList = objCILQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (CwbimportlogColumns)objList.get(0);
	}
	public static CwbimportrowColumns loadByCwbrId(Long cwbrId) throws Exception {
		CwbimportrowQuery objCIRQuery = new CwbimportrowQuery();
		objCIRQuery.setCwbrid(cwbrId.toString());
		List objList = objCIRQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (CwbimportrowColumns)objList.get(0);
	}
}
