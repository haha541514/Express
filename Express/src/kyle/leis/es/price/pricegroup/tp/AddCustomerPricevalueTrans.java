package kyle.leis.es.price.pricegroup.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupvalueColumns;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDemand;
import kyle.leis.hi.TepCustomerpricegroup;
import kyle.leis.hi.TepCustomerpricegroupvalue;
import kyle.leis.hi.TepCustomerpricegroupvaluePK;

public class AddCustomerPricevalueTrans extends AbstractTransaction {
    private List m_listCPGValueColumns;
	private TepCustomerpricegroup m_objTCustomerpricegroup;
	
    public void setParam(List listCPGValueColumns, 
    		TepCustomerpricegroup objTCustomerpricegroup) {
    	m_listCPGValueColumns = listCPGValueColumns;
    	m_objTCustomerpricegroup = objTCustomerpricegroup;
    }
	
	public void transaction(Session objSession) throws Exception {
		// 先删除后新增
		objSession.delete(" from TepCustomerpricegroupvalue as cpgv where cpgv.comp_id.epCode = " + 
				String.valueOf(m_objTCustomerpricegroup.getEpCode()));
		if (m_listCPGValueColumns == null || m_listCPGValueColumns.size() < 1)
			return;
		
		for (int i = 0; i < m_listCPGValueColumns.size(); i++) {
			CustomerpricegroupvalueColumns objCPGValueColumns = (CustomerpricegroupvalueColumns)m_listCPGValueColumns.get(i);
			TepCustomerpricegroupvalue objTCPGValue = new TepCustomerpricegroupvalue();
			TepCustomerpricegroupvaluePK objCPGVPK = new TepCustomerpricegroupvaluePK();
			// 设置主键
			objCPGVPK.setEpCode(m_objTCustomerpricegroup.getEpCode());
			objCPGVPK.setFkCode(objCPGValueColumns.getFkfkcode());
			objCPGVPK.setPgCode(objCPGValueColumns.getPgpgcode());
			objTCPGValue.setComp_id(objCPGVPK);
			// 设置相关属性
			CustomerPricegroupDemand.setCPGroupvalueByColumns(objTCPGValue, 
					objCPGValueColumns, 
					objSession);
			objTCPGValue.setTepCustomerpricegroup(m_objTCustomerpricegroup);
			// 保存
			objSession.save(objTCPGValue);
		}
	}
	
}
