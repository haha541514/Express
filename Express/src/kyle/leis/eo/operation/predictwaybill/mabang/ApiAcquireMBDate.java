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
		//得到数据json
		JSONArray jary = JSONArray.fromObject(jsData.get("orders"));
		for (int i = 0; i < jary.size(); i++) {
			//设置订单信息
			PredictwaybillColumns predictwaybillColumns = new PredictwaybillColumns();
			
			//每一条json数据
			JSONObject orders =  jary.getJSONObject(i);
			//内部订单号
			String code =orders.getString("code"); 
			//验证产品相同的订单
			JSONObject myExpressChannel =JSONObject.fromObject(orders.get("myExpressChannel")); 
			String customerCode = myExpressChannel.getString("customerCode");
			
			//查询账户信息
			UserCondition objUserCondition = new UserCondition();
			UserColumns objUrReturn =null;
			//验证用户标识是否相同
			JSONObject customer =JSONObject.fromObject(orders.get("customer")); 
			JSONObject logisticsKeys =JSONObject.fromObject(customer.get("logisticsKeys"));
			JSONObject bqc =JSONObject.fromObject(logisticsKeys.get("bqc"));
			String api_key =bqc.getString("api_key"); 
			if(!StringUtility.isNull(opId)){
				objUserCondition.setOpid(opId);
				objUrReturn = (UserColumns) UserDemand.query(objUserCondition).get(0);
				//校验订单时需要当前用户的用户名
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
					erp.setErrorMessage("用户API为空,请检查API_KEY是否正确。");
					String exceptionParams = MabangUtil.getExceptionParams(erp);
					al.setApi("api.biaoju.order.update");
					al.setJsonParams(exceptionParams);
					linkData(al);
					continue;
				}
				objUserCondition.setOpcode(api_key);
				objUrReturn = (UserColumns) UserDemand.query(objUserCondition).get(0);
				//验证用户apikey是否存在
				//不存在则返回错误
				if (objUrReturn == null || 
	    				StringUtility.isNull(objUrReturn.getCococode())){
					ERPEntity erp =new ERPEntity();
					erp.setCode(code);
					erp.setErrorMessage("系统不存在该用户API,请检查API_KEY是否正确。");
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
						erp.setErrorMessage("该用户不存在该产品,请检查产品是否正确。");
						String exceptionParams = MabangUtil.getExceptionParams(erp);
						al.setApi("api.biaoju.order.update");
						al.setJsonParams(exceptionParams);
						linkData(al);
						continue;
					}
					predictwaybillColumns.setPkpk_code(customerCode);
				}
			}

			//平台交易号：例如 ebay,wish 等交易号
			String platformTradeCode = orders.getString("platformTradeCode");
			//订单状态
			String status =orders.getString("status");
			//是否有异常
			String hasException =orders.getString("hasException");
			//异常信息，物流供应商处理信息
			String processMessage =orders.getString("processMessage");
			//Package ID
			String packageId =orders.getString("packageId");
			//预计订单运费
			String priceForcast =orders.getString("priceForcast");
			//实际订单运费
			String priceReal =orders.getString("priceReal");
			//包裹地址国家代码
			String shippingCountryCode =orders.getString("shippingCountryCode");
			//订单生成时间
			String timeCreated =orders.getString("timeCreated");
			//客户预报订单重量（单位： g 克）
			String weightForcast =orders.getString("weightForcast");
			if(!StringUtility.isNull(weightForcast)){
				BigDecimal b1 = new BigDecimal(weightForcast);
				BigDecimal b2 = b1.divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_EVEN);
				weightForcast = b2.toString();
			}
			//供应商实际称重订单重量（单位： g 克）
			String weightReal =orders.getString("weightReal");
			//包裹长度（单位： mm 毫米）
			String length =orders.getString("length");
			//包裹宽度（单位： mm 毫米）
			String width =orders.getString("width");
			//包裹高度（单位： mm 毫米）
			String height =orders.getString("height");
			//订单申报物品中文名
			String productNameCn =orders.getString("productNameCn");
			//订单申报物品英文名
			String productNameEn =orders.getString("productNameEn");
			//订单申报价值(原始货币)
			String productValue =orders.getString("productValue");
			//备注
			String remark =orders.getString("remark");
			//货物数量
			String itemListQuantity =orders.getString("itemListQuantity");
			//订单项数量
			String itemListCount =orders.getString("itemListCount");
			//货币符号
			String currencyCode =orders.getString("currencyCode");
			//交易平台付款时间
			String platformPayTime =orders.getString("platformPayTime");

			//导入数据
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

			//供应商产品设置扩展字段会显示以下参数
			//	JSONObject extendFields =  JSONObject.fromObject(orders.get("extendFields"));
			//	JSONObject fields =  JSONObject.fromObject(extendFields.get("fields"));

			
			//寄件人信息
			JSONObject addressPickup =  JSONObject.fromObject(orders.get("addressPickup"));

			//退件人信息
			JSONObject addressBack =  JSONObject.fromObject(orders.get("addressBack"));

			//收件人信息
			JSONObject addressReceive =  JSONObject.fromObject(orders.get("addressReceive"));
			
			String countryCode =addressReceive.getString("countryCode");//收件人国家
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
			//卖家信息
//			JSONObject customer =  JSONObject.fromObject(orders.get("customer"));

			String expressChannelCode =orders.getString("expressChannelCode");
			predictwaybillColumns.setPwbpwb_serverewbcode(expressChannelCode);
			//产品信息
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
						
			
			//设置商品详情
			List<PredictcargoinfoColumns> listCargoInfo = new ArrayList<PredictcargoinfoColumns>();
			//商品信息
			JSONArray jaryItem = JSONArray.fromObject(orders.get("itemList"));
			for (int j = 0; j < jaryItem.size(); j++) {
				PredictcargoinfoColumns predictcargoinfo =new PredictcargoinfoColumns();
				
				JSONObject item =  jaryItem.getJSONObject(j);
				
				//商品 SKU
				String sku =item.getString("sku");
				//商品名称
				String productName =item.getString("productName");
				//商品中文申报名称
				String declareNameCn =item.getString("declareNameCn");
				//商品英文申报名称
				String declareNameEn =item.getString("declareNameEn");
				//商品单件重量（单位： g 克）
				String weight =item.getString("weight");
				//数量
				String quantity =item.getString("quantity");
				//商品申报价值(原始货币)
				String declareValue =item.getString("declareValue");
				//交易平台商品 URL 地址
				String itemUrl =item.getString("itemUrl");
				//交易平台商品 ID
				String itemId =item.getString("itemId");
				//海关编码
				String hsCode =item.getString("hsCode");
				//仓位号
				String gridCode =item.getString("gridCode");
				//售价
				String sellPrice =item.getString("sellPrice");
				//仓库名称
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
			// 保存预报
			SavedResultUtility srut = predictwaybill.save(listPwbc.get(i), 
					listlsInfo.get(i), opId);
			//String code = listPwbc.get(i).getPwbpwb_orderid();
			String code = listPwbc.get(i).getPwbpwb_transactionid();
			
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
