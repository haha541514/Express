package kyle.leis.eo.operation.manifest.dax;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.manifest.da.ForcreatemanifestCondition;
import kyle.leis.eo.operation.manifest.da.ForcreatemanifestQuery;
import kyle.leis.eo.operation.manifest.da.ManifestColumns;
import kyle.leis.eo.operation.manifest.da.ManifestCondition;
import kyle.leis.eo.operation.manifest.da.ManifestQuery;
import kyle.leis.eo.operation.manifest.da.ManifestvalueColumns;
import kyle.leis.eo.operation.manifest.da.ManifestvalueQuery;
import kyle.leis.eo.operation.manifest.da.SpscontentColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TopManifest;

public class ManifestDemand {
	public List queryForCreateManifest(ForcreatemanifestCondition objFCMC) throws Exception {
		ForcreatemanifestQuery objFCMQ = new ForcreatemanifestQuery();
		if (!StringUtility.isNull(objFCMC.getEe_structurecode())) {
			String strEe_structurecode = EnterpriseelementDemand.getEestructurecode(objFCMC.getEe_structurecode());
			objFCMC.setEe_structurecode(strEe_structurecode);
		}
		objFCMQ.setCondition(objFCMC);
		return objFCMQ.getResults();
	}
	
	public List queryForCreateManifestEX(ForcreatemanifestCondition objFCMC) throws Exception {
		ForcreatemanifestQueryEX objFCMQ = new ForcreatemanifestQueryEX();
		if (!StringUtility.isNull(objFCMC.getEe_structurecode())) {
			String strEe_structurecode = EnterpriseelementDemand.getEestructurecode(objFCMC.getEe_structurecode());
			objFCMC.setEe_structurecode(strEe_structurecode);
		}
		objFCMQ.setCondition(objFCMC);
		return objFCMQ.getResults();
	}	
	
	
	public List queryManifest(ManifestCondition objManifestC) throws Exception {
		ManifestQuery objManifestQuery = new ManifestQuery();	
		if (!StringUtility.isNull(objManifestC.getEe_structurecode())) {
			String strEe_structurecode = EnterpriseelementDemand.getEestructurecode(objManifestC.getEe_structurecode());
			objManifestC.setEe_structurecode(strEe_structurecode);
		}		
		objManifestQuery.setCondition(objManifestC);
		return objManifestQuery.getResults();
	}
	
	public ManifestColumns loadManifestColumns(String strMfcode) throws Exception {
		ManifestQuery objManifestQuery = new ManifestQuery();	
		objManifestQuery.setMfcode(strMfcode);
		List objList = objManifestQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (ManifestColumns)objList.get(0);
	}
	
	public List loadManifestvalue(String strMfcode) throws Exception {
		ManifestvalueQuery objManifestvalueQuery = new ManifestvalueQuery();
		objManifestvalueQuery.setMfcode(strMfcode);
		return objManifestvalueQuery.getResults();
	}
	
	public LoadResult loadResults(String strMfcode) throws Exception {
		LoadResult objLoadResult = new LoadResult();
		objLoadResult.setManifestColumns(loadManifestColumns(strMfcode));
		objLoadResult.setManifestvalue(loadManifestvalue(strMfcode));
		return objLoadResult;
	}
	
	public List querySpsContent(String strMfcode) throws Exception {
		/*
		SpscontentCondition objSpsContentC = new SpscontentCondition();	
		objSpsContentC.setMfcode(strMfcode);
		SpscontentQuery objSpsContentQuery = new SpscontentQuery();
		objSpsContentQuery.setCondition(objSpsContentC);
		return objSpsContentQuery.getResults();
		*/
		List listManifestvalue = loadManifestvalue(strMfcode);
		if (listManifestvalue == null || listManifestvalue.size() < 1)
			return null;
		// ·µ»ØÖµ
		List<SpscontentColumns> listSpsContent = new ArrayList<SpscontentColumns>();
		DHLSPSData objDHLSPSData = new DHLSPSData();
		for (int i = 0; i < listManifestvalue.size(); i++) {
			ManifestvalueColumns objMFVColumns = (ManifestvalueColumns)listManifestvalue.get(i);
			String strCwcode = objMFVColumns.getCwcwcode();
			String strSpsContent = objDHLSPSData.build(strCwcode);
			
			SpscontentColumns objSpscontentColumns = new SpscontentColumns();
			objSpscontentColumns.setSpsContent(strSpsContent);
			listSpsContent.add(objSpscontentColumns);
		}
		return listSpsContent;
	}
	
	public TopManifest buildManifest(String strOp_id_create, 
			String strMfRemark, 
			Session objSession) throws Exception {
		TopManifest objTopManifest = new TopManifest();
		
		objTopManifest.setMfCreatedate(DateFormatUtility.getSysdate());
		objTopManifest.setMfModifydate(DateFormatUtility.getSysdate());
		objTopManifest.setMfRemark(strMfRemark);
		if (!StringUtility.isNull(strOp_id_create)) {
			TdiOperator objOperator = TdiOperatorDC.loadByKey(strOp_id_create);
			objTopManifest.setTdiOperatorByMfOpIdCreate(objOperator);	
			objTopManifest.setTdiOperatorByMfOpIdModify(objOperator);
		}
		TdiSimplestatus objSimplestatus = TdiSimplestatusDC.loadByKey("ON");
		objTopManifest.setTdiSimplestatus(objSimplestatus);
		
		return objTopManifest;
	}
}
