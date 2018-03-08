package kyle.leis.eo.operation.transportwaybill.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.operation.transportwaybill.da.StatistictcwColumns;
import kyle.leis.eo.operation.transportwaybill.da.StatistictcwCondition;
import kyle.leis.eo.operation.transportwaybill.da.StatistictcwQuery;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillCondition;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillQuery;
import kyle.leis.eo.operation.transportwaybill.da.TransportforcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportforcorewaybillCondition;
import kyle.leis.eo.operation.transportwaybill.da.TransportforcorewaybillQuery;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillCondition;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillQuery;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybilltraceColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybilltraceQuery;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillvalueColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillvalueQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiTransportwaybillkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiTransportwaybillstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiTrasporttoolDC;
import kyle.leis.fs.dictionary.dictionarys.dax.TransportTrackDemand;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiTransportwaybillkind;
import kyle.leis.hi.TdiTransportwaybillstatus;
import kyle.leis.hi.TdiTrasporttool;
import kyle.leis.hi.TopTransportwaybill;
import net.sf.hibernate.Session;

public class TransportWaybillDemand {
	/**
	 * 查询
	 * @param objTWCondition
	 * @return
	 * @throws Exception
	 */
	public static List query(TransportwaybillCondition objTWCondition) throws Exception {
		
		if (!StringUtility.isNull(objTWCondition.getEestructurecode())) {
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objTWCondition.getEestructurecode());
			objTWCondition.setEestructurecode(strEestructurecode);
		}
		TransportwaybillQuery objTWQuery = new TransportwaybillQuery();
		objTWQuery.setCondition(objTWCondition);
		return objTWQuery.getResults();
	}
	
	/**
	 * 根据票单主键(cw_code)查询运单
	 * @param strTwbid
	 * @return
	 * @throws Exception
	 */
	public static TransportwaybillColumns load(String strTwbid) throws Exception {
		TransportwaybillQuery objTWQuery = new TransportwaybillQuery();
		objTWQuery.setTwbid(strTwbid);
		List objList = objTWQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (TransportwaybillColumns)objList.get(0);
	}	
	
	public static TransportwaybillColumns loadByTwbcode(String strTwblabelcode) throws Exception {
		TransportwaybillQuery objTWQuery = new TransportwaybillQuery();
		objTWQuery.setTwblabelcode(strTwblabelcode);
		List objList = objTWQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (TransportwaybillColumns)objList.get(0);
	}		
	
	
	/**
	 * 根据运输主单查询运单
	 * @param objTCWCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryCorewaybill(TransportcorewaybillCondition objTCWCondition) 
	throws Exception {
		TransportcorewaybillQuery objTCWQuery = new TransportcorewaybillQuery();
		objTCWQuery.setCondition(objTCWCondition);
		return objTCWQuery.getResults();
	}
	
	public static List loadCorewaybill(String strTwbid) throws Exception {
		TransportcorewaybillQuery objTCWQuery = new TransportcorewaybillQuery();
		objTCWQuery.setTwbid(strTwbid);
		List listResults = objTCWQuery.getResults();
		
		TransportcorewaybillQueryEX objTCWQueryEX = new TransportcorewaybillQueryEX();
		objTCWQueryEX.setTwbid(strTwbid);
		List list = objTCWQueryEX.getResults();
		/*
		TransportCWBagQuery objTCWBQEX = new TransportCWBagQuery();
		objTCWBQEX.setTwbid(strTwbid);
		List listTCWB = objTCWBQEX.getResults();
		*/
		listResults.addAll(list);
		//listResults.addAll(listTCWB);
		
		return listResults;
	}
	
	/*public static TransportforcorewaybillColumns loadByCwcode(String strCwcode) throws Exception
	{
		TransportforcorewaybillQuery objTFCWQuery = new TransportforcorewaybillQuery();
		TransportforcorewaybillCondition objTFCWCondition = new TransportforcorewaybillCondition();
		objTFCWCondition.setCwcode(strCwcode);
		objTFCWQuery.setCondition(objTFCWCondition);
		List objList = objTFCWQuery.getResults();
		if(CollectionUtility.isNull(objList)) return null;
		return (TransportforcorewaybillColumns) objList.get(0);
	}*/
	
	public static TransportforcorewaybillColumns queryForCW(TransportforcorewaybillCondition objTFCWCondition) throws Exception
	{
		TransportforcorewaybillQuery objTFCWQuery = new TransportforcorewaybillQuery();
		objTFCWQuery.setCondition(objTFCWCondition);
		List objList = objTFCWQuery.getResults();
		if(CollectionUtility.isNull(objList)) return null;
		return (TransportforcorewaybillColumns) objList.get(0);
	}
	
	public static BigDecimal sumCorewaybillGrossweight(List listTransportcorewaybill) {
		BigDecimal objSumGrossweight = new BigDecimal("0");
		if (listTransportcorewaybill == null || listTransportcorewaybill.size() < 1)
			return objSumGrossweight;
		for (int i = 0; i < listTransportcorewaybill.size(); i++) {
			TransportcorewaybillColumns objTCWBColumns = (TransportcorewaybillColumns)listTransportcorewaybill.get(i);
			objSumGrossweight = objSumGrossweight.add(new BigDecimal(objTCWBColumns.getCwcw_grossweight()));
		}
		return objSumGrossweight;
	}
	
	public static TransportwaybilltraceColumns getLatestTWBTrace(List<TransportwaybilltraceColumns> listTWTColumns) {
		if (listTWTColumns == null || listTWTColumns.size() < 1)
			return null;
		TransportwaybilltraceColumns objLatestTWBTrace = listTWTColumns.get(0);
		if (listTWTColumns.size() <= 1)
			return objLatestTWBTrace;
		for (int i = 1; i < listTWTColumns.size(); i++) {
			TransportwaybilltraceColumns objTWBTrace = listTWTColumns.get(i);
			if (objLatestTWBTrace.getTwbttwbtoccurdate().compareTo(objTWBTrace.getTwbttwbtoccurdate()) < 0)
				objLatestTWBTrace = objTWBTrace;
		}
		return objLatestTWBTrace;
	}
	
	public static StatistictcwColumns querySTW(StatistictcwCondition objSTCWCondition) throws Exception {
		StatistictcwQuery objStatistictcwQuery = new StatistictcwQuery();
		objStatistictcwQuery.setCondition(objSTCWCondition);
		List listResults = objStatistictcwQuery.getResults();
		if (listResults == null || listResults.size() < 1)
			return null;
		return (StatistictcwColumns)listResults.get(0);
	}
	
	/**
	 * 装载出货主单值
	 * @param strTwbid
	 * @return
	 * @throws Exception
	 */
	public static List loadValue(String strTwbid) throws Exception {
		TransportwaybillvalueQuery objTWVQuery = new TransportwaybillvalueQuery();
		objTWVQuery.setTwbid(strTwbid);
		return objTWVQuery.getResults();
	}
	
	public static TransportwaybillvalueColumns queryByBwcode(String strBwcode) throws Exception {
		TransportwaybillvalueQuery objTWVQuery = new TransportwaybillvalueQuery();
		objTWVQuery.setBwcode(strBwcode);
		List listResults = objTWVQuery.getResults();
		if (listResults == null || listResults.size() < 1)
			return null;
		return (TransportwaybillvalueColumns)listResults.get(0);
	}	
	
	
	public static List<WaybilltrackColumns> buildWaybillTrack(List<TransportwaybilltraceColumns> listTWBTColumns,
			String strCwcode) throws Exception {
		if (listTWBTColumns == null || listTWBTColumns.size() < 1)
			return null;
		List<WaybilltrackColumns> listWBTColumns = new ArrayList<WaybilltrackColumns>();
		for (int i = 0; i < listTWBTColumns.size(); i++) {
			TransportwaybilltraceColumns objTWBTColumns = (TransportwaybilltraceColumns)listTWBTColumns.get(i);
			WaybilltrackColumns objWaybilltrackColumns = new WaybilltrackColumns();
			
			String strWbtscode = TransportTrackDemand.getTrackStatus(objTWBTColumns.getTwbtcomp_idtwbscode());
			if (StringUtility.isNull(strWbtscode)) continue;			
			
			objWaybilltrackColumns.setDtdtcode(objTWBTColumns.getDtdtcode());
			objWaybilltrackColumns.setWbbtcwcode(Long.parseLong(strCwcode));
			objWaybilltrackColumns.setWbtswbtscode(strWbtscode);
			objWaybilltrackColumns.setWbtwbtoccurdate(DateFormatUtility.getStandardDate(objTWBTColumns.getTwbttwbtoccurdate()));
			objWaybilltrackColumns.setWbtwbtopensign("Y");
			objWaybilltrackColumns.setWbtwbtfrom("TWB");
			
			listWBTColumns.add(objWaybilltrackColumns);
		}
		return listWBTColumns;
	}
	
	
	/**
	 * 装载运输主单状态跟踪记录
	 * @param strTwbid
	 * @return
	 * @throws Exception
	 */
	public static List loadTrace(String strTwbid) throws Exception {
		TransportwaybilltraceQuery objTWTQuery = new TransportwaybilltraceQuery();
		objTWTQuery.setTwbid(strTwbid);
		return objTWTQuery.getResults();
	}
	
	public static void setTransportByColumns(TopTransportwaybill objTTransportwaybill,
			TransportwaybillColumns objTWColumns,
			String strOperId,
			Session objSession) throws Exception {
		objTTransportwaybill.setTwbLabelcode(objTWColumns.getTwbtwblabelcode());
		if (!StringUtility.isNull(objTWColumns.getTwbktwbkcode())) {
			TdiTransportwaybillkind objTTWBK = TdiTransportwaybillkindDC.loadByKey(objTWColumns.getTwbktwbkcode());
			objTTransportwaybill.setTdiTransportwaybillkind(objTTWBK);
		}
		if (!StringUtility.isNull(objTWColumns.getTwbstwbscode())) {
			TdiTransportwaybillstatus objTTWS = TdiTransportwaybillstatusDC.loadByKey(objTWColumns.getTwbstwbscode());
			objTTransportwaybill.setTdiTransportwaybillstatus(objTTWS);
		}
		if (!StringUtility.isNull(objTWColumns.getTtttcode())) {
			TdiTrasporttool objTTT = TdiTrasporttoolDC.loadByKey(objTWColumns.getTtttcode());
			objTTransportwaybill.setTdiTrasporttool(objTTT);
		}
		if (!StringUtility.isNull(objTWColumns.getEeeecode())) {
			TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objTWColumns.getEeeecode());
			objTTransportwaybill.setTdiEnterpriseelement(objTEE);
		}
	}
	
}
