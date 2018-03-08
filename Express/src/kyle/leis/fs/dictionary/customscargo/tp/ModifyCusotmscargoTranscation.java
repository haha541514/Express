package kyle.leis.fs.dictionary.customscargo.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoColumns;
import kyle.leis.fs.dictionary.customscargo.dax.CustomscargoDemand;
import kyle.leis.hi.TdiCustomscargo;

public class ModifyCusotmscargoTranscation extends AbstractTransaction {
	
	private CustomscargoColumns m_CustomscargoColumns;

	public void setParam(CustomscargoColumns customscargo){
		this.m_CustomscargoColumns=customscargo;
	}
	public void transaction(Session objSession) throws Exception {
			
			TdiCustomscargo objTdiCustomscargo =new TdiCustomscargo();
			CustomscargoDemand.setCustomscargoByColumns(objTdiCustomscargo, m_CustomscargoColumns, objSession);	
			objSession.update(objTdiCustomscargo);
	
	}
}
