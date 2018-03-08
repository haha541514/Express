package kyle.leis.eo.finance.financeauditlogtype.test;


import java.util.List;

import kyle.leis.eo.finance.financeauditlogtype.bl.financeauditlogtype;
import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeColumns;
import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeCondition;
import kyle.leis.eo.finance.financeauditlogtype.tp.DeleteFinanceauditlogtypeTransaction;
import kyle.leis.eo.finance.financeauditlogtype.tp.ModifyFinanceauditlogtypeTransaction;
import kyle.leis.eo.finance.financeauditlogtype.tp.SaveFinanceauditlogtypeTransaction;

public class test {
	public static void main(String[] args) throws Exception {	
		financeauditlogtype f=new financeauditlogtype();
		 List <TdifinanceauditlogtypeColumns>list=f.query(null);
	     for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getFalfaltcode());
		}
		//TdifinanceauditlogtypeColumns financeauditlogtypeColumns=new TdifinanceauditlogtypeColumns();
		//f.delete("A04");
	}
}
