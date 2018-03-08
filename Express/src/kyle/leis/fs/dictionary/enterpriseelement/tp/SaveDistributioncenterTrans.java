package kyle.leis.fs.dictionary.enterpriseelement.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.EeseqColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.EeseqQuery;
import kyle.leis.fs.dictionary.enterpriseelement.dax.BranchDemand;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TdiBranch;
import kyle.leis.hi.TdiDistributioncenter;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiEnterpriseelement;
import net.sf.hibernate.Session;

public class SaveDistributioncenterTrans extends AbstractTransaction {

	private DistributioncenterColumns m_objDistributioncenterCol;
	private String m_strOperId;
	private String m_strNewEecode;
	private List m_listEECity;
	
	public String getNewEecode()
	{
		return this.m_strNewEecode;
	}
	
	public void setParam(DistributioncenterColumns objDistributioncenterCol,
			List listEECity,
			String strOperId)
	{
		this.m_objDistributioncenterCol = objDistributioncenterCol;
		this.m_strOperId = strOperId;
		m_listEECity = listEECity;
	}
	public void transaction(Session objSession) throws Exception {
		if(m_objDistributioncenterCol == null || StringUtility.isNull(m_strOperId)) return;
		
		TdiEnterpriseelement objTdiEnterpriseelement;
		TdiDistributioncenter objTdistributioncenter;
		TdiBranch objTdiBranch;
		
		if(!StringUtility.isNull(m_objDistributioncenterCol.getDceecode()))
		{
			/*objTdiEnterpriseelement = TdiEnterpriseelementDC.loadByKey(m_objDistributioncenterCol.getDceecode());
			objTdistributioncenter = TdiDistributioncenterDC.loadByKey(m_objDistributioncenterCol.getDceecode());*/
			objTdiEnterpriseelement = (TdiEnterpriseelement)objSession.load(TdiEnterpriseelement.class, m_objDistributioncenterCol.getDceecode());
			objTdistributioncenter = (TdiDistributioncenter)objSession.load(TdiDistributioncenter.class,m_objDistributioncenterCol.getDceecode());
		}
		else
		{
			objTdiEnterpriseelement = new TdiEnterpriseelement();
			EeseqQuery objEeseqQuery = new EeseqQuery();
			EeseqColumns objEeseqColumns = (EeseqColumns)objEeseqQuery.getResults().get(0);
			objTdiEnterpriseelement.setEeCode(objEeseqColumns.getEeseq());//设置主键
			objTdiEnterpriseelement.setEeCreatedate(DateFormatUtility.getSysdate());
			objTdiEnterpriseelement.setEeOpIdCreator(Long.parseLong(m_strOperId));
			
			objTdistributioncenter = new TdiDistributioncenter();
			objTdistributioncenter.setEeCode(objTdiEnterpriseelement.getEeCode());
		}
		
		//增加分公司属性
		if(!StringUtility.isNull(m_objDistributioncenterCol.getDcbreecode()))
			objTdiBranch = (TdiBranch)objSession.load(TdiBranch.class, m_objDistributioncenterCol.getDcbreecode());
		else
		{
			objTdiBranch = new TdiBranch();
			objTdiBranch.setEeCode(objTdiEnterpriseelement.getEeCode());
		}
		
		EnterpriseelementDemand.transferDcColToEeCol(objTdiEnterpriseelement, m_objDistributioncenterCol, m_strOperId);
		objSession.save(objTdiEnterpriseelement);
		
		BranchDemand.transferDcColToBrCol(objTdiBranch, m_objDistributioncenterCol);
		objTdiBranch.setTdiEnterpriseelement(objTdiEnterpriseelement);
		objSession.save(objTdiBranch);
		
		objTdistributioncenter.setTdiBranch(objTdiBranch);
		objTdistributioncenter.setTdiEnterpriseelement(objTdiEnterpriseelement);
		objTdistributioncenter.setTdiDistrict((TdiDistrict)TdiDistrictDC.loadByKey(m_objDistributioncenterCol.getDcdtbdtcode()));	
		objSession.save(objTdistributioncenter);
		// 保存对应的城市
		SaveEECityTransaction objSEET = new SaveEECityTransaction();
		objSEET.setParam(m_listEECity, objTdiEnterpriseelement);
		objSEET.transaction(objSession);		
		
		if(objTdiEnterpriseelement.getEeCode().equals(objTdistributioncenter.getEeCode()))
			m_strNewEecode = objTdiEnterpriseelement.getEeCode();
	}

}
