package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;

import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.es.company.channel.da.ChanneladdressColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.fs.waybillcode.bl.IComplexPrefix;

public class DGMComplexPrefix implements IComplexPrefix {
	private ForinputallColumns m_objFIAColumns;
	
	public DGMComplexPrefix(ForinputallColumns objFIAColumns) {
		m_objFIAColumns = objFIAColumns;
	}

	public String buildPrefix() throws Exception {
		StringBuffer sb = new StringBuffer();
		//sb.append("420");
		//sb.append(m_objFIAColumns.getHwconsigneepostcode());
		sb.append("92");
		
		// 16盎司以上为83，以下为82
		String strChargeweight = m_objFIAColumns.getCwserverchargeweight();
		BigDecimal objStandardRate = new BigDecimal("35.2739619");
		BigDecimal objChargeweight = objStandardRate.multiply(new BigDecimal(strChargeweight)).divide(new BigDecimal("1"), 2, 4);
		if (objChargeweight.compareTo(new BigDecimal("16")) > 0) {
			sb.append("612");
		}
		else {
			sb.append("748");
		}		
		ChanneladdressColumns objCAColumns = ChannelDemand.loadChanneladdress(m_objFIAColumns.getChncode_Cwspchn());
		sb.append(objCAColumns.getChnachnamid());
		return sb.toString();
	}

}
