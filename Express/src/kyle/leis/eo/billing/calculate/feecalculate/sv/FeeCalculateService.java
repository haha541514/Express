package kyle.leis.eo.billing.calculate.feecalculate.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.billing.calculate.feecalculate.bl.AutoFeeCalculate;
import kyle.leis.eo.billing.calculate.feecalculate.bl.SaleTrialCalculate;
import kyle.leis.eo.billing.calculate.feecalculate.blx.BatchAutoFeeCalcThread;
import kyle.leis.eo.billing.calculate.feecalculate.dax.SaleTrialCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.SaleTrialCalculateResult;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;

public class FeeCalculateService extends AService {
	
	public String recalculate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strCwcode = (String)objPD.getParameter(0, String.class);
		AutoFeeCalculate objAutoFeeCalculate = new AutoFeeCalculate();
		objAutoFeeCalculate.recalculate(strCwcode);
		
		return "";
	}
	
	public String calcReceivable(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strCwcode = (String)objPD.getParameter(0, String.class);
		AutoFeeCalculate objAutoFeeCalculate = new AutoFeeCalculate();
		objAutoFeeCalculate.calcReceivable(strCwcode, "");
		
		return "";
	}	
	
	
	public String recalcServerpayable(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strCwcode = (String)objPD.getParameter(0, String.class);
		AutoFeeCalculate objAutoFeeCalculate = new AutoFeeCalculate();
		objAutoFeeCalculate.calcServerpayable(strCwcode, "");
		
		return "";
	}	
	
	
	public String batchRecalculate(Decoder objPD) throws Exception {
		BatchAutoFeeCalcThread objBAFCThread = new BatchAutoFeeCalcThread();
		objBAFCThread.start();
		return "";
	}
	
	/**
	 * œ˙ € ‘À„
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String trialCalculateForSale(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String[] astrParameter = objPD.getParameterArray(0, String.class);
		List listCorewaybillpieces = objPD.getParameterList(1, CorewaybillpiecesColumns.class);
		
		SaleTrialCalculateParameter objSCParameter = new SaleTrialCalculateParameter(astrParameter);
		objSCParameter.setPiecesList(listCorewaybillpieces);
		
		SaleTrialCalculate objSTCalculate = new SaleTrialCalculate();
		List<SaleTrialCalculateResult> listResults = objSTCalculate.calculate(objSCParameter);
		
		Encoder objEncode = new Encoder();
		String[][] aastr = new String[listResults.size()][];
		for (int i = 0; i < listResults.size(); i++) {
			SaleTrialCalculateResult objSTCResult = listResults.get(i);
			String[]astr = objSTCResult.toStringArray();
			aastr[i] = astr;
		}
		objEncode.addParameter(aastr);
		return objEncode.toString();	
	}
}
