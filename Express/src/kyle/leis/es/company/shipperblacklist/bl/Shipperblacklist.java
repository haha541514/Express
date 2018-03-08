package kyle.leis.es.company.shipperblacklist.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.es.company.shipperblacklist.da.ShipperblacklistColumns;
import kyle.leis.es.company.shipperblacklist.dax.ShipperblacklistDemand;
import kyle.leis.es.company.shipperblacklist.tp.DeleteShipperbalcklistTrans;
import kyle.leis.es.company.shipperblacklist.tp.SaveShipperblacklistTrans;

public class Shipperblacklist {

	public ShipperblacklistColumns add(ShipperblacklistColumns objSBLColumns,String strOperId) throws Exception
	{
		SaveShipperblacklistTrans objSaveSBLTrans = new SaveShipperblacklistTrans();
		objSaveSBLTrans.setParam(objSBLColumns, strOperId);
		objSaveSBLTrans.execute();
		
		return ShipperblacklistDemand.queryBySblcode(String.valueOf(objSaveSBLTrans.getNewSblcode()));
	}
	
	public List batchAdd(List<ShipperblacklistColumns> listSBLColumns,String strOperId) throws Exception
	{
		List<ShipperblacklistColumns> listSBLColumnsReturn = new ArrayList<ShipperblacklistColumns>();
		for(ShipperblacklistColumns objSBLColumns : listSBLColumns)
		{
			listSBLColumnsReturn.add(add(objSBLColumns,strOperId));
		}
		if(CollectionUtility.isNull(listSBLColumnsReturn)) return null;
		return listSBLColumnsReturn;
	}
	
	public void delete(String[] astrSblcode) throws Exception
	{
		DeleteShipperbalcklistTrans objDeleteSBLTrans = new DeleteShipperbalcklistTrans();
		objDeleteSBLTrans.setParam(astrSblcode);
		objDeleteSBLTrans.execute();
	}
}
