package kyle.leis.fs.dictionary.productkind.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.productkind.bl.Productkind;
import kyle.leis.fs.dictionary.productkind.da.PkcargokindColumns;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.da.ProductkindCondition;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;

public class ProductkindService extends AService {

	/*
	 * 根据编号(主键)查询产品
	 */
	public String queryBypkCode(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strpkCode = (String)objPD.getParameter(0, String.class);
		ProductkindColumns objProductkindCol = ProductkindDemand.queryBypkCode(strpkCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objProductkindCol);
		return objEncode.toString();
	}
	
	/*
	 * 查询产品
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		ProductkindCondition objProductkindCon = (ProductkindCondition)objPD.getParameter(0, ProductkindCondition.class);
		List objList = ProductkindDemand.query(objProductkindCon);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String queryPkcargokind(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strPkcode = (String)objPD.getParameter(0, String.class);
		List objList = ProductkindDemand.queryPkcargokind(strPkcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	/*
	 * 添加产品
	 */
	public String addProductkind(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD, 2, this);
		
		ProductkindColumns objProductkindCol = (ProductkindColumns) objPD.getParameter(0, ProductkindColumns.class);
		List listPkck = objPD.getParameterList(1, PkcargokindColumns.class);
		Productkind objProductkind = new Productkind();
		ProductkindColumns objReturnProductkindCol = objProductkind.addProductkind(objProductkindCol, listPkck);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturnProductkindCol);
		return objEncode.toString();
	}
	
	/*
	 * 修改简易状态
	 */
	public String modifyStatus(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strpkCode = (String)objPD.getParameter(0, String.class);
		String strssCode = (String)objPD.getParameter(1, String.class);
		
		Productkind objProductkind = new Productkind();
		objProductkind.modifyStatus(strpkCode, strssCode);
		
		return "";
	}
	
	/*
	 * 修改是否限制收货标记
	 */
	public String modifySign(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strpkCode = (String)objPD.getParameter(0, String.class);
		String strpkSign = (String)objPD.getParameter(1,String.class);
		
		Productkind objProductkind = new Productkind();
		objProductkind.modifySign(strpkCode, strpkSign);
		
		return "";
	}
	/**
	 * 获取产品代码
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String createProductCode(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 2, this);
		
		String code = (String)objPD.getParameter(0, String.class);
		String lever = (String) objPD.getParameter(1, String.class);
		String productCode = ProductkindDemand.createProductCode(code, lever);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(productCode);
		return objEncode.toString();
	}
}
