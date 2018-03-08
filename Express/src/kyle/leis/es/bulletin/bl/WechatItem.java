package kyle.leis.es.bulletin.bl;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.bulletin.da.WechatitemColumns;
import kyle.leis.es.bulletin.dax.BulletinDemand;
import kyle.leis.es.bulletin.tp.DelWechatItemTrans;
import kyle.leis.es.bulletin.tp.SaveWechatItemTrans;

public class WechatItem {
	/**
	 * ±£´æ
	 * @param columns
	 * @throws Exception
	 */
	public void save(WechatitemColumns columns) throws Exception{
		SaveWechatItemTrans itemTrans = new SaveWechatItemTrans();
		itemTrans.setParams(columns);
		itemTrans.execute();
		if (StringUtility.isNull(columns.getWccomp_idwciid())) {
			BulletinDemand.sendMessage(columns);
		}
	}
	
	public void delete(String cwid, String blid) throws Exception{
		DelWechatItemTrans delWechatItemTrans = new DelWechatItemTrans(cwid, blid);
		delWechatItemTrans.execute();
	}
}
