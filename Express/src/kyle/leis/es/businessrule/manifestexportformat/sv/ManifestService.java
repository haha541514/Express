package kyle.leis.es.businessrule.manifestexportformat.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.manifestexportformat.bl.MainifestBL;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestCusExportcolumnCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifeststandardcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifeststandardcolumnCondition;
import kyle.leis.es.businessrule.manifestexportformat.dax.ManifestexportformatDemand;

public class ManifestService extends AService {

	/**
	 * 20160905 Query MainifestExpot by wukaiquan
	 * **/
	public String queryManifestExport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifestexportformatCondition objFCMC = (ManifestexportformatCondition) objPD
				.getParameter(0, ManifestexportformatCondition.class);
		ManifestexportformatDemand objManifestCustomDemand = new ManifestexportformatDemand();
		List listResults = objManifestCustomDemand
				.queryManifestexportformat(objFCMC);

		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();

	}

	/**
	 * 20160905 deleteManifestExport by wukaiquan
	 * **/
	public String deleteManifestExport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String mefcode = (String) objPD.getParameter(0, String.class);
		MainifestBL mainifest = new MainifestBL();
		String objReturn = mainifest.deleteManifestExport(mefcode);

		if (StringUtility.isNull(objReturn)) {
			return null;
		} else {
			Encoder objEncoder = new Encoder();
			objEncoder.addParameter(objReturn);
			return objReturn;
		}
	}

	/**
	 * 20160905 saveManifestExport by wukaiquan
	 * **/
	public String saveManifestExport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifestexportformatColumns columns = (ManifestexportformatColumns) objPD
				.getParameter(0, ManifestexportformatColumns.class);
		MainifestBL manifestBL = new MainifestBL();
		ManifestexportformatColumns listResults = manifestBL
				.saveManifestExport(columns);

		if (listResults == null) {
			return null;
		} else {
			Encoder objEncoder = new Encoder();
			objEncoder.addParameter(listResults);
			return objEncoder.toString();
		}

	}

	// 标准列查询
	public String queryManifeststandardcolumn(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifeststandardcolumnCondition con = (ManifeststandardcolumnCondition) objPD
				.getParameter(0, ManifeststandardcolumnCondition.class);
		ManifestexportformatDemand objManifestCustomDemand = new ManifestexportformatDemand();
		List listResults = objManifestCustomDemand
				.queryManifeststandardcolumn(con);
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();
	}

	// 标准列保存
	public String saveManifeststandardcolumn(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifeststandardcolumnColumns columns = (ManifeststandardcolumnColumns) objPD
				.getParameter(0, ManifeststandardcolumnColumns.class);
		MainifestBL objMainifestBL = new MainifestBL();
		ManifeststandardcolumnColumns objReturn = objMainifestBL
				.saveManifeststandardcolumn(columns);
		if (objReturn == null)
			return null;
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objReturn);
		return objEncoder.toString();

	}

	// 标准列删除
	public String deleteManifeststandardcolumn(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String msccode = (String) objPD.getParameter(0, String.class);
		MainifestBL objMainifestBL = new MainifestBL();
		String objReturn = objMainifestBL.deleteManifeststandardcolumn(msccode);
		if(StringUtility.isNull(objReturn))
			return "";
		return objReturn;
	}

	// 导出列查询
	public String queryManifestEFColumns(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifestefcolumnCondition con = (ManifestefcolumnCondition) objPD
				.getParameter(0, ManifestefcolumnCondition.class);
		ManifestexportformatDemand objManifestCustomDemand = new ManifestexportformatDemand();
		List list = objManifestCustomDemand.queryManifestefcolumn(con);
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(list);
		return objEncoder.toString();
	}

	// 导出列添加
	public String saveManifestEFColumns(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		List<Object> list = objPD.getParameterList(0,
				ManifestefcolumnColumns.class);
		MainifestBL bl = new MainifestBL();
		String objReturn = bl.saveManifestefcolumnColumns(list);
		return objReturn;
	}

	// 导出列删除
	public String deleteManifestEFColumns(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifestefcolumnColumns col = (ManifestefcolumnColumns) objPD
				.getParameter(0, ManifestefcolumnColumns.class);
		MainifestBL objMainifestBL = new MainifestBL();
		String objReturn = objMainifestBL.deleteManifestefcolumnColumns(col);
		return objReturn;
	}

	// 批量删除
	public String deleteManifestEFColumnsByMefCode(Decoder objPD)
			throws Exception {
		checkParameterCount(objPD, 1, this);
		String mefCode = (String) objPD.getParameter(0, String.class);
		MainifestBL objMainifestBL = new MainifestBL();
		ManifestefcolumnColumns col = new ManifestefcolumnColumns();
		col.setMeccomp_idmefcode(Long.parseLong(mefCode));
		String objReturn = objMainifestBL.deleteManifestefcolumnColumns(col);
		return objReturn;
	}

	//
	public String queryCusManifestExport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		String mef_code = (String) objPD.getParameter(0, String.class);
		String cw_code  = (String) objPD.getParameter(1, String.class);
		String sw_code  = (String) objPD.getParameter(2, String.class);
		ManifestCusExportcolumnCondition con = (ManifestCusExportcolumnCondition) objPD
				.getParameter(3, ManifestCusExportcolumnCondition.class);
		ManifestexportformatDemand demand = new ManifestexportformatDemand();
		String sql = demand.buildSql(mef_code);
		String[] capname_list = demand.queryManifestCaptionName(mef_code);
		String[][] value_list = demand.queryManifestValue(sql, con,
				capname_list.length, mef_code,cw_code,sw_code);
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(capname_list);
		if (value_list != null && value_list.length > 0) {
			objEncoder.addParameter(value_list);
		}
		return objEncoder.toString();
	}
}
