package kyle.leis.fs.dictionary.enterpriseelement.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.enterpriseelement.bl.Branch;
import kyle.leis.fs.dictionary.enterpriseelement.da.BranchColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.BranchCondition;
import kyle.leis.fs.dictionary.enterpriseelement.da.EecityColumns;
import kyle.leis.fs.dictionary.enterpriseelement.dax.BranchDemand;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;

public class BranchService extends AService {

	/*
	 * 查询分公司
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		BranchCondition objBranchCon = (BranchCondition) objPD.getParameter(0, BranchCondition.class);
		List objList = BranchDemand.query(objBranchCon);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objList);
		return objEncoder.toString();
	}
	
	public String queryEecity(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strEecode = (String)objPD.getParameter(0, String.class);
		List objList = EnterpriseelementDemand.queryEecity(strEecode);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objList);
		return objEncoder.toString();
	}
	
	/*
	 * 添加分公司
	 */
	public String addBranch(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD, 3, this);
		
		BranchColumns objBranchCol = (BranchColumns) objPD.getParameter(0, BranchColumns.class);
		List listEECity = objPD.getParameterList(1, EecityColumns.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Branch objBranch = new Branch();
		BranchColumns objBranchReturn = objBranch.addBranch(objBranchCol, listEECity, strOperId);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objBranchReturn);
		return objEncoder.toString();
	}
}
