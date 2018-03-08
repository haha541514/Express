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

		// ��һ������ָ�����񣬵ڶ���ָ��Service,���������Դ�����
		checkParameterCount(objPD, 1, this);

		CargokindColumns objCargokindColumns = (CargokindColumns) objPD
				.getParameter(0, CargokindColumns.class);
		Cargokind objCargo = new Cargokind();
		CargokindColumns objReturn = objCargo.save(objCargokindColumns);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);//����
		return objEncode.toString();

	}


	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CargokindCondition objCondition = new CargokindCondition();
		objCondition = (CargokindCondition) objPD.getParameter(0, CargokindCondition.class);//��ȡ��һλ
		List<CargokindColumns> objColumns = CargokindDemand.Query(objCondition);	
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objColumns);
		return objEncoder.toString();
		
	}

	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String cgk_code = (String) objPD.getParameter(0, String.class);// ��codeתΪString,cgk_code=null,û�д�����
		Cargokind objCargo = new Cargokind();
		CargokindColumns objCargokindColumns = objCargo.delete(cgk_code);

		if (objCargokindColumns != null) {
			return "sss";
		} else {
			return null;
		}
	}

}
