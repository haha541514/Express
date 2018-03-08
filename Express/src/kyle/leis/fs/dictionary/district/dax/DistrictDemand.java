package kyle.leis.fs.dictionary.district.dax;


import java.util.Date;
import java.util.List;
import net.sf.hibernate.Session;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.cachecontainer.da.DistrictColumns;
import kyle.leis.fs.cachecontainer.da.DistrictQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCityDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiStateDC;
import kyle.leis.fs.dictionary.district.da.AlldhldistrictColumns;
import kyle.leis.fs.dictionary.district.da.AlldhldistrictQuery;
import kyle.leis.fs.dictionary.district.da.AlldistictColumns;
import kyle.leis.fs.dictionary.district.da.AlldistictCondition;
import kyle.leis.fs.dictionary.district.da.AlldistictQuery;
import kyle.leis.fs.dictionary.district.da.CityColumns;
import kyle.leis.fs.dictionary.district.da.CityCondition;
import kyle.leis.fs.dictionary.district.da.CityQuery;
import kyle.leis.fs.dictionary.district.da.CountryColumns;
import kyle.leis.fs.dictionary.district.da.CountryCondition;
import kyle.leis.fs.dictionary.district.da.CountryQuery;
import kyle.leis.fs.dictionary.district.da.DhldistrictColumns;
import kyle.leis.fs.dictionary.district.da.DhldistrictCondition;
import kyle.leis.fs.dictionary.district.da.DhldistrictQuery;
import kyle.leis.fs.dictionary.district.da.DhldistrictlocationColumns;
import kyle.leis.fs.dictionary.district.da.DhldistrictlocationQuery;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdhldistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdistrictCondition;
import kyle.leis.fs.dictionary.district.da.DicdistrictQuery;
import kyle.leis.fs.dictionary.district.da.DistrictandcountryColumns;
import kyle.leis.fs.dictionary.district.da.DistrictandcountryQuery;
import kyle.leis.fs.dictionary.district.da.DistrictjdbcColumns;
import kyle.leis.fs.dictionary.district.da.DistrictjdbcCondition;
import kyle.leis.fs.dictionary.district.da.DistrictjdbcQuery;
import kyle.leis.fs.dictionary.district.da.RegionColumns;
import kyle.leis.fs.dictionary.district.da.RegionCondition;
import kyle.leis.fs.dictionary.district.da.RegionQuery;
import kyle.leis.fs.dictionary.district.da.StateColumns;
import kyle.leis.fs.dictionary.district.da.StateCondition;
import kyle.leis.fs.dictionary.district.da.StateQuery;
import kyle.leis.fs.dictionary.district.da.TarazzdistrictColumns;
import kyle.leis.fs.dictionary.district.da.TarazzdistrictQuery;
import kyle.leis.fs.dictionary.district.da.XinshdistrictQuery;
import kyle.leis.hi.TdiCity;
import kyle.leis.hi.TdiDhldistrict;
import kyle.leis.hi.TdiDhlremotedistrict;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiDistrictkind;
import kyle.leis.hi.TdiRegion;
import kyle.leis.hi.TdiState;

public class DistrictDemand {
	public static DistrictColumns load(String strDtcode) throws Exception {
		DistrictQuery objDistrictQuery = new DistrictQuery();
		objDistrictQuery.setDtcode(strDtcode);
		List objList = objDistrictQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (DistrictColumns) objList.get(0);
	}

	public static String getDtcodeByDtename(String strCountrycode,
			String strDtename) throws Exception {
		DistrictandcountryQuery objDACQ = new DistrictandcountryQuery();
		objDACQ.setCityname(strDtename);
		objDACQ.setCountryhubcode(strCountrycode);
		List objList = objDACQ.getResults();
		if (objList == null || objList.size() < 1)
			return "";
		return ((DistrictandcountryColumns) objList.get(0)).getDtdt_code();
	}

