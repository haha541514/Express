package kyle.leis.fs.dictionary.enterpriseelement.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.enterpriseelement.bl.Distributioncenter;
import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterCondition;
import kyle.leis.fs.dictionary.enterpriseelement.da.EecityColumns;
import kyle.leis.fs.dictionary.enterpriseelement.dax.DistributioncenterDemand;

public class DistributioncenterService extends AService {

	/*
	 * 查询分拨点
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		DistributioncenterCondition objDistributioncenterCon = (DistributioncenterCondition) objPD.getParameter(0, DistributioncenterCondition.class);
		List objList = DistributioncenterDemand.query(objDistributioncenterCon);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objList);
		return objEncoder.toString();
	}
	
	/*
	 * 添加分拨点
	 */
	public String addDistributioncenter(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD, 3, this);
		
		DistributioncenterColumns objDistributioncenterCol = (DistributioncenterColumns) objPD.getParameter(0, DistributioncenterColumns.class);
		List listEECity = objPD.getParameterList(1, EecityColumns.class);
		String strOperId = (String) objPD.getParameter(2,String.class);
		
		
		Distributioncenter objDistributioncenter = new Distributioncenter();
		DistributioncenterColumns objDistributioncenterReturn = objDistributioncenter.addDistributioncenter(objDistributioncenterCol, 
				listEECity, 
				strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objDistributioncenterReturn);
		return objEncode.toString();
	}
}
