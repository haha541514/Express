package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillQuery;
import kyle.leis.eo.operation.corewaybill.da.TopcorewaybillTR;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.VolumeweightColumns;
import kyle.leis.eo.operation.housewaybill.da.VolumeweightQuery;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCargotypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCorewaybillstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPaymentmodeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiCargotype;
import kyle.leis.hi.TdiCorewaybillstatus;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiPaymentmode;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TopBatchwaybill;
import kyle.leis.hi.TopBatchwaybillvalue;
import kyle.leis.hi.TopCorewaybill;
import net.sf.hibernate.Session;

public class CorewaybillDemand {
	/**
	 * 获得快件的扣件状态
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static String getIssueHoldstatus(String strCwcode) 
	throws Exception {
		if (StringUtility.isNull(strCwcode)) return "";
		SimplecorewaybillColumns objSCBColumns = loadSimpleCorewaybill(strCwcode);
		if (objSCBColumns == null)
			return "";
		return objSCBColumns.getCwihs_code();
	}
	
	public static String getVolumeweight(String strCwcode) throws Exception {
		VolumeweightQuery objVolumeweightQuery = new VolumeweightQuery();
		objVolumeweightQuery.setCw_code(strCwcode);
		List objList = objVolumeweightQuery.getResults();
		if (objList == null || objList.size() < 1) 
			return "0";
		return ((VolumeweightColumns)objList.get(0)).getVolumeweight();
	}
	
	public static SimplecorewaybillColumns loadSimpleCorewaybill(String strCwcode) 
	throws Exception {
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCw_code(strCwcode);
		List objList = objSCWQuery.getResults();
		
		if (objList == null || objList.size() < 1) return null;
		return (SimplecorewaybillColumns)objList.get(0);
	}
	
	public static SimplecorewaybillColumns loadSimpleCorewaybill(String strCustomerEwbcode,
			String strCocode) 
	throws Exception {
		/*SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCw_customerewbcode(strCustomerEwbcode);
		objSCWQuery.setCocodecustomer(strCocode);
		List objList = objSCWQuery.getResults();
		
		if (objList == null || objList.size() < 1) return null;
		return (SimplecorewaybillColumns)objList.get(0);*/
		return loadSimpleCorewaybill(strCustomerEwbcode, strCocode, false);
	}	
	/**
	 * 
	 * @param strCustomerEwbcode
	 * @param strCocode
	 * @param filterZf 是否过滤作废订单
	 * @return
	 * @throws Exception
	 */
	public static SimplecorewaybillColumns loadSimpleCorewaybill(String strCustomerEwbcode,
			String strCocode, boolean filterZf) 
	throws Exception {
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCw_customerewbcode(strCustomerEwbcode);
		objSCWQuery.setCocodecustomer(strCocode);
		if (filterZf) {
			objSCWQuery.setNoincwscode("CEL");
		}
		List objList = objSCWQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (SimplecorewaybillColumns)objList.get(0);
	}
	
	public static SimplecorewaybillColumns loadSimpleCorewaybill(String strEwbcode,
			String strCocode,
			String strEwbcodeType) 
	throws Exception {
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER))
			objSCWQuery.setCw_customerewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SELF))
			objSCWQuery.setCw_ewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SERVER))
			objSCWQuery.setCw_serverewbcode(strEwbcode);
		objSCWQuery.setCocodecustomer(strCocode);
		List objList = objSCWQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (SimplecorewaybillColumns)objList.get(0);
	}	
	
	
	public static List loadByServerEwbcode(String strEwbcode, 
			String strChncode) throws Exception {
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCw_serverewbcode(strEwbcode);
		objSCWQuery.setChncodesupplier(strChncode);
		objSCWQuery.setNoincwscode("CEL,EL,CHD,CTS,CHP,PR");
		return objSCWQuery.getResults();	
	}
	
	public static String getCwcodeBySChildLabelcode(String strSChildLabelcode) 
	throws Exception {
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setSchildlabelcode(strSChildLabelcode);
		objSCWQuery.setNoincwscode("CEL,EL,CHD,CTS,CHP,PR");
		List listResults = objSCWQuery.getResults();		
		if (listResults == null || listResults.size() < 1)
			return "";
		return ((SimplecorewaybillColumns)listResults.get(0)).getCwcw_code();
	}
	
	
	public static SimplecorewaybillColumns loadCWByEwbcode(String strEwbcode,
			String strEwbcodeType) throws Exception {
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER))
			objSCWQuery.setCw_customerewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SELF))
			objSCWQuery.setCw_ewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SERVER))
			objSCWQuery.setCw_serverewbcode(strEwbcode);
		List objList = objSCWQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (SimplecorewaybillColumns)objList.get(0);
	}
	
	public static void setCWBByHouseColumns(TopCorewaybill objCoreWayBill,
			HousewaybillColumns objHwColumns,
			String strOperId, 
			Session objSession) throws Exception {
		objCoreWayBill.setCwGrossweight(new BigDecimal(objHwColumns.getCwcwgrossweight()));
		objCoreWayBill.setCwChargeweight(new BigDecimal(objHwColumns.getCwcwchargeweight()));
		objCoreWayBill.setCwCustomerchargeweight(new BigDecimal(objHwColumns.getCwcwcustomerchargeweight()));
		objCoreWayBill.setCwPieces(Integer.parseInt(objHwColumns.getCwcwpieces()));
		
		objCoreWayBill.setCwTransferchargeweight(new BigDecimal(objHwColumns.getCwcwtransferchargeweight()));
		objCoreWayBill.setCwTransfergrossweight(new BigDecimal(objHwColumns.getCwcwtransfergrossweight()));
		objCoreWayBill.setCwTransferpieces(new BigDecimal(objHwColumns.getCwcwtransferpieces()));
		objCoreWayBill.setCwServerchargeweight(new BigDecimal(objHwColumns.getCwcwserverchargeweight()));
		
		objCoreWayBill.setCwCustomerewbcode(objHwColumns.getCwcwcustomerewbcode());
		objCoreWayBill.setCwEwbcode(objHwColumns.getCwcwewbcode());
		objCoreWayBill.setCwServerewbcode(objHwColumns.getCwcwserverewbcode());
		
		objCoreWayBill.setCwModifydate(DateFormatUtility.getSysdate());
		objCoreWayBill.setCwOpIdModifier(new BigDecimal(strOperId));
		
		objCoreWayBill.setCwPostcodeDestination(objHwColumns.getCwcwpostcodedestination());
		// 收货体积重系数
		int iVolumerate = 5000;
		if (!StringUtility.isNull(objHwColumns.getCwcwvolumerate()))
			iVolumerate = Integer.parseInt(objHwColumns.getCwcwvolumerate());
		objCoreWayBill.setCwVolumerate(iVolumerate);
		// 出货体积重系数
		int iTransferVolumerate = 5000;
		if (!StringUtility.isNull(objHwColumns.getCwcwtransfervolumerate()))
			iTransferVolumerate = Integer.parseInt(objHwColumns.getCwcwtransfervolumerate());
		objCoreWayBill.setCwTransfervolumerate(iTransferVolumerate);		
		// 服务渠道
		if (!StringUtility.isNull(objHwColumns.getSchnchncode()) &&
				!objHwColumns.getSchnchncode().equals("null")) {
			TchnChannel objChannel = (TchnChannel)objSession.load(TchnChannel.class, 
					objHwColumns.getSchnchncode());
			objCoreWayBill.setTchnChannelByChnCodeSupplier(objChannel);
			TcoCorporation objCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objChannel.getTcoCorporation().getCoCode());
				objCoreWayBill.setTcoCorporationByCoCodeSupplier(objCorporation);
		}
		// 设置服务渠道为空
		if (!StringUtility.isNull(objHwColumns.getSchnchncode()) &&
				objHwColumns.getSchnchncode().equals("null")) {
			objCoreWayBill.setTchnChannelByChnCodeSupplier(null);
			objCoreWayBill.setTcoCorporationByCoCodeSupplier(null);
		}
		
		if (!StringUtility.isNull(objHwColumns.getCcococode())) {
			TcoCorporation objCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objHwColumns.getCcococode());
			objCoreWayBill.setTcoCorporationByCoCodeCustomer(objCorporation);
		}
		if (!StringUtility.isNull(objHwColumns.getScococode())) {
			TcoCorporation objCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objHwColumns.getScococode());
			objCoreWayBill.setTcoCorporationByCoCodeSupplier(objCorporation);
		}
		if (!StringUtility.isNull(objHwColumns.getCtctcode())) {
			TdiCargotype objCargoType = TdiCargotypeDC.loadByKey(objHwColumns.getCtctcode());
			objCoreWayBill.setTdiCargotype(objCargoType);
		}
		if (!StringUtility.isNull(objHwColumns.getCwscwscode())) {
			TdiCorewaybillstatus objCWBStatus = TdiCorewaybillstatusDC.loadByKey(objHwColumns.getCwscwscode());
			objCoreWayBill.setTdiCorewaybillstatus(objCWBStatus);
		}
		if (!StringUtility.isNull(objHwColumns.getDdtdtcode())) {
			TdiDistrict objDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
					objHwColumns.getDdtdtcode());
			objCoreWayBill.setTdiDistrictByDtCodeDestination(objDistrict);
		}
		if (!StringUtility.isNull(objHwColumns.getSdtdtcode())) {
			TdiDistrict objDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
					objHwColumns.getSdtdtcode());
			objCoreWayBill.setTdiDistrictByDtCodeSignin(objDistrict);
		}
		if (!StringUtility.isNull(objHwColumns.getOdtdtcode())) {
			TdiDistrict objDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
					objHwColumns.getOdtdtcode());
			objCoreWayBill.setTdiDistrictByDtCodeOrigin(objDistrict);
		}
		if (!StringUtility.isNull(objHwColumns.getPmpmcode())) {
			TdiPaymentmode objPayment = TdiPaymentmodeDC.loadByKey(objHwColumns.getPmpmcode());
			objCoreWayBill.setTdiPaymentmode(objPayment);
		}
		if (!StringUtility.isNull(objHwColumns.getPkpkcode())) {
			TdiProductkind objProductKind = TdiProductkindDC.loadByKey(objHwColumns.getPkpkcode());
			objCoreWayBill.setTdiProductkind(objProductKind);
		}
		if (!StringUtility.isNull(objHwColumns.getAbwbwcode())) {
			TopBatchwaybill objBWBArrival = (TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
					Long.parseLong(objHwColumns.getAbwbwcode()));
			objCoreWayBill.setTopBatchwaybillByBwCodeArrival(objBWBArrival);
		}
		if (!StringUtility.isNull(objHwColumns.getDbwbwcode())) {
			TopBatchwaybill objBWBDeparture = (TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
					Long.parseLong(objHwColumns.getDbwbwcode()));
			objCoreWayBill.setTopBatchwaybillByBwCodeDeparture(objBWBDeparture);
		}
		if (!StringUtility.isNull(objHwColumns.getEeeecode())) {
			TdiEnterpriseelement objEecode = TdiEnterpriseelementDC.loadByKey(objHwColumns.getEeeecode());
			objCoreWayBill.setTdiEnterpriseelement(objEecode);
		}
		if (!StringUtility.isNull(objHwColumns.getAbwvbwbvid())) {
			TopBatchwaybillvalue objTBBV = (TopBatchwaybillvalue)objSession.load(TopBatchwaybillvalue.class, 
					Long.parseLong(objHwColumns.getAbwvbwbvid()));
			objCoreWayBill.setTopBatchwaybillvalueByBwbvIdArrival(objTBBV);
		}
		if (!StringUtility.isNull(objHwColumns.getDbwvbwbvid())) {
			TopBatchwaybillvalue objTBBV = (TopBatchwaybillvalue)objSession.load(TopBatchwaybillvalue.class, 
					Long.parseLong(objHwColumns.getDbwvbwbvid()));
			objCoreWayBill.setTopBatchwaybillvalueByBwbvIdDeparture(objTBBV);
		}
		
		
		String strBagcounts = objHwColumns.getCwcwbagcounts();
		String strBillcounts = objHwColumns.getCwcwbillcounts();
		if (StringUtility.isNull(strBagcounts))
			strBagcounts = "1";
		if (StringUtility.isNull(strBillcounts))
			strBillcounts = "1";
		
		objCoreWayBill.setCwBagcounts(Integer.parseInt(strBagcounts));
		objCoreWayBill.setCwBillcounts(Integer.parseInt(strBillcounts));
		
		String strBatchwaybillsign = "N";
		if (!StringUtility.isNull(objHwColumns.getCwcwbatchwaybillsign()))
			strBatchwaybillsign = objHwColumns.getCwcwbatchwaybillsign();
		objCoreWayBill.setCwBatchwaybillsign(strBatchwaybillsign);		
	}
	
	public static void setCWBByInputAllColumns(TopcorewaybillTR objTopcorewaybillTR, 
			ForinputallColumns objFIAColumns, 
			String strOperId, 
			Session objSession) throws Exception {
		objTopcorewaybillTR.setCw_channelewbcode(objFIAColumns.getCwchannelewbcode());
		objTopcorewaybillTR.setCw_grossweight(objFIAColumns.getCwgrossweight());
		
		objTopcorewaybillTR.setCw_customerewbcode(objFIAColumns.getCwcustomerewbcode());
		objTopcorewaybillTR.setCw_ewbcode(objFIAColumns.getCwewbcode());
		objTopcorewaybillTR.setCw_serverewbcode(objFIAColumns.getCwserverewbcode());
		
		objTopcorewaybillTR.setCw_modifydate(DateFormatUtility.getStandardSysdate());
		objTopcorewaybillTR.setCw_op_id_modifier(strOperId);
		objTopcorewaybillTR.setCw_pieces(objFIAColumns.getCwpieces());
		objTopcorewaybillTR.setCw_postcode_destination(objFIAColumns.getCwpostcodedestination());
		
		// 获得计费重量
		ChargeweightResult objCWResult = getChargeweight(objFIAColumns);
		objTopcorewaybillTR.setCw_chargeweight(objCWResult.getChargeweight());
		objTopcorewaybillTR.setCw_volumerate(objCWResult.getVolumeRate());
		// 出货重量
		objTopcorewaybillTR.setCw_transferchargeweight(objFIAColumns.getCwtransferchargeweight());
		objTopcorewaybillTR.setCw_transfergrossweight(objFIAColumns.getCwtransfergrossweight());
		objTopcorewaybillTR.setCw_transfervolumeweight(objFIAColumns.getTransfervolumeweight());
		objTopcorewaybillTR.setCw_transferpieces(objFIAColumns.getCwpieces());
		objTopcorewaybillTR.setCw_serverchargeweight(objFIAColumns.getCwtransferchargeweight());
		// 设置服务商重量
		String strServerchargeweight = objFIAColumns.getCwserverchargeweight();
		if (!StringUtility.isNull(strServerchargeweight) &&
				new BigDecimal(strServerchargeweight).compareTo(new BigDecimal("0")) > 0)
			objTopcorewaybillTR.setCw_serverchargeweight(strServerchargeweight);
		// 企业元素
		objTopcorewaybillTR.setEe_code(objFIAColumns.getEecode());
		// 出货体积重
		objTopcorewaybillTR.setCw_transfervolumerate(objFIAColumns.getTransfervolumerate());
		if (!StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) {
			TchnChannel objChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			objTopcorewaybillTR.setChn_code_supplier(objFIAColumns.getChncode_Cwspchn());
			objTopcorewaybillTR.setCo_code_supplier(objChannel.getTcoCorporation().getCoCode());
		}
		if (!StringUtility.isNull(objFIAColumns.getCocode_Cwcus())) {
			objTopcorewaybillTR.setCo_code_customer(objFIAColumns.getCocode_Cwcus());
		}	
		if (!StringUtility.isNull(objFIAColumns.getCtcode())) {
			objTopcorewaybillTR.setCt_code(objFIAColumns.getCtcode());
		}
		// 出货或作废的运单则不改变其运单状态
		String strCwscode = objFIAColumns.getCwscode();
		if (StringUtility.isNull(strCwscode) || strCwscode.equals("SI"))
			strCwscode = "IP";
		objTopcorewaybillTR.setCws_code(strCwscode);
			
		if (!StringUtility.isNull(objFIAColumns.getDtcode())) {
			objTopcorewaybillTR.setDt_code_destination(objFIAColumns.getDtcode());
		}
		
		if (!StringUtility.isNull(objFIAColumns.getSidtcode())) {
			objTopcorewaybillTR.setDt_code_signin(objFIAColumns.getSidtcode());
		}		
		
		if (!StringUtility.isNull(objFIAColumns.getDtcode_Cwodt())) {
			objTopcorewaybillTR.setDt_code_origin(objFIAColumns.getDtcode_Cwodt());
		}
		if (!StringUtility.isNull(objFIAColumns.getPmcode())) {
			objTopcorewaybillTR.setPm_code(objFIAColumns.getPmcode());
		}
		if (!StringUtility.isNull(objFIAColumns.getPk_code())) {
			objTopcorewaybillTR.setPk_code(objFIAColumns.getPk_code());
		}
		if (!StringUtility.isNull(objFIAColumns.getBwcode_Cwabm())) {
			objTopcorewaybillTR.setBw_code_arrival(objFIAColumns.getBwcode_Cwabm());
		}
	}
	
	
	public static void setCWBByInputAllColumns(TopCorewaybill objCoreWayBill, 
			ForinputallColumns objFIAColumns, 
			String strOperId, 
			Session objSession) throws Exception {
		objCoreWayBill.setCwChannelewbcode(objFIAColumns.getCwchannelewbcode());
		objCoreWayBill.setCwGrossweight(new BigDecimal(objFIAColumns.getCwgrossweight()));
		
		objCoreWayBill.setCwCustomerewbcode(objFIAColumns.getCwcustomerewbcode());
		objCoreWayBill.setCwEwbcode(objFIAColumns.getCwewbcode());
		objCoreWayBill.setCwServerewbcode(objFIAColumns.getCwserverewbcode());
		
		objCoreWayBill.setCwModifydate(DateFormatUtility.getSysdate());
		objCoreWayBill.setCwOpIdModifier(new BigDecimal(strOperId));
		objCoreWayBill.setCwPieces(Integer.parseInt(objFIAColumns.getCwpieces()));
		objCoreWayBill.setCwPostcodeDestination(objFIAColumns.getCwpostcodedestination());
		
		// 获得计费重量
		ChargeweightResult objCWResult = getChargeweight(objFIAColumns);
		objCoreWayBill.setCwChargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objCoreWayBill.setCwVolumerate(Integer.parseInt(objCWResult.getVolumeRate()));
		// 出货重量
		objCoreWayBill.setCwTransferchargeweight(new BigDecimal(objFIAColumns.getCwtransferchargeweight()));
		objCoreWayBill.setCwTransfergrossweight(new BigDecimal(objFIAColumns.getCwtransfergrossweight()));
		objCoreWayBill.setCwTransfervolumeweight(new BigDecimal(objFIAColumns.getTransfervolumeweight()));
		objCoreWayBill.setCwTransferpieces(new BigDecimal(objCoreWayBill.getCwPieces()));
		objCoreWayBill.setCwServerchargeweight(objCoreWayBill.getCwTransferchargeweight());
		// 设置服务商重量
		String strServerchargeweight = objFIAColumns.getCwserverchargeweight();
		if (!StringUtility.isNull(strServerchargeweight) &&
				new BigDecimal(strServerchargeweight).compareTo(new BigDecimal("0")) > 0)
			objCoreWayBill.setCwServerchargeweight(new BigDecimal(strServerchargeweight));
		// 企业元素
		TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objFIAColumns.getEecode());
		objCoreWayBill.setTdiEnterpriseelement(objTEE);
		// 出货体积重
		objCoreWayBill.setCwTransfervolumerate(Integer.parseInt(objFIAColumns.getTransfervolumerate()));
		if (!StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) {
			TchnChannel objChannel = (TchnChannel)objSession.load(TchnChannel.class, 
				objFIAColumns.getChncode_Cwspchn());
			objCoreWayBill.setTchnChannelByChnCodeSupplier(objChannel);
			TcoCorporation objCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objChannel.getTcoCorporation().getCoCode());
				objCoreWayBill.setTcoCorporationByCoCodeSupplier(objCorporation);
		}
		if (!StringUtility.isNull(objFIAColumns.getCocode_Cwcus())) {
			TcoCorporation objCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
				objFIAColumns.getCocode_Cwcus());
			objCoreWayBill.setTcoCorporationByCoCodeCustomer(objCorporation);
		}
		/*
		if (!StringUtility.isNull(objFIAColumns.getCocode_Cwsp())) {
			TcoCorporation objCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
				objFIAColumns.getCocode_Cwsp());
			objCoreWayBill.setTcoCorporationByCoCodeSupplier(objCorporation);
		}*/		
		if (!StringUtility.isNull(objFIAColumns.getCtcode())) {
			TdiCargotype objCargoType = TdiCargotypeDC.loadByKey(objFIAColumns.getCtcode());
			objCoreWayBill.setTdiCargotype(objCargoType);
		}
		// 出货或作废的运单则不改变其运单状态
		String strCwscode = objFIAColumns.getCwscode();
		if (StringUtility.isNull(strCwscode) || strCwscode.equals("SI"))
			strCwscode = "IP";
		TdiCorewaybillstatus objCWBStatus = (TdiCorewaybillstatus)objSession.load(TdiCorewaybillstatus.class, strCwscode);
		objCoreWayBill.setTdiCorewaybillstatus(objCWBStatus);
			
		if (!StringUtility.isNull(objFIAColumns.getDtcode())) {
			TdiDistrict objDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, objFIAColumns.getDtcode());
			objCoreWayBill.setTdiDistrictByDtCodeDestination(objDistrict);
		}
		
		if (!StringUtility.isNull(objFIAColumns.getSidtcode())) {
			TdiDistrict objDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, objFIAColumns.getSidtcode());
			objCoreWayBill.setTdiDistrictByDtCodeSignin(objDistrict);
		}		
		
		if (!StringUtility.isNull(objFIAColumns.getDtcode_Cwodt())) {
			TdiDistrict objDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, objFIAColumns.getDtcode_Cwodt());
			objCoreWayBill.setTdiDistrictByDtCodeOrigin(objDistrict);
		}
		if (!StringUtility.isNull(objFIAColumns.getPmcode())) {
			TdiPaymentmode objPayment = TdiPaymentmodeDC.loadByKey(objFIAColumns.getPmcode());
			objCoreWayBill.setTdiPaymentmode(objPayment);
		}
		if (!StringUtility.isNull(objFIAColumns.getPk_code())) {
			TdiProductkind objProductKind = TdiProductkindDC.loadByKey(objFIAColumns.getPk_code());
			objCoreWayBill.setTdiProductkind(objProductKind);
		}
		if (!StringUtility.isNull(objFIAColumns.getBwcode_Cwabm())) {
			TopBatchwaybill objBWBArrival = (TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
					Long.parseLong(objFIAColumns.getBwcode_Cwabm()));
			objCoreWayBill.setTopBatchwaybillByBwCodeArrival(objBWBArrival);
		}
	}
	
	private static ChargeweightParameter transferToCWParameter(ForinputallColumns objFIAColumns) 
	throws Exception {
		ChargeweightParameter objCWParameter = new ChargeweightParameter();
		
		objCWParameter.setCocode(objFIAColumns.getCocode());
		objCWParameter.setDtcode(objFIAColumns.getDtcode());
		objCWParameter.setGrossWeight(objFIAColumns.getCwgrossweight());
		objCWParameter.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_SALES);
		objCWParameter.setPkcode(objFIAColumns.getPk_code());
		objCWParameter.setPostcode(objFIAColumns.getHwconsigneepostcode());
		objCWParameter.setSearchDate(objFIAColumns.getAdddate());
		// 获得件数详细信息
		objCWParameter.setWaybillpiecesCollection(CorewaybillpiecesDemand.load(objFIAColumns.getCwcode()));
		
		return objCWParameter;
	}
	
	public static ChargeweightParameter transferToCWParameter(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces) 
	throws Exception {
		ChargeweightParameter objCWParameter = new ChargeweightParameter();
		
		if (!StringUtility.isNull(objHwColumns.getDdtdtcode()))
			objCWParameter.setDtcode(objHwColumns.getDdtdtcode());
		else
			objCWParameter.setDtcode(objHwColumns.getSdtdtcode());
		
		objCWParameter.setCocode(objHwColumns.getCcococode());
		objCWParameter.setGrossWeight(objHwColumns.getCwcwgrossweight());
		objCWParameter.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_SALES);
		objCWParameter.setPkcode(objHwColumns.getPkpkcode());
		objCWParameter.setPostcode(objHwColumns.getHwhwconsigneepostcode());
		objCWParameter.setSearchDate(objHwColumns.getAbwadddate());
		// 获得件数详细信息
		objCWParameter.setWaybillpiecesCollection(listCorewaybillpieces);
		
		return objCWParameter;
	}
	
	public static String getChargeweight(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces) throws Exception {
		ChargeweightParameter objCWParameter = transferToCWParameter(objHwColumns, listCorewaybillpieces);
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
		return objCWResult.getChargeweight();
	}
	
	public static ChargeweightResult getChargeweight(ForinputallColumns objFIAColumns) throws Exception {
		ChargeweightParameter objCWParameter = transferToCWParameter(objFIAColumns);
		Chargeweight objChargeweight = new Chargeweight();
		return objChargeweight.calculate(objCWParameter);
	}
}
