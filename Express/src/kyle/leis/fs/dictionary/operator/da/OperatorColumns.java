package kyle.leis.fs.dictionary.operator.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class OperatorColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OperatorColumns() {
		m_astrColumns = new String[31];
	}
	
	public OperatorColumns(Long Opid, 
            String Opcode, String Opname, 
            String Opsex, String Sword, 
            String Opename, String Opsname, 
            String Opidnumber, String Opemail, 
            long Opidcreator, Date Opcreatedate, 
            long Opidmodifier, Date Opmodifydate, 
            String Opaddress, String Optelephone, 
            String Opmobile, Date Opconfirmdate, 
            String Eecode, String Eeesname, 
            String Eename, String Eeename, 
            String dtDtcode, String Cocode, 
            String Oscode, String Dpcode, 
            String Eestructurecode, String ekEekcode, 
            String Stcode, String Stname, 
            String Ctcode, String Ctname){
		m_astrColumns = new String[31];
		setOpid(Opid);
		setOpcode(Opcode);
		setOpname(Opname);
		setOpsex(Opsex);
		setSword(Sword);
		setOpename(Opename);
		setOpsname(Opsname);
		setOpidnumber(Opidnumber);
		setOpemail(Opemail);
		setOpidcreator(Opidcreator);
		setOpcreatedate(Opcreatedate);
		setOpidmodifier(Opidmodifier);
		setOpmodifydate(Opmodifydate);
		setOpaddress(Opaddress);
		setOptelephone(Optelephone);
		setOpmobile(Opmobile);
		setOpconfirmdate(Opconfirmdate);
		setEecode(Eecode);
		setEeesname(Eeesname);
		setEename(Eename);
		setEeename(Eeename);
		setDtdtcode(dtDtcode);
		setCocode(Cocode);
		setOscode(Oscode);
		setDpcode(Dpcode);
		setEestructurecode(Eestructurecode);
		setEekcode(ekEekcode);
		setStcode(Stcode);
		setStname(Stname);
		setCtcode(Ctcode);
		setCtname(Ctname);
	}

	public void setOpid(Long Opid) {
		this.setField(0, Opid);
	}

	public String getOpid() {
		return this.getField(0);
	}

	public void setOpcode(String Opcode) {
		this.setField(1, Opcode);
	}

	public String getOpcode() {
		return this.getField(1);
	}

	public void setOpname(String Opname) {
		this.setField(2, Opname);
	}

	public String getOpname() {
		return this.getField(2);
	}

	public void setOpsex(String Opsex) {
		this.setField(3, Opsex);
	}

	public String getOpsex() {
		return this.getField(3);
	}

	public void setSword(String Sword) {
		this.setField(4, Sword);
	}

	public String getSword() {
		return this.getField(4);
	}

	public void setOpename(String Opename) {
		this.setField(5, Opename);
	}

	public String getOpename() {
		return this.getField(5);
	}

	public void setOpsname(String Opsname) {
		this.setField(6, Opsname);
	}

	public String getOpsname() {
		return this.getField(6);
	}

	public void setOpidnumber(String Opidnumber) {
		this.setField(7, Opidnumber);
	}

	public String getOpidnumber() {
		return this.getField(7);
	}

	public void setOpemail(String Opemail) {
		this.setField(8, Opemail);
	}

	public String getOpemail() {
		return this.getField(8);
	}

	public void setOpidcreator(long Opidcreator) {
		this.setField(9, Opidcreator);
	}

	public String getOpidcreator() {
		return this.getField(9);
	}

	public void setOpcreatedate(Date Opcreatedate) {
		this.setField(10, Opcreatedate);
	}

	public String getOpcreatedate() {
		return this.getField(10);
	}

	public void setOpidmodifier(long Opidmodifier) {
		this.setField(11, Opidmodifier);
	}

	public String getOpidmodifier() {
		return this.getField(11);
	}

	public void setOpmodifydate(Date Opmodifydate) {
		this.setField(12, Opmodifydate);
	}

	public String getOpmodifydate() {
		return this.getField(12);
	}

	public void setOpaddress(String Opaddress) {
		this.setField(13, Opaddress);
	}

	public String getOpaddress() {
		return this.getField(13);
	}

	public void setOptelephone(String Optelephone) {
		this.setField(14, Optelephone);
	}

	public String getOptelephone() {
		return this.getField(14);
	}

	public void setOpmobile(String Opmobile) {
		this.setField(15, Opmobile);
	}

	public String getOpmobile() {
		return this.getField(15);
	}

	public void setOpconfirmdate(Date Opconfirmdate) {
		this.setField(16, Opconfirmdate);
	}

	public String getOpconfirmdate() {
		return this.getField(16);
	}

	public void setEecode(String Eecode) {
		this.setField(17, Eecode);
	}

	public String getEecode() {
		return this.getField(17);
	}

	public void setEeesname(String Eeesname) {
		this.setField(18, Eeesname);
	}

	public String getEeesname() {
		return this.getField(18);
	}

	public void setEename(String Eename) {
		this.setField(19, Eename);
	}

	public String getEename() {
		return this.getField(19);
	}

	public void setEeename(String Eeename) {
		this.setField(20, Eeename);
	}

	public String getEeename() {
		return this.getField(20);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(21, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(21);
	}

	public void setCocode(String Cocode) {
		this.setField(22, Cocode);
	}

	public String getCocode() {
		return this.getField(22);
	}

	public void setOscode(String Oscode) {
		this.setField(23, Oscode);
	}

	public String getOscode() {
		return this.getField(23);
	}

	public void setDpcode(String Dpcode) {
		this.setField(24, Dpcode);
	}

	public String getDpcode() {
		return this.getField(24);
	}

	public void setEestructurecode(String Eestructurecode) {
		this.setField(25, Eestructurecode);
	}

	public String getEestructurecode() {
		return this.getField(25);
	}

	public void setEekcode(String ekEekcode) {
		this.setField(26, ekEekcode);
	}

	public String getEekcode() {
		return this.getField(26);
	}

	public void setStcode(String Stcode) {
		this.setField(27, Stcode);
	}

	public String getStcode() {
		return this.getField(27);
	}

	public void setStname(String Stname) {
		this.setField(28, Stname);
	}

	public String getStname() {
		return this.getField(28);
	}

	public void setCtcode(String Ctcode) {
		this.setField(29, Ctcode);
	}

	public String getCtcode() {
		return this.getField(29);
	}

	public void setCtname(String Ctname) {
		this.setField(30, Ctname);
	}

	public String getCtname() {
		return this.getField(30);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