	public static AlldistictColumns loadAlldistict(
			AlldistictCondition objAlldistictCondition) throws Exception {
		AlldistictQuery objAlldistictQuery = new AlldistictQuery();
		objAlldistictQuery.setUseCachesign(true);
		objAlldistictQuery.setCondition(objAlldistictCondition);
		List objList = objAlldistictQuery.getResults();

		if (objList == null || objList.size() < 1)
			return null;
		return (AlldistictColumns) objList.get(0);
	}

	public static String getDtcodeByHubcode(String strDthubcode)
			throws Exception {
		CountryQuery objCountryQuery = new CountryQuery();
		objCountryQuery.setUseCachesign(true);
		objCountryQuery.setDthubcode(strDthubcode);
		List objList = objCountryQuery.getResults();

		if (objList == null || objList.size() < 1)
			return "";
		return ((CountryColumns) objList.get(0)).getDtdt_code();
	}

	public static String getNotCountryDtcodeByHubcode(String strDthubcode)
			throws Exception {
		DistrictQueryEX objCountryQuery = new DistrictQueryEX();
		objCountryQuery.setUseCachesign(true);
		objCountryQuery.setDthubcode(strDthubcode);
		List objList = objCountryQuery.getResults();

		if (objList == null || objList.size() < 1)
			return "";
		return ((CountryColumns) objList.get(0)).getDtdt_code();

	}

	public static String getDtenameByHubcode(String strDthubcode)
			throws Exception {
		CountryQuery objCountryQuery = new CountryQuery();
		objCountryQuery.setUseCachesign(true);
		objCountryQuery.setDthubcode(strDthubcode);
		List objList = objCountryQuery.getResults();

		if (objList == null || objList.size() < 1)
			return "";
		return ((CountryColumns) objList.get(0)).getDtdt_ename();

	}

	public static List queryCountry(CountryCondition objCountryCondition)
			throws Exception {
		CountryQuery objCountryQuery = new CountryQuery();
		objCountryQuery.setCondition(objCountryCondition);
		return objCountryQuery.getResults();
	}

	public static String getDtenameByDtcode(String strDtcode) throws Exception {
		TdiDistrict objTdiDistrict = TdiDistrictDC.loadByKey(strDtcode);
		return objTdiDistrict.getDtEname();
	}

	public static String getDtnameByDtcode(String strDtcode) throws Exception {
		if (StringUtility.isNull(strDtcode))
			return "";
		TdiDistrict objTdiDistrict = TdiDistrictDC.loadByKey(strDtcode);
		if (objTdiDistrict == null)
			return "";
		return objTdiDistrict.getDtName();
	}

	public static String getDthubcodeByDtcode(String strDtcode)
			throws Exception {
		TdiDistrict objTdiDistrict = TdiDistrictDC.loadByKey(strDtcode);
		return objTdiDistrict.getDtHubcode();
	}

	public static String getCountryHubcodeByCity(String strDtcode)
			throws Exception {
		TdiDistrict objTdiDistrict = TdiDistrictDC.loadByKey(strDtcode);
		return objTdiDistrict.getTdiDistrict().getDtHubcode();
	}

	public static String getCountrycodeByCity(String strDtcode)
			throws Exception {
		TdiDistrict objTdiDistrict = TdiDistrictDC.loadByKey(strDtcode);
		if (objTdiDistrict == null)
			return null;
		return objTdiDistrict.getTdiDistrict().getDtCode();
	}

	public static String getCountryEnameByCity(String strDtcode)
			throws Exception {
		TdiDistrict objTdiDistrict = TdiDistrictDC.loadByKey(strDtcode);
		return objTdiDistrict.getTdiDistrict().getDtEname();
	}

	public static String getCountryNameByCity(String strDtcode)
			throws Exception {
		TdiDistrict objTdiDistrict = TdiDistrictDC.loadByKey(strDtcode);
		return objTdiDistrict.getTdiDistrict().getDtName();
	}

