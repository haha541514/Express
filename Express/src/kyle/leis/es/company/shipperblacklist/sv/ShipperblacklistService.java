package kyle.leis.es.company.shipperblacklist.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.company.shipperblacklist.bl.Shipperblacklist;
import kyle.leis.es.company.shipperblacklist.da.ShipperblacklistColumns;
import kyle.leis.es.company.shipperblacklist.da.ShipperblacklistCondition;
import kyle.leis.es.company.shipperblacklist.dax.ShipperblacklistDemand;

public class ShipperblacklistService extends AService {
	/**
	 * 添加黑名单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String add(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		ShipperblacklistColumns objSBLColumns = (ShipperblacklistColumns)objPD.getParameter(0, ShipperblacklistColumns.class);
		String strOperId = (String) objPD.getParameter(1, String.class);
		
		Shipperblacklist objShipperbalcklist = new Shipperblacklist();
		ShipperblacklistColumns objSblReturnColumns = objShipperbalcklist.add(objSBLColumns, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSblReturnColumns);
		return objEncode.toString();
	}
	
	/**
	 * 批量添加黑名单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String batchAdd(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		List listSBLColumns = objPD.getParameterList(0, ShipperblacklistColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Shipperblacklist objShipperbalcklist = new Shipperblacklist();
		List listSBLColumnsReturn = objShipperbalcklist.batchAdd(listSBLColumns, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listSBLColumnsReturn);
		return objEncode.toString();
	}
	
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		ShipperblacklistCondition objSBLCondition = (ShipperblacklistCondition) objPD.getParameter(0, ShipperblacklistCondition.class);
		List objList = ShipperblacklistDemand.query(objSBLCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 删除
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String delete(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String[] astrSblcode = objPD.getParameterArray(0, String.class);
		Shipperblacklist objShipperblacklist = new Shipperblacklist();
		objShipperblacklist.delete(astrSblcode);
		
		return "";
	}
}
