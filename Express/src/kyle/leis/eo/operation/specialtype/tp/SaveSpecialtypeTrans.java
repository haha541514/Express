package kyle.leis.eo.operation.specialtype.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpressspecialtypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiExpressspecialtype;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopWaybillspecialtype;
import kyle.leis.hi.TopWaybillspecialtypePK;

public class SaveSpecialtypeTrans extends AbstractTransaction {
	private List m_listSpecialtypes;
	private String m_strOperId;
	private String m_strCwcode;
	private boolean m_isDelOriginSpecialtype;
	
	public void setParam(String strCwcode, 
			String strEstcode, 
			String strOperId, 
			String strRemark) {
		List<SpecialtypeColumns> listSpecialtypes = new ArrayList<SpecialtypeColumns>();
		SpecialtypeColumns objSpecialtypeColumns = new SpecialtypeColumns();
		objSpecialtypeColumns.setWbstcomp_idcwcode(Long.parseLong(strCwcode));
		objSpecialtypeColumns.setWbstcomp_idestcode(strEstcode);
		objSpecialtypeColumns.setWbstwbstremark(strRemark);
		
		listSpecialtypes.add(objSpecialtypeColumns);
		setParam(listSpecialtypes,
				strCwcode,
				strOperId, 
				false);
	}
	
	public void setParam(List listSpecialtypes,
			String strCwcode,
			String strOperId,
			boolean isDelOriginSpecialtype) {
		m_listSpecialtypes = listSpecialtypes;
		m_strOperId = strOperId;
		m_strCwcode = strCwcode;
		m_isDelOriginSpecialtype = isDelOriginSpecialtype;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listSpecialtypes == null || m_listSpecialtypes.size() < 1)
			return;
		if (StringUtility.isNull(m_strCwcode))
			return;
		// 删除
		if (m_isDelOriginSpecialtype) {
			objSession.delete(" from TopWaybillspecialtype wbst where wbst.comp_id.cwCode = " + m_strCwcode);
		}
		// 新增
		TopCorewaybill objTopCorewaybill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
				Long.parseLong(m_strCwcode));
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		for (int i = 0; i < m_listSpecialtypes.size(); i++) {
			TopWaybillspecialtype objTWBSType = new TopWaybillspecialtype();
			SpecialtypeColumns objSpecialtypeColumns = (SpecialtypeColumns)m_listSpecialtypes.get(i);
			// 设置属性
			TdiExpressspecialtype objTESTtype = TdiExpressspecialtypeDC.loadByKey(objSpecialtypeColumns.getWbstcomp_idestcode());
			objTWBSType.setTdiExpressspecialtype(objTESTtype);
			objTWBSType.setTdiOperator(objTdiOperator);
			objTWBSType.setTopCorewaybill(objTopCorewaybill);
			objTWBSType.setWbstCreatedate(DateFormatUtility.getSysdate());
			objTWBSType.setWbstRemark(objSpecialtypeColumns.getWbstwbstremark());
			// 主键
			TopWaybillspecialtypePK objTWBSTPK = new TopWaybillspecialtypePK();
			objTWBSTPK.setCwCode(Long.parseLong(m_strCwcode));
			objTWBSTPK.setEstCode(objSpecialtypeColumns.getWbstcomp_idestcode());
			objTWBSType.setComp_id(objTWBSTPK);
			// 保存
			objSession.save(objTWBSType);
		}
	}
}
