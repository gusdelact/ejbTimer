package mx.gob.sat.ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * @author murali
 *
 */
public interface TimerHome extends EJBHome
{
  public TimerRemote create() throws CreateException, RemoteException;
}