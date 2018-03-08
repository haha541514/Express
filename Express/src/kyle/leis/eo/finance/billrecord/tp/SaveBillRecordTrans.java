package kyle.leis.eo.finance.billrecord.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeColumns;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeCondition;
import kyle.leis.eo.billing.incidentalfee.dax.IncidentalfeeDemand;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.da.PayableforbillCondition;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.payable.dax.PayableforbillCD;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableforbillCondition;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;
import kyle.leis.eo.finance.billrecord.dax.BillRecordDemand;
import kyle.leis.eo.finance.billrecord.dax.IBillRecordBasicData;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.es.company.customer.da.CustomerColumns;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.hi.TdiBillrecordstatus;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiBillrecord;
import net.sf.hibernate.Session;

public class SaveBillRecordTrans extends AbstractTransaction {
	private BillrecordColumns m_objBillrecordColumns;
	private List m_listReceivable;
	private List m_listPayable;
	private List m_listIncidentalfee;
	private String m_strOperId;
	private boolean m_isOriginCurrency = false;
	
	private Long m_lNewBR_ID;
	private TfiBillrecord m_objTfiBillrecord;
	
	/**
	 * ֱ���տ�ʱ�����˵������˵�
	 * @param m_objCashrecordColumns
	 * @param strOperId
	 * @throws Exception
	 */
	public void setDCCashParam(CashrecordColumns objCashrecordColumns,
			String strOperId, 
			String[] astrCwCode) throws Exception {
		if (astrCwCode == null || astrCwCode.length < 1) return;
		// �����˵�
		BillrecordColumns objBillrecordColumns = BillRecordDemand.buildByCashColumns(objCashrecordColumns);
		// �������
		m_listReceivable = new ArrayList<ReceivableColumns>();
		m_listPayable = new ArrayList<PayableColumns>();
		for (int i = 0; i < astrCwCode.length; i++) {
			// ��ѯӦ�շ���
			ReceivableforbillCondition objRvCondition = new ReceivableforbillCondition();
			objRvCondition.setCwcode(astrCwCode[i]);
			objRvCondition.setFscode("C");
			List listCwReceivable = ReceivableDemand.queryForBill(objRvCondition);
			if (listCwReceivable != null && listCwReceivable.size() > 0)
				m_listReceivable.addAll(ReceivableDemand.transferBillRvToRvColumns(listCwReceivable));
			// ��ѯӦ������
			PayableforbillCondition objPyCondition = new PayableforbillCondition();
			objPyCondition.setCwcode(astrCwCode[i]);
			objPyCondition.setFscode("C");
			List listPayable = PayableDemand.queryForBill(objPyCondition);
			if (listPayable != null && listPayable.size() > 0)
				m_listPayable.addAll(PayableDemand.transferBillPyToPyColumns(listPayable));
		}
		m_objBillrecordColumns = objBillrecordColumns;
		m_strOperId = strOperId;
	}
	
	
	public void setParam(BillrecordColumns objBillrecordColumns, 
			String strOperId,String isCheckBill) throws Exception {
		m_objBillrecordColumns = objBillrecordColumns;
		// �������
		m_listReceivable = new ArrayList<ReceivableColumns>();
		m_listPayable = new ArrayList<PayableColumns>();
		
		m_listIncidentalfee = new ArrayList<IncidentalfeeColumns>();
		
		// ��ѯδ���˵�Ӧ�շ���
		ReceivableforbillCondition objRvCondition = new ReceivableforbillCondition();
		objRvCondition.setCocode(objBillrecordColumns.getCococode());
		objRvCondition.setChncode(objBillrecordColumns.getChnchncode());
		objRvCondition.setEndrvoccurdate(objBillrecordColumns.getBrbroccurdate());
		objRvCondition.setFscode("C");
		if(isCheckBill.equals("Y")){
			objRvCondition.setCwswequalsign(isCheckBill);//�Ƿ�ֻ�����Ѿ������˵�
		}
		if(!StringUtility.isNull(objBillrecordColumns.getCococode())){
			CustomerColumns objCustomer = CustomerDemand.load(objBillrecordColumns.getCococode());
			if(objCustomer != null){
				String customerType = objCustomer.getCtctcode();
				if(customerType.equals("BP")){
					objRvCondition.setHwowninputcwauditsign("Y");//�Ƿ�ֻ��������˵ķ���
				}
			}
		}
		List listCwReceivable = ReceivableDemand.queryForBill(objRvCondition);
		if (listCwReceivable != null && listCwReceivable.size() > 0)
			m_listReceivable.addAll(ReceivableDemand.transferBillRvToRvColumns(listCwReceivable));
		// ��ѯӦ������
		PayableforbillCD objPayableforbillCD = new PayableforbillCD();
		objPayableforbillCD.copyFromReceivable(objRvCondition);
		List listPayable = PayableDemand.queryForBill(objPayableforbillCD);
		if (listPayable != null && listPayable.size() > 0)
			m_listPayable.addAll(PayableDemand.transferBillPyToPyColumns(listPayable));
		
		//��ѯ�ӷ���Ŀ
		IncidentalfeeCondition objIncidentalfeeCon = new IncidentalfeeCondition();
		objIncidentalfeeCon.setFscode("C");
		objIncidentalfeeCon.setCocode(objBillrecordColumns.getCococode());
		objIncidentalfeeCon.setChncode(objBillrecordColumns.getChnchncode());
		objIncidentalfeeCon.setEndoccurdate(objBillrecordColumns.getBrbroccurdate());
		if(CollectionUtility.isNull(m_listIncidentalfee))
			m_listIncidentalfee.addAll(IncidentalfeeDemand.query(objIncidentalfeeCon)); 
		
		m_strOperId = strOperId;
	}
	
