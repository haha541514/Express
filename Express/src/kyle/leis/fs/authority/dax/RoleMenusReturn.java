package kyle.leis.fs.authority.dax;

import java.util.List;

public class RoleMenusReturn {
	private List m_listRoleMenus;
	private boolean m_isContainException;
	private String m_strErrText;
	
	public void setRoleMenus(List listRoleMenus) {
		m_listRoleMenus = listRoleMenus;
	}
	
	public List getRoleMenus() {
		return m_listRoleMenus;
	}
	
	public void setExceptionSign(boolean isContainException) {
		m_isContainException = isContainException;
	}
	
	public boolean isContainException() {
		return m_isContainException;
	}
	
	public void setErrText(String strErrText) {
		m_strErrText = strErrText;
	}
	
	public String getErrText() {
		return m_strErrText;
	}
	
	public String[] getReturnDescribtion() {
		String[] astr = new String[2];
		astr[0] = String.valueOf(m_isContainException);
		astr[1] = m_strErrText;
		return astr;
	}
}
