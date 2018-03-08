package kyle.leis.eo.billing.incidentalfee.da;

public class SumincidentalfeeQueryEX extends SumincidentalfeeQuery {
	public SumincidentalfeeQueryEX() {
		m_strSelectClause = "SELECT NVL(SUM(ROUND(decode(iff.bk_code, 'A0101', 1, 'A0201', -1, 'A01', 1, 'A02', -1, 0) * iff.if_actualtotal, 2)),0) as incidentalFeeTotal FROM T_BL_INCIDENTALFEE iff";
	}
}
