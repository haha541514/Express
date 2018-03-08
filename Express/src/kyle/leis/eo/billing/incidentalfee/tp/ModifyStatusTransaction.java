package kyle.leis.eo.billing.incidentalfee.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TblIncidentalfee;
import kyle.leis.hi.TfiBillrecord;
import net.sf.hibernate.Session;

public class ModifyStatusTransaction extends AbstractTransaction {

	/*private String m_strIfId;
	private String m_strFsCode;
	private String m_strOperId;
	
	public void setParam(String strIfId,String strFsCode,String strOperId)
	{
		this.m_strIfId = strIfId;
		this.m_strFsCode = strFsCode;
		this.m_strOperId = strOperId;
	}
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strIfId) || StringUtility.isNull(m_strFsCode)) return ;
		
		TblIncidentalfee objTblIncidentalfee = (TblIncidentalfee)objSession.load(TblIncidentalfee.class, Long.valueOf(m_strIfId));
		objTblIncidentalfee.setTdiFeestatus(TdiFeestatusDC.loadByKey(m_strFsCode));
		objTblIncidentalfee.setTdiOperatorByOpIdConfirm(TdiOperatorDC.loadByKey(m_strOperId));
		objTblIncidentalfee.setTdiOperatorByOpIdModify(TdiOperatorDC.loadByKey(m_strOperId));
		objTblIncidentalfee.setIfModifydate(DateFormatUtility.getSysdate());
		objTblIncidentalfee.setIfConfirmdate(DateFormatUtility.getSysdate());
		objSession.save(objTblIncidentalfee);
	}*/
	
	
	private String m_strIfId;
	private String m_strFsCode;
	private String m_strOperId;
	private TfiBillrecord m_objTfiBillrecord;
	
	public void setParam(String strIfId,String strFsCode,TfiBillrecord objTfiBillrecord,String strOperId)
	{
		this.m_objTfiBillrecord = objTfiBillrecord;
		setParam(strIfId,strFsCode,strOperId);
	}
	
	public void setParam(String strIfId,String strFsCode,String strOperId)
	{
		this.m_strIfId = strIfId;
		this.m_strFsCode = strFsCode;
		this.m_strOperId = strOperId;
	}
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strIfId) || StringUtility.isNull(m_strFsCode)) return ;
		TblIncidentalfee objTblIncidentalfee = (TblIncidentalfee)objSession.load(TblIncidentalfee.class, Long.valueOf(m_strIfId));
		//³öÕÊ
		if(objTblIncidentalfee.getTfiBillrecord() == null && m_objTfiBillrecord != null)
		{
			objTblIncidentalfee.setTfiBillrecord(m_objTfiBillrecord);
		}
//		if((objTblIncidentalfee.getBrId() == null || objTblIncidentalfee.getBrId().equals("")) && m_objTfiBillrecord != null)
//		{
//			objTblIncidentalfee.setBrId(1l);
//		}
		
		objTblIncidentalfee.setTdiFeestatus(TdiFeestatusDC.loadByKey(m_strFsCode));
		objTblIncidentalfee.setTdiOperatorByOpIdConfirm(TdiOperatorDC.loadByKey(m_strOperId));
		objTblIncidentalfee.setTdiOperatorByOpIdModify(TdiOperatorDC.loadByKey(m_strOperId));
		objTblIncidentalfee.setIfModifydate(DateFormatUtility.getSysdate());
		objTblIncidentalfee.setIfConfirmdate(DateFormatUtility.getSysdate());
		objSession.save(objTblIncidentalfee);
	}
	
	

}
