package kyle.leis.fs.waybillcode.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.query.PageConfig;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.waybillcode.bl.AWaybillcode;
import kyle.leis.fs.waybillcode.da.WaybillcodevalueColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodevalueCondition;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.fs.waybillcode.dax.WaybillcodeDemand;
import kyle.leis.hi.TdiActionkind;
import kyle.leis.hi.TdiWaybillcodestatus;
import kyle.leis.hi.TfsWaybillcodeaction;
import kyle.leis.hi.TfsWaybillcodevalue;
import kyle.leis.hi.TfsWaybillcodevaluePK;

public class UseInValueTransaction extends AbstractTransaction {
	private String m_strOperId;
	private String m_strBckcode;
	private int m_iNumbers;
	private ArrayList<String> m_alLabelcode;
	private AWaybillcode m_objWaybillcode;
	
	public ArrayList<String> getLabelcodeCollection() {
		return m_alLabelcode;
	}	
	
	public void setParam(String strBckcode,
			int iNumbers ,
			String strOperId,
			AWaybillcode objWaybillcode) {
		m_strBckcode = strBckcode;
		m_iNumbers = iNumbers;
		m_strOperId = strOperId;
		m_objWaybillcode = objWaybillcode;
	}


	public void transaction(Session objSession) throws Exception {
		WaybillcodevalueCondition objBCVCondition = new WaybillcodevalueCondition();
		PageConfig objPageConfig = new PageConfig();
		// 设置获取数量
		objPageConfig.setCurrentPageNo(1);
		objPageConfig.setMaxPageResultCount(m_iNumbers);
		objBCVCondition.setPageConfig(objPageConfig);
		// 获取注册的单号
		objBCVCondition.setBckcode(m_strBckcode);
		objBCVCondition.setBcscscode(IWaybillcodeBasicData.WBC_STATUS_REGISTER);
		List listBCValueColumns = WaybillcodeDemand.queryBCValue(objBCVCondition);
		
		if (listBCValueColumns == null || listBCValueColumns.size() < 1) {
			UseWaybillcode objUseWaybillcode = new UseWaybillcode();
			objUseWaybillcode.transaction(objSession);
			return;
		}
		m_alLabelcode = new ArrayList<String>();
		for (int i = 0; i < listBCValueColumns.size(); i++) {
			// 装载记录
			WaybillcodevalueColumns objBCValueColumns = (WaybillcodevalueColumns)listBCValueColumns.get(i);
			TfsWaybillcodevaluePK objWBVPK = new TfsWaybillcodevaluePK();
			objWBVPK.setBcId(Long.parseLong(objBCValueColumns.getBcvcomp_idbcid()));
			objWBVPK.setBcvId(Long.parseLong(objBCValueColumns.getBcvcomp_idbcvid()));
			TfsWaybillcodevalue objWBValue = (TfsWaybillcodevalue)objSession.load(TfsWaybillcodevalue.class, 
					objWBVPK);
			// 返回的单据号
			m_alLabelcode.add(m_objWaybillcode.buildLabelcode(objBCValueColumns.getBcvbcvlabelcode(),
					objBCValueColumns.getBcvbcvprefix(),
					objBCValueColumns.getBcvbcvsuffix()));
			// 更新状态
			TdiWaybillcodestatus objWBCStatus = (TdiWaybillcodestatus)objSession.load(TdiWaybillcodestatus.class, 
					IWaybillcodeBasicData.WBC_STATUS_USED);
			objWBValue.setTdiWaybillcodestatus(objWBCStatus);
			// 插入日志记录
			TfsWaybillcodeaction objTWBCAction = new TfsWaybillcodeaction();
			objTWBCAction.setBcaRemark("使用单据");
			objTWBCAction.setOpIdCreator(Long.parseLong(m_strOperId));
			TdiActionkind objTdiActionkind = (TdiActionkind)objSession.load(TdiActionkind.class, "AD");
			objTWBCAction.setTdiActionkind(objTdiActionkind);
			objTWBCAction.setTfsWaybillcodevalue(objWBValue);
		}
	}	
	
	private class UseWaybillcode extends AbstractTransaction {
		
		public void transaction(Session objSession) throws Exception {
			StringBuffer sbUpdateSQL = new StringBuffer();
			
			sbUpdateSQL.append("update T_FS_WAYBILLCODE wc set wc.cs_code = '");
			sbUpdateSQL.append(IWaybillcodeBasicData.WBC_STATUS_USED);
			sbUpdateSQL.append("', wc.OP_ID_Modifier = ");
			sbUpdateSQL.append(m_strOperId);
			sbUpdateSQL.append(", wc.BC_ModifyDate = sysdate ");
			
			sbUpdateSQL.append("where wc.cs_code = '");
			sbUpdateSQL.append(IWaybillcodeBasicData.WBC_STATUS_REGISTER);
			sbUpdateSQL.append("' and wc.bck_code = '");
			sbUpdateSQL.append(m_strBckcode);
			sbUpdateSQL.append("'");
			
			super.execute(objSession, sbUpdateSQL.toString());
		}
		
	}
}
