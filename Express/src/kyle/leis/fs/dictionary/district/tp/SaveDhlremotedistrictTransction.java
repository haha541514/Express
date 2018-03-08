package kyle.leis.fs.dictionary.district.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TdiDhlremotedistrict;

public class SaveDhlremotedistrictTransction extends AbstractTransaction {
	String result;
	List m_dhlrlist;

	public String getResult() {
		return result;
	}

	public void setM_dhlrlist(List mDhlrlist) {
		m_dhlrlist = mDhlrlist;
	}

	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		if (m_dhlrlist == null || m_dhlrlist.size() < 1)
			return;
		DhlRemotedDistrictSeqQuery seq = new DhlRemotedDistrictSeqQuery();
		for (int i = 0; i < m_dhlrlist.size(); i++) {
			TdiDhlremotedistrict objDhlremotedistrict = new TdiDhlremotedistrict();
			DhlremotedistrictColumns objDhlremotedistrictColumns = (DhlremotedistrictColumns) m_dhlrlist
					.get(i);
			objDhlremotedistrict.setDrdCode(seq.getNewSequencecode());
			DistrictDemand.setDhlremotedistrictColumns(objDhlremotedistrict, objDhlremotedistrictColumns, objSession);
			objSession.save(objDhlremotedistrict);	
			result = "";
		}

	}

}
