package mx.gob.sat.ejb;



import javax.ejb.EJBLocalObject;
 


public interface ScheduleExpressionTimerLocal extends EJBLocalObject  {


	public void createScheduleExpressionTime()  ;



	public void cancelScheduleExpressionTime()  ;
}
