package kyle.leis.eo.operation.housewaybill.dax;

import java.math.BigDecimal;



import net.sf.hibernate.Session;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.TophousewaybillTR;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBatterykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCargokindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDeliverytypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiInsurancebeneficiaryDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiInvoiceprinttypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPackagetypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybillcodekindDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiWaybillcodekind;
import kyle.leis.hi.TopHousewaybill;

public class InputDemand {
	
	public static void setHWBByInputAllColumns(TopHousewaybill objHouseWayBill, 
			ForinputallColumns objFIAColumns, 
			String strOperId, 
			Session objSession) 
	throws Exception {
		//objHouseWayBill.setCwCode(objFIAColumns.getCwcode());
		objHouseWayBill.setHwConsigneeaccount(objFIAColumns.getHwconsigneeaccount());
		objHouseWayBill.setHwConsigneeaddress1(objFIAColumns.getHwconsigneeaddress1());
		objHouseWayBill.setHwConsigneeaddress2(objFIAColumns.getHwconsigneeaddress2());
		objHouseWayBill.setHwConsigneeaddress3(objFIAColumns.getHwconsigneeaddress3());
		objHouseWayBill.setHwConsigneeaddressex(objFIAColumns.getHwconsigneeaddressex());
		
		objHouseWayBill.setHwConsigneecompany(objFIAColumns.getHwconsigneecompany());
		objHouseWayBill.setHwConsigneefax(objFIAColumns.getHwconsigneefax());
		objHouseWayBill.setHwConsigneename(objFIAColumns.getHwconsigneename());
		objHouseWayBill.setHwConsigneenameex(objFIAColumns.getHwconsigneenameex());
		
		objHouseWayBill.setHwConsigneepostcode(objFIAColumns.getHwconsigneepostcode());
		objHouseWayBill.setHwConsigneetelephone(objFIAColumns.getHwconsigneetelephone());
		objHouseWayBill.setHwInsurancesign(objFIAColumns.getHwinsurancesign());
		objHouseWayBill.setHwConsigneecity(objFIAColumns.getHwConsigneecity());
		objHouseWayBill.setHwConsigneecityex(objFIAColumns.getHwconsigneecityex());
		
		objHouseWayBill.setHwElectricmark(objFIAColumns.getHwelectricmark());
		// 保险价值以及币种
		if (!StringUtility.isNull(objFIAColumns.getHwinsurancevalue())) {
			objHouseWayBill.setHwInsurancevalue(new BigDecimal(objFIAColumns.getHwinsurancevalue()));
			String strCkcode = objFIAColumns.getHwinsurancecurrency();
			if (StringUtility.isNull(strCkcode))
				strCkcode = "HKD";
			TdiCurrencykind objTdiCurrencykind = TdiCurrencykindDC.loadByKey(strCkcode);
			objHouseWayBill.setTdiCurrencykind(objTdiCurrencykind);
		}
		if (!StringUtility.isNull(objFIAColumns.getHwopidpacking()))
			objHouseWayBill.setHwOpIdPacking(Long.parseLong(objFIAColumns.getHwopidpacking()));
		if (!StringUtility.isNull(objFIAColumns.getHwopidsignin()))
			objHouseWayBill.setHwOpIdSignin(Long.parseLong(objFIAColumns.getHwopidsignin()));
		if (!StringUtility.isNull(objFIAColumns.getHwopidsignout()))
			objHouseWayBill.setHwOpIdSignout(Long.parseLong(objFIAColumns.getHwopidsignout()));
		if (!StringUtility.isNull(objFIAColumns.getHwopidweighting()))
			objHouseWayBill.setHwOpIdWeighting(Long.parseLong(objFIAColumns.getHwopidweighting()));
		
		// objHouseWayBill.setHwOpIdRecord(Long.parseLong(strOperId));
		// objHouseWayBill.setHwRecorddate(DateFormatUtility.getSysdate());
		// objHouseWayBill.setHwSignindate(DateFormatUtility.getSysdate());
		// objHouseWayBill.setHwSignoutdate(DateFormatUtility.getSysdate());
		objHouseWayBill.setHwShipperaccount(objFIAColumns.getHwshipperaccount());
		objHouseWayBill.setHwShipperaddress1(objFIAColumns.getHwshipperaddress1());
		objHouseWayBill.setHwShipperaddress2(objFIAColumns.getHwshipperaddress2());
		objHouseWayBill.setHwShipperaddress3(objFIAColumns.getHwshipperaddress3());
		objHouseWayBill.setHwShippercompany(objFIAColumns.getHwshippercompany());
		objHouseWayBill.setHwShipperfax(objFIAColumns.getHwshipperfax());
		objHouseWayBill.setHwShippername(objFIAColumns.getHwshippername());
		objHouseWayBill.setHwShipperpostcode(objFIAColumns.getHwshipperpostcode());
		objHouseWayBill.setHwShippertelephone(objFIAColumns.getHwshippertelephone());
		// objHouseWayBill.setHwSignindate(DateFormatUtility.getSysdate());
		// objHouseWayBill.setHwSignoutdate(DateFormatUtility.getSysdate());
		// 发件人城市
		if (!StringUtility.isNull(objFIAColumns.getHwDtcodeshipper())) {
			TdiDistrict objDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
					objFIAColumns.getHwDtcodeshipper());
			objHouseWayBill.setTdiDistrict(objDistrict);
		}
		// 单据类型
		if (!StringUtility.isNull(objFIAColumns.getServerwbckbckcode())) {
			TdiWaybillcodekind objTWBCK = TdiWaybillcodekindDC.loadByKey(objFIAColumns.getServerwbckbckcode());
			objHouseWayBill.setTdiWaybillcodekindByHwServerewbkind(objTWBCK);
		} else {
			objHouseWayBill.setTdiWaybillcodekindByHwServerewbkind(null);
		}
		if (!StringUtility.isNull(objFIAColumns.getSubwbckbckcode())) {
			TdiWaybillcodekind objTWBCK = TdiWaybillcodekindDC.loadByKey(objFIAColumns.getSubwbckbckcode());
			objHouseWayBill.setTdiWaybillcodekindByHwSubchildewbkind(objTWBCK);
		} else {
			objHouseWayBill.setTdiWaybillcodekindByHwSubchildewbkind(null);
		}
		// 渠道主账号
		if (!StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) {
			TchnChannel objChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			if (!StringUtility.isNull(objChannel.getChnMasteraccount())) {
				objHouseWayBill.setHwChannelmasteraccount(objChannel.getChnMasteraccount());
				objHouseWayBill.setHwPaymentaccount(objChannel.getChnPaymentaccount());
			} else {
				objHouseWayBill.setHwChannelmasteraccount(objFIAColumns.getMasteraccount());
				objHouseWayBill.setHwPaymentaccount(objFIAColumns.getPaymentaccount());
			}
		}		
		objHouseWayBill.setTdiBatterykind(null);
		objHouseWayBill.setTdiCargokind(null);
		objHouseWayBill.setTdiDeliverytype(null);
		objHouseWayBill.setTdiPackagetype(null);
		if (!StringUtility.isNull(objFIAColumns.getHwbk_code()))
			objHouseWayBill.setTdiBatterykind(TdiBatterykindDC.loadByKey(objFIAColumns.getHwbk_code()));
		if (!StringUtility.isNull(objFIAColumns.getHwcgk_code()))
			objHouseWayBill.setTdiCargokind(TdiCargokindDC.loadByKey(objFIAColumns.getHwcgk_code()));
		if (!StringUtility.isNull(objFIAColumns.getDtcode()))
			objHouseWayBill.setTdiDeliverytype(TdiDeliverytypeDC.loadByKey(objFIAColumns.getDtcode()));
		if (!StringUtility.isNull(objFIAColumns.getHwpat_code()))
			objHouseWayBill.setTdiPackagetype(TdiPackagetypeDC.loadByKey(objFIAColumns.getHwpat_code()));
		
