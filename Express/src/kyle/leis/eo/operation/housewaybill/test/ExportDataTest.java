package kyle.leis.eo.operation.housewaybill.test;

import java.util.List;

import kyle.leis.eo.operation.housewaybill.da.ExportdataColumns;
import kyle.leis.eo.operation.housewaybill.da.ExportdataCondition;
import kyle.leis.eo.operation.housewaybill.da.ExportdataQuery;
import kyle.leis.eo.operation.housewaybill.dax.ExportDataDemand;

public class ExportDataTest {
   public static void main(String[] args) throws Exception {
	   /*
	   ExportdataCondition objExportdataCondition =new ExportdataCondition();
	   objExportdataCondition.setServerewbcode("BQC1408140002");
	   List<ExportdataColumns> listExportdataColumns=ExportDataDemand.query(objExportdataCondition);
	   for(int i=0;i<listExportdataColumns.size();i++){
		   System.out.println(listExportdataColumns.get(i).getCwchn_code_supplier()+"||"+listExportdataColumns.get(i).getCwcw_customerewbcode()+"||"+
				   listExportdataColumns.get(i).getDetailedGoodsDescription1()+"||"+listExportdataColumns.get(i).getDetailedGoodsDescription2()
				   +"||"+listExportdataColumns.get(i).getCwcw_code());
	   }
	   */
	   
	   String strCargoname = "tester|²âÊÔÒÇ";
		String[] astr = new String[2];
		if (strCargoname.indexOf("|") > 0) {
			astr[0] = strCargoname.substring(0, strCargoname.indexOf("|"));
			astr[1] = strCargoname.substring(strCargoname.indexOf("|") + 1);
		} else {
			astr[0] = strCargoname;
			astr[1] = strCargoname;
		}
	   System.out.println(astr[0] + "   " + astr[1]);
   }
}
