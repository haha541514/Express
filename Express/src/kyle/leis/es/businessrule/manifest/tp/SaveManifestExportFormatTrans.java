package kyle.leis.es.businessrule.manifest.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnColumns;
import kyle.leis.es.businessrule.manifest.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.manifest.dax.ManifestCustomDemand;
import kyle.leis.hi.TbrManifestefcolumn;
import kyle.leis.hi.TbrManifestexportformat;

public class SaveManifestExportFormatTrans extends AbstractTransaction {
	private ManifestexportformatColumns obj_mef;//导出清单格式
	private List  obj_mfefList;//导出清单列
	private Long obj_mefcode;
	
	public void setParam(ManifestexportformatColumns mefColumns,List mfefList){
		obj_mef=mefColumns;
		obj_mfefList=mfefList;
	}
	
	public Long getObj_mefcode() {
		return obj_mefcode;
	}

	public void transaction(Session objSession) throws Exception {
		
		if(obj_mef==null){
			return;
		}
		//导出清单格式增加或修改
		TbrManifestexportformat objTbrManifestexportformat=null;
		if(StringUtility.isNull(obj_mef.getMefmefcode())){
			objTbrManifestexportformat=new TbrManifestexportformat();
			objTbrManifestexportformat.setMefCreatedate(DateFormatUtility.getSysdate());
			objTbrManifestexportformat.setMefCode(Long.parseLong(ManifestCustomDemand.getNewMefCode()));
		}else{
			objTbrManifestexportformat=(TbrManifestexportformat)objSession.load(TbrManifestexportformat.class, 
					Long.parseLong(obj_mef.getMefmefcode()));
		}
		ManifestCustomDemand.setTbrMEF(objTbrManifestexportformat,obj_mef);
		objSession.save(objTbrManifestexportformat);
		obj_mefcode=objTbrManifestexportformat.getMefCode();
		
		//导出清单列增加或修改
		TbrManifestefcolumn objTbrManifestefcolumn=null;
		if(obj_mfefList != null && obj_mfefList.size()>0){
			//先删除
			objSession.delete(" from TbrManifestefcolumn mefc where mefc.comp_id.mefCode = " + obj_mefcode);
			//再插入
			for(int i=0;i<obj_mfefList.size();i++){
				ManifestefcolumnColumns objManifestefcolumn = (ManifestefcolumnColumns)obj_mfefList.get(i);
				objTbrManifestefcolumn=new TbrManifestefcolumn();
				objTbrManifestefcolumn.setTbrManifestexportformat(objTbrManifestexportformat);				
				ManifestCustomDemand.setTbrMEFC(objSession, objTbrManifestefcolumn, objManifestefcolumn, i+1, obj_mefcode);
				objSession.save(objTbrManifestefcolumn);
			}
		}
	}
}