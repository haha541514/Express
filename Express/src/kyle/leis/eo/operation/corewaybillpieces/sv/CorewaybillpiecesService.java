package kyle.leis.eo.operation.corewaybillpieces.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.operation.corewaybillpieces.bl.Corewaybillpieces;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesCondition;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;

public class CorewaybillpiecesService extends AService {
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CorewaybillpiecesCondition objCWBPCondition = (CorewaybillpiecesCondition)objPD.getParameter(0, 
				CorewaybillpiecesCondition.class);
		List objList = CorewaybillpiecesDemand.query(objCWBPCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String modifyBgLabelcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String strOriginBgLabelcode = (String)objPD.getParameter(0, String.class);
		String strNewBgLabelcode  = (String)objPD.getParameter(1, String.class);
		
		Corewaybillpieces objCorewaybillpieces = new Corewaybillpieces();
		objCorewaybillpieces.modifyBgLabelcode(strOriginBgLabelcode, strNewBgLabelcode);
		
		return "";
	}	
	
	public String saveSelfLabelcodeTrans(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);

		List listCWPieces = objPD.getParameterList(0, CorewaybillpiecesColumns.class);
		String strCwcode = (String)objPD.getParameter(1, String.class);
		String strServerEwbcode  = (String)objPD.getParameter(2, String.class);
		
		Corewaybillpieces objCorewaybillpieces = new Corewaybillpieces();
		objCorewaybillpieces.saveSelfLabelcodeTrans(listCWPieces, 
				strCwcode, 
				strServerEwbcode);
		return "";
	}	
	
}
