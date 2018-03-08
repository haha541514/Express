package kyle.leis.eo.finance.financialstatistics.tp;

import java.math.BigDecimal;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TcoFinancialstatistic;
import kyle.leis.hi.TcoFinancialstatisticPK;

public class SaveWhenCashModifyTrans extends AbstractTransaction {
	private String m_strCocode;
	private String m_strOriginCrtotal;
	private String m_strCurrentCrtotal;
	
	public void setParam(String strCocode, 
			String strOriginCrtotal, 
			String strCurrentCrtotal) {
		m_strCocode = strCocode;
		m_strOriginCrtotal = strOriginCrtotal;
		m_strCurrentCrtotal = strCurrentCrtotal;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strCocode)) return;
		if (StringUtility.isNull(m_strOriginCrtotal))
			m_strOriginCrtotal = "0";
		
		TcoFinancialstatisticPK objTFPK = new TcoFinancialstatisticPK();
		objTFPK.setCoCode(m_strCocode);
		objTFPK.setFsCarryoverenterprise("ALL");
		objTFPK.setCkCode("RMB");
		TcoFinancialstatistic objFStatistic = (TcoFinancialstatistic)objSession.load(TcoFinancialstatistic.class, 
				objTFPK);
		
		BigDecimal objOriginBalance = objFStatistic.getFsBalanceamount();
		BigDecimal objBalanceDifference = new BigDecimal(m_strCurrentCrtotal).add(new BigDecimal(m_strOriginCrtotal).multiply(new BigDecimal("-1")));
		objFStatistic.setFsBalanceamount(objOriginBalance.add(objBalanceDifference));
		
		objSession.save(objFStatistic);
	}
}
