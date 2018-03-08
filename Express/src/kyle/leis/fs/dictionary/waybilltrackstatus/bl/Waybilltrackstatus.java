package kyle.leis.fs.dictionary.waybilltrackstatus.bl;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.fs.cachecontainer.da.WaybilltrackstatusColumns;
import kyle.leis.fs.dictionary.waybilltrackstatus.dax.WaybilltrackstatusDemand;
import kyle.leis.fs.dictionary.waybilltrackstatus.tp.AddWaybilltrackstatusTransaction;
import kyle.leis.fs.dictionary.waybilltrackstatus.tp.DelWaybilltrackstatusTransaction;

public class Waybilltrackstatus {
	/**
	 * Ìí¼Ó
	 * @param columns
	 * @return
	 * @throws Exception
	 */
	public WaybilltrackstatusColumns add(WaybilltrackstatusColumns columns) throws Exception{
		AddWaybilltrackstatusTransaction transaction = new AddWaybilltrackstatusTransaction();
		transaction.setWaybilltrackstatusColumns(columns);
		transaction.execute();
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		return WaybilltrackstatusDemand.getByWbtsCode(columns.getWbtswbtscode());
	}
	/**
	 * É¾³ý
	 * @param pkCode
	 * @return
	 * @throws Exception
	 */
	public WaybilltrackstatusColumns delete(String pkCode) throws Exception{
		DelWaybilltrackstatusTransaction transaction = new DelWaybilltrackstatusTransaction();
		transaction.setPkCode(pkCode);
		transaction.execute();
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		return transaction.getColumns();
		
	}
}










