package kyle.leis.eo.finance.financialstatistics.tp;

import java.math.BigDecimal;
import java.util.logging.Logger;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TcoFinancialstatistic;
import kyle.leis.hi.TcoFinancialstatisticPK;

public class SaveFSTransaction extends AbstractTransaction {
	private String m_strCocode;
	private String m_strRecDifference;
	private String m_strPayDifference;
	private String m_strCkcode;
	private String m_strCwcode;
	private String m_strBkcode;
	private boolean m_isPayBalance;
	private static Logger s_objLogger = Logger.getLogger(SaveFSTransaction.class.getName());
	
	public void setParam(String strCocode, 
			String strRecDifference, 
			String strPayDifference,
			String strCkcode,
			String strCwcode,
			String strBkcode,
			boolean isPayBalance) {
		m_strCocode = strCocode;
		m_strRecDifference = strRecDifference;
		m_strPayDifference = strPayDifference;
		m_strCkcode = strCkcode;
		m_strCwcode = strCwcode;
		m_strBkcode = strBkcode;
		m_isPayBalance = isPayBalance;
		
	}

	public void transaction(Session objSession) throws Exception {
		TcoFinancialstatisticPK objTFPK = new TcoFinancialstatisticPK();
		objTFPK.setCoCode(m_strCocode);
		objTFPK.setCkCode("RMB");
		if (!StringUtility.isNull(m_strCkcode))
			objTFPK.setCkCode(m_strCkcode);
		
		objTFPK.setFsCarryoverenterprise("ALL");
		TcoFinancialstatistic objFStatistic = (TcoFinancialstatistic)objSession.load(TcoFinancialstatistic.class, 
				objTFPK);
		BigDecimal objOriginRecAmount = objFStatistic.getFsReceivableamount();
		BigDecimal objOriginPayAmount = objFStatistic.getFsPayableamount();
		BigDecimal objOriginBalance = objFStatistic.getFsBalanceamount();
		
		BigDecimal objReceivableAmount = objOriginRecAmount.add(new BigDecimal(m_strRecDifference));
		BigDecimal objPayableAmount = objOriginPayAmount.add(new BigDecimal(m_strPayDifference));
		BigDecimal objBalanceDifference = new BigDecimal(m_strPayDifference).add(new BigDecimal(m_strRecDifference).multiply(new BigDecimal("-1")));
		
		objFStatistic.setFsReceivableamount(objReceivableAmount);
		objFStatistic.setFsPayableamount(objPayableAmount);
		objFStatistic.setFsBalanceamount(objOriginBalance.add(objBalanceDifference));
		
		objSession.save(objFStatistic);
		
		StringBuffer sb = new StringBuffer();
		sb.append(DateFormatUtility.getStandardSysdate() + " ");
		if (!StringUtility.isNull(m_strCwcode))
			sb.append("cwcode " + m_strCwcode);
		
		sb.append(" isPayBalance " + m_isPayBalance);
		sb.append(" bkcode " + m_strBkcode);
		sb.append(" cocode " + m_strCocode);
		sb.append(" originbalance " + objOriginBalance.toString());
		sb.append(" balancedifference " + objBalanceDifference.toString());
		sb.append(" afterbalace " + objFStatistic.getFsBalanceamount().toString());	
		s_objLogger.warning(sb.toString());
		
	}
}
