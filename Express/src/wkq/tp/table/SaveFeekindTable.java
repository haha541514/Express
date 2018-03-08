package wkq.tp.table;

import kyle.common.dbaccess.table.TableAccess;
import wkq.da.FeekindColumns;
import wkq.da.TdifeekindTD;
import wkq.da.TdifeekindTR;

public class SaveFeekindTable  {
	private FeekindColumns m_objFeekindColumns;
	private TdifeekindTR tr;
	private TdifeekindTD td;
	private TableAccess table;
	
	private String m_newFkcode;

	public void setNewFkCode(String fkCode){
		this.m_newFkcode = fkCode;
	}
	
	public String getNewFkcode() {
		return this.m_newFkcode;
	}
	
	public void setParam(FeekindColumns objFeekindColumns) {
		this.m_objFeekindColumns = objFeekindColumns;
	}
	public void setTR(TdifeekindTR tr) {
		this.tr = tr;
	}
	
	
	
	
	
	
	
	
	
}
