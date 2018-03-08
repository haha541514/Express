package kyle.leis.eo.operation.cwbimportlog.da;


public class CwbimportlogQueryEX extends CwbimportlogQuery {
	
	public CwbimportlogQueryEX(){
	    m_strWhereClause = "toc.potId is null";
	}
}
