package wkq.tp;

import java.util.List;

import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.hi.TdiFeekind;
import net.sf.hibernate.Session;
import wkq.da.FeekindColumns;
import wkq.da.TdifeekindTR;
import wkq.dax.FeekindDemand;


public class SaveFeekindHibernate extends AbstractTransaction {
	private FeekindColumns m_objFeekindColumns;
	private String m_newFkcode;

	public void setNewFkCode(String fkCode){
		this.m_newFkcode = fkCode;
	}
	
	public String getNewFkcode() {
		return this.m_newFkcode;
	}
	
	public void setParam(FeekindColumns objFeekindColumns) {
		this.m_objFeekindColumns = objFeekindColumns;
	}

	/**
	 * 20160727 周三 11:59 
	 * by wukaiquan 
	 * option: 将objTdiFeekind对象放入session,进行新增
	 * **/

	
	
	/*public void transaction(Session objSession) throws Exception {

		if (m_objFeekindColumns == null)
			return;

		//FeekindQuery query = new FeekindQuery();
		//query.setFkcode(m_newFkcode);
		//java.lang.Exception: class not found: kyle.leis.eo.finance.Feekind.da.FeekindColumns 
		//[SELECT new kyle.leis.eo.finance.Feekind.da.FeekindColumns(fo.fkCode,fo.fkName,fo.fkEname,fo.fkReferenceposition,fo.fkManualmodifysign,   fo.fkBasesign,fo.fkRemark,fo.fkAccountingonlysign,   fo.fkDeclarevaluesign,   si.ssCode,   ex.estCode) FROM kyle.leis.hi.TdiFeekind as fo inner join fo.tdiSimplestatus as si inner join fo.tdiExpressspecialtype as ex WHERE fo.fkCode = 'K8086']
		//List<?> list = query.getResults();

		
		//多了一个";"无效字符
			
			String saveSql = "insert into t_di_feekind  values('"
					+ m_objFeekindColumns.getFofkcode() + "','"
					+ m_objFeekindColumns.getSisscode() + "','"
					+ m_objFeekindColumns.getFofkname() + "','"
					+ m_objFeekindColumns.getFofkename() + "','"
					+ m_objFeekindColumns.getFofkreferenceposition() + "','"
					+ m_objFeekindColumns.getFofkmanualmodifysign() + "','"
					+ m_objFeekindColumns.getSign()+ "','"
					+ m_objFeekindColumns.getFofkremark() + "','"
					+ m_objFeekindColumns.getExestcode()+ "','"
					+ m_objFeekindColumns.getFofkaccountingonlysign()+ "','"
					+ m_objFeekindColumns.getFofkdeclarevaluesign() + "')";

			execute(objSession, saveSql);

	}
	*/
	
	
	
	  /*public void transaction(Session objSession) throws Exception {
	  
	  if (m_objFeekindColumns == null) return;
	  		//FeekindQuery query = new FeekindQuery();
			//query.setFkcode(m_newFkcode);
			//java.lang.Exception: class not found: kyle.leis.eo.finance.Feekind.da.FeekindColumns 
			//[SELECT new kyle.leis.eo.finance.Feekind.da.FeekindColumns(fo.fkCode,fo.fkName,fo.fkEname,fo.fkReferenceposition,fo.fkManualmodifysign,   fo.fkBasesign,fo.fkRemark,fo.fkAccountingonlysign,   fo.fkDeclarevaluesign,   si.ssCode,   ex.estCode) FROM kyle.leis.hi.TdiFeekind as fo inner join fo.tdiSimplestatus as si inner join fo.tdiExpressspecialtype as ex WHERE fo.fkCode = 'K8086']
			//Query类里面有sql语句select * from 
			//List<?> list = query.getResults();

			TdiFeekind objFeekind  = new TdiFeekind();
			
			//objFeekind = (TdiFeekind)objSession.load(TdiFeekind.class, m_objFeekindColumns.getFofkcode());
			//FeekindDemand.setFeekindByColumns(objFeekind,m_objFeekindColumns,objSession); 
			objFeekind.setFkCode(m_objFeekindColumns.getFofkcode());
			objFeekind.setFkBasesign(m_objFeekindColumns.getSign());
			objFeekind.setFkAccountingonlysign(m_objFeekindColumns.getFofkaccountingonlysign());
			objFeekind.setFkDeclarevaluesign(m_objFeekindColumns.getFofkdeclarevaluesign());
			objFeekind.setFkEname(m_objFeekindColumns.getFofkename());
			objFeekind.setFkName(m_objFeekindColumns.getFofkname());
			
			objFeekind.setFkManualmodifysign(m_objFeekindColumns.getFofkmanualmodifysign());
			objFeekind.setFkReferenceposition(m_objFeekindColumns.getFofkreferenceposition());
			objFeekind.setFkRemark(m_objFeekindColumns.getFofkremark());
			
	
			//TdiSimplestatus tdiSimplestatus = new TdiSimplestatus();
			//tdiSimplestatus.setSsCode(m_objFeekindColumns.getSisscode());
			//objFeekind.setTdiSimplestatus(tdiSimplestatus);
			
			//设置对象
			objFeekind.setTdiSimplestatus((TdiSimplestatus)objSession.load(TdiSimplestatus.class, m_objFeekindColumns.getSisscode()));
			
			objFeekind.setTdiExpressspecialtype(null);//TdiExpressspecialtype都是空			
			objSession.save(objFeekind);

	  }*/
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
		
		//更新，通过查看源代码注释
		table.insertRecord(tdiFeekindTR);
		
	}*/
	
	public void transaction(Session objSession) throws Exception{
		  if (m_objFeekindColumns == null) return;
			TdiFeekind objFeekind = new TdiFeekind();
			//setFeekindByColumns的类要看清楚是hi下面的类
			//Feekind费用种类loand是认为其在数据库存在
			//objFeekind = (TdiFeekind)objSession.load(TdiFeekind.class, m_objFeekindColumns.getFofkcode());
			//由于FeekindDemand里面没有设置m_newFkcode,所以这里设置
			objFeekind.setFkCode(m_newFkcode);
			FeekindDemand.setFeekindByColumns(objFeekind,m_objFeekindColumns,objSession);
			objSession.save(objFeekind);
	}
	
	

}