	public static String getDHLLocationcode(String strCityname,
			String strHubcode, String strNaHubcode, String strPostcode)
			throws Exception {
		if (strNaHubcode.equals("GB") && !StringUtility.isNull(strPostcode)) {
			if (strPostcode.indexOf(" ") > 0)
				strPostcode = strPostcode
						.substring(0, strPostcode.indexOf(" "));
		}

		DhldistrictlocationQuery objDLQ = new DhldistrictlocationQuery();
		objDLQ.setDdcityname(strCityname);
		objDLQ.setDdhubcode(strHubcode);
		objDLQ.setDdnationcode(strNaHubcode);
		if (!StringUtility.isNull(strPostcode)) {
			objDLQ.setStartpostcode(strPostcode);
			objDLQ.setEndpostcode(strPostcode);
		}
		objDLQ.setUseCachesign(true);
		List listResult = objDLQ.getResults();
		if (listResult == null || listResult.size() < 1)
			return "";
		return ((DhldistrictlocationColumns) listResult.get(0))
				.getDhldd_locationcode();
	}

	public static String getDHLStateCode(String strCityname, String strHubcode,
			String strNaHubcode, String strPostcode) throws Exception {
		AlldhldistrictColumns objADDColumns = getAlldhldistrict(strCityname,
				strHubcode, strNaHubcode, strPostcode);
		if (objADDColumns == null)
			return "";
		return objADDColumns.getDhldd_statecode();
	}

	public static String getDHLLocationCode(String strCityname,
			String strHubcode, String strNaHubcode, String strPostcode)
			throws Exception {
		AlldhldistrictColumns objADDColumns = getAlldhldistrict(strCityname,
				strHubcode, strNaHubcode, strPostcode);
		if (objADDColumns == null)
			return "";
		return objADDColumns.getDhldd_locationcode();
	}

	public static AlldhldistrictColumns getAlldhldistrict(String strCityname,
			String strHubcode, String strNaHubcode, String strPostcode)
			throws Exception {
		if (strNaHubcode.equals("GB") && !StringUtility.isNull(strPostcode)) {

			if (strPostcode.indexOf("-") > 0)
				strPostcode = strPostcode.replace("-", "");

			if (strPostcode.indexOf(" ") > 0)
				strPostcode = strPostcode
						.substring(0, strPostcode.indexOf(" "));
		}
		if (strNaHubcode.equals("CA") && !StringUtility.isNull(strPostcode)
				&& strPostcode.length() > 5) {
			strPostcode = strPostcode.substring(0, 5);
		}
		AlldhldistrictQuery objAlldhldistrictQuery = new AlldhldistrictQuery();
		objAlldhldistrictQuery.setDdcityname(strCityname);
		objAlldhldistrictQuery.setDdhubcode(strHubcode);
		objAlldhldistrictQuery.setDdnationcode(strNaHubcode);
		if (!StringUtility.isNull(strPostcode)) {
			objAlldhldistrictQuery.setStartpostcode(strPostcode);
			objAlldhldistrictQuery.setEndpostcode(strPostcode);
		}
		objAlldhldistrictQuery.setUseCachesign(true);
		List listResult = objAlldhldistrictQuery.getResults();
		if (listResult == null || listResult.size() < 1)
			return null;
		return (AlldhldistrictColumns) listResult.get(0);
	}

	public static String getTarazzStateCode(String strCityname,
			String strPostcode) throws Exception {
		if (StringUtility.isNull(strCityname)
				|| StringUtility.isNull(strPostcode))
			return "";
		TarazzdistrictQuery objTQuery = new TarazzdistrictQuery();
		objTQuery.setTdpostcode(strPostcode);
		objTQuery.setLocalityname(strCityname);
		List listResult = objTQuery.getResults();

		if (listResult == null || listResult.size() < 1)
			return "";
		return ((TarazzdistrictColumns) listResult.get(0)).getTdtd_statecode();
	}

