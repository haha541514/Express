package kyle.leis.es.company.predicttemplate.dax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.predicttemplate.da.ColumnmappingtypeQuery;
import kyle.leis.es.company.predicttemplate.da.ColumnstemplateCondition;
import kyle.leis.es.company.predicttemplate.da.ColumnstemplateQuery;
import kyle.leis.es.company.predicttemplate.da.DictionarymappingtypeQuery;
import kyle.leis.es.company.predicttemplate.da.PredictdicmappingColumns;
import kyle.leis.es.company.predicttemplate.da.PredictdicmappingQuery;
import kyle.leis.es.company.predicttemplate.da.PredictordertemplateCondition;
import kyle.leis.es.company.predicttemplate.da.PredictordertemplateQuery;
import kyle.leis.es.company.predicttemplate.da.PredicttemplateColumns;
import kyle.leis.es.company.predicttemplate.da.PredicttemplateCondition;
import kyle.leis.es.company.predicttemplate.da.PredicttemplateQuery;
import kyle.leis.es.company.predicttemplate.da.PredicttemplatevalueColumns;
import kyle.leis.es.company.predicttemplate.da.PredicttemplatevalueQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TemplatecolumnQuery;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TcoPredictordertemplate;
import kyle.leis.hi.TcoPredictordertemplatevalue;
import kyle.leis.hi.TdiColumnmappingtype;
import kyle.leis.hi.TdiDictionarymappingkind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiTemplatecolumn;

public class PredicttemplateDemand {
	
	public static List query(PredicttemplateCondition objPTC) throws Exception {
		PredicttemplateQuery objPTQuery = new PredicttemplateQuery();
		objPTQuery.setCondition(objPTC);
		return objPTQuery.getResults();
	}
	
	public static PredicttemplateColumns load(String strPotid,
			boolean isUseCacheSign) throws Exception {
		PredicttemplateCondition objPTC = new PredicttemplateCondition();
		objPTC.setPotid(strPotid);
		objPTC.setUseCacheSign(isUseCacheSign);
		List listResults = query(objPTC);
		if (listResults == null || listResults.size() < 1)
			return null;
		return (PredicttemplateColumns)listResults.get(0);
	}
	
	public static List queryTemplatevalue(String strPotid,
			boolean isUseCacheSign) throws Exception {
		PredicttemplatevalueQuery objPTVQ = new PredicttemplatevalueQuery();
		objPTVQ.setPotid(strPotid);
		objPTVQ.setUseCachesign(isUseCacheSign);
		return objPTVQ.getResults();
	}
	
	public static Map<String, PredicttemplatevalueColumns> queryTemplatevalue(String strPotid) 
	throws Exception {
		Map<String, PredicttemplatevalueColumns> hmTemplatevalue = new HashMap<String, PredicttemplatevalueColumns>();
		List listResults = queryTemplatevalue(strPotid, true);
		if (listResults == null || listResults.size() < 1)
			return hmTemplatevalue;
		for (int i = 0; i < listResults.size(); i++) {
			PredicttemplatevalueColumns objPTVC = (PredicttemplatevalueColumns)listResults.get(i);
			hmTemplatevalue.put(objPTVC.getPotvpotvcolumnname(), objPTVC);
		}
		return hmTemplatevalue;
	}
	
	/**
	 * 映射方式
	 * @return
	 * @throws Exception
	 */
	public static List queryCMT() throws Exception {
		ColumnmappingtypeQuery objQuery = new ColumnmappingtypeQuery();
		List objList = objQuery.getResults();
		if(objList == null || objList.size()<0)
			return null;
		return objList;
	}
	
	/**
	 * 基础数据
	 * @return
	 * @throws Exception
	 */
	public static List queryDMT() throws Exception {
		DictionarymappingtypeQuery objQuery = new DictionarymappingtypeQuery();
		List objList = objQuery.getResults();
		if(objList == null || objList.size()<0)
			return null;
		return objList;
	}
	
