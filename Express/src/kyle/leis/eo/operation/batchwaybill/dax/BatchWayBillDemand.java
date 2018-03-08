package kyle.leis.eo.operation.batchwaybill.dax;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillCondition;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillQuery;
import kyle.leis.eo.operation.batchwaybill.da.DeparturebatchwaybillCondition;
import kyle.leis.eo.operation.batchwaybill.da.DeparturebatchwaybillQuery;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillCondition;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillQuery;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwbvalueColumns;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwbvalueCondition;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwbvalueQuery;
import kyle.leis.eo.operation.batchwaybill.da.TopbatchwaybillTR;
import kyle.leis.eo.operation.batchwaybill.tp.SaveBatchwaybillTrans;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCorporationDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiArrivaldeparturetypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBatchwaybillstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiArrivaldeparturetype;
import kyle.leis.hi.TdiBatchwaybillstatus;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TopBatchwaybill;
import net.sf.hibernate.Session;

public class BatchWayBillDemand {
	
	public static List query(BatchwaybillCondition objBWCondition) throws Exception {
		BatchwaybillQuery objBWQuery = new BatchwaybillQuery();
		if(!StringUtility.isNull(objBWCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objBWCondition.getEecode());
			objBWCondition.setEestructurecode(strEestructurecode);
			objBWCondition.setEecode(null);
		}		
		objBWQuery.setCondition(objBWCondition);
		return objBWQuery.getResults();
	}
	
	//
	public static List queryDeparture(DeparturebatchwaybillCondition objDBWCondition) throws Exception {
		DeparturebatchwaybillQuery objDBWQuery = new DeparturebatchwaybillQuery();
		if(!StringUtility.isNull(objDBWCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objDBWCondition.getEestructurecode());
			objDBWCondition.setEestructurecode(strEestructurecode);
		}		
		objDBWQuery.setCondition(objDBWCondition);
		return objDBWQuery.getResults();
	}
	
	public static BatchwaybillColumns load(String strBwCode) throws Exception {
		BatchwaybillCondition objBWCondition = new BatchwaybillCondition();
		objBWCondition.setBwcode(strBwCode);
		List objList = query(objBWCondition);
		if (objList == null || objList.size() < 1) return null;
		return (BatchwaybillColumns)objList.get(0);
	}
	
	public static BatchwaybillColumns loadByBwLabelcode(String strBwLabelcode) throws Exception {
		BatchwaybillCondition objBWCondition = new BatchwaybillCondition();
		objBWCondition.setBwlabelcode(strBwLabelcode);
		List objList = query(objBWCondition);
		if (objList == null || objList.size() < 1) return null;
		return (BatchwaybillColumns)objList.get(0);
	}	
	
	public static SimplebatchwbvalueColumns loadBWVCorewaybill(String strBwvid) throws Exception {
		SimplebatchwbvalueQuery objSBVQ = new SimplebatchwbvalueQuery();
		objSBVQ.setBwbvid(strBwvid);
		List objList = objSBVQ.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (SimplebatchwbvalueColumns)objList.get(0);
	}		
	
	public static SimplebatchwbvalueColumns loadBWVCorewaybillByCW(String strCwcode) throws Exception {
		SimplebatchwbvalueQuery objSBVQ = new SimplebatchwbvalueQuery();
		objSBVQ.setCwcode(strCwcode);
		List objList = objSBVQ.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (SimplebatchwbvalueColumns)objList.get(0);
	}	
	
	public static List queryBWVCorewaybill(SimplebatchwbvalueCondition objSBVC) throws Exception {
		SimplebatchwbvalueQuery objSBVQ = new SimplebatchwbvalueQuery();
		objSBVQ.setCondition(objSBVC);
		return objSBVQ.getResults();
	}	
	
	
	public static List querySimpleBatchwaybill(SimplebatchwaybillCondition objSBWCondition) 
	throws Exception {
		SimplebatchwaybillQuery objSBWQuery = new SimplebatchwaybillQuery();
		if(!StringUtility.isNull(objSBWCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objSBWCondition.getEecode());
			objSBWCondition.setEestructurecode(strEestructurecode);
			objSBWCondition.setEecode(null);
		}	
		objSBWQuery.setCondition(objSBWCondition);
		return objSBWQuery.getResults();
	}
	
