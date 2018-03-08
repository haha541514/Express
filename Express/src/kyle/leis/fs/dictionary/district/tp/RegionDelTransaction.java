package kyle.leis.fs.dictionary.district.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.RegionColumns;
import kyle.leis.fs.dictionary.district.da.RegionQuery;
import kyle.leis.hi.TdiCity;
import kyle.leis.hi.TdiRegion;

public class RegionDelTransaction extends AbstractTransaction {
	private String result;
	private String[] m_rgCodeList;

	public String getResult() {
		return result;
	}

	public void setM_rgCodeList(String[] mRgCodeList) {
		m_rgCodeList = mRgCodeList;
	}

	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		if (m_rgCodeList.length == 0)
			return;
		String m_del = "'"+m_rgCodeList[0]+"'";
		for (int i = 1; i < m_rgCodeList.length; i++) {
			m_del += ",'" + m_rgCodeList[i]+"'";
		}
		execute(objSession, "delete from t_di_region where rg_code in ("
				+ m_del + ")");
		this.result = "";
	}

}
