package kyle.leis.fs.businesslog.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class BusinesslogColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BusinesslogColumns() {
		m_astrColumns = new String[10];
	}
	
	public BusinesslogColumns(Long blogBlid, 
            Date blogBlcreatedate, String blogBlbusinesscode, 
            String blogBlcontent, Long opOpid, 
            String opOpname, String blkBlkcode, 
            String blkBlkname, String akAkcode, 
            String akAkname){
		m_astrColumns = new String[10];
		setBlogblid(blogBlid);
		setBlogblcreatedate(blogBlcreatedate);
		setBlogblbusinesscode(blogBlbusinesscode);
		setBlogblcontent(blogBlcontent);
		setOpopid(opOpid);
		setOpopname(opOpname);
		setBlkblkcode(blkBlkcode);
		setBlkblkname(blkBlkname);
		setAkakcode(akAkcode);
		setAkakname(akAkname);
	}

	public void setBlogblid(Long blogBlid) {
		this.setField(0, blogBlid);
	}

	public String getBlogblid() {
		return this.getField(0);
	}

	public void setBlogblcreatedate(Date blogBlcreatedate) {
		this.setField(1, blogBlcreatedate);
	}

	public String getBlogblcreatedate() {
		return this.getField(1);
	}

	public void setBlogblbusinesscode(String blogBlbusinesscode) {
		this.setField(2, blogBlbusinesscode);
	}

	public String getBlogblbusinesscode() {
		return this.getField(2);
	}

	public void setBlogblcontent(String blogBlcontent) {
		this.setField(3, blogBlcontent);
	}

	public String getBlogblcontent() {
		return this.getField(3);
	}

	public void setOpopid(Long opOpid) {
		this.setField(4, opOpid);
	}

	public String getOpopid() {
		return this.getField(4);
	}

	public void setOpopname(String opOpname) {
		this.setField(5, opOpname);
	}

	public String getOpopname() {
		return this.getField(5);
	}

	public void setBlkblkcode(String blkBlkcode) {
		this.setField(6, blkBlkcode);
	}

	public String getBlkblkcode() {
		return this.getField(6);
	}

	public void setBlkblkname(String blkBlkname) {
		this.setField(7, blkBlkname);
	}

	public String getBlkblkname() {
		return this.getField(7);
	}

	public void setAkakcode(String akAkcode) {
		this.setField(8, akAkcode);
	}

	public String getAkakcode() {
		return this.getField(8);
	}

	public void setAkakname(String akAkname) {
		this.setField(9, akAkname);
	}

	public String getAkakname() {
		return this.getField(9);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
