package kyle.leis.es.businessrule.manifest.sv;

import java.util.ArrayList;
import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.manifest.bl.ManifestCustom;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnColumns;
import kyle.leis.es.businessrule.manifest.da.ManifestefcolumnCondition;
import kyle.leis.es.businessrule.manifest.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.manifest.da.ManifestexportformatCondition;
import kyle.leis.es.businessrule.manifest.dax.ManifestCustomDemand;

public class ManifestCustomService extends AService {
	//��ѯ�嵥��׼��
	public String queryManifestStandardColumns(Decoder objPD) throws Exception {
		ManifestCustomDemand objManifestCustomDemand = new ManifestCustomDemand();
		List listResults = objManifestCustomDemand.queryManifestStandardColumns();
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();
	}
	//��ѯ�嵥������ʽ
	public String queryManifestexportformat(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifestexportformatCondition objFCMC = (ManifestexportformatCondition)objPD.getParameter(0, 
				ManifestexportformatCondition.class);
		ManifestCustomDemand objManifestCustomDemand = new ManifestCustomDemand();
		List listResults = objManifestCustomDemand.queryManifestexportformat(objFCMC);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();
	}
	//��ѯ�嵥������
	public String queryManifestefcolumn(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		ManifestefcolumnCondition mefcCondition = (ManifestefcolumnCondition)objPD.getParameter(0, 
				ManifestefcolumnCondition.class);
		ManifestCustomDemand objManifestCustomDemand = new ManifestCustomDemand();
		List listResults = objManifestCustomDemand.queryManifestefcolumn(mefcCondition);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();
		
	}
	//���浼���嵥��ʽ�뵼���嵥��
	public String createManifestexportformat(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 2, this);
		ManifestexportformatColumns mfefColumns=(ManifestexportformatColumns)objPD.getParameter(0, 
				ManifestexportformatColumns.class);
		List mefcList = objPD.getParameterList(1, ManifestefcolumnColumns.class);
		ManifestCustom mfc=new ManifestCustom();				
		List listResults=mfc.createManifestexportformat(mfefColumns,mefcList);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();
	}
	//ɾ�������嵥��ʽ
	public String deleteManifestexportformat(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		String mefcode=(String)objPD.getParameter(0, String.class);
		ManifestCustom mfc=new ManifestCustom();
		mfc.deleteManifestexportformat(Long.parseLong(mefcode));
		
		//ɾ�����
		ManifestexportformatCondition mefCondition=new ManifestexportformatCondition();
		mefCondition.setMefcode(mefcode);
		ManifestCustomDemand objManifestCustomDemand = new ManifestCustomDemand();
		List listResults = objManifestCustomDemand.queryManifestexportformat(mefCondition);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();
	}
}
