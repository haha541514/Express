package kyle.leis.eo.operation.corewaybillpieces.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.corewaybill.sv.CorewaybillDelegate;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesCondition;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesQuery;
import kyle.leis.es.businessrule.weightrule.da.CalcweightvalueQuery;

public class CorewaybillpiecesDemand {
	public static List load(String strCwcode) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return null;
		CorewaybillpiecesCondition objCwpCondition = new CorewaybillpiecesCondition();
		objCwpCondition.setCwcode(strCwcode);
		return query(objCwpCondition);
	}
	
	public static boolean hasSignOutPieces(String strCwcode) throws Exception {
		List listResults = load(strCwcode);
		if (listResults == null || listResults.size() < 1)
			return false;
		for (int i = 0; i < listResults.size(); i++) {
			CorewaybillpiecesColumns objCPColumns = (CorewaybillpiecesColumns)listResults.get(i);
			if (!StringUtility.isNull(objCPColumns.getCwscwscode()) &&
					objCPColumns.getCwscwscode().equals("SO"))
				return true;
		}
		return false;
	}
	
	
	public static List query(CorewaybillpiecesCondition objCwpCondition) 
	throws Exception {
		CorewaybillpiecesQuery objCwPiecesQuery = new CorewaybillpiecesQuery();
		objCwPiecesQuery.setCondition(objCwpCondition);
		return objCwPiecesQuery.getResults();
	}
	
	public static PromptUtilityCollection setChildLabelcode(String strOldSubWaybillcode,
			String strSubWaybillcode,
			List listWaybillpieces,
			boolean isForceChange) 
	throws Exception {
		return setDHLChildLabelcode(strOldSubWaybillcode, 
				strSubWaybillcode, listWaybillpieces, isForceChange);
	}
	
	public static boolean isAllSignout(List listWaybillpieces) {
		if (listWaybillpieces == null || listWaybillpieces.size() < 1)
			return true;
		for (int i = 0; i < listWaybillpieces.size(); i++) {
			CorewaybillpiecesColumns objCWPiecesColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
			if (StringUtility.isNull(objCWPiecesColumns.getCwscwscode()) ||
					!objCWPiecesColumns.getCwscwscode().equals("SO"))
				return false;
		}
		return true;
	}	
	
	public static String sumGrossweight(List listWaybillpieces) {
		if (listWaybillpieces == null || 
				listWaybillpieces.size() < 1)
			return "0";
		BigDecimal bdGrossweight = new BigDecimal("0");
		for (int i = 0; i < listWaybillpieces.size(); i++) {
			CorewaybillpiecesColumns objCWPiecesColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
			bdGrossweight = bdGrossweight.add(new BigDecimal(objCWPiecesColumns.getCpcpgrossweight()));
		}
		return bdGrossweight.toString();
	}	
	
	
	public static String calcVolumeweight(List listWaybillpieces, 
			String strVolumeRate) {
		if (listWaybillpieces == null || 
				listWaybillpieces.size() < 1 || 
				StringUtility.isNull(strVolumeRate))
			return "0";
		BigDecimal volumeWeight = new BigDecimal("0");
		BigDecimal volumeRate = new BigDecimal(strVolumeRate);
		for (int i = 0; i < listWaybillpieces.size(); i++) {
			CorewaybillpiecesColumns objCWPiecesColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
			BigDecimal Length = new BigDecimal(objCWPiecesColumns.getCpcplength());
			BigDecimal Height = new BigDecimal(objCWPiecesColumns.getCpcpheight());
			BigDecimal Width = new BigDecimal(objCWPiecesColumns.getCpcpwidth());
			volumeWeight = volumeWeight.add(Length.multiply(Height).multiply(Width).divide(volumeRate, 2, 4));
		}
		return volumeWeight.toString();
	}
	
	public String checkPiecesRestrict(String strPgwrestrictdesc,
			String strPvwrestrictdesc,
			CorewaybillpiecesColumns objCWPColumns) throws Exception {
		strPgwrestrictdesc = strPgwrestrictdesc.toUpperCase();
		strPvwrestrictdesc = strPvwrestrictdesc.toUpperCase();
		String strOriginGwdesc = strPgwrestrictdesc;
		String strOriginVwdesc = strPvwrestrictdesc;
		
		StringBuffer sbConditionText = new StringBuffer();
		if (!StringUtility.isNull(strPgwrestrictdesc)) {
			strPgwrestrictdesc = strPgwrestrictdesc.replaceAll("GW", objCWPColumns.getCpcpgrossweight());
			strPgwrestrictdesc = strPgwrestrictdesc.replaceAll(";", " and ");
			sbConditionText.append(strPgwrestrictdesc);
		}
		if (!StringUtility.isNull(strPvwrestrictdesc)) {
			strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("L", objCWPColumns.getCpcplength());
			strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("W", objCWPColumns.getCpcpwidth());
			strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("H", objCWPColumns.getCpcpheight());
			strPvwrestrictdesc = strPvwrestrictdesc.replaceAll(";", " and ");
			if (!StringUtility.isNull(strPvwrestrictdesc)) {
				sbConditionText.append(" and ");
			}
			sbConditionText.append(strPgwrestrictdesc);
		}
		try {
			String strSqlText = "select count(1) from dual where " + sbConditionText.toString(); 
			CalcweightvalueQuery objCalcWVQuery = new CalcweightvalueQuery();
			List objList = objCalcWVQuery.getResults(strSqlText);
			// 不满足条件
			if (objList == null || objList.size() < 1) {
				return "该货物的实重与材积不能选择此服务，该件的实重为：" + objCWPColumns.getCpcpgrossweight() +
				"长：" + objCWPColumns.getCpcplength() +
				"宽：" + objCWPColumns.getCpcpwidth() +
				"高：" + objCWPColumns.getCpcpheight() + "。要求：实重：" + strOriginGwdesc + "材积：" + strOriginVwdesc;
			}
		} catch (Exception ex) {
			return "所选服务的实重与材积限制公式设置错误，设置的实重限制公式为：" + strPgwrestrictdesc +
			"，设置的材积限制公式为：" + strPvwrestrictdesc;
		}
		return "";
	}	
	
	private static PromptUtilityCollection setDHLChildLabelcode(String strOldSubWaybillcode,
			String strSubWaybillcode,
			List listWaybillpieces,
			boolean isForceChange)
	throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (listWaybillpieces == null || listWaybillpieces.size() < 1)
			return null;
		int iPieces = 0;
		for (int i = 0; i < listWaybillpieces.size(); i++) {
			CorewaybillpiecesColumns objCWPiecesColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
			String strLabelcode = objCWPiecesColumns.getCpcplabelcode();
			if (isForceChange || StringUtility.isNull(strLabelcode) || 
					!strOldSubWaybillcode.equals(strSubWaybillcode))
				iPieces++;
		}
		if (iPieces <= 0) return null;
		
		CorewaybillDelegate cwd = new CorewaybillDelegate();
		List<String> listDhlChild = cwd.getDHLChildLabelcode(strSubWaybillcode, "0", iPieces);
		if (listDhlChild == null || listDhlChild.size() < iPieces) {
			objPUCollection.add("E_WBC_001", "单据不足，无法再获得子单号码", "CorewaybillpiecesDemand.setDHLChildLabelcode");
			return objPUCollection;
		}
		// 设置数据
		int index = 0;
		for (int i = 0; i < listWaybillpieces.size(); i++) {
			CorewaybillpiecesColumns objCWPiecesColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
			String strLabelcode = objCWPiecesColumns.getCpcplabelcode();
			if (isForceChange || StringUtility.isNull(strLabelcode)  || 
					!strOldSubWaybillcode.equals(strSubWaybillcode)) {
				objCWPiecesColumns.setCpcplabelcode(listDhlChild.get(index));
				index++;
			}
		}
		return objPUCollection;
	}
}
