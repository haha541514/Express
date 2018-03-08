package kyle.leis.es.company.channel.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ChannelQuery extends HGeneralQuery {
	
	public ChannelQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.channel.da.ChannelColumns(chn.chnCode,chn.chnCreatedate,chn.chnModifydate,chn.cstCode,chn.chnName,chn.chnEname,chn.chnSname,chn.chnSename,chn.chnManifestseriesnumber,chn.chnMasteraccount,chn.chnPaymentaccount,cs.csCode,cs.csName,mop.opId,mop.opName,cop.opId,cop.opName,ee.eeCode,ee.eeSname,ee.eeEsname,co.coCode,co.coSname,co.coSename,chn.chnMaxdtransferweight,chn.chnWpxspsmappingname,sot.sotCode,sot.sotName,ssg.ssgCode,ssg.ssgName,ssg.ssgEname,sbck.bckCode,sbck.bckName,sbck.bckEname,mbck.bckCode,mbck.bckName,mbck.bckEname,lf.lfCode,lf.lfName,clf.clfCode,clf.clfName,chn.chnMaxdtransfercharge,chn.chnMaxmtransfercharge,ck.ckCode,ck.ckName,est.estCode,est.estName,chn.chnLabelprintprefix,chn.chnSobypiecessign,chn.chnMaxoverdeclarevalue,chn.chnChawbprefix) FROM TchnChannel as chn inner join chn.tdiChannelstatus as cs inner join chn.tdiOperatorByChnOpIdModify as mop inner join chn.tdiOperatorByChnOpIdCreate as cop inner join chn.tdiEnterpriseelement as ee inner join chn.tcoCorporation as co left join chn.tdiSignouttype as sot left join chn.tdiServerstructuregroup as ssg left join chn.tdiWaybillcodekindByChnSubbillcodekind as sbck left join chn.tdiWaybillcodekindByChnMainbillcodekind as mbck left join chn.tdiCustomlabelformat as clf left join chn.tdiLabelformatByChnCustomerlabel as lf left join chn.tdiCurrencykind as ck left join chn.tdiExpressspecialtype as est";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "chn.chnCode = '~~'", "chn.cstCode = '~~'", "chn.chnName like '%~~%'", "chn.chnSname like '%~~%'", "cs.csCode in (~~)", "ee.eeCode = '~~'", "co.coCode = '~~'", "mop.opId = ~~", "cop.opId = ~~", "chn.chnCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= chn.chnCreatedate", "chn.chnModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= chn.chnModifydate", "chn.chnEname = '~~'", "chn.chnSename = '~~'", "ssg.ssgCode = '~~'", "sbck.bckCode = '~~'", "mbck.bckCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setChncode(String chnCode) {
		this.setField(0, chnCode);
	}

	public String getChncode() {
		return this.getField(0);
	}

	public void setCstcode(String cstCode) {
		this.setField(1, cstCode);
	}

	public String getCstcode() {
		return this.getField(1);
	}

	public void setChnname(String chnName) {
		this.setField(2, chnName);
	}

	public String getChnname() {
		return this.getField(2);
	}

	public void setChnsname(String chnSname) {
		this.setField(3, chnSname);
	}

	public String getChnsname() {
		return this.getField(3);
	}

	public void setCscode(String csCode) {
		this.setField(4, csCode);
	}

	public String getCscode() {
		return this.getField(4);
	}

	public void setEecode(String eeCode) {
		this.setField(5, eeCode);
	}

	public String getEecode() {
		return this.getField(5);
	}

	public void setCocode(String coCode) {
		this.setField(6, coCode);
	}

	public String getCocode() {
		return this.getField(6);
	}

	public void setOpidcreator(String opidCreator) {
		this.setField(7, opidCreator);
	}

	public String getOpidcreator() {
		return this.getField(7);
	}

	public void setOpidmodifier(String opidModifier) {
		this.setField(8, opidModifier);
	}

	public String getOpidmodifier() {
		return this.getField(8);
	}

	public void setStartcreatedate(String StartCreateDate) {
		this.setField(9, StartCreateDate);
	}

	public String getStartcreatedate() {
		return this.getField(9);
	}

	public void setEndcreatedate(String EndCreateDate) {
		this.setField(10, EndCreateDate);
	}

	public String getEndcreatedate() {
		return this.getField(10);
	}

	public void setStartmodifydate(String StartModifyDate) {
		this.setField(11, StartModifyDate);
	}

	public String getStartmodifydate() {
		return this.getField(11);
	}

	public void setEndmodifydate(String EndModifyDate) {
		this.setField(12, EndModifyDate);
	}

	public String getEndmodifydate() {
		return this.getField(12);
	}

	public void setChnename(String chnEname) {
		this.setField(13, chnEname);
	}

	public String getChnename() {
		return this.getField(13);
	}

	public void setChnsename(String chnSename) {
		this.setField(14, chnSename);
	}

	public String getChnsename() {
		return this.getField(14);
	}

	public void setSsgcode(String ssgCode) {
		this.setField(15, ssgCode);
	}

	public String getSsgcode() {
		return this.getField(15);
	}

	public void setSbckcode(String sbckCode) {
		this.setField(16, sbckCode);
	}

	public String getSbckcode() {
		return this.getField(16);
	}

	public void setMbckcode(String mbckCode) {
		this.setField(17, mbckCode);
	}

	public String getMbckcode() {
		return this.getField(17);
	}

}
