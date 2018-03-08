package kyle.leis.es.price.currency.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.price.currency.da.CurrencyvalueColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TepCurrency;
import kyle.leis.hi.TepCurrencyvalue;
import kyle.leis.hi.TepCurrencyvaluePK;

public class SaveCYValueTrans extends AbstractTransaction {
    private List m_listCurrencyValue;
    private TepCurrency m_objTepCurrency;
	
	public void setParam(List listCurrencyValue,
			TepCurrency objTepCurrency) {
		m_listCurrencyValue = listCurrencyValue;
		m_objTepCurrency = objTepCurrency;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		// 删除
		objSession.delete(" from TepCurrencyvalue as cuv where cuv.comp_id.epCode = " + 
				String.valueOf(m_objTepCurrency.getEpCode()));
		if (m_listCurrencyValue == null || m_listCurrencyValue.size() < 1) 
			return;
		// 新增
		for (int i = 0; i < m_listCurrencyValue.size(); i++) {
			CurrencyvalueColumns objCurrencyvalueColumns = (CurrencyvalueColumns)m_listCurrencyValue.get(i);
			TepCurrencyvalue objTCUV = new TepCurrencyvalue();
			// 主键
			TepCurrencyvaluePK objTCPK = new TepCurrencyvaluePK();
			objTCPK.setCvId(i + 1);
			objTCPK.setEpCode(m_objTepCurrency.getEpCode());
			objTCUV.setComp_id(objTCPK);
			// 其他列
			objTCUV.setCvCurrencyrate(new BigDecimal(objCurrencyvalueColumns.getCvcvcurrencyrate()));
			
			TdiCurrencykind objBCK = TdiCurrencykindDC.loadByKey(objCurrencyvalueColumns.getCkbckcode());
			TdiCurrencykind objCCK = TdiCurrencykindDC.loadByKey(objCurrencyvalueColumns.getCkcckcode());
			objTCUV.setTdiCurrencykindByCkCodeBase(objBCK);
			objTCUV.setTdiCurrencykindByCkCodeChange(objCCK);
			objTCUV.setTepCurrency(m_objTepCurrency);
			
			objSession.save(objTCUV);
		}
	}
}
