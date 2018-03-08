package kyle.leis.es.businessrule.manifest.dax;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnColumns;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnCondition;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnQuery;
import kyle.leis.es.businessrule.manifest.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.manifest.da.ManifestexportformatCondition;
import kyle.leis.es.businessrule.manifest.da.ManifestexportformatQuery;
import kyle.leis.es.businessrule.manifest.da.ManifeststandardQuery;
import kyle.leis.es.businessrule.manifest.da.MefseqColumns;
import kyle.leis.es.businessrule.manifest.da.MefseqQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TbrManifestefcolumn;
import kyle.leis.hi.TbrManifestefcolumnPK;
import kyle.leis.hi.TbrManifestexportformat;
import kyle.leis.hi.TdiManifeststandardcolumn;
import kyle.leis.hi.TdiOperator;

public class ManifestCustomDemand {
	//查询所有清单标准列
	public List queryManifestStandardColumns() throws Exception{
		ManifeststandardQuery objMFCQ = new ManifeststandardQuery();
		return objMFCQ.getResults();
	}
	
	//查询清单导出格式
	public List queryManifestexportformat(ManifestexportformatCondition mfefCondition) throws Exception{
		ManifestexportformatQuery objMFEFQ=new ManifestexportformatQuery();
		objMFEFQ.setCondition(mfefCondition);
		return objMFEFQ.getResults();
	}
	
	//查询导出清单列
	public List queryManifestefcolumn(ManifestefcolumnCondition objmefcCondition) throws Exception{
		ManifestefcolumnQuery objmefcQuery = new ManifestefcolumnQuery();
		objmefcQuery.setCondition(objmefcCondition);
		return objmefcQuery.getResults();
	}
	
	//序列生成
	public static String getNewMefCode() throws Exception {
		MefseqQuery objMefseqQuery = new MefseqQuery();
		List objList = objMefseqQuery.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception ("无法根据菜单的序列号生成主键!"));
		return ((MefseqColumns)objList.get(0)).getSmefseq();
	}
	public static void setTbrMEF(TbrManifestexportformat tbrmef,ManifestexportformatColumns mefc) throws Exception{
		tbrmef.setMefBegincolumn(Integer.parseInt(mefc.getMefmefbegincolumn()));
		tbrmef.setMefBeginrow(Integer.parseInt(mefc.getMefmefbeginrow()));		
		tbrmef.setMefEname(mefc.getMefmefename());
		tbrmef.setMefExportfilesuffix(mefc.getMefmefexportfilesuffix());
		tbrmef.setMefModifydate(DateFormatUtility.getSysdate());
		tbrmef.setMefName(mefc.getMefmefname());
		tbrmef.setMefTemplatepath(mefc.getMefmeftemplatepath());
		//创建人
		TdiOperator tdiCreateOperator=null;
		if(!StringUtility.isNull(mefc.getOpcopid())){
			tdiCreateOperator=TdiOperatorDC.loadByKey(mefc.getOpcopid());
			tbrmef.setTdiOperatorByMefCreator(tdiCreateOperator);
		}
		//修改人
		TdiOperator tdiModifyOperator=null;
		if(!StringUtility.isNull(mefc.getOpmopid())){
			tdiModifyOperator=TdiOperatorDC.loadByKey(mefc.getOpmopid());
			tbrmef.setTdiOperatorByMefModifier(tdiModifyOperator);
		}
	}
	public static void setTbrMEFC(Session objSession,TbrManifestefcolumn objTbrManifestefcolumn,
			ManifestefcolumnColumns objManifestefcolumn,int mefcId,Long obj_mefcode) throws Exception{
		TbrManifestefcolumnPK comp_id=new TbrManifestefcolumnPK();
		comp_id.setMefcId(mefcId);
		comp_id.setMefCode(obj_mefcode);
		objTbrManifestefcolumn.setComp_id(comp_id);
		objTbrManifestefcolumn.setMefcCaptionname(objManifestefcolumn.getMefcmefccaptionname());
		objTbrManifestefcolumn.setMefcFixedcolumnformula(objManifestefcolumn.getMefcmefcfixedcolumnformula());
		objTbrManifestefcolumn.setMefcStructruecode(objManifestefcolumn.getMefcmefcstructruecode());
		if(!StringUtility.isNull(objManifestefcolumn.getMscmsccode())){
			TdiManifeststandardcolumn objTdiManifeststandardcolumn = (TdiManifeststandardcolumn)objSession.load(TdiManifeststandardcolumn.class, 
					Long.parseLong(objManifestefcolumn.getMscmsccode()));
			objTbrManifestefcolumn.setTdiManifeststandardcolumn(objTdiManifeststandardcolumn);
		}	
		
	}
}
