package kyle.leis.fs.servletutil;

public class ResultsXML {
	public static String doResults(String strMessageCode,
			String strMessage) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("\r\n");
		sb.append("\t<error>\n\t\t<errorcode>" + strMessageCode
				+ "</errorcode>\n\t\t<errorinfo>" + strMessage
				+ "</errorinfo>\n\t</error>\n");
		return sb.toString();
	}

}
