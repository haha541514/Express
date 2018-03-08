package kyle.leis.fs.dictionary.district.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.district.bl.DHLRemoteDistrict;
import kyle.leis.fs.dictionary.district.bl.District;
import kyle.leis.fs.dictionary.district.da.CityExportColumns;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteDistrictDemand;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteVerifyResult;

public class DhlremotedistrictService extends AService {

	/**
	 * 验证偏远地区
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String checkDhlremotedistrict(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String strCountryHubcode = (String) objPD.getParameter(0, String.class);
		String strCityname = (String) objPD.getParameter(1, String.class);
		String strPostcode = (String) objPD.getParameter(2, String.class);
		
		DHLRemoteDistrict objDHLRemoteDistrict = new DHLRemoteDistrict();
		DHLRemoteVerifyResult objDHLRemoteVerifyResult = objDHLRemoteDistrict.verify(strCountryHubcode, strPostcode, strCityname);
		DhlremotedistrictColumns objResult = objDHLRemoteVerifyResult.getDHLRemoteDistrict();
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objResult);
		return objEncode.toString();
	}
	
	/**
	 * 查询偏远地区
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryDhlremotedistrict(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String strCountryHubcode = (String) objPD.getParameter(0, String.class);
		String strCityname = (String) objPD.getParameter(1, String.class);
		String strPostcode = (String) objPD.getParameter(2, String.class);
		
		DHLRemoteDistrict objDHLRemoteDistrict = new DHLRemoteDistrict();
		List objResult = objDHLRemoteDistrict.query(strCountryHubcode, strPostcode, strCityname);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objResult);
		return objEncode.toString();
	}
	
	/**
	 * 统计国家偏远地区数量
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryDRDByCountryHubcode(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strCountryHubcode = (String) objPD.getParameter(0, String.class);
		List objResult = DHLRemoteDistrictDemand.queryDRDByCountryHubcode(strCountryHubcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objResult);
		return objEncode.toString();
	}
	
	
	/**
	 * 20160822 16:28 
	 * by wukaiquan
	 * option:先删除，然后导入数据,
	 * t_di_dhldistrict
	 * @throws Exception 
	 * **/
	public String ExportDhlDistricts(Decoder objPD) throws Exception{
		
		checkParameterCount(objPD,1,this);
		List listcolumns = objPD.getParameterList(0, CityExportColumns.class);
		District objDistrict = new District();
		String objReturn = objDistrict.saveMulitdhlDistrict(listcolumns);
	
		
		return objReturn;
		
	} 
	
	
}
