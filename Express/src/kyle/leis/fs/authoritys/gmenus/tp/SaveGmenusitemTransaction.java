package kyle.leis.fs.authoritys.gmenus.tp;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import kyle.common.dbaccess.session.HSessionFactory;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.authoritys.gmenus.da.GmenusColumns;

import kyle.leis.fs.authoritys.gmenus.dax.GmenusDemand;
import kyle.leis.fs.authoritys.gmenus.dax.GmenusItemDemand;
import kyle.leis.hi.TfsGuimenu;

public class SaveGmenusitemTransaction extends AbstractTransaction {

	private String m_strNewgmcode;
	private GmenusColumns m_objGmenusColumns;
	private String m_gmicontend;
	public void setParam(GmenusColumns objGmenusColumns,String m_objgmicontend)
	{
		m_gmicontend=m_objgmicontend;
		m_objGmenusColumns = objGmenusColumns;
	}
	public void transaction(Session objSession) throws Exception {
		
		//TfsGuimenu objTfsGuimenu = null;
		if(m_objGmenusColumns == null) return;
		TfsGuimenu objTfsGuimenu=null;
		Session objSession2 = HSessionFactory.getTransSession();
		Transaction objTP = null;
		try {
			if (objSession2 == null)
				throw (new Exception("无法获取连接"));
			objTP = objSession2.beginTransaction();
		if(StringUtility.isNull(m_objGmenusColumns.getGmgmcode())){
			objTfsGuimenu = new TfsGuimenu();
			String newGmCode=GmenusItemDemand.getNewGmenusCode();
			objTfsGuimenu.setGmCode(newGmCode);
		}else{
			objTfsGuimenu  = (TfsGuimenu)objSession2.load(TfsGuimenu.class, m_objGmenusColumns.getGmgmcode());
		}		
		GmenusDemand.setGmenusColumns(objTfsGuimenu, m_objGmenusColumns, objSession2);
		objSession2.save(objTfsGuimenu);
		m_strNewgmcode = objTfsGuimenu.getGmCode();
		objTP.commit();
		} catch(HibernateException objHE) {
			if (objTP != null) {
				objHE.printStackTrace();
				objTP.rollback();
				throw (new Exception(objHE.getMessage()));
			}
		} catch(Exception objEX) {
			if (objTP != null) {
				objEX.printStackTrace();
				objTP.rollback();
				throw objEX;
			}			
		}
		if(!StringUtility.isNull(m_gmicontend)){
			//保存内容
			List listGmitem=GmenusItemDemand.queryByGmicode(m_strNewgmcode);
			if(listGmitem!=null && listGmitem.size()>=0){
				String strDeleteSql="delete from t_Fs_Guimenuitem where gm_code='"+m_strNewgmcode+"'";
				execute(objSession,strDeleteSql,"");	
			}			
			String strupdateSql="insert into t_Fs_Guimenuitem(gm_code,Gmi_Content) values('"+m_strNewgmcode+"',?)";
			execute(objSession,strupdateSql,m_gmicontend);
		}				
	}
	public String getM_strNewgmcode() {
		return m_strNewgmcode;
	}
	public void setM_strNewgmcode(String newgmcode) {
		m_strNewgmcode = newgmcode;
	}

}
