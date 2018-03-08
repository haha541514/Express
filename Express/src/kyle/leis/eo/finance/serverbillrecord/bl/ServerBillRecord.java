package kyle.leis.eo.finance.serverbillrecord.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.da.PayableforbillCondition;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.payable.tp.ModifyServerPayableTrans;
import kyle.leis.eo.billing.payable.tp.SavePayableTrans;
import kyle.leis.eo.finance.billrecord.bl.BillRecord;
import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;
import kyle.leis.eo.finance.billrecord.dax.BillRecordDemand;
import kyle.leis.eo.finance.serverbillrecord.da.ChargeweightdifferenceColumns;
import kyle.leis.eo.finance.serverbillrecord.da.DifferencedetailColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableCondition;
import kyle.leis.eo.finance.serverbillrecord.da.ServerwaybillColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;
import kyle.leis.eo.finance.serverbillrecord.tp.DeleteServerPayableTrans;
import kyle.leis.eo.finance.serverbillrecord.tp.DeleteServerwaybillTrans;
import kyle.leis.eo.finance.serverbillrecord.tp.ModifyStatusTransaction;
import kyle.leis.eo.finance.serverbillrecord.tp.ModifyTotalchargeTrans;
import kyle.leis.eo.finance.serverbillrecord.tp.SaveTransaction;
import kyle.leis.eo.finance.serverbillrecord.tp.ModifyOwninputauditTransation;
import kyle.leis.eo.operation.corewaybill.tp.ModifyChargeweightTrans;
import kyle.leis.eo.operation.corewaybill.tp.ModifyServerchargeweightTrans;

public class ServerBillRecord {
	/**
	 * 保存
	 * @param objSBRColumns
	 * @param listSaveBilldetail
	 * @param strOperId
	 */
	public ServerbillrecordColumns save(ServerbillrecordColumns objSBRColumns,
			List listServerPayable,
			String strOperId) throws Exception {
		// 修改账单时候先删除账单明细
		// 为了支持账单修改时相同运单在不同的账单上，所以删除和保存不放在同一个事务中
		// 否则查询是相同运单相同费用是否存在时，用旧事务查询出来永远都存在相同的费用
		if (!StringUtility.isNull(objSBRColumns.getSbrsbrid())) {
			delete(objSBRColumns.getSbrsbrid());
			objSBRColumns.setSbrsbrid(null);
			// DeleteServerPayableTrans objDSPTrans = new DeleteServerPayableTrans();
			// objDSPTrans.setParam(objSBRColumns.getSbrsbrid());
			// objDSPTrans.execute();			
		}
		// 保存
		SaveTransaction objSaveTrans = new SaveTransaction();
		objSaveTrans.setParam(objSBRColumns, 
				listServerPayable, 
				strOperId);
		objSaveTrans.execute();
		String strSavedSbrId = objSaveTrans.getSavedSbrId(); 
		// 修改账单金额
		ModifyTotalchargeTrans objMTCTrans = new ModifyTotalchargeTrans();
		objMTCTrans.setParam(strSavedSbrId, listServerPayable);
		objMTCTrans.execute();
		// 返回值
		return ServerBillRecordDemand.load(strSavedSbrId);
	}
	
	/**
	 * 用账单上的服务商重量修改系统中的服务商重量
	 * @param listDifferenceChargeweight
	 * @param strOperId
	 * @throws Exception
	 */
	public void acceptServerChargeweight(List listSCWDifference,
			String strOperId) throws Exception {
		if (listSCWDifference == null || 
				listSCWDifference.size() < 1)
			return;
		ArrayList<String> alRecalculate = new ArrayList<String>();
		for (int i = 0; i < listSCWDifference.size(); i++) {
			DifferencedetailColumns objDDColumns = (DifferencedetailColumns)listSCWDifference.get(i);
			String strCwcode = objDDColumns.getCwcw_code();
			if (StringUtility.isNull(strCwcode)) continue;
			// 服务商重量
			String strServerChargeweight = objDDColumns.getSwbswb_chargeweight();
			if (StringUtility.isNull(strServerChargeweight))
				strServerChargeweight = "0";
			BigDecimal objServerChargeweight = new BigDecimal(strServerChargeweight);
			if (objServerChargeweight.compareTo(new BigDecimal("0")) <= 0) continue;
			// 修改服务商重量
			alRecalculate.add(strCwcode);
			ModifyServerchargeweightTrans objMSCWTrans = new ModifyServerchargeweightTrans();
			objMSCWTrans.setParam(strCwcode, 
					strServerChargeweight, 
					strOperId);
			objMSCWTrans.execute();
			// 重新计费由前台多线程处理
		}
	}
	
