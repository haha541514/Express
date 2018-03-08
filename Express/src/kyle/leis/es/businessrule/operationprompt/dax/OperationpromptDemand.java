package kyle.leis.es.businessrule.operationprompt.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptColumns;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptCondition;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptQuery;
import kyle.leis.es.businessrule.operationprompt.da.OptchannelCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptchannelQuery;
import kyle.leis.es.businessrule.operationprompt.da.OptcorporationCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptcorporationQuery;
import kyle.leis.es.businessrule.operationprompt.da.OptdeparturedistrictCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptdeparturedistrictQuery;
import kyle.leis.es.businessrule.operationprompt.da.OptexpressspecialtypeCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptexpressspecialtypeQuery;
import kyle.leis.es.businessrule.operationprompt.da.OptoperationtacheCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptoperationtacheQuery;
import kyle.leis.es.businessrule.operationprompt.da.OptorigindistrictCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptorigindistrictQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBusinessrulekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCargotypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPaymentmodeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TbrOperationprompt;
import kyle.leis.hi.TbrOptcorporation;
import kyle.leis.hi.TbrOptcorporationPK;
import kyle.leis.hi.TcoCorporation;
import net.sf.hibernate.Session;

public class OperationpromptDemand {

	public static List query(OperationpromptCondition objOPTCondition) throws Exception
	{
		OperationpromptQuery objOPTQuery = new OperationpromptQuery();
		if(!StringUtility.isNull(objOPTCondition.getValiddate1()))
			objOPTCondition.setValiddate2(objOPTCondition.getValiddate1());
		objOPTQuery.setCondition(objOPTCondition);
		return objOPTQuery.getResults();
	}
	
	public static List queryCorporation(OptcorporationCondition objOPTCCondition) throws Exception
	{
		OptcorporationQuery objOPTCQuery = new OptcorporationQuery();
		objOPTCQuery.setCondition(objOPTCCondition);
		return objOPTCQuery.getResults();
	}
	
	/**
	 * 判断是否存在客户或服务商
	 * @param strBrId
	 * @param strCsSign
	 * @return true:存在,false不存在
	 * @throws Exception
	 */
	public static boolean isExistCorporation(String strBrId,String strCsSign) throws Exception
	{
		OptcorporationCondition objOPTCCondition = new OptcorporationCondition();
		objOPTCCondition.setBrid(strBrId);
		objOPTCCondition.setOptccssign(strCsSign);
		List objList = queryCorporation(objOPTCCondition);
		if(CollectionUtility.isNull(objList))
			return false;
		return true;
	}
	
	public static List queryChannel(OptchannelCondition objOPTChnCondtioon) throws Exception
	{
		OptchannelQuery objOPTChnQuery = new OptchannelQuery();
		objOPTChnQuery.setCondition(objOPTChnCondtioon);
		return objOPTChnQuery.getResults();
	}
	
	public static boolean isExistChannel(String strBrId,String strCsSign) throws Exception
	{
		OptchannelCondition objOPTChnCondtioon = new OptchannelCondition();
		objOPTChnCondtioon.setBrid(strBrId);
		objOPTChnCondtioon.setOptncssign(strCsSign);
		List objList = queryChannel(objOPTChnCondtioon);
		if(CollectionUtility.isNull(objList))
			return false;
		return true;
	}
	
	public static List queryEst(OptexpressspecialtypeCondition objOPTEstCondition) throws Exception
	{
		OptexpressspecialtypeQuery objOPTEstQuery = new OptexpressspecialtypeQuery();
		objOPTEstQuery.setCondition(objOPTEstCondition);
		return objOPTEstQuery.getResults();
	}
	
	public static boolean isExistEst(String strBrId) throws Exception
	{
		OptexpressspecialtypeCondition objOPTEstCondition = new OptexpressspecialtypeCondition();
		objOPTEstCondition.setBrid(strBrId);
		List objList = queryEst(objOPTEstCondition);
		if(CollectionUtility.isNull(objList))
			return false;
		return true;
	}
	
	public static List queryOptOt(OptoperationtacheCondition objOPTOtCondition) throws Exception
	{
		OptoperationtacheQuery objOPTOtQuery = new OptoperationtacheQuery();
		objOPTOtQuery.setCondition(objOPTOtCondition);
		return objOPTOtQuery.getResults();
	}
	
	public static boolean isExistOt(String strBrId) throws Exception
	{
		OptoperationtacheCondition objOPTOtCondition = new OptoperationtacheCondition();
		objOPTOtCondition.setBrid(strBrId);
		List objList = queryOptOt(objOPTOtCondition);
		if(CollectionUtility.isNull(objList))
			return false;
		return true;
	}
	
