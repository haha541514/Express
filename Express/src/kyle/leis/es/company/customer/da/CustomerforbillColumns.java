package kyle.leis.es.company.customer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomerforbillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomerforbillColumns() {
		m_astrColumns = new String[13];
	}
	
	public CustomerforbillColumns(String cmCo_code, 
            String cmCm_creditlimit, String cmCm_op_id_dun, 
            String opOp_name, String coCo_sname, 
            String coCo_labelcode, String coCo_sename, 
            String Sumconfirmfee, String Sumdraftfee, 
            String cmtCt_code, String cmtCt_name,
            String eeEe_code, String eeEe_sname){
		m_astrColumns = new String[13];
		setCmco_code(cmCo_code);
		setCmcm_creditlimit(cmCm_creditlimit);
		setCmcm_op_id_dun(cmCm_op_id_dun);
		setOpop_name(opOp_name);
		setCoco_sname(coCo_sname);
		setCoco_labelcode(coCo_labelcode);
		setCoco_sename(coCo_sename);
		setSumconfirmfee(Sumconfirmfee);
		setSumdraftfee(Sumdraftfee);
		setCmtct_code(cmtCt_code);
		setCmtct_name(cmtCt_name);
		setEeee_code(eeEe_code);
		setEeee_sname(eeEe_sname);		
	}

	public void setCmco_code(String cmCo_code) {
		this.setField(0, cmCo_code);
	}

	public String getCmco_code() {
		return this.getField(0);
	}

	public void setCmcm_creditlimit(String cmCm_creditlimit) {
		this.setField(1, cmCm_creditlimit);
	}

	public String getCmcm_creditlimit() {
		return this.getField(1);
	}

	public void setCmcm_op_id_dun(String cmCm_op_id_dun) {
		this.setField(2, cmCm_op_id_dun);
	}

	public String getCmcm_op_id_dun() {
		return this.getField(2);
	}

	public void setOpop_name(String opOp_name) {
		this.setField(3, opOp_name);
	}

	public String getOpop_name() {
		return this.getField(3);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(4, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(4);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(5, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(5);
	}

	public void setCoco_sename(String coCo_sename) {
		this.setField(6, coCo_sename);
	}

	public String getCoco_sename() {
		return this.getField(6);
	}



	public void setSumconfirmfee(String Sumconfirmfee) {
		this.setField(7, Sumconfirmfee);
	}

	public String getSumconfirmfee() {
		return this.getField(7);
	}


	public void setSumdraftfee(String Sumdraftfee) {
		this.setField(8, Sumdraftfee);
	}

	public String getSumdraftfee() {
		return this.getField(8);
	}

	public void setCmtct_code(String cmtCt_code) {
		this.setField(9, cmtCt_code);
	}

	public String getCmtct_code() {
		return this.getField(9);
	}

	public void setCmtct_name(String cmtCt_name) {
		this.setField(10, cmtCt_name);
	}

	public String getCmtct_name() {
		return this.getField(10);
	}

	public void setEeee_code(String eeEe_code) {
		this.setField(11, eeEe_code);
	}

	public String getEeee_code() {
		return this.getField(11);
	}

	public void setEeee_sname(String eeEe_sname) {
		this.setField(12, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(12);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
