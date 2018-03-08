package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.dbaccess.query.PageConfig;
import kyle.common.dbaccess.session.HSessionFactory;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.fs.dictionary.district.da.CountryColumns;
import kyle.leis.fs.dictionary.district.da.CountryQuery;
import kyle.leis.fs.dictionary.operator.da.OperatorColumns;
import kyle.leis.fs.dictionary.operator.da.OperatorQuery;
import kyle.leis.hi.TopBatchwaybill;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;

public class HibernateAppTest {
	
	public static void main(String[] args) {
	
		try {
		
			// testQuery();
			// Dept objDept = new Dept();		
			// testGetProperty(objDept);
			// testStokizer();
			// testPageQuery();
			testJPageQuery();
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static void testStokizer() {
		String strSource = "12345~&1221";
		String strdelim = "~&";
		
		String[] astr = strSource.split(strdelim);
		System.out.println(strdelim);
		if (astr != null && astr.length > 0) {
			for (int i = 0; i < astr.length; i++) {
				System.out.println(astr[i]);
			}
		}
		
		/*
		ArrayList<String> objALSource = new ArrayList<String>();
		if (strSource != null && strSource.length() > 0 && strSource.indexOf(strdelim) >= 0) {
			StringTokenizer objST = new StringTokenizer(strSource, strdelim, false);
			while (objST.hasMoreTokens()) {
				objALSource.add(objST.nextToken());
			}
		}*/	
	}
	
	public static void testTransaction() throws HibernateException {
		Session objSession = HSessionFactory.getSession();
		Transaction tx = objSession.beginTransaction();
		TopBatchwaybill objTBWB = new TopBatchwaybill();
		objSession.save(objTBWB);
		tx.commit();
	}
	
	
	public static void testGetProperty(Object object){
		
		Field[] objFields = object.getClass().getDeclaredFields();
		
		if (objFields != null && objFields.length > 0)
			for (int i = 0; i < objFields.length; i++){
				Field objField = objFields[i];
				System.out.println(objField.getName());
			}
		
	}
	
	public static void testPageQuery() throws Exception {
		OperatorQuery objOperatorQuery = new OperatorQuery();
		PageConfig objPageConfig = new PageConfig();
		objPageConfig.setCurrentPageNo(1);
		objOperatorQuery.setPageConfig(objPageConfig);
		List objList = objOperatorQuery.getResults();
		for (int i = 0; i < objList.size(); i++)
			System.out.println(((OperatorColumns)objList.get(i)).getOpname());
	}
	
	public static void testJPageQuery() throws Exception {
		CountryQuery objCountryQuery = new CountryQuery();
		PageConfig objPageConfig = new PageConfig();
		objPageConfig.setCurrentPageNo(2);
		objCountryQuery.setPageConfig(objPageConfig);
		
		List objList = objCountryQuery.getResults();
		for (int i = 0; i < objList.size(); i++)
			System.out.println(((CountryColumns)objList.get(i)).getDtdt_hubcode());
	}
	
	
	public static void testQuery() throws Exception {
		
		try {
			
			Session objSession = HSessionFactory.getSession();
			
			//String strSQL = " from Dept ";
			//List objList = objSession.find(strSQL);
			
			/* Criteria模式
			Criteria criteria = objSession.createCriteria(Dept.class);
			criteria.add(Expression.eq("dname", "SALES"));
			List objList = criteria.list();
			*/
			
			
			// String strSQL = "select a.dname,b.ename,b.job from Dept as a inner join a.emps as b where a.dname = 'SALES'";
			// String strSQL = "select test.CompoundDeptQ(s_test.nextval) from TepZone as a where a.znCode = 3";
			
			// 复合查询
			
			
			String strSQL = "select count(*) from TopCorewaybill as cw";
			Query query = objSession.createQuery(strSQL);
			List objList = query.list();
			
			
			//List objList = CargoInfoDemand.queryByCwCode("123");
			
			/*
			DeptQuery objDeptQuery = new DeptQuery();
			DeptCondition objDeptCondition = new DeptCondition();
			objDeptCondition.setDeptno("123");
			objDeptQuery.setCondition(objDeptCondition);
			List objList = objDeptQuery.getResults();
			*/
			if (objList != null && objList.size() > 0){
				
				Encoder objEncoder = new Encoder();
				objEncoder.addParameter(objList);
				System.out.println(objEncoder.toString());
				
				Decoder objDecoder = new Decoder(objEncoder.toString());
				Object object = objDecoder.getParameter(0, CompoundDeptQ.class);
				
				CompoundDeptQ objCDQ = (CompoundDeptQ)object;
				System.out.println(objCDQ.getDname());
				
				/*
				if (objList.get(0) instanceof Dept) {
					Dept objDept = (Dept)objList.get(0);
					System.out.println(objDept.getDeptno());
				}
				*/
				
				/*
				if (objList.get(0) instanceof CompoundDeptQ) {
					CompoundDeptQ objCompoundDeptQ = (CompoundDeptQ)objList.get(0);
					System.out.println(objCompoundDeptQ.getDname());
				}*/				
				
				/*
				for (int i = 0; i < objList.size(); i++){
					Object[] tttt = (Object[])objList.get(i);
					for (int j = 0; j < tttt.length; j++){
						if (tttt[j] instanceof String)
							System.out.println(((String)tttt[j]));
						
						
					}
				}
				*/
			}
			else {
				System.out.println("Not has any records");
			}
				
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
