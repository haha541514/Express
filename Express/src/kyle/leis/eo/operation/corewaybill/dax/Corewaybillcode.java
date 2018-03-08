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
		// 效验是否需要获取主单
		strChannelMasterAccount = StringUtility.isNull(strChannelMasterAccount) ? "" : strChannelMasterAccount;
		String strOldChannelMasterAccount = "";
		if (objHWBColumns != null)
			strOldChannelMasterAccount = objHWBColumns.getEraccount();
		strOldChannelMasterAccount = StringUtility.isNull(strOldChannelMasterAccount) ? "" : strOldChannelMasterAccount;
		
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(strMainWaybillcode)) 
			return null;
		// 是否已经换单标记
		String strChageServerEWBSign = "";
		if (objHWBColumns != null)	
			strChageServerEWBSign = objHWBColumns.getHwhwserverewbchangedsign();
		
		if (StringUtility.isNull(strChageServerEWBSign))
			strChageServerEWBSign = "N";
		// 获取主单
		// 如果原渠道跟现渠道的主单类型不一致则重新获取主单号
		// 或者没有换单也必须重新取号码
		if (objHWBColumns == null || 
				StringUtility.isNull(objHWBColumns.getHwhwopidrecord()) ||
				!strMainWaybillcode.equals(strOldMainWaybillcode) ||
				strChageServerEWBSign.equals("N") ||
				!strChannelMasterAccount.equals(strOldChannelMasterAccount)) {	
			// 初始化
			ServiceWaybillcodeFactory sf = new ServiceWaybillcodeFactory();
			sf.initWaybillcode(strMainWaybillcode, 
					objFIAColumns, 
					objPUCollection);
			if (objPUCollection != null && !objPUCollection.canGo(false))
				return objPUCollection;
			// 映射成WebService要求的数据格式
			ARequestXML objRequestXML = sf.getRequestXML();
			// 未初始化则按系统取号
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
			// 通过webservice获取主单
			AWaybillcodeRequest objWayBillCode = sf.getWaybillcodeRequest();
			WaybillcodeResponse objWaybillcodeResponse = objWayBillCode.getWaybillcode(param);
			// 设置主单跟子单
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
		// 设置主单跟子单号码
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
			// 设置换单打印标签标记
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
		// 是否已经换单标记
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
					"单据不足，无法再获得主单号码", 	"Input.modify");
			return objPUCollection;	
		}
		// 强制换单
		if (!isPackageChange) {
			if (StringUtility.isNull(strSubWaybillcode))
				return objPUCollection;
			objPUCollection = CorewaybillpiecesDemand.setChildLabelcode("_X",
					strSubWaybillcode,
					listWaybillpieces,
					true);
			// 未取得子单号码
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
		// 是否已经换单标记
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		String strChageServerEWBSign = "";
		if (objHWBColumns != null)
			strChageServerEWBSign = objHWBColumns.getHwhwserverewbchangedsign();
		if (StringUtility.isNull(strChageServerEWBSign))
			strChageServerEWBSign = "N";
		// 去除打印标签的url
		String strRemark = objFIAColumns.getHwremark();
		if (StringUtility.isNull(strRemark))
			objFIAColumns.setHwremark("");
		else
			objFIAColumns.setHwremark(strRemark.replaceAll("Label:", ""));	
		
		// 如果设置了按原单号走货规则并且单号正确则不需要换主单
		if (isCorrectLabelcode && isSettingSOByOriginEWB) {
			objPUCollection = CorewaybillpiecesDemand.setChildLabelcode(strOldSubWaybillcode,
					strSubWaybillcode,
					listWaybillpieces,
					false);
			// 未取得子单号码
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
		// 获取主单
		// 如果原渠道跟现渠道的主单类型不一致则重新获取主单号
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
						"单据不足，无法再获得主单号码", 	"Input.modify");
				return objPUCollection;	
			}
			// 强制换单
			if (!strSubWaybillcode.equals(IWaybillcodeBasicData.BCK_DHLCHILD_E)) {
				objPUCollection = CorewaybillpiecesDemand.setChildLabelcode(strOldSubWaybillcode,
						strSubWaybillcode,
						listWaybillpieces,
						true);
				// 未取得子单号码
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
			// 获取子单
			if (!strSubWaybillcode.equals(IWaybillcodeBasicData.BCK_DHLCHILD_E)) {
				objPUCollection = CorewaybillpiecesDemand.setChildLabelcode(strOldSubWaybillcode,
						strSubWaybillcode,
						listWaybillpieces,
						false);
				// 未取得子单号码
				if (objPUCollection == null)
					listWaybillpieces = null;		
			}
		}
		return objPUCollection;
	}	
}
