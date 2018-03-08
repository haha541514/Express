package kyle.leis.fs.dictionary.expressspecialtype.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.expressspecialtype.bl.Expressspecialtype;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeColumns;
import kyle.leis.fs.dictionary.expressspecialtype.da.ExpressspecialtypeCondition;
import kyle.leis.fs.dictionary.expressspecialtype.dax.ExpressspecialtypeDemand;

public class ExpressspecialtypeService extends AService {
	
	/**
	 * 获取类型代码（主键）
	 * @throws Exception 
	 */
	public String getSpecialTypeCode(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 2, this);
		
		String code = (String)objPD.getParameter(0, String.class);
		String lever = (String) objPD.getParameter(1, String.class);
		String productCode = ExpressspecialtypeDemand.getTypeCode(code, lever);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(productCode);
		return objEncode.toString();
	}
	/**
	 * 添加特殊类型
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String addExpressspecialtype(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		ExpressspecialtypeColumns columns = 
			(ExpressspecialtypeColumns) objPD.getParameter(0, ExpressspecialtypeColumns.class);
		Expressspecialtype expressspecialtype = new Expressspecialtype();
		ExpressspecialtypeColumns returnColumns = expressspecialtype.addExpressspecialtype(columns);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(returnColumns);
		return objEncode.toString();
	}
	/**
	 * 删除类型
	 * @throws Exception 
	 */
	public String delExpressspecialtype(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		String pkCode = (String) objPD.getParameter(0, String.class);
		Expressspecialtype expressspecialtype = new Expressspecialtype();
		ExpressspecialtypeColumns returnDel = expressspecialtype.delExpressspecialtype(pkCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(returnDel);
		return objEncode.toString();
	}
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		ExpressspecialtypeCondition condition = 
			(ExpressspecialtypeCondition) objPD.getParameter(0, ExpressspecialtypeCondition.class);
		List<ExpressspecialtypeColumns> list = ExpressspecialtypeDemand.query(condition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(list);
		return objEncode.toString();
	}
}
