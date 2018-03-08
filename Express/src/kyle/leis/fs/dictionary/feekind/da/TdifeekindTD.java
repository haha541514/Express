package  kyle.leis.fs.dictionary.feekind.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * 表定义类
 */
public class TdifeekindTD extends TableDefinition {
	private final static String TableName = "t_di_feekind";

	private final static int[] PKFieldOrder = { 0 };

	// LongField代表LONG型字段为第几个字段，-1表示表中无LONG型字段
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "FK_CODE", "SS_CODE", "FK_NAME", "FK_ENAME", "FK_REFERENCEPOSITION", "FK_MANUALMODIFYSIGN", "FK_BASESIGN", "FK_REMARK", "EST_CODE", "FK_ACCOUNTINGONLYSIGN", "FK_DECLAREVALUESIGN" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, CHAR, CHAR, VARCHAR2, VARCHAR2, CHAR, CHAR };

	private final static boolean FieldNullable[] = { false, false, false, false, false, false, false, true, true, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static TdifeekindTD s_objTdifeekindTD;

	private TdifeekindTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static TdifeekindTD getDatabaseInstance() {
	if ( s_objTdifeekindTD == null )
		s_objTdifeekindTD = new TdifeekindTD();
	return s_objTdifeekindTD;
  }
}
