package kyle.fetcher.track.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillforfetcherColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillforfetcherColumns() {
		m_astrColumns = new String[10];
	}
	
	public WaybillforfetcherColumns(String cwCw_code, 
            String cwCw_serverewbcode, String cwCw_channelewbcode, 
            String cwChn_code_supplier, String wbbtWbts_code, 
            String wbbtWpa_code, String bwAdd_date, 
            String cwCo_code_supplier, String ssgSsg_code,
            String strCdt_ename){
		m_astrColumns = new String[10];
		setCwcw_code(cwCw_code);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCwcw_channelewbcode(cwCw_channelewbcode);
		setCwchn_code_supplier(cwChn_code_supplier);
		setWbbtwbts_code(wbbtWbts_code);
		setWbbtwpa_code(wbbtWpa_code);
		setBwadd_date(bwAdd_date);
		setCwco_code_supplier(cwCo_code_supplier);
		setSsgssg_code(ssgSsg_code);
		setCdt_ename(strCdt_ename);
		//setLatestsystrack(strLatestSYSTrack);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(1, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(1);
	}

	public void setCwcw_channelewbcode(String cwCw_channelewbcode) {
		this.setField(2, cwCw_channelewbcode);
	}

	public String getCwcw_channelewbcode() {
		return this.getField(2);
	}

	public void setCwchn_code_supplier(String cwChn_code_supplier) {
		this.setField(3, cwChn_code_supplier);
	}

	public String getCwchn_code_supplier() {
		return this.getField(3);
	}

	public void setWbbtwbts_code(String wbbtWbts_code) {
		this.setField(4, wbbtWbts_code);
	}

	public String getWbbtwbts_code() {
		return this.getField(4);
	}

	public void setWbbtwpa_code(String wbbtWpa_code) {
		this.setField(5, wbbtWpa_code);
	}

	public String getWbbtwpa_code() {
		return this.getField(5);
	}

	public void setBwadd_date(String bwAdd_date) {
		this.setField(6, bwAdd_date);
	}

	public String getBwadd_date() {
		return this.getField(6);
	}

	public void setCwco_code_supplier(String cwCo_code_supplier) {
		this.setField(7, cwCo_code_supplier);
	}

	public String getCwco_code_supplier() {
		return this.getField(7);
	}

	public void setSsgssg_code(String ssgSsg_code) {
		this.setField(8, ssgSsg_code);
	}

	public String getSsgssg_code() {
		return this.getField(8);
	}

	public void setCdt_ename(String cdt_Ename) {
		this.setField(9, cdt_Ename);
	}

	public String getCdt_ename() {
		return this.getField(9);
	}	
	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
