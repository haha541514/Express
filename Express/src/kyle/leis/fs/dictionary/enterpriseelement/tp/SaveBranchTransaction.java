package kyle.leis.fs.dictionary.enterpriseelement.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.enterpriseelement.da.BranchColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.EeseqColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.EeseqQuery;
import kyle.leis.fs.dictionary.enterpriseelement.dax.BranchDemand;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TdiBranch;
import kyle.leis.hi.TdiEnterpriseelement;
import net.sf.hibernate.Session;

public class SaveBranchTransaction extends AbstractTransaction {

	private BranchColumns m_objBranchCol;
	private String m_strOperId;
	private String m_strNewEecode;
	private List m_listEECity;
	
	public String getNewEecode()
	{
		return this.m_strNewEecode;
	}
	
	public void setParam(BranchColumns objBranchCol,
			List listEECity,
			String strOperId)
	{
		this.m_objBranchCol = objBranchCol;
		this.m_strOperId = strOperId;
		m_listEECity = listEECity;
	}
	public void transaction(Session objSession) throws Exception {
		if(m_objBranchCol == null || StringUtility.isNull(m_strOperId)) return;
		
		TdiEnterpriseelement objTdiEnterpriseelement;
		TdiBranch objTdiBranch;
		if(StringUtility.isNull(m_objBranchCol.getBreecode()))
		{
			objTdiEnterpriseelement = new TdiEnterpriseelement();
			EeseqQuery objEeseqQuery = new EeseqQuery();
			EeseqColumns objEeseqColumns = (EeseqColumns)objEeseqQuery.getResults().get(0);
			objTdiEnterpriseelement.setEeCode(objEeseqColumns.getEeseq());//设置主键
			objTdiEnterpriseelement.setEeCreatedate(DateFormatUtility.getSysdate());
			objTdiEnterpriseelement.setEeOpIdCreator(Long.parseLong(m_strOperId));
			
			objTdiBranch = new TdiBranch();
			objTdiBranch.setEeCode(objTdiEnterpriseelement.getEeCode());
		}
		else
		{
			/*objTdiEnterpriseelement = TdiEnterpriseelementDC.loadByKey(m_objBranchCol.getBreecode());
			objTdiBranch = TdiBranchDC.loadByKey(m_objBranchCol.getBreecode());*/
			objTdiEnterpriseelement = (TdiEnterpriseelement)objSession.load(TdiEnterpriseelement.class, m_objBranchCol.getBreecode());
			objTdiBranch = (TdiBranch)objSession.load(TdiBranch.class, m_objBranchCol.getBreecode());
		}
		
		EnterpriseelementDemand.transferBrColToEeCol(objTdiEnterpriseelement,m_objBranchCol,m_strOperId);
		BranchDemand.setBranchbyCol(objTdiBranch, m_objBranchCol);
		
		objSession.save(objTdiEnterpriseelement);
		objTdiBranch.setTdiEnterpriseelement(objTdiEnterpriseelement);
		objSession.save(objTdiBranch);
		// 保存所支持的城市
		SaveEECityTransaction objSEET = new SaveEECityTransaction();
		objSEET.setParam(m_listEECity, objTdiEnterpriseelement);
		objSEET.transaction(objSession);
		
		if(objTdiEnterpriseelement.getEeCode().equals(objTdiBranch.getEeCode()))
			m_strNewEecode = objTdiEnterpriseelement.getEeCode();
	}

}
