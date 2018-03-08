package kyle.leis.es.ruledate;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.common.util.jlang.DateUtility;

public abstract class ARuleDate {
	/**
	 * 检查日期是否重复
	 * @return
	 */
	public ArrayList<RuleCheckReturn> checkRuleDate(
			GeneralColumns objSavingColumns) throws Exception {
		ArrayList<RuleCheckReturn> listRuleCheckReturn = new ArrayList<RuleCheckReturn>();
		// 查询限定条件一致的数据
		ICondition objCondition = buildCondition(objSavingColumns);
		List listRepeatedRules = queryRulesRange(objCondition);
		if (listRepeatedRules == null || listRepeatedRules.size() < 1)
			return null;
		// 获得相关属性
		String strSavingStartDate = getRuleStartDate(objSavingColumns);
		String strSavingEndDate = getRuleEndDate(objSavingColumns);
		if (strSavingStartDate.length() < 19)
			strSavingStartDate = strSavingStartDate + " 00:00:00";
		if (strSavingEndDate.length() < 19)
			strSavingEndDate = strSavingEndDate + " 23:59:59";
		String strSavingRulecode = getRulecode(objSavingColumns);
		// 判断日期是否重复
		for (int i = 0; i < listRepeatedRules.size(); i++) {
			GeneralColumns objQueryColumns = (GeneralColumns) listRepeatedRules
					.get(i);
			// 获得查询结果中的开始和结束日期
			String strQueryStartDate = getRuleStartDate(objQueryColumns);
			String strQueryEndDate = getRuleEndDate(objQueryColumns);
			String strQueryRulecode = getRulecode(objQueryColumns);
			// 是否为同一价格表
			if (strQueryRulecode.equals(strSavingRulecode)) continue;
			// 检查日期是否冲突
			ArrayList<RuleCheckReturn> list = checkRuleDate(strSavingStartDate, strSavingEndDate, 
					strQueryStartDate, strQueryEndDate, 
					strQueryRulecode);
			if (list != null && list.size() > 0)
				listRuleCheckReturn.addAll(list);
		}
		return listRuleCheckReturn;
	}
	
	/**
	 * 根据Columns数据来构造查询条件，用以查询符合限定条件的规则
	 * 
	 * @param objGeneralColumns
	 * @return
	 */
	protected abstract ICondition buildCondition(GeneralColumns objSavingColumns);

	/**
	 * 根据新保存规则来查找是否存在限定条件一致的数据 
	 * 如果存在这些数据则继续判断日期是否重复
	 * @param objCondition
	 * @return
	 */
	protected abstract List queryRulesRange(ICondition objCondition)
			throws Exception;

	/**
	 * 通过下面一组函数来获得规则Columns中的相关属性
	 * @param objQueryColumns
	 * @return
	 */
	protected abstract String getRuleStartDate(GeneralColumns objQueryColumns);
	protected abstract String getRuleEndDate(GeneralColumns objQueryColumns);
	protected abstract String getRulecode(GeneralColumns objQueryColumns);
	
	/**
	 * 在原来日期基础上增减天数
	 * @param strOriginDate
	 * @param i
	 * @return
	 */
	public String addDate(String strOriginDate, int i) {
		GregorianCalendar objGC = DateUtility.getCalendar(strOriginDate);
		objGC.add(GregorianCalendar.DATE, i);
		Date dt = objGC.getTime();

		Format objformat = new SimpleDateFormat("yyyy-MM-dd");
		return objformat.format(dt);
	}
	
