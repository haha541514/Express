package kyle.leis.eo.operation.predictwaybill.smerp.vo;

import java.util.List;


/**
 * ��ӡ����ֵ
 * */
public class PrintRequest{
	private String token;//ERP������Ȩ��
	private List<DetailOrder> detail;// ƽ̨������ID���� �ӿڵ�֧�ֶ������

	
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