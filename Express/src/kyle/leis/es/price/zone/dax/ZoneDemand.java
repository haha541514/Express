package kyle.leis.es.price.zone.dax;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.zone.da.ZoneColumns;
import kyle.leis.es.price.zone.da.ZoneCondition;
import kyle.leis.es.price.zone.da.ZoneQuery;
import kyle.leis.es.price.zone.da.ZonedistrictpostcodeColumns;
import kyle.leis.es.price.zone.da.ZonedistrictpostcodeCondition;
import kyle.leis.es.price.zone.da.ZonedistrictpostcodeQuery;
import kyle.leis.es.price.zone.da.ZonevalueCondition;
import kyle.leis.es.price.zone.da.ZonevalueQuery;
import kyle.leis.es.price.zone.da.ZonevaluedistrictColumns;
import kyle.leis.es.price.zone.da.ZonevaluedistrictCondition;
import kyle.leis.es.price.zone.da.ZonevaluedistrictQuery;
import kyle.leis.es.price.zone.da.ZonezfcodeColumns;
import kyle.leis.es.price.zone.da.ZonezfcodeCondition;
import kyle.leis.es.price.zone.da.ZonezfcodeQuery;
import kyle.leis.fs.cachecontainer.da.DistrictColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TdiZoneformat;
import kyle.leis.hi.TepZone;

public class ZoneDemand {
	/**
	 * ��ѯ����
	 * @param objZoneCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryZone(ZoneCondition objZoneCondition) 
	throws Exception {
		ZoneQuery objZoneQuery = new ZoneQuery();
		objZoneQuery.setCondition(objZoneCondition);
		return objZoneQuery.getResults();
	}
	
	/**
	 * ��ѯ����ֵ
	 * @param objZnvCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryZoneValue(ZonevalueCondition objZnvCondition) 
	throws Exception {
		ZonevalueQuery objZonevalueQuery = new ZonevalueQuery();
		objZonevalueQuery.setCondition(objZnvCondition);
		return objZonevalueQuery.getResults();
	}	
	
	public static List loadZoneValue(String strZncode) 
	throws Exception{
		ZonevalueQuery objZonevalueQuery = new ZonevalueQuery();
		objZonevalueQuery.setZncode(strZncode);
		return objZonevalueQuery.getResults();
	}	
	
	/**
	 * ��ѯ����ֵ����
	 * @param strZncode
	 * @return
	 * @throws Exception
	 */
	public static List loadZoneValueDistrict(String strZncode) 
	throws Exception {
		ZonevaluedistrictQuery objZoneValueDQ = new ZonevaluedistrictQuery();
		objZoneValueDQ.setZncode(strZncode);
		return objZoneValueDQ.getResults();
	}
	
	public static List queryZoneValueDistrict(ZonevaluedistrictCondition objZnvdCondition) 
	throws Exception {
		ZonevaluedistrictQuery objZnvdQuery = new ZonevaluedistrictQuery();
		objZnvdQuery.setCondition(objZnvdCondition);
		return objZnvdQuery.getResults();
	}
	
