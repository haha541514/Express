package kyle.leis.eo.operation.transportwaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillColumns;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TopTransportwaybill;

public class SaveTransportTrans extends AbstractTransaction {
	private TransportwaybillColumns m_objTWColumns;
	private String[] m_astrBwcode;
	private String m_strOperId;
	private String m_strNewTwbId;
	private String[] m_astrBaglabelcode;
	private String[] m_astrCwcode;
	
	public void setParam(TransportwaybillColumns objTWColumns,
			String[] astrBwcode,
			String strOperId,
			String[] astrBaglabelcode,
			String[] astrCwcode) {
		m_objTWColumns = objTWColumns;
		m_astrBwcode = astrBwcode;
		m_strOperId = strOperId;
		m_astrBaglabelcode = astrBaglabelcode;
		m_astrCwcode = astrCwcode;
	}
	
	public String getNewTwbId() {
		return m_strNewTwbId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objTWColumns == null) return;
		TopTransportwaybill objTTransportwaybill = null;
		
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		if (StringUtility.isNull(m_objTWColumns.getTwbtwbid())) {
			objTTransportwaybill = new TopTransportwaybill();
			objTTransportwaybill.setTdiOperatorByOpIdCreator(objTdiOperator);
			objTTransportwaybill.setTwbCreatedate(DateFormatUtility.getSysdate());
		} else {
			objTTransportwaybill = (TopTransportwaybill)objSession.load(TopTransportwaybill.class, 
					Long.parseLong(m_objTWColumns.getTwbtwbid()));
		}
		objTTransportwaybill.setTwbModifydate(DateFormatUtility.getSysdate());
		objTTransportwaybill.setTdiOperatorByOpIdModifier(objTdiOperator);
		// 设置其他属性
		TransportWaybillDemand.setTransportByColumns(objTTransportwaybill, 
				m_objTWColumns, 
				m_strOperId, 
				objSession);
		objSession.save(objTTransportwaybill);
		// 保存值
		AddTransportValueTrans objAddTVT = new AddTransportValueTrans();
		
		objAddTVT.setParam(objTTransportwaybill, m_astrBwcode, 
				m_astrBaglabelcode,
				m_astrCwcode);
		objAddTVT.transaction(objSession);
		m_strNewTwbId = String.valueOf(objTTransportwaybill.getTwbId());
	}
}
