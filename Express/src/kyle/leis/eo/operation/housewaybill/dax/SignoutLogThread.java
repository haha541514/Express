package kyle.leis.eo.operation.housewaybill.dax;

import java.io.File;

import kyle.common.util.jlang.FileUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.hi.TdiProductkind;

public class SignoutLogThread extends Thread {
	
	private String m_strPkcode;
	private String m_strServerewbcode;
	private String m_strCheckLog;
	
	public SignoutLogThread(String strPkcode,
			String strServerewbcode,
			String strCheckLog) {
		m_strPkcode = strPkcode;
		m_strServerewbcode = strServerewbcode;
		m_strCheckLog = strCheckLog;
	}
	
	public void run() {
		try {
			
			if (StringUtility.isNull(m_strCheckLog))
				return;
			if (StringUtility.isNull(m_strPkcode))
				return;
			/*
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(m_strPkcode);
			// 小包产品不效验是否欠费
			
			if (objTdiProductkind.getTdiServerstructuregroup() != null &&
					!StringUtility.isNull(objTdiProductkind.getTdiServerstructuregroup().getSsgCode()) &&
					objTdiProductkind.getTdiServerstructuregroup().getSsgCode().startsWith("HKPK")) {
			*/	
				String strDirectory = "/usr/local/checkoutlog";
				String strFilename = m_strServerewbcode + ".txt";
				if (!FileUtility.exist(strDirectory))
					FileUtility.makehome(strDirectory);
				if (FileUtility.exist(strDirectory + "/" + strFilename)) {
					File objF = new File(strDirectory + "/" + strFilename);
					objF.delete();
				}
				FileUtility.createFile(strDirectory, strFilename, m_strCheckLog);			
			//}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
