package kyle.leis.es.price.incidentalprice.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class IncidentalpricevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IncidentalpricevalueColumns() {
		m_astrColumns = new String[23];
	}
	
	public IncidentalpricevalueColumns(Long ipvcomp_idEpcode, 
            Integer ipvcomp_idIpvid, String ipvIpvmode, 
            String ipvIpvautocalculatesign, String ipvIpvreversesign, 
            BigDecimal ipvIpvminimumvalue, BigDecimal ipvIpvpricevalue, 
            BigDecimal ipvIpvcommissionrate, String ipvIpvremark, 
            String ckCkcode, String ckCkname, 
            String pmPmcode, String pmPmname, 
            String fkFkcode, String fkFkname, 
            String utUtcode, String utUtname, 
            String estEstcode, String estEstname, 
            String ctCtcode, String ctCtname, 
            BigDecimal ipvIpvcarryweight, BigDecimal ipvIpvmaxvalue){
		m_astrColumns = new String[23];
		setIpvcomp_idepcode(ipvcomp_idEpcode);
		setIpvcomp_idipvid(ipvcomp_idIpvid);
		setIpvipvmode(ipvIpvmode);
		setIpvipvautocalculatesign(ipvIpvautocalculatesign);
		setIpvipvreversesign(ipvIpvreversesign);
		setIpvipvminimumvalue(ipvIpvminimumvalue);
		setIpvipvpricevalue(ipvIpvpricevalue);
		setIpvipvcommissionrate(ipvIpvcommissionrate);
		setIpvipvremark(ipvIpvremark);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
		setUtutcode(utUtcode);
		setUtutname(utUtname);
		setEstestcode(estEstcode);
		setEstestname(estEstname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setIpvipvcarryweight(ipvIpvcarryweight);
		setIpvipvmaxvalue(ipvIpvmaxvalue);
	}

	public void setIpvcomp_idepcode(Long ipvcomp_idEpcode) {
		this.setField(0, ipvcomp_idEpcode);
	}

	public String getIpvcomp_idepcode() {
		return this.getField(0);
	}

	public void setIpvcomp_idipvid(Integer ipvcomp_idIpvid) {
		this.setField(1, ipvcomp_idIpvid);
	}

	public String getIpvcomp_idipvid() {
		return this.getField(1);
	}

	public void setIpvipvmode(String ipvIpvmode) {
		this.setField(2, ipvIpvmode);
	}

	public String getIpvipvmode() {
		return this.getField(2);
	}

	public void setIpvipvautocalculatesign(String ipvIpvautocalculatesign) {
		this.setField(3, ipvIpvautocalculatesign);
	}

	public String getIpvipvautocalculatesign() {
		return this.getField(3);
	}

	public void setIpvipvreversesign(String ipvIpvreversesign) {
		this.setField(4, ipvIpvreversesign);
	}

	public String getIpvipvreversesign() {
		return this.getField(4);
	}

	public void setIpvipvminimumvalue(BigDecimal ipvIpvminimumvalue) {
		this.setField(5, ipvIpvminimumvalue);
	}

	public String getIpvipvminimumvalue() {
		return this.getField(5);
	}

	public void setIpvipvpricevalue(BigDecimal ipvIpvpricevalue) {
		this.setField(6, ipvIpvpricevalue);
	}

	public String getIpvipvpricevalue() {
		return this.getField(6);
	}

	public void setIpvipvcommissionrate(BigDecimal ipvIpvcommissionrate) {
		this.setField(7, ipvIpvcommissionrate);
	}

	public String getIpvipvcommissionrate() {
		return this.getField(7);
	}

	public void setIpvipvremark(String ipvIpvremark) {
		this.setField(8, ipvIpvremark);
	}

	public String getIpvipvremark() {
		return this.getField(8);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(9, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(9);
	}

	public void setCkckname(String ckCkname) {
		this.setField(10, ckCkname);
	}

	public String getCkckname() {
		return this.getField(10);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(11, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(11);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(12, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(12);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(13, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(13);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(14, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(14);
	}

	public void setUtutcode(String utUtcode) {
		this.setField(15, utUtcode);
	}

	public String getUtutcode() {
		return this.getField(15);
	}

	public void setUtutname(String utUtname) {
		this.setField(16, utUtname);
	}

	public String getUtutname() {
		return this.getField(16);
	}

	public void setEstestcode(String estEstcode) {
		this.setField(17, estEstcode);
	}

	public String getEstestcode() {
		return this.getField(17);
	}

	public void setEstestname(String estEstname) {
		this.setField(18, estEstname);
	}

	public String getEstestname() {
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

	public void setIpvipvcarryweight(BigDecimal ipvIpvcarryweight) {
		this.setField(21, ipvIpvcarryweight);
	}

	public String getIpvipvcarryweight() {
		return this.getField(21);
	}

	public void setIpvipvmaxvalue(BigDecimal ipvIpvmaxvalue) {
		this.setField(22, ipvIpvmaxvalue);
	}

	public String getIpvipvmaxvalue() {
		return this.getField(22);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
