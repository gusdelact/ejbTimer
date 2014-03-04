package mx.gob.sat.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DormirTasklet implements Tasklet {
    public final static long PERIODO_SEGUNDOS = 60L;
    private static final Log log = LogFactory.getLog(DormirTasklet.class);
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
			throws Exception {
		log.info("Durmiendo " + PERIODO_SEGUNDOS + " segundos ...");
		Thread.sleep(PERIODO_SEGUNDOS*1000);
		log.info("Despertando ...");
		return RepeatStatus.FINISHED;
	}

	/**
	 * @param args
	 */
	

}
