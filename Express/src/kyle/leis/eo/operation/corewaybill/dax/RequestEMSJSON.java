package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RequestEMSJSON extends RequestXML {

	@SuppressWarnings("unchecked")
	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo, 
			List listPieces, 
			PromptUtilityCollection objPUCollection)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("reference", objFIAColumns.getCwcustomerewbcode());//客户单号
		//发件人信息
		jsonObject.put("sender_name", objFIAColumns.getHwshippername());
		jsonObject.put("sender_email", "");
		jsonObject.put("sender_company_name", objFIAColumns.getHwshippercompany());
		jsonObject.put("sender_address_line_1", objFIAColumns.getHwshipperaddress1());
		jsonObject.put("sender_address_line_2", objFIAColumns.getHwshipperaddress2());
		jsonObject.put("sender_address_line_3", objFIAColumns.getHwshipperaddress3());
		jsonObject.put("sender_city", DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		jsonObject.put("sender_postcode", objFIAColumns.getHwshipperpostcode());
		jsonObject.put("sender_province", "Guangdong");
		jsonObject.put("sender_country_code", "CHN");
		//收件人信息
		jsonObject.put("validate_recipient_address", false);
		jsonObject.put("recipient_name", objFIAColumns.getHwconsigneename());
		jsonObject.put("recipient_email", "");
		jsonObject.put("recipient_phone_number", objFIAColumns.getHwconsigneetelephone());
		jsonObject.put("recipient_company_name", objFIAColumns.getHwconsigneecompany());
		// 截取地址，地址不能超过30个字符
		String[] astrAddress = new String[4];
		String strAddress = objFIAColumns.getHwconsigneeaddress1() + " " +
		objFIAColumns.getHwconsigneeaddress2() + " " +
		objFIAColumns.getHwconsigneeaddress3();	
		for (int i = 0; i < 4; i++) {
			astrAddress[i] = ".";
			if (strAddress.length() > i * 29) {
				if (strAddress.length() > (i + 1) * 29)
					astrAddress[i] = strAddress.substring(i * 29, (i + 1) * 29);
				else
					astrAddress[i] = strAddress.substring(i * 29);
			}
		}
		jsonObject.put("recipient_address_line_1", astrAddress[0]);
		jsonObject.put("recipient_address_line_2", astrAddress[1]);
		jsonObject.put("recipient_address_line_3", astrAddress[2]);
		jsonObject.put("recipient_address_line_4", astrAddress[3]);
		jsonObject.put("recipient_suburb", objFIAColumns.getHwConsigneecity());
		jsonObject.put("recipient_postcode", objFIAColumns.getHwconsigneepostcode());
		jsonObject.put("recipient_state", DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
				DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()), 
				DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
				objFIAColumns.getHwconsigneepostcode()));	//发件人所在州
		jsonObject.put("recipient_country_code", DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
		jsonObject.put("delivery_instructions", "");//交货说明
		//件重量
		JSONArray jsonArray = new JSONArray();
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight()) ||
				new BigDecimal(objFIAColumns.getTransfervolumeweight()).equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		BigDecimal strUnittransferweight = new BigDecimal(strWeight).divide(new BigDecimal(objFIAColumns.getCwpieces()), 2, 4);
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		for (int i = 1; i <= iTransferPieces; i++) {
			JSONObject weightJsonObject = new JSONObject();
			weightJsonObject.put("weight", strUnittransferweight);
			jsonArray.add(weightJsonObject);
		}
		jsonObject.put("parcels", jsonArray);
		//物品信息
		jsonObject.put("dangerous_goods", false);
		// 发票信息
		BigDecimal strDeclaredvalue = new BigDecimal("0");
		String strDeclaredcurrency = "USD";
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				strDeclaredcurrency = objCIC.getCkckcode();
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + ",";
			}
			if (!StringUtility.isNull(strDeclaredcontent) && 
					strDeclaredcontent.length() > 90)
				strDeclaredcontent = strDeclaredcontent.substring(0, 89);
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2, 2);
		}
		jsonObject.put("goods_description", strDeclaredcontent);
		jsonObject.put("goods_value", strDeclaredvalue);
		jsonObject.put("goods_currency", strDeclaredcurrency);
		jsonObject.put("export_reason", "");
		jsonObject.put("courier_service", "TR");
		jsonObject.put("print_own_label", "Y");
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
}
