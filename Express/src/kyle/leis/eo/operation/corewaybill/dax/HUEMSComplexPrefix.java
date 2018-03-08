package kyle.leis.eo.operation.corewaybill.dax;

import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.waybillcode.bl.IComplexPrefix;

public class HUEMSComplexPrefix implements IComplexPrefix {

	private ForinputallColumns m_objFIAColumns;
	
	public HUEMSComplexPrefix(ForinputallColumns objFIAColumns) {
		m_objFIAColumns = objFIAColumns;
	}	
	
	public String buildPrefix() throws Exception {
		// TODO Auto-generated method stub
		//return m_objFIAColumns.getHwconsigneepostcode();
		
		return "1005";
	}

}
