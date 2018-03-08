package kyle.leis.fs.dictionary.cargokind.dax;

import java.util.List;



import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.fs.dictionary.cargokind.da.CargokindColumns;
import kyle.leis.fs.dictionary.cargokind.da.CargokindCondition;
import kyle.leis.fs.dictionary.cargokind.da.CargokindQuery;
import kyle.leis.hi.TdiCargokind;
import kyle.leis.hi.TdiSimplestatus;

public class CargokindDemand {

	/**
	 * CargokindDemand的作用主要用来查询和设置参数
	 * @throws Exception 
	 * 查所有
	 * **/
	public static List Query(CargokindCondition objCondition ) throws Exception{
		List querylist = null;
		//String cgk_code = "";
	
		CargokindQuery objQuery = new CargokindQuery();
		objQuery.setCondition(objCondition);
		try {
				querylist = objQuery.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i = 0; i < querylist.size() ; i++){
			CargokindColumns columns = (CargokindColumns) querylist.get(i);
			System.out.println(columns.getCkcgkcode()+columns.getSissname());
		}
		
		
		if(querylist == null || querylist.size() == 0){
			return null;
		}else{
			return querylist;
		}
		
		
	}
	/**
	 * 20160802 周二 16:24
	 * by wukaiquan 
	 * columns对象转为entity
	 * 
	 * **/
	public static void setCargokindByid(TdiCargokind objCargo,
			CargokindColumns columns, Session session)
			throws HibernateException {
		
		objCargo.setCgkCode(columns.getCkcgkcode());//27
		System.out.println(columns.getSisscode());//Y?
		objCargo.setTdiSimplestatus((TdiSimplestatus) session.load(TdiSimplestatus.class, columns.getSisscode()));
		objCargo.setCgkName(columns.getCkcgkname());//笔记本
		objCargo.setCgkEname(columns.getCkcgkename());//ON
		objCargo.setCgkBatterysign(columns.getCkcgkbatterysign());//Cargo	
	}

	
	/**
	 * 根据主键查找,主键有，就是查找出来的是空值 = =
	 * **/
	public static CargokindColumns queryById(String ckcgkcode) {
		List list =null;
		CargokindCondition condition = new CargokindCondition();
		condition.setCgkcode(ckcgkcode);
		CargokindQuery query  = new CargokindQuery();
		query.setCondition(condition);//没有设置查询条件，所以为空。
		try {
			 list = query.getResults();//为什么list的值都是Code Generate by Keyle
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!CollectionUtility.isNull(list) && list.size() == 1)
			return (CargokindColumns) list.get(0);
		return null;
	}

}
