package kyle.leis.eo.operation.housewaybill.svx;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableCondition;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesCondition;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesQuery;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.ForinputallQuery;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.RestfulJsonConsignee;
import kyle.leis.eo.operation.housewaybill.dax.RestfulJsonContent;
import kyle.leis.eo.operation.housewaybill.dax.RestfulJsonInfo;
import kyle.leis.eo.operation.housewaybill.dax.RestfulJsonOrderFee;
import kyle.leis.eo.operation.housewaybill.dax.RestfulJsonPiece;
import kyle.leis.eo.operation.housewaybill.dax.RestfulJsonShipper;
import kyle.leis.fs.authoritys.user.bl.User;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.feekind.da.FeekindColumns;
import kyle.leis.fs.dictionary.feekind.dax.FeekindDemand;


@Path("/PredictQuery")
public class PredictOrderRestfulQueryServlet {
		@SuppressWarnings({ "unchecked" })
		@POST
	    @Path("/{id}")
	    @Produces({ "application/json; charset=UTF-8;"})
	    public Response FinanceQuery(@PathParam("id") String Id,
	    		@FormParam("username") String username,
	    		@FormParam("password")   String password,
	    		@FormParam("OrderID") String OrderID) throws Exception {
			HashMap hm=new HashMap();		
			
			if("PredictOrderQuery".equals(Id)){
				
		    	if (StringUtility.isNull(username)|| StringUtility.isNull(password)) {	   
					 hm.put("errorinfo", "-1");
					 hm.put("errcode", "用户名或密码不能为空");			    		
		             return Response.ok(hm).build();
	    		}
		    	
		    	UserColumns objUserColumns = null;
		    	User objUser = new User();
	    		objUserColumns = objUser.login(username, password);
	    		if (objUserColumns == null || 
	    			StringUtility.isNull(objUserColumns.getCococode())) {	   
					 hm.put("errorinfo", "-1");
					 hm.put("errcode", "用户名或密码错误，请修改后再提交");			    		
		             return Response.ok(hm).build();
	    		}
	    		if (StringUtility.isNull(OrderID)) { 		  
					 hm.put("errorinfo", "-1");
					 hm.put("errcode", "运单key不能为空，请重新输入");			    		
		             return Response.ok(hm).build();
	    		}		
	    	 
	    		ForinputallQuery FQ=new ForinputallQuery();
	    		ForinputallCondition fc=new ForinputallCondition();
	    		fc.setCwcode(OrderID);
	    		FQ.setCondition(fc);
	    	
	    	//info	
				List<ForinputallColumns> lis=FQ.getResults();
				for(ForinputallColumns pc:lis){ 
					RestfulJsonInfo info = new RestfulJsonInfo();
					info.setPackageOrDoc(pc.getCtcode());
					info.setInvoiceChoices(pc.getItiptcode());
					info.setInsuranceBeneficiary(pc.getIfyib_code());
					info.setDCustoms(pc.getHwhwDcustomssign());
					info.setWeight(pc.getCwgrossweight());
					info.setOrderID(pc.getCwcode());
					CorewaybillpiecesCondition cd=new CorewaybillpiecesCondition();
					cd.setCwcode(pc.getCwcode());
					CorewaybillpiecesQuery cq=new CorewaybillpiecesQuery();
					cq.setCondition(cd);
					List<CorewaybillpiecesColumns> ls=cq.getResults();
					info.setVolumeWeight(CorewaybillpiecesDemand.calcVolumeweight(ls, pc.getstrVolumerate()));
					info.setFeeWeight(pc.getCwchargeweight()); 
					info.setAmount(pc.getCwpieces());
					info.setWeightRcv(pc.getCwchargeweight());
					info.setAmountRcv(pc.getCwpieces());
					info.setDCustoms(pc.getHwhwDcustomssign());
					info.setVolumeWeightRcv(CorewaybillpiecesDemand.calcVolumeweight(ls, pc.getstrVolumerate()));
					info.setFeeWeightRcv(pc.getCwchargeweight());
					//Consignee	
					RestfulJsonConsignee rc=new RestfulJsonConsignee();
					rc.setPersonName(pc.getHwconsigneename());
					rc.setCompanyName(pc.getHwconsigneecompany());
					rc.setAddress1(pc.getHwconsigneeaddress1());
					rc.setAddress2(pc.getHwconsigneeaddress2());
					rc.setAddress3(pc.getHwconsigneeaddress3());
					rc.setCity(pc.getHwConsigneecity());
					rc.setPostalCode(pc.getHwconsigneepostcode());
					
					String strCountryHubcode = DistrictDemand.getCountryHubcodeByCity(pc.getDtcode());				 
					rc.setCountryCode(strCountryHubcode);
					rc.setPhoneNumber(pc.getHwconsigneetelephone());
					rc.setPackageType(pc.getHwpat_code());
					rc.setDeliveryType(pc.getHwdt_code());
					rc.setCargoKind(pc.getHwcgk_code());
					rc.setBatteryKind(pc.getHwbk_code());
					rc.setFaxNumber(pc.getHwconsigneefax());
				
					
					//Piece
					List<RestfulJsonPiece> ps=new ArrayList<RestfulJsonPiece>();		
					for(CorewaybillpiecesColumns cc:ls){ 
						RestfulJsonPiece rp=new RestfulJsonPiece();
						rp.setHeight(cc.getCpcpheight());
						rp.setLength(cc.getCpcplength());
						rp.setWidth(cc.getCpcpwidth());
						rp.setWeight(cc.getCpcpgrossweight());	
						rp.setVolumeWeight(CorewaybillpiecesDemand.calcVolumeweight(ls, pc.getstrVolumerate()));
						ps.add(rp);
					}
					
					
					//Content
					InputAllQReturn objIAQR = HousewaybillDemand.queryInput(fc);
					List listCargo = objIAQR.getCargoInfoResults();
					List<RestfulJsonContent> pct=new ArrayList<RestfulJsonContent>();
					if (listCargo != null && listCargo.size() > 0) {
						
						for (int i = 0; i < listCargo.size(); i++) {		
							CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
							RestfulJsonContent rt=new RestfulJsonContent();
							
							rt.setAmount(objCIC.getCicipieces());
							rt.setUnitPrice(objCIC.getCiciunitprice());
							rt.setAttacheinfo(objCIC.getCiciattacheinfo());
							rt.setCurrencyCurrency(objCIC.getCkckcode());
							rt.setDescription(objCIC.getCiciename());
							rt.setDescriptionCN(objCIC.getCiciname());
							rt.setTotalPrice(objCIC.getCicitotalprice());
							rt.setRemark(objCIC.getCiciremark());				
							pct.add(rt);
							}
					
					}
					//Shipper
					RestfulJsonShipper rs=new RestfulJsonShipper();
					rs.setServer(pc.getPk_code());
					rs.setReferenceID(pc.getCwcustomerewbcode());
					rs.setPersonName(pc.getHwshippername());
					rs.setCompanyName(pc.getHwshippercompany());
					rs.setAddress1(pc.getHwshipperaddress1());
					rs.setAddress2(pc.getHwshipperaddress2());
					rs.setAddress3(pc.getHwshipperaddress3());
					rs.setCity(pc.getHwDtcodeshipper());
					rs.setPostalCode(pc.getHwshipperpostcode());
					
					String strCountryHubcode2 = DistrictDemand.getCountryHubcodeByCity(pc.getHwDtcodeshipper());				 
					rs.setCountryCode(strCountryHubcode2);
					rs.setPhoneNumber(pc.getHwshippertelephone());
					
					//OrderFee
					Double Finally = 0.00;
					Double Finally2 = 0.00;
					List<RestfulJsonOrderFee> reb=new ArrayList<RestfulJsonOrderFee>();				
					List<ReceivableColumns> rec=ReceivableDemand.load(pc.getCwcode());			
					List<String> fkcode=new ArrayList<String>();
						for(ReceivableColumns col:rec){  //获取FKCODE  SET币种
							fkcode.add(col.getFkfkcode());
							info.setCurrency(col.getCkckcode());
						}
						//去掉重复的FKCODE
						 List<String> listWithoutDup = new ArrayList<String>(new HashSet<String>(fkcode));
					       for(int i=0;i<listWithoutDup.size();i++){
					    		Double feeAll = 0.00;
								Double actualFeeAll = 0.00;
								Double link; 
								Double link2;
								
					    	   RestfulJsonOrderFee re=new RestfulJsonOrderFee();
					    	   FeekindColumns objFeekind=FeekindDemand.queryByFkcode(listWithoutDup.get(i));
					    	   ReceivableCondition rcd=new ReceivableCondition();
					    	   rcd.setFkcode(listWithoutDup.get(i));
					    	   rcd.setCwcode(pc.getCwcode());
					    	   List<ReceivableColumns> res=  ReceivableDemand.query(rcd);
					    	   for(ReceivableColumns rcl:res){
					    		   link=Double.valueOf(rcl.getRvrvtotal());
					    		   link2=Double.valueOf(rcl.getRvrvactualtotal());
					    		   feeAll=feeAll+link;  //累加费用
					    		   actualFeeAll=actualFeeAll+link2;
					    	   }
					    	   Finally=Finally+feeAll;  //总的费用
					    	   Finally2=Finally2+actualFeeAll; //总的实际费用
					    	   re.setFeecode(listWithoutDup.get(i));
					    	   re.setFeeName(objFeekind.getFkfkname());
					    	   re.setFeeMoney(String.valueOf(feeAll));
					    	   reb.add(re);
					       }
					
					info.setOrderPrice(String.valueOf(Finally));
					info.setPaidMoney(String.valueOf(Finally2));
					hm.put("OrderFee", reb);
					hm.put("Shipper", rs);
					hm.put("Content", pct);		
					hm.put("Piece", ps);
					hm.put("Consignee", rc);
					hm.put("Info", info);
					
				 return Response.ok(hm).build();
				}
			}
			hm.put("errorinfo", "-1");
		    hm.put("errcode", "请求失败，请检查请求地址");			    		
            return Response.ok(hm).build();	
	   }
	
}
