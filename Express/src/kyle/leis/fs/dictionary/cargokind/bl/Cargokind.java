package kyle.leis.fs.dictionary.cargokind.bl;

import java.util.List;

import kyle.leis.fs.dictionary.cargokind.da.CargokindColumns;
import kyle.leis.fs.dictionary.cargokind.da.CargokindCondition;
import kyle.leis.fs.dictionary.cargokind.dax.CargokindDemand;
import kyle.leis.fs.dictionary.cargokind.tp.DeleteCargokind;
import kyle.leis.fs.dictionary.cargokind.tp.ModifyCargokind;
import kyle.leis.fs.dictionary.cargokind.tp.SaveCargokind;

/**
 * 处理业务
 * **/
public class Cargokind {


	
	public CargokindColumns save(CargokindColumns columns) throws Exception{
	
		
		SaveCargokind save = new SaveCargokind();
		save.setParam(columns,columns.getCkcgkcode());
		System.out.println(columns.getCkcgkcode());
		save.execute();

		CargokindColumns objReturn = CargokindDemand.queryById(columns.getCkcgkcode());
		if(objReturn == null) return null;//如果返回值为空
		return objReturn;
		
	}
	

	public CargokindColumns delete(String cgkcode) throws Exception{
		
		DeleteCargokind delete = new DeleteCargokind();
		delete.setParam(cgkcode);
		delete.execute();
		return delete.getCargokindColumns();
	}
	
	
	
	
	
	
	
	
	
}
