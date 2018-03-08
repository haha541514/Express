package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ForinputallColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ForinputallColumns() {
		m_astrColumns = new String[115];
	}

	public ForinputallColumns(Long strBwcode, Date strAdddate,
            String strChncode, String strChnsname, String strChnsename,
			String strEecode, String strEesname, String strCocode,
			String strCosname, Long strCwcode,
			String strCwpostcodedestination, int strCwpieces,
			BigDecimal strCwgrossweight, BigDecimal strCwchargeweight,
			BigDecimal strCwtransferpieces, BigDecimal strCwtransfergrossweight,
			BigDecimal strCwtransferchargeweight, BigDecimal strCwserverchargeweight,
			String strCwcustomerewbcode, String strCwserverewbcode,
			String strCwchannelewbcode, String strCwewbcode,
			BigDecimal strCwopidcreator, Date strCwcreatedate, String strPmcode,
			String strPmname, String strCtcode, String strCtname,
			String strChncode_Cwspchn, String strChnsename_Cwspchn,
			String strChncode_Cwcuschn, String strChnsename_Cwcuschn,
			String strPk_code, String strPksname, String strPksename,
			String strDtcode, String strDthubcode, String strDtename,
			String strDtcode_Cwodt, String strDthubcode_Cwodt,
			String strDtename_Cwodt, String strCwscode, String strCwsname,
			Long strBwcode_Cwdbm, Long strBwcode_Cwabm,
			String strCocode_Cwcus, String strCosname_Cwcus,
			String strCocode_Cwsp, String strCosname_Cwsp,
			String strHwshipperaccount, String strHwshippername,
			String strHwshippercompany, String strHwshipperaddress1,
			String strHwshipperaddress2, String strHwshipperaddress3,
			String strHwshipperpostcode, String strHwshippertelephone,
			String strHwshipperfax, String strHwconsigneename,
			String strHwconsigneecompany, String strHwconsigneepostcode,
			String strHwconsigneeaddress1, String strHwconsigneeaddress2,
			String strHwconsigneeaddress3, String strHwconsigneetelephone,
			String strHwconsigneeaccount, String strHwconsigneefax,
			Date strHwsignindate, Long strHwopidsignin,
			Long strHwopidweighting, Date strHwsignoutdate,
			Long strHwopidsignout, Date strHwrecorddate,
			Long strHwopidrecord, Long strHwopidpacking,
			BigDecimal strHwinsurancevalue, String strHwinsurancesign,
			String strHwConsigneecity, String strDtcodeshipper,
			BigDecimal strTransfervolumeweight, Integer strVolumerate,
			Integer strTransfervolumerate, String strHwinsurancecurrency, 
			Integer iHwlabelprinttimes, String strCtename,
			String strCtsename, String strCwsename,
			String strSidtcode, String strSidthubcode,
			String serverwbckBckcode, String hwHwserverewbchangedsign, 
			String subwbckBckcode, String strHwLabelprintremark,
			Integer strHwCargoprinttimes, String strHwremark,
			String transactionid, String strBuyerid,
			BigDecimal customerchargeweight, String strHwConsigneeaddressex,
			String strHwConsigneenameex, String cwZnvname,
			String strHwelectricmark, String strHWSEWBChangedByWebSign,
			String strHwbookingid , String strHwConsigneecityex,
			String strChannelmasteraccount, String strPaymentaccount,
			String hwPat_code, String hwDt_code, 
			String hwCgk_code, String hwBk_code,
			String strHwDcustomssign, String strItIptcode, 
	        String strIfyIbcode,String hwDutypaidsign) {
		m_astrColumns = new String[115];
		setBwcode(strBwcode);
		setAdddate(strAdddate);
		setChncode(strChncode);
		setChnsname(strChnsname);
		setChnsename(strChnsename);
		setEecode(strEecode);
		setEesname(strEesname);
		setCocode(strCocode);
		setCosname(strCosname);
		setCwcode(strCwcode);
		setCwpostcodedestination(strCwpostcodedestination);
		setCwpieces(strCwpieces);
		setCwgrossweight(strCwgrossweight);
		setCwchargeweight(strCwchargeweight);
		setCwtransferpieces(strCwtransferpieces);
		setCwtransfergrossweight(strCwtransfergrossweight);
		setCwtransferchargeweight(strCwtransferchargeweight);
		setCwserverchargeweight(strCwserverchargeweight);
		setCwcustomerewbcode(strCwcustomerewbcode);
		setCwserverewbcode(strCwserverewbcode);
		setCwchannelewbcode(strCwchannelewbcode);
		setCwewbcode(strCwewbcode);
		setCwopidcreator(strCwopidcreator);
		setCwcreatedate(strCwcreatedate);
		setPmcode(strPmcode);
		setPmname(strPmname);
		setCtcode(strCtcode);
		setCtname(strCtname);
		setChncode_Cwspchn(strChncode_Cwspchn);
		setChnsename_Cwspchn(strChnsename_Cwspchn);
		setChncode_Cwcuschn(strChncode_Cwcuschn);
		setChnsename_Cwcuschn(strChnsename_Cwcuschn);
		setPk_code(strPk_code);
		setPksname(strPksname);
		setPksename(strPksename);
		setDtcode(strDtcode);
		setDthubcode(strDthubcode);
		setDtename(strDtename);
		setDtcode_Cwodt(strDtcode_Cwodt);
		setDthubcode_Cwodt(strDthubcode_Cwodt);
		setDtename_Cwodt(strDtename_Cwodt);
		setCwscode(strCwscode);
		setCwsname(strCwsname);
		setBwcode_Cwdbm(strBwcode_Cwdbm);
		setBwcode_Cwabm(strBwcode_Cwabm);
		setCocode_Cwcus(strCocode_Cwcus);
		setCosname_Cwcus(strCosname_Cwcus);
		setCocode_Cwsp(strCocode_Cwsp);
		setCosname_Cwsp(strCosname_Cwsp);
		setHwshipperaccount(strHwshipperaccount);
		setHwshippername(strHwshippername);
		setHwshippercompany(strHwshippercompany);
		setHwshipperaddress1(strHwshipperaddress1);
		setHwshipperaddress2(strHwshipperaddress2);
		setHwshipperaddress3(strHwshipperaddress3);
		setHwshipperpostcode(strHwshipperpostcode);
		setHwshippertelephone(strHwshippertelephone);
		setHwshipperfax(strHwshipperfax);
		setHwconsigneename(strHwconsigneename);
		setHwconsigneecompany(strHwconsigneecompany);
		setHwconsigneepostcode(strHwconsigneepostcode);
		setHwconsigneeaddress1(strHwconsigneeaddress1);
		setHwconsigneeaddress2(strHwconsigneeaddress2);
		setHwconsigneeaddress3(strHwconsigneeaddress3);
		setHwconsigneetelephone(strHwconsigneetelephone);
		setHwconsigneeaccount(strHwconsigneeaccount);
		setHwconsigneefax(strHwconsigneefax);
		setHwsignindate(strHwsignindate);
		setHwopidsignin(strHwopidsignin);
		setHwopidweighting(strHwopidweighting);
		setHwsignoutdate(strHwsignoutdate);
		setHwopidsignout(strHwopidsignout);
		setHwrecorddate(strHwrecorddate);
		setHwopidrecord(strHwopidrecord);
		setHwopidpacking(strHwopidpacking);
		setHwinsurancevalue(strHwinsurancevalue);
		setHwinsurancesign(strHwinsurancesign);
		setHwConsigneecity(strHwConsigneecity);
		setHwDtcodeshipper(strDtcodeshipper);
		setTransfervolumeweight(strTransfervolumeweight);
		setVolumerate(strVolumerate);
		setTransfervolumerate(strTransfervolumerate);
		setHwinsurancecurrency(strHwinsurancecurrency);
		setHwlabelprinttimes(iHwlabelprinttimes);
		setCtename(strCtename);
		setCtsename(strCtsename);
		setCwsename(strCwsename);
		this.setSidtcode(strSidtcode);
		this.setSidthubcode(strSidthubcode);
		setServerwbckbckcode(serverwbckBckcode);
		setHwhwserverewbchangedsign(hwHwserverewbchangedsign);
		// setHwhwcustomslabelprintsign(hwHwcustomslabelprintsign);
		setSubwbckbckcode(subwbckBckcode);
		setLabelprintremark(strHwLabelprintremark);
		setHwCargoprinttimes(strHwCargoprinttimes);
		setHwremark(strHwremark);
		setTransactionid(transactionid);
		setBuyerid(strBuyerid);
		setCwcustomerchargeweight(customerchargeweight);
		setHwconsigneeaddressex(strHwConsigneeaddressex);
		setHwconsigneenameex(strHwConsigneenameex);
		setCwznvname(cwZnvname);
		setHwelectricmark(strHwelectricmark);
		setHWSEWBChangedByWebSign(strHWSEWBChangedByWebSign);
		setHwbookingid(strHwbookingid);
		setHwconsigneecityex(strHwConsigneecityex);
		setMasteraccount(strChannelmasteraccount);
		setPaymentaccount(strPaymentaccount);
		setHwpat_code(hwPat_code);
		setHwdt_code(hwDt_code);
		setHwcgk_code(hwCgk_code);
		setHwbk_code(hwBk_code);			
		setHwhwDcustomssign(strHwDcustomssign);
		setItiptcode(strItIptcode);
		setIfyibcode(strIfyIbcode);
		setHwdutypaidsign(hwDutypaidsign);
	}

	public void setBwcode(Long strBwcode) {
		this.setField(0, strBwcode);
	}

	public String getBwcode() {
		return this.getField(0);
	}

	public void setAdddate(Date strAdddate) {
		this.setField(1, strAdddate);
	}

	public String getAdddate() {
		return this.getField(1);
	}

	public void setChncode(String strChncode) {
		this.setField(2, strChncode);
	}

	public String getChncode() {
		return this.getField(2);
	}

	public void setChnsname(String strChnsname) {
		this.setField(3, strChnsname);
	}

	public String getChnsname() {
		return this.getField(3);
	}

	public void setChnsename(String strChnsename) {
		this.setField(4, strChnsename);
	}

	public String getChnsename() {
		return this.getField(4);
	}

	public void setEecode(String strEecode) {
		this.setField(5, strEecode);
	}

	public String getEecode() {
		return this.getField(5);
	}

	public void setEesname(String strEesname) {
		this.setField(6, strEesname);
	}

	public String getEesname() {
		return this.getField(6);
	}

	public void setCocode(String strCocode) {
		this.setField(7, strCocode);
	}

	public String getCocode() {
		return this.getField(7);
	}

	public void setCosname(String strCosname) {
		this.setField(8, strCosname);
	}

	public String getCosname() {
		return this.getField(8);
	}

	public void setCwcode(Long strCwcode) {
		this.setField(9, strCwcode);
	}

	public String getCwcode() {
		return this.getField(9);
	}

	public void setCwpostcodedestination(String strCwpostcodedestination) {
		this.setField(10, strCwpostcodedestination);
	}

	public String getCwpostcodedestination() {
		return this.getField(10);
	}

	public void setCwpieces(int strCwpieces) {
		this.setField(11, strCwpieces);
	}

	public String getCwpieces() {
		return this.getField(11);
	}

	public void setCwgrossweight(BigDecimal strCwgrossweight) {
		this.setField(12, strCwgrossweight);
	}

	public String getCwgrossweight() {
		return this.getField(12);
	}

	public void setCwchargeweight(BigDecimal strCwchargeweight) {
		this.setField(13, strCwchargeweight);
	}

	public String getCwchargeweight() {
		return this.getField(13);
	}

	public void setCwtransferpieces(BigDecimal strCwtransferpieces) {
		this.setField(14, strCwtransferpieces);
	}

	public String getCwtransferpieces() {
		return this.getField(14);
	}

	public void setCwtransfergrossweight(BigDecimal strCwtransfergrossweight) {
		this.setField(15, strCwtransfergrossweight);
	}

	public String getCwtransfergrossweight() {
		return this.getField(15);
	}

	public void setCwtransferchargeweight(BigDecimal strCwtransferchargeweight) {
		this.setField(16, strCwtransferchargeweight);
	}

	public String getCwtransferchargeweight() {
		return this.getField(16);
	}

	public void setCwserverchargeweight(BigDecimal strCwserverchargeweight) {
		this.setField(17, strCwserverchargeweight);
	}

	public String getCwserverchargeweight() {
		return this.getField(17);
	}

	public void setCwcustomerewbcode(String strCwcustomerewbcode) {
		this.setField(18, strCwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(18);
	}

	public void setCwserverewbcode(String strCwserverewbcode) {
		this.setField(19, strCwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(19);
	}

	public void setCwchannelewbcode(String strCwchannelewbcode) {
		this.setField(20, strCwchannelewbcode);
	}

	public String getCwchannelewbcode() {
		return this.getField(20);
	}

	public void setCwewbcode(String strCwewbcode) {
		this.setField(21, strCwewbcode);
	}

	public String getCwewbcode() {
		return this.getField(21);
	}

	public void setCwopidcreator(BigDecimal strCwopidcreator) {
		this.setField(22, strCwopidcreator);
	}

	public String getCwopidcreator() {
		return this.getField(22);
	}

	public void setCwcreatedate(Date strCwcreatedate) {
		this.setField(23, strCwcreatedate);
	}

	public String getCwcreatedate() {
		return this.getField(23);
	}

	public void setPmcode(String strPmcode) {
		this.setField(24, strPmcode);
	}

	public String getPmcode() {
		return this.getField(24);
	}

	public void setPmname(String strPmname) {
		this.setField(25, strPmname);
	}

	public String getPmname() {
		return this.getField(25);
	}

	public void setCtcode(String strCtcode) {
		this.setField(26, strCtcode);
	}

	public String getCtcode() {
		return this.getField(26);
	}

	public void setCtname(String strCtname) {
		this.setField(27, strCtname);
	}

	public String getCtname() {
		return this.getField(27);
	}

	public void setChncode_Cwspchn(String strChncode_Cwspchn) {
		this.setField(28, strChncode_Cwspchn);
	}

	public String getChncode_Cwspchn() {
		return this.getField(28);
	}

	public void setChnsename_Cwspchn(String strChnsename_Cwspchn) {
		this.setField(29, strChnsename_Cwspchn);
	}

	public String getChnsename_Cwspchn() {
		return this.getField(29);
	}

	public void setChncode_Cwcuschn(String strChncode_Cwcuschn) {
		this.setField(30, strChncode_Cwcuschn);
	}

	public String getChncode_Cwcuschn() {
		return this.getField(30);
	}

	public void setChnsename_Cwcuschn(String strChnsename_Cwcuschn) {
		this.setField(31, strChnsename_Cwcuschn);
	}

	public String getChnsename_Cwcuschn() {
		return this.getField(31);
	}

	public void setPk_code(String strPk_code) {
		this.setField(32, strPk_code);
	}

	public String getPk_code() {
		return this.getField(32);
	}

	public void setPksname(String strPksname) {
		this.setField(33, strPksname);
	}

	public String getPksname() {
		return this.getField(33);
	}

	public void setPksename(String strPksename) {
		this.setField(34, strPksename);
	}

	public String getPksename() {
		return this.getField(34);
	}

	public void setDtcode(String strDtcode) {
		this.setField(35, strDtcode);
	}

	public String getDtcode() {
		return this.getField(35);
	}

	public void setDthubcode(String strDthubcode) {
		this.setField(36, strDthubcode);
	}

	public String getDthubcode() {
		return this.getField(36);
	}

	public void setDtename(String strDtename) {
		this.setField(37, strDtename);
	}

	public String getDtename() {
		return this.getField(37);
	}

	public void setDtcode_Cwodt(String strDtcode_Cwodt) {
		this.setField(38, strDtcode_Cwodt);
	}

	public String getDtcode_Cwodt() {
		return this.getField(38);
	}

	public void setDthubcode_Cwodt(String strDthubcode_Cwodt) {
		this.setField(39, strDthubcode_Cwodt);
	}

	public String getDthubcode_Cwodt() {
		return this.getField(39);
	}

	public void setDtename_Cwodt(String strDtename_Cwodt) {
		this.setField(40, strDtename_Cwodt);
	}

	public String getDtename_Cwodt() {
		return this.getField(40);
	}

	public void setCwscode(String strCwscode) {
		this.setField(41, strCwscode);
	}

	public String getCwscode() {
		return this.getField(41);
	}

	public void setCwsname(String strCwsname) {
		this.setField(42, strCwsname);
	}

	public String getCwsname() {
		return this.getField(42);
	}

	public void setBwcode_Cwdbm(Long strBwcode_Cwdbm) {
		this.setField(43, strBwcode_Cwdbm);
	}

	public String getBwcode_Cwdbm() {
		return this.getField(43);
	}

	public void setBwcode_Cwabm(Long strBwcode_Cwabm) {
		this.setField(44, strBwcode_Cwabm);
	}

	public String getBwcode_Cwabm() {
		return this.getField(44);
	}

	public void setCocode_Cwcus(String strCocode_Cwcus) {
		this.setField(45, strCocode_Cwcus);
	}

	public String getCocode_Cwcus() {
		return this.getField(45);
	}

	public void setCosname_Cwcus(String strCosname_Cwcus) {
		this.setField(46, strCosname_Cwcus);
	}

	public String getCosname_Cwcus() {
		return this.getField(46);
	}

	public void setCocode_Cwsp(String strCocode_Cwsp) {
		this.setField(47, strCocode_Cwsp);
	}

	public String getCocode_Cwsp() {
		return this.getField(47);
	}

	public void setCosname_Cwsp(String strCosname_Cwsp) {
		this.setField(48, strCosname_Cwsp);
	}

	public String getCosname_Cwsp() {
		return this.getField(48);
	}

	public void setHwshipperaccount(String strHwshipperaccount) {
		this.setField(49, strHwshipperaccount);
	}

	public String getHwshipperaccount() {
		return this.getField(49);
	}

	public void setHwshippername(String strHwshippername) {
		this.setField(50, strHwshippername);
	}

	public String getHwshippername() {
		return this.getField(50);
	}

	public void setHwshippercompany(String strHwshippercompany) {
		this.setField(51, strHwshippercompany);
	}

	public String getHwshippercompany() {
		return this.getField(51);
	}

	public void setHwshipperaddress1(String strHwshipperaddress1) {
		this.setField(52, strHwshipperaddress1);
	}

	public String getHwshipperaddress1() {
		return this.getField(52);
	}

	public void setHwshipperaddress2(String strHwshipperaddress2) {
		this.setField(53, strHwshipperaddress2);
	}

	public String getHwshipperaddress2() {
		return this.getField(53);
	}

	public void setHwshipperaddress3(String strHwshipperaddress3) {
		this.setField(54, strHwshipperaddress3);
	}

	public String getHwshipperaddress3() {
		return this.getField(54);
	}

	public void setHwshipperpostcode(String strHwshipperpostcode) {
		this.setField(55, strHwshipperpostcode);
	}

	public String getHwshipperpostcode() {
		return this.getField(55);
	}

	public void setHwshippertelephone(String strHwshippertelephone) {
		this.setField(56, strHwshippertelephone);
	}

	public String getHwshippertelephone() {
		return this.getField(56);
	}

	public void setHwshipperfax(String strHwshipperfax) {
		this.setField(57, strHwshipperfax);
	}

	public String getHwshipperfax() {
		return this.getField(57);
	}

	public void setHwconsigneename(String strHwconsigneename) {
		this.setField(58, strHwconsigneename);
	}

	public String getHwconsigneename() {
		return this.getField(58);
	}

	public void setHwconsigneecompany(String strHwconsigneecompany) {
		this.setField(59, strHwconsigneecompany);
	}

	public String getHwconsigneecompany() {
		return this.getField(59);
	}

	public void setHwconsigneepostcode(String strHwconsigneepostcode) {
		this.setField(60, strHwconsigneepostcode);
	}

	public String getHwconsigneepostcode() {
		return this.getField(60);
	}

	public void setHwconsigneeaddress1(String strHwconsigneeaddress1) {
		this.setField(61, strHwconsigneeaddress1);
	}

	public String getHwconsigneeaddress1() {
		return this.getField(61);
	}

	public void setHwconsigneeaddress2(String strHwconsigneeaddress2) {
		this.setField(62, strHwconsigneeaddress2);
	}

	public String getHwconsigneeaddress2() {
		return this.getField(62);
	}

	public void setHwconsigneeaddress3(String strHwconsigneeaddress3) {
		this.setField(63, strHwconsigneeaddress3);
	}

	public String getHwconsigneeaddress3() {
		return this.getField(63);
	}

	public void setHwconsigneetelephone(String strHwconsigneetelephone) {
		this.setField(64, strHwconsigneetelephone);
	}

	public String getHwconsigneetelephone() {
		return this.getField(64);
	}

	public void setHwconsigneeaccount(String strHwconsigneeaccount) {
		this.setField(65, strHwconsigneeaccount);
	}

	public String getHwconsigneeaccount() {
		return this.getField(65);
	}

	public void setHwconsigneefax(String strHwconsigneefax) {
		this.setField(66, strHwconsigneefax);
	}

	public String getHwconsigneefax() {
		return this.getField(66);
	}

	public void setHwsignindate(Date strHwsignindate) {
		this.setField(67, strHwsignindate);
	}

	public String getHwsignindate() {
		return this.getField(67);
	}

	public void setHwopidsignin(Long strHwopidsignin) {
		this.setField(68, strHwopidsignin);
	}

	public String getHwopidsignin() {
		return this.getField(68);
	}

	public void setHwopidweighting(Long strHwopidweighting) {
		this.setField(69, strHwopidweighting);
	}

	public String getHwopidweighting() {
		return this.getField(69);
	}

	public void setHwsignoutdate(Date strHwsignoutdate) {
		this.setField(70, strHwsignoutdate);
	}

	public String getHwsignoutdate() {
		return this.getField(70);
	}

	public void setHwopidsignout(Long strHwopidsignout) {
		this.setField(71, strHwopidsignout);
	}

	public String getHwopidsignout() {
		return this.getField(71);
	}

	public void setHwrecorddate(Date strHwrecorddate) {
		this.setField(72, strHwrecorddate);
	}

	public String getHwrecorddate() {
		return this.getField(72);
	}

	public void setHwopidrecord(Long strHwopidrecord) {
		this.setField(73, strHwopidrecord);
	}

	public String getHwopidrecord() {
		return this.getField(73);
	}

	public void setHwopidpacking(Long strHwopidpacking) {
		this.setField(74, strHwopidpacking);
	}

	public String getHwopidpacking() {
		return this.getField(74);
	}

	public void setHwinsurancevalue(BigDecimal strHwinsurancevalue) {
		this.setField(75, strHwinsurancevalue);
	}

	public String getHwinsurancevalue() {
		return this.getField(75);
	}

	public void setHwinsurancesign(String strHwinsurancesign) {
		this.setField(76, strHwinsurancesign);
	}

	public String getHwinsurancesign() {
		return this.getField(76);
	}

	public void setHwConsigneecity(String strHwConsigneecity) {
		this.setField(77, strHwConsigneecity);
	}

	public String getHwConsigneecity() {
		return this.getField(77);
	}	
	
	public void setHwDtcodeshipper(String strDtcodeshipper) {
		this.setField(78, strDtcodeshipper);
	}

	public String getHwDtcodeshipper() {
		return this.getField(78);
	}	
	
	public void setTransfervolumeweight(BigDecimal strTransfervolumeweight) {
		this.setField(79, strTransfervolumeweight);
	}

	public String getTransfervolumeweight() {
		return this.getField(79);
	}
	
	public void setVolumerate(Integer strVolumerate) {
		this.setField(80, strVolumerate);
	}

	public String getstrVolumerate() {
		return this.getField(80);
	}
	
	public void setTransfervolumerate(Integer strTransfervolumerate) {
		this.setField(81, strTransfervolumerate);
	}

	public String getTransfervolumerate() {
		return this.getField(81);
	}	
	
	public void setHwinsurancecurrency(String strHwinsurancecurrency) {
		this.setField(82, strHwinsurancecurrency);
	}

	public String getHwinsurancecurrency() {
		return this.getField(82);
	}	
	
	public void setHwlabelprinttimes(Integer strHwlabelprinttimes) {
		this.setField(83, strHwlabelprinttimes);
	}
	
	public String getHwlabelprinttimes() {
		return this.getField(83);
	}
	
	public String getCtename()
	{
		return this.getField(84);
	}
	
	public void setCtename(String strCtename)
	{
		this.setField(84,strCtename);
	}
	
	public String getCtsename()
	{
		return this.getField(85);
	}
	
	public void setCtsename(String strCtsename)
	{
		this.setField(85, strCtsename);
	}
	
	public String getCwsename()
	{
		return this.getField(86);
	}
	
	public void setCwsename(String strCwsename)
	{
		this.setField(86, strCwsename);
	}
	
	public String getSidtcode()
	{
		return this.getField(87);
	}
	
	public void setSidtcode(String strSidtcode)
	{
		this.setField(87, strSidtcode);
	}
	
	public String getSidthubcode()
	{
		return this.getField(88);
	}
	
	public void setSidthubcode(String strSidthubcode)
	{
		this.setField(88, strSidthubcode);
	}	
	
	public void setServerwbckbckcode(String serverwbckBckcode) {
		this.setField(89, serverwbckBckcode);
	}

	public String getServerwbckbckcode() {
		return this.getField(89);
	}

	public void setHwhwserverewbchangedsign(String hwHwserverewbchangedsign) {
		this.setField(90, hwHwserverewbchangedsign);
	}

	public String getHwhwserverewbchangedsign() {
		return this.getField(90);
	}
	
	public void setSubwbckbckcode(String subwbckBckcode) {
		this.setField(91, subwbckBckcode);
	}

	public String getSubwbckbckcode() {
		return this.getField(91);
	}	
	
	public void setLabelprintremark(String labelprintremark) {
		this.setField(92, labelprintremark);
	}

	public String getLabelprintremark() {
		return this.getField(92);
	}
	
	public void setHwCargoprinttimes(Integer hwCargoprinttimes) {
		this.setField(93, hwCargoprinttimes);
	}

	public String getHwCargoprinttimes() {
		return this.getField(93);
	}
	
	public void setHwremark(String hwremark) {
		this.setField(94, hwremark);
	}

	public String getHwremark() {
		return this.getField(94);
	}	
	
	public void setTransactionid(String transactionid) {
		this.setField(95, transactionid);
	}

	public String getTransactionid() {
		return this.getField(95);
	}		
	
	public void setBuyerid(String buyerid) {
		this.setField(96, buyerid);
	}

	public String getBuyerid() {
		return this.getField(96);
	}		
	
	public void setCwcustomerchargeweight(BigDecimal customerchargeweight) {
		this.setField(97, customerchargeweight);
	}

	public String getCwcustomerchargeweight() {
		return this.getField(97);
	}		
	
	public void setHwconsigneeaddressex(String hwConsigneeaddressex) {
		this.setField(98, hwConsigneeaddressex);
	}

	public String getHwconsigneeaddressex() {
		return this.getField(98);
	}		
	
	public void setHwconsigneenameex(String hwConsigneenameex) {
		this.setField(99, hwConsigneenameex);
	}

	public String getHwconsigneenameex() {
		return this.getField(99);
	}		
	
	public void setCwznvname(String cwZnvname) {
		this.setField(100, cwZnvname);
	}

	public String getCwznvname() {
		return this.getField(100);
	}	
	
	public void setHwelectricmark(String strHwelectricmar) {
		this.setField(101, strHwelectricmar);
	}

	public String getHwelectricmark() {
		return this.getField(101);
	}		
	
	public void setHWSEWBChangedByWebSign(String strHWSEWBChangedByWebSign) {
		this.setField(102, strHWSEWBChangedByWebSign);
	}

	public String getHWSEWBChangedByWebSign() {
		return this.getField(102);
	}	
	
	public void setHwbookingid(String strHwbookingid) {
		this.setField(103, strHwbookingid);
	}

	public String getHwbookingid() {
		return this.getField(103);
	}		
	
	public void setHwconsigneecityex(String strHwConsigneecityex){
		this.setField(104, strHwConsigneecityex);
	}
	
	public String getHwconsigneecityex(){
		return this.getField(104);
	}
	
	public void setMasteraccount(String strChnMasteraccount) {
		this.setField(105, strChnMasteraccount);
	}

	public String getMasteraccount() {
		return this.getField(105);
	}	
	
	public void setPaymentaccount(String strPaymentaccount) {
		this.setField(106, strPaymentaccount);
	}

	public String getPaymentaccount() {
		return this.getField(106);
	}	
	
	public void setHwpat_code(String hwPat_code) {
		this.setField(107, hwPat_code);
	}

	public String getHwpat_code() {
		return this.getField(107);
	}

	public void setHwdt_code(String hwDt_code) {
		this.setField(108, hwDt_code);
	}

	public String getHwdt_code() {
		return this.getField(108);
	}

	public void setHwcgk_code(String hwCgk_code) {
		this.setField(109, hwCgk_code);
	}

	public String getHwcgk_code() {
		return this.getField(109);
	}

	public void setHwbk_code(String hwBk_code) {
		this.setField(110, hwBk_code);
	}

	public String getHwbk_code() {
		return this.getField(110);
		
	}	
	
	public void setHwdutypaidsign(String Hwdutypaidsign) {
		this.setField(111, Hwdutypaidsign);
	}

	public String getHwdutypaidsign() {
		return this.getField(111);
	}		
	
	public void setHwhwDcustomssign(String hwHwDcustomssign) {
		this.setField(112, hwHwDcustomssign);
	}

	public String getHwhwDcustomssign() {
		return this.getField(112);
	}

	public void setItiptcode(String itIptcode) {
		this.setField(113, itIptcode);
	}

	public String getItiptcode() {
		return this.getField(113);
	}

	public void setIfyibcode(String IfyIbcode) {
		this.setField(114, IfyIbcode);
	}

	public String getIfyib_code() {
		return this.getField(114);
	}
	
	public String toString() {
		return "Code Generate By Kyle";
	}

}
