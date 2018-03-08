package kyle.leis.fs.dictionary.transporttool.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TransporttoolColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransporttoolColumns() {
		m_astrColumns = new String[17];
	}
	
	public TransporttoolColumns(String ttTtcode, 
            String ttTtlabelcode, String ttTtdeparturetime, 
            String ttTtarrivaltime, String ttTtdrivername, 
            String dtdpDtcode, String dtdpDtname, 
            String dtdpDtename, String dtarDtcode, 
            String dtarDtname, String dtarDtename, 
            String twbkTwbkcode, String twbkTwbkname, 
            String twbkTwbkename, String chnChncode, 
            String chnChnname, String chnChnename){
		m_astrColumns = new String[17];
		setTtttcode(ttTtcode);
		setTtttlabelcode(ttTtlabelcode);
		setTtttdeparturetime(ttTtdeparturetime);
		setTtttarrivaltime(ttTtarrivaltime);
		setTtttdrivername(ttTtdrivername);
		setDtdpdtcode(dtdpDtcode);
		setDtdpdtname(dtdpDtname);
		setDtdpdtename(dtdpDtename);
		setDtardtcode(dtarDtcode);
		setDtardtname(dtarDtname);
		setDtardtename(dtarDtename);
		setTwbktwbkcode(twbkTwbkcode);
		setTwbktwbkname(twbkTwbkname);
		setTwbktwbkename(twbkTwbkename);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnename(chnChnename);
	}

	public void setTtttcode(String ttTtcode) {
		this.setField(0, ttTtcode);
	}

	public String getTtttcode() {
		return this.getField(0);
	}

	public void setTtttlabelcode(String ttTtlabelcode) {
		this.setField(1, ttTtlabelcode);
	}

	public String getTtttlabelcode() {
		return this.getField(1);
	}

	public void setTtttdeparturetime(String ttTtdeparturetime) {
		this.setField(2, ttTtdeparturetime);
	}

	public String getTtttdeparturetime() {
		return this.getField(2);
	}

	public void setTtttarrivaltime(String ttTtarrivaltime) {
		this.setField(3, ttTtarrivaltime);
	}

	public String getTtttarrivaltime() {
		return this.getField(3);
	}

	public void setTtttdrivername(String ttTtdrivername) {
		this.setField(4, ttTtdrivername);
	}

	public String getTtttdrivername() {
		return this.getField(4);
	}

	public void setDtdpdtcode(String dtdpDtcode) {
		this.setField(5, dtdpDtcode);
	}

	public String getDtdpdtcode() {
		return this.getField(5);
	}

	public void setDtdpdtname(String dtdpDtname) {
		this.setField(6, dtdpDtname);
	}

	public String getDtdpdtname() {
		return this.getField(6);
	}

	public void setDtdpdtename(String dtdpDtename) {
		this.setField(7, dtdpDtename);
	}

	public String getDtdpdtename() {
		return this.getField(7);
	}

	public void setDtardtcode(String dtarDtcode) {
		this.setField(8, dtarDtcode);
	}

	public String getDtardtcode() {
		return this.getField(8);
	}

	public void setDtardtname(String dtarDtname) {
		this.setField(9, dtarDtname);
	}

	public String getDtardtname() {
		return this.getField(9);
	}

	public void setDtardtename(String dtarDtename) {
		this.setField(10, dtarDtename);
	}

	public String getDtardtename() {
		return this.getField(10);
	}

	public void setTwbktwbkcode(String twbkTwbkcode) {
		this.setField(11, twbkTwbkcode);
	}

	public String getTwbktwbkcode() {
		return this.getField(11);
	}

	public void setTwbktwbkname(String twbkTwbkname) {
		this.setField(12, twbkTwbkname);
	}

	public String getTwbktwbkname() {
		return this.getField(12);
	}

	public void setTwbktwbkename(String twbkTwbkename) {
		this.setField(13, twbkTwbkename);
	}

	public String getTwbktwbkename() {
		return this.getField(13);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(14, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(14);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(15, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(15);
	}

	public void setChnchnename(String chnChnename) {
		this.setField(16, chnChnename);
	}

	public String getChnchnename() {
		return this.getField(16);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
