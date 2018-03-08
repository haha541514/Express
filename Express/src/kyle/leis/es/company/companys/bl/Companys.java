package kyle.leis.es.company.companys.bl;

import kyle.leis.es.company.companys.tp.ModifyCompanysStatusTrans;

public class Companys {
	public void modifyStatus(String strCocode, 
			String strCscode, 
			String strOperId) throws Exception {
		ModifyCompanysStatusTrans objMCSTrans = new ModifyCompanysStatusTrans();
		objMCSTrans.setParam(strCocode, 
				strCscode, 
				strOperId);
		objMCSTrans.execute();
	}
}
