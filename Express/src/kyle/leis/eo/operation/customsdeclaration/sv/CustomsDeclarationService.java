package kyle.leis.eo.operation.customsdeclaration.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.operation.customsdeclaration.bl.CustomsDeclaration;
import kyle.leis.eo.operation.customsdeclaration.da.AirportcustomsdataCondition;
import kyle.leis.eo.operation.customsdeclaration.da.CdaddressColumns;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsdeclarationColumns;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsdeclarationCondition;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsreportCondition;
import kyle.leis.eo.operation.customsdeclaration.da.InputcustomsdataCondition;
import kyle.leis.eo.operation.customsdeclaration.dax.CustomsDeclarationDemand;

public class CustomsDeclarationService extends AService {
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		CustomsdeclarationCondition objCDCondition = (CustomsdeclarationCondition)objPD.getParameter(0, 
				CustomsdeclarationCondition.class);
		List listResults = CustomsDeclarationDemand.query(objCDCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	public String getRandomAddress(Decoder objPD) throws Exception {
		CdaddressColumns objCdaddressColumns = CustomsDeclarationDemand.getRandomAddress();
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCdaddressColumns);
		return objEncode.toString();
	}
	
	
	/**
	 * 导出海关数据
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryReport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		CustomsreportCondition objCRCondition = (CustomsreportCondition)objPD.getParameter(0, 
				CustomsreportCondition.class);
		List listResults = CustomsDeclarationDemand.queryReport(objCRCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}	
	
	public String queryForInputReport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		InputcustomsdataCondition objICDC = (InputcustomsdataCondition)objPD.getParameter(0, 
				InputcustomsdataCondition.class);
		List listResults = CustomsDeclarationDemand.queryForInputReport(objICDC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}		
	
	public String queryForAirportReport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		AirportcustomsdataCondition objACDCondition = (AirportcustomsdataCondition)objPD.getParameter(0, 
				AirportcustomsdataCondition.class);
		List listResults = CustomsDeclarationDemand.queryForAirportReport(objACDCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}		
	
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		List listCDColumns = objPD.getParameterList(0, CustomsdeclarationColumns.class);
		String strCwcode = (String)objPD.getParameter(1, String.class);
		
		CustomsDeclaration objCustomsDeclaration = new CustomsDeclaration();
		objCustomsDeclaration.save(listCDColumns, strCwcode);
		
		return "";
	}
	
	/**
	 * 打印海关条码
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String print(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		CustomsDeclaration objCustomsDeclaration = new CustomsDeclaration();
		objCustomsDeclaration.print(strCwcode, strOperId);
		
		return "";
	}
}
