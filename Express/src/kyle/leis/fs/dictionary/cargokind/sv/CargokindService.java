package kyle.leis.fs.dictionary.cargokind.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.cargokind.bl.Cargokind;
import kyle.leis.fs.dictionary.cargokind.da.CargokindColumns;
import kyle.leis.fs.dictionary.cargokind.da.CargokindCondition;
import kyle.leis.fs.dictionary.cargokind.da.CargokindSeqQuery;
import kyle.leis.fs.dictionary.cargokind.dax.CargokindDemand;

public class CargokindService extends AService {

	private Cargokind objCargo;

	public String save(Decoder objPD) throws Exception {

		// 第一个参数指定服务，第二个指定Service,第三个可以带参数
		checkParameterCount(objPD, 1, this);

		CargokindColumns objCargokindColumns = (CargokindColumns) objPD
				.getParameter(0, CargokindColumns.class);
		Cargokind objCargo = new Cargokind();
		CargokindColumns objReturn = objCargo.save(objCargokindColumns);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);//加密
		return objEncode.toString();

	}


	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CargokindCondition objCondition = new CargokindCondition();
		objCondition = (CargokindCondition) objPD.getParameter(0, CargokindCondition.class);//获取第一位
		List<CargokindColumns> objColumns = CargokindDemand.Query(objCondition);	
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objColumns);
		return objEncoder.toString();
		
	}

	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String cgk_code = (String) objPD.getParameter(0, String.class);// 将code转为String,cgk_code=null,没有传过来
		Cargokind objCargo = new Cargokind();
		CargokindColumns objCargokindColumns = objCargo.delete(cgk_code);

		if (objCargokindColumns != null) {
			return "sss";
		} else {
			return null;
		}
	}

}
