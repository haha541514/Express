package kyle.leis.fs.dictionary.batterykind.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindColumns;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindSeqQuery;
import kyle.leis.fs.dictionary.batterykind.dax.BatterkindDemand;
import kyle.leis.hi.TdiBatterykind;

public class SaveBatterkind extends AbstractTransaction{

	private BatterykindColumns objColumns;
	private String bkCode;
	public void setParam(BatterykindColumns columns,String bkcode){
		
		this.objColumns = columns;
		this.bkCode = bkcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		
		if(objColumns == null ||  StringUtility.isNull(objColumns.getSisscode()) )
			//cgk_Code����Ϊ�գ�ss_Code����
			return;
		

		if(StringUtility.isNull(bkCode)){
			//bkcode,Ϊ�գ�����
			TdiBatterykind objBatter = new TdiBatterykind();
			BatterykindSeqQuery query = new BatterykindSeqQuery();
			objColumns.setBkbkcode(query.getNewSequencecode());//��������
			BatterkindDemand.setBatterkind(objBatter, objSession, objColumns);
			objSession.save(objBatter);
			
		}else{
			//�޸�
			TdiBatterykind objBatter = new TdiBatterykind();
			BatterkindDemand.setBatterkind(objBatter, objSession, objColumns);
			objSession.update(objBatter);
		}
		
		
		
		
	}

}
