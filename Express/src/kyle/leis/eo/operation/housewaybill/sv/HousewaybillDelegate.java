package kyle.leis.eo.operation.housewaybill.sv;

import java.util.List;

import kyle.common.connectors.servlet.RemoteServlet;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

public class HousewaybillDelegate extends RemoteServlet {
	public HousewaybillDelegate() {
		super();
		setServiceName("HouseWayBillService");
	}
	
	public void syncUDFHousewaybill(String strCwcode) throws Exception {
		// 获得运单信息
		ForinputallColumns objFIAColumns = HousewaybillDemand.load(strCwcode);
		List listCargoInfo = CargoInfoDemand.queryByCwCode(strCwcode);		
		List listCorewaybillPieces = CorewaybillpiecesDemand.load(strCwcode);
		// 设置代理商为SLY
		objFIAColumns.setCocode_Cwcus("0");
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objFIAColumns);
		objEncode.addParameter(listCargoInfo);
		objEncode.addParameter(listCorewaybillPieces);
		objEncode.addParameter("0");
		// 调用同步运单的方法
		execute("syncHousewaybill", objEncode.toString());
	}
	
	public InputAllQReturn inputAllForService(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces) throws Exception {
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strOperId);
		objEncode.addParameter(objFIAColumns);
		objEncode.addParameter(listCargo);
		objEncode.addParameter(listWaybillPieces);
		// 调用同步运单的方法
		String strResults = execute("inputAllForService", objEncode.toString());
		// 解析返回值
		Decoder objPD = new Decoder(strResults);
		InputAllQReturn objIAQR = new InputAllQReturn();
		int iCount = objPD.getParameterCount();
		
		if (iCount == 1) {
			String[][] astrPrompt = objPD.getParameter2Array(0);
			PromptUtilityCollection objPU = new PromptUtilityCollection(astrPrompt);
			// 返回
			objIAQR.setPromptUtilityCollection(objPU);
			return objIAQR;
		} else if (iCount == 2 || iCount == 3) {
			List listInputColumns = objPD.getParameterList(0, ForinputallColumns.class);
			List listCorewaybillpieces = objPD.getParameterList(1, CorewaybillpiecesColumns.class);	
			// 返回
			objIAQR.setHWBResults(listInputColumns);
			objIAQR.setPieces(listCorewaybillpieces);
		} else if (iCount == 4) {
			String[][] astrPrompt = objPD.getParameter2Array(0);
			PromptUtilityCollection objPU = new PromptUtilityCollection(astrPrompt);
			
			List listInputColumns = objPD.getParameterList(1, ForinputallColumns.class);
			List listCorewaybillpieces = objPD.getParameterList(2, CorewaybillpiecesColumns.class);	
			// 返回
			objIAQR.setHWBResults(listInputColumns);
			objIAQR.setPieces(listCorewaybillpieces);	
			objIAQR.setPromptUtilityCollection(objPU);
		}
		return objIAQR;
	}	
	
	public InputAllQReturn inputAllForService(String strEncoder) throws Exception {
		// 调用同步运单的方法
		String strResults = execute("inputAllForService", strEncoder);
		// 解析返回值
		Decoder objPD = new Decoder(strResults);
		InputAllQReturn objIAQR = new InputAllQReturn();
		int iCount = objPD.getParameterCount();
		
		if (iCount == 1) {
			String[][] astrPrompt = objPD.getParameter2Array(0);
			PromptUtilityCollection objPU = new PromptUtilityCollection(astrPrompt);
			// 返回
			objIAQR.setPromptUtilityCollection(objPU);
			return objIAQR;
		} else if (iCount == 2 || iCount == 3) {
			List listInputColumns = objPD.getParameterList(0, ForinputallColumns.class);
			List listCorewaybillpieces = objPD.getParameterList(1, CorewaybillpiecesColumns.class);	
			// 返回
			objIAQR.setHWBResults(listInputColumns);
			objIAQR.setPieces(listCorewaybillpieces);
		} else if (iCount == 4) {
			String[][] astrPrompt = objPD.getParameter2Array(0);
			PromptUtilityCollection objPU = new PromptUtilityCollection(astrPrompt);
			
			List listInputColumns = objPD.getParameterList(1, ForinputallColumns.class);
			List listCorewaybillpieces = objPD.getParameterList(2, CorewaybillpiecesColumns.class);	
			// 返回
			objIAQR.setHWBResults(listInputColumns);
			objIAQR.setPieces(listCorewaybillpieces);	
			objIAQR.setPromptUtilityCollection(objPU);
		}
		return objIAQR;
	}		
	
}
