package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillforbillCondition extends GeneralCondition {
	public WaybillforbillCondition() {
		m_astrConditions = new String[29];
	}	
	
	public void setCw_customerewbcode(String cw_customerewbcode) {
		this.setField(0, cw_customerewbcode);
	}

	public String getCw_customerewbcode() {
		return this.getField(0);
	}

	public void setCw_ewbcode(String cw_ewbcode) {
		this.setField(1, cw_ewbcode);
	}

	public String getCw_ewbcode() {
		return this.getField(1);
	}

	public void setCw_serverewbcode(String cw_serverewbcode) {
		this.setField(2, cw_serverewbcode);
	}

	public String getCw_serverewbcode() {
		return this.getField(2);
	}

	public void setChn_code_supplier(String chn_code_supplier) {
		this.setField(3, chn_code_supplier);
	}

	public String getChn_code_supplier() {
		return this.getField(3);
	}

	public void setCo_code_supplier(String co_code_supplier) {
		this.setField(4, co_code_supplier);
	}

	public String getCo_code_supplier() {
		return this.getField(4);
	}

	public void setAbw_labelcode(String abw_labelcode) {
		this.setField(5, abw_labelcode);
	}

	public String getAbw_labelcode() {
		return this.getField(5);
	}

	public void setDbw_labelcode(String dbw_labelcode) {
		this.setField(6, dbw_labelcode);
	}

	public String getDbw_labelcode() {
		return this.getField(6);
	}

	public void setCws_code(String cws_code) {
		this.setField(7, cws_code);
	}

	public String getCws_code() {
		return this.getField(7);
	}

	public void setPm_code(String pm_code) {
		this.setField(8, pm_code);
	}

	public String getPm_code() {
		return this.getField(8);
	}

	public void setPk_code(String pk_code) {
		this.setField(9, pk_code);
	}

	public String getPk_code() {
		return this.getField(9);
	}

	public void setCt_code(String ct_code) {
		this.setField(10, ct_code);
	}

	public String getCt_code() {
		return this.getField(10);
	}

	public void setOdt_code(String odt_code) {
		this.setField(11, odt_code);
	}

	public String getOdt_code() {
		return this.getField(11);
	}

	public void setDdt_code(String ddt_code) {
		this.setField(12, ddt_code);
	}

	public String getDdt_code() {
		return this.getField(12);
	}

	public void setCddt_code(String cddt_code) {
		this.setField(13, cddt_code);
	}

	public String getCddt_code() {
		return this.getField(13);
	}

	public void setCo_code_customer(String co_code_customer) {
		this.setField(14, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(14);
	}

	public void setStartadddate(String StartAdddate) {
		this.setField(15, StartAdddate);
	}

	public String getStartadddate() {
		return this.getField(15);
	}

	public void setEndadddate(String EndAdddate) {
		this.setField(16, EndAdddate);
	}

	public String getEndadddate() {
		return this.getField(16);
	}

	public void setStartdepdate(String StartDepdate) {
		this.setField(17, StartDepdate);
	}

	public String getStartdepdate() {
		return this.getField(17);
	}

	public void setEnddepdate(String EndDepdate) {
		this.setField(18, EndDepdate);
	}

	public String getEnddepdate() {
		return this.getField(18);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(19, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(19);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(20, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(20);
	}

	public void setEecode(String eeCode) {
		this.setField(21, eeCode);
	}

	public String getEecode() {
		return this.getField(21);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(22, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(22);
	}

	public void setStartdeclarevalue(String StartDeclarevalue) {
		this.setField(23, StartDeclarevalue);
	}

	public String getStartdeclarevalue() {
		return this.getField(23);
	}

	public void setEnddeclarevalue(String EndDeclarevalue) {
		this.setField(24, EndDeclarevalue);
	}

	public String getEnddeclarevalue() {
		return this.getField(24);
	}

	public void setNotcalcfeesign(String notcalcfeesign) {
		this.setField(25, notcalcfeesign);
	}

	public String getNotcalcfeesign() {
		return this.getField(25);
	}

	public void setOp_id_sale(String op_id_sale) {
		this.setField(26, op_id_sale);
	}

	public String getOp_id_sale() {
		return this.getField(26);
	}
	public void setCctcode(String cctCode) {
		this.setField(27, cctCode);
	}

	public String getCctcode() {
		return this.getField(27);
	}
}
