package kyle.fetcher.track.tp;

import java.util.Hashtable;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.leis.eo.customerservice.track.tp.SaveBatchTrackTransaction;

/**
 *
 * User: Kyle
 * Date: 2009-2-9
 * Time: 16:07:52
 */
public class SavePODTransaction extends AbstractTransaction {
	private String m_strCwcode;
	private String m_strSignStatus;
	private String m_strSignforUser;
	private String m_strSignforDate;
	
	public void setPOD(FRecord objFRecordSignFor, 
			Hashtable htParameter) {
		m_strSignStatus = objFRecordSignFor.getFieldValue(ITrackBasic.FTF_SIGN_STATUS).trim();
		m_strCwcode = ((String)htParameter.get(ITrackBasic.DEPOSITOR_BUSINESSCODE)).trim();
		
		System.out.println("Status:"+m_strSignStatus);
		//System.out.println("code:"+m_strCwcode);
		
		//签收状态必须为：签收（SF）、结案（CC）、退回（RE）三者之一，否则只能保存为空。
		if (m_strSignStatus.equals("SF") ||
				m_strSignStatus.equals("CC")||
				m_strSignStatus.equals("RE")) {
			m_strSignforDate = objFRecordSignFor.getFieldValue(ITrackBasic.FTF_SIGNFOR_DATE).trim();
			m_strSignforUser = objFRecordSignFor.getFieldValue(ITrackBasic.FTF_SIGNFOR_PERSON).trim();

		} else {
			m_strSignforDate = null;
			m_strSignforUser = null;
			m_strSignStatus = null;
		}
	}
	
	public void transaction(Session objSession) throws Exception {
		SaveBatchTrackTransaction objSBTTrans = new SaveBatchTrackTransaction();
		if (StringUtility.isNull(m_strSignStatus))
			return;
		objSBTTrans.setSignforParam(m_strCwcode,
				m_strSignforUser,
				m_strSignforDate,
				m_strSignStatus);
		objSBTTrans.transaction(objSession);
	}
}
