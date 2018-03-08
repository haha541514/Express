package kyle.leis.eo.operation.manifest.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ManifestvalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ManifestvalueColumns() {
		
	}
	
	public ManifestvalueColumns(Integer mfvcomp_idMvid, 
            Long mfvcomp_idMfcode, Long hwbHwopidrecord, 
            Date hwbHwrecorddate, Long cwCwcode, 
            int cwCwpieces, BigDecimal cwCwgrossweight, 
            BigDecimal cwCwchargeweight, BigDecimal cwCwtransferpieces, 
            BigDecimal cwCwtransfergrossweight, BigDecimal cwCwtransferchargeweight, 
            String cwCwcustomerewbcode, String cwCwserverewbcode, 
            String cwCwewbcode, String chnspChncode, 
            String chnspChnsname, String chnspChnsename, 
            String pkPkcode, String pkPksename, 
            String ctCtcode, String ctCtname, 
            String pmPmcode, String pmPmname){
		m_astrColumns = new String[23];
		setMfvcomp_idmvid(mfvcomp_idMvid);
		setMfvcomp_idmfcode(mfvcomp_idMfcode);
		setHwbhwopidrecord(hwbHwopidrecord);
		setHwbhwrecorddate(hwbHwrecorddate);
		setCwcwcode(cwCwcode);
		setCwcwpieces(cwCwpieces);
		setCwcwgrossweight(cwCwgrossweight);
		setCwcwchargeweight(cwCwchargeweight);
		setCwcwtransferpieces(cwCwtransferpieces);
		setCwcwtransfergrossweight(cwCwtransfergrossweight);
		setCwcwtransferchargeweight(cwCwtransferchargeweight);
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwserverewbcode(cwCwserverewbcode);
		setCwcwewbcode(cwCwewbcode);
		setChnspchncode(chnspChncode);
		setChnspchnsname(chnspChnsname);
		setChnspchnsename(chnspChnsename);
		setPkpkcode(pkPkcode);
		setPkpksename(pkPksename);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
	}

	public void setMfvcomp_idmvid(Integer mfvcomp_idMvid) {
		this.setField(0, mfvcomp_idMvid);
	}

	public String getMfvcomp_idmvid() {
		return this.getField(0);
	}

	public void setMfvcomp_idmfcode(Long mfvcomp_idMfcode) {
		this.setField(1, mfvcomp_idMfcode);
	}

	public String getMfvcomp_idmfcode() {
		return this.getField(1);
	}

	public void setHwbhwopidrecord(Long hwbHwopidrecord) {
		this.setField(2, hwbHwopidrecord);
	}

	public String getHwbhwopidrecord() {
		return this.getField(2);
	}

	public void setHwbhwrecorddate(Date hwbHwrecorddate) {
		this.setField(3, hwbHwrecorddate);
	}

	public String getHwbhwrecorddate() {
		return this.getField(3);
	}

	public void setCwcwcode(Long cwCwcode) {
		this.setField(4, cwCwcode);
	}

	public String getCwcwcode() {
		return this.getField(4);
	}

	public void setCwcwpieces(int cwCwpieces) {
		this.setField(5, cwCwpieces);
	}

	public String getCwcwpieces() {
		return this.getField(5);
	}

	public void setCwcwgrossweight(BigDecimal cwCwgrossweight) {
		this.setField(6, cwCwgrossweight);
	}

	public String getCwcwgrossweight() {
		return this.getField(6);
	}

	public void setCwcwchargeweight(BigDecimal cwCwchargeweight) {
		this.setField(7, cwCwchargeweight);
	}

	public String getCwcwchargeweight() {
		return this.getField(7);
	}

	public void setCwcwtransferpieces(BigDecimal cwCwtransferpieces) {
		this.setField(8, cwCwtransferpieces);
	}

	public String getCwcwtransferpieces() {
		return this.getField(8);
	}

	public void setCwcwtransfergrossweight(BigDecimal cwCwtransfergrossweight) {
		this.setField(9, cwCwtransfergrossweight);
	}

	public String getCwcwtransfergrossweight() {
		return this.getField(9);
	}

	public void setCwcwtransferchargeweight(BigDecimal cwCwtransferchargeweight) {
		this.setField(10, cwCwtransferchargeweight);
	}

	public String getCwcwtransferchargeweight() {
		return this.getField(10);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(11, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(11);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(12, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(12);
	}

	public void setCwcwewbcode(String cwCwewbcode) {
		this.setField(13, cwCwewbcode);
	}

	public String getCwcwewbcode() {
		return this.getField(13);
	}

	public void setChnspchncode(String chnspChncode) {
		this.setField(14, chnspChncode);
	}

	public String getChnspchncode() {
		return this.getField(14);
	}

	public void setChnspchnsname(String chnspChnsname) {
		this.setField(15, chnspChnsname);
	}

	public String getChnspchnsname() {
		return this.getField(15);
	}

	public void setChnspchnsename(String chnspChnsename) {
		this.setField(16, chnspChnsename);
	}

	public String getChnspchnsename() {
		return this.getField(16);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(17, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(17);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(18, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(18);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(19, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(19);
	}

	public void setCtctname(String ctCtname) {
		this.setField(20, ctCtname);
	}

	public String getCtctname() {
		return this.getField(20);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(21, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(21);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(22, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(22);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
