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
	 * 添加/修改区域信息
	 * SavaDistrictTransaction没有
	 */
	
	 /* public DistrictColumns addDistrict(DistrictColumns objDistrictColumns,
	  String strOperator) throws Exception {
	  SavaDistrictTransaction objSDTran = new SavaDistrictTransaction();
	  
	  objSDTrans.setParam(objDistrictColumns); objSDTrans.execute();
	  
	  DistrictColumns objReturn = DistrictDemand.queryByDtcode(objSDTrans.getNewDtcode()); if (objReturn == null) return null; return objReturn;
	  
	  }*/
	 

	// 添加/修改省信息

	public StateColumns saveState(StateColumns objStateColumns) throws Exception {
		StateSaveTransaction objStateSaveTransaction = new StateSaveTransaction();

		objStateSaveTransaction.setM_objTdistateColumns(objStateColumns);
		objStateSaveTransaction.execute();

		StateColumns objReturn = DistrictDemand
				.queryState(objStateSaveTransaction.getnewStCode());
		return objReturn;
	}

	// 添加/修改城市信息

	public CityColumns saveCity(CityColumns objCityColumns, String strOperator)
			throws Exception {
		CitySaveTransaction objSaveCityTransation = new CitySaveTransaction();
		objSaveCityTransation.setM_objTdicityColumns(objCityColumns);

		objSaveCityTransation.execute();

		CityColumns objReturn = DistrictDemand.queryCity(objSaveCityTransation
				.getnewCtCode());
		return objReturn;
	}

	// 添加/修改地区信息
	// 创建人，修改人。在District表中，
	public RegionColumns saveRegion(RegionColumns objRegionColumns,
			String strOperator) throws Exception {
		RegionSaveTransaction objRegionSaveTransaction = new RegionSaveTransaction();
		objRegionSaveTransaction.setObjTdiregionColumns(objRegionColumns);
		objRegionSaveTransaction.execute();

		RegionColumns objReturn = DistrictDemand
				.queryRegion(objRegionSaveTransaction.getNewRgcode());
		return objReturn;
	}

	// 删除区域信息

	public String delRegion(String[] rgCode_list) throws Exception {
		RegionDelTransaction objDelTransactions = new RegionDelTransaction();
		objDelTransactions.setM_rgCodeList(rgCode_list);
		objDelTransactions.execute();
		String strResult = objDelTransactions.getResult();
		if (strResult == "")
			return "";
		return "删除失败";
	}

	// 删除城市信息

	public String delCity(String[] ctCode_list) throws Exception {
		CityDelTransaction objCityDelTransaction = new CityDelTransaction();
		objCityDelTransaction.setM_ctCodeList(ctCode_list);
		objCityDelTransaction.execute();
		String strResult = objCityDelTransaction.getResult();
		if (strResult == "")
			return "";
		return "删除失败";
	}

	// 删除省信息

	public String delState(String[] stCode_list) throws Exception {
		StateDelTransaction objStateDelTransaction = new StateDelTransaction();
		objStateDelTransaction.setM_stCodeList(stCode_list);
		objStateDelTransaction.execute();
		String strResult = objStateDelTransaction.getResult();
		if (strResult == "")
			return "";
		return "删除失败";
	}

	/**
	 * 删除省信息，重载delState
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
		return "删除失败";
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
			return null;// 如果返回值为空
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
		// 批量导入,设置操作人,都是新增，由于id自增长，
		columns.setDidtopcodecreator(strOperator);
		objSave.setParam(columns, columns.getDidtcode());
		System.out.println(columns.getDidtcode());
		objSave.execute();

		DicdistrictColumns objReturn = DistrictDemand.querById(columns
				.getDidtcode());
		if (objReturn == null)
			return null;// 如果返回值为空
		return objReturn;
	}
	/**
	 * 20160817 17:15
	 * by wukaiquan\
	 * 查询省并添加省
	 * **/
	public StateColumns queryState(StateCondition condition) throws Exception{
		District objDistrict = new District();

		StateQuery query = new StateQuery();
		query.setCondition(condition);
		List listReturn  = query.getResults();
		StateColumns stateColumns = new   StateColumns();
		StateColumns objReturnStateColumns = new  StateColumns();
		if(CollectionUtility.isNull(listReturn)){
			//添加省,再返回StateColumns

			stateColumns.setStstsname(condition.getStsname());
			stateColumns.setStstname(condition.getStsname());
			stateColumns.setStstename(condition.getStsname());
			stateColumns.setDidtcode("1");
			objReturnStateColumns = objDistrict.saveState(stateColumns);
			return objReturnStateColumns;
		}else{
			//部位空直接放回StateColumns
			return (StateColumns) listReturn.get(0);
		}
	}
	

	/**
	 * 20160817 10:05
	 *  by wukaiquan 
	 * 批量添加citys
	 * **/
	public String saveMulitCity(List listObjCity) throws Exception {
		District objDistrict = new District();
		StateCondition condition = new StateCondition();
		for (int i = 0; i < listObjCity.size(); i++) {
			//查询省,先做一种事务，省
			CityExportColumns objCityExportColumns = (CityExportColumns) listObjCity.get(i);
			String stateName = objCityExportColumns.getTdcStateEname();
			condition.setStsname(stateName);
			objDistrict.queryState(condition);
		}
		//批量添加城市
		for (int i = 0; i < listObjCity.size(); i++) {
			CityExportColumns objCityExportColumns = (CityExportColumns) listObjCity.get(i);
			String stateName = objCityExportColumns.getTdcStateEname();
			condition.setStsname(stateName);
			StateColumns objReturnStateColumns = objDistrict.queryState(condition);
			
			// 根据二字节码查找国家,DistrictColumns还是我自己生成的。
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
	 *先删除原表数据, 在批量导入
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
	     //清空所有数据
		 TruncateDrdDistrict trd = new TruncateDrdDistrict();
		 trd.execute();

		 SaveDhlremotedistrictTransction sa = new SaveDhlremotedistrictTransction();
	     sa.setM_dhlrlist(listcolumns);
	     sa.execute();
	     return "";
	}
	
	
}
