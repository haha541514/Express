package kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PredictcargoinfoQuery extends JGeneralQuery {
	
	public PredictcargoinfoQuery(){
	    m_strSelectClause = "SELECT pci.PCI_ID,pci.PWB_Code,pci.CK_Code,pci.PCI_Name,pci.PCI_Ename,pci.PCI_Pieces,pci.PCI_Weight,pci.PCI_UnitPrice,pci.PCI_TotalPrice,pci.PCI_HSCode,pci.PCI_AttacheInfo,pci.PCI_Remark FROM T_OP_PREDICTCARGOINFO pci";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pci.PWB_Code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PredictcargoinfoColumns();
	}
	
	public void setPwbcode(String pwbcode) {
		this.setField(0, pwbcode);
	}

	public String getPwbcode() {
		return this.getField(0);
	}

}
