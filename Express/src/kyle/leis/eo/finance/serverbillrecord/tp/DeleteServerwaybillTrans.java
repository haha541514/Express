package kyle.leis.eo.finance.serverbillrecord.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.finance.serverbillrecord.da.CountserverpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ListserverwaybillColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableCondition;

@SuppressWarnings("unchecked")
public class DeleteServerwaybillTrans extends AbstractTransaction{	

	private String m_strSbrId;
	private List m_listWaybill;

	public void setParam(String strSbrId,
			List listWaybill) {
		m_strSbrId = strSbrId;
		m_listWaybill = listWaybill;
	}

	public void transaction(Session objSession) throws Exception {
		
		if(m_listWaybill == null || m_listWaybill.size() == 0)
			return ;
		
		for(int i = 0;i<m_listWaybill.size();i++){
			ListserverwaybillColumns objSWBColumns = (ListserverwaybillColumns)m_listWaybill.get(i);
			String strSwbcode = objSWBColumns.getSwbswb_code();
			CountserverpayableColumns objCSPBColumns = ServerBillRecordDemand.CountPayable(strSwbcode);
			int count = Integer.parseInt(objCSPBColumns.getCount());
			if(count == 0){				
				String strDeleteSwb = "delete from t_fi_serverwaybill swb where swb.swb_code = " + strSwbcode;
				execute(objSession,strDeleteSwb);
			}
			else{
				ServerpayableCondition objSPYCondition = new ServerpayableCondition();
				objSPYCondition.setSwbcode(strSwbcode);
			    List listServerPayable = ServerBillRecordDemand.queryPayable(objSPYCondition);
				ModifyTotalchargeTrans objMTCTrans = new ModifyTotalchargeTrans();
				objMTCTrans.setParam(m_strSbrId, listServerPayable);
				objMTCTrans.transaction(objSession);
			}
		}
		
		String strDeleteSbr = "delete from t_fi_serverbillrecord sbr where sbr.sbr_id = " + m_strSbrId;
		execute(objSession,strDeleteSbr);
	}

}
