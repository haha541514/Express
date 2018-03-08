package  kyle.leis.eo.operation.cargoinfo.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * ������
 */
public class TophwbcargoinfoTD extends TableDefinition {
	private final static String TableName = "T_OP_HWBCARGOINFO";

	private final static int[] PKFieldOrder = { 0, 1 };

	// LongField����LONG���ֶ�Ϊ�ڼ����ֶΣ�-1��ʾ������LONG���ֶ�
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "CI_ID", "CW_CODE", "CI_NAME", "CI_ENAME", "CI_PIECES", "CI_UNITPRICE", "CI_TOTALPRICE", "CI_HSCODE", "CK_CODE", "CI_ATTACHEINFO", "CI_REMARK", "CI_WEIGHT" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { NUMBER, NUMBER, VARCHAR2, VARCHAR2, NUMBER, NUMBER, NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER };

	private final static boolean FieldNullable[] = { false, false, true, false, false, false, true, true, true, true, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static TophwbcargoinfoTD s_objTophwbcargoinfoTD;

	private TophwbcargoinfoTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static TophwbcargoinfoTD getDatabaseInstance() {
	if ( s_objTophwbcargoinfoTD == null )
		s_objTophwbcargoinfoTD = new TophwbcargoinfoTD();
	return s_objTophwbcargoinfoTD;
  }
}
