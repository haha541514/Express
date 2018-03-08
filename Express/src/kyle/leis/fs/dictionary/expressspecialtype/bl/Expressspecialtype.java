package kyle.leis.fs.dictionary.expressspecialtype.bl;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeColumns;
import kyle.leis.fs.dictionary.expressspecialtype.dax.ExpressspecialtypeDemand;
import kyle.leis.fs.dictionary.expressspecialtype.tp.AddExpressspecialtypeTransaction;
import kyle.leis.fs.dictionary.expressspecialtype.tp.DelSpecialTypeTransact;

public class Expressspecialtype {
	/**
	 * �����������
	 * @return
	 * @throws Exception 
	 */
	public ExpressspecialtypeColumns addExpressspecialtype(ExpressspecialtypeColumns columns) throws Exception{
		AddExpressspecialtypeTransaction transaction = new AddExpressspecialtypeTransaction();
		transaction.setParamsColumns(columns);
		transaction.execute();
		// ˢ�»���
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		return ExpressspecialtypeDemand.queryByPkCode(columns.getEstestcode());
	}
	/**
	 * ɾ��
	 * @param pkCode ����
	 * @return
	 * @throws Exception
	 */
	public ExpressspecialtypeColumns delExpressspecialtype(String pkCode) throws Exception{
		DelSpecialTypeTransact transact = new DelSpecialTypeTransact();
		transact.setPkCode(pkCode);
		transact.execute();
		// ˢ�»���
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		return transact.getReturnColumns();
	}
}
