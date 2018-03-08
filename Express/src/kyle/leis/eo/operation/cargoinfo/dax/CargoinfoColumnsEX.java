package kyle.leis.eo.operation.cargoinfo.dax;

import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;

public class CargoinfoColumnsEX extends CargoinfoColumns {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setCicipieces(String ciCipieces) {
		this.setField(4, ciCipieces);
	}
	
	public void setCiciunitprice(String ciCiunitprice) {
		this.setField(5, ciCiunitprice);
	}
	
	public void setCicitotalprice(String ciCitotalprice) {
		this.setField(6, ciCitotalprice);
	}	
}
