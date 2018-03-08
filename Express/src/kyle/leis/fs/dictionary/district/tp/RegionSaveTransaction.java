package kyle.leis.fs.dictionary.district.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.RegionColumns;
import kyle.leis.fs.dictionary.district.da.RegionQuery;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.district.dax.RegionSeq;
import kyle.leis.hi.TdiCity;
import kyle.leis.hi.TdiRegion;

public class RegionSaveTransaction extends AbstractTransaction {
	private String newRgcode;
	private RegionColumns objTdiregionColumns;

	
	public String getNewRgcode() {
		return newRgcode;
	}

	public void setObjTdiregionColumns(RegionColumns objTdiregionColumns) {
		this.objTdiregionColumns = objTdiregionColumns;
	}

	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		if (objTdiregionColumns == null)
			return;
		TdiRegion tr;
		// 传入条件中没有主键
		if (StringUtility.isNull(objTdiregionColumns.getTdrrgcode())) {
			tr = new TdiRegion();
			RegionSeq seq = new RegionSeq();
			String st = seq.getNewSequencecode();
			objTdiregionColumns.setTdrrgcode(st);
		} else {
			tr = (TdiRegion) objSession.load(TdiRegion.class,
					objTdiregionColumns.getTdrrgcode());
		}
		DistrictDemand
				.setRegionColumns(tr, objTdiregionColumns, objSession);
		objSession.save(tr);
		newRgcode = tr.getRgCode();
	}

}
