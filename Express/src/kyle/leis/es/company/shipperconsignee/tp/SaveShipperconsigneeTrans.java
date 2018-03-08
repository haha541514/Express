package kyle.leis.es.company.shipperconsignee.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCustomerDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TcoShipperconsigneeinfo;

public class SaveShipperconsigneeTrans extends AbstractTransaction {
	private ShipperconsigneeColumns m_objSCColumns;
	private String m_strSavedSCCode;
	
	public void setParam(ShipperconsigneeColumns objSCColumns) {
		m_objSCColumns = objSCColumns;
	}
	
	public String getSavedSCCode() {
		return m_strSavedSCCode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objSCColumns == null) return;
		String strSccode = m_objSCColumns.getScsccode();
		TcoShipperconsigneeinfo objTSCInfo;
		// 新增或修改
		if (StringUtility.isNull(strSccode)) {
			objTSCInfo = new TcoShipperconsigneeinfo();
			objTSCInfo.setScCode(ShipperconsigneeDemand.getNewSCcode());
		} else {
			objTSCInfo = (TcoShipperconsigneeinfo)objSession.load(TcoShipperconsigneeinfo.class, 
					strSccode);
		}
		objTSCInfo.setScAddress1(m_objSCColumns.getScscaddress1());
		objTSCInfo.setScAddress2(m_objSCColumns.getScscaddress2());
		objTSCInfo.setScAddress3(m_objSCColumns.getScscaddress3());
		objTSCInfo.setScCitycode(m_objSCColumns.getScsccitycode());
		objTSCInfo.setScCompanyname(m_objSCColumns.getScsccompanyname());
		objTSCInfo.setScFax(m_objSCColumns.getScscfax());
		objTSCInfo.setScLabelcode(m_objSCColumns.getScsclabelcode());
		objTSCInfo.setScName(m_objSCColumns.getScscname());
		objTSCInfo.setScPostcode(m_objSCColumns.getScscpostcode());
		objTSCInfo.setScTelephone(m_objSCColumns.getScsctelephone());
		objTSCInfo.setScShipperconsigneetype(m_objSCColumns.getScscshipperconsigneetype());
		
		if (!StringUtility.isNull(m_objSCColumns.getChnchncode())) {
			TchnChannel objTCHN = (TchnChannel)objSession.load(TchnChannel.class, 
					m_objSCColumns.getChnchncode());
			objTSCInfo.setTchnChannel(objTCHN);
		}
		if (!StringUtility.isNull(m_objSCColumns.getCmcocode())) {
			TcoCustomer objTcoCustomer = TcoCustomerDC.loadByKey(m_objSCColumns.getCmcocode());
			objTSCInfo.setTcoCustomer(objTcoCustomer);
		}		
		objSession.save(objTSCInfo);
		m_strSavedSCCode = objTSCInfo.getScCode();
	}

}
