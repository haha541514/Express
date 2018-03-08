package kyle.leis.fs.dictionary.district.tp;

import java.util.List;



import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.district.da.DhldistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdhldistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdistrictColumns;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TdiDhldistrict;

public class SaveDhlDistrictTransction extends AbstractTransaction{
	private String result;
	private List m_dhlList;
	
	
	public void setDhlDistrictlist(List m_dhlDistrictList){
		this.m_dhlList  = m_dhlDistrictList;
		
	}

	public void transaction(Session objSession) throws Exception {

		if (m_dhlList == null || m_dhlList.size() < 1)
			return;
		//批量导入
	
		DhlDistrictSeqQuery seqQeury = new DhlDistrictSeqQuery();
		for (int i = 0; i < m_dhlList.size(); i++) {
			TdiDhldistrict objDhldistrict = new TdiDhldistrict();
			DicdhldistrictColumns columns = (DicdhldistrictColumns) m_dhlList.get(i);
			objDhldistrict.setDdCode(seqQeury.getNewSequencecode());
			DistrictDemand.setDhlDistrictColumns(objDhldistrict,columns,objSession);
			objSession.save(objDhldistrict);	
		}
		
		
	}
	
	
	
}
