package kyle.leis.fs.dictionary.operator.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OperatorQuery extends HGeneralQuery {
	
	public OperatorQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.operator.da.OperatorColumns(op.opId, op.opCode, op.opName, op.opSex, op.opPassword, op.opEname, op.opSname, op.opIdnumber, op.opEmail, op.opIdCreator, op.opCreatedate, op.opIdModifier, op.opModifydate, op.opAddress, op.opTelephone, op.opMobile, op.opConfirmdate, ee.eeCode, ee.eeEsname, ee.eeName, ee.eeEname, dt.dtCode, co.coCode, os.osCode, dp.dpCode, ee.eeStructurecode, ek.eekCode,st.stCode,st.stName,ct.ctCode,ct.ctName) FROM TdiOperator as op inner join op.tdiEnterpriseelement as ee left join ee.tdiDistrict as dt left join op.tcoCorporation as co inner join op.tdiOperatorstatus as os inner join op.tdiDepartment as dp inner join ee.tdiEnterpriseelementkind as ek  left join op.tdiState as st left join op.tdiCity as ct";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "op.opId = ~~", "op.opCode = '~~'", "op.opPassword = '~~'", "op.opEname = '~~'", "op.opIdnumber = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
