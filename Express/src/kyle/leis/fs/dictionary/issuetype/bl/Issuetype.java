package kyle.leis.fs.dictionary.issuetype.bl;

import java.util.List;

import kyle.leis.fs.dictionary.issuetype.da.TdiissuetypeColumns;
import kyle.leis.fs.dictionary.issuetype.dax.TdiissuetypeDemand;
import kyle.leis.fs.dictionary.issuetype.tp.AddIssuetypeTransaction;
import kyle.leis.fs.dictionary.issuetype.tp.DeleteIssuetypeTransaction;
import kyle.leis.fs.dictionary.issuetype.tp.ModifyIssuetypeTransaction;

public class Issuetype {
	@SuppressWarnings("unchecked")
	public List save(TdiissuetypeColumns objTdiissuetypeColumns)
			throws Exception {
		String strIsutcode = objTdiissuetypeColumns.getIsutisutcode();
		List<TdiissuetypeColumns> objList = TdiissuetypeDemand.queryByIscode(strIsutcode);
		if (objList.size() == 0) {
			AddIssuetypeTransaction objAITrans = new AddIssuetypeTransaction();
			objAITrans.setParam(objTdiissuetypeColumns);
			objAITrans.execute();			
		} else {
			ModifyIssuetypeTransaction objMITrans = new ModifyIssuetypeTransaction();
			objMITrans.setParam(objTdiissuetypeColumns);
			objMITrans.execute();
		}
		List<TdiissuetypeColumns> objsaveList = TdiissuetypeDemand.queryByIscode(strIsutcode);
		return objsaveList;
	}

	public void deleteIssuetype(String strIsutcode, String strSscode)
			throws Exception {
		DeleteIssuetypeTransaction objDITrans = new DeleteIssuetypeTransaction();
		objDITrans.setParam(strIsutcode, strSscode);
		objDITrans.execute();
	}

}
