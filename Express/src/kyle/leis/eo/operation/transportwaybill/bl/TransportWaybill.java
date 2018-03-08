package kyle.leis.eo.operation.transportwaybill.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesCondition;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.transportwaybill.blx.TransportwaybilltrackThread;
import kyle.leis.eo.operation.transportwaybill.da.TransportforcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportforcorewaybillCondition;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybilltraceColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillvalueColumns;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;
import kyle.leis.eo.operation.transportwaybill.tp.AddTransportCWValueTrans;
import kyle.leis.eo.operation.transportwaybill.tp.AddTransportValueTrans;
import kyle.leis.eo.operation.transportwaybill.tp.ApportionTransportFeeTrans;
import kyle.leis.eo.operation.transportwaybill.tp.ModifyStatusTrans;
import kyle.leis.eo.operation.transportwaybill.tp.SaveTransportTrans;

public class TransportWaybill {
	/**
	 * 保存
	 * @param objTWColumns
	 * @param astrBwcode
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public TransportwaybillColumns save(TransportwaybillColumns objTWColumns,
			String[] astrBwcode,
			String strOperId,
			String[] astrBaglaelcode,
			String[] astrCwcode) throws Exception {
		SaveTransportTrans objSaveTransportTrans = new SaveTransportTrans();
		objSaveTransportTrans.setParam(objTWColumns, 
				astrBwcode, 
				strOperId,
				astrBaglaelcode,
				astrCwcode);
		objSaveTransportTrans.execute();
		return TransportWaybillDemand.load(objSaveTransportTrans.getNewTwbId());
	}
	
	/**
	 * 添加出货主单
	 * @param strTwbId
	 * @param astrBwcode
	 * @throws Exception
	 */
	public void addTransportvalue(String strTwbId, 
			String[] astrBwcode) throws Exception {
		AddTransportValueTrans objAddTVTrans = new AddTransportValueTrans();
		objAddTVTrans.setParam(strTwbId, astrBwcode);
		objAddTVTrans.execute();
	}
	
	public void addTransportcwvalue(String strTwbId, 
			String[] astrCwcode) throws Exception {
		AddTransportCWValueTrans objAddTVCWTrans = new AddTransportCWValueTrans();
		objAddTVCWTrans.setParam(strTwbId, astrCwcode);
		objAddTVCWTrans.execute();
	}	
	
	/**
	 * 改变状态
	 * @param strTwbid
	 * @param strAkcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void modifyStatus(List listTWTColumns,
			String strOperId) throws Exception {
		if (listTWTColumns == null || listTWTColumns.size() < 1)
			return;
		List<TransportwaybilltraceColumns> listTransferedColumns = new ArrayList<TransportwaybilltraceColumns>();
		String strTwbid = ((TransportwaybilltraceColumns)listTWTColumns.get(0)).getTwbtcomp_idtwbid();;
		for (int i = 0; i < listTWTColumns.size(); i++) {
			listTransferedColumns.add((TransportwaybilltraceColumns)listTWTColumns.get(i));
		}
		// 改变状态
		ModifyStatusTrans objModifyStatusTrans = new ModifyStatusTrans();
		objModifyStatusTrans.setParam(listTransferedColumns,
				strOperId);
		objModifyStatusTrans.execute();
		// 生成轨迹
		TransportwaybilltrackThread objTWBTThread = new TransportwaybilltrackThread(strTwbid,
				listTransferedColumns,
				strOperId);
		objTWBTThread.start();
	}
	
	/**
	 * 分摊中港运费
	 * @param strTwbid
	 * @param strCkcode
	 * @param strTransportFeeTotal
	 * @param strOperId
	 * @throws Exception
	 */
	public void apportionTransportFee(String strTwbid, 
			String strCkcode,
			String strTransportFeeTotal, 
			String strOperId) throws Exception {
		ApportionTransportFeeTrans objATSFTrans = new ApportionTransportFeeTrans();
		objATSFTrans.setParam(strTwbid, 
				strCkcode, 
				strTransportFeeTotal, 
				strOperId);
		objATSFTrans.execute();
	}
	
	/**
	 * 根据单票(cwcode)查询运单
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static TransportforcorewaybillColumns loadByCwcode(String strCwcode) throws Exception
	{
		List objCWPList =  CorewaybillpiecesDemand.load(strCwcode);
		if(CollectionUtility.isNull(objCWPList)) 
			return null;
		CorewaybillpiecesColumns objCwpColumns = (CorewaybillpiecesColumns)objCWPList.get(0);
		String strBaglabelcode = objCwpColumns.getCpcpbaglabelcode();
		ForinputallColumns objFIAColumns = HousewaybillDemand.load(strCwcode);
		String strBwcode = objFIAColumns.getBwcode_Cwdbm();
		if (StringUtility.isNull(strBwcode)) return null;
		
		TransportforcorewaybillCondition objTPFCWCondition = new TransportforcorewaybillCondition();
		objTPFCWCondition.setBwcode(strBwcode);
		if(!StringUtility.isNull(strBaglabelcode))
			objTPFCWCondition.setTwbvbaglabelcode(strBaglabelcode);
		TransportforcorewaybillColumns objTFCColumns = TransportWaybillDemand.queryForCW(objTPFCWCondition);
		if (objTFCColumns == null) {
			objTPFCWCondition = new TransportforcorewaybillCondition();
			objTPFCWCondition.setCwcode(strCwcode);		
			objTFCColumns = TransportWaybillDemand.queryForCW(objTPFCWCondition);
		}
		return objTFCColumns;
	}
	
	public String getTransportWaybill(String strScanNo, 
			String strType) throws Exception {
		if (strType.equals("MAWB")) {
			TransportwaybillColumns objTWBColumns = TransportWaybillDemand.loadByTwbcode(strScanNo);
			return objTWBColumns.getTwbtwbid();
		} else if (strType.equals("HAWB")) {
			HousewaybillColumns objHWBColumns = HousewaybillDemand.load(strScanNo, ICorewaybillBasicData.EWBCODE_TYPE_SERVER);
			if (objHWBColumns == null)
				return "ERROR";
			String strDbwcode = objHWBColumns.getDbwbwcode();
			TransportwaybillvalueColumns objTWVColumns = TransportWaybillDemand.queryByBwcode(strDbwcode);
			if (objTWVColumns == null)
				return "NULL";
			return objTWVColumns.getTwbvcomp_idtwbid();
		} else if (strType.equals("BAG")) {
			CorewaybillpiecesCondition objCwpCondition = new CorewaybillpiecesCondition();
			objCwpCondition.setCpbaglabelcode(strScanNo);
			List listResults = CorewaybillpiecesDemand.query(objCwpCondition);
			if (listResults == null || listResults.size() < 1)
				return "ERROR";
			CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)listResults.get(0);
			String strCwcode = objCWPColumns.getCpcomp_idcwcode();
			HousewaybillColumns objHWBColumns = HousewaybillDemand.loadByCwcode(strCwcode);
			String strDbwcode = objHWBColumns.getDbwbwcode();
			TransportwaybillvalueColumns objTWVColumns = TransportWaybillDemand.queryByBwcode(strDbwcode);
			if (objTWVColumns == null)
				return "NULL";
			return objTWVColumns.getTwbvcomp_idtwbid();			
		}
		return "NOSUPPORT";
	}
	
}
