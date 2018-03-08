package kyle.leis.fs.dictionary.batterykind.tp;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.batterykind.da.BatterykindColumns;
import kyle.leis.fs.dictionary.batterykind.dax.BatterkindDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBillingkindDC;
import kyle.leis.hi.TdiBatterykind;
import kyle.leis.hi.TdiSimplestatus;

public class DeleteBatterkind extends AbstractTransaction{

	private BatterykindColumns m_columns;
	private String bk_code;
	public void setParam(String code){
		this.bk_code = code;
	}
	public BatterykindColumns getBatterykindColumns(){
		return m_columns;
	}
	/**
	 * 	ɾ�������޸�ss_code��״̬
	 *	ֱ���޸�״̬
	 * **/
	public void transaction(Session objSession) throws Exception {
	
		if(StringUtility.isNull(bk_code)){
			//bk_codeΪ�գ��Ż�null
			m_columns = null;
			
		}else{
			TdiBatterykind objBatter = (TdiBatterykind) TdiBillingkindDC.load(TdiBatterykind.class, bk_code);
			BatterykindColumns objColumns = new BatterykindColumns();
			objColumns.setSisscode("OFF");
			objBatter.setTdiSimplestatus((TdiSimplestatus)objSession.load(TdiSimplestatus.class, objColumns.getSisscode()));
			objSession.update(objBatter);
		
			this.m_columns = BatterkindDemand.queryById(bk_code);//�޸ĳɹ�����columns
		}
	}

}
