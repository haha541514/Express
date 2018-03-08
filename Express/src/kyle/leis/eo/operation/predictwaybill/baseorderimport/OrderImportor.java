package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public interface OrderImportor {
	/**
	 * ���붩��
	 * @param startdate
	 * @param enddate
	 * @param accessToken
	 * @param pkCode ����
	 * @param coCode �ͻ����
	 * @param opId �����߱��
	 * @param cawtId ���̱��
	 * @return
	 */
	OrderImportResult doImport(String startdate, String enddate, 
			String accessToken, String pkCode, String coCode, String opId, String cawtId);
}
