package kyle.leis.es.systemproperty.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.es.systemproperty.da.SystempropertyColumns;
import kyle.leis.hi.TesSystemproperty;
import kyle.leis.hi.TesSystempropertyPK;
import net.sf.hibernate.Session;

public class SaveSystemPropertyTransaction extends AbstractTransaction {

	private List<SystempropertyColumns> m_listSPColumns;
	private String m_strEecode;
	
	public void setParam(List<SystempropertyColumns> listSPColumns)
	{
		this.m_listSPColumns = listSPColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(CollectionUtility.isNull(m_listSPColumns)) return;
		
		m_strEecode = m_listSPColumns.get(0).getEeeecode();
		for(int i=0;i<m_listSPColumns.size();i++)
		{
			TesSystempropertyPK objPK = new TesSystempropertyPK();
			objPK.setEeCode(m_strEecode);
			objPK.setSpCode(m_listSPColumns.get(i).getSpcomp_idspcode());
			TesSystemproperty objTesSystemproperty = (TesSystemproperty)objSession.load(TesSystemproperty.class,objPK);
			objTesSystemproperty.setSpValue(m_listSPColumns.get(i).getSpspvalue());
			objSession.save(objTesSystemproperty);
		}
	}
	
	public String getEecode()
	{
		return m_strEecode;
	}
}
