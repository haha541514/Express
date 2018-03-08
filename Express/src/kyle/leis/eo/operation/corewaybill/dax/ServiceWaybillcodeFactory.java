package kyle.leis.eo.operation.corewaybill.dax;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybillcodekindDC;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiWaybillcodekind;

import com.AWaybillcodeRequest;
import com.dhl.DHLEShipWebWayBillCode;
import com.dhl.DHLMyWebWaybillCode;
import com.dhl.DHLTestWayBillCode;
import com.dhl.DHLWayBillCode;
import com.dhl.DHLWebWayBillCode;
import com.dhl.SGSOAPWayBillCode;
import com.ems.EMSWayBillCode;
import com.ems.HKEMSSoapWayBillCode;
import com.eparcel.EParcelInterface;
import com.eub.EUBWaybillCode;
import com.fedex.FedexWebWayBillCode;
import com.tnt.TNTIConWaybillCode;
import com.tnt.TNTWebWayBillCode;
import com.ups.UPSCanadaWebWaybillCode;
import com.ups.UPSSoapWaybillCode;
import com.ups.UPSWebWaybillCode;
import com.usps.USPSSoapWayBillCode;
import com.usps.USPSWayBillCode;

public class ServiceWaybillcodeFactory {
	
	private ARequestXML m_objRequestXML;
	private AWaybillcodeRequest m_objWayBillCode;
	
	public ARequestXML getRequestXML() {
		return m_objRequestXML;
	}
	
	public AWaybillcodeRequest getWaybillcodeRequest() {
		return m_objWayBillCode;
	}	
	
