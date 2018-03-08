package  kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * 表定义类
 */
public class TopbatchwaybillTD extends TableDefinition {
	private final static String TableName = "t_op_batchwaybill";

	private final static int[] PKFieldOrder = { 0 };

	// LongField代表LONG型字段为第几个字段，-1表示表中无LONG型字段
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "BW_CODE", "BWS_CODE", "EE_CODE", "ADT_CODE", "BW_OP_ID_CREATE", "BW_CREATEDATE", "BW_OP_ID_MODIFY", "BW_MODIFYDATE", "BW_OP_ID_COMPLETE", "BW_COMPLETEDATE", "BW_OP_ID_AUDIT", "BW_AUDITDATE", "BW_OP_ID_APPROVE", "CO_CODE", "CHN_CODE", "BW_APPROVEDATE", "ADD_DATE", "BW_REMARK", "BW_TOTALGROSSWEIGHT", "BW_TOTALPIECES", "BW_LABELCODE", "BW_BATCHNUMBER", "BW_CONTAINERID" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, DATE, NUMBER, DATE, NUMBER, DATE, NUMBER, DATE, NUMBER, VARCHAR2, VARCHAR2, DATE, DATE, VARCHAR2, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2 };

	private final static boolean FieldNullable[] = { false, false, false, false, false, false, false, false, true, true, true, true, true, false, true, true, false, true, true, true, false, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static TopbatchwaybillTD s_objTopbatchwaybillTD;

	private TopbatchwaybillTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static TopbatchwaybillTD getDatabaseInstance() {
	if ( s_objTopbatchwaybillTD == null )
		s_objTopbatchwaybillTD = new TopbatchwaybillTD();
	return s_objTopbatchwaybillTD;
  }
}
