package kyle.leis.eo.operation.housewaybill.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kyle.common.util.jlang.ObjectGenerator;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.LabeldataCondition;
import kyle.leis.eo.operation.housewaybill.da.LabeldataQuery;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TemplatecolumnColumns;
import kyle.leis.fs.dictionary.dictionarys.dax.DictionaryDemand;
import kyle.leis.fs.dictionary.district.da.AlldhldistrictColumns;
import kyle.leis.fs.dictionary.district.da.CountrypostcodecountColumns;
import kyle.leis.fs.dictionary.district.da.CountrypostcodecountQuery;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;

public class PredictOrderCheck {
	
	private PredictOrderMap m_objPredictOrderMap;
	private List<PredictOrderColumnsEX> m_listFilterRepeatWaybill;
	
	public void setPredictOrderMap(PredictOrderMap objPredictOrderMap) {
		m_objPredictOrderMap = objPredictOrderMap;
	}
	
	public List<PredictOrderColumnsEX> getFilterRepeatwaybill() {
		return m_listFilterRepeatWaybill;
	}
	
	/**
	 * 从模板数据中检查客户运单号是否重复
	 * @param strCocode
	 * @param listWaybill
	 * @param isAllowMerge
	 * @return
	 * @throws Exception
	 */
	public PredictOrderMap checkRepeatCustomerEWB(String strCocode,
			List<PredictOrderColumnsEX> listWaybill,
			boolean isAllowMerge,
			String strRepeatType) throws Exception {
		//PredictOrderMap objPredictOrderMap = new PredictOrderMap();
		if (m_objPredictOrderMap == null)
			m_objPredictOrderMap = new PredictOrderMap();
		
		Map<String, PredictOrderColumnsEX> hmPredict = new HashMap<String, PredictOrderColumnsEX>();
		List<String> listRepeatKey = new ArrayList<String>();
		
		if (listWaybill == null || listWaybill.size() < 1)
			return m_objPredictOrderMap;
		for (PredictOrderColumnsEX objPOCEX : listWaybill) {
			// 默认判断产品+运单号
			String strKey = objPOCEX.getWaybillforpredict().getPkpk_code() + objPOCEX.getWaybillforpredict().getCwcw_customerewbcode();
			// 收件人名+地址重复
			if (strRepeatType.equals("CN")) {
				strKey = StringUtility.replaceWhenNull(objPOCEX.getWaybillforpredict().getHwhw_consigneename(), "") +
				StringUtility.replaceWhenNull(objPOCEX.getWaybillforpredict().getPkpk_code(), "") +
				StringUtility.replaceWhenNull(objPOCEX.getWaybillforpredict().getHwhw_consigneeaddress1(), "") + 
				StringUtility.replaceWhenNull(objPOCEX.getWaybillforpredict().getHwhw_consigneeaddress2(), "") + 
				StringUtility.replaceWhenNull(objPOCEX.getWaybillforpredict().getHwhw_consigneeaddress3(), "");
			}
			BigDecimal objSource = new BigDecimal(StringUtility.replaceWhenNull(objPOCEX.getWaybillforpredict().getCwcw_customerchargeweight(), 
					"0"));
			String strOriginCEWBCode = objPOCEX.getWaybillforpredict().getCwcw_customerewbcode();
			if (StringUtility.isNull(strKey))
				strKey = "不能为空";
			if (hmPredict.containsKey(strKey)) {
				// 不允许合并则直接提示
				if (!isAllowMerge) {
					m_objPredictOrderMap.putPrompt("订单重复", objPOCEX);
					listRepeatKey.add(strKey);
				} else {
					// 合并
					PredictOrderDemand.merge(objPOCEX, hmPredict.get(strKey));
					PredictOrderColumnsEX objDestPOCEX = hmPredict.get(strKey);
					BigDecimal objDest = new BigDecimal(StringUtility.replaceWhenNull(objDestPOCEX.getWaybillforpredict().getCwcw_customerchargeweight(),
							"0"));
					String strCustomerEwbcode = objDestPOCEX.getWaybillforpredict().getCwcw_customerewbcode();
					if (!strCustomerEwbcode.equals(strOriginCEWBCode)) {
						objDestPOCEX.getWaybillforpredict().setCwcw_customerewbcode(strCustomerEwbcode + "," + strOriginCEWBCode);
					}
					// 合并重量
					objDestPOCEX.getWaybillforpredict().setCwcw_customerchargeweight(objSource.add(objDest).toString());
					m_objPredictOrderMap.putRepeatedWaybill(objPOCEX);
				}
			} else {
				hmPredict.put(strKey, objPOCEX);
			}
		}
		/********************add by zzj*******start******************************************/
		for (String str : hmPredict.keySet()) {
			if (listRepeatKey.contains(str)) {
				m_objPredictOrderMap.putPrompt("订单重复", hmPredict.get(str));
			}
		}
		/********************add by zzj*********end****************************************/
		List<PredictOrderColumnsEX> listResults = new ArrayList<PredictOrderColumnsEX>();
		for (String str : hmPredict.keySet()) {
			PredictOrderColumnsEX obj = hmPredict.get(str);
			boolean isAdd = true;
			if (!isAllowMerge) {
				for (String strRepeat : listRepeatKey) {
					if (str.equals(strRepeat)) {
						obj.setOpermode("");
						isAdd = false;
						break;
					}
				}
			}
			if (isAdd)
				listResults.add(obj);
		}
		m_listFilterRepeatWaybill = listResults;
		return m_objPredictOrderMap;
	}	
	
