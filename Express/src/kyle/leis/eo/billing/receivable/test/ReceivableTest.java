package kyle.leis.eo.billing.receivable.test;

import java.math.BigDecimal;

import kyle.common.connectors.util.Decoder;
import kyle.leis.eo.billing.receivable.bl.Receivable;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.billing.receivable.sv.ReceivableService;

public class ReceivableTest {
	private static ReceivableService s_objRVService = new ReceivableService();
	public static void main(String[] args) {
		try {
			// System.out.println(save());
			// System.out.println(eliminate());
			// delete();
			sumYesHoldUndoSignout();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static String save() throws Exception {
		String str = "~`2011-07-05 09:49:28~`2011-07-05 09:49:28~`2011-07-05 09:49:28~`~`5000~`2~`1~`10000~`10000~`~`101220~`2011-07-05 09:49:28~`0~`~`~`RMB~`人民币~`~`~`~`~`101220~`~`~`~`~`~`A0101~`快递运费~`13705~`麦登~`HJMD~`~`112077~`A0101~`收货重量应收~`C~`草稿~`~@~#112077~`~@~#101220~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objRVService.save(objPD);
	}
	
	public static String eliminate() throws Exception {
		//String str = "292740~`~@~#101242~`~@~#";
		//Decoder objPD = new Decoder(str);
		//return s_objRVService.eliminate(objPD);
		return new BigDecimal("0.00").toString();
	}
	
	public static String sumYesHoldUndoSignout() throws Exception {
		BigDecimal obj1 = ReceivableDemand.sumUndoSignout("1");
		BigDecimal obj2 = ReceivableDemand.sumHoldUndoSignout("1");
		
		BigDecimal obj3 = ReceivableDemand.sumTodayHoldUndoSignout("1");
		BigDecimal obj4 = ReceivableDemand.sumTodayUndoSignout("1");
		
		BigDecimal objYesHold = obj2.add(obj3.multiply(new BigDecimal("-1")));
		BigDecimal objYesUndosignout = obj1.add(obj4.multiply(new BigDecimal("-1")));
		
		System.out.println(objYesHold.toString());
		System.out.println(objYesUndosignout.toString());
		System.out.println(obj4.toString());
		
		return obj1.toString();
	}	
	
	public static void delete() throws Exception {
		String[] astr = new String[1];
		Receivable objReceivable = new Receivable();
		astr[0] = "11265";
		objReceivable.delete(astr, "0");
		
		astr[0] = "11261";
		objReceivable.delete(astr, "0");
		
		astr[0] = "11260";
		objReceivable.delete(astr, "0");
		
		astr[0] = "11218";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10993";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10875";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10872";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10816";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10786";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10700";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10683";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10672";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10648";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10628";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10590";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10573";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10537";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10534";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10521";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10443";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10396";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10345";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10312";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10258";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10251";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10214";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10198";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10189";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10180";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10161";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10158";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10139";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10083";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10049";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10042";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10005";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10004";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10003";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10002";
		objReceivable.delete(astr, "0");
		
		astr[0] = "10001";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9998";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9997";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9994";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9987";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9980";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9973";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9966";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9965";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9922";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9905";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9854";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9841";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9798";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9769";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9730";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9725";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9724";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9507";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9502";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9409";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9372";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9314";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9300";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9299";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9298";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9295";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9294";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9288";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9206";
		objReceivable.delete(astr, "0");
		
		astr[0] = "9204";
		objReceivable.delete(astr, "0");
		
	}
}
