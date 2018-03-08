package kyle.leis.es.businessrule.manifestexportformat.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteManifeststandardcolumnTP extends AbstractTransaction {
	private String result;
	private String mes_code;

	public String getResult() {
		return result;
	}

	public void setMes_code(String mesCode) {
		mes_code = mesCode;
	}

	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(mes_code)) {
			result = null;
			return;
		}

		String deletesql = " delete from t_di_manifeststandardcolumns where msc_code = "
				+ mes_code;
		execute(objSession, deletesql);
		result = "É¾³ý³É¹¦";
	}
}
