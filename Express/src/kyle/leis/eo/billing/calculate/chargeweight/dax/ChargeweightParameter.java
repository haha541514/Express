package kyle.leis.eo.billing.calculate.chargeweight.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;

public class ChargeweightParameter extends GeneralColumns {
	
	private List m_listWaybillpieces;
	private String m_strHawbGrossweight;
	
	public ChargeweightParameter() {
		m_astrColumns = new String[9];
	}
	
	public String getDtcode() {
		return this.getField(0);
	}
	
	public void setDtcode(String strDtcode) {
		this.setField(0, strDtcode);
	}
	
	public String getPostcode() {
		return this.getField(1);
	}
	
	public void setPostcode(String strPostcode) {
		this.setField(1, strPostcode);
	}
	
	public String getGrossWeight() {
		return this.getField(2);
	}
	
	public void setGrossWeight(String strGrossWeight) {
		this.setField(2, strGrossWeight);
	}
	
	public String getPkcode() {
		return this.getField(3);
	}
	
	public void setPkcode(String strPkcode) {
		this.setField(3, strPkcode);
	}	

	public String getCocode() {
		return this.getField(4);
	}
	
	public void setCocode(String strCocode) {
		this.setField(4, strCocode);
	}	

	public String getPdcode() {
		return this.getField(5);
	}
	
	public void setPdcode(String strPdcode) {
		this.setField(5, strPdcode);
	}
	
	public String getSearchDate() {
		return this.getField(6);
	}
	
	public void setSearchDate(String strSearchDate) {
		this.setField(6, strSearchDate);
	}
	
	public String getOriginVolumerate() {
		return this.getField(7);
	}
	
	public void setOriginVolumerate(String strOriginVolumerate) {
		this.setField(7, strOriginVolumerate);
	}
	
	public String getVolumeweight() {
		return this.getField(8);
	}
	
	public void setVolumeweight(String strVolumeweight) {
		this.setField(8, strVolumeweight);
	}
	
	public String getHawbGrossweight() {
		return m_strHawbGrossweight;
	}
	
	public void setHawbGrossweight(String strHawbGrossweight) {
		m_strHawbGrossweight = strHawbGrossweight;
	}
	
	public List getWaybillpiecesCollection() {
		return m_listWaybillpieces;
	}
	
	public void setWaybillpiecesCollection(List listWaybillpieces) {
		m_listWaybillpieces = listWaybillpieces;
	}		
}
