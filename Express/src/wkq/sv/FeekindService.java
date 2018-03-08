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
	 * ��ѯ��������
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
	 * �¼ӷ�������,
	 */
	public void addFeekind(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		//Columns�����ò�ѯ���
		FeekindColumns   objFeekindColumns = (FeekindColumns)objPD.getParameter(
				0, FeekindColumns  .class);//����ȡ����
		Feekind objFeekind = new Feekind();
		//��ӵľ���ʵ�ִ�
		FeekindColumns objReturn = objFeekind.addFeekind(objFeekindColumns);

		/*Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();*/
	}

	/*
	 * ����
	 
	public String eliminate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strFkcode = (String) objPD.getParameter(0, String.class);
		Feekind objFeekind = new Feekind();
		objFeekind.modifyStatus(strFkcode, "OFF");
		return "";
	}*/

	/**
	 * 20160727 ����16:21 
	 * by wukaiquan
	 * �޸�
	 * 
	 * **/
	public String editFeedind() {
		
		
		
		
		return null;
	}
	/**
	 * 20160727 ����16:21 
	 * by wukaiquan
	 * ����
	 * 
	 * **/
	public String addFeedind() {
		
		return null;
	}
	/**
	 * 20160727 ����16:21 
	 * by wukaiquan
	 * ɾ��
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
