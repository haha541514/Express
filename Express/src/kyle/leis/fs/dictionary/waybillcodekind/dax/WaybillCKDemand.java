package kyle.leis.fs.dictionary.waybillcodekind.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindColumns;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindCondition;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindQuery;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindseqColumns;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindseqQuery;
import kyle.leis.hi.TdiLabelformat;
import kyle.leis.hi.TdiWaybillcodekind;
import net.sf.hibernate.Session;

public class WaybillCKDemand {
	
	@SuppressWarnings("unchecked")
	public static List query(WaybillcodekindCondition objWBCKCondition) throws Exception {
		WaybillcodekindQuery objWBCKQuery = new WaybillcodekindQuery();
		objWBCKQuery.setCondition(objWBCKCondition);
		List<WaybillcodekindColumns> objList = objWBCKQuery.getResults();
		if(objList == null || objList.size() == 0)
			return null;
		return objList;
	}
	
	public static void setWaybillCKByColumns(TdiWaybillcodekind objWaybillCK,
			WaybillcodekindColumns objColumns, Session objSession)
			throws Exception {
		if(!StringUtility.isNull(objColumns.getWbckbck_code())){
			objWaybillCK.setBckCode(objColumns.getWbckbck_code());
		}else{
			objWaybillCK.setBckCode(getNewWaybillcodekindcode());
		}
		if(!StringUtility.isNull(objColumns.getWbckbck_buildvaluesign())){
			objWaybillCK.setBckBuildvaluesign(objColumns.getWbckbck_buildvaluesign());
		}
		if(!StringUtility.isNull(objColumns.getWbckbck_ename())){
			objWaybillCK.setBckEname(objColumns.getWbckbck_ename());
		}

		if(!StringUtility.isNull(objColumns.getWbckbck_groupcode())){
			objWaybillCK.setBckGroupcode(objColumns.getWbckbck_groupcode());
		}
		if(!StringUtility.isNull(objColumns.getWbckbck_name())){
			objWaybillCK.setBckName(objColumns.getWbckbck_name());
		}
		objWaybillCK.setBckFromwebservicesign(objColumns.getWbckbck_fromwebservicesign());
		if(!StringUtility.isNull(objColumns.getWbcklf_code())){
			objWaybillCK.setTdiLabelformat((TdiLabelformat) objSession.load(TdiLabelformat.class, 
					objColumns.getWbcklf_code()));	
		}
	}
	public static String getNewWaybillcodekindcode() throws Exception {
		WaybillcodekindseqQuery objWaybillcodekindseqQuery = new WaybillcodekindseqQuery();
		List objList = objWaybillcodekindseqQuery.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception ("无法根据单据种类的序列号生成主键!"));
		return ((WaybillcodekindseqColumns)objList.get(0)).getBckcode();
	}
	
}