	public static SimplebatchwaybillColumns loadSimpleBatchwaybill(String strBwvid) 
	throws Exception {
		SimplebatchwaybillQuery objSBWQuery = new SimplebatchwaybillQuery();
		objSBWQuery.setBwbvid(strBwvid);
		List listResults = objSBWQuery.getResults();
		if (listResults == null || listResults.size() < 1)
			return null;
		return (SimplebatchwaybillColumns)listResults.get(0);
	}	
	
	public static SimplebatchwaybillColumns getLatestBatchwaybill(String strChncode,
			String strCocode,
			String strEecode,
			String strAddDate,
			String strAdtcode) throws Exception {
		List objList = getLatestBatchwaybill(strChncode,
				strCocode,
				strEecode,
				strAddDate.substring(0, 10) + " 00:00:00",
				strAddDate.substring(0, 10) + " 23:59:59",
				strAdtcode);
		if (objList == null || objList.size() < 1) return null;
		return (SimplebatchwaybillColumns)objList.get(0);
	}
	
	public static List getLatestBatchwaybill(String strChncode,
			String strCocode,
			String strEecode,
			String strStartAddDate,
			String strEndAddDate,
			String strAdtcode) throws Exception {
		SimplebatchwaybillCondition objSBWCondition = new SimplebatchwaybillCondition();
		objSBWCondition.setCocode(strCocode);
		objSBWCondition.setChncode(strChncode);
		objSBWCondition.setEecode(strEecode);
		objSBWCondition.setNotinbwscode("EL");
		objSBWCondition.setAdtcode(strAdtcode);
		objSBWCondition.setStartadddate(strStartAddDate.substring(0, 10) + " 00:00:00");
		objSBWCondition.setEndadddate(strEndAddDate.substring(0, 10) + " 23:59:59");
		return querySimpleBatchwaybill(objSBWCondition);
	}	
	
	public static String createLatestUncompleteABW(String strCocode,
			String strChncode,
			String strEecode,
			String strAdddate,
			String strOperId) throws Exception {
		SimplebatchwaybillColumns objSBWBColumns = getLatestUnCompleteBatchwaybill(strChncode, 
				strCocode, 
				strEecode, 
				strAdddate, 
				"A");
		if (objSBWBColumns == null ||
				StringUtility.isNull(objSBWBColumns.getBwbwcode())) {
			SaveBatchwaybillTrans objAddBWT = new SaveBatchwaybillTrans();
			objAddBWT.setArrivalParam(strCocode, 
					strChncode, 
					strAdddate, 
					strEecode, 
					strOperId, 
					null);
			objAddBWT.execute();
			return String.valueOf(objAddBWT.getNewBatchWayBill().getBwCode());	
		}
		return objSBWBColumns.getBwbwcode();
	}
	
	
	public static SimplebatchwaybillColumns getLatestUnCompleteBatchwaybill(String strChncode,
			String strCocode,
			String strEecode,
			String strAddDate,
			String strAdtcode) throws Exception {
		SimplebatchwaybillCondition objSBWCondition = new SimplebatchwaybillCondition();
		objSBWCondition.setCocode(strCocode);
		objSBWCondition.setChncode(strChncode);
		objSBWCondition.setEecode(strEecode);
		objSBWCondition.setNotinbwscode("EL,CF");
		objSBWCondition.setAdtcode(strAdtcode);
		objSBWCondition.setStartadddate(strAddDate.substring(0, 10) + " 00:00:00");
		objSBWCondition.setEndadddate(strAddDate.substring(0, 10) + " 23:59:59");
		List objList = querySimpleBatchwaybill(objSBWCondition);
		if (objList == null || objList.size() < 1) return null;
		return (SimplebatchwaybillColumns)objList.get(0);
	}	
	
