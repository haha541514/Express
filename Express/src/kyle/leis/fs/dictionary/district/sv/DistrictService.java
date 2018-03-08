package kyle.leis.fs.dictionary.district.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.district.bl.District;
import kyle.leis.fs.dictionary.district.da.CityColumns;
import kyle.leis.fs.dictionary.district.da.CityCondition;
import kyle.leis.fs.dictionary.district.da.CityExportColumns;
import kyle.leis.fs.dictionary.district.da.DhldistrictColumns;
import kyle.leis.fs.dictionary.district.da.DhldistrictCondition;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdhldistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdistrictColumns;

import kyle.leis.fs.dictionary.district.da.DistrictjdbcColumns;
import kyle.leis.fs.dictionary.district.da.DistrictjdbcCondition;
import kyle.leis.fs.dictionary.district.da.RegionColumns;
import kyle.leis.fs.dictionary.district.da.RegionCondition;
import kyle.leis.fs.dictionary.district.da.StateColumns;
import kyle.leis.fs.dictionary.district.da.StateCondition;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class DistrictService extends AService {
	public String queryCountry(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strDkcode = (String)objPD.getParameter(0, String.class);
		List listResult = DistrictDemand.queryCountry(strDkcode);
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResult);
		return objEncode.toString();
	}
	
	public String queryDHLDistrict(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		DhldistrictCondition objDDCondition = (DhldistrictCondition)objPD.getParameter(0, 
				DhldistrictCondition.class);
		List listResult = DistrictDemand.queryDHLDistrict(objDDCondition);
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResult);
		return objEncode.toString();
	}	
	
	public String getDHLLocationcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		String strCityname = (String)objPD.getParameter(0, String.class);
		String strHubcode = (String)objPD.getParameter(1, String.class);
		String strNaHubcode = (String)objPD.getParameter(2, String.class);
		String strPostcode = (String)objPD.getParameter(3, String.class);
		
		String strDHLLocationcode = DistrictDemand.getDHLLocationcode(strCityname, 
				strHubcode, 
				strNaHubcode, 
				strPostcode);
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strDHLLocationcode);
		return objEncode.toString();
	}	
	
	
	// * 查询城市信息
	 
	public String queryCity(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CityCondition objCityCondition = (CityCondition) objPD.getParameter(0,
				CityCondition.class);
		List objList = DistrictDemand.queryCity(objCityCondition);
		Encoder objEncode = new Encoder();

		objEncode.addParameter(objList);
		return objEncode.toString();
	}

	
	 //* 添加/修改城市信息
	 
	public String saveCity(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CityColumns objCityColumns = (CityColumns) objPD.getParameter(0,
				CityColumns.class);
		String strOperator = (String) objPD.getParameter(1, String.class);

		District objDistrict = new District();
		CityColumns objRuturn = objDistrict.saveCity(objCityColumns,
				strOperator);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objRuturn);
		return objEncode.toString();
	}

	
	//* 删除城市信息
	 
	public String deleteCity(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String[] ctCode_list = objPD.getParameterArray(0, String.class);//ctCode_list = 0
		District objDistrict = new District();
		String result = objDistrict.delCity(ctCode_list);
		Encoder objEncode = new Encoder();
		if (result == "")
			objEncode.addParameter("删除成功");
		return objEncode.toString();
	}
	
	// * 查询省份信息
	 
	public String queryState(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		StateCondition objStateCondition = (StateCondition) objPD.getParameter(
				0, StateCondition.class);
		List objList = DistrictDemand.queryState(objStateCondition);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}

	
	// * 添加/修改省份信息
	 
	public String saveState(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		StateColumns objStateColumns = (StateColumns) objPD.getParameter(0,
				StateColumns.class);
		//String strOperator = (String) objPD.getParameter(1, String.class);
		//String strOperator = "1";
		District objDistrict = new District();
		StateColumns objReturn = objDistrict.saveState(objStateColumns);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();
	}
	
	// * 删除省份信息
	 
	public String deleteState(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		//String[] stCode_list = objPD.getParameterArray(0, String.class);//接受的是数组,我接收的是一个
		String stCode= (String) objPD.getParameter(0, String.class);
		District objDistrict = new District();
		String result = objDistrict.delState(stCode);
		Encoder objEncode = new Encoder();
		if (result == "")
			objEncode.addParameter("删除成功");
		return objEncode.toString();
	}

	
	// * 删除区域信息
	 
	public String deleteRegion(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String[] rgCode_list = objPD.getParameterArray(0, String.class);
		District objDistrict = new District();
		String result = objDistrict.delRegion(rgCode_list);
		Encoder objEncode = new Encoder();
		if (result == "")
			objEncode.addParameter("删除成功");
		return objEncode.toString();
	}

	
	 //* 添加/修改地区区域信息
	 
	public String saveRegion(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		RegionColumns objRegionColumns = (RegionColumns) objPD.getParameter(0,
				RegionColumns.class);
		String strOperator = (String) objPD.getParameter(1, String.class);

		District objDistrict = new District();
		RegionColumns objReturn = objDistrict.saveRegion(objRegionColumns,
				strOperator);

		Encoder objEndode = new Encoder();
		objEndode.addParameter(objReturn);
		return objEndode.toString();
	}

	
	 //* 查询地区区域信息
	 
	public String queryRegion(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		RegionCondition objRegionCondition = (RegionCondition) objPD
				.getParameter(0, RegionCondition.class);
		List objlist = DistrictDemand.queryRegion(objRegionCondition);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objlist);
		return objEncode.toString();
	}
	
	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:save
	 * @throws Exception 
	 * **/
	public String save(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		
		DicdistrictColumns columns = (DicdistrictColumns) objPD.getParameter(0, DicdistrictColumns.class);
		District objDistrict = new District();
		
		DicdistrictColumns objReturn =	objDistrict.save(columns);
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objReturn);
		return objEncoder.toString();
	}
	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:Query
	 * @throws Exception 
	 * **/
	@SuppressWarnings("unchecked")
	public String query(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		
		DistrictjdbcCondition condtion = (DistrictjdbcCondition)objPD.getParameter(0, DistrictjdbcCondition.class);
		List<DistrictjdbcColumns> list = DistrictDemand.jdbcQuery(condtion);
		Encoder  objEncoder = new Encoder();
		objEncoder.addParameter(list);

		return objEncoder.toString();
	}
	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:delete，
	 * @throws Exception 
	 * **/
	public String delete(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
	
		String code = (String) objPD.getParameter(0, String.class);
		District objDistrict = new District();
		String conditionStr = objDistrict.delete(code);
		if(conditionStr != null){
			return conditionStr;
		}else{
			return null;
		}
		
	}
	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:districts批量导入,接受对象数组
	 * @throws Exception 
	 * **/
