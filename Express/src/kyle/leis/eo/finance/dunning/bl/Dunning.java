package kyle.leis.eo.finance.dunning.bl;

import java.math.BigDecimal;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsColumns;
import kyle.leis.eo.finance.dunning.dax.DunningDemand;
import kyle.leis.eo.finance.dunning.tp.ModifySignoutTran;

public class Dunning {
	public String getCreditbalance(String strCocode) throws Exception {
		// ��ù�˾���
		// �������=��ʱ���+���ö��+��˾���
		FinancestatisticsColumns objFSColumns = DunningDemand.load(strCocode);
		if (objFSColumns == null) return "0";
		// ���ö��
		BigDecimal objCreditlimit = new BigDecimal("0");
		if (!StringUtility.isNull(objFSColumns.getCmcmcreditlimit()))
			objCreditlimit = new BigDecimal(objFSColumns.getCmcmcreditlimit());
		// ��ʱ���
		BigDecimal objTemporaryCLT = new BigDecimal("0");
		if (!StringUtility.isNull(objFSColumns.getCmcmtemporarycreditlimit()))
			objTemporaryCLT = new BigDecimal(objFSColumns.getCmcmtemporarycreditlimit());
		String strStartDate = objFSColumns.getCmcmtclstartdate();
		String strEndDate = objFSColumns.getCmcmtclenddate();
		if (StringUtility.isNull(strStartDate) || StringUtility.isNull(strEndDate))
			objTemporaryCLT = new BigDecimal("0");
		// �Ƿ�ʧЧ
		else if (DateFormatUtility.getStandardDate(strEndDate).before(DateFormatUtility.getSysdate()))
			objTemporaryCLT = new BigDecimal("0");
		
		objCreditlimit = objCreditlimit.add(objTemporaryCLT);
		// �������
		BigDecimal objBalanceAmount = new BigDecimal(objFSColumns.getFsfsbalanceamount());
		// ���Ͽۻ�����
		String strHoldHWRate = objFSColumns.getCmcmholdhwrate();
		if (StringUtility.isNull(strHoldHWRate) || 
				new BigDecimal(strHoldHWRate).compareTo(new BigDecimal("0")) == 0)
			return objBalanceAmount.add(objCreditlimit).toString();
		else {
			objBalanceAmount = objBalanceAmount.add(objCreditlimit);
			if (objBalanceAmount.compareTo(new BigDecimal("0")) < 0) {
				BigDecimal objHoldHWRate = new BigDecimal(strHoldHWRate);
				objBalanceAmount = objBalanceAmount.multiply(objHoldHWRate).divide(new BigDecimal("1"), 2, 2);
			}
			return objBalanceAmount.toString();
		}
	}
	
	public void modifySignout(String strCocode,String isAllow) throws Exception{
		ModifySignoutTran objModifySignoutTran=new ModifySignoutTran();
		objModifySignoutTran.setParam(strCocode, isAllow);
		objModifySignoutTran.execute();
	}
}