	public static void setBatchwaybillByColumns(BatchwaybillColumns objBWColumns,
			TopbatchwaybillTR objTopbatchwaybillTR,
			String strOperId,
			Session objSession) throws Exception {
		objTopbatchwaybillTR.setAdd_date(objBWColumns.getBwadddate());
		objTopbatchwaybillTR.setBw_modifydate(DateFormatUtility.getStandardSysdate());
		objTopbatchwaybillTR.setBw_remark(objBWColumns.getBwbwremark());
		objTopbatchwaybillTR.setBw_containerid(objBWColumns.getBwbwcontainerid());
		// 代理渠道或服务渠道
		if (!StringUtility.isNull(objBWColumns.getChnchncode())) {
			TchnChannel objChannel = TchnChannelDC.loadByKey(objBWColumns.getChnchncode());
			objTopbatchwaybillTR.setChn_code(objBWColumns.getChnchncode());
			objTopbatchwaybillTR.setCo_code(objChannel.getTcoCorporation().getCoCode());
		}
		// 代理商或服务商
		if (!StringUtility.isNull(objBWColumns.getCococode()) &&
				StringUtility.isNull(objBWColumns.getChnchncode())) {
			objTopbatchwaybillTR.setCo_code(objBWColumns.getCococode());
		}
		// 到货或出货类型
		if (!StringUtility.isNull(objBWColumns.getAdtadtcode())) {
			objTopbatchwaybillTR.setAdt_code(objBWColumns.getAdtadtcode());
		}
		// 总单状态
		if (!StringUtility.isNull(objBWColumns.getBwsbwscode())) {
			objTopbatchwaybillTR.setBws_code(objBWColumns.getBwsbwscode());
		}
		// 分拨点
		if (!StringUtility.isNull(objBWColumns.getEeeecode())) {
			objTopbatchwaybillTR.setEe_code(objBWColumns.getEeeecode());
		}
		// 修改人
		if (!StringUtility.isNull(strOperId)) {
			objTopbatchwaybillTR.setBw_op_id_modify(strOperId);
		}	
	}	
	
