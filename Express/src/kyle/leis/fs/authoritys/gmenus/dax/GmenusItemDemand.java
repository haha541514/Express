package kyle.leis.fs.authoritys.gmenus.dax;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.leis.es.company.companys.da.CorporationseqColumns;
import kyle.leis.es.company.companys.da.CorporationseqQuery;
import kyle.leis.fs.authoritys.gmenus.da.GmenusColumns;
import kyle.leis.fs.authoritys.gmenus.da.GmenusCondition;
import kyle.leis.fs.authoritys.gmenus.da.GmenusQuery;
import kyle.leis.fs.authoritys.gmenus.da.GmenusitemColumns;
import kyle.leis.fs.authoritys.gmenus.da.GmenusitemCondition;
import kyle.leis.fs.authoritys.gmenus.da.GmenusitemQuery;
import kyle.leis.fs.authoritys.gmenus.da.GmenusitemQueryEX;
import kyle.leis.fs.authoritys.gmenus.da.GmenusseqColumns;
import kyle.leis.fs.authoritys.gmenus.da.GmenusseqQuery;
import kyle.leis.fs.authoritys.gmenus.da.GmitemCondition;
import kyle.leis.fs.authoritys.gmenus.da.GmitemQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiGuiopenstyleDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiInfomationsystemkindDC;
import kyle.leis.hi.TfsGuimenu;
import kyle.leis.hi.TfsGuimenuitem;

public class GmenusItemDemand {
	
	/**
	 * ��ѯ������һ���˵�
	 * @param objGmenusitemCondition
	 * @return
	 * @throws Exception
	 */
	public static List query(GmenusitemCondition objGmenusitemCondition)throws Exception {
		GmenusitemQuery objGmenusitemQuery = new GmenusitemQuery();
		objGmenusitemQuery.setUseCachesign(true);
		objGmenusitemQuery.setCondition(objGmenusitemCondition);
		return objGmenusitemQuery.getResults();
	}
	
	/**
	 * ��ҳ����Ĳ�ѯ
	 * @param objGmenusitemCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryByparam(GmenusitemCondition objGmenusitemCondition) throws Exception{
		GmenusitemQueryEX objGmenusitemQueryEX = new GmenusitemQueryEX();
		objGmenusitemQueryEX.setCondition(objGmenusitemCondition);
		return objGmenusitemQueryEX.getResults();
	}
	
	public static List queryByGmicode(String gmCode) throws Exception{
		GmitemCondition objGmitemCondition=new GmitemCondition();
		objGmitemCondition.setGmcode(gmCode);
		GmitemQuery objGmitemQuery=new GmitemQuery();
		objGmitemQuery.setCondition(objGmitemCondition);
		return objGmitemQuery.getResults();
	}
	public static String getNewGmenusCode() throws Exception {
		GmenusseqQuery objGmenusseqQuery = new GmenusseqQuery();
		List objList = objGmenusseqQuery.getResults();
		if (objList == null || objList.size() < 1)
			throw (new Exception ("�޷����ݲ˵������к���������!"));
		return ((GmenusseqColumns)objList.get(0)).getGmenusseq();
	}
}
