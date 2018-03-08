package kyle.leis.eo.customerservice.track.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class SimpletrackColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimpletrackColumns() {
		m_astrColumns = new String[10];
	}
	
	public SimpletrackColumns(String swbtSwbt_id,
			String swbSwb_customerewbcode, 
            String swbtSwbt_description, String swbtSwbt_location, 
            Date swbtSwbt_occurdate, String swbtSwbt_creator, 
            String swbtSwbt_modifier,Date swbtSwbt_createdate, 
            Date swbtSwbt_modifydate,String swbtSwbt_opensign
            ){
		m_astrColumns = new String[10];
		setSwbtswbt_id(swbtSwbt_id);
		setSwbswb_customerewbcode(swbSwb_customerewbcode);
		setSwbtswbt_description(swbtSwbt_description);
		setSwbtswbt_location(swbtSwbt_location);
		setSwbtswbt_occurdate(swbtSwbt_occurdate);
		setSwbtswbt_creator(swbtSwbt_creator);
		setSwbtswbt_modifier(swbtSwbt_modifier);
		setSwbtswbt_createdate(swbtSwbt_createdate);
		setSwbtswbt_modifydate(swbtSwbt_modifydate);
	}
	public void setSwbtswbt_id(String swbtSwbt_id) {
		this.setField(0, swbtSwbt_id);
	}

	public String getSwbtswbt_id() {
		return this.getField(0);
	}
	public void setSwbswb_customerewbcode(String swbSwb_customerewbcode) {
		this.setField(1, swbSwb_customerewbcode);
	}

	public String getSwbswb_customerewbcode() {
		return this.getField(1);
	}

	public void setSwbtswbt_description(String swbtSwbt_description) {
		this.setField(2, swbtSwbt_description);
	}

	public String getSwbtswbt_description() {
		return this.getField(2);
	}

	public void setSwbtswbt_location(String swbtSwbt_location) {
		this.setField(3, swbtSwbt_location);
	}

	public String getSwbtswbt_location() {
		return this.getField(3);
	}

	public void setSwbtswbt_occurdate(Date swbtSwbt_occurdate) {
		this.setField(4, swbtSwbt_occurdate);
	}

	public String getSwbtswbt_occurdate() {
		return this.getField(4);
	}

	public void setSwbtswbt_creator(String swbtSwbt_creator) {
		this.setField(5, swbtSwbt_creator);
	}

	public String getSwbtswbt_creator() {
		return this.getField(5);
	}

	public void setSwbtswbt_modifier(String swbtSwbt_modifier) {
		this.setField(6, swbtSwbt_modifier);
	}

	public String getSwbtswbt_modifier() {
		return this.getField(6);
	}

	public void setSwbtswbt_createdate(Date swbtSwbt_createdate) {
		this.setField(7, swbtSwbt_createdate);
	}

	public String getSwbtswbt_createdate() {
		return this.getField(7);
	}

	public void setSwbtswbt_modifydate(Date swbtSwbt_modifydate) {
		this.setField(8, swbtSwbt_modifydate);
	}

	public String getSwbtswbt_modifydate() {
		return this.getField(8);
	}
	public void setSwbtswbt_opensign(String swbtSwbt_opensign) {
		this.setField(9, swbtSwbt_opensign);
	}

	public String getSwbtswbt_opensign() {
		return this.getField(9);
	}
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
