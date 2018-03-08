package kyle.leis.es.systemcertification.dax;


import java.util.Date;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.es.systemcertification.da.SystemcertificationColumns;
import kyle.leis.es.systemcertification.da.SystemcertificationCondition;
import kyle.leis.es.systemcertification.da.SystemcertificationQuery;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TesSystemcertification;
import net.sf.hibernate.Session;

public class SystemCertificationDemand {

	public static List query(SystemcertificationCondition objSystemcertificationCon) throws Exception
	{
		SystemcertificationQuery objSystemcertificationQu = new SystemcertificationQuery();
		objSystemcertificationQu.setCondition(objSystemcertificationCon);
		List objList = objSystemcertificationQu.getResults();
		if(CollectionUtility.isNull(objList)) return null;
		return objList;
	}
	
	public static void setSystemcertificationByColumns(TesSystemcertification objTesSystemcertification,SystemcertificationColumns objSystemcertificationCol,String strOperId, Session objSession) throws Exception
	{
		Date objToday = DateFormatUtility.getSysdate();
		String strToday = new String(DateFormatUtility.getStandardSysdate());
		objTesSystemcertification.setScEnddate(DateFormatUtility.getStandardDate(strToday.substring(0,3)+(Integer.parseInt(strToday.substring(3,4))+1)+strToday.substring(4,10)+" 23:59:59"));
		objTesSystemcertification.setScHdserialnumber(objSystemcertificationCol.getScschdserialnumber());
		objTesSystemcertification.setScIpaddress(objSystemcertificationCol.getScscipaddress());
		objTesSystemcertification.setScMacaddress(objSystemcertificationCol.getScscmacaddress());
		objTesSystemcertification.setScModifydate(objToday);
		objTesSystemcertification.setScStartdate(DateFormatUtility.getStandardDate(strToday.substring(0,10)+" 00:00:00"));
		
		//objTesSystemcertification.setTdiOperatorByScOpIdModify(TdiOperatorDC.loadByKey(strOperId));
		objTesSystemcertification.setTdiOperatorByScOpIdModify((TdiOperator)objSession.load(TdiOperator.class, Long.valueOf(strOperId)));

		objTesSystemcertification.setScApplydate(objToday);
		objTesSystemcertification.setTdiOperatorByScOpIdApply((TdiOperator)objSession.load(TdiOperator.class, Long.valueOf(strOperId)));
		objTesSystemcertification.setTdiSimplestatus((TdiSimplestatus)objSession.load(TdiSimplestatus.class, "NW"));
		
	}
}
