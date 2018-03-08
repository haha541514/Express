package kyle.leis.eo.finance.cashrecord.bl;

import java.math.BigDecimal;

import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.eo.finance.cashrecord.dax.CashRecordDemand;
import kyle.leis.eo.finance.cashrecord.dax.ICashRecordBasicData;
import kyle.leis.eo.finance.cashrecord.tp.ModifyCashRecordStatusTrans;
import kyle.leis.eo.finance.cashrecord.tp.SaveCashByHWBTrans;
import kyle.leis.eo.finance.cashrecord.tp.SaveCashByServerBillTrans;
import kyle.leis.eo.finance.cashrecord.tp.SaveCashRecordTrans;
import kyle.leis.eo.finance.financialstatistics.bl.Financialstatistics;
import kyle.leis.es.smsservice.bl.AutoSendSms;
import kyle.leis.es.smsservice.bl.WeChatMsgSend;
import kyle.leis.hi.TfiCashrecord;

public class CashRecord {
	public CashrecordColumns save(CashrecordColumns objCashrecordColumns, 
			String strOperId) throws Exception {
		SaveCashRecordTrans objSaveCashRecordTrans = new SaveCashRecordTrans();
		objSaveCashRecordTrans.setParam(objCashrecordColumns, strOperId);
		objSaveCashRecordTrans.execute();
		// ˢ������
		Long lNewCrid = objSaveCashRecordTrans.getNewCrid();
		if (lNewCrid == null) return null;
		// �޸����
		modifyBalanceAmount(objSaveCashRecordTrans.getOriginCrtotal(),
				objSaveCashRecordTrans.getSavedCashrecord());
		// objSaveCashRecordTrans.getSavedCashrecord();
		CashrecordColumns objNewCashrecordCol = CashRecordDemand.load(String.valueOf(lNewCrid));
		//��������͵Ķ��ż�¼
		try{
			AutoSendSms objAutoSendSms = new /*AutoSendSms()*/ WeChatMsgSend();
			objAutoSendSms.saveAutoSmsmessage(objNewCashrecordCol.getCococode(), "SNK002", 
					objNewCashrecordCol.getCkckcode() + " " + objNewCashrecordCol.getCrcrtotal() + "Ԫ");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//������Ϣ���ƶ��ֻ�
		// CashRecordDemand.sendMessage(objNewCashrecordCol.getCococode(), objNewCashrecordCol.getCrcrtotal());
		
		return objNewCashrecordCol;
	}
	
	public CashrecordColumns saveDirectCustomerCash(CashrecordColumns objCashrecordColumns, 
			String strOperId, 
			String[] astrCwCode) throws Exception {
		SaveCashByHWBTrans objSaveDCCTrans = new SaveCashByHWBTrans();
		objSaveDCCTrans.setParam(objCashrecordColumns, strOperId, astrCwCode);
		objSaveDCCTrans.execute();
		
		Long lNewCrid = objSaveDCCTrans.getNewCrid();
		if (lNewCrid == null) return null;
		// �޸����
		modifyBalanceAmount("0",
				objSaveDCCTrans.getSavedCashrecord());		
		return CashRecordDemand.load(String.valueOf(lNewCrid));
	}
	
	/**
	 * ���������˵�����
	 * @param objCashrecordColumns
	 * @param strOperId
	 * @param astrSWBCode
	 * @return
	 * @throws Exception
	 */
	public CashrecordColumns saveByServerBillrecord(CashrecordColumns objCashrecordColumns, 
			String strOperId, 
			String[] astrSWBCode) throws Exception {
		SaveCashByServerBillTrans objSCBSBTrans = new SaveCashByServerBillTrans();
		objSCBSBTrans.setParam(objCashrecordColumns, strOperId, astrSWBCode);
		objSCBSBTrans.execute();
		
		Long lNewCrid = objSCBSBTrans.getNewCrid();
		if (lNewCrid == null) return null;
		// �޸����
		modifyBalanceAmount("0",
				objSCBSBTrans.getSavedCashrecord());		
		return CashRecordDemand.load(String.valueOf(lNewCrid));
	}
	
	public CashrecordColumns modifyStatus(String strCrid, 
			String strCrscode, 
			String strOperId) throws Exception {
		// ���޸�ȷ��״̬������ʱֱ��Ϊȷ��
		/*
		if (strCrscode.equals("C"))
			return CashRecordDemand.load(strCrid);
		*/
		ModifyCashRecordStatusTrans objModifyCRSTrans = new ModifyCashRecordStatusTrans();
		objModifyCRSTrans.setParam(strCrid, strCrscode, strOperId);
		objModifyCRSTrans.execute();
		// �޸����
		modifyBalanceAmount(objModifyCRSTrans.getOriginCrtotal(),
				objModifyCRSTrans.getSavedCashrecord());			
		// ˢ������
		return CashRecordDemand.load(strCrid);
	}
	
	/**
	 * �޸Ĺ�˾���
	 * @param strOriginCrtotal
	 * @param objSavedTfiCashrecord
	 */
	private void modifyBalanceAmount(String strOriginCrtotal, 
			TfiCashrecord objSavedTfiCashrecord) throws Exception {
		BigDecimal objNewCrtotal = objSavedTfiCashrecord.getCrTotal().multiply(objSavedTfiCashrecord.getCrCurrencyrate());
		objNewCrtotal = objNewCrtotal.divide(new BigDecimal("1"), 2, 4);
		// ����״̬��Ϊ����
		String strNewCrscode = objSavedTfiCashrecord.getTdiCashrecordstatus().getCrsCode();		
		if (strNewCrscode.equals(ICashRecordBasicData.CASHSTATUS_ELIMINATE))
			objNewCrtotal = new BigDecimal("0");
		// ������Ϊ����
		String strCrkcode = objSavedTfiCashrecord.getTdiCashrecordkind().getCrkCode();
		if (strCrkcode.equals(ICashRecordBasicData.CRK_PAYABLE_ACCOUNT)) {
			if (objSavedTfiCashrecord.getTcoCorporation().getTdiCustomersuppliertype().equals("S")) {
				objNewCrtotal = objSavedTfiCashrecord.getCrTotal();
			}
			objNewCrtotal = objNewCrtotal.multiply(new BigDecimal("-1"));
		}
		
		Financialstatistics.modifyCash(objSavedTfiCashrecord.getTcoCorporation().getCoCode(), 
				strOriginCrtotal, 
				objNewCrtotal.toString());
	}
}