/*	public String saveCitys(Decoder objPD) throws Exception{
		String objReturn = null;
		int params = objPD.getParameterCount();
		System.out.println(params); 
		checkParameterCount(objPD, params, this);
		CityExportColumns columns[] = new CityExportColumns[params];
		//String objReturn = (String) objPD.getParameter(params-1,String.class);//获得操作人
		for(int i = 0; i < params ; i++){
			columns[i] = (CityExportColumns) objPD.getParameter(i, CityExportColumns.class);
		} 
		District objDistrict = new District();
		for(int i = 0 ; i < params  ;i++ ){
			//批量导入
			 objReturn = objDistrict.saveMulitCity(columns[i]);
		}	
		return objReturn;
		
	}*/
	
	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:districts批量导入,接受list对象
	 * @throws Exception 
	 * **/
	

	public String saveCitys(Decoder objPD) throws Exception{
		String objReturn = null;
		checkParameterCount(objPD, 1, this);
		List listcolumns = objPD.getParameterList(0, CityExportColumns.class);
		District objDistrict = new District();
		objReturn = objDistrict.saveMulitCity(listcolumns);
	
		return objReturn;
		
	}
	
	/**
	 * 20160822 Tus 
	 * by wukaiquan 
	 * oprion:DhlDistricts批量导入,接受list对象
	 * @throws Exception 
	 * **/
	public String saveDhlDistricts(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		String objReturn = null;

		List listcolumns = objPD.getParameterList(0, DicdhldistrictColumns.class);
		District objDistrict = new District();
		objReturn = objDistrict.saveMulitdhlDistrict(listcolumns);
	
		return objReturn;
		
	}
	
	public String saveDhlRemotedDistricts(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		String objReturn = null;
		List listcolumns = objPD.getParameterList(0, DhlremotedistrictColumns.class);
		District objDistrict = new District();
		objReturn = objDistrict.saveMulitdhlRemotedDistrict(listcolumns);
		return "";
	}
	
}
