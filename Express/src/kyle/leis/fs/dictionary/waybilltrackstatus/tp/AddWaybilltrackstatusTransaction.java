package kyle.leis.fs.dictionary.waybilltrackstatus.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.cachecontainer.da.WaybilltrackstatusColumns;
import kyle.leis.fs.dictionary.waybilltrackstatus.dax.WaybilltrackstatusDemand;
import kyle.leis.hi.TdiWaybilltrackstatus;
import net.sf.hibernate.Session;

public class AddWaybilltrackstatusTransaction extends AbstractTransaction {
	private WaybilltrackstatusColumns waybilltrackstatusColumns;

	public WaybilltrackstatusColumns getWaybilltrackstatusColumns() {
		return waybilltrackstatusColumns;
	}
	public void setWaybilltrackstatusColumns(
			WaybilltrackstatusColumns waybilltrackstatusColumns) {
		this.waybilltrackstatusColumns = waybilltrackstatusColumns;
	}


	public void transaction(Session objSession) throws Exception {
		if (waybilltrackstatusColumns == null || waybilltrackstatusColumns.getWbtswbtscode() == null) {
			return;
		}
		TdiWaybilltrackstatus waybilltrackstatus =(TdiWaybilltrackstatus) objSession.get(TdiWaybilltrackstatus.class, 
				waybilltrackstatusColumns.getWbtswbtscode());
		if (waybilltrackstatus == null) {//ÐÂÔö
			waybilltrackstatus = new TdiWaybilltrackstatus();
		}
		WaybilltrackstatusDemand.convertToPO(waybilltrackstatusColumns, waybilltrackstatus);
		objSession.save(waybilltrackstatus);
	}

}
