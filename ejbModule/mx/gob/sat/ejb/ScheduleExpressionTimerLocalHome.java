package mx.gob.sat.ejb;



import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface ScheduleExpressionTimerLocalHome extends EJBLocalHome {
  public ScheduleExpressionTimerLocal create() throws CreateException ;
}
