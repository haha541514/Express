package kyle.leis.fs.authoritys.role.bl;

import java.util.List;

import kyle.leis.fs.authoritys.role.da.RoleColumns;
import kyle.leis.fs.authoritys.role.da.RoleCondition;
import kyle.leis.fs.authoritys.role.da.RoleQuery;
import kyle.leis.fs.authoritys.role.tp.DeleteRoleTransaction;
import kyle.leis.fs.authoritys.role.tp.SaveRoleTransaction;

public class Role {
	/*
	 * ������ɫ
	 */
	public RoleColumns save(String strOperId, RoleColumns objRoleColumns)
			throws Exception {
		SaveRoleTransaction objSRTrans = new SaveRoleTransaction();
		objSRTrans.setParam(strOperId, objRoleColumns);
		objSRTrans.execute();
		
		RoleCondition objRoleCondition = new RoleCondition();
		objRoleCondition.setRlcode(objSRTrans.getM_strNewRlcode());
		return (RoleColumns)query(objRoleCondition).get(0);
	}

	/*
	 * ��ѯ��ɫ
	 */
	public List query(RoleCondition objRoleCondition) throws Exception {
		RoleQuery objQuery = new RoleQuery();
		objQuery.setCondition(objRoleCondition);	
		return objQuery.getResults();
	}

	/*
	 * ����ƽ̨��ѯ��ɫ
	 */
	public List queryRoleByIskCode(String strIsk_code) throws Exception {
		RoleCondition objRoleConditon = new RoleCondition();
		objRoleConditon.setIsk_code(strIsk_code);
		return query(objRoleConditon);
	}

	/*
	 * ���ݽ�ɫ������ɫ
	 */
	public List queryRoleByRoleCode(String strRole_code) throws Exception {
		RoleCondition objRoleCondition = new RoleCondition();
		objRoleCondition.setRlcode(strRole_code);
		return query(objRoleCondition);
	}

	public void delete(String strRole_code) throws Exception {
		DeleteRoleTransaction objDRTrans = new DeleteRoleTransaction();
		objDRTrans.setParam(strRole_code);
		objDRTrans.execute();
	}
}
