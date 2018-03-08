package kyle.leis.fs.dictionary.productkind.bl;

import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;
import kyle.leis.fs.dictionary.productkind.tp.AddProductkindTransaction;
import kyle.leis.fs.dictionary.productkind.tp.ModifySignTransaction;
import kyle.leis.fs.dictionary.productkind.tp.ModifyStatusTransaction;

public class Productkind {
	public ProductkindColumns addProductkind(ProductkindColumns objPKColumns,
			List listPkcargokind) throws Exception
	{
		AddProductkindTransaction objAPKTransaction = new AddProductkindTransaction();
		objAPKTransaction.setPraam(objPKColumns, listPkcargokind);
		objAPKTransaction.execute();
		
		// Ë¢ÐÂ»º³å
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		
		return ProductkindDemand.queryBypkCode(objAPKTransaction.getNewpkCode());
	}
	
	public void modifyStatus(String strpkCode,String strssCode) throws Exception
	{
		ModifyStatusTransaction objMSTransaction = new ModifyStatusTransaction();
		objMSTransaction.setParame(strpkCode, strssCode);
		objMSTransaction.execute();
	}
	
	public void modifySign(String strpkCode,String strpkSign) throws Exception
	{
		ModifySignTransaction objMSTransaction = new ModifySignTransaction();
		objMSTransaction.setParame(strpkCode, strpkSign);
		objMSTransaction.execute();
	}
}
