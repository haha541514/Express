package kyle.leis.es.company.channel.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.da.ChannelCondition;
import kyle.leis.es.company.channel.da.ChannelQuery;
import kyle.leis.es.company.channel.da.ChanneladdressColumns;
import kyle.leis.es.company.channel.da.ChanneladdressQuery;
import kyle.leis.es.company.channel.da.ChannelseqColumns;
import kyle.leis.es.company.channel.da.ChannelseqQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiChannelstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCustomlabelformatDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpressspecialtypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiLabelformatDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiServerstructuregroupDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSignouttypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybillcodekindDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiChannelstatus;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiCustomlabelformat;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiExpressspecialtype;
import kyle.leis.hi.TdiLabelformat;
import kyle.leis.hi.TdiServerstructuregroup;
import kyle.leis.hi.TdiSignouttype;
import kyle.leis.hi.TdiWaybillcodekind;
import net.sf.hibernate.Session;

public class ChannelDemand {
	public static List query(ChannelCondition objChannelCondition) 
	throws Exception {
		ChannelQuery objChannelQuery = new ChannelQuery();
		objChannelQuery.setCondition(objChannelCondition);
		return objChannelQuery.getResults();
	}
	
	public static ChannelColumns load(String strChncode, boolean isUseCache) throws Exception {
		ChannelQuery objChannelQuery = new ChannelQuery();
		objChannelQuery.setChncode(strChncode);
		objChannelQuery.setUseCachesign(isUseCache);
		List objList = objChannelQuery.getResults();
		if (objList == null) return null;
		return (ChannelColumns)objList.get(0);
	}
	
	public static ChanneladdressColumns loadChanneladdress(String strChncode) throws Exception {
		ChanneladdressQuery objChanneladdressQuery = new ChanneladdressQuery();
		objChanneladdressQuery.setChncode(strChncode);
		objChanneladdressQuery.setUseCachesign(true);
		List listResults = objChanneladdressQuery.getResults();
		if (listResults == null || listResults.size() < 1) return null;
		return (ChanneladdressColumns)listResults.get(0);
	}	
	
	
	public static String getOperationLabelFormat(String strChncode) throws Exception {
		ChannelColumns objChannelColumns = load(strChncode, true);
		if (objChannelColumns == null) return "";
		
		String strBckcode = objChannelColumns.getMbckbckcode();
		if (StringUtility.isNull(strBckcode)) return "";
		
		TdiWaybillcodekind objTdiWaybillcodekind = TdiWaybillcodekindDC.loadByKey(strBckcode);
		TdiLabelformat objTdiLabelformat = objTdiWaybillcodekind.getTdiLabelformat();
		if (objTdiLabelformat == null) return "";
		
		return objTdiWaybillcodekind.getTdiLabelformat().getLfCode();
	}
	
