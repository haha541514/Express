package kyle.leis.eo.operation.specialtype.bl;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.customerservice.issue.bl.Issue;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.specialtype.dax.ISpecialtypeBasicData;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.eo.operation.specialtype.tp.DeleteSpecialtypeTrans;
import kyle.leis.eo.operation.specialtype.tp.SaveSingleSpecialtypeTrans;
import kyle.leis.eo.operation.specialtype.tp.SaveSpecialtypeTrans;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.businesslog.bl.Businesslog;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiDistrict;

public class Specialtype {
	/**
	 * ����ĳ����������
	 * @param strCwcode
	 * @param strEstcode
	 * @param strOperId
	 * @param strRemark
	 * @return
	 * @throws Exception
	 */
	public List addSpecialtype(String strCwcode, 
			String strEstcode, 
			String strOperId, 
			String strRemark,
			boolean isRecalFee) throws Exception {
		SaveSingleSpecialtypeTrans objSSSTTrans = new SaveSingleSpecialtypeTrans();
		objSSSTTrans.setParam(strCwcode, 
				strEstcode, 
				strOperId, 
				strRemark);
		objSSSTTrans.execute();
		// �Ʒ�
		if (isRecalFee) {
			AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(strCwcode,
					IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
					"",
					true);			
			objAFCThread.start();
		}
		return SpecialtypeDemand.load(strCwcode);
	}
	
	public void addRestrictedElevatedRisk(String strCwcode,
			String strDtcode,
			String strSsgcode,
			String strOperId) throws Exception {
		if (!StringUtility.isNull(strSsgcode) &&
				strSsgcode.startsWith("DHL") &&
				!strSsgcode.equals("DHL-GlobeMail") &&
				!strSsgcode.equals("DHL-USGlobeMail") &&				
				!StringUtility.isNull(strDtcode)) {
			TdiDistrict td = TdiDistrictDC.loadByKey(strDtcode);
			String strIssueRemark = "";
			if ("Y".equals(td.getDtRestrictedsign())) {
				addSpecialtype(strCwcode, 
						ISpecialtypeBasicData.SPECIALTYPE_RESTRICTEDDT, 
						strOperId, 
						"���˵���", 
						false);
				strIssueRemark = "Ŀ�Ĺ���Ϊ" + td.getDtName() + "������Ŀ�ĵأ���Ҫ��������Ŀ�ĵظ��ӷѶ��ۼ�";
			} else {
				// ɾ��
				delete(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_RESTRICTEDDT);
			}
			if ("Y".equals(td.getDtElevatedrisksign())) {
				addSpecialtype(strCwcode, 
						ISpecialtypeBasicData.SPECIALTYPE_ELEVATEDRISK, 
						strOperId, 
						"�߷��յ���", 
						false);
				if (!StringUtility.isNull(strIssueRemark)) {
					strIssueRemark = "Ŀ�Ĺ���Ϊ" + td.getDtName() + "������Ŀ�ĵغ͸߷��յ�������Ҫ��������Ŀ�ĵظ��ӷѺ͸߷��յ������ۼ�";
				} else { 
					strIssueRemark = "Ŀ�Ĺ���Ϊ" + td.getDtName() + "�Ǹ߷��յ�������Ҫ���ո߷��յ������ӷѶ��ۼ�";
				}
			} else {
				// ɾ��
				delete(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_ELEVATEDRISK);
			}
			if (!StringUtility.isNull(strIssueRemark)) { 
				Issue objIssue= new Issue();
				objIssue.addHoldIssue(strCwcode, String.valueOf(502), strIssueRemark, strOperId);
			}	
			return;
		}
		// �����ǣ���ɾ�����˵����͸߷��յ�������������
		delete(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_ELEVATEDRISK);
		delete(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_RESTRICTEDDT);
	}	
	
	
	
