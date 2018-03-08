package kyle.leis.fs.dictionary.productkind.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCargokindDC;
import kyle.leis.fs.dictionary.productkind.da.PkcargokindColumns;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindSequence;
import kyle.leis.hi.TdiPkcargokind;
import kyle.leis.hi.TdiPkcargokindPK;
import kyle.leis.hi.TdiProductkind;
import net.sf.hibernate.Session;

public class AddProductkindTransaction extends AbstractTransaction {
	private ProductkindColumns m_objPKColumns;
	private List m_listPkcargokind;
	private String m_newpkCode;
	
	public String getNewpkCode()
	{
		return m_newpkCode;
	}
	
	public void setPraam(ProductkindColumns objPKColumns,
			List listPkcargokind)
	{
		this.m_objPKColumns = objPKColumns;
		m_listPkcargokind = listPkcargokind;
	}
	
	public void transaction(Session objSession) throws Exception {
		TdiProductkind objTdiProductkind = null;
		//if(m_objPKColumns == null || StringUtility.isNull(m_objPKColumns.getPkpkcode())) return;
		if(!StringUtility.isNull(m_objPKColumns.getPkpkcode())) {
			//修改
			objTdiProductkind = (TdiProductkind)objSession.load(TdiProductkind.class, m_objPKColumns.getPkpkcode());
			// 先删除
			objSession.delete(" from TdiPkcargokind as pkck where pkck.comp_id.pkCode = '" + 
					objTdiProductkind.getPkCode() + "'");
		}
		else
		{
			//新增
			objTdiProductkind = new TdiProductkind();
			ProductkindSequence pks = new ProductkindSequence();
			String strPkcode = pks.getNewSequencecode();
			if (!StringUtility.isNull(m_objPKColumns.getPkstructurecode())) {
				 if (m_objPKColumns.getPkstructurecode().length() < 3)
					 strPkcode = m_objPKColumns.getPkstructurecode() + strPkcode;
				 else if (m_objPKColumns.getPkstructurecode().length() >= 3)
					 strPkcode = m_objPKColumns.getPkstructurecode().substring(0, 3) + strPkcode;
			}
			objTdiProductkind.setPkCode(strPkcode);
		}
		ProductkindDemand.setProductkindByColumns(objTdiProductkind, m_objPKColumns);
		objSession.save(objTdiProductkind);
		// 产品对应的货物种类
		if (m_listPkcargokind != null && m_listPkcargokind.size() > 0) {
			for (int i = 0; i < m_listPkcargokind.size(); i++) {
				PkcargokindColumns pkck = (PkcargokindColumns)m_listPkcargokind.get(i);
				TdiPkcargokind objTPKCK = new TdiPkcargokind();
				
				TdiPkcargokindPK objTPKCKPK = new TdiPkcargokindPK();
				objTPKCKPK.setCgkCode(pkck.getPkckcomp_idcgkcode());
				objTPKCKPK.setPkCode(objTdiProductkind.getPkCode());
				objTPKCK.setComp_id(objTPKCKPK);
				objTPKCK.setTdiProductkind(objTdiProductkind);
				objTPKCK.setTdiCargokind(TdiCargokindDC.loadByKey(pkck.getPkckcomp_idcgkcode()));
				
				objSession.save(objTPKCK);
			}
		}
		m_newpkCode = objTdiProductkind.getPkCode();
	}

}
