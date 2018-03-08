package kyle.leis.eo.operation.housewaybill.bl;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwbvalueColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.tp.SaveWaybillTransaction;

public class PackageWaybill {
	
	public SavedResultUtility save(String strCwserverewbcode,
			String strAdtcode,
			String strBwvid,
			String strOperID) throws Exception {
		// 返回值
		SavedResultUtility objSRUtility = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		HousewaybillColumns objHousewaybillColumns = null;
		if (strAdtcode.equals("A")) {
			objHousewaybillColumns = HousewaybillDemand.load(strCwserverewbcode, 
					"SI,CHP,IP",
					ICorewaybillBasicData.EWBCODE_TYPE_SERVER);
		} else {
			objHousewaybillColumns = HousewaybillDemand.load(strCwserverewbcode, 
					"SI,IP",
					ICorewaybillBasicData.EWBCODE_TYPE_SERVER);			
		}
		if (objHousewaybillColumns == null) {
			objPUCollection.add("E_001", 
					"不存在的运单", 
					"PackageWaybill.save");
			objSRUtility.setPromptUtilityCollection(objPUCollection);
			return objSRUtility;
		}
		if (strAdtcode.equals("A"))
			return signin(objHousewaybillColumns, strBwvid, strOperID);
		else
			return signout(objHousewaybillColumns, strBwvid, strOperID);
	}
	
	/**
	 * 小包收货扫描
	 * @param objHousewaybillColumns
	 * @param strBwvid
	 * @return
	 * @throws Exception
	 */
	private SavedResultUtility signin(HousewaybillColumns objHousewaybillColumns,
			String strBwvid,
			String strOperID) throws Exception {
		// 返回值
		SavedResultUtility objSRUtility = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// 效验运单状态
		String strCwscode = objHousewaybillColumns.getCwscwscode();
		if (StringUtility.isNull(strCwscode) || 
				(!strCwscode.equals("CHP") && !strCwscode.equals("PR"))) {
			objPUCollection.add("E_001", 
					"运单不为预报状态无法收货", 
					"PackageWaybill.save");
			objSRUtility.setPromptUtilityCollection(objPUCollection);
			return objSRUtility;			
		}
		
		SimplebatchwaybillColumns objSBWBC = BatchWayBillDemand.loadSimpleBatchwaybill(strBwvid);
		if (!objHousewaybillColumns.getCcococode().equals(objSBWBC.getCococode())) {
			objPUCollection.add("E_001", 
					"运单的客户名称与主单的客户名称不一致无法收货", 
					"PackageWaybill.save");
			objSRUtility.setPromptUtilityCollection(objPUCollection);
			return objSRUtility;				
		}
		// 效验产品是否一致
		SimplebatchwbvalueColumns objSBVC = BatchWayBillDemand.loadBWVCorewaybill(strBwvid);
		HousewaybillColumns objHWPackage = HousewaybillDemand.loadByCwcode(objSBVC.getBwvcw_code());
		if (!objHousewaybillColumns.getPkpkcode().equals(objHWPackage.getPkpkcode())) {
			objPUCollection.add("E_001", 
					"运单的产品与小包号码的产品不一致无法收货", 
					"PackageWaybill.save");
			objSRUtility.setPromptUtilityCollection(objPUCollection);
			return objSRUtility;			
		}		
		
		objHousewaybillColumns.setCwscwscode("IP");
		objHousewaybillColumns.setAbwvbwbvid(Long.parseLong(strBwvid));
		objHousewaybillColumns.setAbwbwcode(Long.parseLong(objSBWBC.getBwbwcode()));
		objHousewaybillColumns.setHwhwsignindate(DateFormatUtility.getSysdate());
		objHousewaybillColumns.setHwhwsignoutdate(DateFormatUtility.getStandardDate("1900-01-01"));
		
		SaveWaybillTransaction objSWT = new SaveWaybillTransaction();
		objSWT.setSignInParam(objHousewaybillColumns, null, 
				null, strOperID);
		objSWT.execute();
		
		Track objTrack = new Track();
		objTrack.addSingleTrack(objHousewaybillColumns.getHwcwcode(), 
				objHousewaybillColumns.getOdtdtcode(), 
				"AF", 
				strOperID, 
				DateFormatUtility.getStandardSysdate());		
		
		
		objSRUtility.setPromptUtilityCollection(objPUCollection);
		objSRUtility.setColumns(objHousewaybillColumns);
		
		return objSRUtility;		
	}	
	
