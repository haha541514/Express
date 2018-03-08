package kyle.leis.es.company.companys.dax;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.companys.da.CorporationseqColumns;
import kyle.leis.es.company.companys.da.CorporationseqQuery;
import kyle.leis.es.company.customer.da.CustomerColumns;
import kyle.leis.es.company.supplier.da.SupplierColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCorporationstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCustomersuppliertypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiCorporationstatus;
import kyle.leis.hi.TdiCustomersuppliertype;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiOperator;

public class CompanysDemand {
	public static void setCompanyByCustomer(TcoCorporation objTcoCorporation,
			CustomerColumns objCustomerColumns,
			String strOperId) throws Exception {
		objTcoCorporation.setCoAddress(objCustomerColumns.getCocoaddress());
		objTcoCorporation.setCoEname(objCustomerColumns.getCocoename());
		objTcoCorporation.setCoLabelcode(objCustomerColumns.getCocolabelcode());
		objTcoCorporation.setCoModifydate(DateFormatUtility.getSysdate());
		objTcoCorporation.setCoName(objCustomerColumns.getCoconame());
		objTcoCorporation.setCoPostcode(objCustomerColumns.getCocopostcode());
		objTcoCorporation.setCoRemark(objCustomerColumns.getCocoremark());
		objTcoCorporation.setCoSename(objCustomerColumns.getCocosename());
		objTcoCorporation.setCoSname(objCustomerColumns.getCocosname());
		objTcoCorporation.setCoWebsite(objCustomerColumns.getCocowebsite());
		
		if (!StringUtility.isNull(objCustomerColumns.getCococarryoversign()) &&
				objCustomerColumns.getCococarryoversign().equals("Y")) {
			objTcoCorporation.setCoCarryoversign("Y");
			objTcoCorporation.setCoCarryoverdate(DateFormatUtility.getStandardDate(objCustomerColumns.getCococarryoverdate()));
		}
		if (StringUtility.isNull(objCustomerColumns.getCococarryoversign()) ||
				(!StringUtility.isNull(objCustomerColumns.getCococarryoversign()) && 
						objCustomerColumns.getCococarryoversign().equals("N"))) {
			objTcoCorporation.setCoCarryoversign("N");
			objTcoCorporation.setCoCarryoverdate(null);
		}
		
		TdiCorporationstatus objTCS = TdiCorporationstatusDC.loadByKey(objCustomerColumns.getCoscscode());
		objTcoCorporation.setTdiCorporationstatus(objTCS);
		
		TdiCustomersuppliertype objTCST = TdiCustomersuppliertypeDC.loadByKey(objCustomerColumns.getCstcstcode());
		objTcoCorporation.setTdiCustomersuppliertype(objTCST);
		
		TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objCustomerColumns.getEeeecode());
		objTcoCorporation.setTdiEnterpriseelement(objTEE);
		
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
		objTcoCorporation.setTdiOperatorByCoOpIdModify(objTdiOperator);
	}
	
	public static String getNewCorporationcode() throws Exception {
		CorporationseqQuery objCorporationseqQuery = new CorporationseqQuery();
		List objList = objCorporationseqQuery.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception ("无法根据公司的序列号生成主键!"));
		return ((CorporationseqColumns)objList.get(0)).getCorporationseq();
	}
	
	public static void setCompanyBySuppier(TcoCorporation objTcoCorporation,
			SupplierColumns objSupplierColumns,
			String strOperId) throws Exception {
		objTcoCorporation.setCoAddress(objSupplierColumns.getCocoaddress());
		objTcoCorporation.setCoEname(objSupplierColumns.getCocoename());
		objTcoCorporation.setCoLabelcode(objSupplierColumns.getCocolabelcode());
		objTcoCorporation.setCoModifydate(DateFormatUtility.getSysdate());
		objTcoCorporation.setCoName(objSupplierColumns.getCoconame());
		objTcoCorporation.setCoPostcode(objSupplierColumns.getCocopostcode());
		objTcoCorporation.setCoRemark(objSupplierColumns.getCocoremark());
		objTcoCorporation.setCoSename(objSupplierColumns.getCocosename());
		objTcoCorporation.setCoSname(objSupplierColumns.getCocosname());
		objTcoCorporation.setCoWebsite(objSupplierColumns.getCocowebsite());
		
		if (!StringUtility.isNull(objSupplierColumns.getCococarryoversign()) &&
				objSupplierColumns.getCococarryoversign().equals("Y")) {
			objTcoCorporation.setCoCarryoversign("Y");
			objTcoCorporation.setCoCarryoverdate(DateFormatUtility.getStandardDate(objSupplierColumns.getCococarryoverdate()));
		}
		if (StringUtility.isNull(objSupplierColumns.getCococarryoversign()) ||
				(!StringUtility.isNull(objSupplierColumns.getCococarryoversign()) && 
						objSupplierColumns.getCococarryoversign().equals("N"))) {
			objTcoCorporation.setCoCarryoversign("N");
			objTcoCorporation.setCoCarryoverdate(null);
		}		
		
		
		TdiCorporationstatus objTCS = TdiCorporationstatusDC.loadByKey(objSupplierColumns.getCoscscode());
		objTcoCorporation.setTdiCorporationstatus(objTCS);
		
		TdiCustomersuppliertype objTCST = TdiCustomersuppliertypeDC.loadByKey(objSupplierColumns.getCstcstcode());
		objTcoCorporation.setTdiCustomersuppliertype(objTCST);
		
		TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objSupplierColumns.getEeeecode());
		objTcoCorporation.setTdiEnterpriseelement(objTEE);
		
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
		objTcoCorporation.setTdiOperatorByCoOpIdModify(objTdiOperator);
	}
	
}
