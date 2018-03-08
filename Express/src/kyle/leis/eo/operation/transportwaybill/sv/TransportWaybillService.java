package kyle.leis.eo.operation.transportwaybill.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.operation.transportwaybill.bl.TransportWaybill;
import kyle.leis.eo.operation.transportwaybill.da.StatistictcwColumns;
import kyle.leis.eo.operation.transportwaybill.da.StatistictcwCondition;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillCondition;
import kyle.leis.eo.operation.transportwaybill.da.TransportforcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillCondition;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybilltraceColumns;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;

public class TransportWaybillService extends AService {
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		TransportwaybillCondition objTWCondition = (TransportwaybillCondition)objPD.getParameter(0, 
				TransportwaybillCondition.class);
		
		List listResults = TransportWaybillDemand.query(objTWCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	/**
	 * 根据运输主单查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryCorewaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		TransportcorewaybillCondition objTCWCondition = (TransportcorewaybillCondition)objPD.getParameter(0, 
				TransportcorewaybillCondition.class);
		List listResults = TransportWaybillDemand.queryCorewaybill(objTCWCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	/**
	 * 装载出货主单和状态变化记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strTwbid = (String)objPD.getParameter(0, String.class);
		
		List listValue = TransportWaybillDemand.loadValue(strTwbid);
		List listTrace = TransportWaybillDemand.loadTrace(strTwbid);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listValue);
		objEncode.addParameter(listTrace);
		return objEncode.toString();
	}
	
	/**
	 * 根据票单主键(cw_code)查询运输主单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String loadByCwcode(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD, 1, this);
		
		String strCwcode = (String) objPD.getParameter(0, String.class);
		TransportWaybill objTransportWaybill = new TransportWaybill();
		TransportforcorewaybillColumns objReturn = objTransportWaybill.loadByCwcode(strCwcode);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();
	}
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);
		
		TransportwaybillColumns objTWColumns = (TransportwaybillColumns)objPD.getParameter(0, TransportwaybillColumns.class);
		String[] astrBwcode = objPD.getParameterArray(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		String[] astrBaglaelcode = objPD.getParameterArray(3, String.class);
		String[] astrCwcode = objPD.getParameterArray(4, String.class);
		
		TransportWaybill objTransportWaybill = new TransportWaybill();
		TransportwaybillColumns objTWBColumns = objTransportWaybill.save(objTWColumns,
				astrBwcode,
				strOperId,
				astrBaglaelcode,
				astrCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objTWBColumns);
		return objEncode.toString();
	}
	
	/**
	 * 添加出货主单，先删除再新增
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String addTransportvalue(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strTwbId = (String)objPD.getParameter(0, String.class);
		String[] astrBwcode = objPD.getParameterArray(1, String.class);
		TransportWaybill objTransportWaybill = new TransportWaybill();
		objTransportWaybill.addTransportvalue(strTwbId, 
				astrBwcode);
		
		return "";
	}
	
	public String addTransportcwvalue(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strTwbId = (String)objPD.getParameter(0, String.class);
		String[] astrCwcode = objPD.getParameterArray(1, String.class);
		TransportWaybill objTransportWaybill = new TransportWaybill();
		objTransportWaybill.addTransportcwvalue(strTwbId, 
				astrCwcode);
		
		return "";
	}	
	
	/**
	 * 修改状态
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyStatus(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		List listTWTColumns = objPD.getParameterList(0, TransportwaybilltraceColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		TransportWaybill objTransportWaybill = new TransportWaybill();
		objTransportWaybill.modifyStatus(listTWTColumns, 
				strOperId);
		return "";
	}
	
	/**
	 * 分摊运输费用
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String apportionTransportFee(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		String strTwbid = (String)objPD.getParameter(0, String.class); 
		String strCkcode = (String)objPD.getParameter(1, String.class); 
		String strTransportFeeTotal = (String)objPD.getParameter(2, String.class); 
		String strOperId = (String)objPD.getParameter(3, String.class);
		
		TransportWaybill objTransportWaybill = new TransportWaybill();
		objTransportWaybill.apportionTransportFee(strTwbid, 
				strCkcode,
				strTransportFeeTotal,
				strOperId);
		return "0";
	}	
	
	public String getTransportWaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strScanNo = (String)objPD.getParameter(0, String.class); 
		String strType = (String)objPD.getParameter(1, String.class); 
		
		TransportWaybill objTransportWaybill = new TransportWaybill();
		String str = objTransportWaybill.getTransportWaybill(strScanNo, 
				strType);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(str);
		return objEncode.toString();		
	}		
	
	public String querySTW(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		StatistictcwCondition objSTCWCondition = (StatistictcwCondition)objPD.getParameter(0, 
				StatistictcwCondition.class);
		StatistictcwColumns objStatistictcwColumns = TransportWaybillDemand.querySTW(objSTCWCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objStatistictcwColumns);
		return objEncode.toString();
	}	
	
}
