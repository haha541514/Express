package kyle.leis.eo.operation.customsdeclaration.bl;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.sv.CorewaybillDelegate;
import kyle.leis.eo.operation.customsdeclaration.da.CustomsdeclarationColumns;
import kyle.leis.eo.operation.customsdeclaration.tp.SaveCustomsDeclarationTrans;
import kyle.leis.eo.operation.housewaybill.tp.SaveHousewaybillTrans;

public class CustomsDeclaration {
	/**
	 * 保存
	 * @param listCDColumns
	 * @param strCwcode
	 * @throws Exception
	 */
	public void save(List listCDColumns,
			String strCwcode) throws Exception {
		// 生成海关条码
		if (listCDColumns != null && listCDColumns.size() > 0) {
			CorewaybillDelegate cwd = new CorewaybillDelegate();
			for (int i = 0; i < listCDColumns.size(); i++) {
				CustomsdeclarationColumns objCDColumns = (CustomsdeclarationColumns)listCDColumns.get(i);
				String strCDlabelcode = objCDColumns.getCdcdlabelcode();
				if (StringUtility.isNull(strCDlabelcode)) {
					strCDlabelcode = cwd.getCustomsLabelcode("0");
					objCDColumns.setCdcdlabelcode(strCDlabelcode);
				}
			}
		}
		SaveCustomsDeclarationTrans objSCDTrans = new SaveCustomsDeclarationTrans();
		objSCDTrans.setParam(listCDColumns, 
				strCwcode);
		objSCDTrans.execute();
	}
	
	/**
	 * 更新打印标记
	 * @param strCwcode
	 * @throws Exception
	 */
	public void print(String strCwcode, String strOperId) throws Exception {
		SaveHousewaybillTrans objSHWTrans = new SaveHousewaybillTrans();
		objSHWTrans.setPrintCustomslabelParam(strCwcode, strOperId);
		objSHWTrans.execute();
	}
}
