package kyle.leis.eo.operation.corewaybill.dax;

import java.io.CharArrayWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class RequestEubXML extends RequestXMLEX {

	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns, 
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection) throws Exception {
		// TODO Auto-generated method stub
		// <mailnum>
		// longxiaoyi_af3dedf8cb3335e6879cbd5912b2d41d
		StringBuffer sbXML = new StringBuffer();
		
		sbXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sbXML.append("<orders xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		sbXML.append("<order>");
		sbXML.append("<orderid>" + objFIAColumns.getCwcustomerewbcode() + "</orderid>");
		sbXML.append("<operationtype>0</operationtype>");
		sbXML.append("<producttype>0</producttype>");
		sbXML.append("<customercode>WENFENG HE</customercode>");
		sbXML.append("<vipcode></vipcode>");
		sbXML.append("<clcttype>0</clcttype>");
		sbXML.append("<pod>false</pod>");
		sbXML.append("<untread>Abandoned</untread>");
		sbXML.append("<volweight>0</volweight>");
		
		String strBegindate = DateFormatUtility.getStandardSysdate();
		sbXML.append("<startdate>" + strBegindate.substring(0, 10) + "T00:00:00</startdate>");
		sbXML.append("<enddate>" + DateUtility.getMoveDate(2).substring(0, 10) + "T23:59:59</enddate> ");
		
		// 用收发件人代替
		ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.load("EUB_001");
		if (objSCColumns == null) {
			return "E_001_请先设置EUB_001的发件人信息";
		}
		sbXML.append("<printcode>01</printcode> ");
		sbXML.append("<sender> ");
		sbXML.append("<name>" + objSCColumns.getScscname() + "</name>");
		sbXML.append("<postcode>510403</postcode> ");
		sbXML.append("<phone></phone> ");
		sbXML.append("<mobile></mobile>");
		sbXML.append("<country>CN</country> ");
		sbXML.append("<province>440000</province> ");
		sbXML.append("<city>440100</city>  ");
		sbXML.append("<county>440111</county>   ");
		sbXML.append("<company></company>   ");
		sbXML.append("<street>" + objSCColumns.getScscaddress1() + objSCColumns.getScscaddress2() + objSCColumns.getScscaddress3() + "</street>");
		sbXML.append("<email></email>   ");
		sbXML.append("</sender>");
		
		sbXML.append("<receiver>");
		sbXML.append("<name>" + objFIAColumns.getHwconsigneename() + "</name>");
		sbXML.append("<postcode>" + objFIAColumns.getHwconsigneepostcode() + "</postcode>");
		sbXML.append("<phone>" + objFIAColumns.getHwconsigneetelephone() + "</phone>");
		sbXML.append("<mobile></mobile>");
		
		String strDtcode = objFIAColumns.getDtcode();
		sbXML.append("<country>" + DistrictDemand.getCountryEnameByCity(strDtcode) + "</country>");
		
		String strStatecode = DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
				"",
				DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
				objFIAColumns.getHwconsigneepostcode());
		
		sbXML.append("<province>"+ strStatecode + "</province>");
		sbXML.append("<city>" + objFIAColumns.getHwConsigneecity() + "</city>");
		sbXML.append("<county></county>"); 
		sbXML.append("<street>" + objFIAColumns.getHwconsigneeaddress1() + "</street>");
		sbXML.append("<street_extra_1>" + objFIAColumns.getHwconsigneeaddress2() + "</street_extra_1>");
		sbXML.append("<street_extra_2>" + objFIAColumns.getHwconsigneeaddress3() + "</street_extra_2>");
		sbXML.append("</receiver>");
		
		sbXML.append("<collect>");
		sbXML.append("<name>" + objFIAColumns.getHwshippername() + "</name>");
		sbXML.append("<postcode>" + StringUtility.replaceWhenNull(objFIAColumns.getHwshipperpostcode(),".", "518000") + "</postcode>");
		sbXML.append("<phone>" + objFIAColumns.getHwshippertelephone() + "</phone>");
		sbXML.append("<mobile></mobile>");
		sbXML.append("<country>CN</country>");
		sbXML.append("<province>440000</province>");
		sbXML.append("<city>440300</city>");
		sbXML.append("<county>440306</county>");
		sbXML.append("<company></company>");
		sbXML.append("<street>" + objFIAColumns.getHwshipperaddress1() + "</street>");
		sbXML.append("<email></email>");
		sbXML.append("</collect>");  		
		
		sbXML.append("<items>");
		
		if (listCargo == null || listCargo.size() < 1) {
			return "E_001_商品信息不能为空";
		}
		// 重量
		String strWeight = objFIAColumns.getCwchargeweight();
		BigDecimal obj = new BigDecimal(strWeight);
		BigDecimal objDivideValue = obj.divide(new BigDecimal(listCargo.size()), 3, 1);		
		
		String sku1 = "", sku2 = "";
		for (int i = 0; i < listCargo.size(); i++) {
			CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
			sbXML.append("<item>"); 
			sbXML.append("<cnname>" + 
					StringUtility.replaceWhenNull(objCIC.getCiciname(), objCIC.getCiciename())
					+ "</cnname>"); 
			sbXML.append("<enname>" + objCIC.getCiciename() + "</enname>"); 
			sbXML.append("<count>" + objCIC.getCicipieces() + "</count>"); 
			sbXML.append("<weight>" + objDivideValue.toString() + "</weight>");
			sbXML.append("<delcarevalue>" + objCIC.getCicitotalprice() + "</delcarevalue>");
			sbXML.append("<origin>CN</origin>");
			sbXML.append("</item>");
			if (!StringUtility.isNull(objCIC.getCiciattacheinfo()) &&
					StringUtility.isNull(sku1))
				sku1 = objCIC.getCiciattacheinfo();
			if (!StringUtility.isNull(objCIC.getCiciattacheinfo()) &&
					!StringUtility.isNull(sku1))
				sku2 = objCIC.getCiciattacheinfo();
		}
		
		sbXML.append("</items>");
		
		sbXML.append("<remark/>");
		if (!StringUtility.isNull(sku1))
			sbXML.append("<sku1>" + sku1 + "</sku1>");
		
		if (!StringUtility.isNull(sku2))
			sbXML.append("<sku2>" + sku2 + "</sku2>");
		
		sbXML.append("</order>");
		sbXML.append("</orders>");		
		
		return formatXML(sbXML.toString());
	}
	
	private String formatXML(String xmlStr) throws Exception {
		Document doc = DocumentHelper.parseText(xmlStr);
		// 格式化XML
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setSuppressDeclaration(true);// 设置不输出XML声明
		Writer out = new CharArrayWriter();
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
		String res = out.toString();
		return res;
	}	

}
