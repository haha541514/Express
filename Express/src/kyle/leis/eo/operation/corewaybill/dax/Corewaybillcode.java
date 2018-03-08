package kyle.leis.eo.operation.corewaybill.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.corewaybill.sv.CorewaybillDelegate;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybillcodekindDC;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TdiServerstructuregroup;
import kyle.leis.hi.TdiWaybillcodekind;

import com.AWaybillcodeRequest;
import com.WaybillcodeParam;
import com.WaybillcodeResponse;

public class Corewaybillcode {
	public PromptUtilityCollection setWaybillcodeByService(ForinputallColumns objFIAColumns,
			List listWaybillpieces,
			List listCargo,
			HousewaybillColumns objHWBColumns,
			String strMainWaybillcode,
			String strOldMainWaybillcode,
			String strSubWaybillcode,
			String strChannelMasterAccount) throws Exception {
		// Ч���Ƿ���Ҫ��ȡ����
		strChannelMasterAccount = StringUtility.isNull(strChannelMasterAccount) ? "" : strChannelMasterAccount;
		String strOldChannelMasterAccount = "";
		if (objHWBColumns != null)
			strOldChannelMasterAccount = objHWBColumns.getEraccount();
		strOldChannelMasterAccount = StringUtility.isNull(strOldChannelMasterAccount) ? "" : strOldChannelMasterAccount;
		
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(strMainWaybillcode)) 
			return null;
		// �Ƿ��Ѿ��������
		String strChageServerEWBSign = "";
		if (objHWBColumns != null)	
			strChageServerEWBSign = objHWBColumns.getHwhwserverewbchangedsign();
		
