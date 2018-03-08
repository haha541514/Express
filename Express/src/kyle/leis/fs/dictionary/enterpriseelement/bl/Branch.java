package kyle.leis.fs.dictionary.enterpriseelement.bl;

import java.util.List;

import kyle.leis.fs.dictionary.enterpriseelement.da.BranchColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.BranchCondition;
import kyle.leis.fs.dictionary.enterpriseelement.dax.BranchDemand;
import kyle.leis.fs.dictionary.enterpriseelement.tp.SaveBranchTransaction;

public class Branch {

	public BranchColumns addBranch(BranchColumns objBranchCol,
			List listEECity,
			String strOperId) throws Exception
	{
		SaveBranchTransaction objSaveBranchTrans = new SaveBranchTransaction();
		objSaveBranchTrans.setParam(objBranchCol, listEECity, strOperId);
		objSaveBranchTrans.execute();
		
		BranchCondition objBranchCon = new BranchCondition();
		objBranchCon.setEeeecode(objSaveBranchTrans.getNewEecode());
		return (BranchColumns)BranchDemand.query(objBranchCon).get(0);
	}
}