	/**
	 * 用账单上的服务商重量修改系统中的计费重量
	 * @param listCWDifference
	 * @param strOperId
	 * @throws Exception
	 */
	public void acceptChargeweight(List listCWDifference,
			String strOperId) throws Exception {
		if (listCWDifference == null || listCWDifference.size() < 1)
			return;
		//ArrayList<String> alRecalculate = new ArrayList<String>();
		for (int i = 0; i < listCWDifference.size(); i++) {
			ChargeweightdifferenceColumns objCWDColumns = (ChargeweightdifferenceColumns)listCWDifference.get(i);
			String strCwcode = objCWDColumns.getCwcw_code();
			if (StringUtility.isNull(strCwcode)) continue;
			// 账单中的服务商重量
			String strServerChargeweight = objCWDColumns.getSwbswb_chargeweight();
			if (StringUtility.isNull(strServerChargeweight))
				strServerChargeweight = "0";
			BigDecimal objServerChargeweight = new BigDecimal(strServerChargeweight);
			if (objServerChargeweight.compareTo(new BigDecimal("0")) <= 0) continue;
			// 修改收货重量
			//alRecalculate.add(strCwcode);
			ModifyChargeweightTrans objMCWTrans = new ModifyChargeweightTrans();
			objMCWTrans.setParam(strCwcode, 
					strServerChargeweight, 
					strOperId);
			objMCWTrans.execute();
			// 重新计费由前台多线程处理
		}
	}
	
	/**
	 * 存在费用差异的运单接受服务商费用
	 * @param strServerEwbcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void acceptSeverCharge(String strCwcode, 
			String strOperId) throws Exception {
		ServerpayableCondition objSPYCondition = new ServerpayableCondition();
		objSPYCondition.setSbdreferencecode(strCwcode);
		List listServerPayable = ServerBillRecordDemand.queryPayable(objSPYCondition);
		acceptSeverCharge(listServerPayable, strOperId);
	}
	
	private HashMap<String, List> parseServerPayable(List listServerPayable) {
		HashMap<String, List> hmServerPayable = new HashMap<String, List>();
		for (int i = 0; i < listServerPayable.size(); i++) {
			ServerpayableColumns objServerpayableColumns = (ServerpayableColumns)listServerPayable.get(i);
			List<ServerpayableColumns> listParsedServerPayable = new ArrayList<ServerpayableColumns>();
			if (hmServerPayable.containsKey(objServerpayableColumns.getSwbswbreferencecode())) {
				listParsedServerPayable = hmServerPayable.get(objServerpayableColumns.getSwbswbreferencecode());
				listParsedServerPayable.add(objServerpayableColumns);
			} else {
				listParsedServerPayable.add(objServerpayableColumns);
				hmServerPayable.put(objServerpayableColumns.getSwbswbreferencecode(), listParsedServerPayable);
			}		
		}
		return hmServerPayable;
	}
	
	/**
	 * 存在费用差异的运单接受服务商费用
	 * 集合为单票件的费用
	 * @param objDDColumns
	 * @param strOperId
	 * @throws Exception
	 */
	public void acceptSeverCharge(List listServerPayable, 
			String strOperId) throws Exception {
		if (listServerPayable == null || listServerPayable.size() < 1)
			return;
		// 可能存在多个相同的服务商单号，所以这里会出现多个cwcode的情况
		HashMap<String, List> hmServerPayable = parseServerPayable(listServerPayable);
		for (String strKey : hmServerPayable.keySet()) {
			listServerPayable = hmServerPayable.get(strKey);
			ServerpayableColumns objServerpayableColumns = (ServerpayableColumns)listServerPayable.get(0);
			// 原始费用
			String strCwcode = objServerpayableColumns.getSwbswbreferencecode();
			List listOriginPayableColumns = PayableDemand.load(strCwcode, "A0201");
			ServerwaybillColumns objSWBColumns = ServerBillRecordDemand.loadServerwaybill(objServerpayableColumns.getSwbswbserverewbcode(),
					objServerpayableColumns.getChnchncode(),
					strCwcode);
			// 服务商账单上的费用
			List<PayableColumns> listPayable = new ArrayList<PayableColumns>();
			List<ServerpayableColumns> listMergedResult = merge(listServerPayable);
			for (int i = 0; i < listMergedResult.size(); i++) {
				objServerpayableColumns = (ServerpayableColumns)listMergedResult.get(i);
				objServerpayableColumns.getFkfkcode();
				objServerpayableColumns.getChnchncode();
				PayableColumns objPayableColumns = ServerBillRecordDemand.buildByServerPayable(objServerpayableColumns,
						objSWBColumns,
						strOperId);
				listPayable.add(objPayableColumns);
			}
			// 保存费用
			SavePayableTrans objSavePayableTrans = new SavePayableTrans();
			objSavePayableTrans.setParam(listPayable, 
					strCwcode, 
					strOperId, 
					true, 
					listOriginPayableColumns);
			objSavePayableTrans.execute();
		}
	}
	
