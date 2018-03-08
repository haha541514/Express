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

		// ��Ȩ�š��û�id������
		strModelContent = strModelContent.replace("$license$", ACCESS_LICENSE_NUMBER);
		strModelContent = strModelContent.replace("$userid$", USER_ID);
		strModelContent = strModelContent.replace("$password$", PASSWORD);
				
		// �����˺�
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		
		//Request?����ʱд�̶�ֵ��
		/**example��
		 * <Request>
				<TransactionReference><!-- ��ʶ�ͻ��˺ͷ�����֮��Ľ��ף�required:no-->
					<CustomerContext>Customer Comment</CustomerContext><!-- required:no -->
					<XpciVersion/>
				</TransactionReference>
				<RequestAction>ShipConfirm</RequestAction>
				<RequestOption>validate</RequestOption><!-- �ֵ���ַ��֤ ��blank, validate, or nonvalidate.��-->
		 </Request>
		 */
		
		//shipper:
		strModelContent = strModelContent.replace("$name$", StringUtility.splitMaxLength(objFIAColumns.getHwshippername(), 35));
//		strModelContent = strModelContent.replace("$ATTENTIONNAME$", objFIAColumns.getHwshippername());
		strModelContent = strModelContent.replace("$phone number$", objFIAColumns.getHwshippertelephone().replace("-", ""));
		strModelContent = strModelContent.replace("$shipper number$", objFIAColumns.getHwshipperaccount()/* objTchnChannel.getChnMasteraccount()��*/);// �����˵��ʺ�
		strModelContent = strModelContent.replace("$taxidentificationnumber$", objTchnChannel.getChnMasteraccount());//�����˵���˰��ʶ�� 
		strModelContent = strModelContent.replace("$address line1$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress1(), 35));
//		strModelContent = strModelContent.replace("$ADDRESS LINE2$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress2(), 35));
//		strModelContent = strModelContent.replace("$ADDRESS LINE3$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress3(), 35));
		strModelContent = strModelContent.replace("$city$", DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		strModelContent = strModelContent.replace("$state province code$", "GD");//������ʡ�ǹ㶫��GD)��
		strModelContent = strModelContent.replace("$postal code$", objFIAColumns.getHwshipperpostcode());
		strModelContent = strModelContent.replace("$country code$",DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()));
		
		//ship to��
		strModelContent = strModelContent.replace("$Company name$", objFIAColumns.getHwconsigneecompany());
		strModelContent = strModelContent.replace("$Attention name$", objFIAColumns.getHwconsigneename()); //�ջ�������
		strModelContent = strModelContent.replace("$Phone number$", objFIAColumns.getHwconsigneetelephone().replace("-", "")); 
		strModelContent = strModelContent.replace("$Address line1$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress1(), 35));
//		strModelContent = strModelContent.replace("$address line2$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress2(), 35));
//		strModelContent = strModelContent.replace("$address line3$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress3(), 35));
		strModelContent = strModelContent.replace("$City$", objFIAColumns.getHwConsigneecity());
		String statecode = DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity()//�ݡ�ʡ
				,objFIAColumns.getDthubcode()//��������
				,DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode())//���Ҵ���
				,objFIAColumns.getHwconsigneepostcode());
		strModelContent = strModelContent.replace("$State province code$", statecode==null?".":statecode);
		strModelContent = strModelContent.replace("$Postal code$", objFIAColumns.getHwconsigneepostcode());
		strModelContent = strModelContent.replace("$Country code$",  DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
		
		//ship from:(ship from=shipper����
		strModelContent = strModelContent.replace("$Company Name$", objFIAColumns.getHwshippercompany());
		strModelContent = strModelContent.replace("$Attention Name$", objFIAColumns.getHwshippername());
		strModelContent = strModelContent.replace("$Phone Number$", objFIAColumns.getHwshippertelephone().replace("-", ""));
		strModelContent = strModelContent.replace("$TaxidentificationNumber$", objTchnChannel.getChnMasteraccount());//�����˵���˰��ʶ�� 
		strModelContent = strModelContent.replace("$Address Line1$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress1(), 35));
//		strModelContent = strModelContent.replace("$ADDRESS LINE2$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress2(), 35));
//		strModelContent = strModelContent.replace("$ADDRESS LINE3$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress3(), 35));
		strModelContent = strModelContent.replace("$City$", DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		strModelContent = strModelContent.replace("$State Province Code$", "GD");
		strModelContent = strModelContent.replace("$Postal Code$", objFIAColumns.getHwshipperpostcode());
		strModelContent = strModelContent.replace("$Country Code$",DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()));
		
		//PaymentInformation:�������˺ţ�
		strModelContent = strModelContent.replace("$Account Number$", objTchnChannel.getChnPaymentaccount());
		
		//Service��
		/**example:
		 *   <Service>//UPS����01=�ڶ��쵽��02=���쵽��03=�������䣬07=��ݣ�08=�ӿ죬11=UPS��׼��12=3�쵽��13=��ǰ2�쵽��14=�ڶ����糿����54=�ؿ�Ӽ���59=�ڶ������絽��65=UPS��������M2=��һ���ʼ���M3=�����ʼ���M4=�ӿ�MaiI���£�M5=�����ʼ�
			      <Code>02</Code>                       //��������
			      <Description>2nd Day Air</Description>//��������
            </Service>
		 * 
		 */
		// ��Ʊ��Ϣ
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
		
		
		//Package��
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight()) ||
				new BigDecimal(objFIAColumns.getTransfervolumeweight()).equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		String strUnittransferweight = new BigDecimal(strWeight).divide(new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		// �������ͣ��ļ�D������P��ESI��ΪH
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
		/**���»�����Ϣ�ֶζ��յ�Դ�������е�Sample Requets���Ƚϼ򵥣������ܻ��л���ߴ硢Ͷ�������ֶ�*/
		for (int i = 1; i <= iTransferPieces; i++) {
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			<PackagingType>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Code>"+strProductcode+"</Code>");//��װ����
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Description>"+objFIAColumns.getCtename()+"</Description>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			</PackagingType>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			<Description>"+strDeclaredcontent+"</Description");//��������
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			<ReferenceNumber>");//�ο����
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Code>"+"00"+"</Code>");//�ο�������ʹ��루�����������д�˸�00��
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("			      <Value>"+objFIAColumns.getCwcustomerewbcode()+"</Value>");//�ͻ��ṩ�Ĳο����(�ͻ����ţ���
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
//			strTransferPiecesWords.append("			<LargePackageIndicator/>");//��������
//			strTransferPiecesWords.append("\r\n");
//			strTransferPiecesWords.append("			<AdditionalHandling>0</AdditionalHandling>");//�Ƿ���Ҫ���⴦����д��ʾ����Ҫ

		}
		strTransferPiecesWords.append("		</Package> ");
		strModelContent = strModelContent.replace("<Package>$Package$</Package>",
				strTransferPiecesWords.toString());
		return strModelContent;
	}
	
	/**
	 * �ѷ��ص�XML�ַ��������express/XOLTResult.xml
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

