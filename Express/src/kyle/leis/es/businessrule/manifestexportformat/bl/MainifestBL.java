package kyle.leis.es.businessrule.manifestexportformat.bl;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifeststandardcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.dax.ManifestexportformatDemand;
import kyle.leis.es.businessrule.manifestexportformat.tp.DeleteManiefstExportTP;
import kyle.leis.es.businessrule.manifestexportformat.tp.DeleteManifestEFColumnsTP;
import kyle.leis.es.businessrule.manifestexportformat.tp.DeleteManifeststandardcolumnTP;
import kyle.leis.es.businessrule.manifestexportformat.tp.SaveManifestEFColumnsTP;
import kyle.leis.es.businessrule.manifestexportformat.tp.SaveManifestExportTP;
import kyle.leis.es.businessrule.manifestexportformat.tp.SaveManifeststandardcolumnTP;
import kyle.leis.hi.TbrManifestefcolumnPK;

public class MainifestBL {

	/**
	 * 20160906 setTbrManifestExportformat by wukaiquan
	 * 
	 * @throws Exception
	 * **/
	public String deleteManifestExport(String mefcode) throws Exception {

		DeleteManiefstExportTP delete = new DeleteManiefstExportTP();
		delete.setParams(mefcode);
		delete.execute();

		return delete.getIsdelte();
	}

	/**
	 * 20160906 setTbrManifestExportformat by wukaiquan
	 * 
	 * @throws Exception
	 * **/
	public ManifestexportformatColumns saveManifestExport(
			ManifestexportformatColumns Columns) throws Exception {
		SaveManifestExportTP saveManifestExport = new SaveManifestExportTP();
		saveManifestExport.setParams(Columns);
		saveManifestExport.execute();

		ManifestexportformatColumns objReturn = ManifestexportformatDemand
				.findManiExportById(Long.toString(saveManifestExport
						.getObj_mefcode()));

		return objReturn;
	}

	public ManifeststandardcolumnColumns saveManifeststandardcolumn(
			ManifeststandardcolumnColumns columns) throws Exception {
		SaveManifeststandardcolumnTP tp = new SaveManifeststandardcolumnTP();
		tp.setColumns(columns);
		tp.execute();

		ManifeststandardcolumnColumns objReturn = ManifestexportformatDemand
				.queryManifeststandardcolumnMSCbyId(tp.getMes_code());
		return objReturn;
	}

	public String deleteManifeststandardcolumn(String mscCode) throws Exception {
		// 是否已经被关联
		ManifestexportformatDemand demand = new ManifestexportformatDemand();
		ManifestefcolumnCondition con = new ManifestefcolumnCondition();
		con.setMsccode(mscCode);
		List list = demand.queryManifestefcolumn(con);
		if (list.size() > 0)
			return null;
		DeleteManifeststandardcolumnTP tp = new DeleteManifeststandardcolumnTP();
		tp.setMes_code(mscCode);
		tp.execute();
		return tp.getResult();
	}

	public String saveManifestefcolumnColumns(List mefclist) throws Exception {
		SaveManifestEFColumnsTP tp = new SaveManifestEFColumnsTP();
		tp.setM_mefclist(mefclist);
		tp.execute();
		return tp.getResult();
	}

	public String deleteManifestefcolumnColumns(ManifestefcolumnColumns col)
			throws Exception {
		DeleteManifestEFColumnsTP tp = new DeleteManifestEFColumnsTP();
		TbrManifestefcolumnPK pk = new TbrManifestefcolumnPK();
		if (!StringUtility.isNull(col.getMeccomp_idmefcid()))
			pk.setMefcId(Integer.valueOf(col.getMeccomp_idmefcid()));
		if (!StringUtility.isNull(col.getMeccomp_idmefcode()))
			pk.setMefCode(Long.parseLong(col.getMeccomp_idmefcode()));
		tp.setPk(pk);
		tp.execute();
		return tp.getResult();
	}

}
