package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import org.jdom.Element;

import kyle.common.conf.elementconfig.AElementConfig;
import kyle.common.util.xml.XMLExplorer;

public class ShipmentRequests extends AElementConfig {
	private ServiceHeader m_objServiceHeader;
	private Consignee m_objConsignee;
	private Shipper m_objShipper;
	private ShipmentPieces m_objShipmentPieces;
	private ShipmentContents m_objShipmentContents;
	
	public ShipmentRequests(String strSource) {
		Element objRootElement = XMLExplorer.getXMLRoot(strSource);
		parseSelf(objRootElement);
	}
	
	@Override
	protected void parse(Element objElement) {
		m_objServiceHeader = new ServiceHeader(objElement);
		m_objConsignee = new Consignee(objElement);
		m_objShipper = new Shipper(objElement);
		m_objShipmentPieces = new ShipmentPieces(objElement.getChild("ShipmentDetails").getChild("Pieces"), "Piece");
		m_objShipmentContents = new ShipmentContents(objElement.getChild("ShipmentDetails").getChild("Contents"), "Content");
	}

	@Override
	protected void check(StringBuffer sbCheck) {
		// TODO Auto-generated method stub
		
	}
	
	public ServiceHeader getServiceHeader() {
		return m_objServiceHeader;
	}
	
	public Consignee getConsignee() {
		return m_objConsignee;
	}
	
	public Shipper getShipper() {
		return m_objShipper;
	}	
	
	public ShipmentPieces getShipmentPieces() {
		return m_objShipmentPieces;
	}	
	
	public ShipmentContents getShipmentContents() {
		return m_objShipmentContents;
	}		
}
