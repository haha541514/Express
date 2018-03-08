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
		// ����Ϊ��
		if (StringUtility.isNull(strCountryHubcode))
			return objDHLRVResult;
		// ���ȼ���ʱ�
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
				// ����ʱ���ALLCODES�����
				objDRDCondition.setDrdnationcode(strCountryHubcode);
				objDRDCondition.setDrdcityname(strCityname);
				objDRDCondition.setDrdpostcode("ALLCODES");
				listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			}
		}
		// �ʱ�����Ϊ�ջ򲻺��ʱ�ʱ��ʼ����Ч��
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
		// ���ȼ���ʱ�
		List listDHLRemoteDistrict = new ArrayList();
		// ����Ϊ��
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
				// ����ʱ���ALLCODES�����
				objDRDCondition.setDrdnationcode(strCountryHubcode);
				objDRDCondition.setDrdcityname(strCityname);
				objDRDCondition.setDrdpostcode("ALLCODES");
				listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			}
		}
		// �ʱ�����Ϊ�ջ򲻺��ʱ�ʱ��ʼ����Ч��
		if (StringUtility.isNull(strPostcode) && !StringUtility.isNull(strCityname)) {
			DhlremotedistrictCondition objDRDCondition = new DhlremotedistrictCondition();
			objDRDCondition.setDrdnationcode(strCountryHubcode);
			objDRDCondition.setDrdcityname(strCityname);
			listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
		}
		return listDHLRemoteDistrict;
	}
	
	//ƫԶ��ѯ�����ط���,��һ���������ظ�����࣬���Ľ���	
	public List query(DhlremotedistrictCondition objDRDCondition,
			String strCountryHubcode, 
			String strPostcode, 
			String strCityname) throws Exception {
		// ���ȼ���ʱ�
		List listDHLRemoteDistrict = new ArrayList();
		// ����Ϊ��
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
				// ����ʱ���ALLCODES�����
				objDRDCondition.setDrdnationcode(strCountryHubcode);
				objDRDCondition.setDrdcityname(strCityname);
				objDRDCondition.setDrdpostcode("ALLCODES");
			
				listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
			}
		}
		// �ʱ�����Ϊ�ջ򲻺��ʱ�ʱ��ʼ����Ч��
		if (StringUtility.isNull(strPostcode) && !StringUtility.isNull(strCityname)) {
			objDRDCondition.setDrdnationcode(strCountryHubcode);
			objDRDCondition.setDrdcityname(strCityname);
			listDHLRemoteDistrict = DHLRemoteDistrictDemand.query(objDRDCondition);
		}
		return listDHLRemoteDistrict;
	}
	
	
	
	
	
	
}