	public static List queryOptOdt(OptorigindistrictCondition objOPTDtCondition) throws Exception
	{
		OptorigindistrictQuery objOPTOdtQuery = new OptorigindistrictQuery();
		objOPTOdtQuery.setCondition(objOPTDtCondition);
		return objOPTOdtQuery.getResults();
	}
	
	public static boolean isExixtOdt(String strBrId) throws Exception
	{
		OptorigindistrictCondition objOPTDtCondition = new OptorigindistrictCondition();
		objOPTDtCondition.setBrid(strBrId);
		List objList = queryOptOdt(objOPTDtCondition);
		if(CollectionUtility.isNull(objList))
			return false;
		return true;
	}
	
	public static List queryOptDdt(OptdeparturedistrictCondition objOPTDdtCondition) throws Exception
	{
		OptdeparturedistrictQuery objOPTDDTQuery = new OptdeparturedistrictQuery();
		objOPTDDTQuery.setCondition(objOPTDdtCondition);
		return objOPTDDTQuery.getResults();
	}
	
	public static boolean isExixtDdt(String strBrId) throws Exception
	{
		OptdeparturedistrictCondition objOPTDdtCondition = new OptdeparturedistrictCondition();
		objOPTDdtCondition.setBrid(strBrId);
		List objList = queryOptDdt(objOPTDdtCondition);
		if(CollectionUtility.isNull(objList))
			return false;
		return true;
	}
	
	public static OperationPromptQueryReturn loadByBrId(String strBrId) throws Exception
	{
		OperationPromptQueryReturn objOPTQueryReturn = new OperationPromptQueryReturn();
		//加载操作规则基本信息
		OperationpromptCondition objOPTCondition = new OperationpromptCondition();
		objOPTCondition.setBrid(strBrId);
		OperationpromptColumns objOPTColumns = (OperationpromptColumns)query(objOPTCondition).get(0);
		objOPTQueryReturn.setM_objOPTColumns(objOPTColumns);
		//加载户
		OptcorporationCondition objOPTCsCondition = new OptcorporationCondition();
		objOPTCsCondition.setOptccssign("Y");
		objOPTCsCondition.setBrid(strBrId);
		List listCustomer = queryCorporation(objOPTCsCondition);
		objOPTQueryReturn.setM_listCustomer(listCustomer);
		//加载服务商
		OptcorporationCondition objOPTSpCondition = new OptcorporationCondition();
		objOPTSpCondition.setOptccssign("N");
		objOPTSpCondition.setBrid(strBrId);
		List listSupplier = queryCorporation(objOPTSpCondition);
		objOPTQueryReturn.setM_listSupplier(listSupplier);
		//加载代理渠道
		/*OptchannelCondition objOPTCsChnCondition = new OptchannelCondition();
		objOPTCsChnCondition.setBrid(strBrId);
		objOPTCsChnCondition.setOptncssign("Y");
		List listCustomerChn = queryChannel(objOPTCsChnCondition);
		objOPTQueryReturn.setM_listCsChannel(listCustomerChn);*/
		//加载服务渠道
		OptchannelCondition objOPTSpChnCondition = new OptchannelCondition();
		objOPTSpChnCondition.setBrid(strBrId);
		objOPTSpChnCondition.setOptncssign("N");
		List listSupplierChn = queryChannel(objOPTSpChnCondition);
		objOPTQueryReturn.setM_listSpChannel(listSupplierChn);
		//加载特殊类型
		OptexpressspecialtypeCondition objOPTEstCondition = new OptexpressspecialtypeCondition();
		objOPTEstCondition.setBrid(strBrId);
		List listEst = queryEst(objOPTEstCondition);
		objOPTQueryReturn.setM_listExpressSpecialType(listEst);
		//加载操作环节
		OptoperationtacheCondition objOPTOtCondition = new OptoperationtacheCondition();
		objOPTOtCondition.setBrid(strBrId);
		List listOptOt = queryOptOt(objOPTOtCondition);
		objOPTQueryReturn.setM_listOperationTache(listOptOt);
		//加载起源地
		OptorigindistrictCondition objOPTOdtCondition = new OptorigindistrictCondition();
		objOPTOdtCondition.setBrid(strBrId);
		List listOptOd = queryOptOdt(objOPTOdtCondition);
		objOPTQueryReturn.setM_listOriginDistrict(listOptOd);
		//加载目的地
		OptdeparturedistrictCondition objOPTDdtCondition = new OptdeparturedistrictCondition();
		objOPTDdtCondition.setBrid(strBrId);
		List listOptDdt = queryOptDdt(objOPTDdtCondition);
		objOPTQueryReturn.setM_listDepartureDistrict(listOptDdt);
		
		return objOPTQueryReturn;
	}
	
	
	
