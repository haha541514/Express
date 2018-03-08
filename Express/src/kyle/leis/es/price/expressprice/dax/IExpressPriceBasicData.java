package kyle.leis.es.price.expressprice.dax;

public interface IExpressPriceBasicData {
	// 状态相关
	public static final String PRICE_STATUS_NEW = "N";
	public static final String PRICE_STATUS_CONFIRM = "C";
	public static final String PRICE_STATUS_RELEASE = "R";
	public static final String PRICE_STATUS_ELIMINATE = "E";
	// 价格种类相关
	public static final String PRICEKIND_FREIGHT = "A01";
	public static final String PRICEKIND_INCIDENTAL = "A02";
	public static final String PRICEKIND_DISCOUNT = "A03";
	public static final String PRICEKIND_PRICEGROUP = "A04";
	public static final String PRICEKIND_CUSTOMER = "A05";
	public static final String PRICEKIND_ADJUSTIVE = "A06";
	public static final String PRICEKIND_CURRENCY = "A07";
	
	public static final String PRICEDOMAIN_SALES = "A01";
	public static final String PRICEDOMAIN_COSTS = "A02";
	public static final String PRICEDOMAIN_MIDDLE = "A03";
}
