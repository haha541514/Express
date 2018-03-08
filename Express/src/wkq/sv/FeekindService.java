package wkq.sv;

import java.util.List;

import wkq.da.FeekindColumns;
import wkq.da.FeekindCondition;
import wkq.dax.FeekindDemand;
import wkq.tp.DeleteFeekindhibernate;
import wkq.bl.Feekind;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;


public class FeekindService extends AService {
	/*
	 * 查询费用种类
	 */
	public String queryFeekind(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		FeekindCondition objFeekindCondition = (FeekindCondition) objPD
				.getParameter(0, FeekindCondition.class);
		List objList = FeekindDemand.query(objFeekindCondition);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}

	/*
	 * 新加费用种类,
	 */
	public void addFeekind(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		//Columns用来访查询结果
		FeekindColumns   objFeekindColumns = (FeekindColumns)objPD.getParameter(
				0, FeekindColumns  .class);//数据取到了
		Feekind objFeekind = new Feekind();
		//添加的具体实现处
		FeekindColumns objReturn = objFeekind.addFeekind(objFeekindColumns);

		/*Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();*/
	}

	/*
	 * 作废
	 
	public String eliminate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strFkcode = (String) objPD.getParameter(0, String.class);
		Feekind objFeekind = new Feekind();
		objFeekind.modifyStatus(strFkcode, "OFF");
		return "";
	}*/

	/**
	 * 20160727 周三16:21 
	 * by wukaiquan
	 * 修改
	 * 
	 * **/
	public String editFeedind() {
		
		
		
		
		return null;
	}
	/**
	 * 20160727 周三16:21 
	 * by wukaiquan
	 * 新增
	 * 
	 * **/
	public String addFeedind() {
		
		return null;
	}
	/**
	 * 20160727 周三16:21 
	 * by wukaiquan
	 * 删除
	 * 
	 * **/
	public void deleteFeedind(String fkcode) {
		DeleteFeekindhibernate delete = new DeleteFeekindhibernate();
		delete.setFkcode(fkcode);
		try {
			delete.execute();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
