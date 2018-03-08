package kyle.leis.fs.dictionary.district.bl;

import java.util.List;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.fs.cachecontainer.da.DistrictColumns;
import kyle.leis.fs.dictionary.district.da.CityColumns; //import kyle.leis.fs.dictionary.district.da.DistrictColumns;
import kyle.leis.fs.dictionary.district.da.CityExportColumns;
import kyle.leis.fs.dictionary.district.da.DicdistrictColumns;
import kyle.leis.fs.dictionary.district.da.DicdistrictCondition;
import kyle.leis.fs.dictionary.district.da.RegionColumns;
import kyle.leis.fs.dictionary.district.da.StateColumns;
import kyle.leis.fs.dictionary.district.da.StateCondition;
import kyle.leis.fs.dictionary.district.da.StateQuery;
import kyle.leis.fs.dictionary.district.dax.CityDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.district.tp.CityDelTransaction;
import kyle.leis.fs.dictionary.district.tp.CitySaveTransaction;
import kyle.leis.fs.dictionary.district.tp.DeleteDistrict;
import kyle.leis.fs.dictionary.district.tp.RegionDelTransaction;
import kyle.leis.fs.dictionary.district.tp.RegionSaveTransaction;
import kyle.leis.fs.dictionary.district.tp.SaveDhlDistrictTransction;
import kyle.leis.fs.dictionary.district.tp.SaveDhlremotedistrictTransction;
import kyle.leis.fs.dictionary.district.tp.SaveDistrict; //import kyle.leis.fs.dictionary.district.tp.SavaDistrictTransaction;
import kyle.leis.fs.dictionary.district.tp.StateDelTransaction;
import kyle.leis.fs.dictionary.district.tp.StateDelete;
import kyle.leis.fs.dictionary.district.tp.StateSaveTransaction;
import kyle.leis.fs.dictionary.district.tp.TruncateDhlDistrict;
import kyle.leis.fs.dictionary.district.tp.TruncateDrdDistrict;
import kyle.leis.hi.TdiState;

public class District {
	/*
	 * ���/�޸�������Ϣ
	 * SavaDistrictTransactionû��
	 */
	
	 /* public DistrictColumns addDistrict(DistrictColumns objDistrictColumns,
	  String strOperator) throws Exception {
	  SavaDistrictTransaction objSDTran = new SavaDistrictTransaction();
	  
	  objSDTrans.setParam(objDistrictColumns); objSDTrans.execute();
	  
	  DistrictColumns objReturn = DistrictDemand.queryByDtcode(objSDTrans.getNewDtcode()); if (objReturn == null) return null; return objReturn;
	  
	  }*/
	 

	// ���/�޸�ʡ��Ϣ

	public StateColumns saveState(StateColumns objStateColumns) throws Exception {
		StateSaveTransaction objStateSaveTransaction = new StateSaveTransaction();

		objStateSaveTransaction.setM_objTdistateColumns(objStateColumns);
		objStateSaveTransaction.execute();

		StateColumns objReturn = DistrictDemand
				.queryState(objStateSaveTransaction.getnewStCode());
		return objReturn;
	}

	// ���/�޸ĳ�����Ϣ

	public CityColumns saveCity(CityColumns objCityColumns, String strOperator)
			throws Exception {
		CitySaveTransaction objSaveCityTransation = new CitySaveTransaction();
		objSaveCityTransation.setM_objTdicityColumns(objCityColumns);

		objSaveCityTransation.execute();

		CityColumns objReturn = DistrictDemand.queryCity(objSaveCityTransation
				.getnewCtCode());
		return objReturn;
	}

	// ���/�޸ĵ�����Ϣ
	// �����ˣ��޸��ˡ���District���У�
	public RegionColumns saveRegion(RegionColumns objRegionColumns,
			String strOperator) throws Exception {
		RegionSaveTransaction objRegionSaveTransaction = new RegionSaveTransaction();
		objRegionSaveTransaction.setObjTdiregionColumns(objRegionColumns);
		objRegionSaveTransaction.execute();

		RegionColumns objReturn = DistrictDemand
				.queryRegion(objRegionSaveTransaction.getNewRgcode());
		return objReturn;
	}

	// ɾ��������Ϣ

	public String delRegion(String[] rgCode_list) throws Exception {
		RegionDelTransaction objDelTransactions = new RegionDelTransaction();
		objDelTransactions.setM_rgCodeList(rgCode_list);
		objDelTransactions.execute();
		String strResult = objDelTransactions.getResult();
		if (strResult == "")
			return "";
		return "ɾ��ʧ��";
	}

	// ɾ��������Ϣ

	public String delCity(String[] ctCode_list) throws Exception {
		CityDelTransaction objCityDelTransaction = new CityDelTransaction();
		objCityDelTransaction.setM_ctCodeList(ctCode_list);
		objCityDelTransaction.execute();
		String strResult = objCityDelTransaction.getResult();
		if (strResult == "")
			return "";
		return "ɾ��ʧ��";
	}

	// ɾ��ʡ��Ϣ

	public String delState(String[] stCode_list) throws Exception {
		StateDelTransaction objStateDelTransaction = new StateDelTransaction();
		objStateDelTransaction.setM_stCodeList(stCode_list);
		objStateDelTransaction.execute();
		String strResult = objStateDelTransaction.getResult();
		if (strResult == "")
			return "";
		return "ɾ��ʧ��";
	}

