package kyle.leis.fs.dictionary.district.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

public class TruncateDrdDistrict extends AbstractTransaction{

	//清空表中的数据
	public void transaction(Session objSession) throws Exception {

		//先删除原有表的数据
		String delSql = "truncate table t_di_dhlremotedistrict";
		execute(objSession,delSql);
	}

}
