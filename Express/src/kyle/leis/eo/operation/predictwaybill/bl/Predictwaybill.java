package kyle.leis.eo.operation.predictwaybill.bl;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.billing.calculate.feecalculate.bl.SaleTrialCalculate;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.calculate.feecalculate.dax.SaleTrialCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.SaleTrialCalculateResult;
import kyle.leis.eo.billing.receivable.bl.Receivable;
import kyle.leis.eo.finance.dunning.bl.Dunning;
import kyle.leis.eo.operation.corewaybill.dax.Corewaybillcode;
import kyle.leis.eo.operation.housewaybill.bl.Input;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderCheck;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.dax.PredictwaybillCheck;
import kyle.leis.eo.operation.predictwaybill.dax.PredictwaybillDemand;
import kyle.leis.eo.operation.predictwaybill.tp.ModifyPredictwaybillstatusTrans;
import kyle.leis.eo.operation.predictwaybill.tp.SavePredictwaybillTrans;
import kyle.leis.eo.operation.predictwaybill.tp.WithdrawPredictwaybillTrans;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiProductkind;

import com.ems.HKEMSInterface;

public class Predictwaybill {
	/** 入口易接口调用 **/
	private final boolean apiCall;
	
	public Predictwaybill() {
		this.apiCall = false;
	}
	
	public Predictwaybill(boolean apiCall) {
		this.apiCall = apiCall;
	}
	