	/**
	 * ɾ��ʡ��Ϣ������delState
	 * by wukaiquan
	 * 
	 * **/
	public String delState(String stCode_list) throws Exception {
		StateDelete delete = new StateDelete();
		delete.setM_stCodeList(stCode_list);
		delete.execute();
		String strResult = delete.getResult();
		if (strResult == "")
			return "";
		return "ɾ��ʧ��";
	}
	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:save
	 * @throws Exception
	 * **/
	public DicdistrictColumns save(DicdistrictColumns columns) throws Exception {
		SaveDistrict objSave = new SaveDistrict();
		objSave.setParam(columns, columns.getDidtcode());
		System.out.println(columns.getDidtcode());
		objSave.execute();

		DicdistrictColumns objReturn = DistrictDemand.querById(columns
				.getDidtcode());
		if (objReturn == null)
			return null;// �������ֵΪ��
		return objReturn;
	}

	/**
	 * 20160809 Tus 
	 * by wukaiquan 
	 * oprion:delete
	 * @throws Exception
	 * **/
	public String delete(String code) throws Exception {
		DeleteDistrict objDelete = new DeleteDistrict();
		objDelete.setParam(code);
		objDelete.execute();

		return objDelete.getConditionStr();
	}
	/**
	 * 20160817 17:15
	 * by wukaiquan\
	 * 
	 * **/
	public DicdistrictColumns saveDistricts(DicdistrictColumns columns,
			String strOperator) throws Exception {
		SaveDistrict objSave = new SaveDistrict();
		// ��������,���ò�����,��������������id��������
		columns.setDidtopcodecreator(strOperator);
		objSave.setParam(columns, columns.getDidtcode());
		System.out.println(columns.getDidtcode());
		objSave.execute();

		DicdistrictColumns objReturn = DistrictDemand.querById(columns
				.getDidtcode());
		if (objReturn == null)
			return null;// �������ֵΪ��
		return objReturn;
	}
	/**
	 * 20160817 17:15
	 * by wukaiquan\
	 * ��ѯʡ�����ʡ
	 * **/
	public StateColumns queryState(StateCondition condition) throws Exception{
		District objDistrict = new District();

		StateQuery query = new StateQuery();
		query.setCondition(condition);
		List listReturn  = query.getResults();
		StateColumns stateColumns = new   StateColumns();
		StateColumns objReturnStateColumns = new  StateColumns();
		if(CollectionUtility.isNull(listReturn)){
			//���ʡ,�ٷ���StateColumns

			stateColumns.setStstsname(condition.getStsname());
			stateColumns.setStstname(condition.getStsname());
			stateColumns.setStstename(condition.getStsname());
			stateColumns.setDidtcode("1");
			objReturnStateColumns = objDistrict.saveState(stateColumns);
			return objReturnStateColumns;
		}else{
			//��λ��ֱ�ӷŻ�StateColumns
			return (StateColumns) listReturn.get(0);
		}
	}
	

	/**
	 * 20160817 10:05
	 *  by wukaiquan 
	 * �������citys
	 * **/
	public String saveMulitCity(List listObjCity) throws Exception {
		District objDistrict = new District();
		StateCondition condition = new StateCondition();
		for (int i = 0; i < listObjCity.size(); i++) {
			//��ѯʡ,����һ������ʡ
			CityExportColumns objCityExportColumns = (CityExportColumns) listObjCity.get(i);
			String stateName = objCityExportColumns.getTdcStateEname();
			condition.setStsname(stateName);
			objDistrict.queryState(condition);
		}
		//������ӳ���
		for (int i = 0; i < listObjCity.size(); i++) {
			CityExportColumns objCityExportColumns = (CityExportColumns) listObjCity.get(i);
			String stateName = objCityExportColumns.getTdcStateEname();
			condition.setStsname(stateName);
			StateColumns objReturnStateColumns = objDistrict.queryState(condition);
			
			// ���ݶ��ֽ�����ҹ���,DistrictColumns�������Լ����ɵġ�
			DicdistrictCondition objDistrictCondition = new DicdistrictCondition();
			objDistrictCondition.setDthubcode(objCityExportColumns.getTdcHubcode());
			List listDistrict = DistrictDemand.query(objDistrictCondition);
			if (CollectionUtility.isNull(listDistrict)) {
					continue;
				}
			DicdistrictColumns objDistrictColumns = (DicdistrictColumns) listDistrict.get(0);
			CityColumns columns = new CityColumns();
			CityDemand.setCityColumns(columns, objCityExportColumns, objReturnStateColumns, objDistrictColumns);
			
			CitySaveTransaction objSaveCityTransation = new CitySaveTransaction();
			objSaveCityTransation.setM_objTdicityColumns(columns);
			objSaveCityTransation.execute(); 
		}
		return "abc";

	}
	/**
	 * 20160822 16:41
	 * by wukaiquan
	 *��ɾ��ԭ������, ����������
	 * @throws Exception **/
	public String saveMulitdhlDistrict(List listcolumns) throws Exception {
		
		TruncateDhlDistrict truncate  = new TruncateDhlDistrict();
		truncate.execute();
		
		SaveDhlDistrictTransction transction = new SaveDhlDistrictTransction();
		transction.setDhlDistrictlist(listcolumns);
		transction.execute();
		
		return "abc";
	}
    
	public String saveMulitdhlRemotedDistrict(List listcolumns) throws Exception{
	     //�����������
		 TruncateDrdDistrict trd = new TruncateDrdDistrict();
		 trd.execute();

		 SaveDhlremotedistrictTransction sa = new SaveDhlremotedistrictTransction();
	     sa.setM_dhlrlist(listcolumns);
	     sa.execute();
	     return "";
	}
	
	
}
