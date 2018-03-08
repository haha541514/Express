package kyle.leis.eo.billing.incidentalfee.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeColumns;
import kyle.leis.eo.billing.incidentalfee.dax.IncidentalfeeDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TblIncidentalfee;
import kyle.leis.hi.TdiOperator;
import net.sf.hibernate.Session;

public class SaveIncidentalFeeTransaction extends AbstractTransaction {

	private String m_strOperId;
	private IncidentalfeeColumns m_objIncidentalfeeCol;
	private String m_strNewIfId;
	public String getNewIfId()
	{
		return this.m_strNewIfId;
	}
	
	public void setParam(IncidentalfeeColumns objIncidentalfeeCol,String strOperId)
	{
		this.m_objIncidentalfeeCol = objIncidentalfeeCol;
		this.m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_objIncidentalfeeCol == null) return;
		
		TblIncidentalfee objTblIncidentalfee = null;
		if(!StringUtility.isNull(m_objIncidentalfeeCol.getIfifid()))
		{
			//修改
			objTblIncidentalfee = (TblIncidentalfee)objSession.load(TblIncidentalfee.class, Long.valueOf(m_objIncidentalfeeCol.getIfifid()));
			if(m_objIncidentalfeeCol.getFsfscode().equals("B"))
				objTblIncidentalfee.setIfIdReference(Long.valueOf(m_objIncidentalfeeCol.getIfifid()));//指向原来的费用值
		}
		else
		{
			objTblIncidentalfee =  new TblIncidentalfee();
			//测试id
			//objTblIncidentalfee.setIfId(2l);
			objTblIncidentalfee.setIfCreatedate(DateFormatUtility.getSysdate());
			objTblIncidentalfee.setTdiOperatorByOpIdCreate((TdiOperator)TdiOperatorDC.loadByKey(m_strOperId));
		}
		
		IncidentalfeeDemand.setIncidentalfeeByColumns(objTblIncidentalfee, m_objIncidentalfeeCol, m_strOperId, objSession);
		objSession.save(objTblIncidentalfee);
		
		this.m_strNewIfId = String.valueOf(objTblIncidentalfee.getIfId());
	}

}
