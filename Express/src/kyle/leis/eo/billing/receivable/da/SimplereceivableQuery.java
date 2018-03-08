package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SimplereceivableQuery extends JGeneralQuery {
	
	public SimplereceivableQuery(){
	    m_strSelectClause = "SELECT rv.rv_id,rv.fs_code,rv.co_code,rv.ck_code,rv.fk_code,rv.chn_code,rv.rv_op_id_creator,rv.rv_createdate,rv.rv_op_id_modifier,rv.rv_modifydate,rv.cw_code,rv.br_id,rv.rv_currencyrate,rv.rv_actualtotal FROM t_bl_receivable rv";
	    m_strWhereClause = "rv.bk_code = 'A0101'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "rv.co_code = '~~'", "rv.fs_code in (~~)", "rv.fs_code not in (~~)", "rv.cw_code = ~~", "rv.br_id = ~~", "rv.fk_code IN (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SimplereceivableColumns();
	}
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setInfscode(String InfsCode) {
		this.setField(1, InfsCode);
	}

	public String getInfscode() {
		return this.getField(1);
	}

	public void setNotinfscode(String NotInfsCode) {
		this.setField(2, NotInfsCode);
	}

	public String getNotinfscode() {
		return this.getField(2);
	}

	public void setCw_code(String cw_code) {
		this.setField(3, cw_code);
	}

	public String getCw_code() {
		return this.getField(3);
	}

	public void setBrid(String brId) {
		this.setField(4, brId);
	}

	public String getBrid() {
		return this.getField(4);
	}

	public void setFkcode(String fkCode) {
		this.setField(5, fkCode);
	}

	public String getFkcode() {
		return this.getField(5);
	}

}
