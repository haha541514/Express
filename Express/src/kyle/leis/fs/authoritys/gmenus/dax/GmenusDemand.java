package kyle.leis.fs.authoritys.gmenus.dax;

import java.util.List;

import kyle.leis.fs.authoritys.gmenus.da.GmenusColumns;
import kyle.leis.fs.authoritys.gmenus.da.GmenusCondition;
import kyle.leis.fs.authoritys.gmenus.da.GmenusQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiGuiopenstyleDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiInfomationsystemkindDC;
import kyle.leis.hi.TfsGuimenu;
import net.sf.hibernate.Session;

public class GmenusDemand {

	public static List query(GmenusCondition objGmenusCondition)
			throws Exception {
		GmenusQuery objGmenusQuery = new GmenusQuery();
		objGmenusQuery.setCondition(objGmenusCondition);
		return objGmenusQuery.getResults();
	}

	public static void setGmenusColumns(TfsGuimenu objTfsGmenu,
			GmenusColumns objGmenusColumns, Session objSession)
			throws Exception {
		objTfsGmenu.setGmGroupcode(objGmenusColumns.getGmgmgroupcode());
		objTfsGmenu.setGmIcon(objGmenusColumns.getGmgmicon());
		objTfsGmenu.setGmLevel(Integer
				.parseInt(objGmenusColumns.getGmgmlevel()));
		objTfsGmenu.setGmLink(objGmenusColumns.getGmgmlink());
		objTfsGmenu.setGmMaxusecount(Integer.parseInt(objGmenusColumns
				.getGmgmmaxusecount()));
		objTfsGmenu.setGmName(objGmenusColumns.getGmgmname());
		objTfsGmenu.setGmParameter(objGmenusColumns.getGmgmparameter());
		objTfsGmenu.setGmPinyinname(objGmenusColumns.getGmgmpinyinname());
		objTfsGmenu.setGmShortcutkey(objGmenusColumns.getGmgmshortcutkey());
		objTfsGmenu.setGmShowtoolbar(objGmenusColumns.getGmgmshowtoolbar());
		objTfsGmenu.setGmStructurecode(objGmenusColumns.getGmgmstructurecode());

		objTfsGmenu.setTdiGuiopenstyle(TdiGuiopenstyleDC
				.loadByKey(objGmenusColumns.getGosgoscode()));
		objTfsGmenu.setTdiInfomationsystemkind(TdiInfomationsystemkindDC
				.loadByKey(objGmenusColumns.getIskiskcode()));
	}
}
