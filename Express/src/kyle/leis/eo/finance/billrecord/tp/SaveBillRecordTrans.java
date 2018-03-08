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
	 * 直客收款时，按运单生成账单
	 * @param m_objCashrecordColumns
	 * @param strOperId
	 * @throws Exception
	 */
	public void setDCCashParam(CashrecordColumns objCashrecordColumns,
			String strOperId, 
			String[] astrCwCode) throws Exception {
		if (astrCwCode == null || astrCwCode.length < 1) return;
		// 构造账单
		BillrecordColumns objBillrecordColumns = BillRecordDemand.buildByCashColumns(objCashrecordColumns);
		// 构造费用
		m_listReceivable = new ArrayList<ReceivableColumns>();
		m_listPayable = new ArrayList<PayableColumns>();
		for (int i = 0; i < astrCwCode.length; i++) {
			// 查询应收费用
			ReceivableforbillCondition objRvCondition = new ReceivableforbillCondition();
			objRvCondition.setCwcode(astrCwCode[i]);
			objRvCondition.setFscode("C");
			List listCwReceivable = ReceivableDemand.queryForBill(objRvCondition);
			if (listCwReceivable != null && listCwReceivable.size() > 0)
				m_listReceivable.addAll(ReceivableDemand.transferBillRvToRvColumns(listCwReceivable));
			// 查询应付费用
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
		// 构造费用
		m_listReceivable = new ArrayList<ReceivableColumns>();
		m_listPayable = new ArrayList<PayableColumns>();
		
		m_listIncidentalfee = new ArrayList<IncidentalfeeColumns>();
		
		// 查询未出账的应收费用
		ReceivableforbillCondition objRvCondition = new ReceivableforbillCondition();
		objRvCondition.setCocode(objBillrecordColumns.getCococode());
		objRvCondition.setChncode(objBillrecordColumns.getChnchncode());
		objRvCondition.setEndrvoccurdate(objBillrecordColumns.getBrbroccurdate());
		objRvCondition.setFscode("C");
		if(isCheckBill.equals("Y")){
			objRvCondition.setCwswequalsign(isCheckBill);//是否只生成已经对账运单
		}
		if(!StringUtility.isNull(objBillrecordColumns.getCococode())){
			CustomerColumns objCustomer = CustomerDemand.load(objBillrecordColumns.getCococode());
			if(objCustomer != null){
				String customerType = objCustomer.getCtctcode();
				if(customerType.equals("BP")){
					objRvCondition.setHwowninputcwauditsign("Y");//是否只生产已审核的费用
				}
			}
		}
		List listCwReceivable = ReceivableDemand.queryForBill(objRvCondition);
		if (listCwReceivable != null && listCwReceivable.size() > 0)
			m_listReceivable.addAll(ReceivableDemand.transferBillRvToRvColumns(listCwReceivable));
		// 查询应付费用
		PayableforbillCD objPayableforbillCD = new PayableforbillCD();
		objPayableforbillCD.copyFromReceivable(objRvCondition);
		List listPayable = PayableDemand.queryForBill(objPayableforbillCD);
		if (listPayable != null && listPayable.size() > 0)
			m_listPayable.addAll(PayableDemand.transferBillPyToPyColumns(listPayable));
		
		//查询杂费项目
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
		// 生成账单
		TfiBillrecord objTfiBillrecord = null;
		if (StringUtility.isNull(m_objBillrecordColumns.getBrbrid())) {
			objTfiBillrecord = new TfiBillrecord();
			// 创建人
			if (!StringUtility.isNull(m_strOperId)) {
				TdiOperator objCOP = (TdiOperator)objSession.load(TdiOperator.class, 
						Long.parseLong(m_strOperId));
				objTfiBillrecord.setTdiOperatorByBrOpIdCreator(objCOP);
				objTfiBillrecord.setBrCreatedate(DateFormatUtility.getSysdate());
			}
			objTfiBillrecord.setBrLablecode(BillRecordDemand.buildBillLabelcode(m_objBillrecordColumns));
			// 状态
			TdiBillrecordstatus objTdiBillrecordstatus = (TdiBillrecordstatus)objSession.load(TdiBillrecordstatus.class, 
					IBillRecordBasicData.BRS_CONFIRM);
			objTfiBillrecord.setTdiBillrecordstatus(objTdiBillrecordstatus);			
		} else {
			objTfiBillrecord = (TfiBillrecord)objSession.load(TfiBillrecord.class, 
					Long.parseLong(m_objBillrecordColumns.getBrbrid()));
		}
		// 原币种
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
        // 计算total值
		BigDecimal objRvActualTotal = ReceivableDemand.sumActualTotal(m_listReceivable, m_isOriginCurrency);
		BigDecimal objPyActualTotal = PayableDemand.sumActualTotal(m_listPayable, m_isOriginCurrency);
		// 杂费
		BigDecimal objIncidentalfeeTotal = IncidentalfeeDemand.sumActualTotal(m_listIncidentalfee);
		
		// 原账单金额
		BigDecimal objBrTotal = new BigDecimal("0");
		if (objTfiBillrecord.getBrTotal() != null)
			objBrTotal = objTfiBillrecord.getBrTotal();
		objBrTotal = objBrTotal.add(objRvActualTotal);
		objBrTotal = objBrTotal.add(objIncidentalfeeTotal);//添加杂费
		// 原账单金额 + 应收金额 - 应付金额
		objTfiBillrecord.setBrTotal(objBrTotal.add(objPyActualTotal.multiply(new BigDecimal("-1"))));
		objSession.save(objTfiBillrecord);
		
		m_lNewBR_ID = objTfiBillrecord.getBrId();
		m_objTfiBillrecord = objTfiBillrecord;
		// 修改应收费用状态
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
		// 修改应付费用状态
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
		
		//修改杂费状态
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
		
		// 更新账单编号
		objTfiBillrecord.setBrLablecode(String.valueOf(objTfiBillrecord.getBrId()));
		objSession.update(objTfiBillrecord);
		
	}
}
