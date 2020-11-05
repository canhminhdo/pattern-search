package pattern.listener;

import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;
import pattern.search.BestFirstSearch;

public class AnalyzerListener extends ListenerAdapter {
	
	int count;
	
	public AnalyzerListener() {}

	@Override
	public void stateAdvanced(Search search) {
		if (search.isEndState()) {
			count ++;
		}
	}

	@Override
	public void searchStarted(Search search) {
		count = 0;
	}

	@Override
	public void searchFinished(Search search) {
		System.out.println("P-Measure = " + count);
		System.out.println("Queue size = " + ((BestFirstSearch)search).getQueueSize());
	}
}
