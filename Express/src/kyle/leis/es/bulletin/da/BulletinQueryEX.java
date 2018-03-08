package kyle.leis.es.bulletin.da;

public class BulletinQueryEX extends BulletinQuery{
	public BulletinQueryEX(){
		m_astrConditionWords = new String[] {"bl.blHeading like '~~'", "bl.blContentindex like '%~~%'", "bl.blValiddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bl.blValiddate", "mop.opId = ~~", "bk.bkCode = '~~'", "bll.blCode = '~~'", "bl.blId = ~~" };
	}
	
	public BulletinQueryEX(String weChatSign){
		if (weChatSign == null) {
			m_strWhereClause = " bl.blWechatsign is null";
		} else {
			m_strWhereClause = " bl.blWechatsign = '" + weChatSign + "'";
		}
	}
}
