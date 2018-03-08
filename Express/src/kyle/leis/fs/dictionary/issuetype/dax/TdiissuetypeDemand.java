package kyle.leis.fs.dictionary.issuetype.dax;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.leis.fs.dictionary.issuetype.da.TdiissuetypeColumns;
import kyle.leis.fs.dictionary.issuetype.da.TdiissuetypeCondition;
import kyle.leis.fs.dictionary.issuetype.da.TdiissuetypeQuery;
import kyle.leis.hi.TdiIssuetype;
import kyle.leis.hi.TdiSimplestatus;

@SuppressWarnings("unchecked")
public class TdiissuetypeDemand {

	public static void setTdiissuetypeByColumns(TdiIssuetype objTdiisuetype,
			TdiissuetypeColumns objTdiissuetypeColumns, Session objSession)
			throws Exception {
		String strSscode = objTdiissuetypeColumns.getSssscode();
		objTdiisuetype.setIsutCode(objTdiissuetypeColumns.getIsutisutcode());
		objTdiisuetype.setIsutCustomervisiblesign(objTdiissuetypeColumns.getIsutisutcustomervisiblesign());
		objTdiisuetype.setIsutEname(objTdiissuetypeColumns.getIsutisutename());
		objTdiisuetype.setIsutGroup(objTdiissuetypeColumns.getIsutisutgroup());
		objTdiisuetype.setIsutName(objTdiissuetypeColumns.getIsutisutname());
		objTdiisuetype.setIsutNoticeinfo(objTdiissuetypeColumns.getIsutisutnoticeinfo());
		objTdiisuetype.setTdiSimplestatus((TdiSimplestatus) objSession.load(TdiSimplestatus.class, strSscode));

	}

	/*
	 * 方法：根据主键查询 作者：杨晨蕾
	 */
	public static List queryByIscode(String strIsutcode) throws Exception {
		TdiissuetypeQuery objTdiIssuetypeQuery = new TdiissuetypeQuery();
		TdiissuetypeCondition objTdiissuetypeCondition = new TdiissuetypeCondition();
		objTdiissuetypeCondition.setIsutcode(strIsutcode);
		objTdiIssuetypeQuery.setCondition(objTdiissuetypeCondition);
		List<TdiissuetypeColumns> objList = objTdiIssuetypeQuery.getResults();
		return objList;
	}

	/*
	 * 方法：查询 作者：杨晨蕾
	 */
	public static List query(TdiissuetypeCondition objTdiissuetypeCondition)
			throws Exception {
		TdiissuetypeQuery objTdiissuetypeQuery = new TdiissuetypeQuery();
		objTdiissuetypeQuery.setCondition(objTdiissuetypeCondition);
		List<TdiissuetypeColumns> objList = objTdiissuetypeQuery.getResults();
		return objList;
	}


}
