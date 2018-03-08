package kyle.leis.es.price.incidentalprice.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class IncidentalstoragechannelColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IncidentalstoragechannelColumns() {
		m_astrColumns = new String[6];
	}
	
	public IncidentalstoragechannelColumns(Long isccomp_idEpcode, 
            Integer isccomp_idIpvid, String isccomp_idChncode, 
            String chnChncode, String chnChnsname, 
            String chnChnsename){
		m_astrColumns = new String[6];
		setIsccomp_idepcode(isccomp_idEpcode);
		setIsccomp_idipvid(isccomp_idIpvid);
		setIsccomp_idchncode(isccomp_idChncode);
		setChnchncode(chnChncode);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
	}

	public void setIsccomp_idepcode(Long isccomp_idEpcode) {
		this.setField(0, isccomp_idEpcode);
	}

	public String getIsccomp_idepcode() {
		return this.getField(0);
	}

	public void setIsccomp_idipvid(Integer isccomp_idIpvid) {
		this.setField(1, isccomp_idIpvid);
	}

	public String getIsccomp_idipvid() {
		return this.getField(1);
	}

	public void setIsccomp_idchncode(String isccomp_idChncode) {
		this.setField(2, isccomp_idChncode);
	}

	public String getIsccomp_idchncode() {
		return this.getField(2);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(3, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(3);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(4, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(4);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(5, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
