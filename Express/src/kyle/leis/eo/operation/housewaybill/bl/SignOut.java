package kyle.leis.eo.operation.housewaybill.bl;

import java.util.List;

import com.ems.AUEMSInterface;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.IntervalTime;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.customerservice.track.bl.TrackThread;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.tp.SaveBatchwaybillTrans;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesCondition;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.SignoutLogThread;
import kyle.leis.eo.operation.housewaybill.tp.JSignoutTran;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.hi.TchnChannel;

public class SignOut {
	
	private StringBuffer m_sbLogInfo;
	
	public SavedResultUtility save(String strBwcodeDeparture,
			String strCwServerewbcode,
			String strBagLabelcode,
			String strOperId,
			boolean isIgnoreNotice,
			boolean isArrearAllowSignout,
			String strSotcode) throws Exception {
		return save(strBwcodeDeparture,
				strCwServerewbcode,
				strBagLabelcode,
				strOperId,
				isIgnoreNotice,
				isArrearAllowSignout,
				strSotcode,
				"",
				false);
	}
	
	
	public SavedResultUtility save(String strBwcodeDeparture,
			String strCwServerewbcode,
			String strBagLabelcode,
			String strOperId,
			boolean isIgnoreNotice,
			boolean isArrearAllowSignout,
			String strSotcode,
			String strSelfLabelcode,
			boolean isSOByServerLabelcode) throws Exception {
		HousewaybillColumns objHwbColumns = null;
		// 检查是否为空
		SavedResultUtility objSRUtility = new SavedResultUtility();
		// 按服务商子单出货
		// 但strSelfLabelcode不为空时表示按件出货
		// 按件出货时分两种模式，第一种服务商子单跟公司子单同时扫描出货
		// 第二种公司子单跟服务商单号同时扫描出货
		// 第一种模式取运单的方式跟其他方式不同，只能通过服务商子单号码来取运单
		boolean hasObtainHawb = false;
		if (!StringUtility.isNull(strSelfLabelcode)) {
			// 此出货模式为第一种模式，服务商单号实际应该是服务商子单号
			if (isSOByServerLabelcode) {
				if (StringUtility.isNull(strSelfLabelcode)) {
					PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
					objPUCollection.add("E_SIGNOUT_002", "按服务商子单号签出公司子单号不能为空", "SignOut.save");
					objSRUtility.setPromptUtilityCollection(objPUCollection);
					return objSRUtility;	
				}				
				String strCwcode = CorewaybillDemand.getCwcodeBySChildLabelcode(strCwServerewbcode);
				if (StringUtility.isNull(strCwcode)) {
					PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
					objPUCollection.add("E_SIGNOUT_002", strCwServerewbcode + "的服务商子单号码不存在", "SignOut.save");
					objSRUtility.setPromptUtilityCollection(objPUCollection);
					return objSRUtility;	
				}
				HousewaybillCondition objHWBCondition = new HousewaybillCondition();
				objHWBCondition.setCwcode(strCwcode);
				List listResults = HousewaybillDemand.query(objHWBCondition);
				objHwbColumns = (HousewaybillColumns)listResults.get(0);
				hasObtainHawb = true;
				// 效验服务商子单是否为出货状态
				/*
				CorewaybillpiecesCondition objCwpCondition = new CorewaybillpiecesCondition();
				objCwpCondition.setCplabelcode(strCwServerewbcode);
				objCwpCondition.setCwcode(objHwbColumns.getHwcwcode());
				List listPiecesResults = CorewaybillpiecesDemand.query(objCwpCondition);
				String strCwscode = ((CorewaybillpiecesColumns)listPiecesResults.get(0)).getCwscwscode();
				if (!StringUtility.isNull(strCwscode) && strCwscode.equals("SO")) {
					PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
					objPUCollection.add("E_SIGNOUT_004", strCwServerewbcode + "的服务商子单号码已经为出货状态", "SignOut.save");
					objSRUtility.setPromptUtilityCollection(objPUCollection);
					return objSRUtility;	
				}
				*/
			}
		}
		// 开始检查
		IntervalTime objIT = new IntervalTime("获取运单信息");
		m_sbLogInfo = new StringBuffer();
		
		if (!hasObtainHawb) {
			List listResults = HousewaybillDemand.loadNormalCollection(strCwServerewbcode, 
					ICorewaybillBasicData.EWBCODE_TYPE_SERVER);		
			if (listResults == null || listResults.size() < 1) {
				PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
				objPUCollection.add("E_SIGNOUT_001", "不存在此运单", "SignOut.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;
			}
			if (listResults.size() == 1) {
				objHwbColumns = (HousewaybillColumns)listResults.get(0);
			}
			else {
				for (int i = 0; i < listResults.size(); i++) {
					objHwbColumns = (HousewaybillColumns)listResults.get(i);
					if (objHwbColumns.getCwscwscode().equals("IP"))
						break;
				}
			}
			if (objHwbColumns == null || 
					StringUtility.isNull(objHwbColumns.getHwcwcode())) {
				PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
				objPUCollection.add("E_SIGNOUT_001", "不存在此运单", "SignOut.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;			
			}
		}
		if (StringUtility.isNull(objHwbColumns.getSchnchncode())) {
			PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
			objPUCollection.add("E_SIGNOUT_001", "服务渠道不能为空", "SignOut.save");
			objSRUtility.setPromptUtilityCollection(objPUCollection);
			return objSRUtility;					
		}
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		objIT = new IntervalTime("获取渠道以及检查是否支持所选择的效验模式");
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objHwbColumns.getSchnchncode());
		if (!StringUtility.isNull(strSelfLabelcode)) {
			// 效验是否属于同一个运单
			CorewaybillpiecesCondition objCwpCondition = new CorewaybillpiecesCondition();
			objCwpCondition.setCpselflabelcode(strSelfLabelcode);
			objCwpCondition.setCwcode(objHwbColumns.getHwcwcode());
			List listResults = CorewaybillpiecesDemand.query(objCwpCondition);
			if (listResults == null || listResults.size() < 1) {
				PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
				objPUCollection.add("E_SIGNOUT_116", strSelfLabelcode + "的公司子单号码与" + strCwServerewbcode + "的服务商子单号码不属于同一运单", "SignOut.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;	
			}
			String strCwscode = ((CorewaybillpiecesColumns)listResults.get(0)).getCwscwscode();
			if (!StringUtility.isNull(strCwscode) && strCwscode.equals("SO")) {
				PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
				objPUCollection.add("E_SIGNOUT_004", strSelfLabelcode + "的公司子单号码已经为出货状态", "SignOut.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;				
			}		
			if (!isSOByServerLabelcode && !StringUtility.isNull(objTchnChannel.getChnSobypiecessign())
					&& objTchnChannel.getChnSobypiecessign().equals("Y")) {
				PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
				objPUCollection.add("E_SIGNOUT_005", "设置为按服务商子单号码出货的服务渠道必须要选择按服务商子单出货", "SignOut.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;				
			}
			if (isSOByServerLabelcode && (StringUtility.isNull(objTchnChannel.getChnSobypiecessign())
					|| objTchnChannel.getChnSobypiecessign().equals("N"))) {
				PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
				objPUCollection.add("E_SIGNOUT_005", "渠道未设置成按服务商子单号码出货，请扫描服务商单号出货", "SignOut.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;				
			}			
			// 件数超过最大设置则给出提示
			String strMaxPieces = SystempropertyDemand.getMaxPiecesSOByChildLabelcode();
			if (!isIgnoreNotice &&
					!StringUtility.isNull(strMaxPieces) &&
					Integer.parseInt(objHwbColumns.getCwcwpieces()) > Integer.parseInt(strMaxPieces)) {
				PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
				objPUCollection.add("H_SIGNOUT_001", "件数超过" + strMaxPieces + "的最大限制", "SignOut.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);	
				return objSRUtility;
			}
		}
		String strEnterprise = SystempropertyDemand.getEnterprise();
		// 出货模式
		if (StringUtility.isNull(strEnterprise) || !strEnterprise.equals("QQYX")) {
			if (!StringUtility.isNull(strSotcode) && 
					!strSotcode.equals(ICorewaybillBasicData.SIGNOUT_MODE_NORMAL)) {
				// 判断服务渠道的出货模式是否跟选择的出货模式一致
				if (!objTchnChannel.getTdiSignouttype().getSotCode().equals(strSotcode)) {
					PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
					objPUCollection.add("E_SIGNOUT_001", "该票件不支持按您所选择的出货模式", "SignOut.save");
					objSRUtility.setPromptUtilityCollection(objPUCollection);
					return objSRUtility;				
				}
				// 如果选择了DHL标准或DHL第三方模式则首先创建出货主单
				if (strSotcode.equals(ICorewaybillBasicData.SIGNOUT_MODE_DHLSTANDARD) || 
						strSotcode.equals(ICorewaybillBasicData.SIGNOUT_MODE_DHLTHIRD)||
						strSotcode.equals(ICorewaybillBasicData.SIGNOUT_MODE_DHLCOMMERCE)) 
					strBwcodeDeparture = autoCreateDeparture(objHwbColumns.getSchnchncode(), 
						objHwbColumns.getScococode(),
						objHwbColumns.getEeeecode());
			}
		}
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		return save(strBwcodeDeparture,
				objHwbColumns,
				strBagLabelcode,
				strOperId,
				isIgnoreNotice,
				isArrearAllowSignout,
				strSelfLabelcode);
	}
	
	public SavedResultUtility save(String strBwcodeDeparture,
			String strCwServerewbcode,
			String strBagLabelcode,
			String strOperId,
			boolean isIgnoreNotice,
			boolean isArrearAllowSignout,
			String strSelfLabelcode,
			boolean isSOByServerLabelcode) throws Exception {
		IntervalTime objIT = new IntervalTime("获取运单信息2");
		
		HousewaybillColumns objHwbColumns = HousewaybillDemand.load(strCwServerewbcode, 
				ICorewaybillBasicData.EWBCODE_TYPE_SERVER);
		// 检查是否为空
		SavedResultUtility objSRUtility = new SavedResultUtility();
		if (objHwbColumns == null || 
				StringUtility.isNull(objHwbColumns.getHwcwcode())) {
			PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
			objPUCollection.add("E_SIGNOUT_001", "不存在此运单", "SignOut.save");
			objSRUtility.setPromptUtilityCollection(objPUCollection);
			return objSRUtility;
		}
		if (m_sbLogInfo == null)
			m_sbLogInfo = new StringBuffer();
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		return save(strBwcodeDeparture,
				objHwbColumns,
				strBagLabelcode,
				strOperId,
				isIgnoreNotice,
				isArrearAllowSignout,
				strSelfLabelcode);
	}	
	
	private void dispatch(HousewaybillColumns objHwbColumns,
			PromptUtilityCollection objPUCollection) throws Exception {
		if (StringUtility.isNull(objHwbColumns.getSchnchncode())) 
			return;
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objHwbColumns.getSchnchncode());
		if (objTchnChannel.getTdiServerstructuregroup() == null ||
				!objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("AUEMS"))
			return;
		AUEMSInterface objAUEMSInterface = new AUEMSInterface();
		boolean isDispatched = objAUEMSInterface.dispatch(objHwbColumns.getCwcwserverewbcode());
		if (!isDispatched)
			objPUCollection.add("E_SIGNOUT_0001", "调用确认出货的接口失败", "dispatch");	
		/*
		List listPieces = CorewaybillpiecesDemand.load(objHwbColumns.getHwcwcode());
		if (listPieces != null && listPieces.size() > 0) {
			AUEMSInterface objAUEMSInterface = new AUEMSInterface();
			for (int i = 0; i < listPieces.size(); i++) {
				CorewaybillpiecesColumns objCPColumns = (CorewaybillpiecesColumns)listPieces.get(i);
				if (StringUtility.isNull(objCPColumns.getCpcpbarcodelabelcode()))
					continue;
				boolean isDispatched = objAUEMSInterface.dispatch(objCPColumns.getCpcpbarcodelabelcode());
				if (!isDispatched) {
					objPUCollection.add("E_SIGNOUT_0001", "调用确认出货的接口失败", "dispatch");
					return;
				}
			}
		}	*/
	}
	
	private SavedResultUtility save(String strBwcodeDeparture,
			HousewaybillColumns objHwbColumns,
			String strBagLabelcode,
			String strOperId,
			boolean isIgnoreNotice,
			boolean isArrearAllowSignout,
			String strSelfLabelcode) throws Exception {
		
		IntervalTime objIT = new IntervalTime("获取运单件信息3");
		
		SavedResultUtility objSRUtility = new SavedResultUtility();
		List listCWPieces = buildSavedCWPieces(strBagLabelcode, objHwbColumns.getHwcwcode(), strSelfLabelcode);
		
		if (m_sbLogInfo == null)
			m_sbLogInfo = new StringBuffer();
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		// 效验
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		PromptUtilityCollection objPUCollection = objCoreWayBillCheck.checkSignOut(objHwbColumns, 
				strBwcodeDeparture,
				listCWPieces,
				strOperId,
				isArrearAllowSignout);
		String strCheckLog = objCoreWayBillCheck.getSOCheckLog();
		if (!StringUtility.isNull(strCheckLog))
			m_sbLogInfo.append(strCheckLog + "\r\n");
		// 澳洲渠道组需要调用接口确认出货
		dispatch(objHwbColumns, objPUCollection);
		// 检查
		if (objPUCollection.canGo(isIgnoreNotice)) {
			objIT = new IntervalTime("保存出货以及增加轨迹信息");
			/*
			SaveWaybillTransaction objSaveWBTrans = new SaveWaybillTransaction();
			objSaveWBTrans.setSignOutParam(objHwbColumns, 
					strBwcodeDeparture, 
					listCWPieces, 
					strOperId,
					strSelfLabelcode);
			objSaveWBTrans.execute();
			*/
			JSignoutTran objJSignoutTran = new JSignoutTran();
			objJSignoutTran.setSignOutParam(objHwbColumns, 
					strBwcodeDeparture, 
					listCWPieces, 
					strOperId,
					strSelfLabelcode);
			objJSignoutTran.execute();
			
			objHwbColumns = HousewaybillDemand.loadByCwcode(objHwbColumns.getHwcwcode());
			objSRUtility.setColumns(objHwbColumns);
			// 新增出货轨迹
			/*
			Track objTrack = new Track();
			objTrack.addSingleTrack(objHwbColumns.getHwcwcode(), 
					objHwbColumns.getOdtdtcode(), 
					"OC", 
					strOperId, 
					DateFormatUtility.getStandardSysdate());
			*/
			TrackThread objTrack = new TrackThread(objHwbColumns.getHwcwcode(), 
					objHwbColumns.getOdtdtcode(), 
					"OC", 
					strOperId, 
					DateFormatUtility.getStandardSysdate());
			objTrack.start();
			
			m_sbLogInfo.append(objIT.toString() + "\r\n");
			// 计费
			AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(objHwbColumns.getHwcwcode(),
					IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
					false);			
			objAFCThread.start();
			// 记录日志
			if (DateFormatUtility.getStandardSysdate().compareTo("2015-12-16 12:30:00") < 0) {
				SignoutLogThread objSLT = new SignoutLogThread(objHwbColumns.getPkpkcode(),
						objHwbColumns.getCwcwserverewbcode(),
						m_sbLogInfo.toString());
				objSLT.start();
			}
			// 清空提示内容
			objPUCollection = new PromptUtilityCollection();
		}
		objSRUtility.setPromptUtilityCollection(objPUCollection);
		return objSRUtility;
	}
	
	private List buildSavedCWPieces(String strBagLabelcode,
			String strCwcode,
			String strSelfLabelcode) throws Exception {
		List objList = CorewaybillpiecesDemand.load(strCwcode);
		if (objList == null || objList.size() < 1) return null;
		int iHasSignoutCount = 0;
		for (int i = 0; i < objList.size(); i++) {
			CorewaybillpiecesColumns objCwpColumns = (CorewaybillpiecesColumns)objList.get(i);
			if (!StringUtility.isNull(strSelfLabelcode) && strSelfLabelcode.equals(objCwpColumns.getCpcpselflabelcode())) {
				objCwpColumns.setCwscwscode("SO");	
				objCwpColumns.setCpcpbaglabelcode(strBagLabelcode);
			}
			if (StringUtility.isNull(objCwpColumns.getCpcpbaglabelcode()) && 
					StringUtility.isNull(strSelfLabelcode))
				objCwpColumns.setCpcpbaglabelcode(strBagLabelcode);
			
			if ("SO".equals(objCwpColumns.getCwscwscode()))
				iHasSignoutCount = iHasSignoutCount + 1;
		}
		// 大于10件其他未出货的全部出货
		if (objList.size() > 10 && iHasSignoutCount >= 1) {
			for (int i = 0; i < objList.size(); i++) {
				CorewaybillpiecesColumns objCwpColumns = (CorewaybillpiecesColumns)objList.get(i);
				if (!"SO".equals(objCwpColumns.getCwscwscode())) {
					objCwpColumns.setCwscwscode("SO");
					objCwpColumns.setCpcpbaglabelcode(strBagLabelcode);					
				}
			}
		}
		return objList;
	}
	
	private String autoCreateDeparture(String strServerChncode,
			String strSupplier,
			String strEecode) throws Exception {
		// 设置出货主单
		SimplebatchwaybillColumns objSBWBColumns = BatchWayBillDemand.getLatestUnCompleteBatchwaybill(strServerChncode, 
				strSupplier,
				strEecode,
				DateFormatUtility.getStandardSysdate(), 
				"D");
		// 新建出货主单
		String strBwcodeDeparture = "";
		if (objSBWBColumns == null) {
			// 保存
			SaveBatchwaybillTrans objSBWBTrans = new SaveBatchwaybillTrans();
			objSBWBTrans.setDepartureParam(strSupplier, 
					strServerChncode, 
					DateFormatUtility.getStandardSysdate(), 
					strEecode, 
					"0", 
					null);
			objSBWBTrans.execute();
			strBwcodeDeparture = String.valueOf(objSBWBTrans.getNewBwcode());
		} else {
			strBwcodeDeparture = objSBWBColumns.getBwbwcode();
		}
		return strBwcodeDeparture;
	}
}
