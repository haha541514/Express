package wkq.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class SonTest extends FatherTest{
	int a = 10;
	int b = 20;
	int c = 30;
	public  SonTest(){
		 this.a = 11;
		 this.c = 30;
		 super.fa = 30;
	}

	public int test01(){
		
		return 2*a+b;
	}
	public int funstu(){
		return a+f;
	}
	
	
}
class MySort implements Comparator{
	
	/**
	*@data 2016-10-24 by wukq
	*@return return 0;
	*@version 1.0
	**/
	public int compare(Object o1, Object o2) {
		FatherTest obj1 = (FatherTest) o1;
		FatherTest obj2 = (FatherTest) o2;
		if(obj1.a+obj1.b > obj2.a+obj2.b){
			return 1;
		}
		
		return 0;
	}
}

 class Studenttest{
	
	public static void main(String[] args) {
		function02();
	}
	static List<FatherTest> objlist = null;
	static {
	    objlist = new ArrayList<FatherTest>();
		objlist.add(new FatherTest(1,2));
		objlist.add(new FatherTest(11,22));
		objlist.add(new FatherTest(33,44));
		objlist.add(new FatherTest(3,2));
		objlist.add(new FatherTest(1,2));
		
	}
	public static void function01(){	
		FatherTest obj = new  SonTest();//ȷʵ�Ǹ���Ķ��󣬵��õĶ��Ǹ���ķ���
		System.out.println(obj.a);
		System.out.println(obj.b);
		System.out.println(obj.a + obj.b);//û��C,���ò��������C������ʵ������ʱ��a�� b�����¸�ֵ��,6
		System.out.println(obj.test02());//6������ķ���
		System.out.println(obj.test01());//42, a = 22,b = 20,��������ķ�����a��bҲ������ģ��ڸ����޹ء�
		//obj.funstu();//��������ķ�������
	}
	public static void function02(){	
		Comparator comp =  new MySort();
		comp.compare(objlist, comp);
		
	}
	
	
	
	
}