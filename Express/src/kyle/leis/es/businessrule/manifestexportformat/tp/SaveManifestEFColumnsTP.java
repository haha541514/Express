package kyle.leis.es.businessrule.manifestexportformat.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.dax.ManifestexportformatDemand;
import kyle.leis.hi.TbrManifestefcolumn;
import kyle.leis.hi.TbrManifestefcolumnPK;

public class SaveManifestEFColumnsTP extends AbstractTransaction {
	private List m_mefclist;
	private String result;

	public String getResult() {
		return result;
	}

	public void setM_mefclist(List mMefclist) {
		m_mefclist = mMefclist;
	}

	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		if (m_mefclist == null || m_mefclist.size() < 1) {
			result = null;
			return;
		}
		TbrManifestefcolumn objTbrManifestefcolumn = null;
		int k = -1;
		for (int i = 0; i < m_mefclist.size(); i++) {
			objTbrManifestefcolumn = new TbrManifestefcolumn();
			ManifestefcolumnColumns columns = (ManifestefcolumnColumns) m_mefclist
					.get(i);
			if (StringUtility.isNull(columns.getMeccomp_idmefcid())) {
				objTbrManifestefcolumn = new TbrManifestefcolumn();
				k++;
			} else {
				TbrManifestefcolumnPK comp_Id = new TbrManifestefcolumnPK();
				comp_Id.setMefcId(Integer
						.valueOf(columns.getMeccomp_idmefcid()));
				comp_Id.setMefCode(Long.parseLong(columns
						.getMeccomp_idmefcode()));
				objTbrManifestefcolumn = (TbrManifestefcolumn) objSession.load(
						TbrManifestefcolumn.class, comp_Id);
			}
			ManifestexportformatDemand.setManifestefcolumnColumns(objTbrManifestefcolumn,
					columns, objSession, k);
			objSession.save(objTbrManifestefcolumn);
		}
		result = "";
	}
}
