package kyle.leis.eo.operation.manifest.bl;

import java.util.List;

import kyle.leis.eo.operation.manifest.dax.LoadResult;
import kyle.leis.eo.operation.manifest.dax.ManifestDemand;
import kyle.leis.eo.operation.manifest.tp.AddManifestTransaction;
import kyle.leis.eo.operation.manifest.tp.RemoveManifestvalueTrans;

public class Manifest {
	
	public List createManifest(String strOp_id_create,
			String strMfRemark,
			String strEecode,
			String[] astrCwCode) throws Exception {
		AddManifestTransaction objAddManifestT = new AddManifestTransaction();
		objAddManifestT.setParam(strOp_id_create, strMfRemark, astrCwCode, strEecode);
		objAddManifestT.execute();
		String strMfcode = objAddManifestT.getMfcode();
		ManifestDemand objManifestDemand = new ManifestDemand();
		return objManifestDemand.querySpsContent(strMfcode);
	}
	
	public LoadResult removeManifestvalue(String[] astrCwcode, 
			String strMfCode,
			String strOperId) throws Exception {
		RemoveManifestvalueTrans objRMValueTrans = new RemoveManifestvalueTrans();
		objRMValueTrans.setParam(astrCwcode, strMfCode, strOperId);
		objRMValueTrans.execute();
		// Ë¢ÐÂ
		ManifestDemand objManifestDemand = new ManifestDemand();
		return objManifestDemand.loadResults(strMfCode);
	}
	
}
