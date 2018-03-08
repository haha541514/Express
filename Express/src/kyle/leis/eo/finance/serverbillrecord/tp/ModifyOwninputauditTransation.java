package kyle.leis.eo.finance.serverbillrecord.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class ModifyOwninputauditTransation extends AbstractTransaction {
	private String cwCode;
	private String ownInputAudit;
	public void setParam(String cwCode,String owninputaudit) {
		this.cwCode = cwCode;
		this.ownInputAudit=owninputaudit;
	}

	
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(cwCode)||StringUtility.isNull(ownInputAudit)) return;
		//更改自制单客户计费重量为已审核状态
		String strUpdateSql="";
		if(ownInputAudit.equals("Y")){
			 strUpdateSql = "update t_op_housewaybill set HW_OWNINPUTCWAUDITSIGN='Y' where cw_code="+cwCode;
				
		}else if(ownInputAudit.equals("N")){
			 strUpdateSql = "update t_op_housewaybill set HW_OWNINPUTCWAUDITSIGN='N' where cw_code="+cwCode;
		}
		execute(objSession, strUpdateSql);	
	}
	
}
