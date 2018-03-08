package kyle.leis.es.businessrule.maniexport.sv;

import java.util.List;

import com.sun.corba.se.impl.orbutil.ObjectUtility;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.maniexport.bl.MainifestBL;
import kyle.leis.es.businessrule.maniexport.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.maniexport.da.ManifestexportformatCondition;
import kyle.leis.es.businessrule.maniexport.dax.ManifestDemand;


public class ManifestService extends AService{

	
	/**20160905 
	 * Query MainifestExpot
	 * by wukaiquan
	 * **/
	public String queryManifestExport(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		ManifestexportformatCondition objFCMC = (ManifestexportformatCondition)objPD.getParameter(0, 
				ManifestexportformatCondition.class);
		ManifestDemand objManifestCustomDemand = new ManifestDemand();
		List listResults = objManifestCustomDemand.queryManifestexportformat(objFCMC);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();
		
	}
	
	/**20160905 
	 * deleteManifestExport
	 * by wukaiquan
	 * **/
	public String deleteManifestExport(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		String mefcode=(String)objPD.getParameter(0, String.class);
		MainifestBL mainifest = new MainifestBL();
		String objReturn = mainifest.deleteManifestExport(mefcode);
		
		
		
		if(StringUtility.isNull(objReturn)){
			return null;
		}else{
			Encoder objEncoder = new Encoder();
			objEncoder.addParameter(objReturn);
			return objEncoder.toString();
		}
	}

	/**20160905 
	 * saveManifestExport
	 * by wukaiquan
	 * **/
	public String saveManifestExport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifestexportformatColumns columns = (ManifestexportformatColumns)objPD.getParameter(0, 
				ManifestexportformatColumns.class);
		MainifestBL manifestBL = new MainifestBL();
		ManifestexportformatColumns listResults = manifestBL.saveManifestExport(columns);
		
		if(listResults == null){
			return null;
		}else{
			Encoder objEncoder = new Encoder();
			objEncoder.addParameter(listResults);
			return objEncoder.toString();
		}

	}
	
	
	
	
}
