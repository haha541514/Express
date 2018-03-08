package kyle.leis.eo.operation.transportwaybill.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.payable.tp.SavePayableTrans;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillColumns;
import kyle.leis.eo.operation.transportwaybill.dax.ITransportWaybillBasicData;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TopTransportwaybill;

public class ApportionTransportFeeTrans extends AbstractTransaction {
	private String m_strTwbid;
	private List m_listCoreWaybill;
	private String m_strCkcode;
	private String m_strCurrencyRate;
	private TransportwaybillColumns m_objTWBColumns;
	private String m_strTransportFeeTotal;
	private String m_strOperId;
	
	public void setParam(String strTwbid, 
			String strCkcode, 
			String strTransportFeeTotal, 
			String strOperId) throws Exception {
		m_listCoreWaybill = TransportWaybillDemand.loadCorewaybill(strTwbid);
		if (m_listCoreWaybill == null || m_listCoreWaybill.size() < 1)
			return;
		// 查询运输主单
		m_objTWBColumns = TransportWaybillDemand.load(m_strTwbid);
		// 获得汇率
		Currency objCurrency = new Currency();
		m_strCurrencyRate = objCurrency.getCurrencyrate("", 
				"A", 
				m_objTWBColumns.getTwbtwbcreatedate(),
				"1",
				strCkcode);
		m_strTwbid = strTwbid;
		m_strCkcode = strCkcode;
		m_strTransportFeeTotal = strTransportFeeTotal;
		m_strOperId = strOperId;
	}
	
	private PayableColumns transferToTransportPayable(BigDecimal objApportionFee, 
			BigDecimal objGrossweight,
			String strCwcode) {
		// 增加至应付费用
		PayableColumns objPayableColumns = new PayableColumns();
		
		objPayableColumns.setBkbkcode(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW);
		objPayableColumns.setChnchncode(m_objTWBColumns.getTtchnchncode());
		objPayableColumns.setCococode(m_objTWBColumns.getTtcococode());
		objPayableColumns.setCkckcode(m_strCkcode);
		objPayableColumns.setCwcwcode(Long.parseLong(strCwcode));
		objPayableColumns.setFkfkcode(ITransportWaybillBasicData.FEE_TRANSPORT);
		objPayableColumns.setFsfscode(IReceivableBasicData.FEE_STATUS_CONFIRM);
		objPayableColumns.setMopopid(Long.parseLong(m_strOperId));
		// objPayableColumns.setPyepcode("分摊价格")
		objPayableColumns.setPypyactualtotal(objApportionFee);
		objPayableColumns.setPypycurrencyrate(new BigDecimal(m_strCurrencyRate));
		objPayableColumns.setPypyoccurdate(DateFormatUtility.getStandardDate(m_objTWBColumns.getTwbtwbcreatedate()));
		objPayableColumns.setPypyremark("根据运输主单总费用来分摊运输费用");
		objPayableColumns.setPypytotal(objApportionFee);
		objPayableColumns.setPypyunitnumber(objGrossweight);
		objPayableColumns.setPypyunitprice(objApportionFee.divide(objGrossweight, 2, 4));
		objPayableColumns.setPypycommissionrate(new BigDecimal("0"));
		
		return objPayableColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listCoreWaybill == null || m_listCoreWaybill.size() < 1)
			return;
		// 总重量
		BigDecimal objTotalGrossweight = TransportWaybillDemand.sumCorewaybillGrossweight(m_listCoreWaybill);
		BigDecimal objTotalFee = new BigDecimal(m_strTransportFeeTotal);
		BigDecimal objPreTotalFee = new BigDecimal("0"); 
		if (objTotalGrossweight.compareTo(new BigDecimal("0")) <= 0 ||
				objTotalFee.compareTo(new BigDecimal("0")) <= 0 )
			return;
		
		// 修改运输费用以及币种
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		TopTransportwaybill objTTransportwaybill = (TopTransportwaybill)objSession.load(TopTransportwaybill.class, 
				Long.parseLong(m_strTwbid));
		objTTransportwaybill.setTdiOperatorByOpIdModifier(objTdiOperator);
		objSession.save(objTTransportwaybill);
		
		// 根据每票件实重占总重量的比例来分摊总费用，分摊剩余的费用累加到最后一票
		for (int i = 0; i < m_listCoreWaybill.size(); i++) {
			TransportcorewaybillColumns objTCWBColumns = (TransportcorewaybillColumns)m_listCoreWaybill.get(i);
			BigDecimal objGrossweight = new BigDecimal(objTCWBColumns.getCwcw_grossweight());
			BigDecimal objApportionFee = objGrossweight.divide(objTotalGrossweight, 2, 4).multiply(objTotalFee);
			// 非最后一票则正常分摊，否则需要把剩余的费用都分摊到最后一票
			if (i < m_listCoreWaybill.size() - 1) {
				objPreTotalFee = objPreTotalFee.add(objApportionFee);
			} else {
				objApportionFee = objTotalFee.add(objPreTotalFee.multiply(new BigDecimal("-1")));
			}
			if (objApportionFee.compareTo(new BigDecimal("0")) <= 0)
				continue;
			// 转换成应付的Columns
			PayableColumns objPayableColumns = transferToTransportPayable(objApportionFee,
					objGrossweight,
					objTCWBColumns.getCwcw_code());
			List<PayableColumns> listSavedPayable = new ArrayList<PayableColumns>();
			listSavedPayable.add(objPayableColumns);
			// 原始的应付费用
			List listOriginPyColumns = PayableDemand.load(objTCWBColumns.getCwcw_code(), "A0201");
			// 保存
			SavePayableTrans objSavePayableTrans = new SavePayableTrans();
			objSavePayableTrans.setParam(listSavedPayable, 
					objTCWBColumns.getCwcw_code(), 
					m_strOperId, 
					false, 
					listOriginPyColumns);
			objSavePayableTrans.transaction(objSession);
		}
	}
}
