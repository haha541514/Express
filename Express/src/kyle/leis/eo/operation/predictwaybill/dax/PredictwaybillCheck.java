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
		// 检查客户单号是否重复
		PredictwaybillCondition objPWCondition = new PredictwaybillCondition();
		objPWCondition.setCo_code_customer(objPredictwaybillColumns.getCoco_code());
		objPWCondition.setPwbs_code("CHU,CTS,CHP");
		objPWCondition.setPwb_orderid(objPredictwaybillColumns.getPwbpwb_orderid());
		List listResults = PredictwaybillDemand.query(objPWCondition);
		if (listResults != null && listResults.size() > 0) {
			 if (StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_code())) {
				 objPUCollection.add("E_001", "订单号重复", "PredictwaybillCheck.check");
				 return objPUCollection;
			 } else {
				 if (listResults.size() > 1) {
					 objPUCollection.add("E_001", "订单号重复", "PredictwaybillCheck.check");
					 return objPUCollection;
				 }
			 }
		}
		// 
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("SLYIM")) {
			// 效验邮编是否正确
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
						(objPredictwaybillColumns.getPwbpwb_consigneecity().equals("深圳市") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("罗湖区") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("南山区") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("龙岗区") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("盐田区") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("福田区") ||
								objPredictwaybillColumns.getPwbpwb_consigneecity().equals("宝安区"))) {
					strPostcode = strPostcode.substring(0, 3);
				} else {
					strPostcode = strPostcode.substring(0, 2);
				}
				objAlldistictCondition.setLikepostcode(strPostcode);
				objAlldistictCondition.setDtname(objPredictwaybillColumns.getPwbpwb_consigneecity());
			}
			AlldistictColumns objADColumns = DistrictDemand.loadAlldistict(objAlldistictCondition);
			if (objADColumns == null) {
				 objPUCollection.add("E_001", "您输入的"+objPredictwaybillColumns.getPwbpwb_consigneecity() + 
						 "城市，" + objPredictwaybillColumns.getPwbpwb_consigneestate() + "省，邮编" + objPredictwaybillColumns.getPwbpwb_consigneepostcode() +
						 "不正确", 
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
		// 效验邮编是否正确
		AlldistictCondition objAlldistictCondition = new AlldistictCondition();
		//objAlldistictCondition.setDtname(objPredictwaybillColumns.getPwbpwb_consigneecity());
		if (StringUtility.isNull(strProvincename))
			objAlldistictCondition.setDtname(strCityname);
		else
			objAlldistictCondition.setStatename(strProvincename);
		
		if (StringUtility.isNull(strPostcode) || strPostcode.length() < 3) {
			 objPUCollection.add("E_001", "您输入的邮编不能为空或者邮编不正确", 
					 "PredictwaybillCheck.check");
			 return "";			
		}
		
		if (!StringUtility.isNull(strPostcode)) {
			if (!StringUtility.isNull(strCityname) &&
					(strCityname.equals("深圳市") ||
							strCityname.equals("罗湖区") ||
							strCityname.equals("南山区") ||
							strCityname.equals("龙岗区") ||
							strCityname.equals("盐田区") ||
							strCityname.equals("福田区") ||
							strCityname.equals("宝安区"))) {
				strPostcode = strPostcode.substring(0, 3);
			} else {
				strPostcode = strPostcode.substring(0, 2);
			}
			objAlldistictCondition.setLikepostcode(strPostcode);
		}
		AlldistictColumns objADColumns = DistrictDemand.loadAlldistict(objAlldistictCondition);
		if (objADColumns == null) {
			 objPUCollection.add("E_001", "您输入的"+strCityname + 
					 "城市，" + StringUtility.replaceWhenNull(strProvincename, "") + "省，邮编" + 
					 strPostcode +
					 "不正确", 
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
			 objPUCollection.add("E_001", "不为暂存状态无法上传", "PredictwaybillCheck.check");
			 return objPUCollection;
		}
		if (StringUtility.isNull(objPredictwaybillColumns.getChnchn_code())) {
			 objPUCollection.add("E_001", "服务渠道为空，无法上传", "PredictwaybillCheck.check");
			 return objPUCollection;
		}		
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		objPUCollection = objCoreWayBillCheck.checkCEWBCodeWhenPredict(objPredictwaybillColumns.getPwbpwb_orderid(),
				objPredictwaybillColumns.getCoco_code());
		return objPUCollection;
	}	
	
}
