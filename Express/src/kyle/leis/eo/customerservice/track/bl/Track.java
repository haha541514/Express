package kyle.leis.eo.customerservice.track.bl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.da.WaybillfortrackColumns;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.da.WebaccesstrackColumns;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;
import kyle.leis.eo.customerservice.track.tp.AddSingleTrackTrans;
import kyle.leis.eo.customerservice.track.tp.BatchaddSingleTrackTrans;
import kyle.leis.eo.customerservice.track.tp.DeleteWBTrackTrans;
import kyle.leis.eo.customerservice.track.tp.ModifyLatestFetchDataTrans;
import kyle.leis.eo.customerservice.track.tp.SaveWBTrackTransaction;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesCondition;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillColumns;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;

public class Track {
	public List Save(WaybilltrackColumns objWBTColumns,
			String strOperId, 
			boolean isDelOriginTrack) throws Exception {
		SaveWBTrackTransaction objSWBTTrans = new SaveWBTrackTransaction();
		objSWBTTrans.setParam(objWBTColumns, 
				strOperId, 
				isDelOriginTrack);
		objSWBTTrans.execute();
		String strCwcode = objWBTColumns.getWbbtcwcode();
		return TrackDemand.loadTracks(strCwcode);
	}
	
	/**
	 * 新增单条轨迹，先将来原来轨迹作废再新增
	 * @param strCwcode
	 * @param strDtcode
	 * @param strWbtscode
	 * @param strOperId
	 * @param strOccurDate
	 * @throws Exception
	 */
	public void addSingleTrack(String strCwcode, 
			String strDtcode, 
			String strWbtscode,
			String strOperId,
			String strOccurDate) throws Exception {
		AddSingleTrackTrans objASTTrans = new AddSingleTrackTrans();
		objASTTrans.setParam(strCwcode, 
				strDtcode, 
				strWbtscode, 
				strOperId, 
				strOccurDate);
		objASTTrans.execute();
	}
	
	public void addSingleTrack(String strCwcode, 
			String strDtcode, 
			String strWbtscode,
			String strWbtdescription,
			String strOperId,
			String strOccurDate) throws Exception {
		AddSingleTrackTrans objASTTrans = new AddSingleTrackTrans();
		objASTTrans.setParam(strCwcode, 
				strDtcode, 
				strWbtscode, 
				strOperId,
				strWbtdescription,
				strOccurDate);
		objASTTrans.execute();
	}	
	
	public void checkPoint(String strScanNo,
			String strType,
			String strOperId,
			String strWbtscode,
			String strDtcode) throws Exception {
		String[] astrCwcode = null;
		if (strType.equals("MAWB")) {
			TransportwaybillColumns objTWBColumns = TransportWaybillDemand.loadByTwbcode(strScanNo);
			String strTwbid = objTWBColumns.getTwbtwbid();
			List listResults = TransportWaybillDemand.loadCorewaybill(strTwbid);
			Set<String> setCwcode = new HashSet<String>();
			for (int i = 0; i < listResults.size(); i++) {
				TransportcorewaybillColumns objTCBColumns = (TransportcorewaybillColumns)listResults.get(i);
				setCwcode.add(objTCBColumns.getCwcw_code());
			}
			astrCwcode = new String[setCwcode.size()];
			int i = 0;
			for (String str : setCwcode) {
				astrCwcode[i] = str;
				i++;
			}
		} else if (strType.equals("HAWB")) {
			astrCwcode = new String[1];
			HousewaybillColumns objHWBColumns = HousewaybillDemand.load(strScanNo, ICorewaybillBasicData.EWBCODE_TYPE_SERVER);
			astrCwcode[0] = objHWBColumns.getHwcwcode();
		} else if (strType.equals("BAG")) {
			CorewaybillpiecesCondition objCwpCondition = new CorewaybillpiecesCondition();
			objCwpCondition.setCpbaglabelcode(strScanNo);
			List listResults = CorewaybillpiecesDemand.query(objCwpCondition);
			astrCwcode = new String[listResults.size()];
			for (int i = 0; i < listResults.size(); i++) {
				CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)listResults.get(i);
				astrCwcode[i] = objCWPColumns.getCpcomp_idcwcode();
			}
		}
		// 增加轨迹
		BatchaddSingleTrackTrans objBASTT = new BatchaddSingleTrackTrans();
		objBASTT.setParam(astrCwcode, strOperId, strWbtscode, strDtcode);
		objBASTT.execute();
	}
	
	public void modifyLatestFetchdate(String strCwcode, String strRemark) throws Exception {
		ModifyLatestFetchDataTrans objMLFTT = new ModifyLatestFetchDataTrans();
		objMLFTT.setParam(strCwcode, strRemark);
		objMLFTT.execute();
	}
	
	
	public void delete (String[] astrWbtid, 
			String strCwcode) throws Exception {
		DeleteWBTrackTrans objDWBTTrans = new DeleteWBTrackTrans();
		objDWBTTrans.setParam(astrWbtid, 
				strCwcode);
		objDWBTTrans.execute();
	}
	// by 20100310
	public WebaccesstrackColumns buildWebAccessTrackColumns(WaybillfortrackColumns objWBFTColumns,
			List<WaybilltrackColumns>listWBTColumns) throws Exception
	{
		WebaccesstrackColumns objWATColumns = new WebaccesstrackColumns();
		objWATColumns.setStrCwchargeweight(objWBFTColumns.getCwcwchargeweight());
		objWATColumns.setStrCwcode(objWBFTColumns.getCwcwcode());
		objWATColumns.setStrCwcustomerewbcode(objWBFTColumns.getCwcwcustomerewbcode());
		String strPkcode = objWBFTColumns.getPkpkcode();
		boolean isSettingServerEWBCode = false;
		if (!StringUtility.isNull(strPkcode)) {
			ProductkindColumns objPKC = ProductkindDemand.queryBypkCode(strPkcode);
			if (objPKC.getPkshowserverewbcode().equals("N")) {
				objWATColumns.setStrCwserverewbcode(objWBFTColumns.getCwcwewbcode());
				isSettingServerEWBCode = true;
			}
		}
		if (!isSettingServerEWBCode)
			objWATColumns.setStrCwserverewbcode(objWBFTColumns.getCwcwserverewbcode());
		
		objWATColumns.setStrHawbcode(objWBFTColumns.getCwcwewbcode());
		objWATColumns.setStrDtcode(objWBFTColumns.getCddtdthubcode());
		objWATColumns.setStrPksname(objWBFTColumns.getPkpksname());
		objWATColumns.setStrWbtsname(objWBFTColumns.getWbtswbtsname());
		objWATColumns.setStrWbtsename(objWBFTColumns.getWbtswbtscode());
		objWATColumns.setStrLatesttrackdesc(objWBFTColumns.getWbbtwbbtlatesttrackdesc());
		objWATColumns.setListWBTColumns(listWBTColumns);
		objWATColumns.setStrOrigin(objWBFTColumns.getOdtdtename());
		if(!StringUtility.isNull(objWBFTColumns.getDdtdtename()))
			objWATColumns.setStrDdtename(objWBFTColumns.getDdtdtename());
		else if(!StringUtility.isNull(objWBFTColumns.getCddtdtename()))
			objWATColumns.setStrDdtename(objWBFTColumns.getCddtdtename());
		objWATColumns.setStrSignatory(objWBFTColumns.getWbbtwbbtsignforuser());
		objWATColumns.setCountryEname(objWBFTColumns.getCddtdtename());
		objWATColumns.setPieces(objWBFTColumns.getCwcwpieces());
		return objWATColumns;
	}
}
