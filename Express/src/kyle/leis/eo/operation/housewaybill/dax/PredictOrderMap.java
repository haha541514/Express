package kyle.leis.eo.operation.housewaybill.dax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictOrderMap {
	private Map<String, List<PredictOrderColumnsEX>> m_hmPromptWaybill = new HashMap<String, List<PredictOrderColumnsEX>> ();
	private List<PredictOrderColumnsEX> listNormalWaybill = new ArrayList<PredictOrderColumnsEX>();
	private List<PredictOrderColumnsEX> listSavedWaybill = new ArrayList<PredictOrderColumnsEX>();//�ѱ���Ķ���
	private List<PredictOrderColumnsEX> listRepeatedWaybill = new ArrayList<PredictOrderColumnsEX>();//�ϲ��Ķ���
	private boolean uploadComplete;//�Ƿ��ϴ����
	
	public void putPrompt(String strPromptInfo,
			PredictOrderColumnsEX objPOC) {
		List<PredictOrderColumnsEX> listPOCEX  = new ArrayList<PredictOrderColumnsEX>();
		if (m_hmPromptWaybill.containsKey(strPromptInfo))
			listPOCEX = m_hmPromptWaybill.get(strPromptInfo);
		objPOC.setIndex(listPOCEX.size() + 1);
		listPOCEX.add(objPOC);
		m_hmPromptWaybill.put(strPromptInfo, listPOCEX);
	}
	
	public void putPrompt(String strPromptInfo,
			List<PredictOrderColumnsEX> listPOCEX) {
		if (listPOCEX != null && listPOCEX.size() > 0) {
			for (PredictOrderColumnsEX objPOCEX :  listPOCEX)
				putPrompt(strPromptInfo, objPOCEX);
		}
	}
	
	public void putPromptByrow(String strPromptInfo,
			List<PredictOrderRow> listPredictOrderRow) {
		if (listPredictOrderRow != null && listPredictOrderRow.size() > 0) {
			for (int i = 0; i < listPredictOrderRow.size(); i++) {
				putPrompt(strPromptInfo, new PredictOrderColumnsEX());
			}
		}
	}		
	
	public void putNormal(PredictOrderColumnsEX objWFPColumns) {
		listNormalWaybill.add(objWFPColumns);
	}
	
	public List<PredictOrderColumnsEX> getNormalWaybill() {
		return listNormalWaybill;
	}
	
	public int getNormalWaybillSize() {
		if (listNormalWaybill == null || listNormalWaybill.size() < 1)
			return 0;
		return listNormalWaybill.size();
	}	
	
	
	public Map getPromptWaybill() {
		return m_hmPromptWaybill;
		
	}
	/**
	 * ��ӱ���ɹ��Ķ���
	 * @param objWFPColumns
	 */
	public void putSavedSeccess(PredictOrderColumnsEX objWFPColumns){
		listSavedWaybill.add(objWFPColumns);
	}
	/**
	 * ��ȡ�ѳɹ�����Ķ���
	 * @return
	 */
	public List<PredictOrderColumnsEX> getSavedSeccessWaybill(){
		return listSavedWaybill;
	}
	/**
	 * ��Ӻϲ��Ķ���
	 * @param objWFPColumns
	 */
	public void putRepeatedWaybill(PredictOrderColumnsEX objWFPColumns){
		listRepeatedWaybill.add(objWFPColumns);
	}
	/**
	 * ��ȡ�ϲ��Ķ���
	 * @return
	 */
	public List<PredictOrderColumnsEX> getRepeatedWaybill(){
		return listRepeatedWaybill;
	}
	/**
	 * �ϴ��Ƿ����
	 * @return
	 */
	public boolean isUploadComplete() {
		return uploadComplete;
	}
	/**
	 * �����ϴ�״̬��trueΪ����ɣ�����δ���
	 * @param uploadComplete
	 */
	public void setUploadComplete(boolean uploadComplete) {
		this.uploadComplete = uploadComplete;
	}
}
