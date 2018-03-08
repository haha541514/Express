package kyle.leis.eo.billing.calculate.freight.bl;

import java.util.HashMap;
import java.util.List;

import kyle.leis.es.price.freightprice.da.FreightpricevalueColumns;

public class Freightwaveweight {
	// 
	public HashMap<String, Integer> getWaveweight(List listFreightvalues,
			int iLevel) {
		HashMap<String, Integer> hmWaveweight = new HashMap<String, Integer>();
		// ����������
		HashMap<String, Integer> hmUpperwaveweight = getUpperwaveweight(listFreightvalues, iLevel);
		if (hmUpperwaveweight != null && hmUpperwaveweight.size() > 0)
			hmWaveweight.putAll(hmUpperwaveweight);
		// ����������
		HashMap<String, Integer> hmLowerwaveweight = getLowerwaveweight(listFreightvalues, iLevel);
		if (hmLowerwaveweight != null && hmLowerwaveweight.size() > 0)
			hmWaveweight.putAll(hmLowerwaveweight);
		return hmWaveweight;
	}
	
	/**
	 * ������ϵĲ������������ڵĵȼ�
	 * @param listFreightvalues
	 * @param iLevel
	 * @return
	 */
	private HashMap<String, Integer> getUpperwaveweight(List listFreightvalues,
			int iLevel) {
		if (iLevel >= listFreightvalues.size() - 1)
			return null;		
		HashMap<String, Integer> hmWaveweight = new HashMap<String, Integer>();
		FreightpricevalueColumns objLevelFPVColumns = (FreightpricevalueColumns)listFreightvalues.get(iLevel);
		// ������һ�������ȼ�
		for (int i = iLevel + 1; i < listFreightvalues.size(); i++) {
			FreightpricevalueColumns objIndexFPVColumns = (FreightpricevalueColumns)listFreightvalues.get(i);
			if (!objIndexFPVColumns.getFvtfvtcode().equals(objLevelFPVColumns.getFvtfvtcode())) {
				hmWaveweight.put(objIndexFPVColumns.getFpvfpvweightgrade(), i);
				break;
			}
		}
		return hmWaveweight;
	}
	
	private HashMap<String, Integer> getLowerwaveweight(List listFreightvalues,
			int iLevel) {
		if (iLevel <= 0)
			return null;		
		HashMap<String, Integer> hmWaveweight = new HashMap<String, Integer>();
		FreightpricevalueColumns objLevelFPVColumns = (FreightpricevalueColumns)listFreightvalues.get(iLevel);
		// ������һ�������ȼ�
		for (int i = iLevel - 1; i >= 0; i--) {
			FreightpricevalueColumns objIndexFPVColumns = (FreightpricevalueColumns)listFreightvalues.get(i);
			if (!objIndexFPVColumns.getFvtfvtcode().equals(objLevelFPVColumns.getFvtfvtcode())) {
				hmWaveweight.put(objIndexFPVColumns.getFpvfpvweightgrade(), i);	
				break;
			}
		}
		return hmWaveweight;
	}
}
