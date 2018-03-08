package kyle.leis.fs.waybillcode.dax;

public interface IWaybillcodeBasicData {
	public final static String WBC_STATUS_USED = "US";
	public final static String WBC_STATUS_REGISTER = "RG";
	public final static String WBC_STATUS_ELIMINATE = "EL";
	
	public final static String BCK_DHLCHILD = "DHLCC";
	public final static String BCK_DHLMASTER = "DHLMC";
	public final static String BCK_DHLFROMWEB_MASTER = "DHLFWMC";
	public final static String BCK_USPSMASTER = "USPSMC";
	public final static String BAC_DHLSOAPFROMWEB_MASTER = "DHLSOAPFWMC";
	public final static String BAC_DHLSG_MASTER = "DHLSGMC";
	public final static String BCK_SBDAUEMSMC = "SBDAUEMSMC";
	public final static String BCK_MYDHLMASTER = "MYDHLMC";
	
	public final static String BCK_DHLCHILD_E = "DHLCC_E";
	public final static String BCK_DHLMASTER_E = "DHLMC_E";
	
	public final static String BCK_CUSTOMSINCREMENT = "CMSIM";
	public final static String BCK_HONGKONGPACKAGE = "HKPK";
	public final static String BCK_DHLGLOBEMASTER = "DHLMG";
	public final static String BCK_FRANCEEMSMASTER = "FREMSMG";
	public final static String BCK_DHLDGM = "DGM";
	public final static String BCK_GROUPHUEMS = "EMSHU";
	public final static String BCK_DHLCNSHIP = "DHLCNSHIP";
	public final static String BAC_USPSSOAP = "USPSSOAP";
	public final static String BCK_TNTWEBSHIP = "TNTWEBSHIP";
	public final static String BAC_HKEMSIMPORT = "HKEMSIMPORT";
	public final static String BCK_TNTICONSHIP = "TNTICONSHIP";
	public final static String BCK_FEDEXWEBSHIP = "FEDEXWEBSHIP";
	public final static String BCK_EPARCEL = "EPARCEL";
	public final static String BCK_MYDHLWEBSHIP = "MYDHLWEBSHIP";
	public final static String BCK_UPSWEBSHIP = "UPSWEBSHIP";
	public final static String BAC_UPSSOAP = "UPSSOAP";
	public final static String BCK_EMSEUB = "EMSEUB";
	public final static String BCK_UPSCANADAWEB = "UPSCAWEB";
	
	public final static String COMPLEX_PREFIX_DGM = "DGMComplexPrefix";
	public final static String COMPLEX_PREFIX_HUEMS = "HUEMSComplexPrefix";
	
	public final static int MINLENGTH = 1;
	public final static int MAXLENGTH = 30;
	public final static int MAX_DHL_COREWAYBILLCODE_LENGTH = 10;
}
