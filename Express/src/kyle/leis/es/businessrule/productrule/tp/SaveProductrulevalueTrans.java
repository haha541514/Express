package kyle.leis.es.businessrule.productrule.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.productrule.da.ChannelproductruleColumns;
import kyle.leis.es.businessrule.productrule.da.CorporationproductruleColumns;
import kyle.leis.es.businessrule.productrule.da.DistrictproductruleColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCustomerDC;
import kyle.leis.hi.TbrChannelproductrule;
import kyle.leis.hi.TbrChannelproductrulePK;
import kyle.leis.hi.TbrCussignoutbyoriginwb;
import kyle.leis.hi.TbrCussignoutbyoriginwbPK;
import kyle.leis.hi.TbrDistrictproductrule;
import kyle.leis.hi.TbrDistrictproductrulePK;
import kyle.leis.hi.TbrProductrule;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TdiDistrict;

public class SaveProductrulevalueTrans extends AbstractTransaction {
	private List m_listChnPRColumns;
	private List m_listDistrictPRColumns;
	private List m_listCorporationPRColumns;
	private TbrProductrule m_objTbrProductrule;
	
	public void setParam(List listChnPRColumns,
			List listDistrictPRColumns,
			List listCorporationPRColumns,
			TbrProductrule objTbrProductrule) {
		m_listChnPRColumns = listChnPRColumns;
		m_listDistrictPRColumns = listDistrictPRColumns;
		m_listCorporationPRColumns = listCorporationPRColumns;
		m_objTbrProductrule = objTbrProductrule;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objTbrProductrule == null || m_objTbrProductrule.getBrId() == null) 
			return;
		// 先删除
		objSession.delete(" from TbrChannelproductrule as cpr where cpr.comp_id.brId = " 
				+ m_objTbrProductrule.getBrId());
		objSession.delete(" from TbrDistrictproductrule as dpr where dpr.comp_id.brId = " 
				+ m_objTbrProductrule.getBrId());		
		objSession.delete(" from TbrCussignoutbyoriginwb as cswb where cswb.comp_id.brId = "
				+ m_objTbrProductrule.getBrId());
		// 新增渠道规则
		if (m_listChnPRColumns != null && m_listChnPRColumns.size() > 0)
			for (int i = 0; i < m_listChnPRColumns.size(); i++) {
				ChannelproductruleColumns objCPRColumns = (ChannelproductruleColumns)m_listChnPRColumns.get(i);
				TbrChannelproductrule objTCPRule = new TbrChannelproductrule();
				
				TbrChannelproductrulePK objTCPPK = new TbrChannelproductrulePK();
				objTCPPK.setBrId(m_objTbrProductrule.getBrId());
				objTCPPK.setCprId(i);
				objTCPRule.setComp_id(objTCPPK);
				objTCPRule.setTbrProductrule(m_objTbrProductrule);
				// 设置渠道
				if (!StringUtility.isNull(objCPRColumns.getChnchncode())) {
					TchnChannel objTchnChannel = (TchnChannel)objSession.load(TchnChannel.class, 
							objCPRColumns.getChnchncode());
					objTCPRule.setTchnChannel(objTchnChannel);
				}
				if (!StringUtility.isNull(objCPRColumns.getCprznid()))
					objTCPRule.setZnId(Long.parseLong(objCPRColumns.getCprznid()));
				if (!StringUtility.isNull(objCPRColumns.getCprznvid()))
					objTCPRule.setZnvId(Integer.parseInt(objCPRColumns.getCprznvid()));
				objSession.save(objTCPRule);
			}
		// 新增起运地
		if (m_listDistrictPRColumns != null && m_listDistrictPRColumns.size() > 0)
			for (int i = 0; i < m_listDistrictPRColumns.size(); i++) {
				DistrictproductruleColumns objDPRColumns = (DistrictproductruleColumns)m_listDistrictPRColumns.get(i);
				TbrDistrictproductrule objTDPRule = new TbrDistrictproductrule();
				
				TbrDistrictproductrulePK objTDPRPK = new TbrDistrictproductrulePK();
				objTDPRPK.setBrId(m_objTbrProductrule.getBrId());
				objTDPRPK.setDtCode(objDPRColumns.getDtdtcode());
				objTDPRule.setComp_id(objTDPRPK);
				objTDPRule.setTbrProductrule(m_objTbrProductrule);

				if (!StringUtility.isNull(objDPRColumns.getDtdtcode())) {
					TdiDistrict objTdiDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
							objDPRColumns.getDtdtcode());
					objTDPRule.setTdiDistrict(objTdiDistrict);
				}
				objSession.save(objTDPRule);
			}
		//
		if(m_listCorporationPRColumns!= null && m_listCorporationPRColumns.size() > 0)
			for(int i = 0; i<m_listCorporationPRColumns.size();i++){
				CorporationproductruleColumns objCPRColumns = (CorporationproductruleColumns) m_listCorporationPRColumns.get(i);
				TbrCussignoutbyoriginwb  objTCPRule = new TbrCussignoutbyoriginwb ();
				TbrCussignoutbyoriginwbPK  objTCPRulePK  = new TbrCussignoutbyoriginwbPK ();
				
				objTCPRulePK.setBrId(m_objTbrProductrule.getBrId());
				objTCPRulePK.setCoCode(objCPRColumns.getCococode());
				objTCPRule.setComp_id(objTCPRulePK);
				objTCPRule.setTbrProductrule(m_objTbrProductrule);

				if(!StringUtility.isNull(objCPRColumns.getCococode())){
					TcoCustomer objTcoCustomer = TcoCustomerDC.loadByKey(objCPRColumns.getCococode());
					objTCPRule.setTcoCustomer(objTcoCustomer);
				}
				objSession.save(objTCPRule);
			}
	}

}