	public static List queryCountry(String strDkcode) throws Exception {
		CountryQuery objCountryQuery = new CountryQuery();
		objCountryQuery.setDkcode(strDkcode);
		return objCountryQuery.getResults();
	}

	public static List queryDHLDistrict(DhldistrictCondition objDDCondition)
			throws Exception {
		DhldistrictQuery objDhldistrictQuery = new DhldistrictQuery();
		objDhldistrictQuery.setCondition(objDDCondition);
		return objDhldistrictQuery.getResults();
	}

	public static String getDHLHubcode(String strNationcode, String strPostcode)
			throws Exception {
		if (strNationcode.equals("GB") && !StringUtility.isNull(strPostcode)) {
			if (strPostcode.indexOf("-") > 0)
				strPostcode = strPostcode.replace("-", "");

			if (strPostcode.indexOf(" ") > 0)
				strPostcode = strPostcode
						.substring(0, strPostcode.indexOf(" "));
		}
		if (strNationcode.equals("CA") && !StringUtility.isNull(strPostcode)
				&& strPostcode.length() > 5) {
			strPostcode = strPostcode.substring(0, 5);
		}
		DhldistrictQuery objDhldistrictQuery = new DhldistrictQuery();
		DhldistrictCondition objDDCondition = new DhldistrictCondition();
		objDDCondition.setDdnationcode(strNationcode);
		objDDCondition.setStartpostcode(strPostcode);
		objDDCondition.setEndpostcode(strPostcode);//dhldistrictColumns替换了,所以
		objDhldistrictQuery.setCondition(objDDCondition);
		List listResults = objDhldistrictQuery.getResults();
		if (listResults == null || listResults.size() < 1)
			return strNationcode;
		return ((DhldistrictColumns) listResults.get(0)).getDhldd_hubcode();
	}

	public static List queryDHLDistrict(DhldistrictCondition objDDCondition,
			String ddCityName, String ddHubCode) throws Exception {
		DhldistrictQuery objDhldistrictQuery = new DhldistrictQueryEX(
				ddCityName, ddHubCode);
		objDhldistrictQuery.setCondition(objDDCondition);
		return objDhldistrictQuery.getResults();
	}

	/**
	 * 
	 * @param objDDCondition
	 * @param ddCityName
	 *            英文名
	 * @param ddHubCode
	 *            机场代码
	 * @param ddCityCName
	 *            中文名
	 * @return
	 * @throws Exception
	 */

	public static List queryDHLDistrict(DhldistrictCondition objDDCondition,
			String ddCityName, String ddHubCode, String ddCityCName)
			throws Exception {
		DhldistrictQuery objDhldistrictQuery = new DhldistrictQueryEX(
				ddCityName, ddHubCode, ddCityCName);
		objDhldistrictQuery.setCondition(objDDCondition);
		return objDhldistrictQuery.getResults();
	}

	public static List<?> queryXinshDistrict() throws Exception {
		XinshdistrictQuery query = new XinshdistrictQuery();
		return query.getResults();
	}

	// 条件查询城市
	public static List queryCity(CityCondition objTdicityCondition)
			throws Exception {
		CityQuery objQuery = new CityQuery();
		objQuery.setCondition(objTdicityCondition);
		return objQuery.getResults();
	}

	// id查询城市
	public static CityColumns queryCity(String ctCode) throws Exception {
		CityCondition objTdicityCondition = new CityCondition();
		objTdicityCondition.setCtcode(ctCode);
		List objlist = queryCity(objTdicityCondition);
		if (!CollectionUtility.isNull(objlist) && objlist.size() == 1)
			return (CityColumns) objlist.get(0);
		return null;
	};

