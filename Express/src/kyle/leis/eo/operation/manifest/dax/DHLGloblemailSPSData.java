package kyle.leis.eo.operation.manifest.dax;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.da.LabeldataColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.da.ChanneladdressColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class DHLGloblemailSPSData extends ADGMSPSData {
	public String build(BatchwaybillColumns objBWColumns, ChannelColumns objChannelColumns) throws Exception {
		StringBuffer sbSPSText = new StringBuffer();
		ChanneladdressColumns objCAColumns = ChannelDemand.loadChanneladdress(objBWColumns.getChnchncode());
		// 获得运单信息
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		objHWBCondition.setDbwlabelcode(objBWColumns.getBwbwlabelcode());
		List listResults = HousewaybillDemand.query(objHWBCondition);
		// 开始导出数据
		Date objAdddate = DateFormatUtility.getStandardDate(objBWColumns.getBwadddate());
		String strManifestID = objChannelColumns.getChnchnpaymentaccount() +
		DateFormatUtility.getDateString(objAdddate, "yyyyddMMHHmm");
		// RcdHeader部分
		sbSPSText.append("RcdHeader|");
		sbSPSText.append("1.0|");
		sbSPSText.append(strManifestID + "|");
		sbSPSText.append(DateFormatUtility.getDateString(objAdddate, "yyyy-MM-dd") + "|");
		sbSPSText.append(DateFormatUtility.getDateString(objAdddate, "HH:mm:ss") + "|");		
		sbSPSText.append(objChannelColumns.getEraccount() + "|");
		sbSPSText.append(objChannelColumns.getChnchnpaymentaccount() + "|");
		sbSPSText.append("|");
		// 票数
		sbSPSText.append(listResults.size() + "|");
		sbSPSText.append("\r\n");
		//RcdPickupHeader部分
		sbSPSText.append("RcdPickupHeader|");
		sbSPSText.append(DateFormatUtility.getDateString(objAdddate, "yyyy-MM-dd") + "|");
		sbSPSText.append(DateFormatUtility.getDateString(objAdddate, "HH:mm") + "|");	
		sbSPSText.append("IEEC|Mr. Sherman Chen|72 Chippenham Dr.||Monroe|NY|14526|5853778974||");
		sbSPSText.append("\r\n");
		// RcdShipDetail部分
		BigDecimal objSumChargeweight = new BigDecimal("0");
		for (int i = 0; i < listResults.size(); i++) {
			HousewaybillColumns objHousewaybillColumns = (HousewaybillColumns)listResults.get(i);
			sbSPSText.append("RcdShipDetail|");
			sbSPSText.append(objChannelColumns.getChnchnpaymentaccount() + "|");
			sbSPSText.append(objCAColumns.getChnachnaprocessingaddress1() + "|");
			// 16盎司以上为83，以下为82
			String strChargeweight = objHousewaybillColumns.getCwcwserverchargeweight();
			BigDecimal objStandardRate = new BigDecimal("35.2739619");
			BigDecimal objChargeweight = objStandardRate.multiply(new BigDecimal(strChargeweight)).divide(new BigDecimal("1"), 2, 4);
			objSumChargeweight = objSumChargeweight.add(objChargeweight);
			String strProduct = "83";
			if (objChargeweight.compareTo(new BigDecimal("16")) > 0) {
				sbSPSText.append(strProduct + "|");
				sbSPSText.append("7|");
			}
			else {
				strProduct = "82";
				sbSPSText.append(strProduct + "|");
				sbSPSText.append("3|");
			}
			sbSPSText.append("|||");
			sbSPSText.append(objHousewaybillColumns.getCwcwcustomerewbcode() + "|");
			sbSPSText.append("420" + objHousewaybillColumns.getHwhwconsigneepostcode() + objHousewaybillColumns.getCwcwserverewbcode() + "|");
			sbSPSText.append("C01|");
			sbSPSText.append("|");
			sbSPSText.append(objChargeweight.toString() + "|");
			sbSPSText.append("OZ|");
			// 查询产品邮编对应的信息
			LabeldataColumns objLabeldataColumns = HousewaybillDemand.loadDHLGlobleMailPTST(objHousewaybillColumns.getHwhwconsigneepostcode(), 
					strProduct);
			if (objLabeldataColumns == null) {
				sbSPSText.append("无产品邮编对应的信息|");
			} else {
				sbSPSText.append(objLabeldataColumns.getPle2() + "|");
				sbSPSText.append(objLabeldataColumns.getPle1() + "|");
				sbSPSText.append(objLabeldataColumns.getPle4() + "|");
				sbSPSText.append(objLabeldataColumns.getPle5() + "|");
				sbSPSText.append(objLabeldataColumns.getPle6() + "|");
			}
			sbSPSText.append("||||CM|||");
			sbSPSText.append("\r\n");
			// 地址信息
			ForinputallColumns objFIAColumns = HousewaybillDemand.load(objHousewaybillColumns.getHwcwcode());
			sbSPSText.append("RcdConsigneeAddress|");
			sbSPSText.append(objFIAColumns.getHwconsigneecompany() + "|");
			
			if (StringUtility.isNull(objFIAColumns.getHwconsigneenameex())) {
				sbSPSText.append(objFIAColumns.getHwconsigneename() +  "|");
			} else {
				sbSPSText.append(StringUtility.buildFromByte(objFIAColumns.getHwconsigneenameex(), "utf-8") +  "|");
			}			
			//sbSPSText.append(StringUtility.buildFromByte(objFIAColumns.getHwconsigneenameex(), "utf-8") + "|");
			
			//StringUtility.buildFromByte(objFIAColumns.getHwconsigneeaddressex(), "utf-8");
			String[] astrAddress = null;
			if (StringUtility.isNull(objFIAColumns.getHwconsigneeaddressex())) {
				astrAddress = parseAddress(objFIAColumns.getHwconsigneeaddress1() + 
				objFIAColumns.getHwconsigneeaddress2() + objFIAColumns.getHwconsigneeaddress3(), 3, 39);
			} else {
				astrAddress = parseAddress(StringUtility.buildFromByte(objFIAColumns.getHwconsigneeaddressex(), "utf-8"));
			}			
			
			sbSPSText.append(astrAddress[0].replace(".", " ") + "|");
			sbSPSText.append(astrAddress[1].replace(".", " ") + "|");
			sbSPSText.append(objFIAColumns.getHwConsigneecity() + "|");
			sbSPSText.append(DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
					"", 
					DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
					objFIAColumns.getHwconsigneepostcode()) + "|");
			sbSPSText.append(objFIAColumns.getHwconsigneepostcode() + "|");
			sbSPSText.append(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()) + "|");
			String telephone = StringUtility.replaceWhenNull(objFIAColumns.getHwconsigneetelephone(), "");
			sbSPSText.append(telephone.replace(".", " ") + "|");
			sbSPSText.append("|");
			sbSPSText.append("\r\n");
		}
		// RcdFooter|部分
		sbSPSText.append("RcdFooter|");
		sbSPSText.append(objSumChargeweight.toString() + "|");
		sbSPSText.append("OZ|1|");
		
		buildFile(objBWColumns.getBwbwlabelcode(),
				sbSPSText.toString());
		
		return sbSPSText.toString();
	}
	
	private String[] parseAddress(String strAddress) {
		strAddress = strAddress.replaceAll("\r", "");
		strAddress = strAddress.replaceAll("\n", "");
		String[] astrAddress = new String[2];
		for (int i = 0; i < 2; i++) {
			astrAddress[i] = ".";
			if (strAddress.length() > i * 39) {
				if (strAddress.length() > (i + 1) * 39)
					astrAddress[i] = strAddress.substring(i * 39, (i + 1) * 39);
				else
					astrAddress[i] = strAddress.substring(i * 39);
			}
		}
		return astrAddress;
	}
	
}
