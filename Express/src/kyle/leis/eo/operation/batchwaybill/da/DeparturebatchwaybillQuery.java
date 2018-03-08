package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DeparturebatchwaybillQuery extends JGeneralQuery {
	
	public DeparturebatchwaybillQuery(){
	    m_strSelectClause = "SELECT distinct BW.BW_CODE, BW.BW_CREATEDATE,BW.BW_MODIFYDATE,BW.BW_COMPLETEDATE,BW.BW_AUDITDATE,BW.BW_APPROVEDATE,BW.ADD_DATE,BW.BW_REMARK,BW.BW_TOTALGROSSWEIGHT,BW.BW_TOTALPIECES,BW.BW_LABELCODE,BW.BW_BATCHNUMBER,BWS.BWS_CODE,BWS.BWS_NAME,ADT.ADT_CODE,ADT.ADT_NAME,CO.CO_CODE,CO.CO_NAME,CO.CO_SNAME,CO.CO_SENAME,CWP.CP_BAGLABELCODE FROM T_OP_COREWAYBILLPIECES CWP, T_OP_COREWAYBILL CW, T_OP_BATCHWAYBILL BW,T_DI_BATCHWAYBILLSTATUS BWS, T_DI_ARRIVALDEPARTURETYPE ADT, T_CO_CORPORATION CO";
	    m_strWhereClause = "CW.CW_CODE = CWP.CW_CODE AND BW.BW_CODE = CW.BW_CODE_DEPARTURE AND BWS.BWS_CODE = BW.BWS_CODE AND ADT.ADT_CODE = BW.ADT_CODE AND CO.CO_CODE = BW.CO_CODE";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "CWP.CP_BAGLABELCODE = '~~'", "BW.BW_CODE = ~~", "BW.ADD_DATE >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= BW.ADD_DATE", "BW.BW_LABELCODE = '~~'", "BWS.BWS_CODE = '~~'", "CO.CO_CODE = '~~'", "FUN_GET_ESSTRUCTURECODE(bw.ee_code) like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1};		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DeparturebatchwaybillColumns();
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
