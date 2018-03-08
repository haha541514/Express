package kyle.leis.eo.operation.predictwaybill.smerp.vo;

import java.util.List;


/**
 * 打印请求值
 * */
public class PrintRequest{
	private String token;//ERP生成授权码
	private List<DetailOrder> detail;// 平台订单号ID数组 接口得支持多个订单

	
	/************Set && Get****************/
	
	public List<DetailOrder> getDetail() {
		return detail;
	}

	public void setDetail(List<DetailOrder> detail) {
		this.detail = detail;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

}