	public void initWaybillcode(String strMainWaybillcode,
			ForinputallColumns objFIAColumns,
			PromptUtilityCollection objPUCollection) throws Exception {
		
		TdiWaybillcodekind objTdiWaybillcodekind = TdiWaybillcodekindDC.loadByKey(strMainWaybillcode);
		if (!"Y".equals(objTdiWaybillcodekind.getBckFromwebservicesign()))
			return;
		// 映射成WebService要求的数据格式
		m_objRequestXML = new RequestDHLXML();
		m_objWayBillCode = new DHLWayBillCode();
		// USPS的服务
		if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_USPSMASTER)) {
			m_objRequestXML = new RequestUSPSXML();
			m_objWayBillCode = new USPSWayBillCode();
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_DHLFROMWEB_MASTER)) {
			m_objRequestXML = new RequestDHLWEB();
			m_objWayBillCode = new DHLWebWayBillCode();
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BAC_DHLSOAPFROMWEB_MASTER)) {
			m_objRequestXML = new RequestDHLXML();		
			m_objWayBillCode = new DHLTestWayBillCode();
			// 设置标签路径
			setLabelPathAndURL(objPUCollection, "dhlsvwaybill");
			
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BAC_DHLSG_MASTER)) {
			m_objRequestXML = new RequestSGSoapXML();
			m_objWayBillCode = new SGSOAPWayBillCode();
			// 设置标签路径
			setLabelPathAndURL(objPUCollection, "dhlsvwaybill");			
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_SBDAUEMSMC)) {
			m_objRequestXML = new RequestEMSJSON();
			m_objWayBillCode = new EMSWayBillCode();
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_DHLCNSHIP)) {
			m_objRequestXML = new RequestDHLEShipWeb();
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			m_objWayBillCode = new DHLEShipWebWayBillCode(channel.getChnRegistername(), 
					channel.getChnRegisterpassword());			
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BAC_USPSSOAP)) {
			m_objRequestXML = new RequestUSPSSoapXML();
			m_objWayBillCode = new USPSSoapWayBillCode();
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_TNTWEBSHIP)) {
			m_objRequestXML = new RequestTNTWeb();
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			m_objWayBillCode = new TNTWebWayBillCode(channel.getChnRegistername(), 
					channel.getChnRegisterpassword());			
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BAC_HKEMSIMPORT)) {
			m_objRequestXML = new RequestEMSSoapXML();
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			m_objWayBillCode = new HKEMSSoapWayBillCode(channel.getChnRegistername(), 
					channel.getChnRegisterpassword());			
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_TNTICONSHIP)) {
			m_objRequestXML = new RequestTNTXML();
			m_objWayBillCode = new TNTIConWaybillCode();
			// 设置标签路径
			setLabelPathAndURL(objPUCollection, "tntwaybill");
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_FEDEXWEBSHIP)) {
			m_objRequestXML = new RequestFedexWeb();
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			m_objWayBillCode = new FedexWebWayBillCode(channel.getChnRegistername(), 
					channel.getChnRegisterpassword());		
			setLabelPathAndURL(objPUCollection, "fdxwaybill");
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_EPARCEL)) {
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			m_objRequestXML = new RequestEParcelXML();
			m_objWayBillCode = new EParcelInterface(channel.getChnRegistername(), 
					channel.getChnRegisterpassword());
			// 设置标签路径
			setLabelPathAndURL(objPUCollection, "eparcel");
			
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_MYDHLWEBSHIP)){
			m_objRequestXML = new RequestDHLMyWeb();
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			m_objWayBillCode = new DHLMyWebWaybillCode(channel.getChnRegistername(), 
					channel.getChnRegisterpassword());			
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_UPSWEBSHIP)){
			m_objRequestXML = new RequestUPSWeb();
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			m_objWayBillCode = new UPSWebWaybillCode(channel.getChnRegistername(), 
					channel.getChnRegisterpassword());			
			// 设置标签路径
			setLabelPathAndURL(objPUCollection, "upsLabelImages");
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BAC_UPSSOAP)){
			m_objRequestXML = new RequestUPSSoap();
			
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			String chnName = channel.getChnRegistername();
			if (StringUtility.isNull(chnName) || !chnName.contains(",")) {
				objPUCollection.add("E_002", "请设置密钥和账号（密钥在前面），以','分割", "setWaybillcodeByService");
				return;
			}
			String[] keyAndName = chnName.split(",");
			m_objWayBillCode = new UPSSoapWaybillCode(keyAndName[0], keyAndName[1],
					channel.getChnRegisterpassword());			
			// 设置标签路径
			setLabelPathAndURL(objPUCollection, "upsLabelImages");
			
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_EMSEUB)){
			m_objRequestXML = new RequestEubXML();
			m_objWayBillCode = new EUBWaybillCode();
		
		} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_UPSCANADAWEB)) {
			m_objRequestXML = new RequestUPSWebCanada();
			TchnChannel channel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			m_objWayBillCode = new UPSCanadaWebWaybillCode(channel.getChnRegistername(), 
					channel.getChnRegisterpassword());
		}
		else {
			// 所有DHL
			//setLabelPathAndURL(objPUCollection, "dhlsvwaybill");
		}
	}
	
	private void setLabelPathAndURL(PromptUtilityCollection objPUCollection,
			String strActionname) throws Exception {
		String strWaybillFilePath = SystempropertyDemand.getWaybillFilePath();
		String strWaybillPrintURL = SystempropertyDemand.getWaybillPrintURL();
		
		if (StringUtility.isNull(strWaybillFilePath)) {
			objPUCollection.add("E_002", "请在系统属性表中设置WaybillFilePath的项，以确定标签生成的路径", "setWaybillcodeByService");
			return;
		}
		if (StringUtility.isNull(strWaybillPrintURL)) {
			objPUCollection.add("E_002", "请在系统属性表中设置WaybillPrintURL的项，以确定客户端访问标签的URL", "setWaybillcodeByService");
			return;
		}				
		m_objWayBillCode.setVisitPDFHttpPrefix(strWaybillPrintURL + "/" + strActionname + "/");
		m_objWayBillCode.setVisitPDFFilePrefix(strWaybillFilePath + "/" + strActionname + "/");
	}

}
