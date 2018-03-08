package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ServerfeekindmappingCondition extends GeneralCondition {
	public ServerfeekindmappingCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setSfkmserverbillkind(String sfkmServerbillkind) {
		this.setField(0, sfkmServerbillkind);
	}

	public String getSfkmserverbillkind() {
		return this.getField(0);
	}

}
