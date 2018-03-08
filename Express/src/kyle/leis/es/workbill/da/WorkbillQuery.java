package kyle.leis.es.workbill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WorkbillQuery extends HGeneralQuery {
	
	public WorkbillQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.workbill.da.WorkbillColumns(wb.wbId,wb.wbCreatedate,wb.wbModifydate,wb.wbPlanstartdate,wb.wbPlanenddate,wb.wbActualstartdate,wb.wbActualenddate,wb.wbContent,wb.wbHeading,wbk.wbkName,pr.opName,ex.opName,mo.opName,su.opName,cr.opName,wbl.wblName,wbs.wbsName,pr.opId,ex.opId,mo.opId,su.opId,cr.opId,wbk.wbkCode,wbl.wblCode,wbs.wbsCode) FROM TesWorkbill as wb inner join wb.tdiOperatorByWbOpIdPrincipal as pr inner join wb.tdiOperatorByWbOpIdExecutor as ex inner join wb.tdiOperatorByWbOpIdModifier as mo inner join wb.tdiOperatorByWbOpIdSurveillant as su inner join wb.tdiOperatorByWbOpIdCreator as cr inner join wb.tdiWorkbillstatus as wbs inner join wb.tdiWorkbilllevel as wbl inner join wb.tdiWorkbillkind as wbk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "wbl.wblCode desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "su.opId='~~'", "ex.opId='~~'", "wbs.wbsCode='~~'", "wb.wbId='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSurveillant(String surveillant) {
		this.setField(0, surveillant);
	}

	public String getSurveillant() {
		return this.getField(0);
	}

	public void setExecutor(String executor) {
		this.setField(1, executor);
	}

	public String getExecutor() {
		return this.getField(1);
	}

	public void setWbscode(String wbsCode) {
		this.setField(2, wbsCode);
	}

	public String getWbscode() {
		return this.getField(2);
	}

	public void setWbid(String wbId) {
		this.setField(3, wbId);
	}

	public String getWbid() {
		return this.getField(3);
	}

}
