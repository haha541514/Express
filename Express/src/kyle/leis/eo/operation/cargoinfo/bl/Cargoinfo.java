package kyle.leis.eo.operation.cargoinfo.bl;

import java.util.List;

import kyle.leis.eo.operation.cargoinfo.tp.AddCargoInfoTransaction;

public class Cargoinfo {
	
	public void save(List listCargoInfo, 
			String strCwcode) throws Exception {
		AddCargoInfoTransaction objACIT = new AddCargoInfoTransaction();
		objACIT.setParam(listCargoInfo, strCwcode);
		objACIT.execute();
	}
	
	public void save(List listCargoInfo,
			String strGenericGoods,
			String strCwcode) throws Exception {
		AddCargoInfoTransaction objACIT = new AddCargoInfoTransaction();
		objACIT.setParam(listCargoInfo, strGenericGoods, strCwcode);
		objACIT.execute();
	}
	
	public static boolean deleteCargoinfo(List listCargoInfo,String strCwcode){		
		try {
			AddCargoInfoTransaction objACITrans=new AddCargoInfoTransaction();
			objACITrans.setParam(listCargoInfo, strCwcode);
			objACITrans.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
