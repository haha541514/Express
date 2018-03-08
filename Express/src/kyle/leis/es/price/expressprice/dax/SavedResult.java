package kyle.leis.es.price.expressprice.dax;

import java.util.ArrayList;

import kyle.common.connectors.util.Encoder;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class SavedResult {
	private String m_strRulecode;
	private ArrayList<RuleCheckReturn> m_alRuleCheckReturn;
	
	public void setRulecode(String strRulecode) {
		m_strRulecode = strRulecode;
	}
	
	public String getRulecode() {
		return m_strRulecode;
	}
	
	public void setRuleCheckList(ArrayList<RuleCheckReturn> alRuleCheckReturn) {
		m_alRuleCheckReturn = alRuleCheckReturn;
	}
	
	public ArrayList<RuleCheckReturn> getRuleCheckList() {
		return m_alRuleCheckReturn;
	}
	
	public String toString() {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(m_strRulecode);
		if (m_alRuleCheckReturn != null && m_alRuleCheckReturn.size() > 0) {
			String[][] aastr = new String[m_alRuleCheckReturn.size()][];
			for (int i = 0; i < m_alRuleCheckReturn.size(); i++) {
				RuleCheckReturn objRuleCheckReturn = m_alRuleCheckReturn.get(i);
				aastr[i] = objRuleCheckReturn.getStringArray();
			}
			objEncode.addParameter(aastr);
		}
		return objEncode.toString();
	}	
}
