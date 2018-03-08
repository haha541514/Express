package kyle.leis.eo.operation.transportwaybill.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class TransportwaybilltraceColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransportwaybilltraceColumns() {
		m_astrColumns = new String[9];
	}
	
	public TransportwaybilltraceColumns(Long twbtcomp_idTwbid, 
            String twbtcomp_idTwbscode, Date twbtTwbtcreatedate, 
            Date twbtTwbtoccurdate, String twbsTwbsname, 
            String dtDtcode, String dtDthubcode, 
            Long copOpid, String copOpname){
		m_astrColumns = new String[9];
		setTwbtcomp_idtwbid(twbtcomp_idTwbid);
		setTwbtcomp_idtwbscode(twbtcomp_idTwbscode);
		setTwbttwbtcreatedate(twbtTwbtcreatedate);
		setTwbttwbtoccurdate(twbtTwbtoccurdate);
		setTwbstwbsname(twbsTwbsname);
		setDtdtcode(dtDtcode);
		setDtdthubcode(dtDthubcode);
		setCopopid(copOpid);
		setCopopname(copOpname);
	}

	public void setTwbtcomp_idtwbid(Long twbtcomp_idTwbid) {
		this.setField(0, twbtcomp_idTwbid);
	}

	public String getTwbtcomp_idtwbid() {
		return this.getField(0);
	}

	public void setTwbtcomp_idtwbscode(String twbtcomp_idTwbscode) {
		this.setField(1, twbtcomp_idTwbscode);
	}

	public String getTwbtcomp_idtwbscode() {
		return this.getField(1);
	}

	public void setTwbttwbtcreatedate(Date twbtTwbtcreatedate) {
		this.setField(2, twbtTwbtcreatedate);
	}

	public String getTwbttwbtcreatedate() {
		return this.getField(2);
	}

	public void setTwbttwbtoccurdate(Date twbtTwbtoccurdate) {
		this.setField(3, twbtTwbtoccurdate);
	}

	public String getTwbttwbtoccurdate() {
		return this.getField(3);
	}

	public void setTwbstwbsname(String twbsTwbsname) {
		this.setField(4, twbsTwbsname);
	}

	public String getTwbstwbsname() {
		return this.getField(4);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(5, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(5);
	}

	public void setDtdthubcode(String dtDthubcode) {
		this.setField(6, dtDthubcode);
	}

	public String getDtdthubcode() {
		return this.getField(6);
	}

	public void setCopopid(Long copOpid) {
		this.setField(7, copOpid);
	}

	public String getCopopid() {
		return this.getField(7);
	}

	public void setCopopname(String copOpname) {
		this.setField(8, copOpname);
	}

	public String getCopopname() {
		return this.getField(8);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