		// 换单标记跟打印标记
		String strChangedServerEWBSign = objFIAColumns.getHwhwserverewbchangedsign();
		if (StringUtility.isNull(strChangedServerEWBSign))
			strChangedServerEWBSign = "N";
		// 通过webservice换单标记
		String strSEWBChangedByWebSign = objFIAColumns.getHWSEWBChangedByWebSign();
		if (StringUtility.isNull(strSEWBChangedByWebSign))
			strSEWBChangedByWebSign = "N";
		
		String strLabelprintSign = objFIAColumns.getHwlabelprinttimes();
		if (StringUtility.isNull(strLabelprintSign))
			strLabelprintSign = "0";
		String strCargoprinttimes = objFIAColumns.getHwCargoprinttimes();
		if(StringUtility.isNull(strCargoprinttimes))
			strCargoprinttimes = "0";
		
		objHouseWayBill.setHwServerewbchangedsign(strChangedServerEWBSign);
		objHouseWayBill.setHwSewbchangedbywebsign(strSEWBChangedByWebSign);
		objHouseWayBill.setHwLabelprinttimes(Integer.parseInt(strLabelprintSign));
		objHouseWayBill.setHwCargoprinttimes(Integer.parseInt(strCargoprinttimes));
		objHouseWayBill.setHwLabelprintremark(objFIAColumns.getLabelprintremark());
		objHouseWayBill.setHwBuyerid(objFIAColumns.getBuyerid());
		objHouseWayBill.setHwTransactionid(objFIAColumns.getTransactionid());
		objHouseWayBill.setHwBookingid(objFIAColumns.getHwbookingid());
		//info	
		objHouseWayBill.setHwDcustomssign(objFIAColumns.getHwhwDcustomssign());
		
