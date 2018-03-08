package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * 表定义类
 */
public class TdidistrictTD extends TableDefinition {
	private final static String TableName = "t_di_district";

	private final static int[] PKFieldOrder = { 0 };

	// LongField代表LONG型字段为第几个字段，-1表示表中无LONG型字段
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "DT_CODE", "DT_COUNTCODE", "DK_CODE", "DT_HUBCODE", "DT_NAME", "DT_ENAME", "DT_STATECODE", "DT_STATENAME", "DT_GRADE", "DT_STARTPOSTCODE", "DT_ENDPOSTCODE", "DT_OP_CODE_CREATOR", "DT_CREATEDATE", "DT_OP_CODE_MODIFIER", "DT_MODIFYDATE", "DT_REMARK", "DT_STARTCITYSIGN", "DT_ELEVATEDRISKSIGN", "DT_RESTRICTEDSIGN" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, CHAR, VARCHAR2, VARCHAR2, VARCHAR2, DATE, VARCHAR2, DATE, VARCHAR2, CHAR, CHAR, CHAR };

	private final static boolean FieldNullable[] = { false, false, false, false, true, true, true, true, false, true, true, false, false, false, false, true, false, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static TdidistrictTD s_objTdidistrictTD;

	private TdidistrictTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static TdidistrictTD getDatabaseInstance() {
	if ( s_objTdidistrictTD == null )
		s_objTdidistrictTD = new TdidistrictTD();
	return s_objTdidistrictTD;
  }
}
