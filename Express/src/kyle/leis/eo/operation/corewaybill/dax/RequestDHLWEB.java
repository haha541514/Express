package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;

public class RequestDHLWEB extends RequestXML {

	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns, 
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection) throws Exception {
		StringBuffer sb = new StringBuffer();
		
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		String strReference = objFIAColumns.getCwcustomerewbcode();
		String strSystemPE = SystempropertyDemand.getEnterprise();
		String cienameMark = "";
		if (!StringUtility.isNull(strSystemPE) && 
				(strSystemPE.startsWith("SBD") || strSystemPE.startsWith("SLY")) &&
				objTchnChannel.getTdiServerstructuregroup() != null && 
				"DHL_NC".equals(objTchnChannel.getTdiServerstructuregroup().getSsgCode())) {
			if (strReference.length() >= 6)
				strReference = strReference.substring(strReference.length() - 6);
		}
		if (objTchnChannel.getTdiServerstructuregroup() != null && 
				("DHL_HKMY").equals(objTchnChannel.getTdiServerstructuregroup().getSsgCode())) {
			if (strReference.length() >= 6)
				strReference = strReference.substring(strReference.length() - 6, strReference.length());
			strReference = "SV11-Y-" + strReference;
		}		
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("WC") ) {
			strReference = "";
		}
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("SLY")){
			cienameMark = "  sample";
		}
		sb.append("from_where=not_dhl&");
		sb.append("paper_ty=L&");
		sb.append("origin=HKG&");
		sb.append("dhl_acc_no=" + objTchnChannel.getChnMasteraccount() + "&");
		sb.append("send_name=" + StringUtility.splitMaxLength(objFIAColumns.getHwshippername(), 35) + "&");
		sb.append("reference=" + strReference + "&");
		sb.append("send_company=" + StringUtility.splitMaxLength(objFIAColumns.getHwshippercompany(), 35) + "&");
		sb.append("send_address1=" + StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress1(), 35) + "&");
		sb.append("send_address2=" + StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress2(), 35) + "&");
		sb.append("send_address3=" + StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress3(), 35) + "&");
		sb.append("send_pc=&");
		sb.append("send_media=tel&");
		sb.append("send_tel=" + objFIAColumns.getHwshippertelephone().replace("-", "") + "&");
		sb.append("consign_company=" + StringUtility.splitMaxLength(objFIAColumns.getHwconsigneecompany(), 35) + "&");
		sb.append("consign_address1=" + StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress1(), 35) + "&");
		sb.append("consign_address2=" + StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress2(), 35) + "&");
		sb.append("consign_address3=" + StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress3(), 35) + "&");
		sb.append("consign_city=" + StringUtility.splitMaxLength(objFIAColumns.getHwConsigneecity(), 35) + "&");
		
		String strCountryHubcode = DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode());
		String strPostcode = objFIAColumns.getHwconsigneepostcode(); //autoRebuildDHLPostcode(strCountryHubcode, objFIAColumns.getHwconsigneepostcode());
		if (!StringUtility.isNull(strPostcode) && strPostcode.startsWith("E_"))
			return strPostcode;		
		// 巴西不加sample
		if ("BR".equals(strCountryHubcode))
			cienameMark = "";
		
		sb.append("consign_pc=" + StringUtility.replaceWhenNull(strPostcode,"") + "&");
		sb.append("consign_country=" + strCountryHubcode + "&");
		sb.append("consign_person=" + StringUtility.splitMaxLength(objFIAColumns.getHwconsigneename(), 35) + "&");
		sb.append("consign_media=tel&");
		sb.append("consign_tel=" + objFIAColumns.getHwconsigneetelephone().replace("-", "") + "&");
		sb.append("charge_to_account=" + objTchnChannel.getChnPaymentaccount() + "&");
		sb.append("charge_to=RECEIVER&");
		sb.append("charge_by=&");
		sb.append("ship_insurance=&");
		sb.append("ship_insur_value=&");
		sb.append("browser=Netscape&");
		sb.append("export=PERMANENT&");
		// 发票信息
		String strDeclaredvalue = "0.00";
		String strDeclaredcurrency = "USD";
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				strDeclaredcurrency = objCIC.getCkckcode();
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + cienameMark + ",";
				if (!StringUtility.isNull(strDeclaredcurrency) &&
						!strDeclaredcurrency.equals("USD") &&
						!strDeclaredcurrency.equals("HKD") &&
						!strDeclaredcurrency.equals("EUR") &&
						!strDeclaredcurrency.equals("JPY")) {
					return "E_001_申报币种只能是USD、HKD、EUR、JPY中的一种";
				}
			}
			/*
			if (!StringUtility.isNull(strDeclaredcontent) && 
					strDeclaredcontent.length() > 90)
				strDeclaredcontent = strDeclaredcontent.substring(0, 89);
			*/
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2, 2).toString();
			/** 当申报价值总额小于USD 128时，才在品名后面加Sample 字样，等于或大于USD128的，不加。
			 * 目前不考虑币种，默认为USD**/
			if (objDeclaredvalue.compareTo(new BigDecimal("128")) >= 0 && !"BR".equals(strCountryHubcode)) {
				strDeclaredcontent.replace("  sample,", ",");
			}
		}
		// 文件包裹
		if (objFIAColumns.getCtcode().equals("ADOX")) {
			sb.append("ship_product=DOCUMENT&");
			sb.append("declare_value=&");
		}
		else {
			sb.append("ship_product=DUTIABLE PARCEL&");
			sb.append("declare_value=" + strDeclaredvalue + "&");
		}
		
		sb.append("ship_product_dtl=EXPRESS WORLDWIDE&");		
		sb.append("contents_desc=" + strDeclaredcontent + "&");
		//sb.append("declare_value=" + strDeclaredvalue + "&");
		sb.append("declare_currency=" + strDeclaredcurrency + "&");
		
		sb.append("commodity_code=&");
		String dest_duties = "Receiver";
		List listSpecialtype = SpecialtypeDemand.load(objFIAColumns.getCwcode());
		if (listSpecialtype != null && listSpecialtype.size() > 0) {
			for (Object object : listSpecialtype) {
				SpecialtypeColumns specialtypeColumns = (SpecialtypeColumns) object;
				if ("A0201".equals(specialtypeColumns.getWbstcomp_idestcode())) {
					dest_duties = "Sender";
					break;
				}
			}
		}
		sb.append("dest_duties=" + dest_duties + "&");
		sb.append("dest_other=&");
		
		sb.append("ship_qty=" + objFIAColumns.getCwpieces() + "&");
		if (new BigDecimal(objFIAColumns.getCwtransferchargeweight()).compareTo(new BigDecimal("0.5")) <= 0) {
			sb.append("ship_weight=0.5&");
		} else {
			sb.append("ship_weight=" + 
					new BigDecimal(objFIAColumns.getCwtransferchargeweight()).divide(new BigDecimal("1"), 0, 1).toString() + 
					"&");
		}
		sb.append("length1=&");
		sb.append("width1=&");
		sb.append("height1=&");
		sb.append("length2=&");
		sb.append("width2=&");
		sb.append("height2=&");		
		sb.append("length3=&");
		sb.append("width3=&");
		sb.append("height3=&");				
		sb.append("length4=&");
		sb.append("width4=&");
		sb.append("height4=&");	
		sb.append("email=&");
		sb.append("agreetc=Y&");
		sb.append("sub_grp_id=SANI");
		
		return sb.toString();
	}

}
