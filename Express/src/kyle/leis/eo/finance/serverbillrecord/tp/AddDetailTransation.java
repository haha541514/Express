package kyle.leis.eo.finance.serverbillrecord.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;
import kyle.leis.hi.TfiServerbillrecord;
import kyle.leis.hi.TfiServerpayable;
import kyle.leis.hi.TfiServerwaybill;

public class AddDetailTransation extends AbstractTransaction {
	private TfiServerbillrecord m_objTSBRecord; 
	private List m_listSavePayable;
	
	public void setParam(TfiServerbillrecord objSBRecord,
			List listSavePayable) {
		m_objTSBRecord = objSBRecord;
		m_listSavePayable = listSavePayable;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objTSBRecord == null) return;
		// 保存费用明细
		if (m_listSavePayable == null || m_listSavePayable.size() < 1)
			return;
		BigDecimal objTotalAmount = new BigDecimal("0");
		// 运单号对应的服务商运单记录
		HashMap<String, TfiServerwaybill> hmServerEwbWaybill = new HashMap<String, TfiServerwaybill>();
		// 需要更改统计信息的服务商运单
		List<TfiServerwaybill> listModifyWaybill = new ArrayList<TfiServerwaybill>();
		for (int i = 0; i < m_listSavePayable.size(); i++) {
			ServerpayableColumns objSPYColumns = (ServerpayableColumns)m_listSavePayable.get(i);
			/* debug
			if (objSPYColumns.getSwbswbserverewbcode().equals("9967578272") ||
					objSPYColumns.getSwbswbserverewbcode().equals("9970645344") ||
					objSPYColumns.getSwbswbserverewbcode().equals("9965299562") ||
					objSPYColumns.getSwbswbserverewbcode().equals("9969605085")) {
				objSPYColumns.setSwbswbserverewbcode(objSPYColumns.getSwbswbserverewbcode());
			}
			*/
			// 首先进行费用种类映射
			ServerBillRecordDemand.setServerPayableFkcode(objSPYColumns);
			// 如果已经存在此费用则不再重复导入数据
			boolean isExistsPayable = ServerBillRecordDemand.isExistsPayable(objSPYColumns.getSwbswbserverewbcode(), 
					objSPYColumns.getFkfkcode(), 
					objSPYColumns.getChnchncode(),
					m_objTSBRecord.getSbrLabelcode());
			if (isExistsPayable) continue;
			// 设置汇率
			Currency objCurrency = new Currency();
			String strCurrencyrate = objCurrency.getCurrencyrate("",  "A", 
					DateFormatUtility.getStandardDate(m_objTSBRecord.getSbrOccurdate()), 
					"1",
					objSPYColumns.getCkckcode());
			if (StringUtility.isNull(strCurrencyrate))
				throw (new Exception("无法获得服务商币种对应的汇率，币种为：" + 
						objSPYColumns.getCkckcode()));
			objSPYColumns.setSpyspycurrencyrate(new BigDecimal(strCurrencyrate));
			objSPYColumns.setChnchncode(m_objTSBRecord.getTchnChannel().getChnCode());
			// 先保存服务商运单
			// 1、如果系统中不存在此服务商单号则首先新增服务商运单数据
			// 2、如果已经存在则需要更改运单的费用统计信息
			// 3、需要保存的服务商费用明细中可能存在重复的服务商单号
			String strServerWBCode = objSPYColumns.getSwbswbserverewbcode();
			TfiServerwaybill objTfiServerwaybill;
			if (hmServerEwbWaybill.containsKey(strServerWBCode)) {
				// 运单重复则需要更新运单统计信息
				objTfiServerwaybill = hmServerEwbWaybill.get(strServerWBCode);
				ServerBillRecordDemand.resetWaybillFee(objTfiServerwaybill, objSPYColumns);
				listModifyWaybill.add(objTfiServerwaybill);
			} else {
				// 运单不重复
				// 如果系统中不存在该服务商运单则新建，否则修改该运单的统计信息
				AddServerwaybillTrans objAddSWBTrans = new AddServerwaybillTrans();
				objAddSWBTrans.setParam(objSPYColumns);
				objAddSWBTrans.transaction(objSession);
				objTfiServerwaybill = objAddSWBTrans.getSavedServerwaybill();
				if (!objAddSWBTrans.isInsertWaybill())
					listModifyWaybill.add(objTfiServerwaybill);
				// 放至运单集合中避免重复
				hmServerEwbWaybill.put(strServerWBCode, objTfiServerwaybill);
			}
			// 保存费用明细
			TfiServerpayable objTfiServerpayable = new TfiServerpayable();
			// 设置费用的属性
			ServerBillRecordDemand.setServerPayableByColumns(objSPYColumns,
					objTfiServerpayable,
					m_objTSBRecord.getSbrOccurdate());
			// 设置服务商账单
			objSPYColumns.setSwbswbreferencecode(objTfiServerwaybill.getSwbReferencecode());
			objTfiServerpayable.setTfiServerbillrecord(m_objTSBRecord);
			objTfiServerpayable.setTfiServerwaybill(objTfiServerwaybill);
			objSession.save(objTfiServerpayable);
			// 统计费用以便更改账单上的总费用
			String strCurrencyRate = objSPYColumns.getSpyspycurrencyrate();
			BigDecimal objColumnsTotalCharge = new BigDecimal(objSPYColumns.getSpyspytotalcharge()).multiply(new BigDecimal(strCurrencyRate));
			objColumnsTotalCharge = objColumnsTotalCharge.divide(new BigDecimal("1"), 2, 4);			
			objTotalAmount = objTotalAmount.add(objColumnsTotalCharge);
		}
		// 修改运单的统计信息
		if (listModifyWaybill != null && listModifyWaybill.size() > 0) {
			for (int i = 0; i < listModifyWaybill.size(); i++) {
				TfiServerwaybill objTfiServerwaybill = listModifyWaybill.get(i);
				objSession.update(objTfiServerwaybill);
			}
		}
		// 由于存在费用合并到同一个账单的情况，因此在保存明细后统一修改账单金额
		/*
		BigDecimal objOriginSBRTotal = m_objTSBRecord.getSbrTotal();
		if (objOriginSBRTotal == null)
			objOriginSBRTotal = new BigDecimal("0");
		m_objTSBRecord.setSbrTotal(objOriginSBRTotal.add(objTotalAmount));
		objSession.update(m_objTSBRecord);
		*/
	}
}
