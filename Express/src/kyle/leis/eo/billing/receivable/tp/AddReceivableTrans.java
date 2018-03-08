package kyle.leis.eo.billing.receivable.tp;

import java.math.BigDecimal;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TblReceivable;
import kyle.leis.hi.TdiOperator;

public class AddReceivableTrans extends AbstractTransaction  {
	private ReceivableColumns m_objRvColumns;
	private String m_strOperId;
	private Long m_lRvid;
	private boolean m_isUseSysdateForCreateDate = false;
	
	public void setParam(ReceivableColumns objRvColumns, 
			String strOperId) {
		m_objRvColumns = objRvColumns;
		m_strOperId = strOperId;
	}
	
	public void setParam(ReceivableColumns objRvColumns, 
			String strOperId,
			boolean isUseSysdateForCreateDate) {
		m_objRvColumns = objRvColumns;
		m_strOperId = strOperId;
		m_isUseSysdateForCreateDate = isUseSysdateForCreateDate;
	}	
	
	
	public Long getNewRvid() {
		return m_lRvid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objRvColumns == null) return;
		
		TblReceivable objTblReceivable = new TblReceivable();
		ReceivableDemand.setReceivalbeFromColumns(objTblReceivable, 
				m_objRvColumns, 
				m_strOperId, 
				objSession);
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
			objTblReceivable.setTdiOperatorByRvOpIdCreator(objTdiOperator);
			if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("WC"))			
				objTblReceivable.setRvCreatedate(objTblReceivable.getRvOccurdate());
			else
				objTblReceivable.setRvCreatedate(DateFormatUtility.getSysdate());
			
			objTblReceivable.setRvTotal(new BigDecimal(m_objRvColumns.getRvrvactualtotal()));
		}
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("WC") &&
				m_isUseSysdateForCreateDate)
			objTblReceivable.setRvCreatedate(DateFormatUtility.getSysdate());
		objSession.save(objTblReceivable);
		m_lRvid = objTblReceivable.getRvId();
	}

}
