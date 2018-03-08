package kyle.leis.eo.operation.transportwaybill.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class TransportwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransportwaybillColumns() {
		m_astrColumns = new String[28];
	}
	
	public TransportwaybillColumns(Long twbTwbid, 
            String twbTwblabelcode, Date twbTwbcreatedate, 
            Date twbTwbmodifydate, String twbsTwbscode, 
            String twbsTwbsname, Long mopOpid, 
            String mopOpname, Long copOpid, 
            String copOpname, String twbkTwbkcode, 
            String twbkTwbkname, String ttTtcode, 
            String ttTtlabelcode, String ttTtdeparturetime, 
            String ttTtarrivaltime, String ttTtdrivername, 
            String ddtDtcode, String ddtDthubcode, 
            String adtDtcode, String adtDthubcode, 
            String ttchnChncode, String ttcoCocode, 
            BigDecimal twbTwbtransportfeetotal, String ckCkcode, 
            String ckCkname, String eeEecode, 
            String eeEesname){
		m_astrColumns = new String[28];
		setTwbtwbid(twbTwbid);
		setTwbtwblabelcode(twbTwblabelcode);
		setTwbtwbcreatedate(twbTwbcreatedate);
		setTwbtwbmodifydate(twbTwbmodifydate);
		setTwbstwbscode(twbsTwbscode);
		setTwbstwbsname(twbsTwbsname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setTwbktwbkcode(twbkTwbkcode);
		setTwbktwbkname(twbkTwbkname);
		setTtttcode(ttTtcode);
		setTtttlabelcode(ttTtlabelcode);
		setTtttdeparturetime(ttTtdeparturetime);
		setTtttarrivaltime(ttTtarrivaltime);
		setTtttdrivername(ttTtdrivername);
		setDdtdtcode(ddtDtcode);
		setDdtdthubcode(ddtDthubcode);
		setAdtdtcode(adtDtcode);
		setAdtdthubcode(adtDthubcode);
		setTtchnchncode(ttchnChncode);
		setTtcococode(ttcoCocode);
		setTwbtwbtransportfeetotal(twbTwbtransportfeetotal);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
	}

	public void setTwbtwbid(Long twbTwbid) {
		this.setField(0, twbTwbid);
	}

	public String getTwbtwbid() {
		return this.getField(0);
	}

	public void setTwbtwblabelcode(String twbTwblabelcode) {
		this.setField(1, twbTwblabelcode);
	}

	public String getTwbtwblabelcode() {
		return this.getField(1);
	}

	public void setTwbtwbcreatedate(Date twbTwbcreatedate) {
		this.setField(2, twbTwbcreatedate);
	}

	public String getTwbtwbcreatedate() {
		return this.getField(2);
	}

	public void setTwbtwbmodifydate(Date twbTwbmodifydate) {
		this.setField(3, twbTwbmodifydate);
	}

	public String getTwbtwbmodifydate() {
		return this.getField(3);
	}

	public void setTwbstwbscode(String twbsTwbscode) {
		this.setField(4, twbsTwbscode);
	}

	public String getTwbstwbscode() {
		return this.getField(4);
	}

	public void setTwbstwbsname(String twbsTwbsname) {
		this.setField(5, twbsTwbsname);
	}

	public String getTwbstwbsname() {
		return this.getField(5);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(6, mopOpid);
	}

	public String getMopopid() {
		return this.getField(6);
	}

	public void setMopopname(String mopOpname) {
		this.setField(7, mopOpname);
	}

	public String getMopopname() {
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

	public void setTwbktwbkcode(String twbkTwbkcode) {
		this.setField(10, twbkTwbkcode);
	}

	public String getTwbktwbkcode() {
		return this.getField(10);
	}

	public void setTwbktwbkname(String twbkTwbkname) {
		this.setField(11, twbkTwbkname);
	}

	public String getTwbktwbkname() {
		return this.getField(11);
	}

	public void setTtttcode(String ttTtcode) {
		this.setField(12, ttTtcode);
	}

	public String getTtttcode() {
		return this.getField(12);
	}

	public void setTtttlabelcode(String ttTtlabelcode) {
		this.setField(13, ttTtlabelcode);
	}

	public String getTtttlabelcode() {
		return this.getField(13);
	}

	public void setTtttdeparturetime(String ttTtdeparturetime) {
		this.setField(14, ttTtdeparturetime);
	}

	public String getTtttdeparturetime() {
		return this.getField(14);
	}

	public void setTtttarrivaltime(String ttTtarrivaltime) {
		this.setField(15, ttTtarrivaltime);
	}

	public String getTtttarrivaltime() {
		return this.getField(15);
	}

	public void setTtttdrivername(String ttTtdrivername) {
		this.setField(16, ttTtdrivername);
	}

	public String getTtttdrivername() {
		return this.getField(16);
	}

	public void setDdtdtcode(String ddtDtcode) {
		this.setField(17, ddtDtcode);
	}

	public String getDdtdtcode() {
		return this.getField(17);
	}

	public void setDdtdthubcode(String ddtDthubcode) {
		this.setField(18, ddtDthubcode);
	}

	public String getDdtdthubcode() {
		return this.getField(18);
	}

	public void setAdtdtcode(String adtDtcode) {
		this.setField(19, adtDtcode);
	}

	public String getAdtdtcode() {
		return this.getField(19);
	}

	public void setAdtdthubcode(String adtDthubcode) {
		this.setField(20, adtDthubcode);
	}

	public String getAdtdthubcode() {
		return this.getField(20);
	}

	public void setTtchnchncode(String ttchnChncode) {
		this.setField(21, ttchnChncode);
	}

	public String getTtchnchncode() {
		return this.getField(21);
	}

	public void setTtcococode(String ttcoCocode) {
		this.setField(22, ttcoCocode);
	}

	public String getTtcococode() {
		return this.getField(22);
	}

	public void setTwbtwbtransportfeetotal(BigDecimal twbTwbtransportfeetotal) {
		this.setField(23, twbTwbtransportfeetotal);
	}

	public String getTwbtwbtransportfeetotal() {
		return this.getField(23);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(24, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(24);
	}

	public void setCkckname(String ckCkname) {
		this.setField(25, ckCkname);
	}

	public String getCkckname() {
		return this.getField(25);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(26, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(26);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(27, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(27);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
