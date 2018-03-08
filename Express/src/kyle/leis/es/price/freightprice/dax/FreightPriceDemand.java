package kyle.leis.es.price.freightprice.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.dax.ExpresspriceDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.freightprice.da.FreightpriceQuery;
import kyle.leis.es.price.freightprice.da.FreightpricevalueColumns;
import kyle.leis.es.price.freightprice.da.FreightpricevalueQuery;
import kyle.leis.es.price.freightprice.da.SurchargevalueColumns;
import kyle.leis.es.price.freightprice.da.SurchargevalueQuery;
import kyle.leis.es.price.freightprice.da.SurchargevaluebaseColumns;
import kyle.leis.es.price.freightprice.da.SurchargevaluebaseQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCargotypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPaymentmodeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricedomainDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricegroupDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiUnittypeDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiCargotype;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiExpresspricekind;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiFreightvaluetype;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiPaymentmode;
import kyle.leis.hi.TdiPricedomain;
import kyle.leis.hi.TdiPricegroup;
import kyle.leis.hi.TdiPricestatus;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TdiUnittype;
import kyle.leis.hi.TepExpressprice;
import kyle.leis.hi.TepFreightprice;
import kyle.leis.hi.TepFreightpricevalue;
import kyle.leis.hi.TepSurchargevalue;
import kyle.leis.hi.TepSurchargevaluebase;
import kyle.leis.hi.TepZone;

public class FreightPriceDemand {
	public static List queryFreightPrice(FreightpriceCondition objFPCondition) 
	throws Exception {
		FreightpriceCondition objChangedFPCondition = objFPCondition;
		if (!StringUtility.isNull(objFPCondition.getEpstartdate()))
			objChangedFPCondition.setEpstartdate2(objFPCondition.getEpstartdate());		
		
		if (!StringUtility.isNull(objFPCondition.getEpcode())) {
			objChangedFPCondition = new FreightpriceCondition();
			objChangedFPCondition.setEpcode(objFPCondition.getEpcode());
		}
		FreightpriceQuery objFPQuery = new FreightpriceQuery();
		objFPQuery.setCondition(objChangedFPCondition);
		return objFPQuery.getResults();
	}
	
	public static List loadFreightPrice(String strEpcode) 
	throws Exception {
		FreightpriceCondition objFPCondition = new FreightpriceCondition();
		objFPCondition.setEpcode(strEpcode);
		return queryFreightPrice(objFPCondition);
	}
	
	public static List loadFreightValue(String strEpcode) 
	throws Exception {
		FreightpricevalueQuery objFPVQuery = new FreightpricevalueQuery();
		objFPVQuery.setEpcode(strEpcode);
		return objFPVQuery.getResults();
	}
	
	public static List loadSurchargeValue(String strEpcode) 
	throws Exception {
		SurchargevalueQuery objSVQuery = new SurchargevalueQuery();
		objSVQuery.setEpcode(strEpcode);
		return objSVQuery.getResults();
	}
	
	public static LoadResult load(String strEpcode) 
	throws Exception {
		LoadResult objLoadResult = new LoadResult();
		objLoadResult.setFreightPriceColumns(loadFreightPrice(strEpcode));
		objLoadResult.setFreightValueColumns(loadFreightValue(strEpcode));
		objLoadResult.setSurchargeValueColumns(loadSurchargeValue(strEpcode));
		objLoadResult.setSurchargeBaseColumns(loadSurchargeBase(strEpcode));
		objLoadResult.setEnterprise(ExpresspriceDemand.loadEnterprise(strEpcode));
		return objLoadResult;
	}
	
	public static List loadSurchargeBase(String strEpcode) 
	throws Exception {
		SurchargevaluebaseQuery objSVBQuery = new SurchargevaluebaseQuery();
		objSVBQuery.setEpcode(strEpcode);
		return objSVBQuery.getResults();
	}	
	
