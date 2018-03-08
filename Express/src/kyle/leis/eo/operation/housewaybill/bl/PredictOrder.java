package kyle.leis.eo.operation.housewaybill.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillQuery;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.PredictOrderColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCorporationDC;
import kyle.leis.hi.TcoCorporation;


public class PredictOrder {
	/*
	 * ����Ԥ������
	 */
	public InputAllQReturn save(PredictOrderColumns objPOColumns,String strOperId) throws Exception
	{
		Input input = new Input();
		ForinputallColumns objFIAColumns = buildForinputAllColumns(objPOColumns);
		List listCargo = buildCargoinfoColumns(objPOColumns, objFIAColumns);
		List listWaybillPieces = buildPiecesInfo(objPOColumns);
		return input.inputAll(strOperId, objFIAColumns, 
				listCargo, listWaybillPieces, true);
	}
	
	private List buildPiecesInfo(PredictOrderColumns objPOColumns) {
		if (StringUtility.isNull(objPOColumns.getStrCwpieces()))
			objPOColumns.setStrCwpieces("1");
		
		int iPieces = Integer.parseInt(objPOColumns.getStrCwpieces());
		if (StringUtility.isNull(objPOColumns.getStrCwgrossweight()))
			objPOColumns.setStrCwgrossweight("0");
		List<CorewaybillpiecesColumns> listPiecesColumns = new ArrayList<CorewaybillpiecesColumns>();
		for (int i = 0; i < iPieces; i++) {
			CorewaybillpiecesColumns objCWPColumns = new CorewaybillpiecesColumns();
			objCWPColumns.setCpcomp_idcpid(i);
			//objCWPColumns.setCpcomp_idcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			if (i == 0) 
				objCWPColumns.setCpcpgrossweight(new BigDecimal(objPOColumns.getStrCwgrossweight()));
			else
				objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
			objCWPColumns.setCpcpheight(new BigDecimal("0"));
			objCWPColumns.setCpcpwidth(new BigDecimal("0"));
			objCWPColumns.setCpcplength(new BigDecimal("0"));
			listPiecesColumns.add(objCWPColumns);
		}
		return listPiecesColumns;
	}	
	

	public InputAllQReturn modify(PredictOrderColumns objPOColumns,String strOperId) throws Exception
	{
		Input input = new Input();
		ForinputallColumns objFIAColumns = buildForinputAllColumns(objPOColumns);
		List listCargo = buildCargoinfoColumns(objPOColumns,objFIAColumns);
		return input.modify(strOperId, objFIAColumns, 
				listCargo, false, false,
				false);
	}
	
	
	/*
	 * ��������Ԥ������
	 */
	public List<InputAllQReturn> saveBatchPredictOrder(List<PredictOrderColumns> listPOColumns,
			String strOperId) throws Exception
	{
		List<InputAllQReturn> listIAQReturn  = new ArrayList<InputAllQReturn>();
		InputAllQReturn objIAQReturn = null;
		int resultSize = 0;
		for(int i=0; i<listPOColumns.size(); i++)
		{
			objIAQReturn = save(listPOColumns.get(i),strOperId);
			objIAQReturn.setErrCustomerEWBCode(listPOColumns.get(i).getStrCwcustomerewbcode());
			listIAQReturn.add(objIAQReturn);
			resultSize++ ;
		}
		System.out.println("�ѱ���"+resultSize+"�����ݣ�");
		return listIAQReturn;
	}
	
