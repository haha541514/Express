package kyle.leis.eo.operation.predictwaybill.mabang;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.predictwaybill.bl.Predictwaybill;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.da.UserCondition;
import kyle.leis.fs.authoritys.user.dax.UserDemand;
import kyle.leis.fs.cachecontainer.dax.ProductkindColumnsEX;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ApiAcquireMBDate {
	
	public String linkData(ApiLinkEntity al){
		String url = MabangUtil.getUrl(al);
		String data= ApiHttpResult.getHttpResult(url);
		System.out.println(data);
		return data;
	}

	public Map<String,Object> getSaveDataColumns(String data,String opId,String pkCode,String coCode,ApiLinkEntity al) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		List<PredictwaybillColumns> listPwbc = new ArrayList<PredictwaybillColumns>();
		List<List<PredictcargoinfoColumns>> listlsInfo = new ArrayList<List<PredictcargoinfoColumns>>();
		
		JSONObject result =  JSONObject.fromObject(data);
		JSONObject jsData =  JSONObject.fromObject(result.get("Data"));
		//�õ�����json
		JSONArray jary = JSONArray.fromObject(jsData.get("orders"));
		for (int i = 0; i < jary.size(); i++) {
			//���ö�����Ϣ
			PredictwaybillColumns predictwaybillColumns = new PredictwaybillColumns();
			
			//ÿһ��json����
			JSONObject orders =  jary.getJSONObject(i);
			//�ڲ�������
			String code =orders.getString("code"); 
			//��֤��Ʒ��ͬ�Ķ���
			JSONObject myExpressChannel =JSONObject.fromObject(orders.get("myExpressChannel")); 
			String customerCode = myExpressChannel.getString("customerCode");
			
			//��ѯ�˻���Ϣ
			UserCondition objUserCondition = new UserCondition();
			UserColumns objUrReturn =null;
			//��֤�û���ʶ�Ƿ���ͬ
			JSONObject customer =JSONObject.fromObject(orders.get("customer")); 
			JSONObject logisticsKeys =JSONObject.fromObject(customer.get("logisticsKeys"));
			JSONObject bqc =JSONObject.fromObject(logisticsKeys.get("bqc"));
			String api_key =bqc.getString("api_key"); 
			if(!StringUtility.isNull(opId)){
				objUserCondition.setOpid(opId);
				objUrReturn = (UserColumns) UserDemand.query(objUserCondition).get(0);
				//У�鶩��ʱ��Ҫ��ǰ�û����û���
				String userName = objUrReturn.getOpopcode();
				if(!pkCode.equals(customerCode)){
					continue;
				}
				if(!userName.equals(api_key)){
					continue;
				}
			}else{
				if(api_key==null||"".equals(api_key)){
					ERPEntity erp =new ERPEntity();
					erp.setCode(code);
					erp.setErrorMessage("�û�APIΪ��,����API_KEY�Ƿ���ȷ��");
					String exceptionParams = MabangUtil.getExceptionParams(erp);
					al.setApi("api.biaoju.order.update");
					al.setJsonParams(exceptionParams);
					linkData(al);
					continue;
				}
				objUserCondition.setOpcode(api_key);
				objUrReturn = (UserColumns) UserDemand.query(objUserCondition).get(0);
				//��֤�û�apikey�Ƿ����
				//�������򷵻ش���
				if (objUrReturn == null || 
	    				StringUtility.isNull(objUrReturn.getCococode())){
					ERPEntity erp =new ERPEntity();
					erp.setCode(code);
					erp.setErrorMessage("ϵͳ�����ڸ��û�API,����API_KEY�Ƿ���ȷ��");
					String exceptionParams = MabangUtil.getExceptionParams(erp);
					al.setApi("api.biaoju.order.update");
					al.setJsonParams(exceptionParams);
					linkData(al);
					continue;
				}else{
					opId = objUrReturn.getOpopid();
					coCode = objUrReturn.getCococode();
					List<ProductkindColumnsEX> listpkc = MabangUtil.getAllProduct(coCode);
					boolean isNull =true ;
					for(int j=0; j<listpkc.size();j++){
						if(customerCode.equals(listpkc.get(j).getPkcode())){
							isNull = false;
						}
					}
					if(isNull){
						ERPEntity erp =new ERPEntity();
						erp.setCode(code);
						erp.setErrorMessage("���û������ڸò�Ʒ,�����Ʒ�Ƿ���ȷ��");
						String exceptionParams = MabangUtil.getExceptionParams(erp);
						al.setApi("api.biaoju.order.update");
						al.setJsonParams(exceptionParams);
						linkData(al);
						continue;
					}
					predictwaybillColumns.setPkpk_code(customerCode);
				}
			}

			//ƽ̨���׺ţ����� ebay,wish �Ƚ��׺�
			String platformTradeCode = orders.getString("platformTradeCode");
			//����״̬
			String status =orders.getString("status");
			//�Ƿ����쳣
			String hasException =orders.getString("hasException");
			//�쳣��Ϣ��������Ӧ�̴�����Ϣ
			String processMessage =orders.getString("processMessage");
			//Package ID
			String packageId =orders.getString("packageId");
			//Ԥ�ƶ����˷�
			String priceForcast =orders.getString("priceForcast");
			//ʵ�ʶ����˷�
			String priceReal =orders.getString("priceReal");
			//������ַ���Ҵ���
			String shippingCountryCode =orders.getString("shippingCountryCode");
			//��������ʱ��
			String timeCreated =orders.getString("timeCreated");
			//�ͻ�Ԥ��������������λ�� g �ˣ�
			String weightForcast =orders.getString("weightForcast");
			if(!StringUtility.isNull(weightForcast)){
				BigDecimal b1 = new BigDecimal(weightForcast);
				BigDecimal b2 = b1.divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_EVEN);
				weightForcast = b2.toString();
			}
			//��Ӧ��ʵ�ʳ��ض�����������λ�� g �ˣ�
			String weightReal =orders.getString("weightReal");
			//�������ȣ���λ�� mm ���ף�
			String length =orders.getString("length");
			//������ȣ���λ�� mm ���ף�
			String width =orders.getString("width");
			//�����߶ȣ���λ�� mm ���ף�
			String height =orders.getString("height");
			//�����걨��Ʒ������
			String productNameCn =orders.getString("productNameCn");
			//�����걨��ƷӢ����
			String productNameEn =orders.getString("productNameEn");
			//�����걨��ֵ(ԭʼ����)
			String productValue =orders.getString("productValue");
			//��ע
			String remark =orders.getString("remark");
			//��������
			String itemListQuantity =orders.getString("itemListQuantity");
			//����������
			String itemListCount =orders.getString("itemListCount");
			//���ҷ���
			String currencyCode =orders.getString("currencyCode");
			//����ƽ̨����ʱ��
			String platformPayTime =orders.getString("platformPayTime");

			//��������
			predictwaybillColumns.setPwbpwb_orderid(platformTradeCode);
			predictwaybillColumns.setPwbpwb_transactionid(code);
			//				if(status.equals("2")){
			predictwaybillColumns.setPwbspwbs_code("CTS");
			//				}else if(status.equals("3")){
			//					predictwaybillColumns.setPwbspwbs_code("CHD");
			//				}else if(status.equals("4")){
			//					predictwaybillColumns.setPwbspwbs_code("SO");
			//				}else if(status.equals("5")){
			//					predictwaybillColumns.setPwbspwbs_code("SI");
			//				}

			predictwaybillColumns.setPwbpwb_createdate(timeCreated);
			predictwaybillColumns.setPwbpwb_chargeweight(weightForcast);
			predictwaybillColumns.setPwbpwb_cargoename(productNameCn);
			predictwaybillColumns.setPwbpwb_cargoamount(productValue);
			predictwaybillColumns.setPwbpwb_cargopieces(itemListQuantity);
			predictwaybillColumns.setPwbck_code(currencyCode);

			//��Ӧ�̲�Ʒ������չ�ֶλ���ʾ���²���
			//	JSONObject extendFields =  JSONObject.fromObject(orders.get("extendFields"));
			//	JSONObject fields =  JSONObject.fromObject(extendFields.get("fields"));

			
			//�ļ�����Ϣ
			JSONObject addressPickup =  JSONObject.fromObject(orders.get("addressPickup"));

			//�˼�����Ϣ
			JSONObject addressBack =  JSONObject.fromObject(orders.get("addressBack"));

			//�ռ�����Ϣ
			JSONObject addressReceive =  JSONObject.fromObject(orders.get("addressReceive"));
			
			String countryCode =addressReceive.getString("countryCode");//�ռ��˹���
			String receiver =addressReceive.getString("receiver");
			String province =addressReceive.getString("province");
			String city =addressReceive.getString("city");
			String street1 =addressReceive.getString("street1");
			String telephone =addressReceive.getString("telephone");
			String zipcode =addressReceive.getString("zipcode");
			
			try {
				predictwaybillColumns.setDtdt_code(DistrictDemand.getDtcodeByHubcode(countryCode));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			predictwaybillColumns.setPwbpwb_consigneename(receiver);
			predictwaybillColumns.setPwbpwb_consigneestate(province);
			
			predictwaybillColumns.setPwbpwb_consigneecity(city);
			predictwaybillColumns.setPwbpwb_consigneeaddress1(street1);
			
			predictwaybillColumns.setPwbpwb_consigneecityex(StringUtility.buildToByte(city, "utf-8", 512));
			predictwaybillColumns.setPwbpwb_consigneeaddressex(StringUtility.buildToByte(street1, "utf-8", 512));
			predictwaybillColumns.setPwbpwb_consigneenameex(StringUtility.buildToByte(receiver, "utf-8", 512));
			
			predictwaybillColumns.setPwbpwb_consigneetel(telephone);
			predictwaybillColumns.setPwbpwb_consigneepostcode(zipcode);
			//������Ϣ
//			JSONObject customer =  JSONObject.fromObject(orders.get("customer"));

			String expressChannelCode =orders.getString("expressChannelCode");
			predictwaybillColumns.setPwbpwb_serverewbcode(expressChannelCode);
			//��Ʒ��Ϣ
//			JSONObject expressChannel =  JSONObject.fromObject(orders.get("expressChannel"));
//			String name =expressChannel.getString("name");
//			String channelType =expressChannel.getString("channelType");
//			String allowReturn =expressChannel.getString("allowReturn");
//			String allowBattary =expressChannel.getString("allowBattary");
			if(!StringUtility.isNull(pkCode)){
			predictwaybillColumns.setPkpk_code(pkCode);
			}
			if(!StringUtility.isNull(coCode)){
			predictwaybillColumns.setCoco_code(coCode);
			}
			if(StringUtility.isNull(opId)){
				opId ="1";
			}
			
			listPwbc.add(predictwaybillColumns);
						
			
			//������Ʒ����
			List<PredictcargoinfoColumns> listCargoInfo = new ArrayList<PredictcargoinfoColumns>();
			//��Ʒ��Ϣ
			JSONArray jaryItem = JSONArray.fromObject(orders.get("itemList"));
			for (int j = 0; j < jaryItem.size(); j++) {
				PredictcargoinfoColumns predictcargoinfo =new PredictcargoinfoColumns();
				
				JSONObject item =  jaryItem.getJSONObject(j);
				
				//��Ʒ SKU
				String sku =item.getString("sku");
				//��Ʒ����
				String productName =item.getString("productName");
				//��Ʒ�����걨����
				String declareNameCn =item.getString("declareNameCn");
				//��ƷӢ���걨����
				String declareNameEn =item.getString("declareNameEn");
				//��Ʒ������������λ�� g �ˣ�
				String weight =item.getString("weight");
				//����
				String quantity =item.getString("quantity");
				//��Ʒ�걨��ֵ(ԭʼ����)
				String declareValue =item.getString("declareValue");
				//����ƽ̨��Ʒ URL ��ַ
				String itemUrl =item.getString("itemUrl");
				//����ƽ̨��Ʒ ID
				String itemId =item.getString("itemId");
				//���ر���
				String hsCode =item.getString("hsCode");
				//��λ��
				String gridCode =item.getString("gridCode");
				//�ۼ�
				String sellPrice =item.getString("sellPrice");
				//�ֿ�����
				String warehouseName =item.getString("warehouseName");
				
				if(!StringUtility.isNull(weight)){
					BigDecimal bdweight = new BigDecimal(weight);
					BigDecimal bdweight2 = bdweight.divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_EVEN);
					weight = bdweight2.toString();
				}
				predictcargoinfo.setPcipci_name(declareNameCn);
				predictcargoinfo.setPcipci_ename(declareNameEn);
				predictcargoinfo.setPcipci_weight(weight);
				predictcargoinfo.setPcipci_pieces(quantity);
				predictcargoinfo.setPcipci_id(itemId);
				predictcargoinfo.setPcipci_hscode(hsCode);
				predictcargoinfo.setPcipci_unitprice(declareValue);
				predictcargoinfo.setPcick_code(currencyCode);
				
				listCargoInfo.add(predictcargoinfo);
			}
			listlsInfo.add(listCargoInfo);
		}
		map.put("listPwbc", listPwbc);
		map.put("listlsInfo", listlsInfo);
		map.put("opId", opId);
		return map;
	}

	public List<ERPEntity> doImport(List<PredictwaybillColumns> listPwbc,
			List<List<PredictcargoinfoColumns>> listlsInfo,String opId) throws Exception{
		List<ERPEntity> listErp =new ArrayList<ERPEntity>();
		for (int i = 0 ;i<listPwbc.size();i++){
			ERPEntity erp =new ERPEntity();
			Predictwaybill predictwaybill =new Predictwaybill();
			// ����Ԥ��
			SavedResultUtility srut = predictwaybill.save(listPwbc.get(i), 
					listlsInfo.get(i), opId);
			//String code = listPwbc.get(i).getPwbpwb_orderid();
			String code = listPwbc.get(i).getPwbpwb_transactionid();
			
			if (srut.getPromptUtilityCollection().canGo(false)) {
				PredictwaybillColumns savedColumns = (PredictwaybillColumns) srut.getColumns();
				String pwbCode = savedColumns.getPwbpwb_code();
				// �걨
				SavedResultUtility ufResult = predictwaybill.upload(pwbCode,opId);
				if (ufResult.getPromptUtilityCollection().canGo(false)) {
					erp.setIsSuccess(true);
					PredictwaybillColumns ufColumns = (PredictwaybillColumns) ufResult.getColumns();
					erp.setServiceNumber(ufColumns.getPwbpwb_serverewbcode());
					erp.setCode(code);
				} else {
					// ����Ԥ��
					predictwaybill.withdraw(pwbCode, opId, false);
					erp.setIsSuccess(false);
					erp.setCode(code);
					erp.setErrorMessage(ufResult.getPromptUtilityCollection().toString());
				}
			} else {
				erp.setIsSuccess(false);
				erp.setCode(code);
				erp.setErrorMessage(srut.getPromptUtilityCollection().toString());
			}
			listErp.add(erp);
		}
		return listErp;
	}
}
