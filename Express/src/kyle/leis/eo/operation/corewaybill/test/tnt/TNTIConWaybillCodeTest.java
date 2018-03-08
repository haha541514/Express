package kyle.leis.eo.operation.corewaybill.test.tnt;

import java.util.List;

import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.corewaybill.dax.RequestTNTXML;
import kyle.leis.eo.operation.corewaybill.dax.RequestXML;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

import org.junit.Test;

import com.AWaybillcodeRequest;
import com.WaybillcodeParam;
import com.WaybillcodeResponse;
import com.tnt.TNTIConWaybillCode;

/**
 * ²úÆ·£ºHKTNTÅ·ÖÞ´ÙÏú
 * @author Administrator
 *
 */
public class TNTIConWaybillCodeTest {

	@Test
	public void testGetWaybillcode() throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCustomerewbcode("GD935264807WW");

		InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
		List listCargoInfo = objIAQR.getCargoInfoResults();
		ForinputallColumns objForinputallColumns = (ForinputallColumns) objIAQR
				.getHWBResults().get(0);

		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();

		RequestXML objRequestXML = new RequestTNTXML();
		WaybillcodeParam strRequestXML = objRequestXML.buildRequest(
				objForinputallColumns, listCargoInfo, null, objPUCollection);
		System.out.println(strRequestXML);
		System.out.println("chnCode -->" + objForinputallColumns.getChncode_Cwspchn());
		AWaybillcodeRequest objWayBillCode = new TNTIConWaybillCode();
		WaybillcodeResponse objWaybillcodeResponse = objWayBillCode
				.getWaybillcode(strRequestXML);
		List<String[]> list = objWaybillcodeResponse.getCondition();
		for (String[] strings : list) {
			for (String string : strings) {
				System.out.println(string);
			}
		}
		System.out.println("mainCwCode:" + objWaybillcodeResponse.getMainWaybillcode());
		System.out.println("lableUrl:" + objWaybillcodeResponse.getLableurl());
	}

}
