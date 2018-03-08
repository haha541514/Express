package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoCondition;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoQuery;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.hi.TchnChannel;

public class RequestSGSoapXML  extends RequestXMLEX {

	
	@SuppressWarnings("unchecked")
	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo, 
			List listPieces, 
			PromptUtilityCollection objPUCollection)
			throws Exception {
		StringBuilder xmlStr = new StringBuilder();
		xmlStr.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		xmlStr.append("<soap12:Envelope  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:soap12=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
		xmlStr.append("<soap12:Body>");
		xmlStr.append("<GetImageLabelXMLNew xmlns=\"http://tempuri.org/\">");
		xmlStr.append("<ws>");
		
		//账户信息
		String strEnterprise = SystempropertyDemand.getEnterprise();	
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);	
		if (!StringUtility.isNull(objTchnChannel.getChnRegistername())) {
			xmlStr.append("<CustomerID>"+objTchnChannel.getChnRegistername()+"</CustomerID>");
			xmlStr.append("<SiteID>"+objTchnChannel.getChnRegistername()+"</SiteID>");
			xmlStr.append("<Password>"+objTchnChannel.getChnRegisterpassword()+"</Password>");				
		}else{
			return "用户不存在";
		}
		
		//申报价值
			String strDeclaredvalue = "0.00";
			String strDeclaredcurrency = "USD";
			String strDeclaredcontent = "DOC";
			if (listCargo != null && listCargo.size() > 0) {
				BigDecimal objDeclaredvalue = new BigDecimal("0");
				strDeclaredcontent = "";
				for (int i = 0; i < listCargo.size(); i++) {
					CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
					objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
					if (StringUtility.isNull(objCIC.getCkckcode()))
						strDeclaredcurrency = "USD";
					else
						strDeclaredcurrency = objCIC.getCkckcode();
					
					strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + ",";
				}
				if (!StringUtility.isNull(strDeclaredcontent) && 
						strDeclaredcontent.length() > 90)
					strDeclaredcontent = strDeclaredcontent.substring(0, 89);
				strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2, 2).toString();
			}
		xmlStr.append("<declaredCurrency>"+strDeclaredcurrency+"</declaredCurrency>");
		xmlStr.append("<DeclaredValue>"+strDeclaredvalue+"</DeclaredValue>");

		xmlStr.append("<SettlementMethod>"+"</SettlementMethod>");
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() != null &&
				IWaybillcodeBasicData.BAC_DHLSOAPFROMWEB_MASTER.equals(objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckGroupcode())) {
			String strCustomerewbcode = objFIAColumns.getCwcustomerewbcode();			
			xmlStr.append("<hawb_no>"+strCustomerewbcode+"</hawb_no>");
		}
		else{
			xmlStr.append("<hawb_no>"+objFIAColumns.getCwcustomerewbcode()+"</hawb_no>");
			
		}
		
		xmlStr.append("<caddress1>"+StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress1(), 35)+"</caddress1>");
		xmlStr.append("<caddress2>"+StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress2(), 35)+"</caddress2>");
		xmlStr.append("<caddress3>"+StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress3(), 35)+"</caddress3>");
		

		String strCountryHubcode = DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode());
		//String Dtcode = DistrictDemand.getDtcodeByDtename(strCountryHubcode, objFIAColumns.getHwConsigneecity());
		String strCityHubcode = DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode());
		
	
		//xmlStr.append("<Ccountrycode>"+"DE"+"</Ccountrycode>");
		//xmlStr.append("<CCountryName>"+"GERMANY"+"</CCountryName>");
		xmlStr.append("<Ccountrycode>"+strCountryHubcode+"</Ccountrycode>");
		xmlStr.append("<CCountryName>"+DistrictDemand.getCountryEnameByCity(objFIAColumns.getDtcode())+"</CCountryName>");
		xmlStr.append("<cityName>"+objFIAColumns.getHwConsigneecity()+"</cityName>");
		xmlStr.append("<ccity>"+strCityHubcode+"</ccity>");
		//xmlStr.append("<cityName>"+"ARZBACH"+"</cityName>");
		//xmlStr.append("<ccity>"+"FRA"+"</ccity>");
		xmlStr.append("<cname>"+StringUtility.splitMaxLength(objFIAColumns.getHwconsigneename(), 35)+"</cname>");
		xmlStr.append("<ctel>"+objFIAColumns.getHwconsigneetelephone().replace("-", "")+"</ctel>");
		xmlStr.append("<ccompany>"+ StringUtility.splitMaxLength(objFIAColumns.getHwconsigneecompany(), 35)+"</ccompany>");
		//xmlStr.append("<ccode>"+"56337"+"</ccode>");
		xmlStr.append("<ccode>"+objFIAColumns.getHwconsigneepostcode()+"</ccode>");
		xmlStr.append("<wt>"+objFIAColumns.getCwtransferchargeweight()+"</wt>"); //重量
		
		//货物信息
		// 货物类型，文件D，包裹P，ESI则为H
		String strProductcode = "P";
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("QQYX"))
			strProductcode = "F";
		if (objFIAColumns.getCtcode().equals("ADOX"))
			strProductcode = "D";
		if (objFIAColumns.getCtcode().equals("AWPX")) {
			String strWPXMapping = objTchnChannel.getChnWpxspsmappingname();
		if (!StringUtility.isNull(strWPXMapping) && strWPXMapping.equals("ESI"))
				strProductcode = "H";
		}
		
		CargoinfoCondition ccd=new CargoinfoCondition();
		ccd.setCwcode(objFIAColumns.getCwcode());
		CargoinfoQuery cq=new CargoinfoQuery();
		cq.setCondition(ccd);
		List<CargoinfoColumns> lis = cq.getResults();
		String name1=null;
		for(CargoinfoColumns ccf:lis){
			name1=ccf.getCiciename();
		}
		xmlStr.append("<name1>"+name1+"</name1>");
		xmlStr.append("<BaterrayName>"+"</BaterrayName>");
		xmlStr.append("<hawbType>"+strProductcode+"</hawbType>");
		xmlStr.append("<pcs>"+objFIAColumns.getCwpieces()+"</pcs>");
		
		//发件人信息	
		xmlStr.append("<sname>"+StringUtility.splitMaxLength(objFIAColumns.getHwshippername(), 35)+"</sname>");
		xmlStr.append("<stel>"+ objFIAColumns.getHwshippertelephone().replace("-", "")+"</stel>");
		xmlStr.append("<scompany>"+StringUtility.splitMaxLength(objFIAColumns.getHwshippercompany(), 35)+"</scompany>");
		xmlStr.append("<saddress1>"+ StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress1(), 35)+"</saddress1>");
		xmlStr.append("<saddress2>"+ StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress2(), 35)+"</saddress2>");
		xmlStr.append("<saddress3>"+ StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress3(), 35)+"</saddress3>");
		xmlStr.append("<scity>"+DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getHwDtcodeshipper())+"</scity>");
		xmlStr.append("<scityName>"+ DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper())+"</scityName>");
		xmlStr.append("<scode>"+objFIAColumns.getHwshipperpostcode()+"</scode>");
		xmlStr.append("<ShipPersonEmail>"+"</ShipPersonEmail>");
		
		xmlStr.append("<scountrycode>"+DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper())+"</scountrycode>");
		xmlStr.append("<scountryName>"+DistrictDemand.getCountryEnameByCity(objFIAColumns.getHwDtcodeshipper())+"</scountryName>");
		
		xmlStr.append("<AgentID>"+"SG-02"+"</AgentID>");
		String strTaxPaidMode = "R";
		SpecialtypeColumns objSpecialtypeColumns = SpecialtypeDemand.load(objFIAColumns.getCwcode(), "A0201");
		if (objSpecialtypeColumns != null && !StringUtility.isNull(objSpecialtypeColumns.getEstestname())) {
			strTaxPaidMode = "S";	
		} else {
			strTaxPaidMode=null;
		}
		xmlStr.append("<DutyPaymentType>"+strTaxPaidMode+"</DutyPaymentType>");
		xmlStr.append("<LabelImageFormat>PDF</LabelImageFormat>");
		
		xmlStr.append("<PiecesInfo>");	
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight()) ||
				new BigDecimal(objFIAColumns.getTransfervolumeweight()).equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		
		String strUnittransferweight = new BigDecimal(strWeight).divide(new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();	
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());		
		for (int i = 1; i <= iTransferPieces; i++) {
			xmlStr.append("<PiecesInfo>");
			xmlStr.append("<Weight>"+strUnittransferweight+"</Weight>");
			xmlStr.append("<Long>"+"11"+"</Long>");
			xmlStr.append("<Width>"+"11"+"</Width>");
			xmlStr.append("<Height>"+"11"+"</Height>");
			xmlStr.append("</PiecesInfo>");
		}
		
		if(iTransferPieces==1){
			xmlStr.append("<PiecesInfo>");
			xmlStr.append("<Weight>"+strUnittransferweight+"</Weight>");
			xmlStr.append("<Long>"+"11"+"</Long>");
			xmlStr.append("<Width>"+"11"+"</Width>");
			xmlStr.append("<Height>"+"11"+"</Height>");
			xmlStr.append("</PiecesInfo>");
			xmlStr.append("</PiecesInfo>");
		}
		
		xmlStr.append("</ws>");
		xmlStr.append("</GetImageLabelXMLNew>");
		xmlStr.append("</soap12:Body>");
		xmlStr.append("</soap12:Envelope>");
		
		
		return xmlStr.toString();
	}

}