	public PredictOrderMap check(String strCocode,
			List<PredictOrderColumnsEX> listWaybill) throws Exception {
		return check(strCocode, listWaybill, false);
	}
	
	
	public PredictOrderMap check(String strCocode,
			List<PredictOrderColumnsEX> listWaybill, 
			boolean apiCall) throws Exception {
		List listStandardTemplate = null;
		
		//System.out.println("xxx" + SystempropertyDemand.getEnterprise() + "yyy" + apiCall);
		
		if (SystempropertyDemand.getEnterprise().startsWith("SLYIM") &&
				apiCall) {
			listStandardTemplate = DictionaryDemand.queryStandardTemplate(true);
		} else {
			listStandardTemplate = DictionaryDemand.queryStandardTemplate();
		}
		if (m_objPredictOrderMap == null)
			m_objPredictOrderMap = new PredictOrderMap();
		
		if (listStandardTemplate == null || listStandardTemplate.size() < 1) {
			m_objPredictOrderMap.putPrompt("标准模板还没有设置", listWaybill);
			return m_objPredictOrderMap;
		}
		for (int i = 0; i < listWaybill.size(); i++) {
			PredictOrderColumnsEX objPOCEX = listWaybill.get(i);

			String strCheckResult = check(strCocode, objPOCEX, 
					listStandardTemplate, false);
			if (!StringUtility.isNull(strCheckResult)) {
				m_objPredictOrderMap.putPrompt(strCheckResult, objPOCEX);
				continue;				
			}
			m_objPredictOrderMap.putNormal(objPOCEX);
		}
		return m_objPredictOrderMap;
	}
	
	/**
	 * 效验
	 * @param strCocode
	 * @param objPOCEX
	 * @param listStandardTemplate
	 * @return
	 * @throws Exception
	 */
	public String check(String strCocode,
			PredictOrderColumnsEX objPOCEX,
			List listStandardTemplate,
			boolean isOnlyCheckBase) throws Exception {
		if (!checkBases(objPOCEX, listStandardTemplate)) {
			return "无法通过基本效验";
		}
		String stPkcode = objPOCEX.getWaybillforpredict().getPkpk_code();
		if (StringUtility.isNull(stPkcode)) {
			return "无法通过基本效验，走货渠道为空或无法映射";
		} else {
			// 效验产品
			List<ProductkindColumns> listProductKind = ProductkindDemand.getCanUseProduct(strCocode,
					"719",
					"1");
			boolean isContainPk = false;
			for (ProductkindColumns pkc : listProductKind) {
				// 批量上传跟订单保存传的pkcode不一致，批量上传的时候用的是简称，订单保存直接用的是pkcode
				if (stPkcode.equals(pkc.getPkpkename()) || 
						stPkcode.equals(pkc.getPkpkcode())) {
					isContainPk = true;
					break;
				}
			}
			if (!isContainPk)
				return "无法通过基本效验，走货渠道错误，我司未开通此走货渠道";
		}
		// 校验重量
		String weight = objPOCEX.getWaybillforpredict().getCwcw_customerchargeweight();
		if (StringUtility.isNull(weight) || new BigDecimal(weight).compareTo(BigDecimal.ZERO) <= 0) {
			return "重量必须大于零";
		}
		// 效验客户的运单号码（订单号）
		String strCustomerEwbcode = objPOCEX.getWaybillforpredict().getCwcw_customerewbcode();
		if (StringUtility.isNull(strCustomerEwbcode))
			return "客户运单号或订单号不能为空";
		if (!isOnlyCheckBase)
			return checkRepeatCustomerEWB(strCocode, objPOCEX);
		else
			return "";
		
	}
	
