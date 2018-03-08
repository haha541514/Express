package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class Dhlconnect4Query extends JGeneralQuery {
	
	public Dhlconnect4Query(){
	    m_strSelectClause = "SELECT cw.cw_code as ShipmentId ,'SINGLEUSR' as Owner,'' as TrackId, '' as PickupNo,schn.chn_masteraccount as SAccount,hw.hw_shippercompany as SCompany,hw.hw_shippername as SContact,hw.hw_shipperaddress1 as SAddress1,hw.hw_shipperaddress2 as SAddress2,hw.hw_shipperaddress3 as SAddress3,odt.dt_ename as SCity,odt.dt_statename as SState,hw.hw_shipperpostcode as SZip,ocdt.dt_hubcode as SCntryCd,ocdt.dt_ename as SCountry,hw.hw_shippertelephone as SPhone,'' as SPhExt,'' as SFaxTlx,'' as SFaxTlxAddr,'' as SEmail,'' as SEmailAddr,hw.hw_consigneename as CContact,hw.hw_consigneecompany as CCompany,hw.hw_consigneeaddress1 as CAddress1,hw.hw_consigneeaddress2 as CAddress2,hw.hw_consigneeaddress3 as CAddress3,hw.hw_consigneecity as CCity,ddt.dt_statename as CState,hw.hw_consigneepostcode as CZip,cdt.dt_hubcode as CCntryCd,cdt.dt_ename as CCountry,hw.hw_consigneetelephone as CPhone,'' as CPhExt,'TRUE' as CFaxTlx,hw.hw_consigneefax as CFaxTlxAddr,'FALSE' as CEmail,'' as CEmailAddress,cw.cw_pieces as Pieces,'DS' as DHLPackage,'K' as WghtUnit,cw.cw_serverchargeweight as Weight,'C' as DimUnit,0 as DimWeight,1 as Dutiable,FUN_GET_TOTALCARGOINFO(cw.cw_code) as DeclareVal,'' as DeclareCurr,cw.cw_customerewbcode as Reference,'P' as PrdctCd,'P' as GlobalPrdctCd,decode(cw.ct_code,'AWPX','WPX','ADOX','DOX') as ProductName,'DD' as DoorTo,FUN_GET_CARGOINFO(cw.cw_code) as Contents,'' as EIN_SSN,'' as SchBno,'' as ExpLicense,to_char(hw.hw_recorddate,'yyyy-mm-dd') as ExplicExpryDt,'' as ImpLicense,'' as CEIN_SSN,'S' as Payment_Options,schn.chn_paymentaccount as Bill_Account_No,'' as CreditType,'' as CreditAcct,'' as CreditExp,'' as CreditAppv,'FALSE' as Insured,0.00 as InsureAmt,'CNY' as InsureCurrency,'R' as DDTPayment,'' as DDTAcctNo,to_char(hw.hw_recorddate,'yyyy-mm-dd') as ShipDate,HW_SHIPPERNAME as PreparedBy,'' as AwbNo,'DSA' as DHLBillCd,'TRUE' as Rated,'' as ShpChrg,'' as PckgChrg,'' as ChrgWeight,'' as ChargeCurrency,'Validate Destination' as Courier,ddt.dt_hubcode as DestSvcArea,odt.dt_hubcode as OriginSvcArea,'' as ErrorState,'' as PrtDate,0 as ReprintNo,4 as Status,'' as TermsOfTrade,'' as ShipmentInUse,'' as InUseOwnerName,'FALSE' as UsedSAT,'FALSE' as UsedDDP,'FALSE' as UsedPDN,'' as UsedDPGM,'FALSE' as UsedNFT,'FALSE' as UsedGenVAS,'' as SCNPJ_CPF,'' as SIE_RG,'' as CCNPJ_CPF,'' as CIE_RG,'' as Facility_Id,'' as NextDay_Route_Code,'FALSE' as NextDay_Flag,'' as Latest_Pickup_Cutoff,'' as Latest_Booking_Cutoff,48 as ProductSortCode,'' as MailingListGroupId,'FALSE' as ESI_Flag,'' as MailingListName,'' as SSuburb,'' as CSuburb,'' as MailingErrorText from T_OP_COREWAYBILL cw,T_OP_HOUSEWAYBILL hw,t_op_batchwaybill bw,T_DI_DISTRICT ddt,T_DI_DISTRICT cdt,T_DI_DISTRICT ocdt,T_DI_DISTRICT odt,t_chn_channel schn";
	    m_strWhereClause = "cw.cw_code = hw.cw_code and cw.bw_code_arrival = bw.bw_code and cw.dt_code_origin = odt.dt_code and odt.dt_countcode = ocdt.dt_code and cw.dt_code_destination=ddt.dt_code and cdt.dt_code = ddt.dt_countcode and cw.chn_code_supplier = schn.chn_code and cw.cws_code in ('SI','SO','IP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_code = '~~'", "cw.cw_customerewbcode in (~~)", "cw.cw_serverewbcode in (~~)", "bw.bw_labelcode = '~~'", "hw.hw_recorddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_recorddate", "cw.pk_code = '~~'", "hw.hw_op_id_record = ~~", "schn.ssg_code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new Dhlconnect4Columns();
	}
	
	public void setCwcode(String cwcode) {
		this.setField(0, cwcode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCwcustomerewbcode(String cwcustomerewbcode) {
		this.setField(1, cwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(1);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(2, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(2);
	}

	public void setBwlabelcode(String bwlabelcode) {
		this.setField(3, bwlabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(3);
	}

	public void setStartrecorddate(String StartRecordDate) {
		this.setField(4, StartRecordDate);
	}

	public String getStartrecorddate() {
		return this.getField(4);
	}

	public void setEndrecorddate(String EndRecordDate) {
		this.setField(5, EndRecordDate);
	}

	public String getEndrecorddate() {
		return this.getField(5);
	}

	public void setPkcode(String pkcode) {
		this.setField(6, pkcode);
	}

	public String getPkcode() {
		return this.getField(6);
	}

	public void setOpidrecord(String opidrecord) {
		this.setField(7, opidrecord);
	}

	public String getOpidrecord() {
		return this.getField(7);
	}

	public void setSsgcode(String ssgcode) {
		this.setField(8, ssgcode);
	}

	public String getSsgcode() {
		return this.getField(8);
	}

}
