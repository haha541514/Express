package kyle.leis.eo.operation.housewaybill.test;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.connectors.util.Constants;
import kyle.common.connectors.util.Decoder;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.bl.Housewaybill;
import kyle.leis.eo.operation.housewaybill.bl.Input;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.ForinputallQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforcustomsCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.sv.HousewaybillDelegate;
import kyle.leis.eo.operation.housewaybill.sv.HousewaybillService;

public class HouseWayBillInputTest {
	public static void main(String[] args) {
		try {		
			// System.out.println(query());
			// split();
			modify();
			// inputAllForService();
			// queryForcustoms();
			// inputAll();
			// queryAll();
			// testServerCW();
			// queryInput();
			// getChargeweight();
			// signIn();
			// modifyCorewaybill();
			// batchSignInsignOut();
			//System.out.println(eliminate());
			// queryForPredict();
			// System.out.println(getLabelConsignments());
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String split() {
		String str = "111,22";
		int iIndex = str.indexOf(",");
		System.out.println(str.substring(0, iIndex));
		System.out.println(str.substring(iIndex + 1));
		return "";
	}
	
	public static String eliminate() throws Exception {
		//Housewaybill objHousewaybill = new Housewaybill();
		// String str = "...1234567890QAwertyuiop[]asdfghjkl;'zxcvbnm,/?~!@#$%^&*()_+";
		// objHousewaybill.eliminate(str, "0");
		return DateFormatUtility.getStandardDate(DateFormatUtility.getStandardDate("1900-01-01 00:00:00"));
		// return str.replaceAll("[.]", "");
	}	
	
	public static String query() throws Exception {
		//ForinputallCondition objFInputAllC = new ForinputallCondition();
		//objFInputAllC.setCustomerewbcode("1733989935");
		//InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
		
		// IService ojbService = (IService)ObjectGenerator.getObject("kyle.leis.eo.operation.housewaybill.sv.HousewaybillService");
		
		String str = "~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`2011-12-03 00:00:00~`2011-12-06 23:59:59~`~`~`~`~`~`~`~@~#";
		HousewaybillService objHWS = new HousewaybillService();
		Decoder objPD = new Decoder(str);
		return objHWS.queryForBill(objPD);
		/*
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		objHWBCondition.setAbwlabelcode("1009875-SLYSZX-100308C");
		List objList = HousewaybillDemand.query(objHWBCondition);
		return String.valueOf(objList.size());
		*/
	}
	
	public static void inputAll() throws Exception {
		/*
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCustomerewbcode("123");
		HouseWayBillInput objHWBI = new HouseWayBillInput();
		List objResult = objHWBI.query(objFInputAllC);
		ForinputallColumns objForInputAllColumns = (ForinputallColumns)objResult.get(0);
		objForInputAllColumns.setCwserverewbcode("45678");
		objHWBI.inputAll("1", objForInputAllColumns);
		*/
		//String str = "1~`~@~#155711~`2014-10-14 11:48:48~`~`~`~`1~`~`22960~`~`727390~`58131~`1~`0.962~`0.962~`1~`0.962~`0.962~`0.962~`RC024163238MY~`RC024163238MY~`~`RC024163238MY~`~`~`APP~`~`AWPX~`~`12361~`~`~`~`A2171~`~`~`184~`~`~`719~`~`~`~`~`~`~`~`~`~`~`~`.~`.~`.~`.~`.~`~`.~`.~`SAMANTHA DUFFY~`.~`58131~`27 HUMBER RD COVENTRYWEST MIDLANDSUNI TED KINGDOM~`.~`.~`1773~`~`~`~`~`~`~`~`~`~`~`~`~`COVENTRY~`719~`0~`5000~`5000~`~`~`~`~`~`~`~`MYPKMC~`Y~`~`~`~`~`RC024163238MY~`.~`0.962~`50&55&32&72&85&77&66&69&82&32&82&68&32&67&79&86&69&78&84&82&89&87&69&83&84&32&77&73&68&76&65&78&68&83&85&78&73&84&69&68&32&75&73&78&71&68&79&77~`83&65&77&65&78&84&72&65&32&68&85&70&70&89~`~`~`~`~`~@~#~`~@~#False~`~@~#";
		String str = "109022~`~@~#158553~`2014-10-15 12:21:08~`~`~`~`1~`~`20362~`~`736961~`2149~`1~`0.406~`0.406~`1~`0.406~`0.406~`0.406~`RC024028269MY~`RC024028269MY~`~`RC024028269MY~`~`~`APP~`~`AWPX~`~`12381~`~`~`~`A2171~`~`~`116~`~`~`719~`~`~`~`~`~`~`~`~`~`~`~`.~`.~`.~`.~`.~`~`.~`.~`AURELIE LECUYER~`.~`2149~`11 ALLEE DES BERGERONNETTESREIMSREIMS FRANCE~`.~`.~`2149~`~`~`~`~`~`~`~`~`~`~`~`~`REIMS~`719~`0~`5000~`5000~`~`~`~`~`~`~`~`~`N~`~`~`~`~`RC024028269MY~`.~`0.406~`49&49&32&65&76&76&69&69&32&68&69&83&32&66&69&82&71&69&82&79&78&78&69&84&84&69&83&82&69&73&77&83&82&69&73&77&83&70&82&65&78&67&69~`65&85&82&69&76&73&69&32&76&69&67&85&89&69&82~`~`~`~`~`~@~#~`~@~#False~`~@~#";
		HousewaybillService objHWS = new HousewaybillService();
		Decoder objPD = new Decoder(str);
		objHWS.inputAll(objPD);
	}
	
	public static void inputAllForService() throws Exception {
		/*
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCustomerewbcode("123");
		HouseWayBillInput objHWBI = new HouseWayBillInput();
		List objResult = objHWBI.query(objFInputAllC);
		ForinputallColumns objForInputAllColumns = (ForinputallColumns)objResult.get(0);
		objForInputAllColumns.setCwserverewbcode("45678");
		objHWBI.inputAll("1", objForInputAllColumns);
		*/
		//String str = "1~`~@~#155711~`2014-10-14 11:48:48~`~`~`~`1~`~`22960~`~`727390~`58131~`1~`0.962~`0.962~`1~`0.962~`0.962~`0.962~`RC024163238MY~`RC024163238MY~`~`RC024163238MY~`~`~`APP~`~`AWPX~`~`12361~`~`~`~`A2171~`~`~`184~`~`~`719~`~`~`~`~`~`~`~`~`~`~`~`.~`.~`.~`.~`.~`~`.~`.~`SAMANTHA DUFFY~`.~`58131~`27 HUMBER RD COVENTRYWEST MIDLANDSUNI TED KINGDOM~`.~`.~`1773~`~`~`~`~`~`~`~`~`~`~`~`~`COVENTRY~`719~`0~`5000~`5000~`~`~`~`~`~`~`~`MYPKMC~`Y~`~`~`~`~`RC024163238MY~`.~`0.962~`50&55&32&72&85&77&66&69&82&32&82&68&32&67&79&86&69&78&84&82&89&87&69&83&84&32&77&73&68&76&65&78&68&83&85&78&73&84&69&68&32&75&73&78&71&68&79&77~`83&65&77&65&78&84&72&65&32&68&85&70&70&89~`~`~`~`~`~@~#~`~@~#False~`~@~#";
		/*
		String strSource = "http://211.154.135.204:9002/Express/HouseWayBillService.as?Function=inputAllForService&Parameter=0~`~@~#~^~`2014-11-24 13:06:23~`~^~`~^~`~^~`1~`QX~`23700~`深圳中通~`~^~`~^~`1~`1~`1~`1~`1~`1~`1~`3010648163~`2706775116~`~^~`3010648163~`105462~`2014-11-24 13:13:11~`APP~`预付~`AWPX~`包裹~`12540~`BQC-DHL特惠~`~^~`~^~`D0136~`DHL-HKA~`DHL-HKA~`1032~`SIN~`PULAU BUKOM KECHIL~`719~`SZX~`PINGHU,SHENZHEN~`IP~`制单~`~^~`~^~`14140~`深圳中通~`13380~`BQC~`ASFD~`ASDFAF~`23DAF~`ASDFASDF~`ASDFASDF~`ASDFASDF~`1~`232342~`~^~`ASDFFDS~`SD SDFX ESDFSM XASD~`818948~`ASDAF~`ASDFASDF~`23234~`1212232~`~^~`~^~`2014-11-24 13:13:11~`105462~`102681~`2014-11-25 15:18:51~`~^~`2014-11-25 14:59:45~`1~`~^~`0~`N~`SINGAPORE~`738~`0~`5000~`5000~`HKD~`0~`WPX~`WPX~`Input~`101~`SG~`~^~`~^~`~^~`~^~`0~`~^~`~^~`~^~`1~`~^~`~^~`01~`~^~`~^~`~^~`~^~`~^~`~@~#0~`617026~`232~`ASDFSDF~`1~`1~`1~`~^~`USD~`~^~`~^~`~@~#0~`617026~`1~`0~`0~`0~`~^~`~^~`~^~`~^~`~^~`~^~`~^~`~@~#";
		strSource = strSource.replace(Constants.NULL, "");
		System.out.println(strSource);
		*/
		String str = "0~`~@~#~^~`2014-11-24 13:06:23~`~^~`~^~`~^~`1~`QX~`23700~`深圳中通~`~^~`~^~`1~`1~`1~`1~`1~`1~`1~`3010648163~`2706775116~`~^~`3010648163~`105462~`2014-11-24 13:13:11~`APP~`预付~`AWPX~`包裹~`12540~`BQC-DHL特惠~`~^~`~^~`D0136~`DHL-HKA~`DHL-HKA~`1032~`SIN~`PULAU BUKOM KECHIL~`719~`SZX~`PINGHU,SHENZHEN~`IP~`制单~`~^~`~^~`14140~`深圳中通~`13380~`BQC~`ASFD~`ASDFAF~`23DAF~`ASDFASDF~`ASDFASDF~`ASDFASDF~`1~`232342~`~^~`ASDFFDS~`SD SDFX ESDFSM XASD~`818948~`ASDAF~`ASDFASDF~`23234~`1212232~`~^~`~^~`2014-11-24 13:13:11~`105462~`102681~`2014-11-25 15:18:51~`~^~`2014-11-25 14:59:45~`1~`~^~`0~`N~`SINGAPORE~`738~`0~`5000~`5000~`HKD~`0~`WPX~`WPX~`Input~`101~`SG~`~^~`~^~`~^~`~^~`0~`~^~`~^~`~^~`1~`~^~`~^~`01~`~^~`~^~`~^~`~^~`~^~`~@~#0~`617026~`232~`ASDFSDF~`1~`1~`1~`~^~`USD~`~^~`~^~`~@~#0~`617026~`1~`0~`0~`0~`~^~`~^~`~^~`~^~`~^~`~^~`~^~`~@~#";
		
		str = "@`3087989~`2014-11-24 13:06:23~`~`~`~`1~`百千诚物流~`23700~`深圳乾兴国际~`4525680~`~`1~`1~`1~`1~`1~`1~`1~`X3010648163~`6791418410~`~`X3010648163~`0~`2014-11-25 16:56:06~`APP~`预付~`AWPX~`包裹~`12921~`HKDHL其他~`~`~`D0136~`BQ特惠(打单)~`BQ特惠(打单)~`1032~`SIN~`PULAU BUKOM KECHIL~`719~`SZX~`SHENZHEN~`IP~`制单~`~`3087989~`14140~`深圳弘海远达国际~`505~`HKDHL~`ASFD~`ASDFAF~`23DAF~`ASDFASDF~`ASDFASDF~`ASDFASDF~`1~`232342~`~`ASDFFDS~`SD SDFX ESDFSM XASD~`818948~`ASDAF~`ASDFASDF~`23234~`1212232~`~`~`2014-11-24 13:06:23~`105462~`102681~`2014-11-25 16:56:06~`~`~`~`~`0~`N~`SINGAPORE~`738~`0~`5000~`5000~`HKD~`0~`WPX~`WPX~`Input~`1032~`SIN~`DHLMC~`Y~`DHLCC~`~`0~`~`~`~`1~`~`~`~`~`Y~`~`631039817~`631039817~`~@~#0~`4525680~`1~`0~`0~`0~`4600001371301905~`~`~`~`~`~`~`~@~#";
		str = "@`E_OP_001~`客户运单号已经被使用~`checkSignIn.checkCustomerEwbcode~`~@~#~#~#";
		Decoder objPD = new Decoder(str);
		int iCount = objPD.getParameterCount();
		
		List listInputColumns = objPD.getParameterList(0, ForinputallColumns.class);
		List listCorewaybillpieces = objPD.getParameterList(1, CorewaybillpiecesColumns.class);	
		
		
		//HousewaybillDelegate obj = new HousewaybillDelegate();
		//obj.inputAllForService(str);
	}	
	
	
	public static void modify() throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCustomerewbcode("00000011");
		HousewaybillDemand objHWBI = new HousewaybillDemand();
		InputAllQReturn objIAR = objHWBI.queryInput(objFInputAllC);
		
		Input objInput = new Input();
		objInput.modify("1", 
				(ForinputallColumns)objIAR.getHWBResults().get(0),
				objIAR.getCargoInfoResults(),
				false,
				false,
				false);
		/*
		String str = "0~`~@~#~^~`2014-11-22 16:10:05~`~^~`~^~`~^~`1~`QX~`23700~`永康圆通~`~^~`~^~`1~`1.5~`1.5~`1~`1.5~`1.5~`1.5~`3010648266~`3338252735 ~`~^~`3010648266~`105462~`2014-11-22 16:10:53~`APP~`预付~`AWPX~`包裹~`11760~`BQ-D促销价(QX2)~`~^~`~^~`D0136~`HKDHL代理~`HKDHL代理~`32~`AT~`AUSTRIA~`719~`SZX~`PINGHU,SHENZHEN~`IP~`制单~`~^~`~^~`24001~`永康圆通~`13380~`BQC~`~^~`.~`.~`.~`.~`.~`~^~`~^~`~^~`-~`-~`~^~`.~`.~`.~`~^~`~^~`~^~`2014-11-22 16:10:53~`105462~`102681~`2014-11-24 17:44:08~`~^~`2014-11-24 09:57:44~`105462~`~^~`~^~`~^~`~^~`~^~`0~`5000~`5000~`~^~`0~`WPX~`WPX~`Input~`32~`AT~`~^~`~^~`~^~`~^~`0~`~^~`~^~`~^~`1.5~`~^~`~^~`12区~`~^~`~^~`~^~`~^~`~^~`~@~#~#0~`616890~`1.5~`0~`0~`0~`~^~`~^~`~^~`~^~`~^~`~^~`~^~`~@~#";
		HousewaybillService objHWS = new HousewaybillService();
		Decoder objPD = new Decoder(str);
		objHWS.modifyInputAll(objPD);
		*/
	}
	
	public static void queryAll() throws Exception {
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		System.out.println("123");
		objHWBCondition.setServerewbcode("RX614683116DE");
		List list = HousewaybillDemand.query(objHWBCondition);
		
		HousewaybillColumns objHwcolumns = (HousewaybillColumns)list.get(0);
		List lr = CargoInfoDemand.queryByCwCode(objHwcolumns.getHwcwcode());
		
		System.out.println("123");
	}
	
	public static void testServerCW() throws Exception {
		
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCustomerewbcode("RX905555942DE");
		HousewaybillDemand objHWBI = new HousewaybillDemand();
		InputAllQReturn objIAR = objHWBI.queryInput(objFInputAllC);
		List list = objIAR.getHWBResults();
		ForinputallColumns objFIAColumns = (ForinputallColumns)list.get(0);
		List listWaybillPieces = null;
		Input objInput = new Input();
		ChargeweightParameter objCWParameter = objInput.transferToSWParameter(objFIAColumns,
				listWaybillPieces);
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);			
		if ("A01".equals(objCWResult.getServerweightrulekind())) {
			objFIAColumns.setCwserverchargeweight(new BigDecimal(objFIAColumns.getCwchargeweight()));
			objFIAColumns.setCwtransferchargeweight(new BigDecimal(objFIAColumns.getCwchargeweight()));
		} else {
			objFIAColumns.setCwserverchargeweight(new BigDecimal(objCWResult.getChargeweight()));
			objFIAColumns.setCwtransferchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		}		
		
		System.out.println(objCWResult.getChargeweight());
		
	}
	
	
	public static void queryInput() throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setServerewbcode("RX614683116DE");
		HousewaybillDemand.queryInput(objFInputAllC);
	}
	
	public static void queryForPredict() throws Exception {
		//WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
		//objWFPCondition.setEndcreatedate("2014-05-19");
		//objWFPCondition.setStartcreatedate("2014-05-18");
		//HousewaybillDemand.queryForPredict(objWFPCondition);
		String str = "Label:http://apps.dhl.com.hk/cgi-bin/label/html2pdf/cgipdf.cgi?awb=4959038545";
		System.out.println(str.replaceAll("Label:", ""));
	}	
	
	
	public static void signIn() throws Exception {
		String str = "~`~`~`~`~`~`~`~`~`~`~`~`1~`0.5~`0.5~`~`~`~`~`00992112~`~`~`~`~`~`1~`~`~`D0101~`~`~`~`~`~`~`~`~`~`~`~`~`719~`~`~`APP~`~`~`~`~`~`~`~`AWPX~`~`~`~`~`1~`~`~`161~`~`~`~`~`~`~@~#1~`~`0.5~`0~`0~`0~`~`~`~@~#1~`~@~#False~`~@~#";
		HousewaybillService objHWS = new HousewaybillService();
		Decoder objPD = new Decoder(str);
		objHWS.signIn(objPD);		
	}
	
	public static void modifyCorewaybill() throws Exception {
		String str = "266~`~`~`~`~`~`~`~`~`~`~`~`~`400.41~`400.5~`~`~`~`~`F001~`F001~`F001~`~`~`~`~`~`~`T0101~`~`~`~`~`~`~`202~`~`~`~`~`~`719~`~`~`APP~`~`~`~`~`~`~`~`AWPX~`~`~`~`~`~`~`~`~`~`~`~`~`~`0~`~`~`~@~#~`~@~#1~`~@~#False~`~@~#";
		HousewaybillService objHWS = new HousewaybillService();
		Decoder objPD = new Decoder(str);
		objHWS.modify(objPD);			
	}
	public static void queryReport() throws Exception {
		String str = "~`~`~`2010-11-16 00:00:00~`2011-11-16 23:59:59~`~`~`~`~`~`~`~`~`~`EL~`~`~`~`~`~`~`~`~`~`~`~`~`1~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`999999~`0~`~`1~`2009-10-16 00:00:00~`2011-11-16 23:59:59~`~@~#";
		HousewaybillService objHWS = new HousewaybillService();
		Decoder objPD = new Decoder(str);
		objHWS.queryForreport(objPD);			
	}
	public static void getChargeweight() throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setEwbcode("2268072925");
		ForinputallQuery objFInputAllQ = new ForinputallQuery();
		objFInputAllQ.setCondition(objFInputAllC);
		List listHouseWayBill = objFInputAllQ.getResults();
		ForinputallColumns objFIAColumns = (ForinputallColumns)listHouseWayBill.get(0);
		ChargeweightResult objCWResult = CorewaybillDemand.getChargeweight(objFIAColumns);
		System.out.println(objCWResult.getChargeweight());
	}
	
	public static void queryForcustoms() throws Exception {
		HousewaybillforcustomsCondition objHWBFCCondition = new HousewaybillforcustomsCondition();
		objHWBFCCondition.setIncustomerewbcode("111");
		objHWBFCCondition.setInserverewbcode("xxx");
		HousewaybillDemand.queryForcustoms(objHWBFCCondition);
	}	
	
	
	public static void batchSignInsignOut() throws Exception {
		String str = "319021563012~`101~`3~`D0102~`DHL特惠~`54~`180~`BOLIVIA~`~`~`~`~`~`~`~`~@~#~`~`~`CHARGER~`3~`~`550~`~`~`~@~#1~`~@~#3661~`~@~#1~`~@~#";
		HousewaybillService objHWS = new HousewaybillService();
		Decoder objPD = new Decoder(str);
		objHWS.batchSignInSignout(objPD);			
	}
	
	public static String getLabelConsignments() throws Exception {
		return HousewaybillDemand.getLabelConsignments("1582755");
	}
}
