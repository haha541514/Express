package kyle.leis.fs.authoritys.user.dax;

import java.text.SimpleDateFormat;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.da.UserCondition;
import kyle.leis.fs.authoritys.user.da.UserQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCityDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCorporationstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCustomersuppliertypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDepartmentDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFunctionDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPositionDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiStateDC;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiOperatorstatus;
import net.sf.hibernate.Session;

public class UserDemand {
	
	public static void setUserColumns(TdiOperator objTdiOperator,
			UserColumns objUserColumns,
			String strOperId,
			Session objSession) throws Exception
	{
		objTdiOperator.setOpTelephone(objUserColumns.getOpoptelephone());
		objTdiOperator.setOpAddress(objUserColumns.getOpopaddress());
		objTdiOperator.setOpCode(objUserColumns.getOpopcode());
		objTdiOperator.setOpEmail(objUserColumns.getOpopemail());
		objTdiOperator.setOpEname(objUserColumns.getOpopename());
		objTdiOperator.setOpIdModifier(Long.parseLong(strOperId));
		objTdiOperator.setOpIdnumber(objUserColumns.getOpopidnumber());
		objTdiOperator.setOpMobile(objUserColumns.getOpopmobile());
		objTdiOperator.setOpModifydate(DateFormatUtility.getSysdate());
		objTdiOperator.setOpName(objUserColumns.getOpopname());
		objTdiOperator.setOpPassword(objUserColumns.getWord());
		objTdiOperator.setOpSex(objUserColumns.getOpopsex());
		objTdiOperator.setOpSname(objUserColumns.getOpopsname());
		objTdiOperator.setOpTelephone(objUserColumns.getOpoptelephone());
		
		objTdiOperator.setTdiEnterpriseelement(TdiEnterpriseelementDC.loadByKey(objUserColumns.getEeeecode()));
		objTdiOperator.setTdiFunction(TdiFunctionDC.loadByKey(objUserColumns.getFcfccode()));
		objTdiOperator.setTdiPosition(TdiPositionDC.loadByKey(objUserColumns.getPspscode()));
		objTdiOperator.setTdiDepartment(TdiDepartmentDC.loadByKey(objUserColumns.getDpdpcode()));
		objTdiOperator.setTdiState(TdiStateDC.loadByKey(objUserColumns.getStstcode()));
		objTdiOperator.setTdiCity(TdiCityDC.loadByKey(objUserColumns.getCtctcode()));
		if(!StringUtility.isNull(objUserColumns.getCococode()))
			objTdiOperator.setTcoCorporation((TcoCorporation)objSession.load(TcoCorporation.class,objUserColumns.getCococode()));
		if(!StringUtility.isNull(objUserColumns.getOpopconfirmdate()))
			objTdiOperator.setOpConfirmdate(new SimpleDateFormat("yyyy-MM-dd").parse(objUserColumns.getOpopconfirmdate()));
		else
			objTdiOperator.setOpConfirmdate(DateFormatUtility.getSysdate());
		if(!StringUtility.isNull(objUserColumns.getOpopdimissiondate()))
			objTdiOperator.setOpDimissiondate(new SimpleDateFormat("yyyy-MM-dd").parse(objUserColumns.getOpopdimissiondate()));
		if(!StringUtility.isNull(objUserColumns.getOsoscode()))
			objTdiOperator.setTdiOperatorstatus((TdiOperatorstatus)objSession.load(TdiOperatorstatus.class, objUserColumns.getOsoscode()));
		
		objTdiOperator.setOpMsnname(objUserColumns.getOpopmsnname());
		objTdiOperator.setOpQqnumber(objUserColumns.getOpopqqnumber());
		objTdiOperator.setOpFaxnumber(objUserColumns.getOpopfaxnumber());
		objTdiOperator.setOpIssuecontactpersonsign(objUserColumns.getOpopissuecontactpersonsign());
	}
	
	public static void setCorporationByUserColumns(TcoCorporation objTcoCorporation, UserColumns objUserColumns,String strOperId,Session objSession) throws Exception
	{
		//��˾��ţ��û����
		objTcoCorporation.setCoLabelcode("Lab-"+objUserColumns.getOpopcode());
		//�����˺��޸���Ĭ�ƶ���ϵͳ����Ա(0)
		TdiOperator objTdiOperator = (TdiOperator)TdiOperatorDC.loadByKey(strOperId);
		objTcoCorporation.setCoCreatedate(DateFormatUtility.getSysdate());
		objTcoCorporation.setTdiOperatorByCoOpIdCreate(objTdiOperator);
		objTcoCorporation.setCoModifydate(DateFormatUtility.getSysdate());
		objTcoCorporation.setTdiOperatorByCoOpIdModify(objTdiOperator);
		//��˾���ƣ��û����+�û�����
		objTcoCorporation.setCoName(objUserColumns.getOpopcode()+"-"+objUserColumns.getOpopname());
		//��˾��ƣ��û����+�û����
		objTcoCorporation.setCoSname(objUserColumns.getOpopcode()+"-"+objUserColumns.getOpopsname());
		//��˾Ӣ�ļ�ƣ��û����+�û�Ӣ������
		objTcoCorporation.setCoSename("Ens-"+objUserColumns.getOpopcode());
		
		//��˾Ӣ�����ƣ��û����+�û�Ӣ������(���Է���������Ϊ�գ���ӳ���ļ�����Ϊ��)
		objTcoCorporation.setCoEname("Enn-"+objUserColumns.getOpopcode());
		
		objTcoCorporation.setTdiCorporationstatus(TdiCorporationstatusDC.loadByKey("N"));
		objTcoCorporation.setTdiCustomersuppliertype(TdiCustomersuppliertypeDC.loadByKey("C"));
		objTcoCorporation.setTdiEnterpriseelement(TdiEnterpriseelementDC.loadByKey("1"));
		
	}
	
	public static List query(UserCondition objUserCondition) throws Exception
	{
		UserQuery objUserQuery  = new UserQuery();
		objUserQuery.setCondition(objUserCondition);
		return objUserQuery.getResults();
	}
	
	public static UserColumns queryByOperCode(String strOpcode) throws Exception
	{
		UserCondition objUserCondition = new UserCondition();
		objUserCondition.setOpcode(strOpcode);
		return (UserColumns)query(objUserCondition).get(0);
	}
	/**
	 * ͨ��΢�źŲ�ѯ
	 * @param openID
	 * @return
	 * @throws Exception
	 */
	public static UserColumns queryByOpenID(String openID) throws Exception{
		UserQueryEX queryEX = new UserQueryEX(openID);
		List<?> result = queryEX.getResults();
		if (!result.isEmpty()) {
			return (UserColumns) result.get(0);
		}
		return null;
	}
	
}
