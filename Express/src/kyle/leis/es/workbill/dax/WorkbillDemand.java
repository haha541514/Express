package kyle.leis.es.workbill.dax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.workbill.da.WorkbillColumns;
import kyle.leis.es.workbill.da.WorkbillCondition;
import kyle.leis.es.workbill.da.WorkbillQuery;
import kyle.leis.es.workbill.da.WorkbillactionColumns;
import kyle.leis.es.workbill.da.WorkbillactionCondition;
import kyle.leis.es.workbill.da.WorkbillactionQuery;
import kyle.leis.hi.TdiActionkind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiWorkbillkind;
import kyle.leis.hi.TdiWorkbilllevel;
import kyle.leis.hi.TdiWorkbillstatus;
import kyle.leis.hi.TesWorkbill;
import kyle.leis.hi.TesWorkbillaction;

public class WorkbillDemand {
	//查询工作单
	public List allWorkBill(WorkbillCondition objWorkbillCondition) throws Exception{
		WorkbillQuery objWorkBillQuery=new WorkbillQuery();
		objWorkBillQuery.setCondition(objWorkbillCondition);
		return objWorkBillQuery.getResults();
	}
	
	//查询工作单相对应的操作记录
	public List getWorkbillaction(String wbId) throws Exception{
		WorkbillactionCondition  objWorkbillaction=new WorkbillactionCondition();
		objWorkbillaction.setWbid(wbId);
		WorkbillactionQuery objWorkbillactionQuery=new WorkbillactionQuery();
		objWorkbillactionQuery.setCondition(objWorkbillaction);
		return objWorkbillactionQuery.getResults();
	}
	
	public TesWorkbill setWorkBill(WorkbillColumns objWorkbill) throws ParseException{
		TesWorkbill objTesWorkbill=new TesWorkbill();
		
		TdiOperator creater=new TdiOperator();
		creater.setOpId(Long.parseLong(objWorkbill.getCropid()));
		objTesWorkbill.setTdiOperatorByWbOpIdCreator(creater);
		
		TdiOperator executor=new TdiOperator();
		executor.setOpId(Long.parseLong(objWorkbill.getExopid()));
		objTesWorkbill.setTdiOperatorByWbOpIdExecutor(executor);
		
		TdiOperator modifier=new TdiOperator();
		modifier.setOpId(Long.parseLong(objWorkbill.getMoopid()));
		objTesWorkbill.setTdiOperatorByWbOpIdModifier(modifier);
		
		TdiOperator principal=new TdiOperator();
		principal.setOpId(Long.parseLong(objWorkbill.getPropid()));
		objTesWorkbill.setTdiOperatorByWbOpIdPrincipal(principal);
		
		TdiOperator surveillant=new TdiOperator();
		surveillant.setOpId(Long.parseLong(objWorkbill.getSuopid()));
		objTesWorkbill.setTdiOperatorByWbOpIdSurveillant(surveillant);
		
		TdiWorkbillkind kind=new TdiWorkbillkind();
		kind.setWbkCode(objWorkbill.getWbkwbkcode());
		objTesWorkbill.setTdiWorkbillkind(kind);
		
		TdiWorkbilllevel level=new TdiWorkbilllevel();
		level.setWblCode(objWorkbill.getWblwblcode());
		objTesWorkbill.setTdiWorkbilllevel(level);
		
		TdiWorkbillstatus status=new TdiWorkbillstatus();
		status.setWbsCode(objWorkbill.getWbswbscode());
		objTesWorkbill.setTdiWorkbillstatus(status);
		
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objTesWorkbill.setWbActualenddate(date.parse(objWorkbill.getWbwbactualenddate()));
		objTesWorkbill.setWbActualstartdate(date.parse(objWorkbill.getWbwbactualstartdate()));
		objTesWorkbill.setWbContent(objWorkbill.getWbwbcontent());
		objTesWorkbill.setWbCreatedate(date.parse(objWorkbill.getWbwbcreatedate()));
		objTesWorkbill.setWbHeading(objWorkbill.getWbwbheading());
		System.out.print(objWorkbill.getWbwbid());
		   if(!StringUtility.isNull(objWorkbill.getWbwbid())){
			   	objTesWorkbill.setWbId(Long.parseLong(objWorkbill.getWbwbid()));
		   }
		objTesWorkbill.setWbModifydate(date.parse(objWorkbill.getWbwbmodifydate()));
		objTesWorkbill.setWbPlanenddate(date.parse(objWorkbill.getWbwbplanenddate()));
		objTesWorkbill.setWbPlanstartdate(date.parse(objWorkbill.getWbwbplanstartdate()));
		
		return objTesWorkbill;
	}
	public TesWorkbillaction setWorkBillAction(WorkbillactionColumns objWorkbillaction) throws ParseException{
		TesWorkbillaction objTesWorkbillaction=new TesWorkbillaction();
		
		TdiActionkind kind=new TdiActionkind();
		kind.setAkCode(objWorkbillaction.getAkakcode());
		objTesWorkbillaction.setTdiActionkind(kind);
		
		TdiOperator operator=new TdiOperator();
		System.out.println(objWorkbillaction.getOpopid());
		operator.setOpId(Long.parseLong(objWorkbillaction.getOpopid()));
		objTesWorkbillaction.setTdiOperator(operator);
		
		TesWorkbill workbill=new TesWorkbill();
		workbill.setWbId(Long.parseLong(objWorkbillaction.getWbwbid()));
		objTesWorkbillaction.setTesWorkbill(workbill);
		
		objTesWorkbillaction.setWbaContent(objWorkbillaction.getWbawbacontent());
		objTesWorkbillaction.setWbaCreatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objWorkbillaction.getWbawbacreatedate()));
	
		return objTesWorkbillaction;
	}
}
