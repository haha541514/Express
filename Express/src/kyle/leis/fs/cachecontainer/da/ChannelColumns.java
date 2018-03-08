package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChannelColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChannelColumns() {
		m_astrColumns = new String[9];
	}
	
	public ChannelColumns(String strChncode, String strChnname, 
			String strChnsname, String strChnsename,
			String strCocode, String strChnwpxspsmappingname,
			String strSotcode, String strCscode,
			String strCsname){
		m_astrColumns = new String[9];
		setChncode(strChncode);
		setChnname(strChnname);
		setChnsname(strChnsname);
		setChnsename(strChnsename);
		setCocode(strCocode);
		setChnwpxspsmappingname(strChnwpxspsmappingname);
		setSotcode(strSotcode);
		setCscode(strCscode);
		setCsname(strCsname);
	}

	public void setChncode(String strChncode) {
		this.setField(0, strChncode);
	}

	public String getChncode() {
		return this.getField(0);
	}

	public void setChnname(String strChnname) {
		this.setField(1, strChnname);
	}

	public String getChnname() {
		return this.getField(1);
	}

	public void setChnsname(String strChnsname) {
		this.setField(2, strChnsname);
	}

	public String getChnsname() {
		return this.getField(2);
	}

	public void setChnsename(String strChnsename) {
		this.setField(3, strChnsename);
	}

	public String getChnsename() {
		return this.getField(3);
	}

	public void setCocode(String strCocode) {
		this.setField(4, strCocode);
	}

	public String getCocode() {
		return this.getField(4);
	}
	
	public void setChnwpxspsmappingname(String strChnwpxspsmappingname) {
		this.setField(5, strChnwpxspsmappingname);
	}

	public String getChnwpxspsmappingname() {
		return this.getField(5);
	}	
	
	public void setSotcode(String strSotcode) {
		this.setField(6, strSotcode);
	}

	public String getSotcode() {
		return this.getField(6);
	}
	
	public void setCscode(String strCscode) {
		this.setField(7, strCscode);
	}

	public String getCscode() {
		return this.getField(7);
	}	
	
	public void setCsname(String strCsname) {
		this.setField(8, strCsname);
	}

	public String getCsname() {
		return this.getField(8);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
