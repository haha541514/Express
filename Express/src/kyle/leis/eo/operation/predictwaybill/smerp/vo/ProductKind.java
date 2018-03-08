package kyle.leis.eo.operation.predictwaybill.smerp.vo;

public class ProductKind {
	private String Code;
	private String Name;

	public ProductKind() {
	}

	public ProductKind(String code, String name) {
		Code = code;
		Name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
