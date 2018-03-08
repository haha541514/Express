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
		//�������		
		//String saveStr = "~`����~`��³~`�籾~`~`~`C~`9898~`9898~`0~`~`0~`~`��DHL�������е���~`N~`N~`N~`~`~`~`~`~`~`EMS~`~`~@~#";	
		//�������
		String saveStr = "3785~`���1~`Ŧ˹1~`��¼1~`~`~`C~`9898~`9898~`0~`~`0~`~`��DHL�������е���~`N~`N~`N~`1~`~`~`~`~`~`EMS~`~`~@~#";	

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
		//String queryStr="~`PK~`����~`~`~`~@~#";
		//��ѯ�õ�����Queery,��ѯ������Query����ģ�λ�ð���Query�������
		//String saveStr = "~`����~`��³~`�籾~`~`~`C~`9898~`9898~`0~`~`0~`~`��DHL�������е���~`N~`N~`N~`~`~`~`~`~`~`EMS~`~`~@~#";	

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
	 * ��������
	 * by wukaiquan 
	 * ���ҡ��������ơ������ơ���������
	 * @throws Exception 
	 * **/
	/*public void saveCitys() throws Exception{
		//���ҡ��������ơ������ơ��������ִ���
		m_service = new DistrictService();
		CityExportColumns[] columns = new CityExportColumns[3];
		Encoder objEncoder = new Encoder();
		String saveStr;
		for(int i=0; i<3 ;i++){
			columns[i] = new CityExportColumns();
			//columns[i].setTdcctcode(null);//����toString,תΪ����,st_codeΪ�գ�
			columns[i].setTdcHubcode("CH");
			columns[i].setTdcStateEname("�Ĵ�");
			columns[i].setTdcctname("TEST");
			columns[i].setTdcctename("TEST");
			columns[i].setTdcctstartpostcode("999901");
			columns[i].setTdcctendpostcode("999901");
			objEncoder.addParameter(columns[i]);		//�����ۼӵ�
		}
		saveStr = objEncoder.toString();
		System.out.println(saveStr);
	
		Decoder objPD = new Decoder(saveStr);			//Decoder��������
		m_service.saveCitys(objPD);
	}
*/	
	/**
	 * 20160815
	 * ��������
	 * by wukaiquan ,��������
	 * ���ҡ��������ơ������ơ���������
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
			columns[i].setTdcStateEname("�Ĵ�");
			columns[i].setTdcctname("TEST");
			columns[i].setTdcctename("TEST");
			columns[i].setTdcctstartpostcode("999901");
			columns[i].setTdcctendpostcode("999901");
			objEncoder.addParameter(columns[i]);		//�����ۼӵ�
			//objEncoder.addParameter(objList); ����list����
		}
		saveStr = objEncoder.toString();
		System.out.println(saveStr);
		Decoder objPD = new Decoder(saveStr);			//Decoder��������
		m_service.saveCitys(objPD);
	}

	/**
	 * 20160815
	 * ��������
	 * by wukaiquan ,list ����
	 * ���ҡ��������ơ������ơ���������
	 * @throws Exception 
	 * **/
	public void saveCitys2() throws Exception{
		
		m_service = new DistrictService();
		String saveStr;
		saveStr = "CN~`��Į~`HH~`AA~`CC~`12321~`21212~`~@" +
				"CN~`����ɭ��~`AS~`HH~`AF~`12321~`21212~`~@" +
				"CN~`���˱�~`AC~`BB~`CF~`12321~`21212~`~@" +
				"CN~`����~`HH~`ZZ~`CD~`12321~`21212~`~@~#";
		Decoder objPD = new Decoder(saveStr);			
		m_service.saveCitys(objPD);
	}
	

	/**
	 *��ѯ���� 
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
		String saveStr = "2633~`Į��~`��Į~`��Į~`12321~`21212~`1~`~`~`~`103~`~`~`~@~#";
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
	 *��ѯʡ 
	 * 
	 * **/
	public void queryState() throws Exception{
		m_service = new DistrictService();
		//String saveStr = "105~`ɽ��~`ɽ��~`ɽ��~`1~`~`~`~@~#";
		String saveStr = "~`~`~`~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(saveStr);
		String objReturn = m_service.queryState(objPD);
		System.out.println(objReturn);
	}
	
	public void saveState() throws Exception{
		m_service = new DistrictService();
		String saveStr = "~`����~`����~`����~`1~`~`~`~`~@~#";
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
	 * ��������
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