	private PromptUtilityCollection m_objPUCollection;
	public SavedResultUtility save(PredictwaybillColumns objPredictwaybillColumns,
			List listCargoinfo,
			String strOperID) {
		SavedResultUtility objSRU = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		try {
			if(!StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_orderid()))
			       objPredictwaybillColumns.setPwbpwb_orderid(objPredictwaybillColumns.getPwbpwb_orderid().replace("?", ""));
			if(!StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_transactionid()))
			       objPredictwaybillColumns.setPwbpwb_transactionid(objPredictwaybillColumns.getPwbpwb_transactionid().replace("?", ""));
			objPUCollection = PredictwaybillCheck.check(objPredictwaybillColumns);
			if (!objPUCollection.canGo(false)) {
				objSRU.setPromptUtilityCollection(objPUCollection);
				return objSRU;
			}
			SavePredictwaybillTrans objSPWT = new SavePredictwaybillTrans();
			reBuildSavedColumns(objPredictwaybillColumns);
			objSPWT.setParam(objPredictwaybillColumns, listCargoinfo, strOperID);
			objSPWT.execute();
			objSRU.setColumns(objSPWT.getSavedPredictwaybillColumns());
			objSRU.setPromptUtilityCollection(objPUCollection);
		} catch (Exception ex) {
			ex.printStackTrace();
			objPUCollection.add("E_001", ex.getMessage(), "Predictwaybill.save");
			objSRU.setPromptUtilityCollection(objPUCollection);
		}
		return objSRU;
	}
	
	private void reBuildSavedColumns(PredictwaybillColumns objPredictwaybillColumns) throws Exception {
		// 重新设置目的国家、产品、渠道以及服务商号码
		if (!StringUtility.isNull(objPredictwaybillColumns.getPkpk_code())) {
			TdiProductkind objTPK = TdiProductkindDC.loadByKey(objPredictwaybillColumns.getPkpk_code());
			if (objTPK.getTchnChannel() != null)
				objPredictwaybillColumns.setChnchn_code(objTPK.getTchnChannel().getChnCode());
		}
	}
	
	public SavedResultUtility uploadFinance(String strPwbcode,
			String strOperID) {
		SavedResultUtility objSRU = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(strPwbcode)) {
			objPUCollection.add("E_001", "运单值为空，无法申报", "upload");
			objSRU.setPromptUtilityCollection(objPUCollection);
			return objSRU;
		}
		try {
			Input input = new Input();
			PredictwaybillColumns objPredictwaybillColumns = PredictwaybillDemand.load(strPwbcode);
			List listCargoinfo = PredictwaybillDemand.loadCargoinfo(strPwbcode);
			// 构建保存对象
			ForinputallColumns objFIAColumns = PredictwaybillDemand.buildForinputAll(objPredictwaybillColumns);
			List listCargo = PredictwaybillDemand.buildCargoinfo(objPredictwaybillColumns, listCargoinfo, objFIAColumns);
			List listWaybillPieces = PredictwaybillDemand.buildPiecesinfo(objPredictwaybillColumns);
			PredictwaybillDemand.buildChargeweightAndChannel(objFIAColumns, objPredictwaybillColumns);
			// 检查
			objPUCollection = PredictwaybillCheck.checkUpload(objPredictwaybillColumns);
			if (!objPUCollection.canGo(false)) {
				objSRU.setPromptUtilityCollection(objPUCollection);
				return objSRU;
			}			
			// sbd需要效验州名是否为空
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			if (objTchnChannel.getTdiServerstructuregroup() != null &&
					objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("AUEMS")) {
				String strStatecode = DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
						DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()), 
						DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
						objFIAColumns.getHwconsigneepostcode());
				if (StringUtility.isNull(strStatecode)) {
					objPUCollection.add("E_001", "州名为空，无法上传", "upload");
					objSRU.setPromptUtilityCollection(objPUCollection);
					return objSRU;
				}
			}
			// 判断余额
			// 余额大于0则获取服务商单号
			PromptUtilityCollection objPU = checkFinance(objFIAColumns, listWaybillPieces);
			if (!objPU.canGo(false)) {
				objSRU.setPromptUtilityCollection(objPU);
				return objSRU;
			}
			// 设置服务商单号	
			// 优先通过service获取，返回值为负数表示渠道不能通过service的方式来获取，必须用system
			if (setWaybillcodeByService(objFIAColumns, listWaybillPieces, listCargo) < 0) {
				Corewaybillcode objCorewaybillcode = new Corewaybillcode();
				objPUCollection = objCorewaybillcode.setNewWaybillcodeBySystem(objFIAColumns,
						listWaybillPieces,
						objFIAColumns.getChncode_Cwspchn(),
						strOperID);
				if (!objPUCollection.canGo(false)) {
					objSRU.setPromptUtilityCollection(objPUCollection);
					return objSRU;
				}
			} else {
				if (m_objPUCollection != null && !m_objPUCollection.canGo(false)) {
					m_objPUCollection.setAllError();
					objSRU.setPromptUtilityCollection(m_objPUCollection);
					return objSRU;
				}
			}
			// 换单成功
			if (!StringUtility.isNull(objFIAColumns.getServerwbckbckcode()) && 
					StringUtility.isNull(objFIAColumns.getHwbookingid())) {
				objFIAColumns.setCwewbcode(objFIAColumns.getCwserverewbcode());
			}
			input.inputAllForPredict(strOperID, 
					objFIAColumns, 
					listCargo, 
					listWaybillPieces, 
					objPredictwaybillColumns);		
			objSRU.setColumns(objPredictwaybillColumns);
			objSRU.setPromptUtilityCollection(objPUCollection);		
			
			// 计费
			AutoFeeCalculateThread objAFCT = new AutoFeeCalculateThread(objPredictwaybillColumns.getPwbcw_code(),
					IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
					false);
			if (apiCall) {
				objAFCT.setSyncCustomerBalance(objPredictwaybillColumns.getCoco_code());
				objAFCT.start();
			} else {
				objAFCT.run();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			objPUCollection.add("E_001", ex.getMessage(), "Predictwaybill.upload");
			objSRU.setPromptUtilityCollection(objPUCollection);
		}
		return objSRU;
	}	
	
	private PromptUtilityCollection checkFinance(ForinputallColumns objFIAColumns,
			List listWaybillPieces) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		SaleTrialCalculateParameter objSTCP = new SaleTrialCalculateParameter();
		
		objSTCP.setPkcode(objFIAColumns.getPk_code());
		objSTCP.setCocode(CustomerDemand.getTopParentCustomer(objFIAColumns.getCocode()));
		objSTCP.setCtcode(objFIAColumns.getCtcode());
		objSTCP.setPmcode(objFIAColumns.getPmcode());
		objSTCP.setOrginDtcode(objFIAColumns.getDtcode_Cwodt());
		objSTCP.setDestDtcode(objFIAColumns.getDtcode());
		objSTCP.setGrossweight(objFIAColumns.getCwchargeweight());
		objSTCP.setPostcode(objFIAColumns.getCwpostcodedestination());
		objSTCP.setPiecesList(listWaybillPieces);
		
		String totalPriceValue = "0";
		if (!apiCall) {
			SaleTrialCalculate objSaleTrialCalculate = new SaleTrialCalculate();
			List<SaleTrialCalculateResult> listResults = objSaleTrialCalculate.calculate(objSTCP);
			// 试算金额
			if (listResults == null || listResults.size() < 1) {
				objPUCollection.add("E_001", "未计算出此票件的费用，无法上传", "upload");
				return objPUCollection;
			}
			// 获得金额值
			SaleTrialCalculateResult objSTCR = listResults.get(0);
			totalPriceValue = objSTCR.getTotalpricevalue();
			if (StringUtility.isNull(totalPriceValue)) {
				objPUCollection.add("E_001", "未计算出此票件的费用，无法上传", "upload");
				return objPUCollection;			
			}
		}
		// 判断余额
		Dunning objDunning = new Dunning();
		String strCreditBalance = objDunning.getCreditbalance(CustomerDemand.getTopParentCustomer(objFIAColumns.getCocode()));
		BigDecimal objCreditBalance = new BigDecimal(strCreditBalance).add(new BigDecimal(totalPriceValue).multiply(new BigDecimal("-1")));
		if (objCreditBalance.compareTo(new BigDecimal("0")) < 0) {
			objPUCollection.add("E_001", "余额不足，无法上传", "upload");
			// 作废原运单
			return objPUCollection;
		}
		return objPUCollection;
	}
	
	
	public SavedResultUtility upload(String strPwbcode,
			String strOperID) {
		SavedResultUtility objSRU = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(strPwbcode)) {
			objPUCollection.add("E_001", "运单值为空，无法申报", "upload");
			objSRU.setPromptUtilityCollection(objPUCollection);
			return objSRU;
		}
		try {
			Input input = new Input();
			PredictwaybillColumns objPredictwaybillColumns = PredictwaybillDemand.load(strPwbcode);
			// 构建保存对象
			ForinputallColumns objFIAColumns = PredictwaybillDemand.buildForinputAll(objPredictwaybillColumns);
			List listCargo = PredictwaybillDemand.buildCargoinfo(objPredictwaybillColumns, objFIAColumns);
			List listWaybillPieces = PredictwaybillDemand.buildPiecesinfo(objPredictwaybillColumns);
			PredictwaybillDemand.buildChargeweightAndChannel(objFIAColumns, objPredictwaybillColumns);
			// 检查
			objPUCollection = PredictwaybillCheck.checkUpload(objPredictwaybillColumns);
			if (!objPUCollection.canGo(false)) {
				objSRU.setPromptUtilityCollection(objPUCollection);
				return objSRU;
			}			
			// 效验
			PredictOrderCheck objPOC = new PredictOrderCheck();
			String strErrorInfo = objPOC.checkPostAndCity(objFIAColumns, listCargo);
			if (!StringUtility.isNull(strErrorInfo)) {
				PromptUtilityCollection objPU = new PromptUtilityCollection();
				objPU.add("E_001", strErrorInfo, "save");
				objSRU.setPromptUtilityCollection(objPU);
				return objSRU;
			}
			// sbd需要效验州名是否为空
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			if (objTchnChannel.getTdiServerstructuregroup() != null &&
					objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("AUEMS")) {
				String strStatecode = DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
						DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()), 
						DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
						objFIAColumns.getHwconsigneepostcode());
				if (StringUtility.isNull(strStatecode)) {
					objPUCollection.add("E_001", "州名为空，无法上传", "upload");
					objSRU.setPromptUtilityCollection(objPUCollection);
					return objSRU;
				}
			}
			// 设置服务商单号	
			// 优先通过service获取，返回值为负数表示渠道不能通过service的方式来获取，必须用system
			if (setWaybillcodeByService(objFIAColumns, listWaybillPieces, listCargo) < 0) {
				Corewaybillcode objCorewaybillcode = new Corewaybillcode();
				objPUCollection = objCorewaybillcode.setNewWaybillcodeBySystem(objFIAColumns,
						listWaybillPieces,
						objFIAColumns.getChncode_Cwspchn(),
						strOperID);
				if (!objPUCollection.canGo(false)) {
					objSRU.setPromptUtilityCollection(objPUCollection);
					return objSRU;
				}
			} else {
				if (m_objPUCollection != null && !m_objPUCollection.canGo(false)) {
					m_objPUCollection.setAllError();
					objSRU.setPromptUtilityCollection(m_objPUCollection);
					return objSRU;
				}
			}
			// 换单成功
			if (!StringUtility.isNull(objFIAColumns.getServerwbckbckcode()) && 
					StringUtility.isNull(objFIAColumns.getHwbookingid())) {
				objFIAColumns.setCwewbcode(objFIAColumns.getCwserverewbcode());
			}
			input.inputAllForPredict(strOperID, 
					objFIAColumns, 
					listCargo, 
					listWaybillPieces, 
					objPredictwaybillColumns);			
			objSRU.setColumns(objPredictwaybillColumns);
			objSRU.setPromptUtilityCollection(objPUCollection);
		} catch (Exception ex) {
			ex.printStackTrace();
			objPUCollection.add("E_001", ex.getMessage(), "Predictwaybill.upload");
			objSRU.setPromptUtilityCollection(objPUCollection);
		}
		return objSRU;
	}
	
	private int setWaybillcodeByService(ForinputallColumns objFIAColumns,
			List listWaybillPieces,
			List listCargo) throws Exception {
		if (StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) 
			return -1;
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
		/*
		if (objTchnChannel.getTdiServerstructuregroup() == null ||
				(!objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("AUEMS") &&
						!objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("EMSIM")))
			return -1;
		*/
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() == null)
			return -1;
		Corewaybillcode objCorewaybillcode = new Corewaybillcode();
		HousewaybillColumns objHWBColumns = new HousewaybillColumns();
		m_objPUCollection = objCorewaybillcode.setWaybillcodeByService(objFIAColumns, 
				listWaybillPieces, 
				listCargo, 
				objHWBColumns, 
				objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckCode(), 
				"_E", 
				"", 
				objTchnChannel.getChnMasteraccount());
		if (m_objPUCollection == null)
			return -1;
		return 0;
	}	
	
	public SavedResultUtility withdraw(String strPwbcode,
			String strOperID,
			boolean isLoadByCwcode) {
		SavedResultUtility objSRU = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(strPwbcode)) {
			objPUCollection.add("E_001", "运单值为空，无法删除或者撤销", "delete");
			objSRU.setPromptUtilityCollection(objPUCollection);
			return objSRU;
		}
		try {
			PredictwaybillColumns objPredictwaybillColumns = null;
			if (isLoadByCwcode)
				objPredictwaybillColumns = PredictwaybillDemand.loadBycwcode(strPwbcode);
			else
				objPredictwaybillColumns = PredictwaybillDemand.load(strPwbcode);
			WithdrawPredictwaybillTrans objWPWTrans = new WithdrawPredictwaybillTrans();
			// 原数据为暂存则只需要删除预报数据表
			if (objPredictwaybillColumns.getPwbspwbs_code().equals("CTS")) {
				objWPWTrans.setParam(objPredictwaybillColumns, "CEL", strOperID);
				objWPWTrans.execute();
			} else if (objPredictwaybillColumns.getPwbspwbs_code().equals("CHU") || 
					objPredictwaybillColumns.getPwbspwbs_code().equals("CHP")) {
				// 香港进口系统需要调用接口并作废费用
				objPUCollection = cancelInterface(objPredictwaybillColumns);
				if (objPUCollection != null && !objPUCollection.canGo(false)) {
					objSRU.setPromptUtilityCollection(objPUCollection);
					return objSRU;
				}
				objWPWTrans.setParam(objPredictwaybillColumns, "CTS", strOperID);
				objWPWTrans.execute();
			}
			objSRU.setColumns(objPredictwaybillColumns);
			objSRU.setPromptUtilityCollection(objPUCollection);
		} catch (Exception ex) {
			ex.printStackTrace();
			objPUCollection.add("E_001", ex.getMessage(), "Predictwaybill.withdraw");
			objSRU.setPromptUtilityCollection(objPUCollection);
		}
		return objSRU;
	}
	
	private PromptUtilityCollection cancelInterface(PredictwaybillColumns objPredictwaybillColumns) 
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(objPredictwaybillColumns.getChnchn_code()) ||
				StringUtility.isNull(objPredictwaybillColumns.getPwbcw_code()))
			return objPUCollection;
		
		// 删除应收费用
		Receivable objReceivable = new Receivable();
		objReceivable.deleteAll(objPredictwaybillColumns.getPwbcw_code(), "0");		
		
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objPredictwaybillColumns.getChnchn_code());
		if (objTchnChannel.getTdiServerstructuregroup() != null && 
				objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("EMSIM")) {
			HKEMSInterface objHKEMSInterface = new HKEMSInterface();
			return objHKEMSInterface.cancel(objPredictwaybillColumns.getPwbpwb_serverewbcode());
		}
		return objPUCollection;
	}
	
	public SavedResultUtility print(String strPwbcode,
			String strOperID) {
		SavedResultUtility objSRU = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(strPwbcode)) {
			objPUCollection.add("E_001", "运单值为空，无法打印", "print");
			objSRU.setPromptUtilityCollection(objPUCollection);
			return objSRU;
		}
		try {
			ModifyPredictwaybillstatusTrans objMPWSTrans = new ModifyPredictwaybillstatusTrans();
			objMPWSTrans.setParam(strPwbcode, "CHP", strOperID);
			objMPWSTrans.execute();
			objSRU.setPromptUtilityCollection(objPUCollection);
		} catch (Exception ex) {
			ex.printStackTrace();
			objPUCollection.add("E_001", ex.getMessage(), "Predictwaybill.withdraw");
			objSRU.setPromptUtilityCollection(objPUCollection);
		}
		return objSRU;
	}	
	
	
}
