package kyle.leis.eo.operation.predictwaybill.tongtool;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;

import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;

public class TongToolTest {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		ApiTongToolDate tt = new ApiTongToolDate();
		String mainUrl = "112.74.27.18:8180";
		String toKen = "9c362a49d4d34019a622f52ee8dc1bcd";
		String operationType = "A";
		
		// String since= "2015-11-11 14:49";
		// String orderStatus ="";
		// String limit="";
		// String shippingMethodCode = "";
		// String data = tt.getDate(mainUrl, toKen, since, orderStatus, limit,
		// shippingMethodCode,"");
		// List<PredictwaybillColumns> listPwbc =
		// tt.getPredictwaybillColumns(data, "", "", "");
		// List<List<PredictcargoinfoColumns>> listlsInfo =
		// tt.getPredictcargoinfoColumns(data);
		// try {
		// List<SavedResultUtility> listSru
		// =tt.doImport(listPwbc,listlsInfo,"");
		// System.out.println(listSru.size());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		/*
		 * HKDGM平邮-P A1614 
		 * HKD代理-P D0101 
		 * HKD促销-V D0154
		 * "shippingMethodCode":"D0108","shippingMethodName":"CND大货"}}
		 * "shippingMethodCode":"D0176","shippingMethodName":"CND特惠-P"}}
		 * "shippingMethodCode":"D0126","shippingMethodName":"HKD促销-P"}}
		 * "shippingMethodCode":"D0153","shippingMethodName":"HKD代理-V"}}
		 * "shippingMethodCode":"A1615","shippingMethodName":"HKDGM挂号-P"}}
		 * "shippingMethodCode":"D0113","shippingMethodName":"CND小货"}}
		 * "shippingMethodCode":"D013320","shippingMethodName":"HKD代理-D"}}
		 * "shippingMethodCode":"D013322","shippingMethodName":"HKD代理-Z"}}
		 * "shippingMethodCode":"D013334","shippingMethodName":"CND特惠-D"}}
		 * "shippingMethodCode":"A163400","shippingMethodName":"HKDGM平邮-V1"}}
		 * "shippingMethodCode":"A163401","shippingMethodName":"HKDGM挂号-V1"}}
		 */
		// 通途添加产品
		/*
		String[] shippingMethodName = new String[] { "HKDGM平邮-P", "HKD代理-P",
				"HKD促销-V", "CND大货", "CND特惠-P", "HKD促销-P", "HKD代理-V",
				"HKDGM挂号-P", "CND小货", "HKD代理-D", "HKD代理-Z", "CND特惠-D",
				"HKDGM平邮-V1", "HKDGM挂号-V1" };
		String[] shippingMethodCode = new String[] { "A1614", "D0101", "D0154",
				"D0108", "D0176", "D0126", "D0153", "A1615", "D0113",
				"D013320", "D013322", "D013334", "A163400", "A163401" };
		*/
//		
		String[] shippingMethodName = new String[] {"华南小包挂号","华南小包平邮","华中小包挂号","华中小包平邮","匈牙利邮政挂号","匈牙利邮政平邮"};
		String[] shippingMethodCode = new String[] {"A163540",  "A163500","A163541","A163542","A163560","A163600"};
		
		for (int i = 0; i < shippingMethodName.length; i++) {
			System.out.println(i + 1 + ": " + shippingMethodCode[i] + "   "
					+ shippingMethodName[i]);
			 String data = tt.setLogistics(mainUrl, toKen, operationType,
			 shippingMethodName[i], shippingMethodCode[i]);
			 System.out.println(data);
		}
	}

	public static String getUTF8StringFromGBKString(String gbkStr) {
		try {
			return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new InternalError();
		}
	}

	public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
		int n = gbkStr.length();
		byte[] utfBytes = new byte[3 * n];
		int k = 0;
		for (int i = 0; i < n; i++) {
			int m = gbkStr.charAt(i);
			if (m < 128 && m >= 0) {
				utfBytes[k++] = (byte) m;
				continue;
			}
			utfBytes[k++] = (byte) (0xe0 | (m >> 12));
			utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
			utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
		}
		if (k < utfBytes.length) {
			byte[] tmp = new byte[k];
			System.arraycopy(utfBytes, 0, tmp, 0, k);
			return tmp;
		}
		return utfBytes;
	}
}
