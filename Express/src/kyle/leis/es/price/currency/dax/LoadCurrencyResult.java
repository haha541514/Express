package kyle.leis.es.price.currency.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.currency.da.CurrencyColumns;

public class LoadCurrencyResult {
	private CurrencyColumns m_objCurrencyColumns;
	private List m_listCurrencyValue;
	
	public CurrencyColumns getCurrencycolumns() {
		return m_objCurrencyColumns;
	}
	
	public void setCurrencycolumns(CurrencyColumns objCurrencyColumns) {
		m_objCurrencyColumns = objCurrencyColumns;
	}
	
	public List getCurrencyvalue() {
		return m_listCurrencyValue;
	}
	
	public void setCurrencyvalue(List listCurrencyValue) {
		m_listCurrencyValue = listCurrencyValue;
	}
	
	public String toString() {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(m_objCurrencyColumns);
		objEncode.addParameter(m_listCurrencyValue);
		return objEncode.toString();
	}
}
