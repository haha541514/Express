package kyle.leis.eo.operation.corewaybillcode.tp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybillcode.da.CorewaybillcodeColumns;
import kyle.leis.eo.operation.corewaybillcode.dax.CorewaybillcodeDemand;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopCorewaybillcode;
import kyle.leis.hi.TopCorewaybillcodePK;

public class SaveCorewaybillcodeTrans extends AbstractTransaction {

	private Set<String> m_ewbcodes;
	private String m_strCwcode;
	private TopCorewaybill m_objTCWB;
	
	public void setParam(List listCWBCodes,
			String strOriginCustomerEwbcode,
			String strMergeCustomerEwbcode, 
			String strCwcode) {
		Set<String> ewbcodes = new HashSet<String>();
		if (listCWBCodes != null && listCWBCodes.size() > 0) {
			for (int i = 0; i < listCWBCodes.size(); i++) {
				CorewaybillcodeColumns objCWBCC = (CorewaybillcodeColumns)listCWBCodes.get(i);
				if (!objCWBCC.getCwbccwbccustomerewbcode().equals(strOriginCustomerEwbcode))
					ewbcodes.add(objCWBCC.getCwbccwbccustomerewbcode());
			}
		}
		if (!strOriginCustomerEwbcode.equals(strMergeCustomerEwbcode))
			ewbcodes.add(strMergeCustomerEwbcode);
		
		setParam(strCwcode, ewbcodes);
	}
	
	public void setParam(List listCWBCodes,
			String strCwcode) {
		Set<String> ewbcodes = new HashSet<String>();
		if (listCWBCodes != null && listCWBCodes.size() > 0) {
			for (int i = 0; i < listCWBCodes.size(); i++) {
				CorewaybillcodeColumns objCWBCC = (CorewaybillcodeColumns)listCWBCodes.get(i);
				ewbcodes.add(objCWBCC.getCwbccwbccustomerewbcode());
			}
		}
		setParam(strCwcode, ewbcodes);
	}	
	
	public void setParam(String strCwcode, String strCustomerEwbcode) {
		if (StringUtility.isNull(strCustomerEwbcode))
			return;
		String[] astr = strCustomerEwbcode.split(",");
		setParam(strCwcode, astr);
	}
	
	public void setParam(TopCorewaybill objTCWB, String strCustomerEwbcode) {
		if (StringUtility.isNull(strCustomerEwbcode))
			return;
		String[] astr = strCustomerEwbcode.split(",");
		setParam(objTCWB, astr);
	}	
	
	public void setParam(String strCwcode, String[] astr) {
		if (astr == null || astr.length < 1)
			return;
		Set<String> ewbcodes = new HashSet<String>();
		for (int i = 0; i < astr.length; i++) {
			ewbcodes.add(astr[i]);
		}
		setParam(strCwcode, ewbcodes);
	}
	
	public void setParam(TopCorewaybill objTCWB, String[] astr) {
		if (astr == null || astr.length < 1)
			return;
		Set<String> ewbcodes = new HashSet<String>();
		for (int i = 0; i < astr.length; i++) {
			ewbcodes.add(astr[i]);
		}
		setParam(objTCWB, ewbcodes);
	}	
	
	public void setParam(String strCwcode, Set<String> ewbcodes) {
		m_strCwcode = strCwcode;
		m_ewbcodes = ewbcodes;
	}
	
	public void setParam(TopCorewaybill objTCWB, Set<String> ewbcodes) {
		m_objTCWB = objTCWB;
		m_ewbcodes = ewbcodes;
	}	
	
	public void transaction(Session objSession) throws Exception {
		if (m_ewbcodes == null || m_ewbcodes.size() < 1)
			return;
		if (StringUtility.isNull(m_strCwcode))
			m_strCwcode = String.valueOf(m_objTCWB.getCwCode());
		List listResults = null;
		if (!StringUtility.isNull(m_strCwcode))
			listResults = CorewaybillcodeDemand.load(m_strCwcode);
		int iOriginTotal = 0;
		if (listResults != null && listResults.size() > 0)
			iOriginTotal = listResults.size();
		
		if (m_objTCWB == null)
			m_objTCWB = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
					Long.parseLong(m_strCwcode));
		int i = 1;
		
		for (String strCustomerEwbcode : m_ewbcodes) {
			if (StringUtility.isNull(strCustomerEwbcode))
				continue;
			if (strCustomerEwbcode.equals(m_objTCWB.getCwCustomerewbcode()))
				continue;
			if (CorewaybillcodeDemand.isExistsCustomerewbcode(strCustomerEwbcode, 
					listResults))
				continue;
			TopCorewaybillcodePK objTCWBCPK = new TopCorewaybillcodePK();
			objTCWBCPK.setCwbcId(iOriginTotal + i);
			i++;
			objTCWBCPK.setCwCode(m_objTCWB.getCwCode());
			
			TopCorewaybillcode objTCBC = new TopCorewaybillcode();
			objTCBC.setComp_id(objTCWBCPK);
			objTCBC.setCwbcCustomerewbcode(strCustomerEwbcode);
			objTCBC.setTopCorewaybill(m_objTCWB);
			
			objSession.save(objTCBC);
		}
	}

}
