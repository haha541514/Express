package kyle.leis.eo.operation.customsdeclaration.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.customsdeclaration.da.AirportcustomsdataCondition;
import kyle.leis.eo.operation.customsdeclaration.da.AirportcustomsdataQuery;
import kyle.leis.eo.operation.customsdeclaration.da.CdaddressColumns;
import kyle.leis.eo.operation.customsdeclaration.da.CdaddressQuery;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsdeclarationColumns;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsdeclarationCondition;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsdeclarationQuery;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsreportCondition;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsreportQuery;
import kyle.leis.eo.operation.customsdeclaration.da.InputcustomsdataCondition;
import kyle.leis.eo.operation.customsdeclaration.da.InputcustomsdataQuery;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TopCustomsdeclaration;
import net.sf.hibernate.Session;

public class CustomsDeclarationDemand {
	/**
	 * 查询报关数据
	 * @param objCDCondition
	 * @return
	 * @throws Exception
	 */
	public static List query(CustomsdeclarationCondition objCDCondition) 
	throws Exception {
		CustomsdeclarationQuery objDCQuery = new CustomsdeclarationQuery();
		objDCQuery.setCondition(objCDCondition);
		return objDCQuery.getResults();
	}
	
	public static CdaddressColumns getRandomAddress() throws Exception {
		int iCdacode = (int)(Math.random() * 762);
		CdaddressQuery objCdaddressQuery = new CdaddressQuery();
		objCdaddressQuery.setCdscacode(String.valueOf(iCdacode));
		List listResults = objCdaddressQuery.getResults();
		return (CdaddressColumns)listResults.get(0);
	}
	
	
	/**
	 * 装载报关记录
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static List load(String strCwcode) throws Exception {
		CustomsdeclarationQuery objDCQuery = new CustomsdeclarationQuery();
		objDCQuery.setCwcode(strCwcode);
		return objDCQuery.getResults();		
	}
	
	
	/**
	 * 报关用的报表
	 * @param objCRCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryReport(CustomsreportCondition objCRCondition) throws Exception {
		if(!StringUtility.isNull(objCRCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objCRCondition.getEestructurecode());
			objCRCondition.setEestructurecode(strEestructurecode);
		}			
		
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strSystemPE) && 
				(strSystemPE.startsWith("SBD") || strSystemPE.startsWith("SLY"))) {
			CustomsreportQueryEX objobjCRQueryEX = new CustomsreportQueryEX();
			objobjCRQueryEX.setCondition(objCRCondition);
			return objobjCRQueryEX.getResults();
		} else {
			CustomsreportQuery objCRQuery = new CustomsreportQuery();
			objCRQuery.setCondition(objCRCondition);
			return objCRQuery.getResults();
		}
	}
	
	public static List queryForInputReport(InputcustomsdataCondition objICDC) 
	throws Exception {
		InputcustomsdataQuery objICDQuery = new InputcustomsdataQuery();
		if(!StringUtility.isNull(objICDC.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objICDC.getEestructurecode());
			objICDC.setEestructurecode(strEestructurecode);
		}			
		objICDQuery.setCondition(objICDC);
		return objICDQuery.getResults();
	}	
	
	public static List queryForAirportReport(AirportcustomsdataCondition objACDCondition) 
	throws Exception {
		AirportcustomsdataQuery objACDQuery = new AirportcustomsdataQuery();
		if(!StringUtility.isNull(objACDCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objACDCondition.getEestructurecode());
			objACDCondition.setEestructurecode(strEestructurecode);
		}		
		objACDQuery.setCondition(objACDCondition);
		return objACDQuery.getResults();
	}		
	
	
	/**
	 * 设置报关数据
	 * @param objTCD
	 * @param objCDColumns
	 * @param objSession
	 * @throws Exception
	 */
	public static void setTDeclarationByColumns(TopCustomsdeclaration objTCDeclartion,
			CustomsdeclarationColumns objCDColumns,
			Session objSession) throws Exception {
		objTCDeclartion.setCdEname(objCDColumns.getCdcdename());
		objTCDeclartion.setCdGrossweight(new BigDecimal(objCDColumns.getCdcdgrossweight()));
		objTCDeclartion.setCdName(objCDColumns.getCdcdname());
		objTCDeclartion.setCdPieces(Integer.parseInt(objCDColumns.getCdcdpieces()));
		objTCDeclartion.setCdTotalprice(new BigDecimal(objCDColumns.getCdcdtotalprice()));
		objTCDeclartion.setCdUnitprice(new BigDecimal(objCDColumns.getCdcdunitprice()));
		objTCDeclartion.setCdConsigneeaddress(objCDColumns.getCdcdconsigneeaddress());
		objTCDeclartion.setCdShipperaddress(objCDColumns.getCdcdshipperaddress());
		// 生成海关条码
		objTCDeclartion.setCdLabelcode(objCDColumns.getCdcdlabelcode());
		if(!StringUtility.isNull(objCDColumns.getCdcdamount()))
			objTCDeclartion.setCdAmount(new BigDecimal(objCDColumns.getCdcdamount()));
		if(!StringUtility.isNull(objCDColumns.getCdcdgoodslabelcode()))
			objTCDeclartion.setCdGoodslabelcode(objCDColumns.getCdcdgoodslabelcode());
		if(!StringUtility.isNull(objCDColumns.getCdcdunitname()))
			objTCDeclartion.setCdUnitname(objCDColumns.getCdcdunitname());
	}
}
