package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class LabeldataQuery extends JGeneralQuery {
	
	public LabeldataQuery(){
	    m_strSelectClause = "SELECT pl.lcn,pl.product,pl.mailtype,pl.zipcode,pl.e1,pl.e2,pl.e3,pl.e4,pl.e5,pl.e6 FROM T_DGM_PTST_LKPTBLE pl";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pl.lcn='~~'", "pl.product='~~'", "pl.mailtype='~~'", "pl.zipcode='~~'","pl.zipcode like '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1};		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new LabeldataColumns();
	}
	
	public void setLcns(String lcns) {
		this.setField(0, lcns);
	}

	public String getLcns() {
		return this.getField(0);
	}

	public void setProducts(String products) {
		this.setField(1, products);
	}

	public String getProducts() {
		return this.getField(1);
	}

	public void setMtype(String mtype) {
		this.setField(2, mtype);
	}

	public String getMtype() {
		return this.getField(2);
	}

	public void setCode(String code) {
		this.setField(3, code);
	}

	public String getCode() {
		return this.getField(3);
	}
	public void setLikeCode(String likeCode) {
		this.setField(4, likeCode);
	}

	public String getLikeCode() {
		return this.getField(4);
	}
}
