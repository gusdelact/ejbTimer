package mx.gob.sat.ejb;

import java.rmi.RemoteException;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
import javax.ejb.TimerService;

/**
 * @author murali
 *
 */
@Stateless()
public class MyTimer implements TimerRemote, TimedObject
{
	@Resource
	SessionContext context;

	private TimerHandle timerHandler;

	public MyTimer()
	{

	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException
	{

	}

	public void ejbRemove() throws EJBException, RemoteException
	{

	}

	public TimerHandle createMyTimer()
			throws EJBException
			{
		TimerService timerService = context.getTimerService();
		Timer timer = timerService.createTimer(30000L, "created timer");
		 

		this.timerHandler = timer.getHandle();
		System.out.println(this.getClass().toString()+
				"Timer Created ...");
		return this.timerHandler;
			}

	public void ejbTimeout(Timer timer)
	{
		System.out.println(timer.getClass().toString() + 
				"EJB Timed Out ..." + 
				new Date());
		
		this.createMyTimer();
	}

	public void ejbCreate()
			throws CreateException
			{

			}

	public TimerHandle getTimerHandleForTimer() throws EJBException
	{
		if (this.timerHandler != null) {
			System.out.println(this.timerHandler);
			System.out.println(this.timerHandler.getTimer());
			System.out.println(this.timerHandler.getTimer().getTimeRemaining());

		}

		return this.timerHandler;
	}

	public EJBHome getEJBHome()
			throws RemoteException
			{
		return null;
			}

	public Handle getHandle()
			throws RemoteException
			{
		return null;
			}

	public Object getPrimaryKey()
			throws RemoteException
			{
		return null;
			}

	public boolean isIdentical(EJBObject arg0)
			throws RemoteException
			{
		return false;
			}

	public void remove()
			throws RemoteException, RemoveException
			{
			}

	@Override
	public void cancelMyTimer() throws RemoteException {
		if (this.timerHandler != null) {
			System.out.println(this.timerHandler.getClass().toString() + " cancel MyTimer");
			this.timerHandler.getTimer().cancel();
		}

	}
}