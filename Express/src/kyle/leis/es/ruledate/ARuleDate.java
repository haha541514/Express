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
	 * ��������Ƿ��ظ�
	 * @return
	 */
	public ArrayList<RuleCheckReturn> checkRuleDate(
			GeneralColumns objSavingColumns) throws Exception {
		ArrayList<RuleCheckReturn> listRuleCheckReturn = new ArrayList<RuleCheckReturn>();
		// ��ѯ�޶�����һ�µ�����
		ICondition objCondition = buildCondition(objSavingColumns);
		List listRepeatedRules = queryRulesRange(objCondition);
		if (listRepeatedRules == null || listRepeatedRules.size() < 1)
			return null;
		// ����������
		String strSavingStartDate = getRuleStartDate(objSavingColumns);
		String strSavingEndDate = getRuleEndDate(objSavingColumns);
		if (strSavingStartDate.length() < 19)
			strSavingStartDate = strSavingStartDate + " 00:00:00";
		if (strSavingEndDate.length() < 19)
			strSavingEndDate = strSavingEndDate + " 23:59:59";
		String strSavingRulecode = getRulecode(objSavingColumns);
		// �ж������Ƿ��ظ�
		for (int i = 0; i < listRepeatedRules.size(); i++) {
			GeneralColumns objQueryColumns = (GeneralColumns) listRepeatedRules
					.get(i);
			// ��ò�ѯ����еĿ�ʼ�ͽ�������
			String strQueryStartDate = getRuleStartDate(objQueryColumns);
			String strQueryEndDate = getRuleEndDate(objQueryColumns);
			String strQueryRulecode = getRulecode(objQueryColumns);
			// �Ƿ�Ϊͬһ�۸��
			if (strQueryRulecode.equals(strSavingRulecode)) continue;
			// ��������Ƿ��ͻ
			ArrayList<RuleCheckReturn> list = checkRuleDate(strSavingStartDate, strSavingEndDate, 
					strQueryStartDate, strQueryEndDate, 
					strQueryRulecode);
			if (list != null && list.size() > 0)
				listRuleCheckReturn.addAll(list);
		}
		return listRuleCheckReturn;
	}
	
	/**
	 * ����Columns�����������ѯ���������Բ�ѯ�����޶������Ĺ���
	 * 
	 * @param objGeneralColumns
	 * @return
	 */
	protected abstract ICondition buildCondition(GeneralColumns objSavingColumns);

	/**
	 * �����±�������������Ƿ�����޶�����һ�µ����� 
	 * ���������Щ����������ж������Ƿ��ظ�
	 * @param objCondition
	 * @return
	 */
	protected abstract List queryRulesRange(ICondition objCondition)
			throws Exception;

	/**
	 * ͨ������һ�麯������ù���Columns�е��������
	 * @param objQueryColumns
	 * @return
	 */
	protected abstract String getRuleStartDate(GeneralColumns objQueryColumns);
	protected abstract String getRuleEndDate(GeneralColumns objQueryColumns);
	protected abstract String getRulecode(GeneralColumns objQueryColumns);
	
	/**
	 * ��ԭ�����ڻ�������������
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
		// �¸��Ǿ�
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
		// ��������ཻ
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
		// ��������ཻ
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
		// �ɰ�����
		if (strStartDate.compareTo(strSavingStartDate) < 0 && 
				strEndDate.compareTo(strSavingEndDate) > 0) {
			// �������
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
			// �����ұ�
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
			// �����м�
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
