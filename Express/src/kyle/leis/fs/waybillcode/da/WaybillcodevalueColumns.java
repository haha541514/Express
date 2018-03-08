package kyle.leis.fs.waybillcode.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillcodevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillcodevalueColumns() {
		
	}
	
	public WaybillcodevalueColumns(Long bcvcomp_idBcvid, 
            Long bcvcomp_idBcid, String bcvBcvlabelcode, 
            String bcvBcvprefix, String bcvBcvsuffix, 
            String bckBckcode, String bckBckname, 
            String bckBckgroupcode, String bckBckbuildvaluesign, 
            String bcsCscode, String bcsCsname){
		m_astrColumns = new String[11];
		setBcvcomp_idbcvid(bcvcomp_idBcvid);
		setBcvcomp_idbcid(bcvcomp_idBcid);
		setBcvbcvlabelcode(bcvBcvlabelcode);
		setBcvbcvprefix(bcvBcvprefix);
		setBcvbcvsuffix(bcvBcvsuffix);
		setBckbckcode(bckBckcode);
		setBckbckname(bckBckname);
		setBckbckgroupcode(bckBckgroupcode);
		setBckbckbuildvaluesign(bckBckbuildvaluesign);
		setBcscscode(bcsCscode);
		setBcscsname(bcsCsname);
	}

	public void setBcvcomp_idbcvid(Long bcvcomp_idBcvid) {
		this.setField(0, bcvcomp_idBcvid);
	}

	public String getBcvcomp_idbcvid() {
		return this.getField(0);
	}

	public void setBcvcomp_idbcid(Long bcvcomp_idBcid) {
		this.setField(1, bcvcomp_idBcid);
	}

	public String getBcvcomp_idbcid() {
		return this.getField(1);
	}

	public void setBcvbcvlabelcode(String bcvBcvlabelcode) {
		this.setField(2, bcvBcvlabelcode);
	}

	public String getBcvbcvlabelcode() {
		return this.getField(2);
	}

	public void setBcvbcvprefix(String bcvBcvprefix) {
		this.setField(3, bcvBcvprefix);
	}

	public String getBcvbcvprefix() {
		return this.getField(3);
	}

	public void setBcvbcvsuffix(String bcvBcvsuffix) {
		this.setField(4, bcvBcvsuffix);
	}

	public String getBcvbcvsuffix() {
		return this.getField(4);
	}

	public void setBckbckcode(String bckBckcode) {
		this.setField(5, bckBckcode);
	}

	public String getBckbckcode() {
		return this.getField(5);
	}

	public void setBckbckname(String bckBckname) {
		this.setField(6, bckBckname);
	}

	public String getBckbckname() {
		return this.getField(6);
	}

	public void setBckbckgroupcode(String bckBckgroupcode) {
		this.setField(7, bckBckgroupcode);
	}

	public String getBckbckgroupcode() {
		return this.getField(7);
	}

	public void setBckbckbuildvaluesign(String bckBckbuildvaluesign) {
		this.setField(8, bckBckbuildvaluesign);
	}

	public String getBckbckbuildvaluesign() {
		return this.getField(8);
	}

	public void setBcscscode(String bcsCscode) {
		this.setField(9, bcsCscode);
	}

	public String getBcscscode() {
		return this.getField(9);
	}

	public void setBcscsname(String bcsCsname) {
		this.setField(10, bcsCsname);
	}

	public String getBcscsname() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
