package kyle.leis.es.company.channel.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChannelseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChannelseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public ChannelseqColumns(String Channelseq){
		m_astrColumns = new String[1];
		setChannelseq(Channelseq);
	}

	public void setChannelseq(String Channelseq) {
		this.setField(0, Channelseq);
	}

	public String getChannelseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
