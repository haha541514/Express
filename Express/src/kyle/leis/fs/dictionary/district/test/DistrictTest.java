package kyle.leis.fs.dictionary.district.test;

import net.sf.hibernate.Session;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.dbaccess.query.GeneralColumns;
import kyle.leis.fs.cachecontainer.da.CityQuery;
import kyle.leis.fs.dictionary.district.da.CityColumns;
import kyle.leis.fs.dictionary.district.da.CityExportColumns;
import kyle.leis.fs.dictionary.district.dax.CitySeq;
import kyle.leis.fs.dictionary.district.sv.DistrictService;
import kyle.leis.fs.dictionary.district.tp.CityseqQuery;
import kyle.leis.hi.TdiDistrict;

public class DistrictTest {
	private DistrictService m_service;
	public static void main(String[] args) throws Exception {
		DistrictTest test = new DistrictTest();
		//test.query();   
		//test.save();		
		//test.delete();   
		//test.saveCitys();
		//test.saveCitys2();
		
		//test.queryCity();
		//test.saveCity();
		//test.deleteCity();
		
		//test.queryState();
		//test.saveState();
		//test.deleteState();
		//test.saveDhlDistricts();
		
	}

	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:save
	 * @throws Exception 
	 * **/
	public void save() throws Exception{
		m_service = new DistrictService();
		//插入城市		
		//String saveStr = "~`哈迪~`达鲁~`哥本~`~`~`C~`9898~`9898~`0~`~`0~`~`从DHL地区表中导入~`N~`N~`N~`~`~`~`~`~`~`EMS~`~`~@~#";	
		//插入国家
		String saveStr = "3785~`麦克1~`纽斯1~`迪录1~`~`~`C~`9898~`9898~`0~`~`0~`~`从DHL地区表中导入~`N~`N~`N~`1~`~`~`~`~`~`EMS~`~`~@~#";	

		Decoder objPD = new Decoder(saveStr);
		String objReturn = m_service.save(objPD);
		System.out.println(objReturn);
		
	}
	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * query
	 * @throws Exception 
	 * **/
	public void query() throws Exception{
		m_service = new DistrictService();
		//String queryStr="~`~`~`~`~`~@~#";
		//String queryStr="~`PK~`京北~`~`~`~@~#";
		//查询用的类似Queery,查询条件是Query里面的，位置按照Query里面的来
		//String saveStr = "~`哈迪~`达鲁~`哥本~`~`~`C~`9898~`9898~`0~`~`0~`~`从DHL地区表中导入~`N~`N~`N~`~`~`~`~`~`~`EMS~`~`~@~#";	

		String queryStr = "~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`EMS~`~`~@~#";	
		Decoder objPD = new Decoder(queryStr);
		String objReturn = 	m_service.query(objPD);
		System.out.println(objReturn);
	}
	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:delete
	 * @throws Exception 
	 * **/
	public void delete() throws Exception{
		m_service = new DistrictService();
		String deleteId = "3751~`~`~`~@~#";
		Decoder objPD = new Decoder(deleteId);
		String objReturn = m_service.delete(objPD);
		System.out.println(objReturn);
	}
	/**
	 * 20160815
	 * 批量导入
	 * by wukaiquan 
	 * 国家、城市名称、州名称、城市三字
	 * @throws Exception 
	 * **/
	/*public void saveCitys() throws Exception{
		//国家、城市名称、州名称、城市三字代码
		m_service = new DistrictService();
		CityExportColumns[] columns = new CityExportColumns[3];
		Encoder objEncoder = new Encoder();
		String saveStr;
		for(int i=0; i<3 ;i++){
			columns[i] = new CityExportColumns();
			//columns[i].setTdcctcode(null);//经过toString,转为空了,st_code为空，
			columns[i].setTdcHubcode("CH");
			columns[i].setTdcStateEname("四川");
			columns[i].setTdcctname("TEST");
			columns[i].setTdcctename("TEST");
			columns[i].setTdcctstartpostcode("999901");
			columns[i].setTdcctendpostcode("999901");
			objEncoder.addParameter(columns[i]);		//可以累加的
		}
		saveStr = objEncoder.toString();
		System.out.println(saveStr);
	
		Decoder objPD = new Decoder(saveStr);			//Decoder参数解码
		m_service.saveCitys(objPD);
	}
*/	
	/**
	 * 20160815
	 * 批量导入
	 * by wukaiquan ,对象数组
	 * 国家、城市名称、州名称、城市三字
	 * @throws Exception 
	 * **/
	public void saveCitys() throws Exception{
		
		m_service = new DistrictService();
		CityExportColumns[] columns = new CityExportColumns[3];
		Encoder objEncoder = new Encoder();
		String saveStr;
		for(int i=0; i<3 ;i++){
			columns[i] = new CityExportColumns();
			columns[i].setTdcHubcode("CH");
			columns[i].setTdcStateEname("四川");
			columns[i].setTdcctname("TEST");
			columns[i].setTdcctename("TEST");
			columns[i].setTdcctstartpostcode("999901");
			columns[i].setTdcctendpostcode("999901");
			objEncoder.addParameter(columns[i]);		//可以累加的
			//objEncoder.addParameter(objList); 接收list对象
		}
		saveStr = objEncoder.toString();
		System.out.println(saveStr);
		Decoder objPD = new Decoder(saveStr);			//Decoder参数解码
		m_service.saveCitys(objPD);
	}

