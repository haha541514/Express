package kyle.leis.eo.billing.receivable.dax;

import kyle.leis.eo.billing.payable.da.PayableforbillCondition;
import kyle.leis.eo.billing.receivable.da.ReceivableforbillCondition;

public class ReceivableforbillCD extends ReceivableforbillCondition {
	public void copyFromPayable(PayableforbillCondition objPyForbillCondition) {
		String[] astr = objPyForbillCondition.getFields();
		if (astr != null && astr.length > 0)
			for (int i = 0; i < astr.length; i++)
				m_astrConditions[i] = astr[i];
	}
}
