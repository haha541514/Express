package kyle.leis.es.price.adjustiveprice.tp;


import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueColumns;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceValueDemand;
import kyle.leis.hi.TepAdjustivepricevalue;
import net.sf.hibernate.Session;

public class SaveAdjustivepricevalueTrans extends AbstractTransaction {

	private String m_strNewEpCode;
	private String m_strEpCode;
	private List<AdjustivepricevalueColumns> m_listAdjustivepricevalueCol;
	
	public String getNewEpCode()
	{
		return this.m_strNewEpCode;
	}
	public void setParam(String strEpCode, List<AdjustivepricevalueColumns> listAdjustivepricevalueCol)
	{
		this.m_strEpCode = strEpCode;
		this.m_listAdjustivepricevalueCol = listAdjustivepricevalueCol;
	}
	
	public void transaction(Session objSession) throws Exception {
		TepAdjustivepricevalue objTepAdjustivepricevalue = null;
		AdjustivepricevalueColumns objAdjustivepricevalueCol = null;
		//先删除原来的价格值
		execute(objSession,"delete from t_ep_adjustivepricevalue apv where apv.ep_code = " + m_strEpCode);
		if(!CollectionUtility.isNull(m_listAdjustivepricevalueCol))
		for(int i=0;i<m_listAdjustivepricevalueCol.size();i++)
		{
			objTepAdjustivepricevalue = new TepAdjustivepricevalue();
			objAdjustivepricevalueCol = m_listAdjustivepricevalueCol.get(i);
			objAdjustivepricevalueCol.setApvcomp_idapvid(i);
			objAdjustivepricevalueCol.setApvcomp_idepcode(Long.valueOf(m_strEpCode));
			AdjustivePriceValueDemand.setAdjustivepricevalueByColumns(objTepAdjustivepricevalue, objAdjustivepricevalueCol);
			objSession.save(objTepAdjustivepricevalue);
		}
		this.m_strNewEpCode = String.valueOf(objTepAdjustivepricevalue.getComp_id().getEpCode());
	}

}
