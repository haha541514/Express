package kyle.leis.eo.billing.calculate.freight.bl;

import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.pricegroup.bl.PricegroupSearch;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.fs.cachecontainer.dax.CacheContainerDemand;

public class FreightSearch {
	static Logger s_objLogger = Logger.getLogger(FreightSearch.class.getName());
	// 查找运费价格表
	public FreightpriceColumns search(FreightpriceCondition objFPCondition) 
	throws Exception {
		FreightpriceCondition objOldFPCondition = new FreightpriceCondition();
		objOldFPCondition.setFields(objFPCondition.getFields());
		objOldFPCondition.setUseCacheSign(true);
		// 优先查找公司价格
		try {
			objFPCondition.setUseCacheSign(true);
			FreightpriceColumns objFPColumns = searchCorFreightprice(objFPCondition);
			// 采购价则未查找到价格
			if (objFPColumns == null &&
					objFPCondition.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_COSTS)) {
				s_objLogger.warning("查找采购运费价格时，未找到任何价格表，请检查计费条件");
				return null;
			}
			// 采购价格
			if (objFPColumns != null)
				// objFPCondition.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_COSTS))
				return objFPColumns;
			// 销售价如找不到对应的客户价格则查找客户对应的价格组
			// 查找价格组
			objFPCondition.setCocode("");
			objFPCondition.setChncode("");
			PricegroupSearch objPricegroupSearch = new PricegroupSearch();
			String strPricegroup = objPricegroupSearch.searchPricegroup(objOldFPCondition);
			// 未设置价格组则查找公布价
			if (StringUtility.isNull(strPricegroup)) {
				objFPColumns = searchCommonPrice(objFPCondition);
				if (objFPColumns != null) {
					return objFPColumns;
				} else {
					s_objLogger.warning("未查找到任何客户价格组，请检查计费条件");
					return null;				
				}
			}
			// 客户价格组值
			int iPricegroup = Integer.parseInt(strPricegroup);
			// 循环递减取价格表
			while (iPricegroup >= 0) {
				objFPCondition.setPgcode(String.valueOf(iPricegroup));
				objFPColumns = searchFreightprice(objFPCondition);	
				if (objFPColumns != null && !StringUtility.isNull(objFPColumns.getFpepcode()))
					return objFPColumns;
				iPricegroup--;
			}
			// 仍然未查询到任何价格则查找公布价
			return searchCommonPrice(objFPCondition);
		} finally {
			objFPCondition.setFields(objOldFPCondition.getFields());
		}
	}
	
	/**
	 * 查找公司价格表
	 * @param objFPCondition
	 * @return
	 * @throws Exception
	 */
	private FreightpriceColumns searchCorFreightprice(FreightpriceCondition objFPCondition) 
	throws Exception {
		objFPCondition.setPgcode("");
		return searchFreightprice(objFPCondition);		
	}
	
	/**
	 * 查找价格表并判断结果是否符合要求
	 * @param objFPCondition
	 * @return
	 * @throws Exception
	 */
	private FreightpriceColumns searchFreightprice(FreightpriceCondition objFPCondition) 
	throws Exception {
		List objList = FreightPriceDemand.queryFreightPrice(objFPCondition);
		if (objList != null && objList.size() > 1) {
			s_objLogger.warning("查找公司运费价格时，找到1条以上的价格表，请检查计费条件");
			throw new Exception("查找公司运费价格时，找到1条以上的价格表，请检查计费条件");
		}
		// 正好找到价格表，则直接返回正确结果
		if (objList != null && objList.size() == 1) {
			FreightpriceColumns objFPColumns = (FreightpriceColumns)objList.get(0);
			return objFPColumns;
		} else if (StringUtility.isNull(objFPCondition.getEpecode()) && 
				!StringUtility.isNull(objFPCondition.getEecode())){
			// 查找价格支持的多个分公司
			String strEecode = objFPCondition.getEecode();
			try {
				objFPCondition.setEpecode(strEecode);
				objFPCondition.setEecode("");
				FreightpriceColumns objFPColumns = searchFreightprice(objFPCondition);
				return objFPColumns;
			} catch (Exception ex) {
				
			} finally {
				objFPCondition.setEpecode("");
				objFPCondition.setEecode(strEecode);	
			}
		}
		return null;
	}
	
	private FreightpriceColumns searchCommonPrice(FreightpriceCondition objFPCondition) throws Exception {
		String strPricegroup = CacheContainerDemand.getCommonpricegroup();
		if (StringUtility.isNull(strPricegroup))
			return null;
		objFPCondition.setPgcode(String.valueOf(strPricegroup));
		FreightpriceColumns objFPColumns = searchFreightprice(objFPCondition);	
		if (objFPColumns != null && !StringUtility.isNull(objFPColumns.getFpepcode()))
			return objFPColumns;
		return null;
	}
	
}
