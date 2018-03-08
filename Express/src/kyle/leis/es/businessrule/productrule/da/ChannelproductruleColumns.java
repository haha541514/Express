package kyle.leis.es.businessrule.productrule.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChannelproductruleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChannelproductruleColumns() {
		m_astrColumns = new String[8];
	}
	
	public ChannelproductruleColumns(Integer cprcomp_idCprid, 
            Long cprcomp_idBrid, Long cprZnid, 
            Integer cprZnvid, String chnChncode, 
            String chnChnname, String chnChnsname, 
            String chnChnsename){
		m_astrColumns = new String[8];
		setCprcomp_idcprid(cprcomp_idCprid);
		setCprcomp_idbrid(cprcomp_idBrid);
		setCprznid(cprZnid);
		setCprznvid(cprZnvid);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
	}

	public void setCprcomp_idcprid(Integer cprcomp_idCprid) {
		this.setField(0, cprcomp_idCprid);
	}

	public String getCprcomp_idcprid() {
		return this.getField(0);
	}

	public void setCprcomp_idbrid(Long cprcomp_idBrid) {
		this.setField(1, cprcomp_idBrid);
	}

	public String getCprcomp_idbrid() {
		return this.getField(1);
	}

	public void setCprznid(Long cprZnid) {
		this.setField(2, cprZnid);
	}

	public String getCprznid() {
		return this.getField(2);
	}

	public void setCprznvid(Integer cprZnvid) {
		this.setField(3, cprZnvid);
	}

	public String getCprznvid() {
		return this.getField(3);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(4, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(4);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(5, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(5);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(6, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(6);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(7, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
