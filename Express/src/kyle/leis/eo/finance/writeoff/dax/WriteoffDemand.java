package kyle.leis.eo.finance.writeoff.dax;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.writeoff.da.WriteoffColumns;
import kyle.leis.eo.finance.writeoff.da.WriteoffCondition;
import kyle.leis.eo.finance.writeoff.da.WriteoffQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TfiWriteoff;

public class WriteoffDemand {
	public static List query(WriteoffCondition objWriteoffCondition) throws Exception {
		WriteoffQuery objWriteoffQuery = new WriteoffQuery();
		objWriteoffQuery.setCondition(objWriteoffCondition);
		return objWriteoffQuery.getResults();
	}
	
	public static WriteoffColumns load(String strWoId) throws Exception {
		WriteoffQuery objWriteoffQuery = new WriteoffQuery();
		objWriteoffQuery.setWoid(strWoId);
		List objList = objWriteoffQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (WriteoffColumns)objList.get(0);
	}
	
	public static void setWriteoffByColumns(TfiWriteoff objTfiWriteoff,
			WriteoffColumns objWriteoffColumns,
			String strOperId,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objWriteoffColumns.getCkckcode())) {
			TdiCurrencykind objTdiCurrencykind = TdiCurrencykindDC.loadByKey(objWriteoffColumns.getCkckcode());
			objTfiWriteoff.setTdiCurrencykind(objTdiCurrencykind);
		}
		objTfiWriteoff.setWoRemark(objWriteoffColumns.getWoworemark());
		objTfiWriteoff.setWoTotal(new BigDecimal(objWriteoffColumns.getWowototal()));
		//  ÉúÐ§×´Ì¬
		TdiSimplestatus objTSS = TdiSimplestatusDC.loadByKey("ON");
		objTfiWriteoff.setTdiSimplestatus(objTSS);		
	}
	
	public static String getWoLabelcode(WriteoffColumns objWriteoffColumns) {
		return "temp";
	}
}