	/**
	 * 20160815
	 * 批量导入
	 * by wukaiquan ,list 对象
	 * 国家、城市名称、州名称、城市三字
	 * @throws Exception 
	 * **/
	public void saveCitys2() throws Exception{
		
		m_service = new DistrictService();
		String saveStr;
		saveStr = "CN~`大漠~`HH~`AA~`CC~`12321~`21212~`~@" +
				"CN~`迷雾森林~`AS~`HH~`AF~`12321~`21212~`~@" +
				"CN~`洪浪北~`AC~`BB~`CF~`12321~`21212~`~@" +
				"CN~`安徽~`HH~`ZZ~`CD~`12321~`21212~`~@~#";
		Decoder objPD = new Decoder(saveStr);			
		m_service.saveCitys(objPD);
	}
	

	/**
	 *查询城市 
	 * 
	 * **/
	public void queryCity() throws Exception{
		m_service = new DistrictService();
		String saveStr = "2597~`HH~`AA~`CC~`~@~#";
		Decoder objPD = new Decoder(saveStr);
		String objReturn = m_service.queryCity(objPD);
		System.out.println(objReturn);
	}
	
	public void saveCity() throws Exception{
		m_service = new DistrictService();
		String saveStr = "2633~`漠北~`大漠~`大漠~`12321~`21212~`1~`~`~`~`103~`~`~`~@~#";
		Decoder objPD = new Decoder(saveStr);
		String objReturn = m_service.saveCity(objPD);
		System.out.println(objReturn);
	}
	
	public void deleteCity() throws Exception{
		m_service = new DistrictService();
		String saveStr = "118~`~@~#";
		//String saveStr = "2477~`~@2595~`~@2538~`~@~#";
		
		Decoder objPD = new Decoder(saveStr);
		String objReturn = m_service.deleteCity(objPD);
		System.out.println(objReturn);
	}
	

	/**
	 *查询省 
	 * 
	 * **/
	public void queryState() throws Exception{
		m_service = new DistrictService();
		//String saveStr = "105~`山阳~`山阳~`山阳~`1~`~`~`~@~#";
		String saveStr = "~`~`~`~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(saveStr);
		String objReturn = m_service.queryState(objPD);
		System.out.println(objReturn);
	}
	
	public void saveState() throws Exception{
		m_service = new DistrictService();
		String saveStr = "~`上天~`天阴~`天阴~`1~`~`~`~`~@~#";
		Decoder objPD = new Decoder(saveStr);
		String objReturn = m_service.saveState(objPD);
		System.out.println(objReturn);
	}
	public void deleteState() throws Exception{
		m_service = new DistrictService();
		String saveStr = "118~`~@~#";
		Decoder objPD = new Decoder(saveStr);
		String objReturn = m_service.deleteState(objPD);
		System.out.println(objReturn);
	}
	
	/**
	 * 20160815
	 * 批量导入
	 * by wukaiquan 
	 * @throws Exception 
	 * **/
	public void saveDhlDistricts() throws Exception{
		
		m_service = new DistrictService();
		String saveStr;
		saveStr = "~`AT~`AUSTRIA~`~`~`HERMAGOR~`VIE~`5210~`1205~`VIE~`~`~@" +
		 "~`AT~`AUSTRIA~`~`~`HERMAGOR~`VIE~`5210~`1205~`VIE~`~`~@" +
		 "~`AT~`AUSTRIA~`~`~`HERMAGOR~`VIE~`5210~`1205~`VIE~`~`~@" +
		 "~`AT~`AUSTRIA~`~`~`HERMAGOR~`VIE~`5210~`1205~`VIE~`~`~@~#";
		Decoder objPD = new Decoder(saveStr);			
		String objReturn = m_service.saveDhlDistricts(objPD);
		System.out.println(objReturn);
	}
	
	
}
