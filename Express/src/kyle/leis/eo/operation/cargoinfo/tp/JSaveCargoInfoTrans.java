package kyle.leis.eo.operation.cargoinfo.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.da.TophwbcargoinfoTR;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;

public class JSaveCargoInfoTrans extends AbstractTransaction {
	private List m_listCargoInfo;
	private String m_strCwcode;
	
	public void setParam(List listCargoInfo, 
			String strCwcode) {	
		m_strCwcode = strCwcode;
		m_listCargoInfo = listCargoInfo;
	}
	
	public void transaction(Session objSession) throws Exception {
		// 先删除后新增
		if (StringUtility.isNull(m_strCwcode)) return;
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		
		TophwbcargoinfoTR objTophwbcargoinfoTR = new TophwbcargoinfoTR();
		objTophwbcargoinfoTR.setCw_codeCondition(m_strCwcode);
		objTableAccess.deleteRecord(objTophwbcargoinfoTR);
		
		if (m_listCargoInfo == null || m_listCargoInfo.size() < 1) return;
		for (int i = 0; i < m_listCargoInfo.size(); i++) {
			CargoinfoColumns objCIC = (CargoinfoColumns)m_listCargoInfo.get(i);
			objTophwbcargoinfoTR = new TophwbcargoinfoTR();
			objCIC.setCicomp_idciid(i);
			CargoInfoDemand.setCargoInfoByColumns(objTophwbcargoinfoTR, objCIC, m_strCwcode);
			objTableAccess.insertRecord(objTophwbcargoinfoTR);
		}
	}
}
