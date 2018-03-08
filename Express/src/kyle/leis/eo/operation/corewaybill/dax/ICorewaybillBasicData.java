package kyle.leis.eo.operation.corewaybill.dax;

public interface ICorewaybillBasicData {
	public static final String COREWAYBILL_STATUS_SIGNIN = "SI";
	public static final String COREWAYBILL_STATUS_INPUT = "IP";
	public static final String COREWAYBILL_STATUS_SIGNOUT = "SO";
	public static final String COREWAYBILL_STATUS_PREALERT = "PR";
	
	public static final String EWBCODE_TYPE_SELF = "E";
	public static final String EWBCODE_TYPE_CUSTOMER = "C";
	public static final String EWBCODE_TYPE_SERVER = "S";
	public static final String EWBCODE_TYPE_CHANNEL = "H";
	public static final String EWBCODE_TYPE_SERVERLABELCODE = "SLC";
	
	public static final String CARGOTYPE_WPX = "AWPX";
	public static final String CARGOTYPE_DOX = "ADOX";
	
	
	public static final String SIGNOUT_MODE_DHLSTANDARD = "DHLS";
	public static final String SIGNOUT_MODE_DHLTHIRD = "DHLT";
	public static final String SIGNOUT_MODE_NORMAL = "NORM";
	public static final String SIGNOUT_MODE_DHLCOMMERCE = "DHLMY";
}
