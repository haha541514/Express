package kyle.leis.eo.operation.predictwaybill.dax;

import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillQuery;

public class PredictwaybillQueryEX extends PredictwaybillQuery {
	public PredictwaybillQueryEX(String strTopcocode) {
		m_strSelectClause = "SELECT pwb.PWB_Code,pwb.PWB_CreateDate,pwb.PWB_ModifyDate,pwb.PWB_ConsigneeName,pwb.PWB_ConsigneeTel,pwb.PWB_ConsigneeAddress1,pwb.PWB_ConsigneeAddress2,pwb.PWB_ConsigneeCity,pwb.PWB_ConsigneeState,pwb.PWB_ConsigneePostcode,pwb.PWB_CargoEname,pwb.PWB_CargoPieces,pwb.PWB_CargoAmount,pwb.PWB_TransactionID,pwb.PWB_OrderID,pwb.PWB_Chargeweight,pwb.PWB_CustomRemark,pwbs.PWBS_Code,pwbs.PWBS_Name,pk.PK_Code,pk.PK_SName,co.CO_Code,co.CO_LabelCode,cop.OP_ID,cop.OP_Name,mop.OP_ID,mop.OP_Name,chn.chn_code,chn.CHN_SName,pwb.PWB_ServerEWBCode,dt.DT_Code,dt.DT_Name,pwb.cw_code,pwb.PWB_DeclareDate,pwb.PWB_PrintDate,pop.OP_ID,pop.OP_Name,dop.OP_ID,dop.OP_Name,pwb.PWB_ConsigneeNameEX,pwb.PWB_ConsigneeAddressEX,pwb.PWB_ConsigneeCityEX,pwb.PWB_Buyerid,pwb.CK_Code, FUN_GET_SUBCUSTOMERNAME(co.CO_Code," + strTopcocode + ",co.CO_LabelCode) as subconame FROM T_OP_PREDICTWAYBILL pwb,T_DI_PREDICTWAYBILLSTATUS pwbs,T_DI_PRODUCTKIND pk,T_CO_CORPORATION co,T_DI_OPERATOR cop,T_DI_OPERATOR mop,T_DI_OPERATOR pop,T_DI_OPERATOR dop,T_CHN_CHANNEL chn,T_DI_DISTRICT dt";
	}
}
