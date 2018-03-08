package kyle.leis.eo.billing.receivable.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.billing.receivable.bl.Receivable;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableCondition;
import kyle.leis.eo.billing.receivable.da.ReceivableforfaxCondition;
import kyle.leis.eo.billing.receivable.dax.ReceivableColumnsForImport;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;

public class ReceivableService extends AService {
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		ReceivableCondition objReceivableCondition = (ReceivableCondition)objPD.getParameter(0, 
				ReceivableCondition.class);
		List objList = ReceivableDemand.query(objReceivableCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String queryForfax(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		ReceivableforfaxCondition objRFFCondition = (ReceivableforfaxCondition)objPD.getParameter(0, 
				ReceivableforfaxCondition.class);
		List objList = ReceivableDemand.queryForfax(objRFFCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);

		List listRvColumns = objPD.getParameterList(0, ReceivableColumns.class);
		String strCwcode = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Receivable objReceivable = new Receivable();
		objReceivable.save(listRvColumns, strCwcode, strOperId);
		return "";
	}
	
	/**
	 * 确定
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String confirm(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String[] astrRvid = objPD.getParameterArray(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Receivable objReceivable = new Receivable();
		objReceivable.modifyStatus(astrRvid, strOperId, "C");
		return "";
	}
	
	/**
	 * 删除
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String eliminate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String[] astrRvid = objPD.getParameterArray(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Receivable objReceivable = new Receivable();
		objReceivable.delete(astrRvid, strOperId);
		return "";
	}
	
	/**
	 * 根据主键装载记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strCwcode = (String)objPD.getParameter(0, String.class);
		List objList = ReceivableDemand.load(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	public String importReceivable(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		List listRvColumnsforimport = objPD.getParameterList(0, ReceivableColumnsForImport.class);
		String strOperID = (String)objPD.getParameter(1, String.class);		
		
		Receivable objReceivable = new Receivable();
		PromptUtilityCollection objPU = objReceivable.importReceivable(listRvColumnsforimport, strOperID);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPU.toStringArray());
		return objEncode.toString();
	}	
	
}
