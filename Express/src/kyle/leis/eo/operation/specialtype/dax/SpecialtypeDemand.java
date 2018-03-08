package kyle.leis.eo.operation.specialtype.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpressspecialtypeDC;

public class SpecialtypeDemand {	
	public static List load(String strCwcode) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return null;
		SpecialtypeQuery objSpecialtypeQuery = new SpecialtypeQuery();
		objSpecialtypeQuery.setCwcode(strCwcode);
		return objSpecialtypeQuery.getResults();
	}
	
	public static SpecialtypeColumns load(String strCwcode, 
			String strEstcode) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return null;
		SpecialtypeQuery objSpecialtypeQuery = new SpecialtypeQuery();
		objSpecialtypeQuery.setCwcode(strCwcode);
		objSpecialtypeQuery.setEstcode(strEstcode);
		List objList = objSpecialtypeQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (SpecialtypeColumns)objList.get(0);
	}
	
	public static boolean isExistsSpecialtype(List listSpecialtype, 
			String strEstcode) {
		if (listSpecialtype == null || listSpecialtype.size() < 1)
			return false;
		for (int i = 0; i < listSpecialtype.size(); i++) {
			SpecialtypeColumns objSpecialtypeColumns = (SpecialtypeColumns)listSpecialtype.get(i);
			if (objSpecialtypeColumns.getWbstcomp_idestcode().equals(strEstcode))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断是否存在主键对应的特殊类型
	 * @param strEstCode
	 * @return
	 */
	public static boolean isExistEst(String strEstCode)
	{
		try
		{
			if(TdiExpressspecialtypeDC.loadByKey(strEstCode)!=null)
				return true;
			return false;
		}catch(Exception e)
		{
//			e.printStackTrace();
			return false;
		}
	}
	
	public static String getBusinessLog(List listSpecialtype) throws Exception {
		if (listSpecialtype == null || listSpecialtype.size() < 1)
			return "空";
		String strBusinessLog = "";
		for (int i = 0; i < listSpecialtype.size(); i++) {
			SpecialtypeColumns objSpecialtypeColumns = (SpecialtypeColumns)listSpecialtype.get(i);
			strBusinessLog = strBusinessLog + objSpecialtypeColumns.getEstestname();
			if (i < listSpecialtype.size() - 1)
				strBusinessLog = strBusinessLog + "、";
		}
		return strBusinessLog;
	}
}
