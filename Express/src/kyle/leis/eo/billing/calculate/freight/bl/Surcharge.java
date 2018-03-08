package kyle.leis.eo.billing.calculate.freight.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.SurchargevalueColumns;
import kyle.leis.es.price.freightprice.da.SurchargevaluebaseColumns;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;

public class Surcharge {
	private HashMap<String,FeeCalculateResult> m_hmCalculatResult = new HashMap<String,FeeCalculateResult>();
	private List<SurchargevalueColumns> m_listSurchargevalue;
	
	public void addSurchargevalue(FreightpriceColumns objFPriceColumns,
			int iZnvid) throws Exception {
		if (objFPriceColumns == null || 
				StringUtility.isNull(objFPriceColumns.getFpepcode()))
			return;
		// 获得附加费价格值组
		List objList = FreightPriceDemand.loadSurchargeValue(objFPriceColumns.getFpepcode());
		if (objList == null || objList.size() < 1) return;
		m_listSurchargevalue = getZoneSurchargevalue(objList, iZnvid);
		if (m_listSurchargevalue == null || 
				m_listSurchargevalue.size() < 1)
			return;
		// 增加附加费
		addIncidentalvalue(objFPriceColumns);
	}
	
	private List<FeeCalculateResult> addIncidentalvalue(FreightpriceColumns objFPriceColumns) 
	throws Exception {
		List<FeeCalculateResult> listCalcFeeResult = new ArrayList<FeeCalculateResult>();
		List objSurchargeBase = FreightPriceDemand.loadSurchargeBase(objFPriceColumns.getFpepcode());
		for (int i = 0; i < m_listSurchargevalue.size(); i++) {
			FeeCalculateResult objClacFeeResult = new FeeCalculateResult();
			SurchargevalueColumns objSvalueColumns = m_listSurchargevalue.get(i);
			
			objClacFeeResult.setUtcode(objSvalueColumns.getUtutcode());
			objClacFeeResult.setFkcode(objSvalueColumns.getFkfkcode());
			objClacFeeResult.setReversesign(objSvalueColumns.getSvsvreversesign());
			objClacFeeResult.setMinimumvalue(objSvalueColumns.getSvsvminimumvalue());
			objClacFeeResult.setMaxvalue(objSvalueColumns.getSvsvmaximumvalue());
			objClacFeeResult.setBasevalue(objSvalueColumns.getValue());
			objClacFeeResult.setPricevalue(objSvalueColumns.getSvsvpricevalue());
			objClacFeeResult.setCkcode(objFPriceColumns.getCkckcode());
			objClacFeeResult.setPdcode(objFPriceColumns.getPdpdcode());
			objClacFeeResult.setEpcode(objFPriceColumns.getFpepcode());
			objClacFeeResult.setEpvid(objSvalueColumns.getSvcomp_idsvid());
			// 汇率
			objClacFeeResult.setCurrencyrate("1");
			if (!m_hmCalculatResult.containsKey(objSvalueColumns.getFkfkcode())) {
				// 查找基值
				if (objSvalueColumns.getUtutcode().equals(IFeeCalculateBasicData.UNITTYPE_PERCENTAGE)) {
					ArrayList<String> alBaseFkcode = getBaseFkcode(objSurchargeBase, 
							objSvalueColumns.getSvcomp_idsvid());
					objClacFeeResult.setBaseFkcode(alBaseFkcode);
				}
				m_hmCalculatResult.put(objSvalueColumns.getFkfkcode(), objClacFeeResult);
				listCalcFeeResult.add(objClacFeeResult);
			}
		}
		return listCalcFeeResult;
	}
	
	private ArrayList<String> getBaseFkcode(List objSurchargeBase, String strSvid) {
		if (objSurchargeBase == null || objSurchargeBase.size() < 1)
			return null;
		ArrayList<String> alBaseFkcode = new ArrayList<String>();
		for (int i = 0; i < objSurchargeBase.size(); i++) {
			SurchargevaluebaseColumns objSvbaseColumns = (SurchargevaluebaseColumns)objSurchargeBase.get(i);
			if (objSvbaseColumns.getSvbcomp_idsvid().equals(strSvid))
				alBaseFkcode.add(objSvbaseColumns.getSvbcomp_idfkcode());	
		}
		return alBaseFkcode;
	}
	
	private List<SurchargevalueColumns> getZoneSurchargevalue(List listSurchargevalue,
			int iZnvid) {
		List<SurchargevalueColumns> objList = new ArrayList<SurchargevalueColumns>();
		for (int i = 0; i < listSurchargevalue.size(); i++) {
			SurchargevalueColumns objSvalueColumns = (SurchargevalueColumns)listSurchargevalue.get(i);
			if (objSvalueColumns.getSvznvid().equals(String.valueOf(iZnvid)))
				objList.add(objSvalueColumns);
		}
		return objList;
	}
	
	public HashMap<String,FeeCalculateResult> getSurchargevalueResult() {
		return m_hmCalculatResult;
	}
}
