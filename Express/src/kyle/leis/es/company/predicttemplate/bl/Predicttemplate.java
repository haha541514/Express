package kyle.leis.es.company.predicttemplate.bl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoinfoColumnsEX;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderCell;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderRow;
import kyle.leis.es.company.predicttemplate.bl.cmt.ColumnmappingtypeFactory;
import kyle.leis.es.company.predicttemplate.da.PredicttemplateColumns;
import kyle.leis.es.company.predicttemplate.da.PredicttemplatevalueColumns;
import kyle.leis.es.company.predicttemplate.dax.PredicttemplateDemand;
import kyle.leis.es.company.predicttemplate.tp.SavePredictTrans;

public class Predicttemplate {
	
	public List<PredictOrderColumnsEX> transferTostandard(String strPredictOrderTemplate,
			List<PredictOrderRow> listPredictOrderRow) throws Exception {
		List<PredictOrderColumnsEX> listWaybill = new ArrayList<PredictOrderColumnsEX>();
		if (listPredictOrderRow == null || listPredictOrderRow.size() < 1)
			return listWaybill;
		// 获得与标准列的映射关系
		Map<String, PredicttemplatevalueColumns> hmPredictColumns = PredicttemplateDemand.queryTemplatevalue(strPredictOrderTemplate);
		if (hmPredictColumns == null || hmPredictColumns.size() < 1)
			return listWaybill;
		for (int i = 0; i < listPredictOrderRow.size(); i++) {
			PredictOrderRow objPOR = listPredictOrderRow.get(i);
			List<PredictOrderCell>listCells = objPOR.getListPredictOrdercell();
			if (listCells == null || listCells.size() < 1)
				continue;
			PredictOrderColumnsEX objWFPC = transferTostandard(strPredictOrderTemplate, 
					listCells, 
					hmPredictColumns);
			objWFPC.setExcelRowIndex(objPOR.getExcelRowIndex());
			// 如果发件人信息为空则取默认的发件人账号数据
			// 暂时空置
			if (objWFPC != null)
				listWaybill.add(objWFPC);
		}
		return listWaybill;
	}
	
	private PredictOrderColumnsEX transferTostandard(String strPredictOrderTemplate,
			List<PredictOrderCell>listCells,
			Map<String, PredicttemplatevalueColumns> hmPredictColumns) throws Exception {
		PredictOrderColumnsEX objWFPCEX = new PredictOrderColumnsEX();
		WaybillforpredictColumns waybillforpredict = new WaybillforpredictColumns();
		List<CargoinfoColumns> listCargoInfo = new ArrayList<CargoinfoColumns>();
		CargoinfoColumnsEX objCargoinfoColumns = null;
		int iCargoGroupIndex = 1;
		int iCargoGroupID = 0;
		
		for (int i = 0; i < listCells.size(); i++) {
			PredictOrderCell objPOC = listCells.get(i);
			if (!hmPredictColumns.containsKey(objPOC.getCellname().trim()))
				continue;
			PredicttemplatevalueColumns objPTVColumns = hmPredictColumns.get(objPOC.getCellname().trim());
			String strCellvalue = objPOC.getCellvalue();
			String strDmkcode = objPTVColumns.getDmkdmkcode();
			IColumnMappingType objIMT = ColumnmappingtypeFactory.createMappingtype(objPTVColumns.getCmtcmtcode());
			
			if (!StringUtility.isNull(strDmkcode) && !StringUtility.isNull(strCellvalue)) {
				strCellvalue = PredicttemplateDemand.getMappingvalue(strPredictOrderTemplate,
						strCellvalue,
						strDmkcode);
			}
			String strColumnEname = objPTVColumns.getTctccolumnename();
			if (StringUtility.isNull(strColumnEname) || StringUtility.isNull(strCellvalue))
				continue;
			if (objPTVColumns.getTctccolumntype().equals("W")) {
				objIMT.setValue(strColumnEname, 
						waybillforpredict, 
						strCellvalue);
			}
			else if (objPTVColumns.getTctccolumntype().equals("C")) {
				int iActualIndex = Integer.parseInt(objPTVColumns.getTctccolumngroup());
				if (iActualIndex != iCargoGroupIndex) {
					iCargoGroupIndex = iActualIndex;
					iCargoGroupID = 0;
					listCargoInfo.add(objCargoinfoColumns);
				}
				if (iCargoGroupIndex == iActualIndex && 
						iCargoGroupID == 0) {
					objCargoinfoColumns = new CargoinfoColumnsEX();
				}
				if (iCargoGroupIndex == iActualIndex)
					iCargoGroupID = iCargoGroupID + 1;
					
				objIMT.setValue(strColumnEname, 
						objCargoinfoColumns, 
						strCellvalue);			
			}
		}
		// 最后一个Cargo加入到List中
		if (objCargoinfoColumns != null)
			listCargoInfo.add(objCargoinfoColumns);
		// 客户运单号转为大写
		if (!StringUtility.isNull(waybillforpredict.getCwcw_customerewbcode())) {
			waybillforpredict.setCwcw_customerewbcode(waybillforpredict.getCwcw_customerewbcode().toUpperCase());
		}
		if (!StringUtility.isNull(waybillforpredict.getCwcw_serverewbcode())) {
			waybillforpredict.setCwcw_serverewbcode(waybillforpredict.getCwcw_serverewbcode().toUpperCase());
		}
		objWFPCEX.setListCargoInfo(listCargoInfo);
		objWFPCEX.setWaybillforpredict(waybillforpredict);

		
		return objWFPCEX;
	}
	
	/**
	 * 保存映射模板
	 * @param objColumns
	 * @param objPOTVList
	 * @throws Exception
	 */
	public void save(PredicttemplateColumns objColumns,
			List objPOTVList) throws Exception {
		SavePredictTrans objTrans = new SavePredictTrans();
		objTrans.setParam(objColumns, objPOTVList);
		objTrans.execute();
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
	}	
	
}
