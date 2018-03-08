package wkq.tp;


import wkq.da.FeekindColumns;
import wkq.da.FeekindQuery;
import wkq.da.TdifeekindTR;
import net.sf.hibernate.Session;
import kyle.baiqian.hi.TdiSimplestatus;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.dbaccess.transaction.ITransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiFeekind;

public class DeleteFeekindhibernate extends AbstractTransaction{

	//private FeekindColumns m_objFeekindColumns;
	private String m_newFkcode;
	private TableAccess table;
	public void setFkcode(String code){
		this.m_newFkcode = code;		
	}
	/**ɾ��,ͨ��id
	 * 
	 * 
	 * **/
	public void transaction(Session objSession) throws Exception {
		//�ж��Ƿ�Ϊ��
		if(StringUtility.isNull(m_newFkcode)){
			return;
		}

		//sqlɾ��
		//String deleteSql = "delete from t_di_feekind fe where fe.fk_code = '"+ m_newFkcode+"'"; 
		//execute(objSession,deleteSql);

	/*	//hibernatesɾ������ʲô����?
		String deleteSql = " from TdiFeekind fe where fe.fkCode = '"+ m_newFkcode+"'"; 
		objSession.delete(deleteSql);*/
		
		
		//Tdifeekind �̳� TableRecode,TableRecodeʵ��ITableRecode]
		table = new TableAccess(objSession.connection());
		TdifeekindTR objTR = new TdifeekindTR();
		objTR.setFk_codeCondition(m_newFkcode);
		
		table.deleteRecord(objTR);
		
	}

}
