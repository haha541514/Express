package kyle.leis.eo.operation.corewaybillpieces.bl;

import java.util.List;

import kyle.leis.eo.operation.corewaybillpieces.tp.SaveServerLabelcodeTrans;
import kyle.leis.eo.operation.corewaybillpieces.tp.UpdateBagLabelTransaction;

public class Corewaybillpieces {
	
	public void modifyBgLabelcode(String strOriginBgLabelcode, 
			String strNewBgLabelcode) throws Exception {
		UpdateBagLabelTransaction objUBLT = new UpdateBagLabelTransaction();
		objUBLT.setParam(strOriginBgLabelcode, strNewBgLabelcode);
		objUBLT.execute();
	}
	
	public void saveSelfLabelcodeTrans(List listCWPieces,
			String strCwcode,
			String strServerEwbcode) throws Exception {
		SaveServerLabelcodeTrans objSSLT = new SaveServerLabelcodeTrans();
		objSSLT.setParam(listCWPieces, strCwcode, strServerEwbcode);
		objSSLT.execute();
	}
}
