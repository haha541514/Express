package kyle.leis.eo.customerservice.track.dax;

import java.util.List;

import kyle.leis.eo.customerservice.track.da.SimplebillCondition;
import kyle.leis.eo.customerservice.track.da.SimplebillQuery;
import kyle.leis.eo.customerservice.track.da.SimpletrackColumns;
import kyle.leis.eo.customerservice.track.da.SimpletrackCondition;
import kyle.leis.eo.customerservice.track.da.SimpletrackQuery;
import kyle.leis.eo.customerservice.track.tp.DeleteSimpleTrackTransaction;
import kyle.leis.eo.customerservice.track.tp.SaveSimpleTrackTransaction;
import kyle.leis.eo.customerservice.track.tp.UpdateSimpleTrackTransaction;

public class SimpleTrackDemand {
  @SuppressWarnings("unchecked")
  public static List trackQuery(SimpletrackCondition objSimpletrackCondition) throws Exception{
	  SimpletrackQuery objSimpletrackQuery=new SimpletrackQuery();
	  objSimpletrackQuery.setCondition(objSimpletrackCondition);
	  return objSimpletrackQuery.getResults();
  }
  @SuppressWarnings("unchecked")
  public static List trackQueryById(String swbtid) throws Exception{
	  SimpletrackCondition objSimpletrackCondition=new SimpletrackCondition();
	  objSimpletrackCondition.setSwbtid(swbtid);
	  SimpletrackQuery objSimpletrackQuery=new SimpletrackQuery();
	  objSimpletrackQuery.setCondition(objSimpletrackCondition);
	  return objSimpletrackQuery.getResults();
  }
  @SuppressWarnings("unchecked")
  public static List billQuery(SimplebillCondition objSimplebillCondition) throws Exception{
	  SimplebillQuery objSimplebillQuery=new SimplebillQuery();
	  objSimplebillQuery.setCondition(objSimplebillCondition);
	  return objSimplebillQuery.getResults();
  }
  public static void addTrack(SimpletrackColumns objSimpletrackColumns) throws Exception{
	  SaveSimpleTrackTransaction objSaveSimpleTrackTransaction=new SaveSimpleTrackTransaction();
	  objSaveSimpleTrackTransaction.setParam(objSimpletrackColumns);
	  objSaveSimpleTrackTransaction.execute();
  }
  public static void deleteTrack(String swbCode,String swbtId) throws Exception{
	  DeleteSimpleTrackTransaction ojbDeleteSimpleTrackTransaction=new DeleteSimpleTrackTransaction();
	  ojbDeleteSimpleTrackTransaction.setParam(swbCode, swbtId);
	  ojbDeleteSimpleTrackTransaction.execute();
  }
  public static void updateTrack(SimpletrackColumns objSimpletrackColumns) throws Exception{
	  UpdateSimpleTrackTransaction objUpdateSimpleTrackTransaction=new UpdateSimpleTrackTransaction();
	  objUpdateSimpleTrackTransaction.setParam(objSimpletrackColumns);
	  objUpdateSimpleTrackTransaction.execute();
  }
}
