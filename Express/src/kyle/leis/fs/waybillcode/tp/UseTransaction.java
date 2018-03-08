package kyle.leis.fs.waybillcode.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.waybillcode.bl.AWaybillcode;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodeCondition;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.fs.waybillcode.dax.WaybillcodeDemand;
import kyle.leis.hi.TdiWaybillcodestatus;
import kyle.leis.hi.TfsWaybillcode;

public class UseTransaction extends AbstractTransaction {
	private String m_strBrkcode;
	private int m_iNumbers;
	private AWaybillcode m_objWaybillcode;
	
	private ArrayList<String> m_alLabelcode = new ArrayList<String>();
	
	public void setParam(String strBckcode, 
			int iNumbers,
			AWaybillcode objWaybillcode) {
		m_strBrkcode = strBckcode;
		m_iNumbers = iNumbers;
		m_objWaybillcode = objWaybillcode;
	}
	
	public ArrayList<String> getLabelcodeCollection() {
		return m_alLabelcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strBrkcode)) return;
		// 获得Labelcode
		WaybillcodeCondition objWBCCondition = new WaybillcodeCondition();
		objWBCCondition.setBckcode(m_strBrkcode);
		objWBCCondition.setCscode(IWaybillcodeBasicData.WBC_STATUS_REGISTER);
		List objList = WaybillcodeDemand.query(objWBCCondition);		
		
		int iHasGotNumbers = 0;
		ArrayList<String> alHasUsedBcid = new ArrayList<String>();
		String strLastBcid = "";
		String strLastLabelcode = "";
		boolean isCanGotLabelcode = false;
		BigDecimal objCurrentcode = null;
		
		for (int i = 0; i < objList.size(); i++) {
			WaybillcodeColumns objWBCColumns = (WaybillcodeColumns)objList.get(i);
			BigDecimal objEndcode = new BigDecimal(objWBCColumns.getBcbcendcode());
			BigDecimal objStartcode = new BigDecimal(objWBCColumns.getBcbcstartcode());
			strLastBcid = objWBCColumns.getBcbcid();
			String strCurrentcode = objWBCColumns.getBcbccurrentlabelcode();
			if (StringUtility.isNull(strCurrentcode)) {
				strCurrentcode = objWBCColumns.getBcbcstartcode();
				objCurrentcode = new BigDecimal(strCurrentcode);
			} else {
				objCurrentcode = new BigDecimal(strCurrentcode).add(new BigDecimal("1"));
			}
			while (objCurrentcode.compareTo(objStartcode) >= 0 &&
					objCurrentcode.compareTo(objEndcode) <= 0 && 
					iHasGotNumbers < m_iNumbers) {
				iHasGotNumbers++;
				strLastLabelcode = objCurrentcode.toString();
				String strLabelcode = m_objWaybillcode.buildLabelcode(strLastLabelcode,
						objWBCColumns.getBcbcprefix(),
						objWBCColumns.getBcbcsuffix());
				m_alLabelcode.add(strLabelcode);
				objCurrentcode = objCurrentcode.add(new BigDecimal("1"));
			}
			if (objEndcode.compareTo(objCurrentcode) <= 0)
				alHasUsedBcid.add(objWBCColumns.getBcbcid());
			if (objEndcode.compareTo(objCurrentcode.add(new BigDecimal("-1"))) >= 0 &&
					iHasGotNumbers == m_iNumbers) {
				isCanGotLabelcode = true;
				break;
			}
		}
		if (!isCanGotLabelcode) {
			m_alLabelcode.clear();
			return;
		}
		// 更新已经使用完成的单号
		for (int i = 0; i < alHasUsedBcid.size(); i++) {
			TfsWaybillcode objTfsWaybillcode = (TfsWaybillcode)objSession.load(TfsWaybillcode.class, 
					Long.parseLong(alHasUsedBcid.get(i)));
			TdiWaybillcodestatus objTWBCStatus = (TdiWaybillcodestatus)objSession.load(TdiWaybillcodestatus.class, 
					IWaybillcodeBasicData.WBC_STATUS_USED);
			objTfsWaybillcode.setTdiWaybillcodestatus(objTWBCStatus);
			objTfsWaybillcode.setBcCurrentlabelcode(objTfsWaybillcode.getBcEndcode());
			objSession.save(objTfsWaybillcode);
		}
		// 更新未使用完成的单号
		if (!StringUtility.isNull(strLastBcid) && 
				!StringUtility.isNull(strLastLabelcode)) {
			TfsWaybillcode objTfsWaybillcode = (TfsWaybillcode)objSession.load(TfsWaybillcode.class, 
					Long.parseLong(strLastBcid));
			objTfsWaybillcode.setBcCurrentlabelcode(strLastLabelcode);
			objSession.save(objTfsWaybillcode);
		}
	}
}