		if (StringUtility.isNull(strChageServerEWBSign))
			strChageServerEWBSign = "N";
		// ��ȡ����
		// ���ԭ���������������������Ͳ�һ�������»�ȡ������
		// ����û�л���Ҳ��������ȡ����
		if (objHWBColumns == null || 
				StringUtility.isNull(objHWBColumns.getHwhwopidrecord()) ||
				!strMainWaybillcode.equals(strOldMainWaybillcode) ||
				strChageServerEWBSign.equals("N") ||
				!strChannelMasterAccount.equals(strOldChannelMasterAccount)) {	
			// ��ʼ��
			ServiceWaybillcodeFactory sf = new ServiceWaybillcodeFactory();
			sf.initWaybillcode(strMainWaybillcode, 
					objFIAColumns, 
					objPUCollection);
			if (objPUCollection != null && !objPUCollection.canGo(false))
				return objPUCollection;
			// ӳ���WebServiceҪ������ݸ�ʽ
			ARequestXML objRequestXML = sf.getRequestXML();
			// δ��ʼ����ϵͳȡ��
			if (objRequestXML == null)
				return null;
			WaybillcodeParam param = objRequestXML.buildRequest(objFIAColumns, 
					listCargo, listWaybillpieces,
					objPUCollection);
			if (param.isNull())
				return objPUCollection;
			if (param.isError()) {
				objPUCollection.add("E_001", param.getSingleWaybillcodeParam(), "setWaybillcodeByService");
				return objPUCollection;
			}
			// ͨ��webservice��ȡ����
			AWaybillcodeRequest objWayBillCode = sf.getWaybillcodeRequest();
			WaybillcodeResponse objWaybillcodeResponse = objWayBillCode.getWaybillcode(param);
			// �����������ӵ�
			return setWaybillcodeByResponseResult(objFIAColumns, 
					listWaybillpieces, 
					objWaybillcodeResponse,
					strMainWaybillcode,
					strSubWaybillcode);
		}
		return objPUCollection;
	}
	
	
	private PromptUtilityCollection setWaybillcodeByResponseResult(ForinputallColumns objFIAColumns,
			List listWaybillpieces,
			WaybillcodeResponse objDHLWaybillcodeResult,
			String strMainWaybillcode,
			String strSubWaybillcode) {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		String strProgramcode = "H_001";
		/*
		if (!objDHLWaybillcodeResult.getExecuteResult())
			strProgramcode = "E_001";
		*/
		if (objDHLWaybillcodeResult.getCondition().size() > 0)
			for (int i = 0; i < objDHLWaybillcodeResult.getCondition().size(); i++) {
				String[] astrCondition = objDHLWaybillcodeResult.getCondition().get(i);
				objPUCollection.add(strProgramcode, astrCondition[0] + "&&&" + astrCondition[1], "Corewaybillcode.setWaybillcodeByService");
			}
		// �����������ӵ�����
		if (objDHLWaybillcodeResult.getExecuteResult()) {
			String strServerEwbcode = objDHLWaybillcodeResult.getMainWaybillcode();
			objFIAColumns.setCwserverewbcode(strServerEwbcode);
			// objFIAColumns.setCwewbcode(strServerEwbcode);
			objFIAColumns.setHwbookingid(objDHLWaybillcodeResult.getM_bookingId());
			if (objDHLWaybillcodeResult.getSubWaybillcode() != null && 
					objDHLWaybillcodeResult.getSubWaybillcode().size() > 0) {
				for (int i = 0; i < listWaybillpieces.size(); i++) {
					CorewaybillpiecesColumns objCWPiecesColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
					if (objDHLWaybillcodeResult.getSubWaybillcode().size() >= i + 1) {
						objCWPiecesColumns.setCpcplabelcode(objDHLWaybillcodeResult.getSubWaybillcode().get(i));
						if (objDHLWaybillcodeResult.getM_barcodeNumber() != null && objDHLWaybillcodeResult.getM_barcodeNumber().size() > 0) {
							objCWPiecesColumns.setCpcpbarcodelabelcode(objDHLWaybillcodeResult.getM_barcodeNumber().get(i));
							objFIAColumns.setCwewbcode(objDHLWaybillcodeResult.getM_barcodeNumber().get(i));
						}
					}
				}
			}
			// ���û�����ӡ��ǩ���
			objFIAColumns.setServerwbckbckcode(strMainWaybillcode);
			objFIAColumns.setHwhwserverewbchangedsign("Y");
			objFIAColumns.setHwlabelprinttimes(0);
			objFIAColumns.setSubwbckbckcode(strSubWaybillcode);
			objFIAColumns.setHWSEWBChangedByWebSign("Y");
			
			if (!StringUtility.isNull(objDHLWaybillcodeResult.getLableurl())) {
				objFIAColumns.setHwremark(objFIAColumns.getHwremark() + "Label:" + objDHLWaybillcodeResult.getLableurl());
			} else {
				String strRemark = objFIAColumns.getHwremark();
				if (StringUtility.isNull(strRemark))
					objFIAColumns.setHwremark("");
				else
					objFIAColumns.setHwremark(strRemark.replaceAll("Label:", ""));
			}
		} 
		return objPUCollection;
	}
	
	public PromptUtilityCollection setNewWaybillcodeBySystem(ForinputallColumns objFIAColumns,
			List listWaybillpieces,
			String strChncode,
			String strOperId) throws Exception {
		// �Ƿ��Ѿ��������
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(strChncode)) 
			return objPUCollection;
		
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		// TdiServerstructuregroup objTSSG = objTchnChannel.getTdiServerstructuregroup();
		
		TdiProductkind objTPK = TdiProductkindDC.loadByKey(objFIAColumns.getPk_code());
		TdiServerstructuregroup objPKTSSG = objTPK.getTdiServerstructuregroup();
		
		String strMainWaybillcode = "";
		String strSubWaybillcode = "";
		
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() != null)
			strMainWaybillcode = objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckCode();
		if (objTchnChannel.getTdiWaybillcodekindByChnSubbillcodekind() != null)
			strSubWaybillcode = objTchnChannel.getTdiWaybillcodekindByChnSubbillcodekind().getBckCode();		
		
		boolean isPackageChange = false;
		
		if (objPKTSSG != null && 
				!StringUtility.isNull(objPKTSSG.getSsgCode()) &&
				(objPKTSSG.getSsgCode().equals("HKPK") || 
						objPKTSSG.getSsgCode().equals("DHL-USGlobeMail"))) {
			isPackageChange = true;
		}
		
		if (StringUtility.isNull(strMainWaybillcode) || 
				strMainWaybillcode.equals(IWaybillcodeBasicData.BCK_DHLMASTER_E))
			return objPUCollection;
		
		//IComplexPrefix objIComplexPrefix = null;
		String strComplexPrefixType = "";
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckGroupcode().equals(IWaybillcodeBasicData.BCK_DHLDGM)) {
			strComplexPrefixType = IWaybillcodeBasicData.COMPLEX_PREFIX_DGM;
			//objIComplexPrefix = new DGMComplexPrefix(objFIAColumns);
		} else if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckGroupcode().equals(IWaybillcodeBasicData.BCK_GROUPHUEMS)) {
			strComplexPrefixType = IWaybillcodeBasicData.COMPLEX_PREFIX_HUEMS;
		}
		CorewaybillDelegate cwd = new CorewaybillDelegate();
		String strServerEwbcode = cwd.getDHLMainLabelcode(strMainWaybillcode, strOperId, strComplexPrefixType, objFIAColumns);
		if (StringUtility.isNull(strServerEwbcode)) {
			objPUCollection.add("E_WBC_001", 	
					"���ݲ��㣬�޷��ٻ����������", 	"Input.modify");
			return objPUCollection;	
		}
		// ǿ�ƻ���
		if (!isPackageChange) {
			if (StringUtility.isNull(strSubWaybillcode))
				return objPUCollection;
			objPUCollection = CorewaybillpiecesDemand.setChildLabelcode("_X",
					strSubWaybillcode,
					listWaybillpieces,
					true);
			// δȡ���ӵ�����
			if (objPUCollection == null)
				listWaybillpieces = null;		
			else if (!objPUCollection.canGo(false))
				return objPUCollection;
		}
		
		objFIAColumns.setCwserverewbcode(strServerEwbcode);
		objFIAColumns.setServerwbckbckcode(strMainWaybillcode);
		objFIAColumns.setHwhwserverewbchangedsign("Y");
		objFIAColumns.setHwlabelprinttimes(0);
		objFIAColumns.setSubwbckbckcode(strSubWaybillcode);		
		objFIAColumns.setHWSEWBChangedByWebSign("N");
		return objPUCollection;
	}
	
	
	public PromptUtilityCollection setWaybillcodeBySystem(ForinputallColumns objFIAColumns,
			List listWaybillpieces,
			HousewaybillColumns objHWBColumns,
			String strMainWaybillcode,
			String strOldMainWaybillcode,
			String strSubWaybillcode,
			String strOldSubWaybillcode,
			String strOperId,
			boolean isCorrectLabelcode,
			boolean isSettingSOByOriginEWB) throws Exception {
		// �Ƿ��Ѿ��������
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		String strChageServerEWBSign = "";
		if (objHWBColumns != null)
			strChageServerEWBSign = objHWBColumns.getHwhwserverewbchangedsign();
		if (StringUtility.isNull(strChageServerEWBSign))
			strChageServerEWBSign = "N";
		// ȥ����ӡ��ǩ��url
		String strRemark = objFIAColumns.getHwremark();
		if (StringUtility.isNull(strRemark))
			objFIAColumns.setHwremark("");
		else
			objFIAColumns.setHwremark(strRemark.replaceAll("Label:", ""));	
		
		// ��������˰�ԭ�����߻������ҵ�����ȷ����Ҫ������
		if (isCorrectLabelcode && isSettingSOByOriginEWB) {
			objPUCollection = CorewaybillpiecesDemand.setChildLabelcode(strOldSubWaybillcode,
					strSubWaybillcode,
					listWaybillpieces,
					false);
			// δȡ���ӵ�����
			if (objPUCollection == null)
				listWaybillpieces = null;		
			else if (!objPUCollection.canGo(false))
				return objPUCollection;			
			
			if (objHWBColumns != null)
				objFIAColumns.setCwserverewbcode(objHWBColumns.getCwcwserverewbcode());
			objFIAColumns.setServerwbckbckcode(strMainWaybillcode);
			objFIAColumns.setHwhwserverewbchangedsign("Y");
			objFIAColumns.setHWSEWBChangedByWebSign("N");
			objFIAColumns.setHwlabelprinttimes(0);
			objFIAColumns.setSubwbckbckcode(strSubWaybillcode);			
			
			return objPUCollection;
		}
		// ��ȡ����
		// ���ԭ���������������������Ͳ�һ�������»�ȡ������
		if (objHWBColumns == null || 
				StringUtility.isNull(objHWBColumns.getHwhwopidrecord()) ||
				!strMainWaybillcode.equals(strOldMainWaybillcode) ||
				strChageServerEWBSign.equals("N")) {		
			
			String strComplexPrefixType = "";		
			TdiWaybillcodekind objTdiWaybillcodekind = TdiWaybillcodekindDC.loadByKey(strMainWaybillcode);
			if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_DHLDGM)) {
				strComplexPrefixType = IWaybillcodeBasicData.COMPLEX_PREFIX_DGM;
			} else if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_GROUPHUEMS)) {
				strComplexPrefixType = IWaybillcodeBasicData.COMPLEX_PREFIX_HUEMS;
			}
			CorewaybillDelegate cwd = new CorewaybillDelegate();
			String strServerEwbcode = cwd.getDHLMainLabelcode(strMainWaybillcode, strOperId, strComplexPrefixType, objFIAColumns);	
			if (StringUtility.isNull(strServerEwbcode)) {
				objPUCollection.add("E_WBC_001", 	
						"���ݲ��㣬�޷��ٻ����������", 	"Input.modify");
				return objPUCollection;	
			}
			// ǿ�ƻ���
			if (!strSubWaybillcode.equals(IWaybillcodeBasicData.BCK_DHLCHILD_E)) {
				objPUCollection = CorewaybillpiecesDemand.setChildLabelcode(strOldSubWaybillcode,
						strSubWaybillcode,
						listWaybillpieces,
						true);
				// δȡ���ӵ�����
				if (objPUCollection == null)
					listWaybillpieces = null;		
				else if (!objPUCollection.canGo(false))
					return objPUCollection;
			}
			objFIAColumns.setCwserverewbcode(strServerEwbcode);
			//objFIAColumns.setCwewbcode(strServerEwbcode);
			objFIAColumns.setServerwbckbckcode(strMainWaybillcode);
			objFIAColumns.setHwhwserverewbchangedsign("Y");
			objFIAColumns.setHWSEWBChangedByWebSign("N");
			objFIAColumns.setHwlabelprinttimes(0);
			objFIAColumns.setSubwbckbckcode(strSubWaybillcode);
		} else {
			// ��ȡ�ӵ�
			if (!strSubWaybillcode.equals(IWaybillcodeBasicData.BCK_DHLCHILD_E)) {
				objPUCollection = CorewaybillpiecesDemand.setChildLabelcode(strOldSubWaybillcode,
						strSubWaybillcode,
						listWaybillpieces,
						false);
				// δȡ���ӵ�����
				if (objPUCollection == null)
					listWaybillpieces = null;		
			}
		}
		return objPUCollection;
	}	
}
