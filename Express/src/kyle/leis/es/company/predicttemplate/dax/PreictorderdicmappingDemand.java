package kyle.leis.es.company.predicttemplate.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.predicttemplate.da.PredictdicmappingColumns;
import kyle.leis.es.company.predicttemplate.da.PredictdicmappingCondition;
import kyle.leis.es.company.predicttemplate.da.PredictdicmappingQuery;
import kyle.leis.hi.TcoPredictordertemplate;
import kyle.leis.hi.TcoPreictorderdicmapping;
import kyle.leis.hi.TdiDictionarymappingkind;
import net.sf.hibernate.Session;

/**
 * @author Synchrn
 * @date:2012-5-17
 * @version :1.0
 * 
 */
public class PreictorderdicmappingDemand {
	public static void setColumns(
			TcoPreictorderdicmapping objTcoPreictorderdicmapping,
			PredictdicmappingColumns objPredictdicmappingColumns,
			Session objSession) throws Exception {
		System.out.println(objPredictdicmappingColumns.getDmkdmkcode() + "-"
				+ objPredictdicmappingColumns.getPodmpodmoriginvalue() + "-"
				+ objPredictdicmappingColumns.getPotpotid() + "-"
				+ objPredictdicmappingColumns.getPodmpodmstandardvalue());

		// ����ģ��
		if (!StringUtility.isNull(objPredictdicmappingColumns.getPotpotid())) {
			objTcoPreictorderdicmapping
					.setTcoPredictordertemplate((TcoPredictordertemplate) objSession
							.load(TcoPredictordertemplate.class, Long
									.valueOf(objPredictdicmappingColumns
											.getPotpotid())));
		}
		// ��������
		if (!StringUtility.isNull(objPredictdicmappingColumns.getDmkdmkcode())) {
			objTcoPreictorderdicmapping
					.setTdiDictionarymappingkind((TdiDictionarymappingkind) objSession
							.load(TdiDictionarymappingkind.class,
									objPredictdicmappingColumns.getDmkdmkcode()));
		}

		if (!StringUtility.isNull(objPredictdicmappingColumns
				.getPodmpodmoriginvalue())) {
			objTcoPreictorderdicmapping
					.setPodmOriginvalue(objPredictdicmappingColumns
							.getPodmpodmoriginvalue());
		}

		if (!StringUtility.isNull(objPredictdicmappingColumns
				.getPodmpodmstandardvalue())) {
			objTcoPreictorderdicmapping
					.setPodmStandardvalue(objPredictdicmappingColumns
							.getPodmpodmstandardvalue());
		}

		if(!StringUtility.isNull(objPredictdicmappingColumns.getPodmpodmid())){
			objTcoPreictorderdicmapping.setPodmId(Long.valueOf(objPredictdicmappingColumns.getPodmpodmid()));
		}
		// System.out.println("---------");
		// objTcoPreictorderdicmapping.setPodmId(Long.valueOf(PreictorderdicmappingDemand.getPodmId()));
		// }

	}

	//@SuppressWarnings("unchecked")
	//public static String getPodmId() throws Exception {
		//ScPredictdicmappingSequenceQuery objSCSQuery = new ScPredictdicmappingSequenceQuery();
		//List objList = objSCSQuery.getResults();
		//if (objList == null || objList.size() < 1) {
			//throw (new Exception("�޷�ȡ�ù�����������"));
		//}
		//return ((ScPredictdicmappingSequenceColumns) objList.get(0))
				//.getPredictdicmappingSeq();
	//}
	
	/**
	 * ��ù����б�
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List queryListCountry(PredictdicmappingCondition objPredictdicmappingCondition) throws Exception{
		PredictdicmappingQuery objQuery = new PredictdicmappingQuery();
		objQuery.setCondition(objPredictdicmappingCondition);
		List objList = objQuery.getResults();
		if(objList == null || objList.size()<0)
			return null;
		return objList;
	}
	
}
