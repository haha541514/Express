package kyle.leis.eo.operation.housewaybill.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class DHLConnectImportColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DHLConnectImportColumns() {
		m_astrColumns = new String[5];
	}
	
	public DHLConnectImportColumns(String strAwbno,
			String strReference, String strPieceno,
			String strPieceweight, String strPieceid){
		
		m_astrColumns = new String[5];
		setAwbno(strAwbno);
		setReference(strReference);
		setPieceno(strPieceno);
		setPieceweight(strPieceweight);
		setPieceid(strPieceid);		
	}

    public void setAwbno(String strAwbno)
    {
        this.setField(0, strAwbno);
    }

    public String getAwbno()
    {
        return this.getField(0);
    }

    public void setReference(String strReference)
    {
        this.setField(1, strReference);
    }

    public String getReference()
    {
        return this.getField(1);
    }

    public void setPieceno(String strPieceno)
    {
        this.setField(2, strPieceno);
    }

    public String getPieceno()
    {
        return this.getField(2);
    }

    public void setPieceweight(String strPieceweight)
    {
        this.setField(3, strPieceweight);
    }

    public String getstrPieceweight()
    {
        return this.getField(3);
    }

    public void setPieceid(String strPieceid)
    {
        this.setField(4, strPieceid);
    }

    public String getPieceid()
    {
        return this.getField(4);
    }

}
