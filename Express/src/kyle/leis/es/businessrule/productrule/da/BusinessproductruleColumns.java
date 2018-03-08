package kyle.leis.es.businessrule.productrule.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class BusinessproductruleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BusinessproductruleColumns() {
		m_astrColumns = new String[7];
	}
	
	public BusinessproductruleColumns(Long brBrid, 
            String brBrname, String brBrename, 
            Date brBrstartdate, Date brBrenddate, 
            Long prBrid, String prPrallcussignoutbyoriginwbsign){
		m_astrColumns = new String[7];
		setBrbrid(brBrid);
		setBrbrname(brBrname);
		setBrbrename(brBrename);
		setBrbrstartdate(brBrstartdate);
		setBrbrenddate(brBrenddate);
		setPrbrid(prBrid);
		setPrprallcussignoutbyoriginwbsign(prPrallcussignoutbyoriginwbsign);
	}

	public void setBrbrid(Long brBrid) {
		this.setField(0, brBrid);
	}

	public String getBrbrid() {
		return this.getField(0);
	}

	public void setBrbrname(String brBrname) {
		this.setField(1, brBrname);
	}

	public String getBrbrname() {
		return this.getField(1);
	}

	public void setBrbrename(String brBrename) {
		this.setField(2, brBrename);
	}

	public String getBrbrename() {
		return this.getField(2);
	}

	public void setBrbrstartdate(Date brBrstartdate) {
		this.setField(3, brBrstartdate);
	}

	public String getBrbrstartdate() {
		return this.getField(3);
	}

	public void setBrbrenddate(Date brBrenddate) {
		this.setField(4, brBrenddate);
	}

	public String getBrbrenddate() {
		return this.getField(4);
	}

	public void setPrbrid(Long prBrid) {
		this.setField(5, prBrid);
	}

	public String getPrbrid() {
		return this.getField(5);
	}

	public void setPrprallcussignoutbyoriginwbsign(String prPrallcussignoutbyoriginwbsign) {
		this.setField(6, prPrallcussignoutbyoriginwbsign);
	}

	public String getPrprallcussignoutbyoriginwbsign() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
