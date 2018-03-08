package kyle.leis.fs.dictionary.waybilltrackstatus.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.cachecontainer.da.WaybilltrackstatusColumns;
import kyle.leis.fs.dictionary.waybilltrackstatus.dax.WaybilltrackstatusDemand;
import net.sf.hibernate.Session;

public class DelWaybilltrackstatusTransaction extends AbstractTransaction {
	private String pkCode;
	private WaybilltrackstatusColumns columns;
	
	public WaybilltrackstatusColumns getColumns() {
		return columns;
	}

	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}


	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(pkCode)) {
			return;
		}
		columns = WaybilltrackstatusDemand.getByWbtsCode(pkCode);
		if (columns == null) {
			return;
		}
		String sql = "update T_DI_WAYBILLTRACKSTATUS t set t.SS_CODE = 'OFF' where t.WBTS_CODE = '" + pkCode + "'";
		execute(objSession, sql);
	}

}