	public static void setOptcorporationByParams(TbrOptcorporation objTbrOptcorporation,Long lBrId,String strCocode,String strOptcCssign,TbrOperationprompt objTbrOperationprompt,Session objSession) throws Exception
	{
		TbrOptcorporationPK comp_id = new TbrOptcorporationPK();
		comp_id.setBrId(lBrId);
		comp_id.setCoCode(strCocode);
		objTbrOptcorporation.setComp_id(comp_id);
		/*if(StringUtility.isNull(strOptcCssign))
			objTbrOptcorporation.setOptcCssign("Y");//客户
		else
			objTbrOptcorporation.setOptcCssign(strOptcCssign);//服务商*/
		objTbrOptcorporation.setOptcCssign(strOptcCssign);
		objTbrOptcorporation.setTbrOperationprompt(objTbrOperationprompt);
		objTbrOptcorporation.setTcoCorporation((TcoCorporation)objSession.load(TcoCorporation.class,strCocode));
	}
	
	public static void setBusinessruleByColumns(TbrBusinessrule objTbrBusinessrule,OperationpromptColumns objOPTColumns) throws Exception
	{
		objTbrBusinessrule.setBrEname(objOPTColumns.getBrbrename());
		objTbrBusinessrule.setBrEnddate(DateFormatUtility.getStandardDate(objOPTColumns.getBrbrenddate()));
		objTbrBusinessrule.setBrName(objOPTColumns.getBrbrname());
		objTbrBusinessrule.setBrRemark(objOPTColumns.getBrbrremark());
		objTbrBusinessrule.setBrStartdate(DateFormatUtility.getStandardDate(objOPTColumns.getBrbrstartdate()));
		objTbrBusinessrule.setTdiBusinessrulekind(TdiBusinessrulekindDC.loadByKey("A04"));//
		objTbrBusinessrule.setTdiSimplestatus(TdiSimplestatusDC.loadByKey(objOPTColumns.getSssscode()));
	}
	
	public static void setOperationpromptByColumns(TbrOperationprompt objTbrOperationprompt,OperationpromptColumns objOPTColumns) throws Exception
	{
		objTbrOperationprompt.setOptChargeweightbegin(new BigDecimal(objOPTColumns.getOptoptchargeweightbegin()));
		objTbrOperationprompt.setOptChargeweightend(new BigDecimal(objOPTColumns.getOptoptchargeweightend()));
		objTbrOperationprompt.setOptContent(objOPTColumns.getOptoptcontent());
		objTbrOperationprompt.setOptDeclarevaluebegin(new BigDecimal(objOPTColumns.getOptoptdeclarevaluebegin()));
		objTbrOperationprompt.setOptDeclarevalueend(new BigDecimal(objOPTColumns.getOptoptdeclarevalueend()));
		objTbrOperationprompt.setOptName(objOPTColumns.getOptoptname());
		objTbrOperationprompt.setOptPiecegrossweightbegin(new BigDecimal(objOPTColumns.getOptoptpiecegrossweightbegin()));
		objTbrOperationprompt.setOptPiecegrossweightend(new BigDecimal(objOPTColumns.getOptoptpiecegrossweightend()));
		objTbrOperationprompt.setOptUniversalachannelsign(objOPTColumns.getOptoptuniversalachannelsign());
		objTbrOperationprompt.setOptUniversalcustomersign(objOPTColumns.getOptoptuniversalcustomersign());
		objTbrOperationprompt.setOptUniversaldeparturesign(objOPTColumns.getOptoptuniversaldeparturesign());
		objTbrOperationprompt.setOptUniversaldestinationsign(objOPTColumns.getOptoptuniversaldestinationsign());
		objTbrOperationprompt.setOptUniversalschannelsign(objOPTColumns.getOptoptuniversalschannelsign());
		objTbrOperationprompt.setOptUniversalservesign(objOPTColumns.getOptoptuniversalservesign());
		objTbrOperationprompt.setOptUniversaltachesign(objOPTColumns.getOptoptuniversaltachesign());
		objTbrOperationprompt.setOptVwgwformular(objOPTColumns.getOptoptvwgwformular());
		objTbrOperationprompt.setPtCode(objOPTColumns.getOptptcode());
		objTbrOperationprompt.setTdiCargotype(TdiCargotypeDC.loadByKey(objOPTColumns.getCtctcode()));
		objTbrOperationprompt.setTdiPaymentmode(TdiPaymentmodeDC.loadByKey(objOPTColumns.getPmpmcode()));
		objTbrOperationprompt.setTdiProductkind(TdiProductkindDC.loadByKey(objOPTColumns.getPkpkcode()));
		
	}
}
