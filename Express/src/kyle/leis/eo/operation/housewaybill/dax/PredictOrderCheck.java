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
	 * ��ģ�������м��ͻ��˵����Ƿ��ظ�
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
			// Ĭ���жϲ�Ʒ+�˵���
			String strKey = objPOCEX.getWaybillforpredict().getPkpk_code() + objPOCEX.getWaybillforpredict().getCwcw_customerewbcode();
			// �ռ�����+��ַ�ظ�
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
				strKey = "����Ϊ��";
			if (hmPredict.containsKey(strKey)) {
				// ������ϲ���ֱ����ʾ
				if (!isAllowMerge) {
					m_objPredictOrderMap.putPrompt("�����ظ�", objPOCEX);
					listRepeatKey.add(strKey);
				} else {
					// �ϲ�
					PredictOrderDemand.merge(objPOCEX, hmPredict.get(strKey));
					PredictOrderColumnsEX objDestPOCEX = hmPredict.get(strKey);
					BigDecimal objDest = new BigDecimal(StringUtility.replaceWhenNull(objDestPOCEX.getWaybillforpredict().getCwcw_customerchargeweight(),
							"0"));
					String strCustomerEwbcode = objDestPOCEX.getWaybillforpredict().getCwcw_customerewbcode();
					if (!strCustomerEwbcode.equals(strOriginCEWBCode)) {
						objDestPOCEX.getWaybillforpredict().setCwcw_customerewbcode(strCustomerEwbcode + "," + strOriginCEWBCode);
					}
					// �ϲ�����
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
				m_objPredictOrderMap.putPrompt("�����ظ�", hmPredict.get(str));
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
			m_objPredictOrderMap.putPrompt("��׼ģ�廹û������", listWaybill);
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
	 * Ч��
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
			return "�޷�ͨ������Ч��";
		}
		String stPkcode = objPOCEX.getWaybillforpredict().getPkpk_code();
		if (StringUtility.isNull(stPkcode)) {
			return "�޷�ͨ������Ч�飬�߻�����Ϊ�ջ��޷�ӳ��";
		} else {
			// Ч���Ʒ
			List<ProductkindColumns> listProductKind = ProductkindDemand.getCanUseProduct(strCocode,
					"719",
					"1");
			boolean isContainPk = false;
			for (ProductkindColumns pkc : listProductKind) {
				// �����ϴ����������洫��pkcode��һ�£������ϴ���ʱ���õ��Ǽ�ƣ���������ֱ���õ���pkcode
				if (stPkcode.equals(pkc.getPkpkename()) || 
						stPkcode.equals(pkc.getPkpkcode())) {
					isContainPk = true;
					break;
				}
			}
			if (!isContainPk)
				return "�޷�ͨ������Ч�飬�߻�����������˾δ��ͨ���߻�����";
		}
		// У������
		String weight = objPOCEX.getWaybillforpredict().getCwcw_customerchargeweight();
		if (StringUtility.isNull(weight) || new BigDecimal(weight).compareTo(BigDecimal.ZERO) <= 0) {
			return "�������������";
		}
		// Ч��ͻ����˵����루�����ţ�
		String strCustomerEwbcode = objPOCEX.getWaybillforpredict().getCwcw_customerewbcode();
		if (StringUtility.isNull(strCustomerEwbcode))
			return "�ͻ��˵��Ż򶩵��Ų���Ϊ��";
		if (!isOnlyCheckBase)
			return checkRepeatCustomerEWB(strCocode, objPOCEX);
		else
			return "";
		
	}
	
	/**
	 * �����ݿ��м��ͻ��������Ƿ��ظ�
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
		// �����������ͬ�Ķ��������ж��ռ����Ƿ�һ��
		if (objSCWC == null) {
			// Ҫ��
			/*
			WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
			objWFPCondition.setHwconsigneename(strConsigneename);
			objWFPCondition.setHwconsigneecompany(strConsigneecompany);
			objWFPCondition.setCocodecustomer(strCocode);
			objWFPCondition.setIncwscode("CTS");
			List listResults = HousewaybillDemand.queryForPredict(objWFPCondition);
			if (listResults != null && listResults.size() > 0) {
				objPromptUtility = new PromptUtility("SAVE|MERGE",
						"ϵͳ�Ѿ�������ͬ���ռ��˺��ռ��˹�˾��״̬Ϊ�ݴ�״̬",
						"");
				return objPromptUtility;
			}
			*/
			return objPromptUtility;
		}
		// �ͻ��Ѵ�ӡ��ǩ
		if (objSCWC.getCwcws_code().equals("CHP")) {
			objPromptUtility = new PromptUtility("OVVRIDE|HOLD",
					"ϵͳ�Ѿ�������ͬ�Ķ����Ų����Ѿ���ӡ��Label",
					"");
			return objPromptUtility;			
		}
		if (objSCWC.getCwcws_code().equals("CHD")) {
			objPromptUtility = new PromptUtility("OVVRIDE|MERGE",
					"ϵͳ�Ѿ�������ͬ�Ķ����Ų����Ѿ��걨",
					"");
			return objPromptUtility;				
		}		
		if (objSCWC.getCwcws_code().equals("SI") ||
				objSCWC.getCwcws_code().equals("IP") ||
				objSCWC.getCwcws_code().equals("SO")) {
			
			objPromptUtility = new PromptUtility("",
					"ϵͳ�Ѿ�������ͬ�Ķ����Ų�����˾��ȷ���ջ���ϵͳ���Ὣ�ⲿ�ֶ��������ϴ�",
					"");
			return objPromptUtility;				
		}
		if (objSCWC.getCwcws_code().equals("CTS")) {			
			String strOpermode = "OVVRIDE|MERGE";
			// Ҫ��
			WaybillforpredictColumns objExistsWFPC = HousewaybillDemand.loadForPredict(objSCWC.getCwcw_code());
			if (objExistsWFPC != null && 
					objExistsWFPC.getHwhw_consigneename() != null &&
					objExistsWFPC.getHwhw_consigneename().equals(strConsigneename) &&
					objExistsWFPC.getHwhw_consigneecompany() != null &&
					objExistsWFPC.getHwhw_consigneecompany().equals(strConsigneecompany)) {

				
				objPromptUtility = new PromptUtility(strOpermode,
						"ϵͳ�Ѿ�������ͬ�Ķ����Ŷ����ռ��������ռ��˹�˾Ҳ�ظ���ϵͳ�еĶ���Ϊ�Ƶ��ݴ�״̬",
						"");
				return objPromptUtility;					
			}
			else {
				objPromptUtility = new PromptUtility(strOpermode,
						"ϵͳ�Ѿ�������ͬ�Ķ����Ų���Ϊ�Ƶ��ݴ�״̬",
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
			objPOEX.setPromptinfo("����Ҫ�л�����Ϣ");
			return false;
		}
		// Ч���Ƿ�Ϊ�պͳ���
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
			
			String strPromptInfo = objTC.getTctccolumnname() + "����Ϊ��";
			if (objTC.getTctccolumnename().equals("Cwdt_code_signin") ||
					objTC.getTctccolumnename().equals("Pkpk_code"))
				strPromptInfo = strPromptInfo + "�����޷�ӳ��";
			
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
				objPOEX.setPromptinfo(objTC.getTctccolumnname() + "��󳤶Ȳ��ܴ���" + iMaxLength);
				return false;
			}
			if (strActualValue.length() < iMinLength) {
				objPOEX.setPromptinfo(objTC.getTctccolumnname() + "��С���Ȳ���С��" + iMinLength);
				return false;
			}				
		}
		return true;		
	}
	
	/**
	 * ��֤�ʱ�ͳ���
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
			/** �ж�һ��t_di_dhldistinct��
			 * ���������Ӧ����û���ʱ�Ļ���
			 * ���ܿͻ���û���ϴ��ʱֱ࣬�ӽ��ʱౣ��Ϊ�ա� **/
			if (!postCodeExists(strNationalHubcode)) {
				objFIAColumns.setHwconsigneepostcode("");
			}
			//�����ʱ�
			if (StringUtility.isNull(objFIAColumns.getDtcode()))
				return "";
			if (!StringUtility.isNull(objFIAColumns.getHwconsigneepostcode())) {
				String strDthubcode = DistrictDemand.getDHLHubcode(strNationalHubcode, 
						objFIAColumns.getHwconsigneepostcode());
				if (StringUtility.isNull(strDthubcode) || strDthubcode.equals(strNationalHubcode)) {
					return "����[" + strNationalHubcode 
						+ "]���ʱ�[" + objFIAColumns.getHwconsigneepostcode() + "]����ȷ��";
				}
				//�����ʱ�
				String str = DistrictDemand.getDHLLocationCode(objFIAColumns.getHwConsigneecity(),
						strDthubcode, 
						strNationalHubcode, 
						objFIAColumns.getHwconsigneepostcode());
				if (StringUtility.isNull(str)) {
					return "����[" + objFIAColumns.getHwConsigneecity() 
						+ "]���ʱ�[" + objFIAColumns.getHwconsigneepostcode() +  "]����ȷ��";
				}
			} else {
				AlldhldistrictColumns objADDColumns = DistrictDemand.getAlldhldistrict(objFIAColumns.getHwConsigneecity(),
						"", 
						strNationalHubcode, 
						"");
				if (objADDColumns == null) {
					return "����[" + objFIAColumns.getHwConsigneecity() 
						+ "]�Ĺ���[" + strNationalHubcode +  "]����ȷ��";
				}				
			}
		}
		// Ч��DGM
		// 1���ʱ಻�ܳ���11���ַ�
		// 2���걨����Ϊ0
		// 3�������ʱ���5-9λ
		// 4��Ʒ����������3���ַ������ܳ���50���ַ�
		// 5����ַ3���ܳ���30���ַ�
		if ("DHL-USGlobeMail".equals(channelColumns.getSsgssgcode()) || 
				"DGM-PY".equals(channelColumns.getSsgssgcode())) {
			if (StringUtility.isNull(objFIAColumns.getHwconsigneepostcode()) ||
					objFIAColumns.getHwconsigneepostcode().length() > 11) {
				return "�ʱ�[" + objFIAColumns.getHwconsigneepostcode() +  "]����ȷ������Ϊ�ջ򳬹�11���ַ�";
			}
			// ����
			String strNationalHubcode = DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode());
			if ("US".equals(strNationalHubcode) &&
					(objFIAColumns.getHwconsigneepostcode().length() < 5  ||
							objFIAColumns.getHwconsigneepostcode().length() > 9)) {
				return "�����ʱ�[" + objFIAColumns.getHwconsigneepostcode() +  "]����ȷ������Ϊ5-9λ";
			}
			// Ʒ��
			if (listCargo != null && listCargo.size() > 0) {
				for (int i = 0; i < listCargo.size(); i++) {
					CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargo.get(i);
					if (StringUtility.isNull(objCargoinfoColumns.getCicitotalprice()) ||
							new BigDecimal(objCargoinfoColumns.getCicitotalprice()).compareTo(BigDecimal.ZERO) <= 0) {
						return "�걨��ֵ[" + objCargoinfoColumns.getCicitotalprice() +  "]����Ϊ0";
					}
					if (StringUtility.isNull(objCargoinfoColumns.getCiciename()) &&
							(objCargoinfoColumns.getCiciename().length() < 3  ||
									objCargoinfoColumns.getCiciename().length() > 50)) {
						return "�걨Ʒ��[" + objCargoinfoColumns.getCiciename() +  "]��������3���ַ������ܳ���50���ַ�";
					}
				}
			}
		}
		// ����һ��DGM��ǩ��ʽ
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
				return "DGM����Ϊ[" + objFIAColumns.getCwchargeweight() 
				+ "]���ʱ�[" + objFIAColumns.getHwconsigneepostcode() +  "]û�з���";				
			}
			
		}
		
		
		return "";
	}
	
	/**
	 * �����Ƿ�����ʱ�
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
