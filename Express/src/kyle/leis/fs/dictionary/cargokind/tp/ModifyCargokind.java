package kyle.leis.fs.dictionary.cargokind.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.cargokind.da.CargokindColumns;
import kyle.leis.fs.dictionary.cargokind.da.CargokindSeqQuery;
import kyle.leis.fs.dictionary.cargokind.dax.CargokindDemand;
import kyle.leis.hi.TdiCargokind;

public class ModifyCargokind extends AbstractTransaction{

	private  CargokindColumns objCargokindColumns;
	


	public void setParam(CargokindColumns objCargokindColumns) {
		
		this.objCargokindColumns = objCargokindColumns;
	}
	
	
	public void transaction(Session objSession) throws Exception {

		if(objCargokindColumns == null) return;
		
		TdiCargokind objTdiCargokind = new TdiCargokind();
	//	CargokindSeqQuery query = new CargokindSeqQuery();
	//	objTdiCargokind.setCgkCode(query.getNewSequencecode());
	//	System.out.println(query.getNewSequencecode());
		//
		//System.out.println(objCargokindColumns.getCkcgkcode());
		//objTdiCargokind.setCgkCode(objCargokindColumns.getCkcgkcode());
		
		//java.sql.exception:Unknown entity class: kyle.leis.fs.dictionary.cargokind.da.CargokindColumns
		//”√load≤ª––°£
		//objTdiCargokind = (TdiCargokind)objSession.load(CargokindColumns.class, objCargokindColumns.getCkcgkcode());
		
		CargokindDemand.setCargokindByid(objTdiCargokind, objCargokindColumns, objSession);
		objSession.update(objTdiCargokind);
		
		
	}

}
