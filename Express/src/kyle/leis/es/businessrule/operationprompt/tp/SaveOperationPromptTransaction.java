package kyle.leis.es.businessrule.operationprompt.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptColumns;
import kyle.leis.es.businessrule.operationprompt.dax.OperationpromptDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpressspecialtypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperationtacheDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TbrOperationprompt;
import kyle.leis.hi.TbrOptchannel;
import kyle.leis.hi.TbrOptchannelPK;
import kyle.leis.hi.TbrOptcorporation;
import kyle.leis.hi.TbrOptdeparturedistrict;
import kyle.leis.hi.TbrOptdeparturedistrictPK;
import kyle.leis.hi.TbrOptexpressspecialtype;
import kyle.leis.hi.TbrOptexpressspecialtypePK;
import kyle.leis.hi.TbrOptoperationtache;
import kyle.leis.hi.TbrOptoperationtachePK;
import kyle.leis.hi.TbrOptorigindistrict;
import kyle.leis.hi.TbrOptorigindistrictPK;
import net.sf.hibernate.Session;

public class SaveOperationPromptTransaction extends AbstractTransaction {
	
	private OperationpromptColumns m_objOPTColumns;
	private String[] m_astrCustomer;
	private String[] m_astrSupplier;
	private String[] m_astrChannel;
	private String m_strOptn_CsSign;
	private String[] m_astrSpecialType;
	private String[] m_astrOperationTache;
	private String[] m_astrOdtCode;
	private String[] m_astrDdtCode;
	private String m_strOperator;
	private String m_strNewBrId;
	
	
	public void setParameter(OperationpromptColumns objOPTColumns,String[] astrCustomer,String[] astrSupplier,String[] astrChannel,String strOptn_CsSign,String[] astrSpecialType,String[] astrOperationTache,String[] astrOdtCode,String[] astrDdtCode,String strOperator)
	{
		this.m_objOPTColumns =  objOPTColumns;
		this.m_astrCustomer = astrCustomer;
		this.m_astrSupplier = astrSupplier;
		this.m_astrChannel = astrChannel;
		this.m_strOptn_CsSign = strOptn_CsSign;
		this.m_astrSpecialType = astrSpecialType;
		this.m_astrOperationTache = astrOperationTache;
		this.m_astrOdtCode = astrOdtCode;
		this.m_astrDdtCode = astrDdtCode;
		this.m_strOperator = strOperator;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_objOPTColumns == null) return;
		
		TbrBusinessrule objTbrBusinessrule;
		TbrOperationprompt objTbrOperationprompt;
		
		if(StringUtility.isNull(m_objOPTColumns.getOptbrid()))
		{
			objTbrBusinessrule = new TbrBusinessrule();
			objTbrBusinessrule.setBrCreatedate(DateFormatUtility.getSysdate());
			objTbrBusinessrule.setBrModifydate(DateFormatUtility.getSysdate());
			objTbrBusinessrule.setTdiOperatorByBrOpIdCreate(TdiOperatorDC.loadByKey(m_strOperator));
			objTbrBusinessrule.setTdiOperatorByBrOpIdModifier(TdiOperatorDC.loadByKey(m_strOperator));
			objTbrOperationprompt = new TbrOperationprompt();
		}
		else
		{
			objTbrBusinessrule = (TbrBusinessrule)objSession.load(TbrBusinessrule.class, Long.valueOf(m_objOPTColumns.getOptbrid()));////
			objTbrBusinessrule.setBrModifydate(DateFormatUtility.getSysdate());
			objTbrBusinessrule.setTdiOperatorByBrOpIdModifier(TdiOperatorDC.loadByKey(m_strOperator));
			objTbrOperationprompt = (TbrOperationprompt) objSession.load(TbrOperationprompt.class, Long.valueOf(m_objOPTColumns.getOptbrid()));
		}
		
