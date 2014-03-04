package mx.gob.sat.ejb;



import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerService;

 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Session Bean implementation class ScheduleExpressionTimer
 */
@Stateless
public class ScheduleExpressionTimer implements ScheduleExpressionTimerLocal , TimedObject {

	private static final Log log = LogFactory.getLog(ScheduleExpressionTimer.class);
	@Resource
	SessionContext context;

	

	/**
	 * Default constructor. 
	 */
	public ScheduleExpressionTimer() {

	}

	@Override
	public void ejbTimeout(Timer timer) {
        log.info( "Timeout" + new Date());
        String[] springConfig  = 
			{	"database-configuration.xml", 
				"repository-configuration.xml",
				"jobs-configuration.xml" 
			};
		
		

		try {
			log.info("Arrancando ejemplo de Batch en mode Asincrono");
			
			ClassPathXmlApplicationContext context = 
					new ClassPathXmlApplicationContext(springConfig);
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncherAsync");
			Job job = (Job) context.getBean("pr02batch");
			JobParameters jobParameters = 
					  new JobParametersBuilder()
					  .addLong("time",System.currentTimeMillis()).toJobParameters();

			JobExecution execution = jobLauncher.run(job, jobParameters );
			log.info("Exit Status : " + execution.getStatus()+"\n");
			
		
            context.close();
            
        	log.info(" ... timeout en " + timer.getNextTimeout());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
        

	}

	@Override
	public void createScheduleExpressionTime()   {

		TimerService timerService = context.getTimerService();
		//La primera expiración es 1 minuto despues de arrancar y luego se repite cada 300 segundos o 5 minutos
		Timer timer = 
				timerService.createTimer(60000L,300*1000L , "created ScheduleExpressionTime");
		 
		 
		log.info("Timer Created ... timeout en " + timer.getNextTimeout());
	}

	@Override
	public void cancelScheduleExpressionTime()   {
		TimerService timerService = context.getTimerService();
		@SuppressWarnings("unchecked")
		Collection<Timer> timers = timerService.getTimers();
		for( Timer timer : timers) {
			
			timer.cancel();
			log.info(" cancel ScheduleExpressionTime");
		}
	}

	@Override
	public EJBLocalHome getEJBLocalHome() throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrimaryKey() throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIdentical(EJBLocalObject obj) throws EJBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove() throws RemoveException, EJBException {
		// TODO Auto-generated method stub
		
	}

}
