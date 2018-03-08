package kyle.leis.es.smsservice.test;

import kyle.common.util.trigger.bl.ITask;
import kyle.leis.es.smsservice.bl.AutoSendSmsTask;

public class MsgSendTaskTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ITask task = new AutoSendSmsTask();
		task.execute("");
	}

}
