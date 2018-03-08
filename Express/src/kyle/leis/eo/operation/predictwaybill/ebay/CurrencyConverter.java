package kyle.leis.eo.operation.predictwaybill.ebay;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.CurrencyValue;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.ShippingServiceCost;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.Subtotal;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.Total;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.TransactionPrice;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CurrencyConverter implements Converter {

	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		CurrencyValue currencyValue = (CurrencyValue) source;
		writer.addAttribute("currencyID", currencyValue.getCurrency());
        writer.setValue(String.valueOf(currencyValue.getValue()));
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		String nodeName = reader.getNodeName();
		CurrencyValue currencyValue = null;
		if ("ShippingServiceCost".equals(nodeName)) {
			currencyValue = new ShippingServiceCost();
		} else if ("Subtotal".equals(nodeName)) {
			currencyValue = new Subtotal();
		} else if ("Total".equals(nodeName)) {
			currencyValue = new Total();
		} else if ("TransactionPrice".equals(nodeName)) {
			currencyValue = new TransactionPrice();
		}
		if (currencyValue != null) {
			currencyValue.setCurrency(reader.getAttribute("currencyID"));
			currencyValue.setValue(Double.valueOf(reader.getValue()));
		}
		return currencyValue;
	}

	public boolean canConvert(Class type) {
		return type.equals(ShippingServiceCost.class)
			|| type.equals(Subtotal.class)
			|| type.equals(Total.class)
			|| type.equals(TransactionPrice.class);
	}

}
