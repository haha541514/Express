package kyle.leis.eo.operation.channelsearch.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.operation.channelsearch.bl.ChannelSearch;
import kyle.leis.eo.operation.channelsearch.dax.ChannelSearchResult;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;

public class ChannelsearchService extends AService {
	/**
	 * ÇþµÀÑ¡Ôñ
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String searchChannels(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		FreightpriceCondition objFPCondition = (FreightpriceCondition)objPD.getParameter(0, 
				FreightpriceCondition.class);
		FeeCalculateParameter objCalcFeeParameter = (FeeCalculateParameter)objPD.getParameter(1, 
				FeeCalculateParameter.class);
		
		ChannelSearch objChannelSearch = new ChannelSearch(); 
		List<ChannelSearchResult> listChannels = objChannelSearch.searchChannels(objFPCondition, 
				objCalcFeeParameter);
		
		if (listChannels == null || listChannels.size() < 1)
			return "";
		
		Encoder objEncode = new Encoder();
		String[][] aastr = new String[listChannels.size()][];
		for (int i = 0; i < listChannels.size(); i++) {
			ChannelSearchResult objChannelResult = listChannels.get(i);
			String[]astr = objChannelResult.toStringArray();
			aastr[i] = astr;
		}
		objEncode.addParameter(aastr);
		return objEncode.toString();
	}
}
