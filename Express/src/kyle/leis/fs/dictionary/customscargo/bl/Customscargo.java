package kyle.leis.fs.dictionary.customscargo.bl;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoColumns;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoCondition;
import kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameColumns;
import kyle.leis.fs.dictionary.customscargo.dax.CustomscargoDemand;
import kyle.leis.fs.dictionary.customscargo.tp.AddCustomscargoTranscation;
import kyle.leis.fs.dictionary.customscargo.tp.DeleteCustomscargoTranscation;
import kyle.leis.fs.dictionary.customscargo.tp.DeleteMemoryDeclareTrans;
import kyle.leis.fs.dictionary.customscargo.tp.ModifyCusotmscargoTranscation;
import kyle.leis.fs.dictionary.customscargo.tp.SaveMemoryDeclareTrans;

public class Customscargo {
	
	public CustomscargoColumns saveCustomscargo(List ccList) throws Exception{
		
		//检查结果集里面的数据是否存在，存在则修改，不存在则增加
		String ccCode=((CustomscargoColumns)ccList.get(0)).getCccccode();
		CustomscargoCondition objCustomscargoCondition=new CustomscargoCondition();
		if(StringUtility.isNull(ccCode)){
			objCustomscargoCondition.setCccodes("  ");
		}else{
			objCustomscargoCondition.setCccodes(ccCode);
		}
		List customscargoList=CustomscargoDemand.query(objCustomscargoCondition);
		
		if(customscargoList.size()==0){
			//可增加一条或者批量增加
			AddCustomscargoTranscation objAdd=new AddCustomscargoTranscation();
			objAdd.setParam(ccList);
			objAdd.execute();
			return null;
		}else{
			//只可修改一条数据
			ModifyCusotmscargoTranscation objModify=new ModifyCusotmscargoTranscation();
			objModify.setParam((CustomscargoColumns)ccList.get(0));
			objModify.execute();
			return (CustomscargoColumns) customscargoList.get(0);
		}
	}
	
	public void deleteCustomscargo(String ccCode)throws Exception{
		DeleteCustomscargoTranscation objDelete=new DeleteCustomscargoTranscation();
		objDelete.setParam(ccCode);
		objDelete.execute();
	}
	
	public MemorydeclarenameColumns saveMemoryDeclare(MemorydeclarenameColumns objMDNColumns) 
	throws Exception {
		SaveMemoryDeclareTrans objSMDT = new SaveMemoryDeclareTrans();
		objSMDT.setParam(objMDNColumns);
		objSMDT.execute();
		
		return objSMDT.getSavedColumns();
	}
	
	public void deleteMemoryDeclare(String strMndcode)throws Exception{
		DeleteMemoryDeclareTrans objDelete = new DeleteMemoryDeclareTrans();
		objDelete.setParam(strMndcode);
		objDelete.execute();
	}	
}
