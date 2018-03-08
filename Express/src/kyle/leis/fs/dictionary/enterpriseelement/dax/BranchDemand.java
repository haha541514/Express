package kyle.leis.fs.dictionary.enterpriseelement.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.enterpriseelement.da.BranchColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.BranchCondition;
import kyle.leis.fs.dictionary.enterpriseelement.da.BranchQuery;
import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterColumns;
import kyle.leis.hi.TdiBranch;

public class BranchDemand {
	public static List query(BranchCondition objBranchCon) throws Exception
	{
		BranchQuery objBranchQue = new BranchQuery();
		objBranchQue.setCondition(objBranchCon);
		return objBranchQue.getResults();
	}
	
	public static BranchColumns queryByEecode(String strEecode) throws Exception
	{
		if(StringUtility.isNull(strEecode)) return null;
		
		BranchCondition objBranchCon = new BranchCondition();
		objBranchCon.setEeeecode(strEecode);
		List objList = query(objBranchCon);
		if(objList == null ||objList.size()<1) return null;
		return (BranchColumns)objList.get(0);
	}
	
	public static void setBranchbyCol(TdiBranch objTdiBranch,BranchColumns objBranchCol)
	{
		if(!StringUtility.isNull(objBranchCol.getBrbropidcustomerservice()))
			objTdiBranch.setBrOpIdCustomerservice(Long.parseLong(objBranchCol.getBrbropidcustomerservice()));
		else
			objTdiBranch.setBrOpIdCustomerservice(0l);
		if(!StringUtility.isNull(objBranchCol.getBrbropiddunner()))
			objTdiBranch.setBrOpIdDunner(Long.parseLong(objBranchCol.getBrbropiddunner()));
		else 
			objTdiBranch.setBrOpIdDunner(0l);
		if(!StringUtility.isNull(objBranchCol.getBrbropidmanager()))
			objTdiBranch.setBrOpIdManager(Long.parseLong(objBranchCol.getBrbropidmanager()));
		else
			objTdiBranch.setBrOpIdManager(0l);
		if(!StringUtility.isNull(objBranchCol.getBrbropidsaler()))
			objTdiBranch.setBrOpIdSaler(Long.parseLong(objBranchCol.getBrbropidsaler()));
		else
			objTdiBranch.setBrOpIdSaler(0l);
	}
	
	public static void transferDcColToBrCol(TdiBranch objTdiBranch,DistributioncenterColumns objDistributioncenterCol)
	{
		if(!StringUtility.isNull(objDistributioncenterCol.getDcbrbropidcustomerservice()))
			objTdiBranch.setBrOpIdCustomerservice(Long.parseLong(objDistributioncenterCol.getDcbrbropidcustomerservice()));
		else
			objTdiBranch.setBrOpIdCustomerservice(0l);
		if(!StringUtility.isNull(objDistributioncenterCol.getDcbrbropiddunner()))
			objTdiBranch.setBrOpIdDunner(Long.parseLong(objDistributioncenterCol.getDcbrbropiddunner()));
		else 
			objTdiBranch.setBrOpIdDunner(0l);
		if(!StringUtility.isNull(objDistributioncenterCol.getDcbrbropidmanager()))
			objTdiBranch.setBrOpIdManager(Long.parseLong(objDistributioncenterCol.getDcbrbropidmanager()));
		else
			objTdiBranch.setBrOpIdManager(0l);
		if(!StringUtility.isNull(objDistributioncenterCol.getDcbrbropidsaler()))
			objTdiBranch.setBrOpIdSaler(Long.parseLong(objDistributioncenterCol.getDcbrbropidsaler()));
		else
			objTdiBranch.setBrOpIdSaler(0l);
	}
}
