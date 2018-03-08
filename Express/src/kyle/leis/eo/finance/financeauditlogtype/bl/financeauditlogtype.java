package kyle.leis.eo.finance.financeauditlogtype.bl;

import java.util.List;

import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeColumns;
import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeCondition;
import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeQuery;
import kyle.leis.eo.finance.financeauditlogtype.tp.DeleteFinanceauditlogtypeTransaction;
import kyle.leis.eo.finance.financeauditlogtype.tp.ModifyFinanceauditlogtypeTransaction;
import kyle.leis.eo.finance.financeauditlogtype.tp.SaveFinanceauditlogtypeTransaction;

public class financeauditlogtype {
	//����
    public void save(TdifinanceauditlogtypeColumns financeauditlogtypeColumns) throws Exception{
    	SaveFinanceauditlogtypeTransaction objTransaction= new SaveFinanceauditlogtypeTransaction();
    	objTransaction.setParam(financeauditlogtypeColumns);
    	objTransaction.execute();
    }
    //��ѯ
    public List query(TdifinanceauditlogtypeCondition financeauditlogtypeCondition) throws Exception{
    	TdifinanceauditlogtypeQuery objQuery=new TdifinanceauditlogtypeQuery();
    	objQuery.setCondition(financeauditlogtypeCondition);
    	return objQuery.getResults();
    }
    //ɾ��
    public void delete(String code) throws Exception{
       DeleteFinanceauditlogtypeTransaction objTransaction=new DeleteFinanceauditlogtypeTransaction();
       objTransaction.setM_code(code);
       objTransaction.execute();
    }
    //�޸�
    public void modify(TdifinanceauditlogtypeColumns financeauditlogtypeColumns) throws Exception{
    	ModifyFinanceauditlogtypeTransaction objTransaction= new ModifyFinanceauditlogtypeTransaction();
    	objTransaction.setParam(financeauditlogtypeColumns);
    	objTransaction.execute();
    }
}
