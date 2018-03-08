package kyle.leis.eo.operation.predictwaybill.baseorderimport;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.prompt.PromptUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowColumns;
import kyle.leis.eo.operation.predictwaybill.bl.Predictwaybill;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;

public abstract class AbstractOrderImportor implements OrderImportor {

	public OrderImportResult doImport(String startdate, String enddate,
			String accessToken, String pkCode, String coCode, String opId, String cawtId) {
		OrderImportResult importResult = new OrderImportResult();
		List<CwbimportrowColumns> cwbimportrowColumns = new ArrayList<CwbimportrowColumns>(); // 记录日志信息
		try {
			List<OrderImportorInput> importorInputs = getImportorInputs(startdate, enddate, accessToken, pkCode, coCode, cawtId);
			int normalSize = 0;
			long rowNumber = 0;
			for (OrderImportorInput orderImportorInput : importorInputs) {
				Predictwaybill predictwaybill = new Predictwaybill();
				PredictwaybillColumns predictwaybillColumns = orderImportorInput.getPredictwaybillColumns();
				SavedResultUtility resultUtility = predictwaybill.save(predictwaybillColumns, 
						orderImportorInput.getListCargoInfo(), opId); // 保存订单
				CwbimportrowColumns rowColumns = new CwbimportrowColumns();
				rowColumns.setCwbrcomp_idcwbrid(++rowNumber);
				StringBuilder remarkSb = new StringBuilder("客户单号：" + predictwaybillColumns.getPwbpwb_orderid() + "。");
				if (resultUtility.getPromptUtilityCollection().canGo(false)) {
					normalSize++;
					rowColumns.setCwbrcwbrsuccesssign("Y");
				} else {
					rowColumns.setCwbrcwbrsuccesssign("N");
					for (PromptUtility prompt : resultUtility.getPromptUtilityCollection().getCollection()) {
						remarkSb.append(prompt.getDescribtion() + "。");
					}
				}
				rowColumns.setCwbrcwbrremark(remarkSb.toString());
				cwbimportrowColumns.add(rowColumns);
			}
			importResult.setCwbimportrowColumns(cwbimportrowColumns);
			importResult.setSuccessCount(normalSize);
		} catch (Exception e) {
			e.printStackTrace();
			PromptUtilityCollection promptUtilities = new PromptUtilityCollection();
			promptUtilities.add("E_01", e.getMessage(), "AbstractOrderImportor.doImport");
			importResult.setPromptUtilities(promptUtilities);
		}
		return importResult;
	}
	
	protected abstract List<OrderImportorInput> getImportorInputs(String startdate, String enddate,
			String accessToken, String pkCode, String coCode, String cawtId) throws Exception;

}
