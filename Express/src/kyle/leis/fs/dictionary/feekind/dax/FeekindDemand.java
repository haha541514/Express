package kyle.leis.fs.dictionary.feekind.dax;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.feekind.da.FeekindColumns;
import kyle.leis.fs.dictionary.feekind.da.FeekindCondition;
import kyle.leis.fs.dictionary.feekind.da.FeekindQuery;
import kyle.leis.hi.TdiExpressspecialtype;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiSimplestatus;
import net.sf.hibernate.Session;

public class FeekindDemand {

	/*
	 * 查询
	 */
	public static List query(FeekindCondition objFeekindCondition) throws Exception
	{
		FeekindQuery objFeekindQuery = new FeekindQuery();
		objFeekindQuery.setCondition(objFeekindCondition);
		return objFeekindQuery.getResults();
	}
	/*
	 * 根据主键查询
	 */
	public static FeekindColumns queryByFkcode(String strFkcode) throws Exception
	{
		FeekindCondition objFeekCondition = new FeekindCondition();
		objFeekCondition.setFkcode(strFkcode);
		List objList = query(objFeekCondition);
		if(!CollectionUtility.isNull(objList) && objList.size() == 1)
			return (FeekindColumns)objList.get(0);
		return null;
	}
	
	public static void setFeekindByColumns(TdiFeekind objTdiFeekind,FeekindColumns objFeekindColumns,Session objSession) throws Exception
	{
		objTdiFeekind.setFkBasesign(objFeekindColumns.getSign());
		objTdiFeekind.setFkEname(objFeekindColumns.getFkfkename());
		objTdiFeekind.setFkManualmodifysign(objFeekindColumns.getFkfkmanualmodifysign());
		objTdiFeekind.setFkName(objFeekindColumns.getFkfkname());
		objTdiFeekind.setFkReferenceposition(objFeekindColumns.getFkfkreferenceposition());
		if(!StringUtility.isNull(objFeekindColumns.getFkfkremark()))
			objTdiFeekind.setFkRemark(objFeekindColumns.getFkfkremark());
		if(!StringUtility.isNull(objFeekindColumns.getEstestcode()))
			objTdiFeekind.setTdiExpressspecialtype((TdiExpressspecialtype)objSession.load(TdiExpressspecialtype.class,objFeekindColumns.getEstestcode()));
		objTdiFeekind.setTdiSimplestatus((TdiSimplestatus)objSession.load(TdiSimplestatus.class,objFeekindColumns.getSssscode()));
	}
}
