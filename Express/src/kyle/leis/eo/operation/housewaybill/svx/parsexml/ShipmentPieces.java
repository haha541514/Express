package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import kyle.common.conf.listconfig.ACompulsoryListConfig;

public class ShipmentPieces extends ACompulsoryListConfig {
	private List<ShipmentPiece> m_listShipmentPiece;

	public ShipmentPieces(Element objFatherElement, String strChildName) {
		parseFatherContent(objFatherElement, strChildName);
	}

	@Override
	public ShipmentPiece getConfig(int i) {
		// TODO Auto-generated method stub
		return m_listShipmentPiece.get(i);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_listShipmentPiece.size();
	}

	@Override
	protected void init() {
		m_listShipmentPiece = new ArrayList<ShipmentPiece>();

	}

	@Override
	protected void parse(Element objElement) {
		ShipmentPiece objShipmentPiece = new ShipmentPiece(objElement);
		m_listShipmentPiece.add(objShipmentPiece);
	}

}