	// 设置城市条件参数
	public static void setCityColumns(TdiCity objTdicity,
			CityColumns objTdicityColnums, Session objSession) throws Exception {
		objTdicity.setCtCode(objTdicityColnums.getCictcode());
		objTdicity.setCtEname(objTdicityColnums.getCictename());
		if (!StringUtility.isNull(objTdicityColnums.getCictname()))
			objTdicity.setCtName(objTdicityColnums.getCictname());
		if (!StringUtility.isNull(objTdicityColnums.getCictsname()))
			objTdicity.setCtSname(objTdicityColnums.getCictsname());
		if (!StringUtility.isNull(objTdicityColnums.getCictstartpostcode()))
			objTdicity.setCtStartpostcode(objTdicityColnums
					.getCictstartpostcode());
		if (!StringUtility.isNull(objTdicityColnums.getCictendpostcode()))
			objTdicity
					.setCtEndpostcode(objTdicityColnums.getCictendpostcode());
		// 剩两个属性
		if (!StringUtility.isNull(objTdicityColnums.getStstcode()))
			objTdicity.setTdiState(TdiStateDC.loadByKey(objTdicityColnums
					.getStstcode()));//TdiStateDCload的时候是空
		if (!StringUtility.isNull(objTdicityColnums.getDidtcode()))
			objTdicity.setTdiDistrict(TdiDistrictDC.loadByKey(objTdicityColnums
					.getDidtcode()));
	}

	// 条件查询区域
	public static List<RegionColumns> queryRegion(
			RegionCondition objRegionCondition) throws Exception {
		RegionQuery query = new RegionQuery();
		query.setCondition(objRegionCondition);
		return query.getResults();
	}

	// 根据rg_code查询区域
	public static RegionColumns queryRegion(String rgcode) throws Exception {
		RegionCondition objRegionCondition = new RegionCondition();
		objRegionCondition.setRgcode(rgcode);
		List list = queryRegion(objRegionCondition);
		if (!CollectionUtility.isNull(list) && list.size() == 1)
			return (RegionColumns) list.get(0);
		return null;
	}

	// 设置区域属性
	public static void setRegionColumns(TdiRegion objTdiRegion,
			RegionColumns objTdiregionColumns, Session objSession)
			throws Exception {
		if (!StringUtility.isNull(objTdiregionColumns.getTdrrgcode()))
			objTdiRegion.setRgCode(objTdiregionColumns.getTdrrgcode());
		if (!StringUtility.isNull(objTdiregionColumns.getTdrrgename()))
			objTdiRegion.setRgEname(objTdiregionColumns.getTdrrgename());
		if (!StringUtility.isNull(objTdiregionColumns.getTdrrgname()))
			objTdiRegion.setRgName(objTdiregionColumns.getTdrrgname());
		if (!StringUtility.isNull(objTdiregionColumns.getTdrcctcode()))
			objTdiRegion.setTdiCity(TdiCityDC.loadByKey(objTdiregionColumns
					.getTdrcctcode()));
	}

	// 查询省份数据
	public static List queryState(StateCondition objTdistateCondition)
			throws Exception {
		StateQuery tquery = new StateQuery();
		tquery.setCondition(objTdistateCondition);
		return tquery.getResults();
	}

	/*
	 * 通过ID查询省信息
	 */
	public static StateColumns queryState(String stCode) throws Exception {
		StateCondition objStateCondition = new StateCondition();
		objStateCondition.setStcode(stCode);
		List objlist = queryState(objStateCondition);
		if (!CollectionUtility.isNull(objlist) && objlist.size() == 1)
			return (StateColumns) objlist.get(0);
		return null;
	}