	/*public void setParam(BillrecordColumns objBillrecordColumns,
			List listReceivable,
			List listPayable,
			String strOperId) {
		m_objBillrecordColumns = objBillrecordColumns;
		m_listReceivable = listReceivable;
		m_listPayable = listPayable;
		m_strOperId = strOperId;
	}*/
	public void setParam(BillrecordColumns objBillrecordColumns,
			List listReceivable,
			List listPayable,
			List listIncidentalfee,
			String strOperId,
			boolean isOriginCurrency) {
		m_objBillrecordColumns = objBillrecordColumns;
		m_listReceivable = listReceivable;
		m_listPayable = listPayable;
		m_listIncidentalfee = listIncidentalfee;
		m_strOperId = strOperId;
		m_isOriginCurrency = isOriginCurrency;
	}
	
	
	public Long getNewBrid() {
		return m_lNewBR_ID;
	}
	
	public TfiBillrecord getNewBillrecord() {
		return m_objTfiBillrecord;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objBillrecordColumns == null) return;
		if ((m_listReceivable == null || m_listReceivable.size() < 1) &&
				(m_listPayable == null || m_listPayable.size() < 1) && 
				(m_listIncidentalfee == null || m_listIncidentalfee.size() < 1))
			return;
		// �����˵�
		TfiBillrecord objTfiBillrecord = null;
		if (StringUtility.isNull(m_objBillrecordColumns.getBrbrid())) {
			objTfiBillrecord = new TfiBillrecord();
			// ������
			if (!StringUtility.isNull(m_strOperId)) {
				TdiOperator objCOP = (TdiOperator)objSession.load(TdiOperator.class, 
						Long.parseLong(m_strOperId));
				objTfiBillrecord.setTdiOperatorByBrOpIdCreator(objCOP);
				objTfiBillrecord.setBrCreatedate(DateFormatUtility.getSysdate());
			}
			objTfiBillrecord.setBrLablecode(BillRecordDemand.buildBillLabelcode(m_objBillrecordColumns));
			// ״̬
			TdiBillrecordstatus objTdiBillrecordstatus = (TdiBillrecordstatus)objSession.load(TdiBillrecordstatus.class, 
					IBillRecordBasicData.BRS_CONFIRM);
			objTfiBillrecord.setTdiBillrecordstatus(objTdiBillrecordstatus);			
		} else {
			objTfiBillrecord = (TfiBillrecord)objSession.load(TfiBillrecord.class, 
					Long.parseLong(m_objBillrecordColumns.getBrbrid()));
		}
		// ԭ����
		if (m_isOriginCurrency) {
			if (m_listReceivable != null && m_listReceivable.size() > 0) {
				ReceivableColumns objReceivableColumns = (ReceivableColumns)m_listReceivable.get(0);
				if (!StringUtility.isNull(objReceivableColumns.getCkckcode()))
					m_objBillrecordColumns.setCkckcode(objReceivableColumns.getCkckcode());
			} else if (m_listPayable != null && m_listPayable.size() > 0) {
				PayableColumns objPayableColumns = (PayableColumns)m_listPayable.get(0);
				if (!StringUtility.isNull(objPayableColumns.getCkckcode()))
					m_objBillrecordColumns.setCkckcode(objPayableColumns.getCkckcode());
			} else if (m_listIncidentalfee != null && m_listIncidentalfee.size() > 0) {
				IncidentalfeeColumns objIncidentalfeeCol = (IncidentalfeeColumns)m_listIncidentalfee.get(0);
				if (!StringUtility.isNull(objIncidentalfeeCol.getCkckcode()))
					m_objBillrecordColumns.setCkckcode(objIncidentalfeeCol.getCkckcode());				
			}
		}
		BillRecordDemand.setBillRecordByColumns(objTfiBillrecord, 
				m_objBillrecordColumns, 
				m_strOperId, 
				objSession);
        // ����totalֵ
		BigDecimal objRvActualTotal = ReceivableDemand.sumActualTotal(m_listReceivable, m_isOriginCurrency);
		BigDecimal objPyActualTotal = PayableDemand.sumActualTotal(m_listPayable, m_isOriginCurrency);
		// �ӷ�
		BigDecimal objIncidentalfeeTotal = IncidentalfeeDemand.sumActualTotal(m_listIncidentalfee);
		
