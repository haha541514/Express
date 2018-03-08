package kyle.leis.es.businessrule.channeltrackmapping.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.channeltrackmapping.da.ChanneltrackmappingColumns;
import kyle.leis.es.businessrule.channeltrackmapping.dax.ChanneltrackmappingDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TbrChanneltrackmapping;
import kyle.leis.hi.TdiOperator;

public class SaveChanneltrackmapping extends AbstractTransaction {
	private ChanneltrackmappingColumns m_objCTMC;
	private String m_strOperID;
	private String m_strSavedID;
	
	public void setParam(ChanneltrackmappingColumns objCTMC,
			String strOperID) {
		m_objCTMC = objCTMC;
		m_strOperID = strOperID;
	}
	
	public String getSavedID() {
		return m_strSavedID;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		String strCTM_ID = m_objCTMC.getCtmctmid();
		TbrChanneltrackmapping objTCTM;
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperID);
		
		if (StringUtility.isNull(strCTM_ID)) {
			objTCTM = new TbrChanneltrackmapping();
			objTCTM.setTdiOperatorByOpIdCreator(objTdiOperator);
			objTCTM.setCtmCreatedate(DateFormatUtility.getSysdate());
		} else {
			objTCTM = (TbrChanneltrackmapping)objSession.load(TbrChanneltrackmapping.class, 
					Long.parseLong(strCTM_ID));
		}
		objTCTM.setCtmModifydate(DateFormatUtility.getSysdate());
		objTCTM.setTdiOperatorByOpIdModifier(objTdiOperator);
		
		ChanneltrackmappingDemand.setByColumns(m_objCTMC, objTCTM, objSession);
		
		objSession.save(objTCTM);
		
		m_strSavedID = String.valueOf(objTCTM.getCtmId());
	}
}