	/**
	 * 小包出货扫描
	 * @param objHousewaybillColumns
	 * @param strBwvid
	 * @return
	 * @throws Exception
	 */
	private SavedResultUtility signout(HousewaybillColumns objHousewaybillColumns,
			String strBwvid,
			String strOperID) throws Exception {
		// 返回值
		SavedResultUtility objSRUtility = new SavedResultUtility();
		// 检查是否为小包号码的出货
		String strCwcode = objHousewaybillColumns.getHwcwcode();
		SimplebatchwbvalueColumns objSBVC = BatchWayBillDemand.loadBWVCorewaybillByCW(strCwcode);
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// 小包号码出货需要效验客户是否欠费
		BatchwaybillColumns objBatchwaybillColumns = null;
		if (objSBVC != null) {
			objBatchwaybillColumns = BatchWayBillDemand.load(strBwvid);
			if (objBatchwaybillColumns == null) {
				objPUCollection.add("E_001", 
						"选择出货的主单不存在，请您确认是否为小包号码出货", 
						"PackageWaybill.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;					
			}
			
			CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
			objCoreWayBillCheck.checkFinanceForSignOut(strCwcode, 
					objHousewaybillColumns.getCcococode(), 
					strOperID, 
					objHousewaybillColumns.getPkpkcode(),
					objHousewaybillColumns.getHwhwsignindate(),
					false,
					objPUCollection);
			if (!objPUCollection.canGo(false)) {			
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;
			}
			// 不欠费则可以直接出货
			objHousewaybillColumns.setCwscwscode("IP");
			objHousewaybillColumns.setSchnchncode(objBatchwaybillColumns.getChnchncode());
			objHousewaybillColumns.setScococode(objBatchwaybillColumns.getCococode());
		}
		SimplebatchwaybillColumns objSBWBC = null;
		// 效验运单状态
		if (objSBVC == null) {
			objPUCollection = checkSignout(objHousewaybillColumns);
			if (!objPUCollection.canGo(false)) {
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;
			}
			objSBWBC = BatchWayBillDemand.loadSimpleBatchwaybill(strBwvid);
			if (objSBWBC == null) {
				objPUCollection.add("E_001", 
						"选择出货的小包号码不存在，请您确认是否运单出货或者是否选择了需要出货的小包号码", 
						"PackageWaybill.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;					
			}
			if (StringUtility.isNull(objHousewaybillColumns.getSchnchncode()) || 
					!objHousewaybillColumns.getSchnchncode().equals(objSBWBC.getChnchncode())) {
				objPUCollection.add("E_001", 
						"运单的渠道名称与主单的渠道名称不一致无法出货", 
						"PackageWaybill.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;				
			}
			// 效验产品是否一致
			objSBVC = BatchWayBillDemand.loadBWVCorewaybill(strBwvid);
			/*
			HousewaybillColumns objHWPackage = HousewaybillDemand.loadByCwcode(objSBVC.getBwvcw_code());
			if (!objHousewaybillColumns.getPkpkcode().equals(objHWPackage.getPkpkcode())) {
				objPUCollection.add("E_001", 
						"运单的产品与小包号码的产品不一致无法出货", 
						"PackageWaybill.save");
				objSRUtility.setPromptUtilityCollection(objPUCollection);
				return objSRUtility;			
			}
			*/		
			objHousewaybillColumns.setDbwvbwbvid(Long.parseLong(strBwvid));			
		}
		
		objHousewaybillColumns.setCwscwscode("SO");
		String strBwcode = "";
		if (objSBWBC != null)
			strBwcode = objSBWBC.getBwbwcode();
		else
			strBwcode = objBatchwaybillColumns.getBwbwcode();
		
		objHousewaybillColumns.setDbwbwcode(Long.parseLong(strBwcode));
		objHousewaybillColumns.setHwhwsignoutdate(DateFormatUtility.getSysdate());
		
		List listPieces = CorewaybillpiecesDemand.load(objHousewaybillColumns.getHwcwcode());
		if (listPieces == null || listPieces.size() < 1) {
			CorewaybillpiecesColumns objCWPC = new CorewaybillpiecesColumns();
			objCWPC.setCpcpgrossweight(new BigDecimal(objHousewaybillColumns.getCwcwgrossweight()));
			objCWPC.setCpcplength(new BigDecimal("0"));
			objCWPC.setCpcpheight(new BigDecimal("0"));
			objCWPC.setCpcpwidth(new BigDecimal("0"));
			listPieces.add(objCWPC);
		}
		for (int i = 0; i < listPieces.size(); i++) {
			CorewaybillpiecesColumns objCWPC = (CorewaybillpiecesColumns)listPieces.get(i);
			objCWPC.setCpcpbaglabelcode(objSBVC.getBwvbwbv_baglabelcode());
		}		
		
		SaveWaybillTransaction objSWT = new SaveWaybillTransaction();
		objSWT.setSignOutParam(objHousewaybillColumns, strBwcode, 
				listPieces, 
				strOperID,
				"");
		objSWT.execute();
		
		// 新增出货轨迹
		Track objTrack = new Track();
		objTrack.addSingleTrack(objHousewaybillColumns.getHwcwcode(), 
				objHousewaybillColumns.getOdtdtcode(), 
				"OC", 
				strOperID, 
				DateFormatUtility.getStandardSysdate());		
		
		objSRUtility.setPromptUtilityCollection(objPUCollection);
		objSRUtility.setColumns(objHousewaybillColumns);
		
		return objSRUtility;		
	}
	
	/**
	 * 出货效验
	 * @param objHousewaybillColumns
	 * @return
	 * @throws Exception
	 */
	private PromptUtilityCollection checkSignout(HousewaybillColumns objHousewaybillColumns) 
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		String strCwscode = objHousewaybillColumns.getCwscwscode();
		if (StringUtility.isNull(strCwscode) || 
				(!strCwscode.equals("IP"))) {
			objPUCollection.add("E_001", 
					"运单状态不为制单状态无法出货", 
					"PackageWaybill.save");
			return objPUCollection;			
		}
		// 到货的小包号码没有出货则不允许出货
		// 暂时屏蔽
		/*
		String strAbwvid = objHousewaybillColumns.getAbwvbwbvid();
		SimplebatchwbvalueColumns objSBWVC = BatchWayBillDemand.loadBWVCorewaybill(strAbwvid);
		String strCwcode = objSBWVC.getBwvcw_code();
		HousewaybillColumns objAbwHousewaybill = HousewaybillDemand.loadByCwcode(strCwcode);
		strCwscode = objAbwHousewaybill.getCwscwscode();
		if (StringUtility.isNull(strCwscode) || 
				(!strCwscode.equals("SO"))) {
			objPUCollection.add("E_001", 
					"请首先将该票件的小包号码出货", 
					"PackageWaybill.save");
			return objPUCollection;			
		}
		*/
		return objPUCollection;
	}
	
}
