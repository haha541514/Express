package kyle.leis.fs.authoritys.gmenus.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.authoritys.gmenus.da.GmenusColumns;
import kyle.leis.fs.authoritys.gmenus.dax.GmenusDemand;
import kyle.leis.hi.TfsGuimenu;
import net.sf.hibernate.Session;

public class SaveGmenusTransaction extends AbstractTransaction {

	private String m_strNewgmcode;
	private GmenusColumns m_objGmenusColumns;
	public void setParam(GmenusColumns objGmenusColumns)
	{
		m_objGmenusColumns = objGmenusColumns;
	}
	public void transaction(Session objSession) throws Exception {
		TfsGuimenu objTfsGuimenu = null;
		if(m_objGmenusColumns == null) return;
		if(StringUtility.isNull(m_objGmenusColumns.getGmgmcode()))
			objTfsGuimenu = new TfsGuimenu();
		else
			objTfsGuimenu  = (TfsGuimenu)objSession.load(TfsGuimenu.class, objTfsGuimenu.getGmCode());
		
		GmenusDemand.setGmenusColumns(objTfsGuimenu, m_objGmenusColumns, objSession);
		objSession.save(objTfsGuimenu);
		m_strNewgmcode = objTfsGuimenu.getGmCode();
	}
	public String getM_strNewgmcode() {
		return m_strNewgmcode;
	}
	public void setM_strNewgmcode(String newgmcode) {
		m_strNewgmcode = newgmcode;
	}

}
