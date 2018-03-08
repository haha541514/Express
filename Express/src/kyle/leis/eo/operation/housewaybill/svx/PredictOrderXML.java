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
import kyle.leis.eo.operation.housewaybill.svx.parsexml.ShipmentContent;
import kyle.leis.eo.operation.housewaybill.svx.parsexml.ShipmentContents;
import kyle.leis.eo.operation.housewaybill.svx.parsexml.ShipmentPiece;
import kyle.leis.eo.operation.housewaybill.svx.parsexml.ShipmentPieces;
import kyle.leis.eo.operation.housewaybill.svx.parsexml.ShipmentRequests;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;

public class PredictOrderXML {
	private String m_strXMLOfData;
	
	public PredictOrderXML(String strXMLOfData) {
		m_strXMLOfData = strXMLOfData;
	}
	
	public static PredictwaybillColumns transformWFDC(WaybillforpredictColumns wfpd) throws Exception{
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
		
		pwbc.setPwbpwb_dutypaidsign(wfpd.getHwhw_dutypaidsign());
		if (!StringUtility.isNull(wfpd.getPkpk_code())) {
			ProductkindColumns pc = ProductkindDemand.queryBypkEname(wfpd.getPkpk_code());
			if (pc != null)
				pwbc.setPkpk_code(pc.getPkpkcode());
		}
		pwbc.setPwbpwb_customremark("api");
		return pwbc;
	}
	
