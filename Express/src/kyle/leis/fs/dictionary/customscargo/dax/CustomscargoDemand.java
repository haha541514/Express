package kyle.leis.fs.dictionary.customscargo.dax;

import java.text.SimpleDateFormat;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoColumns;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoCondition;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoQuery;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoseqColumns;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoseqQuery;
import kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameCondition;
import kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameQuery;
import kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameseqColumns;
import kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameseqQuery;
import kyle.leis.hi.TdiCustomscargo;
import net.sf.hibernate.Session;

public class CustomscargoDemand {

	public static List query(CustomscargoCondition objCustomscargoCondition) throws Exception{
		CustomscargoQuery objCustomscargoQuery=new CustomscargoQuery();
		String strCcename=objCustomscargoCondition.getCcename();
		String StrCheckEname=objCustomscargoCondition.getCheckename();
		
		if (!StringUtility.isNull(strCcename) && strCcename.indexOf("'") > 0){
			objCustomscargoCondition.setCcename(strCcename.replaceAll("'", "''"));
		}
		if (!StringUtility.isNull(StrCheckEname) && StrCheckEname.indexOf("'") > 0){
			objCustomscargoCondition.setCheckename(StrCheckEname.replaceAll("'", "''"));
		}
		objCustomscargoQuery.setCondition(objCustomscargoCondition);
		return objCustomscargoQuery.getResults();
	}
	
	public static List queryMemorydeclarename(MemorydeclarenameCondition objMDC) throws Exception {
		MemorydeclarenameQuery objCustomscargoQuery = new MemorydeclarenameQuery();
		objCustomscargoQuery.setCondition(objMDC);
		return objCustomscargoQuery.getResults();
	}	
	
	
	public static void setCustomscargoByColumns(TdiCustomscargo objTdiCustomscargo,
			CustomscargoColumns objChannelColumns,
			Session objSession) throws Exception{
		if(StringUtility.isNull(objChannelColumns.getCccccode())){
			objTdiCustomscargo.setCcCode(CustomscargoDemand.getNewCustomscargoCode());
		}else{
			objTdiCustomscargo.setCcCode(objChannelColumns.getCccccode());
		}
		objTdiCustomscargo.setCcEname(objChannelColumns.getCcccename());
		objTdiCustomscargo.setCcHscode(objChannelColumns.getCccchscode());
		objTdiCustomscargo.setCcModifydate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objChannelColumns.getCcccmodifydate()));
		objTdiCustomscargo.setCcName(objChannelColumns.getCcccname());
		objTdiCustomscargo.setCcOpIdModifier(new Long(objChannelColumns.getCcccopidmodifier()));
		objTdiCustomscargo.setCcUnittype(objChannelColumns.getCcccunittype());
		
	}
	
	public static String getNewCustomscargoCode() throws Exception {
		CustomscargoseqQuery objCustomscargoseqQuery = new CustomscargoseqQuery();
		List objList = objCustomscargoseqQuery.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception ("无法根据海关货物的序列号生成主键!"));
		return ((CustomscargoseqColumns)objList.get(0)).getCustomscargoseq();
	}
	
	public static String getMemorydeclarenameseq() throws Exception {
		MemorydeclarenameseqQuery objMDNQ = new MemorydeclarenameseqQuery();
		List objList = objMDNQ.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception ("无法生成品名助记的序列!"));
		return ((MemorydeclarenameseqColumns)objList.get(0)).getMemorydeclarenameseq();
	}	
	
}
