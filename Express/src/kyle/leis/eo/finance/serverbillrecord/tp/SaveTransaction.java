package kyle.leis.eo.finance.serverbillrecord.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiServerbillrecord;

public class SaveTransaction extends AbstractTransaction {
	private ServerbillrecordColumns m_objSBRColumns;
	private List m_listSaveBilldetail;
	private String m_strOperId;
	private String m_strNewSbrId;
	
	public void setParam(ServerbillrecordColumns objSBRColumns,
			List listSaveBilldetail,
			String strOperId) {
		m_objSBRColumns = objSBRColumns;
		m_listSaveBilldetail = listSaveBilldetail;
		m_strOperId = strOperId;
	}
	
	public String getSavedSbrId() {
		return m_strNewSbrId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objSBRColumns == null) return;
		// 保存账单和账单明细
		String strSbrId = m_objSBRColumns.getSbrsbrid();
		String strSbrLabelcode = m_objSBRColumns.getSbrsbrlabelcode();
		TfiServerbillrecord objTSBR = null;
		if (StringUtility.isNull(strSbrId)) {
			// 新增分两种情况，
			// 1、系统中不存在账单编号，则直接新增账单明细
			// 2、系统中已经存在此账单编号，则修改原账单总金额、合并单票件的费用、重量等。
			ServerbillrecordColumns objSBRColumns = ServerBillRecordDemand.loadByLabelcode(strSbrLabelcode,
					m_objSBRColumns.getChnchncode());
			if (objSBRColumns == null || 
					StringUtility.isNull(objSBRColumns.getSbrsbrid())) {
				// 新增
				objTSBR = new TfiServerbillrecord();
				objTSBR.setSbrCreatedate(DateFormatUtility.getSysdate());
				TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
				objTSBR.setTdiOperatorByOpIdCreator(objTdiOperator);
			} else {
				// 合并账单明细
				objTSBR = (TfiServerbillrecord)objSession.load(TfiServerbillrecord.class, 
						Long.parseLong(objSBRColumns.getSbrsbrid()));
			}
		} else {
			// 修改账单，先删除原账单明细，再新增
			objTSBR = (TfiServerbillrecord)objSession.load(TfiServerbillrecord.class, 
					Long.parseLong(strSbrId));
		}
		// 保存服务商账单
		ServerBillRecordDemand.setBillRecordByColumns(m_objSBRColumns,
				objTSBR,
				m_strOperId,
				objSession);
		objSession.save(objTSBR);
		m_strNewSbrId = String.valueOf(objTSBR.getSbrId());
		// 保存服务商账单明细
		AddDetailTransation objAddTrans = new AddDetailTransation();
		objAddTrans.setParam(objTSBR, m_listSaveBilldetail);
		objAddTrans.transaction(objSession);
	}
}
