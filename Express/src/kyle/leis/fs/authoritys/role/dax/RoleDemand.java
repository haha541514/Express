package kyle.leis.fs.authoritys.role.dax;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.authoritys.role.da.RoleColumns;
import kyle.leis.hi.TdiInfomationsystemkind;
import kyle.leis.hi.TfsRole;
import net.sf.hibernate.Session;

public class RoleDemand {

	public static void setTfsRoleByColumns(TfsRole objTfsRole,
			RoleColumns objRoleColumns, String strOperId, Session objSession) throws Exception{
		objTfsRole.setRlEname(objRoleColumns.getRlrlename());
		objTfsRole.setRlModifydate(DateFormatUtility.getSysdate());
		objTfsRole.setRlName(objRoleColumns.getRlrlname());
		objTfsRole.setRlOpIdModifier(Integer.parseInt(strOperId));
		objTfsRole.setRlRemark(objRoleColumns.getRlrlremark());
		objTfsRole.setRlStructurecode(objRoleColumns.getRlrlstructurecode());
		
		
		if(!StringUtility.isNull(objRoleColumns.getRlrladministratorsign()))
			objTfsRole.setRlAdministratorsign(objRoleColumns.getRlrladministratorsign());
		if(!StringUtility.isNull(objRoleColumns.getIskiskcode()))
			objTfsRole.setTdiInfomationsystemkind((TdiInfomationsystemkind)objSession.load(TdiInfomationsystemkind.class, objRoleColumns.getIskiskcode()));
	}
}
