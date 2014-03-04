package mx.gob.sat.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

public class ItemWriter01 implements ItemWriter<String> {
	
	private static final Log log = LogFactory.getLog(ItemWriter.class);

	@Override
	public void write(List<? extends String> arg0) throws Exception {
		log.info(arg0.get(0));
		
	}

}
