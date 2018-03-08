package kyle.leis.eo.operation.specialtype.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.operation.specialtype.bl.RemoteDistrictSpecialtype;
import kyle.leis.eo.operation.specialtype.bl.Specialtype;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteVerifyResult;

public class SpecialtypeService extends AService {
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		List listResults = SpecialtypeDemand.load(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	/**
	 * 新增某项特殊类型
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String addSpecialtype(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strEstcode = (String)objPD.getParameter(1, String.class); 
		String strOperId = (String)objPD.getParameter(2, String.class); 
		String strRemark = (String)objPD.getParameter(3, String.class);
		
		Specialtype objSpecialtype = new Specialtype();
		List objList = objSpecialtype.addSpecialtype(strCwcode, 
				strEstcode, 
				strOperId, 
				strRemark,
				true);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		List listSpecialtypes = objPD.getParameterList(0, SpecialtypeColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class); 
		String strCwcode = (String)objPD.getParameter(2, String.class);
		
		Specialtype objSpecialtype = new Specialtype();
		List objList = objSpecialtype.save(listSpecialtypes, 
				strOperId, 
				strCwcode);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strEstcode = (String)objPD.getParameter(1, String.class);
		
		Specialtype objSpecialtype = new Specialtype();
		objSpecialtype.delete(strCwcode, strEstcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter("");
		return objEncode.toString();
	}	
	
	
	
	/**
	 * 删除所有特殊类型
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String deleteAll(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		
		Specialtype objSpecialtype = new Specialtype();
		objSpecialtype.delete(strCwcode, "");
		Encoder objEncode = new Encoder();
		objEncode.addParameter("");
		return objEncode.toString();
	}
	
	/**
	 * 查询ODA
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String checkODA(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class); 
		String strCountryHubcode = (String)objPD.getParameter(1, String.class);  
		String strPostcode = (String)objPD.getParameter(2, String.class);  
		String strCityname = (String)objPD.getParameter(3, String.class); 
		String strChncode = (String)objPD.getParameter(4, String.class); 
		
		RemoteDistrictSpecialtype objRDS = new RemoteDistrictSpecialtype();
		DHLRemoteVerifyResult objDHLRVR = objRDS.verify(strCwcode, 
				strCountryHubcode, 
				strPostcode, 
				strCityname,
				strChncode);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objDHLRVR.toStringArray());
		return objEncode.toString();
	}
}
