package kyle.leis.es.bulletin.dax;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.msgpush.MsgPush;
import kyle.common.util.msgpush.MsgRequest;
import kyle.leis.es.bulletin.da.BulletinColumns;
import kyle.leis.es.bulletin.da.BulletinCondition;
import kyle.leis.es.bulletin.da.BulletinQuery;
import kyle.leis.es.bulletin.da.BulletinQueryEX;
import kyle.leis.es.bulletin.da.WechatitemColumns;
import kyle.leis.fs.cachecontainer.da.BulletinkindQuery;
import kyle.leis.fs.cachecontainer.da.BulletinlevelQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBulletinkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBulletinlevelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TesBulletinDC;
import kyle.leis.hi.TdiBulletinkind;
import kyle.leis.hi.TdiBulletinlevel;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TesBulletin;
import net.sf.hibernate.Session;

public class BulletinDemand {
	public static List query(BulletinCondition objBLCondition) throws Exception {
		BulletinQuery objBulletinQuery = new BulletinQuery();
		objBulletinQuery.setCondition(objBLCondition);
		return objBulletinQuery.getResults();
	}

	public static List queryOrderEX(BulletinCondition objBLCondition)
			throws Exception {
		BulletinQueryOrderEX objBulletinQuery = new BulletinQueryOrderEX();
		objBulletinQuery.setCondition(objBLCondition);
		return objBulletinQuery.getResults();
	}

	public static List queryByHeading(BulletinCondition objBLCondition)
			throws Exception {
		BulletinQueryEX objBulletinQueryEX = new BulletinQueryEX();
		objBulletinQueryEX.setCondition(objBLCondition);
		return objBulletinQueryEX.getResults();
	}

	/**
	 * 从缓冲中取得数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List queryByCache() throws Exception {
		TesBulletinDC objTesBulletinDC = new TesBulletinDC();
		return objTesBulletinDC.getResults();
	}

	public static void setBulletinByColumns(BulletinColumns objBulletinColumns,
			TesBulletin objTesBulletin, String strOperId, Session objSession)
			throws Exception {
		objTesBulletin.setBlContent(objBulletinColumns.getBlblcontent());
		objTesBulletin.setBlContentindex(objBulletinColumns
				.getBlblcontentindex());
		objTesBulletin.setBlHeading(objBulletinColumns.getBlblheading());
		objTesBulletin.setBlLink(objBulletinColumns.getBlbllink());
		objTesBulletin.setBlModifydate(DateFormatUtility.getSysdate());
		objTesBulletin.setBlSignname(objBulletinColumns.getBlblsignname());
		objTesBulletin.setBlValiddate(DateFormatUtility.getSysdate());
		objTesBulletin.setBlWechatsign(objBulletinColumns.getBlblwechatsign());

		if (!StringUtility.isNull(objBulletinColumns.getBkbkcode())) {
			TdiBulletinkind objTBK = TdiBulletinkindDC
					.loadByKey(objBulletinColumns.getBkbkcode());
			objTesBulletin.setTdiBulletinkind(objTBK);
		}
		if (!StringUtility.isNull(objBulletinColumns.getBllblcode())) {
			TdiBulletinlevel objTBLL = TdiBulletinlevelDC
					.loadByKey(objBulletinColumns.getBllblcode());
			objTesBulletin.setTdiBulletinlevel(objTBLL);
		}
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
		objTesBulletin.setTdiOperatorByOpIdModifier(objTdiOperator);
	}

	/**
	 * 获得所有公告类型
	 * 
	 * @throws Exception
	 */
	public static List queryAllBllKing() throws Exception {
		BulletinkindQuery bkq = new BulletinkindQuery();
		return bkq.getResults();

	}

	/**
	 * 获取所有公告级别
	 */
	public static List queryAllbllLevel() throws Exception {
		BulletinlevelQuery blq = new BulletinlevelQuery();
		return blq.getResults();
	}

	/**
	 *查询微信公告
	 * 
	 * @param condition
	 * @param weChatSign
	 * @return
	 */
	public static List<?> queryByWeChatSign(BulletinCondition condition,
			String weChatSign) {
		BulletinQueryEX queryEX = new BulletinQueryEX(weChatSign);
		queryEX.setCondition(condition);
		try {
			return queryEX.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<BulletinColumns>();
	}

	public static void sendMessage(BulletinColumns bulletinColumns) {
		MsgPush msgPush = new MsgPush();
		MsgRequest request = new MsgRequest();
		request.setMsgTitle(bulletinColumns.getBlblheading());
		request.setMsgContent(bulletinColumns.getBlblheading() + "......\r\n"
				+ "详情请访问官网！");
		msgPush.setGroupSend(true);
		msgPush.sendMsg(request);
	}
	
	public static void sendMessage(WechatitemColumns columns){
		MsgPush msgPush = new MsgPush();
		MsgRequest msgRequest = new MsgRequest();
		msgRequest.setMsgType("news");
		msgRequest.setMsgTitle(columns.getWcwcititle());
		msgRequest.setMsgContent(columns.getWcwcititle() + "......\r\n" + columns.getWcwcidescription());
		msgRequest.setParam("title", columns.getWcwcititle());
		msgRequest.setParam("url", columns.getWcwciurl());
		msgPush.setGroupSend(true);
		msgPush.sendMsg(msgRequest);
	}
}