	/**
	 * 从数据库中检查客户订单号是否重复
	 * @param strCocode
	 * @param objPOCEX
	 * @return
	 * @throws Exception
	 */
	private String checkRepeatCustomerEWB(String strCocode,
			PredictOrderColumnsEX objPOCEX) throws Exception {
		
		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		
		PromptUtility objPromptUtility = checkRepeatCustomerEWB(strCocode,
				objWFPC.getCwcw_customerewbcode(),
				objWFPC.getHwhw_consigneename(),
				objWFPC.getHwhw_consigneecompany());
		if (objPromptUtility == null)
			return "";
		objPOCEX.setOpermode(objPromptUtility.getPromptCode());
		return objPromptUtility.getDescribtion();
	}
	
	public PromptUtility checkRepeatCustomerEWB(String strCocode,
			String strCustomerewbcode,
			String strConsigneename,
			String strConsigneecompany) throws Exception {
		PromptUtility objPromptUtility = null;
		SimplecorewaybillColumns objSCWC = CorewaybillDemand.loadSimpleCorewaybill(strCustomerewbcode, 
				strCocode, true);
		// 如果不存在相同的订单号则判断收件人是否一致
		if (objSCWC == null) {
			// 要改
			/*
			WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
			objWFPCondition.setHwconsigneename(strConsigneename);
			objWFPCondition.setHwconsigneecompany(strConsigneecompany);
			objWFPCondition.setCocodecustomer(strCocode);
			objWFPCondition.setIncwscode("CTS");
			List listResults = HousewaybillDemand.queryForPredict(objWFPCondition);
			if (listResults != null && listResults.size() > 0) {
				objPromptUtility = new PromptUtility("SAVE|MERGE",
						"系统已经存在相同的收件人和收件人公司且状态为暂存状态",
						"");
				return objPromptUtility;
			}
			*/
			return objPromptUtility;
		}
		// 客户已打印标签
		if (objSCWC.getCwcws_code().equals("CHP")) {
			objPromptUtility = new PromptUtility("OVVRIDE|HOLD",
					"系统已经存在相同的订单号并且已经打印了Label",
					"");
			return objPromptUtility;			
		}
		if (objSCWC.getCwcws_code().equals("CHD")) {
			objPromptUtility = new PromptUtility("OVVRIDE|MERGE",
					"系统已经存在相同的订单号并且已经申报",
					"");
			return objPromptUtility;				
		}		
		if (objSCWC.getCwcws_code().equals("SI") ||
				objSCWC.getCwcws_code().equals("IP") ||
				objSCWC.getCwcws_code().equals("SO")) {
			
			objPromptUtility = new PromptUtility("",
					"系统已经存在相同的订单号并且我司已确认收货，系统不会将这部分订单重新上传",
					"");
			return objPromptUtility;				
		}
		if (objSCWC.getCwcws_code().equals("CTS")) {			
			String strOpermode = "OVVRIDE|MERGE";
			// 要改
			WaybillforpredictColumns objExistsWFPC = HousewaybillDemand.loadForPredict(objSCWC.getCwcw_code());
			if (objExistsWFPC != null && 
					objExistsWFPC.getHwhw_consigneename() != null &&
					objExistsWFPC.getHwhw_consigneename().equals(strConsigneename) &&
					objExistsWFPC.getHwhw_consigneecompany() != null &&
					objExistsWFPC.getHwhw_consigneecompany().equals(strConsigneecompany)) {

				
				objPromptUtility = new PromptUtility(strOpermode,
						"系统已经存在相同的订单号而且收件人名和收件人公司也重复，系统中的订单为制单暂存状态",
						"");
				return objPromptUtility;					
			}
			else {
				objPromptUtility = new PromptUtility(strOpermode,
						"系统已经存在相同的订单号并且为制单暂存状态",
						"");
				return objPromptUtility;					
			}
		}
		return objPromptUtility;
	}	
	
	
	
