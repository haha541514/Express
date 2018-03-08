package kyle.leis.eo.operation.predictwaybill.baseorderimport;

import java.util.List;

import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;

public class OrderImportorInput {
	private PredictwaybillColumns predictwaybillColumns;
	private List<PredictcargoinfoColumns> listCargoInfo;

	public PredictwaybillColumns getPredictwaybillColumns() {
		return predictwaybillColumns;
	}

	public void setPredictwaybillColumns(
			PredictwaybillColumns predictwaybillColumns) {
		this.predictwaybillColumns = predictwaybillColumns;
	}

	public List<PredictcargoinfoColumns> getListCargoInfo() {
		return listCargoInfo;
	}

	public void setListCargoInfo(List<PredictcargoinfoColumns> listCargoInfo) {
		this.listCargoInfo = listCargoInfo;
	}

}
