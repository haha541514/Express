package kyle.leis.fs.dictionary.expressspecialtype.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeColumns;
import kyle.leis.fs.dictionary.expressspecialtype.dax.ExpressspecialtypeDemand;
import net.sf.hibernate.Session;

public class DelSpecialTypeTransact extends AbstractTransaction{
	private String pkCode;
	private ExpressspecialtypeColumns returnColumns;

	public ExpressspecialtypeColumns getReturnColumns() {
		return returnColumns;
	}
	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(pkCode)) {
			return;
		}
		//判断是否有子类型
		List<ExpressspecialtypeColumns> list = ExpressspecialtypeDemand.query(null);
		for (ExpressspecialtypeColumns column : list) {
			String estCode = column.getEstestcode();
			if (estCode.startsWith(pkCode)
					&& estCode.length() > pkCode.length()) {
				return;
			}
		}
		returnColumns = ExpressspecialtypeDemand.queryByPkCode(pkCode);
		if (returnColumns == null) {//有子类型或者记录不存在，则退出
			return;
		}
		String sql = "update t_di_expressspecialtype t set t.SS_CODE = 'OFF' where t.est_code = '" + pkCode + "'";
		execute(objSession, sql);
	}
	
}
