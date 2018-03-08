package kyle.leis.es.price.expressprice.tp;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.price.expressprice.da.PriceenterpriseColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.hi.TepEnterprise;
import kyle.leis.hi.TepEnterprisePK;
import kyle.leis.hi.TepExpressprice;

public class SaveEnterpriseTransaction extends AbstractTransaction {
	
	private List m_listPriceenterprise;
	private TepExpressprice m_objTepExpressprice;
	
	public void setParam(List listPriceenterprise,
			TepExpressprice objTepExpressprice) {
		
		m_listPriceenterprise = listPriceenterprise;
		m_objTepExpressprice = objTepExpressprice;		
		
	}
	
	public void transaction(Session objSession) throws Exception {
		// 删除价格支持的企业
		execute(objSession, "delete from T_EP_Enterprise ep " + 
				" WHERE ep.ep_code = " + String.valueOf(m_objTepExpressprice.getEpCode()));
		
		if (m_listPriceenterprise != null && m_listPriceenterprise.size() > 0) {
			for (int i = 0; i < m_listPriceenterprise.size(); i++) {
				PriceenterpriseColumns pe = (PriceenterpriseColumns)m_listPriceenterprise.get(i);
				
				String strEecode = pe.getPecomp_ideeecode();
				TepEnterprise objTepEnterprise = new TepEnterprise();
				
				TepEnterprisePK comp_id = new TepEnterprisePK();
				comp_id.setEeCode(strEecode);
				comp_id.setEpCode(m_objTepExpressprice.getEpCode());
				objTepEnterprise.setComp_id(comp_id);
				
				objTepEnterprise.setTdiEnterpriseelement(TdiEnterpriseelementDC.loadByKey(strEecode));
				objTepEnterprise.setTepExpressprice(m_objTepExpressprice);
				
				objSession.save(objTepEnterprise);
			}
		}
	}
}
