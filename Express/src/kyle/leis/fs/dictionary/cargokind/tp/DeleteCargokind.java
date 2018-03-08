package kyle.leis.fs.dictionary.cargokind.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.leis.hi.TdiSimplestatus;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.cargokind.da.CargokindColumns;
import kyle.leis.fs.dictionary.cargokind.da.CargokindCondition;
import kyle.leis.fs.dictionary.cargokind.dax.CargokindDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCargokindDC;
import kyle.leis.hi.TdiCargokind;

public class DeleteCargokind extends AbstractTransaction {

	private String cgk_code;
	private CargokindColumns m_CargokindColumns;

	public void setParam(String code) {
		this.cgk_code = code;
	}

	public CargokindColumns getCargokindColumns() {
		return m_CargokindColumns;
	}

	public void transaction(Session objSession) throws Exception {
		// TdiBatterykind
		List list = objSession
				.find("from TdiBatterykind ba where ba.tdiCargokind.cgkCode ='"
						+ cgk_code + "'");

		if (list.size() > 0) {
			// �����ã��޷�ɾ�����޷��޸ģ���Batterykind�������ű�
			/*CargokindCondition objCondition = new CargokindCondition();
			objCondition.setCgkcode(cgk_code);
			List objReturn = CargokindDemand.Query(objCondition);*/
			this.m_CargokindColumns = null;//����ֵΪ�մ���ɾ�����ɹ�
		} else {
			// ͨ��ID����ʵ����,�����޸�
			TdiCargokind objCargo = (TdiCargokind) TdiCargokindDC.load(TdiCargokind.class, cgk_code);
			CargokindColumns columns = new CargokindColumns();
			columns.setSisscode("OFF");
			objCargo.setTdiSimplestatus((kyle.leis.hi.TdiSimplestatus) objSession.load(TdiSimplestatus.class, columns.getSisscode()));
			objSession.update(objCargo);
			this.m_CargokindColumns = CargokindDemand.queryById(cgk_code);//�޸ĳɹ�����columns,�޸ĳɹ�
		}
	}

}
