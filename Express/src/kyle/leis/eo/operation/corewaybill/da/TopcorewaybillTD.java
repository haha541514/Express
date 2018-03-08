package  kyle.leis.eo.operation.corewaybill.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * 表定义类
 */
public class TopcorewaybillTD extends TableDefinition {
	private final static String TableName = "t_op_corewaybill";

	private final static int[] PKFieldOrder = { 0 };

	// LongField代表LONG型字段为第几个字段，-1表示表中无LONG型字段
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "CW_CODE", "CWS_CODE", "DT_CODE_ORIGIN", "DT_CODE_DESTINATION", "CW_POSTCODE_DESTINATION", "CT_CODE", "PM_CODE", "PK_CODE", "BW_CODE_ARRIVAL", "BW_CODE_DEPARTURE", "CO_CODE_CUSTOMER", "CO_CODE_SUPPLIER", "CHN_CODE_CUSTOMER", "CHN_CODE_SUPPLIER", "CW_PIECES", "CW_GROSSWEIGHT", "CW_CHARGEWEIGHT", "CW_TRANSFERPIECES", "CW_TRANSFERGROSSWEIGHT", "CW_TRANSFERCHARGEWEIGHT", "CW_SERVERCHARGEWEIGHT", "CW_CUSTOMEREWBCODE", "CW_SERVEREWBCODE", "CW_CHANNELEWBCODE", "CW_EWBCODE", "CW_OP_ID_CREATOR", "CW_CREATEDATE", "CW_OP_ID_MODIFIER", "CW_MODIFYDATE", "CW_TRANSFERVOLUMEWEIGHT", "EE_CODE", "DT_CODE_SIGNIN", "IHS_CODE", "CW_CUSTOMERCHARGEWEIGHT", "CW_VOLUMERATE", "CW_TRANSFERVOLUMERATE", "ZNV_NAME", "FAS_CODE", "BW_CODE_WEIGHTCHECK", "CW_BILLCOUNTS", "CW_BAGCOUNTS", "BWBV_ID_ARRIVAL", "BWBV_ID__DEPARTURE", "CW_BATCHWAYBILLSIGN" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, DATE, NUMBER, DATE, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, NUMBER, NUMBER, VARCHAR2, VARCHAR2, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, CHAR };

	private final static boolean FieldNullable[] = { false, false, false, true, true, false, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, true, false, false, false, true, true, true, false, true, true, false, false, false, true, true, true, true, true, true, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static TopcorewaybillTD s_objTopcorewaybillTD;

	private TopcorewaybillTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static TopcorewaybillTD getDatabaseInstance() {
	if ( s_objTopcorewaybillTD == null )
		s_objTopcorewaybillTD = new TopcorewaybillTD();
	return s_objTopcorewaybillTD;
  }
}
