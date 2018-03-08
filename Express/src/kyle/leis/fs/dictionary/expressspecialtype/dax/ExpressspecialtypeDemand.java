package kyle.leis.fs.dictionary.expressspecialtype.dax;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DBStringUtility;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeColumns;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeCondition;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeQuery;

public class ExpressspecialtypeDemand {
	/**
	 * 查询特殊类型
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<ExpressspecialtypeColumns> query(ExpressspecialtypeCondition condition) 
			throws Exception{
		ExpressspecialtypeQuery query = new ExpressspecialtypeQuery();
		query.setCondition(condition);
		return query.getResults();
	}
	/**
	 * 通过主键查询特殊类型
	 * @param pkCode
	 * @return
	 * @throws Exception
	 */
	public static ExpressspecialtypeColumns queryByPkCode(String pkCode) throws Exception{
		ExpressspecialtypeCondition condition = new ExpressspecialtypeCondition();
		condition.setEstcode(pkCode);
		condition.setUseCacheSign(true);
		List<ExpressspecialtypeColumns> list = query(condition);
		return list == null || list.isEmpty() ? null : list.get(0);
	}
	/**
	 * 获取类型编码
	 * @param code
	 * @param lever 0表示同级产品代码，1表示子产品代码
	 * @return
	 * @throws Exception
	 */
	public static String getTypeCode(String code, String lever) throws Exception{
		int leverWidth = 2;
		List<ExpressspecialtypeColumns> columns = query(null);
		List<String> fieldList = CollectionUtility.getFieldList(columns, "estestcode");
		String parentCode = code;
		if ("0".equals(lever)) {
			parentCode = DBStringUtility.getParentCode(code, leverWidth);
		}
		return DBStringUtility.createStructureCode(parentCode, fieldList, leverWidth);
	}
}
