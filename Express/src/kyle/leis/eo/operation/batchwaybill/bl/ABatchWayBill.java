package kyle.leis.eo.operation.batchwaybill.bl;

import java.util.List;

import com.ems.HKEMSInterface;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.batchwaybill.blx.BatchWayBillCheck;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.dax.IBatchWayBillBasicData;
import kyle.leis.eo.operation.batchwaybill.tp.SaveBatchwaybillTrans;
import kyle.leis.eo.operation.housewaybill.da.CorewaybillofdepColumns;
import kyle.leis.eo.operation.housewaybill.da.CorewaybillofdepQuery;
import kyle.leis.es.smsservice.bl.AutoSendSms;
import kyle.leis.es.smsservice.bl.WeChatMsgSend;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.hi.TchnChannel;

public abstract class ABatchWayBill {
	// ����
	public SavedResultUtility save(BatchwaybillColumns objBWColumns,
			String strOperId, 
			boolean isIgnoreNotice) throws Exception {
		SavedResultUtility objSavedResultUtility = new SavedResultUtility();
		rebuildSavedColumns(objBWColumns);
		// Ч��
			PromptUtilityCollection objPUCollection = checkBeforeSave(objBWColumns);	
		// ������µ����κ�
		if (StringUtility.isNull(objBWColumns.getBwbwcode())) {
			String strNewBatchnumber = BatchWayBillDemand.getNewBatchnumber(objBWColumns.getChnchncode(),
					objBWColumns.getCococode(),
					objBWColumns.getEeeecode(),
					objBWColumns.getBwadddate(),
					objBWColumns.getAdtadtcode());
			if (strNewBatchnumber.compareTo("Z") > 0) {
				objPUCollection.add("E_BW_001", 
						"�ù�˾һ��Ľ������������Ѿ�������������κŵ����ƣ�", 
						"BatchWayBillCheck.checkSaving");
			}
			objBWColumns.setBwbwbatchnumber(strNewBatchnumber);
		}
		if (objPUCollection.canGo(isIgnoreNotice)) {
			// ����
			objBWColumns = save(objBWColumns, strOperId);
			objSavedResultUtility.setColumns(objBWColumns);
		}
		objSavedResultUtility.setPromptUtilityCollection(objPUCollection);
		return objSavedResultUtility;
	}
	
	private BatchwaybillColumns save(BatchwaybillColumns objBWColumns,
			String strOperId) throws Exception {
		SaveBatchwaybillTrans objSaveBatchwaybillTrans = new SaveBatchwaybillTrans();
		objSaveBatchwaybillTrans.setParam(objBWColumns, strOperId);
		objSaveBatchwaybillTrans.execute();
		Long lNewBwcode = objSaveBatchwaybillTrans.getNewBwcode();
		return BatchWayBillDemand.load(String.valueOf(lNewBwcode));		
	}
	
