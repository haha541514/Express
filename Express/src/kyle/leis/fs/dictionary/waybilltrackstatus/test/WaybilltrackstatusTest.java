package kyle.leis.fs.dictionary.waybilltrackstatus.test;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.cachecontainer.da.WaybilltrackstatusColumns;
import kyle.leis.fs.dictionary.waybilltrackstatus.sv.WaybilltrackstatusService;

public class WaybilltrackstatusTest {
	private static WaybilltrackstatusService service = new WaybilltrackstatusService();
	public static void main(String[] args){
		query();
		//edit();
		//del();
	}
	//��ѯ
	public static void query(){
		String strSource = "SHZZJ~`~@~#";
		Decoder objPD = new Decoder(strSource);
		try {
			System.out.println(service.queryTrackstatus(objPD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��ӡ��޸�
	public static void edit(){
		Encoder encoder = new Encoder();
		WaybilltrackstatusColumns objGC = new WaybilltrackstatusColumns();
		objGC.setWbtswbtscode("SHZZJ");
		objGC.setWbtswbtsname("�����ݿ�TEST");
		objGC.setWbtswbtsename("Shipment on hold");
		objGC.setWbtswbtsabnormalsign("Y");
		objGC.setWbtpwbtpcode("DL");
		encoder.addParameter(objGC);
		Decoder objPD = new Decoder(encoder.toString());
		try {
			System.out.println(service.editTrackstatus(objPD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//ɾ��
	public static void del(){
		String strSource = "SHZZJ~`~@~#";
		Decoder objPD = new Decoder(strSource);
		try {
			System.out.println(service.delTrackstatus(objPD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
