package kyle.leis.fs.dictionary.issuetype.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.issuetype.bl.Issuetype;
import kyle.leis.fs.dictionary.issuetype.da.TdiissuetypeColumns;
import kyle.leis.fs.dictionary.issuetype.da.TdiissuetypeCondition;
import kyle.leis.fs.dictionary.issuetype.dax.TdiissuetypeDemand;

@SuppressWarnings("unchecked")
public class IssuetypeService extends AService {

	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		TdiissuetypeColumns objTdiissuetypeColumns = (TdiissuetypeColumns) objPD.getParameter(0, TdiissuetypeColumns.class);
		Issuetype objIssuetype = new Issuetype();
		List<TdiissuetypeColumns> objList = objIssuetype.save(objTdiissuetypeColumns);
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objList);
		return objEncoder.toString();
	}



	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		TdiissuetypeCondition objTdiissuetypeCondition = (TdiissuetypeCondition) objPD.getParameter(0, TdiissuetypeCondition.class);
		List<TdiissuetypeColumns> objList = TdiissuetypeDemand.query(objTdiissuetypeCondition);

		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objList);
		return objEncoder.toString();

	}

	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String strIsutcode = (String) objPD.getParameter(0, String.class);
		Issuetype objIssuetype = new Issuetype();
		objIssuetype.deleteIssuetype(strIsutcode, "OFF");
		return "";
	}
}
