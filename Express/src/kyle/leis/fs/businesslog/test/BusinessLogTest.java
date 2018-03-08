package kyle.leis.fs.businesslog.test;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.fs.businesslog.da.BusinesslogColumns;
import kyle.leis.fs.businesslog.da.BusinesslogCondition;
import kyle.leis.fs.businesslog.dax.BusinesslogDemand;
import kyle.leis.fs.businesslog.sv.BusinesslogService;

public class BusinesslogTest {
	
	private static BusinesslogService s_objBusinesslogService = new BusinesslogService();
	
	public static void main(String[] args) {
		try {
			//测试查询方法
//			queryAll();
			
			//测试查询方法(Service)
//			querySv();
			
			//测试新增方法
			saveTest();
//			System.out.println(DateFormatUtility.getSysdate());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}

	public static void queryAll() throws Exception {
//		String str = "bl001";
		String blId = "";
		BusinesslogCondition objBusinessCondition = new BusinesslogCondition();
//		objBusinessCondition.setBlbusinesscode(str);
		objBusinessCondition.setBlid(blId);
		List blList = BusinesslogDemand.queryCondition(objBusinessCondition);
		for (int i = 0; i < blList.size(); i++) {
			BusinesslogColumns objColumns = (BusinesslogColumns) blList.get(i);
			System.out.println("objColumns.getOpopname()为：               "+objColumns.getOpopname());
			System.out.println("objColumns.getAkakname()为：                   "+objColumns.getAkakname());
			System.out.println("objColumns.getBlkblkname()为：                   "+objColumns.getBlkblkname());
			System.out.println(objColumns.getAkakcode());
			System.out.println(objColumns.getBlkblkcode());
			System.out.println(objColumns.getBlogblbusinesscode());
			System.out.println(objColumns.getBlogblcontent());
			System.out.println(objColumns.getBlogblcreatedate());
			System.out.println(objColumns.getBlogblid());
			System.out.println(objColumns.getOpopid());
		}
	}

	public static void querySv() throws Exception
	{
		String parm = "~`AD~`2010-9-21 00:00:00~`2010-9-24 23:59:59~`OP~`OP~`~@~#";
		Decoder opjPD = new Decoder(parm);
		System.out.println(s_objBusinesslogService.queryCondition(opjPD));
	}
	
	public static void saveTest() throws Exception
	{
		/*Hibernate: select tdiactionk0_.AK_CODE as AK_CODE0_, tdiactionk0_.AK_NAME as AK_NAME0_, tdiactionk0_.AK_ENAME as AK_ENAME0_ from T_DI_ACTIONKIND tdiactionk0_ where tdiactionk0_.AK_CODE=?
		Hibernate: select tdibusines0_.BLK_CODE as BLK_CODE0_, tdibusines0_.BLK_NAME as BLK_NAME0_, tdibusines0_.BLK_ENAME as BLK_ENAME0_ from EXAD.T_DI_BUSINESSLOGKIND tdibusines0_ where tdibusines0_.BLK_CODE=?
		Hibernate: select tfsbusines0_.BLK_CODE as BLK_CODE__, tfsbusines0_.BL_ID as BL_ID__, tfsbusines0_.BL_ID as BL_ID13_, tfsbusines0_.OP_ID_CREATE as OP_ID_CR2_13_, tfsbusines0_.BLK_CODE as BLK_CODE13_, tfsbusines0_.AK_CODE as AK_CODE13_, tfsbusines0_.BL_CREATEDATE as BL_CREAT5_13_, tfsbusines0_.BL_BUSINESSCODE as BL_BUSIN6_13_, tfsbusines0_.BL_CONTENT as BL_CONTENT13_, tdioperato1_.OP_ID as OP_ID0_, tdioperato1_.OP_CODE as OP_CODE0_, tdioperato1_.OP_NAME as OP_NAME0_, tdioperato1_.OP_SEX as OP_SEX0_, tdioperato1_.OP_PASSWORD as OP_PASSW5_0_, tdioperato1_.OP_ENAME as OP_ENAME0_, tdioperato1_.OP_SNAME as OP_SNAME0_, tdioperato1_.OP_IDNUMBER as OP_IDNUM8_0_, tdioperato1_.OP_EMAIL as OP_EMAIL0_, tdioperato1_.OP_ID_CREATOR as OP_ID_C10_0_, tdioperato1_.OP_CREATEDATE as OP_CREA11_0_, tdioperato1_.OP_ID_MODIFIER as OP_ID_M12_0_, tdioperato1_.OP_MODIFYDATE as OP_MODI13_0_, tdioperato1_.OP_ADDRESS as OP_ADDRESS0_, tdioperato1_.OP_TELEPHONE as OP_TELE15_0_, tdioperato1_.OP_MOBILE as OP_MOBILE0_, tdioperato1_.OP_CONFIRMDATE as OP_CONF17_0_, tdioperato1_.PS_CODE as PS_CODE0_, tdioperato1_.EE_CODE as EE_CODE0_, tdioperato1_.FC_CODE as FC_CODE0_, tdioperato1_.DP_CODE as DP_CODE0_, tdipositio2_.PS_CODE as PS_CODE1_, tdipositio2_.PS_NAME as PS_NAME1_, tdipositio2_.PS_ENAME as PS_ENAME1_, tdienterpr3_.EE_CODE as EE_CODE2_, tdienterpr3_.EE_LEVEL as EE_LEVEL2_, tdienterpr3_.EE_STRUCTURECODE as EE_STRUC3_2_, tdienterpr3_.EE_NAME as EE_NAME2_, tdienterpr3_.EE_ENAME as EE_ENAME2_, tdienterpr3_.EE_SNAME as EE_SNAME2_, tdienterpr3_.EE_ESNAME as EE_ESNAME2_, tdienterpr3_.EE_ADDRESS as EE_ADDRESS2_, tdienterpr3_.EE_EADDRESS as EE_EADDR9_2_, tdienterpr3_.EE_POSTCODE as EE_POST10_2_, tdienterpr3_.EE_EMAIL as EE_EMAIL2_, tdienterpr3_.EE_TELEPHONE as EE_TELE12_2_, tdienterpr3_.EE_FAX as EE_FAX2_, tdienterpr3_.EE_OP_ID_CREATOR as EE_OP_I14_2_, tdienterpr3_.EE_CREATEDATE as EE_CREA15_2_, tdienterpr3_.EE_OP_ID_MODIFIER as EE_OP_I16_2_, tdienterpr3_.EE_MODIFYDATE as EE_MODI17_2_, tdienterpr3_.DT_CODE as DT_CODE2_, tdienterpr3_.RG_CODE as RG_CODE2_, tdienterpr3_.EEK_CODE as EEK_CODE2_, tdidistric4_.DT_CODE as DT_CODE3_, tdidistric4_.DT_HUBCODE as DT_HUBCODE3_, tdidistric4_.DT_NAME as DT_NAME3_, tdidistric4_.DT_ENAME as DT_ENAME3_, tdidistric4_.DT_STATECODE as DT_STATE5_3_, tdidistric4_.DT_STATENAME as DT_STATE6_3_, tdidistric4_.DT_GRADE as DT_GRADE3_, tdidistric4_.DT_STARTPOSTCODE as DT_START8_3_, tdidistric4_.DT_ENDPOSTCODE as DT_ENDPO9_3_, tdidistric4_.DT_OP_CODE_CREATOR as DT_OP_C10_3_, tdidistric4_.DT_CREATEDATE as DT_CREA11_3_, tdidistric4_.DT_OP_CODE_MODIFIER as DT_OP_C12_3_, tdidistric4_.DT_MODIFYDATE as DT_MODI13_3_, tdidistric4_.DT_REMARK as DT_REMARK3_, tdidistric4_.DT_STARTCITYSIGN as DT_STAR15_3_, tdidistric4_.DK_CODE as DK_CODE3_, tdidistric4_.DT_COUNTCODE as DT_COUN17_3_, tdidistric5_.DK_CODE as DK_CODE4_, tdidistric5_.DK_NAME as DK_NAME4_, tdidistric5_.DK_ENAME as DK_ENAME4_, tdidistric5_.DK_REMARK as DK_REMARK4_, tdibranch6_.EE_CODE as EE_CODE5_, tdibranch6_.BR_OP_ID_MANAGER as BR_OP_ID2_5_, tdibranch6_.BR_OP_ID_CUSTOMERSERVICE as BR_OP_ID3_5_, tdibranch6_.BR_OP_ID_DUNNER as BR_OP_ID4_5_, tdibranch6_.BR_OP_ID_SALER as BR_OP_ID5_5_, tdidistrib7_.EE_CODE as EE_CODE6_, tdidistrib7_.DT_CODE_BILLING as DT_CODE_2_6_, tdidistrib7_.BR_CODE as BR_CODE6_, tdibranch8_.EE_CODE as EE_CODE7_, tdibranch8_.BR_OP_ID_MANAGER as BR_OP_ID2_7_, tdibranch8_.BR_OP_ID_CUSTOMERSERVICE as BR_OP_ID3_7_, tdibranch8_.BR_OP_ID_DUNNER as BR_OP_ID4_7_, tdibranch8_.BR_OP_ID_SALER as BR_OP_ID5_7_, tdiregion9_.RG_CODE as RG_CODE8_, tdiregion9_.RG_NAME as RG_NAME8_, tdiregion9_.RG_ENAME as RG_ENAME8_, tdienterpr10_.EEK_CODE as EEK_CODE9_, tdienterpr10_.EEK_NAME as EEK_NAME9_, tdienterpr10_.EEK_ENAME as EEK_ENAME9_, tdifunctio11_.FC_CODE as FC_CODE10_, tdifunctio11_.FC_NAME as FC_NAME10_, tdifunctio11_.FC_ENAME as FC_ENAME10_, tdidepartm12_.DP_CODE as DP_CODE11_, tdidepartm12_.DP_NAME as DP_NAME11_, tdidepartm12_.DP_ENAME as DP_ENAME11_, tdiactionk13_.AK_CODE as AK_CODE12_, tdiactionk13_.AK_NAME as AK_NAME12_, tdiactionk13_.AK_ENAME as AK_ENAME12_ from EXAD.T_FS_BUSINESSLOG tfsbusines0_ left outer join T_DI_OPERATOR tdioperato1_ on tfsbusines0_.OP_ID_CREATE=tdioperato1_.OP_ID left outer join T_DI_POSITION tdipositio2_ on tdioperato1_.PS_CODE=tdipositio2_.PS_CODE left outer join T_DI_ENTERPRISEELEMENT tdienterpr3_ on tdioperato1_.EE_CODE=tdienterpr3_.EE_CODE left outer join T_DI_DISTRICT tdidistric4_ on tdienterpr3_.DT_CODE=tdidistric4_.DT_CODE left outer join T_DI_DISTRICTKIND tdidistric5_ on tdidistric4_.DK_CODE=tdidistric5_.DK_CODE left outer join T_DI_BRANCH tdibranch6_ on tdienterpr3_.EE_CODE=tdibranch6_.EE_CODE left outer join T_DI_DISTRIBUTIONCENTER tdidistrib7_ on tdienterpr3_.EE_CODE=tdidistrib7_.EE_CODE left outer join T_DI_BRANCH tdibranch8_ on tdidistrib7_.BR_CODE=tdibranch8_.EE_CODE left outer join T_DI_REGION tdiregion9_ on tdienterpr3_.RG_CODE=tdiregion9_.RG_CODE left outer join T_DI_ENTERPRISEELEMENTKIND tdienterpr10_ on tdienterpr3_.EEK_CODE=tdienterpr10_.EEK_CODE left outer join T_DI_FUNCTION tdifunctio11_ on tdioperato1_.FC_CODE=tdifunctio11_.FC_CODE left outer join T_DI_DEPARTMENT tdidepartm12_ on tdioperato1_.DP_CODE=tdidepartm12_.DP_CODE left outer join T_DI_ACTIONKIND tdiactionk13_ on tfsbusines0_.AK_CODE=tdiactionk13_.AK_CODE where tfsbusines0_.BLK_CODE=?
		Hibernate: select tdidistric0_.DT_CODE as DT_CODE1_, tdidistric0_.DT_HUBCODE as DT_HUBCODE1_, tdidistric0_.DT_NAME as DT_NAME1_, tdidistric0_.DT_ENAME as DT_ENAME1_, tdidistric0_.DT_STATECODE as DT_STATE5_1_, tdidistric0_.DT_STATENAME as DT_STATE6_1_, tdidistric0_.DT_GRADE as DT_GRADE1_, tdidistric0_.DT_STARTPOSTCODE as DT_START8_1_, tdidistric0_.DT_ENDPOSTCODE as DT_ENDPO9_1_, tdidistric0_.DT_OP_CODE_CREATOR as DT_OP_C10_1_, tdidistric0_.DT_CREATEDATE as DT_CREA11_1_, tdidistric0_.DT_OP_CODE_MODIFIER as DT_OP_C12_1_, tdidistric0_.DT_MODIFYDATE as DT_MODI13_1_, tdidistric0_.DT_REMARK as DT_REMARK1_, tdidistric0_.DT_STARTCITYSIGN as DT_STAR15_1_, tdidistric0_.DK_CODE as DK_CODE1_, tdidistric0_.DT_COUNTCODE as DT_COUN17_1_, tdidistric1_.DK_CODE as DK_CODE0_, tdidistric1_.DK_NAME as DK_NAME0_, tdidistric1_.DK_ENAME as DK_ENAME0_, tdidistric1_.DK_REMARK as DK_REMARK0_ from T_DI_DISTRICT tdidistric0_ left outer join T_DI_DISTRICTKIND tdidistric1_ on tdidistric0_.DK_CODE=tdidistric1_.DK_CODE where tdidistric0_.DT_CODE=?
		Hibernate: select S_BL_CODE.nextval from dual
		Hibernate: insert into EXAD.T_FS_BUSINESSLOG (OP_ID_CREATE, BLK_CODE, AK_CODE, BL_CREATEDATE, BL_BUSINESSCODE, BL_CONTENT, BL_ID) values (?, ?, ?, ?, ?, ?, ?)
		Hibernate: select tfsbusines0_.BL_ID as x0_0_, tfsbusines0_.BL_CREATEDATE as x1_0_, tfsbusines0_.BL_BUSINESSCODE as x2_0_, tfsbusines0_.BL_CONTENT as x3_0_, tdioperato1_.OP_ID as x4_0_, tdibusines2_.BLK_CODE as x5_0_, tdiactionk3_.AK_CODE as x6_0_ from EXAD.T_FS_BUSINESSLOG tfsbusines0_ inner join T_DI_OPERATOR tdioperato1_ on tfsbusines0_.OP_ID_CREATE=tdioperato1_.OP_ID inner join EXAD.T_DI_BUSINESSLOGKIND tdibusines2_ on tfsbusines0_.BLK_CODE=tdibusines2_.BLK_CODE inner join T_DI_ACTIONKIND tdiactionk3_ on tfsbusines0_.AK_CODE=tdiactionk3_.AK_CODE where (tfsbusines0_.BL_ID=695 )
		695~`2010-02-05 16:49:31~`578~`发件人信息为空~`1~`OP~`AD~`~@~#
		*/
		
		
//		String str = "OP~`2010-2-5 15:10:47~`578~`发件人信息~`1~`~`AD~`~@~#"; 错误格式
		String str = "~`2010-10-7 14:47:26~`578~`测试时间~`1~`~`OP~`~`AD~`~@~#";
		
		Decoder decode = new Decoder(str);
		System.out.println(s_objBusinesslogService.save(decode));
	}
}
