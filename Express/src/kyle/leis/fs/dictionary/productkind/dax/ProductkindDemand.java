package kyle.leis.fs.dictionary.productkind.dax;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DBStringUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.bl.ExpressPrice;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiServerstructuregroupDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.fs.dictionary.productkind.da.PkcargokindQuery;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.da.ProductkindCondition;
import kyle.leis.fs.dictionary.productkind.da.ProductkindQuery;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TdiServerstructuregroup;

public class ProductkindDemand {

	public static List query(ProductkindCondition objPKCondition) throws Exception
	{
		ProductkindQuery objPKQuery = new ProductkindQuery();
		objPKQuery.setCondition(objPKCondition);
		return objPKQuery.getResults();
	}
	
	public static List queryPkcargokind(String strPkcode) throws Exception
	{
		PkcargokindQuery objPkcargokindQuery = new PkcargokindQuery();
		objPkcargokindQuery.setPkcode(strPkcode);
		return objPkcargokindQuery.getResults();
	}	
	
	
	public static ProductkindColumns queryBypkEname(String pkEname) throws Exception
	{
		ProductkindCondition objPKCondition = new ProductkindCondition();
		objPKCondition.setPkename(pkEname);
		objPKCondition.setUseCacheSign(true);
		List objList = query(objPKCondition);
		if(objList.size()<1) return null;
		return (ProductkindColumns)objList.get(0);
	}
	
	public static List<ProductkindColumns> queryProductkind2()throws Exception
	{
		ProductkindQuery objPKQ = new ProductkindQuery();
		objPKQ.setUseCachesign(true);
		List<ProductkindColumns> listPKC = (List<ProductkindColumns>)objPKQ.getResults();
		return listPKC;
	}	
	
	public static List queryAllProductkind()throws Exception
	{
		ProductkindQuery objPKQ = new ProductkindQuery();
		objPKQ.setSscode("ON");
		return objPKQ.getResults();
	}	
	
	public static List<ProductkindColumns> getCanUseProduct(String strCocode,
			String strDtcode,
			String strEecode) throws Exception {
		ExpressPrice e = new ExpressPrice();
		HashSet<String> hashset = e.searchProductKind(strCocode, strDtcode, strEecode);
		List<ProductkindColumns> listPKC = queryProductkind2();
		List<ProductkindColumns> listCanUsePKC = new ArrayList<ProductkindColumns>();
		
		for (ProductkindColumns pkc : listPKC) {
			if (!StringUtility.isNull(pkc.getPkpksigninrestrictsign()) &&
					pkc.getPkpksigninrestrictsign().equals("N")) {
				listCanUsePKC.add(pkc);
			} else {
				for (String str : hashset) {
					if (!StringUtility.isNull(str) && 
							str.equals(pkc.getPkpkcode())) {
						listCanUsePKC.add(pkc);
						break;
					}
				}
			}
		}
		return listCanUsePKC;
	}
	
	
	public static ProductkindColumns queryBypkCode(String pkCode) throws Exception
	{
		ProductkindCondition objPKCondition = new ProductkindCondition();
		objPKCondition.setPkcode(pkCode);
		objPKCondition.setUseCacheSign(true);
		List objList = query(objPKCondition);
		if(objList.size()<1) return null;
		return (ProductkindColumns)objList.get(0);
	}
	
