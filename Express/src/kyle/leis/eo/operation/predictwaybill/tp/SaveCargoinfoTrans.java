package kyle.leis.eo.operation.predictwaybill.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.ToppredictcargoinfoTR;

public class SaveCargoinfoTrans extends AbstractTransaction {

	private List m_listCargoinfo;
	private String m_strPwbcode;
	
	public void setParam(List listCargoinfo,
			String strPwbcode) {
		m_listCargoinfo = listCargoinfo;
		m_strPwbcode = strPwbcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strPwbcode) || 
				m_listCargoinfo == null ||
				m_listCargoinfo.size() < 1) 
			return;
		// 先删除
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		ToppredictcargoinfoTR objTPCITR = new ToppredictcargoinfoTR();
		objTPCITR.setPwb_codeCondition(m_strPwbcode);
		objTableAccess.deleteRecord(objTPCITR);
		// 新增
		for (int i = 0; i < m_listCargoinfo.size(); i++) {
			PredictcargoinfoColumns objPCIColumns = (PredictcargoinfoColumns)m_listCargoinfo.get(i);
			if (StringUtility.isNull(objPCIColumns.getPcipci_ename()))
				continue;
			objTPCITR = new ToppredictcargoinfoTR();
			
			String[] astr = new String[2];
			String strCargoname = objPCIColumns.getPcipci_ename();
			// 英文|中文的格式
			if (strCargoname.indexOf("|") > 0) {
				astr[0] = strCargoname.substring(0, strCargoname.indexOf("|"));
				astr[1] = strCargoname.substring(strCargoname.indexOf("|") + 1);
			} else {
				astr[0] = strCargoname;
				astr[1] = strCargoname;
			}			
			
			objTPCITR.setCk_code(objPCIColumns.getPcick_code());
			objTPCITR.setPci_attacheinfo(objPCIColumns.getPcipci_attacheinfo());
			objTPCITR.setPci_ename(astr[0]);
			objTPCITR.setPci_name(astr[1]);
			if (!StringUtility.isNull(objPCIColumns.getPcipci_name()))
				objTPCITR.setPci_name(objPCIColumns.getPcipci_name());
			
			objTPCITR.setPci_hscode(objPCIColumns.getPcipci_hscode());
			objTPCITR.setPci_id(String.valueOf(i));			
			objTPCITR.setPci_pieces(objPCIColumns.getPcipci_pieces());
			objTPCITR.setPci_remark(objPCIColumns.getPcipci_remark());
			objTPCITR.setPci_unitprice(objPCIColumns.getPcipci_unitprice());
			
			if (StringUtility.isNull(objPCIColumns.getPcipci_totalprice())) {
				BigDecimal objTotalprice = new BigDecimal(objPCIColumns.getPcipci_unitprice()).multiply(new BigDecimal(objPCIColumns.getPcipci_pieces()));
				objTPCITR.setPci_totalprice(objTotalprice.divide(new BigDecimal("1"), 2, 2).toString());				
			} else {
				objTPCITR.setPci_totalprice(objPCIColumns.getPcipci_totalprice());
			}
			objTPCITR.setPci_weight(objPCIColumns.getPcipci_weight());
			objTPCITR.setPwb_code(m_strPwbcode);
			
			objTableAccess.insertRecord(objTPCITR);
		}
	}
	

}
