package kyle.leis.eo.operation.predictwaybill.baseorderimport;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowColumns;

public class OrderImportResult {
	private List<CwbimportrowColumns> cwbimportrowColumns = new ArrayList<CwbimportrowColumns>();
	private int successCount;
	private PromptUtilityCollection promptUtilities;

	public List<CwbimportrowColumns> getCwbimportrowColumns() {
		return cwbimportrowColumns;
	}

	public void setCwbimportrowColumns(
			List<CwbimportrowColumns> cwbimportrowColumns) {
		this.cwbimportrowColumns = cwbimportrowColumns;
	}
	
	public void addCwbimportrowColumns(CwbimportrowColumns cwbimportrowColumns){
		this.cwbimportrowColumns.add(cwbimportrowColumns);
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public PromptUtilityCollection getPromptUtilities() {
		return promptUtilities;
	}

	public void setPromptUtilities(PromptUtilityCollection promptUtilities) {
		this.promptUtilities = promptUtilities;
	}

}