	public static void setBatchwaybillByColumns(BatchwaybillColumns objBWColumns,
			TopBatchwaybill objTopBatchwaybill,
			String strOperId,
			Session objSession
			) throws Exception {
		objTopBatchwaybill.setAddDate(DateFormatUtility.getStandardDate(objBWColumns.getBwadddate()));
		objTopBatchwaybill.setBwModifydate(DateFormatUtility.getSysdate());
		objTopBatchwaybill.setBwRemark(objBWColumns.getBwbwremark());
		objTopBatchwaybill.setBwContainerid(objBWColumns.getBwbwcontainerid());
		// 代理渠道或服务渠道
		TchnChannel objChannel = null;
		if (!StringUtility.isNull(objBWColumns.getChnchncode())) {
			objChannel = (TchnChannel)objSession.load(TchnChannel.class, 
					objBWColumns.getChnchncode());
			objTopBatchwaybill.setTchnChannel(objChannel);
			objTopBatchwaybill.setTcoCorporation(objChannel.getTcoCorporation());
		}
		// 代理商或服务商
		TcoCorporation objCorporation = null;
		if (!StringUtility.isNull(objBWColumns.getCococode()) &&
				StringUtility.isNull(objBWColumns.getChnchncode())) {
			objCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, objBWColumns.getCococode());
			objTopBatchwaybill.setTcoCorporation(objCorporation);
		}
		// 到货或出货类型
		if (!StringUtility.isNull(objBWColumns.getAdtadtcode())) {
			TdiArrivaldeparturetype objADTCode = TdiArrivaldeparturetypeDC.loadByKey(objBWColumns.getAdtadtcode());
			objTopBatchwaybill.setTdiArrivaldeparturetype(objADTCode);
		}
		// 总单状态
		if (!StringUtility.isNull(objBWColumns.getBwsbwscode())) {
			TdiBatchwaybillstatus objBWS = TdiBatchwaybillstatusDC.loadByKey(objBWColumns.getBwsbwscode());
			objTopBatchwaybill.setTdiBatchwaybillstatus(objBWS);
		}
		// 分拨点
		TdiEnterpriseelement objEnterpriseE = null;
		if (!StringUtility.isNull(objBWColumns.getEeeecode())) {
			objEnterpriseE = TdiEnterpriseelementDC.loadByKey(objBWColumns.getEeeecode());
			objTopBatchwaybill.setTdiEnterpriseelement(objEnterpriseE);
		}
		// 修改人
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objOperator = TdiOperatorDC.loadByKey(strOperId);
			objTopBatchwaybill.setTdiOperatorByBwOpIdModify(objOperator);
		}	
	}
	
	public static String getNewBatchnumber(String strChncode,
			String strCocode, 
			String strEecode, 
			String strAddDate,
			String strAdtcode) throws Exception {
		SimplebatchwaybillColumns objSBWColumns = getLatestBatchwaybill(strChncode,
				strCocode, 
				strEecode, 
				strAddDate, 
				strAdtcode);
		if (objSBWColumns == null) return "A";
		String strBatchnumber = objSBWColumns.getBwbwbatchnumber();
		int iNextAscii = strBatchnumber.toCharArray()[0] + 1;
		return String.valueOf((char)iNextAscii);
	}
	
	
	public static String getBWLabelCode(TopBatchwaybill objTopBatchwaybill) {
		// 设置批次号
		String strCosename = "";
		if (objTopBatchwaybill.getTchnChannel() != null)
			strCosename = objTopBatchwaybill.getTchnChannel().getChnSename();
		else
			strCosename = objTopBatchwaybill.getTcoCorporation().getCoSename();
		String strLabelCode = strCosename + "-" + objTopBatchwaybill.getTdiEnterpriseelement().getEeEsname();
		if (objTopBatchwaybill.getTdiArrivaldeparturetype().getAdtCode().equals("D")) {
			strLabelCode = objTopBatchwaybill.getTdiEnterpriseelement().getEeEsname() + "-" + strCosename;
		} else if  (objTopBatchwaybill.getTdiArrivaldeparturetype().getAdtCode().equals("W")) {
			strLabelCode = strCosename + "-W-" + objTopBatchwaybill.getTdiEnterpriseelement().getEeEsname();
		}
		return strLabelCode + "-" + 
		DateFormatUtility.getCompactDate(objTopBatchwaybill.getAddDate()).substring(0, 6) + 
		objTopBatchwaybill.getBwBatchnumber();
	}
	
	public static String getBWLabelCode(TopbatchwaybillTR objTopbatchwaybillTR,
			Session objSession) throws Exception {
		// 设置批次号
		String strCosename = "";
		if (objTopbatchwaybillTR.getChn_code() != null) {
			TchnChannel objChannel = TchnChannelDC.loadByKey(objTopbatchwaybillTR.getChn_code());
			strCosename = objChannel.getChnSename();
		}
		else {
			TcoCorporation objTcoCorporation = TcoCorporationDC.loadByKey(objTopbatchwaybillTR.getCo_code());
			strCosename = objTcoCorporation.getCoSename();
		}
		TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objTopbatchwaybillTR.getEe_code());
		
		String strLabelCode = strCosename + "-" + objTEE.getEeEsname();
		if (objTopbatchwaybillTR.getAdt_code().equals("D")) {
			strLabelCode = objTEE.getEeEsname() + "-" + strCosename;
		} else if  (objTopbatchwaybillTR.getAdt_code().equals("W")) {
			strLabelCode = strCosename + "-W-" + objTEE.getEeEsname();
		}
		return strLabelCode + "-" + DateFormatUtility.getCompactDate(DateFormatUtility.getStandardDate(objTopbatchwaybillTR.getAdd_date())).substring(0, 6) + objTopbatchwaybillTR.getBw_batchnumber();
	}	
	
	
	public static String checkPackageBillcounts(String strBwcode,
			String strAdtcode) throws Exception {
		// 检查小包票数是否相等
		SimplebatchwbvalueCondition objSBVC = new SimplebatchwbvalueCondition();
		objSBVC.setBwcode(strBwcode);
		List listResults = BatchWayBillDemand.queryBWVCorewaybill(objSBVC);
		StringBuffer sb = new StringBuffer();
		if (listResults != null && listResults.size() > 0) {
			for (int i = 0; i < listResults.size(); i++) {
				SimplebatchwbvalueColumns objSBVColumns = (SimplebatchwbvalueColumns)listResults.get(i);
				if (objSBVColumns.getPkpk_name().indexOf("挂号") < 0)
					continue;
				String strBwvid = objSBVColumns.getBwvbwbv_id();
				HousewaybillCondition objHWBCondition = new HousewaybillCondition();
				
				if (strAdtcode.equals("A"))
					objHWBCondition.setAbwvid(strBwvid);
				else
					objHWBCondition.setDbwvid(strBwvid);
				objHWBCondition.setPkcode(objSBVColumns.getPkpk_code());
				objHWBCondition.setCwbatchwaybillsign("N");
				List listHousewaybill = HousewaybillDemand.query(objHWBCondition);
				String strBillcounts = objSBVColumns.getCwcw_billcounts();
				if (listHousewaybill.size() != Integer.parseInt(strBillcounts))
					sb.append("小包号码 " + objSBVColumns.getCwcw_ewbcode() + " 的票数为 " + strBillcounts + " 但其下的挂号运单票数为 " + 
					listHousewaybill.size() + "，票数不一致\r\n");
			}
		}
		return sb.toString();
	}
}
