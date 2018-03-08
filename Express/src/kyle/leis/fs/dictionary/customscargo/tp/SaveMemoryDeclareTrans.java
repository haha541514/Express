package kyle.leis.fs.dictionary.customscargo.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameColumns;
import kyle.leis.fs.dictionary.customscargo.dax.CustomscargoDemand;
import kyle.leis.hi.TdiMemorydeclarename;

public class SaveMemoryDeclareTrans extends AbstractTransaction {

	private MemorydeclarenameColumns m_objMDNColumns; 
	
	public void setParam(MemorydeclarenameColumns objMDNColumns) {
		m_objMDNColumns = objMDNColumns;
	}
	
	public MemorydeclarenameColumns getSavedColumns() {
		return m_objMDNColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objMDNColumns == null)
			return;
		TdiMemorydeclarename objTMDN;
		if (StringUtility.isNull(m_objMDNColumns.getMdnmdncode())) {
			objTMDN = new TdiMemorydeclarename();
			objTMDN.setMdnCode(Long.parseLong(CustomscargoDemand.getMemorydeclarenameseq()));
		} else {
			objTMDN = (TdiMemorydeclarename)objSession.load(TdiMemorydeclarename.class, 
					Long.parseLong(m_objMDNColumns.getMdnmdncode()));
		}
		objTMDN.setMdnEname(m_objMDNColumns.getMdnmdnename());
		objTMDN.setMdnName(m_objMDNColumns.getMdnmdnname());
		objTMDN.setMdnLabelcode(m_objMDNColumns.getMdnmdnlabelcode());
		objSession.save(objTMDN);
		
		m_objMDNColumns.setMdnmdncode(objTMDN.getMdnCode());
	}
}
