package kyle.leis.es.smsservice.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class SmsmessageColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SmsmessageColumns() {
		m_astrColumns = new String[11];
	}
	
	public SmsmessageColumns(Long smsSmsid, 
            String smsSmscontent, String smsSmsmobilenumber, 
            String smsSmsreceivecocode, Date smsSmscreatedate, 
            String smsSmsstatus, String csCocode, 
            String cscoConame, String cscoCoename, 
            String cscoCosname, String cscoCosename){
		m_astrColumns = new String[11];
		setSmssmsid(smsSmsid);
		setSmssmscontent(smsSmscontent);
		setSmssmsmobilenumber(smsSmsmobilenumber);
		setSmssmsreceivecocode(smsSmsreceivecocode);
		setSmssmscreatedate(smsSmscreatedate);
		setSmssmsstatus(smsSmsstatus);
		setCscocode(csCocode);
		setCscoconame(cscoConame);
		setCscocoename(cscoCoename);
		setCscocosname(cscoCosname);
		setCscocosename(cscoCosename);
	}

	public void setSmssmsid(Long smsSmsid) {
		this.setField(0, smsSmsid);
	}

	public String getSmssmsid() {
		return this.getField(0);
	}

	public void setSmssmscontent(String smsSmscontent) {
		this.setField(1, smsSmscontent);
	}

	public String getSmssmscontent() {
		return this.getField(1);
	}

	public void setSmssmsmobilenumber(String smsSmsmobilenumber) {
		this.setField(2, smsSmsmobilenumber);
	}

	public String getSmssmsmobilenumber() {
		return this.getField(2);
	}

	public void setSmssmsreceivecocode(String smsSmsreceivecocode) {
		this.setField(3, smsSmsreceivecocode);
	}

	public String getSmssmsreceivecocode() {
		return this.getField(3);
	}

	public void setSmssmscreatedate(Date smsSmscreatedate) {
		this.setField(4, smsSmscreatedate);
	}

	public String getSmssmscreatedate() {
		return this.getField(4);
	}

	public void setSmssmsstatus(String smsSmsstatus) {
		this.setField(5, smsSmsstatus);
	}

	public String getSmssmsstatus() {
		return this.getField(5);
	}

	public void setCscocode(String csCocode) {
		this.setField(6, csCocode);
	}

	public String getCscocode() {
		return this.getField(6);
	}

	public void setCscoconame(String cscoConame) {
		this.setField(7, cscoConame);
	}

	public String getCscoconame() {
		return this.getField(7);
	}

	public void setCscocoename(String cscoCoename) {
		this.setField(8, cscoCoename);
	}

	public String getCscocoename() {
		return this.getField(8);
	}

	public void setCscocosname(String cscoCosname) {
		this.setField(9, cscoCosname);
	}

	public String getCscocosname() {
		return this.getField(9);
	}

	public void setCscocosename(String cscoCosename) {
		this.setField(10, cscoCosename);
	}

	public String getCscocosename() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
