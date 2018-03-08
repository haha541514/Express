package wkq.test;

import java.util.List;

import net.sf.hibernate.Session;


import wkq.da.FeekindColumns;
import wkq.da.FeekindCondition;
import wkq.da.FeekindQuery;
import wkq.da.FeekindjdbcColumns;
import wkq.da.FeekindjdbcCondition;
import wkq.da.FeekindjdbcQuery;
import wkq.dax.FeekindDemand;
import wkq.sv.FeekindService;
import wkq.tp.DeleteFeekindhibernate;
import wkq.tp.ModifyFeekind;
import wkq.tp.SaveFeekindHibernate;

public class FeekTest {

	private static FeekindService feekindService = new FeekindService();
	public Session session;
	public static void main(String[] args) throws Exception {

		//queryAll();
		FeekTest feektest = new FeekTest();
		//feektest.jdbcQueryFeekind();
		//feektest.deleteFeekind();
		//feektest.addFeekind();
		//feektest.modifyFeekindById();
		feektest.queryAll();
		//sql����
		//feektest.addFeekind2();
		System.exit(0);
	}
	
	/**
	 * 20160727 ����16:21 
	 * by wukaiquan
	 * ��ѯ����
	 * hibernate��ʽ
	 * @throws Exception 
	 * **/
	public static void queryAll() throws Exception {
		List querylist = null;
		String fkcode = "";
		FeekindCondition objFeekindCondition = new FeekindCondition();
		objFeekindCondition.setFkcode(fkcode);
		FeekindQuery query = new FeekindQuery();
		query.setCondition(objFeekindCondition);
		
			querylist = query.getResults();//��û�б�������querylistΪ�գ�ȡ����
			
			for(int i=0;i<querylist.size();i++){

				FeekindColumns objFeekindColumns = (FeekindColumns) querylist
						.get(i);
				System.out.println(objFeekindColumns.getFofkcode());
				System.out.println(objFeekindColumns.getFofkename());
				System.out.println(objFeekindColumns.getFofkmanualmodifysign());
				System.out.println(objFeekindColumns.getFofkname());

			}
	
	}



/*	public String addFeekind() throws Exception {
		String str = "T02~`���Զ�~`Test02~`0~`Y~`Y~`some remark~`~`~`~`ON~`~`~@~#";

		Decoder objPD = new Decoder(str);
		//���ַ��������Ȼ������з��ظ�ǰ̨�����
		return m_objFeekindService.addFeekind(objPD);

	}*/
	


	/**20160728 ���� 11:05
	 *  by wukaiquan 
	 * option: jdbc Query
	 * 
	 * **/
	public void jdbcQueryFeekind(){
		List objlist = null;
		String fkcode = "";
		FeekindjdbcCondition feekindcondition = new FeekindjdbcCondition();
		
		FeekindjdbcQuery query = new FeekindjdbcQuery();
		feekindcondition.setFkcode(fkcode);
		query.setCondition(feekindcondition);
		try {
			 objlist = query.getResults();
		} catch (Exception e) {
			System.err.println("--jdbcQeury--");
			e.printStackTrace();
		} 
		 
		for(int i=0; i< objlist.size() ;i++){
			
			FeekindjdbcColumns feekindcolumns = (FeekindjdbcColumns)objlist.get(i);
			
			System.out.println(feekindcolumns.getFofk_code());
			System.out.println(feekindcolumns.getFofk_ename());
			System.out.println(feekindcolumns.getFofk_manualmodifysign());
			System.out.println(feekindcolumns.getFofk_name());
			System.out.println(feekindcolumns.getSiss_code());
			System.out.println(feekindcolumns.getSign());
		
		}
		
		
	}
	
	
	
	/**
	 * 20160727 ����16:21 
	 * by wukaiquan
	 * ɾ��
	 * @throws Exception 
	 * errror:java.sql.SQLException: ORA-02292: Υ������Լ������ (XSDBUSER.FK_RV_FK) - ���ҵ��Ӽ�¼
	 * **/
	public void deleteFeekind() throws Exception {

		/*String fkcode = "A1009";
		DeleteFeekindhibernate delete = new DeleteFeekindhibernate();
		delete.setFkcode(fkcode);
		delete.execute();*/
		//���ܵ���deleteFeekindhibernate ֱ��ɾ�� Υ������Լ������ (XSDBUSER.FK_RV_FK) - ���ҵ��Ӽ�¼
		DeleteFeekindhibernate delete = new DeleteFeekindhibernate();
		String fkCode = "T02";
		delete.setFkcode(fkCode);
		delete.execute();
		
		
	}
	
	/**
	 * 20160728 ���� 09:33 
	 * by wukaiquan
	 * ����
	 * @throws Exception 
	 * Decoder ��������
	 * **/
	public void addFeekind() throws Exception{

		FeekindColumns columns = new FeekindColumns();
		columns.setFofkcode("G22");
		columns.setSisscode("ON");
		columns.setFofkname("������");
		columns.setFofkename("test");
		columns.setFofkreferenceposition("0");
		columns.setFofkmanualmodifysign("Y");
		columns.setSign("Y");
		columns.setFofkremark("");
		columns.setExestcode("");
		columns.setFofkaccountingonlysign("N");
		columns.setFofkdeclarevaluesign("N");

		SaveFeekindHibernate saveFeekind = new SaveFeekindHibernate();
		saveFeekind.setParam(columns);
		System.out.println(columns.getFofkcode());
		saveFeekind.setNewFkCode(columns.getFofkcode());
		saveFeekind.execute();
		
	}
	
	
	public void modifyFeekindById() throws Exception{
		//ss_code =1,
		//java.lang.NumberFormatException: For input string: "A100"
		//java.lang.ClassCastException:java.lang.Long cannot be cast to java.lang.String
		/*String str = "A13~`ON~`������~`Test02~`0~`Y~`Y~`somremark~`~`N~`N~`~@~#";
		
		Decoder objPD = new Decoder(str);
		feekindService.addFeekind(objPD);*/
		FeekindColumns columns = new FeekindColumns();
		columns.setFofkcode("G22");
		columns.setSisscode("ON");
		columns.setFofkname("��������");
		columns.setFofkename("�ִ��Ϻ�");
		columns.setFofkreferenceposition("0");
		columns.setFofkmanualmodifysign("Y");
		columns.setSign("Y");
		columns.setFofkremark("remark");
		columns.setExestcode("");
		columns.setFofkaccountingonlysign("N");
		columns.setFofkdeclarevaluesign("N");

		ModifyFeekind modifyFeekind = new ModifyFeekind();
		modifyFeekind.setParam(columns.getFofkcode(),columns.getSisscode());
		modifyFeekind.setColumns(columns);
		System.out.println(columns.getFofkcode());
		
		modifyFeekind.execute();
		
	}
	
	
	public void addFeekind2() throws Exception{

		FeekindColumns columns = new FeekindColumns();
		columns.setFofkcode("K173");
		columns.setSisscode("ON");
		columns.setFofkname("��Ƥ��");
		columns.setFofkename("test");
		columns.setFofkreferenceposition("0");
		columns.setFofkmanualmodifysign("Y");
		columns.setSign("Y");
		columns.setFofkremark("");
		columns.setExestcode("");
		columns.setFofkaccountingonlysign("N");
		columns.setFofkdeclarevaluesign("N");

		SaveFeekindHibernate saveFeekind = new SaveFeekindHibernate();
		saveFeekind.setParam(columns);
	
		saveFeekind.setNewFkCode(columns.getFofkcode());
		saveFeekind.execute();
		
	}
}
