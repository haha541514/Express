package kyle.leis.fs.dictionary.district.tp;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.district.da.DicdistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdistrictCondition;
import kyle.leis.fs.dictionary.district.da.DicdistrictQuery;

import kyle.leis.hi.TdiDistrict;
import kyle.common.util.jlang.StringUtility;

public class DeleteDistrict extends AbstractTransaction{
	private String dtcode;
	private DicdistrictColumns objColumns;
	private String conditionStr;
	public void setParam(String dt_code) {
		this.dtcode = dt_code;
	}
	
	public DicdistrictColumns getDistrictColumns(){
		return this.objColumns;
		
	}
	public String getConditionStr(){
		return this.conditionStr;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(dtcode)) {
			this.conditionStr = "";
			return;}
		
		//TdiDistrict
		DicdistrictCondition condition = new DicdistrictCondition();
		condition.setCountcode(dtcode);//设置dt_counter
		DicdistrictQuery query = new DicdistrictQuery();
		query.setCondition(condition);//设置查询条件，又忘了
		List list = query.getResults();//select xx from xx where Countcode = dtcode
		
		if(list.size()>0){
			//被引用，无法删除
			this.conditionStr = "F";
			
		}else{	
			try {
			TdiDistrict objTdiDistrict = (TdiDistrict) TdiDistrictDC.load(
					TdiDistrict.class, dtcode);
			String deleteSql = "from TdiDistrict di where di.dtCode = '" + dtcode
					+ "'";
			objSession.delete(deleteSql);
			this.conditionStr = "S";
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
				this.conditionStr = "";
				
			}
		}
		
	}
}
