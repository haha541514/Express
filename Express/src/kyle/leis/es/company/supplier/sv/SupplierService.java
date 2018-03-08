package kyle.leis.es.company.supplier.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.company.companys.sv.CompanysService;
import kyle.leis.es.company.supplier.bl.Supplier;
import kyle.leis.es.company.supplier.da.SupplierColumns;
import kyle.leis.es.company.supplier.dax.SupplierDemand;
import kyle.leis.fs.cachecontainer.da.SupplierCondition;

public class SupplierService extends CompanysService {
	/**
	 * 获得Manifest号码
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String getManifestSeriesNumber(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strCocode = (String)objPD.getParameter(0, String.class);
		String strSeriesNumber = SupplierDemand.getManifestSeriesNumber(strCocode);
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strSeriesNumber);
		return objEncode.toString();
	}
	
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		SupplierCondition objSPCondition = (SupplierCondition)objPD.getParameter(0, 
				SupplierCondition.class);
		List objList = SupplierDemand.query(objSPCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		SupplierColumns objSupplierColumns = (SupplierColumns)objPD.getParameter(0, 
				SupplierColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Supplier objSupplier = new Supplier();
		List objList = objSupplier.save(objSupplierColumns, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
}
