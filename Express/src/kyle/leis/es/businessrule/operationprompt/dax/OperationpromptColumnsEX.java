package kyle.leis.es.businessrule.operationprompt.dax;

import java.math.BigDecimal;
import java.util.Date;

import kyle.leis.es.businessrule.operationprompt.da.OperationpromptColumns;

public class OperationpromptColumnsEX extends OperationpromptColumns {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OperationpromptColumnsEX() {
		m_astrColumns = new String[39];
	}
	
	public OperationpromptColumnsEX(Long optBrid, 
            String optPtcode, String optOptname, 
            String optOptuniversalcustomersign, String optOptuniversalservesign, 
            String optOptuniversalachannelsign, String optOptuniversalschannelsign, 
            String optOptuniversaldeparturesign, String optOptuniversaldestinationsign, 
            String optOptuniversaltachesign, String optOptvwgwformular, 
            BigDecimal optOptchargeweightbegin, BigDecimal optOptchargeweightend, 
            BigDecimal optOptpiecegrossweightbegin, BigDecimal optOptpiecegrossweightend, 
            BigDecimal optOptdeclarevaluebegin, BigDecimal optOptdeclarevalueend, 
            String optOptcontent, String brBrname, 
            String brBrename, Date brBrstartdate, 
            Date brBrenddate, Long copOpid, 
            String copOpname, Date brBrcreatedate, 
            Long mopOpid, String mopOpname, 
            Date brBrmodifydate, String brBrremark, 
            String brkBrkcode, String brkBrkname, 
            String ssSscode, String ssSsname, 
            String ctCtcode, String ctCtname, 
            String pmPmcode, String pmPmname, 
            String pkPkcode, String pkPkname){
		m_astrColumns = new String[39];
		setOptbrid(optBrid);
		setOptptcode(optPtcode);
		setOptoptname(optOptname);
		setOptoptuniversalcustomersign(optOptuniversalcustomersign);
		setOptoptuniversalservesign(optOptuniversalservesign);
		setOptoptuniversalachannelsign(optOptuniversalachannelsign);
		setOptoptuniversalschannelsign(optOptuniversalschannelsign);
		setOptoptuniversaldeparturesign(optOptuniversaldeparturesign);
		setOptoptuniversaldestinationsign(optOptuniversaldestinationsign);
		setOptoptuniversaltachesign(optOptuniversaltachesign);
		setOptoptvwgwformular(optOptvwgwformular);
		setOptoptchargeweightbegin(optOptchargeweightbegin);
		setOptoptchargeweightend(optOptchargeweightend);
		setOptoptpiecegrossweightbegin(optOptpiecegrossweightbegin);
		setOptoptpiecegrossweightend(optOptpiecegrossweightend);
		setOptoptdeclarevaluebegin(optOptdeclarevaluebegin);
		setOptoptdeclarevalueend(optOptdeclarevalueend);
		setOptoptcontent(optOptcontent);
		setBrbrname(brBrname);
		setBrbrename(brBrename);
		setBrbrstartdate(brBrstartdate);
		setBrbrenddate(brBrenddate);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setBrbrcreatedate(brBrcreatedate);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setBrbrmodifydate(brBrmodifydate);
		setBrbrremark(brBrremark);
		setBrkbrkcode(brkBrkcode);
		setBrkbrkname(brkBrkname);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
	}	
	
	
	public boolean equals(Object o) {
		if (o instanceof OperationpromptColumns && 
				this.getOptbrid().equals(((OperationpromptColumns)o).getOptbrid()))
			return true;
		return false;
	}

}
