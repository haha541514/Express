package kyle.leis.eo.operation.predictwaybill.dax;

import java.util.List;
import java.util.concurrent.Callable;

import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.predictwaybill.bl.Predictwaybill;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;

public class PredictwaybillEX implements Callable<SavedResultUtility> {

	private long id;
	private PredictwaybillColumns objPredictwaybillColumns;
	private List m_listCargoinfo;
	private String strOperID;

	
	public PredictwaybillEX(long id,
			PredictwaybillColumns objPredictwaybillColumns,
			List listCargoinfo,
			String strOperID) {
		super();
		this.id = id;
		this.objPredictwaybillColumns = objPredictwaybillColumns;
		m_listCargoinfo = listCargoinfo;
		this.strOperID = strOperID;
	}


	public SavedResultUtility call() throws Exception {
		Predictwaybill pwb = new Predictwaybill();
		SavedResultUtility sru = new SavedResultUtility();
		sru = pwb.save(objPredictwaybillColumns, m_listCargoinfo, strOperID);
		return sru;
	}

}
