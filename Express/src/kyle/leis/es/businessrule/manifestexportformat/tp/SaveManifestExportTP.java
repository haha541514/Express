package kyle.leis.es.businessrule.manifestexportformat.tp;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatColumns;
import kyle.leis.es.businessrule.manifestexportformat.da.ManifestexportformatCondition;
import kyle.leis.es.businessrule.manifestexportformat.dax.ManifestexportformatDemand;
import kyle.leis.hi.TbrManifestexportformat;

public class SaveManifestExportTP extends AbstractTransaction {
	private ManifestexportformatColumns columns;// �����嵥��ʽ
	// private List obj_mfefList;//�����嵥��
	private Long obj_mefcode;

	public void setParams(ManifestexportformatColumns columns) {
		// this.obj_mfefList = list;
		this.columns = columns;
	}

	public Long getObj_mefcode() {
		return obj_mefcode;
	}

	public void transaction(Session objSession) throws Exception {
		if (columns == null) {
			return;
		}
		// �����嵥��ʽ���ӻ��޸�
		TbrManifestexportformat format = new TbrManifestexportformat();
		if (StringUtility.isNull(columns.getMefmefcode())) {
			// ����,12��
			format.setMefCreatedate(DateFormatUtility.getSysdate());
			format.setMefCode(Long.parseLong(ManifestexportformatDemand.getNewMefCode()));
			ManifestexportformatDemand.setTbrManifestExportformat(format, columns, true);
			objSession.save(format);

			obj_mefcode = format.getMefCode();
		} else {
			obj_mefcode = Long.parseLong(columns.getMefmefcode());
			format = (TbrManifestexportformat) objSession.load(
					TbrManifestexportformat.class, Long.parseLong(columns
							.getMefmefcode()));
			ManifestexportformatDemand.setTbrManifestExportformat(format, columns, false);
			// ��ѯ������
			// ManifestexportformatColumns operaterColumns = new
			// ManifestexportformatColumns();

			ManifestexportformatDemand demand = new ManifestexportformatDemand();
			ManifestexportformatCondition operatetorCondtion = new ManifestexportformatCondition();
			;
			operatetorCondtion.setMefcode(columns.getMefmefcode());
			ManifestexportformatColumns operaterColumns = (ManifestexportformatColumns) demand
					.queryManifestexportformat(operatetorCondtion).get(0);
			// ManifestexportformatColumns operaterColumns =
			// (ManifestexportformatColumns)
			// demand.queryManifestexportformat(operatetorCondtion);

			format.setMefCode(Long.parseLong(columns.getMefmefcode()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(operaterColumns.getMefmefcreatedate());
			format.setMefCreatedate(date);
			objSession.update(format);
		}
	}

}
