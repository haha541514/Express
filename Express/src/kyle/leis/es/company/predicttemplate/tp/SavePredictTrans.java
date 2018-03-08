package kyle.leis.es.company.predicttemplate.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.predicttemplate.da.PredicttemplateColumns;
import kyle.leis.es.company.predicttemplate.da.PredicttemplatevalueColumns;
import kyle.leis.es.company.predicttemplate.dax.PredicttemplateDemand;
import kyle.leis.hi.TcoPredictordertemplate;
import kyle.leis.hi.TcoPredictordertemplatevalue;
import kyle.leis.hi.TcoPredictordertemplatevaluePK;
import net.sf.hibernate.Session;

public class SavePredictTrans extends AbstractTransaction {
	private PredicttemplateColumns m_objColumns;
	private List<PredicttemplatevalueColumns> m_objPOTVList;

	@SuppressWarnings("unchecked")
	public void setParam(PredicttemplateColumns objColumns, List objPOTVList) {
		m_objColumns = objColumns;
		m_objPOTVList = objPOTVList;
	}

	public void transaction(Session objSession) throws Exception {
		if (m_objColumns == null)
			return;
		TcoPredictordertemplate objTPOT = null;
		String strPotid = m_objColumns.getPotpotid();
		// 新增或修改
		if (StringUtility.isNull(strPotid)) {
			objTPOT = new TcoPredictordertemplate();
			PredicttemplateDemand.setTcoPOTByColumns(objTPOT, m_objColumns,
					objSession);
			objSession.save(objTPOT);
			Long lPotid = objTPOT.getPotId();
			for (int i = 0; i < m_objPOTVList.size(); i++) {
				TcoPredictordertemplatevalue objTPOTV = new TcoPredictordertemplatevalue();
				TcoPredictordertemplatevaluePK objTPOTVPK = new TcoPredictordertemplatevaluePK();
				PredicttemplatevalueColumns objColumns = m_objPOTVList.get(i);
				// if(StringUtility.isNull(objColumns.getTctcid())){
				// continue;
				// }
				objTPOTVPK.setPotId(lPotid);
				objTPOTVPK.setPotvId(i + 1);
				objTPOTV.setComp_id(objTPOTVPK);
				// iPotvid++;
				PredicttemplateDemand.setTcoPOTVByColumns(objTPOTV, objColumns,
						objSession);
				objSession.save(objTPOTV);
			}
		} else {
			objTPOT = (TcoPredictordertemplate) objSession.load(
					TcoPredictordertemplate.class, Long.parseLong(strPotid));
			// 修改时，先删除
			// objSession.delete(" from TcoPredictordertemplatevalue as potv where potv.comp_id.potId = "+strPotid);
			execute(
					objSession,
					"update t_co_predictordertemplatevalue t set t.cmt_code=null,"
							+ "t.tc_standardcolumn=null,t.dmk_code=null where t.pot_id='"
							+ strPotid + "'");
			PredicttemplateDemand.setTcoPOTByColumns(objTPOT, m_objColumns,
					objSession);
			objSession.update(objTPOT);
			Long lPotid = objTPOT.getPotId();
			for (int i = 0; i < m_objPOTVList.size(); i++) {
				TcoPredictordertemplatevalue objTPOTV = new TcoPredictordertemplatevalue();
				TcoPredictordertemplatevaluePK objTPOTVPK = new TcoPredictordertemplatevaluePK();
				PredicttemplatevalueColumns objColumns = m_objPOTVList.get(i);
				objTPOTVPK.setPotId(lPotid);
				objTPOTVPK.setPotvId(Integer.parseInt(objColumns.getPotvcomp_idpotvid()));
				objTPOTV.setComp_id(objTPOTVPK);
				PredicttemplateDemand.setTcoPOTVByColumns(objTPOTV, objColumns,
						objSession);
				objSession.update(objTPOTV);
			}
		}
	}
}
