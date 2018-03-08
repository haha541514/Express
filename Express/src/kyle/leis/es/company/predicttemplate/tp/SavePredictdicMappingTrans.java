package kyle.leis.es.company.predicttemplate.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.predicttemplate.da.PredictdicmappingColumns;
import kyle.leis.es.company.predicttemplate.dax.PreictorderdicmappingDemand;
import kyle.leis.hi.TcoPreictorderdicmapping;
import net.sf.hibernate.Session;

/**
 * @author Synchrn
 * @date:2012-5-17
 * @version :1.0
 * 
 */
public class SavePredictdicMappingTrans extends AbstractTransaction {
	private PredictdicmappingColumns m_objColumns;

	public void setParam(PredictdicmappingColumns objColumns) {
		m_objColumns = objColumns;
	}

	public void transaction(Session objSession) throws Exception {
		if (this.m_objColumns == null)
			return;
		TcoPreictorderdicmapping objTPOM = null;
		// String strValue = m_objColumns.getPodmpodmoriginvalue();
		// ÐÞ¸Ä
		if (!StringUtility.isNull(m_objColumns.getPodmpodmid())) {
			objTPOM = (TcoPreictorderdicmapping) objSession.load(
					TcoPreictorderdicmapping.class, Long.valueOf(m_objColumns
							.getPodmpodmid()));
		} else {
			// ÐÂÔö
			// objSession.delete(" from TcoPreictorderdicmapping as tm where tm.podmOriginvalue = '"+strValue+"'");
			objTPOM = new TcoPreictorderdicmapping();
		}
		System.out.println(m_objColumns.getDmkdmkcode() + ","
				+ m_objColumns.getPodmpodmoriginvalue() + ","
				+ m_objColumns.getPotpotid() + ","
				+ m_objColumns.getPodmpodmstandardvalue());
		PreictorderdicmappingDemand.setColumns(objTPOM, m_objColumns,
				objSession);
		objSession.save(objTPOM);

	}
}
