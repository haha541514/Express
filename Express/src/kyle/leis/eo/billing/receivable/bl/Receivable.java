package kyle.leis.eo.billing.receivable.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.ReceivableColumnsForImport;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.billing.receivable.tp.DelelteReceivableTrans;
import kyle.leis.eo.billing.receivable.tp.ModifyReceivableStatusTrans;
import kyle.leis.eo.billing.receivable.tp.SaveReceivableTrans;
import kyle.leis.eo.finance.financialstatistics.blx.FinancialstatisticsThread;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

public class Receivable {
	public void save(List listRvColumns, 
			String strCwcode, 
			String strOperId) throws Exception {
		// ��ѯ��ԭʼ����
		List listOriginRvColumns = new ArrayList();
		if (!StringUtility.isNull(strCwcode)) {
			listOriginRvColumns = ReceivableDemand.load(strCwcode);
		}		
		SaveReceivableTrans objSaveReceivableTrans = new SaveReceivableTrans();
		objSaveReceivableTrans.setParam(listRvColumns, 
				strCwcode, 
				strOperId,
				false,
				listOriginRvColumns);
		objSaveReceivableTrans.execute();
		// �������
		FinancialstatisticsThread objFSThread = new FinancialstatisticsThread(listOriginRvColumns,
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
				strCwcode);
		objFSThread.start();
	}
	
	public void modifyStatus(String[] astrRvid,
			String strOperId,
			String strFscode) throws Exception {
		ModifyReceivableStatusTrans objMRvStatusTrans = new ModifyReceivableStatusTrans();
		objMRvStatusTrans.setParam(astrRvid, strOperId, strFscode);
		objMRvStatusTrans.execute();
	}
	
	/**
	 * ɾ��ĳЩ����
	 * @param astrRvid
	 * @param strOperId
	 * @throws Exception
	 */
	public void delete(String[] astrRvid,
			String strOperId) throws Exception {
		// ��ѯԭ����
		DelelteReceivableTrans objDRTrans = new DelelteReceivableTrans();
		objDRTrans.setParam(astrRvid, strOperId);
		// ���ԭʼ����
		String strCwcode = objDRTrans.getDeleteOriginCwcode();
		List listOriginRvColumns = ReceivableDemand.load(strCwcode);
		
		List<ReceivableColumns> listRevColumns = objDRTrans.getOriginReceivable();
		// ��ѯ�ɷ���
		if (listRevColumns == null || listRevColumns.size() < 1)
			return;		
		// ִ������
		objDRTrans.execute();
		// �������
		FinancialstatisticsThread objFSThread = new FinancialstatisticsThread(listOriginRvColumns,
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
				strCwcode);
		objFSThread.start();
	}
	
	/**
	 * ɾ�����з���
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void deleteAll(String strCwcode, 
			String strOperId) throws Exception {
		// ԭʼ���
		List listOriginRvColumns = ReceivableDemand.load(strCwcode);
		
		DelelteReceivableTrans objDRTrans = new DelelteReceivableTrans();
		objDRTrans.setDelAllParam(strCwcode, strOperId);
		objDRTrans.execute();
		// �������
		FinancialstatisticsThread objFSThread = new FinancialstatisticsThread(listOriginRvColumns,
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
				strCwcode);
		objFSThread.start();		
	}
	
	public PromptUtilityCollection importReceivable(List listRvColumnsforimport,
			String strOperID) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (listRvColumnsforimport == null || listRvColumnsforimport.size() < 1)
			return objPUCollection;
		ReceivableColumnsForImport objRCI = (ReceivableColumnsForImport)listRvColumnsforimport.get(0);
		String strCwcustomerewbcode = objRCI.getCwcustomerewbcode();
		HousewaybillColumns objHWColumns = HousewaybillDemand.load(strCwcustomerewbcode, 
				ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER);
		if (objHWColumns == null) {
			objPUCollection.add("E_001", "�����ڵ��˵���", "importReceivable");
			return objPUCollection;
		}
			
		try {
			List listRvcolumns = ReceivableDemand.transferImportRvToColumns(objHWColumns, 
					listRvColumnsforimport, 
					strOperID);
			// ����
			save(listRvcolumns, objHWColumns.getHwcwcode(), strOperID);
		} catch (Exception ex) {
			objPUCollection.add("E_001", ex.toString(), "importReceivable");
			return objPUCollection;
		}
		
		return objPUCollection;
	}
}
