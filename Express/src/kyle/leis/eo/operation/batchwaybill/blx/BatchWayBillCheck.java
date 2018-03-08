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
		// ��鹫˾�Ƿ�Ϊ��
		if (StringUtility.isNull(objBWColumns.getCococode()) &&
				StringUtility.isNull(objBWColumns.getChnchncode()))
			objSavingPUC.add("E_BW_001", 
					"�����ܵ�ʱ��˾����Ϊ�գ�", 
					"BatchWayBillCheck.checkSaving");
		// ��鵽��ʱ��
		String strAdddate = objBWColumns.getBwadddate();
		// һ����{Y�Ϳձ�ʾҪ����ʱ��}
		if(objBWColumns.getBwsbwsname().equals("Y")||StringUtility.isNull(objBWColumns.getBwsbwsname())){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.YEAR, -1);  
			long date = cal.getTimeInMillis();
			format.setLenient(false);
			format.format(new Date(date));
			
			if (DateUtility.compareDate(strAdddate, format.format(new Date(date))))
				objSavingPUC.add("E_BW_002", 
						"�������ڱ����ǵ�ǰʱ���һ����", 
						"BatchWayBillCheck.checkSaving");
//			if (DateUtility.compareDate(DateUtility.getMoveDate(strSysdate, 5),strAdddate))
//				objSavingPUC.add("E_BW_002", 
//						"�������ڱ����ǵ�ǰʱ���һ���ںͺ�����", 
//						"BatchWayBillCheck.checkSaving");
		}
		// ������Ա�ͷֲ�֮��Ĺ�ϵ
		
		
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
		// �����ܵ����
		if (StringUtility.isNull(objBWColumns.getChnchncode()))
			objSavingPUC.add("E_BW_003", 
					"�����ܵ�������������Ϊ��", 
					"BatchWayBillCheck.checkDepartureSaving");
		return objSavingPUC;
	}
	
	public PromptUtilityCollection checkComplete(BatchwaybillColumns objBWColumns) 
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// ���״̬
		if (StringUtility.isNull(objBWColumns.getBwsbwscode()))
			objPUCollection.add("E_BW_004", 
					"���ȱ�������������ɸ�����", 
					"BatchWayBillCheck.checkComplete");
		if (!objBWColumns.getBwsbwscode().equals(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_NEW))
			objPUCollection.add("E_BW_005", 
					"������Ϊ�½�״̬�������������",
					"BatchWayBillCheck.checkComplete");
		return objPUCollection;
	}

	public PromptUtilityCollection checkEliminate(BatchwaybillColumns objBWColumns) 
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// ���״̬
		if (StringUtility.isNull(objBWColumns.getBwsbwscode()))
			objPUCollection.add("E_BW_004", 
					"���ȱ��������������ϸ�����", 
					"BatchWayBillCheck.checkComplete");
		if (!objBWColumns.getBwsbwscode().equals(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_NEW))
			objPUCollection.add("E_BW_005", 
					"������Ϊ�½�״̬��������������",
					"BatchWayBillCheck.checkComplete");
		// �Ƿ�����˵�
		
		
		
		return objPUCollection;
	}
}
