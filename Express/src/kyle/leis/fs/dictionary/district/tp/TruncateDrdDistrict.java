package kyle.leis.fs.dictionary.district.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

public class TruncateDrdDistrict extends AbstractTransaction{

	//��ձ��е�����
	public void transaction(Session objSession) throws Exception {

		//��ɾ��ԭ�б������
		String delSql = "truncate table t_di_dhlremotedistrict";
		execute(objSession,delSql);
	}

}
