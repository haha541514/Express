package kyle.leis.fs.dictionary.batterykind.bl;

import kyle.leis.fs.dictionary.batterykind.da.BatterykindColumns;
import kyle.leis.fs.dictionary.batterykind.dax.BatterkindDemand;
import kyle.leis.fs.dictionary.batterykind.tp.DeleteBatterkind;
import kyle.leis.fs.dictionary.batterykind.tp.ModifyBatterkind;
import kyle.leis.fs.dictionary.batterykind.tp.SaveBatterkind;

public class Batterykind {

	public BatterykindColumns save(BatterykindColumns columns) throws Exception{
		SaveBatterkind objSave = new SaveBatterkind();
		objSave.setParam(columns,columns.getBkbkcode());
		objSave.execute();
		
		BatterykindColumns objReturn = BatterkindDemand.queryById(columns.getBkbkcode());
		return objReturn;
		
	}

	public BatterykindColumns delete(String code) throws Exception{
		DeleteBatterkind objDelete = new DeleteBatterkind();
		objDelete.setParam(code);
		objDelete.execute();
		return objDelete.getBatterykindColumns();
		
	}
	
	
	
}