	// 设置参数
	public static void setStateColumns(TdiState objTdiState,
			StateColumns objTdistateColumns, Session objSession)
			throws Exception {
		if (!StringUtility.isNull(objTdistateColumns.getStstcode()))
			objTdiState.setStCode(objTdistateColumns.getStstcode());
		if (!StringUtility.isNull(objTdistateColumns.getStstename()))
			objTdiState.setStEname(objTdistateColumns.getStstename());
		if (!StringUtility.isNull(objTdistateColumns.getStstname()))
			objTdiState.setStName(objTdistateColumns.getStstname());
		if (!StringUtility.isNull(objTdistateColumns.getStstsname()))
			objTdiState.setStSname(objTdistateColumns.getStstsname());
		if (!StringUtility.isNull(objTdistateColumns.getDidtcode()))
			;
		objTdiState.setTdiDistrict(TdiDistrictDC.loadByKey(objTdistateColumns
				.getDidtcode()));
	}

	/**
	 * 20160809 Tue 
	 * by wukaiquan 
	 * option: 属性获取,添加时的数据转换
	 * @throws Exception 
	 * **/
	public static void setAddDistrict(TdiDistrict objDistrict,DicdistrictColumns objColumns,Session objSession,String conStr) throws Exception {
		
		
		objDistrict.setDtCode(objColumns.getDidtcode());//城市
		//外键,所属国
		objDistrict.setTdiDistrict((TdiDistrict)objSession.load(TdiDistrict.class, objColumns.getDddtcode()));
		//外键,地区种类
		System.out.println( objColumns.getDkdkcode());
		objDistrict.setTdiDistrictkind((TdiDistrictkind)objSession.load(TdiDistrictkind.class, objColumns.getDkdkcode()));		
		objDistrict.setDtHubcode(objColumns.getDidthubcode());
		objDistrict.setDtName(objColumns.getDidtname());//地区名称
		objDistrict.setDtEname(objColumns.getDidtename());//地区英文名
		objDistrict.setDtStatecode(objColumns.getDidtstatecode());//州，省的简称
		objDistrict.setDtStatename(objColumns.getDidtstatename());//州，省的名称
		objDistrict.setDtGrade(objColumns.getDidtgrade());//地区级别
		objDistrict.setDtStartpostcode(objColumns.getDidtstartpostcode());//起始邮编
		objDistrict.setDtEndpostcode(objColumns.getDidtendpostcode());//终止邮编
		objDistrict.setDtOpCodeModifier(objColumns.getDidtopcodemodifier());//修改人
		
		if(conStr.equals("SAVE")){
			//设置创建时间和修改时间,创建人和修改人是一个人
				objDistrict.setDtOpCodeCreator(objColumns.getDidtopcodecreator());//创建人
			 Date currentTime = new Date();
			 objDistrict.setDtCreatedate(currentTime);
			 objDistrict.setDtModifydate(currentTime);
			
		}/*else if(conStr.equals("UPDATE")){
			//设置修改时间
			Date date = new Date();
			objDistrict.setDtModifydate(date);
			
			DistrictColumns getDate =  DistrictDemand.querById(objColumns.getDidtcode());
			objDistrict.setDtOpCodeCreator(getDate.getDidtopcodecreator());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//不能有空格
			String strdate = getDate.getDidtcreatedate();
			Date createDate = sdf.parse(strdate);
			objDistrict.setDtCreatedate(createDate);
		}*/
		//将长时间格式转为短时间格式 
		
		objDistrict.setDtRemark(objColumns.getDidtremark());//备注
		objDistrict.setDtStartcitysign(objColumns.getDidtstartcitysign());
		objDistrict.setDtElevatedrisksign(objColumns.getDidtelevatedrisksign());
		objDistrict.setDtRestrictedsign(objColumns.getDidtrestrictedsign());
	}
	
	
	
	
	
