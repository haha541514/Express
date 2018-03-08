package kyle.leis.es.systemcertification.bl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtility;
import kyle.leis.es.systemcertification.da.SystemcertificationColumns;
import kyle.leis.es.systemcertification.da.SystemcertificationCondition;
import kyle.leis.es.systemcertification.dax.SystemCertificationDemand;
import kyle.leis.es.systemcertification.tp.ExtendSystemcertificationTrans;
import kyle.leis.es.systemcertification.tp.ModifySystemcertificationStatusTrans;
import kyle.leis.es.systemcertification.tp.SaveSFOwnEnterpriseTrans;
import kyle.leis.es.systemcertification.tp.SaveSystemcertificationRemarkTrans;
import kyle.leis.es.systemcertification.tp.SaveSystemcertificationTrans;

public class Systemcertification {
	/*
	 * 检查是否认证通过
	 */
	public PromptUtility checkSystemcertification(String strSchdserialnumber, 
			String strScmacaddress, 
			String strScipaddress,
			String strOperId,
			String strCocode) throws Exception
	{
		SystemcertificationCondition objSystemcertificationCon = new SystemcertificationCondition();
		objSystemcertificationCon.setSchdserialnumber(strSchdserialnumber);
		objSystemcertificationCon.setScmacaddress(strScmacaddress);
		// objSystemcertificationCon.setScipaddress(strScipaddress);
		List objList = SystemCertificationDemand.query(objSystemcertificationCon);
		if(CollectionUtility.isNull(objList))
		{
			//保存首次登录时的审请认证
			SaveSystemcertificationTrans objSaveSystemcertificationTrans = new SaveSystemcertificationTrans();
			objSaveSystemcertificationTrans.setParam(strSchdserialnumber,strScmacaddress,strScipaddress, strOperId);
			objSaveSystemcertificationTrans.execute();
			
			PromptUtility objPromptUtility = new PromptUtility("E_SC_001","您还未授权使用信息系统，请联系系统管理员。","Systemcertification.checkSystemcertification");
			return objPromptUtility;
		}
		if(objList.get(0) instanceof SystemcertificationColumns)
		{
			SystemcertificationColumns objSystemcertificationColReturn = (SystemcertificationColumns)objList.get(0);
			
			if(objSystemcertificationColReturn.getSssscode().equals("NW"))
			{
				PromptUtility objPromptUtility = new PromptUtility("E_SC_003","系统管理员正在检查认证的合法性，请耐心等待","Systemcertification.checkSystemcertification");
				return objPromptUtility;
			}
			if(isInvalidDate(objSystemcertificationColReturn.getScscstartdate(),objSystemcertificationColReturn.getScscenddate()))
			{
				PromptUtility objPromptUtility = new PromptUtility("E_SC_002","认证已过期","Systemcertification.checkSystemcertification");
				return objPromptUtility;
			}
			
			if(objSystemcertificationColReturn.getSssscode().equals("OFF"))
			{
				PromptUtility objPromptUtility = new PromptUtility("E_SC_004","认证未通过，请联系系统管理员","Systemcertification.checkSystemcertification");
				return objPromptUtility;
			}
			// 非公司电脑不允许公司账号登陆
			if ("N".equals(objSystemcertificationColReturn.getScownenterprisesign()) && 
					StringUtility.isNull(strCocode)) {
				PromptUtility objPromptUtility = new PromptUtility("E_SC_005","认证未通过，您没有权限登陆该电脑","Systemcertification.checkSystemcertification");
				return objPromptUtility;				
			}
			
		}
		return null;
	}
	
	/*
	 * 审核通过、认证失效
	 */
	public SystemcertificationColumns modifySystemcertificationStatus(String strOperId,String strScId,String strScssCode) throws Exception
	{
		if(StringUtility.isNull(strScId))return null;
		ModifySystemcertificationStatusTrans objModifySystemcertificationStatusTrans = new ModifySystemcertificationStatusTrans();
		objModifySystemcertificationStatusTrans.setParam(strOperId,strScId, strScssCode);
		objModifySystemcertificationStatusTrans.execute();
		return queryByscId(objModifySystemcertificationStatusTrans.getNewScId());
	}
	
	/*
	 * 认证延长
	 */
	public SystemcertificationColumns extendSystemcertification(String strOperId,String strScId,String strExtendDate) throws Exception
	{
		ExtendSystemcertificationTrans objExtendSystemcertificationTrans = new ExtendSystemcertificationTrans();
		objExtendSystemcertificationTrans.setParam(strOperId, strScId, strExtendDate);
		objExtendSystemcertificationTrans.execute();
		return queryByscId(objExtendSystemcertificationTrans.getNewScId());
	}
	
	/*
	 * 添加认证备注
	 */
	public void addRemark(String strOperId,
			String strScId,
			String strRemark) throws Exception
	{
		SaveSystemcertificationRemarkTrans objSaveSystemcertificationTrans = new SaveSystemcertificationRemarkTrans();
		objSaveSystemcertificationTrans.setParam(strOperId, strScId, strRemark);
		objSaveSystemcertificationTrans.execute();
	}
	
	public void modifyOwnEnterprise(String strOperId,
			String strScId,
			String strOwnEnterpriseSign) throws Exception {
		SaveSFOwnEnterpriseTrans objSSFET = new SaveSFOwnEnterpriseTrans();
		objSSFET.setParam(strOperId, strScId, strOwnEnterpriseSign);
		objSSFET.execute();
	}	
	
	
	/*
	 * 根据主键查询
	 */
	public SystemcertificationColumns queryByscId(String strscId) throws Exception
	{
		SystemcertificationCondition objSystemcertificationCon = new SystemcertificationCondition();
		objSystemcertificationCon.setScid(strscId);
		List objList = SystemCertificationDemand.query(objSystemcertificationCon);
		if(CollectionUtility.isNull(objList) && objList.size()!=1) return null;
		return (SystemcertificationColumns)objList.get(0);
	}
	
	/*
	 * 时间判断（判断是否在认证时间段内）
	 * true不是在认证时间段内
	 * false在认证时间段内
	 */
	public boolean isInvalidDate(String strStartDate,String strEndDate)
	{
		try
		{
			Date now = new Date();
			DateFormat objDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if(now.before(objDF.parse(strEndDate)) && now.after(objDF.parse(strStartDate)))
				return false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
}
