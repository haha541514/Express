package kyle.leis.fs.dictionary.batterykind.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.batterykind.bl.Batterykind;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindColumns;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindCondition;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindSeqQuery;
import kyle.leis.fs.dictionary.batterykind.dax.BatterkindDemand;

public class BatterykindService extends AService{

	public String save(Decoder objPD) throws Exception{
		checkParameterCount(objPD,1, this);
		BatterykindColumns columns = (BatterykindColumns) objPD.getParameter(0, BatterykindColumns.class);
		System.out.println(columns.getCkcgkcode());//Îª¿Õ£¬
		Batterykind objKind = new Batterykind();
		
		BatterykindColumns objReturn = objKind.save(columns);
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objReturn);
		return objEncoder.toString();

	}
	
	
	public String delete(Decoder objPD) throws Exception{
	
		String code = (String) objPD.getParameter(0, String.class);
		Batterykind objBatterykind = new Batterykind();
		BatterykindColumns objColumns = objBatterykind.delete(code);
		if( objColumns  != null){
			Encoder objEncoder = new Encoder();
			objEncoder.addParameter(objColumns);
			return objEncoder.toString();
		}else{
			return null;
		}
		
		
	}
	public String query(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		
		BatterykindCondition objCondition = new BatterykindCondition();
		objCondition = (BatterykindCondition) objPD.getParameter(0, BatterykindCondition.class);
		List<BatterykindColumns> list = BatterkindDemand.query(objCondition);

		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(list);
		return objEncoder.toString();
		
	}
}
