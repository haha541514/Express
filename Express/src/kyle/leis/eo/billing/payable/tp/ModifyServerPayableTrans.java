package kyle.leis.eo.billing.payable.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.finance.serverbillrecord.da.DifferencedetailColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableCondition;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;

public class ModifyServerPayableTrans extends AbstractTransaction {

	private List m_listEqulServerpayable;
	
	public void setParam(List listEqulServerpayable) {
		m_listEqulServerpayable = listEqulServerpayable;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listEqulServerpayable == null || m_listEqulServerpayable.size() < 1)
			return;
		for (int i = 0; i < m_listEqulServerpayable.size(); i++) {
			DifferencedetailColumns objDDColumns = (DifferencedetailColumns)m_listEqulServerpayable.get(i);
			String strCwcode = objDDColumns.getCwcw_code();
			// 查询服务商应付
			ServerpayableCondition objSPYCondition = new ServerpayableCondition();
			objSPYCondition.setSbdreferencecode(strCwcode);
			List listServerpayable = ServerBillRecordDemand.queryPayable(objSPYCondition);
			if (listServerpayable == null || listServerpayable.size() < 1)
				continue;
			for (int j = 0; j < listServerpayable.size(); j++) {
				ServerpayableColumns objServerpayableColumns = (ServerpayableColumns)listServerpayable.get(j);
				String strUpdateSql = "UPDATE T_BL_PAYABLE py SET py.SPY_ID = " +
				objServerpayableColumns.getSpyspyid() + 
				// " WHERE py.FK_CODE = '" + objServerpayableColumns.getFkfkcode() + "'" +
				// "   AND py.CW_CODE = " + strCwcode;
				" WHERE py.CW_CODE = " + strCwcode;
				execute(objSession, strUpdateSql);
			}
		}
	}

}
