package  kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * 表定义类
 */
public class ToppredictcargoinfoTD extends TableDefinition {
	private final static String TableName = "T_OP_PREDICTCARGOINFO";

	private final static int[] PKFieldOrder = { 0, 1 };

	// LongField代表LONG型字段为第几个字段，-1表示表中无LONG型字段
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "PCI_ID", "PWB_CODE", "CK_CODE", "PCI_NAME", "PCI_ENAME", "PCI_PIECES", "PCI_WEIGHT", "PCI_UNITPRICE", "PCI_TOTALPRICE", "PCI_HSCODE", "PCI_ATTACHEINFO", "PCI_REMARK" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, NUMBER, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2 };

	private final static boolean FieldNullable[] = { false, false, false, false, true, false, true, false, false, true, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static ToppredictcargoinfoTD s_objToppredictcargoinfoTD;

	private ToppredictcargoinfoTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static ToppredictcargoinfoTD getDatabaseInstance() {
	if ( s_objToppredictcargoinfoTD == null )
		s_objToppredictcargoinfoTD = new ToppredictcargoinfoTD();
	return s_objToppredictcargoinfoTD;
  }
}