	public static String getMappingvalue(String strPotid,
			String strOriginValue,
			String strDmkcode) throws Exception {
		PredictdicmappingQuery objPMQ = new PredictdicmappingQuery();
		objPMQ.setDmkcode(strDmkcode);
		objPMQ.setPodmoriginvalue(strOriginValue);
		objPMQ.setPotid(strPotid);
		objPMQ.setUseCachesign(true);
		List listResults = objPMQ.getResults();
		// 如果查找到记录则直接返回该模板的基础数据，如果没有查找到则还要查找公用数据
		if (listResults != null && listResults.size() > 0)
			return ((PredictdicmappingColumns)listResults.get(0)).getPodmpodmstandardvalue();
		
		objPMQ = new PredictdicmappingQuery();
		objPMQ.setDmkcode(strDmkcode);
		objPMQ.setPodmoriginvalue(strOriginValue);
		objPMQ.setPotid("");
		objPMQ.setUseCachesign(true);
		objPMQ.setIspotid(" null ");
		listResults = objPMQ.getResults();
		if (listResults != null && listResults.size() > 0)
			return ((PredictdicmappingColumns)listResults.get(0)).getPodmpodmstandardvalue();
		
		return null;
	}
	
	/**
	 * 标准模板列
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List queryTemplate() throws Exception{
		TemplatecolumnQuery objQuery = new TemplatecolumnQuery();
		List objList = objQuery.getResults();
		if(objList == null || objList.size()<0)
			return null;
		return objList;
	}
	
	/**
	 * 标准模板列映射客户模板项
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List queryColumnsTemplate(String strPotid) throws Exception{
		ColumnstemplateQuery objQuery = new ColumnstemplateQuery();
		ColumnstemplateCondition objCondition = new ColumnstemplateCondition();
		objCondition.setPotid(strPotid);
		objQuery.setCondition(objCondition);
		List objList = objQuery.getResults();
		if(objList == null || objList.size()<0)
			return null;
		return objList;
	}
	
	/**
	 * 查询模板名称
	 * @param strPotid
	 * @return
	 * @throws Exception
	 */
	public static List queryPredict(String strPotid, 
			String strCocode, 
			boolean isUseCache)
			throws Exception {
		PredictordertemplateQuery objQuery = new PredictordertemplateQuery();
		PredictordertemplateCondition objCondition = new PredictordertemplateCondition();
		objCondition.setPotid(strPotid);
		if(StringUtility.isNull(strCocode)){
			objCondition.setIscocode("null");
		}else{
			objCondition.setCocode(strCocode);
		}
		objCondition.setUseCacheSign(isUseCache);
		objQuery.setCondition(objCondition);
		List objList = objQuery.getResults();
		return objList;
	}
	
	public static void setTcoPOTVByColumns(TcoPredictordertemplatevalue objTPOTV,
			PredicttemplatevalueColumns objColumns, Session objSession)
			throws Exception {	
		if(!StringUtility.isNull(objColumns.getTctcid())){
			objTPOTV.setTdiTemplatecolumn((TdiTemplatecolumn)objSession.load(TdiTemplatecolumn.class, objColumns.getTctcid()));		
		}
		if(!StringUtility.isNull(objColumns.getCmtcmtcode())){
			objTPOTV.setTdiColumnmappingtype((TdiColumnmappingtype)objSession.load(TdiColumnmappingtype.class,objColumns.getCmtcmtcode()));		
		}
		
		if(!StringUtility.isNull(objColumns.getDmkdmkcode())){
			objTPOTV.setTdiDictionarymappingkind((TdiDictionarymappingkind)objSession.
					load(TdiDictionarymappingkind.class,objColumns.getDmkdmkcode()));
		}	
		objTPOTV.setPotvColumnname(objColumns.getPotvpotvcolumnname());	
	}
	
	public static void setTcoPOTByColumns(TcoPredictordertemplate objTPOT,
			PredicttemplateColumns objColumns, Session objSession)
			throws Exception {
		objTPOT.setTdiOperatorByOpIdCreator((TdiOperator)objSession.
				load(TdiOperator.class, Long.parseLong(objColumns.getCopopid())));
		objTPOT.setTdiOperatorByOpIdModifier((TdiOperator)objSession.
				load(TdiOperator.class, Long.parseLong(objColumns.getMopopid())));
		objTPOT.setPotCreatedate(DateFormatUtility.getSysdate());
		objTPOT.setPotModifydate(DateFormatUtility.getSysdate());
		objTPOT.setPotName(objColumns.getPotpotname());
		objTPOT.setPotRemark(objColumns.getPotpotremark());
		String strCocode =  objColumns.getCococode();
		objTPOT.setTcoCustomer((TcoCustomer)objSession.load(TcoCustomer.class,strCocode));
		
	}
}
