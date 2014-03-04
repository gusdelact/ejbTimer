package mx.gob.sat.ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import javax.ejb.Remote;
import javax.ejb.TimerHandle;

/**
 * @author murali
 *
 */
@Remote
public interface TimerRemote extends EJBObject
{
  public TimerHandle createMyTimer() throws RemoteException;

  public TimerHandle getTimerHandleForTimer() throws RemoteException;
  
  public void cancelMyTimer() throws RemoteException;
}