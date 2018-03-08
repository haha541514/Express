package kyle.leis.fs.dictionary.district.dax;

import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;

public class DHLRemoteVerifyResult {
	private boolean m_isDHLRemoteDistrict = false;
	private boolean m_isPostcodeRemote = false;
	private boolean m_isAutoVerifysign = true;
	private boolean m_isSettintChannelNull = false;
	private DhlremotedistrictColumns m_objDRDColumns;
	
	public boolean getDHLRemoteVerifyResult() {
		return m_isDHLRemoteDistrict;
	}
	
	public void setDHLRemoteVerifyResult(boolean isDHLRemoteDistrict) {
		m_isDHLRemoteDistrict = isDHLRemoteDistrict;
	}

	public boolean getAutoVerifysign() {
		return m_isAutoVerifysign;
	}
	
	public void setAutoVerifysign(boolean isAutoVerifysign) {
		m_isAutoVerifysign = isAutoVerifysign;
	}	
	
	public boolean getPostcodeRemoteSign() {
		return m_isPostcodeRemote;
	}
	
	public void setPostcodeRemoteSign(boolean isPostcodeRemote) {
		m_isPostcodeRemote = isPostcodeRemote;
	}
	
	public boolean getChannelNullsign() {
		return m_isSettintChannelNull;
	}
	
	public void setChannelNullsign(boolean isSettintChannelNull) {
		m_isSettintChannelNull = isSettintChannelNull;
	}	
	
	
	public String getRemark() {
		// 效验结果是ODA，则记录之
		DhlremotedistrictColumns objDRDColumns = getDHLRemoteDistrict();
		if (objDRDColumns == null)
			return "";
		String strRemark = "偏远城市国家为：" + objDRDColumns.getDrddrd_nationname() +
		"偏远城市邮编为：" + objDRDColumns.getDrddrd_postcode() + 
		"偏远城市名为：" + objDRDColumns.getDrddrd_cityname();
		if (getPostcodeRemoteSign())
			strRemark = "国家+邮编是偏远地区，" + strRemark;
		else
			strRemark = "国家+城市是偏远地区，" + strRemark;
		return strRemark;
	}
	
	public DhlremotedistrictColumns getDHLRemoteDistrict() {
		return m_objDRDColumns;
	}
	
	public void setDHLRemoteDistrict(DhlremotedistrictColumns objDRDColumns) {
		m_objDRDColumns = objDRDColumns;
	}
	
	public String[] toStringArray() {
		String[] astr = new String[5];
		astr[0] = String.valueOf(m_isDHLRemoteDistrict);
		astr[1] = String.valueOf(m_isPostcodeRemote);
		astr[2] = String.valueOf(m_isAutoVerifysign);
		astr[3] = String.valueOf(m_isSettintChannelNull);
		astr[4] = getRemark();
		return astr;
	}
}
