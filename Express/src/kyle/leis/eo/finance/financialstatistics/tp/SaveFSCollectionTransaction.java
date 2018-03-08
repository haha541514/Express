package kyle.leis.eo.finance.financialstatistics.tp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;

public class SaveFSCollectionTransaction extends AbstractTransaction {
	private HashMap<String, String> m_hmRevDifference;
	private HashMap<String, String> m_hmPayDifference;
	private String m_strCkcode;
	private String m_strCwcode;
	private String m_strBkcode;
	
	private static Logger s_objLogger = Logger.getLogger(SaveFSCollectionTransaction.class.getName());
	
	public void setParam(HashMap<String, String> hmRevDifference,
			HashMap<String, String> hmPayDifference,
			String strCkcode,
			String strCwcode,
			String strBkcode) {
		m_hmRevDifference = hmRevDifference;
		m_hmPayDifference = hmPayDifference;
		m_strCkcode = strCkcode;
		m_strCwcode = strCwcode;
		m_strBkcode = strBkcode;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		// 更新存在应收差额的财务统计
		if (m_hmRevDifference != null && 
				m_hmRevDifference.size() > 0) {
			Iterator<String> objCocode = m_hmRevDifference.keySet().iterator();
			while (objCocode.hasNext()) {
				String strCocode = objCocode.next();
				String strPayAmount = "0";
				if (m_hmPayDifference != null && 
						m_hmPayDifference.containsKey(strCocode))
					strPayAmount = m_hmPayDifference.get(strCocode);
				// 更新应收应付费用
				if (StringUtility.isNull(m_strBkcode) || 
						m_strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) {
					SaveFSTransaction objSaveTrans = new SaveFSTransaction();
					objSaveTrans.setParam(strCocode, 
							m_hmRevDifference.get(strCocode), 
							strPayAmount,
							"",
							m_strCwcode,
							m_strBkcode,
							false);
					objSaveTrans.transaction(objSession);
				}
			}
		}
		// 更新只存在应付差额的财务统计
		if (m_hmPayDifference != null && m_hmPayDifference.size() > 0) {
			Iterator<String> objCocode = m_hmPayDifference.keySet().iterator();
			while (objCocode.hasNext()) {
				String strCocode = objCocode.next();
				if (m_hmRevDifference != null && 
						m_hmRevDifference.containsKey(strCocode)) 
					continue;
				// 更新应收应付费用
				if (StringUtility.isNull(m_strBkcode) || 
						m_strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW)) {
					SaveFSTransaction objSaveTrans = new SaveFSTransaction();
					objSaveTrans.setParam(strCocode, 
							"0", 
							m_hmPayDifference.get(strCocode),
							m_strCkcode,
							m_strCwcode,
							m_strBkcode,
							true);
					objSaveTrans.transaction(objSession);	
				}
			}
		}
	}
}
