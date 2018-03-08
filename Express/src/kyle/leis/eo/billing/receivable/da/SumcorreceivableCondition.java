package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SumcorreceivableCondition extends GeneralCondition {
	public SumcorreceivableCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setRvoccurdate(String rvoccurdate) {
		this.setField(1, rvoccurdate);
	}

	public String getRvoccurdate() {
		return this.getField(1);
	}

	public void setYrvoccurdate(String yrvoccurdate) {
		this.setField(2, yrvoccurdate);
	}

	public String getYrvoccurdate() {
		return this.getField(2);
	}

	public void setDrvoccurdate(String drvoccurdate) {
		this.setField(3, drvoccurdate);
	}

	public String getDrvoccurdate() {
		return this.getField(3);
	}

	public void setStartrvoccurdate(String StartRvOccurdate) {
		this.setField(4, StartRvOccurdate);
	}

	public String getStartrvoccurdate() {
		return this.getField(4);
	}

	public void setEndrvoccurdate(String EndRvOccurdate) {
		this.setField(5, EndRvOccurdate);
	}

	public String getEndrvoccurdate() {
		return this.getField(5);
	}

}