	private String buildCustomerEWBCode(String strCocode) throws Exception {
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCocodecustomer(strCocode);
		objSCWQuery.setStartcreatedate(DateFormatUtility.getStandardSysdate().substring(0, 10));
		objSCWQuery.setEndcreatedate(DateFormatUtility.getStandardSysdate().substring(0, 10) + " 23:59:59");
		List listResults = objSCWQuery.getResults();
		int iCurrentIndex = 1;
		if (listResults != null && listResults.size() > 0)
			iCurrentIndex = listResults.size() + 1;
		TcoCorporation objTcoCorporation = TcoCorporationDC.loadByKey(strCocode);
		return objTcoCorporation.getCoLabelcode() + DateFormatUtility.getCompactOnlyDateSysdate() + iCurrentIndex;
	}
	
	
	/*
	 * ����ForinputallColumns
	 */
	public ForinputallColumns buildForinputAllColumns(PredictOrderColumns objPOColumns) throws Exception
	{
		ForinputallColumns objFIAColumns = new ForinputallColumns();
		BigDecimal objDefaulfValue = new BigDecimal("0.500");
		//�ͻ��˵���
		String strCwcustomerewbcode = objPOColumns.getStrCwcustomerewbcode();
		if (StringUtility.isNull(strCwcustomerewbcode)) {
			strCwcustomerewbcode = buildCustomerEWBCode(objPOColumns.getStrCocode());
		}
		
		objFIAColumns.setCwcustomerewbcode(strCwcustomerewbcode);
		objFIAColumns.setCwserverewbcode(strCwcustomerewbcode);
		objFIAColumns.setCwewbcode(strCwcustomerewbcode);
		
		if(!StringUtility.isNull(objPOColumns.getStrCwcwcode()))
			objFIAColumns.setCwcode(Long.valueOf(objPOColumns.getStrCwcwcode()));//20101021
		if(!StringUtility.isNull(objPOColumns.getStrSupplierCode()))
			objFIAColumns.setCocode_Cwsp(objPOColumns.getStrSupplierCode());
		
		//objFIAColumns.setAdddate(DateFormatUtility.getSysdate());
		objFIAColumns.setAdddate(DateFormatUtility.getStandardDate(objPOColumns.getStrAdddate()));
		objFIAColumns.setCocode(objPOColumns.getStrCocode());
		objFIAColumns.setCocode_Cwcus(objPOColumns.getStrCocode());
		if (StringUtility.isNull(objPOColumns.getEeCode()))
			objFIAColumns.setEecode("1");
		else
			objFIAColumns.setEecode(objPOColumns.getEeCode());
			
		//objFIAColumns.setChncode();���Կ�
		objFIAColumns.setDtcode_Cwodt("719");
		objFIAColumns.setPk_code(objPOColumns.getStrPk_code());
		objFIAColumns.setDtcode(objPOColumns.getStrDtcode());
		objFIAColumns.setSidtcode(objPOColumns.getStrDtcode());
//		objFIAColumns.setDtcode(objPOColumns.getStrDtDcode());
		objFIAColumns.setCwscode("CTS");
		//ë��
		String strCw_grossweight = objPOColumns.getStrCwgrossweight();
		if(strCw_grossweight != null && !strCw_grossweight.equals(""))
		{
			BigDecimal objCwgrossweight = new BigDecimal(strCw_grossweight);
			objFIAColumns.setCwgrossweight(objCwgrossweight);
			//if(objCwgrossweight.compareTo(objDefaulfValue)<0)
			//	objFIAColumns.setCtcode("ADOX");
		}
		else
			objFIAColumns.setCwgrossweight(objDefaulfValue);
		if(objFIAColumns.getCtcode() == null || objFIAColumns.getCtcode().equals(""))
			objFIAColumns.setCtcode("AWPX");
		
		//���÷����̵�����
		BigDecimal objFIACGrossweight = new BigDecimal(objFIAColumns.getCwgrossweight());
		objFIAColumns.setCwchargeweight(objFIACGrossweight);//////
		objFIAColumns.setCwserverchargeweight(objFIACGrossweight);
		objFIAColumns.setCwtransferchargeweight(objFIACGrossweight);
		objFIAColumns.setCwtransfergrossweight(objFIACGrossweight);
		objFIAColumns.setTransfervolumeweight(objFIACGrossweight);
		
		objFIAColumns.setTransfervolumerate(Integer.parseInt("5000"));//////
		objFIAColumns.setPmcode("APP");//����ģʽ(Ԥ��������Ĭ��Ԥ��)
		
		if(objPOColumns.getStrCwpieces() != null && !objPOColumns.getStrCwpieces().equals(""))
			objFIAColumns.setCwpieces(new Integer(objPOColumns.getStrCwpieces()).intValue());
		else
			objFIAColumns.setCwpieces(1);
		//objFIAColumns.setCwcustomerchargeweight();��setter����
		//objFIAColumns.setDtcodeorigin();�������ʺ� ����ΪTdiDistrict����
		//objFIAColumns.setEecode("1"); �˴�������Ч,û����CoreWayBill=> objCoreWayBill.setTdiEnterpriseelement(TdiEnterpriseelement e)
		
		//�ռ�����Ϣ
		objFIAColumns.setHwconsigneename(objPOColumns.getStrHwconsigneename());
		objFIAColumns.setHwconsigneecompany(objPOColumns.getHwconsigneecompanyname());
		objFIAColumns.setHwconsigneetelephone(objPOColumns.getStrHwconsigneetelephone());
		objFIAColumns.setHwconsigneeaddress1(objPOColumns.getStrHwconsigneeaddress1());
		objFIAColumns.setHwconsigneeaddress2(objPOColumns.getStrHwconsigneeaddress2());
		objFIAColumns.setHwconsigneeaddress3(objPOColumns.getStrHwconsigneeaddress3());
		objFIAColumns.setCwpostcodedestination(objPOColumns.getStrCwpostcodedestination());
		//��������Ϣ
		//objFIAColumns.setHwshipperaccount(strHwshipperaccount)�������ʺ�
		String strShipperLabelcode = objPOColumns.getShipperInfoLabelcode();
		ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.loadBySclabelcode(strShipperLabelcode);
		if (objSCColumns != null) {
			objFIAColumns.setHwshipperaddress1(objSCColumns.getScscaddress1());
			objFIAColumns.setHwshipperaddress2(objSCColumns.getScscaddress2());
			objFIAColumns.setHwshipperaddress3(objSCColumns.getScscaddress3());
			objFIAColumns.setHwshippercompany(objSCColumns.getScsccompanyname());
			objFIAColumns.setHwshipperfax(objSCColumns.getScscfax());
			objFIAColumns.setHwshippername(objSCColumns.getScscname());
			objFIAColumns.setHwshipperpostcode(objSCColumns.getScscpostcode());
			objFIAColumns.setHwshippertelephone(objSCColumns.getScsctelephone());			
		} else {
			objFIAColumns.setHwshipperaddress1(".");
			objFIAColumns.setHwshipperaddress2(".");
			objFIAColumns.setHwshipperaddress3(".");
			objFIAColumns.setHwshippercompany(".");
			objFIAColumns.setHwshipperfax(".");
			objFIAColumns.setHwshippername(".");
			objFIAColumns.setHwshipperpostcode(".");
			objFIAColumns.setHwshippertelephone(".");			
		}

		return objFIAColumns;
	}
	
