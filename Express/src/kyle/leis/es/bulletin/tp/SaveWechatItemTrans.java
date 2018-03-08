package kyle.leis.es.bulletin.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.bulletin.da.WechatitemColumns;
import kyle.leis.es.bulletin.da.WechatitemQuery;
import kyle.leis.hi.TesWechatitem;
import kyle.leis.hi.TesWechatitemPK;
import net.sf.hibernate.Session;

public class SaveWechatItemTrans extends AbstractTransaction {
	private WechatitemColumns wechatitemColumns;
	
	public void setParams(WechatitemColumns columns){
		this.wechatitemColumns = columns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (wechatitemColumns == null) {
			return;
		}
		TesWechatitemPK comp_id = new TesWechatitemPK();
		String cwid = wechatitemColumns.getWccomp_idwciid();
		String blid = wechatitemColumns.getBlblid();
		comp_id.setBlId(Long.valueOf(blid));
		if (StringUtility.isNull(cwid)) {//±£´æ
			WechatitemQuery query = new WechatitemQuery();
			query.setBlid(blid);
			List<?> list = query.getResults();
			int maxItem = 0;
			if (!list.isEmpty()) {
				for (Object object : list) {
					WechatitemColumns columns = (WechatitemColumns) object;
					int comcwid = Integer.valueOf(columns.getWccomp_idwciid()).intValue();
					maxItem = comcwid > maxItem ? comcwid : maxItem;
				}
			}
			comp_id.setWciId(maxItem + 1);
			TesWechatitem tesWechatitem = new TesWechatitem();
			tesWechatitem.setComp_id(comp_id);
			tesWechatitem.setWciTitle(wechatitemColumns.getWcwcititle());
			tesWechatitem.setWciDescription(wechatitemColumns.getWcwcidescription());
			tesWechatitem.setWciPicurl(wechatitemColumns.getWcwcipicurl());
			tesWechatitem.setWciUrl(wechatitemColumns.getWcwciurl());
			tesWechatitem.setWciContent(wechatitemColumns.getWcwcicontent());
			objSession.save(tesWechatitem);
		} else {//ÐÞ¸Ä
			String sql = "update t_es_wechatitem t set t.WCI_TITLE = '" + wechatitemColumns.getWcwcititle() + "',"
					+ " t.WCI_DESCRIPTION = '" + StringUtility.replaceWhenNull(wechatitemColumns.getWcwcidescription(), "") + "',"
					+ " t.WCI_PICURL = '" + StringUtility.replaceWhenNull(wechatitemColumns.getWcwcipicurl(), "") + "',"
					+ " t.WCI_URL = '" + StringUtility.replaceWhenNull(wechatitemColumns.getWcwciurl(), "") + "',"
					+ " t.WCI_CONTENT = '" + StringUtility.replaceWhenNull(wechatitemColumns.getWcwcicontent(), "") + "'"
					+ " where t.WCI_ID = " + cwid
					+ " and t.BL_ID = " + blid;
			execute(objSession, sql);
		}
		
	}

}
