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
		m_CargoService = new CargokindService();//����ûnew,��ָ���쳣= =
		String queryStr = "~`~`~`~`ON~`~`~@~#";
		Decoder objPD = new Decoder(queryStr);
		String objReturn =   m_CargoService.query(objPD);
		System.out.println(objReturn);
		return null;
		
	}

	/**
	 * 20160802 �ܶ� 16:24
	 * by wukaiquan 
	 * save
	 * @throws Exception 
	 * ���еĴ洢����
	 * **/
	public void save() throws Exception{
				
		m_CargoService = new CargokindService();
		String saveSql = "41~`dell~`Cargo~`Y~`ON~`~@~#";//�����Columns�����ַ���λ���й�ϵ
		//String saveSql = "~`asdas~`adasd~`Y~`~`~`~@~#";//û��ss_code����Ϊ��save,
		Decoder objPD  = new Decoder(saveSql);
		
		//��ôȥ�������objEncoder string
		String objReturn = m_CargoService.save(objPD);
		System.out.println(objReturn);
		
		
	}
	

	public void deleteCargokin() throws Exception{
		m_CargoService = new CargokindService();
		String deleteId = "31~`~@~#";//û����~`~@~#��β
		Decoder objPD = new Decoder(deleteId);
		String objReturn = m_CargoService.delete(objPD);
		System.out.println(objReturn);//Ϊʲô���ص���һ����Ч�ģ�������ʧЧ��
	}
	
	
}
