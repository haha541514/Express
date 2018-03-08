package kyle.leis.eo.operation.manifest.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ForcreatemanifestQuery extends JGeneralQuery {
	
	public ForcreatemanifestQuery(){
	    m_strSelectClause = "SELECT rop.op_name,hwb.hw_recorddate,cw.cw_code,cw.cw_pieces,cw.cw_grossweight,cw.cw_chargeweight,cw.cw_transferpieces,cw.cw_transfergrossweight,cw.cw_transferchargeweight,cw.cw_customerewbcode,cw.cw_serverewbcode,cw.cw_ewbcode,chn.chn_code,chn.chn_sname,chn.chn_sename,pk.pk_code,pk.pk_sename,ct.ct_code,ct.ct_name,pm.pm_code,pm.pm_name,ee.ee_code,ee.EE_SName FROM t_op_housewaybill hwb,t_op_corewaybill cw,t_op_batchwaybill bw,t_chn_channel chn,t_co_corporation co,t_di_productkind pk,t_di_cargotype ct,t_di_paymentmode pm,t_di_operator rop, T_DI_ENTERPRISEELEMENT ee";
	    m_strWhereClause = "hwb.cw_code = cw.cw_code and bw.bw_code(+) = cw.bw_code_departure and cw.co_code_customer = co.co_code and cw.chn_code_supplier = chn.chn_code and cw.pk_code = pk.pk_code and cw.ct_code = ct.ct_code and cw.pm_code = pm.pm_code and hwb.hw_op_id_record = rop.op_id and cw.ee_code = ee.ee_code and cw.cws_code NOT IN ('EL', 'CEL') and not exists (select mfv.mv_id from t_op_manifestvalue mfv where mfv.cw_code = cw.cw_code)";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "chn.chn_code = '~~'", "hwb.hw_op_id_record = ~~", "hwb.hw_recorddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hwb.hw_recorddate", "bw.bw_labelcode = '~~'", "co.co_code = '~~'", "cw.cws_code = '~~'", "chn.ssg_code = '~~'", "not exists (select mfv.mf_code from t_op_manifestvalue mfv where mfv.cw_code = cw.cw_code and 'Y' = '~~')", "ee.EE_Structurecode like '~~%'", "cw.cw_serverewbcode in (~~)", "cw.cw_customerewbcode in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ForcreatemanifestColumns();
	}
	
	public void setChn_code(String chn_code) {
		this.setField(0, chn_code);
	}

	public String getChn_code() {
		return this.getField(0);
	}

	public void setOpidrecord(String OpIdRecord) {
		this.setField(1, OpIdRecord);
	}

	public String getOpidrecord() {
		return this.getField(1);
	}

	public void setRecordstartdate(String RecordStartdate) {
		this.setField(2, RecordStartdate);
	}

	public String getRecordstartdate() {
		return this.getField(2);
	}

	public void setRecordenddate(String RecordEnddate) {
		this.setField(3, RecordEnddate);
	}

	public String getRecordenddate() {
		return this.getField(3);
	}

	public void setBw_labelcode(String bw_labelcode) {
		this.setField(4, bw_labelcode);
	}

	public String getBw_labelcode() {
		return this.getField(4);
	}

	public void setCo_code(String co_code) {
		this.setField(5, co_code);
	}

	public String getCo_code() {
		return this.getField(5);
	}

	public void setCws_code(String cws_code) {
		this.setField(6, cws_code);
	}

	public String getCws_code() {
		return this.getField(6);
	}

	public void setSsg_code(String ssg_code) {
		this.setField(7, ssg_code);
	}

	public String getSsg_code() {
		return this.getField(7);
	}

	public void setNotexistsmanifestsign(String notexistsmanifestsign) {
		this.setField(8, notexistsmanifestsign);
	}

	public String getNotexistsmanifestsign() {
		return this.getField(8);
	}

	public void setEe_structurecode(String ee_structurecode) {
		this.setField(9, ee_structurecode);
	}

	public String getEe_structurecode() {
		return this.getField(9);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(10, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(10);
	}

	public void setCwcustomerewbcode(String cwcustomerewbcode) {
		this.setField(11, cwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(11);
	}

}
