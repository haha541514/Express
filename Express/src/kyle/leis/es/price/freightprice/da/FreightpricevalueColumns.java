package kyle.leis.es.price.freightprice.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class FreightpricevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FreightpricevalueColumns() {
		
	}
	
	public FreightpricevalueColumns(Long fpvcomp_idEpcode, 
            Integer fpvcomp_idFpvid, long fpvZnvid, 
            BigDecimal fpvFpvweightgrade, BigDecimal fpvFpvweightunit, 
            BigDecimal fpvFpvcarryweight, BigDecimal fpvFpvpricevalue, 
            String fvtFvtcode, String fvtFvtname){
		m_astrColumns = new String[9];
		setFpvcomp_idepcode(fpvcomp_idEpcode);
		setFpvcomp_idfpvid(fpvcomp_idFpvid);
		setFpvznvid(fpvZnvid);
		setFpvfpvweightgrade(fpvFpvweightgrade);
		setFpvfpvweightunit(fpvFpvweightunit);
		setFpvfpvcarryweight(fpvFpvcarryweight);
		setFpvfpvpricevalue(fpvFpvpricevalue);
		setFvtfvtcode(fvtFvtcode);
		setFvtfvtname(fvtFvtname);
	}

	public void setFpvcomp_idepcode(Long fpvcomp_idEpcode) {
		this.setField(0, fpvcomp_idEpcode);
	}

	public String getFpvcomp_idepcode() {
		return this.getField(0);
	}

	public void setFpvcomp_idfpvid(Integer fpvcomp_idFpvid) {
		this.setField(1, fpvcomp_idFpvid);
	}

	public String getFpvcomp_idfpvid() {
		return this.getField(1);
	}

	public void setFpvznvid(long fpvZnvid) {
		this.setField(2, fpvZnvid);
	}

	public String getFpvznvid() {
		return this.getField(2);
	}

	public void setFpvfpvweightgrade(BigDecimal fpvFpvweightgrade) {
		this.setField(3, fpvFpvweightgrade);
	}

	public String getFpvfpvweightgrade() {
		return this.getField(3);
	}

	public void setFpvfpvweightunit(BigDecimal fpvFpvweightunit) {
		this.setField(4, fpvFpvweightunit);
	}

	public String getFpvfpvweightunit() {
		return this.getField(4);
	}

	public void setFpvfpvcarryweight(BigDecimal fpvFpvcarryweight) {
		this.setField(5, fpvFpvcarryweight);
	}

	public String getFpvfpvcarryweight() {
		return this.getField(5);
	}

	public void setFpvfpvpricevalue(BigDecimal fpvFpvpricevalue) {
		this.setField(6, fpvFpvpricevalue);
	}

	public String getFpvfpvpricevalue() {
		return this.getField(6);
	}

	public void setFvtfvtcode(String fvtFvtcode) {
		this.setField(7, fvtFvtcode);
	}

	public String getFvtfvtcode() {
		return this.getField(7);
	}

	public void setFvtfvtname(String fvtFvtname) {
		this.setField(8, fvtFvtname);
	}

	public String getFvtfvtname() {
		return this.getField(8);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
