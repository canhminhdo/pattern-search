package pattern.listener;

import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;

public class PatternListener extends ListenerAdapter {

	public PatternListener() {
	}

	@Override
	public void searchStarted(Search search) {
		System.out.println("Start");
	}

	@Override
	public void searchFinished(Search search) {
		System.out.println("Finish");
	}

	@Override
	public void searchConstraintHit(Search search) {
		System.out.println(search.getLastSearchConstraint());
	}
}
