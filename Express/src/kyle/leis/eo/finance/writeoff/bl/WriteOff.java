package kyle.leis.eo.finance.writeoff.bl;

import kyle.leis.eo.finance.writeoff.da.WriteoffColumns;
import kyle.leis.eo.finance.writeoff.dax.WriteoffDemand;
import kyle.leis.eo.finance.writeoff.tp.DeleteWriteOffTrans;
import kyle.leis.eo.finance.writeoff.tp.SaveWriteOffTrans;

public class WriteOff {
	/**
	 * 核销保存
	 * @param objWriteoffColumns
	 * @param astrCrId
	 * @param astrBrId
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public WriteoffColumns save(WriteoffColumns objWriteoffColumns,
			String[] astrCrId,
			String[] astrBrId,
			String strOperId) throws Exception {
		SaveWriteOffTrans objSWOTrans = new SaveWriteOffTrans();
		objSWOTrans.setParam(objWriteoffColumns, 
				astrCrId, 
				astrBrId, 
				strOperId);
		objSWOTrans.execute();
		String strSavedWoId = objSWOTrans.getSavedWoId();
		return WriteoffDemand.load(strSavedWoId);
	}
	
	/**
	 * 撤销核销
	 * @param strWoId
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public WriteoffColumns delete(String strWoId, 
			String strOperId) throws Exception {
		DeleteWriteOffTrans objDeleteWriteOffTrans = new DeleteWriteOffTrans();
		objDeleteWriteOffTrans.setParam(strWoId, strOperId);
		objDeleteWriteOffTrans.execute();
		return WriteoffDemand.load(strWoId);
	}
}
