package  kyle.leis.eo.operation.corewaybillpieces.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * 表定义类
 */
public class TopcorewaybillpiecesTD extends TableDefinition {
	private final static String TableName = "t_op_corewaybillpieces";

	private final static int[] PKFieldOrder = { 0, 1 };

	// LongField代表LONG型字段为第几个字段，-1表示表中无LONG型字段
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "CP_ID", "CW_CODE", "CP_GROSSWEIGHT", "CP_LENGTH", "CP_WIDTH", "CP_HEIGHT", "CP_LABELCODE", "CP_BAGLABELCODE", "CP_SIBAGLABELCODE", "CP_BARCODELABELCODE", "CWS_CODE", "CP_SELFLABELCODE" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2 };

	private final static boolean FieldNullable[] = { false, false, false, false, false, false, true, true, true, true, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static TopcorewaybillpiecesTD s_objTopcorewaybillpiecesTD;

	private TopcorewaybillpiecesTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static TopcorewaybillpiecesTD getDatabaseInstance() {
	if ( s_objTopcorewaybillpiecesTD == null )
		s_objTopcorewaybillpiecesTD = new TopcorewaybillpiecesTD();
	return s_objTopcorewaybillpiecesTD;
  }
}