	public static void setProductkindByColumns(TdiProductkind objTdiProductkind,
			ProductkindColumns objPKColumns) throws Exception
	{
		objTdiProductkind.setPkDescription(objPKColumns.getPkpkdescription());
		objTdiProductkind.setPkEname(objPKColumns.getPkpkename());
		objTdiProductkind.setPkName(objPKColumns.getPkpkname());
		objTdiProductkind.setPkSename(objPKColumns.getPkpksename());
		objTdiProductkind.setPkSname(objPKColumns.getPkpksname());
		objTdiProductkind.setPkStructurecode(objPKColumns.getPkstructurecode());
		objTdiProductkind.setPkShowserverewbcode(StringUtility.replaceWhenNull(objPKColumns.getPkshowserverewbcode(), 
				"Y"));
		objTdiProductkind.setTdiSimplestatus(TdiSimplestatusDC.loadByKey(objPKColumns.getSssscode()));
		
		// 分公司
		if (!StringUtility.isNull(objPKColumns.getEecode()))
			objTdiProductkind.setTdiEnterpriseelement(TdiEnterpriseelementDC.loadByKey(objPKColumns.getEecode()));
		else
			objTdiProductkind.setTdiEnterpriseelement(null);
		
		if(!StringUtility.isNull(objPKColumns.getPkpksaletrialsign()))
			objTdiProductkind.setPkSaletrialsign(objPKColumns.getPkpksaletrialsign());
		else
			objTdiProductkind.setPkSaletrialsign("Y");
		if(!StringUtility.isNull(objPKColumns.getPkpksigninrestrictsign()))
			objTdiProductkind.setPkSigninrestrictsign(objPKColumns.getPkpksigninrestrictsign());
		else
			objTdiProductkind.setPkSigninrestrictsign("N");
		
		if(!StringUtility.isNull(objPKColumns.getPksiprintselflabelcodesign()))
			objTdiProductkind.setPkSiprintselflabelcodesign(objPKColumns.getPksiprintselflabelcodesign());
		else
			objTdiProductkind.setPkSiprintselflabelcodesign("N");		
		
		
		if (!StringUtility.isNull(objPKColumns.getChncode())) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objPKColumns.getChncode());
			objTdiProductkind.setTchnChannel(objTchnChannel);
		} else {
			objTdiProductkind.setTchnChannel(null);
		}
		
		if (!StringUtility.isNull(objPKColumns.getSsgcode())) {
			TdiServerstructuregroup objTSSG = TdiServerstructuregroupDC.loadByKey(objPKColumns.getSsgcode());
			objTdiProductkind.setTdiServerstructuregroup(objTSSG);
		} else {
			objTdiProductkind.setTdiServerstructuregroup(null);
		}		
		
		objTdiProductkind.setPkBillingbybatchwaysign(StringUtility.replaceWhenNull(objPKColumns.getPkbillingbybatchwaysign(),
				"N"));
		objTdiProductkind.setPkSigninneedpostcode(objPKColumns.getPksigninneedpostcode());
		objTdiProductkind.setPkPcwrestrictformula(objPKColumns.getPkpcwrestrictformula());
		objTdiProductkind.setPkPdsrestrictformula(objPKColumns.getPkpdsrestrictformula());
		objTdiProductkind.setPkArrearallowsignout(objPKColumns.getPkarrearallowsignout());
	}
	
	
	/**
	 * 生成产品代码
	 * @param code
	 * @param lever 0表示同级产品代码，1表示子产品代码
	 * @return
	 * @throws Exception
	 */
	public static String createProductCode(String code, String lever) throws Exception{
		int leverWidth = 2;
		List list = queryAllProductkind();
		List<String> fieldList = CollectionUtility.getFieldList(list, "pkstructurecode");
		String parentCode = code;
		if ("0".equals(lever)) {
			parentCode = DBStringUtility.getParentCode(code, leverWidth);
		}
		return DBStringUtility.createStructureCode(parentCode, fieldList, leverWidth);
	}
	
	//根据产品名称得到产品介绍的链接
	  public static String findLinkByPkName(String PkName) throws Exception{
		  /*
		  ProductkindQuery objPKQ = new ProductkindQuery();
		  objPKQ.setField(1, PkName);
		  List<ProductkindColumns> listPKC = (List<ProductkindColumns>)objPKQ.getResults();
          return listPKC.get(0).getPkIntroductionlink();
          */
		  return "";
	  }
	
}
