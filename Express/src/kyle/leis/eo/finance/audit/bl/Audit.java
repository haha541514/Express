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
			objPUC.add("E_001", "运单不能为空", "Audit.audit");
			return objPUC;
		}
		// 是否存在运单
		SimplecorewaybillColumns objSCWBColumns = CorewaybillDemand.loadSimpleCorewaybill(strCwcode);
		if (objSCWBColumns == null) {
			objPUC.add("E_001", "运单不存在，无法审核！", "Audit.audit");
			return objPUC;			
		}
		// 只审核应收则不判断应付费用
		if (!isReceiveAuditSign) {		
			boolean isExistsDraft = ReceivableDemand.isExistsDraftFee(strCwcode);
			if (isExistsDraft) {
				objPUC.add("E_001", "存在草稿的应收费用，请确定之后再审核！", "Audit.audit");
				return objPUC;	
			}
			isExistsDraft = PayableDemand.isExistsDraftFee(strCwcode);
			if (isExistsDraft) {
				objPUC.add("E_001", "存在草稿的应付费用，请确定之后再审核！", "Audit.audit");
				return objPUC;	
			}
		}
		// 是否已经审核过
		// 是否存在审核日志
		// 只审核应收则不判断应付费用
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
		// 修改审核状态
		ModifyAuditStatusTrans objMASTrans = new ModifyAuditStatusTrans();
		objMASTrans.setParam(strCwcode, strFascode, strOperId);
		objMASTrans.execute();
		
		return objPUC;
	}
	
	
	private PromptUtilityCollection checkFeeDetail(String strCwcode) throws Exception {
		PromptUtilityCollection objPUC = new PromptUtilityCollection();
		// 查询应收应付费用
		BigDecimal objRvTotal = ReceivableDemand.sumActualTotal(strCwcode);
		BigDecimal objPyTotal = PayableDemand.sumActualTotal(strCwcode);
		if (objRvTotal.compareTo(new BigDecimal("0")) <= 0) {
			objPUC.add("E_001", "应收费用小于等于0，无法通过审核", "Audit.audit");
			return objPUC;			
		}
		if (objPyTotal.equals(new BigDecimal("0"))) {
			objPUC.add("E_001", "应付费用等于0，无法通过审核", "Audit.audit");
			return objPUC;			
		}
		if (objRvTotal.compareTo(objPyTotal) <= 0) {
			objPUC.add("E_001", "毛利小于等于0，无法通过审核", "Audit.audit");
			return objPUC;			
		}
		return objPUC;
	}
	
	/**
	 * 撤销审核
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
