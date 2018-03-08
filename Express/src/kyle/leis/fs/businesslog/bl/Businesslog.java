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
	 * ������ע
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
	 * Corewaybillpieces����������ϸ��Ϣ��ע����
	 * @param strBusinessobjectcode
	 * @param strOperId
	 * @param objList
	 * @throws Exception
	 */
	public void addBusinessLog(String strBusinessobjectcode,
			String strOperId, 
			List objList) throws Exception {
		if(objList == null || objList.size() < 0) return;
		String strSign = ""; //�жϱ�ע�Ƿ񳬹�ָ���ַ�
		
		for(int i = 0;i<objList.size();i++){
			CorewaybillpiecesColumns objColumns = (CorewaybillpiecesColumns) objList.get(i);
			BigDecimal weight = new BigDecimal(objColumns.getCpcpgrossweight());
			BigDecimal length = new BigDecimal(objColumns.getCpcplength());			
			BigDecimal height = new BigDecimal(objColumns.getCpcpheight());
			BigDecimal width = new BigDecimal(objColumns.getCpcpwidth());
			int n = i + 1;
			strSign = strSign + "��"+n+"����ʵ��Ϊ"+weight+",��*��*��="+length+"*"+width+"*"+height+"��";				
			String strLength = strSign.replaceAll("[^\\x00-\\xff]", "**");

		    //�����ע���ݳ���256���ַ�
			if(strLength.length() > 256){		
				//ȡ�����м�¼�ļ���
		    	String array[] = strSign.split("��");	
		    	//�������һ����¼֮������ݷŵ�strRemark��
		    	String strRemark = "";//��ע
		    	int len = array.length;
		    	for(int j = 0 ;j<len-1;j++){		    		
		    		strRemark = strRemark + array[j] +"��";		    		
		    	}
		    	//���жϱ�ע��ȡ�õı�ע�������
		    	strSign = array[len - 1]+"��";
		    	
		    	addBusinessLog(strBusinessobjectcode, 
						"0", 
						"", 
						strRemark);
		    }
			
			//�������һ�鱸ע
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
		// ����
		SaveBusinesslogTransaction saveBusinesslog = new SaveBusinesslogTransaction();
		saveBusinesslog.setParam(objBusinesslogCol);
		saveBusinesslog.execute();
		// �ͷ�Ա��¼�ı�ע����뵽���ι켣��
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
	 * ���ؽ����(�ٴβ�ѯ�ļ�)
	 */
	public List save(BusinesslogColumns objBusinesslogCol) throws Exception
	{
		SaveBusinesslogTransaction saveBusinesslog = new SaveBusinesslogTransaction();
		saveBusinesslog.setParam(objBusinesslogCol);
		saveBusinesslog.execute();
		// �ͷ�Ա��¼�ı�ע����뵽���ι켣��
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
