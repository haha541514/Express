package kyle.leis.eo.operation.housewaybill.svx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PredictOrderJsonNew {
private String m_PredictOrderJson;
	
	public PredictOrderJsonNew(String strPredictOrderJson) {
		m_PredictOrderJson = strPredictOrderJson;
	}
	
	public PredictOrderColumnsEX getPredictOrderEX(){
		if (StringUtility.isNull(m_PredictOrderJson))
			return null;
		
		PredictOrderColumnsEX objPOCEX = new PredictOrderColumnsEX();
		
		List<CargoinfoColumns> listCargoinfo = getPredictcargoinfoColumns();
		List<CorewaybillpiecesColumns> listPieces = getCorewaybillpiecesColumns();
		WaybillforpredictColumns objWFPColumns = getPredictwaybillColumns(listPieces);
		objPOCEX.setListCargoInfo(listCargoinfo);
		objPOCEX.setWaybillforpredict(objWFPColumns);
		objPOCEX.setListCorewaybillpieces(listPieces);
		
		return objPOCEX;
	}
	
	public PredictwaybillColumns transformWFDC(WaybillforpredictColumns wfpd) throws Exception{
		PredictwaybillColumns pwbc = new PredictwaybillColumns();
		pwbc.setPwbpwb_code(wfpd.getCwcw_code());
//		pwbc.setPwbcw_code(wfpd.getCwcw_code());
		pwbc.setPwbspwbs_code("CTS");
		pwbc.setPwbpwb_orderid(wfpd.getCwcw_customerewbcode());
		pwbc.setChnchn_code(wfpd.getChnchn_code());
		pwbc.setPwbpwb_consigneename(wfpd.getHwhw_consigneename());
		pwbc.setPwbpwb_consigneetel(wfpd.getHwhw_consigneetelephone());
		pwbc.setPwbpwb_consigneeaddress1(wfpd.getHwhw_consigneeaddress1());
		pwbc.setPwbpwb_consigneeaddress2(wfpd.getHwhw_consigneeaddress2());
		pwbc.setPwbpwb_consigneecity(wfpd.getHwhw_consigneecity());
		pwbc.setPwbpwb_consigneestate(wfpd.getDtdt_statecode());
		pwbc.setPwbpwb_consigneepostcode(wfpd.getHwhw_consigneepostcode());
		pwbc.setPwbpwb_transactionid(wfpd.getHwhw_transactionid());
		pwbc.setPwbpwb_serverewbcode(wfpd.getCwcw_serverewbcode());
		pwbc.setPwbpwb_chargeweight(wfpd.getCwcw_chargeweight());
		pwbc.setPwbpwb_createdate(wfpd.getPwpwb_createdate());
		pwbc.setPwbpwb_declaredate(wfpd.getPwpwb_declaredate());
		pwbc.setPwbpwb_printdate(wfpd.getPwpwb_printdate());
		pwbc.setPwbpwb_buyerid(wfpd.getHwhw_buyerid());
		pwbc.setPkpk_code(wfpd.getPkpk_code());
		if (!StringUtility.isNull(wfpd.getPkpk_code())) {
			ProductkindColumns pc = ProductkindDemand.queryBypkEname(wfpd.getPkpk_code());
			if (pc != null)
				pwbc.setPkpk_code(pc.getPkpkcode());
		}
		pwbc.setPwbpwb_customremark("api");
		return pwbc;
	}
	
	public List transformListCIC(List<CargoinfoColumns> listCargo)throws Exception{
		List<PredictcargoinfoColumns> listPCIC = new ArrayList<PredictcargoinfoColumns>();
		for(int i=0;i < listCargo.size();i++){
			PredictcargoinfoColumns pcic =new PredictcargoinfoColumns();
			CargoinfoColumns cic = listCargo.get(i);
			pcic.setPcipwb_code(cic.getCicomp_idcwcode());
			pcic.setPcick_code(cic.getCkckcode());
			pcic.setPcipci_name(cic.getCiciname());
			pcic.setPcipci_ename(cic.getCiciename());
			pcic.setPcipci_pieces(cic.getCicipieces());
			pcic.setPcipci_weight(cic.getCiciweight());
			pcic.setPcipci_unitprice(cic.getCiciunitprice());
			pcic.setPcipci_totalprice(cic.getCicitotalprice());
			pcic.setPcipci_hscode(cic.getCicihscode());
			pcic.setPcipci_attacheinfo(cic.getCiciattacheinfo());
			pcic.setPcipci_remark(cic.getCiciremark());
			pcic.setPcipci_weight("0");
			listPCIC.add(pcic);
		}
		return listPCIC;
	}
	
	private WaybillforpredictColumns getPredictwaybillColumns(List<CorewaybillpiecesColumns> listPieces){
		if (StringUtility.isNull(m_PredictOrderJson))
			return null;
		
		WaybillforpredictColumns objWFPColumns = new WaybillforpredictColumns();
		
		JSONObject result =  JSONObject.fromObject(m_PredictOrderJson);
		JSONObject Info =  JSONObject.fromObject(result.get("Info"));
		JSONObject consigneeOrders =  JSONObject.fromObject(result.get("Consignee"));
		JSONObject shipperOrders =  JSONObject.fromObject(result.get("Shipper"));
		//收件人信息
		String personName =consigneeOrders.getString("PersonName");
		String companyName =consigneeOrders.getString("CompanyName");
		String address1 =consigneeOrders.getString("Address1");
		String address2 =consigneeOrders.getString("Address2");
		String address3 =consigneeOrders.getString("Address3");
		String city =consigneeOrders.getString("City");
		String postalCode =consigneeOrders.getString("PostalCode");
		String countryCode =consigneeOrders.getString("CountryCode");
		String phoneNumber =consigneeOrders.getString("PhoneNumber");
		String faxNumber =consigneeOrders.getString("FaxNumber");
		//发件人信息
		String referenceID =shipperOrders.getString("ReferenceID");
		String server =shipperOrders.getString("Server");
		String shipperPersonName =shipperOrders.getString("PersonName");
		String shipperCompanyName =shipperOrders.getString("CompanyName");
		String shipperAddress1 =shipperOrders.getString("Address1");
		String shipperAddress2 =shipperOrders.getString("Address2");
		String shipperAddress3 =shipperOrders.getString("Address3");
		String shipperCity =shipperOrders.getString("City");
		String shipperPostalCode =shipperOrders.getString("PostalCode");
		String shipperCountryCode =shipperOrders.getString("CountryCode");
		String shipperPhoneNumber =shipperOrders.getString("PhoneNumber");
		String shipperFaxNumber =shipperOrders.getString("FaxNumber");
		
		//info
		
		String OrderID =Info.getString("OrderID");
		if(!StringUtility.isNull(OrderID)){
			objWFPColumns.setCwcw_code(OrderID);
		}
		String InsuranceAmount =Info.getString("InsuranceAmount");
		String InvoiceChoices =Info.getString("InvoiceChoices");	
		String InsuranceChoices =Info.getString("InsuranceChoices");
		String InsuranceBeneficiary =Info.getString("InsuranceBeneficiary");
		String DCustoms =Info.getString("D_Customs");
		if(InsuranceChoices.equals(1)){
			objWFPColumns.setHwhw_insurancevalue("0");
		}else{
		if(StringUtility.isNull(InsuranceAmount)){
			objWFPColumns.setHwhw_insurancevalue("0");
		}else{
			objWFPColumns.setHwhw_insurancevalue(InsuranceAmount);
		}
		}
		objWFPColumns.setIfyib_code(InsuranceBeneficiary);
		objWFPColumns.setItipt_code(InvoiceChoices);
		objWFPColumns.setHwhw_dcustomssign(DCustoms);
		
		objWFPColumns.setCwcw_customerewbcode(referenceID.toUpperCase());
		objWFPColumns.setPkpk_code(server);
		
		objWFPColumns.setHwhw_shippercompany(shipperCompanyName.toUpperCase());
		objWFPColumns.setHwhw_shippername(shipperPersonName.toUpperCase());
	
		objWFPColumns.setHwhw_shipperaddress1(shipperAddress1.toUpperCase());
		objWFPColumns.setHwhw_shipperaddress2(StringUtility.replaceWhenNull(shipperAddress2, "").toUpperCase());
		objWFPColumns.setHwhw_shipperaddress3(StringUtility.replaceWhenNull(shipperAddress3, "").toUpperCase());
		objWFPColumns.setHwhw_shippertelephone(shipperPhoneNumber);
		objWFPColumns.setHwhw_shipperpostcode(shipperPostalCode.toUpperCase());
		objWFPColumns.setHwhw_shipperfax(shipperFaxNumber);
		
		objWFPColumns.setHwhw_consigneename(personName.toUpperCase());
		objWFPColumns.setHwhw_consigneecompany(companyName.toUpperCase());
		
		objWFPColumns.setHwhw_consigneecity(city.toUpperCase());
		objWFPColumns.setHwhw_consigneeaddress1(address1.toUpperCase());
		objWFPColumns.setHwhw_consigneeaddress2(StringUtility.replaceWhenNull(address2, "").toUpperCase());
		objWFPColumns.setHwhw_consigneeaddress3(StringUtility.replaceWhenNull(address3, "").toUpperCase());
		
		Pattern pt = Pattern.compile("[']");
		objWFPColumns.setHwhw_consigneecity(pt.matcher(objWFPColumns.getHwhw_consigneecity()).replaceAll(""));
		objWFPColumns.setHwhw_consigneeaddress1(pt.matcher(objWFPColumns.getHwhw_consigneeaddress1()).replaceAll(""));
		objWFPColumns.setHwhw_consigneeaddress2(pt.matcher(objWFPColumns.getHwhw_consigneeaddress2()).replaceAll(""));
		objWFPColumns.setHwhw_consigneeaddress3(pt.matcher(objWFPColumns.getHwhw_consigneeaddress3()).replaceAll(""));
		
		objWFPColumns.setHwhw_consigneetelephone(phoneNumber);
		objWFPColumns.setHwhw_consigneepostcode(postalCode.toUpperCase().replace("-", ""));
		objWFPColumns.setHwhw_consigneefax(faxNumber);
		objWFPColumns.setCwdt_code_signin(countryCode.toUpperCase());
		
		BigDecimal objGrossweight = new BigDecimal("0");
		for (int i = 0; i < listPieces.size(); i++) {
			CorewaybillpiecesColumns objCBPColumns = listPieces.get(i);
			objGrossweight = objGrossweight.add(new BigDecimal(objCBPColumns.getCpcpgrossweight()));
		}
		objWFPColumns.setCwcw_chargeweight(objGrossweight.toString());
		objWFPColumns.setCwcw_customerchargeweight(objGrossweight.toString());
		objWFPColumns.setCwcw_pieces(String.valueOf(listPieces.size()));
		
		return objWFPColumns;
	}
	
	private List<CorewaybillpiecesColumns> getCorewaybillpiecesColumns() {
		List<CorewaybillpiecesColumns> listPieces = new ArrayList<CorewaybillpiecesColumns>();
		JSONObject result =  JSONObject.fromObject(m_PredictOrderJson);
		JSONObject shipmentDetails =  JSONObject.fromObject(result.get("ShipmentDetails"));
	
		JSONArray pieceList = JSONArray.fromObject(shipmentDetails.get("Piece"));
		for (int i = 0; i < pieceList.size(); i++) {
			JSONObject piece =  pieceList.getJSONObject(i);
			String weight =piece.getString("Weight");
			String length =piece.getString("Length");
			String height =piece.getString("Height");
			String width =piece.getString("Width");
			CorewaybillpiecesColumns objCorewaybillpiecesColumns = new CorewaybillpiecesColumns();
			objCorewaybillpiecesColumns.setCpcpgrossweight(new BigDecimal(weight));
			objCorewaybillpiecesColumns.setCpcplength(new BigDecimal(length));
			objCorewaybillpiecesColumns.setCpcpheight(new BigDecimal(height));
			objCorewaybillpiecesColumns.setCpcpwidth(new BigDecimal(width));
			listPieces.add(objCorewaybillpiecesColumns);
		}
		return listPieces;
	}		
	
	private List<CargoinfoColumns> getPredictcargoinfoColumns(){
		if (StringUtility.isNull(m_PredictOrderJson))
			return null;
		
		List<CargoinfoColumns> listCargoinfo = new ArrayList<CargoinfoColumns>();
		JSONObject result =  JSONObject.fromObject(m_PredictOrderJson);
		 
		JSONObject contents =  JSONObject.fromObject(result.get("Contents"));
		JSONArray contentList = JSONArray.fromObject(contents.get("Content"));
			for (int i = 0; i < contentList.size(); i++) {
				CargoinfoColumns objCargoinfoColumns = new CargoinfoColumns();
				JSONObject content =  contentList.getJSONObject(i);
				String amount =content.getString("Amount");
				String unitPrice =content.getString("UnitPrice");
				String totalPrice =content.getString("TotalPrice");
				String currency =content.getString("Currency");
				String description =content.getString("Description");
				if (description.equals("Doc")) {
					break;
				}
				objCargoinfoColumns.setCiciename(description);
				objCargoinfoColumns.setCiciname(description);
				objCargoinfoColumns.setCicipieces(Integer.parseInt(amount));
				objCargoinfoColumns.setCicitotalprice(new BigDecimal(totalPrice));
				objCargoinfoColumns.setCiciunitprice(new BigDecimal(unitPrice));
				objCargoinfoColumns.setCkckcode(currency);
				objCargoinfoColumns.setCiciattacheinfo(".");
				
				listCargoinfo.add(objCargoinfoColumns);
			}
		return listCargoinfo;
	}
}
