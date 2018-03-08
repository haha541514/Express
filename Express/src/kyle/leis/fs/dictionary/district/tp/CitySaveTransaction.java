package kyle.leis.fs.dictionary.district.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.CityColumns;
import kyle.leis.fs.dictionary.district.dax.CitySeq;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TdiCity;
import kyle.leis.hi.TdiState;
public class CitySaveTransaction extends AbstractTransaction{
       private CityColumns m_objTdicityColumns;
       private String newCtCode;
       

	public void setM_objTdicityColumns(CityColumns mObjTdicityColumns) {
		m_objTdicityColumns = mObjTdicityColumns;
	}

	public String getnewCtCode() {
		return newCtCode;
	}
	/***
	 * 20160816,14:45
	 * update
	 * by wukaiquan
	 *	  if(m_objTdicityColumns.getTdcctcode()==null || StringUtility.isNull(m_objTdicityColumns.getTdcctcode())
	 *  add || StringUtility.isNull(m_objTdicityColumns.getTdcctcode())
	 * **/
	public void transaction(Session objSession) throws Exception {
	  if(m_objTdicityColumns==null)
		  return;
	  TdiCity objTdicity;
	  
	  if(m_objTdicityColumns.getCictcode()==null || StringUtility.isNull(m_objTdicityColumns.getCictcode())){
		CitySeq cityseq = new CitySeq();
		objTdicity = new TdiCity();
		String st = cityseq.getNewSequencecode();
		this.m_objTdicityColumns.setCictcode(st);
	  }else{//修改
		  objTdicity = (TdiCity) objSession.load(TdiCity.class, m_objTdicityColumns.getCictcode());  
	  }
	  //设置属性	  a
	  DistrictDemand.setCityColumns(objTdicity,m_objTdicityColumns,objSession);
	  //保存数据
	  objSession.save(objTdicity);
	  newCtCode = objTdicity.getCtCode();
	}
}
	


