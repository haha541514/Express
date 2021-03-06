package kyle.leis.eo.operation.corewaybillpieces.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorewaybillpiecesCondition extends GeneralCondition {
	public CorewaybillpiecesCondition() {
		m_astrConditions = new String[7];
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
