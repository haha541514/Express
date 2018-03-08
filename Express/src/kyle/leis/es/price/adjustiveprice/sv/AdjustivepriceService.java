package kyle.leis.es.price.adjustiveprice.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.adjustiveprice.bl.Adjustiveprice;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceColumns;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceCondition;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueColumns;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceDemand;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceValueDemand;
import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.sv.AExpressPriceService;

public class AdjustivepriceService extends AExpressPriceService {

	/*
	 * 查询价格设置
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		AdjustivepriceCondition objAdjustivepriceCon = (AdjustivepriceCondition) objPD.getParameter(0, AdjustivepriceCondition.class);
		List objList = AdjustivePriceDemand.query(objAdjustivepriceCon);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/*
	 * 根据设置编号查询价格设置
	 */
	public String queryByEpCode(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strEpCode = (String) objPD.getParameter(0, String.class);
		AdjustivepriceColumns objAdjustivepriceCol = AdjustivePriceDemand.queryByEpCode(strEpCode);
		List listAdjustivepricevalue = AdjustivePriceValueDemand.queryByEpCode(strEpCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objAdjustivepriceCol);
		objEncode.addParameter(listAdjustivepricevalue);
		return objEncode.toString();
	}
	
	
	
	/*
	 * 调整价格设置
	 */
	public String saveAdjustiveprice(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,4,this);
		
		AdjustivepriceColumns objAdjustivepriceCol = (AdjustivepriceColumns) objPD.getParameter(0, AdjustivepriceColumns.class);
		List listAdjustivepricevalueCol = objPD.getParameterList(1, AdjustivepricevalueColumns.class);
		String strOperId =(String)objPD.getParameter(2, String.class);
		String isIgnoreNotice = (String) objPD.getParameter(3, String.class);
		
		Adjustiveprice objAdjustiveprice = new Adjustiveprice();
		SavedResult objSavedResult = objAdjustiveprice.saveAdjustiveprice(objAdjustivepriceCol, listAdjustivepricevalueCol,strOperId,Boolean.valueOf(isIgnoreNotice));
		
		return objSavedResult.toString();
	}
	
	protected AExpressPrice getExpressPrice()
	{
		return new Adjustiveprice();
	}

}
