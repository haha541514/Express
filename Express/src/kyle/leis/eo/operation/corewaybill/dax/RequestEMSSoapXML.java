package kyle.leis.eo.operation.corewaybill.dax;

import java.io.CharArrayWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Arrays;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TdiDistrict;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class RequestEMSSoapXML extends RequestXMLEX {

	@SuppressWarnings("unchecked")
	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {

		StringBuilder xmlBuilder = new StringBuilder();
		xmlBuilder
				.append("<eExpress_shipment_import xmlns=\"http://linexsolutions.com/\">");
		xmlBuilder.append("<awb>");
		xmlBuilder.append("<customerHawb>"
				+ objFIAColumns.getCwcustomerewbcode() + "</customerHawb>");
		xmlBuilder.append("<shipmentDate>"
				+ DateFormatUtility.getDateString(new Date(), "yyyy-MM-dd")
				+ "</shipmentDate>");
		// �ռ�����Ϣ
		xmlBuilder.append("<rName>" + objFIAColumns.getHwconsigneename()
				+ "</rName>");
		xmlBuilder.append("<rCountry>"
				+ DistrictDemand.getCountryNameByCity(objFIAColumns
						.getDtcode()) + "</rCountry>");

		TdiDistrict objTD = TdiDistrictDC.loadByKey(objFIAColumns.getDtcode());
		xmlBuilder.append("<rProvince>" + objTD.getDtStatename()
				+ "</rProvince>");
		String[] addresses = splitRAddress(objFIAColumns, 50, 2);
		for (int i = 0; i < addresses.length; i++) {
			xmlBuilder.append("<rAddress" + (i + 1) + " >" + addresses[i]
					+ "</rAddress" + (i + 1) + ">");
		}
		if (addresses.length < 2) {
			xmlBuilder.append("<rAddress2></rAddress2>");
		}
		// xmlBuilder.append("<rCity>" + objTD.getDtName() + "</rCity>");
		xmlBuilder.append("<rCity>" + objFIAColumns.getHwConsigneecity() + "</rCity>");
		xmlBuilder.append("<rZip>" + objFIAColumns.getHwconsigneepostcode()
				+ "</rZip>");
		
		// Ч��绰
		if (StringUtility.isNull(objFIAColumns.getHwconsigneetelephone()) || 
				objFIAColumns.getHwconsigneetelephone().length() != 11) {
			return "E_001_�ռ��˵绰����Ϊ11λ����";
		}
		if (!objFIAColumns.getHwconsigneetelephone().startsWith("1")) {
			return "E_001_�ռ��˵绰����Ϊ1��ͷ��11λ����";
		}
		if (!Pattern.matches("[0-9]*", objFIAColumns.getHwconsigneetelephone())) {
			return "E_001_�ռ��˵绰����Ϊ11λ����";
		}		
		
		xmlBuilder.append("<rTel>" + objFIAColumns.getHwconsigneetelephone()
				+ "</rTel>");
		// Ʊ����Ϣ
		xmlBuilder.append("<Pieces>" + objFIAColumns.getCwpieces()
				+ "</Pieces>");
		xmlBuilder.append("<weight>"
				+ objFIAColumns.getCwtransferchargeweight() + "</weight>");
		String strDeclaredvalue = "0.00";
		String strDeclaredcurrency = "RMB";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns) listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC
						.getCicitotalprice()));
				strDeclaredcurrency = objCIC.getCkckcode();
			}
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2,
					2).toString();
		}
		if (StringUtility.isNull(strDeclaredcurrency))
			strDeclaredcurrency = "RMB";
		xmlBuilder.append("<dCurrency>" + strDeclaredcurrency + "</dCurrency>");
		xmlBuilder.append("<dValue>" + strDeclaredvalue + "</dValue>");
		
		String strDuty_paid = "N";
		if (!StringUtility.isNull(objFIAColumns.getHwdutypaidsign())) {
			strDuty_paid = objFIAColumns.getHwdutypaidsign();
		}
		String[] mustYesCity={"�麣","��ͷ","����","����","����","�麣��","��ͷ��","������","������","������"};
		if(Arrays.asList(mustYesCity).contains(objFIAColumns.getHwConsigneecity())){
			if(strDuty_paid.equals("N")){
				return "E_001_��"+objFIAColumns.getHwConsigneecity()+"�е�DUTY_PAID����ΪYES��" ;
			}
		}
		xmlBuilder.append("<duty_paid>" + strDuty_paid + "</duty_paid>");
		
		xmlBuilder.append("</awb>");
		// �����Ϣ
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight())
				|| new BigDecimal(objFIAColumns.getTransfervolumeweight())
						.equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		BigDecimal strUnittransferweight = new BigDecimal(strWeight).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4);
		xmlBuilder.append("<objAwbDetail>");
		if (listCargo != null && listCargo.size() > 0) {
			for (Object obj : listCargo) {
				xmlBuilder.append("<awbDetail>");
				CargoinfoColumns cargoinfoColumns = (CargoinfoColumns) obj;
				xmlBuilder.append("<itemDescription>"
						+ cargoinfoColumns.getCiciename()
						+ "</itemDescription>");
				xmlBuilder.append("<hsCode></hsCode>");
				xmlBuilder
						.append("<itemPrice>"
								+ new BigDecimal(cargoinfoColumns
										.getCiciunitprice())
										.divide(BigDecimal.ONE, 2,
												RoundingMode.HALF_UP)
								+ "</itemPrice>");
				xmlBuilder.append("<itemPieces>"
						+ cargoinfoColumns.getCicipieces() + "</itemPieces>");
				xmlBuilder.append("<itemWeight>" + strUnittransferweight
						+ "</itemWeight>");
				xmlBuilder.append("</awbDetail>");
			}
		}
		xmlBuilder.append("</objAwbDetail>");
		xmlBuilder.append("</eExpress_shipment_import>");

		return formatXML(xmlBuilder.toString());
	}

	/**
	 * ��ʽ��XML
	 * 
	 * @param xmlStr
	 * @return
	 * @throws Exception
	 */
	public String formatXML(String xmlStr) throws Exception {
		Document doc = DocumentHelper.parseText(xmlStr);
		// ��ʽ��XML
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setSuppressDeclaration(true);// ���ò����XML����
		Writer out = new CharArrayWriter();
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
		String res = out.toString();
		return res;
	}

}
