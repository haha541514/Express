package kyle.leis.eo.customerservice.track.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.da.SimplebillColumns;
import kyle.leis.eo.customerservice.track.da.SimplebillCondition;
import kyle.leis.eo.customerservice.track.da.SimpletrackColumns;
import kyle.leis.eo.customerservice.track.dax.SimpleTrackDemand;
import kyle.leis.eo.customerservice.track.dax.SimpleTrackSequence;
import kyle.leis.eo.customerservice.track.dax.SimplebillSequence;
import kyle.leis.hi.TcsSimplewaybill;
import kyle.leis.hi.TcsSimplewaybilltrack;

public class SaveSimpleTrackTransaction extends AbstractTransaction {
	private SimpletrackColumns m_objSimpletrackColumns;

	public void setParam(SimpletrackColumns objSimpletrackColumns) {
		this.m_objSimpletrackColumns = objSimpletrackColumns;
	}

	@SuppressWarnings("unchecked")
	public void transaction(Session objSession) throws Exception {
		String[] astrCwewbcode = m_objSimpletrackColumns
				.getSwbswb_customerewbcode().split(",");
		for (int i = 0; i < astrCwewbcode.length; i++) {
			SimplebillCondition objSimplebillCondition = new SimplebillCondition();
			objSimplebillCondition
					.setSwbcustomerewbcode(astrCwewbcode[i]);
			List<SimplebillColumns> objSimplebillColumnsList = SimpleTrackDemand
					.billQuery(objSimplebillCondition);
			String swbcode = "";
			if (objSimplebillColumnsList == null
					|| objSimplebillColumnsList.size() == 0) {
				TcsSimplewaybill objTcsSimplewaybill = new TcsSimplewaybill();
				SimplebillSequence objSimplebillSequence = new SimplebillSequence();
				swbcode = objSimplebillSequence.getNewSequencecode();
				objTcsSimplewaybill.setSwbCode(Long.parseLong(swbcode));
				objTcsSimplewaybill
						.setSwbCustomerewbcode(astrCwewbcode[i]);
				objSession.save(objTcsSimplewaybill);
			}
			TcsSimplewaybilltrack objTcsSimplewaybilltrack = new TcsSimplewaybilltrack();
			SimpleTrackSequence objSimpleTrackSequence = new SimpleTrackSequence();
			objTcsSimplewaybilltrack.setSwbtId(Long
					.parseLong(objSimpleTrackSequence.getNewSequencecode()));
			if (!StringUtility.isNull(swbcode))
				objTcsSimplewaybilltrack.setSwbCode(Long.parseLong(swbcode));
			else
				objTcsSimplewaybilltrack.setSwbCode(Long
						.parseLong(objSimplebillColumnsList.get(0)
								.getSwbswb_code()));
			objTcsSimplewaybilltrack.setSwbtCreatedate(DateFormatUtility
					.getSysdate());
			objTcsSimplewaybilltrack.setSwbtModifydate(DateFormatUtility
					.getSysdate());
			objTcsSimplewaybilltrack.setSwbtModifier(m_objSimpletrackColumns
					.getSwbtswbt_modifier());
			objTcsSimplewaybilltrack.setSwbtCreator(m_objSimpletrackColumns
					.getSwbtswbt_creator());
			objTcsSimplewaybilltrack.setSwbtDescription(m_objSimpletrackColumns
					.getSwbtswbt_description());
			objTcsSimplewaybilltrack.setSwbtLocation(m_objSimpletrackColumns
					.getSwbtswbt_location());
			objTcsSimplewaybilltrack.setSwbtOccurdate(DateFormatUtility
					.getStandardDate(m_objSimpletrackColumns
							.getSwbtswbt_occurdate()));
			objTcsSimplewaybilltrack.setSwbtOpensign(m_objSimpletrackColumns
					.getSwbtswbt_opensign());
			objSession.save(objTcsSimplewaybilltrack);
		}
	}

}
