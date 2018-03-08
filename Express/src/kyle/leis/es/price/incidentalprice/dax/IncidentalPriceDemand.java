package kyle.leis.es.price.incidentalprice.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.dax.ExpresspriceDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceQuery;
import kyle.leis.es.price.incidentalprice.da.IncidentalpricevalueColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalpricevalueCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpricevalueQuery;
import kyle.leis.es.price.incidentalprice.da.IncidentalstoragechannelColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalstoragechannelCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalstoragechannelQuery;
import kyle.leis.es.price.incidentalprice.da.IncidentalvaluebaseColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalvaluebaseCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalvaluebaseQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCargotypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpresspricekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpressspecialtypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
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
import kyle.leis.hi.TdiExpressspecialtype;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiPaymentmode;
import kyle.leis.hi.TdiPricedomain;
import kyle.leis.hi.TdiPricegroup;
import kyle.leis.hi.TdiPricestatus;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TdiUnittype;
import kyle.leis.hi.TepExpressprice;
import kyle.leis.hi.TepIncidentalprice;
import kyle.leis.hi.TepIncidentalpricevalue;
import kyle.leis.hi.TepIncidentalstorgechannel;
import kyle.leis.hi.TepIncidentalvaluebase;

public class IncidentalPriceDemand {
	public static List query(IncidentalpriceCondition objIPCondition) 
	throws Exception {
		IncidentalpriceCondition objChangedIPCondition = objIPCondition;
		
		if (!StringUtility.isNull(objChangedIPCondition.getEpstartdate()))
			objChangedIPCondition.setEpstartdate2(objChangedIPCondition.getEpstartdate());		
		
		if (!StringUtility.isNull(objChangedIPCondition.getEpcode()))
		{
			objChangedIPCondition = new IncidentalpriceCondition();
			objChangedIPCondition.setEpcode(objIPCondition.getEpcode());
		}
		IncidentalpriceQuery objIPQuery = new IncidentalpriceQuery();
		objIPQuery.setCondition(objChangedIPCondition);
		return objIPQuery.getResults();
	}
	
	public static IncidentalpriceColumns loadIncidentalprice(String strEpcode) 
	throws Exception {
		IncidentalpriceCondition objIPCondition = new IncidentalpriceCondition();
		objIPCondition.setEpcode(strEpcode);
		List objList = query(objIPCondition);
		if (objList == null || objList.size() < 1) return null;
		
		IncidentalpriceColumns objIPColumns = (IncidentalpriceColumns)objList.get(0);
		return objIPColumns;
	}
	
	public static List loadIncidentalvalue(String strEpcode) 
	throws Exception {
		IncidentalpricevalueCondition objIPVCondition = new IncidentalpricevalueCondition();
		objIPVCondition.setEpcode(strEpcode);
		IncidentalpricevalueQuery objIPVQuery = new IncidentalpricevalueQuery();
		objIPVQuery.setCondition(objIPVCondition);
		return objIPVQuery.getResults();
	}
	
	public static List loadIncidentalvaluebase(String strEpcode) 
	throws Exception {
		IncidentalvaluebaseCondition objIPVBCondition = new IncidentalvaluebaseCondition();
		objIPVBCondition.setEpcode(strEpcode);
		IncidentalvaluebaseQuery objIPVBQuery = new IncidentalvaluebaseQuery();
		objIPVBQuery.setCondition(objIPVBCondition);
		return objIPVBQuery.getResults();
	}
	
	public static List loadIncidentalstoragechannel(String strEpcode) 
	throws Exception {
		IncidentalstoragechannelCondition objISCCondition = new IncidentalstoragechannelCondition();
		objISCCondition.setEpcode(strEpcode);
		IncidentalstoragechannelQuery objISCQuery = new IncidentalstoragechannelQuery();
		objISCQuery.setCondition(objISCCondition);
		return objISCQuery.getResults();
	}
	
	public static LoadIncidentalResult load(String strEpcode) 
	throws Exception {
		LoadIncidentalResult objLIResult = new LoadIncidentalResult();
		objLIResult.setIPriceColumns(loadIncidentalprice(strEpcode));
		objLIResult.setIPValueColumns(loadIncidentalvalue(strEpcode));
		objLIResult.setIPVBaseColumns(loadIncidentalvaluebase(strEpcode));
		objLIResult.setIPVChannelColumns(loadIncidentalstoragechannel(strEpcode));
		objLIResult.setEnterprise(ExpresspriceDemand.loadEnterprise(strEpcode));
		return objLIResult;
	}
	
