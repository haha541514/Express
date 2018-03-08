package kyle.leis.eo.operation.cargoinfo.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.hi.TopHousewaybill;
import kyle.leis.hi.TopHwbcargoinfo;

public class AddCargoInfoTransaction extends AbstractTransaction {
	private List<CargoinfoColumns> m_listCargoInfo;
	private TopHousewaybill m_objHWB;
	private String m_strCwcode;
	private String m_strGenericGoods;
	private boolean m_isNeedGenericGoods;
	
	public void setParam(List listCargoInfo, 
			String strGenericGoods,
			String strCwcode) throws Exception {
		m_strGenericGoods = strGenericGoods;
		m_isNeedGenericGoods = true;
		setParam(listCargoInfo, strCwcode);
	}	
	
	
	public void setParam(List listCargoInfo, 
			String strCwcode) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return;
		TopHousewaybill objHWB = null;
		setParam(listCargoInfo, strCwcode, objHWB);
	}
	
	public void setParam(List listCargoInfo, 
			String strCwcode, 
			TopHousewaybill objHWB) {
		
		m_objHWB = objHWB;
		m_strCwcode = strCwcode;
		
		if (listCargoInfo == null || listCargoInfo.size() < 1) return;		
		
		m_listCargoInfo = new ArrayList<CargoinfoColumns>();
		for (int i = 0; i < listCargoInfo.size(); i++) {
			if (listCargoInfo.get(i) instanceof CargoinfoColumns) {
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargoInfo.get(i);	
				objCIC.setCicomp_idcwcode(Long.parseLong(strCwcode));
				m_listCargoInfo.add(objCIC);	
			}
		}
	}
	
	public void transaction(Session objSession) throws Exception {
		// 先删除后新增
		if (StringUtility.isNull(m_strCwcode)) return;
		if (m_objHWB == null)
			m_objHWB = (TopHousewaybill)objSession.load(TopHousewaybill.class, Long.parseLong(m_strCwcode));
		// 商品大类不设置单独的字段目前只用在昇荣国际
		if (m_isNeedGenericGoods) {
			m_objHWB.setHwLabelprintremark(m_strGenericGoods);
			objSession.save(m_objHWB);
		}
		// 先查找是否存在海关编码
		/* 不再删除海关编码
		List listCD = CustomsDeclarationDemand.load(m_strCwcode);
		if (listCD != null && listCD.size() > 0)
			return;
		*/
		/*
		objSession.delete(" from TopCustomsdeclaration as cd where cd.topHwbcargoinfo.comp_id.cwCode = '" + 
				m_strCwcode + "'");
		*/
		objSession.delete(" from TopHwbcargoinfo as ci where ci.comp_id.cwCode = '" + 
				m_strCwcode + "'");
		if (m_listCargoInfo == null || m_listCargoInfo.size() < 1) return;
		for (int i = 0; i < m_listCargoInfo.size(); i++) {
			CargoinfoColumns objCIC = m_listCargoInfo.get(i);
			TopHwbcargoinfo objTopHwbcargoinfo = new TopHwbcargoinfo();
			//if (StringUtility.isNull(objCIC.getCicomp_idciid()))
			objCIC.setCicomp_idciid(i);
			CargoInfoDemand.setCargoInfoByColumns(objTopHwbcargoinfo, objCIC, m_objHWB);
			objSession.save(objTopHwbcargoinfo);
		}
	}
}
