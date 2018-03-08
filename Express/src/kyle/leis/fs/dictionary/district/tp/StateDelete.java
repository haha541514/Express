package kyle.leis.fs.dictionary.district.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.fs.dictionary.district.da.CityCondition;
import kyle.leis.fs.dictionary.district.dax.CityDemand;

public class StateDelete extends AbstractTransaction{

	private String result;
	private String m_stCodeList;

	public String getResult() {
		return result;
	}

	public void setM_stCodeList(String mStCodeList) {
		m_stCodeList = mStCodeList;
	}

	public void transaction(Session objSession) throws Exception {

		CityCondition condition = new CityCondition();
		String m_del = m_stCodeList;
		condition.setStcode(m_stCodeList);
		List listReturn = CityDemand.queryCity(condition);
		if(CollectionUtility.isNull(listReturn) || listReturn.size() == 0){
				execute(objSession, "delete from t_di_state where st_code = '" + m_del+ "'");
				this.result = "";
		}else{
			this.result = "default";
		}
		
	}

}
