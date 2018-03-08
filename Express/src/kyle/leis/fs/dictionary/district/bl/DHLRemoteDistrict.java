package kyle.leis.fs.dictionary.district.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictCondition;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteDistrictDemand;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteVerifyResult;

public class DHLRemoteDistrict {
	public DHLRemoteVerifyResult verify(String strCountryHubcode, 
			String strPostcode, 
			String strCityname) throws Exception {
		DHLRemoteVerifyResult objDHLRVResult = new DHLRemoteVerifyResult();
		// 国家为空
		if (StringUtility.isNull(strCountryHubcode))
			return objDHLRVResult;
		// 首先检查邮编
		List listDHLRemoteDistrict = new ArrayList();
		if (!StringUtility.isNull(strPostcode)) {
			/*
			while (true) {
				if (strPostcode.startsWith("0"))
					strPostcode = strPostcode.substring(1);
				else
					break;
			}*/
			DhlremotedistrictCondition objDRDCondition = new DhlremotedistrictCondition();
			objDRDCondition.setDrdnationcode(strCountryHubcode);
			objDRDCondition.setDrdpostcode(strPostcode);
			listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			if ((listDHLRemoteDistrict == null || listDHLRemoteDistrict.size() < 1) &&
					!StringUtility.isNull(strCityname)) {
				// 检查邮编是ALLCODES的情况
				objDRDCondition.setDrdnationcode(strCountryHubcode);
				objDRDCondition.setDrdcityname(strCityname);
				objDRDCondition.setDrdpostcode("ALLCODES");
				listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			}
		}
		// 邮编检查结果为空或不含邮编时开始城市效验
		if (StringUtility.isNull(strPostcode) && !StringUtility.isNull(strCityname)) {
			DhlremotedistrictCondition objDRDCondition = new DhlremotedistrictCondition();
			objDRDCondition.setDrdnationcode(strCountryHubcode);
			objDRDCondition.setDrdcityname(strCityname);
			listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
		}
		if (listDHLRemoteDistrict != null && listDHLRemoteDistrict.size() > 0) {
			objDHLRVResult.setDHLRemoteVerifyResult(true);
			objDHLRVResult.setDHLRemoteDistrict((DhlremotedistrictColumns)listDHLRemoteDistrict.get(0));
		}
		return objDHLRVResult;
	}
	
	
	public List query(String strCountryHubcode, 
			String strPostcode, 
			String strCityname) throws Exception {
		// 首先检查邮编
		List listDHLRemoteDistrict = new ArrayList();
		// 国家为空
		if (StringUtility.isNull(strCountryHubcode))
			return null;
		if (!StringUtility.isNull(strPostcode)) {
			/*
			while (true) {
				if (strPostcode.startsWith("0"))
					strPostcode = strPostcode.substring(1);
				else
					break;
			}*/			
			DhlremotedistrictCondition objDRDCondition = new DhlremotedistrictCondition();
			objDRDCondition.setDrdnationcode(strCountryHubcode);
			objDRDCondition.setDrdpostcode(strPostcode);
			listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			if ((listDHLRemoteDistrict == null || listDHLRemoteDistrict.size() < 1) &&
					!StringUtility.isNull(strCityname)) {
				// 检查邮编是ALLCODES的情况
				objDRDCondition.setDrdnationcode(strCountryHubcode);
				objDRDCondition.setDrdcityname(strCityname);
				objDRDCondition.setDrdpostcode("ALLCODES");
				listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			}
		}
		// 邮编检查结果为空或不含邮编时开始城市效验
		if (StringUtility.isNull(strPostcode) && !StringUtility.isNull(strCityname)) {
			DhlremotedistrictCondition objDRDCondition = new DhlremotedistrictCondition();
			objDRDCondition.setDrdnationcode(strCountryHubcode);
			objDRDCondition.setDrdcityname(strCityname);
			listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
		}
		return listDHLRemoteDistrict;
	}
	
	//偏远查询的重载方法,多一个参数（重复代码多，待改进）	
	public List query(DhlremotedistrictCondition objDRDCondition,
			String strCountryHubcode, 
			String strPostcode, 
			String strCityname) throws Exception {
		// 首先检查邮编
		List listDHLRemoteDistrict = new ArrayList();
		// 国家为空
		if (StringUtility.isNull(strCountryHubcode))
			return null;
		
		if (!StringUtility.isNull(strPostcode)) {
			/*while (true) {
				if (strPostcode.startsWith("0"))
					strPostcode = strPostcode.substring(1);
				else
					break;
			}*/
			objDRDCondition.setDrdnationcode(strCountryHubcode);
			objDRDCondition.setDrdpostcode(strPostcode);
		
			listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			if ((listDHLRemoteDistrict == null || listDHLRemoteDistrict.size() < 1) &&
					!StringUtility.isNull(strCityname)) {
				// 检查邮编是ALLCODES的情况
				objDRDCondition.setDrdnationcode(strCountryHubcode);
				objDRDCondition.setDrdcityname(strCityname);
				objDRDCondition.setDrdpostcode("ALLCODES");
			
				listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			}
		}
		// 邮编检查结果为空或不含邮编时开始城市效验
		if (StringUtility.isNull(strPostcode) && !StringUtility.isNull(strCityname)) {
			objDRDCondition.setDrdnationcode(strCountryHubcode);
			objDRDCondition.setDrdcityname(strCityname);
			listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
		}
		return listDHLRemoteDistrict;
	}
	
	
	
	
	
	
}
