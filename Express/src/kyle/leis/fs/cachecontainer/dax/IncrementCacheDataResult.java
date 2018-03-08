package kyle.leis.fs.cachecontainer.dax;

import java.util.List;

public class IncrementCacheDataResult {
	private List m_listCacheData;
	private String m_strServerBatchNumber;
	
	public List getCacheData() {
		return m_listCacheData;
	}
	
	public void setCacheData(List listCacheData) {
		m_listCacheData = listCacheData;
	}
	
	public String getServerBatchNumber() {
		return m_strServerBatchNumber;
	}
	
	public void setServerBatchNumber(String strServerBatchNumber) {
		m_strServerBatchNumber = strServerBatchNumber;
	}
}
