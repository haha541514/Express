package kyle.leis.ds.report.finance.test;

import java.util.List;
import java.util.ArrayList;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcoColumns;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcoCondition;
import kyle.leis.ds.report.finance.dax.FinancereportDemand;

public class FinancereportTest {
	public static void main(String[] args) {
		try {
			// System.out.println(queryFeegroupbyCo());
			testIf();
			// queryFeegroupbyCo();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static void testIf() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		
		List<String> list2 = new ArrayList<String>();
		list2.add("aaa");
		for (int i = 0; i < list2.size(); i++) {
			for (String str : list)
				list2.add(str);
		}
		System.out.println("sss");
	}
	
	
	public static String queryFeegroupbyCo() throws Exception {
		FeegroupbyonylcoCondition objFGCCondition = new FeegroupbyonylcoCondition();
		//objFGCCondition.setEndadddate("2010-02-01");
		List objList = FinancereportDemand.queryFeegroupOnlybyCo(objFGCCondition);
		FeegroupbyonylcoColumns objColumns = (FeegroupbyonylcoColumns) objList.get(0);
		System.out.println("=-============"+objColumns.getCoco_labelcode());
		return objList.toString();
		
	}
}
