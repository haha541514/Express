package kyle.leis.eo.operation.manifest.dax;

import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;

public class DGMSPSDataFactory {
	
	public String buildDGMSPSData(String strDbwLabelcode) throws Exception {
		BatchwaybillColumns objBWColumns = BatchWayBillDemand.loadByBwLabelcode(strDbwLabelcode);
		if (objBWColumns == null) return "";
		// 获得渠道信息
		ChannelColumns objChannelColumns = ChannelDemand.load(objBWColumns.getChnchncode(), false);		
		
		ADGMSPSData objDGMSPSData = createDGMSPSData(objChannelColumns.getLflfcode(),
				objChannelColumns.getMbckbckcode()); 
		return objDGMSPSData.build(objBWColumns, objChannelColumns);
	}
	
	
	public ADGMSPSData createDGMSPSData(String strLfcode,
			String strBckcode) throws Exception {
		if ("EPARCEL".equals(strBckcode))
			return new EparcelSPSData();
		if ("C_DGMG".equals(strLfcode) || 
				"C_DGMP".equals(strLfcode) || 
				"C_DGMPGB".equals(strLfcode))
			return new DGMSPSData();
		if ("C_SDHLGM".equals(strLfcode))
			return new DHLGloblemailSPSData();	
		if ("C_DGMUDF".equals(strLfcode))
			return new DGMUDFSPSData();		
		else
			return new DHLGloblemailSPSData();
	}

}
