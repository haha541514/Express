package kyle.leis.es.systemproperty.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.systemproperty.da.FilemanagerColumns;
import kyle.leis.es.systemproperty.dax.FileManagerSequence;
import kyle.leis.hi.TesFile;



public class SaveFileManagerTransaction extends AbstractTransaction {
	private FilemanagerColumns m_FMColumns;
	
	public void setParam(FilemanagerColumns FMColumns)
	{
		this.m_FMColumns =FMColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_FMColumns.getFlfl_name())) return;
		
			TesFile objF=new TesFile();
			FileManagerSequence objFMS=new FileManagerSequence();
			objF.setFlId(Long.parseLong(objFMS.getNewSequencecode()));
			objF.setFlName(m_FMColumns.getFlfl_name());
			objF.setFlPath(m_FMColumns.getFlfl_path());
			objF.setFlCreatedate(DateFormatUtility.getSysdate());
			objSession.save(objF);
	}
	
}
