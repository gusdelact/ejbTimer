package mx.gob.sat.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ItemReader01 implements ItemReader<String> {

	private int index=0;
    private int maxIndex;
	
	public int getMaxIndex() {
		return maxIndex;
	}
	public void setMaxIndex(int maxIndex) {
		this.maxIndex = maxIndex;
	}
	private int incrementIndex() {
	        return index++;
	}
	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if(index > getMaxIndex()) {
            return null;
        }
 
        incrementIndex();
		return "procesando ...";
	}

}
