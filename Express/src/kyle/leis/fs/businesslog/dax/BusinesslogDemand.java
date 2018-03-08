package kyle.leis.fs.businesslog.dax;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.businesslog.da.BusinesslogColumns;
import kyle.leis.fs.businesslog.da.BusinesslogCondition;
import kyle.leis.fs.businesslog.da.BusinesslogQuery;
import kyle.leis.hi.TdiBusinesslogkind;
import kyle.leis.hi.TfsBusinesslog;
import kyle.leis.hi.TdiActionkind;
import kyle.leis.hi.TdiOperator;
import net.sf.hibernate.Session;

public class BusinesslogDemand {
	/*
	 * 查询日志
	 */
	public static List queryCondition(BusinesslogCondition objBusinessCondition)
			throws Exception {
		BusinesslogQuery objBusinesslogQuery = new BusinesslogQuery();
		objBusinesslogQuery.setCondition(objBusinessCondition);
		return objBusinesslogQuery.getResults();
	}

	/*
	 * 用于保存时的返回值
	 */
	/*public static BusinesslogQueryReturn loadByBlId(String blId) throws Exception
	{
		BusinesslogQueryReturn objBusinesslogQueryReturn = new BusinesslogQueryReturn();
		BusinesslogCondition objBusinesslogCondition = new BusinesslogCondition();
		objBusinesslogCondition.setBlid(blId);
		List listBusinesslog = queryCondition(objBusinesslogCondition);
		//    0
		objBusinesslogQueryReturn.setM_objBusinesslogCol((BusinesslogColumns)listBusinesslog.get(0));
		
		return objBusinesslogQueryReturn;
	}*/
	
	/*
	 * 用于保存时的返回值集(按blBusinesscode查集)
	 */
	public static List loadByblBusinesscode(String blBusinesscode) throws Exception
	{
		BusinesslogCondition objBusinesslogCondition = new BusinesslogCondition();
		objBusinesslogCondition.setBlbusinesscode(blBusinesscode);
		return queryCondition(objBusinesslogCondition);
		
	}
	
	
	/*
	 * 保存时设置table的值
	 */
	public static void setBusinesslogByColumns(TfsBusinesslog objBusinesslog,
			BusinesslogColumns objBusinesslogColumns,
			Session objSession) throws Exception
	{
		objBusinesslog.setBlBusinesscode(objBusinesslogColumns.getBlogblbusinesscode());
		objBusinesslog.setBlContent(objBusinesslogColumns.getBlogblcontent());
		if(StringUtility.isNull(objBusinesslogColumns.getBlogblcreatedate()))
			objBusinesslog.setBlCreatedate(DateFormatUtility.getSysdate());
		else
			objBusinesslog.setBlCreatedate(DateFormatUtility.getStandardDate(objBusinesslogColumns.getBlogblcreatedate()));
		
		if (StringUtility.isNull(objBusinesslogColumns.getAkakcode()))
			objBusinesslogColumns.setAkakcode("AD");	
		TdiActionkind tdiActionkind = (TdiActionkind)objSession.load(TdiActionkind.class, 
				objBusinesslogColumns.getAkakcode());
		objBusinesslog.setTdiActionkind(tdiActionkind);
		
		if(StringUtility.isNull(objBusinesslogColumns.getBlkblkcode()))
			objBusinesslogColumns.setBlkblkcode("OP");
		TdiBusinesslogkind tdiBusinesslogkind = (TdiBusinesslogkind)objSession.load(TdiBusinesslogkind.class, 
				objBusinesslogColumns.getBlkblkcode());	
		objBusinesslog.setTdiBusinesslogkind(tdiBusinesslogkind);
		
		if(!StringUtility.isNull(objBusinesslogColumns.getOpopid()))
		{
			TdiOperator tdiOperator = (TdiOperator)objSession.load(TdiOperator.class, Long.parseLong(objBusinesslogColumns.getOpopid()));
			objBusinesslog.setTdiOperator(tdiOperator);
		}
		
		
	}
}
