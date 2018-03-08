package kyle.leis.es.businessrule.manifestexportformat.dax;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.manifestexportformat.da.FixedcolumnformulaColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.FixedcolumnformulaCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.FixedcolumnformulaQuery;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestCusExportcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestCusExportcolumnCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestCusExportcolumnQuery;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestefcolumnQuery;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatQuery;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifeststandardcolumnColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifeststandardcolumnCondition;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifeststandardcolumnQuery;
import kyle.leis.es.businessrule.manifestexportformat.tp.SaveManifestExportTP;
import kyle.leis.es.businessrule.manifest.da.MefseqColumns;
import kyle.leis.es.businessrule.manifest.da.MefseqQuery;

import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TbrManifestefcolumn;
import kyle.leis.hi.TbrManifestefcolumnPK;
import kyle.leis.hi.TbrManifestexportformat;
import kyle.leis.hi.TdiManifeststandardcolumn;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;

public class ManifestDemand {

	/**
	 * 20160905 Query Manifestexportformat by wukaiquan
	 * **/
	public List queryManifestexportformat(
			ManifestexportformatCondition mfefCondition) throws Exception {
		ManifestexportformatQuery objMFEFQ = new ManifestexportformatQuery();
		objMFEFQ.setCondition(mfefCondition);
		return objMFEFQ.getResults();
	}

