package kyle.leis.es.smsservice.tp;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.smsservice.da.SrrseqColumns;
import kyle.leis.es.smsservice.da.SrrseqQuery;
import kyle.leis.hi.TesSmsrechargerecord;
import kyle.leis.hi.TesSmsrechargerecordPK;
import kyle.leis.hi.TesSmsservice;
import net.sf.hibernate.Session;

public class SaveRechargeRecordTransaction extends AbstractTransaction {
	private String m_strSsId;
	private String m_strAmount;
	private String m_strRemark;
	private String m_strOpCreateName;
	private String m_strNewSrrId;
	
	public String getNewSrrId()
	{
		return this.m_strNewSrrId;
	}
	
	public void setPrame(String strSsId,String strAmount,String strRemark,String strOpCreateName)
	{
		this.m_strSsId = strSsId;
		this.m_strAmount = strAmount;
		this.m_strRemark = strRemark;
		this.m_strOpCreateName = strOpCreateName;
	}
	public void transaction(Session objSession) throws Exception 
	{
		if(StringUtility.isNull(m_strSsId) || StringUtility.isNull(m_strAmount)) return;
		
		//更新余额
		BigDecimal objRechargeamount = new BigDecimal(m_strAmount);
		TesSmsservice objSmsservice = (TesSmsservice)objSession.load(TesSmsservice.class, Long.valueOf(m_strSsId));
		BigDecimal objOldBalanceamount = objSmsservice.getSsBalanceamount();
	    objSmsservice.setSsBalanceamount(objOldBalanceamount.add(objRechargeamount));
	    //保存新余额
	    objSession.save(objSmsservice);
		//新增充值记录
	    TesSmsrechargerecord objSmsrechargerecode = new TesSmsrechargerecord();
	    TesSmsrechargerecordPK comp_id = new TesSmsrechargerecordPK();
	    SrrseqQuery objSrrseqQuery = new SrrseqQuery();
	    List list = objSrrseqQuery.getResults();
	    comp_id.setSrrId(Long.valueOf(((SrrseqColumns)list.get(0)).getSrrseq()));
	    comp_id.setSsId(Long.valueOf(m_strSsId));
	    objSmsrechargerecode.setComp_id(comp_id);
	    objSmsrechargerecode.setSrrAmount(objRechargeamount);
	    objSmsrechargerecode.setSrrCreatedate(DateFormatUtility.getSysdate());
	    objSmsrechargerecode.setSrrOpNameCreate(m_strOpCreateName);
	    objSmsrechargerecode.setSrrRemark(m_strRemark);
	    objSmsrechargerecode.setTesSmsservice(objSmsservice);
	    //保存
	    objSession.save(objSmsrechargerecode);
	    this.m_strNewSrrId = String.valueOf(objSmsrechargerecode.getComp_id().getSrrId());
	}

}
