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
		//�������涼д��һ��������
		//CargokindQuery query = new CargokindQuery();
		//CargokindCondition condition = new CargokindCondition();
		//condition.setCgkcode(m_Cgkcode);//ͨ��������ѯΪ��
		//List list = query.getResults();//
		//System.out.println(list);
		//ʲôʱ�򷵻ؿգ�column = null
		if(StringUtility.isNull(m_Cgkcode)){
			//����,m_Cgkcode Ϊ�գ�isNull����true
			CargokindSeqQuery sequery = new CargokindSeqQuery();
			objCargokindcolumns.setCkcgkcode(sequery.getNewSequencecode());//��������
			
			TdiCargokind objTdiCargokind = new TdiCargokind();
			CargokindDemand.setCargokindByid(objTdiCargokind, objCargokindcolumns, objSession);
			objSession.save(objTdiCargokind);
		}else{
			//�޸�
			TdiCargokind objTdiCargokind = new TdiCargokind();
			CargokindDemand.setCargokindByid(objTdiCargokind, objCargokindcolumns, objSession);
			objSession.update(objTdiCargokind);
		}
		
		
		
	
		
	}




}
