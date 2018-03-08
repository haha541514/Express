package kyle.leis.es.smsservice.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SmsreceiveruleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SmsreceiveruleColumns() {
		m_astrColumns = new String[20];
	}
	
	public SmsreceiveruleColumns(String smsrcomp_idSnkcode, 
            Long srOpid, String srOpcode, 
            String srOpename, String srOpsname, 
            String srOpname, String snkSnkname, 
            String snkSnkename, String snttSnttcode, 
            String snttSnttname, String snttSnttename, 
            String snttSnttstarttime, String snttSnttendtime, 
            String snttSnttrestrictsign, String mnkMnkcode, 
            String mnkMnkname, String sreeEesname, 
            String srOpmobile, String srcoCocode, 
            String srcoConame){
		m_astrColumns = new String[20];
		setSmsrcomp_idsnkcode(smsrcomp_idSnkcode);
		setSropid(srOpid);
		setSropcode(srOpcode);
		setSropename(srOpename);
		setSropsname(srOpsname);
		setSropname(srOpname);
		setSnksnkname(snkSnkname);
		setSnksnkename(snkSnkename);
		setSnttsnttcode(snttSnttcode);
		setSnttsnttname(snttSnttname);
		setSnttsnttename(snttSnttename);
		setSnttsnttstarttime(snttSnttstarttime);
		setSnttsnttendtime(snttSnttendtime);
		setSnttsnttrestrictsign(snttSnttrestrictsign);
		setMnkmnkcode(mnkMnkcode);
		setMnkmnkname(mnkMnkname);
		setSreeeesname(sreeEesname);
		setSropmobile(srOpmobile);
		setSrcococode(srcoCocode);
		setSrcoconame(srcoConame);
	}

	public void setSmsrcomp_idsnkcode(String smsrcomp_idSnkcode) {
		this.setField(0, smsrcomp_idSnkcode);
	}

	public String getSmsrcomp_idsnkcode() {
		return this.getField(0);
	}

	public void setSropid(Long srOpid) {
		this.setField(1, srOpid);
	}

	public String getSropid() {
		return this.getField(1);
	}

	public void setSropcode(String srOpcode) {
		this.setField(2, srOpcode);
	}

	public String getSropcode() {
		return this.getField(2);
	}

	public void setSropename(String srOpename) {
		this.setField(3, srOpename);
	}

	public String getSropename() {
		return this.getField(3);
	}

	public void setSropsname(String srOpsname) {
		this.setField(4, srOpsname);
	}

	public String getSropsname() {
		return this.getField(4);
	}

	public void setSropname(String srOpname) {
		this.setField(5, srOpname);
	}

	public String getSropname() {
		return this.getField(5);
	}

	public void setSnksnkname(String snkSnkname) {
		this.setField(6, snkSnkname);
	}

	public String getSnksnkname() {
		return this.getField(6);
	}

	public void setSnksnkename(String snkSnkename) {
		this.setField(7, snkSnkename);
	}

	public String getSnksnkename() {
		return this.getField(7);
	}

	public void setSnttsnttcode(String snttSnttcode) {
		this.setField(8, snttSnttcode);
	}

	public String getSnttsnttcode() {
		return this.getField(8);
	}

	public void setSnttsnttname(String snttSnttname) {
		this.setField(9, snttSnttname);
	}

	public String getSnttsnttname() {
		return this.getField(9);
	}

	public void setSnttsnttename(String snttSnttename) {
		this.setField(10, snttSnttename);
	}

	public String getSnttsnttename() {
		return this.getField(10);
	}

	public void setSnttsnttstarttime(String snttSnttstarttime) {
		this.setField(11, snttSnttstarttime);
	}

	public String getSnttsnttstarttime() {
		return this.getField(11);
	}

	public void setSnttsnttendtime(String snttSnttendtime) {
		this.setField(12, snttSnttendtime);
	}

	public String getSnttsnttendtime() {
		return this.getField(12);
	}

	public void setSnttsnttrestrictsign(String snttSnttrestrictsign) {
		this.setField(13, snttSnttrestrictsign);
	}

	public String getSnttsnttrestrictsign() {
		return this.getField(13);
	}

	public void setMnkmnkcode(String mnkMnkcode) {
		this.setField(14, mnkMnkcode);
	}

	public String getMnkmnkcode() {
		return this.getField(14);
	}

	public void setMnkmnkname(String mnkMnkname) {
		this.setField(15, mnkMnkname);
	}

	public String getMnkmnkname() {
		return this.getField(15);
	}

	public void setSreeeesname(String sreeEesname) {
		this.setField(16, sreeEesname);
	}

	public String getSreeeesname() {
		return this.getField(16);
	}

	public void setSropmobile(String srOpmobile) {
		this.setField(17, srOpmobile);
	}

	public String getSropmobile() {
		return this.getField(17);
	}

	public void setSrcococode(String srcoCocode) {
		this.setField(18, srcoCocode);
	}

	public String getSrcococode() {
		return this.getField(18);
	}

	public void setSrcoconame(String srcoConame) {
		this.setField(19, srcoConame);
	}

	public String getSrcoconame() {
		return this.getField(19);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