	public static void setExpressPriceByColumns(TepExpressprice objTepExpressprice,
			FreightpriceColumns objFPColumns,
			String strOperId, 
			Session objSession) throws Exception {
		objTepExpressprice.setEpEnddate(DateFormatUtility.getStandardDate(objFPColumns.getEpependdate() + " 23:59:59"));
		objTepExpressprice.setEpStartdate(DateFormatUtility.getStandardDate(objFPColumns.getEpepstartdate() + " 00:00:00"));
		objTepExpressprice.setEpModifydate(DateFormatUtility.getSysdate());
		objTepExpressprice.setEpRemark(objFPColumns.getEpepremark());
		objTepExpressprice.setEpWithdrawsign("N");
		if (!StringUtility.isNull(objFPColumns.getEeeecode())) {
			TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objFPColumns.getEeeecode());
			objTepExpressprice.setTdiEnterpriseelement(objTEE);
		}
		if (!StringUtility.isNull(objFPColumns.getPspscode())) {
			TdiPricestatus objTPS = TdiPricestatusDC.loadByKey(objFPColumns.getPspscode());
			objTepExpressprice.setTdiPricestatus(objTPS);
		}
		TdiOperator objTOP = TdiOperatorDC.loadByKey(strOperId);
		objTepExpressprice.setTdiOperatorByEpOpIdModify(objTOP);
		TdiExpresspricekind objTEPK = (TdiExpresspricekind)objSession.load(TdiExpresspricekind.class, 
				IExpressPriceBasicData.PRICEKIND_FREIGHT);
		objTepExpressprice.setTdiExpresspricekind(objTEPK);
	}
	
	public static void setFreightPriceByColumns(TepFreightprice objTepFreightprice,
			FreightpriceColumns objFPColumns,
			Session objSession) throws Exception {
		objTepFreightprice.setFpCommissionrate(new BigDecimal(objFPColumns.getFpfpcommissionrate()));
		
		if (!StringUtility.isNull(objFPColumns.getChnchncode())) {
			TchnChannel objTCHN = (TchnChannel)objSession.load(TchnChannel.class, 
					objFPColumns.getChnchncode());
			objTepFreightprice.setTchnChannel(objTCHN);
		}
		if (!StringUtility.isNull(objFPColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objFPColumns.getCococode());
			objTepFreightprice.setTcoCorporation(objTcoCorporation);
		}
		if (!StringUtility.isNull(objFPColumns.getCtctcode())) {
			TdiCargotype objTdiCargotype = TdiCargotypeDC.loadByKey(objFPColumns.getCtctcode());
			objTepFreightprice.setTdiCargotype(objTdiCargotype);
		}		
		if (!StringUtility.isNull(objFPColumns.getCkckcode())) {
			TdiCurrencykind objTCK = TdiCurrencykindDC.loadByKey(objFPColumns.getCkckcode());
			objTepFreightprice.setTdiCurrencykind(objTCK);
		}
		if (!StringUtility.isNull(objFPColumns.getDtdtcode())) {
			TdiDistrict objTdiDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
					objFPColumns.getDtdtcode());
			objTepFreightprice.setTdiDistrict(objTdiDistrict);
		}
		if (!StringUtility.isNull(objFPColumns.getPmpmcode())) {
			TdiPaymentmode objTdiPaymentmode = TdiPaymentmodeDC.loadByKey(objFPColumns.getPmpmcode());
			objTepFreightprice.setTdiPaymentmode(objTdiPaymentmode);
		}
		if (!StringUtility.isNull(objFPColumns.getPdpdcode())) {
			TdiPricedomain objTPD = TdiPricedomainDC.loadByKey(objFPColumns.getPdpdcode());
			objTepFreightprice.setTdiPricedomain(objTPD);
		}
		if (!StringUtility.isNull(objFPColumns.getPgpgcode())) {
			TdiPricegroup objTdiPricegroup = TdiPricegroupDC.loadByKey(objFPColumns.getPgpgcode());
			objTepFreightprice.setTdiPricegroup(objTdiPricegroup);
		}
		if (!StringUtility.isNull(objFPColumns.getPkpkcode())) {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(objFPColumns.getPkpkcode());
			objTepFreightprice.setTdiProductkind(objTdiProductkind);
		}
		if (!StringUtility.isNull(objFPColumns.getUtutcode())) {
			TdiUnittype objTdiUnittype = TdiUnittypeDC.loadByKey(objFPColumns.getUtutcode());
			objTepFreightprice.setTdiUnittype(objTdiUnittype);
		}
		if (!StringUtility.isNull(objFPColumns.getZnzncode())) {
			TepZone objTepZone = (TepZone)objSession.load(TepZone.class, 
					Long.parseLong(objFPColumns.getZnzncode()));
			objTepFreightprice.setTepZone(objTepZone);
		}
	}
	
	public static void setFreightValueByColumns(TepFreightpricevalue objFPValue,
			FreightpricevalueColumns objFPVColumns,
			Session objSession) throws Exception {
		objFPValue.setFpvCarryweight(new BigDecimal(objFPVColumns.getFpvfpvcarryweight()));
		objFPValue.setFpvPricevalue(new BigDecimal(objFPVColumns.getFpvfpvpricevalue()));
		objFPValue.setFpvWeightgrade(new BigDecimal(objFPVColumns.getFpvfpvweightgrade()));
		objFPValue.setFpvWeightunit(new BigDecimal(objFPVColumns.getFpvfpvweightunit()));
		objFPValue.setZnvId(Long.parseLong(objFPVColumns.getFpvznvid()));
		
		if (!StringUtility.isNull(objFPVColumns.getFvtfvtcode())) {
			TdiFreightvaluetype objTFVT = (TdiFreightvaluetype)objSession.load(TdiFreightvaluetype.class, 
					objFPVColumns.getFvtfvtcode());
			objFPValue.setTdiFreightvaluetype(objTFVT);
		}
	}
	
	public static void setSurchargeValueByColumns(TepSurchargevalue objSurchargeValue,
			SurchargevalueColumns objSVColumns,
			Session objSession) throws Exception {
		objSurchargeValue.setSvBasevalue(new BigDecimal(objSVColumns.getValue()));
		objSurchargeValue.setSvMinimumvalue(new BigDecimal(objSVColumns.getSvsvminimumvalue()));
		objSurchargeValue.setSvPricevalue(new BigDecimal(objSVColumns.getSvsvpricevalue()));
		objSurchargeValue.setSvReversesign(objSVColumns.getSvsvreversesign());
		objSurchargeValue.setZnvId(Long.parseLong(objSVColumns.getSvznvid()));
		
		if (StringUtility.isNull(objSVColumns.getSvsvmaximumvalue()))
			objSVColumns.setSvsvmaximumvalue(new BigDecimal("0"));
		objSurchargeValue.setSvMaximumvalue(new BigDecimal(objSVColumns.getSvsvmaximumvalue()));
		
		if (!StringUtility.isNull(objSVColumns.getFkfkcode())) {
			TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objSVColumns.getFkfkcode());
			objSurchargeValue.setTdiFeekind(objTdiFeekind);
		}
		if (!StringUtility.isNull(objSVColumns.getUtutcode())) {
			TdiUnittype objTdiUnittype = TdiUnittypeDC.loadByKey(objSVColumns.getUtutcode());
			objSurchargeValue.setTdiUnittype(objTdiUnittype);
		}
	}
	
	public static void setSvaluebaseByColumns(TepSurchargevaluebase objSvaluebase,
			SurchargevaluebaseColumns objSVBColumns,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objSVBColumns.getSvbcomp_idfkcode())) {
			TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objSVBColumns.getSvbcomp_idfkcode());
			objSvaluebase.setTdiFeekind(objTdiFeekind);
		}
	}
	
	public static List<SurchargevaluebaseColumns> getSvbColumns(List listSurchargevaluebase,
			String strSvid) {
		if (listSurchargevaluebase == null || listSurchargevaluebase.size() < 1)
			return null;
		List<SurchargevaluebaseColumns> list = new ArrayList<SurchargevaluebaseColumns>();
		for (int i = 0; i < listSurchargevaluebase.size(); i++) {
			SurchargevaluebaseColumns objSvbColumns = (SurchargevaluebaseColumns)listSurchargevaluebase.get(i);
			if (objSvbColumns.getSvbcomp_idsvid().equals(strSvid))
				list.add(objSvbColumns);
		}
		return list;
	}
}
