package kyle.leis.eo.operation.predictwaybill.dax;

import java.util.concurrent.Callable;

import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.predictwaybill.bl.Predictwaybill;

public class PredictwaybillDeclare implements Callable<SavedResultUtility> {

	private long id;
	private String strPwbcode;
	private String strOperID;

	public PredictwaybillDeclare(long id, String strPwbcode, String strOperID) {
		super();
		this.id = id;
		this.strPwbcode = strPwbcode;
		this.strOperID = strOperID;
	}

	public SavedResultUtility call() throws Exception {
		System.out.println("运单"+this.id+" 开始申报!");  
		Predictwaybill pwb = new Predictwaybill();
		SavedResultUtility sru = new SavedResultUtility();
		sru = pwb.upload(strPwbcode, strOperID);
		System.out.println("运单"+this.id+" 申报结束");
		return sru;
	}

}
