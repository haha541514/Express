package kyle.leis.es.bulletin.dax;

import kyle.leis.es.bulletin.da.BulletinQuery;

public class BulletinQueryOrderEX extends BulletinQuery{
	public BulletinQueryOrderEX(){
		m_strOrderByClause = "bl.blValiddate desc";
	}
}
