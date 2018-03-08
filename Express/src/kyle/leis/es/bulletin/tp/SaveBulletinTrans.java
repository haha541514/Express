package kyle.leis.es.bulletin.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.bulletin.da.BulletinColumns;
import kyle.leis.es.bulletin.da.BulletinseqColumns;
import kyle.leis.es.bulletin.da.BulletinseqQuery;

public class SaveBulletinTrans extends AbstractTransaction {
	private BulletinColumns m_objBulletinColumns;
	private String m_strSavedBlID;
	//private String m_strOperId;
	public void setParam(BulletinColumns objBulletinColumns, 
			String strOperId) throws Exception {
		//m_strOperId = strOperId;
		m_objBulletinColumns = objBulletinColumns;
	}
	
	private String getBulletinseq() throws Exception {
		BulletinseqQuery objBulletinseqQuery = new BulletinseqQuery();
		List listResult = objBulletinseqQuery.getResults();
		return ((BulletinseqColumns)listResult.get(0)).getBuildewbseq();
	}
	
	public String getSavedBlID() {
		return m_strSavedBlID;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objBulletinColumns == null) return;
		// 新增或修改
		if (StringUtility.isNull(m_objBulletinColumns.getBlblid())) {
			m_strSavedBlID = getBulletinseq();
			String strInsertSQL="insert into T_ES_BULLETIN(BL_ID,BL_HEADING,BK_CODE,BL_CODE,BL_CONTENT," +
			                    "BL_CONTENTINDEX,BL_LINK,BL_SIGNNAME,BL_VALIDDATE,BL_WECHATSIGN," +
			                    "OP_ID_CREATOR,OP_ID_MODIFIER,BL_CREATEDATE,BL_MODIFYDATE)" +
            "values(" + m_strSavedBlID + ",'" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblheading(),"") + "'," + 
                   "'" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBkbkcode(),"") + "'," + 
                   "'" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBllblcode(),"") + "'," +
                   "?," +
                   "'" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblcontentindex(),"") + "'," +
                   "'" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlbllink(),"") + "'," +
                   "'" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblsignname(),"") + "'," +
                   "to_date('" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblvaliddate(),DateFormatUtility.getStandardSysdate()) + "', 'yyyy-mm-dd hh24:mi:ss')," +
                   "'" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblwechatsign(),"") + "'," +
                   //"'N'," +
                   "0, 0, sysdate, sysdate)";
			execute(objSession, strInsertSQL, StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblcontent(),""));				
		} else {
			// 由于有Long字段，所以只能用SQL语句来执行事务
			m_strSavedBlID = m_objBulletinColumns.getBlblid();
			String strUpdateSQL="update T_ES_BULLETIN " +
            "   set BL_HEADING='" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblheading(),"") + "'," + 
                   "BK_CODE='" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBkbkcode(),"") + "'," + 
                   "BL_CODE='" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBllblcode(),"") + "'," +
                   "BL_MODIFYDATE=sysdate," +
                   "BL_CONTENT=?," +
                   "BL_CONTENTINDEX='" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblcontentindex(),"") + "'," +
                   "BL_LINK='" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlbllink(),"") + "'," +
                   "BL_SIGNNAME='" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblsignname(),"") + "'," +
                   "BL_VALIDDATE=to_date('" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblvaliddate(),DateFormatUtility.getStandardSysdate()) + "', 'yyyy-mm-dd hh24:mi:ss')," +
                   "BL_WECHATSIGN='" + StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblwechatsign(),"") + "'" +
                   //"BL_WECHATSIGN='N'" +
            " where BL_ID = '" + m_objBulletinColumns.getBlblid()+"'";
			execute(objSession,strUpdateSQL, StringUtility.replaceWhenNull(m_objBulletinColumns.getBlblcontent(),""));	
		}
	}
	
}
