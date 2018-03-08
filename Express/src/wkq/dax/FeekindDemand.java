package wkq.dax;

import java.util.List;

import wkq.da.FeekindColumns;
import wkq.da.FeekindCondition;
import wkq.da.FeekindQuery;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiExpressspecialtype;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiSimplestatus;
import net.sf.hibernate.Session;

public class FeekindDemand {

	/*
	 * 查询
	 */
	public static List query(FeekindCondition objFeekindCondition)
			throws Exception {
		FeekindQuery objFeekindQuery = new FeekindQuery();
		objFeekindQuery.setCondition(objFeekindCondition);
		// 这里返回的是feekind.da.feekindolumns的list，而不是wkq.da.FeekindColumns的
		return objFeekindQuery.getResults();
	}

	/*
	 * 根据主键查询
	 */
	public static FeekindColumns queryByFkcode(String strFkcode)
			throws Exception {
		FeekindCondition objFeekCondition = new FeekindCondition();
		objFeekCondition.setFkcode(strFkcode);
		List objList = query(objFeekCondition);
		if (!CollectionUtility.isNull(objList) && objList.size() == 1)
			return (FeekindColumns) objList.get(0);
		return null;
	}

	/**
	 * 20170727 周三,12:01 by wukaiquan option:获取objRdiFeekind的属性进行填充 设置
	 * TdiFeekind 的属性，
	 **/
	public static void setFeekindByColumns(TdiFeekind objTdiFeekind,
			FeekindColumns objFeekindColumns, Session objSession)
			throws Exception {

		//Feekcode	
		objTdiFeekind.setFkBasesign(objFeekindColumns.getSign());
		objTdiFeekind.setFkEname(objFeekindColumns.getFofkename());
		objTdiFeekind.setFkManualmodifysign(objFeekindColumns.getFofkmanualmodifysign());
		objTdiFeekind.setFkName(objFeekindColumns.getFofkname());
		objTdiFeekind.setFkReferenceposition(objFeekindColumns.getFofkreferenceposition());
		objTdiFeekind.setFkAccountingonlysign(objFeekindColumns.getFofkaccountingonlysign());
		objTdiFeekind.setFkDeclarevaluesign(objFeekindColumns.getFofkdeclarevaluesign());
		objTdiFeekind.setFkRemark(objFeekindColumns.getFofkremark());
		
		if (!StringUtility.isNull(objFeekindColumns.getFofkremark()))
			objTdiFeekind.setFkRemark(objFeekindColumns.getFofkremark());
		
		objTdiFeekind.setTdiSimplestatus((TdiSimplestatus) objSession.load(
				TdiSimplestatus.class, objFeekindColumns.getSisscode()));
		//getEstestcode是不是空,两个外键
		if (!StringUtility.isNull(objFeekindColumns.getExestcode()))
			objTdiFeekind.setTdiExpressspecialtype((TdiExpressspecialtype) objSession
							.load(TdiExpressspecialtype.class,
									objFeekindColumns.getExestcode()));
		
		
		
	}

	/**杂费
	 * 20160728 周四 09:18 by wukaiquan oprion:delete Feekind by fkCoke
	 * 
	 * **/
	public static void deleteFeekindByCoke(String strFkcode) {
				
	}

}