	private boolean checkBases(PredictOrderColumnsEX objPOEX, 
			List listStandardTemplate) throws Exception {
		WaybillforpredictColumns objWFPC = objPOEX.getWaybillforpredict();
		List<CargoinfoColumns> listCargoinfoColumns = objPOEX.getListCargoInfo();
		if (listCargoinfoColumns == null || listCargoinfoColumns.size() < 1) {
			objPOEX.setPromptinfo("必须要有货物信息");
			return false;
		}
		// 效验是否为空和长度
		boolean isPassCheck = true;
		for (int i = 0; i < listStandardTemplate.size(); i++) {
			TemplatecolumnColumns objTC = (TemplatecolumnColumns)listStandardTemplate.get(i);
			String strColumnEname = objTC.getTctccolumnename();
			String strValue = "";
			if (objTC.getTctccolumntype().equals("W")) {
				strValue = ObjectGenerator.process("get" + strColumnEname, 
						objWFPC, 
						null);
				if (!checkBases(objTC, strValue, objPOEX))
					isPassCheck = false;				
			} else if (objTC.getTctccolumntype().equals("C") &&
					objTC.getTctccolumngroup().equals("1")) {
				for (int j = 0; j < listCargoinfoColumns.size(); j++) {
					CargoinfoColumns objCargoinfoColumns = listCargoinfoColumns.get(j);
					strValue = ObjectGenerator.process("get" + strColumnEname, 
							objCargoinfoColumns, 
							null);
					if (!checkBases(objTC, strValue, objPOEX))
						isPassCheck = false;					
				}
			}

		}
		return isPassCheck;
	}
	
	private boolean checkBases(TemplatecolumnColumns objTC,
			String strActualValue,
			PredictOrderColumnsEX objPOEX) {
		if (objTC.getTctcallownullsign().equals("N") &&
				StringUtility.isNull(strActualValue)) {
			
			String strPromptInfo = objTC.getTctccolumnname() + "不能为空";
			if (objTC.getTctccolumnename().equals("Cwdt_code_signin") ||
					objTC.getTctccolumnename().equals("Pkpk_code"))
				strPromptInfo = strPromptInfo + "或者无法映射";
			
			objPOEX.setPromptinfo(strPromptInfo);
			return false;
		}
		if ("Cwcw_customerewbcode".equals(objTC.getTctccolumnename()) &&
				strActualValue.indexOf(",") > 0) {
			String[] astr = strActualValue.split(",");
			for (int i = 0; i < astr.length; i++) {
				if (!checkLength(astr[0],
						objTC,
						objPOEX))
					return false;
			}
		} else {
			if (!checkLength(strActualValue,
				objTC,
				objPOEX))
				return false;		
		}
		return true;
	}
	
	private boolean checkLength(String strActualValue,
			TemplatecolumnColumns objTC,
			PredictOrderColumnsEX objPOEX) {
		if (!StringUtility.isNull(strActualValue)) {
			int iMaxLength = Integer.parseInt(objTC.getTctcmaxlength());
			int iMinLength = Integer.parseInt(objTC.getTctcminlength());
			if (strActualValue.length() > iMaxLength) {
				objPOEX.setPromptinfo(objTC.getTctccolumnname() + "最大长度不能大于" + iMaxLength);
				return false;
			}
			if (strActualValue.length() < iMinLength) {
				objPOEX.setPromptinfo(objTC.getTctccolumnname() + "最小长度不能小于" + iMinLength);
				return false;
			}				
		}
		return true;		
	}
	
