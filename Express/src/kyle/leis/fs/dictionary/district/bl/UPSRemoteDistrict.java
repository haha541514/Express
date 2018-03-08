package kyle.leis.fs.dictionary.district.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.UpsremotedistrictColumns;
import kyle.leis.fs.dictionary.district.da.UpsremotedistrictCondition;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteDistrictDemand;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteVerifyResult;

public class UPSRemoteDistrict {
	public DHLRemoteVerifyResult verify(String strCountryHubcode, 
			String strPostcode, 
			String strCityname) throws Exception {
		DHLRemoteVerifyResult objDHLRVResult = new DHLRemoteVerifyResult();
		// ����Ϊ��
		if (StringUtility.isNull(strCountryHubcode))
			return objDHLRVResult;
		// ���ȼ���ʱ�
		List listRemotes = new ArrayList();
		if (!StringUtility.isNull(strPostcode)) {
			UpsremotedistrictCondition objURDCondition = new UpsremotedistrictCondition();
			objURDCondition.setNationcode(strCountryHubcode);
			objURDCondition.setPostcode(strPostcode);
			objURDCondition.setPostcode2(strPostcode);
			listRemotes = DHLRemoteDistrictDemand.queryUps(objURDCondition);
		}
		// �ʱ�����Ϊ�ջ򲻺��ʱ�ʱ��ʼ����Ч��
		if ((listRemotes == null || listRemotes.size() < 1) &&
				!StringUtility.isNull(strCityname)) {
			UpsremotedistrictCondition objURDCondition = new UpsremotedistrictCondition();
			objURDCondition.setNationcode(strCountryHubcode);
			objURDCondition.setUrdcityname(strCityname);			
			listRemotes = DHLRemoteDistrictDemand.queryUps(objURDCondition);
		}
		if (listRemotes != null && listRemotes.size() > 0) {
			objDHLRVResult.setDHLRemoteVerifyResult(true);
			UpsremotedistrictColumns objURDColumns = (UpsremotedistrictColumns)listRemotes.get(0);
			DhlremotedistrictColumns obj = new DhlremotedistrictColumns();
			obj.setDrddrd_cityname(objURDColumns.getUrdurd_cityname());
			obj.setDrddrd_nationcode(strCountryHubcode);
			obj.setDrddrd_nationname(objURDColumns.getUrdurd_nationname());
			obj.setDrddrd_postcode(strPostcode);
			objDHLRVResult.setDHLRemoteDistrict(obj);
		}
		return objDHLRVResult;
	}
}
