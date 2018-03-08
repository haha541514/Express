package kyle.leis.fs.dictionary.district.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.bl.District;
import kyle.leis.fs.dictionary.district.da.StateColumns;
import kyle.leis.fs.dictionary.district.da.StateQuery;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.district.dax.StateSeq;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiState;

public class StateSaveTransaction extends AbstractTransaction {
	private StateColumns m_objTdistateColumns;
	private String newStCode;

	public void setM_objTdistateColumns(StateColumns mObjTdistateColumns) {
		m_objTdistateColumns = mObjTdistateColumns;
	}

	public String getnewStCode() {
		return newStCode;
	}

	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		if (m_objTdistateColumns == null)
			return;
		TdiState objTdiState;
		// жїМќЮЊПе
		if (m_objTdistateColumns.getStstcode() == null || StringUtility.isNull(m_objTdistateColumns.getStstcode())) {
			objTdiState = new TdiState();
			StateSeq seq = new StateSeq();
			String st = seq.getNewSequencecode();
			this.m_objTdistateColumns.setStstcode(st);
		} else {
			objTdiState = (TdiState) objSession.load(TdiState.class,
					m_objTdistateColumns.getStstcode());
		}
		DistrictDemand.setStateColumns(objTdiState, m_objTdistateColumns,
				objSession);
		objSession.save(objTdiState);
		newStCode = objTdiState.getStCode();
	}

}
