package kyle.leis.eo.customerservice.issue.dax;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiEnterpriseelement;

public class HtmlForEmailTemplate {

	// 获得问题件发送的HTML模板
	public static String getIssueEmailContent(
			IssueHawbInfo objIssueHawbInfo,TdiEnterpriseelement objTdiEnterpriseelement) {

		String strDestinationContury = convertNulltoSpace(objIssueHawbInfo
				.getDestinationCountry());

		if (!StringUtility.isNull(objIssueHawbInfo.getDestinationCountry())) {
			strDestinationContury = strDestinationContury + "的快件";
		}

		// 显示时，在小于1KG的重量前加0
		objIssueHawbInfo
				.setChargeWeight(standardDecimalDisplay(objIssueHawbInfo
						.getChargeWeight()));

		String strSigninDate = null;
		if (!StringUtility.isNull(objIssueHawbInfo.getSignindate())) {
			strSigninDate = objIssueHawbInfo.getSignindate().substring(
					0, 16);
		} else {
			strSigninDate = DateFormatUtility.getStandardSysdate()
					.substring(0, 16);
		}

		String strTemp;
		strTemp = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n"
				+ "\"http://www.w3.org/TR/html4/loose.dtd\">\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">\n"
				+ "<title>"
				+ objTdiEnterpriseelement.getEeEname()
				+"</title>\n"
				+ "<style type=\"text/css\"> \n"
				+ "body {\n"
				+ "	margin-top: 0px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-bottom: 0px;\n"
				+ "	margin-left: auto;\n"
				+ "	text-align: center;\n"
				+ "	font-family: Verdana, Arial, Helvetica, sans-serif;\n"
				+ "	font-size: 12px;\n"
				+ "}\n"
				+ "#top {\n"
				+ "	width: 758px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	line-height: 25px;\n"
				+ "	color: #FFFFFF;\n"
				+ "	background-color: #9E8E7E;\n"
				+ "	text-align: right;\n"
				+ "	padding-right: 20px;\n"
				+ "	font-size: 14px;\n"
				+ "	font-weight: bold;\n"
				+ "	margin-top: 5px;\n"
				+ "}\n"
				+ "#top1 {\n"
				+ "	width: 776px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	color: #1d2088;\n"
				+ "	text-align: left;\n"
				+ "	background-color: #FFFFFF;\n"
				+ "	border-right-width: 1px;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-right-style: solid;\n"
				+ "	border-left-style: solid;\n"
				+ "	border-right-color: #CEC6BE;\n"
				+ "	border-left-color: #CEC6BE;\n"
				+ "	height: 60px;\n"
				+ "}\n"
				+ "#top11{\n"
				+ "	float: left;\n"
				+ "	width: 50px;\n"
				+ "	height: 60px;\n"
				+ "}\n"
				+ "#top111{\n"
				+ "	width: 50px;\n"
				+ "	height: 8px;\n"
				+ "	border-top-width: 8px;\n"
				+ "	border-bottom-width: 8px;\n"
				+ "	border-top-style: solid;\n"
				+ "	border-bottom-style: solid;\n"
				+ "	border-top-color: #FF9900;\n"
				+ "	border-bottom-color: #FF9900;\n"
				+ "	margin-top: 10px;\n"
				+ "	color: #FF6600;\n"
				+ "	margin-bottom: 8px;\n"
				+ "	background-color: #FFFFFF;\n"
				+ "	font-size: 8px;\n"
				+ "}\n"
				+ "#top112{\n"
				+ "	width: 50px;\n"
				+ "	height: 8px;\n"
				+ "	background-color: #FF9900;\n"
				+ "	font-size: 8px;\n"
				+ "	color: #FF6600;\n"
				+ "}\n"
				+ "\n"
				+ "#top12{\n"
				+ "	float: left;\n"
				+ "	width: 120px;\n"
				+ "	font-size: 52px;\n"
				+ "	font-weight: bold;\n"
				+ "	text-align: center;\n"
				+ "	height: 60px;\n"
				+ "}\n"
				+ "#top13{\n"
				+ "	width: 600px;\n"
				+ "	height: 60px;\n"
				+ "	float: right;\n"
				+ "}\n"
				+ "#top131{\n"
				+ "	width: 600px;\n"
				+ "	height: 8px;\n"
				+ "	border-top-width: 8px;\n"
				+ "	border-bottom-width: 8px;\n"
				+ "	border-top-style: solid;\n"
				+ "	border-bottom-style: solid;\n"
				+ "	border-top-color: #FF9900;\n"
				+ "	border-bottom-color: #FF9900;\n"
				+ "	margin-top: 10px;\n"
				+ "	margin-bottom: 8px;\n"
				+ "	font-size: 8px;\n"
				+ "}\n"
				+ "#top132{\n"
				+ "	width: 600px;\n"
				+ "	height: 8px;\n"
				+ "	background-color: #FF9900;\n"
				+ "	font-size: 8px;\n"
				+ "	color: #FF6600;\n"
				+ "}\n"
				+ "#middle {\n"
				+ "	width: 778px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #FF9900;\n"
				+ "}\n"
				+ "#m_left{\n"
				+ "	width: 100px;\n"
				+ "	float: left;\n"
				+ "	background-color: #FF9900;\n"
				+ "}\n"
				+ "#m_right{\n"
				+ "	width: 677px;\n"
				+ "	float: left;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-left-style: solid;\n"
				+ "	border-left-color: #FF9900;\n"
				+ "	background-color: #FFFFFF;\n"
				+ "	font-size: 14px;\n"
				+ "	line-height: 20px;\n"
				+ "	font-weight: bold;\n"
				+ "}\n"
				+ ".m_right1 {\n"
				+ "	width: 677px;\n"
				+ "	float: left;\n"
				+ "	text-align: right;\n"
				+ "	border-top-width: 1px;\n"
				+ "	border-top-style: solid;\n"
				+ "	border-top-color: #FF9900;\n"
				+ "}\n"
				+ ".m{\n"
				+ "	line-height: 18px;\n"
				+ "	width: 350px;\n"
				+ "	text-align: left;\n"
				+ "	font-size: 12px;\n"
				+ "	font-weight: normal;\n"
				+ "	padding-right: 50px;\n"
				+ "	padding-left: 50px;\n"
				+ "}\n"
				+ ".m1{\n"
				+ "	line-height: 18px;\n"
				+ "	width: 350px;\n"
				+ "	text-align: left;\n"
				+ "	font-size: 14px;\n"
				+ "	font-weight: bold;\n"
				+ "	color: #FF6600;\n"
				+ "	padding-right: 50px;\n"
				+ "	padding-left: 50px;\n"
				+ "}\n"
				+ ".m2{\n"
				+ "	line-height: 30px;\n"
				+ "	width: 350px;\n"
				+ "	text-align: left;\n"
				+ "	font-size: 14px;\n"
				+ "	font-weight: bold;\n"
				+ "	color: #000000;\n"
				+ "	padding-right: 50px;\n"
				+ "	padding-left: 50px;\n"
				+ "}\n"
				+ ".m3{\n"
				+ "	width: 50px;\n"
				+ "	float: left;\n"
				+ "}\n"
				+ ".m4{\n"
				+ "	line-height: 25px;\n"
				+ "	float: left;\n"
				+ "	width: 220px;\n"
				+ "}\n"
				+ ".m5{\n"
				+ "	line-height: 20px;\n"
				+ "	float: left;\n"
				+ "	color: #FF6600;\n"
				+ "	padding-right: 5px;\n"
				+ "	padding-left: 5px;\n"
				+ "	background-color: #FFFFFF;\n"
				+ "	margin-right: 3px;\n"
				+ "	margin-left: 3px;\n"
				+ "	border-bottom: 1px solid #CCCCCC;\n"
				+ "}\n"
				+ ".m6{line-height: 20px;\n"
				+ "	width: 200px;\n"
				+ "	text-align: left;\n"
				+ "	font-size: 14px;\n"
				+ "	color: #595959;}\n"
				+ ".m7 {color: #FF6600}\n"
				+ "\n"
				+ "#middle1 {\n"
				+ "	width: 776px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #FFFFFF;\n"
				+ "	text-align: left;\n"
				+ "	border-top-width: 1px;\n"
				+ "	border-top-style: dashed;\n"
				+ "	border-top-color: #CEC6BE;\n"
				+ "	border-right-width: 1px;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-right-style: solid;\n"
				+ "	border-left-style: solid;\n"
				+ "	border-right-color: #CEC6BE;\n"
				+ "	border-left-color: #CEC6BE;\n"
				+ "}\n"
				+ "#middle2 {\n"
				+ "	width: 676px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #FFFFFF;\n"
				+ "	font-size: 14px;\n"
				+ "	line-height: 20px;\n"
				+ "	color: #595959;\n"
				+ "	padding-right: 50px;\n"
				+ "	padding-left: 50px;\n"
				+ "	padding-top: 5px;\n"
				+ "	text-align: left;\n"
				+ "	border-right-width: 1px;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-right-style: solid;\n"
				+ "	border-left-style: solid;\n"
				+ "	border-right-color: #CEC6BE;\n"
				+ "	border-left-color: #CEC6BE;\n"
				+ "}\n"
				+ "#middle3 {\n"
				+ "	width: 676px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #F6F9FC;\n"
				+ "	font-size: 14px;\n"
				+ "	line-height: 30px;\n"
				+ "	color: #000000;\n"
				+ "	padding-right: 50px;\n"
				+ "	padding-left: 50px;\n"
				+ "	padding-top: 10px;\n"
				+ "	text-align: left;\n"
				+ "	font-weight: bold;\n"
				+ "	border-right-width: 1px;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-right-style: solid;\n"
				+ "	border-left-style: solid;\n"
				+ "	border-right-color: #CEC6BE;\n"
				+ "	border-left-color: #CEC6BE;\n"
				+ "}\n"
				+ "#middle4 {\n"
				+ "	width: 676px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #FFFFFF;\n"
				+ "	font-size: 14px;\n"
				+ "	padding-right: 50px;\n"
				+ "	padding-left: 50px;\n"
				+ "	text-align: left;\n"
				+ "	line-height: 25px;\n"
				+ "	height: 25px;\n"
				+ "	color: #595959;\n"
				+ "	border-right-width: 1px;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-right-style: solid;\n"
				+ "	border-left-style: solid;\n"
				+ "	border-right-color: #CEC6BE;\n"
				+ "	border-left-color: #CEC6BE;\n"
				+ "}\n"
				+ "#middle5 {\n"
				+ "	width: 726px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #FFFFFF;\n"
				+ "	text-align: right;\n"
				+ "	border-right-width: 1px;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-right-style: solid;\n"
				+ "	border-left-style: solid;\n"
				+ " padding-left: 50px;\n"
				+ "	border-right-color: #CEC6BE;\n"
				+ "	border-left-color: #CEC6BE;\n"
				+ "}\n"
				+ "#middle6 {\n"
				+ "	width: 676px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #F6F9FC;\n"
				+ "	font-size: 14px;\n"
				+ "	line-height: 20px;\n"
				+ "	color: #595959;\n"
				+ "	padding-right: 50px;\n"
				+ "	padding-left: 50px;\n"
				+ "	padding-top: 5px;\n"
				+ "	text-align: left;\n"
				+ "	border-right-width: 1px;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-right-style: solid;\n"
				+ "	border-left-style: solid;\n"
				+ "	border-right-color: #CEC6BE;\n"
				+ "	border-left-color: #CEC6BE;\n"
				+ "}\n"
				+ "#middle7 {\n"
				+ "	width: 676px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #F6F9FC;\n"
				+ "	text-align: left;\n"
				+ "	border-right-width: 1px;\n"
				+ "	border-left-width: 1px;\n"
				+ "	border-right-style: solid;\n"
				+ "	border-left-style: solid;\n"
				+ "	border-right-color: #CEC6BE;\n"
				+ "	border-left-color: #CEC6BE;\n"
				+ "	padding-right: 50px;\n"
				+ "	padding-left: 50px;\n"
				+ "}\n"
				+ "#down {\n"
				+ "	width: 758px;\n"
				+ "	margin-right: auto;\n"
				+ "	margin-left: auto;\n"
				+ "	background-color: #9E8E7E;\n"
				+ "	clear: both;\n"
				+ "	text-align: left;\n"
				+ "	padding-left: 20px;\n"
				+ "	line-height: 20px;\n"
				+ "	color: #FFFFFF;\n"
				+ "}\n"
				+ "#down a:link,a:visited,a:hover,a:active{\n"
				+ "	color: #FFFFFF;\n"
				+ "	text-decoration: none;\n"
				+ "}\n"
				+ "\n"
				+ "</style>\n"
				+ "</head>\n"
				+ "\n"
				+ "<body>\n"
				+ "<div id=\"top\"></div>\n"
				+ "<div id=\"middle3\">尊敬的 "
				+ objIssueHawbInfo.getCustomerName()+" 客户"
				+ "：</div>\n"
				+ "<div id=\"middle4\"><div class=\"m4\">贵司于&nbsp;<span class=\"m7\">"
				+ strSigninDate
				+ "</span></div><div class=\"m4\">发往：<span class=\"m7\">"
				+ convertNulltoSpace(objIssueHawbInfo
						.getDestinationCountry())
				+ "</span>&nbsp;</div>\n"
				+ "</div>\n"
				+ "<div id=\"middle4\"><div class=\"m4\">运单号：<span class=\"m7\">"
				+ objIssueHawbInfo.getCustomerewbCode()
				+ "</span> </div><div class=\"m4\">重量：<span class=\"m7\">"
				+ objIssueHawbInfo.getChargeWeight()
				+ "（KG）</span></div></div>\n"
				+ "<div id=\"middle4\"><div class=\"m4\">类型：<span class=\"m7\">"
				+ objIssueHawbInfo.getCargoType()
				+ "</span></div>\n"
				+ "<div class=\"m4\">产品：<span class=\"m7\">"
				+ objIssueHawbInfo.getProductKind()
				+ "</span></div>\n"
				+ "</div>\n"
				+ "<div id=\"middle4\"><div class=\"m4\">问题类型：<span class=\"m7\">"
				+ objIssueHawbInfo.getIssueType()
				+ "</span></div>\n"
				+ "</div>\n"
				+ "<div id=\"middle4\">问题原因：<span class=\"m7\">"
				+ objIssueHawbInfo.getIssueContent()
				+ "</span></div>\n"
				//+ "<div id=\"middle2\">\n"
				//+ objIssueHawbInfo.getIssueContent()
				//+ "<br> \n"
				//+ "  <br>\n"
				//+ "</div>\n"
				//+ "<div id=\"middle7\">\n"
				//+ "  <div class=\"m6\">请贵司尽快提供处理意见。<br>谢谢合作！</div></div>\n"
				+ "  <div id=\"middle5\"><div class=\"m6\"><br>\n"
				+ "    日期：<span class=\"m7\">"
				+ DateFormatUtility.getStandardSysdate()
				+ "</span> </div>\n"
				+ "</div>\n"
				+ "<div id=\"middle1\"><div class=\"m2\">"
				+ objIssueHawbInfo.getSenderName()
				+ "</div>\n"
				+ "	<div class=\"m1\">"
				+ objTdiEnterpriseelement.getEeName()
				+"</div>\n"
				+ "<div class=\"m\">地址："
				+ objTdiEnterpriseelement.getEeAddress()
				+"<br>\n"
				+ "邮编："
				+ objTdiEnterpriseelement.getEePostcode()
				+"<br>总机："
				+ objTdiEnterpriseelement.getEeTelephone()
				+"<br>直线："
				+ objIssueHawbInfo.getSenderTelephone()
				+ "</div>\n"
				+ "    <div class=\"m\">.</div></div>\n"
				+ "<div id=\"down\"></div>\n"
				+ "</body>\n" + "</html>\n";

		return strTemp;
	}

	// 转换空字符串、null、全部为空格的字符串为HTML可识别的空格
	private static String convertNulltoHtmlSpace(String strSource) {

		if ((!StringUtility.isNull(strSource))
				&& (!StringUtility.isNull(strSource.trim()))) {

			return strSource.trim();
		}
		return "<div>&nbsp;</div>";
	}

	// 转换空字符串、null、全部为空格的字符串为空格
	private static String convertNulltoSpace(String strSource) {

		if ((!StringUtility.isNull(strSource))
				&& (!StringUtility.isNull(strSource.trim()))) {

			return strSource.trim();
		}
		return " ";
	}

	public String getDateTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss G E D F w W a E F");
		String mDateTime = formatter.format(cal.getTime());
		String strDate = mDateTime.substring(0, 19);
		return strDate;
	}

	private static String standardDecimalDisplay(String strSourceDecimal) {

		// 显示时，在小于1KG的毛重前加0
		String strStandardDisplay;
		if (strSourceDecimal.startsWith(".")) {
			strStandardDisplay = "0" + strSourceDecimal;
		} else {
			strStandardDisplay = strSourceDecimal;
		}

		return strStandardDisplay;
	}
}
