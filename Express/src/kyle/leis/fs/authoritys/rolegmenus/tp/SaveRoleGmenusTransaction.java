package kyle.leis.fs.authoritys.rolegmenus.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.authoritys.rolegmenus.da.RolegmenusColumns;
import kyle.leis.fs.authoritys.rolegmenus.dax.RoleGmenusDemand;
import kyle.leis.hi.TfsRolemenu;
import kyle.leis.hi.TfsRolemenuPK;
import net.sf.hibernate.Session;

public class SaveRoleGmenusTransaction extends AbstractTransaction {

	private String m_strOperId;
	private String[] m_astrRole_code;
	private String[] m_astrMenus_code;

	public void setParam(String[] astrRole_code, String[] astrMenus_code,
			String strOperId) {
		m_astrRole_code = astrRole_code;
		m_astrMenus_code = astrMenus_code;
		m_strOperId = strOperId;
	}

	public void transaction(Session objSession) throws Exception {
		if (m_astrRole_code == null || m_astrRole_code.length < 1
				|| StringUtility.isNull(m_strOperId) || m_strOperId.equals(""))
			return;
		for (int i = 0; i < m_astrRole_code.length; i++) {
			List<RolegmenusColumns> listRGMColumns = (List<RolegmenusColumns>) RoleGmenusDemand
					.queryByRlcode(m_astrRole_code[i]);

			if (listRGMColumns != null && listRGMColumns.size() > 0)// 修改时先删除
				objSession
						.delete("from TfsRolemenu rm where rm.comp_id.rlCode = "
								+ m_astrRole_code[i] + " '");
			if (m_astrMenus_code == null || m_astrMenus_code.length < 1)
				continue;
			for (int j = 0; j < m_astrMenus_code.length; j++) {
				TfsRolemenu objTfsRolemenu = new TfsRolemenu();
				if (m_astrMenus_code[j].equals(""))
					break;
				for (int x = 0; x < listRGMColumns.size(); x++) {
					RolegmenusColumns objRGMColumns = listRGMColumns.get(x);
					if (objRGMColumns == null)
						break;
					if (m_astrMenus_code[j].equals(objRGMColumns.getGmgmcode())) {
						objTfsRolemenu.setRmOpIdCreator(Integer
								.parseInt(objRGMColumns.getRgmrmopidcreator()));
						objTfsRolemenu.setRmCreatedate(DateFormatUtility
								.getStandardDate(objRGMColumns
										.getRgmrmcreatedate()));
					}
				}
				objTfsRolemenu.setRmOpIdCreator(Integer.parseInt(m_strOperId));
				objTfsRolemenu.setRmCreatedate(DateFormatUtility.getSysdate());
				TfsRolemenuPK objTfsRolemenuPK = new TfsRolemenuPK();
				objTfsRolemenuPK.setRlCode(m_astrRole_code[i]);
				objTfsRolemenuPK.setGmCode(m_astrMenus_code[j]);// 需判断菜单是否为空
				objTfsRolemenu.setComp_id(objTfsRolemenuPK);
				objTfsRolemenu.setRmModifydate(DateFormatUtility.getSysdate());
				objTfsRolemenu.setRmOpIdModifier(Integer.parseInt(m_strOperId));

				objSession.save(objTfsRolemenu);
			}
		}
	}

}
