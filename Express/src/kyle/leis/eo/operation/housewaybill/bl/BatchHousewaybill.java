package kyle.leis.eo.operation.housewaybill.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.tp.SaveBatchwaybillTrans;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.PredictOrderColumns;
import kyle.leis.eo.operation.housewaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.tp.SaveWaybillTransaction;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.hi.TchnChannel;

public class BatchHousewaybill {
	/**
	 * 批量完成全部操作
	 * @param objPOColumns
	 * @param strOperId
	 */
	public PromptUtilityCollection signInSignOut(PredictwaybillColumns objPWBColumns,
			List listCargoInfo,
			String strChncode,
			String strBwcodeDeparture,
			String strOperId,
			boolean isAutoSignout,
			boolean isPrealertSign,
			String strEecode) throws Exception {
		PredictOrderColumns objPOColumns = transfer(objPWBColumns, listCargoInfo);
		objPOColumns.setEeCode(strEecode);
		// 检查客户运单是否重复
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		PromptUtilityCollection objCheckEwbcode = objCoreWayBillCheck.checkCustomerEwbcode(objPOColumns.getStrCwcustomerewbcode(), 
				"NULL");
		if (!objCheckEwbcode.canGo(true))
			return objCheckEwbcode;
		// 保存为预报
		PredictOrder objPredictOrder = new PredictOrder();
		InputAllQReturn objInputAllQReturn = objPredictOrder.save(objPOColumns, strOperId);
		PromptUtilityCollection objPUCollection = objInputAllQReturn.getPromptUtilityCollection();
		if (objPUCollection != null && !objPUCollection.canGo(true))
			return objPUCollection;
		// 制单设置服务渠道
		List listHouseWayBill = objInputAllQReturn.getHWBResults();
		if (listHouseWayBill == null || listHouseWayBill.size() < 1) {
			objPUCollection = new PromptUtilityCollection();
			objPUCollection.add("E_001", 
					"新增预报数据失败！", 
					"BatchHousewaybill.signInSignOut");
			return objPUCollection;
		}
		String strCwcode = ((ForinputallColumns)listHouseWayBill.get(0)).getCwcode();
		HousewaybillColumns objHWColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		// 设置服务商和服务渠道
		if (!StringUtility.isNull(strChncode)) {
			objHWColumns.setSchnchncode(strChncode);
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
			objHWColumns.setScococode(objTchnChannel.getTcoCorporation().getCoCode());
		}
		// 设置件数信息
		List listCWPieces = buildPiecesInfo(objHWColumns);		
		// 预报用计费重量作为体积重量导入，还需要重新计费重量
		if (isPrealertSign) {
			// 获得体积重系数
			ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objHWColumns, 
					null);
			Chargeweight objChargeweight = new Chargeweight();
			ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
			objHWColumns.setCwcwvolumerate(Integer.parseInt(objCWResult.getVolumeRate()));
			listCWPieces = buildPrealertPiecesInfo(objHWColumns, 
					objPWBColumns.getCwcwchargeweight(),
					Integer.parseInt(objCWResult.getVolumeRate()));
			// 计算计费重量
			objCWResult = objChargeweight.getDefaultChargeWeight(objPWBColumns.getCwcwgrossweight(), 
					objPWBColumns.getCwcwchargeweight());
			objHWColumns.setCwcwchargeweight(new BigDecimal(objCWResult.getChargeweight()));
			objHWColumns.setCwscwscode("PR");
			objHWColumns.setSchnchncode("");
			objHWColumns.setScococode("");
			isAutoSignout = false;
		}	else {
			//设置出货重量
			objHWColumns.setCwscwscode("SI");
			if (!StringUtility.isNull(objPWBColumns.getCwcwtransferchargeweight())) {
				objHWColumns.setCwcwtransferchargeweight(new BigDecimal(objPWBColumns.getCwcwtransferchargeweight()));
				objHWColumns.setCwcwserverchargeweight(new BigDecimal(objPWBColumns.getCwcwtransferchargeweight()));
			}
		}
		// 收货即出货保存
		SaveWaybillTransaction objSWTrans = new SaveWaybillTransaction();
		objSWTrans.setSignInSignOutParam(objHWColumns, 
				strBwcodeDeparture, 
				listCWPieces, 
				null, 
				strOperId,
				isAutoSignout);
		objSWTrans.execute();
		// 记录轨迹
		if (!isPrealertSign) {
			Track objTrack = new Track();
			objTrack.addSingleTrack(strCwcode, 
					objHWColumns.getOdtdtcode(), 
					"AF", 
					strOperId, 
					DateFormatUtility.getStandardSysdate());			
		}
		// 新增出货轨迹
		if (isAutoSignout) {
			Track objTrack = new Track();
			objTrack.addSingleTrack(objHWColumns.getHwcwcode(), 
					objHWColumns.getOdtdtcode(), 
					"OC", 
					strOperId, 
					DateFormatUtility.getStandardSysdate());			
		}
		// 重新计费
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(strCwcode,
				"",
				false);
		objAFCThread.start();
		if (objPUCollection == null)
			objPUCollection = new PromptUtilityCollection(); 
		return objPUCollection;
	}
	
	/**
	 * 制单并出货
	 * @param objFIAColumns
	 * @param listCargoInfo
	 * @param isAutoSignout
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public PromptUtilityCollection simpleInputSignout(ForinputallColumns objFIAColumns,
			List listCargoInfo,
			boolean isAutoSignout,
			boolean isArrearAllowSignout,
			String strOperId) throws Exception {
		// 制单
		Input objInput = new Input();
		if (!StringUtility.isNull(objFIAColumns.getHwconsigneecompany()) && 
				objFIAColumns.getHwconsigneecompany().equals(".")) {
			objFIAColumns.setHwconsigneecompany("-");
		}
		if (!StringUtility.isNull(objFIAColumns.getHwconsigneename()) && 
				objFIAColumns.getHwconsigneename().equals(".")) {
			objFIAColumns.setHwconsigneename("-");
		}		
		InputAllQReturn objInputAllQReturn = objInput.modify(strOperId, 
				objFIAColumns, 
				listCargoInfo,
				false,
				false,
				false);
		PromptUtilityCollection objPUCollection = objInputAllQReturn.getPromptUtilityCollection();
		if (objPUCollection != null && !objPUCollection.canGo(true))
			return objPUCollection;
		if (objPUCollection == null)
			objPUCollection = new PromptUtilityCollection();
		// 不自动出货则退出程序
		if (!isAutoSignout) return objPUCollection;
		if (StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) {
			objPUCollection.add("E_002", "服务渠道不能为空", "BatchHousewaybill.simpleInputSignout");
			return objPUCollection;
		}
		objFIAColumns.setCocode_Cwsp(TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn()).getTcoCorporation().getCoCode());
		// 设置出货主单
		SimplebatchwaybillColumns objSBWBColumns = BatchWayBillDemand.getLatestUnCompleteBatchwaybill(objFIAColumns.getChncode_Cwspchn(), 
				objFIAColumns.getCocode_Cwsp(),
				objFIAColumns.getEecode(),
				DateFormatUtility.getStandardSysdate(), 
				"D");
		// 新建出货主单
		String strBwcodeDeparture = "";
		if (objSBWBColumns == null) {
			// 保存
			SaveBatchwaybillTrans objSBWBTrans = new SaveBatchwaybillTrans();
			objSBWBTrans.setDepartureParam(objFIAColumns.getCocode_Cwsp(), 
					objFIAColumns.getChncode_Cwspchn(), 
					DateFormatUtility.getStandardSysdate(), 
					objFIAColumns.getEecode(), 
					"0", 
					null);
			objSBWBTrans.execute();
			strBwcodeDeparture = String.valueOf(objSBWBTrans.getNewBwcode());
		} else {
			strBwcodeDeparture = objSBWBColumns.getBwbwcode();
		}
		// 出货
		// String strCwcode = objFIAColumns.getCwcode();
		// HousewaybillColumns objHWColumns = HousewaybillDemand.loadByCwcode(strCwcode);	
		// List listCWPieces = CorewaybillpiecesDemand.load(strCwcode);
		SignOut objSignOut = new SignOut();
		SavedResultUtility objSRU = objSignOut.save(strBwcodeDeparture, 
				objFIAColumns.getCwserverewbcode(), 
				null, 
				strOperId, 
				false,
				isArrearAllowSignout,
				null,
				false);
		/*
		SaveWaybillTransaction objSWTrans = new SaveWaybillTransaction();
		objSWTrans.setSignOutParam(objHWColumns, 
				strBwcodeDeparture, 
				listCWPieces, 
				strOperId);*/
		PromptUtilityCollection objSOPUCollection = objSRU.getPromptUtilityCollection();
		if (objSOPUCollection == null)
			objSOPUCollection = new PromptUtilityCollection();
		if (objPUCollection != null)
			objSOPUCollection.addAll(objPUCollection);
		return objSOPUCollection;		
	}
	
	
	/**
	 * 同步运单数据
	 * @param objFIAColumns
	 * @param listCargoInfo
	 * @param listCWPieces
	 * @param strOperId
	 * @throws Exception
	 */
	public PromptUtilityCollection syncHousewaybill(ForinputallColumns objFIAColumns,
			List listCargoInfo,
			List listCWPieces,
			String strOperId) throws Exception {
		Input objInput = new Input();
		InputAllQReturn objInputAllQReturn = objInput.inputAll(strOperId, 
				objFIAColumns, 
				listCargoInfo, 
				listCWPieces,
				true);
		PromptUtilityCollection objPUCollection = objInputAllQReturn.getPromptUtilityCollection();
		if (objPUCollection != null && !objPUCollection.canGo(true))
			return objPUCollection;
		// 制单设置服务渠道
		List listHouseWayBill = objInputAllQReturn.getHWBResults();		
		String strCwcode = ((ForinputallColumns)listHouseWayBill.get(0)).getCwcode();
		HousewaybillColumns objHWColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		// 设置出货主单
		SimplebatchwaybillColumns objSBWBColumns = BatchWayBillDemand.getLatestUnCompleteBatchwaybill(objHWColumns.getSchnchncode(), 
				objHWColumns.getScococode(), 
				objHWColumns.getEeeecode(), 
				DateFormatUtility.getStandardSysdate(), 
				"D");
		// 新建出货主单
		String strBwcodeDeparture = objSBWBColumns.getBwbwcode();
		if (objSBWBColumns == null) {
			// 保存
			SaveBatchwaybillTrans objSBWBTrans = new SaveBatchwaybillTrans();
			objSBWBTrans.setDepartureParam(objHWColumns.getScococode(), 
					objHWColumns.getSchnchncode(), 
					DateFormatUtility.getStandardSysdate(), 
					objHWColumns.getEeeecode(), 
					"0", 
					null);
			objSBWBTrans.execute();
			strBwcodeDeparture = String.valueOf(objSBWBTrans.getNewBwcode());
		}
		// 出货
		SaveWaybillTransaction objSWTrans = new SaveWaybillTransaction();
		objSWTrans.setSignOutParam(objHWColumns, 
				strBwcodeDeparture, 
				listCWPieces, 
				strOperId,
				"");
		if (objPUCollection == null)
			objPUCollection = new PromptUtilityCollection();
		return objPUCollection;
	}
	
	private List buildPrealertPiecesInfo(HousewaybillColumns objHWColumns, 
			String strVolumechargeweight,
			int iVolumeRate) {
		if (StringUtility.isNull(strVolumechargeweight))
			strVolumechargeweight = "0";
		int iPieces = Integer.parseInt(objHWColumns.getCwcwpieces());
		List<CorewaybillpiecesColumns> listPiecesColumns = new ArrayList<CorewaybillpiecesColumns>();
		for (int i = 0; i < iPieces; i++) {
			CorewaybillpiecesColumns objCWPColumns = new CorewaybillpiecesColumns();
			objCWPColumns.setCpcomp_idcpid(i);
			objCWPColumns.setCpcomp_idcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			if (i == 0) {
				objCWPColumns.setCpcpgrossweight(new BigDecimal(objHWColumns.getCwcwgrossweight()));
				objCWPColumns.setCpcpheight(new BigDecimal(strVolumechargeweight));
				objCWPColumns.setCpcpwidth(new BigDecimal(String.valueOf(iVolumeRate)));
				objCWPColumns.setCpcplength(new BigDecimal("1"));		
				objCWPColumns.setCpcpselflabelcode(buildSelfLabelcode(objHWColumns.getCwcwcustomerewbcode(),
						1));
			}
			else {
				objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
				objCWPColumns.setCpcpheight(new BigDecimal("0"));
				objCWPColumns.setCpcpwidth(new BigDecimal("0"));
				objCWPColumns.setCpcplength(new BigDecimal("0"));	
				objCWPColumns.setCpcpselflabelcode(buildSelfLabelcode(objHWColumns.getCwcwcustomerewbcode(),
						i + 1));				
			}
			listPiecesColumns.add(objCWPColumns);
		}
		return listPiecesColumns;
	}	
	
    private String buildSelfLabelcode(String strCustomerEwbcode, int iIndex)
    {
    	String strSelfLabelcode = String.valueOf(iIndex);
        for (int i = 0; i < 3 - strSelfLabelcode.length(); i++)
        {
            strSelfLabelcode = "0" + strSelfLabelcode;
        }
        return strCustomerEwbcode + "H" + strSelfLabelcode;
    }	
	
	
	private List buildPiecesInfo(HousewaybillColumns objHWColumns) {
		int iPieces = Integer.parseInt(objHWColumns.getCwcwpieces());
		List<CorewaybillpiecesColumns> listPiecesColumns = new ArrayList<CorewaybillpiecesColumns>();
		for (int i = 0; i < iPieces; i++) {
			CorewaybillpiecesColumns objCWPColumns = new CorewaybillpiecesColumns();
			objCWPColumns.setCpcomp_idcpid(i);
			objCWPColumns.setCpcomp_idcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			if (i == 0) 
				objCWPColumns.setCpcpgrossweight(new BigDecimal(objHWColumns.getCwcwchargeweight()));
			else
				objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
			objCWPColumns.setCpcpheight(new BigDecimal("0"));
			objCWPColumns.setCpcpwidth(new BigDecimal("0"));
			objCWPColumns.setCpcplength(new BigDecimal("0"));
			listPiecesColumns.add(objCWPColumns);
		}
		return listPiecesColumns;
	}
	
	private PredictOrderColumns transfer(PredictwaybillColumns objPWBColumns,
			List listCargoInfo) {
		PredictOrderColumns objPredictOrderColumns = new PredictOrderColumns();
		objPredictOrderColumns.setStrCwcustomerewbcode(objPWBColumns.getCwcwcustomerewbcode());
		objPredictOrderColumns.setStrCwpostcodedestination(objPWBColumns.getCwhwhwconsigneepostcode());
		objPredictOrderColumns.setStrDtcode(objPWBColumns.getCwdtocodedtcode());
		objPredictOrderColumns.setStrHwconsigneeaddress1(objPWBColumns.getCwhwhwconsigneeaddress1());
		objPredictOrderColumns.setStrHwconsigneeaddress2(objPWBColumns.getCwhwhwconsigneeaddress2());
		objPredictOrderColumns.setStrHwconsigneeaddress3(objPWBColumns.getCwhwhwconsigneeaddress3());
		objPredictOrderColumns.setStrHwconsigneename(objPWBColumns.getCwhwhwconsigneename());
		objPredictOrderColumns.setStrHwconsigneetelephone(objPWBColumns.getCwhwhwconsigneetelephone());
		objPredictOrderColumns.setStrHwremark(objPWBColumns.getCwhwhwremark());
		objPredictOrderColumns.setStrPk_code(objPWBColumns.getCwpkpkcode());
		objPredictOrderColumns.setStrCwgrossweight(objPWBColumns.getCwcwgrossweight());
		objPredictOrderColumns.setStrCwpieces(objPWBColumns.getCwcwpieces());
		objPredictOrderColumns.setStrCocode(objPWBColumns.getCwcocuscocode());
		objPredictOrderColumns.setStrAdddate(objPWBColumns.getCwcwcreatedate());
		objPredictOrderColumns.setStrCwpostcodedestination(objPWBColumns.getCwPostcodeDestination());
		
		if (listCargoInfo != null && listCargoInfo.size() > 0) {
			String[] astrCiname = new String[listCargoInfo.size()];
			String[] astrCiUnitprice = new String[listCargoInfo.size()];
			String[] astrCiPieces = new String[listCargoInfo.size()];
			for (int i = 0; i < listCargoInfo.size(); i++) {
				CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
				astrCiname[i] = objCargoinfoColumns.getCiciename();
				astrCiUnitprice[i] = objCargoinfoColumns.getCiciunitprice();
				astrCiPieces[i] = objCargoinfoColumns.getCicipieces();
			}
			objPredictOrderColumns.setStrCiename(astrCiname);
			objPredictOrderColumns.setStrCipieces(astrCiPieces);
			objPredictOrderColumns.setStrCiunitprice(astrCiUnitprice);
		}
		return objPredictOrderColumns;
	}

}
