package kyle.leis.eo.operation.corewaybill.dax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;






import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.ForinputallQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;
public class RequestUPSXML extends RequestXML {

	private static String m_strModelContent;
	private final static String ACCESS_LICENSE_NUMBER = "TEST";
	private final static String USER_ID = "TEST";
	private final static String PASSWORD = "TEST";
	
	@Override
	public String getRequestModelContent(String strXMLFile) throws Exception {
		String strModelContent = "";
		FileInputStream fis = new FileInputStream(strXMLFile);
		try {
			int fisSize = fis.available();
			byte[] buffer = new byte[fisSize];
			fis.read(buffer);
			strModelContent = new String(buffer);
		} finally {
			if (fis != null)
				fis.close();
		}
		return strModelContent;
	}
	
	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		if (StringUtility.isNull(m_strModelContent))
			m_strModelContent = getRequestModelContent("F:\\2014workspace\\express\\src\\ShipConfirmRequest.xml");
		String strModelContent = m_strModelContent;

		// 授权号、用户id和密码
		strModelContent = strModelContent.replace("$license$", ACCESS_LICENSE_NUMBER);
		strModelContent = strModelContent.replace("$userid$", USER_ID);
		strModelContent = strModelContent.replace("$password$", PASSWORD);
				
		// 渠道账号
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		
		//Request?（暂时写固定值）
		/**example：
		 * <Request>
				<TransactionReference><!-- 标识客户端和服务器之间的交易，required:no-->
					<CustomerContext>Customer Comment</CustomerContext><!-- required:no -->
					<XpciVersion/>
				</TransactionReference>
				<RequestAction>ShipConfirm</RequestAction>
				<RequestOption>validate</RequestOption><!-- 街道地址验证 （blank, validate, or nonvalidate.）-->
		 </Request>
		 */
		
