package kyle.leis.eo.operation.cwbimportlog.bl;

import java.util.Date;

import kyle.leis.eo.operation.cwbimportlog.da.CwbimportdataColumns;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportlogColumns;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowColumns;
import kyle.leis.eo.operation.cwbimportlog.dax.CwbimportlogDemand;
import kyle.leis.eo.operation.cwbimportlog.tp.AddcwbimportdataTrans;
import kyle.leis.eo.operation.cwbimportlog.tp.AddcwbimportlogTrans;
import kyle.leis.eo.operation.cwbimportlog.tp.AddcwbimportrowTrans;
import kyle.leis.eo.operation.cwbimportlog.tp.ModifycwbimportlogTrans;


public class Cwbimportlog {
	//�����ϴ������¼
	public CwbimportlogColumns saveLog(String strOperId,int totalrecords,int unsuccessfulrecords,String strPath,String remark) throws Exception {
		/*CwbimportlogColumns objCIL=new CwbimportlogColumns();
		objCIL.setToctdioperatoropid(Long.parseLong(strOperId));
		objCIL.setToccwlcreatedate(new Date());
		objCIL.setToccwltotalrecords(totalrecords);
		objCIL.setToccwlunsuccessfulrecords(unsuccessfulrecords);
		objCIL.setToccwlfilepath(strPath);
		objCIL.setToccwlremark(remark);
		objCIL.setToccwlStatus("C");	//�ϴ����
		AddcwbimportlogTrans objACILTrans = new AddcwbimportlogTrans();
		objACILTrans.setParam(objCIL,Long.parseLong(strOperId));
		objACILTrans.execute();
		// ����ֵ
		Long cwId = objACILTrans.getCwlId();
		return CwbimportlogDemand.load(cwId);*/
		return saveLog(strOperId, totalrecords, unsuccessfulrecords, strPath, remark, null, new Date());
	}
	/**
	 * �����ϴ���־
	 * @param strOperId
	 * @param totalrecords
	 * @param unsuccessfulrecords
	 * @param strPath
	 * @param remark
	 * @param templeCode
	 * @param createDate
	 * @return
	 * @throws Exception
	 */
	public CwbimportlogColumns saveLog(String strOperId, int totalrecords, int unsuccessfulrecords, String strPath,
			String remark, Long templeCode, Date createDate) throws Exception {
		CwbimportlogColumns objCIL=new CwbimportlogColumns();
		objCIL.setToctdioperatoropid(Long.parseLong(strOperId));
		objCIL.setToccwlcreatedate(createDate);
		objCIL.setToccwltotalrecords(totalrecords);
		objCIL.setToccwlunsuccessfulrecords(unsuccessfulrecords);
		objCIL.setToccwlfilepath(strPath);
		objCIL.setToccwlremark(remark);
		objCIL.setToccwlStatus("C");	//�ϴ����
		objCIL.setTocpotid(templeCode);
		AddcwbimportlogTrans objACILTrans = new AddcwbimportlogTrans();
		objACILTrans.setParam(objCIL,Long.parseLong(strOperId));
		objACILTrans.execute();
		// ����ֵ
		Long cwId = objACILTrans.getCwlId();
		return CwbimportlogDemand.load(cwId);
	}
	
	/**
	 * ����״̬Ϊ�ϴ��е���־
	 * @param strOperId
	 * @param totalrecords
	 * @param unsuccessfulrecords
	 * @param strPath
	 * @param templeCode
	 * @param createDate
	 * @return
	 * @throws Exception
	 */
	public CwbimportlogColumns saveLogStatusUploading(String strOperId, int totalrecords, String strPath,
			 Long templeCode, Date createDate) throws Exception {
		CwbimportlogColumns objCIL=new CwbimportlogColumns();
		objCIL.setToctdioperatoropid(Long.parseLong(strOperId));
		objCIL.setToccwlcreatedate(createDate);
		objCIL.setToccwltotalrecords(totalrecords);
		objCIL.setToccwlunsuccessfulrecords(0);
		objCIL.setToccwlfilepath(strPath);
		objCIL.setToccwlStatus("I");	//�ϴ���
		objCIL.setTocpotid(templeCode);
		AddcwbimportlogTrans objACILTrans = new AddcwbimportlogTrans();
		objACILTrans.setParam(objCIL,Long.parseLong(strOperId));
		objACILTrans.execute();
		Long cwId = objACILTrans.getCwlId();
		return CwbimportlogDemand.load(cwId);
	}
	
	/**
	 * �������ϴ�״̬��Ϊ�ϴ����״̬
	 * @param cwbimportColumns
	 * @param remark
	 * @param unsuccessfulrecords
	 * @param opId
	 * @throws Exception
	 */
	public void updateStatus(CwbimportlogColumns cwbimportColumns,String remark,
			int unsuccessfulrecords,Long opId)throws Exception{
		cwbimportColumns.setToccwlremark(remark);
		cwbimportColumns.setToccwlunsuccessfulrecords(unsuccessfulrecords);
		cwbimportColumns.setToccwlStatus("C");
		ModifycwbimportlogTrans modifycwblogTrans = new ModifycwbimportlogTrans();
		modifycwblogTrans.setParam(cwbimportColumns, opId);
		modifycwblogTrans.execute();
	}
	
	//������
	public void saveRow(int cwbrid,String cwilid,String successsign,String operatetype,String remark) throws Exception {
		CwbimportrowColumns cwimportrow=new CwbimportrowColumns();
		cwimportrow.setCwbrcomp_idcwbrid(new Long(cwbrid));
		cwimportrow.setCwbrtopcwbimportlogcwlid(Long.parseLong(cwilid));
		cwimportrow.setCwbrcwbrsuccesssign(successsign);
		cwimportrow.setCwbrcwbroperatetype(operatetype);
		cwimportrow.setCwbrcwbrremark(remark);
		AddcwbimportrowTrans objACIRTrans = new AddcwbimportrowTrans();
		objACIRTrans.setParam(cwimportrow);
		objACIRTrans.execute();
	}
	//����������
	public void saveData(CwbimportdataColumns objCID) throws Exception {
		AddcwbimportdataTrans objACIDTrans = new AddcwbimportdataTrans();
		objACIDTrans.setParam(objCID);
		objACIDTrans.execute();
	}
}
