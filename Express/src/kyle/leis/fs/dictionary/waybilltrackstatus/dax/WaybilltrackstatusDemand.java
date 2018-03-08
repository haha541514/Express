package kyle.leis.fs.dictionary.waybilltrackstatus.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.cachecontainer.da.WaybilltrackstatusColumns;
import kyle.leis.fs.cachecontainer.da.WaybilltrackstatusQuery;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TdiWaybilltrackstatus;
import kyle.leis.hi.TdiWaybilltransferphase;

public class WaybilltrackstatusDemand {
	/**
	 * 查询轨迹状态
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<WaybilltrackstatusColumns> get() throws Exception{
		WaybilltrackstatusQuery query = new WaybilltrackstatusQuery();
		return query.getResults();
	}
	/**
	 * 通过主键查询
	 * @param wbtsCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static WaybilltrackstatusColumns getByWbtsCode(String wbtsCode) throws Exception{
		WaybilltrackstatusQueryEX queryEX = new WaybilltrackstatusQueryEX(wbtsCode);
		List<WaybilltrackstatusColumns> list = queryEX.getResults();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	/**
	 * 将VO转换成PO
	 * @param vo
	 * @param po
	 */
	public static void convertToPO(WaybilltrackstatusColumns vo, TdiWaybilltrackstatus po){
		po.setWbtsCode(StringUtility.replaceWhenNull(vo.getWbtswbtscode(), po.getWbtsCode()));
		po.setWbtsName(StringUtility.replaceWhenNull(vo.getWbtswbtsname(), po.getWbtsName()));
		po.setWbtsEname(StringUtility.replaceWhenNull(vo.getWbtswbtsename(), po.getWbtsEname()));
		po.setWbtsAbnormalsign(StringUtility.replaceWhenNull(vo.getWbtswbtsabnormalsign(), po.getWbtsAbnormalsign()));
		if (!StringUtility.isNull(vo.getWbtpwbtpcode())) {
			TdiWaybilltransferphase tdiWaybilltransferphase = new TdiWaybilltransferphase();
			tdiWaybilltransferphase.setWbtpCode(vo.getWbtpwbtpcode());
			po.setTdiWaybilltransferphase(tdiWaybilltransferphase);
		}
		if (po.getTdiSimplestatus() == null) {
			TdiSimplestatus simplestatus = new TdiSimplestatus();
			simplestatus.setSsCode("ON");
			po.setTdiSimplestatus(simplestatus);
		}
	}
}













