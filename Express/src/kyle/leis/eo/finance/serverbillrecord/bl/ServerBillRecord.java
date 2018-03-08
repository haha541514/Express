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
	 * ����
	 * @param objSBRColumns
	 * @param listSaveBilldetail
	 * @param strOperId
	 */
	public ServerbillrecordColumns save(ServerbillrecordColumns objSBRColumns,
			List listServerPayable,
			String strOperId) throws Exception {
		// �޸��˵�ʱ����ɾ���˵���ϸ
		// Ϊ��֧���˵��޸�ʱ��ͬ�˵��ڲ�ͬ���˵��ϣ�����ɾ���ͱ��治����ͬһ��������
		// �����ѯ����ͬ�˵���ͬ�����Ƿ����ʱ���þ������ѯ������Զ��������ͬ�ķ���
		if (!StringUtility.isNull(objSBRColumns.getSbrsbrid())) {
			delete(objSBRColumns.getSbrsbrid());
			objSBRColumns.setSbrsbrid(null);
			// DeleteServerPayableTrans objDSPTrans = new DeleteServerPayableTrans();
			// objDSPTrans.setParam(objSBRColumns.getSbrsbrid());
			// objDSPTrans.execute();			
		}
		// ����
		SaveTransaction objSaveTrans = new SaveTransaction();
		objSaveTrans.setParam(objSBRColumns, 
				listServerPayable, 
				strOperId);
		objSaveTrans.execute();
		String strSavedSbrId = objSaveTrans.getSavedSbrId(); 
		// �޸��˵����
		ModifyTotalchargeTrans objMTCTrans = new ModifyTotalchargeTrans();
		objMTCTrans.setParam(strSavedSbrId, listServerPayable);
		objMTCTrans.execute();
		// ����ֵ
		return ServerBillRecordDemand.load(strSavedSbrId);
	}
	
	/**
	 * ���˵��ϵķ����������޸�ϵͳ�еķ���������
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
			// ����������
			String strServerChargeweight = objDDColumns.getSwbswb_chargeweight();
			if (StringUtility.isNull(strServerChargeweight))
				strServerChargeweight = "0";
			BigDecimal objServerChargeweight = new BigDecimal(strServerChargeweight);
			if (objServerChargeweight.compareTo(new BigDecimal("0")) <= 0) continue;
			// �޸ķ���������
			alRecalculate.add(strCwcode);
			ModifyServerchargeweightTrans objMSCWTrans = new ModifyServerchargeweightTrans();
			objMSCWTrans.setParam(strCwcode, 
					strServerChargeweight, 
					strOperId);
			objMSCWTrans.execute();
			// ���¼Ʒ���ǰ̨���̴߳���
		}
	}
	
	/**
	 * ���˵��ϵķ����������޸�ϵͳ�еļƷ�����
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
			// �˵��еķ���������
			String strServerChargeweight = objCWDColumns.getSwbswb_chargeweight();
			if (StringUtility.isNull(strServerChargeweight))
				strServerChargeweight = "0";
			BigDecimal objServerChargeweight = new BigDecimal(strServerChargeweight);
			if (objServerChargeweight.compareTo(new BigDecimal("0")) <= 0) continue;
			// �޸��ջ�����
			//alRecalculate.add(strCwcode);
			ModifyChargeweightTrans objMCWTrans = new ModifyChargeweightTrans();
			objMCWTrans.setParam(strCwcode, 
					strServerChargeweight, 
					strOperId);
			objMCWTrans.execute();
			// ���¼Ʒ���ǰ̨���̴߳���
		}
	}
	
	/**
	 * ���ڷ��ò�����˵����ܷ����̷���
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
	 * ���ڷ��ò�����˵����ܷ����̷���
	 * ����Ϊ��Ʊ���ķ���
	 * @param objDDColumns
	 * @param strOperId
	 * @throws Exception
	 */
	public void acceptSeverCharge(List listServerPayable, 
			String strOperId) throws Exception {
		if (listServerPayable == null || listServerPayable.size() < 1)
			return;
		// ���ܴ��ڶ����ͬ�ķ����̵��ţ������������ֶ��cwcode�����
		HashMap<String, List> hmServerPayable = parseServerPayable(listServerPayable);
		for (String strKey : hmServerPayable.keySet()) {
			listServerPayable = hmServerPayable.get(strKey);
			ServerpayableColumns objServerpayableColumns = (ServerpayableColumns)listServerPayable.get(0);
			// ԭʼ����
			String strCwcode = objServerpayableColumns.getSwbswbreferencecode();
			List listOriginPayableColumns = PayableDemand.load(strCwcode, "A0201");
			ServerwaybillColumns objSWBColumns = ServerBillRecordDemand.loadServerwaybill(objServerpayableColumns.getSwbswbserverewbcode(),
					objServerpayableColumns.getChnchncode(),
					strCwcode);
			// �������˵��ϵķ���
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
			// �������
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
	 * �ϲ���ͬ�����������������Ƶķ�����Ŀ
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
			// �ϲ�����
			if (hmExists.containsKey(strKey)) {
				ServerpayableColumns objExistsSPYColumns = hmExists.get(strKey);
				if (!objExistsSPYColumns.getCkckcode().equals(strCkcode))
					throw (new Exception("���������ơ���������һ��ʱ������ͬʱ���ڶ������ʲ�ͬ�ķ�����Ŀ"));
				BigDecimal objExistsTotalCharge = new BigDecimal(objExistsSPYColumns.getSpyspytotalcharge());
				BigDecimal objTotalCharge = new BigDecimal(objServerpayableColumns.getSpyspytotalcharge());
				objExistsSPYColumns.setSpyspytotalcharge(objTotalCharge.add(objExistsTotalCharge));
			} else {
				hmExists.put(strKey, objServerpayableColumns);
			}
		}
		// ����ֵ
		Iterator<String> objIterator = hmExists.keySet().iterator();
		while (objIterator.hasNext()) {
			String strKey = objIterator.next();
			listMergeResult.add(hmExists.get(strKey));
		}
		return listMergeResult;
	}
	
	
	/**
	 * ȷ���˵�
	 * @param strSbrId
	 * @param strOperId
	 * @throws Exception
	 */
	public BillrecordColumns confirm(String strSbrId, 
			String strOperId,
			boolean isModifyServerBillStatus) throws Exception {
		// ��ѯ�����ڷ��ò���ķ����̷��ò�������Ӧ�����õĹ�ϵ
		List listChargeEqual = ServerBillRecordDemand.queryChargeDifference(strSbrId, true);
		ModifyServerPayableTrans objMSPTrans = new ModifyServerPayableTrans();
		objMSPTrans.setParam(listChargeEqual);
		objMSPTrans.execute();
		// �޸ķ������˵���״̬
		if (isModifyServerBillStatus) {
			ModifyStatusTransaction objMSTrans = new ModifyStatusTransaction();
			objMSTrans.setParam(strSbrId, strOperId, "ON");
			objMSTrans.execute();
		}
		// ���ɶ�Ӧ�ķ������˵�
		ServerbillrecordColumns objSBRColumns = ServerBillRecordDemand.load(strSbrId);
		BillrecordColumns objBillrecordColumns = BillRecordDemand.buildByServerBillrecord(objSBRColumns);
		// ��ѯ�����̷���
		PayableforbillCondition objPFBCondition = new PayableforbillCondition();
		objPFBCondition.setSbrid(strSbrId);
		objPFBCondition.setFscode("C");
		objPFBCondition.setCocode(objSBRColumns.getCococode());
		objPFBCondition.setCkcode(objSBRColumns.getCkckcode());
		List listPayableForbill = PayableDemand.queryForBill(objPFBCondition);
		List<PayableColumns> listPayable = PayableDemand.transferBillPyToPyColumns(listPayableForbill);
		// ���ɷ������˵�
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
	 * ɾ��
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
