package kyle.leis.fs.waybillcode.dax;

import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;

public class WaybillcodeRegisterReturn {

	private WaybillcodeColumns m_objWaybillcodeColumns;
	private PromptUtilityCollection m_objPromptUtilityCollection;
	
	public void setWaybillcodeColumns(WaybillcodeColumns objWaybillcodeColumns)
	{
		this.m_objWaybillcodeColumns = objWaybillcodeColumns;
	}
	public WaybillcodeColumns getWaybillcodeColumns()
	{
		return this.m_objWaybillcodeColumns;
	}
	
	public void setPromptUtilityCollection(PromptUtilityCollection objPromptUtilityCollection)
	{
		this.m_objPromptUtilityCollection = objPromptUtilityCollection;
	}
	
	public PromptUtilityCollection getPromptUtilityCollection()
	{
		return this.m_objPromptUtilityCollection;
	}
}
