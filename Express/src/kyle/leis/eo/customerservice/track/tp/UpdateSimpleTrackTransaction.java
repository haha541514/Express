package kyle.leis.eo.customerservice.track.tp;


import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.eo.customerservice.track.da.SimpletrackColumns;

public class UpdateSimpleTrackTransaction extends AbstractTransaction{
	 private SimpletrackColumns m_objSimpletrackColumns;
     public void setParam(SimpletrackColumns objSimpletrackColumns){
			this.m_objSimpletrackColumns =objSimpletrackColumns;
		} 
	public void transaction(Session objSession) throws Exception {
		execute(objSession, "update T_CS_SimpleWayBillTrack swbt set swbt.swbt_modifier = '" + 
				m_objSimpletrackColumns.getSwbtswbt_modifier() + 
				"',swbt.swbt_modifydate=to_date('"+DateFormatUtility.getStandardSysdate()+
				"','yyyy-mm-dd hh24:mi:ss'),swbt.swbt_description='"+m_objSimpletrackColumns.getSwbtswbt_description()+
				"',swbt.swbt_location='"+m_objSimpletrackColumns.getSwbtswbt_location()+
				"',swbt.swbt_occurdate=to_date('"+m_objSimpletrackColumns.getSwbtswbt_occurdate()+
				"','yyyy-mm-dd hh24:mi:ss') where swbt.swbt_id="+Long.parseLong(m_objSimpletrackColumns.getSwbtswbt_id()));
		
	}

}
