package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.metrology.WeightConversion;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.district.da.UspsdistrictCondition;
import kyle.leis.fs.dictionary.district.da.UspsdistrictQuery;

public class RequestUSPSXML extends RequestXML {

	private static String m_strModelContent;
	private final static String SITE_ID = "TEST";
	private final static String PASSWORD = "TEST";

	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {

		if (StringUtility.isNull(m_strModelContent))
			m_strModelContent = getRequestModelContent("ShipmentRequest.xml");
		String strModelContent = m_strModelContent;

		// 用户名和密码
		strModelContent = strModelContent.replace("$loginid$", SITE_ID);
		strModelContent = strModelContent.replace("$password$", PASSWORD);

		// DEFATTRIBUTES
		strModelContent = strModelContent.replace("$company$", objFIAColumns.getHwconsigneecompany());
		strModelContent = strModelContent.replace("$contact$", objFIAColumns.getHwconsigneename());
		strModelContent = strModelContent.replace("$address1$", objFIAColumns.getHwconsigneeaddress1());
		strModelContent = strModelContent.replace("$address2$", objFIAColumns.getHwconsigneeaddress2());
		strModelContent = strModelContent.replace("$city$", objFIAColumns.getHwConsigneecity());

		UspsdistrictQuery objUspsdistrictQuery = new UspsdistrictQuery();
		UspsdistrictCondition objUspsdistrictCondition = new UspsdistrictCondition();
		objUspsdistrictCondition.setCityname(objFIAColumns.getHwConsigneecity());
		objUspsdistrictCondition.setHubcode(objFIAColumns.getDthubcode());
		objUspsdistrictQuery.setCondition(objUspsdistrictCondition);
		List stateNameList = objUspsdistrictQuery.getResults();
		String stateName = "";
		if (stateNameList.size() > 0) {
			stateName = stateNameList.get(0).toString();
		}

		strModelContent = strModelContent.replace("$stateprovince$", stateName);
		strModelContent = strModelContent.replace("$postalcode$", objFIAColumns.getHwconsigneepostcode());
		strModelContent = strModelContent.replace("$residential$", "TRUE");
		strModelContent = strModelContent.replace("$phone$", objFIAColumns.getHwconsigneetelephone());
		strModelContent = strModelContent.replace("$countrysymbol$","UNITED_STATES");
		strModelContent = strModelContent.replace("$consigneecode$",objFIAColumns.getHwconsigneepostcode());

		// SHIPPERINFO
		strModelContent = strModelContent.replace("$shopper$", "IEEC");
		strModelContent = strModelContent.replace("$terms$", "SHIPER");
		strModelContent = strModelContent.replace("$currencycode$",objFIAColumns.getHwinsurancecurrency());// 货币代码
		strModelContent = strModelContent.replace("$shipDate$", objFIAColumns.getAdddate());

		String strDeclaredvalue = "0.00";
		String strDeclaredcurrency = "USD";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns) listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				strDeclaredcurrency = objCIC.getCkckcode();
			}
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2,2).toString();
		}

		strModelContent = strModelContent.replace("$currencode$",strDeclaredcurrency);
		strModelContent = strModelContent.replace("$monetarycalue$",strDeclaredvalue);

		// PACKAGES
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight())
				|| new BigDecimal(objFIAColumns.getTransfervolumeweight())
						.equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		String strUnittransferweight = new BigDecimal(strWeight).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		StringBuffer strTransferPiecesWords = new StringBuffer();
		strTransferPiecesWords.append("		<PACKAGES> ");
		for (int i = 1; i <= iTransferPieces; i++) {
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			<PKG>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<PKGWEIGHT>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				 	<WEIGHTUNITS>P</WEIGHTUNITS>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				 	<WEIGHTVALUE>"+ WeightConversion.convertKgToPound(new Double(strUnittransferweight)) + "</WEIGHTVALUE>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				</PKGWEIGHT>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<DIMENSION>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				 	<DIMUNITS>IN</DIMUNITS>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				 	<DIMVALUE>0x0x0</DIMVALUE>");// 默认尺寸大小
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				</DIMENSION>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<PACKAGING>CUSTOM</PACKAGING>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<REFERENCE>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				 	<CONSIGNEEREFERENCE>"+ objFIAColumns.getCwewbcode() + "</CONSIGNEEREFERENCE>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				 	<SHIPPERREFERENCE>"+ objFIAColumns.getCwcustomerewbcode()+ "</SHIPPERREFERENCE>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				</REFERENCE>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<LABELFORMAT>TANDATA_USPS_LABEL.MMS</LABELFORMAT>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			</PKG>");
			strTransferPiecesWords.append("\r\n");
		}
		strTransferPiecesWords.append("		</PACKAGES> ");
		strModelContent = strModelContent.replace("<SORT>$packages$</SORT>",
				strTransferPiecesWords.toString());

		strModelContent = strModelContent.replace("$scs$","TANDATA_USPS.USPS.PARCELPOST");
		strModelContent = strModelContent.replace("$closeoutmode$", "1");
		strModelContent = strModelContent.replace("$packagedetail$", "TRUE");

		return strModelContent;
	}

}
