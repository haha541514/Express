package kyle.leis.es.price.incidentalprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IncidentalpricevalueQuery extends HGeneralQuery {
	
	public IncidentalpricevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.incidentalprice.da.IncidentalpricevalueColumns(ipv.comp_id.epCode,ipv.comp_id.ipvId,ipv.ipvMode,ipv.ipvAutocalculatesign,ipv.ipvReversesign,ipv.ipvMinimumvalue,ipv.ipvPricevalue,ipv.ipvCommissionrate,ipv.ipvRemark,ck.ckCode,ck.ckName,pm.pmCode,pm.pmName,fk.fkCode,fk.fkName,ut.utCode,ut.utName,est.estCode,est.estName,ct.ctCode,ct.ctName,ipv.ipvCarryweight,ipv.ipvMaxvalue) FROM TepIncidentalpricevalue as ipv inner join ipv.tepIncidentalprice as ip inner join ipv.tdiCurrencykind as ck inner join ipv.tdiPaymentmode as pm inner join ipv.tdiFeekind as fk inner join ipv.tdiUnittype as ut left join ipv.tdiExpressspecialtype as est inner join ipv.tdiCargotype as ct";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ipv.comp_id.epCode = ~~", "ipv.comp_id.ipvId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setIpvid(String ipvId) {
		this.setField(1, ipvId);
	}

	public String getIpvid() {
		return this.getField(1);
	}

}
