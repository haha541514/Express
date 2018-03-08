package kyle.leis.eo.customerservice.issue.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class IssueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IssueColumns() {
		m_astrColumns = new String[40];
	}

	public IssueColumns(Long isuIsuid, Date isuIsucreatedate,
			Date isuIsumodifydate, String isuIscontent, String isusIsuscode,
			String isusIsusname, String isugIsugcode, String isugIsugname,
			Long copOpid, String copOpname, Long mopOpid, String mopOpname,
			Long eopOpid, String eopOpname, String ihsIhscode,
			String ihsIhsname, String isutIsutcode, String isutIsutname,
			Long cwCwcode, BigDecimal cwCwchargeweight,
			String cwCwcustomerewbcode, String cwCwserverewbcode,
			String cwCwewbcode, String pmPmcode, String pmPmname,
			String ctCtcode, String ctCtname, String ccoCocode,
			String ccoCosname, String ccoColabelcode, String cwihsIhscode,
			String cwihsIhsname, Long ssopOpid, String ssopOpname,
			Long csopOpid, String csopOpname, String eeEecode,
			String eeEesname, String cctCtcode, String cctCtname) {
		m_astrColumns = new String[40];
		setIsuisuid(isuIsuid);
		setIsuisucreatedate(isuIsucreatedate);
		setIsuisumodifydate(isuIsumodifydate);
		setIsuiscontent(isuIscontent);
		setIsusisuscode(isusIsuscode);
		setIsusisusname(isusIsusname);
		setIsugisugcode(isugIsugcode);
		setIsugisugname(isugIsugname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setEopopid(eopOpid);
		setEopopname(eopOpname);
		setIhsihscode(ihsIhscode);
		setIhsihsname(ihsIhsname);
		setIsutisutcode(isutIsutcode);
		setIsutisutname(isutIsutname);
		setCwcwcode(cwCwcode);
		setCwcwchargeweight(cwCwchargeweight);
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwserverewbcode(cwCwserverewbcode);
		setCwcwewbcode(cwCwewbcode);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setCcococode(ccoCocode);
		setCcocosname(ccoCosname);
		setCcocolabelcode(ccoColabelcode);
		setCwihsihscode(cwihsIhscode);
		setCwihsihsname(cwihsIhsname);
		setSsopopid(ssopOpid);
		setSsopopname(ssopOpname);
		setCsopopid(csopOpid);
		setCsopopname(csopOpname);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setCctctcode(cctCtcode);
		setCctctname(cctCtname);
	}

	public void setIsuisuid(Long isuIsuid) {
		this.setField(0, isuIsuid);
	}

	public String getIsuisuid() {
		return this.getField(0);
	}

	public void setIsuisucreatedate(Date isuIsucreatedate) {
		this.setField(1, isuIsucreatedate);
	}

	public String getIsuisucreatedate() {
		return this.getField(1);
	}

	public void setIsuisumodifydate(Date isuIsumodifydate) {
		this.setField(2, isuIsumodifydate);
	}

	public String getIsuisumodifydate() {
		return this.getField(2);
	}

	public void setIsuiscontent(String isuIscontent) {
		this.setField(3, isuIscontent);
	}

	public String getIsuiscontent() {
		return this.getField(3);
	}

	public void setIsusisuscode(String isusIsuscode) {
		this.setField(4, isusIsuscode);
	}

	public String getIsusisuscode() {
		return this.getField(4);
	}

	public void setIsusisusname(String isusIsusname) {
		this.setField(5, isusIsusname);
	}

	public String getIsusisusname() {
		return this.getField(5);
	}

	public void setIsugisugcode(String isugIsugcode) {
		this.setField(6, isugIsugcode);
	}

	public String getIsugisugcode() {
		return this.getField(6);
	}

	public void setIsugisugname(String isugIsugname) {
		this.setField(7, isugIsugname);
	}

	public String getIsugisugname() {
		return this.getField(7);
	}

	public void setCopopid(Long copOpid) {
		this.setField(8, copOpid);
	}

	public String getCopopid() {
		return this.getField(8);
	}

	public void setCopopname(String copOpname) {
		this.setField(9, copOpname);
	}

	public String getCopopname() {
		return this.getField(9);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(10, mopOpid);
	}

	public String getMopopid() {
		return this.getField(10);
	}

	public void setMopopname(String mopOpname) {
		this.setField(11, mopOpname);
	}

	public String getMopopname() {
		return this.getField(11);
	}

	public void setEopopid(Long eopOpid) {
		this.setField(12, eopOpid);
	}

	public String getEopopid() {
		return this.getField(12);
	}

	public void setEopopname(String eopOpname) {
		this.setField(13, eopOpname);
	}

	public String getEopopname() {
		return this.getField(13);
	}

	public void setIhsihscode(String ihsIhscode) {
		this.setField(14, ihsIhscode);
	}

	public String getIhsihscode() {
		return this.getField(14);
	}

	public void setIhsihsname(String ihsIhsname) {
		this.setField(15, ihsIhsname);
	}

	public String getIhsihsname() {
		return this.getField(15);
	}

	public void setIsutisutcode(String isutIsutcode) {
		this.setField(16, isutIsutcode);
	}

	public String getIsutisutcode() {
		return this.getField(16);
	}

	public void setIsutisutname(String isutIsutname) {
		this.setField(17, isutIsutname);
	}

	public String getIsutisutname() {
		return this.getField(17);
	}

	public void setCwcwcode(Long cwCwcode) {
		this.setField(18, cwCwcode);
	}

	public String getCwcwcode() {
		return this.getField(18);
	}

	public void setCwcwchargeweight(BigDecimal cwCwchargeweight) {
		this.setField(19, cwCwchargeweight);
	}

	public String getCwcwchargeweight() {
		return this.getField(19);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(20, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(20);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(21, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(21);
	}

	public void setCwcwewbcode(String cwCwewbcode) {
		this.setField(22, cwCwewbcode);
	}

	public String getCwcwewbcode() {
		return this.getField(22);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(23, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(23);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(24, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(24);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(25, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(25);
	}

	public void setCtctname(String ctCtname) {
		this.setField(26, ctCtname);
	}

	public String getCtctname() {
		return this.getField(26);
	}

	public void setCcococode(String ccoCocode) {
		this.setField(27, ccoCocode);
	}

	public String getCcococode() {
		return this.getField(27);
	}

	public void setCcocosname(String ccoCosname) {
		this.setField(28, ccoCosname);
	}

	public String getCcocosname() {
		return this.getField(28);
	}

	public void setCcocolabelcode(String ccoColabelcode) {
		this.setField(29, ccoColabelcode);
	}

	public String getCcocolabelcode() {
		return this.getField(29);
	}

	public void setCwihsihscode(String cwihsIhscode) {
		this.setField(30, cwihsIhscode);
	}

	public String getCwihsihscode() {
		return this.getField(30);
	}

	public void setCwihsihsname(String cwihsIhsname) {
		this.setField(31, cwihsIhsname);
	}

	public String getCwihsihsname() {
		return this.getField(31);
	}

	public void setSsopopid(Long ssopOpid) {
		this.setField(32, ssopOpid);
	}

	public String getSsopopid() {
		return this.getField(32);
	}

	public void setSsopopname(String ssopOpname) {
		this.setField(33, ssopOpname);
	}

	public String getSsopopname() {
		return this.getField(33);
	}

	public void setCsopopid(Long csopOpid) {
		this.setField(34, csopOpid);
	}

	public String getCsopopid() {
		return this.getField(34);
	}

	public void setCsopopname(String csopOpname) {
		this.setField(35, csopOpname);
	}

	public String getCsopopname() {
		return this.getField(35);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(36, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(36);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(37, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(37);
	}

	public void setCctctname(String cctCtname) {
		this.setField(39, cctCtname);
	}

	public void setCctctcode(String cctCtcode) {
		this.setField(38, cctCtcode);
	}

	public String getCctctcode() {
		return this.getField(38);
	}

	public String getCctctname() {
		return this.getField(39);
	}

	public String toString() {
		return "Code Generate By Kyle";
	}

}
