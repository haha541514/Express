package kyle.leis.eo.operation.specialtype.test;

import kyle.leis.eo.operation.specialtype.da.SpecialtypeCondition;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeQuery;
import kyle.leis.eo.operation.specialtype.tp.TestJDBCTransaction;

public class SpecialtypeTest {
	
	public static void query() throws Exception {
		SpecialtypeCondition objSTCondition = new SpecialtypeCondition();
		objSTCondition.setUseCacheSign(true);
		
		SpecialtypeQuery objSTQuery = new SpecialtypeQuery();
		objSTQuery.setCondition(objSTCondition);
		
		objSTQuery.getResults();
	}
	
	public static String cutPrefix() throws Exception {
		String strPostcode = "1111234";
		while (true) {
			if (strPostcode.startsWith("0"))
				strPostcode = strPostcode.substring(1);
			else
				break;
		}
		return strPostcode;
	}
	
	
	public static void testJDBCTransaction() throws Exception {
		TestJDBCTransaction obj = new TestJDBCTransaction();
		obj.execute();
	}
	
	public static void main(String[] args) {
		try {
			
			// query();
			// testJDBCTransaction();
			System.out.println(cutPrefix());
			System.out.println(cutPrefix().length());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
