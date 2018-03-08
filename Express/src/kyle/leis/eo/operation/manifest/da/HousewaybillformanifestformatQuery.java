package kyle.leis.eo.operation.manifest.da;

import kyle.leis.eo.operation.housewaybill.da.HousewaybillformanifestQuery;

public class HousewaybillformanifestformatQuery extends HousewaybillformanifestQuery {		
	public HousewaybillformanifestformatQuery(String strSelectClause){
		super.m_strSelectClause="SELECT "+strSelectClause+" FROM T_OP_HOUSEWAYBILL hw,T_OP_COREWAYBILL cw,T_DI_COREWAYBILLSTATUS cws,T_DI_ENTERPRISEELEMENT ee,T_DI_PRODUCTKIND pk,T_DI_DISTRICT ddt,T_DI_DISTRICT cddt, T_DI_DISTRICT sdt, T_DI_DISTRICT odt,T_DI_DISTRICT shdt,T_DI_PAYMENTMODE pm,T_CHN_CHANNEL schn,T_CHN_CHANNEL cchn, T_DI_CARGOTYPE ct,T_CO_CORPORATION sco, T_CO_CORPORATION cco, T_CO_CUSTOMER cm,t_co_financialstatistics cofs,T_DI_CUSTOMERTYPE cct,T_DI_OPERATOR csop,T_DI_OPERATOR ssop,T_DI_OPERATOR siop,T_DI_OPERATOR soop,T_DI_OPERATOR rcop,T_OP_BATCHWAYBILL abw,T_OP_BATCHWAYBILL dbw,T_DI_ISSUEHOLDSTATUS ihs";
	}
}
