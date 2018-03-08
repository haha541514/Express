package wkq.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeekTest2 {

	public static void main(String[] args) throws ParseException {
		FeekTest2.test();
	}
	
	
	public  static void test() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strdate = "2008-08-08 12:10:12";
		Date date = sdf.parse(strdate);
		System.out.println(date);
	}
}
