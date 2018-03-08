package kyle.leis.fs.dictionary.operator.dax;

import java.util.List;

import kyle.leis.fs.dictionary.operator.da.OperatorColumns;

public class OperatorLoginReturn {
	private boolean m_bLoginStatus;
	private String m_strErrText;
	private List m_listRoleMenus;
	private OperatorColumns m_objOperatorColumns;
	
	public void setLoginStatus(boolean bLoginStatus) {
		m_bLoginStatus = bLoginStatus;
	}
	
	public boolean getLoginStatus() {
		return m_bLoginStatus;
	}
	
	public void setErrText(String strErrText) {
		m_strErrText = strErrText;
	}
	
	public String getErrText() {
		return m_strErrText;
	}
	
	public void setOperatorColumns(OperatorColumns objOperatorColumns) {
		m_objOperatorColumns = objOperatorColumns;
	}
	
	public OperatorColumns getOperatorColumns() {
		return m_objOperatorColumns;
	}
	
	public void setRoleMenus(List listRoleMenus) {
		m_listRoleMenus = listRoleMenus;
	}
	
	public List getRoleMenus() {
		return m_listRoleMenus;
	}
	
	public String[] getLoginReturn() {
		String[] astrLoginReturn = new String[2];
		astrLoginReturn[0] = String.valueOf(m_bLoginStatus);
		astrLoginReturn[1] = m_strErrText;
		return astrLoginReturn;
	}
}
