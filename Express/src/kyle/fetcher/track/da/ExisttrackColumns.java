package kyle.fetcher.track.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ExisttrackColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ExisttrackColumns() {
		m_astrColumns = new String[8];
	}
	
	public ExisttrackColumns(String wbtWbt_id, 
            String wbtCw_code, String wbtWbt_district, 
            String wbtCo_code, String wbtWbts_code, 
            String wbtWbt_description, String wbtWbt_location, 
            String wbtWbt_occurdate){
		m_astrColumns = new String[8];
		setWbtwbt_id(wbtWbt_id);
		setWbtcw_code(wbtCw_code);
		setWbtwbt_district(wbtWbt_district);
		setWbtco_code(wbtCo_code);
		setWbtwbts_code(wbtWbts_code);
		setWbtwbt_description(wbtWbt_description);
		setWbtwbt_location(wbtWbt_location);
		setWbtwbt_occurdate(wbtWbt_occurdate);
	}

	public void setWbtwbt_id(String wbtWbt_id) {
		this.setField(0, wbtWbt_id);
	}

	public String getWbtwbt_id() {
		return this.getField(0);
	}

	public void setWbtcw_code(String wbtCw_code) {
		this.setField(1, wbtCw_code);
	}

	public String getWbtcw_code() {
		return this.getField(1);
	}

	public void setWbtwbt_district(String wbtWbt_district) {
		this.setField(2, wbtWbt_district);
	}

	public String getWbtwbt_district() {
		return this.getField(2);
	}

	public void setWbtco_code(String wbtCo_code) {
		this.setField(3, wbtCo_code);
	}

	public String getWbtco_code() {
		return this.getField(3);
	}

	public void setWbtwbts_code(String wbtWbts_code) {
		this.setField(4, wbtWbts_code);
	}

	public String getWbtwbts_code() {
		return this.getField(4);
	}

	public void setWbtwbt_description(String wbtWbt_description) {
		this.setField(5, wbtWbt_description);
	}

	public String getWbtwbt_description() {
		return this.getField(5);
	}

	public void setWbtwbt_location(String wbtWbt_location) {
		this.setField(6, wbtWbt_location);
	}

	public String getWbtwbt_location() {
		return this.getField(6);
	}

	public void setWbtwbt_occurdate(String wbtWbt_occurdate) {
		this.setField(7, wbtWbt_occurdate);
	}

	public String getWbtwbt_occurdate() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
