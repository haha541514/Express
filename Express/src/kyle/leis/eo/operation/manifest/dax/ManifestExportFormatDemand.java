package kyle.leis.eo.operation.manifest.dax;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillformanifestCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillfortarazzColumns;
import kyle.leis.eo.operation.manifest.da.HousewaybillformanifestformatQuery;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnColumns;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnCondition;
import kyle.leis.es.businessrule.manifest.dax.ManifestCustomDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class ManifestExportFormatDemand {
	//根据不同的导出清单格式查询出数据
	public List queryForMEF(String mefCode,HousewaybillformanifestCondition objHWBFMCondition) throws Exception{
		//得到导出清单列
		ManifestCustomDemand mcDemand = new ManifestCustomDemand();
		ManifestefcolumnCondition objmefcCondition = new ManifestefcolumnCondition();
		objmefcCondition.setMefcode(mefCode);
		List mefcList = mcDemand.queryManifestefcolumn(objmefcCondition);
		List hwList = new ArrayList();
		if(mefcList != null){
			String strSelectClause="";
			for(int i=0;i<mefcList.size();i++){
				ManifestefcolumnColumns mefcColumns = (ManifestefcolumnColumns)mefcList.get(i);
				if(!StringUtility.isNull(mefcColumns.getMscmscsqlcolumnname())){
					strSelectClause=strSelectClause+","+mefcColumns.getMscmscsqlcolumnname();
				}else if(!StringUtility.isNull(mefcColumns.getMefcmefcfixedcolumnformula())){
					strSelectClause=strSelectClause+","+mefcColumns.getMefcmefcfixedcolumnformula();
				}
			}			
			strSelectClause=strSelectClause.substring(1, strSelectClause.length());
			HousewaybillformanifestformatQuery hwformefq = new HousewaybillformanifestformatQuery(strSelectClause);
			hwformefq.setCondition(objHWBFMCondition);
			hwList=hwformefq.getResults();
			if (hwList.size() > 0 && mefCode.equals("81")) {
				// 转换Tarazz的州名
				for (int i = 0; i < hwList.size(); i++) {
					HousewaybillfortarazzColumns objHFTColumns = (HousewaybillfortarazzColumns)hwList.get(i);
					String strCityname = objHFTColumns.getReceivercity();
					String strPostcode = objHFTColumns.getReceiverpostalcode();
					strPostcode = StringUtility.removePrefix(strPostcode, '0');
					
					String strStateCode = DistrictDemand.getTarazzStateCode(strCityname, strPostcode);
					objHFTColumns.setReceiverpostalcode(strPostcode);
					objHFTColumns.setReceivercitycode(strStateCode);
				}
			}
		}
		return hwList;
	}
}
