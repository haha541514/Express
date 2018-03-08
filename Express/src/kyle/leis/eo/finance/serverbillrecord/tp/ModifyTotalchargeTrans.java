package kyle.leis.eo.finance.serverbillrecord.tp;

import java.util.HashMap;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;

public class ModifyTotalchargeTrans extends AbstractTransaction {
	private String m_strSbrId;
	private String m_strSbrTotal;
	private List m_listServerPayable;
	
	public void setParam(String strSbrId, 
			List listServerPayable) throws Exception {
		m_strSbrId = strSbrId;
		m_listServerPayable = listServerPayable;
		// ͳ�Ʒ����ܶ�
		m_strSbrTotal = ServerBillRecordDemand.sumServerBillDetailTotal(strSbrId);
	}
	
	public void transaction(Session objSession) throws Exception {
		String strUpdateSql = "UPDATE T_FI_SERVERBILLRECORD sbr SET sbr.SBR_TOTAL = " +
		m_strSbrTotal + " WHERE sbr.SBR_ID = " + m_strSbrId;
		execute(objSession, strUpdateSql);
		// �޸��˵�����
		if (m_listServerPayable == null || m_listServerPayable.size() < 1)
			return;
		HashMap<String, Integer> hmServerPayable = new HashMap<String, Integer>();
		for (int i = 0; i < m_listServerPayable.size(); i++) {
			ServerpayableColumns objSPYColumns = (ServerpayableColumns)m_listServerPayable.get(i);
			String strServerEwbcode = objSPYColumns.getSwbswbserverewbcode();
			if (StringUtility.isNull(strServerEwbcode) ||
					StringUtility.isNull(objSPYColumns.getSwbswbreferencecode())) continue;
			// �ж��Ƿ��Ѿ����¹�
			if (hmServerPayable.containsKey(strServerEwbcode)) continue;
			// ��ѯ�˷ѡ�ȼ�ͺ��ӷ�
			String strFreightCharge = ServerBillRecordDemand.sumServerFreightCharge(strServerEwbcode,
					objSPYColumns.getSwbswbreferencecode());
			String strOilCharge = ServerBillRecordDemand.sumServerOilCharge(strServerEwbcode,
					objSPYColumns.getSwbswbreferencecode());
			String strIncidentalCharge = ServerBillRecordDemand.sumServerIncidentalCharge(strServerEwbcode,
					objSPYColumns.getSwbswbreferencecode());
			// ���·������˵��еķ���
			strUpdateSql = "UPDATE t_fi_serverwaybill swb SET swb.swb_totalfreightcharge = " + strFreightCharge + 
			", swb.swb_totalincidentalcharge = " + strIncidentalCharge +
			", swb.swb_totalsurcharge = " + strOilCharge +
			" WHERE swb.swb_serverewbcode = '" + strServerEwbcode + "'" +
			"   and swb.SWB_REFERENCECODE = " + objSPYColumns.getSwbswbreferencecode();
			execute(objSession, strUpdateSql);
			
			hmServerPayable.put(strServerEwbcode, 1);
		}
	}
}
