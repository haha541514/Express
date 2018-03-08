package kyle.leis.fs.waybillcode.bl;

import java.util.HashMap;
import java.util.List;

import kyle.leis.fs.waybillcode.da.WaybillcodekindColumns;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.fs.waybillcode.dax.WaybillcodeDemand;

public class WaybillcodeFactory {
	private static WaybillcodeFactory s_objWBCFactory;
	private HashMap<String, WaybillcodekindColumns> m_hmWBCKindCollection;
	
	private WaybillcodeFactory() {
		m_hmWBCKindCollection = new HashMap<String, WaybillcodekindColumns>();
		try {
			List objList = WaybillcodeDemand.queryWaybillcodekind();
			if (objList != null && objList.size() > 0)
				for (int i = 0; i < objList.size(); i++) {
					WaybillcodekindColumns objWBCKColumns = (WaybillcodekindColumns)objList.get(i);
					m_hmWBCKindCollection.put(objWBCKColumns.getWbckbckcode(), objWBCKColumns);
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
	
	public HashMap<String, WaybillcodekindColumns> getWBKindCollection() {
		return m_hmWBCKindCollection;
	}
	
	public static AWaybillcode getWaybillcode(String strBckcode) throws Exception {
		if (s_objWBCFactory == null)
			s_objWBCFactory = new WaybillcodeFactory();
		
		HashMap<String, WaybillcodekindColumns> hmWBCKindCollection = s_objWBCFactory.getWBKindCollection();
		if (hmWBCKindCollection == null || !hmWBCKindCollection.containsKey(strBckcode))
			return null;
		WaybillcodekindColumns objWBCKColumns = hmWBCKindCollection.get(strBckcode);
		if (objWBCKColumns.getWbckbckgroupcode().equals(IWaybillcodeBasicData.BCK_DHLCHILD))
			return new DHLChildWaybillcode(objWBCKColumns);
		else if (objWBCKColumns.getWbckbckgroupcode().equals(IWaybillcodeBasicData.BCK_DHLMASTER))
			return new DHLCoreWaybillcode(objWBCKColumns);
		else if (objWBCKColumns.getWbckbckgroupcode().equals(IWaybillcodeBasicData.BCK_HONGKONGPACKAGE))
			return new HKPKWaybillcode(objWBCKColumns);		
		else if (objWBCKColumns.getWbckbckgroupcode().equals(IWaybillcodeBasicData.BCK_DHLGLOBEMASTER))
			return new DHLGlobeCoreWaybillcode(objWBCKColumns);	
		else if (objWBCKColumns.getWbckbckgroupcode().equals(IWaybillcodeBasicData.BCK_FRANCEEMSMASTER))
			return new FranchEMSWaybillcode(objWBCKColumns);		
		else if (objWBCKColumns.getWbckbckgroupcode().equals(IWaybillcodeBasicData.BCK_DHLDGM))
			return new DGMCoreWaybillcode(objWBCKColumns);	
		else if (objWBCKColumns.getWbckbckgroupcode().equals(IWaybillcodeBasicData.BCK_GROUPHUEMS))
			return new HUCoreWaybillcode(objWBCKColumns);			
		else
			return new CustomsWaybillcode(objWBCKColumns);
	}
}
