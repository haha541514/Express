package kyle.leis.eo.billing.calculate.chargeweight.dax;

public class ChargeweightResult {
	private String m_strChargeweight;
	private String m_strVolumeweight;
	private String m_strVolumeRate;
	private String m_strCarryweight;
	private String m_strExpression;
	private String m_strServerweightrulekind;
	private String m_strTransferWRK;
	private String m_strGrossweight;
	private String m_strResultChargeweight;
	
	public void setChargeweight(String strChargeweight) {
		m_strChargeweight = strChargeweight;
	}
	
	public String getChargeweight() {
		return m_strChargeweight;
	}
	
	public void setVolumeweight(String strVolumeweight) {
		m_strVolumeweight = strVolumeweight;
	}
	
	public String getVolumeweight() {
		return m_strVolumeweight;
	}
	
	public void setVolumeRate(String strVolumeRate) {
		m_strVolumeRate = strVolumeRate;
	}
	
	public String getVolumeRate() {
		return m_strVolumeRate;
	}
	
	public void setCarryweight(String strCarryweight) {
		m_strCarryweight = strCarryweight;
	}
	
	public String getCarryweight() {
		return m_strCarryweight;
	}	

	public void setExpression(String strExpression) {
		m_strExpression = strExpression;
	}
	
	public String getExpression() {
		return m_strExpression;
	}	
	
	public void setServerweightrulekind(String strServerweightrulekind) {
		m_strServerweightrulekind = strServerweightrulekind;
	}
	
	public String getServerweightrulekind() {
		return m_strServerweightrulekind;
	}
	
	public void setTransferWRK(String strTransferWRK) {
		m_strTransferWRK = strTransferWRK;
	}
	
	public String getTransferWRK() {
		return m_strTransferWRK;
	}	
	
	
	public void setGrossweight(String strGrossweight) {
		m_strGrossweight = strGrossweight;
	}
	
	public String getGrossweight() {
		return m_strGrossweight;
	}	
	
	public void setResultChargeweight(String strResultChargeweight) {
		m_strResultChargeweight = strResultChargeweight;
	}
	
	public String getResultChargeweight() {
		return m_strResultChargeweight;
	}	
	
	public String[] toStringArray() {
		String[] astr = new String[7];
		astr[0] = m_strChargeweight;
		astr[1] = m_strVolumeweight;
		astr[2] = m_strVolumeRate;
		astr[3] = m_strCarryweight;
		astr[4] = m_strExpression;
		astr[5] = m_strServerweightrulekind;
		astr[6] = m_strGrossweight;
		return astr;
	}
}
