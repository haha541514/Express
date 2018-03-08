package kyle.leis.es.bulletin.dax;

import java.util.List;

import kyle.leis.es.bulletin.da.WechatitemColumns;
import kyle.leis.es.bulletin.da.WechatitemCondition;
import kyle.leis.es.bulletin.da.WechatitemQuery;
import kyle.leis.hi.TesWechatitemPK;

public class WechatItemDemand {
	public static WechatitemColumns loadByKey(TesWechatitemPK pk) throws Exception{
		WechatitemQuery query = new WechatitemQuery();
		WechatitemCondition condition = new WechatitemCondition();
		condition.setWciid(String.valueOf(pk.getWciId()));
		condition.setBlid(String.valueOf(pk.getBlId()));
		List<?> list = query.getResults();
		if (!list.isEmpty()) {
			return (WechatitemColumns) list.get(0);
		} else {
			return null;
		}
	}
}
