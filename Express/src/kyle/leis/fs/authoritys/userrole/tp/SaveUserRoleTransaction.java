package kyle.leis.fs.authoritys.userrole.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.hi.TfsUserrole;
import kyle.leis.hi.TfsUserrolePK;
import net.sf.hibernate.Session;

public class SaveUserRoleTransaction extends AbstractTransaction {

	private String m_strUser_code;
	private String[] m_astrRole_code;
	private String m_strIsk_code;
	
	public void setParam(String strUser_code,String[] astrRole_code,String strIsk_code)
	{
		m_strUser_code = strUser_code;
		m_astrRole_code = astrRole_code;
		m_strIsk_code = strIsk_code;
	}
	public void transaction(Session objSession) throws Exception {
		TfsUserrole objTfsUserrole = null;
		//先删除用户角色对应表
		if(m_strUser_code !=null && !m_strUser_code.equals(""))
		{
			/*DeleteUserRoleTransaction objDURTrans = new DeleteUserRoleTransaction();
			objDURTrans.setParam(m_strUser_code, null, m_strIsk_code);
			objDURTrans.execute();*///可根据DeleteUserRoleTransaction.delete()的参数不同来删除(...!安全性)
			
			objSession.delete("from TfsUserrole ul where ul.comp_id.urUsercode ='"
					+ m_strUser_code + "' and ul.comp_id.iskCode ='"
					+ m_strIsk_code + "'"); 
		}
		if(m_astrRole_code != null && m_astrRole_code.length>0)
		{
			for(int i=0;i<m_astrRole_code.length;i++)
			{
				objTfsUserrole = new TfsUserrole();
				
				TfsUserrolePK comp_id = new TfsUserrolePK();
				if(m_strIsk_code == null || m_strIsk_code.equals(""))
					comp_id.setIskCode("LEDIS");
				else
					comp_id.setIskCode(m_strIsk_code);
				if(m_astrRole_code[i] == null || m_astrRole_code[i].equals("")) continue; 
				comp_id.setRlCode(m_astrRole_code[i]);
				comp_id.setUrUsercode(m_strUser_code);
				
				objTfsUserrole.setComp_id(comp_id);
				objSession.save(objTfsUserrole);
			}
		}
	}

}
