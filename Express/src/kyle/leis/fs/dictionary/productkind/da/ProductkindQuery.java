package kyle.leis.fs.dictionary.productkind.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ProductkindQuery extends HGeneralQuery {
	
	public ProductkindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.productkind.da.ProductkindColumns(pk.pkCode, pk.pkName, pk.pkEname, pk.pkSname, pk.pkSename, pk.pkDescription, pk.pkSigninrestrictsign, pk.pkSaletrialsign, ss.ssCode, ss.ssName, pk.pkShowserverewbcode, pk.pkBillingbybatchwaysign,pk.pkSigninneedpostcode,pk.pkPcwrestrictformula,pk.pkPdsrestrictformula,pk.pkArrearallowsignout, pk.pkPaybillingbybatchwaysign, pk.pkSiprintselflabelcodesign,pk.pkIntroductionlink, chn.chnCode, pk.pkStructurecode, ssg.ssgCode, ee.eeStructurecode, ee.eeCode, ee.eeSname) FROM TdiProductkind as pk inner join pk.tdiSimplestatus as ss left join pk.tchnChannel as chn left join pk.tdiServerstructuregroup as ssg left join pk.tdiEnterpriseelement as ee";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pk.pkCode = '~~'", "ss.ssCode in (~~)", "pk.pkEname = '~~'", "pk.pkName = '~~'", "pk.pkSname = '~~'", "pk.pkSename = '~~'", "(ee.eeStructurecode is null or ee.eeStructurecode like '~~%')" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPkcode(String pkCode) {
		this.setField(0, pkCode);
	}

	public String getPkcode() {
		return this.getField(0);
	}

	public void setSscode(String ssCode) {
		this.setField(1, ssCode);
	}

	public String getSscode() {
		return this.getField(1);
	}

	public void setPkename(String pkEname) {
		this.setField(2, pkEname);
	}

	public String getPkename() {
		return this.getField(2);
	}
	
	public void setPkName(String PkName) {
		this.setField(3, PkName);
	}

	public String getPkName() {
		return this.getField(3);
	}
	
	public void setPkSname(String PkSname) {
		this.setField(4, PkSname);
	}

	public String getPkSname() {
		return this.getField(4);
	}
	
	public void setPkSename(String PkSename) {
		this.setField(5, PkSename);
	}

	public String getPkSename() {
		return this.getField(5);
	}	
	
	public void setEestructurecode(String Eestructurecode) {
		this.setField(6, Eestructurecode);
	}

	public String getEestructurecode() {
		return this.getField(6);
	}		
}