	public static void setExpressPriceByColumns(TepExpressprice objTepExpressprice, 
			IncidentalpriceColumns objIPColumns, 
			String strOperId, 
			Session objSession) throws Exception {
		objTepExpressprice.setEpEnddate(DateFormatUtility.getStandardDate(objIPColumns.getEpependdate() + " 23:59:59"));
		objTepExpressprice.setEpStartdate(DateFormatUtility.getStandardDate(objIPColumns.getEpepstartdate() + " 00:00:00"));
		objTepExpressprice.setEpModifydate(DateFormatUtility.getSysdate());
		objTepExpressprice.setEpRemark(objIPColumns.getEpepremark());
		objTepExpressprice.setEpWithdrawsign("N");
		if (!StringUtility.isNull(objIPColumns.getEeeecode())) {
			TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objIPColumns.getEeeecode());
			objTepExpressprice.setTdiEnterpriseelement(objTEE);
		}
		if (!StringUtility.isNull(objIPColumns.getPspscode())) {
			TdiPricestatus objTPS = TdiPricestatusDC.loadByKey(objIPColumns.getPspscode());
			objTepExpressprice.setTdiPricestatus(objTPS);
		}
		TdiOperator objTOP = (TdiOperator)objSession.load(TdiOperator.class, 
				Long.parseLong(strOperId));
		objTepExpressprice.setTdiOperatorByEpOpIdModify(objTOP);
		
