package kyle.leis.eo.operation.predictwaybill.tongtool;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.predictwaybill.bl.Predictwaybill;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.mabang.ApiHttpResult;
import kyle.leis.eo.operation.predictwaybill.mabang.ERPEntity;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class ApiTongToolDate {
	//获取通途订单数据
	public String getDate(String mainUrl,
			String toKen,
			String since,
			String orderStatus,
			String limit,
			String shippingMethodCode,
			String nextToken){
		if(StringUtility.isNull(nextToken)){
			if(StringUtility.isNull(mainUrl)||StringUtility.isNull(toKen)||StringUtility.isNull(since)){
				return "";
			}
		}else{
			if(StringUtility.isNull(mainUrl)||StringUtility.isNull(toKen)){
				return "";
			}
		}
		JSONObject data=new JSONObject();
		if(!StringUtility.isNull(since)){
			data.put("since", since.replaceAll(" ",""));
		}
		if(!StringUtility.isNull(orderStatus)){
			data.put("orderStatus", orderStatus);
		}
		if(!StringUtility.isNull(limit)){
			data.put("limit", limit);
		}
		if(!StringUtility.isNull(shippingMethodCode)){
			data.put("shippingMethodCode", shippingMethodCode);
		}
		if(!StringUtility.isNull(nextToken)){
			data.put("nextToken", nextToken);
		}
		JSONObject json=new JSONObject();
		json.put("token", toKen);
		json.put("data", data);
		String url ="http://"+mainUrl+"/process/resume/package/admin/getOrder?q="+json.toString();
		String tongToolData= ApiHttpResult.getHttpResult(url);
		
		return tongToolData;
	}
	//添加物流渠道
	public String setLogistics(String mainUrl,
			String toKen,
			String operationType,
			String shippingMethodName,
			String shippingMethodCode) throws UnsupportedEncodingException{
		if(StringUtility.isNull(mainUrl)||StringUtility.isNull(toKen)||StringUtility.isNull(operationType)||
				StringUtility.isNull(shippingMethodName)||StringUtility.isNull(shippingMethodCode)){
			return "";
		}
		JSONObject data=new JSONObject();
		data.put("operationType", operationType);
		data.put("shippingMethodCode", shippingMethodCode);
//		shippingMethodName = URLEncoder.encode(shippingMethodName, "utf-8");
		data.put("shippingMethodName", shippingMethodName);
		data.put("hasTrackingNumber", "1");
		JSONObject json=new JSONObject();
		json.put("token", toKen);
		json.put("data", data);
		String url ="http://"+mainUrl+"/process/resume/shippingMethod/admin/maintainShippingMethod";
		String tongToolData= ApiTongHttpResult.getHttpResult(url,json.toString());
		
		return tongToolData;
	}
	//回写订单
	public String returnOrder(String mainUrl,String toKen,String ttPacketId,String statusChange,
			String logisticsSysId,String trackingNumber,String failureReason){
		if(StringUtility.isNull(mainUrl)||StringUtility.isNull(toKen)||StringUtility.isNull(ttPacketId)
				||StringUtility.isNull(statusChange)){
			return "";
		}
		JSONObject data=new JSONObject();
		data.put("statusChange", statusChange);
		data.put("ttPacketId", ttPacketId);
		if(!StringUtility.isNull(logisticsSysId)){
			data.put("logisticsSysId", logisticsSysId);
		}
		if(!StringUtility.isNull(trackingNumber)){
			data.put("trackingNumber", trackingNumber);
		}
		if(!StringUtility.isNull(failureReason)){
			data.put("failureReason", failureReason);
		}
		JSONObject json=new JSONObject();
		json.put("token", toKen);
		json.put("data", data);
		String url = "http://"+mainUrl+"/process/resume/package/admin/writebackOrder";
		String tongToolData= ApiTongHttpResult.getHttpResult(url,json.toString());
//		System.out.println(tongToolData);
		return tongToolData;
	}
	
	public Map<String,Object> getPredictwaybillColumns(String data,
			String opId,
			String pkCode,
			String coCode, 
			String userName){
		Map<String,Object> map =new HashMap<String, Object>();
		List<PredictwaybillColumns> listPwbc= new ArrayList<PredictwaybillColumns>();
		List<List<PredictcargoinfoColumns>> listlsInfo = new ArrayList<List<PredictcargoinfoColumns>>();
		
		JSONObject result =  JSONObject.fromObject(data);
		JSONArray jsData =  JSONArray.fromObject(result.get("data"));
		//得到数据json
		JSONArray jary = JSONArray.fromObject(jsData.getJSONObject(0).get("orderArray"));
		for (int i = 0; i < jary.size(); i++) {
			//每一条json数据
			JSONObject orders =  jary.getJSONObject(i);
			
			//校验是否是当前用户的订单
			JSONArray apiParamArray = JSONArray.fromObject(orders.get("apiParamArray"));
			if(apiParamArray!=null && apiParamArray.size()>0){
				JSONObject apiParam =  apiParamArray.getJSONObject(0);
				String apiValue =apiParam.getString("apiValue");
				if(!userName.equals(apiValue)){
					continue;
				}
			}
			//设置订单信息
			PredictwaybillColumns predictwaybillColumns = new PredictwaybillColumns();

			//通途包裹号
			String ttPacketId =orders.getString("ttPacketId");
			//通途包裹状态
			String ttPacketStatus =orders.getString("ttPacketStatus");
			//物流商系统单号
			String carrierOrderId =orders.getString("carrierOrderId");
			//跟踪号,未在物流商系统下单状态的订单或无跟踪号的渠道的订单为空
			String trackingNumber =orders.getString("trackingNumber");
			//订单状态的最后更新时间
			String lastsyncTime =orders.getString("lastsyncTime");
			//收件人姓名
			String recipientName =orders.getString("recipientName");
			//收件人地址1
			String recipientAddress1 =orders.getString("recipientAddress1");
			//收件人地址2
			String recipientAddress2 =orders.getString("recipientAddress2");
			//收件人省州
			String recipientState =orders.getString("recipientState");
			//收件人城市
			String recipientCity =orders.getString("recipientCity");
			//收件人国家英文名称
			String recipientCountryEnName =orders.getString("recipientCountryEnName");
			//收件人国家中文名称
			String recipientCountryCnName =orders.getString("recipientCountryCnName");
			//收件人国家二字代码
			String recipientCountry =orders.getString("recipientCountry");
			//收件人邮编
			String recipientPostalCode =orders.getString("recipientPostalCode");
			//收件人电话
			String recipientTelephone =orders.getString("recipientTelephone");
			//收件人手机
			String recipientMobile =orders.getString("recipientMobile");
			//收件人电子邮箱
			String recipientEmail =orders.getString("recipientEmail");
			
			//寄件人姓名
			String senderName =orders.getString("senderName");
			//寄件人公司
			String senderCompany =orders.getString("senderCompany"); 
			//寄件人地址1
			String senderAddress1 =orders.getString("senderAddress1");
			//寄件人地址2
			String senderAddress2 =orders.getString("senderAddress2");
			//寄件人省州
			String senderState =orders.getString("senderState");
			//寄件人城市
			String senderCity =orders.getString("senderCity");
			//寄件人国家
			String senderCountry =orders.getString("senderCountry");
			//寄件人邮编
			String senderPostalCode =orders.getString("senderPostalCode");
			//寄件人电话
			String senderTelephone =orders.getString("senderTelephone");
			//寄件人手机
			String senderMobile =orders.getString("senderMobile");
			//寄件人电子邮箱
			String senderEmail =orders.getString("senderEmail");
			//通途商户号
			String merchantId =orders.getString("merchantId");
			//渠道代码，物流商系统用来识别渠道的代码，不填则返回物流商所有渠道的订单
			String shippingMethodCode =orders.getString("shippingMethodCode");
			
			//导入数据
			predictwaybillColumns.setPwbpwb_orderid(ttPacketId);
//			predictwaybillColumns.setPwbpwb_transactionid();
			predictwaybillColumns.setPwbspwbs_code("CTS");
			predictwaybillColumns.setPwbpwb_modifydate(lastsyncTime);
//			predictwaybillColumns.setPwbpwb_createdate(timeCreated);
//			predictwaybillColumns.setPwbpwb_chargeweight(weightReal);
//			predictwaybillColumns.setPwbpwb_cargoename(productNameCn);
//			predictwaybillColumns.setPwbpwb_cargoamount(productValue);
//			predictwaybillColumns.setPwbpwb_cargopieces(itemListQuantity);
//			predictwaybillColumns.setPwbck_code(currencyCode);
			
			predictwaybillColumns.setPwbpwb_consigneename(recipientName);
			// 防止乱码
			predictwaybillColumns.setPwbpwb_consigneenameex(StringUtility.buildToByte(recipientName, "utf-8", 512));
			
			
			predictwaybillColumns.setPwbpwb_consigneestate(recipientState);
			predictwaybillColumns.setPwbpwb_consigneecity(recipientCity);
			
			predictwaybillColumns.setPwbpwb_consigneeaddress1(recipientAddress1);
			predictwaybillColumns.setPwbpwb_consigneeaddress2(recipientAddress2);
			// 防止乱码
			predictwaybillColumns.setPwbpwb_consigneeaddressex(StringUtility.buildToByte(recipientAddress1 + " " + recipientAddress2, "utf-8", 512));
			
			predictwaybillColumns.setPwbpwb_consigneetel(recipientTelephone);
			predictwaybillColumns.setPwbpwb_consigneepostcode(recipientPostalCode);
			predictwaybillColumns.setPwbpwb_serverewbcode(carrierOrderId);
			try {
				predictwaybillColumns.setDtdt_code(DistrictDemand.getDtcodeByHubcode(recipientCountry));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			if(!StringUtility.isNull(pkCode)){
			predictwaybillColumns.setPkpk_code(pkCode);
			}
			if(!StringUtility.isNull(coCode)){
			predictwaybillColumns.setCoco_code(coCode);
			}
			if(StringUtility.isNull(opId)){
				opId ="1";
			}
//			String totWeight ="";
			BigDecimal b1 = new BigDecimal(0);
			//设置商品详情
			List<PredictcargoinfoColumns> listCargoInfo = new ArrayList<PredictcargoinfoColumns>();
			//商品信息
			JSONArray jaryItem = JSONArray.fromObject(orders.get("declarationArray"));
			for (int j = 0; j < jaryItem.size(); j++) {
				PredictcargoinfoColumns predictcargoinfo =new PredictcargoinfoColumns();
				
				JSONObject item =  jaryItem.getJSONObject(j);
				
				//海关编码
				String hsCode =item.getString("hsCode");
				//申报中文品名
				String declareCnName =item.getString("declareCnName");
				//申报英文品名
				String declareEnName =item.getString("declareEnName");
				//申报币种，三字货币代码
				String declareCurrency =item.getString("declareCurrency");
				//申报价值
				String declareValue =item.getString("declareValue");
				//申报重量，单位千克
				String declareWeight =item.getString("declareWeight");
				
				//申报的产品链接
				String declareUrl =item.getString("declareUrl");
				//申报的产品ID
				String declareProductId =item.getString("declareProductId");
				//申报产品数量
				String declareNumber =item.getString("declareNumber");
				
				//设置总重量
				BigDecimal b2 = new BigDecimal(declareWeight);
				b1 = b1.add(b2);
				
				if(!StringUtility.isNull(declareWeight)){
					BigDecimal deWeight = new BigDecimal(declareWeight);
					BigDecimal deWeight2 = deWeight.divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_EVEN);
					declareWeight = deWeight2.toString();
					predictcargoinfo.setPcipci_weight(declareWeight);
				}
				predictcargoinfo.setPcipci_name(declareCnName);
				predictcargoinfo.setPcipci_ename(declareEnName);
//				predictcargoinfo.setPcipci_weight(declareWeight);
				predictcargoinfo.setPcipci_pieces(declareNumber);
				predictcargoinfo.setPcipci_id(declareProductId);
				predictcargoinfo.setPcipci_hscode(hsCode);
				predictcargoinfo.setPcipci_unitprice(declareValue);
				predictcargoinfo.setPcick_code(declareCurrency);
				
				listCargoInfo.add(predictcargoinfo);
			}
			BigDecimal b3 = b1.divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_EVEN);
			String totWeight = b3.toString();
			predictwaybillColumns.setPwbpwb_chargeweight(totWeight);
			
			listlsInfo.add(listCargoInfo);
			listPwbc.add(predictwaybillColumns);
		}
		map.put("listPwbc", listPwbc);
		map.put("listlsInfo", listlsInfo);
		return map;
	}
		
		public List<List<PredictcargoinfoColumns>> getPredictcargoinfoColumns(String data,String userName){
			
			List<List<PredictcargoinfoColumns>> listlsInfo = new ArrayList<List<PredictcargoinfoColumns>>();
			
			JSONObject result =  JSONObject.fromObject(data);
			JSONArray jsData =  JSONArray.fromObject(result.get("data"));
			//得到数据json
			JSONArray jary = JSONArray.fromObject(jsData.getJSONObject(0).get("orderArray"));
			for (int i = 0; i < jary.size(); i++) {
				//每一条json数据
				JSONObject orders =  jary.getJSONObject(i);
				
				//校验是否是当前用户的订单
				JSONArray apiParamArray = JSONArray.fromObject(orders.get("apiParamArray"));
				if(apiParamArray!=null && apiParamArray.size()>0){
					JSONObject apiParam =  apiParamArray.getJSONObject(0);
					String apiValue =apiParam.getString("apiValue");
					if(!userName.equals(apiValue)){
						continue;
					}
				}
				
				//设置商品详情
				List<PredictcargoinfoColumns> listCargoInfo = new ArrayList<PredictcargoinfoColumns>();
				//商品信息
				JSONArray jaryItem = JSONArray.fromObject(orders.get("declarationArray"));
				for (int j = 0; j < jaryItem.size(); j++) {
					PredictcargoinfoColumns predictcargoinfo =new PredictcargoinfoColumns();
					
					JSONObject item =  jaryItem.getJSONObject(j);
					
					//海关编码
					String hsCode =item.getString("hsCode");
					//申报中文品名
					String declareCnName =item.getString("declareCnName");
					//申报英文品名
					String declareEnName =item.getString("declareEnName");
					//申报币种，三字货币代码
					String declareCurrency =item.getString("declareCurrency");
					//申报价值
					String declareValue =item.getString("declareValue");
					//申报重量，单位千克
					String declareWeight =item.getString("declareWeight");
					//申报的产品链接
					String declareUrl =item.getString("declareUrl");
					//申报的产品ID
					String declareProductId =item.getString("declareProductId");
					//申报产品数量
					String declareNumber =item.getString("declareNumber");
					
					
					predictcargoinfo.setPcipci_name(declareCnName);
					predictcargoinfo.setPcipci_ename(declareEnName);
					predictcargoinfo.setPcipci_weight(declareWeight);
					predictcargoinfo.setPcipci_pieces(declareNumber);
					predictcargoinfo.setPcipci_id(declareProductId);
					predictcargoinfo.setPcipci_hscode(hsCode);
					predictcargoinfo.setPcipci_unitprice(declareValue);
					predictcargoinfo.setPcick_code(declareCurrency);
					
					listCargoInfo.add(predictcargoinfo);
				}
				listlsInfo.add(listCargoInfo);
			}
			return listlsInfo;
		}

		public List<ERPEntity> doImport(List<PredictwaybillColumns> listPwbc,
				List<List<PredictcargoinfoColumns>> listlsInfo,String opId) throws Exception{
			List<ERPEntity> listErp =new ArrayList<ERPEntity>();
			for (int i = 0 ;i<listPwbc.size();i++){
				ERPEntity erp =new ERPEntity();
				Predictwaybill predictwaybill =new Predictwaybill();
				SavedResultUtility srut = predictwaybill.save(listPwbc.get(i), 
						listlsInfo.get(i), opId);
				String code = listPwbc.get(i).getPwbpwb_orderid();
				if (srut.getPromptUtilityCollection().canGo(false)) {
					PredictwaybillColumns savedColumns = (PredictwaybillColumns) srut.getColumns();
					String pwbCode = savedColumns.getPwbpwb_code();
					// 申报
					SavedResultUtility ufResult = predictwaybill.upload(pwbCode,opId);
					if (ufResult.getPromptUtilityCollection().canGo(false)) {
						erp.setIsSuccess(true);
						PredictwaybillColumns ufColumns = (PredictwaybillColumns) ufResult.getColumns();
						erp.setServiceNumber(ufColumns.getPwbpwb_serverewbcode());
						erp.setCode(code);
					} else {
						// 撤销预报
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
