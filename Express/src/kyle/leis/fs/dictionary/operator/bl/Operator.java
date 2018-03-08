package kyle.leis.fs.dictionary.operator.bl;

import java.util.List;

import net.sf.hibernate.HibernateException;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtility;
import kyle.leis.es.systemcertification.bl.Systemcertification;
import kyle.leis.fs.authority.bl.Authority;
import kyle.leis.fs.authority.dax.RoleMenusReturn;
import kyle.leis.fs.dictionary.operator.da.OperatorColumns;
import kyle.leis.fs.dictionary.operator.da.OperatorCondition;
import kyle.leis.fs.dictionary.operator.da.OperatorQuery;
import kyle.leis.fs.dictionary.operator.dax.OperatorLoginReturn;
import kyle.leis.fs.dictionary.operator.tp.ModifyPasswordTrans;
import kyle.leis.hi.TdiOperator;

public class Operator {
	public OperatorLoginReturn login(String strOperCode, 
			String strOperPassword, 
			String strIsk_code,
			String strHDSerialNumber,
			String strMACAddress,
			String strIPAddress,
			boolean isCarryoverSystem) 
	throws Exception {
		try {
			OperatorLoginReturn objOLR = new OperatorLoginReturn();
			
			OperatorQuery objOperatorQuery = new OperatorQuery();
			OperatorCondition objOperatorC = new OperatorCondition();
			objOperatorC.setOpcode(strOperCode);
			objOperatorQuery.setCondition(objOperatorC);
			List objList = objOperatorQuery.getResults();
			
			if (CollectionUtility.isNull(objList)) {
				objOLR.setLoginStatus(false);
				objOLR.setErrText("�����ڵ��û���");
				return objOLR;
			}
			if (objList.get(0) instanceof OperatorColumns) {
				OperatorColumns objOperatorColumns = (OperatorColumns)objList.get(0);
				String strQueryPassword = objOperatorColumns.getSword();
				String strOscode = objOperatorColumns.getOscode();
				if (StringUtility.isNull(strOperPassword) || StringUtility.isNull(strQueryPassword)) {
					objOLR.setLoginStatus(false);
					objOLR.setErrText("����Ϊ�ղ������¼");
					return objOLR;
				}
				if (!strOperPassword.equals(strQueryPassword)) {
					objOLR.setLoginStatus(false);
					objOLR.setErrText("���벻��ȷ");
					return objOLR;
				}
				if (!StringUtility.isNull(strOscode) && strOscode.equals("DS")) {
					objOLR.setLoginStatus(false);
					objOLR.setErrText("��ְԱ���������½ϵͳ");
					return objOLR;
				}
				// ��֤
				Systemcertification objSC = new Systemcertification();
				PromptUtility objPU = objSC.checkSystemcertification(strHDSerialNumber, strMACAddress, 
						strIPAddress, objOperatorColumns.getOpid(),
						objOperatorColumns.getCocode());
				if (objPU != null && !objPU.canGo(false)) {
					objOLR.setLoginStatus(false);
					objOLR.setErrText(objPU.getDescribtion());
					return objOLR;					
				}
				// ����û�Ȩ��
				Authority objAuthority = new Authority();
				if (StringUtility.isNull(strIsk_code))
					strIsk_code = "LEDIS";
				RoleMenusReturn objRMR = objAuthority.queryGUIMenu(strOperCode, 
						strIsk_code,
						isCarryoverSystem);
				if (objRMR.isContainException()) {
					objOLR.setLoginStatus(false);
					objOLR.setErrText(objRMR.getErrText());
					return objOLR;
				}
				objOLR.setRoleMenus(objRMR.getRoleMenus());
				objOLR.setOperatorColumns(objOperatorColumns);
				objOLR.setLoginStatus(true);
			}
			return objOLR;
		} catch(HibernateException objHE) {
			objHE.printStackTrace();
			throw new Exception(objHE.getMessage());
		}
	}
	
	public PromptUtility modifyPassword(String strOperId, 
			String strOldPassword, 
			String strNewPassword) throws Exception {
		ModifyPasswordTrans objMPassTrans = new ModifyPasswordTrans();
		objMPassTrans.setParam(strOperId, strNewPassword);
		TdiOperator objTdiOperator = objMPassTrans.getOriginOperator();
		if (objTdiOperator == null) {
			PromptUtility objPromptUtility = new PromptUtility("E_USER_001", 
					"�����ڵ��û���", 
					"Operator.modifyPassword");
			return objPromptUtility;
		}
		if (!objTdiOperator.getOpPassword().equals(strOldPassword)) {
			PromptUtility objPromptUtility = new PromptUtility("E_USER_002", 
					"����ľ����벻��ȷ", 
					"Operator.modifyPassword");
			return objPromptUtility;			
		}
		if (StringUtility.isNull(strNewPassword)) {
			PromptUtility objPromptUtility = new PromptUtility("E_USER_003", 
					"�����벻��Ϊ��", 
					"Operator.modifyPassword");
			return objPromptUtility;			
		}
		objMPassTrans.execute();
		return null;
	}
}
