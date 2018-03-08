package kyle.leis.fs.dictionary.district.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.CityCondition;
import kyle.leis.fs.dictionary.district.da.StateColumns;
import kyle.leis.fs.dictionary.district.da.StateCondition;
import kyle.leis.fs.dictionary.district.da.StateQuery;
import kyle.leis.fs.dictionary.district.dax.CityDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiState;

public class StateDelTransaction extends AbstractTransaction {
	private String result;
	private String[] m_stCodeList;
	
	public String getResult() {
		return result;
	}

	public void setM_stCodeList(String[] stCodeList) {
		m_stCodeList = stCodeList;
	}

	public void transaction(Session objSession) throws Exception {
		if (m_stCodeList.length == 0)
			return;
		String m_del = null;
		String not_del = null;
		CityCondition condition = new CityCondition();
		 for (int i = 1; i < m_stCodeList.length; i++) {

			condition.setStcode(m_stCodeList[i]);
			List listReturn = CityDemand.queryCity(condition);
			if(CollectionUtility.isNull(listReturn) || listReturn.size() == 0){
				//没有被用到，可以删除,然后连接拼装
				m_del += "," + m_stCodeList[i];
			}else{//不能被删除
				not_del += ","+ m_stCodeList[i];
			}
				execute(objSession, "delete from t_di_state where st_code = '" + m_del+ "'");
		}
		this.result = "";

		
	}

}
