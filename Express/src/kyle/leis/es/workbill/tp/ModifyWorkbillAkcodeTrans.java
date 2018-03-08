package kyle.leis.es.workbill.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import net.sf.hibernate.Session;

public class ModifyWorkbillAkcodeTrans extends AbstractTransaction{
	private String m_akCode;
	private String m_wbId;
	public void setParam(String akCode,String wbId){
		this.m_akCode=akCode;
		this.m_wbId=wbId;
	}
	//更改工作单状态
	public void transaction(Session objSession) throws Exception {
			if(m_akCode.equals("QU")||m_akCode.equals("HE")||m_akCode.equals("CL")||m_akCode.equals("RE")){
				String sql="";
				if(m_akCode.equals("HE")){
					
					sql="update t_es_workbill set wbs_code='"+m_akCode+"', wb_actualenddate=to_date('"+DateFormatUtility.getStandardSysdate()+"','yyyy-mm-dd hh24:mi:ss') where wb_id="+m_wbId;
				}else if(m_akCode.equals("RE")){
					sql="update t_es_workbill set wbs_code='DI' where wb_id="+m_wbId;
				}else{
					sql="update t_es_workbill set wbs_code='"+m_akCode+"' where wb_id="+m_wbId;
				}
				execute(objSession,sql);
			}	
		}
}
