package kyle.leis.fs.dictionary.feekind.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.feekind.da.FeekindColumns;
import kyle.leis.fs.dictionary.feekind.dax.FeekindDemand;
import kyle.leis.hi.TdiFeekind;
import net.sf.hibernate.Session;

public class SaveFeekindTransaction extends AbstractTransaction {
	
	private FeekindColumns m_objFeekindColumns;
	private String m_newFkcode;
	
	public String getNewFkcode()
	{
		return this.m_newFkcode;
	}
	
	public void setParam(FeekindColumns objFeekindColumns)
	{
		this.m_objFeekindColumns = objFeekindColumns;
	}
	public void transaction(Session objSession) throws Exception {
		if(m_objFeekindColumns == null ) return;
		
		TdiFeekind objTdiFeekind;
		if(FeekindDemand.queryByFkcode(m_objFeekindColumns.getFkfkcode()) == null)
		{
			objTdiFeekind = new TdiFeekind();
			objTdiFeekind.setFkCode(m_objFeekindColumns.getFkfkcode());
		}
		else
			objTdiFeekind = (TdiFeekind)objSession.load(TdiFeekind.class, m_objFeekindColumns.getFkfkcode());
		
		//objTdiFeekind.setTdiSimplestatus(TdiSimplestatusDC.loadByKey("ON"));
		
		FeekindDemand.setFeekindByColumns(objTdiFeekind, m_objFeekindColumns, objSession);
		objSession.save(objTdiFeekind);
		m_newFkcode = objTdiFeekind.getFkCode();
	}

}
