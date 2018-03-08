package kyle.leis.eo.operation.batchwaybill.blx;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.IBatchWayBillBasicData;

public class BatchWayBillCheck {
	private PromptUtilityCollection checkSaving(BatchwaybillColumns objBWColumns) {
		PromptUtilityCollection objSavingPUC = new PromptUtilityCollection();
		// 检查公司是否为空
		if (StringUtility.isNull(objBWColumns.getCococode()) &&
				StringUtility.isNull(objBWColumns.getChnchncode()))
			objSavingPUC.add("E_BW_001", 
					"创建总单时公司不能为空！", 
					"BatchWayBillCheck.checkSaving");
		// 检查到货时间
		String strAdddate = objBWColumns.getBwadddate();
		// 一年内{Y和空表示要限制时间}
		if(objBWColumns.getBwsbwsname().equals("Y")||StringUtility.isNull(objBWColumns.getBwsbwsname())){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.YEAR, -1);  
			long date = cal.getTimeInMillis();
			format.setLenient(false);
			format.format(new Date(date));
			
			if (DateUtility.compareDate(strAdddate, format.format(new Date(date))))
				objSavingPUC.add("E_BW_002", 
						"主单日期必须是当前时间的一年内", 
						"BatchWayBillCheck.checkSaving");
//			if (DateUtility.compareDate(DateUtility.getMoveDate(strSysdate, 5),strAdddate))
//				objSavingPUC.add("E_BW_002", 
//						"主单日期必须是当前时间的一年内和后五天", 
//						"BatchWayBillCheck.checkSaving");
		}
		// 检查操作员和分拨之间的关系
		
		
		return objSavingPUC;
	}
	
	public PromptUtilityCollection checkArrivalSaving(BatchwaybillColumns objBWColumns) {
		PromptUtilityCollection objSavingPUC = new PromptUtilityCollection();
		objSavingPUC.addAll(checkSaving(objBWColumns));		
		return objSavingPUC;
	}

	public PromptUtilityCollection checkDepartureSaving(BatchwaybillColumns objBWColumns) {
		PromptUtilityCollection objSavingPUC = checkSaving(objBWColumns);
		objSavingPUC.addAll(checkSaving(objBWColumns));
		// 出货总单检查
		if (StringUtility.isNull(objBWColumns.getChnchncode()))
			objSavingPUC.add("E_BW_003", 
					"出货总单服务渠道不能为空", 
					"BatchWayBillCheck.checkDepartureSaving");
		return objSavingPUC;
	}
	
	public PromptUtilityCollection checkComplete(BatchwaybillColumns objBWColumns) 
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// 检查状态
		if (StringUtility.isNull(objBWColumns.getBwsbwscode()))
			objPUCollection.add("E_BW_004", 
					"请先保存主单后再完成该主单", 
					"BatchWayBillCheck.checkComplete");
		if (!objBWColumns.getBwsbwscode().equals(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_NEW))
			objPUCollection.add("E_BW_005", 
					"主单不为新建状态，不能完成主单",
					"BatchWayBillCheck.checkComplete");
		return objPUCollection;
	}

	public PromptUtilityCollection checkEliminate(BatchwaybillColumns objBWColumns) 
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// 检查状态
		if (StringUtility.isNull(objBWColumns.getBwsbwscode()))
			objPUCollection.add("E_BW_004", 
					"请先保存主单后再作废该主单", 
					"BatchWayBillCheck.checkComplete");
		if (!objBWColumns.getBwsbwscode().equals(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_NEW))
			objPUCollection.add("E_BW_005", 
					"主单不为新建状态，不能作废主单",
					"BatchWayBillCheck.checkComplete");
		// 是否包含运单
		
		
		
		return objPUCollection;
	}
}
