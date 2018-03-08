package kyle.leis.fs.dictionary.waybillcodekind.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindColumns;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindCondition;
import kyle.leis.fs.dictionary.waybillcodekind.dax.WaybillCKDemand;


public class DeleteWaybillCKTrans extends AbstractTransaction{

	private String m_strBckcode;
	private WaybillcodekindColumns m_WaybillcodekindColumns;

	public void setParam(String strBckcode) throws Exception {
		this.m_strBckcode = strBckcode;
		}
	public WaybillcodekindColumns getWaybillcodekind(){
		return m_WaybillcodekindColumns;
	}
	public void transaction(Session objSession) throws Exception {			
		List list = objSession.find(" from TchnChannel as chn where chn.tdiWaybillcodekindByChnMainbillcodekind.bckCode = '"+m_strBckcode+"' or chn.tdiWaybillcodekindByChnSubbillcodekind.bckCode = '"+m_strBckcode+"'");
		
		if(list.size()>0){
			//被引用，无法删除
			WaybillcodekindCondition objWBCKCondition = new WaybillcodekindCondition();
			objWBCKCondition.setBckcode(m_strBckcode);
			List objList = WaybillCKDemand.query(objWBCKCondition);
			
			this.m_WaybillcodekindColumns = (WaybillcodekindColumns) objList.get(0);
		}else{
			String strDeleteSql = "delete from t_di_waybillcodekind wbck where wbck.bck_code = '" 
				+ m_strBckcode +"'";
			execute(objSession, strDeleteSql);
			
			this.m_WaybillcodekindColumns = null;
		}
	}

}
