package kyle.leis.es.systemcertification.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class SystemcertificationColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SystemcertificationColumns() {
		m_astrColumns = new String[22];
	}
	
	public SystemcertificationColumns(Long scScid, 
            String scSchdserialnumber, String scScmacaddress, 
            String scScipaddress, Date scScstartdate, 
            Date scScenddate, Date scScconfirmdate, 
            Date scScapplydate, Date scScmodifydate, 
            String ssSscode, String ssSsname, 
            Long opapOpid, String opapOpcode, 
            String opapOpname, Long opcoOpid, 
            String opcoOpcode, String opcoOpname, 
            Long opmoOpid, String opmoOpcode, 
            String opmoOpname, String scScremark,
            String scOwnenterprisesign){
		m_astrColumns = new String[22];
		setScscid(scScid);
		setScschdserialnumber(scSchdserialnumber);
		setScscmacaddress(scScmacaddress);
		setScscipaddress(scScipaddress);
		setScscstartdate(scScstartdate);
		setScscenddate(scScenddate);
		setScscconfirmdate(scScconfirmdate);
		setScscapplydate(scScapplydate);
		setScscmodifydate(scScmodifydate);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
		setOpapopid(opapOpid);
		setOpapopcode(opapOpcode);
		setOpapopname(opapOpname);
		setOpcoopid(opcoOpid);
		setOpcoopcode(opcoOpcode);
		setOpcoopname(opcoOpname);
		setOpmoopid(opmoOpid);
		setOpmoopcode(opmoOpcode);
		setOpmoopname(opmoOpname);
		setScscremark(scScremark);
		setScownenterprisesign(scOwnenterprisesign);
	}

	public void setScscid(Long scScid) {
		this.setField(0, scScid);
	}

	public String getScscid() {
		return this.getField(0);
	}

	public void setScschdserialnumber(String scSchdserialnumber) {
		this.setField(1, scSchdserialnumber);
	}

	public String getScschdserialnumber() {
		return this.getField(1);
	}

	public void setScscmacaddress(String scScmacaddress) {
		this.setField(2, scScmacaddress);
	}

	public String getScscmacaddress() {
		return this.getField(2);
	}

	public void setScscipaddress(String scScipaddress) {
		this.setField(3, scScipaddress);
	}

	public String getScscipaddress() {
		return this.getField(3);
	}

	public void setScscstartdate(Date scScstartdate) {
		this.setField(4, scScstartdate);
	}

	public String getScscstartdate() {
		return this.getField(4);
	}

	public void setScscenddate(Date scScenddate) {
		this.setField(5, scScenddate);
	}

	public String getScscenddate() {
		return this.getField(5);
	}

	public void setScscconfirmdate(Date scScconfirmdate) {
		this.setField(6, scScconfirmdate);
	}

	public String getScscconfirmdate() {
		return this.getField(6);
	}

	public void setScscapplydate(Date scScapplydate) {
		this.setField(7, scScapplydate);
	}

	public String getScscapplydate() {
		return this.getField(7);
	}

	public void setScscmodifydate(Date scScmodifydate) {
		this.setField(8, scScmodifydate);
	}

	public String getScscmodifydate() {
		return this.getField(8);
	}

	public void setSssscode(String ssSscode) {
		this.setField(9, ssSscode);
	}

	public String getSssscode() {
		return this.getField(9);
	}

	public void setSsssname(String ssSsname) {
		this.setField(10, ssSsname);
	}

	public String getSsssname() {
		return this.getField(10);
	}

	public void setOpapopid(Long opapOpid) {
		this.setField(11, opapOpid);
	}

	public String getOpapopid() {
		return this.getField(11);
	}

	public void setOpapopcode(String opapOpcode) {
		this.setField(12, opapOpcode);
	}

	public String getOpapopcode() {
		return this.getField(12);
	}

	public void setOpapopname(String opapOpname) {
		this.setField(13, opapOpname);
	}

	public String getOpapopname() {
		return this.getField(13);
	}

	public void setOpcoopid(Long opcoOpid) {
		this.setField(14, opcoOpid);
	}

	public String getOpcoopid() {
		return this.getField(14);
	}

	public void setOpcoopcode(String opcoOpcode) {
		this.setField(15, opcoOpcode);
	}

	public String getOpcoopcode() {
		return this.getField(15);
	}

	public void setOpcoopname(String opcoOpname) {
		this.setField(16, opcoOpname);
	}

	public String getOpcoopname() {
		return this.getField(16);
	}

	public void setOpmoopid(Long opmoOpid) {
		this.setField(17, opmoOpid);
	}

	public String getOpmoopid() {
		return this.getField(17);
	}

	public void setOpmoopcode(String opmoOpcode) {
		this.setField(18, opmoOpcode);
	}

	public String getOpmoopcode() {
		return this.getField(18);
	}

	public void setOpmoopname(String opmoOpname) {
		this.setField(19, opmoOpname);
	}

	public String getOpmoopname() {
		return this.getField(19);
	}

	public void setScscremark(String scScremark) {
		this.setField(20, scScremark);
	}

	public String getScscremark() {
		return this.getField(20);
	}

	public void setScownenterprisesign(String scOwnenterprisesign) {
		this.setField(21, scOwnenterprisesign);
	}

	public String getScownenterprisesign() {
		return this.getField(21);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
