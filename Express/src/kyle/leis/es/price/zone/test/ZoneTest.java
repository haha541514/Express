package kyle.leis.es.price.zone.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.price.zone.sv.ZoneService;

public class ZoneTest {
	private static ZoneService m_objZoneService = new ZoneService();
	
	public static void main(String[] args) {
		try {
			System.out.println(add());
			// System.out.println(query());
			// System.out.println(load());
			// splitTest();
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String add() throws Exception {
		String str = "1122~`DHL特惠分区~`DHL test~`~`2011-03-31 14:03:36~`DHL特惠分区~`DHL特惠分区~`A0415~`~`~`ON~`~`1~`~`1~`~`~@~#1~`01~`01~`01~`1122~`DHL特惠分区~`~@2~`02~`02~`02~`1122~`DHL特惠分区~`~@~#1~`01~`01~`124~`JP~`日本~`JAPAN~`~@1~`01~`01~`189~`IN~`印度~`INDIA~`~@1~`01~`01~`190~`IR~`伊朗~`IRAN (ISLAMIC REPUBLIC OF)~`~@2~`02~`02~`134~`MO~`澳门~`MACAU~`~@2~`02~`02~`194~`MP~`塞班~`SAIPAN~`~@2~`02~`02~`69~`ML~`马里~`MALI~`~@~#001~`002~`1~`124~`1~`01~`01~`~@~#1~`~@~#";
		Decoder objPD = new Decoder(str);
		return m_objZoneService.save(objPD);
	}
	
	public static String query() throws Exception {
		String str = "~`~`~`~`ON~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return m_objZoneService.query(objPD);
	}
	
	public static String load() throws Exception {
		String str = "43~`~@~#";
		Decoder objPD = new Decoder(str);
		return m_objZoneService.load(objPD);		
	}
	
	public static void splitTest() throws Exception {
		String str = "~`~`~`~`ON~`~`~`~`~`";
		String[] astr = str.split("~`", -1);
		System.out.println(astr.length);
	}
	
}
