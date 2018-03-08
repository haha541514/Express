package kyle.leis.fs.waybillcode.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.waybillcode.bl.AWaybillcode;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.dax.WaybillcodeDemand;
import kyle.leis.fs.waybillcode.dax.WaybillcodeSeq;
import kyle.leis.hi.TfsWaybillcode;
import net.sf.hibernate.Session;

public class RegisterTransaction extends AbstractTransaction {
	private WaybillcodeColumns m_objWaybillcodeColumns;
	private AWaybillcode m_objWaybillcode;
	private String m_strOperId;
	private String m_strNewBcid;
	
	public String getNewBcid()
	{
		return this.m_strNewBcid;
	}
	
	public void setParam(WaybillcodeColumns objWaybillcodeColumns,
			AWaybillcode objWaybillcode,
			String strOperId) {
		m_objWaybillcodeColumns = objWaybillcodeColumns;
		m_objWaybillcode = objWaybillcode;
		m_strOperId = strOperId;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		if(m_objWaybillcodeColumns == null || StringUtility.isNull(m_strOperId)) return;
		
		TfsWaybillcode objTfsWaybillcode;
		if(StringUtility.isNull(m_objWaybillcodeColumns.getBcbcid()))
		{
			objTfsWaybillcode = new TfsWaybillcode();
			WaybillcodeSeq ws = new WaybillcodeSeq();
			objTfsWaybillcode.setBcId(Long.parseLong(ws.getNewSequencecode()));
			
			objTfsWaybillcode.setBcCreatedate(DateFormatUtility.getSysdate());
			objTfsWaybillcode.setTdiOperatorByOpIdCreator(TdiOperatorDC.loadByKey(m_strOperId));
			if (StringUtility.isNull(m_objWaybillcodeColumns.getBcbccurrentlabelcode()))
				objTfsWaybillcode.setBcCurrentlabelcode(m_objWaybillcodeColumns.getBcbcstartcode());
			else
				objTfsWaybillcode.setBcCurrentlabelcode(m_objWaybillcodeColumns.getBcbccurrentlabelcode());
		}
		else
			objTfsWaybillcode = (TfsWaybillcode)objSession.load(TfsWaybillcode.class, Long.valueOf(m_objWaybillcodeColumns.getBcbcid()));
		
		WaybillcodeDemand.setWaybillcodeByCol(objTfsWaybillcode, 
				m_objWaybillcodeColumns, 
				m_objWaybillcode, 
				m_strOperId);
		objSession.save(objTfsWaybillcode);
		this.m_strNewBcid = String.valueOf(objTfsWaybillcode.getBcId());
	}

}
