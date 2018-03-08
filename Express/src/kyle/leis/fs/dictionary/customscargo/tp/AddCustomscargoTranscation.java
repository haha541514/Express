package kyle.leis.fs.dictionary.customscargo.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoColumns;
import kyle.leis.fs.dictionary.customscargo.dax.CustomscargoDemand;
import kyle.leis.hi.TdiCustomscargo;


public class AddCustomscargoTranscation extends AbstractTransaction{
	
	public List<CustomscargoColumns> m_customscargos;
	
	public void setParam(List<CustomscargoColumns> customscargos){
		m_customscargos=customscargos;
	}

	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		for(int i=0; i < m_customscargos.size(); i++){
			TdiCustomscargo objTdiCustomscargo = new TdiCustomscargo();
			CustomscargoDemand.setCustomscargoByColumns(objTdiCustomscargo, m_customscargos.get(i), objSession);
			objSession.save(objTdiCustomscargo);
		}
	}

}
