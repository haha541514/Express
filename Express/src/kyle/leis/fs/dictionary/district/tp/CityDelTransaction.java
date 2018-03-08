package kyle.leis.fs.dictionary.district.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCityDC;
import kyle.leis.fs.dictionary.district.da.CityColumns;
import kyle.leis.fs.dictionary.district.da.CityCondition;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TdiCity;

public class CityDelTransaction extends AbstractTransaction {
	private String[] m_ctCodeList;
	private String result;

	public String[] getM_ctCodeList() {
		return m_ctCodeList;
	}

	public void setM_ctCodeList(String[] mCtCodeList) {
		m_ctCodeList = mCtCodeList;
	}

	public String getResult() {
		return result;
	}
	/**
	 * 这还是一个批量删除
	 * 
	 * 
	 * 
	 * **/
	public void transaction(Session objSession) throws Exception {
		if (m_ctCodeList.length == 0)
			return;
		String m_del = m_ctCodeList[0];
		for (int i = 1; i < m_ctCodeList.length; i++) {
			m_del += "," + m_ctCodeList[i];
		}
		execute(objSession, "delete from t_di_city where ct_code in (" + m_del
				+ ")");
		result = "";
	}

}
