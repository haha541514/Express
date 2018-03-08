package kyle.leis.fs.dictionary.district.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.FdxremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.FdxremotedistrictCondition;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteDistrictDemand;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteVerifyResult;

public class FDXRemoteDistrict {
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
			FdxremotedistrictCondition objFRDCondition = new FdxremotedistrictCondition();
			objFRDCondition.setNationcode(strCountryHubcode);
			objFRDCondition.setPostcode(strPostcode);
			objFRDCondition.setPostcode2(strPostcode);
			listRemotes = DHLRemoteDistrictDemand.queryFedex(objFRDCondition);
		}
		// 邮编检查结果为空或不含邮编时开始城市效验
		if ((listRemotes == null || listRemotes.size() < 1) &&
				!StringUtility.isNull(strCityname)) {
			FdxremotedistrictCondition objFRDCondition = new FdxremotedistrictCondition();
			objFRDCondition.setNationcode(strCountryHubcode);
			objFRDCondition.setFrdcityname(strCityname);			
			listRemotes = DHLRemoteDistrictDemand.queryFedex(objFRDCondition);
		}
		if (listRemotes != null && listRemotes.size() > 0) {
			objDHLRVResult.setDHLRemoteVerifyResult(true);
			FdxremotedistrictColumns objFRDColumns = (FdxremotedistrictColumns)listRemotes.get(0);
			DhlremotedistrictColumns obj = new DhlremotedistrictColumns();
			obj.setDrddrd_cityname(objFRDColumns.getFrdfrd_cityname());
			obj.setDrddrd_nationcode(strCountryHubcode);
			obj.setDrddrd_nationname(objFRDColumns.getFrdfrd_nationname());
			obj.setDrddrd_postcode(strPostcode);
			objDHLRVResult.setDHLRemoteDistrict(obj);
		}
		return objDHLRVResult;
	}
}
