package kyle.leis.eo.operation.housewaybill.dax;

import java.util.List;

import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.housewaybill.da.ExportdataColumns;
import kyle.leis.eo.operation.housewaybill.da.ExportdataCondition;
import kyle.leis.eo.operation.housewaybill.da.ExportdataQuery;

public class ExportDataDemand {
   @SuppressWarnings("unchecked")
public static List query(ExportdataCondition objExportdataCondition) throws Exception{
	   ExportdataQuery objExportdataQuery=new ExportdataQuery();
	   objExportdataQuery.setCondition(objExportdataCondition);
	   List<ExportdataColumns> listExportdataColumns=objExportdataQuery.getResults();
	   System.out.println(listExportdataColumns.get(0).getCwcw_code());
	return queryGoods(listExportdataColumns);
   }
   @SuppressWarnings("unchecked")
public static List queryGoods(List<ExportdataColumns> listExportdataColumns) throws Exception{
	   List<CargoinfoColumns> listCargoinfoColumns=CargoInfoDemand.queryByCwCode(listExportdataColumns.get(0).getCwcw_code());
	   listExportdataColumns.get(0).setCici_currency(listCargoinfoColumns.get(0).getCkckcode());
	   listExportdataColumns.get(0).setDetailedGoodsDescription1(listCargoinfoColumns.get(0).getCiciename());
	   listExportdataColumns.get(0).setHSCode1(listCargoinfoColumns.get(0).getCicihscode());
	   listExportdataColumns.get(0).setQuantity1(listCargoinfoColumns.get(0).getCicipieces());
	   listExportdataColumns.get(0).setValue1(listCargoinfoColumns.get(0).getCiciunitprice());
	   listExportdataColumns.get(0).setCountryOfOrigin1("CH");
	   listExportdataColumns.get(0).setItemCode1(listCargoinfoColumns.get(0).getCiciattacheinfo());
	   if(listCargoinfoColumns.size()>=2){
		   listExportdataColumns.get(0).setDetailedGoodsDescription2(listCargoinfoColumns.get(1).getCiciename());
		   listExportdataColumns.get(0).setHSCode2(listCargoinfoColumns.get(1).getCicihscode());
		   listExportdataColumns.get(0).setQuantity2(listCargoinfoColumns.get(1).getCicipieces());
		   listExportdataColumns.get(0).setValue2(listCargoinfoColumns.get(1).getCiciunitprice()); 
		   listExportdataColumns.get(0).setCountryOfOrigin2("CH");
		   listExportdataColumns.get(0).setItemCode2(listCargoinfoColumns.get(1).getCiciattacheinfo());
	   }
	   if(listCargoinfoColumns.size()>=3){
		   listExportdataColumns.get(0).setDetailedGoodsDescription3(listCargoinfoColumns.get(2).getCiciename());
		   listExportdataColumns.get(0).setHSCode3(listCargoinfoColumns.get(2).getCicihscode());
		   listExportdataColumns.get(0).setQuantity3(listCargoinfoColumns.get(2).getCicipieces());
		   listExportdataColumns.get(0).setValue3(listCargoinfoColumns.get(2).getCiciunitprice());
		   listExportdataColumns.get(0).setCountryOfOrigin3("CH");
		   listExportdataColumns.get(0).setItemCode3(listCargoinfoColumns.get(2).getCiciattacheinfo());
	   }
	   if(listCargoinfoColumns.size()>=4){
		   listExportdataColumns.get(0).setDetailedGoodsDescription4(listCargoinfoColumns.get(3).getCiciename());
		   listExportdataColumns.get(0).setHSCode4(listCargoinfoColumns.get(3).getCicihscode());
		   listExportdataColumns.get(0).setQuantity4(listCargoinfoColumns.get(3).getCicipieces());
		   listExportdataColumns.get(0).setValue4(listCargoinfoColumns.get(3).getCiciunitprice()); 
		   listExportdataColumns.get(0).setCountryOfOrigin4("CH");
		   listExportdataColumns.get(0).setItemCode4(listCargoinfoColumns.get(3).getCiciattacheinfo());
	   }
	   double totalDeclaredValue=0.0;
	   for(int i=0;i<listCargoinfoColumns.size();i++){
		   totalDeclaredValue=totalDeclaredValue+ Double.parseDouble(listCargoinfoColumns.get(i).getCicitotalprice());
	   }
	   listExportdataColumns.get(0).setTotalDeclaredValue(String.valueOf(totalDeclaredValue));
	 return listExportdataColumns;
	   
   }
}
