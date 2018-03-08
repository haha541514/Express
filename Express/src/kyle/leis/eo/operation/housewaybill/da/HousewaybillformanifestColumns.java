package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class HousewaybillformanifestColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public HousewaybillformanifestColumns() {
		m_astrColumns = new String[58];
	}
	
	public HousewaybillformanifestColumns(String Channelcode, 
            String Shippercompany, String Shipperattn, 
            String Referenceid, String Shipmentid, 
            String Receivercompany, String Receiverattn, 
            String Receiveradd1, String Receiveradd2, 
            String Receiveradd3, String Receivercity, 
            String Receivercitycode, String Receiverpostalcode, 
            String Receivercountrycode, String Receivercountryname, 
            String Receivercountrycname, String Receivermobile, 
            String Shipmnetpieces, String Shipmentweight, 
            String Localproductcode, 
            String Cargoinfo, String Cargoinfocname, 
            String Totalcargopcs, String Totalcargoinfo, 
            String Baglabelcode, String Shipperaccount, 
            String hwHw_shippername, String hwHw_shipperaddress1, 
            String hwHw_shipperaddress2, String hwHw_shipperaddress3, 
            String hwHw_shippertelephone, String pmPm_code, 
            String pmPm_name, String Dt_statecode, 
            String cwCw_grossweight, String cwCw_chargeweight, 
            String cwVolumeweight,String ccoCo_labelcode, 
            String ccoCo_sname, String hwHw_transactionid,  
            String Specialtyperemark,  String Pieceslwh,
            String Ddusign, String schnSsg_code,
            String schnChn_paymentaccount, String hwCw_code,
            String hwHw_labelprintremark, String shdtDt_hubcode, 
            String hwHw_shipperpostcode, String hwHw_insurancevalue,
            String cwCw_transfergrossweight, String strpkPk_sname,
            String strSi_opid, String strSi_opname,
            String strCwewbcode, String strHscode,
            String Containerid, String Dbwadddate){
		m_astrColumns = new String[58];
		setChannelcode(Channelcode);
		setShippercompany(Shippercompany);
		setShipperattn(Shipperattn);
		setReferenceid(Referenceid);
		setShipmentid(Shipmentid);
		setReceivercompany(Receivercompany);
		setReceiverattn(Receiverattn);
		setReceiveradd1(Receiveradd1);
		setReceiveradd2(Receiveradd2);
		setReceiveradd3(Receiveradd3);
		setReceivercity(Receivercity);
		setReceivercitycode(Receivercitycode);
		setReceiverpostalcode(Receiverpostalcode);
		setReceivercountrycode(Receivercountrycode);
		setReceivercountryname(Receivercountryname);
		setReceivercountrycname(Receivercountrycname);
		setReceivermobile(Receivermobile);
		setShipmnetpieces(Shipmnetpieces);
		setShipmentweight(Shipmentweight);
		setLocalproductcode(Localproductcode);
		setCargoinfo(Cargoinfo);
		setCargoinfocname(Cargoinfocname);
		setTotalcargopcs(Totalcargopcs);
		setTotalcargoinfo(Totalcargoinfo);
		setBaglabelcode(Baglabelcode);
		setShipperaccount(Shipperaccount);
		setHwhw_shippername(hwHw_shippername);
		setHwhw_shipperaddress1(hwHw_shipperaddress1);
		setHwhw_shipperaddress2(hwHw_shipperaddress2);
		setHwhw_shipperaddress3(hwHw_shipperaddress3);
		setHwhw_shippertelephone(hwHw_shippertelephone);
		setPmpm_code(pmPm_code);
		setPmpm_name(pmPm_name);
		setDt_statecode(Dt_statecode);
		setCwcw_grossweight(cwCw_grossweight);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCwvolumeweight(cwVolumeweight);
		setCcoco_labelcode(ccoCo_labelcode);
		setCcoco_sname(ccoCo_sname);
		setHwhw_transactionid(hwHw_transactionid);
		setSpecialtyperemark(Specialtyperemark);
		setPieceslwh(Pieceslwh);
		setDdusign(Ddusign);
		setSchnssg_code(schnSsg_code);	
		setSchnchn_paymentaccount(schnChn_paymentaccount);
		setHwcw_code(hwCw_code);		
		setHwhw_labelprintremark(hwHw_labelprintremark);
		setShdtdt_hubcode(shdtDt_hubcode);
		setHwhw_shipperpostcode(hwHw_shipperpostcode);
		setHwhw_insurancevalue(hwHw_insurancevalue);	
		setCwcw_transfergrossweight(cwCw_transfergrossweight);
		setPkpk_sname(strpkPk_sname);
		setCwewbcode(strCwewbcode);
		setContainerid(Containerid);
		setDbwadddate(Dbwadddate);
	}

	public void setChannelcode(String Channelcode) {
		this.setField(0, Channelcode);
	}

	public String getChannelcode() {
		return this.getField(0);
	}

	public void setShippercompany(String Shippercompany) {
		this.setField(1, Shippercompany);
	}

	public String getShippercompany() {
		return this.getField(1);
	}

	public void setShipperattn(String Shipperattn) {
		this.setField(2, Shipperattn);
	}

	public String getShipperattn() {
		return this.getField(2);
	}

	public void setReferenceid(String Referenceid) {
		this.setField(3, Referenceid);
	}

	public String getReferenceid() {
		return this.getField(3);
	}

	public void setShipmentid(String Shipmentid) {
		this.setField(4, Shipmentid);
	}

	public String getShipmentid() {
		return this.getField(4);
	}

	public void setReceivercompany(String Receivercompany) {
		this.setField(5, Receivercompany);
	}

	public String getReceivercompany() {
		return this.getField(5);
	}

	public void setReceiverattn(String Receiverattn) {
		this.setField(6, Receiverattn);
	}

	public String getReceiverattn() {
		return this.getField(6);
	}

	public void setReceiveradd1(String Receiveradd1) {
		this.setField(7, Receiveradd1);
	}

	public String getReceiveradd1() {
		return this.getField(7);
	}

	public void setReceiveradd2(String Receiveradd2) {
		this.setField(8, Receiveradd2);
	}

	public String getReceiveradd2() {
		return this.getField(8);
	}

	public void setReceiveradd3(String Receiveradd3) {
		this.setField(9, Receiveradd3);
	}

	public String getReceiveradd3() {
		return this.getField(9);
	}

	public void setReceivercity(String Receivercity) {
		this.setField(10, Receivercity);
	}

	public String getReceivercity() {
		return this.getField(10);
	}

	public void setReceivercitycode(String Receivercitycode) {
		this.setField(11, Receivercitycode);
	}

	public String getReceivercitycode() {
		return this.getField(11);
	}

	public void setReceiverpostalcode(String Receiverpostalcode) {
		this.setField(12, Receiverpostalcode);
	}

	public String getReceiverpostalcode() {
		return this.getField(12);
	}

	public void setReceivercountrycode(String Receivercountrycode) {
		this.setField(13, Receivercountrycode);
	}

	public String getReceivercountrycode() {
		return this.getField(13);
	}

	public void setReceivercountryname(String Receivercountryname) {
		this.setField(14, Receivercountryname);
	}

	public String getReceivercountryname() {
		return this.getField(14);
	}

	public void setReceivercountrycname(String Receivercountrycname) {
		this.setField(15, Receivercountrycname);
	}

	public String getReceivercountrycname() {
		return this.getField(15);
	}

	public void setReceivermobile(String Receivermobile) {
		this.setField(16, Receivermobile);
	}

	public String getReceivermobile() {
		return this.getField(16);
	}

	public void setShipmnetpieces(String Shipmnetpieces) {
		this.setField(17, Shipmnetpieces);
	}

	public String getShipmnetpieces() {
		return this.getField(17);
	}

	public void setShipmentweight(String Shipmentweight) {
		this.setField(18, Shipmentweight);
	}

	public String getShipmentweight() {
		return this.getField(18);
	}

	public void setLocalproductcode(String Localproductcode) {
		this.setField(19, Localproductcode);
	}

	public String getLocalproductcode() {
		return this.getField(19);
	}

	public void setCargoinfo(String Cargoinfo) {
		this.setField(20, Cargoinfo);
	}

	public String getCargoinfo() {
		return this.getField(20);
	}

	public void setCargoinfocname(String Cargoinfocname) {
		this.setField(21, Cargoinfocname);
	}

	public String getCargoinfocname() {
		return this.getField(21);
	}

	public void setTotalcargopcs(String Totalcargopcs) {
		this.setField(22, Totalcargopcs);
	}

	public String getTotalcargopcs() {
		return this.getField(22);
	}

	public void setTotalcargoinfo(String Totalcargoinfo) {
		this.setField(23, Totalcargoinfo);
	}

	public String getTotalcargoinfo() {
		return this.getField(23);
	}

	public void setBaglabelcode(String Baglabelcode) {
		this.setField(24, Baglabelcode);
	}

	public String getBaglabelcode() {
		return this.getField(24);
	}

	public void setShipperaccount(String Shipperaccount) {
		this.setField(25, Shipperaccount);
	}

	public String getShipperaccount() {
		return this.getField(25);
	}

	public void setHwhw_shippername(String hwHw_shippername) {
		this.setField(26, hwHw_shippername);
	}

	public String getHwhw_shippername() {
		return this.getField(26);
	}

	public void setHwhw_shipperaddress1(String hwHw_shipperaddress1) {
		this.setField(27, hwHw_shipperaddress1);
	}

	public String getHwhw_shipperaddress1() {
		return this.getField(27);
	}

	public void setHwhw_shipperaddress2(String hwHw_shipperaddress2) {
		this.setField(28, hwHw_shipperaddress2);
	}

	public String getHwhw_shipperaddress2() {
		return this.getField(28);
	}

	public void setHwhw_shipperaddress3(String hwHw_shipperaddress3) {
		this.setField(29, hwHw_shipperaddress3);
	}

	public String getHwhw_shipperaddress3() {
		return this.getField(29);
	}

	public void setHwhw_shippertelephone(String hwHw_shippertelephone) {
		this.setField(30, hwHw_shippertelephone);
	}

	public String getHwhw_shippertelephone() {
		return this.getField(30);
	}

	public void setPmpm_code(String pmPm_code) {
		this.setField(31, pmPm_code);
	}

	public String getPmpm_code() {
		return this.getField(31);
	}

	public void setPmpm_name(String pmPm_name) {
		this.setField(32, pmPm_name);
	}

	public String getPmpm_name() {
		return this.getField(32);
	}

	public void setDt_statecode(String Dt_statecode) {
		this.setField(33, Dt_statecode);
	}

	public String getDt_statecode() {
		return this.getField(33);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(34, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(34);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(35, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(35);
	}

	public void setCwvolumeweight(String cwVolumeweight) {
		this.setField(36, cwVolumeweight);
	}

	public String getCwvolumeweight() {
		return this.getField(36);
	}

	public void setCcoco_labelcode(String ccoCo_labelcode) {
		this.setField(37, ccoCo_labelcode);
	}

	public String getCcoco_labelcode() {
		return this.getField(37);
	}

	public void setCcoco_sname(String ccoCo_sname) {
		this.setField(38, ccoCo_sname);
	}

	public String getCcoco_sname() {
		return this.getField(38);
	}	
	
	public void setHwhw_transactionid(String hwHw_transactionid) {
		this.setField(39, hwHw_transactionid);
	}

	public String getHwhw_transactionid() {
		return this.getField(39);
	}	
	
	public void setSpecialtyperemark(String Specialtyperemark) {
		this.setField(40, Specialtyperemark);
	}

	public String getSpecialtyperemark() {
		return this.getField(40);
	}	
	
	public void setPieceslwh(String Pieceslwh) {
		this.setField(41, Pieceslwh);
	}

	public String getPieceslwh() {
		return this.getField(41);
	}
	
	public void setDdusign(String Ddusign) {
		this.setField(42, Ddusign);
	}

	public String getDdusign() {
		return this.getField(42);
	}

	public void setSchnssg_code(String schnSsg_code) {
		this.setField(43, schnSsg_code);
	}

	public String getSchnssg_code() {
		return this.getField(43);
	}	
	
	public void setSchnchn_paymentaccount(String schnChn_paymentaccount) {
		this.setField(44, schnChn_paymentaccount);
	}

	public String getSchnchn_paymentaccount() {
		return this.getField(44);
	}

	public void setHwcw_code(String hwCw_code) {
		this.setField(45, hwCw_code);
	}

	public String getHwcw_code() {
		return this.getField(45);
	}	
	
	public void setHwhw_labelprintremark(String hwHw_labelprintremark) {
		this.setField(46, hwHw_labelprintremark);
	}

	public String getHwhw_labelprintremark() {
		return this.getField(46);
	}	

	public void setShdtdt_hubcode(String shdtDt_hubcode) {
		this.setField(47, shdtDt_hubcode);
	}

	public String getShdtdt_hubcode() {
		return this.getField(47);
	}

	public void setHwhw_shipperpostcode(String hwHw_shipperpostcode) {
		this.setField(48, hwHw_shipperpostcode);
	}

	public String getHwhw_shipperpostcode() {
		return this.getField(48);
	}

	public void setHwhw_insurancevalue(String hwHw_insurancevalue) {
		this.setField(49, hwHw_insurancevalue);
	}

	public String getHwhw_insurancevalue() {
		return this.getField(49);
	}	
	
	public void setCwcw_transfergrossweight(String cwCw_transfergrossweight) {
		this.setField(50, cwCw_transfergrossweight);
	}

	public String getCwcw_transfergrossweight() {
		return this.getField(50);
	}	
	
	public void setPkpk_sname(String pkPk_sname) {
		this.setField(51, pkPk_sname);
	}

	public String getPkpk_sname() {
		return this.getField(51);
	}	
	
	public void setSiopid(String strSiopid) {
		this.setField(52, strSiopid);
	}

	public String getSiopid() {
		return this.getField(52);
	}	
	
	public void setSiopname(String strSiopname) {
		this.setField(53, strSiopname);
	}

	public String getSiopname() {
		return this.getField(53);
	}		
	
	public void setCwewbcode(String strCwewbcode) {
		this.setField(54, strCwewbcode);
	}

	public String getCwewbcode() {
		return this.getField(54);
	}		
	
	public void setHscode(String strHscode) {
		this.setField(55, strHscode);
	}

	public String getHscode() {
		return this.getField(55);
	}		
	
	public void setContainerid(String Containerid) {
		this.setField(56, Containerid);
	}

	public String getContainerid() {
		return this.getField(56);
	}	
	
	public void setDbwadddate(String Dbwadddate) {
		this.setField(57, Dbwadddate);
	}

	public String getDbwadddate() {
		return this.getField(57);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