	// ���
	public SavedResultUtility complete(String strBwcode, 
			String strOperId, 
			boolean isIgnoreNotice) throws Exception {
		SavedResultUtility objSavedResultUtility = new SavedResultUtility();
		BatchwaybillColumns objBatchwaybillColumns = BatchWayBillDemand.load(strBwcode);
		// Ч��
		PromptUtilityCollection objPUCollection = checkBeforeComplete(objBatchwaybillColumns);
		if (objPUCollection.canGo(isIgnoreNotice)) {
			if (!StringUtility.isNull(objBatchwaybillColumns.getAdtadtcode()) &&
					objBatchwaybillColumns.getAdtadtcode().equals("D")) {
				// ȷ�ϳ���
				objPUCollection = exportOfEMSImport(objBatchwaybillColumns);
				if (objPUCollection != null && !objPUCollection.canGo(false)) {
					objSavedResultUtility.setPromptUtilityCollection(objPUCollection);
					return objSavedResultUtility;
				}
			}
			objBatchwaybillColumns.setBwsbwscode(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_CONFIRM);
			objBatchwaybillColumns = save(objBatchwaybillColumns, strOperId);
			objSavedResultUtility.setColumns(objBatchwaybillColumns);
		}
		//��������͵Ķ��ż�¼
		if (!StringUtility.isNull(objBatchwaybillColumns.getAdtadtcode()) &&
				objBatchwaybillColumns.getAdtadtcode().equals("A")) {
			try {
				AutoSendSms objAutoSendSms = new /*AutoSendSms()*/ WeChatMsgSend();
				objAutoSendSms.saveAutoSmsmessage(objBatchwaybillColumns.getCococode(), 
						"SNK001", 
						objBatchwaybillColumns.getBwbwcode());

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		objSavedResultUtility.setPromptUtilityCollection(objPUCollection);
		return objSavedResultUtility;
	}
	
	private PromptUtilityCollection exportOfEMSImport(BatchwaybillColumns objBatchwaybillColumns) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(objBatchwaybillColumns.getChnchncode()))
			return objPUCollection;
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objBatchwaybillColumns.getChnchncode());
		if (objTchnChannel.getTdiServerstructuregroup() != null && 
				objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("EMSIM")) {
			CorewaybillofdepQuery objCODQ = new CorewaybillofdepQuery();
			objCODQ.setBwcodedeparture(objBatchwaybillColumns.getBwbwcode());
			List listResults = objCODQ.getResults();
			if (listResults == null || listResults.size() < 1)
				return objPUCollection;
			String[] astrServerEwbcode = new String[listResults.size()];
			for (int i = 0; i < listResults.size(); i++) {
				CorewaybillofdepColumns objCDColumns = (CorewaybillofdepColumns)listResults.get(i);
				astrServerEwbcode[i] = objCDColumns.getCwcw_serverewbcode();
			}
			HKEMSInterface objHKEMSInterface = new HKEMSInterface();
			return objHKEMSInterface.export(astrServerEwbcode, 
					objBatchwaybillColumns.getBwbwcontainerid(), 
					DateFormatUtility.getStandardDate(objBatchwaybillColumns.getBwadddate()), 
					"");
		}
		return objPUCollection;
	}
	
	
	public SavedResultUtility undocomplete(String strBwcode, 
			String strOperId, 
			boolean isIgnoreNotice) throws Exception {
		SavedResultUtility objSavedResultUtility = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		BatchwaybillColumns objBatchwaybillColumns = BatchWayBillDemand.load(strBwcode);
		// Ч��
		objBatchwaybillColumns.setBwsbwscode(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_NEW);
		objBatchwaybillColumns = save(objBatchwaybillColumns, strOperId);
		
		objSavedResultUtility.setColumns(objBatchwaybillColumns);
		objSavedResultUtility.setPromptUtilityCollection(objPUCollection);
		return objSavedResultUtility;
	}
	
	// ����
	public SavedResultUtility eliminate(String strBwcode, 
			String strOperId, 
			boolean isIgnoreNotice) throws Exception {
		SavedResultUtility objSavedResultUtility = new SavedResultUtility();
		BatchwaybillColumns objBatchwaybillColumns = BatchWayBillDemand.load(strBwcode);
		// Ч��
		PromptUtilityCollection objPUCollection = checkBeforeComplete(objBatchwaybillColumns);
		if (objPUCollection.canGo(isIgnoreNotice)) {
			objBatchwaybillColumns.setBwsbwscode(IBatchWayBillBasicData.BATCHWAYBILL_STATUS_ELIMINATE);
			objBatchwaybillColumns = save(objBatchwaybillColumns, strOperId);
			objSavedResultUtility.setColumns(objBatchwaybillColumns);
		}
		objSavedResultUtility.setPromptUtilityCollection(objPUCollection);
		return objSavedResultUtility;
	}
	
	/**
	 * ���ڵ���������ܵ����ø��Ե�����
	 * @param objBWColumns
	 */
	protected abstract void rebuildSavedColumns(BatchwaybillColumns objBWColumns);
	// Ч��
	protected abstract PromptUtilityCollection checkBeforeSave(BatchwaybillColumns objBWColumns);
	
	/**
	 * ���ǰЧ��
	 * @param objBWColumns
	 * @return
	 * @throws Exception
	 */
	protected PromptUtilityCollection checkBeforeComplete(BatchwaybillColumns objBWColumns) 
	throws Exception {
		BatchWayBillCheck objBatchWayBillCheck = new BatchWayBillCheck();
		return objBatchWayBillCheck.checkComplete(objBWColumns);
	}
	
	/**
	 * ����ǰЧ��
	 * @param objBWColumns
	 * @return
	 * @throws Exception
	 */
	protected PromptUtilityCollection checkBeforeEliminate(BatchwaybillColumns objBWColumns) 
	throws Exception {
		BatchWayBillCheck objBatchWayBillCheck = new BatchWayBillCheck();
		return objBatchWayBillCheck.checkEliminate(objBWColumns);		
	}
}
