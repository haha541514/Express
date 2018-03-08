package kyle.leis.es.company.channel.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChanneladdressColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChanneladdressColumns() {
		m_astrColumns = new String[8];
	}
	
	public ChanneladdressColumns(String chnaChncode, 
            String chnaChnaaddress1, String chnaChnaaddress2, 
            String chnaChnaaddress3, String chnaChnaprocessingaddress1, 
            String chnaChnaprocessingaddress2, String chnaChnaprocessingaddress3, 
            String chnaChnamid){
		m_astrColumns = new String[8];
		setChnachncode(chnaChncode);
		setChnachnaaddress1(chnaChnaaddress1);
		setChnachnaaddress2(chnaChnaaddress2);
		setChnachnaaddress3(chnaChnaaddress3);
		setChnachnaprocessingaddress1(chnaChnaprocessingaddress1);
		setChnachnaprocessingaddress2(chnaChnaprocessingaddress2);
		setChnachnaprocessingaddress3(chnaChnaprocessingaddress3);
		setChnachnamid(chnaChnamid);
	}

	public void setChnachncode(String chnaChncode) {
		this.setField(0, chnaChncode);
	}

	public String getChnachncode() {
		return this.getField(0);
	}

	public void setChnachnaaddress1(String chnaChnaaddress1) {
		this.setField(1, chnaChnaaddress1);
	}

	public String getChnachnaaddress1() {
		return this.getField(1);
	}

	public void setChnachnaaddress2(String chnaChnaaddress2) {
		this.setField(2, chnaChnaaddress2);
	}

	public String getChnachnaaddress2() {
		return this.getField(2);
	}

	public void setChnachnaaddress3(String chnaChnaaddress3) {
		this.setField(3, chnaChnaaddress3);
	}

	public String getChnachnaaddress3() {
		return this.getField(3);
	}

	public void setChnachnaprocessingaddress1(String chnaChnaprocessingaddress1) {
		this.setField(4, chnaChnaprocessingaddress1);
	}

	public String getChnachnaprocessingaddress1() {
		return this.getField(4);
	}

	public void setChnachnaprocessingaddress2(String chnaChnaprocessingaddress2) {
		this.setField(5, chnaChnaprocessingaddress2);
	}

	public String getChnachnaprocessingaddress2() {
		return this.getField(5);
	}

	public void setChnachnaprocessingaddress3(String chnaChnaprocessingaddress3) {
		this.setField(6, chnaChnaprocessingaddress3);
	}

	public String getChnachnaprocessingaddress3() {
		return this.getField(6);
	}

	public void setChnachnamid(String chnaChnamid) {
		this.setField(7, chnaChnamid);
	}

	public String getChnachnamid() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
