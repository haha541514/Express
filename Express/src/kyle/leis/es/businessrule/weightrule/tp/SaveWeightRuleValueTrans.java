package kyle.leis.es.businessrule.weightrule.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.weightrule.da.CarryweightrulevalueColumns;
import kyle.leis.es.businessrule.weightrule.da.VolumeweightrulevalueColumns;
import kyle.leis.es.businessrule.weightrule.da.WeightrulevalueColumns;
import kyle.leis.hi.TbrCarryweightrulevalue;
import kyle.leis.hi.TbrCarryweightrulevaluePK;
import kyle.leis.hi.TbrVolumeweightrulevalue;
import kyle.leis.hi.TbrVolumeweightrulevaluePK;
import kyle.leis.hi.TbrWeightrule;
import kyle.leis.hi.TbrWeightrulevalue;
import kyle.leis.hi.TbrWeightrulevaluePK;

public class SaveWeightRuleValueTrans extends AbstractTransaction {
	private TbrWeightrule m_objTbrWeightrule;
	private List m_listWRVColumns;
	private List m_listVWRVColumns;
	private List m_listCWRVColumns;
	
	public void setParam(TbrWeightrule objTbrWeightrule,	
			List listWRVColumns,
			List listVWRVColumns,
			List listCWRVColumns) {
		m_objTbrWeightrule = objTbrWeightrule;
		m_listWRVColumns = listWRVColumns;
		m_listVWRVColumns = listVWRVColumns;
		m_listCWRVColumns = listCWRVColumns;
	}

	public void transaction(Session objSession) throws Exception {
		if (m_objTbrWeightrule == null || m_objTbrWeightrule.getBrId() == null)
			return;
		// 删除
		objSession.delete(" from TbrWeightrulevalue as wrv where wrv.comp_id.brId = " 
				+ m_objTbrWeightrule.getBrId());
		objSession.delete(" from TbrCarryweightrulevalue as cwrv where cwrv.comp_id.brId = " 
				+ m_objTbrWeightrule.getBrId());				
		objSession.delete(" from TbrVolumeweightrulevalue as vwrv where vwrv.comp_id.brId = " 
				+ m_objTbrWeightrule.getBrId());
		// 新增重量规则值
		if (m_listWRVColumns != null && m_listWRVColumns.size() > 0)
			for (int i = 0; i < m_listWRVColumns.size(); i++) {
				WeightrulevalueColumns objWRVColumns = (WeightrulevalueColumns)m_listWRVColumns.get(i);
				TbrWeightrulevalue objTbrWeightrulevalue = new TbrWeightrulevalue();
				// 设置主键
				TbrWeightrulevaluePK objWRVPK = new TbrWeightrulevaluePK();
				objWRVPK.setBrId(m_objTbrWeightrule.getBrId());
				objWRVPK.setBrvId(i);
				objTbrWeightrulevalue.setComp_id(objWRVPK);
				objTbrWeightrulevalue.setTbrWeightrule(m_objTbrWeightrule);
				
				if (!StringUtility.isNull(objWRVColumns.getWrvznid()))
					objTbrWeightrulevalue.setZnId(Long.parseLong(objWRVColumns.getWrvznid()));
				if (!StringUtility.isNull(objWRVColumns.getWrvznvid()))
					objTbrWeightrulevalue.setZnvId(Integer.parseInt(objWRVColumns.getWrvznvid()));
				objTbrWeightrulevalue.setZnvCondition(objWRVColumns.getWrvznvcondition());
				objTbrWeightrulevalue.setZnvExpression(objWRVColumns.getWrvznvexpression());

				objSession.save(objTbrWeightrulevalue);
			}
		// 新增体积重系数
		if (m_listVWRVColumns != null && m_listVWRVColumns.size() > 0)
			for (int i = 0; i < m_listVWRVColumns.size(); i++) {
				VolumeweightrulevalueColumns objVWRVColumns = (VolumeweightrulevalueColumns)m_listVWRVColumns.get(i);
				TbrVolumeweightrulevalue objTVWRValue = new TbrVolumeweightrulevalue();
				// 设置主键
				TbrVolumeweightrulevaluePK objVWRVPK = new TbrVolumeweightrulevaluePK();
				objVWRVPK.setBrId(m_objTbrWeightrule.getBrId());
				objVWRVPK.setVwrvId(i);
				objTVWRValue.setComp_id(objVWRVPK);
				objTVWRValue.setTbrWeightrule(m_objTbrWeightrule);
				
				if (!StringUtility.isNull(objVWRVColumns.getVwrvznid()))
					objTVWRValue.setZnId(Long.parseLong(objVWRVColumns.getVwrvznid()));
				if (!StringUtility.isNull(objVWRVColumns.getVwrvznvid()))
					objTVWRValue.setZnvId(Integer.parseInt(objVWRVColumns.getVwrvznvid()));	
				objTVWRValue.setVwrvValue(Integer.parseInt(objVWRVColumns.getVwrvvwrvvalue()));
				
				objSession.save(objTVWRValue);
			}
		// 新增进位重量
		if (m_listCWRVColumns != null && m_listCWRVColumns.size() > 0)
			for (int i = 0; i < m_listCWRVColumns.size(); i++) {
				CarryweightrulevalueColumns objCWRVColumns = (CarryweightrulevalueColumns)m_listCWRVColumns.get(i);
				TbrCarryweightrulevalue objTCWRValue = new TbrCarryweightrulevalue();
				// 设置主键
				TbrCarryweightrulevaluePK objCWRVPK = new TbrCarryweightrulevaluePK();
				objCWRVPK.setBrId(m_objTbrWeightrule.getBrId());
				objCWRVPK.setCwrvId(i);
				objTCWRValue.setComp_id(objCWRVPK);
				objTCWRValue.setTbrWeightrule(m_objTbrWeightrule);
				
				if (!StringUtility.isNull(objCWRVColumns.getCwrvcwrvvalue()))
					objTCWRValue.setCwrvValue(new BigDecimal(objCWRVColumns.getCwrvcwrvvalue()));
				if (!StringUtility.isNull(objCWRVColumns.getCwrvcwrvweightgrade()))
					objTCWRValue.setCwrvWeightgrade(new BigDecimal(objCWRVColumns.getCwrvcwrvweightgrade()));
				
				objSession.save(objTCWRValue);
			}
	}
}