	/**
	 * 20160905 getNewMefCode by wukaiquan
	 * 
	 * @throws Exception
	 * **/
	public static String getNewMefCode() throws Exception {
		MefseqQuery objMefseqQuery = new MefseqQuery();
		List objList = objMefseqQuery.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception("无法根据菜单的序列号生成主键!"));
		return ((MefseqColumns) objList.get(0)).getSmefseq();

	}

	/**
	 * 20160905 setTbrManifestExportformat by wukaiquan
	 * 
	 * @throws Exception
	 * **/
	public static void setTbrManifestExportformat(
			TbrManifestexportformat format,
			ManifestexportformatColumns columns, boolean isCreteOperator)
			throws Exception {

		format.setMefBegincolumn(Integer.parseInt(columns
				.getMefmefbegincolumn()));
		format.setMefBeginrow(Integer.parseInt(columns.getMefmefbeginrow()));
		format.setMefEname(columns.getMefmefename());
		format.setMefExportfilesuffix(columns.getMefmefexportfilesuffix());
		format.setMefModifydate(DateFormatUtility.getSysdate());
		format.setMefName(columns.getMefmefname());
		format.setMefTemplatepath(columns.getMefmeftemplatepath());
		if (isCreteOperator) {// 新增的时候,可以新增
			// 创建人
			TdiOperator tdiCreateOperator = null;
			if (!StringUtility.isNull(columns.getOpcopid())) {
				tdiCreateOperator = TdiOperatorDC.loadByKey(columns
						.getOpcopid());
				format.setTdiOperatorByMefCreator(tdiCreateOperator);
			}
		}
		// 修改人
		TdiOperator tdiModifyOperator = null;
		if (!StringUtility.isNull(columns.getOpmopid())) {
			tdiModifyOperator = TdiOperatorDC.loadByKey(columns.getOpmopid());
			format.setTdiOperatorByMefModifier(tdiModifyOperator);
		}
		// SScode
		TdiSimplestatus tdiSimplestatus = null;
		if (!StringUtility.isNull(columns.getSisscode())) {
			tdiSimplestatus = TdiSimplestatusDC
					.loadByKey(columns.getSisscode());
			format.setTdiSimplestatus(tdiSimplestatus);
			;

		}
	}

	/**
	 * 20160905 findManiExportById by wukaiquan
	 * 
	 * @throws Exception
	 * **/
	public static ManifestexportformatColumns findManiExportById(
			String mefmefcode) throws Exception {

		ManifestexportformatCondition condtion = new ManifestexportformatCondition();
		condtion.setMefcode(mefmefcode);
		ManifestexportformatQuery objMFEFQ = new ManifestexportformatQuery();
		objMFEFQ.setCondition(condtion);
		List objReturn = objMFEFQ.getResults();
		if (CollectionUtility.isNull(objReturn)) {
			return null;
		} else {
			return (ManifestexportformatColumns) objReturn.get(0);
		}

	}

	public static void setManifestefcolumnColumns(
			TbrManifestefcolumn objTbrManifestefcolumn,
			ManifestefcolumnColumns columns, Session objSession, int i)
			throws Exception {
		// if(!StringUtility.isNull(columns.getMefmefcode()))
		// objTbrManifestefcolumn.s

		if (!StringUtility.isNull(columns.getMecmefccaptionname()))
			objTbrManifestefcolumn.setMefcCaptionname(columns
					.getMecmefccaptionname());
		if (!StringUtility.isNull(columns.getMecmefcfixedcolumnformula()))
			objTbrManifestefcolumn.setMefcFixedcolumnformula(columns
					.getMecmefcfixedcolumnformula());
		if (!StringUtility.isNull(columns.getMecmefcstructruecode()))
			objTbrManifestefcolumn.setMefcStructruecode(columns
					.getMecmefcstructruecode());
		if (!StringUtility.isNull(columns.getMeccomp_idmefcode())) {
			TbrManifestexportformat objTbrManifestexportformat = (TbrManifestexportformat) objSession
					.load(TbrManifestexportformat.class, Long.parseLong(columns
							.getMeccomp_idmefcode()));
			objTbrManifestefcolumn
					.setTbrManifestexportformat(objTbrManifestexportformat);
		}
		if (!StringUtility.isNull(columns.getMscmsccode())) {
			TdiManifeststandardcolumn objTdiManifeststandardcolumn = (TdiManifeststandardcolumn) objSession
					.load(TdiManifeststandardcolumn.class, Long
							.parseLong(columns.getMscmsccode()));
			objTbrManifestefcolumn
					.setTdiManifeststandardcolumn(objTdiManifeststandardcolumn);
		}
		// 添加新列
		TbrManifestefcolumnPK comp_id = new TbrManifestefcolumnPK();
		if (!StringUtility.isNull(columns.getMeccomp_idmefcid())) {
			comp_id.setMefcId(Integer.valueOf(columns.getMeccomp_idmefcid()));
		} else {
			comp_id.setMefcId(queryCountForMdfcid(columns.getMefmefcode(),i));
		}
		if (!StringUtility.isNull(columns.getMefmefcode())) {
			comp_id.setMefCode(Long.parseLong(columns.getMefmefcode()));
		}
		objTbrManifestefcolumn.setComp_id(comp_id);
	}

	public static void setManifeststandardcolumn(
			TdiManifeststandardcolumn objManifeststandardcolumn,
			ManifeststandardcolumnColumns columns, Session objSession) {

		if (!StringUtility.isNull(columns.getMscmsccode()))
			objManifeststandardcolumn.setMscCode(Long.parseLong(columns
					.getMscmsccode()));
		if (!StringUtility.isNull(columns.getMscmsccolumnename()))
			objManifeststandardcolumn.setMscColumnname(columns
					.getMscmsccolumnname());
		if (!StringUtility.isNull(columns.getMscmsccolumnename()))
			objManifeststandardcolumn.setMscColumnename(columns
					.getMscmsccolumnename());
		if (!StringUtility.isNull(columns.getMscmscsqlcolumnname()))
			objManifeststandardcolumn.setMscSqlcolumnname(columns
					.getMscmscsqlcolumnname());
	}

	public static List queryManifeststandardcolumn(
			ManifeststandardcolumnCondition con) throws Exception {
		ManifeststandardcolumnQuery query = new ManifeststandardcolumnQuery();
		query.setCondition(con);
		return query.getResults();
	}

	public static ManifeststandardcolumnColumns queryManifeststandardcolumnMSCbyId(
			Long mscCode) throws Exception {
		ManifeststandardcolumnCondition con = new ManifeststandardcolumnCondition();
		con.setMsccode(mscCode.toString());
		List list = queryManifeststandardcolumn(con);
		if (!CollectionUtility.isNull(list) && list.size() == 1)
			return (ManifeststandardcolumnColumns) list.get(0);
		return null;
	}

	//
	public static List queryManifestefcolumnQuery() throws Exception {
		ManifestefcolumnQuery query = new ManifestefcolumnQuery();
		return query.getResults();
	}

	public static List queryManifestefcolumn(ManifestefcolumnCondition con)
			throws Exception {
		ManifestefcolumnQuery query = new ManifestefcolumnQuery();
		query.setCondition(con);
		return query.getResults();
	}

	// mefc_id列值
	public static int queryCountForMdfcid(String mefCode, int i)
			throws Exception {
		ManifestefcolumnCondition con = new ManifestefcolumnCondition();
		con.setMefcode(mefCode);
		return queryManifestefcolumn(con).size() + 1 + i;
	}

	// 构造查询字段
	public static String buildSql(String mefCode) throws Exception {
		ManifestefcolumnQuery q = new ManifestefcolumnQuery();
		ManifestefcolumnCondition con = new ManifestefcolumnCondition();
		con.setMefcode(mefCode);
		q.setCondition(con);
		List<ManifestefcolumnColumns> list = q.getResults();
		String sql = list.get(0).getMscmscsqlcolumnname();
		for (int i = 1; i < list.size(); i++) {
			sql += "," + list.get(i).getMscmscsqlcolumnname();
		}
		return sql;
	}

	// 标题查询
	public String[] queryManifestCaptionName(String mefCode) throws Exception {
		ManifestefcolumnQuery query = new ManifestefcolumnQuery();
		ManifestefcolumnCondition con = new ManifestefcolumnCondition();
		con.setMefcode(mefCode);
		query.setCondition(con);
		List<ManifestefcolumnColumns> mef_list = query.getResults();
		if (mef_list.size() == 0 || mef_list.size() < 1)
			return null;
		String[] cap_list = new String[mef_list.size()];
		for (int i = 0; i < mef_list.size(); i++) {
//			if (!StringUtility.isNull(mef_list.get(i).getMscmsccode()))
				cap_list[i] = (mef_list.get(i).getMecmefccaptionname());
		}
		return cap_list;
	}

	// 数据查询
	public String[][] queryManifestValue(String sql,
			ManifestCusExportcolumnCondition con, int colmSize, String mefCode,String cwCode,String swCode)
			throws Exception {
		ManifestCusExportcolumnQuery q = new ManifestCusExportcolumnQuery(sql,cwCode,swCode);
		q.setCondition(con);
		q.setColumnsSize(colmSize);
		List<ManifestCusExportcolumnColumns> value_list = q.getResults();
		List<FixedcolumnformulaColumns> fixed_list = queryManifestFixedColmnFormula(mefCode);
		if(value_list.size()==0||value_list.size()<1)
			return null;
		String[][] objReturn = new String[value_list.size()][colmSize];
		for (int i=0;i < value_list.size(); i++) {
			int z = 0;
			for (int j = 0; j < colmSize; j++) {
				if (ifFixed(j, fixed_list)) {
					objReturn[i][j] = fixed_list.get(z)
							.getMecmefcfixedcolumnformula();	
					z++;	
				} else {
					objReturn[i][j] = (value_list.get(i).getFields()[j]);
				}
			}
		}
		return objReturn;
	}

	// 标准列查询
	public static List queryManifestFixedColmnFormula(String mefCode)
			throws Exception {
		FixedcolumnformulaQuery query = new FixedcolumnformulaQuery();
		FixedcolumnformulaCondition con = new FixedcolumnformulaCondition();
		con.setMefcode(mefCode);
		query.setCondition(con);
		List<FixedcolumnformulaColumns> list = query.getResults();
		if (list.size() == 0 || list.size() < 1)
			return null;
		return list;
	}
	// 合并普通列和标准列
	public static boolean ifFixed(int colnumber,
			List<FixedcolumnformulaColumns> list) {
		if(list==null||list.size()<1)
			return false;
		int index = colnumber + 1;
		String i;
		if(index<10){
			 i = "0"+index;
		}
		else{
			 i = index +"";
		}
		for (int j = 0; j < list.size(); j++) {
			if (list.get(j).getMecmefcstructruecode().equals(i))
			return true;
		}
		return false;
	}
}