	public static List transformListCIC(List<CargoinfoColumns> listCargo)throws Exception{
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
			// 重量
			if (StringUtility.isNull(cic.getCiciweight()))
				pcic.setPcipci_weight("0");
			else
				pcic.setPcipci_weight(cic.getCiciweight());
			
			listPCIC.add(pcic);
		}
		return listPCIC;
	}
	
	public PredictOrderColumnsEX parse() throws Exception {
		if (StringUtility.isNull(m_strXMLOfData))
			return null;
		// 解析
		ShipmentRequests objShipmentRequests = new ShipmentRequests(m_strXMLOfData);
		// 转换成预报数据
		PredictOrderColumnsEX objPOCEX = new PredictOrderColumnsEX();
		
		List<CargoinfoColumns> listCargoinfo = transferToCargoinfoList(objShipmentRequests);
		List<CorewaybillpiecesColumns> listPieces = transferToCorewaybillpieces(objShipmentRequests);
		WaybillforpredictColumns objWFPColumns = transferToWaybillforpredict(objShipmentRequests,
				listPieces);
		// 将attacheinfo保存到t_op_housewaybill 表的 hw_remark
		StringBuilder hwRemark = new StringBuilder();
		for (CargoinfoColumns cargoinfo : listCargoinfo) {
			if (!StringUtility.isNull(cargoinfo.getCiciattacheinfo())) {
				hwRemark.append(cargoinfo.getCiciattacheinfo() + ";");
			}
		}
		if (hwRemark.length() > 0) {
			hwRemark.deleteCharAt(hwRemark.length() - 1);
		}
		objWFPColumns.setHwhw_remark(hwRemark.toString());
		
		objPOCEX.setListCargoInfo(listCargoinfo);
		objPOCEX.setWaybillforpredict(objWFPColumns);
		objPOCEX.setListCorewaybillpieces(listPieces);
		
		return objPOCEX;
	}
	
	private WaybillforpredictColumns transferToWaybillforpredict(ShipmentRequests objShipmentRequests,
			List<CorewaybillpiecesColumns> listPieces) throws Exception {
		WaybillforpredictColumns objWFPColumns = new WaybillforpredictColumns();
		objWFPColumns.setCwcw_customerewbcode(objShipmentRequests.getShipper().getReferenceID().toUpperCase());
		objWFPColumns.setPkpk_code(objShipmentRequests.getShipper().getServer());
		
		objWFPColumns.setHwhw_shippercompany(objShipmentRequests.getShipper().getCompanyName().toUpperCase());
		objWFPColumns.setHwhw_shippername(objShipmentRequests.getShipper().getPersonName().toUpperCase());
		objWFPColumns.setHwhw_shipperaddress1(objShipmentRequests.getShipper().getAddress1().toUpperCase());
		objWFPColumns.setHwhw_shipperaddress2(StringUtility.replaceWhenNull(objShipmentRequests.getShipper().getAddress2(), "").toUpperCase());
		objWFPColumns.setHwhw_shipperaddress3(StringUtility.replaceWhenNull(objShipmentRequests.getShipper().getAddress3(), "").toUpperCase());
		objWFPColumns.setHwhw_shippertelephone(objShipmentRequests.getShipper().getPhoneNumber());
		objWFPColumns.setHwhw_shipperpostcode(objShipmentRequests.getShipper().getPostalCode().toUpperCase());
		objWFPColumns.setHwhw_shipperfax(objShipmentRequests.getShipper().getFaxNumber());
		
		objWFPColumns.setHwhw_consigneename(objShipmentRequests.getConsignee().getPersonName().toUpperCase());
		objWFPColumns.setHwhw_consigneecompany(objShipmentRequests.getConsignee().getCompanyName().toUpperCase());
		
		objWFPColumns.setHwhw_consigneecity(objShipmentRequests.getConsignee().getCity().toUpperCase());
		objWFPColumns.setHwhw_consigneeaddress1(objShipmentRequests.getConsignee().getAddress1().toUpperCase());
		objWFPColumns.setHwhw_consigneeaddress2(StringUtility.replaceWhenNull(objShipmentRequests.getConsignee().getAddress2(), "").toUpperCase());
		objWFPColumns.setHwhw_consigneeaddress3(StringUtility.replaceWhenNull(objShipmentRequests.getConsignee().getAddress3(), "").toUpperCase());
		
		Pattern pt = Pattern.compile("[']");
		objWFPColumns.setHwhw_consigneecity(pt.matcher(objWFPColumns.getHwhw_consigneecity()).replaceAll(""));
		objWFPColumns.setHwhw_consigneeaddress1(pt.matcher(objWFPColumns.getHwhw_consigneeaddress1()).replaceAll(""));
		objWFPColumns.setHwhw_consigneeaddress2(pt.matcher(objWFPColumns.getHwhw_consigneeaddress2()).replaceAll(""));
		objWFPColumns.setHwhw_consigneeaddress3(pt.matcher(objWFPColumns.getHwhw_consigneeaddress3()).replaceAll(""));
		
		objWFPColumns.setHwhw_consigneetelephone(objShipmentRequests.getConsignee().getPhoneNumber());
		objWFPColumns.setHwhw_consigneepostcode(objShipmentRequests.getConsignee().getPostalCode().toUpperCase().replace("-", ""));
		objWFPColumns.setHwhw_consigneefax(objShipmentRequests.getConsignee().getFaxNumber());
		objWFPColumns.setCwdt_code_signin(objShipmentRequests.getConsignee().getCountryCode().toUpperCase());
		objWFPColumns.setHwpat_code(objShipmentRequests.getConsignee().getPackageType());
		objWFPColumns.setHwdt_code(objShipmentRequests.getConsignee().getDeliveryType());
		objWFPColumns.setHwcgk_code(objShipmentRequests.getConsignee().getCargoKind());
		objWFPColumns.setHwbk_code(objShipmentRequests.getConsignee().getBatteryKind());
		objWFPColumns.setHwhw_dutypaidsign(objShipmentRequests.getConsignee().getDutyPaid());
		
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
	
	private List<CargoinfoColumns> transferToCargoinfoList(ShipmentRequests objShipmentRequests) {
		List<CargoinfoColumns> listCargoinfo = new ArrayList<CargoinfoColumns>();
		
		ShipmentContents objShipmentContents = objShipmentRequests.getShipmentContents();
		if (objShipmentContents.getCount() == 0)
			return listCargoinfo;
		for (int i = 0; i < objShipmentContents.getCount(); i++) {
			ShipmentContent objShipmentContent = objShipmentContents.getConfig(i);
			if (objShipmentContent.getDescription().equals("Doc")) {
				listCargoinfo = new ArrayList<CargoinfoColumns>();
				break;
			}
			CargoinfoColumns objCargoinfoColumns = new CargoinfoColumns();
			String strCargoname = objShipmentContent.getDescription();
			String strCargonamecn = objShipmentContent.getDescriptionCN();
			// 品名为英文|中文格式
			String[] astr = new String[2];
			if (strCargoname.indexOf("|") > 0) {
				astr[0] = strCargoname.substring(0, strCargoname.indexOf("|"));
				astr[1] = strCargoname.substring(strCargoname.indexOf("|") + 1);
			} else {
				astr[0] = strCargoname;
				astr[1] = strCargoname;
			}			
			objCargoinfoColumns.setCiciename(astr[0]);
			objCargoinfoColumns.setCiciname(astr[1]);
			if (!StringUtility.isNull(strCargonamecn))
				objCargoinfoColumns.setCiciname(strCargonamecn);
			
			objCargoinfoColumns.setCicipieces(Integer.parseInt(objShipmentContent.getAmount()));
			objCargoinfoColumns.setCicitotalprice(new BigDecimal(objShipmentContent.getTotalPrice()));
			objCargoinfoColumns.setCiciunitprice(new BigDecimal(objShipmentContent.getUnitPrice()));
			objCargoinfoColumns.setCkckcode(objShipmentContent.getCurrency());
			objCargoinfoColumns.setCiciattacheinfo(StringUtility.replaceWhenNull(objShipmentContent.getAttacheinfo(), "."));
			objCargoinfoColumns.setCiciremark(objShipmentContent.getRemark());
			if (!StringUtility.isNull(objShipmentContent.getWeight())) {
				objCargoinfoColumns.setCiciweight(new BigDecimal(objShipmentContent.getWeight()));
			}
			listCargoinfo.add(objCargoinfoColumns);
		}
		
		return listCargoinfo;
	}	
	
	private List<CorewaybillpiecesColumns> transferToCorewaybillpieces(ShipmentRequests objShipmentRequests) {
		List<CorewaybillpiecesColumns> listPieces = new ArrayList<CorewaybillpiecesColumns>();
		
		ShipmentPieces objShipmentPieces = objShipmentRequests.getShipmentPieces();
		if (objShipmentPieces.getCount() == 0)
			return listPieces;
		for (int i = 0; i < objShipmentPieces.getCount(); i++) {
			ShipmentPiece objShipmentPiece = objShipmentPieces.getConfig(i);
			CorewaybillpiecesColumns objCorewaybillpiecesColumns = new CorewaybillpiecesColumns();
			
			objCorewaybillpiecesColumns.setCpcpgrossweight(new BigDecimal(objShipmentPiece.getWeight()));
			objCorewaybillpiecesColumns.setCpcplength(new BigDecimal(objShipmentPiece.getLength()));
			objCorewaybillpiecesColumns.setCpcpheight(new BigDecimal(objShipmentPiece.getHeight()));
			objCorewaybillpiecesColumns.setCpcpwidth(new BigDecimal(objShipmentPiece.getWidth()));
			
			listPieces.add(objCorewaybillpiecesColumns);
		}
		
		return listPieces;
	}		
}
