package kyle.leis.fs.dictionary.waybillcodekind.bl;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindColumns;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindCondition;
import kyle.leis.fs.dictionary.waybillcodekind.dax.WaybillCKDemand;
import kyle.leis.fs.dictionary.waybillcodekind.tp.DeleteWaybillCKTrans;
import kyle.leis.fs.dictionary.waybillcodekind.tp.ModifyWaybillCKTrans;
import kyle.leis.fs.dictionary.waybillcodekind.tp.SaveWaybillCKTrans;

public class Waybillcodekind {
	@SuppressWarnings("unchecked")
	public List save(WaybillcodekindColumns objColumns) throws Exception {
		String strBckCode="";
		if (!StringUtility.isNull(objColumns.getWbckbck_code())){
			//ÐÞ¸Ä
			ModifyWaybillCKTrans objModify = new ModifyWaybillCKTrans();
			objModify.setParam(objColumns);
			objModify.execute();
			
			strBckCode = objColumns.getWbckbck_code();
		}else{
			//ÐÂÔö
			SaveWaybillCKTrans objSaveTrans = new SaveWaybillCKTrans();
			objSaveTrans.setParam(objColumns);
			objSaveTrans.execute();
			
			 strBckCode = objSaveTrans.getBckCode();
			
		}
		
		WaybillcodekindCondition objWBCKCondition = new WaybillcodekindCondition();
		objWBCKCondition.setBckcode(strBckCode);
		List objList = WaybillCKDemand.query(objWBCKCondition);
		return objList;
		
		
	}

	public WaybillcodekindColumns delete(String strBckcode) throws Exception {
			DeleteWaybillCKTrans objDeleteTrans = new DeleteWaybillCKTrans();
			objDeleteTrans.setParam(strBckcode);
			objDeleteTrans.execute();
			return objDeleteTrans.getWaybillcodekind();
	}
}