		if(!StringUtility.isNull(objFIAColumns.getItiptcode())){
			objHouseWayBill.setTdiInvoiceprinttype(TdiInvoiceprinttypeDC.loadByKey(objFIAColumns.getItiptcode()));		
		}
		if(!StringUtility.isNull(objFIAColumns.getIfyib_code())){
			objHouseWayBill.setTdiInsurancebeneficiary(TdiInsurancebeneficiaryDC.loadByKey(objFIAColumns.getIfyib_code()));		
		}
		if (!StringUtility.isNull(objFIAColumns.getHwremark()))
		objHouseWayBill.setHwRemark(objFIAColumns.getHwremark());
	}
	
	public static void setHWBByInputAllColumns(TophousewaybillTR objTophousewaybillTR, 
			ForinputallColumns objFIAColumns, 
			String strOperId) 
	throws Exception {
		objTophousewaybillTR.setHw_consigneeaccount(objFIAColumns.getHwconsigneeaccount());
		objTophousewaybillTR.setHw_consigneeaddress1(objFIAColumns.getHwconsigneeaddress1());
		objTophousewaybillTR.setHw_consigneeaddress2(objFIAColumns.getHwconsigneeaddress2());
		objTophousewaybillTR.setHw_consigneeaddress3(objFIAColumns.getHwconsigneeaddress3());
		objTophousewaybillTR.setHw_consigneeaddressex(objFIAColumns.getHwconsigneeaddressex());
		
		objTophousewaybillTR.setHw_consigneecompany(objFIAColumns.getHwconsigneecompany());
		objTophousewaybillTR.setHw_consigneefax(objFIAColumns.getHwconsigneefax());
		objTophousewaybillTR.setHw_consigneename(objFIAColumns.getHwconsigneename());
		objTophousewaybillTR.setHw_consigneenameex(objFIAColumns.getHwconsigneenameex());
		
		objTophousewaybillTR.setHw_consigneepostcode(objFIAColumns.getHwconsigneepostcode());
		objTophousewaybillTR.setHw_consigneetelephone(objFIAColumns.getHwconsigneetelephone());
		objTophousewaybillTR.setHw_insurancesign(objFIAColumns.getHwinsurancesign());
		objTophousewaybillTR.setHw_consigneecity(objFIAColumns.getHwConsigneecity());
		objTophousewaybillTR.setHw_consigneecityex(objFIAColumns.getHwconsigneecityex());
		//objTophousewaybillTR.setHw_consigneecity(HW_CONSIGNEECITY)
		
		objTophousewaybillTR.setHw_electricmark(objFIAColumns.getHwelectricmark());
		// 保险价值以及币种
		if (!StringUtility.isNull(objFIAColumns.getHwinsurancevalue())) {
			objTophousewaybillTR.setHw_insurancevalue(objFIAColumns.getHwinsurancevalue());
			String strCkcode = objFIAColumns.getHwinsurancecurrency();
			if (StringUtility.isNull(strCkcode))
				strCkcode = "HKD";
			objTophousewaybillTR.setHw_insurancecurrency(strCkcode);
		}
		if (!StringUtility.isNull(objFIAColumns.getHwopidpacking()))
			objTophousewaybillTR.setHw_op_id_packing(objFIAColumns.getHwopidpacking());
		if (!StringUtility.isNull(objFIAColumns.getHwopidsignin()))
			objTophousewaybillTR.setHw_op_id_signin(objFIAColumns.getHwopidsignin());
		if (!StringUtility.isNull(objFIAColumns.getHwopidsignout()))
			objTophousewaybillTR.setHw_op_id_signout(objFIAColumns.getHwopidsignout());
		if (!StringUtility.isNull(objFIAColumns.getHwopidweighting()))
			objTophousewaybillTR.setHw_op_id_weighting(objFIAColumns.getHwopidweighting());
		
		objTophousewaybillTR.setHw_shipperaccount(objFIAColumns.getHwshipperaccount());
		objTophousewaybillTR.setHw_shipperaddress1(objFIAColumns.getHwshipperaddress1());
		objTophousewaybillTR.setHw_shipperaddress2(objFIAColumns.getHwshipperaddress2());
		objTophousewaybillTR.setHw_shipperaddress3(objFIAColumns.getHwshipperaddress3());
		objTophousewaybillTR.setHw_shippercompany(objFIAColumns.getHwshippercompany());
		objTophousewaybillTR.setHw_shipperfax(objFIAColumns.getHwshipperfax());
		objTophousewaybillTR.setHw_shippername(objFIAColumns.getHwshippername());
		objTophousewaybillTR.setHw_shipperpostcode(objFIAColumns.getHwshipperpostcode());
		objTophousewaybillTR.setHw_shippertelephone(objFIAColumns.getHwshippertelephone());
		//objTophousewaybillTR.setHw_signoutdate(DateFormatUtility.getStandardSysdate());
		// 发件人城市
		if (!StringUtility.isNull(objFIAColumns.getHwDtcodeshipper())) {
			objTophousewaybillTR.setDt_code_shipper(objFIAColumns.getHwDtcodeshipper());
		}
		// 单据类型
		if (!StringUtility.isNull(objFIAColumns.getServerwbckbckcode())) {
			objTophousewaybillTR.setHw_serverewbkind(objFIAColumns.getServerwbckbckcode());
		} else {
			objTophousewaybillTR.setHw_serverewbkind(null);
		}
		if (!StringUtility.isNull(objFIAColumns.getSubwbckbckcode())) {
			objTophousewaybillTR.setHw_subchildewbkind(objFIAColumns.getSubwbckbckcode());
		} else {
			objTophousewaybillTR.setHw_subchildewbkind(null);
		}
		// 渠道主账号
		if ((SystempropertyDemand.getEnterprise().equals("QX") || 
				SystempropertyDemand.getEnterprise().equals("HST")) &&
				!StringUtility.isNull(objFIAColumns.getMasteraccount()) &&
				!StringUtility.isNull(objFIAColumns.getPaymentaccount())) {
			objTophousewaybillTR.setHw_channelmasteraccount(objFIAColumns.getMasteraccount());
			objTophousewaybillTR.setHw_paymentaccount(objFIAColumns.getPaymentaccount());
		} else {
			if (!StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) {
				TchnChannel objChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
				objTophousewaybillTR.setHw_channelmasteraccount(objChannel.getChnMasteraccount());
				objTophousewaybillTR.setHw_paymentaccount(objChannel.getChnPaymentaccount());
			}
		}
		// 换单标记跟打印标记
		String strChangedServerEWBSign = objFIAColumns.getHwhwserverewbchangedsign();
		if (StringUtility.isNull(strChangedServerEWBSign))
			strChangedServerEWBSign = "N";
		// 通过webservice换单标记
		String strSEWBChangedByWebSign = objFIAColumns.getHWSEWBChangedByWebSign();
		if (StringUtility.isNull(strSEWBChangedByWebSign))
			strSEWBChangedByWebSign = "N";
		
		String strLabelprintSign = objFIAColumns.getHwlabelprinttimes();
		if (StringUtility.isNull(strLabelprintSign))
			strLabelprintSign = "0";
		String strCargoprinttimes = objFIAColumns.getHwCargoprinttimes();
		if(StringUtility.isNull(strCargoprinttimes))
			strCargoprinttimes = "0";
		
		objTophousewaybillTR.setHw_serverewbchangedsign(strChangedServerEWBSign);
		objTophousewaybillTR.setHw_sewbchangedbywebsign(strSEWBChangedByWebSign);
		objTophousewaybillTR.setHw_labelprinttimes(strLabelprintSign);
		objTophousewaybillTR.setHw_cargoprinttimes(strCargoprinttimes);
		objTophousewaybillTR.setHw_labelprintremark(objFIAColumns.getLabelprintremark());
		objTophousewaybillTR.setHw_buyerid(objFIAColumns.getBuyerid());
		objTophousewaybillTR.setHw_transactionid(objFIAColumns.getTransactionid());
		objTophousewaybillTR.setHw_bookingid(objFIAColumns.getHwbookingid());
		
		
		if (!StringUtility.isNull(objFIAColumns.getHwremark()))
			objTophousewaybillTR.setHw_remark(objFIAColumns.getHwremark());
	}	
}
