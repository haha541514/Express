package kyle.leis.fs.dictionary.expressspecialtype.bl;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeColumns;
import kyle.leis.fs.dictionary.expressspecialtype.dax.ExpressspecialtypeDemand;
import kyle.leis.fs.dictionary.expressspecialtype.tp.AddExpressspecialtypeTransaction;
import kyle.leis.fs.dictionary.expressspecialtype.tp.DelSpecialTypeTransact;

public class Expressspecialtype {
	/**
	 * 添加特殊类型
	 * @return
	 * @throws Exception 
	 */
	public ExpressspecialtypeColumns addExpressspecialtype(ExpressspecialtypeColumns columns) throws Exception{
		AddExpressspecialtypeTransaction transaction = new AddExpressspecialtypeTransaction();
		transaction.setParamsColumns(columns);
		transaction.execute();
		// 刷新缓冲
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		return ExpressspecialtypeDemand.queryByPkCode(columns.getEstestcode());
	}
	/**
	 * 删除
	 * @param pkCode 主键
	 * @return
	 * @throws Exception
	 */
	public ExpressspecialtypeColumns delExpressspecialtype(String pkCode) throws Exception{
		DelSpecialTypeTransact transact = new DelSpecialTypeTransact();
		transact.setPkCode(pkCode);
		transact.execute();
		// 刷新缓冲
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		return transact.getReturnColumns();
	}
}
