package kyle.leis.eo.finance.audit.bl;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.tp.ModifyAuditStatusTrans;
import kyle.leis.fs.corewaybillauditlog.dax.AuditlogDemand;

public class Audit {
	public PromptUtilityCollection audit(String strCwcode, 
			String strOperId,
			boolean isReceiveAuditSign) throws Exception {
		PromptUtilityCollection objPUC = new PromptUtilityCollection();
		if (StringUtility.isNull(strCwcode)) {
			objPUC.add("E_001", "�˵�����Ϊ��", "Audit.audit");
			return objPUC;
		}
		// �Ƿ�����˵�
		SimplecorewaybillColumns objSCWBColumns = CorewaybillDemand.loadSimpleCorewaybill(strCwcode);
		if (objSCWBColumns == null) {
			objPUC.add("E_001", "�˵������ڣ��޷���ˣ�", "Audit.audit");
			return objPUC;			
		}
		// ֻ���Ӧ�����ж�Ӧ������
		if (!isReceiveAuditSign) {		
			boolean isExistsDraft = ReceivableDemand.isExistsDraftFee(strCwcode);
			if (isExistsDraft) {
				objPUC.add("E_001", "���ڲݸ��Ӧ�շ��ã���ȷ��֮������ˣ�", "Audit.audit");
				return objPUC;	
			}
			isExistsDraft = PayableDemand.isExistsDraftFee(strCwcode);
			if (isExistsDraft) {
				objPUC.add("E_001", "���ڲݸ��Ӧ�����ã���ȷ��֮������ˣ�", "Audit.audit");
				return objPUC;	
			}
		}
		// �Ƿ��Ѿ���˹�
		// �Ƿ���������־
		// ֻ���Ӧ�����ж�Ӧ������
		String strFascode = "PS";
		if (!isReceiveAuditSign) {
			List listAuditlog = AuditlogDemand.query(strCwcode);
			if (listAuditlog != null && listAuditlog.size() > 0)
				strFascode = "RP";		
			else {
				objPUC = checkFeeDetail(strCwcode);
				if (!objPUC.canGo(true))
					return objPUC;
			}
		}
		// �޸����״̬
		ModifyAuditStatusTrans objMASTrans = new ModifyAuditStatusTrans();
		objMASTrans.setParam(strCwcode, strFascode, strOperId);
		objMASTrans.execute();
		
		return objPUC;
	}
	
	
	private PromptUtilityCollection checkFeeDetail(String strCwcode) throws Exception {
		PromptUtilityCollection objPUC = new PromptUtilityCollection();
		// ��ѯӦ��Ӧ������
		BigDecimal objRvTotal = ReceivableDemand.sumActualTotal(strCwcode);
		BigDecimal objPyTotal = PayableDemand.sumActualTotal(strCwcode);
		if (objRvTotal.compareTo(new BigDecimal("0")) <= 0) {
			objPUC.add("E_001", "Ӧ�շ���С�ڵ���0���޷�ͨ�����", "Audit.audit");
			return objPUC;			
		}
		if (objPyTotal.equals(new BigDecimal("0"))) {
			objPUC.add("E_001", "Ӧ�����õ���0���޷�ͨ�����", "Audit.audit");
			return objPUC;			
		}
		if (objRvTotal.compareTo(objPyTotal) <= 0) {
			objPUC.add("E_001", "ë��С�ڵ���0���޷�ͨ�����", "Audit.audit");
			return objPUC;			
		}
		return objPUC;
	}
	
	/**
	 * �������
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void revokeAudit(String strCwcode, 
			String strOperId) throws Exception {
		ModifyAuditStatusTrans objMASTrans = new ModifyAuditStatusTrans();
		String strFasCode = "UA";
		objMASTrans.setParam(strCwcode, strFasCode, strOperId);	
		objMASTrans.execute();
	}
}
