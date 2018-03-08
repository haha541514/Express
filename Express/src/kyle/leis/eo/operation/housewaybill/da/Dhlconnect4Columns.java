package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class Dhlconnect4Columns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public Dhlconnect4Columns() {
		m_astrColumns = new String[110];
	}
	
	public Dhlconnect4Columns(String Shipmentid, 
            String Owner, String Trackid, 
            String Pickupno, String Saccount, 
            String Scompany, String Scontact, 
            String Saddress1, String Saddress2, 
            String Saddress3, String Scity, 
            String Sstate, String Szip, 
            String Scntrycd, String Scountry, 
            String Sphone, String Sphext, 
            String Sfaxtlx, String Sfaxtlxaddr, 
            String Semail, String Semailaddr, 
            String Ccontact, String Ccompany, 
            String Caddress1, String Caddress2, 
            String Caddress3, String Ccity, 
            String Cstate, String Czip, 
            String Ccntrycd, String Ccountry, 
            String Cphone, String Cphext, 
            String Cfaxtlx, String Cfaxtlxaddr, 
            String Cemail, String Cemailaddress, 
            String Pieces, String Dhlpackage, 
            String Wghtunit, String Weight, 
            String Dimunit, String Dimweight, 
            String Dutiable, String Declareval, 
            String Declarecurr, String Reference, 
            String Prdctcd, String Globalprdctcd, 
            String Productname, String Doorto, 
            String Contents, String Ein_ssn, 
            String Schbno, String Explicense, 
            String Explicexprydt, 
            String Implicense, String Cein_ssn, 
            String Payment_options, String Bill_account_no, 
            String Credittype, String Creditacct, 
            String Creditexp, String Creditappv, 
            String Insured, String Insureamt, 
            String Insurecurrency, String Ddtpayment, 
            String Ddtacctno,
            String Shipdate, String Preparedby, 
            String Awbno, String Dhlbillcd, 
            String Rated, String Shpchrg, 
            String Pckgchrg, String Chrgweight, 
            String Chargecurrency, String Courier, 
            String Destsvcarea, String Originsvcarea, 
            String Errorstate, String Prtdate, 
            String Reprintno, String Status, 
            String Termsoftrade, String Shipmentinuse, 
            String Inuseownername, String Usedsat, 
            String Usedddp, String Usedpdn, 
            String Useddpgm, String Usednft, 
            String Usedgenvas, String Scnpj_cpf, 
            String Sie_rg, String Ccnpj_cpf, 
            String Cie_rg, String Facility_id, 
            String Nextday_route_code, String Nextday_flag, 
            String Latest_pickup_cutoff, String Latest_booking_cutoff, 
            String Productsortcode, String Mailinglistgroupid, 
            String Esi_flag, String Mailinglistname, 
            String Ssuburb, String Csuburb, 
            String Mailingerrortext){
		m_astrColumns = new String[110];
		setShipmentid(Shipmentid);
		setOwner(Owner);
		setTrackid(Trackid);
		setPickupno(Pickupno);
		setSaccount(Saccount);
		setScompany(Scompany);
		setScontact(Scontact);
		setSaddress1(Saddress1);
		setSaddress2(Saddress2);
		setSaddress3(Saddress3);
		setScity(Scity);
		setSstate(Sstate);
		setSzip(Szip);
		setScntrycd(Scntrycd);
		setScountry(Scountry);
		setSphone(Sphone); 
		setSphext(Sphext);
		setSfaxtlx(Sfaxtlx);
		setSfaxtlxaddr(Sfaxtlxaddr);
		setSemail(Semail);
		setSemailaddr(Semailaddr);
		setCcontact(Ccontact);
		setCcompany(Ccompany);
		setCaddress1(Caddress1);
		setCaddress2(Caddress2);
		setCaddress3(Caddress3);
		setCcity(Ccity);
		setCstate(Cstate);
		setCzip(Czip);
		setCcntrycd(Ccntrycd);
		setCcountry(Ccountry);
		setCphone(Cphone);
		setCphext(Cphext);
		setCfaxtlx(Cfaxtlx);
		setCfaxtlxaddr(Cfaxtlxaddr);
		setCemail(Cemail);
		setCemailaddress(Cemailaddress);
		setPieces(Pieces);
		setDhlpackage(Dhlpackage);
		setWghtunit(Wghtunit);
		setWeight(Weight);
		setDimunit(Dimunit);
		setDimweight(Dimweight);
		setDutiable(Dutiable);
		setDeclareval(Declareval);
		setDeclarecurr(Declarecurr);
		setReference(Reference);
		setPrdctcd(Prdctcd);
		setGlobalprdctcd(Globalprdctcd);
		setProductname(Productname);
		setDoorto(Doorto);
		setContents(Contents);
		setEin_ssn(Ein_ssn);
		setSchbno(Schbno);
		setExplicense(Explicense);
		setExplicexprydt(Explicexprydt);
		setImplicense(Implicense);
		setCein_ssn(Cein_ssn);
		setPayment_options(Payment_options);
		setBill_account_no(Bill_account_no);
		setCredittype(Credittype);
		setCreditacct(Creditacct);
		setCreditexp(Creditexp);
		setCreditappv(Creditappv);
		setInsured(Insured);
		setInsureamt(Insureamt);
		setInsurecurrency(Insurecurrency);
		setDdtpayment(Ddtpayment);
		setDdtacctno(Ddtacctno);
		setShipdate(Shipdate);
		setPreparedby(Preparedby);
		setAwbno(Awbno);
		setDhlbillcd(Dhlbillcd);
		setRated(Rated);
		setShpchrg(Shpchrg);
		setPckgchrg(Pckgchrg);
		setChrgweight(Chrgweight);
		setChargecurrency(Chargecurrency);
		setCourier(Courier);
		setDestsvcarea(Destsvcarea);
		setOriginsvcarea(Originsvcarea);
		setErrorstate(Errorstate);
		setPrtdate(Prtdate);
		setReprintno(Reprintno);
		setStatus(Status);
		setTermsoftrade(Termsoftrade);
		setShipmentinuse(Shipmentinuse);
		setInuseownername(Inuseownername);
		setUsedsat(Usedsat);
		setUsedddp(Usedddp);
		setUsedpdn(Usedpdn);
		setUseddpgm(Useddpgm);
		setUsednft(Usednft);
		setUsedgenvas(Usedgenvas);
		setScnpj_cpf(Scnpj_cpf);
		setSie_rg(Sie_rg);
		setCcnpj_cpf(Ccnpj_cpf);
		setCie_rg(Cie_rg);
		setFacility_id(Facility_id);
		setNextday_route_code(Nextday_route_code);
		setNextday_flag(Nextday_flag);
		setLatest_pickup_cutoff(Latest_pickup_cutoff);
		setLatest_booking_cutoff(Latest_booking_cutoff);
		setProductsortcode(Productsortcode);
		setMailinglistgroupid(Mailinglistgroupid);
		setEsi_flag(Esi_flag);
		setMailinglistname(Mailinglistname);
		setSsuburb(Ssuburb);
		setCsuburb(Csuburb);
		setMailingerrortext(Mailingerrortext);
	}

	public void setShipmentid(String Shipmentid) {
		this.setField(0, Shipmentid);
	}

	public String getShipmentid() {
		return this.getField(0);
	}

	public void setOwner(String Owner) {
		this.setField(1, Owner);
	}

	public String getOwner() {
		return this.getField(1);
	}

	public void setTrackid(String Trackid) {
		this.setField(2, Trackid);
	}

	public String getTrackid() {
		return this.getField(2);
	}

	public void setPickupno(String Pickupno) {
		this.setField(3, Pickupno);
	}

	public String getPickupno() {
		return this.getField(3);
	}

	public void setSaccount(String Saccount) {
		this.setField(4, Saccount);
	}

	public String getSaccount() {
		return this.getField(4);
	}

	public void setScompany(String Scompany) {
		this.setField(5, Scompany);
	}

	public String getScompany() {
		return this.getField(5);
	}

	public void setScontact(String Scontact) {
		this.setField(6, Scontact);
	}

	public String getScontact() {
		return this.getField(6);
	}

	public void setSaddress1(String Saddress1) {
		this.setField(7, Saddress1);
	}

	public String getSaddress1() {
		return this.getField(7);
	}

	public void setSaddress2(String Saddress2) {
		this.setField(8, Saddress2);
	}

	public String getSaddress2() {
		return this.getField(8);
	}

	public void setSaddress3(String Saddress3) {
		this.setField(9, Saddress3);
	}

	public String getSaddress3() {
		return this.getField(9);
	}

	public void setScity(String Scity) {
		this.setField(10, Scity);
	}

	public String getScity() {
		return this.getField(10);
	}

	public void setSstate(String Sstate) {
		this.setField(11, Sstate);
	}

	public String getSstate() {
		return this.getField(11);
	}

	public void setSzip(String Szip) {
		this.setField(12, Szip);
	}

	public String getSzip() {
		return this.getField(12);
	}

	public void setScntrycd(String Scntrycd) {
		this.setField(13, Scntrycd);
	}

	public String getScntrycd() {
		return this.getField(13);
	}

	public void setScountry(String Scountry) {
		this.setField(14, Scountry);
	}

	public String getScountry() {
		return this.getField(14);
	}

	public void setSphone(String Sphone) {
		this.setField(15, Sphone);
	}

	public String getSphone() {
		return this.getField(15);
	}

	public void setSphext(String Sphext) {
		this.setField(16, Sphext);
	}

	public String getSphext() {
		return this.getField(16);
	}

	public void setSfaxtlx(String Sfaxtlx) {
		this.setField(17, Sfaxtlx);
	}

	public String getSfaxtlx() {
		return this.getField(17);
	}

	public void setSfaxtlxaddr(String Sfaxtlxaddr) {
		this.setField(18, Sfaxtlxaddr);
	}

	public String getSfaxtlxaddr() {
		return this.getField(18);
	}

	public void setSemail(String Semail) {
		this.setField(19, Semail);
	}

	public String getSemail() {
		return this.getField(19);
	}

	public void setSemailaddr(String Semailaddr) {
		this.setField(20, Semailaddr);
	}

	public String getSemailaddr() {
		return this.getField(20);
	}

	public void setCcontact(String Ccontact) {
		this.setField(21, Ccontact);
	}

	public String getCcontact() {
		return this.getField(21);
	}

	public void setCcompany(String Ccompany) {
		this.setField(22, Ccompany);
	}

	public String getCcompany() {
		return this.getField(22);
	}

	public void setCaddress1(String Caddress1) {
		this.setField(23, Caddress1);
	}

	public String getCaddress1() {
		return this.getField(23);
	}

	public void setCaddress2(String Caddress2) {
		this.setField(24, Caddress2);
	}

	public String getCaddress2() {
		return this.getField(24);
	}

	public void setCaddress3(String Caddress3) {
		this.setField(25, Caddress3);
	}

	public String getCaddress3() {
		return this.getField(25);
	}

	public void setCcity(String Ccity) {
		this.setField(26, Ccity);
	}

	public String getCcity() {
		return this.getField(26);
	}

	public void setCstate(String Cstate) {
		this.setField(27, Cstate);
	}

	public String getCstate() {
		return this.getField(27);
	}

	public void setCzip(String Czip) {
		this.setField(28, Czip);
	}

	public String getCzip() {
		return this.getField(28);
	}

	public void setCcntrycd(String Ccntrycd) {
		this.setField(29, Ccntrycd);
	}

	public String getCcntrycd() {
		return this.getField(29);
	}

	public void setCcountry(String Ccountry) {
		this.setField(30, Ccountry);
	}

	public String getCcountry() {
		return this.getField(30);
	}

	public void setCphone(String Cphone) {
		this.setField(31, Cphone);
	}

	public String getCphone() {
		return this.getField(31);
	}

	public void setCphext(String Cphext) {
		this.setField(32, Cphext);
	}

	public String getCphext() {
		return this.getField(32);
	}

	public void setCfaxtlx(String Cfaxtlx) {
		this.setField(33, Cfaxtlx);
	}

	public String getCfaxtlx() {
		return this.getField(33);
	}

	public void setCfaxtlxaddr(String Cfaxtlxaddr) {
		this.setField(34, Cfaxtlxaddr);
	}

	public String getCfaxtlxaddr() {
		return this.getField(34);
	}

	public void setCemail(String Cemail) {
		this.setField(35, Cemail);
	}

	public String getCemail() {
		return this.getField(35);
	}

	public void setCemailaddress(String Cemailaddress) {
		this.setField(36, Cemailaddress);
	}

	public String getCemailaddress() {
		return this.getField(36);
	}

	public void setPieces(String Pieces) {
		this.setField(37, Pieces);
	}

	public String getPieces() {
		return this.getField(37);
	}

	public void setDhlpackage(String Dhlpackage) {
		this.setField(38, Dhlpackage);
	}

	public String getDhlpackage() {
		return this.getField(38);
	}

	public void setWghtunit(String Wghtunit) {
		this.setField(39, Wghtunit);
	}

	public String getWghtunit() {
		return this.getField(39);
	}

	public void setWeight(String Weight) {
		this.setField(40, Weight);
	}

	public String getWeight() {
		return this.getField(40);
	}

	public void setDimunit(String Dimunit) {
		this.setField(41, Dimunit);
	}

	public String getDimunit() {
		return this.getField(41);
	}

	public void setDimweight(String Dimweight) {
		this.setField(42, Dimweight);
	}

	public String getDimweight() {
		return this.getField(42);
	}

	public void setDutiable(String Dutiable) {
		this.setField(43, Dutiable);
	}

	public String getDutiable() {
		return this.getField(43);
	}

	public void setDeclareval(String Declareval) {
		this.setField(44, Declareval);
	}

	public String getDeclareval() {
		return this.getField(44);
	}

	public void setDeclarecurr(String Declarecurr) {
		this.setField(45, Declarecurr);
	}

	public String getDeclarecurr() {
		return this.getField(45);
	}

	public void setReference(String Reference) {
		this.setField(46, Reference);
	}

	public String getReference() {
		return this.getField(46);
	}

	public void setPrdctcd(String Prdctcd) {
		this.setField(47, Prdctcd);
	}

	public String getPrdctcd() {
		return this.getField(47);
	}

	public void setGlobalprdctcd(String Globalprdctcd) {
		this.setField(48, Globalprdctcd);
	}

	public String getGlobalprdctcd() {
		return this.getField(48);
	}

	public void setProductname(String Productname) {
		this.setField(49, Productname);
	}

	public String getProductname() {
		return this.getField(49);
	}

	public void setDoorto(String Doorto) {
		this.setField(50, Doorto);
	}

	public String getDoorto() {
		return this.getField(50);
	}

	public void setContents(String Contents) {
		this.setField(51, Contents);
	}

	public String getContents() {
		return this.getField(51);
	}

	public void setEin_ssn(String Ein_ssn) {
		this.setField(52, Ein_ssn);
	}

	public String getEin_ssn() {
		return this.getField(52);
	}

	public void setSchbno(String Schbno) {
		this.setField(53, Schbno);
	}

	public String getSchbno() {
		return this.getField(53);
	}

	public void setExplicense(String Explicense) {
		this.setField(54, Explicense);
	}

	public String getExplicense() {
		return this.getField(54);
	}
	public void setExplicexprydt(String Explicexprydt) {
		this.setField(55, Explicexprydt);
	}

	public String getExplicexprydt() {
		return this.getField(55);
	}

	public void setImplicense(String Implicense) {
		this.setField(56, Implicense);
	}

	public String getImplicense() {
		return this.getField(56);
	}

	public void setCein_ssn(String Cein_ssn) {
		this.setField(57, Cein_ssn);
	}

	public String getCein_ssn() {
		return this.getField(57);
	}

	public void setPayment_options(String Payment_options) {
		this.setField(58, Payment_options);
	}

	public String getPayment_options() {
		return this.getField(58);
	}

	public void setBill_account_no(String Bill_account_no) {
		this.setField(59, Bill_account_no);
	}

	public String getBill_account_no() {
		return this.getField(59);
	}

	public void setCredittype(String Credittype) {
		this.setField(60, Credittype);
	}

	public String getCredittype() {
		return this.getField(60);
	}

	public void setCreditacct(String Creditacct) {
		this.setField(61, Creditacct);
	}

	public String getCreditacct() {
		return this.getField(61);
	}

	public void setCreditexp(String Creditexp) {
		this.setField(62, Creditexp);
	}

	public String getCreditexp() {
		return this.getField(62);
	}

	public void setCreditappv(String Creditappv) {
		this.setField(63, Creditappv);
	}

	public String getCreditappv() {
		return this.getField(63);
	}

	public void setInsured(String Insured) {
		this.setField(64, Insured);
	}

	public String getInsured() {
		return this.getField(64);
	}

	public void setInsureamt(String Insureamt) {
		this.setField(65, Insureamt);
	}

	public String getInsureamt() {
		return this.getField(65);
	}

	public void setInsurecurrency(String Insurecurrency) {
		this.setField(66, Insurecurrency);
	}

	public String getInsurecurrency() {
		return this.getField(66);
	}

	public void setDdtpayment(String Ddtpayment) {
		this.setField(67, Ddtpayment);
	}

	public String getDdtpayment() {
		return this.getField(67);
	}

	public void setDdtacctno(String Ddtacctno) {
		this.setField(68, Ddtacctno);
	}

	public String getDdtacctno() {
		return this.getField(68);
	}
	public void setShipdate(String Shipdate) {
		this.setField(69, Shipdate);
	}

	public String getShipdate() {
		return this.getField(69);
	}

	public void setPreparedby(String Preparedby) {
		this.setField(70, Preparedby);
	}

	public String getPreparedby() {
		return this.getField(70);
	}

	public void setAwbno(String Awbno) {
		this.setField(71, Awbno);
	}

	public String getAwbno() {
		return this.getField(71);
	}

	public void setDhlbillcd(String Dhlbillcd) {
		this.setField(72, Dhlbillcd);
	}

	public String getDhlbillcd() {
		return this.getField(72);
	}

	public void setRated(String Rated) {
		this.setField(73, Rated);
	}

	public String getRated() {
		return this.getField(73);
	}

	public void setShpchrg(String Shpchrg) {
		this.setField(74, Shpchrg);
	}

	public String getShpchrg() {
		return this.getField(74);
	}

	public void setPckgchrg(String Pckgchrg) {
		this.setField(75, Pckgchrg);
	}

	public String getPckgchrg() {
		return this.getField(75);
	}

	public void setChrgweight(String Chrgweight) {
		this.setField(76, Chrgweight);
	}

	public String getChrgweight() {
		return this.getField(76);
	}

	public void setChargecurrency(String Chargecurrency) {
		this.setField(77, Chargecurrency);
	}

	public String getChargecurrency() {
		return this.getField(77);
	}

	public void setCourier(String Courier) {
		this.setField(78, Courier);
	}

	public String getCourier() {
		return this.getField(78);
	}

	public void setDestsvcarea(String Destsvcarea) {
		this.setField(79, Destsvcarea);
	}

	public String getDestsvcarea() {
		return this.getField(79);
	}

	public void setOriginsvcarea(String Originsvcarea) {
		this.setField(80, Originsvcarea);
	}

	public String getOriginsvcarea() {
		return this.getField(80);
	}

	public void setErrorstate(String Errorstate) {
		this.setField(81, Errorstate);
	}

	public String getErrorstate() {
		return this.getField(81);
	}

	public void setPrtdate(String Prtdate) {
		this.setField(82, Prtdate);
	}

	public String getPrtdate() {
		return this.getField(82);
	}

	public void setReprintno(String Reprintno) {
		this.setField(83, Reprintno);
	}

	public String getReprintno() {
		return this.getField(83);
	}

	public void setStatus(String Status) {
		this.setField(84, Status);
	}

	public String getStatus() {
		return this.getField(84);
	}

	public void setTermsoftrade(String Termsoftrade) {
		this.setField(85, Termsoftrade);
	}

	public String getTermsoftrade() {
		return this.getField(85);
	}

	public void setShipmentinuse(String Shipmentinuse) {
		this.setField(86, Shipmentinuse);
	}

	public String getShipmentinuse() {
		return this.getField(86);
	}

	public void setInuseownername(String Inuseownername) {
		this.setField(87, Inuseownername);
	}

	public String getInuseownername() {
		return this.getField(87);
	}

	public void setUsedsat(String Usedsat) {
		this.setField(88, Usedsat);
	}

	public String getUsedsat() {
		return this.getField(88);
	}

	public void setUsedddp(String Usedddp) {
		this.setField(89, Usedddp);
	}

	public String getUsedddp() {
		return this.getField(89);
	}

	public void setUsedpdn(String Usedpdn) {
		this.setField(90, Usedpdn);
	}

	public String getUsedpdn() {
		return this.getField(90);
	}

	public void setUseddpgm(String Useddpgm) {
		this.setField(91, Useddpgm);
	}

	public String getUseddpgm() {
		return this.getField(91);
	}

	public void setUsednft(String Usednft) {
		this.setField(92, Usednft);
	}

	public String getUsednft() {
		return this.getField(92);
	}

	public void setUsedgenvas(String Usedgenvas) {
		this.setField(93, Usedgenvas);
	}

	public String getUsedgenvas() {
		return this.getField(93);
	}

	public void setScnpj_cpf(String Scnpj_cpf) {
		this.setField(94, Scnpj_cpf);
	}

	public String getScnpj_cpf() {
		return this.getField(94);
	}

	public void setSie_rg(String Sie_rg) {
		this.setField(95, Sie_rg);
	}

	public String getSie_rg() {
		return this.getField(95);
	}

	public void setCcnpj_cpf(String Ccnpj_cpf) {
		this.setField(96, Ccnpj_cpf);
	}

	public String getCcnpj_cpf() {
		return this.getField(96);
	}

	public void setCie_rg(String Cie_rg) {
		this.setField(97, Cie_rg);
	}

	public String getCie_rg() {
		return this.getField(97);
	}

	public void setFacility_id(String Facility_id) {
		this.setField(98, Facility_id);
	}

	public String getFacility_id() {
		return this.getField(98);
	}

	public void setNextday_route_code(String Nextday_route_code) {
		this.setField(99, Nextday_route_code);
	}

	public String getNextday_route_code() {
		return this.getField(99);
	}

	public void setNextday_flag(String Nextday_flag) {
		this.setField(100, Nextday_flag);
	}

	public String getNextday_flag() {
		return this.getField(100);
	}

	public void setLatest_pickup_cutoff(String Latest_pickup_cutoff) {
		this.setField(101, Latest_pickup_cutoff);
	}

	public String getLatest_pickup_cutoff() {
		return this.getField(101);
	}

	public void setLatest_booking_cutoff(String Latest_booking_cutoff) {
		this.setField(102, Latest_booking_cutoff);
	}

	public String getLatest_booking_cutoff() {
		return this.getField(102);
	}

	public void setProductsortcode(String Productsortcode) {
		this.setField(103, Productsortcode);
	}

	public String getProductsortcode() {
		return this.getField(103);
	}

	public void setMailinglistgroupid(String Mailinglistgroupid) {
		this.setField(104, Mailinglistgroupid);
	}

	public String getMailinglistgroupid() {
		return this.getField(104);
	}

	public void setEsi_flag(String Esi_flag) {
		this.setField(105, Esi_flag);
	}

	public String getEsi_flag() {
		return this.getField(105);
	}

	public void setMailinglistname(String Mailinglistname) {
		this.setField(106, Mailinglistname);
	}

	public String getMailinglistname() {
		return this.getField(106);
	}

	public void setSsuburb(String Ssuburb) {
		this.setField(107, Ssuburb);
	}

	public String getSsuburb() {
		return this.getField(107);
	}

	public void setCsuburb(String Csuburb) {
		this.setField(108, Csuburb);
	}

	public String getCsuburb() {
		return this.getField(108);
	}

	public void setMailingerrortext(String Mailingerrortext) {
		this.setField(109, Mailingerrortext);
	}

	public String getMailingerrortext() {
		return this.getField(109);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