	/*
	 * ����������Ϣ
	 */
	public List buildCargoinfoColumns(PredictOrderColumns objPOColumns, 
			ForinputallColumns objFIAColumns)
	{
		List listCargo = new ArrayList();
		String strFirstCiename = objPOColumns.getStrCiename()[0];
		if (!StringUtility.isNull(strFirstCiename) &&
				strFirstCiename.indexOf("DOC") >= 0)
			objFIAColumns.setCtcode("ADOX");
		
		if((objPOColumns.getStrCiename()==null || 
				objPOColumns.getStrCiename()[0].equals("")) && 
				(objPOColumns.getStrCipieces()==null ||
						objPOColumns.getStrCipieces()[0].equals("")) && 
						(objPOColumns.getStrCiunitprice()==null) || 
						objPOColumns.getStrCiunitprice()[0].equals(""))
			return listCargo;
		//������Ϣ
		String [] arrayEname = objPOColumns.getStrCiename();
		String [] arrayPieces = objPOColumns.getStrCipieces();
		String [] arrayUniteprice = objPOColumns.getStrCiunitprice();
		String [] arrayAttacheinfo = objPOColumns.getAttacheInfo();
		
		for(int i=0;i<arrayEname.length;i++)
		{
			if (StringUtility.isNull(arrayEname[i]))
				continue;
			//����Ĭ��ֵ
			if(arrayPieces[i].equals("")||arrayPieces==null)
			{
				arrayPieces[i] = "1";
			}
			if(arrayUniteprice[i].equals("") || arrayUniteprice[i]==null)
			{
				arrayUniteprice[i] = "0.000";
			}
			// Ʒ������ȷ����Ϊ�ļ�ʱ���������˵��Ļ�������Ϊ�ļ�
			if (arrayEname[i].indexOf("DOC") >= 0)
				objFIAColumns.setCtcode("ADOX");
			
			CargoinfoColumns objCargoinfoColumns = new CargoinfoColumns();
			objCargoinfoColumns.setCiciename(arrayEname[i]);
			objCargoinfoColumns.setCicipieces(Integer.parseInt(arrayPieces[i]));
			objCargoinfoColumns.setCiciunitprice(new BigDecimal(arrayUniteprice[i]));
			objCargoinfoColumns.setCiciname(arrayEname[i]);
			objCargoinfoColumns.setCicomp_idciid(new Integer(i));//ʹ�������Ϊid
			objCargoinfoColumns.setCicitotalprice(new BigDecimal(arrayPieces[i]).multiply(new BigDecimal(arrayUniteprice[i])));
			
			if (arrayAttacheinfo != null && arrayAttacheinfo.length > 0)
				objCargoinfoColumns.setCiciattacheinfo(arrayAttacheinfo[i]);
			
			listCargo.add(objCargoinfoColumns);
	   }
		return listCargo;
	}
}
