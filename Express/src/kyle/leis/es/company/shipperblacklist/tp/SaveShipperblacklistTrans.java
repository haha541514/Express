package kyle.leis.es.company.shipperblacklist.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.shipperblacklist.da.ShipperblacklistColumns;
import kyle.leis.es.company.shipperblacklist.dax.ShipperblacklistDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcoShipperblacklist;
import net.sf.hibernate.Session;

public class SaveShipperblacklistTrans extends AbstractTransaction {

	private ShipperblacklistColumns m_objSBLColumns;
	private String m_strOperId;
	private Long strNewSblcode;
	
	public Long getNewSblcode()
	{
		return this.strNewSblcode;
	}
	
	public void setParam(ShipperblacklistColumns objSBLColumns,String strOperId)
	{
		this.m_objSBLColumns = objSBLColumns;
		this.m_strOperId = strOperId;
	}
	public void transaction(Session objSession) throws Exception {
		if(m_objSBLColumns == null || StringUtility.isNull(m_strOperId)) return;
		
		TcoShipperblacklist objShipperblacklist;
		if(StringUtility.isNull(m_objSBLColumns.getSblsblcode()))
		{
			objShipperblacklist = new TcoShipperblacklist();
			objShipperblacklist.setSblCreatedate(DateFormatUtility.getSysdate());
			objShipperblacklist.setTdiOperatorByOpIdCreator(TdiOperatorDC.loadByKey(m_strOperId));
		}
		else
			objShipperblacklist = (TcoShipperblacklist)objSession.load(TcoShipperblacklist.class, Long.valueOf(m_objSBLColumns.getSblsblcode()));
		
		ShipperblacklistDemand.setShipperblacklistByColumns(objShipperblacklist, m_objSBLColumns, m_strOperId, objSession);
		objSession.save(objShipperblacklist);
		this.strNewSblcode = objShipperblacklist.getSblCode();
	}

}
