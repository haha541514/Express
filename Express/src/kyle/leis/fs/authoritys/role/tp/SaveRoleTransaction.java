package kyle.leis.fs.authoritys.role.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.authoritys.role.da.RlseqColumns;
import kyle.leis.fs.authoritys.role.da.RlseqQuery;
import kyle.leis.fs.authoritys.role.da.RoleColumns;
import kyle.leis.fs.authoritys.role.dax.RoleDemand;
import kyle.leis.hi.TfsRole;
import net.sf.hibernate.Session;

public class SaveRoleTransaction extends AbstractTransaction {

	private String m_strNewRlcode;
	private String m_strOperId;
	private RoleColumns m_objRoleColumns;

	public void setParam(String strOperId, RoleColumns objRoleColumns) {
		m_strOperId = strOperId;
		m_objRoleColumns = objRoleColumns;
	}

	public void transaction(Session objSession) throws Exception {
		if (m_objRoleColumns == null)
			return;
		TfsRole objTfsRole = null;
		if (StringUtility.isNull(m_objRoleColumns.getRlrlcode())) {
			objTfsRole = new TfsRole();
			// 序列S_RL_Code
			RlseqQuery objRlseqQuery = new RlseqQuery();
			List objList = objRlseqQuery.getResults();
			if(objList == null || objList.size()<0)System.out.println("出错：没有序列!");
			RlseqColumns objRlseqColumns = (RlseqColumns)objList.get(0);
			objTfsRole.setRlCode(objRlseqColumns.getRlseq());
			
			objTfsRole.setRlOpIdCreator(Integer.parseInt(m_strOperId));
			objTfsRole.setRlCreatedate(DateFormatUtility.getSysdate());
		} else {
			objTfsRole = (TfsRole) objSession.load(TfsRole.class,
					m_objRoleColumns.getRlrlcode());
		}
		RoleDemand.setTfsRoleByColumns(objTfsRole, m_objRoleColumns,
				m_strOperId, objSession);
		objSession.save(objTfsRole);
		setM_strNewRlcode(objTfsRole.getRlCode());
	}

	public String getM_strNewRlcode() {
		return m_strNewRlcode;
	}

	public void setM_strNewRlcode(String newRlcode) {
		m_strNewRlcode = newRlcode;
	}
	
}
