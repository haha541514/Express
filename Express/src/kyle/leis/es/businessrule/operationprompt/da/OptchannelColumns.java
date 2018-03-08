package kyle.leis.es.businessrule.operationprompt.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OptchannelColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OptchannelColumns() {
		m_astrColumns = new String[6];
	}
	
	public OptchannelColumns(Long optchncomp_idBrid, 
            String optchnOptncssign, String chnChncode, 
            String chnChnname, String chnChnsname, 
            String coCocode){
		m_astrColumns = new String[6];
		setOptchncomp_idbrid(optchncomp_idBrid);
		setOptchnoptncssign(optchnOptncssign);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnsname(chnChnsname);
		setCococode(coCocode);
	}

	public void setOptchncomp_idbrid(Long optchncomp_idBrid) {
		this.setField(0, optchncomp_idBrid);
	}

	public String getOptchncomp_idbrid() {
		return this.getField(0);
	}

	public void setOptchnoptncssign(String optchnOptncssign) {
		this.setField(1, optchnOptncssign);
	}

	public String getOptchnoptncssign() {
		return this.getField(1);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(2, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(2);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(3, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(3);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(4, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(4);
	}

	public void setCococode(String coCocode) {
		this.setField(5, coCocode);
	}

	public String getCococode() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
