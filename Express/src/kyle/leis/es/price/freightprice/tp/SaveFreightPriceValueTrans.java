package kyle.leis.es.price.freightprice.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.price.freightprice.da.FreightpricevalueColumns;
import kyle.leis.es.price.freightprice.da.SurchargevalueColumns;
import kyle.leis.es.price.freightprice.da.SurchargevaluebaseColumns;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.hi.TepFreightprice;
import kyle.leis.hi.TepFreightpricevalue;
import kyle.leis.hi.TepFreightpricevaluePK;
import kyle.leis.hi.TepSurchargevalue;
import kyle.leis.hi.TepSurchargevaluePK;
import kyle.leis.hi.TepSurchargevaluebase;
import kyle.leis.hi.TepSurchargevaluebasePK;

public class SaveFreightPriceValueTrans extends AbstractTransaction {
    private List m_listFreightpricevalue;
    private List m_listSurchargevalue;
    private List m_listSurchargevaluebase;
    private TepFreightprice m_objTepFreightprice;
    
	public void setParam(List listFreightpricevalue, 
			List listSurchargevalue, 
			List listSurchargevaluebase, 
			TepFreightprice objTepFreightprice) {
		m_listFreightpricevalue = listFreightpricevalue;
		m_listSurchargevalue = listSurchargevalue;
		m_listSurchargevaluebase = listSurchargevaluebase;
		m_objTepFreightprice = objTepFreightprice;
	}
	
	public void transaction(Session objSession) throws Exception {
		// 先删除原费用
		/*
		objSession.delete(" from TepFreightpricevalue as fpv where fpv.comp_id.epCode = " + 
				String.valueOf(m_objTepFreightprice.getEpCode()));
		objSession.delete(" from TepSurchargevaluebase as svb where svb.comp_id.epCode = " + 
				String.valueOf(m_objTepFreightprice.getEpCode()));
		objSession.delete(" from TepSurchargevalue as sv where sv.comp_id.epCode = " + 
				String.valueOf(m_objTepFreightprice.getEpCode()));
		*/
		// 删除运费
		execute(objSession, "delete from t_ep_freightpricevalue fpv " + 
				" WHERE fpv.ep_code = " + String.valueOf(m_objTepFreightprice.getEpCode()));	
		// 删除附加费基值
		execute(objSession, "delete from t_ep_surchargevaluebase svb " + 
				" WHERE svb.ep_code = " + String.valueOf(m_objTepFreightprice.getEpCode()));
		// 删除附加费
		execute(objSession, "delete from t_ep_surchargevalue sv " + 
				" WHERE sv.ep_code = " + String.valueOf(m_objTepFreightprice.getEpCode()));
		// 新增运费价格值
		if (m_listFreightpricevalue != null && m_listFreightpricevalue.size() > 0)
			for (int i = 0; i < m_listFreightpricevalue.size(); i++) {
				FreightpricevalueColumns objFPVColumns = (FreightpricevalueColumns)m_listFreightpricevalue.get(i);
				TepFreightpricevalue objTFPV = new TepFreightpricevalue();
				
				FreightPriceDemand.setFreightValueByColumns(objTFPV, 
						objFPVColumns, 
						objSession);
				TepFreightpricevaluePK objFPVPK = new TepFreightpricevaluePK();
				objFPVPK.setEpCode(m_objTepFreightprice.getEpCode());
				objFPVPK.setFpvId(Integer.parseInt(objFPVColumns.getFpvcomp_idfpvid()));
				objTFPV.setComp_id(objFPVPK);
				objTFPV.setTepFreightprice(m_objTepFreightprice);
				objSession.save(objTFPV);
			}
		// 新增附加费价格值
		if (m_listSurchargevalue != null && m_listSurchargevalue.size() > 0)
			for (int i = 0; i < m_listSurchargevalue.size(); i++) {
				SurchargevalueColumns objSVColumns = (SurchargevalueColumns)m_listSurchargevalue.get(i);
				TepSurchargevalue objTepSurchargevalue = new TepSurchargevalue();
				FreightPriceDemand.setSurchargeValueByColumns(objTepSurchargevalue, 
						objSVColumns, 
						objSession);
				
				TepSurchargevaluePK objSVPK = new TepSurchargevaluePK();
				objSVPK.setEpCode(m_objTepFreightprice.getEpCode());
				objSVPK.setSvId(Integer.parseInt(objSVColumns.getSvcomp_idsvid()));
				objTepSurchargevalue.setComp_id(objSVPK);
				
				objTepSurchargevalue.setTepFreightprice(m_objTepFreightprice);
				objSession.save(objTepSurchargevalue);
				// 查找基值
				List<SurchargevaluebaseColumns> listSVBColumns = FreightPriceDemand.getSvbColumns(m_listSurchargevaluebase,
						objSVColumns.getSvcomp_idsvid());
				if (listSVBColumns != null && listSVBColumns.size() > 0)
					for (int j = 0; j < listSVBColumns.size(); j++) {
						TepSurchargevaluebase objTSVB = new TepSurchargevaluebase();
						SurchargevaluebaseColumns objSVBColumns = listSVBColumns.get(j);
						FreightPriceDemand.setSvaluebaseByColumns(objTSVB, 
								objSVBColumns, 
								objSession);
						objTSVB.setTepSurchargevalue(objTepSurchargevalue);
						
						TepSurchargevaluebasePK objSVBPK = new TepSurchargevaluebasePK();
						objSVBPK.setEpCode(objTepSurchargevalue.getComp_id().getEpCode());
						objSVBPK.setFkCode(objSVBColumns.getSvbcomp_idfkcode());
						objSVBPK.setSvId(Integer.parseInt(objSVColumns.getSvcomp_idsvid()));
						objTSVB.setComp_id(objSVBPK);
						
						objSession.save(objTSVB);
					}
			}
	}

}
