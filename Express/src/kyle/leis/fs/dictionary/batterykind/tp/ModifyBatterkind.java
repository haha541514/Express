package kyle.leis.fs.dictionary.batterykind.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindColumns;
import kyle.leis.fs.dictionary.batterykind.dax.BatterkindDemand;
import kyle.leis.hi.TdiBatterykind;

public class ModifyBatterkind extends AbstractTransaction{

	private BatterykindColumns objColumns;
	
	public void setParam(BatterykindColumns columns){
		this.objColumns = columns;
	}
	
	public void transaction(Session objSession) throws Exception {
		
		if(objColumns == null) return;
		TdiBatterykind objTdiBatter = new TdiBatterykind();
		//objTdiBatter.setBkCode(objColumns.getBkbkcode());
		BatterkindDemand.setBatterkind(objTdiBatter, objSession, objColumns);
		objSession.update(objTdiBatter);
		
	}

}
