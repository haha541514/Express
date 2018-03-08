package kyle.leis.eo.operation.housewaybill.tp; 




import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import net.sf.hibernate.Session;

public class ModifyCoreWaybillStatusTrans extends AbstractTransaction {
	private String[] strCwcode;
	private String strCwscode;
	private  String strOperId;
	public void setCWStatusParam(String[] strCwcode,
			String strCwscode,
			String strOperId) throws Exception {
		if (strCwcode==null||strCwcode.length==0)
			return;	
		this.strCwcode=strCwcode;
		this.strCwscode=strCwscode;
		this.strOperId=strOperId;
	}
	public void transaction(Session objSession) throws Exception {
		String updatesql1="update T_OP_COREWAYBILL cw set cw.cws_code='"+strCwscode+"',cw.cw_op_id_modifier='"+strOperId+"' where cw_code in("+getCwcode(strCwcode)+")";
        String updatesql2="update  T_OP_HOUSEWAYBILL hw set hw.hw_customerdeclaredate=to_date('"+DateFormatUtility.getStandardSysdate()+"','yyyy-mm-dd hh24:mi:ss') where cw_code in("+getCwcode(strCwcode)+")";
        execute(objSession, updatesql1);
        execute(objSession, updatesql2);
	}
	public String getCwcode(String[] cwcodes){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<cwcodes.length;i++){
			if(i!=cwcodes.length-1){
				sb.append("'"+cwcodes[i]+"',");
			}else{
				sb.append("'"+cwcodes[i]+"'");
			}
		}
		return sb.toString();
	}
	
}