	public static String getCustomLabel(String strChncode) throws Exception {
		ChannelColumns objChannelColumns = load(strChncode, true);
		if (objChannelColumns == null) return "";
		
		return objChannelColumns.getClfclfcode();
	}	
	
	
	public static String getNewChannelcode() throws Exception {
		ChannelseqQuery objChannelseqQuery = new ChannelseqQuery();
		List objList = objChannelseqQuery.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception ("无法根据渠道的序列号生成主键!"));
		return ((ChannelseqColumns)objList.get(0)).getChannelseq();
	}
	
	public static void setChannelByColumns(TchnChannel objTchnChannel,
			ChannelColumns objChannelColumns,
			Session objSession) throws Exception {
		objTchnChannel.setChnEname(objChannelColumns.getChnchnename());
		objTchnChannel.setChnManifestseriesnumber(objChannelColumns.getChnchnmanifestseriesnumber());
		objTchnChannel.setChnMasteraccount(objChannelColumns.getEraccount());
		objTchnChannel.setChnName(objChannelColumns.getChnchnname());
		objTchnChannel.setChnPaymentaccount(objChannelColumns.getChnchnpaymentaccount());
		objTchnChannel.setChnSename(objChannelColumns.getChnchnsename());
		objTchnChannel.setChnSname(objChannelColumns.getChnchnsname());
		objTchnChannel.setCstCode(objChannelColumns.getChncstcode());
		objTchnChannel.setChnWpxspsmappingname(objChannelColumns.getChnchnwpxspsmappingname());
		objTchnChannel.setChnLabelprintprefix(objChannelColumns.getChnchnlabelprintprefix());
		objTchnChannel.setChnChawbprefix(objChannelColumns.getChnchnchawbprefix());
		
		if (StringUtility.isNull(objChannelColumns.getChnchnsobypiecessign())) {
			objTchnChannel.setChnSobypiecessign("N");
		} else {
			objTchnChannel.setChnSobypiecessign(objChannelColumns.getChnchnsobypiecessign());
		}
		
		String strMaxdtransfercharge = objChannelColumns.getChnchnmaxdtransfercharge();
		if (StringUtility.isNull(strMaxdtransfercharge))
			strMaxdtransfercharge = "0";
		objTchnChannel.setChnMaxdtransfercharge(new BigDecimal(strMaxdtransfercharge));
		
		String strMaxmtransfercharge = objChannelColumns.getChnchnmaxmtransfercharge();
		if (StringUtility.isNull(strMaxmtransfercharge))
			strMaxmtransfercharge = "0";
		objTchnChannel.setChnMaxmtransfercharge(new BigDecimal(strMaxmtransfercharge));
		
		if (!StringUtility.isNull(objChannelColumns.getCkckcode())) {
			TdiCurrencykind objTCK= TdiCurrencykindDC.loadByKey(objChannelColumns.getCkckcode());
			objTchnChannel.setTdiCurrencykind(objTCK);
		}
		
		if (!StringUtility.isNull(objChannelColumns.getLflfcode())) {
			TdiLabelformat objTLF = TdiLabelformatDC.loadByKey(objChannelColumns.getLflfcode());
			objTchnChannel.setTdiLabelformatByChnCustomerlabel(objTLF);
		}
		
		if (!StringUtility.isNull(objChannelColumns.getClfclfcode())) {
			TdiCustomlabelformat objCLF = TdiCustomlabelformatDC.loadByKey(objChannelColumns.getClfclfcode());
			objTchnChannel.setTdiCustomlabelformat(objCLF);
		}		
		if (!StringUtility.isNull(objChannelColumns.getEstestcode())) {
			TdiExpressspecialtype objTEST = TdiExpressspecialtypeDC.loadByKey(objChannelColumns.getEstestcode());
			objTchnChannel.setTdiExpressspecialtype(objTEST);
		} else {
			objTchnChannel.setTdiExpressspecialtype(null);
		}
		// 当日最大出货重量限制
		if (!StringUtility.isNull(objChannelColumns.getChnchnmaxdtransferweight()))
			objTchnChannel.setChnMaxdtransferweight(new BigDecimal(objChannelColumns.getChnchnmaxdtransferweight()));
		
		if (!StringUtility.isNull(objChannelColumns.getChnchnmaxoverdeclarevalue()))
			objTchnChannel.setChnMaxoverdeclarevalue(new BigDecimal(objChannelColumns.getChnchnmaxoverdeclarevalue()));
		
		
		TcoCorporation objTCO = (TcoCorporation)objSession.load(TcoCorporation.class, 
				objChannelColumns.getCococode());
		objTchnChannel.setTcoCorporation(objTCO);
		
		TdiChannelstatus objTCS = TdiChannelstatusDC.loadByKey(objChannelColumns.getCscscode());
		objTchnChannel.setTdiChannelstatus(objTCS);
		
		TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objChannelColumns.getEeeecode()); 
		objTchnChannel.setTdiEnterpriseelement(objTEE);
		
		if (!StringUtility.isNull(objChannelColumns.getSotsotcode())) {
			TdiSignouttype objTST = TdiSignouttypeDC.loadByKey(objChannelColumns.getSotsotcode());
			objTchnChannel.setTdiSignouttype(objTST);
		}
		
		if(!StringUtility.isNull(objChannelColumns.getSsgssgcode())){
			TdiServerstructuregroup objSSG = TdiServerstructuregroupDC.loadByKey(objChannelColumns.getSsgssgcode());
			objTchnChannel.setTdiServerstructuregroup(objSSG);
		}else{
			objTchnChannel.setTdiServerstructuregroup(null);
		}
		
		if((!StringUtility.isNull(objChannelColumns.getSbckbckcode()))){
			TdiWaybillcodekind objSBCK = TdiWaybillcodekindDC.loadByKey(objChannelColumns.getSbckbckcode());
			objTchnChannel.setTdiWaybillcodekindByChnSubbillcodekind(objSBCK);
		}else{
			objTchnChannel.setTdiWaybillcodekindByChnSubbillcodekind(null);
		}
		
		if(!StringUtility.isNull(objChannelColumns.getMbckbckcode())){
			TdiWaybillcodekind objMBCK = TdiWaybillcodekindDC.loadByKey(objChannelColumns.getMbckbckcode());
			objTchnChannel.setTdiWaybillcodekindByChnMainbillcodekind(objMBCK);
		}else{
			objTchnChannel.setTdiWaybillcodekindByChnMainbillcodekind(null);
		}
		
	}
}
