package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import org.jdom.Element;

import kyle.common.conf.elementconfig.AElementConfig;

public class ShipmentPiece extends AElementConfig {
	
	private String Weight;
	private String Length;
	private String Width;
	private String Height;	
	
	
	public ShipmentPiece(Element objElement) {
		parseSelf(objElement);
	}
	
	@Override
	protected void check(StringBuffer sbCheck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void parse(Element objElement) {
		Weight = objElement.getChildText("Weight");
		Length = objElement.getChildText("Length");
		Width = objElement.getChildText("Width");
		Height = objElement.getChildText("Height");
	}

	public String getWeight() {
		return Weight;
	}

	public String getLength() {
		return Length;
	}

	public String getWidth() {
		return Width;
	}

	public String getHeight() {
		return Height;
	}
}
