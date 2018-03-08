package kyle.leis.fs.dictionary.district.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.TntremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.TntremotedistrictCondition;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteDistrictDemand;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteVerifyResult;

public class TNTRemoteDistrict {
	public DHLRemoteVerifyResult verify(String strCountryHubcode, 
			String strPostcode, 
			String strCityname) throws Exception {
		DHLRemoteVerifyResult objDHLRVResult = new DHLRemoteVerifyResult();
		// 国家为空
		if (StringUtility.isNull(strCountryHubcode))
			return objDHLRVResult;
		// 首先检查邮编
		List listRemotes = new ArrayList();
		if (!StringUtility.isNull(strPostcode)) {
			TntremotedistrictCondition objFRDCondition = new TntremotedistrictCondition();
			objFRDCondition.setNationcode(strCountryHubcode);
			objFRDCondition.setPostcode(strPostcode);
			objFRDCondition.setPostcode2(strPostcode);
			listRemotes = DHLRemoteDistrictDemand.queryTNT(objFRDCondition);
		}
		if (listRemotes != null && listRemotes.size() > 0) {
			objDHLRVResult.setDHLRemoteVerifyResult(true);
			TntremotedistrictColumns objFRDColumns = (TntremotedistrictColumns)listRemotes.get(0);
			DhlremotedistrictColumns obj = new DhlremotedistrictColumns();
			obj.setDrddrd_cityname("TNT城市为空");
			obj.setDrddrd_nationcode(strCountryHubcode);
			obj.setDrddrd_nationname(objFRDColumns.getTrdtrd_nationname());
			obj.setDrddrd_postcode(strPostcode);
			objDHLRVResult.setDHLRemoteDistrict(obj);
		}
		return objDHLRVResult;
	}
}
