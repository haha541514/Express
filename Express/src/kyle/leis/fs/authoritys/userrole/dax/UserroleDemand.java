package kyle.leis.fs.authoritys.userrole.dax;

import java.util.List;

import kyle.leis.fs.authoritys.userrole.da.UserroleCondition;
import kyle.leis.fs.authoritys.userrole.da.UserroleQuery;
import kyle.leis.hi.TdiInfomationsystemkind;
import kyle.leis.hi.TfsRole;
import kyle.leis.hi.TfsUserrole;
import kyle.leis.hi.TfsUserrolePK;
import net.sf.hibernate.Session;

public class UserroleDemand {
	/*
	 * …Ë÷√
	 */
	public static void setUserroleByColumns(TfsUserrole objTfsUserrole,
			String urUsercode, String rlCode, String iskCode, Session objSession)
			throws Exception {
		// TfsUserrole objTfsUserrole = new TfsUserrole();
		TfsUserrolePK comp_id = new TfsUserrolePK();
		comp_id.setIskCode(iskCode);
		comp_id.setRlCode(rlCode);
		comp_id.setUrUsercode(urUsercode);
		objTfsUserrole.setComp_id(comp_id);

		objTfsUserrole
				.setTdiInfomationsystemkind((TdiInfomationsystemkind) objSession
						.load(TdiInfomationsystemkind.class, iskCode));
		objTfsUserrole.setTfsRole((TfsRole) objSession.load(TfsRole.class,
				rlCode));
	}
	
	public static List query(UserroleCondition objUserroleCondition) throws Exception
	{
		UserroleQuery objUserroleQuery = new UserroleQuery();
		objUserroleQuery.setCondition(objUserroleCondition);
		return objUserroleQuery.getResults();
	}
	
}
