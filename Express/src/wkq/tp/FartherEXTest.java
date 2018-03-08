package wkq.tp;

import wkq.test.FatherTest;

class FartherEXTest extends FatherTest{

	/**
	 * 
	 */
	FartherEXTest() {
		super();
		//不同包下的,父类的friendly元素访问不到，protcted倒是可以.
		super.fa = 30;
		
	}
	
}