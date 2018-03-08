package kyle.leis.eo.operation.predictwaybill.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.district.da.AlldistictColumns;
import kyle.leis.fs.dictionary.district.da.AlldistictCondition;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class PredictwaybillCheck {
	
	public static PromptUtilityCollection check(PredictwaybillColumns objPredictwaybillColumns) 
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// ���ͻ������Ƿ��ظ�
		PredictwaybillCondition objPWCondition = new PredictwaybillCondition();
		objPWCondition.setCo_code_customer(objPredictwaybillColumns.getCoco_code());
		objPWCondition.setPwbs_code("CHU,CTS,CHP");
		objPWCondition.setPwb_orderid(objPredictwaybillColumns.getPwbpwb_orderid());
		List listResults = PredictwaybillDemand.query(objPWCondition);
		if (listResults != null && listResults.size() > 0) {
			 if (StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_code())) {
				 objPUCollection.add("E_001", "�������ظ�", "PredictwaybillCheck.check");
				 return objPUCollection;
			 } else {
				 if (listResults.size() > 1) {
					 objPUCollection.add("E_001", "�������ظ�", "PredictwaybillCheck.check");
					 return objPUCollection;
				 }
			 }
		}
		// 
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("SLYIM")) {
			// Ч���ʱ��Ƿ���ȷ
			AlldistictCondition objAlldistictCondition = new AlldistictCondition();
			// objAlldistictCondition.setDtname(objPredictwaybillColumns.getPwbpwb_consigneecity());
			// objAlldistictCondition.setStatename(objPredictwaybillColumns.getPwbpwb_consigneestate());
			
			if (StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_consigneestate()))
				objAlldistictCondition.setDtname(objPredictwaybillColumns.getPwbpwb_consigneecity());
			else
				objAlldistictCondition.setStatename(objPredictwaybillColumns.getPwbpwb_consigneestate());			
			
			String strPostcode = objPredictwaybillColumns.getPwbpwb_consigneepostcode();
			if (!StringUtility.isNull(strPostcode)) {
				if (!StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_consigneecity()) &&
						(objPredictwaybillColumns.getPwbpwb_consigneecity().equals("������") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("�޺���") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("��ɽ��") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("������") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("������") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("������") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("������"))) {
					strPostcode = strPostcode.substring(0, 3);
				} else {
					strPostcode = strPostcode.substring(0, 2);
				}
				objAlldistictCondition.setLikepostcode(strPostcode);
				objAlldistictCondition.setDtname(objPredictwaybillColumns.getPwbpwb_consigneecity());
			}
			AlldistictColumns objADColumns = DistrictDemand.loadAlldistict(objAlldistictCondition);
			if (objADColumns == null) {
				 objPUCollection.add("E_001", "�������"+objPredictwaybillColumns.getPwbpwb_consigneecity() + 
						 "���У�" + objPredictwaybillColumns.getPwbpwb_consigneestate() + "ʡ���ʱ�" + objPredictwaybillColumns.getPwbpwb_consigneepostcode() +
						 "����ȷ", 
						 "PredictwaybillCheck.check");
				 return objPUCollection;
			}
			objPredictwaybillColumns.setDtdt_code(objADColumns.getDtdt_code());
		}
		
		return objPUCollection;
	}
	
	public static String checkIMPostcode(PromptUtilityCollection objPUCollection,
			String strProvincename,
			String strCityname,
			String strPostcode) throws Exception {
		// Ч���ʱ��Ƿ���ȷ
		AlldistictCondition objAlldistictCondition = new AlldistictCondition();
		//objAlldistictCondition.setDtname(objPredictwaybillColumns.getPwbpwb_consigneecity());
		if (StringUtility.isNull(strProvincename))
			objAlldistictCondition.setDtname(strCityname);
		else
			objAlldistictCondition.setStatename(strProvincename);
		
		if (StringUtility.isNull(strPostcode) || strPostcode.length() < 3) {
			 objPUCollection.add("E_001", "��������ʱ಻��Ϊ�ջ����ʱ಻��ȷ", 
					 "PredictwaybillCheck.check");
			 return "";			
		}
		
		if (!StringUtility.isNull(strPostcode)) {
			if (!StringUtility.isNull(strCityname) &&
					(strCityname.equals("������") ||
							strCityname.equals("�޺���") ||
							strCityname.equals("��ɽ��") ||
							strCityname.equals("������") ||
							strCityname.equals("������") ||
							strCityname.equals("������") ||
							strCityname.equals("������"))) {
				strPostcode = strPostcode.substring(0, 3);
			} else {
				strPostcode = strPostcode.substring(0, 2);
			}
			objAlldistictCondition.setLikepostcode(strPostcode);
		}
		AlldistictColumns objADColumns = DistrictDemand.loadAlldistict(objAlldistictCondition);
		if (objADColumns == null) {
			 objPUCollection.add("E_001", "�������"+strCityname + 
					 "���У�" + StringUtility.replaceWhenNull(strProvincename, "") + "ʡ���ʱ�" + 
					 strPostcode +
					 "����ȷ", 
					 "PredictwaybillCheck.check");
			 return "";
		}
		return objADColumns.getDtdt_code();
	}
	
	
	
	public static synchronized PromptUtilityCollection checkUpload(PredictwaybillColumns objPredictwaybillColumns) 
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(objPredictwaybillColumns.getPwbspwbs_code()) ||
				!objPredictwaybillColumns.getPwbspwbs_code().equals("CTS")) {
			 objPUCollection.add("E_001", "��Ϊ�ݴ�״̬�޷��ϴ�", "PredictwaybillCheck.check");
			 return objPUCollection;
		}
		if (StringUtility.isNull(objPredictwaybillColumns.getChnchn_code())) {
			 objPUCollection.add("E_001", "��������Ϊ�գ��޷��ϴ�", "PredictwaybillCheck.check");
			 return objPUCollection;
		}		
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		objPUCollection = objCoreWayBillCheck.checkCEWBCodeWhenPredict(objPredictwaybillColumns.getPwbpwb_orderid(),
				objPredictwaybillColumns.getCoco_code());
		return objPUCollection;
	}	
	
}
