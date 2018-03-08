package kyle.leis.eo.operation.batchwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CorebatchwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorebatchwaybillColumns() {
		m_astrColumns = new String[7];
	}
	
	public CorebatchwaybillColumns(String bwAdd_date, 
            String bwBw_labelcode, String bwBw_code, 
            String chnChn_sename, String weight, 
            String count, String wightandcount){
		m_astrColumns = new String[7];
		setBwadd_date(bwAdd_date);
		setBwbw_labelcode(bwBw_labelcode);
		setBwbw_code(bwBw_code);
		setChnchn_sename(chnChn_sename);
	}

	public void setBwadd_date(String bwAdd_date) {
		this.setField(0, bwAdd_date);
	}

	public String getBwadd_date() {
		return this.getField(0);
	}

	public void setBwbw_labelcode(String bwBw_labelcode) {
		this.setField(1, bwBw_labelcode);
	}

	public String getBwbw_labelcode() {
		return this.getField(1);
	}

	public void setBwbw_code(String bwBw_code) {
		this.setField(2, bwBw_code);
	}

	public String getBwbw_code() {
		return this.getField(2);
	}

	public void setChnchn_sename(String chnChn_sename) {
		this.setField(3, chnChn_sename);
	}

	public String getChnchn_sename() {
		return this.getField(3);
	}


	public String getWeight() {
		return this.getField(4);
	}


	public String getCount(){
		return this.getField(5);
	}

	public String getWeightandcount() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
