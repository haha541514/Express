package kyle.leis.es.ruledate;

public class RuleCheckReturn {
	private String m_strOldStartDate;
	private String m_strOldEndDate;
	private String m_strNewStartDate;
	private String m_strNewEndDate;
	private String m_strRulecode;
	private String m_strAtcode;
	
	public static final String ACTIONTYPE_ELIMINATE = "EL";
	public static final String ACTIONTYPE_MODIFYDATE = "MD";
	public static final String ACTIONTYPE_NEW = "NW";
	
	private String[] m_astrFields;
	
	public RuleCheckReturn() {
		m_astrFields = new String[6];
	}
	
	public String getOldStartDate() {
		return m_strOldStartDate;
	}
	
	public void setOldStartDate(String strOldStartDate) {
		m_strOldStartDate = strOldStartDate;
		setField(0, strOldStartDate);
	}
	
	public String getOldEndDate() {
		return m_strOldEndDate;
	}
	
	public void setOldEndDate(String strOldEndDate) {
		m_strOldEndDate = strOldEndDate;
		setField(1, strOldEndDate);
	}

	public String getNewStartDate() {
		return m_strNewStartDate;
	}
	
	public void setNewStartDate(String strNewStartDate) {
		m_strNewStartDate = strNewStartDate;
		setField(2, strNewStartDate);
	}
	
	public String getNewEndDate() {
		return m_strNewEndDate;
	}
	
	public void setNewEndDate(String strNewEndDate) {
		m_strNewEndDate = strNewEndDate;
		setField(3, strNewEndDate);
	}	

	public String getRulecode() {
		return m_strRulecode;
	}
	
	public void setRulecode(String strRulecode) {
		m_strRulecode = strRulecode;
		setField(4, strRulecode);
	}
	
	public String getAtcode() {
		return m_strAtcode;
	}
	
	public void setAtcode(String strAtcode) {
		m_strAtcode = strAtcode;
		setField(5, strAtcode);
	}
	
	public void setField(int iIndex, String strValue) {
		m_astrFields[iIndex] = strValue;
	}
	
	public String[] getStringArray() {
		return m_astrFields;
	}
}
