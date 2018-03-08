package kyle.leis.es.company.channel.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.company.channel.bl.Channel;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.da.ChannelCondition;
import kyle.leis.es.company.channel.da.ChanneladdressColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;

public class ChannelService extends AService {
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		ChannelCondition objChannelCondition = (ChannelCondition)objPD.getParameter(0, 
				ChannelCondition.class);
		List objList = ChannelDemand.query(objChannelCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	public String getOperationLabelFormat(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strChncode = (String)objPD.getParameter(0, String.class);
		String strLabelFormat = ChannelDemand.getOperationLabelFormat(strChncode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strLabelFormat);
		return objEncode.toString();		
	}	
	
	public String loadChanneladdress(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strChncode = (String)objPD.getParameter(0, String.class);
		ChanneladdressColumns objChanneladdressColumns = ChannelDemand.loadChanneladdress(strChncode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objChanneladdressColumns);
		return objEncode.toString();		
	}		
	
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		ChannelColumns objChannelColumns = (ChannelColumns)objPD.getParameter(0, 
				ChannelColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Channel objChannel = new Channel();
		ChannelColumns objSavedChannelColumns = objChannel.save(objChannelColumns, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSavedChannelColumns);
		return objEncode.toString();		
	}
	
	public String modifyStatus(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		String strChncode = (String)objPD.getParameter(0, String.class); 
		String strCscode = (String)objPD.getParameter(1, String.class);  
		String strOperId = (String)objPD.getParameter(2, String.class); 
		
		Channel objChannel = new Channel();
		ChannelColumns objSavedChannelColumns = objChannel.modifyStatus(strChncode, 
				strCscode, 
				strOperId);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSavedChannelColumns);
		return objEncode.toString();			
	}
}
