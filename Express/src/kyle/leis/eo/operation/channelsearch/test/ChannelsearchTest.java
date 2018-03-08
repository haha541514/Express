package kyle.leis.eo.operation.channelsearch.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.eo.operation.channelsearch.dax.ChannelSearchDemand;
import kyle.leis.eo.operation.channelsearch.sv.ChannelsearchService;

public class ChannelsearchTest {
	private static ChannelsearchService s_objChannelsearchService = new ChannelsearchService();
	public static void main(String[] args) {
		try {
			System.out.println(searchChannels());
			// System.out.println(checkServerWeight());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public static String searchChannels() throws Exception {
		String str = "~`2016-09-02 17:30:03~`2016-09-02 17:30:03~`R~`~`~`D0154~`719~`~`A02~`APP~`~`AWPX~`~`~`~`~`~`~`1~`~`~`~@~#28326~`0.5~`0~`~`1~`AWPX~`APP~`7553124~`996~`A0201~`5000~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objChannelsearchService.searchChannels(objPD);
	}
	
	public static String checkServerWeight() throws Exception {
		return String.valueOf(ChannelSearchDemand.checkServerChargeweight("10"));
	}
	
}
