package kyle.leis.eo.operation.customsdeclaration.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsdeclarationColumns;
import kyle.leis.eo.operation.customsdeclaration.dax.CustomsDeclarationDemand;
import kyle.leis.hi.TopCustomsdeclaration;
import kyle.leis.hi.TopHousewaybill;

public class SaveCustomsDeclarationTrans extends AbstractTransaction {
	private List m_listCDColumns;
	private String m_strCwcode;
	public void setParam(List listCDColumns,
			String strCwcode) {
		m_strCwcode = strCwcode;
		m_listCDColumns = listCDColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strCwcode)) return;
		// 先删除
		String strDeleteSql = "delete from T_OP_CUSTOMSDECLARATION cd where cd.CW_CODE = " +
		m_strCwcode;
		execute(objSession, strDeleteSql);
		// 保存
		if (m_listCDColumns == null || m_listCDColumns.size() < 1)
			return;
		for (int i = 0; i < m_listCDColumns.size(); i++) {
			CustomsdeclarationColumns objCDColumns = (CustomsdeclarationColumns)m_listCDColumns.get(i);
			TopCustomsdeclaration objTCDeclartion = new TopCustomsdeclaration();
			// 设置数据
			CustomsDeclarationDemand.setTDeclarationByColumns(objTCDeclartion, 
					objCDColumns,
					objSession);
			TopHousewaybill objTopHousewaybill = (TopHousewaybill)objSession.load(TopHousewaybill.class, 
					Long.parseLong(m_strCwcode));
			// 设置形式发票
			objTCDeclartion.setTopHousewaybill(objTopHousewaybill);
			// 保存
			objSession.save(objTCDeclartion);
		}
	}
}
