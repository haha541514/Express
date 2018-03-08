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
	 * ��ѯ
	 */
	public static List query(FeekindCondition objFeekindCondition)
			throws Exception {
		FeekindQuery objFeekindQuery = new FeekindQuery();
		objFeekindQuery.setCondition(objFeekindCondition);
		// ���ﷵ�ص���feekind.da.feekindolumns��list��������wkq.da.FeekindColumns��
		return objFeekindQuery.getResults();
	}

	/*
	 * ����������ѯ
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
	 * 20170727 ����,12:01 by wukaiquan option:��ȡobjRdiFeekind�����Խ������ ����
	 * TdiFeekind �����ԣ�
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
		//getEstestcode�ǲ��ǿ�,�������
		if (!StringUtility.isNull(objFeekindColumns.getExestcode()))
			objTdiFeekind.setTdiExpressspecialtype((TdiExpressspecialtype) objSession
							.load(TdiExpressspecialtype.class,
									objFeekindColumns.getExestcode()));
		
		
		
	}

	/**�ӷ�
	 * 20160728 ���� 09:18 by wukaiquan oprion:delete Feekind by fkCoke
	 * 
	 * **/
	public static void deleteFeekindByCoke(String strFkcode) {
				
	}

}
