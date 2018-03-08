package kyle.leis.fs.dictionary.expressspecialtype.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeColumns;
import kyle.leis.hi.TdiExpressspecialtype;
import kyle.leis.hi.TdiSimplestatus;
import net.sf.hibernate.Session;

public class AddExpressspecialtypeTransaction extends AbstractTransaction{
	private ExpressspecialtypeColumns paramsColumns;
	
	public void setParamsColumns(ExpressspecialtypeColumns paramsColumns) {
		this.paramsColumns = paramsColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (paramsColumns == null ||StringUtility.isNull(paramsColumns.getEstestcode())) {
			return;
		}
		TdiExpressspecialtype expressspecialtype = 
			(TdiExpressspecialtype) objSession.get(TdiExpressspecialtype.class, paramsColumns.getEstestcode());
		if (expressspecialtype == null) {//ÐÂÔö
			expressspecialtype = new TdiExpressspecialtype();
			expressspecialtype.setEstCode(paramsColumns.getEstestcode());
			
		} 
		expressspecialtype.setEstName(StringUtility.replaceWhenNull(paramsColumns.getEstestname(), 
				expressspecialtype.getEstName()));
		expressspecialtype.setEstEname(StringUtility.replaceWhenNull(paramsColumns.getEstestename(), 
				expressspecialtype.getEstEname()));
		expressspecialtype.setEstEndsign(StringUtility.replaceWhenNull(paramsColumns.getEstestendsign(), 
				expressspecialtype.getEstEndsign()));
		expressspecialtype.setEstExcludesign(StringUtility.replaceWhenNull(paramsColumns.getEstestexcludesign(), 
				expressspecialtype.getEstExcludesign()));
		expressspecialtype.setEstStructurecode(StringUtility.replaceWhenNull(paramsColumns.getEsteststructurecode(),
				expressspecialtype.getEstStructurecode()));
		expressspecialtype.setEstPeculiarlychannelsign(StringUtility.replaceWhenNull(paramsColumns.getEstestpeculiarlychannelsign(), 
				expressspecialtype.getEstPeculiarlychannelsign()));
		if (!StringUtility.isNull(paramsColumns.getSssscode())) {
			TdiSimplestatus simplestatus = new TdiSimplestatus();
			simplestatus.setSsCode(paramsColumns.getSssscode());
			expressspecialtype.setTdiSimplestatus(simplestatus);
		}
		objSession.save(expressspecialtype);
	}

}
