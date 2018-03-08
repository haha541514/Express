package kyle.leis.eo.billing.incidentalfee.bl;

import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeColumns;
import kyle.leis.eo.billing.incidentalfee.dax.IncidentalfeeDemand;
import kyle.leis.eo.billing.incidentalfee.tp.ModifyStatusTransaction;
import kyle.leis.eo.billing.incidentalfee.tp.SaveIncidentalFeeTransaction;

public class Incidentalfee {
	
	/*
	 * 新增杂费项目
	 */
	public IncidentalfeeColumns addIncidentalfee(IncidentalfeeColumns objIncidentalfeeCol,String strOperId) throws Exception
	{
		SaveIncidentalFeeTransaction objSaveIncidentalFeeTrans = new SaveIncidentalFeeTransaction();
		objSaveIncidentalFeeTrans.setParam(objIncidentalfeeCol, strOperId);
		objSaveIncidentalFeeTrans.execute();
		
		return IncidentalfeeDemand.queryByIfId(objSaveIncidentalFeeTrans.getNewIfId());
	}
	
	/*
	 * 修改状态
	 */
	
	public String modifyStatus(String strIfId,String strFsCode,String strOperId) throws Exception
	{
		ModifyStatusTransaction objModifyStatusTrans =  new ModifyStatusTransaction();
		objModifyStatusTrans.setParam(strIfId, strFsCode, strOperId);
		objModifyStatusTrans.execute();
		return "";
	}
}