	/**
	 * 20160809 Tue 
	 * by wukaiquan
	 * option: QueryBYID
	 * @throws Exception 
	 * **/
	public static DicdistrictColumns querById(String didtcode) throws Exception {
		List list;
		DicdistrictCondition objCondition = new DicdistrictCondition();
		objCondition.setDtcode(didtcode);
		DicdistrictQuery objQuery = new DicdistrictQuery();
		objQuery.setCondition(objCondition);
		
		list = objQuery.getResults();
		if(!CollectionUtility.isNull(list) && list.size() ==1){
			return (DicdistrictColumns) list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 20160809 Tue
	 * by wukaiquan
	 * option:query
	 * @throws Exception 
	 * **/
	public static List query(DicdistrictCondition condtion) throws Exception{
		String dtcode = "";
		DicdistrictQuery objQuery = new DicdistrictQuery();
		objQuery.setCondition(condtion);
		List listResults = objQuery.getResults();//getResults父类hibernate的查询方法
		
		
		
		if(listResults.size() == 0 ||listResults == null ){
			return null;
		}else{
			return listResults;
		}
	}
	/**20160809
	 * by wukaiquan
	 * option:jdbcQuery
	 * 
	 * **/
	public static List<DistrictjdbcColumns> jdbcQuery(DistrictjdbcCondition condtion) throws Exception {
		
		DistrictjdbcQuery objQuery = new DistrictjdbcQuery();
		objQuery.setCondition(condtion);
		List listResults = objQuery.getResults();//getResults父类hibernate的查询方法
			
		if(listResults.size() == 0 ||listResults == null ){
			return null;
		}else{
			return listResults;
		}
	}

	/**20160809
	 * by wukaiquan
	 * option:对象转化
	 * 
	 * **/
	public static void setDhlDistrictColumns(TdiDhldistrict objDhldistrict,
			DicdhldistrictColumns columns, Session objSession) {
		objDhldistrict.setDdNationcode(columns.getDiddnationcode());//国家编码
		objDhldistrict.setDdNationname(columns.getDiddnationname());//国家名称
		objDhldistrict.setDdStatecode(columns.getDiddstatecode());//州代码
		objDhldistrict.setDdStatename(columns.getDiddstatename());//州名称
		objDhldistrict.setDdCityname(columns.getDiddcityname());//州名称
		objDhldistrict.setDdHubcode(columns.getDiddhubcode());//机场代码
		objDhldistrict.setDdCityname(columns.getDiddcityname());//城市名称
		objDhldistrict.setDdStartpostcode(columns.getDiddstartpostcode());//开始邮编
		objDhldistrict.setDdEndtpostcode(columns.getDiddendtpostcode());//结束邮编
		objDhldistrict.setDdLocationcode(columns.getDiddlocationcode());//操作点名称	
	}
	
	public static void setDhlremotedistrictColumns(TdiDhlremotedistrict objDhlremotedistrict,
			DhlremotedistrictColumns columns, Session objSession) throws Exception{
		if(!StringUtility.isNull(columns.getDrddrd_cityname()))
		objDhlremotedistrict.setDrdCityname(columns.getDrddrd_cityname());
		if(!StringUtility.isNull(columns.getDrddrd_nationname())){	
		    objDhlremotedistrict.setDrdNationcode(getHubCodeByNationName(columns.getDrddrd_nationname()));
		}
		if(!StringUtility.isNull(columns.getDrddrd_nationname()))
		objDhlremotedistrict.setDrdNationname(columns.getDrddrd_nationname());
		if(!StringUtility.isNull(columns.getDrddrd_postcode()))
		objDhlremotedistrict.setDrdPostcode(columns.getDrddrd_postcode());
		if(!StringUtility.isNull(columns.getDrddrd_statename()))
		objDhlremotedistrict.setDrdStatename(columns.getDrddrd_statename());
	}
	public static String getHubCodeByNationName(String nationName) throws Exception{
		DicdistrictQuery query = new DicdistrictQuery();
		DicdistrictCondition con = new DicdistrictCondition();	
		if(nationName.matches("^[a-zA-Z]*")){
			con.setDtename(nationName);
		}
		else{
			con.setDtname(nationName);
		}
		query.setCondition(con);
		List<DicdistrictColumns> list = query.getResults();
		if(list.size()>0)
		return list.get(0).getDddthubcode();
		return "";
	} 
	
}