		//shipper:
		strModelContent = strModelContent.replace("$name$", StringUtility.splitMaxLength(objFIAColumns.getHwshippername(), 35));
//		strModelContent = strModelContent.replace("$ATTENTIONNAME$", objFIAColumns.getHwshippername());
		strModelContent = strModelContent.replace("$phone number$", objFIAColumns.getHwshippertelephone().replace("-", ""));
		strModelContent = strModelContent.replace("$shipper number$", objFIAColumns.getHwshipperaccount()/* objTchnChannel.getChnMasteraccount()？*/);// 托运人的帐号
		strModelContent = strModelContent.replace("$taxidentificationnumber$", objTchnChannel.getChnMasteraccount());//托运人的纳税标识号 
		strModelContent = strModelContent.replace("$address line1$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress1(), 35));
//		strModelContent = strModelContent.replace("$ADDRESS LINE2$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress2(), 35));
//		strModelContent = strModelContent.replace("$ADDRESS LINE3$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress3(), 35));
		strModelContent = strModelContent.replace("$city$", DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		strModelContent = strModelContent.replace("$state province code$", "GD");//发件人省是广东（GD)？
		strModelContent = strModelContent.replace("$postal code$", objFIAColumns.getHwshipperpostcode());
		strModelContent = strModelContent.replace("$country code$",DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()));
		
		//ship to：
		strModelContent = strModelContent.replace("$Company name$", objFIAColumns.getHwconsigneecompany());
		strModelContent = strModelContent.replace("$Attention name$", objFIAColumns.getHwconsigneename()); //收货人姓名
		strModelContent = strModelContent.replace("$Phone number$", objFIAColumns.getHwconsigneetelephone().replace("-", "")); 
		strModelContent = strModelContent.replace("$Address line1$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress1(), 35));
//		strModelContent = strModelContent.replace("$address line2$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress2(), 35));
//		strModelContent = strModelContent.replace("$address line3$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress3(), 35));
		strModelContent = strModelContent.replace("$City$", objFIAColumns.getHwConsigneecity());
		String statecode = DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity()//州、省
				,objFIAColumns.getDthubcode()//机场代码
				,DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode())//国家代码
				,objFIAColumns.getHwconsigneepostcode());
		strModelContent = strModelContent.replace("$State province code$", statecode==null?".":statecode);
		strModelContent = strModelContent.replace("$Postal code$", objFIAColumns.getHwconsigneepostcode());
		strModelContent = strModelContent.replace("$Country code$",  DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
		
		//ship from:(ship from=shipper？）
		strModelContent = strModelContent.replace("$Company Name$", objFIAColumns.getHwshippercompany());
		strModelContent = strModelContent.replace("$Attention Name$", objFIAColumns.getHwshippername());
		strModelContent = strModelContent.replace("$Phone Number$", objFIAColumns.getHwshippertelephone().replace("-", ""));
		strModelContent = strModelContent.replace("$TaxidentificationNumber$", objTchnChannel.getChnMasteraccount());//托运人的纳税标识号 
		strModelContent = strModelContent.replace("$Address Line1$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress1(), 35));
//		strModelContent = strModelContent.replace("$ADDRESS LINE2$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress2(), 35));
//		strModelContent = strModelContent.replace("$ADDRESS LINE3$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress3(), 35));
		strModelContent = strModelContent.replace("$City$", DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		strModelContent = strModelContent.replace("$State Province Code$", "GD");
		strModelContent = strModelContent.replace("$Postal Code$", objFIAColumns.getHwshipperpostcode());
		strModelContent = strModelContent.replace("$Country Code$",DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()));
		
		//PaymentInformation:（付款账号）
		strModelContent = strModelContent.replace("$Account Number$", objTchnChannel.getChnPaymentaccount());
		
		//Service：
		/**example:
		 *   <Service>//UPS服务（01=第二天到，02=二天到，03=地面运输，07=快递，08=加快，11=UPS标准，12=3天到，13=提前2天到，14=第二天早晨到，54=特快加急，59=第二天上午到，65=UPS保护程序，M2=第一类邮件，M3=优先邮件，M4=加快MaiI创新，M5=优先邮件
			      <Code>02</Code>                       //服务类型
			      <Description>2nd Day Air</Description>//服务描述
            </Service>
		 * 
		 */
		// 发票信息
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + ",";
			}
		}
		
		
		//Package：
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight()) ||
				new BigDecimal(objFIAColumns.getTransfervolumeweight()).equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		String strUnittransferweight = new BigDecimal(strWeight).divide(new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		// 货物类型，文件D，包裹P，ESI则为H
		String strProductcode = "P";
		if (objFIAColumns.getCtcode().equals("ADOX"))
			strProductcode = "D";
		if (objFIAColumns.getCtcode().equals("AWPX")) {
			String strWPXMapping = objTchnChannel.getChnWpxspsmappingname();
			if (!StringUtility.isNull(strWPXMapping) && strWPXMapping.equals("ESI"))
				strProductcode = "H";
		}
		StringBuffer strTransferPiecesWords = new StringBuffer(); ;
		strTransferPiecesWords.append("		<Package> ");
		/**以下货物信息字段对照的源码资料中的Sample Requets，比较简单，还可能会有货物尺寸、投保金额等字段*/
		for (int i = 1; i <= iTransferPieces; i++) {
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			<PackagingType>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Code>"+strProductcode+"</Code>");//包装类型
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Description>"+objFIAColumns.getCtename()+"</Description>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			</PackagingType>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			<Description>"+strDeclaredcontent+"</Description");//货物描述
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			<ReferenceNumber>");//参考编号
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Code>"+"00"+"</Code>");//参考编号类型代码（不清楚，例子写了个00）
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Value>"+objFIAColumns.getCwcustomerewbcode()+"</Value>");//客户提供的参考编号(客户单号？）
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			</ReferenceNumber>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			<PackageWeight>");
			strTransferPiecesWords.append("\r\n");
//			strTransferPiecesWords.append("			      <UnitOfMeasurement></UnitOfMeasurement>");
//			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Weight>"+strUnittransferweight+"</Weight>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			</PackageWeight>");
			strTransferPiecesWords.append("\r\n");
//			strTransferPiecesWords.append("			<LargePackageIndicator/>");//大包裹标记
//			strTransferPiecesWords.append("\r\n");
//			strTransferPiecesWords.append("			<AdditionalHandling>0</AdditionalHandling>");//是否需要额外处理，不写表示不需要

		}
		strTransferPiecesWords.append("		</Package> ");
		strModelContent = strModelContent.replace("<Package>$Package$</Package>",
				strTransferPiecesWords.toString());
		return strModelContent;
	}
	
	/**
	 * 把返回的XML字符串输出到express/XOLTResult.xml
	 * @param response
	 */
	private static void updateResultsToFile(String response){
    	BufferedWriter bw = null;
    	try{    		
    		File outFile = new File("XOLTResult.xml");
    		System.out.println("Output file deletion status: " + outFile.delete());
    		outFile.createNewFile();
    		System.out.println("Output file location: " + outFile.getCanonicalPath());
    		bw = new BufferedWriter(new FileWriter(outFile));
    		StringBuffer strBuf = new StringBuffer();
     		strBuf.append(response);
    		bw.write(strBuf.toString());
    		bw.close();    		    		
    	}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if (bw != null){
					bw.close();
					bw = null;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}		
    }
	
	public static void main(String[] args) throws Exception {
		ForinputallQuery query = new ForinputallQuery();
		ForinputallCondition condition = new ForinputallCondition();
		condition.setCwcode("4288");
		query.setCondition(condition);
		ForinputallColumns columns = (ForinputallColumns) query.getResults().get(0);
		List list = CargoInfoDemand.queryByCwCode("4288");
		RequestUPSXML me = new RequestUPSXML();
		String xmlString = me.buildRequestXML(columns, list, null, new PromptUtilityCollection());
		System.out.println(xmlString);
		updateResultsToFile(xmlString);
    }
	
	
}

