package kyle.leis.fs.dictionary.transporttool.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.transporttool.da.TransporttoolColumns;
import kyle.leis.fs.dictionary.transporttool.dax.TransporttoolDemand;
import kyle.leis.hi.TdiTrasporttool;
import net.sf.hibernate.Session;

public class SaveTransporttoolTransaction extends AbstractTransaction {
	
	private String m_strNewTtCode;
	private TransporttoolColumns m_objTransporttoolCol;
	
	public String getNewTtCode()
	{
		return this.m_strNewTtCode;
	}
	
	public void setParame(TransporttoolColumns objTransporttoolCol)
	{
		this.m_objTransporttoolCol = objTransporttoolCol;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_objTransporttoolCol == null || StringUtility.isNull(m_objTransporttoolCol.getTtttcode())) return;
		
		TdiTrasporttool objTdiTransporttool;
		if(TransporttoolDemand.queryByTtcode(m_objTransporttoolCol.getTtttcode()) != null)
			objTdiTransporttool = (TdiTrasporttool)objSession.load(TdiTrasporttool.class, m_objTransporttoolCol.getTtttcode());
		else
		{
			objTdiTransporttool = new TdiTrasporttool();
			objTdiTransporttool.setTtCode(m_objTransporttoolCol.getTtttcode());
		}
		
		TransporttoolDemand.setTransporttoolByCol(objTdiTransporttool, m_objTransporttoolCol);
		objSession.save(objTdiTransporttool);
		
		m_strNewTtCode = objTdiTransporttool.getTtCode();
	}

}
