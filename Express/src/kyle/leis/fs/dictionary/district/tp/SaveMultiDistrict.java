package kyle.leis.fs.dictionary.district.tp;

import net.sf.hibernate.Session;
import src.kyle.common.dbaccess.transaction.AbstractTransaction;

public class SaveMultiDistrict extends AbstractTransaction{

	private String ReturnValue;
	
	public String getReturnValue(){
		
		return this.ReturnValue;
	}
	
	public void transaction(Session objSession) throws Exception {


		
	}

}
