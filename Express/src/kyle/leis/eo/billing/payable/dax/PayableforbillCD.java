package kyle.leis.eo.billing.payable.dax;

import kyle.leis.eo.billing.payable.da.PayableforbillCondition;
import kyle.leis.eo.billing.receivable.da.ReceivableforbillCondition;

public class PayableforbillCD extends PayableforbillCondition {
	public void copyFromReceivable(ReceivableforbillCondition objRvForbillCondition) {
		String[] astr = objRvForbillCondition.getFields();
		if (astr != null && astr.length > 0)
			for (int i = 0; i < astr.length; i++)
				m_astrConditions[i] = astr[i];
	}
}