	/**
	 * ��ֵ����
	 * @param strCwcode
	 * @param strEstcode
	 * @param strOperId
	 * @param strRemark
	 * @throws Exception
	 */
	public void addOverDeclare(String strCwcode, 
			String strTotalDeclare,
			String strCkcode,
			String strOperId,
			String strChncode) throws Exception {
		if (StringUtility.isNull(strTotalDeclare) || StringUtility.isNull(strCkcode))
			return;
		BigDecimal objStandardDeclarevalue = new BigDecimal("128");
		if (!StringUtility.isNull(strChncode)) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
			if (objTchnChannel.getChnMaxoverdeclarevalue() != null &&
					objTchnChannel.getChnMaxoverdeclarevalue().compareTo(new BigDecimal("0")) > 0)
					objStandardDeclarevalue = objTchnChannel.getChnMaxoverdeclarevalue();
		}
		if (strCkcode.equals("USD") &&	
				new BigDecimal(strTotalDeclare).compareTo(objStandardDeclarevalue) >= 0) {
			boolean blIsNotCustomer = SpecialtypeDemand.isExistEst(ISpecialtypeBasicData.SPECIALTYPE_OVER_DECLARE);
			if (blIsNotCustomer) {
				SaveSingleSpecialtypeTrans objSSSTTrans = new SaveSingleSpecialtypeTrans();
				objSSSTTrans.setParam(strCwcode, 
						ISpecialtypeBasicData.SPECIALTYPE_OVER_DECLARE, 
						strOperId, 
						"ϵͳ�Զ���¼��ֵ����");
				objSSSTTrans.execute();
			}
		}
	}	
	
	
	
	/**
	 * ��¼����������������
	 * @param listCorewaybillpieces
	 * @param strOperId
	 * @param strCwcode
	 * @throws Exception
	 */
	public void addOverLengthSpecialtype(List listCorewaybillpieces,
			String strOperId,
			String strCwcode) throws Exception {
		SimplecorewaybillColumns objSCWBColumns = CorewaybillDemand.loadSimpleCorewaybill(strCwcode);
		String strPkcode = objSCWBColumns.getCwpk_code();
		
		// �ж��Ƿ�Ϊ�ǿͻ����ж��߼���
		// ������ڳ����������ͣ���Ϊ�ǿͻ���
		// ��Ϊ�ͻ��������Ϊ�������ͺͳ����������֣�ֻ�зǿͻ����г�����������
		boolean blIsNotCustomer = SpecialtypeDemand.isExistEst(ISpecialtypeBasicData.SPECIALTYPE_OVER_LW);
		
		if (strPkcode.startsWith("D")) {
			addDHLOverLengthSpecialtype(listCorewaybillpieces, strOperId, 
					strCwcode, blIsNotCustomer);
		} else if (strPkcode.startsWith("T")) {
			addTNTOverLengthSpecialtype(listCorewaybillpieces, strOperId, 
					strCwcode, blIsNotCustomer);
		} else if (strPkcode.startsWith("A07")) {
			// BA��DHL��ƷA07��ͷ
			String strSystemEe = SystempropertyDemand.getEnterprise();
			if (strSystemEe.startsWith("QQYX")) {
				addDHLOverLengthSpecialtype(listCorewaybillpieces, strOperId, 
						strCwcode, blIsNotCustomer);				
			}
		}
		
		
	}
	
	private void addDHLOverLengthSpecialtype(List listCorewaybillpieces,
			String strOperId,
			String strCwcode,
			boolean blIsNotCustomer) throws Exception {
		BigDecimal objLength = new BigDecimal("0");
		BigDecimal objHeight = new BigDecimal("0");
		BigDecimal objWidth = new BigDecimal("0");
		BigDecimal objGrossweight = new BigDecimal("0");
		BigDecimal objStandardLength = new BigDecimal("120");
		BigDecimal objStandardGrossweight = new BigDecimal("70");
		//�Ƿ��Ѽǳ�����falseΪû�м�
		boolean blLengthMark = false;
		//�Ƿ��Ѽǳ��أ�falseΪû�м�
		boolean blGrossweightMark = false;
		
		for(CorewaybillpiecesColumns objCWBPColumns:(List<CorewaybillpiecesColumns>)listCorewaybillpieces)
		{
			Specialtype objSpecialtype = new Specialtype();
			objLength = new BigDecimal(objCWBPColumns.getCpcplength());
			objHeight = new BigDecimal(objCWBPColumns.getCpcpheight());
			objWidth = new BigDecimal(objCWBPColumns.getCpcpwidth());
			objGrossweight = new BigDecimal(objCWBPColumns.getCpcpgrossweight());
			//�ǳ���
			if(!blLengthMark && 
					(objLength.compareTo(objStandardLength) >= 0 || 
							objHeight.compareTo(objStandardLength) >= 0 || 
							objWidth.compareTo(objStandardLength) >= 0 ))
			{
				//�ǿͻ�
				if(blIsNotCustomer)
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_LW, strOperId, "����������������", false);
				else
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_L, strOperId, "����������������", false);
				blLengthMark = true;
				
				Issue objIssue= new Issue();
				objIssue.addHoldIssue(strCwcode, String.valueOf(212), "������������:+"+objStandardGrossweight+"�򵥱߳��ȴ���:"+objStandardLength+",��Ҫȷ�ϳ������ظ��ӷѡ�", strOperId);				
			}
			//�ǳ���
			if(objGrossweight.compareTo(objStandardGrossweight) >= 0)
			{
				//����ǿͻ�����û�мǳ���������ʱ��ǳ��ء��г����򲻼ǳ���,Ĭ��ʹ���Ѽǵĳ�����
				if(!blLengthMark && blIsNotCustomer)
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_LW, strOperId, "����������������", false);
				//����ǿͻ��������ǳ��������ؼǳ��ء������ҳ��أ���Ĭ��ʹ���Ѽǵĳ�����(��Ϊ�����Ѻͳ��طѶ���һ���Ľ��)
				else if(!blIsNotCustomer && !blLengthMark)
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_W, strOperId, "����������������", false);
				
				Issue objIssue= new Issue();
				objIssue.addHoldIssue(strCwcode, String.valueOf(212), "������������:+"+objStandardGrossweight+"�򵥱߳��ȴ���:"+objStandardLength+",��Ҫȷ�ϳ������ظ��ӷѡ�", strOperId);				
				blGrossweightMark = true;	
			}
			//ֻ��¼һ���������ػ��Ｔ��
			if(blLengthMark || blGrossweightMark)
				break;
		}		
	}
	
	private void addTNTOverLengthSpecialtype(List listCorewaybillpieces,
			String strOperId,
			String strCwcode,
			boolean blIsNotCustomer) throws Exception {
		BigDecimal objLength = new BigDecimal("0");
		BigDecimal objHeight = new BigDecimal("0");
		BigDecimal objWidth = new BigDecimal("0");
		BigDecimal objGrossweight = new BigDecimal("0");
		BigDecimal objStandardLength = new BigDecimal("120");
		BigDecimal objOGStandardLength = new BigDecimal("120");
		BigDecimal objStandardGrossweight = new BigDecimal("30");
		//�Ƿ��Ѽǳ�����falseΪû�м�
		boolean blLengthMark = false;
		
		for(CorewaybillpiecesColumns objCWBPColumns : (List<CorewaybillpiecesColumns>)listCorewaybillpieces) {
			Specialtype objSpecialtype = new Specialtype();
			objLength = new BigDecimal(objCWBPColumns.getCpcplength());
			objHeight = new BigDecimal(objCWBPColumns.getCpcpheight());
			objWidth = new BigDecimal(objCWBPColumns.getCpcpwidth());
			objGrossweight = new BigDecimal(objCWBPColumns.getCpcpgrossweight());
			String strRemark = "";
			//�ǳ���
			if(objGrossweight.compareTo(objStandardGrossweight) < 0) {
				if (objLength.compareTo(objStandardLength) >= 0 || 
						objHeight.compareTo(objStandardLength) >= 0 || 
						objWidth.compareTo(objStandardLength) >= 0 ) {
						blLengthMark = true;
						strRemark = "��������С�ڣ�" + objStandardGrossweight + "�����ȴ���" + objStandardLength + "����Ҫ���ճ������á�";
				}
			} else {
				if (objLength.compareTo(objOGStandardLength) >= 0 || 
						objHeight.compareTo(objOGStandardLength) >= 0 || 
						objWidth.compareTo(objOGStandardLength) >= 0 ) {
						blLengthMark = true;
						strRemark = "�����������ڻ���ڣ�" + objStandardGrossweight + "�����ȴ���" + objOGStandardLength + "����Ҫ���ճ������á�";
				}
			}
			if (blLengthMark) {
				//�ǿͻ�
				if(blIsNotCustomer)
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_LW, strOperId, "����������������", false);
				else
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_L, strOperId, "����������������", false);
				
				Issue objIssue= new Issue();
				objIssue.addHoldIssue(strCwcode, "212", strRemark, strOperId);
				
				break;
			}
		}		
	}	
	
	
	/**
	 * ɾ����������
	 * @param strCwcode
	 * @param strEstcode
	 * @throws Exception
	 */
	public void delete(String strCwcode, 
			String strEstcode) throws Exception {
		DeleteSpecialtypeTrans objDSTrans = new DeleteSpecialtypeTrans();
		objDSTrans.setParam(strCwcode, strEstcode);
		objDSTrans.execute();
	}
	
	/**
	 * ����
	 * @param listSpecialtypes
	 * @param strOperId
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public List save(List listSpecialtypes, 
			String strOperId,
			String strCwcode) throws Exception {
		// ��¼��־
		List listOriginSpecialtype = SpecialtypeDemand.load(strCwcode);
		SaveSpecialtypeTrans objSSPTTrans = new SaveSpecialtypeTrans();
		objSSPTTrans.setParam(listSpecialtypes,
				strCwcode,
				strOperId,
				true);
		objSSPTTrans.execute();	
		// ��¼��ע
		List listSpecialtype =  SpecialtypeDemand.load(strCwcode);
		String strBusinessLog = "ԭ�е���������Ϊ��" + SpecialtypeDemand.getBusinessLog(listOriginSpecialtype);
		strBusinessLog = strBusinessLog + 
		"������������Ϊ��" + SpecialtypeDemand.getBusinessLog(listSpecialtype);
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCwcode, strOperId, strBusinessLog);
		// DDP�Զ��ۼ�
		if (SpecialtypeDemand.isExistsSpecialtype(listSpecialtype, "A0201")) {
			Issue objIssue= new Issue();
			objIssue.addHoldIssue(strCwcode, 
					"313", 
					"�˼��˵�ע��������֧����˰����ȷ���Ƿ�һ���ɷ�����֧����˰��ȷ���ɷ�����֧����˰ʱ����˾��֧��һ�����ĸ�˰��֤�𣬹��������ȷ�Ϻ󣬼�ʱ����˾��ϵ������������ˣ�лл��", 
					strOperId);		
		}
		// �Ʒ�
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(strCwcode,
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
				"",
				true);			
		objAFCThread.start();		
		// ����ֵ
		return listSpecialtype;
	}
}