	/**
	 * 验证邮编和城市
	 * @param objFIAColumns
	 * @return
	 * @throws Exception
	 */
	public String checkPostAndCity(ForinputallColumns objFIAColumns,
			List listCargo) throws Exception{
		ChannelColumns channelColumns = ChannelDemand.load(objFIAColumns.getChncode_Cwspchn(), true);
		if (!StringUtility.isNull(objFIAColumns.getHwconsigneepostcode()))
			objFIAColumns.setHwconsigneepostcode(objFIAColumns.getHwconsigneepostcode().toUpperCase().replace("-", ""));
		if (!StringUtility.isNull(channelColumns.getSsgssgcode()) && 
				channelColumns.getSsgssgcode().startsWith("DHL") &&
				!channelColumns.getSsgssgcode().equals("DHL-USGlobeMail")) {
			String strNationalHubcode = DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode());
			/** 判断一下t_di_dhldistinct表，
			 * 如果这个表对应国家没有邮编的话，
			 * 不管客户有没有上传邮编，直接将邮编保存为空。 **/
			if (!postCodeExists(strNationalHubcode)) {
				objFIAColumns.setHwconsigneepostcode("");
			}
			//国家邮编
			if (StringUtility.isNull(objFIAColumns.getDtcode()))
				return "";
			if (!StringUtility.isNull(objFIAColumns.getHwconsigneepostcode())) {
				String strDthubcode = DistrictDemand.getDHLHubcode(strNationalHubcode, 
						objFIAColumns.getHwconsigneepostcode());
				if (StringUtility.isNull(strDthubcode) || strDthubcode.equals(strNationalHubcode)) {
					return "国家[" + strNationalHubcode 
						+ "]的邮编[" + objFIAColumns.getHwconsigneepostcode() + "]不正确！";
				}
				//城市邮编
				String str = DistrictDemand.getDHLLocationCode(objFIAColumns.getHwConsigneecity(),
						strDthubcode, 
						strNationalHubcode, 
						objFIAColumns.getHwconsigneepostcode());
				if (StringUtility.isNull(str)) {
					return "城市[" + objFIAColumns.getHwConsigneecity() 
						+ "]的邮编[" + objFIAColumns.getHwconsigneepostcode() +  "]不正确！";
				}
			} else {
				AlldhldistrictColumns objADDColumns = DistrictDemand.getAlldhldistrict(objFIAColumns.getHwConsigneecity(),
						"", 
						strNationalHubcode, 
						"");
				if (objADDColumns == null) {
					return "城市[" + objFIAColumns.getHwConsigneecity() 
						+ "]的国家[" + strNationalHubcode +  "]不正确！";
				}				
			}
		}
		// 效验DGM
		// 1：邮编不能超过11个字符
		// 2：申报不能为0
		// 3：美国邮编是5-9位
		// 4：品名不能少于3个字符，不能超过50个字符
		// 5：地址3不能超过30个字符
		if ("DHL-USGlobeMail".equals(channelColumns.getSsgssgcode()) || 
				"DGM-PY".equals(channelColumns.getSsgssgcode())) {
			if (StringUtility.isNull(objFIAColumns.getHwconsigneepostcode()) ||
					objFIAColumns.getHwconsigneepostcode().length() > 11) {
				return "邮编[" + objFIAColumns.getHwconsigneepostcode() +  "]不正确，不能为空或超过11个字符";
			}
			// 美国
			String strNationalHubcode = DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode());
			if ("US".equals(strNationalHubcode) &&
					(objFIAColumns.getHwconsigneepostcode().length() < 5  ||
							objFIAColumns.getHwconsigneepostcode().length() > 9)) {
				return "美国邮编[" + objFIAColumns.getHwconsigneepostcode() +  "]不正确，必须为5-9位";
			}
			// 品名
			if (listCargo != null && listCargo.size() > 0) {
				for (int i = 0; i < listCargo.size(); i++) {
					CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargo.get(i);
					if (StringUtility.isNull(objCargoinfoColumns.getCicitotalprice()) ||
							new BigDecimal(objCargoinfoColumns.getCicitotalprice()).compareTo(BigDecimal.ZERO) <= 0) {
						return "申报价值[" + objCargoinfoColumns.getCicitotalprice() +  "]不能为0";
					}
					if (StringUtility.isNull(objCargoinfoColumns.getCiciename()) &&
							(objCargoinfoColumns.getCiciename().length() < 3  ||
									objCargoinfoColumns.getCiciename().length() > 50)) {
						return "申报品名[" + objCargoinfoColumns.getCiciename() +  "]不能少于3个字符，不能超过50个字符";
					}
				}
			}
		}
		// 另外一种DGM标签格式
		if ("C_SDHLGM".equals(channelColumns.getLflfcode())) {
			LabeldataCondition ldc = new LabeldataCondition();
			if (Double.parseDouble(objFIAColumns.getCwchargeweight()) <= 0.17007165) {
				ldc.setProducts("82");
				ldc.setMtype("2");
			} else if (Double.parseDouble(objFIAColumns.getCwchargeweight()) > 0.1701
					&& Double.parseDouble(objFIAColumns.getCwchargeweight()) <= 0.45357165) {
				ldc.setProducts("82");
				ldc.setMtype("3");
			} else if (Double.parseDouble(objFIAColumns.getCwchargeweight()) > 0.4536) {
				ldc.setProducts("83");
				ldc.setMtype("7");
			}
			ldc.setCode(objFIAColumns.getHwconsigneepostcode());
			LabeldataQuery ldq = new LabeldataQuery();
			ldq.setCondition(ldc);
			List list = ldq.getResults();
			if (list == null || list.size() < 1) {
				return "DGM重量为[" + objFIAColumns.getCwchargeweight() 
				+ "]的邮编[" + objFIAColumns.getHwconsigneepostcode() +  "]没有服务！";				
			}
			
		}
		
		
		return "";
	}
	
	/**
	 * 国家是否存在邮编
	 * @param nationCode
	 * @return
	 * @throws Exception
	 */
	private boolean postCodeExists(String nationCode) throws Exception {
		CountrypostcodecountQuery cpq = new CountrypostcodecountQuery();
		cpq.setDd_nationcode(nationCode);
		CountrypostcodecountColumns columns = (CountrypostcodecountColumns) cpq.getResults().get(0);
		return Long.valueOf(columns.getC()) > 0;
	}
	
}
