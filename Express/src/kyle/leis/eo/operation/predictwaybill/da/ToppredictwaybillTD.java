package  kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * 表定义类
 */
public class ToppredictwaybillTD extends TableDefinition {
	private final static String TableName = "T_OP_PREDICTWAYBILL";

	private final static int[] PKFieldOrder = { 0 };

	// LongField代表LONG型字段为第几个字段，-1表示表中无LONG型字段
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "PWB_CODE", "PK_CODE", "OP_ID_CREATOR", "OP_ID_MODIFIER", "PWBS_CODE", "CO_CODE_CUSTOMER", "CHN_CODE", "PWB_DESTINATION", "CW_CODE", "OP_ID_PRINTER", "OP_ID_DECLARER", "CK_CODE", "PWB_CONSIGNEENAME", "PWB_CONSIGNEETEL", "PWB_CONSIGNEEADDRESS1", "PWB_CONSIGNEEADDRESS2", "PWB_CONSIGNEECITY", "PWB_CONSIGNEESTATE", "PWB_CONSIGNEEPOSTCODE", "PWB_CARGOENAME", "PWB_CARGOPIECES", "PWB_CARGOAMOUNT", "PWB_TRANSACTIONID", "PWB_ORDERID", "PWB_SERVEREWBCODE", "PWB_CHARGEWEIGHT", "PWB_CUSTOMREMARK", "PWB_CONSIGNEENAMEEX", "PWB_CONSIGNEEADDRESSEX", "PWB_CONSIGNEECITYEX", "PWB_CREATEDATE", "PWB_MODIFYDATE", "PWB_DECLAREDATE", "PWB_PRINTDATE", "PWB_BUYERID", "BK_CODE", "DT_CODE", "CGK_CODE", "PAT_CODE", "PWB_DUTYPAIDSIGN", "PWB_PIECES" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { NUMBER, VARCHAR2, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, DATE, DATE, DATE, DATE, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, CHAR, NUMBER };

	private final static boolean FieldNullable[] = { false, false, false, false, false, false, true, false, true, true, true, true, false, false, false, true, false, true, false, false, false, false, true, true, true, false, true, true, true, true, false, false, true, true, true, true, true, true, true, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static ToppredictwaybillTD s_objToppredictwaybillTD;

	private ToppredictwaybillTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static ToppredictwaybillTD getDatabaseInstance() {
	if ( s_objToppredictwaybillTD == null )
		s_objToppredictwaybillTD = new ToppredictwaybillTD();
	return s_objToppredictwaybillTD;
  }
}
