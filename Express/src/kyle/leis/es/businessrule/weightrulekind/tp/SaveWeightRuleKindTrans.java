package kyle.leis.es.businessrule.weightrulekind.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindColumns;
import kyle.leis.es.businessrule.weightrulekind.dax.WeightRuleKindDemand;
import kyle.leis.hi.TbrWeightrulekind;

public class SaveWeightRuleKindTrans extends AbstractTransaction {
    private WeightrulekindColumns m_objWRKColumns;
    private String m_strOperId;
    private Long m_lNewWrkid;
    
	public void setParam(WeightrulekindColumns objWRKColumns,
			String strOperId) {
		m_objWRKColumns = objWRKColumns;
		m_strOperId = strOperId;
	}
	
	public Long getNewWrkid() {
		return m_lNewWrkid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objWRKColumns == null) return;
		
		TbrWeightrulekind objTbrWeightrulekind = null;
		if (StringUtility.isNull(m_objWRKColumns.getWrkwrkid()))
			objTbrWeightrulekind = new TbrWeightrulekind();
		else {
			objTbrWeightrulekind = (TbrWeightrulekind)objSession.load(TbrWeightrulekind.class, 
					Long.parseLong(m_objWRKColumns.getWrkwrkid()));
		}
		if (objTbrWeightrulekind == null) return;
		
		WeightRuleKindDemand.setWeightrulekindByColumns(objTbrWeightrulekind, 
				m_objWRKColumns, 
				m_strOperId, 
				objSession);
		objSession.save(objTbrWeightrulekind);
		m_lNewWrkid = objTbrWeightrulekind.getWrkId();
	}

}
