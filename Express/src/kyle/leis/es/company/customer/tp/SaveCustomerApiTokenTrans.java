package kyle.leis.es.company.customer.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.company.customer.da.CawtseqColumns;
import kyle.leis.es.company.customer.da.CawtseqQuery;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenColumns;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TcoCustomerapiwebtoken;
import kyle.leis.hi.TdiCustomerapiwebtype;
import net.sf.hibernate.Session;

public class SaveCustomerApiTokenTrans extends AbstractTransaction{
	private CustomerapiwebtokenColumns customerapiwebtokenColumns;
	private TcoCustomerapiwebtoken customerapiwebtoken;

	public TcoCustomerapiwebtoken getCustomerapiwebtoken() {
		return customerapiwebtoken;
	}

	public void setParam(
			CustomerapiwebtokenColumns customerapiwebtokenColumns) {
		this.customerapiwebtokenColumns = customerapiwebtokenColumns;
	}

	public void transaction(Session objSession) throws Exception {
		if (customerapiwebtokenColumns == null) {
			return;
		}
		TcoCustomerapiwebtoken customerapiwebtoken = null;
		if (customerapiwebtokenColumns.getCawtcawtid() == null) {
			//±£´æ
			customerapiwebtoken = new TcoCustomerapiwebtoken();
			CawtseqQuery cawtseqQuery = new CawtseqQuery();
			List<?> cawtSeqs = cawtseqQuery.getResults();
			CawtseqColumns cawtseqColumns = (CawtseqColumns) cawtSeqs.get(0);
			customerapiwebtoken.setCawtId(Integer.valueOf(cawtseqColumns.getCawtseq()));
			TcoCustomer tcoCustomer = (TcoCustomer) objSession.load(TcoCustomer.class, 
					customerapiwebtokenColumns.getCococode());
			customerapiwebtoken.setTcoCustomer(tcoCustomer);
		} else {
			// ¸üÐÂ
			customerapiwebtoken = (TcoCustomerapiwebtoken) objSession.load(TcoCustomerapiwebtoken.class, 
					Integer.valueOf(customerapiwebtokenColumns.getCawtcawtid()));
		}
		TdiCustomerapiwebtype tdiCustomerapiwebtype = (TdiCustomerapiwebtype) objSession.load(TdiCustomerapiwebtype.class, 
				customerapiwebtokenColumns.getCapwtcapwtcode());
		customerapiwebtoken.setTdiCustomerapiwebtype(tdiCustomerapiwebtype);
		customerapiwebtoken.setCawtUsername(customerapiwebtokenColumns.getCawtcawtusername());
		customerapiwebtoken.setCawtToken(customerapiwebtokenColumns.getCawtcawttoken());
		customerapiwebtoken.setCawtPassword(customerapiwebtokenColumns.getCawtcawtpassword());
		
		objSession.save(customerapiwebtoken);
		this.customerapiwebtoken = customerapiwebtoken;
	}
	
}
