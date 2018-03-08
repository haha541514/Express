package kyle.leis.es.businessrule.manifest.bl;

import java.util.List;

import kyle.leis.es.bulletin.tp.DeleteBulletinTrans;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnCondition;
import kyle.leis.es.businessrule.manifest.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.manifest.da.ManifestexportformatCondition;
import kyle.leis.es.businessrule.manifest.dax.ManifestCustomDemand;
import kyle.leis.es.businessrule.manifest.tp.DeletedeleteManifestexportformatTrans;
import kyle.leis.es.businessrule.manifest.tp.SaveManifestExportFormatTrans;

public class ManifestCustom {
	//保存导出清单格式与导出清单列
	public List createManifestexportformat(ManifestexportformatColumns objmfefColumns,List mefcList) throws Exception{
		SaveManifestExportFormatTrans smeft = new SaveManifestExportFormatTrans();
		smeft.setParam(objmfefColumns,mefcList);
		smeft.execute();
		String mefCode = smeft.getObj_mefcode().toString();
		List getResults = null;
		ManifestCustomDemand mfcd = new ManifestCustomDemand();
		if(mefcList!=null && mefcList.size()>0){
			ManifestefcolumnCondition mefcCondition = new ManifestefcolumnCondition();
			mefcCondition.setMefcode(mefCode);
			getResults = mfcd.queryManifestefcolumn(mefcCondition);
		}else{
			ManifestexportformatCondition mfefCondition = new ManifestexportformatCondition();
			mfefCondition.setMefcode(mefCode);
			getResults = mfcd.queryManifestexportformat(mfefCondition);			
		}		
		return getResults;
	}
	public void deleteManifestexportformat(Long mefcode) throws Exception {
		DeletedeleteManifestexportformatTrans objMEFTrans = new DeletedeleteManifestexportformatTrans();
		objMEFTrans.setParam(mefcode);
		objMEFTrans.execute();
	}
}
