package kyle.leis.eo.customerservice.track.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteSimpleTrackTransaction extends AbstractTransaction{
    private String swb_code;
    private String swbt_ids;
    public void setParam(String swb_code, 
			String swbt_ids){
       this.swb_code=swb_code;
       this.swbt_ids=swbt_ids;
    }
	public void transaction(Session objSession) throws Exception {
		if(!StringUtility.isNull(swb_code)){
			execute(objSession, "delete from T_CS_SimpleWayBillTrack swbt where swbt.swb_code = " + swb_code);
		}else{
			execute(objSession, "delete from T_CS_SimpleWayBillTrack swbt where swbt.swbt_id in("+ swbt_ids+")");
		}
		
	}

}
