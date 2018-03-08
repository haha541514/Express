package kyle.leis.fs.waybillcode.bl;

import java.util.ArrayList;

import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodekindColumns;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.fs.waybillcode.dax.WaybillcodeDemand;
import kyle.leis.fs.waybillcode.dax.WaybillcodeRegisterReturn;
import kyle.leis.fs.waybillcode.tp.RegisterTransaction;
import kyle.leis.fs.waybillcode.tp.UseInValueTransaction;
import kyle.leis.fs.waybillcode.tp.UseTransaction;

public abstract class AWaybillcode {
	protected int m_iMaxLength = IWaybillcodeBasicData.MAXLENGTH;
	protected int m_iMinLength = IWaybillcodeBasicData.MINLENGTH;
	protected WaybillcodekindColumns m_objWBCKindColumns;
	protected IComplexPrefix m_objComplexPrefix;
	
	public AWaybillcode(WaybillcodekindColumns objWBCKColumns) {
		m_objWBCKindColumns = objWBCKColumns;
	}
	
	public AWaybillcode() {
	}	
	
	public void setComplexPrefix(IComplexPrefix objComplexPrefix) {
		m_objComplexPrefix = objComplexPrefix;
	}
	
	public PromptUtilityCollection checkCodeLength(String strRegisterCode) {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (strRegisterCode.length() > m_iMaxLength)
			objPUCollection.add("E_WBC_001", 
					"长度不能大于" + m_iMaxLength, 
					"AWaybillcode.checkCodeLength");
		if (strRegisterCode.length() < m_iMinLength)
			objPUCollection.add("E_WBC_001", 
					"长度不能小于" + m_iMinLength, 
					"AWaybillcode.checkCodeLength");		
		return objPUCollection;
	}
	
	public synchronized ArrayList<String> getUsedLabelcode(String strOperId, 
			int iNumbers) throws Exception {
		// 查询号码种类
		if (m_objWBCKindColumns.getWbckbckbuildvaluesign().equals("N")) {
			UseTransaction objUseTransaction = new UseTransaction();
			objUseTransaction.setParam(m_objWBCKindColumns.getWbckbckcode(), iNumbers, this);
			objUseTransaction.execute();
			return objUseTransaction.getLabelcodeCollection();
		} else if (m_objWBCKindColumns.getWbckbckbuildvaluesign().equals("Y")) {
			UseInValueTransaction objUIV = new UseInValueTransaction();
			objUIV.setParam(m_objWBCKindColumns.getWbckbckcode(), 
					iNumbers, 
					strOperId, 
					this);
			objUIV.execute();
			return objUIV.getLabelcodeCollection();
		}
		return null;
	}
	
	
	
	public synchronized WaybillcodeRegisterReturn register(WaybillcodeColumns objWaybillcodeColumns,
			String strOperId,
			boolean isIgnoreNotice) throws Exception {
		WaybillcodeRegisterReturn objWBCRReturn = new WaybillcodeRegisterReturn();
		PromptUtilityCollection objPUCollection = checkRegister(objWaybillcodeColumns);
		if (objPUCollection.canGo(isIgnoreNotice)) {
			RegisterTransaction objRegisterTransaction = new RegisterTransaction();
			objRegisterTransaction.setParam(objWaybillcodeColumns, this, strOperId);
			objRegisterTransaction.execute();
			WaybillcodeColumns objReturn = WaybillcodeDemand.queryByBcId(objRegisterTransaction.getNewBcid());
			if(objWaybillcodeColumns != null)
			objWBCRReturn.setWaybillcodeColumns(objReturn);
		}
		objWBCRReturn.setPromptUtilityCollection(objPUCollection);
		return objWBCRReturn;
	}
	
	public synchronized PromptUtilityCollection use(String[] astrLabelcode,
			String strOperId) throws Exception {
		return null;
	}
	
	protected abstract PromptUtilityCollection checkRegister(WaybillcodeColumns objWaybillcodeColumns);
	public abstract PromptUtilityCollection checkLabelcode(String strLabelcode);
	public abstract String buildLabelcode(String strCode, String strPrefix, String strSuffix);
}
