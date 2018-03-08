package kyle.leis.eo.operation.housewaybill.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;

public class PredictOrderColumnsEX {
	
	private String opermode;
	private String promptinfo;
	private int index;
	private int iExcelRowIndex;
	private WaybillforpredictColumns waybillforpredict;
	private List<CargoinfoColumns> listCargoInfo;
	private List<CorewaybillpiecesColumns> listPieces;
	
	public String getOpermode() {
		return opermode;
	}
	
	public void setOpermode(String opermode) {
		this.opermode = opermode;
	}
	
	public WaybillforpredictColumns getWaybillforpredict() {
		return waybillforpredict;
	}
	
	public void setWaybillforpredict(WaybillforpredictColumns waybillforpredict) {
		this.waybillforpredict = waybillforpredict;
	}

	public List<CargoinfoColumns> getListCargoInfo() {
		return listCargoInfo;
	}

	public void setListCargoInfo(List<CargoinfoColumns> listCargoInfo) {
		this.listCargoInfo = listCargoInfo;
	}
	
	public List<CorewaybillpiecesColumns> getListCorewaybillpieces() {
		return listPieces;
	}

	public void setListCorewaybillpieces(List<CorewaybillpiecesColumns> listPieces) {
		this.listPieces = listPieces;
	}	
	
	public String getPromptinfo() {
		return promptinfo;
	}

	public void setPromptinfo(String promptinfo) {
		if (!StringUtility.isNull(this.promptinfo))
			this.promptinfo = this.promptinfo + ";" + promptinfo;
		else
			this.promptinfo = promptinfo;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getExcelRowIndex() {
		return iExcelRowIndex;
	}

	public void setExcelRowIndex(int iExcelRowIndex) {
		this.iExcelRowIndex = iExcelRowIndex;
	}
}