	/**
	 * ��ѯ�����ʱ�
	 * @param objZdpcCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryZdpostcode(ZonedistrictpostcodeCondition objZdpcCondition) 
	throws Exception {
		ZonedistrictpostcodeQuery objZdpcQuery = new ZonedistrictpostcodeQuery();
		objZdpcQuery.setCondition(objZdpcCondition);
		return objZdpcQuery.getResults();
	}	
	
	public static List loadZoneDistrictPostcode(String strZncode) 
	throws Exception {
		ZonedistrictpostcodeQuery objZonePostcodeQ = new ZonedistrictpostcodeQuery();
		objZonePostcodeQ.setZncode(strZncode);
		return objZonePostcodeQ.getResults();
	}
	
	public static ZoneQueryReturn loadByZncode(String strZncode) 
	throws Exception {
		ZoneQueryReturn objZoneQueryReturn = new ZoneQueryReturn();
		// ��ѯ����
		ZoneCondition objZoneCondition = new ZoneCondition();
		objZoneCondition.setZncode(strZncode);
		List listZone = queryZone(objZoneCondition);
		objZoneQueryReturn.setZoneColumns((ZoneColumns)listZone.get(0));
		// װ�ط���ֵ
		List listZoneValue = ZoneDemand.loadZoneValue(strZncode);
		objZoneQueryReturn.setZoneValue(listZoneValue);
		// ������������
		List listZoneValueDistrict = ZoneDemand.loadZoneValueDistrict(strZncode);
		objZoneQueryReturn.setZoneValueDistrict(listZoneValueDistrict);
		// �����ʱ�����
		List listZoneDistrictPostcode = ZoneDemand.loadZoneDistrictPostcode(strZncode);
		objZoneQueryReturn.setZoneDistrictPostcode(listZoneDistrictPostcode);
		
		return objZoneQueryReturn;
	}
	
	/**
	 * ����ʱ����Tableֵ
	 * @param objTepZone
	 * @param objZoneColumns
	 * @param strOperId
	 * @param objSession
	 * @throws Exception
	 */
	public static void setZoneByColumns(TepZone objTepZone, 
			ZoneColumns objZoneColumns,
			String strOperId, 
			Session objSession)
	throws Exception {
		objTepZone.setZnModifydate(DateFormatUtility.getSysdate());
		objTepZone.setZnEname(objZoneColumns.getZnznename());
		objTepZone.setZnKeywords(objZoneColumns.getZnznkeywords());
		objTepZone.setZnName(objZoneColumns.getZnznname());
		objTepZone.setZnRemark(objZoneColumns.getZnznremark());
		
		TdiZoneformat objZoneformat=new TdiZoneformat();
		objZoneformat.setZfCode(objZoneColumns.getZfzfcode());
		objTepZone.setTdiZoneformat(objZoneformat);
		
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(strOperId));
			objTepZone.setTdiOperatorByZnOpIdModify(objTdiOperator);
		}
		if (!StringUtility.isNull(objZoneColumns.getPkpkcode())) {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(objZoneColumns.getPkpkcode());
			objTepZone.setTdiProductkind(objTdiProductkind);
		}
		TdiSimplestatus objTdiSimplestatus = TdiSimplestatusDC.loadByKey(objZoneColumns.getSssscode());
		objTepZone.setTdiSimplestatus(objTdiSimplestatus);
	}
	
	/**
	 * �ӷ���ֵ�����л�ȡ����ֵ������ͬ�ķ���
	 * @param strZnvname
	 * @param listZnvdistrictCol
	 * @return
	 */
	public static List<ZonevaluedistrictColumns> getZoneValueDistrict(String strZnvname, 
			List listZnvdistrictCol) {
		List<ZonevaluedistrictColumns> listZnvdColumns = new ArrayList<ZonevaluedistrictColumns>();
		if (listZnvdistrictCol == null || listZnvdistrictCol.size() < 1) 
			return null;
		
		for (int i = 0; i < listZnvdistrictCol.size(); i++) {
			ZonevaluedistrictColumns objZnvdColumns = (ZonevaluedistrictColumns)listZnvdistrictCol.get(i);
			if (objZnvdColumns.getZnvznvname().equals(strZnvname))
				listZnvdColumns.add(objZnvdColumns);
		}
		return listZnvdColumns;
	}
	
	/**
	 * 
	 * @param strZnvname
	 * @param strDtcode
	 * @param listZvdPostcodeCol
	 * @return
	 */
	public static List<ZonedistrictpostcodeColumns> getZvdistrictPostcode(String strZnvname,
			String strDtcode,
			List listZvdPostcodeCol) {
		List<ZonedistrictpostcodeColumns> listResult = new ArrayList<ZonedistrictpostcodeColumns>();
		if (listZvdPostcodeCol == null || listZvdPostcodeCol.size() < 1)
			return null;
		
		for (int i = 0; i < listZvdPostcodeCol.size(); i++) {
			ZonedistrictpostcodeColumns objznpColumns = (ZonedistrictpostcodeColumns)listZvdPostcodeCol.get(i);
			if (objznpColumns.getZnvznvname().equals(strZnvname) &&
					objznpColumns.getZnvdcomp_iddtcode().equals(strDtcode))
				listResult.add(objznpColumns);
		}
		return listResult;
	}
	
	public static String getHKZnvnameByDistrict(String strZncode,
			String strDtcode) throws Exception {
		ZonevaluedistrictCondition objZnvdCondition = new ZonevaluedistrictCondition();
		objZnvdCondition.setUseCacheSign(true);
		objZnvdCondition.setDtcode(strDtcode);
		objZnvdCondition.setZncode(strZncode);
		List objList = queryZoneValueDistrict(objZnvdCondition);
		if (objList == null || objList.size() < 1)
			return "";
		ZonevaluedistrictColumns objZVDC = (ZonevaluedistrictColumns)objList.get(0);
		return objZVDC.getZnvznvname();
	}
	
	public static String getHKZnvenameByDistrict(String strZncode,
			String strDtcode) throws Exception {
		ZonevaluedistrictCondition objZnvdCondition = new ZonevaluedistrictCondition();
		objZnvdCondition.setUseCacheSign(true);
		objZnvdCondition.setDtcode(strDtcode);
		objZnvdCondition.setZncode(strZncode);
		List objList = queryZoneValueDistrict(objZnvdCondition);
		if (objList == null || objList.size() < 1)
			return "";
		ZonevaluedistrictColumns objZVDC = (ZonevaluedistrictColumns)objList.get(0);
		return objZVDC.getZnvznvename();
	}	
	
	
	public static int getZnvidByDistrict(String strDtcode, 
			String strPostcode, 
			String strZncode) throws Exception {
		// �Ȳ����ʱ�
		if (!StringUtility.isNull(strPostcode)) {
			ZonedistrictpostcodeCondition objZdpcCondition = new ZonedistrictpostcodeCondition();
			objZdpcCondition.setUseCacheSign(true);
			objZdpcCondition.setDtcode(strDtcode);
			objZdpcCondition.setValidpostcode1(strPostcode);
			objZdpcCondition.setValidpostcode2(strPostcode);
			objZdpcCondition.setZncode(strZncode);
			List objList = queryZdpostcode(objZdpcCondition);
			if (objList != null && objList.size() == 1) {
				ZonedistrictpostcodeColumns objZdpColumns = (ZonedistrictpostcodeColumns)objList.get(0);
				return Integer.parseInt(objZdpColumns.getZnvcomp_idznvid());
			}
		}
		// ���ҳ���
		if (!StringUtility.isNull(strDtcode)) {
			ZonevaluedistrictCondition objZnvdCondition = new ZonevaluedistrictCondition();
			objZnvdCondition.setUseCacheSign(true);
			objZnvdCondition.setDtcode(strDtcode);
			objZnvdCondition.setZncode(strZncode);
			List objList = queryZoneValueDistrict(objZnvdCondition);
			if (objList != null && objList.size() > 0) {
				// �������ʱ������������ķ���ֻ��һ���ķ���ֵ������ȷ�ķ���ֵ
				int iZonevalueNumbers = 0;
				String strZnvid = "-1";
				for (int i = 0; i < objList.size(); i++) {
					ZonevaluedistrictColumns objZvdColumns = (ZonevaluedistrictColumns)objList.get(i);
					ZonedistrictpostcodeCondition objZdpcCondition = new ZonedistrictpostcodeCondition();
					objZdpcCondition.setUseCacheSign(true);
					objZdpcCondition.setDtcode(objZvdColumns.getDtdtcode());
					objZdpcCondition.setZncode(strZncode);
					objZdpcCondition.setZnvid(objZvdColumns.getZnvcomp_idznvid());
					List objListPostcode = queryZdpostcode(objZdpcCondition);
					if (objListPostcode == null || objListPostcode.size() < 1) {
						strZnvid = objZvdColumns.getZnvcomp_idznvid();
						iZonevalueNumbers++;
					}
				}
				if (iZonevalueNumbers == 1)
					return Integer.parseInt(strZnvid);
			}
		}
		// δ���ҵ��򰴹��Ҳ���
		DistrictColumns objDistrictColumns = DistrictDemand.load(strDtcode);
		if (!objDistrictColumns.getDtcountrydtcode().equals(objDistrictColumns.getDtdtcode())) {
			return getZnvidByDistrict(objDistrictColumns.getDtcountrydtcode(),
					strPostcode,
					strZncode);
		}
		return -1;
	}
	
	//���ݷ�����ʽ������޸�ʱ���÷���
	public static String getZfCodeByCity(String znzfCode){
		ZonezfcodeCondition objzonzfCodeCondition=new ZonezfcodeCondition();
		objzonzfCodeCondition.setZnzfcode(znzfCode);
		ZonezfcodeQuery objZonezfcodeQuery=new ZonezfcodeQuery();
		objZonezfcodeQuery.setCondition(objzonzfCodeCondition);
		String znCode="";
		try {
			znCode=((ZonezfcodeColumns)objZonezfcodeQuery.getResults().get(0)).getZn_code();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return znCode;
	}
	
	public static String getZnvnameByDistrict(String strZncode,
			String strDtcode) throws Exception {
		ZonevaluedistrictCondition objZnvdCondition = new ZonevaluedistrictCondition();
		objZnvdCondition.setUseCacheSign(true);
		objZnvdCondition.setDtcode(strDtcode);
		objZnvdCondition.setZncode(strZncode);
		List objList = queryZoneValueDistrict(objZnvdCondition);
		if (objList == null || objList.size() < 1)
			return "";
		ZonevaluedistrictColumns objZVDC = (ZonevaluedistrictColumns)objList.get(0);
		return objZVDC.getZnvznvname();
	}
}
