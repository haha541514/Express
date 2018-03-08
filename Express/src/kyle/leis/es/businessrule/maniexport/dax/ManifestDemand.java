package kyle.leis.es.businessrule.maniexport.dax;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.maniexport.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.maniexport.da.ManifestexportformatCondition;
import kyle.leis.es.businessrule.maniexport.da.ManifestexportformatQuery;
import kyle.leis.es.businessrule.maniexport.tp.SaveManifestExportTP;
import kyle.leis.es.businessrule.manifest.da.MefseqColumns;
import kyle.leis.es.businessrule.manifest.da.MefseqQuery;

import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TbrManifestexportformat;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;


public class ManifestDemand {

	/**20160905
	 * Query Manifestexportformat
	 * by wukaiquan
	 * **/
	public List queryManifestexportformat(ManifestexportformatCondition mfefCondition) throws Exception{
		ManifestexportformatQuery objMFEFQ=new ManifestexportformatQuery();
		objMFEFQ.setCondition(mfefCondition);
		return objMFEFQ.getResults();
	}
	/**20160905
	 * getNewMefCode 
	 * by wukaiquan
	 * @throws Exception 
	 * **/
	public static String getNewMefCode() throws Exception {
		MefseqQuery objMefseqQuery = new MefseqQuery();
		List objList = objMefseqQuery.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception ("无法根据菜单的序列号生成主键!"));
		return ((MefseqColumns)objList.get(0)).getSmefseq();
	
	}

	/**20160905
	 * setTbrManifestExportformat 
	 * by wukaiquan
	 * @throws Exception 
	 * **/
	public static void setTbrManifestExportformat(
			TbrManifestexportformat format, ManifestexportformatColumns columns,boolean isCreteOperator) throws Exception {

		format.setMefBegincolumn(Integer.parseInt(columns.getMefmefbegincolumn()));
		format.setMefBeginrow(Integer.parseInt(columns.getMefmefbeginrow()));		
		format.setMefEname(columns.getMefmefename());
		format.setMefExportfilesuffix(columns.getMefmefexportfilesuffix());
		format.setMefModifydate(DateFormatUtility.getSysdate());
		format.setMefName(columns.getMefmefname());
		format.setMefTemplatepath(columns.getMefmeftemplatepath());
		if(isCreteOperator){//新增的时候,可以新增
		//创建人
		TdiOperator tdiCreateOperator=null;
		if(!StringUtility.isNull(columns.getOpcopid())){
			tdiCreateOperator=TdiOperatorDC.loadByKey(columns.getOpcopid());
			format.setTdiOperatorByMefCreator(tdiCreateOperator);
			}
		}
		//修改人
		TdiOperator tdiModifyOperator=null;
		if(!StringUtility.isNull(columns.getOpmopid())){
			tdiModifyOperator=TdiOperatorDC.loadByKey(columns.getOpmopid());
			format.setTdiOperatorByMefModifier(tdiModifyOperator);
		}
		//SScode
		TdiSimplestatus tdiSimplestatus = null;
		if(!StringUtility.isNull(columns.getSisscode())){
			tdiSimplestatus = TdiSimplestatusDC.loadByKey(columns.getSisscode());
			format.setTdiSimplestatus(tdiSimplestatus);;
			
		}
	}

	/**20160905
	 * findManiExportById 
	 * by wukaiquan
	 * @throws Exception 
	 * **/
	public static ManifestexportformatColumns findManiExportById(
			String mefmefcode) throws Exception {

		ManifestexportformatCondition condtion = new ManifestexportformatCondition();
		condtion.setMefcode(mefmefcode);
		ManifestexportformatQuery objMFEFQ=new ManifestexportformatQuery();
		objMFEFQ.setCondition(condtion);
		List objReturn = objMFEFQ.getResults();	
		if(CollectionUtility.isNull(objReturn)){
			return null;
		}else{
			return (ManifestexportformatColumns) objReturn.get(0);
		}
		
	
	}

	
}
