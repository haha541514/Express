package wkq.test;

import Decoder.BASE64Encoder;

public  class FatherTest{
	
	int a = 2;
	int b = 3;
	int f = 4;

	public static void main(String[] args) {
		
	}
	
	static{
		byte objbyte = 123 ;
		String str = Byte.toString(objbyte);
		byte objm = 'm';
		BASE64Encoder enc=new BASE64Encoder();
		//String str2 = Byte.toString(objm);//objm109
		//String str3 = 
		//System.out.println(str2);
		//System.out.println(str+str2);
	}
	
	protected int fa = 4;
	public  FatherTest(int a,int b){
		this.a = a;
		this.b = b;
		
	}
	protected FatherTest(){
		
	}
	public int test01(){
		return a*b;
	}
	public int test02(){
		return a+ b;
	}
	
	
}