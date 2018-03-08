package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DeparturebatchwaybillCondition extends GeneralCondition {
	public DeparturebatchwaybillCondition() {
		m_astrConditions = new String[8];
	}	
	
	public void setCp_baglabelcode(String CP_BAGLABELCODE) {
		this.setField(0, CP_BAGLABELCODE);
	}

	public String getCp_baglabelcode() {
		return this.getField(0);
	}

	public void setBw_code(String BW_CODE) {
		this.setField(1, BW_CODE);
	}

	public String getBw_code() {
		return this.getField(1);
	}

	public void setStartadddate(String StartAddDate) {
		this.setField(2, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(2);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(3, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(3);
	}

	public void setBw_labelcode(String BW_LABELCODE) {
		this.setField(4, BW_LABELCODE);
	}

	public String getBw_labelcode() {
		return this.getField(4);
	}

	public void setBws_code(String BWS_CODE) {
		this.setField(5, BWS_CODE);
	}

	public String getBws_code() {
		return this.getField(5);
	}

	public void setCo_code(String CO_CODE) {
		this.setField(6, CO_CODE);
	}

	public String getCo_code() {
		return this.getField(6);
	}
	
	public void setEestructurecode(String eeStructurecode) {
		this.setField(7, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(7);
	}	

}
