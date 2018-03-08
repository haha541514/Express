package  kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.table.TableDefinition;

/**
 * 表定义类
 */
public class TophousewaybillTD extends TableDefinition {
	private final static String TableName = "T_op_Housewaybill";

	private final static int[] PKFieldOrder = { 0 };

	// LongField代表LONG型字段为第几个字段，-1表示表中无LONG型字段
	private final static int LongFieldOrder = -1;

	private final static int[] LobFieldOrder = {  };

	private final static String FieldName[] = { "CW_CODE", "DT_CODE_SHIPPER", "HW_SHIPPERACCOUNT", "HW_SHIPPERNAME", "HW_SHIPPERCOMPANY", "HW_SHIPPERADDRESS1", "HW_SHIPPERADDRESS2", "HW_SHIPPERADDRESS3", "HW_SHIPPERPOSTCODE", "HW_SHIPPERTELEPHONE", "HW_SHIPPERFAX", "HW_CONSIGNEENAME", "HW_CONSIGNEECOMPANY", "HW_CONSIGNEEPOSTCODE", "HW_CONSIGNEEADDRESS1", "HW_CONSIGNEEADDRESS2", "HW_CONSIGNEEADDRESS3", "HW_CONSIGNEETELEPHONE", "HW_CONSIGNEEACCOUNT", "HW_CONSIGNEEFAX", "HW_SIGNINDATE", "HW_OP_ID_SIGNIN", "HW_OP_ID_WEIGHTING", "HW_SIGNOUTDATE", "HW_OP_ID_SIGNOUT", "HW_RECORDDATE", "HW_OP_ID_RECORD", "HW_OP_ID_PACKING", "HW_INSURANCEVALUE", "HW_INSURANCESIGN", "HW_CONSIGNEECITY", "HW_INSURANCECURRENCY", "HW_LABELPRINTTIMES", "HW_REMARK", "HW_CUSTOMSLABELPRINTSIGN", "HW_SERVEREWBKIND", "HW_SERVEREWBCHANGEDSIGN", "HW_SUBCHILDEWBKIND", "HW_CHANNELMASTERACCOUNT", "HW_LABELPRINTREMARK", "HW_CARGOPRINTTIMES", "HW_OP_ID_WEIGHTCHECK", "HW_WEIGHTCHECKKIND", "HW_WEIGHTCHECKDATE", "HW_BUYERID", "HW_TRANSACTIONID", "HW_CUSTOMERLABELPRINTDATE", "HW_ATTACHEINFOSIGN", "HW_CUSTOMERDECLAREDATE", "HW_CONSIGNEEADDRESSEX", "HW_CONSIGNEENAMEEX", "HW_OWNINPUTCWAUDITSIGN", "HW_ARREARSIGNOUT", "HW_ELECTRICMARK", "HW_SEWBCHANGEDBYWEBSIGN", "HW_BOOKINGID", "HW_PAYMENTACCOUNT", "HW_CONSIGNEECITYEX" };

	// FeildType: 1=VARCHAR2,CHAR; 2=NUMBER,DATE,OTHERS; 9=LONG.
	private final static int FieldType[] = { NUMBER, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, VARCHAR2, DATE, NUMBER, NUMBER, DATE, NUMBER, DATE, NUMBER, NUMBER, NUMBER, CHAR, VARCHAR2, VARCHAR2, NUMBER, VARCHAR2, CHAR, VARCHAR2, CHAR, VARCHAR2, VARCHAR2, VARCHAR2, NUMBER, NUMBER, CHAR, DATE, VARCHAR2, VARCHAR2, DATE, CHAR, DATE, VARCHAR2, VARCHAR2, CHAR, CHAR, VARCHAR2, CHAR, VARCHAR2, VARCHAR2, VARCHAR2 };

	private final static boolean FieldNullable[] = { false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };

	private final static int AutoFieldOrder[] = {  };

	private final static String AutoFieldValue[] = { "" };

	private static TophousewaybillTD s_objTophousewaybillTD;

	private TophousewaybillTD() {
		init(TableName, FieldName, FieldType, FieldNullable, 
		PKFieldOrder, LongFieldOrder, LobFieldOrder, 
		AutoFieldOrder, AutoFieldValue);
	}

	public static TophousewaybillTD getDatabaseInstance() {
	if ( s_objTophousewaybillTD == null )
		s_objTophousewaybillTD = new TophousewaybillTD();
	return s_objTophousewaybillTD;
  }
}
