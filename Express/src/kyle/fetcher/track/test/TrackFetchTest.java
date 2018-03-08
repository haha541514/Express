package kyle.fetcher.track.test;

import kyle.common.util.jlang.DateFormatUtility;

public class TrackFetchTest {
	public static void main(String[] args) {
		try {
			System.out.println(getDBDateString());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String getDBDateString() {
		String str = "to_date('January 11, 2010    12:38', 'month dd, yyyy hh24:mi', 'NLS_DATE_LANGUAGE=American')";
		String strSysdate = DateFormatUtility.getStandardSysdate();
		strSysdate = "2015-10-12 01:23:43";
		System.out.println(strSysdate);
		System.out.println(strSysdate.substring(11,13));
		if (strSysdate.substring(11,13).compareTo("20") < 0 &&
				strSysdate.substring(11,13).compareTo("08") >= 0)
			System.out.println("true");
		return "";
		//return DateFormatUtility.getDBDateString(str);
	}
}
