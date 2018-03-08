package kyle.leis.es.price.adjustiveprice.tp;

import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceColumns;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueColumns;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceDate;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceDemand;
import kyle.leis.es.price.adjustiveprice.dax.LoadAdjustivePriceResult;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TepAdjustiveprice;
import kyle.leis.hi.TepExpressprice;
import net.sf.hibernate.Session;

public class SaveAdjustivepriceTrans extends AbstractTransaction {

	private String m_strOperId;
	private String m_strEpCode;
	private AdjustivepriceColumns m_objAdjustivepriceCol;
	private List<AdjustivepricevalueColumns> m_listAdjustivepricevalueCol;
	private ModifyAdjustivePriceDateTrans m_objMAPDTrans;

	public String getEpcode() {
		return this.m_strEpCode;
	}

	public ArrayList<RuleCheckReturn> setConflictParam(
			LoadAdjustivePriceResult objLoadAdjustivePriceResult,
			String strChangedStartDate, String strChangedEndDate,
			String strOperId, boolean isCheckDateConflict) throws Exception {
		AdjustivepriceColumns objAdjustivepriceCol = objLoadAdjustivePriceResult
				.getObjAdjustivepriceCol();
		List listAdjustivepricevalueCol = objLoadAdjustivePriceResult
				.getListAdjustivepricevalue();
		objAdjustivepriceCol.setEpepstartdate(DateFormatUtility
				.getStandardDate(strChangedStartDate));
		objAdjustivepriceCol.setEpependdate(DateFormatUtility
				.getStandardDate(strChangedEndDate));
		return setParam(objAdjustivepriceCol, listAdjustivepricevalueCol,
				strOperId, isCheckDateConflict);
	}

	public ArrayList<RuleCheckReturn> setParam(
			AdjustivepriceColumns objAdjustivepriceCol,
			List<AdjustivepricevalueColumns> listAdjustivepricevalueCol,
			String strOperId, boolean isCheckDateConflict) throws Exception {
		m_objAdjustivepriceCol = objAdjustivepriceCol;
		m_listAdjustivepricevalueCol = listAdjustivepricevalueCol;
		m_strOperId = strOperId;
		if (isCheckDateConflict) {
			ARuleDate objRuleDate = new AdjustivePriceDate();
			ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate
					.checkRuleDate(objAdjustivepriceCol);
			if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
				m_objMAPDTrans = new ModifyAdjustivePriceDateTrans();
				m_objMAPDTrans.setParam(alRuleCheckReturn);
			}
			return alRuleCheckReturn;
		}
		return null;
	}

	public void transaction(Session objSession) throws Exception {
		if (m_objAdjustivepriceCol == null)
			return;

		TepExpressprice objTepExpressprice;
		TepAdjustiveprice objTepAdjustiveprice = null;

		// 修改价格
		if (!StringUtility.isNull(m_objAdjustivepriceCol.getApepcode()))
		{
			objTepExpressprice = (TepExpressprice) objSession.load(
					TepExpressprice.class, Long.valueOf(m_objAdjustivepriceCol
							.getApepcode()));
			objTepAdjustiveprice = (TepAdjustiveprice) objSession.load(TepAdjustiveprice.class, Long.valueOf(m_objAdjustivepriceCol.getApepcode()));
		}
			
		// 新增价格
		else {
			objTepExpressprice = new TepExpressprice();
			objTepAdjustiveprice = new TepAdjustiveprice();
			objTepExpressprice.setEpCreatedate(DateFormatUtility.getSysdate());
			objTepExpressprice
					.setTdiOperatorByEpOpIdCreate((TdiOperator) objSession
							.load(TdiOperator.class, Long.valueOf(m_strOperId)));
			m_objAdjustivepriceCol
					.setPspscode(IExpressPriceBasicData.PRICE_STATUS_NEW);
		}

		AdjustivePriceDemand.setExpresspriceByColumns(objTepExpressprice,
				m_objAdjustivepriceCol, m_strOperId);
		// 保存价格
		objSession.save(objTepExpressprice);
		this.m_strEpCode = String.valueOf(objTepExpressprice.getEpCode());

		if(objTepAdjustiveprice.getEpCode() == null || "".equals(objTepAdjustiveprice.getEpCode()))
			objTepAdjustiveprice.setEpCode(Long.valueOf(this.getEpcode()));
		objTepAdjustiveprice.setTchnChannel((TchnChannel)objSession.load(TchnChannel.class, m_objAdjustivepriceCol.getChnchncode()));
		objTepAdjustiveprice.setTepExpressprice(objTepExpressprice);
		//保存价格渠道关联表
		objSession.save(objTepAdjustiveprice);
		
		//新增价格值
		if (!CollectionUtility.isNull(m_listAdjustivepricevalueCol)) {
			SaveAdjustivepricevalueTrans objSaveAdjustivepricevalueTrans = new SaveAdjustivepricevalueTrans();
			objSaveAdjustivepricevalueTrans.setParam(this.getEpcode(),m_listAdjustivepricevalueCol);
			objSaveAdjustivepricevalueTrans.transaction(objSession);
		}
		
		
		// 修改日期冲突的价格表
		if (m_objMAPDTrans != null)
			m_objMAPDTrans.transaction(objSession);
	}

}
