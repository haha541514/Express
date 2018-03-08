package kyle.leis.es.price.incidentalprice.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.price.incidentalprice.da.IncidentalpricevalueColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalstoragechannelColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalvaluebaseColumns;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDemand;
import kyle.leis.hi.TepIncidentalprice;
import kyle.leis.hi.TepIncidentalpricevalue;
import kyle.leis.hi.TepIncidentalpricevaluePK;
import kyle.leis.hi.TepIncidentalstorgechannel;
import kyle.leis.hi.TepIncidentalstorgechannelPK;
import kyle.leis.hi.TepIncidentalvaluebase;
import kyle.leis.hi.TepIncidentalvaluebasePK;

public class AddIncidentalPriceValueTrans extends AbstractTransaction {
	private List m_listIPValueColumns;
	private List m_listIPVBaseColumns;
	private List m_listIPVChannelColumns;
	private TepIncidentalprice m_objTepIncidentalprice;
	
	public void setParam(List listIPValueColumns,
			List listIPVBaseColumns,
			List listIPVChannelColumns,
			TepIncidentalprice objTepIncidentalprice) {
		m_listIPValueColumns = listIPValueColumns;
		m_listIPVBaseColumns = listIPVBaseColumns;
		m_objTepIncidentalprice = objTepIncidentalprice;
		m_listIPVChannelColumns = listIPVChannelColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		// 先删除后新增
		objSession.delete(" from TepIncidentalvaluebase as ivb where ivb.comp_id.epCode = " + 
				String.valueOf(m_objTepIncidentalprice.getEpCode()));
		objSession.delete(" from TepIncidentalstorgechannel as isc where isc.comp_id.epCode = " + 
				String.valueOf(m_objTepIncidentalprice.getEpCode()));		
		objSession.delete(" from TepIncidentalpricevalue as iv where iv.comp_id.epCode = " + 
				String.valueOf(m_objTepIncidentalprice.getEpCode()));
		
		if (m_listIPValueColumns != null && m_listIPValueColumns.size() > 0) {
			for (int i = 0; i < m_listIPValueColumns.size(); i++) {
				IncidentalpricevalueColumns objIPVColumns = (IncidentalpricevalueColumns)m_listIPValueColumns.get(i);
				TepIncidentalpricevalue objTIPValue = new TepIncidentalpricevalue();
				TepIncidentalpricevaluePK objTIPVPK = new TepIncidentalpricevaluePK();
				
				objTIPVPK.setEpCode(m_objTepIncidentalprice.getEpCode());
				objTIPVPK.setIpvId(Integer.parseInt(objIPVColumns.getIpvcomp_idipvid()));
				objTIPValue.setComp_id(objTIPVPK);
				IncidentalPriceDemand.setIncidentalValueColumns(objTIPValue, 
						objIPVColumns, 
						objSession);
				objTIPValue.setTepIncidentalprice(m_objTepIncidentalprice);
				objSession.save(objTIPValue);
				// 获得基费
				List<IncidentalvaluebaseColumns> listIVBColumns = IncidentalPriceDemand.getIvalueBaseByColumns(m_listIPVBaseColumns, 
						objIPVColumns.getIpvcomp_idipvid());
				if (listIVBColumns != null && listIVBColumns.size() > 0)
					for (int j = 0; j < listIVBColumns.size(); j++) {
						IncidentalvaluebaseColumns objIVBColumns = (IncidentalvaluebaseColumns)listIVBColumns.get(j);
						TepIncidentalvaluebase objTIVB = new TepIncidentalvaluebase();
						TepIncidentalvaluebasePK objTIVBPK = new TepIncidentalvaluebasePK();
						
						objTIVBPK.setEpCode(m_objTepIncidentalprice.getEpCode());
						objTIVBPK.setFkCode(objIVBColumns.getFkfkcode());
						objTIVBPK.setIpvId(Integer.parseInt(objIVBColumns.getIvbcomp_idipvid()));
						objTIVB.setComp_id(objTIVBPK);
						
						IncidentalPriceDemand.setIvaluebaseByColumns(objTIVB, 
								objIVBColumns, 
								objSession);
						objTIVB.setTepIncidentalpricevalue(objTIPValue);
						objSession.save(objTIVB);
					}
				
				// 保存存储渠道
				List<IncidentalstoragechannelColumns> listISCColumns = IncidentalPriceDemand.getIvalueChannelByColumns(m_listIPVChannelColumns, 
						objIPVColumns.getIpvcomp_idipvid());
				if (listISCColumns != null && listISCColumns.size() > 0)
					for (int j = 0; j < listISCColumns.size(); j++) {
						IncidentalstoragechannelColumns objISCColumns = (IncidentalstoragechannelColumns)listISCColumns.get(j);
						TepIncidentalstorgechannel objTISChannel = new TepIncidentalstorgechannel();
						TepIncidentalstorgechannelPK objTISCPK = new TepIncidentalstorgechannelPK();
						
						objTISCPK.setEpCode(m_objTepIncidentalprice.getEpCode());
						objTISCPK.setChnCode(objISCColumns.getChnchncode());
						objTISCPK.setIpvId(Integer.parseInt(objISCColumns.getIsccomp_idipvid()));
						objTISChannel.setComp_id(objTISCPK);
						
						IncidentalPriceDemand.setIvalueChannelByColumns(objTISChannel, 
								objISCColumns, 
								objSession);
						objTISChannel.setTepIncidentalpricevalue(objTIPValue);
						objSession.save(objTISChannel);
					}				
			}
		}
	}
}
