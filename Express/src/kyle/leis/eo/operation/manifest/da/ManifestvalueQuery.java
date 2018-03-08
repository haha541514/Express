package kyle.leis.eo.operation.manifest.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ManifestvalueQuery extends HGeneralQuery {
	
	public ManifestvalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.manifest.da.ManifestvalueColumns(mfv.comp_id.mvId, mfv.comp_id.mfCode, hwb.hwOpIdRecord, hwb.hwRecorddate, cw.cwCode, cw.cwPieces, cw.cwGrossweight, cw.cwChargeweight, cw.cwTransferpieces, cw.cwTransfergrossweight, cw.cwTransferchargeweight, cw.cwCustomerewbcode, cw.cwServerewbcode, cw.cwEwbcode, chnsp.chnCode, chnsp.chnSname, chnsp.chnSename, pk.pkCode, pk.pkSename, ct.ctCode, ct.ctName, pm.pmCode, pm.pmName) FROM TopManifestvalue as mfv inner join mfv.topCorewaybill as cw inner join cw.topHousewaybill as hwb inner join cw.tchnChannelByChnCodeSupplier as chnsp inner join cw.tdiProductkind as pk inner join cw.tdiCargotype as ct inner join cw.tdiPaymentmode as pm";
	    m_strWhereClause = "";
	    m_strOrderByClause = "cw.cwServerewbcode";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "mfv.comp_id.mvId = ~~", "mfv.comp_id.mfCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMvid(String mvId) {
		this.setField(0, mvId);
	}

	public String getMvid() {
		return this.getField(0);
	}

	public void setMfcode(String mfCode) {
		this.setField(1, mfCode);
	}

	public String getMfcode() {
		return this.getField(1);
	}

}
