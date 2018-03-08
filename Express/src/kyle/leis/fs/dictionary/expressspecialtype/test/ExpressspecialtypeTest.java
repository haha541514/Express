package kyle.leis.fs.dictionary.expressspecialtype.test;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeColumns;
import kyle.leis.fs.dictionary.expressspecialtype.sv.ExpressspecialtypeService;

public class ExpressspecialtypeTest {
	public static ExpressspecialtypeService service = new ExpressspecialtypeService();
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//query();
		add();
		//update();
		//delete();
	}
	//添加特殊类型
	public static void add() throws Exception{
		//获取编码
		String params = "A06~`~@~#1~`~@~#";
		Decoder objPD = new Decoder(params);
		String code = service.getSpecialTypeCode(objPD);
		System.out.println(code);
		
		objPD = new Decoder(code);
		String pkCode = (String) objPD.getParameter(0, String.class);
		ExpressspecialtypeColumns expressspecialtype = new ExpressspecialtypeColumns() ;
		expressspecialtype.setEstestcode(pkCode);
		expressspecialtype.setEstestename("ODA");
		expressspecialtype.setEstestendsign("N");
		expressspecialtype.setEstestexcludesign("N");
		expressspecialtype.setEstestname("偏远");
		expressspecialtype.setEstestpeculiarlychannelsign("N");
		expressspecialtype.setEsteststructurecode(pkCode);
		expressspecialtype.setSssscode("ON");
		Encoder encoder = new Encoder();
		encoder.addParameter(expressspecialtype);
		params = encoder.toString();
		params = params.replaceAll("~&", "");
		System.out.println(params);
		objPD = new Decoder(params);
		System.out.println(service.addExpressspecialtype(objPD));
	}
	//修改
	public static void update() throws Exception {
		String pkCode = "A06";
		ExpressspecialtypeColumns expressspecialtype = new ExpressspecialtypeColumns() ;
		expressspecialtype.setEstestcode(pkCode);
		expressspecialtype.setEsteststructurecode(pkCode);
		Encoder encoder = new Encoder();
		encoder.addParameter(expressspecialtype);
		String params = encoder.toString();
		params = params.replaceAll("~&", "");
		System.out.println(params);
		Decoder objPD = new Decoder(params);
		System.out.println(service.addExpressspecialtype(objPD));
	}
	//删除特殊类型
	public static void delete() throws Exception{
		String pkCode = "A06~`~@~#";
		Decoder objPD = new Decoder(pkCode);
		System.out.println(service.delExpressspecialtype(objPD));
	}
	//查询类型
	public static void query() throws Exception{
		String strpkCode = "~`~`~@~#";
		Decoder objPD = new Decoder(strpkCode);
		System.out.println(service.query(objPD));
		
	}
}
