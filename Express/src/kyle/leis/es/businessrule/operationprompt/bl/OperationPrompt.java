package kyle.leis.es.businessrule.operationprompt.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.SingleColumnQuery;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.businessrule.businessrules.tp.AModifyBusinessStatusTrans;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptColumns;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptchannelColumns;
import kyle.leis.es.businessrule.operationprompt.da.OptchannelCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptcorporationColumns;
import kyle.leis.es.businessrule.operationprompt.da.OptcorporationCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptdeparturedistrictColumns;
import kyle.leis.es.businessrule.operationprompt.da.OptdeparturedistrictCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptexpressspecialtypeColumns;
import kyle.leis.es.businessrule.operationprompt.da.OptexpressspecialtypeCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptoperationtacheColumns;
import kyle.leis.es.businessrule.operationprompt.da.OptoperationtacheCondition;
import kyle.leis.es.businessrule.operationprompt.da.OptorigindistrictColumns;
import kyle.leis.es.businessrule.operationprompt.da.OptorigindistrictCondition;
import kyle.leis.es.businessrule.operationprompt.dax.OperationpromptColumnsEX;
import kyle.leis.es.businessrule.operationprompt.dax.OperationpromptDemand;
import kyle.leis.es.businessrule.operationprompt.tp.ModifyOPTStatusTransaction;
import kyle.leis.es.businessrule.operationprompt.tp.SaveOperationPromptTransaction;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class OperationPrompt extends ABusinessrule{
	/**
	 * 添加操作规则
	 * @param objOPTColumns
	 * @param astrCustomer
	 * @param astrSupplier
	 * @param astrChannel
	 * @param strOptn_CsSign
	 * @param astrSpecialType
	 * @param astrOperationTache
	 * @param astrOdtCode
	 * @param astrDdtCode
	 * @param strOperator
	 * @return
	 * @throws Exception
	 */
	public OperationpromptColumns add(OperationpromptColumns objOPTColumns,String[] astrCustomer,String[] astrSupplier,String[] astrChannel,String strOptn_CsSign,String[] astrSpecialType,String[] astrOperationTache,String[] astrOdtCode,String[] astrDdtCode,String strOperator) throws Exception
	{
		SaveOperationPromptTransaction objSaveOPTTrans = new SaveOperationPromptTransaction();
		objSaveOPTTrans.setParameter(objOPTColumns, astrCustomer, astrSupplier, astrChannel, strOptn_CsSign, astrSpecialType, astrOperationTache, astrOdtCode, astrDdtCode, strOperator);
		objSaveOPTTrans.execute();
		String strNewBrid = objSaveOPTTrans.getNewBrid();
		OperationpromptCondition objOPTCondition = new OperationpromptCondition();
		objOPTCondition.setBrid(strNewBrid);
		return (OperationpromptColumns)OperationpromptDemand.query(objOPTCondition).get(0);
	}
	
	/**
	 * 制单等操作规则
	 * @param strCwcode
	 * @param strOtCode
	 * @return
	 * @throws Exception
	 */
	public String[] verifyOpt(String strCwcode,String strOtCode) throws Exception
	{
		HousewaybillColumns objHWBColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		List listCorewaybillpieces = CorewaybillpiecesDemand.load(strCwcode);
		return verify(objHWBColumns,listCorewaybillpieces,strOtCode);
	}
	
	/**
	 * 出货操作规则
	 * @param strCustomerewbcode
	 * @param strOtCode
	 * @return
	 * @throws Exception
	 */
	public String[] verifySignOutopt(String strServerewbcode,String strOtCode) throws Exception
	{
		HousewaybillColumns objHWBColumns = HousewaybillDemand.load(strServerewbcode, "S");
		if(objHWBColumns == null) return null;
		List listCorewaybillpieces = CorewaybillpiecesDemand.load(objHWBColumns.getHwcwcode());
		return verify(objHWBColumns,listCorewaybillpieces,strOtCode);
	}
	
	private List<OperationpromptColumns> copyOperationpromptList(List<OperationpromptColumns> listOPTColumns) {
		List<OperationpromptColumns> listCopyOPTColumns = new ArrayList<OperationpromptColumns>();
		if (!CollectionUtility.isNull(listOPTColumns))
			for (int i = 0; i < listOPTColumns.size(); i++) {
				OperationpromptColumnsEX obj = (OperationpromptColumnsEX)listOPTColumns.get(i);
				listCopyOPTColumns.add(obj);
			}
		return listCopyOPTColumns;
	}
	
	
	
	public String[] verify(HousewaybillColumns objHwColumns,List <CorewaybillpiecesColumns>listCorewaybillpieces,String strOtCode) throws Exception
	{
		/*
		if (objHwColumns.getCwscwscode().equals("IP"))
			return null;
		*/
		String strVolumeweight = CorewaybillDemand.getVolumeweight(objHwColumns.getHwcwcode());
		//构建查询条件,并查找operationprompt表
		OperationpromptCondition objOPTCondition = buildOPTCondition(objHwColumns);
		List<OperationpromptColumns> listOPTColumns  = OperationpromptDemand.query(objOPTCondition);
		if(CollectionUtility.isNull(listOPTColumns))
			return null;

		List<OperationpromptColumns> listCopyOPTColumns = copyOperationpromptList(listOPTColumns);		
		//环节
		listOPTColumns = verifyOpt(listOPTColumns, listCopyOPTColumns, strOtCode);
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		//判断单件重量
		for(int i=0; i<listOPTColumns.size(); i++)
		{
			boolean isExistGrossweight = false;
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);
			for(CorewaybillpiecesColumns objCWBColumns :listCorewaybillpieces)
			{
				BigDecimal objGrossweight = new BigDecimal(objCWBColumns.getCpcpgrossweight());
				BigDecimal objGrossweightbegin = new BigDecimal(listOPTColumns.get(i).getOptoptpiecegrossweightbegin());
				BigDecimal objGrosseightend = new BigDecimal(listOPTColumns.get(i).getOptoptpiecegrossweightend());
				if(objGrossweight.compareTo(objGrossweightbegin)>=0 && objGrossweight.compareTo(objGrosseightend)<=0)
				{
					isExistGrossweight = true;
					break;
				}
			}
			if(!isExistGrossweight)
				listCopyOPTColumns.remove(objOPTColumnsEX);
		}
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		//判断公式
		for(int i=0; i<listOPTColumns.size(); i++)
		{
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);
			
			String strFormular = listOPTColumns.get(i).getOptoptvwgwformular().toLowerCase();
			if (!StringUtility.isNull(strFormular) && (strFormular.equals("1=1") || strFormular.equals("0 = 0")))
				continue;
			strFormular = strFormular.replace("gw", objHwColumns.getCwcwgrossweight());
			strFormular = strFormular.replace("vw", strVolumeweight);
			strFormular = strFormular.replace("cw", objHwColumns.getCwcwchargeweight());
			String strSqltext = "select 1 from dual where " + strFormular;
			String strFlag = SingleColumnQuery.getColumnData(strSqltext);
			
			if(!strFlag.equals("1"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
		}		
		//客户
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		listOPTColumns = verifyCustomer(listOPTColumns, listCopyOPTColumns, objHwColumns.getCcococode(),"Y");
		//服务商
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		listOPTColumns = verifyCustomer(listOPTColumns, listCopyOPTColumns, objHwColumns.getCcococode(),"N");
		//服务渠道
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		listOPTColumns = verifyChannel(listOPTColumns, listCopyOPTColumns, objHwColumns.getSchnchncode());
		//起运地
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		listOPTColumns = verifyOdt(listOPTColumns,listCopyOPTColumns, objHwColumns.getOdtdtcode());
		// 申报价值
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		listOPTColumns = verifyDeclare(listOPTColumns, listCopyOPTColumns, objHwColumns);
		//目的地
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		String strDdtcode = objHwColumns.getDdtdtcode();
		if (StringUtility.isNull(strDdtcode))
			strDdtcode = objHwColumns.getSdtdtcode();
		listOPTColumns = verifyDdt(listOPTColumns, listCopyOPTColumns, strDdtcode);//
		//特殊类型
		listOPTColumns = copyOperationpromptList(listCopyOPTColumns);
		if(!StringUtility.isNull(objHwColumns.getHwcwcode()))
		{
			List<SpecialtypeColumns> listSpecialtypeColumns = SpecialtypeDemand.load(objHwColumns.getHwcwcode());
			listOPTColumns = verifyEst(listOPTColumns, listCopyOPTColumns, listSpecialtypeColumns);
		}
		
		String[] astrContent = new String[listCopyOPTColumns.size()];
		for(int i=0; i<listCopyOPTColumns.size(); i++)
		{
			astrContent[i] = listCopyOPTColumns.get(i).getOptoptcontent();
		}
		return astrContent;
	}
	
	/**
	 * 有效环节
	 * @param listOPTColumns
	 * @param strOtcode
	 * @return
	 * @throws Exception
	 */
	public List<OperationpromptColumns> verifyOpt(List<OperationpromptColumns> listOPTColumns,
			List<OperationpromptColumns> listCopyOPTColumns,
			String strOtcode) throws Exception
	{
		String strBrid = buildAllPrimaryString(listOPTColumns);
		OptoperationtacheCondition objOTCondition = new OptoperationtacheCondition();
		objOTCondition.setOtcode(strOtcode);
		objOTCondition.setBrid(strBrid);
		objOTCondition.setUseCacheSign(true);
		List<OptoperationtacheColumns> listOtColumns = OperationpromptDemand.queryOptOt(objOTCondition);
		
		for(int i = 0; i < listOPTColumns.size(); i++)
		{
			//通用有效
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);
			if(listOPTColumns.get(i).getOptoptuniversaltachesign().equals("Y"))
				continue;
			boolean isExists = isExists(listOtColumns, objOPTColumnsEX.getOptbrid());
			if(!isExists && listOPTColumns.get(i).getOptoptuniversaltachesign().equals("P"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
			else if(isExists && listOPTColumns.get(i).getOptoptuniversaltachesign().equals("N"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
		}
		return listCopyOPTColumns;
	}
	
	private boolean isExists(List<OptoperationtacheColumns> listOtColumns,
			String strBrid) {
		if (listOtColumns == null || listOtColumns.size() < 1)
			return false;
		for(int i = 0; i < listOtColumns.size(); i++) {
			if (listOtColumns.get(i).getOptotcomp_idbrid().equals(strBrid))
				return true;
		}
		return false;
	}
	
	public List<OperationpromptColumns> verifyDeclare(List<OperationpromptColumns> listOPTColumns,
			List<OperationpromptColumns> listCopyOPTColumns,
			HousewaybillColumns objHwColumns) throws Exception
	{
		List listCargoginfo = CargoInfoDemand.queryByCwCode(objHwColumns.getHwcwcode());
		BigDecimal objSumTotalPrice = new BigDecimal("0");
		if (listCargoginfo != null && listCargoginfo.size() > 0) {
			for (int i = 0; i < listCargoginfo.size(); i++) {
				CargoinfoColumns obj = (CargoinfoColumns)listCargoginfo.get(i);
				BigDecimal objTotalpriece = new BigDecimal(obj.getCicitotalprice());
				objSumTotalPrice = objSumTotalPrice.add(objTotalpriece);
			}
		}
		for(int i=0; i<listOPTColumns.size(); i++)
		{
			//通用有效
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);
			String strBegin = objOPTColumnsEX.getOptoptdeclarevaluebegin();
			String strEnd = objOPTColumnsEX.getOptoptdeclarevalueend();				
			
			if(StringUtility.isNull(strBegin) || StringUtility.isNull(strEnd))
				continue;
			if (new BigDecimal(strBegin).compareTo(new BigDecimal("0")) <= 0 )
				continue;
			// 设置了申报价值，而且货物类型为文件则不满足此条件，不用给出提示
			if (objHwColumns.getCtctcode().equals("ADOX")) {
				listCopyOPTColumns.remove(objOPTColumnsEX);
			}
			if (objSumTotalPrice.compareTo(new BigDecimal(strBegin)) >= 0 &&
					objSumTotalPrice.compareTo(new BigDecimal(strEnd)) <= 0) {
				continue;
			} else {
				listCopyOPTColumns.remove(objOPTColumnsEX);
			}
		}
		return listCopyOPTColumns;
	}	
	
	
	/**
	 * 有效客户
	 * @param listOPTColumns
	 * @param strCocode
	 * @param strCsSign
	 * @return
	 * @throws Exception
	 */
	public List<OperationpromptColumns> verifyCustomer(List<OperationpromptColumns> listOPTColumns,
			List<OperationpromptColumns> listCopyOPTColumns,
			String strCocode,
			String strCsSign) throws Exception
	{
		String strBrid = buildAllPrimaryString(listOPTColumns);
		
		OptcorporationCondition objOPTCConditon = new OptcorporationCondition();
		objOPTCConditon.setCocode(strCocode);
		objOPTCConditon.setOptccssign(strCsSign);
		objOPTCConditon.setBrid(strBrid);	
		objOPTCConditon.setUseCacheSign(true);
		List<OptcorporationColumns> listoptCustomer = OperationpromptDemand.queryCorporation(objOPTCConditon);
		
		for(int i=0; i<listOPTColumns.size(); i++)
		{
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);			
			//通用有效或客户为空
			if(strCsSign.equals("Y"))
			{
				if(listOPTColumns.get(i).getOptoptuniversalcustomersign().equals("Y") || StringUtility.isNull(strCocode))
					continue;
			}
			//通用有效或服务商为空
			else if(strCsSign.equals("N"))
			{
				if(listOPTColumns.get(i).getOptoptuniversalservesign().equals("Y") || StringUtility.isNull(strCocode))
					continue;
			}
			boolean isExists = isExistsCustomer(listoptCustomer, objOPTColumnsEX.getOptbrid());
			//规则里不存在客户或服务商
			if(!isExists && 
					strCsSign.equals("Y") && 
					listOPTColumns.get(i).getOptoptuniversalcustomersign().equals("P"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
			if(!isExists && 
					strCsSign.equals("N") && 
					listOPTColumns.get(i).getOptoptuniversalservesign().equals("P"))
				listCopyOPTColumns.remove(objOPTColumnsEX);			
			
			//不符合的客户
			if(isExists && 
					strCsSign.equals("Y") && 
					listOPTColumns.get(i).getOptoptuniversalcustomersign().equals("N"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
			//不符合的服务商
			if(isExists && 
					strCsSign.equals("N") && 
					listOPTColumns.get(i).getOptoptuniversalservesign().equals("N"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
		}
		return listCopyOPTColumns;
	}
	
	private boolean isExistsCustomer(List<OptcorporationColumns> listoptCustomer,
			String strBrid) {
		if (listoptCustomer == null || listoptCustomer.size() < 1)
			return false;
		for(int i = 0; i < listoptCustomer.size(); i++) {
			if (listoptCustomer.get(i).getOptcocomp_idbrid().equals(strBrid))
				return true;
		}
		return false;
	}	
	
	/**
	 * 有效渠道
	 * @param listOPTColumns
	 * @param strChncode
	 * @return
	 * @throws Exception
	 */
	public List<OperationpromptColumns> verifyChannel(List<OperationpromptColumns> listOPTColumns,
			List<OperationpromptColumns> listCopyOPTColumns,
			String strChncode) throws Exception
	{
		String strBrid = buildAllPrimaryString(listOPTColumns);
		
		OptchannelCondition objCHNcondition = new OptchannelCondition();
		objCHNcondition.setChncode(strChncode);
		objCHNcondition.setOptncssign("N");//服务渠道
		objCHNcondition.setUseCacheSign(true);
		objCHNcondition.setBrid(strBrid);
		List<OptchannelColumns> listChncolumns = OperationpromptDemand.queryChannel(objCHNcondition);		
		
		for(int i = 0; i < listOPTColumns.size(); i++)
		{
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);
			//通用有效或渠道为空
			if(listOPTColumns.get(i).getOptoptuniversalschannelsign().equals("Y") || StringUtility.isNull(strChncode))
				continue;
			boolean isExists = isExistsChannel(listChncolumns, objOPTColumnsEX.getOptbrid());
			if(!isExists && 
					objOPTColumnsEX.getOptoptuniversalschannelsign().equals("P"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
			
			if(!isExists && 
					listOPTColumns.get(i).getOptoptuniversalschannelsign().equals("N"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
		}
		return listCopyOPTColumns;
	}
	
	private boolean isExistsChannel(List<OptchannelColumns> listChncolumns,
			String strBrid) {
		if (listChncolumns == null || listChncolumns.size() < 1)
			return false;
		for(int i = 0; i < listChncolumns.size(); i++) {
			if (listChncolumns.get(i).getOptchncomp_idbrid().equals(strBrid))
				return true;
		}
		return false;
	}	
	
	/**
	 * 有效起运地
	 * @param listOPTColumns
	 * @param strOdtCode
	 * @return
	 * @throws Exception
	 */
	public List<OperationpromptColumns> verifyOdt(List<OperationpromptColumns> listOPTColumns,
			List<OperationpromptColumns> listCopyOPTColumns,
			String strOdtCode) throws Exception
	{
		String strBrid = buildAllPrimaryString(listOPTColumns);
		
		OptorigindistrictCondition objODTCondition = new OptorigindistrictCondition();
		objODTCondition.setDtcode(strOdtCode);
		objODTCondition.setBrid(strBrid);
		objODTCondition.setUseCacheSign(true);
		List<OptorigindistrictColumns> listODTColumns = OperationpromptDemand.queryOptOdt(objODTCondition);
		
		for(int i=0; i<listOPTColumns.size(); i++)
		{
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);
			if(listOPTColumns.get(i).getOptoptuniversaldeparturesign().equals("Y") || StringUtility.isNull(strOdtCode))
				continue;
			boolean isExists = isExistsOrigin(listODTColumns, objOPTColumnsEX.getOptbrid());
			if(!isExists && listOPTColumns.get(i).getOptoptuniversaldeparturesign().equals("P"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
			else if(isExists && listOPTColumns.get(i).getOptoptuniversaldeparturesign().equals("N"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
		}
		return listCopyOPTColumns;
	}
	
	private boolean isExistsOrigin(List<OptorigindistrictColumns> listODTColumns,
			String strBrid) {
		if (listODTColumns == null || listODTColumns.size() < 1)
			return false;
		for(int i = 0; i < listODTColumns.size(); i++) {
			if (listODTColumns.get(i).getOptodtcomp_idbrid().equals(strBrid))
				return true;
		}
		return false;
	}	
	
	
	/**
	 * 有效目的地
	 * @param listOPTColumns
	 * @param strDdtCode
	 * @return
	 * @throws Exception
	 */
	public List<OperationpromptColumns> verifyDdt(List<OperationpromptColumns> listOPTColumns,
			List<OperationpromptColumns> listCopyOPTColumns,
			String strDdtCode) throws Exception
	{
		String strBrid = buildAllPrimaryString(listOPTColumns);
		String strCountryDtcode = DistrictDemand.getCountrycodeByCity(strDdtCode);
		
		OptdeparturedistrictCondition objDDTCondition = new OptdeparturedistrictCondition();
		objDDTCondition.setDtcode(strDdtCode);
		objDDTCondition.setCountrydtcode(strCountryDtcode);
		objDDTCondition.setBrid(strBrid);
		objDDTCondition.setUseCacheSign(true);
		List<OptdeparturedistrictColumns> listDDTColumns = OperationpromptDemand.queryOptDdt(objDDTCondition);
		
		for(int i=0; i<listOPTColumns.size(); i++)
		{
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);
			
			if(listOPTColumns.get(i).getOptoptuniversaldestinationsign().equals("Y") || StringUtility.isNull(strDdtCode))
				continue;
			boolean isExists = isExistsDestination(listDDTColumns, listOPTColumns.get(i).getOptbrid());
			if(!isExists && listOPTColumns.get(i).getOptoptuniversaldestinationsign().equals("P"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
			else if(isExists && listOPTColumns.get(i).getOptoptuniversaldestinationsign().equals("N"))
				listCopyOPTColumns.remove(objOPTColumnsEX);
		}
		return listCopyOPTColumns;
	}
	
	private boolean isExistsDestination(List<OptdeparturedistrictColumns> listDDTColumns,
			String strBrid) {
		if (listDDTColumns == null || listDDTColumns.size() < 1)
			return false;
		for(int i = 0; i < listDDTColumns.size(); i++) {
			if (listDDTColumns.get(i).getOptddtcomp_idbrid().equals(strBrid))
				return true;
		}
		return false;
	}	
	
	/**
	 * 特殊类型
	 * @param listOPTColumns
	 * @param listSpecialtypeColumns
	 * @return
	 * @throws Exception
	 */
	public List<OperationpromptColumns> verifyEst(List<OperationpromptColumns> listOPTColumns,
			List<OperationpromptColumns> listCopyOPTColumns,
			List<SpecialtypeColumns> listSpecialtypeColumns) throws Exception
	{
		for(int i=0;i<listOPTColumns.size();i++)
		{
			//加载每个规则对应的特殊类型
			OperationpromptColumnsEX objOPTColumnsEX = (OperationpromptColumnsEX)listOPTColumns.get(i);
			
			OptexpressspecialtypeCondition objESTCondition = new OptexpressspecialtypeCondition();
			objESTCondition.setBrid(listOPTColumns.get(i).getOptbrid());
			List<OptexpressspecialtypeColumns> listESTColumns = OperationpromptDemand.queryEst(objESTCondition);
			if(CollectionUtility.isNull(listESTColumns)) continue;
			if(CollectionUtility.isNull(listSpecialtypeColumns))
			{
				listCopyOPTColumns.remove(objOPTColumnsEX);
				continue;
			}
			boolean isExistEst = false;
			for(OptexpressspecialtypeColumns objESTColumns:listESTColumns)
			{
				//只要有一件特殊类型包含在规则特殊类型里就认为此票件存在特殊类型
				for(SpecialtypeColumns objSpecialtypeColumns:listSpecialtypeColumns)
				{
					if(objESTColumns.getEstestcode().equals(objSpecialtypeColumns.getWbstcomp_idestcode()))
					{
						isExistEst = true;
						break; 
					}
				}
				if(isExistEst)
					break;
			}
			//移除不满足条件的规则特殊类型
			if(!isExistEst)
				listCopyOPTColumns.remove(objOPTColumnsEX);
		}
		return listCopyOPTColumns;
	}
	
	public OperationpromptCondition buildOPTCondition(HousewaybillColumns objHwColumns) throws Exception
	{
		String strValiddate = DateFormatUtility.getStandardSysdate().substring(0, 10);
		strValiddate = strValiddate + " 00:00:00";
		
		OperationpromptCondition objOPTCondtion = new OperationpromptCondition();
		objOPTCondtion.setChargeweight(objHwColumns.getCwcwchargeweight());//
		objOPTCondtion.setChargeweight2(objHwColumns.getCwcwchargeweight());//
		objOPTCondtion.setCtcode2("A,"+objHwColumns.getCtctcode());
		objOPTCondtion.setPkcode2("A,"+objHwColumns.getPkpkcode());
		objOPTCondtion.setPmcode2("A,"+objHwColumns.getPmpmcode());
		objOPTCondtion.setSscode("ON");
		objOPTCondtion.setUseCacheSign(true);
		objOPTCondtion.setValiddate1(strValiddate);
		objOPTCondtion.setValiddate2(strValiddate);
		// 申报价值
		String strCargovalue = CargoInfoDemand.sumCargovalue(objHwColumns.getHwcwcode());
		if(!StringUtility.isNull(strCargovalue))
		{
			objOPTCondtion.setDeclarevalue(strCargovalue);
			objOPTCondtion.setDeclarevalue2(strCargovalue);
		}
		return objOPTCondtion;
	}
	
	private String buildAllPrimaryString(List<OperationpromptColumns> listOPTColumns) {
		if (listOPTColumns == null || listOPTColumns.size() < 1)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < listOPTColumns.size(); i++) {
			sb.append(listOPTColumns.get(i).getOptbrid());
			if (i < listOPTColumns.size() - 1)
				sb.append(",");
		}
		return sb.toString();
	}
	
	
	@Override
	protected AModifyBusinessStatusTrans getModifyBusinessStatusTrans() {
		return new ModifyOPTStatusTransaction();
	}
}
