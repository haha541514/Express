package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public interface OrderImportor {
	/**
	 * 导入订单
	 * @param startdate
	 * @param enddate
	 * @param accessToken
	 * @param pkCode 渠道
	 * @param coCode 客户编号
	 * @param opId 操作者编号
	 * @param cawtId 店铺编号
	 * @return
	 */
	OrderImportResult doImport(String startdate, String enddate, 
			String accessToken, String pkCode, String coCode, String opId, String cawtId);
}
