package kyle.leis.fs.dictionary.district.tp;





import java.text.SimpleDateFormat;
import java.util.Date;

import kyle.common.util.jlang.StringUtility;
import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;


import kyle.leis.fs.dictionary.district.da.DicdistrictColumns;

import kyle.leis.fs.dictionary.district.da.DistrictSeqQuery;
import kyle.leis.hi.TdiDistrict;

public class SaveDistrict extends AbstractTransaction{
	private String str;
	private String dtcode;
	private DicdistrictColumns objColumns;
	public void setParam(DicdistrictColumns columns,String dt_code){
		this.objColumns = columns;
		this.dtcode = dt_code;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		System.out.println(objColumns.getDddtcode());
		//hubcode长度不能大于6
		if(objColumns == null  || (objColumns.getDidthubcode()).length() >6)
		return ;
		if(StringUtility.isNull(dtcode)){
			//新增,dtcode ="", isNUll 为true
			TdiDistrict objTdiDistrict = new TdiDistrict();
			DistrictSeqQuery objSeqQuery = new DistrictSeqQuery();
			objColumns.setDidtcode(objSeqQuery.getNewSequencecode());
	
			if(StringUtility.isNull(objColumns.getDddtcode())){
				//为空，新增国家
				Date currentdate = new Date();	
				String saveSql = "insert into t_di_district values ('"
				+objColumns.getDidtcode()+ "','"
				+objColumns.getDidtcode()+"','"
				+objColumns.getDkdkcode()+"','"
				+objColumns.getDidthubcode()+"','"
				+objColumns.getDidtname()+"','"
				+objColumns.getDidtename()+"','"
				+objColumns.getDidtstatecode()+"','"
				+objColumns.getDidtstatename()+"','"
				+objColumns.getDidtgrade()+"','"
				+objColumns.getDidtstartpostcode()+"','"
				+objColumns.getDidtendpostcode()+"','"
				+objColumns.getDidtopcodecreator()+"',sysdate,'"
				+objColumns.getDidtopcodemodifier()+"',sysdate,'"
				+objColumns.getDidtremark()+"','"
				+objColumns.getDidtstartcitysign()+"','"
				+objColumns.getDidtelevatedrisksign()+"','"
				+objColumns.getDidtrestrictedsign()+"')";	
				execute(objSession,saveSql);
				
			}else{
				//不为空,新增城市
				//DistrictDemand.setAddDistrict(objTdiDistrict,objColumns,objSession,"SAVE");
				//objSession.save(objTdiDistrict);	
				Date currentdate = new Date();
					
				String saveSql = "insert into t_di_district values ('"
				+objColumns.getDidtcode()+ "','"
				+objColumns.getDddtcode()+"','"
				+objColumns.getDkdkcode()+"','"
				+objColumns.getDidthubcode()+"','"
				+objColumns.getDidtname()+"','"
				+objColumns.getDidtename()+"','"
				+objColumns.getDidtstatecode()+"','"
				+objColumns.getDidtstatename()+"','"
				+objColumns.getDidtgrade()+"','"
				+objColumns.getDidtstartpostcode()+"','"
				+objColumns.getDidtendpostcode()+"','"
				+objColumns.getDidtopcodecreator()+"',sysdate,'"
				+objColumns.getDidtopcodemodifier()+"',sysdate,'"
				+objColumns.getDidtremark()+"','"
				+objColumns.getDidtstartcitysign()+"','"
				+objColumns.getDidtelevatedrisksign()+"','"
				+objColumns.getDidtrestrictedsign()+"')";	
				execute(objSession,saveSql);
				

			}	
		}else{
			//修改
			String updateSql = "update t_di_district t set " 
				+ " t.DT_COUNTCODE  = '" + objColumns.getDddtcode()+ "'," 
				+ " t.DK_CODE  = '" + objColumns.getDkdkcode()+ "'," 
				+ " t.DT_HUBCODE = '" + objColumns.getDidthubcode()+ "',"
				+ " t.DT_NAME  = '" + objColumns.getDidtname()+ "',"
				+ " t.DT_ENAME  = '" + objColumns.getDidtename()+ "',"
				+ " t.DT_STATECODE  = '" + objColumns.getDidtstatecode()+ "',"
				+ " t.DT_STATENAME  = '" + objColumns.getDidtstatename()+ "',"
				+ " t.DT_GRADE   = '" + objColumns.getDidtgrade()+ "',"
				+ " t.DT_STARTPOSTCODE   = '" + objColumns.getDidtstartpostcode()+ "',"
				+ " t.DT_ENDPOSTCODE   = '" + objColumns.getDidtendpostcode()+ "',"
				//+ " t.DT_OP_CODE_CREATOR   = '" + getDate.getDidtopcodecreator()+ "',"
				//+ " t.DT_CREATEDATE  = '" + createDate+ "',"
				+ " t.DT_OP_CODE_MODIFIER  = '" + objColumns.getDidtopcodemodifier()+ "',"
				+ " t.DT_MODIFYDATE   = " + "sysdate,"
				+ " t.DT_REMARK   = '" + objColumns.getDidtremark()+ "',"
				+ " t.DT_STARTCITYSIGN   = '" + objColumns.getDidtstartcitysign()+ "',"
				+ " t.DT_ELEVATEDRISKSIGN   = '" + objColumns.getDidtelevatedrisksign()+ "',"
				+ " t.DT_RESTRICTEDSIGN   = '" + objColumns.getDidtrestrictedsign()+ "'" 
				+ " where t.DT_CODE = '" + objColumns.getDidtcode() + "'";
				execute(objSession, updateSql);

			
		}
	
	}

	
}
