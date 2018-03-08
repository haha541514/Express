package kyle.leis.es.businessrule.manifestexportformat.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifeststandardcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.dax.ManifestexportformatDemand;
import kyle.leis.es.businessrule.manifestexportformat.dax.MscSeq;
import kyle.leis.hi.TdiManifeststandardcolumn;

public class SaveManifeststandardcolumnTP extends AbstractTransaction {
	private ManifeststandardcolumnColumns columns;
	private Long mes_code;

	public void setColumns(ManifeststandardcolumnColumns columns) {
		this.columns = columns;
	}

	public Long getMes_code() {
		return mes_code;
	}

	public void transaction(Session objSession) throws Exception {
		TdiManifeststandardcolumn objTfdc;
		if (StringUtility.isNull(columns.getMscmsccode())) {
			objTfdc = new TdiManifeststandardcolumn();
			MscSeq seq = new MscSeq();
			columns.setMscmsccode(Long.parseLong(seq.getNewSequencecode()));
		} else {
			objTfdc = (TdiManifeststandardcolumn) objSession.load(
					TdiManifeststandardcolumn.class, Long.parseLong(columns
							.getMscmsccode()));
		}
		ManifestexportformatDemand.setManifeststandardcolumn(objTfdc, columns, objSession);

		mes_code = objTfdc.getMscCode();

		objSession.save(objTfdc);
	}
}
