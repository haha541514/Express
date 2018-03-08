package wkq.tp;

import wkq.da.FeekindColumns;
import wkq.da.TdifeekindTR;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import wkq.dax.FeekindDemand;
import kyle.leis.hi.TdiFeekind;

import net.sf.hibernate.Session;

public class ModifyFeekind extends AbstractTransaction {

	private String m_strFkcode ;
	private String m_strSscode ;
	private FeekindColumns m_objFeekindColumns;
	
	public void setParam(String strFkcode,String strSscode)
	{
		this.m_strFkcode = strFkcode;
		this.m_strSscode = strSscode;
		
	}
	public void setColumns(FeekindColumns columns){
		this.m_objFeekindColumns = columns;
	}
/*	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strFkcode) || StringUtility.isNull(m_strSscode)) return;
		
		//hibernate方式
		TdiFeekind objTdiFeekind = (TdiFeekind)TdiFeekindDC.load(TdiFeekind.class,m_strFkcode);
		
		objTdiFeekind.setFkCode(m_objFeekindColumns.getFofkcode());
		objTdiFeekind.setFkEname(m_objFeekindColumns.getFofkename());
		objTdiFeekind.setFkName(m_objFeekindColumns.getFofkname());
		
		objTdiFeekind.setTdiSimplestatus((TdiSimplestatus)objSession.load(TdiSimplestatus.class, m_strSscode));
		objSession.update(objTdiFeekind);
		
		
		String updateSql = "update t_di_feekind t set " 
			//+ "t.fc_code = '"+ m_objFeekindColumns.getFofkcode() + "',"
			+ " t.FK_NAME = '" + m_objFeekindColumns.getFofkname()+ "'," 
			+ " t.FK_ENAME = '"+ m_objFeekindColumns.getFofkename() + "',"
			+ " t.FK_REFERENCEPOSITION = '"+ m_objFeekindColumns.getFofkreferenceposition() + "',"
			+ " t.FK_MANUALMODIFYSIGN = '"+ m_objFeekindColumns.getFofkmanualmodifysign() + "',"
			+ " t.FK_REMARK = '" + m_objFeekindColumns.getFofkremark()+ "'," 
			+ " t.FK_ACCOUNTINGONLYSIGN = '"+ m_objFeekindColumns.getFofkaccountingonlysign() + "',"
			+ " t.FK_DECLAREVALUESIGN = '"+ m_objFeekindColumns.getFofkdeclarevaluesign() + "',"
			+ " t.SS_CODE = '" + m_objFeekindColumns.getSisscode()+ "',"
			+ " t.EST_CODE = '"+ m_objFeekindColumns.getExestcode()+ "',"
			+ " t.FK_BASESIGN = '" +  m_objFeekindColumns.getSign()
			+ "'" + " where t.fk_code = '" + m_strFkcode + "'";
			execute(objSession, updateSql);
	}*/
	
	/***
	 * java.sql.SQLException: No conditon field has been setted in the record!
	 * table方式
	 * 
	 * **/
	
	/*public void transaction(Session objSession) throws Exception{
		
		TableAccess table = new TableAccess(objSession.connection());
		TdifeekindTR tdiFeekindTR = new TdifeekindTR();
		
		
		tdiFeekindTR.setFk_code(m_objFeekindColumns.getFofkcode());
		tdiFeekindTR.setSs_code(m_objFeekindColumns.getSisscode());
		tdiFeekindTR.setFk_ename(m_objFeekindColumns.getFofkename());
		tdiFeekindTR.setFk_name(m_objFeekindColumns.getFofkname());
		tdiFeekindTR.setFk_referenceposition(m_objFeekindColumns.getFofkreferenceposition());
		tdiFeekindTR.setFk_manualmodifysign(m_objFeekindColumns.getFofkmanualmodifysign());
		tdiFeekindTR.setFk_basesign(m_objFeekindColumns.getSign());
		tdiFeekindTR.setFk_remark(m_objFeekindColumns.getFofkremark());
		tdiFeekindTR.setEst_code(m_objFeekindColumns.getExestcode());
		tdiFeekindTR.setFk_accountingonlysign(m_objFeekindColumns.getFofkaccountingonlysign());
		tdiFeekindTR.setFk_declarevaluesign(m_objFeekindColumns.getFofkdeclarevaluesign());
	
		tdiFeekindTR.setFk_codeCondition(m_objFeekindColumns.getFofkcode());
		table.updateRecord(tdiFeekindTR);
		
	}*/
	
	public void transaction(Session objSession) throws Exception{
		
		TdiFeekind objFeekind = new TdiFeekind();
		objFeekind = (TdiFeekind)objSession.load(TdiFeekind.class, m_strFkcode);//load查询,根据fkcode查询
		FeekindDemand.setFeekindByColumns(objFeekind, m_objFeekindColumns, objSession);//设置值
		objSession.update(objFeekind);//commit修改
	}
	
	
}
