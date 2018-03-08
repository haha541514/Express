package kyle.leis.fs.dictionary.cargokind.test;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.cargokind.da.CargokindColumns;
import kyle.leis.fs.dictionary.cargokind.da.CargokindCondition;
import kyle.leis.fs.dictionary.cargokind.dax.CargokindDemand;
import kyle.leis.fs.dictionary.cargokind.sv.CargokindService;
import kyle.leis.fs.dictionary.cargokind.tp.DeleteCargokind;
import kyle.leis.fs.dictionary.cargokind.tp.ModifyCargokind;
import kyle.leis.fs.dictionary.cargokind.tp.SaveCargokind;
import kyle.leis.hi.TdiCargokind;

public class CargokindTest {

	private CargokindService m_CargoService;
	public static void main(String[] args) throws Exception {
		CargokindTest test = new CargokindTest();
		test.save();
		//test.deleteCargokin();
		//test.Query();
	}
	
	private String Query() throws Exception {
		m_CargoService = new CargokindService();//对象没new,空指针异常= =
		String queryStr = "~`~`~`~`ON~`~`~@~#";
		Decoder objPD = new Decoder(queryStr);
		String objReturn =   m_CargoService.query(objPD);
		System.out.println(objReturn);
		return null;
		
	}

	/**
	 * 20160802 周二 16:24
	 * by wukaiquan 
	 * save
	 * @throws Exception 
	 * 序列的存储自增
	 * **/
	public void save() throws Exception{
				
		m_CargoService = new CargokindService();
		String saveSql = "41~`dell~`Cargo~`Y~`ON~`~@~#";//这个和Columns里面字符串位置有关系
		//String saveSql = "~`asdas~`adasd~`Y~`~`~`~@~#";//没有ss_code不认为能save,
		Decoder objPD  = new Decoder(saveSql);
		
		//怎么去接受这个objEncoder string
		String objReturn = m_CargoService.save(objPD);
		System.out.println(objReturn);
		
		
	}
	

	public void deleteCargokin() throws Exception{
		m_CargoService = new CargokindService();
		String deleteId = "31~`~@~#";//没有用~`~@~#结尾
		Decoder objPD = new Decoder(deleteId);
		String objReturn = m_CargoService.delete(objPD);
		System.out.println(objReturn);//为什么返回的是一个生效的，而不是失效的
	}
	
	
}