		TdiExpresspricekind objTEPK = TdiExpresspricekindDC.loadByKey(IExpressPriceBasicData.PRICEKIND_INCIDENTAL);
		objTepExpressprice.setTdiExpresspricekind(objTEPK);		
	}
	
	public static void setIncidentalPriceByColumns(TepIncidentalprice objTepIncidentalprice, 
			IncidentalpriceColumns objIPColumns, 
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objIPColumns.getChnchncode())) {
			TchnChannel objTCHN = (TchnChannel)objSession.load(TchnChannel.class, 
					objIPColumns.getChnchncode());
			objTepIncidentalprice.setTchnChannel(objTCHN);
		}
		if (!StringUtility.isNull(objIPColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objIPColumns.getCococode());
			objTepIncidentalprice.setTcoCorporation(objTcoCorporation);
		}
		if (!StringUtility.isNull(objIPColumns.getDtdtcode())) {
			TdiDistrict objTdiDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
					objIPColumns.getDtdtcode());
			objTepIncidentalprice.setTdiDistrict(objTdiDistrict);
		}
		if (!StringUtility.isNull(objIPColumns.getPdpdcode())) {
			TdiPricedomain objTPD = TdiPricedomainDC.loadByKey(objIPColumns.getPdpdcode());
			objTepIncidentalprice.setTdiPricedomain(objTPD);
		}
		if (!StringUtility.isNull(objIPColumns.getPgpgcode())) {
			TdiPricegroup objTdiPricegroup = TdiPricegroupDC.loadByKey(objIPColumns.getPgpgcode());
			objTepIncidentalprice.setTdiPricegroup(objTdiPricegroup);
		}
		if (!StringUtility.isNull(objIPColumns.getPkpkcode())) {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(objIPColumns.getPkpkcode());
			objTepIncidentalprice.setTdiProductkind(objTdiProductkind);
		}
	}
	
	public static void setIncidentalValueColumns(TepIncidentalpricevalue objTIPVaule,
			IncidentalpricevalueColumns objIPValueColumns,
			Session objSession) throws Exception {
		objTIPVaule.setIpvAutocalculatesign(objIPValueColumns.getIpvipvautocalculatesign());
		
 		String strCommissionrate = objIPValueColumns.getIpvipvcommissionrate();
		if (StringUtility.isNull(strCommissionrate))
			strCommissionrate = "0";	
		objTIPVaule.setIpvCommissionrate(new BigDecimal(strCommissionrate));
		
 		String strMinimumvalue = objIPValueColumns.getIpvipvminimumvalue();
		if (StringUtility.isNull(strMinimumvalue))
			strMinimumvalue = "0";
		objTIPVaule.setIpvMinimumvalue(new BigDecimal(strMinimumvalue));
		
 		String strMaxvalue = objIPValueColumns.getIpvipvmaxvalue();
		if (StringUtility.isNull(strMaxvalue))
			strMaxvalue = "0";
		objTIPVaule.setIpvMaxvalue(new BigDecimal(strMaxvalue));		
		
		objTIPVaule.setIpvMode(objIPValueColumns.getIpvipvmode());
		
 		String strIpvpricevalue = objIPValueColumns.getIpvipvpricevalue();
		if (StringUtility.isNull(strIpvpricevalue))
			strIpvpricevalue = "0";		
		objTIPVaule.setIpvPricevalue(new BigDecimal(strIpvpricevalue));
		
		objTIPVaule.setIpvRemark(objIPValueColumns.getIpvipvremark());
		objTIPVaule.setIpvReversesign(objIPValueColumns.getIpvipvreversesign());
		
 		String strCarryweight = objIPValueColumns.getIpvipvcarryweight();
		if (StringUtility.isNull(strCarryweight))
			strCarryweight = "0";		
		objTIPVaule.setIpvCarryweight(new BigDecimal(strCarryweight));
		
		if (!StringUtility.isNull(objIPValueColumns.getCtctcode())) {
			TdiCargotype objTdiCargotype = TdiCargotypeDC.loadByKey(objIPValueColumns.getCtctcode());
			objTIPVaule.setTdiCargotype(objTdiCargotype);
		}
		if (!StringUtility.isNull(objIPValueColumns.getCkckcode())) {
			TdiCurrencykind objTCK = TdiCurrencykindDC.loadByKey(objIPValueColumns.getCkckcode());
			objTIPVaule.setTdiCurrencykind(objTCK);
		}
		if (!StringUtility.isNull(objIPValueColumns.getEstestcode())) {
			TdiExpressspecialtype objTEST = TdiExpressspecialtypeDC.loadByKey(objIPValueColumns.getEstestcode());
			objTIPVaule.setTdiExpressspecialtype(objTEST);
		}
		if (!StringUtility.isNull(objIPValueColumns.getFkfkcode())) {
			TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objIPValueColumns.getFkfkcode());
			objTIPVaule.setTdiFeekind(objTdiFeekind);
		}
		if (!StringUtility.isNull(objIPValueColumns.getPmpmcode())) {
			TdiPaymentmode objTdiPaymentmode = TdiPaymentmodeDC.loadByKey(objIPValueColumns.getPmpmcode());	
			objTIPVaule.setTdiPaymentmode(objTdiPaymentmode);
		}
		if (!StringUtility.isNull(objIPValueColumns.getUtutcode())) {
			TdiUnittype objTdiUnittype = TdiUnittypeDC.loadByKey(objIPValueColumns.getUtutcode());	
			objTIPVaule.setTdiUnittype(objTdiUnittype);
		}
	}
	
	public static void setIvaluebaseByColumns(TepIncidentalvaluebase objTIValueBase,
			IncidentalvaluebaseColumns objIVBColumns,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objIVBColumns.getIvbcomp_idfkcode())) {
			TdiFeekind objTdiFeekind = (TdiFeekind)objSession.load(TdiFeekind.class, 
					objIVBColumns.getIvbcomp_idfkcode());
			objTIValueBase.setTdiFeekind(objTdiFeekind);
		}
	}
	
	public static void setIvalueChannelByColumns(TepIncidentalstorgechannel objTISChannel,
			IncidentalstoragechannelColumns objISCColumns,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objISCColumns.getChnchncode())) {
			TchnChannel objTchnChannel = (TchnChannel)objSession.load(TchnChannel.class, 
					objISCColumns.getChnchncode());
			objTISChannel.setTchnChannel(objTchnChannel);
		}
	}
	
	
	public static List<IncidentalvaluebaseColumns> getIvalueBaseByColumns(List listIncidentalValueBase,
			String strIvid) {
		if (listIncidentalValueBase == null || listIncidentalValueBase.size() < 1)
			return null;
		
		List<IncidentalvaluebaseColumns> list = new ArrayList<IncidentalvaluebaseColumns>();
		for (int i = 0; i < listIncidentalValueBase.size(); i++) {
			IncidentalvaluebaseColumns objIValuebaseColumns = (IncidentalvaluebaseColumns)listIncidentalValueBase.get(i);
			if (objIValuebaseColumns.getIvbcomp_idipvid().equals(strIvid))
				list.add(objIValuebaseColumns);
		}
		return list;
	}
	
	public static List<IncidentalstoragechannelColumns> getIvalueChannelByColumns(List listIncidentalValueChannel,
			String strIvid) {
		if (listIncidentalValueChannel == null || listIncidentalValueChannel.size() < 1)
			return null;
		
		List<IncidentalstoragechannelColumns> list = new ArrayList<IncidentalstoragechannelColumns>();
		for (int i = 0; i < listIncidentalValueChannel.size(); i++) {
			IncidentalstoragechannelColumns objISCColumns = (IncidentalstoragechannelColumns)listIncidentalValueChannel.get(i);
			if (objISCColumns.getIsccomp_idipvid().equals(strIvid))
				list.add(objISCColumns);
		}
		return list;
	}	
}
