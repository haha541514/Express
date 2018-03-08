package kyle.leis.es.company.channel.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiOperator;

public class SaveChannelTransaction extends AbstractTransaction {
	private ChannelColumns m_objChannelColumns;
	private String m_strOperId;
	private String m_strSavedChncode;
	
	public void setParam(ChannelColumns objChannelColumns, 
			String strOperId) {
		m_objChannelColumns = objChannelColumns;
		m_strOperId = strOperId;
	}
	
	public String getSavedChncode() {
		return m_strSavedChncode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objChannelColumns == null) return;
		// 创建人或修改人
		String strChncode = m_objChannelColumns.getChnchncode();
		TchnChannel objTchnChannel = null;
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		// 新增或修改
		if (StringUtility.isNull(strChncode)) {
			objTchnChannel = new TchnChannel();
			objTchnChannel.setTdiOperatorByChnOpIdCreate(objTdiOperator);
			objTchnChannel.setChnCreatedate(DateFormatUtility.getSysdate());
			objTchnChannel.setChnCode(ChannelDemand.getNewChannelcode());
		} else {
			objTchnChannel = (TchnChannel)objSession.load(TchnChannel.class, strChncode);
		}
		objTchnChannel.setTdiOperatorByChnOpIdModify(objTdiOperator);
		objTchnChannel.setChnModifydate(DateFormatUtility.getSysdate());
		// 设置基本数据
		ChannelDemand.setChannelByColumns(objTchnChannel, 
				m_objChannelColumns, 
				objSession);
		objSession.save(objTchnChannel);
		m_strSavedChncode = objTchnChannel.getChnCode();
	}

}
