package kyle.leis.es.smsservice.bl;

public interface SmsSaver {

	/**
	 * ������Ϣ
	 * @param coCode
	 * @param sms
	 * @throws Exception
	 */
	void saveAutoSmsmessage(String coCode, Sms sms) throws Exception;
}
