package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SmsnoticekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SmsnoticekindColumns() {
		m_astrColumns = new String[3];
	}
	
	public SmsnoticekindColumns(String snkSnkcode, 
            String snkSnkname, String snkSnkename){
		m_astrColumns = new String[3];
		setSnksnkcode(snkSnkcode);
		setSnksnkname(snkSnkname);
		setSnksnkename(snkSnkename);
	}

	public void setSnksnkcode(String snkSnkcode) {
		this.setField(0, snkSnkcode);
	}

	public String getSnksnkcode() {
		return this.getField(0);
	}

	public void setSnksnkname(String snkSnkname) {
		this.setField(1, snkSnkname);
	}

	public String getSnksnkname() {
		return this.getField(1);
	}

	public void setSnksnkename(String snkSnkename) {
		this.setField(2, snkSnkename);
	}

	public String getSnksnkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
