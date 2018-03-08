package kyle.leis.eo.operation.transportwaybill.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class TransportwaybillvalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransportwaybillvalueColumns() {
		m_astrColumns = new String[13];
	}
	
	public TransportwaybillvalueColumns(Long twbvcomp_idTwbid, 
            Long twbvcomp_idTwbvid, String twbvTwbvbaglabelcode, 
            Long bwBwcode, String bwBwlabelcode, 
            Date bwAdddate, String coCocode, 
            String coCosname, String coColabelcode, 
            Long cwCwcode, String cwCwserverewbcode, 
            String cwCwcustomerewbcode, String cwCwewbcode){
		m_astrColumns = new String[13];
		setTwbvcomp_idtwbid(twbvcomp_idTwbid);
		setTwbvcomp_idtwbvid(twbvcomp_idTwbvid);
		setTwbvtwbvbaglabelcode(twbvTwbvbaglabelcode);
		setBwbwcode(bwBwcode);
		setBwbwlabelcode(bwBwlabelcode);
		setBwadddate(bwAdddate);
		setCococode(coCocode);
		setCocosname(coCosname);
		setCocolabelcode(coColabelcode);
		setCwcwcode(cwCwcode);
		setCwcwserverewbcode(cwCwserverewbcode);
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwewbcode(cwCwewbcode);
	}

	public void setTwbvcomp_idtwbid(Long twbvcomp_idTwbid) {
		this.setField(0, twbvcomp_idTwbid);
	}

	public String getTwbvcomp_idtwbid() {
		return this.getField(0);
	}

	public void setTwbvcomp_idtwbvid(Long twbvcomp_idTwbvid) {
		this.setField(1, twbvcomp_idTwbvid);
	}

	public String getTwbvcomp_idtwbvid() {
		return this.getField(1);
	}

	public void setTwbvtwbvbaglabelcode(String twbvTwbvbaglabelcode) {
		this.setField(2, twbvTwbvbaglabelcode);
	}

	public String getTwbvtwbvbaglabelcode() {
		return this.getField(2);
	}

	public void setBwbwcode(Long bwBwcode) {
		this.setField(3, bwBwcode);
	}

	public String getBwbwcode() {
		return this.getField(3);
	}

	public void setBwbwlabelcode(String bwBwlabelcode) {
		this.setField(4, bwBwlabelcode);
	}

	public String getBwbwlabelcode() {
		return this.getField(4);
	}

	public void setBwadddate(Date bwAdddate) {
		this.setField(5, bwAdddate);
	}

	public String getBwadddate() {
		return this.getField(5);
	}

	public void setCococode(String coCocode) {
		this.setField(6, coCocode);
	}

	public String getCococode() {
		return this.getField(6);
	}

	public void setCocosname(String coCosname) {
		this.setField(7, coCosname);
	}

	public String getCocosname() {
		return this.getField(7);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(8, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(8);
	}

	public void setCwcwcode(Long cwCwcode) {
		this.setField(9, cwCwcode);
	}

	public String getCwcwcode() {
		return this.getField(9);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(10, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(10);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(11, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(11);
	}

	public void setCwcwewbcode(String cwCwewbcode) {
		this.setField(12, cwCwewbcode);
	}

	public String getCwcwewbcode() {
		return this.getField(12);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
