package kyle.leis.fs.dictionary.waybilltrackstatus.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.cachecontainer.da.WaybilltrackstatusColumns;
import kyle.leis.fs.dictionary.waybilltrackstatus.bl.Waybilltrackstatus;
import kyle.leis.fs.dictionary.waybilltrackstatus.dax.WaybilltrackstatusDemand;

public class WaybilltrackstatusService extends AService {
	/**
	 * �켣״̬��ѯ
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryTrackstatus(Decoder objPD) throws Exception{
		checkParameterCount(objPD,1,this);
		
		Encoder objEncode = new Encoder();
		String pkCode = (String) objPD.getParameter(0, String.class);
		if (StringUtility.isNull(pkCode)) {
			objEncode.addParameter(WaybilltrackstatusDemand.get());
		}else {
			objEncode.addParameter(WaybilltrackstatusDemand.getByWbtsCode(pkCode));
		}
		return objEncode.toString();
	}
	/**
	 * ���/�޸Ĺ켣״̬
	 * @param decoder
	 * @return
	 * @throws Exception
	 */
	public String editTrackstatus(Decoder decoder) throws Exception{
		checkParameterCount(decoder,1,this);
		
		Waybilltrackstatus waybilltrackstatus = new Waybilltrackstatus();
		WaybilltrackstatusColumns columns = (WaybilltrackstatusColumns) decoder.getParameter(0, 
				WaybilltrackstatusColumns.class);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(waybilltrackstatus.add(columns));
		return objEncode.toString();
	}
	/**
	 * ɾ���켣״̬
	 * @param decoder
	 * @return
	 * @throws Exception
	 */
	public String delTrackstatus(Decoder decoder) throws Exception{
		checkParameterCount(decoder,1,this);
		
		Waybilltrackstatus waybilltrackstatus = new Waybilltrackstatus();
		String pkCode = (String) decoder.getParameter(0, String.class);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(waybilltrackstatus.add(waybilltrackstatus.delete(pkCode)));
		return objEncode.toString();
	}
}










