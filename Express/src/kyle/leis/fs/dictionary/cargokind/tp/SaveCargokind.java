package kyle.leis.fs.dictionary.cargokind.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.cargokind.da.CargokindColumns;
import kyle.leis.fs.dictionary.cargokind.da.CargokindCondition;
import kyle.leis.fs.dictionary.cargokind.da.CargokindQuery;
import kyle.leis.fs.dictionary.cargokind.da.CargokindSeqQuery;
import kyle.leis.fs.dictionary.cargokind.dax.CargokindDemand;
import kyle.leis.hi.TdiCargokind;
import net.sf.hibernate.Session;

public class SaveCargokind extends AbstractTransaction{

	CargokindColumns objCargokindcolumns;
	private String m_Cgkcode;
	public void setParam(CargokindColumns columns,String cgkcode){
		this.objCargokindcolumns = columns;
		this.m_Cgkcode = cgkcode;
	}
	
	
	public void transaction(Session objSession) throws Exception {

		if(objCargokindcolumns == null || StringUtility.isNull(objCargokindcolumns.getSisscode()))
				return;
		//新增保存都写在一个方法里
		//CargokindQuery query = new CargokindQuery();
		//CargokindCondition condition = new CargokindCondition();
		//condition.setCgkcode(m_Cgkcode);//通过主键查询为空
		//List list = query.getResults();//
		//System.out.println(list);
		//什么时候返回空，column = null
		if(StringUtility.isNull(m_Cgkcode)){
			//新增,m_Cgkcode 为空，isNull就是true
			CargokindSeqQuery sequery = new CargokindSeqQuery();
			objCargokindcolumns.setCkcgkcode(sequery.getNewSequencecode());//设置主键
			
			TdiCargokind objTdiCargokind = new TdiCargokind();
			CargokindDemand.setCargokindByid(objTdiCargokind, objCargokindcolumns, objSession);
			objSession.save(objTdiCargokind);
		}else{
			//修改
			TdiCargokind objTdiCargokind = new TdiCargokind();
			CargokindDemand.setCargokindByid(objTdiCargokind, objCargokindcolumns, objSession);
			objSession.update(objTdiCargokind);
		}
		
		
		
	
		
	}




}
