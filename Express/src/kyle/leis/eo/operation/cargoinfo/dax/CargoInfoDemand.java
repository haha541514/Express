package kyle.leis.eo.operation.cargoinfo.dax;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoCondition;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoQuery;
import kyle.leis.eo.operation.cargoinfo.da.TophwbcargoinfoTR;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TopHousewaybill;
import kyle.leis.hi.TopHwbcargoinfo;
import kyle.leis.hi.TopHwbcargoinfoPK;

public class CargoInfoDemand {
	public static List queryByCwCode(String strCwcode) 
	throws Exception {
		CargoinfoCondition objCIC = new CargoinfoCondition();
		objCIC.setCwcode(strCwcode);
		CargoinfoQuery objCIQ = new CargoinfoQuery();
		objCIQ.setCondition(objCIC);
		return objCIQ.getResults();
	}
	
	public static String getDescription(String strCwcode) 
	throws Exception {
		CargoinfoCondition objCIC = new CargoinfoCondition();
		objCIC.setCwcode(strCwcode);
		CargoinfoQuery objCIQ = new CargoinfoQuery();
		objCIQ.setCondition(objCIC);
		List listResults = objCIQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return "Others * 1";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < listResults.size(); i++) {
			CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listResults.get(i);
			sb.append(StringUtility.replaceWhenNull(objCargoinfoColumns.getCiciattacheinfo(), 
					"Others"));
			sb.append(" * ");
			sb.append(objCargoinfoColumns.getCicipieces());
			sb.append(" ");
		}
		return sb.toString();
	}	
	
	public static List queryAndweightByCwCode(String strCwcode,
			String strTotalweight) 
	throws Exception {
		List listResults = queryByCwCode(strCwcode);
		if (listResults == null || listResults.size() < 1)
			return listResults;
		BigDecimal obj = new BigDecimal(strTotalweight);
		BigDecimal objDivideValue = obj.divide(new BigDecimal(listResults.size()), 3, 1);
		BigDecimal objRetail = obj.add(objDivideValue.multiply(new BigDecimal(listResults.size())).multiply(new BigDecimal("-1")));
		for (int i = 0; i < listResults.size(); i++) {
			CargoinfoColumns objCIC = (CargoinfoColumns)listResults.get(i);
			if (i == 0)
				objCIC.setCicihscode(objDivideValue.add(objRetail).toString());
			else
				objCIC.setCicihscode(objDivideValue.toString());
		}
		return listResults;
	}	
	
	
	
	public static String sumCargovalue(String strCwcode) 
	throws Exception {
		List listCargoInfo = queryByCwCode(strCwcode);
		if (listCargoInfo == null || listCargoInfo.size() < 1) return "0";
		BigDecimal objCargoTotal = new BigDecimal("0");
		for (int i = 0; i < listCargoInfo.size(); i++) {
			CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
			objCargoTotal = objCargoTotal.add(new BigDecimal(objCargoinfoColumns.getCicitotalprice()));
		}
		return objCargoTotal.toString();
	}	
	
	public static String sumCargovalue(List listCargoInfo) 
	throws Exception {
		if (listCargoInfo == null || listCargoInfo.size() < 1) return "0";
		BigDecimal objCargoTotal = new BigDecimal("0");
		for (int i = 0; i < listCargoInfo.size(); i++) {
			CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
			objCargoTotal = objCargoTotal.add(new BigDecimal(objCargoinfoColumns.getCicitotalprice()));
		}
		return objCargoTotal.toString();
	}		
	
	public static String getCargoCurrency(List listCargoInfo) 
	throws Exception {
		if (listCargoInfo == null || listCargoInfo.size() < 1) return "";
			
		CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(0);
		return objCargoinfoColumns.getCkckcode();
	}
	
	public static void setCargoInfoByColumns(TophwbcargoinfoTR objTophwbcargoinfoTR, 
			CargoinfoColumns objCIColumns, 
			String strCwcode) throws Exception {
		// 去掉特殊字符
		if (!SystempropertyDemand.getEnterprise().startsWith("SLYIM")) {
			Pattern pt = Pattern.compile("[^a-zA-Z 0-9%()&-]");
			objCIColumns.setCiciename(pt.matcher(objCIColumns.getCiciename()).replaceAll(""));
		}
		
		objTophwbcargoinfoTR.setCi_ename(objCIColumns.getCiciename());
		objTophwbcargoinfoTR.setCi_name(objCIColumns.getCiciname());
		
		if (!StringUtility.isNull(objCIColumns.getCicipieces())) {
			objTophwbcargoinfoTR.setCi_pieces(objCIColumns.getCicipieces());
		} else {
			objTophwbcargoinfoTR.setCi_pieces("0");
		}
		if (!StringUtility.isNull(objCIColumns.getCiciunitprice())) {
			objTophwbcargoinfoTR.setCi_unitprice(objCIColumns.getCiciunitprice());
		} else {
			objTophwbcargoinfoTR.setCi_unitprice("0");
		}
		// 重量
		if (!StringUtility.isNull(objCIColumns.getCiciweight())) {
			objTophwbcargoinfoTR.setCi_weight(objCIColumns.getCiciweight());
		} else {
			objTophwbcargoinfoTR.setCi_weight("0");
		}		
		objTophwbcargoinfoTR.setCi_hscode(objCIColumns.getCicihscode());
		objTophwbcargoinfoTR.setCi_attacheinfo(objCIColumns.getCiciattacheinfo());
		objTophwbcargoinfoTR.setCi_totalprice(objCIColumns.getCicitotalprice());
		objTophwbcargoinfoTR.setCi_remark(objCIColumns.getCiciremark());
		// 币种
		String strCkcode = objCIColumns.getCkckcode();
		if (StringUtility.isNull(strCkcode))
			strCkcode = "USD";
		objTophwbcargoinfoTR.setCk_code(strCkcode);		
		// 主键
		objTophwbcargoinfoTR.setCi_id(objCIColumns.getCicomp_idciid());
		objTophwbcargoinfoTR.setCw_code(strCwcode);
	}	
	
	
	public static void setCargoInfoByColumns(TopHwbcargoinfo objCargoInfo, 
			CargoinfoColumns objCIColumns, 
			TopHousewaybill objHWB) throws Exception {
		// 去掉特殊字符
		if (!SystempropertyDemand.getEnterprise().startsWith("SLYIM")) {
			Pattern pt = Pattern.compile("[^a-zA-Z 0-9%()&-]");
			objCIColumns.setCiciename(pt.matcher(objCIColumns.getCiciename()).replaceAll(""));
		}
		
		objCargoInfo.setCiEname(objCIColumns.getCiciename());
		objCargoInfo.setCiName(objCIColumns.getCiciname());
		if (!StringUtility.isNull(objCIColumns.getCicipieces())) {
			objCargoInfo.setCiPieces(new BigDecimal(objCIColumns.getCicipieces()).toBigInteger().intValue());
		} else {
			objCargoInfo.setCiPieces(0);
		}
		if (!StringUtility.isNull(objCIColumns.getCiciunitprice())) {
			objCargoInfo.setCiUnitprice(new BigDecimal(objCIColumns.getCiciunitprice()));
		} else {
			objCargoInfo.setCiUnitprice(new BigDecimal("0"));
		}
		
		objCargoInfo.setCiHscode(objCIColumns.getCicihscode());
		objCargoInfo.setCiAttacheinfo(objCIColumns.getCiciattacheinfo());
		objCargoInfo.setCiTotalprice(new BigDecimal(objCIColumns.getCicitotalprice()));
		objCargoInfo.setCiRemark(objCIColumns.getCiciremark());
		// 币种
		String strCkcode = objCIColumns.getCkckcode();
		if (StringUtility.isNull(strCkcode))
			strCkcode = "USD";
		TdiCurrencykind objTdiCurrencykind = TdiCurrencykindDC.loadByKey(strCkcode);
		objCargoInfo.setTdiCurrencykind(objTdiCurrencykind);		
		// 主键
		TopHwbcargoinfoPK objCargoInfoPK = new TopHwbcargoinfoPK();
		objCargoInfoPK.setCiId(Integer.parseInt(objCIColumns.getCicomp_idciid()));
		objCargoInfoPK.setCwCode(Long.parseLong(objCIColumns.getCicomp_idcwcode()));
		objCargoInfo.setComp_id(objCargoInfoPK);
		objCargoInfo.setTopHousewaybill(objHWB);
	}
}
