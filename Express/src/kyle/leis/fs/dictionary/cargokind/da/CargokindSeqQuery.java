package kyle.leis.fs.dictionary.cargokind.da;

import kyle.common.dbaccess.sequence.GeneralSequence;

public class CargokindSeqQuery extends GeneralSequence{

	//除了继承GeneralSequence,还可以继承JGeneralQuery
	//继承可以继承protected变量
	
	public CargokindSeqQuery() {
		
		m_strSequenceName ="S_CGK_CODE";
	}
}
