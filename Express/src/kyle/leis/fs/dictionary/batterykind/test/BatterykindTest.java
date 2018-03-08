package kyle.leis.fs.dictionary.batterykind.test;




import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindColumns;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindCondition;
import kyle.leis.fs.dictionary.batterykind.dax.BatterkindDemand;
import kyle.leis.fs.dictionary.batterykind.sv.BatterykindService;

public class BatterykindTest {

	private BatterykindService m_objBatterService ;
	public static void main(String[] args) throws Exception {
		BatterykindTest battertest = new BatterykindTest();
		battertest.saveBatterykind();
		//battertest.deletBatterkind();
		//battertest.query();
	}
	
	public void saveBatterykind() throws Exception{
	
		m_objBatterService = new BatterykindService();
		//27~`蓄电池2333~`蓄电池2~`~`~`3~`~`~@~#
						//~`蓄电池2~`Battery~`ON~`~`3~`~`~@~#
		//String saveStr = "~`暗物质电池~`Battery~`ON~`~`8~`~@~#";
		String saveStr = "27~`蓄电池2333~`蓄电池2~`~`~`3~`~`~@~#";
		Decoder objPD = new Decoder(saveStr);
		String objReturn =  m_objBatterService.save(objPD);
		System.out.println(objReturn);
		
		
	}

	public void deletBatterkind() throws Exception{
		m_objBatterService = new BatterykindService();
		String deleteId = "~`~@~#";
		Decoder objPD = new Decoder(deleteId);
		String objReturn = 	m_objBatterService.delete(objPD);
		System.out.println(objReturn);
	}
	
	public String query() throws Exception{
		m_objBatterService = new BatterykindService();
		String queryStr = "~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(queryStr);
		return m_objBatterService.query(objPD);
		
	}
	
}
