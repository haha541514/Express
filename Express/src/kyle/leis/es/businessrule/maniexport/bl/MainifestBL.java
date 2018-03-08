package kyle.leis.es.businessrule.maniexport.bl;

import java.util.List;

import kyle.leis.es.businessrule.maniexport.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.maniexport.dax.ManifestDemand;
import kyle.leis.es.businessrule.maniexport.tp.DeleteManiefstExportTP;
import kyle.leis.es.businessrule.maniexport.tp.SaveManifestExportTP;

public class MainifestBL {

	/**20160906
	 * setTbrManifestExportformat 
	 * by wukaiquan
	 * @throws Exception 
	 * **/
	public String deleteManifestExport(String mefcode) throws Exception {

		DeleteManiefstExportTP delete = new DeleteManiefstExportTP();
		delete.setParams(mefcode);
		delete.execute();
		
		return delete.getIsdelte();
	}

	/**20160906
	 * setTbrManifestExportformat 
	 * by wukaiquan
	 * @throws Exception 
	 * **/
	public ManifestexportformatColumns saveManifestExport(ManifestexportformatColumns Columns) throws Exception {
		SaveManifestExportTP saveManifestExport = new SaveManifestExportTP();
		saveManifestExport.setParams(Columns);
		saveManifestExport.execute();
		
		
		ManifestexportformatColumns objReturn = ManifestDemand.findManiExportById(Long.toString(saveManifestExport.getObj_mefcode()));
		
		
		return objReturn;
		
		
	}
}
