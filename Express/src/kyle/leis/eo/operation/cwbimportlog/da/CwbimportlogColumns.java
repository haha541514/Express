package kyle.leis.eo.operation.cwbimportlog.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CwbimportlogColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CwbimportlogColumns() {
		m_astrColumns = new String[9];
	}
	
	public CwbimportlogColumns(Long tocCwlid, 
            Long toctdioperatorOpid, Date tocCwlcreatedate, 
            int tocCwltotalrecords, int tocCwlunsuccessfulrecords, 
            String tocCwlfilepath, String tocCwlremark, String tocCwlstatus, Long potId){
		m_astrColumns = new String[9];
		setToccwlid(tocCwlid);
		setToctdioperatoropid(toctdioperatorOpid);
		setToccwlcreatedate(tocCwlcreatedate);
		setToccwltotalrecords(tocCwltotalrecords);
		setToccwlunsuccessfulrecords(tocCwlunsuccessfulrecords);
		setToccwlfilepath(tocCwlfilepath);
		setToccwlremark(tocCwlremark);
		setToccwlStatus(tocCwlstatus);
		setTocpotid(potId);
	}

	public void setToccwlid(Long tocCwlid) {
		this.setField(0, tocCwlid);
	}

	public String getToccwlid() {
		return this.getField(0);
	}

	public void setToctdioperatoropid(Long toctdioperatorOpid) {
		this.setField(1, toctdioperatorOpid);
	}

	public String getToctdioperatoropid() {
		return this.getField(1);
	}

	public void setToccwlcreatedate(Date tocCwlcreatedate) {
		this.setField(2, tocCwlcreatedate);
	}

	public String getToccwlcreatedate() {
		return this.getField(2);
	}

	public void setToccwltotalrecords(int tocCwltotalrecords) {
		this.setField(3, tocCwltotalrecords);
	}

	public String getToccwltotalrecords() {
		return this.getField(3);
	}

	public void setToccwlunsuccessfulrecords(int tocCwlunsuccessfulrecords) {
		this.setField(4, tocCwlunsuccessfulrecords);
	}

	public String getToccwlunsuccessfulrecords() {
		return this.getField(4);
	}

	public void setToccwlfilepath(String tocCwlfilepath) {
		this.setField(5, tocCwlfilepath);
	}

	public String getToccwlfilepath() {
		return this.getField(5);
	}

	public void setToccwlremark(String tocCwlremark) {
		this.setField(6, tocCwlremark);
	}

	public String getToccwlremark() {
		return this.getField(6);
	}

	public void setToccwlStatus(String tocCwlstatus){
		this.setField(7, tocCwlstatus);
	}
	
	public String getToccwlstatus(){
		return this.getField(7);
	}
	
	public void setTocpotid(Long potId){
		this.setField(8, potId);
	}
	
	public String getTocpotid(){
		return this.getField(8);
	}
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
