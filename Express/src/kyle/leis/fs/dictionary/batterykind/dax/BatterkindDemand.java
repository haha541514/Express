package kyle.leis.fs.dictionary.batterykind.dax;

import java.util.Collection;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindColumns;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindCondition;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindQuery;
import kyle.leis.hi.TdiBatterykind;
import kyle.leis.hi.TdiCargokind;
import kyle.leis.hi.TdiSimplestatus;
import kyle.common.connectors.util.Encoder;
public class BatterkindDemand {
	
	
	public static List query(BatterykindCondition objCondition) throws Exception {
		String bckcode = "";
		
		BatterykindQuery objQuery = new BatterykindQuery();
		objQuery.setCondition(objCondition);
		
		List list = objQuery.getResults();
		for(int i=0;i<list.size();i++){
			BatterykindColumns columns = (BatterykindColumns) list.get(i);
			System.out.println(columns.getBkbkcode()+columns.getBkbkename()+columns.getBkbkname()
					+columns.getCkcgkcode()+columns.getSisscode()+columns.getCkcgkname()+columns.getSissname());
		}
		if(list == null || list.size() == 0){
			return null;
		}else{
			return list;
		}

	}
	
	public static BatterykindColumns queryById(String bckcode) throws Exception{
		
		List list = null;
		BatterykindCondition objCondition = new BatterykindCondition();
		objCondition.setBkcode(bckcode);;
		BatterykindQuery objQuery = new BatterykindQuery();
		objQuery.setCondition(objCondition);
		
		list = objQuery.getResults();
		//不为空，并且有值，list有值，isNUll取反， false在取反为true
		if(!CollectionUtility.isNull(list) && list.size() == 1){
			return (BatterykindColumns) list.get(0);
			
		}else{
			return null;
		}
		
	}
	

	public static void setBatterkind(TdiBatterykind objBatter,Session session,BatterykindColumns columns) throws HibernateException{
		
		objBatter.setBkCode(columns.getBkbkcode());//设置主键
		objBatter.setBkName(columns.getBkbkname());
		objBatter.setBkEname(columns.getBkbkename());
		objBatter.setTdiSimplestatus((TdiSimplestatus)session.load(TdiSimplestatus.class, columns.getSisscode()));
	
		objBatter.setTdiCargokind((TdiCargokind)session.load(TdiCargokind.class, columns.getCkcgkcode()));

		
		
	}

	
}
