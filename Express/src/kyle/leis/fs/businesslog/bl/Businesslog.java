package kyle.leis.fs.businesslog.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.tp.SaveBatchTrackTransaction;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.fs.businesslog.da.BusinesslogColumns;
import kyle.leis.fs.businesslog.dax.BusinesslogDemand;
import kyle.leis.fs.businesslog.tp.SaveBusinesslogTransaction;

public class Businesslog {
	/**
	 * 操作备注
	 * @param strBusinessobjectcode
	 * @param strOperId
	 * @param strRemark
	 * @throws Exception
	 */
	public void addBusinessLog(String strBusinessobjectcode,
			String strOperId, 
			String strRemark) throws Exception {
		addBusinessLog(strBusinessobjectcode, 
				strOperId, 
				"", 
				strRemark);
	}
	/**
	 * Corewaybillpieces批量货物详细信息备注操作
	 * @param strBusinessobjectcode
	 * @param strOperId
	 * @param objList
	 * @throws Exception
	 */
	public void addBusinessLog(String strBusinessobjectcode,
			String strOperId, 
			List objList) throws Exception {
		if(objList == null || objList.size() < 0) return;
		String strSign = ""; //判断备注是否超过指定字符
		
		for(int i = 0;i<objList.size();i++){
			CorewaybillpiecesColumns objColumns = (CorewaybillpiecesColumns) objList.get(i);
			BigDecimal weight = new BigDecimal(objColumns.getCpcpgrossweight());
			BigDecimal length = new BigDecimal(objColumns.getCpcplength());			
			BigDecimal height = new BigDecimal(objColumns.getCpcpheight());
			BigDecimal width = new BigDecimal(objColumns.getCpcpwidth());
			int n = i + 1;
			strSign = strSign + "第"+n+"件的实重为"+weight+",长*宽*高="+length+"*"+width+"*"+height+"。";				
			String strLength = strSign.replaceAll("[^\\x00-\\xff]", "**");

		    //如果备注内容超过256个字符
			if(strLength.length() > 256){		
				//取得所有记录的集合
		    	String array[] = strSign.split("。");	
		    	//将除最后一条记录之外的内容放到strRemark中
		    	String strRemark = "";//备注
		    	int len = array.length;
		    	for(int j = 0 ;j<len-1;j++){		    		
		    		strRemark = strRemark + array[j] +"。";		    		
		    	}
		    	//将判断备注而取得的备注内容清空
		    	strSign = array[len - 1]+"。";
		    	
		    	addBusinessLog(strBusinessobjectcode, 
						"0", 
						"", 
						strRemark);
		    }
			
			//保存最后一组备注
			if(i == objList.size()-1){
				addBusinessLog(strBusinessobjectcode, 
						"0", 
						"", 
						strSign);
			}
		}
	}
	
	public void addBusinessLog(String strBusinessobjectcode,
			String strOperId,
			String strBlkcode,
			String strRemark) throws Exception {
		if (StringUtility.isNull(strRemark))
			return;
		BusinesslogColumns objBusinesslogCol = new BusinesslogColumns();
		objBusinesslogCol.setBlogblbusinesscode(strBusinessobjectcode);
		objBusinesslogCol.setBlogblcontent(strRemark);
		objBusinesslogCol.setOpopid(Long.parseLong(strOperId));
		objBusinesslogCol.setBlkblkcode(strBlkcode);
		// 保存
		SaveBusinesslogTransaction saveBusinesslog = new SaveBusinesslogTransaction();
		saveBusinesslog.setParam(objBusinesslogCol);
		saveBusinesslog.execute();
		// 客服员记录的备注则插入到批次轨迹表
		if (!StringUtility.isNull(strBlkcode) && 
				(strBlkcode.equals("OP") || strBlkcode.startsWith("CS"))) {
			SaveBatchTrackTransaction objSBTTrans = new SaveBatchTrackTransaction();
			objSBTTrans.setLatestBusinessLog(strBusinessobjectcode, 
					strOperId, 
					strRemark);
			objSBTTrans.execute();
		}
	}
	
	/*
	 * 返回结果集(再次查询的集)
	 */
	public List save(BusinesslogColumns objBusinesslogCol) throws Exception
	{
		SaveBusinesslogTransaction saveBusinesslog = new SaveBusinesslogTransaction();
		saveBusinesslog.setParam(objBusinesslogCol);
		saveBusinesslog.execute();
		// 客服员记录的备注则插入到批次轨迹表
		String strBlkcode = objBusinesslogCol.getBlkblkcode();
		if (!StringUtility.isNull(strBlkcode) && 
				(strBlkcode.equals("OP") || strBlkcode.startsWith("CS"))) {
			SaveBatchTrackTransaction objSBTTrans = new SaveBatchTrackTransaction();
			objSBTTrans.setLatestBusinessLog(objBusinesslogCol.getBlogblbusinesscode(), 
					objBusinesslogCol.getOpopid(), 
					objBusinesslogCol.getBlogblcontent());
			objSBTTrans.execute();		
		}
		String blBusinesscode = saveBusinesslog.getM_strBlBusinesscode();
		if (!StringUtility.isNull(blBusinesscode) && !blBusinesscode.equals("USOL"))
			return BusinesslogDemand.loadByblBusinesscode(blBusinesscode);
		else
			return new ArrayList();
	}
}
