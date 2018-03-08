package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class HousewaybillfortarazzColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public HousewaybillfortarazzColumns() {
		m_astrColumns = new String[23];
	}
	
	public HousewaybillfortarazzColumns(String Channelcode, 
            String Referenceid, String Shipmentid, 
            String Receiverattn, String Receivercompany, 
            String Receiveradd1, String Receiveradd2, 
            String Receiveradd3, String Receiveradd4, 
            String Receivercity, String Receivercitycode, 
            String Receiverpostalcode, String Receivermobile, 
            String Receiveremail, String Hscode, 
            String Cargoinfo, String Volumeweight, 
            String Length, String Width, 
            String Height, String Shipmentweight, 
            String Unitcargoinfo, String Totalcargoinfo){
		m_astrColumns = new String[23];
		setChannelcode(Channelcode);
		setReferenceid(Referenceid);
		setShipmentid(Shipmentid);
		setReceiverattn(Receiverattn);
		setReceivercompany(Receivercompany);
		setReceiveradd1(Receiveradd1);
		setReceiveradd2(Receiveradd2);
		setReceiveradd3(Receiveradd3);
		setReceiveradd4(Receiveradd4);
		setReceivercity(Receivercity);
		setReceivercitycode(Receivercitycode);
		setReceiverpostalcode(Receiverpostalcode);
		setReceivermobile(Receivermobile);
		setReceiveremail(Receiveremail);
		setHscode(Hscode);
		setCargoinfo(Cargoinfo);
		setVolumeweight(Volumeweight);
		setLength(Length);
		setWidth(Width);
		setHeight(Height);
		setShipmentweight(Shipmentweight);
		setUnitcargoinfo(Unitcargoinfo);
		setTotalcargoinfo(Totalcargoinfo);
	}

	public void setChannelcode(String Channelcode) {
		this.setField(0, Channelcode);
	}

	public String getChannelcode() {
		return this.getField(0);
	}

	public void setReferenceid(String Referenceid) {
		this.setField(1, Referenceid);
	}

	public String getReferenceid() {
		return this.getField(1);
	}

	public void setShipmentid(String Shipmentid) {
		this.setField(2, Shipmentid);
	}

	public String getShipmentid() {
		return this.getField(2);
	}

	public void setReceiverattn(String Receiverattn) {
		this.setField(3, Receiverattn);
	}

	public String getReceiverattn() {
		return this.getField(3);
	}

	public void setReceivercompany(String Receivercompany) {
		this.setField(4, Receivercompany);
	}

	public String getReceivercompany() {
		return this.getField(4);
	}

	public void setReceiveradd1(String Receiveradd1) {
		this.setField(5, Receiveradd1);
	}

	public String getReceiveradd1() {
		return this.getField(5);
	}

	public void setReceiveradd2(String Receiveradd2) {
		this.setField(6, Receiveradd2);
	}

	public String getReceiveradd2() {
		return this.getField(6);
	}

	public void setReceiveradd3(String Receiveradd3) {
		this.setField(7, Receiveradd3);
	}

	public String getReceiveradd3() {
		return this.getField(7);
	}

	public void setReceiveradd4(String Receiveradd4) {
		this.setField(8, Receiveradd4);
	}

	public String getReceiveradd4() {
		return this.getField(8);
	}

	public void setReceivercity(String Receivercity) {
		this.setField(9, Receivercity);
	}

	public String getReceivercity() {
		return this.getField(9);
	}

	public void setReceivercitycode(String Receivercitycode) {
		this.setField(10, Receivercitycode);
	}

	public String getReceivercitycode() {
		return this.getField(10);
	}

	public void setReceiverpostalcode(String Receiverpostalcode) {
		this.setField(11, Receiverpostalcode);
	}

	public String getReceiverpostalcode() {
		return this.getField(11);
	}

	public void setReceivermobile(String Receivermobile) {
		this.setField(12, Receivermobile);
	}

	public String getReceivermobile() {
		return this.getField(12);
	}

	public void setReceiveremail(String Receiveremail) {
		this.setField(13, Receiveremail);
	}

	public String getReceiveremail() {
		return this.getField(13);
	}

	public void setHscode(String Hscode) {
		this.setField(14, Hscode);
	}

	public String getHscode() {
		return this.getField(14);
	}

	public void setCargoinfo(String Cargoinfo) {
		this.setField(15, Cargoinfo);
	}

	public String getCargoinfo() {
		return this.getField(15);
	}

	public void setVolumeweight(String Volumeweight) {
		this.setField(16, Volumeweight);
	}

	public String getVolumeweight() {
		return this.getField(16);
	}

	public void setLength(String Length) {
		this.setField(17, Length);
	}

	public String getLength() {
		return this.getField(17);
	}

	public void setWidth(String Width) {
		this.setField(18, Width);
	}

	public String getWidth() {
		return this.getField(18);
	}

	public void setHeight(String Height) {
		this.setField(19, Height);
	}

	public String getHeight() {
		return this.getField(19);
	}

	public void setShipmentweight(String Shipmentweight) {
		this.setField(20, Shipmentweight);
	}

	public String getShipmentweight() {
		return this.getField(20);
	}

	public void setUnitcargoinfo(String Unitcargoinfo) {
		this.setField(21, Unitcargoinfo);
	}

	public String getUnitcargoinfo() {
		return this.getField(21);
	}

	public void setTotalcargoinfo(String Totalcargoinfo) {
		this.setField(22, Totalcargoinfo);
	}

	public String getTotalcargoinfo() {
		return this.getField(22);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
