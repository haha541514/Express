package kyle.leis.eo.billing.calculate.feecalculate.dax;

public interface IFeeCalculateBasicData {
	// 错误代码
	public static final String SEARCH_PRICE_ERROR = "ERROR";
	// 费用类型
	public static final String FEEKIND_FREIGHT = "A01";
	public static final String FEEKIND_DETAIL_FREIGHT = "A0101";
	public static final String FEEKIND_SURCHARGE_OIL = "A0102";
	public static final String FEEKIND_HKFREIGHT = "A1003";
	public static final String FEEKIND_INCIDENTAL = "A10";
	public static final String FEEKIND_DISCOUNT = "A20";
	public static final String FEEKIND_CURRENCY = "A30";
	
	// 付费方式
	public static final String PAYMENTMODE_FREE = "AFR";
	public static final String PAYMENTMODE_PREPAID = "APP";
	public static final String PAYMENTMODE_COLLECT = "ACC";
	
	// 价格状态
	public static final String PRICESTATUS_RELEASE = "R";
	
	// 重量类别
	public static final String FVT_FIRSTWEIGHT = "FW";
	public static final String FVT_UNITWEIGHT = "UW";
	public static final String FVT_CONTINUEWEIGHT = "CW";
	public static final String FVT_INCREASED_CONTINUEWEIGHT = "ICW";
	// 单位类型
	public static final String UNITTYPE_KG = "KG";
	public static final String UNITTYPE_G = "G";
	public static final String UNITTYPE_GW = "GW";
	public static final String UNITTYPE_BILL = "BL";
	public static final String UNITTYPE_PIECE = "PCE";
	public static final String UNITTYPE_PERCENTAGE = "PCT";
	
	// 计费种类
	public static final String BILLINGKIND_RECEIVABLE = "A01";
	public static final String BILLINGKIND_RECEIVABLE_CW = "A0101";
	public static final String BILLINGKIND_PAYABLE = "A02";
	public static final String BILLINGKIND_PAYABLE_SW = "A0201";
	public static final String BILLINGKIND_PAYABLE_CW = "A0202";
	public static final String BILLINGKIND_PAYABLE_TW = "A0203";
	
	// 默认的组织机构
	public static final String DEFAULT_ENTERPRISE = "1";
}