		OperationpromptDemand.setBusinessruleByColumns(objTbrBusinessrule, m_objOPTColumns);
		//保存规则基表
		objSession.save(objTbrBusinessrule);
		Long strBrId = objTbrBusinessrule.getBrId();
		m_strNewBrId = String.valueOf(strBrId);
		OperationpromptDemand.setOperationpromptByColumns(objTbrOperationprompt, m_objOPTColumns);
		objTbrOperationprompt.setTbrBusinessrule(objTbrBusinessrule);
		objTbrOperationprompt.setBrId(strBrId);//
		//保存操作规则
		objSession.save(objTbrOperationprompt);
		//添加对应客户(先删除旧数据，再添加)
		if(OperationpromptDemand.isExistCorporation(String.valueOf(strBrId), "Y"))
			objSession.delete("from TbrOptcorporation optco where optco.comp_id.brId = '"+strBrId+"' and optco.optcCssign = 'Y'"); 
		if(m_astrCustomer != null && m_astrCustomer.length>0)
		{
			for(String strCoCode:m_astrCustomer)
			{
				//保存新数据
				TbrOptcorporation objTbrOptcorporation = new TbrOptcorporation();
				OperationpromptDemand.setOptcorporationByParams(objTbrOptcorporation,strBrId,strCoCode, "Y", objTbrOperationprompt,objSession);
				objSession.save(objTbrOptcorporation);
			}
		}
		//添加对应服务商(先删除旧数据，再添加)
		if(OperationpromptDemand.isExistCorporation(String.valueOf(strBrId), "N"))
			objSession.delete("from TbrOptcorporation optco where optco.comp_id.brId = '"+strBrId+"' and optco.optcCssign = 'N'"); 
		if(m_astrSupplier != null && m_astrSupplier.length>0)
		{
			for(String strCoCode:m_astrSupplier)
			{
				//保存新数据
				TbrOptcorporation objTbrOptcorporation = new TbrOptcorporation();
				OperationpromptDemand.setOptcorporationByParams(objTbrOptcorporation,strBrId, strCoCode, "N",objTbrOperationprompt,objSession);
				objSession.save(objTbrOptcorporation);
			}
		}
		//添加渠道(服务渠道:m_strOptn_CsSign=N)
		if(OperationpromptDemand.isExistChannel(String.valueOf(strBrId), m_strOptn_CsSign))
			objSession.delete("from TbrOptchannel optchn where optchn.comp_id.brId = '"+strBrId+"' and optchn.optnCssign ='"+m_strOptn_CsSign+"'");
		if(m_astrChannel != null && m_astrChannel.length>0)
		{
			for(String strChnCode:m_astrChannel)
			{
				TbrOptchannel objTbrOptchannel = new TbrOptchannel();
				TbrOptchannelPK comp_id = new TbrOptchannelPK();
				comp_id.setBrId(strBrId);
				comp_id.setChnCode(strChnCode);
				objTbrOptchannel.setComp_id(comp_id);
				objTbrOptchannel.setOptnCssign(m_strOptn_CsSign);
				objTbrOptchannel.setTbrOperationprompt(objTbrOperationprompt);
				objTbrOptchannel.setTchnChannel(TchnChannelDC.loadByKey(strChnCode));
				objSession.save(objTbrOptchannel);
			}
		}
		//添加特殊类型
		if(OperationpromptDemand.isExistEst(String.valueOf(strBrId)))
			objSession.delete("from TbrOptexpressspecialtype optest where optest.comp_id.brId = '"+strBrId+"'");
		if(m_astrSpecialType != null && m_astrSpecialType.length>0)
		{
			for(String strEstCode:m_astrSpecialType)
			{
				TbrOptexpressspecialtype objTbrOptexpressspecialtype = new TbrOptexpressspecialtype();
				TbrOptexpressspecialtypePK comp_id = new TbrOptexpressspecialtypePK();
				comp_id.setBrId(strBrId);
				comp_id.setEstCode(strEstCode);
				objTbrOptexpressspecialtype.setComp_id(comp_id);
				objTbrOptexpressspecialtype.setTbrOperationprompt(objTbrOperationprompt);
				objTbrOptexpressspecialtype.setTdiExpressspecialtype(TdiExpressspecialtypeDC.loadByKey(strEstCode));
				objSession.save(objTbrOptexpressspecialtype);
			}
		}
		//添加操作环节
		if(OperationpromptDemand.isExistOt(String.valueOf(strBrId)))
			objSession.delete("from TbrOptoperationtache optot where optot.comp_id.brId = '"+strBrId+"'");
		if(m_astrOperationTache != null && m_astrOperationTache.length>0)
		{
			for(String strOtCode:m_astrOperationTache)
			{
				TbrOptoperationtache objTbrOptoperationtache = new TbrOptoperationtache();
				TbrOptoperationtachePK comp_id = new TbrOptoperationtachePK();
				comp_id.setBrId(strBrId);
				comp_id.setOtCode(strOtCode);
				objTbrOptoperationtache.setComp_id(comp_id);
				objTbrOptoperationtache.setTbrOperationprompt(objTbrOperationprompt);
				// objTbrOptoperationtache.setTdiOperationtache(TdiOperationtacheDC.loadByKey(strOtCode));
				objSession.save(objTbrOptoperationtache);
			}
		}
		//添加起源地
		if(OperationpromptDemand.isExixtOdt(String.valueOf(strBrId)))
			objSession.delete("from TbrOptorigindistrict optodt where optodt.comp_id.brId = '"+strBrId+"'");
		if(m_astrOdtCode != null && m_astrOdtCode.length>0)
		{
			for(String strDtCode:m_astrOdtCode)
			{
				TbrOptorigindistrict objTbrOptorigindistrict = new TbrOptorigindistrict();
				TbrOptorigindistrictPK com_id = new TbrOptorigindistrictPK();
				com_id.setBrId(strBrId);
				com_id.setDtCode(strDtCode);
				objTbrOptorigindistrict.setComp_id(com_id);
				objTbrOptorigindistrict.setTbrOperationprompt(objTbrOperationprompt);
				objTbrOptorigindistrict.setTdiDistrict(TdiDistrictDC.loadByKey(strDtCode));
				objSession.save(objTbrOptorigindistrict);
			}
		}
		//添加目的地
		if(OperationpromptDemand.isExixtDdt(String.valueOf(strBrId)))
			objSession.delete("from TbrOptdeparturedistrict optddt where optddt.comp_id.brId = '"+strBrId+"'");
		if(m_astrDdtCode != null && m_astrDdtCode.length>0)
		{
			for(String strDtCode:m_astrDdtCode)
			{
				TbrOptdeparturedistrict objTbrOptdeparturedistrict = new TbrOptdeparturedistrict();
				TbrOptdeparturedistrictPK comp_id = new TbrOptdeparturedistrictPK();
				comp_id.setBrId(strBrId);
				comp_id.setDtCode(strDtCode);
				objTbrOptdeparturedistrict.setComp_id(comp_id);
//				objTbrOptdeparturedistrict.setOptddEndpostcode(optddEndpostcode);
//				objTbrOptdeparturedistrict.setOptddStartpostcode(optddStartpostcode);
				objTbrOptdeparturedistrict.setTbrOperationprompt(objTbrOperationprompt);
				objTbrOptdeparturedistrict.setTdiDistrict(TdiDistrictDC.loadByKey(strDtCode));
				objSession.save(objTbrOptdeparturedistrict);
			}
		}
	}
	
	public String getNewBrid()
	{
		return this.m_strNewBrId;
	}
}
