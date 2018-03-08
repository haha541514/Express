package kyle.leis.eo.operation.cwbimportlog.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportlogColumns;
import kyle.leis.eo.operation.cwbimportlog.dax.CwbimportlogDemand;
import kyle.leis.hi.TopCwbimportlog;

public class ModifycwbimportlogTrans extends AbstractTransaction {
	private CwbimportlogColumns m_Cwbimportlog;
	private Long m_cwlId;
	private Long m_opId;
	
	public void setParam(CwbimportlogColumns Cwbimportlog,Long opId){
		if(Cwbimportlog==null)
			return;
		m_Cwbimportlog=Cwbimportlog;
		m_opId=opId;
	}
	public Long getCwlId(){
		return m_cwlId;
	}
	public void transaction(Session objSession) throws Exception {
		String updateSQL = "update t_op_cwbimportlog set CWL_UNSUCCESSFULRECORDS ="+m_Cwbimportlog.getToccwlunsuccessfulrecords()
				+", CWL_REMARK ='"+m_Cwbimportlog.getToccwlremark()+"', CWL_STATUS = 'C' where CWL_ID ="+ m_Cwbimportlog.getToccwlid();
		execute(objSession, updateSQL);
	}
	

}