	private ArrayList<RuleCheckReturn> checkRuleDate(String strSavingStartDate,
			String strSavingEndDate, String strStartDate, String strEndDate,
			String strRulecode) {
		ArrayList<RuleCheckReturn> vctResult = new ArrayList<RuleCheckReturn>();
		
		RuleCheckReturn objRuleCheckReturn = new RuleCheckReturn();
		String strChangedStartDate = strSavingStartDate;
		String strChangedEndDate = strSavingEndDate;
		// 新覆盖旧
		if (strStartDate.compareTo(strSavingStartDate) >= 0 && 
				strEndDate.compareTo(strSavingEndDate) <= 0) {
			objRuleCheckReturn.setAtcode(RuleCheckReturn.ACTIONTYPE_ELIMINATE);
			objRuleCheckReturn.setNewStartDate(strStartDate);
			objRuleCheckReturn.setNewEndDate(strEndDate);
			objRuleCheckReturn.setOldStartDate(strStartDate);
			objRuleCheckReturn.setOldEndDate(strEndDate);
			objRuleCheckReturn.setRulecode(strRulecode);
			vctResult.add(objRuleCheckReturn);
			return vctResult;
		}
		// 新与旧右相交
		if (strStartDate.compareTo(strSavingStartDate) < 0 && 
				strEndDate.compareTo(strSavingStartDate) >= 0 && 
				strEndDate.compareTo(strSavingEndDate) <= 0) {
			strChangedEndDate = addDate(strSavingStartDate, -1) + " 23:59:59";
			strChangedStartDate = strStartDate;
			if (strChangedEndDate.compareTo(strChangedStartDate) < 0) {
				strChangedEndDate = strChangedStartDate.substring(0, 10) + " 23:59:59";
			}
			objRuleCheckReturn.setAtcode(RuleCheckReturn.ACTIONTYPE_MODIFYDATE);
			objRuleCheckReturn.setNewStartDate(strChangedStartDate);
			objRuleCheckReturn.setNewEndDate(strChangedEndDate);
			objRuleCheckReturn.setOldStartDate(strStartDate);
			objRuleCheckReturn.setOldEndDate(strEndDate);
			objRuleCheckReturn.setRulecode(strRulecode);
			vctResult.add(objRuleCheckReturn);
			return vctResult;
		}
		// 新与旧左相交
		if (strStartDate.compareTo(strSavingEndDate) <= 0
				&& strEndDate.compareTo(strSavingEndDate) > 0
				&& strStartDate.compareTo(strSavingStartDate) >= 0) {
			strChangedStartDate = addDate(strSavingEndDate, 1) + " 00:00:00";
			strChangedEndDate = strEndDate;
			if (strChangedEndDate.compareTo(strChangedStartDate) < 0) {
				strChangedEndDate = strChangedStartDate.substring(0, 10) + " 23:59:59";
			}
			objRuleCheckReturn.setAtcode(RuleCheckReturn.ACTIONTYPE_MODIFYDATE);
			objRuleCheckReturn.setNewStartDate(strChangedStartDate);
			objRuleCheckReturn.setNewEndDate(strChangedEndDate);
			objRuleCheckReturn.setOldStartDate(strStartDate);
			objRuleCheckReturn.setOldEndDate(strEndDate);
			objRuleCheckReturn.setRulecode(strRulecode);
			vctResult.add(objRuleCheckReturn);
			return vctResult;
		}
		// 旧包含新
		if (strStartDate.compareTo(strSavingStartDate) < 0 && 
				strEndDate.compareTo(strSavingEndDate) > 0) {
			// 新增左边
			strChangedStartDate = strStartDate;
			strChangedEndDate = addDate(strSavingStartDate, -1) + " 23:59:59";
			if (strChangedEndDate.compareTo(strChangedStartDate) < 0) {
				strChangedEndDate = strChangedStartDate.substring(0, 10) + " 23:59:59";
			}
			objRuleCheckReturn.setAtcode(RuleCheckReturn.ACTIONTYPE_NEW);
			objRuleCheckReturn.setNewStartDate(strChangedStartDate);
			objRuleCheckReturn.setNewEndDate(strChangedEndDate);
			objRuleCheckReturn.setOldStartDate(strStartDate);
			objRuleCheckReturn.setOldEndDate(strEndDate);
			objRuleCheckReturn.setRulecode(strRulecode);
			vctResult.add(objRuleCheckReturn);
			// 新增右边
			RuleCheckReturn objRCRRight = new RuleCheckReturn();
			strChangedStartDate = addDate(strSavingEndDate, 1) + " 00:00:00";
			strChangedEndDate = strEndDate;
			if (strChangedEndDate.compareTo(strChangedStartDate) < 0) {
				strChangedEndDate = strChangedStartDate.substring(0, 10) + " 23:59:59";
			}
			objRCRRight.setAtcode(RuleCheckReturn.ACTIONTYPE_NEW);
			objRCRRight.setNewStartDate(strChangedStartDate);
			objRCRRight.setNewEndDate(strChangedEndDate);
			objRCRRight.setOldStartDate(strStartDate);
			objRCRRight.setOldEndDate(strEndDate);
			objRCRRight.setRulecode(strRulecode);
			vctResult.add(objRCRRight);
			// 作废中间
			RuleCheckReturn objRCRMiddle = new RuleCheckReturn();
			objRCRMiddle.setAtcode(RuleCheckReturn.ACTIONTYPE_ELIMINATE);
			objRCRMiddle.setNewStartDate(strSavingStartDate);
			objRCRMiddle.setNewEndDate(strSavingEndDate);
			objRCRMiddle.setOldStartDate(strSavingStartDate);
			objRCRMiddle.setOldEndDate(strSavingEndDate);
			objRCRMiddle.setRulecode(strRulecode);
			vctResult.add(objRCRMiddle);
			return vctResult;
		}
		return null;
	}	
}
