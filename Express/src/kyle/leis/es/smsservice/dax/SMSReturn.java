package kyle.leis.es.smsservice.dax;

import kyle.common.util.sms.SMSResult;

public class SMSReturn extends SMSResult{
	private String m_strPromptText;
	
	public void setPromptText(String strPromptText)
	{
		this.m_strPromptText = strPromptText;
	}
	
	public String getPromptText()
	{
		return this.m_strPromptText;
	}
	
	public String[] getSmsReturn()
	{
		String[] astrSmsReturn = new String[3];
		astrSmsReturn[0] = String.valueOf(super.isSuceess());
		astrSmsReturn[1] = m_strPromptText;
		astrSmsReturn[2] = super.getResultText();
		return astrSmsReturn;
	}
}
