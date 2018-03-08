package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import java.util.ArrayList;
import java.util.List;

import kyle.common.conf.listconfig.ACompulsoryListConfig;

import org.jdom.Element;

public class ShipmentContents extends ACompulsoryListConfig {
	private List<ShipmentContent> m_listShipmentContent;

	public ShipmentContents(Element objFatherElement, String strChildName) {
		parseFatherContent(objFatherElement, strChildName);
	}

	@Override
	public ShipmentContent getConfig(int i) {
		// TODO Auto-generated method stub
		return m_listShipmentContent.get(i);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_listShipmentContent.size();
	}

	@Override
	protected void init() {
		m_listShipmentContent = new ArrayList<ShipmentContent>();

	}

	@Override
	protected void parse(Element objElement) {
		ShipmentContent objShipmentContent = new ShipmentContent(objElement);
		m_listShipmentContent.add(objShipmentContent);
	}

}