	/**
	 * 合并相同服务渠道、费用名称的费用项目
	 * @param listServerPayable
	 * @return
	 * @throws Exception
	 */
	private List<ServerpayableColumns> merge(List listServerPayable) throws Exception {
		List<ServerpayableColumns> listMergeResult = new ArrayList<ServerpayableColumns>();
		HashMap<String, ServerpayableColumns> hmExists = new HashMap<String, ServerpayableColumns>();
		for (int i = 0; i < listServerPayable.size(); i++) {
			ServerpayableColumns objServerpayableColumns = (ServerpayableColumns)listServerPayable.get(i);
			String strFkcode = objServerpayableColumns.getFkfkcode();
			String strChncode = objServerpayableColumns.getChnchncode();
			String strCkcode = objServerpayableColumns.getCkckcode();
			String strKey = strFkcode + "||" + strChncode;
			// 合并费用
			if (hmExists.containsKey(strKey)) {
				ServerpayableColumns objExistsSPYColumns = hmExists.get(strKey);
				if (!objExistsSPYColumns.getCkckcode().equals(strCkcode))
					throw (new Exception("当费用名称、服务渠道一致时，不能同时存在多条汇率不同的费用项目"));
				BigDecimal objExistsTotalCharge = new BigDecimal(objExistsSPYColumns.getSpyspytotalcharge());
				BigDecimal objTotalCharge = new BigDecimal(objServerpayableColumns.getSpyspytotalcharge());
				objExistsSPYColumns.setSpyspytotalcharge(objTotalCharge.add(objExistsTotalCharge));
			} else {
				hmExists.put(strKey, objServerpayableColumns);
			}
		}
		// 返回值
		Iterator<String> objIterator = hmExists.keySet().iterator();
		while (objIterator.hasNext()) {
			String strKey = objIterator.next();
			listMergeResult.add(hmExists.get(strKey));
		}
		return listMergeResult;
	}
	
	
	/**
	 * 确定账单
	 * @param strSbrId
	 * @param strOperId
	 * @throws Exception
	 */
	public BillrecordColumns confirm(String strSbrId, 
			String strOperId,
			boolean isModifyServerBillStatus) throws Exception {
		// 查询不存在费用差异的服务商费用并建立跟应付费用的关系
		List listChargeEqual = ServerBillRecordDemand.queryChargeDifference(strSbrId, true);
		ModifyServerPayableTrans objMSPTrans = new ModifyServerPayableTrans();
		objMSPTrans.setParam(listChargeEqual);
		objMSPTrans.execute();
		// 修改服务商账单的状态
		if (isModifyServerBillStatus) {
			ModifyStatusTransaction objMSTrans = new ModifyStatusTransaction();
			objMSTrans.setParam(strSbrId, strOperId, "ON");
			objMSTrans.execute();
		}
		// 生成对应的服务商账单
		ServerbillrecordColumns objSBRColumns = ServerBillRecordDemand.load(strSbrId);
		BillrecordColumns objBillrecordColumns = BillRecordDemand.buildByServerBillrecord(objSBRColumns);
		// 查询服务商费用
		PayableforbillCondition objPFBCondition = new PayableforbillCondition();
		objPFBCondition.setSbrid(strSbrId);
		objPFBCondition.setFscode("C");
		objPFBCondition.setCocode(objSBRColumns.getCococode());
		objPFBCondition.setCkcode(objSBRColumns.getCkckcode());
		List listPayableForbill = PayableDemand.queryForBill(objPFBCondition);
		List<PayableColumns> listPayable = PayableDemand.transferBillPyToPyColumns(listPayableForbill);
		// 生成服务商账单
		BillRecord objBillRecord = new BillRecord();
		BillrecordColumns objBRColumns = objBillRecord.saveByFee(objBillrecordColumns, 
				null, 
				listPayable, 
				null,
				strOperId,
				true);
		return objBRColumns;
	}
	
	/**
	 * 删除
	 */
	
	public void delete(String strSbrId) throws Exception{
		List listWaybill = ServerBillRecordDemand.queryWaybill(strSbrId);
		
		if (!StringUtility.isNull(strSbrId)) {
			DeleteServerPayableTrans objDSPTrans = new DeleteServerPayableTrans();
			objDSPTrans.setParam(strSbrId);
			objDSPTrans.execute();
		}	
		
		if(listWaybill == null)
			return ;	
		DeleteServerwaybillTrans objDSWBTrans = new DeleteServerwaybillTrans();
		objDSWBTrans.setParam(strSbrId,listWaybill);
		objDSWBTrans.execute();
	}
	
	public void modifyOwninputaudit(String cwCode,String ownInputAudit) throws Exception{
		ModifyOwninputauditTransation objUpdateSignTransation=new ModifyOwninputauditTransation();
		objUpdateSignTransation.setParam(cwCode,ownInputAudit);
		objUpdateSignTransation.execute();	
	}
}