		// ԭ�˵����
		BigDecimal objBrTotal = new BigDecimal("0");
		if (objTfiBillrecord.getBrTotal() != null)
			objBrTotal = objTfiBillrecord.getBrTotal();
		objBrTotal = objBrTotal.add(objRvActualTotal);
		objBrTotal = objBrTotal.add(objIncidentalfeeTotal);//����ӷ�
		// ԭ�˵���� + Ӧ�ս�� - Ӧ�����
		objTfiBillrecord.setBrTotal(objBrTotal.add(objPyActualTotal.multiply(new BigDecimal("-1"))));
		objSession.save(objTfiBillrecord);
		
		m_lNewBR_ID = objTfiBillrecord.getBrId();
		m_objTfiBillrecord = objTfiBillrecord;
		// �޸�Ӧ�շ���״̬
		if (m_listReceivable != null && m_listReceivable.size() > 0)
			for (int i = 0; i < m_listReceivable.size(); i++) {
				ReceivableColumns objReceivableColumns = (ReceivableColumns)m_listReceivable.get(i);
				String strRvid = objReceivableColumns.getRvrvid();	
				String strUpdateSql = "UPDATE T_BL_RECEIVABLE rv " + 
				" SET FS_CODE = 'B'," +	
				" BR_ID = " + objTfiBillrecord.getBrId() + 
				" WHERE rv.RV_ID = " + strRvid;	
				execute(objSession, strUpdateSql);
			}
		// �޸�Ӧ������״̬
		if (m_listPayable != null && m_listPayable.size() > 0)
			for (int i = 0; i < m_listPayable.size(); i++) {
				PayableColumns objPayableColumns = (PayableColumns)m_listPayable.get(i);
				String strPyid = objPayableColumns.getPypyid();
				String strUpdateSql = "UPDATE T_BL_PAYABLE py " + 
				" SET FS_CODE = 'B'," +
				" BR_ID = " + objTfiBillrecord.getBrId() + 
				" WHERE py.PY_ID = " + strPyid;
				execute(objSession, strUpdateSql);
			}
		
		//�޸��ӷ�״̬
		if(m_listIncidentalfee != null && m_listIncidentalfee.size() >0)
		{
			String strSysdate = DateFormatUtility.getStandardSysdate();
			for(int i=0;i<m_listIncidentalfee.size();i++)
			{
				IncidentalfeeColumns objIncidentalfeeCol = (IncidentalfeeColumns)m_listIncidentalfee.get(i);
				String strIfid = objIncidentalfeeCol.getIfifid();
				String strUpdateSql = "UPDATE T_BL_INCIDENTALFEE tif " +
				" SET FS_CODE = 'B'," +
				" BR_ID = " + objTfiBillrecord.getBrId() + 
				" , IF_MODIFYDATE = " + "TO_DATE('"+strSysdate+"','" +"YYYY-MM-DD HH24:MI:SS"+"')"+
				" , OP_ID_MODIFY = " + m_strOperId +
				" WHERE tif.IF_ID = " + strIfid;
				execute(objSession,strUpdateSql);
			}
		}
		
		/*if(m_listIncidentalfee != null && m_listIncidentalfee.size() >0)
		for(int i=0;i<m_listIncidentalfee.size();i++)
		{
			ModifyStatusTransaction objModifyStatusTrans = new ModifyStatusTransaction();
			objModifyStatusTrans.setParam(((IncidentalfeeColumns)m_listIncidentalfee.get(i)).getIfifid(), "B", objTfiBillrecord,m_strOperId);
			objModifyStatusTrans.execute();
		}*/
		
		// �����˵����
		objTfiBillrecord.setBrLablecode(String.valueOf(objTfiBillrecord.getBrId()));
		objSession.update(objTfiBillrecord);
		
	}
}
