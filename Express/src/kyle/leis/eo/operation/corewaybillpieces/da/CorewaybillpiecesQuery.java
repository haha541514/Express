package kyle.leis.eo.operation.corewaybillpieces.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CorewaybillpiecesQuery extends HGeneralQuery {
	
	public CorewaybillpiecesQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns(cp.comp_id.cpId,cp.comp_id.cwCode,cp.cpGrossweight,cp.cpLength,cp.cpWidth,cp.cpHeight,cp.cpLabelcode,cp.cpBaglabelcode,cp.cpSibaglabelcode,cp.cpBarcodelabelcode,cp.cpSelflabelcode,cws.cwsCode,cws.cwsName) FROM TopCorewaybillpiece as cp left join cp.tdiCorewaybillstatus as cws";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cp.comp_id.cpId = ~~", "cp.comp_id.cwCode = ~~", "cp.cpLabelcode = '~~'", "cp.cpBaglabelcode = '~~'", "cp.cpSibaglabelcode = '~~'", "cp.cpSelflabelcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCpid(String cpId) {
		this.setField(0, cpId);
	}

	public String getCpid() {
		return this.getField(0);
	}

	public void setCwcode(String cwCode) {
		this.setField(1, cwCode);
	}

	public String getCwcode() {
		return this.getField(1);
	}

	public void setCplabelcode(String cpLabelcode) {
		this.setField(2, cpLabelcode);
	}

	public String getCplabelcode() {
		return this.getField(2);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(3, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(3);
	}

	public void setCpsibaglabelcode(String cpSibaglabelcode) {
		this.setField(4, cpSibaglabelcode);
	}

	public String getCpsibaglabelcode() {
		return this.getField(4);
	}

	public void setCpselflabelcode(String cpSelflabelcode) {
		this.setField(5, cpSelflabelcode);
	}

	public String getCpselflabelcode() {
		return this.getField(5);
	}

}
