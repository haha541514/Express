package kyle.leis.eo.operation.manifest.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforklexCondition;
import kyle.leis.eo.operation.manifest.bl.Manifest;
import kyle.leis.eo.operation.manifest.da.ForcreatemanifestCondition;
import kyle.leis.eo.operation.manifest.da.ManifestCondition;
import kyle.leis.eo.operation.manifest.dax.DGMSPSDataFactory;
import kyle.leis.eo.operation.manifest.dax.KlexCustomData;
import kyle.leis.eo.operation.manifest.dax.LoadResult;
import kyle.leis.eo.operation.manifest.dax.ManifestDemand;

public class ManifestService extends AService {
	public String queryCreateManifestData(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		ForcreatemanifestCondition objFCMC = (ForcreatemanifestCondition)objPD.getParameter(0, 
				ForcreatemanifestCondition.class);
		ManifestDemand objManifestDemand = new ManifestDemand();
		List listResults = objManifestDemand.queryForCreateManifest(objFCMC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	public String queryCreateManifestDataEX(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		ForcreatemanifestCondition objFCMC = (ForcreatemanifestCondition)objPD.getParameter(0, 
				ForcreatemanifestCondition.class);
		ManifestDemand objManifestDemand = new ManifestDemand();
		List listResults = objManifestDemand.queryForCreateManifestEX(objFCMC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}	
	
	public String queryManifest(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		ManifestCondition objManifestC = (ManifestCondition)objPD.getParameter(0, 
				ManifestCondition.class);
		ManifestDemand objManifestDemand = new ManifestDemand();
		List listResults = objManifestDemand.queryManifest(objManifestC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	public String querySpsContent(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String strMfcode = (String)objPD.getParameter(0, String.class);
		
		ManifestDemand objManifestDemand = new ManifestDemand();
		List listResults = objManifestDemand.querySpsContent(strMfcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	public String createManifest(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		String strOperId = (String)objPD.getParameter(0, String.class);
		String strMfremark = (String)objPD.getParameter(1, String.class);
		String[] astrCwCode = (String[])objPD.getParameterArray(2, String.class);
		String strEecode = (String)objPD.getParameter(3, String.class);
		
		Manifest objManifest = new Manifest();
		List listResults = objManifest.createManifest(strOperId, strMfremark, strEecode, astrCwCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	} 
	
	public String remove(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		String[] astrCwcode = objPD.getParameterArray(0, String.class);
		String strMfCode = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Manifest objManifest = new Manifest();
		LoadResult objLoadResult = objManifest.removeManifestvalue(astrCwcode, strMfCode, strOperId);
		
		return objLoadResult.toString();
	}
	
	public String loadResults(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strMfCode = (String)objPD.getParameter(0, String.class);
		ManifestDemand objManifestDemand = new ManifestDemand();
		LoadResult objLoadResult = objManifestDemand.loadResults(strMfCode);
		
		return objLoadResult.toString();		
	}
	
	public String buildKlexXMLData(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		HousewaybillforklexCondition objHFKC = (HousewaybillforklexCondition)objPD.getParameter(0, 
				HousewaybillforklexCondition.class);
		KlexCustomData objKlexCustomData = new KlexCustomData();
		String strXMLData = objKlexCustomData.buildXMLData(objHFKC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strXMLData);
		return objEncode.toString();		
	}
	
	public String buildDHLGlobleMailData(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strDbwLabelcode = (String)objPD.getParameter(0, String.class);
		DGMSPSDataFactory objDGMSPSDataFactory = new DGMSPSDataFactory();
		String strResults = objDGMSPSDataFactory.buildDGMSPSData(strDbwLabelcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strResults);
		return objEncode.toString();	
	}	
}
