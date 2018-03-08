package kyle.leis.eo.operation.manifest.dax;

import java.io.File;

import com.dhl.sop.label.IDHLLabelBasicData;

import kyle.common.util.jlang.FileUtility;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;

public abstract class ADGMSPSData {
	public abstract String build(BatchwaybillColumns objBWColumns, ChannelColumns objChannelColumns) throws Exception;
	
	public String[] parseAddress(String strAddress, int addressCount, 
			int addressLength) {
		strAddress = strAddress.replaceAll("\r", "");
		strAddress = strAddress.replaceAll("\n", "");
		String[] astrAddress = new String[addressCount];
		for (int i = 0; i < addressCount; i++) {
			astrAddress[i] = ".";
			if (strAddress.length() > i * addressLength) {
				if (strAddress.length() > (i + 1) * addressLength)
					astrAddress[i] = strAddress.substring(i * addressLength, (i + 1) * addressLength);
				else
					astrAddress[i] = strAddress.substring(i * addressLength);
			}
		}
		return astrAddress;
	}
	
	public void buildFile(String strLabelcode, 
			String strResult) throws Exception {
		if (FileUtility.exist(SystempropertyDemand.getWaybillFilePath())) {	
			String strDirectory = "/usr/local/tomcatxleis/webapps/Express/dgm";
			String strFilename = strLabelcode + ".txt";
			if (FileUtility.exist(strFilename)) {
				File objF = new File(strFilename);
				objF.delete();
			}
			FileUtility.createFile(strDirectory, 
					strFilename, 
					strResult);		
		}
	}
}
