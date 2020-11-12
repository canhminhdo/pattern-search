package pattern.search;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.vm.VM;

public class BMCSearch extends SimplePriorityHeuristic {
	
	public BMCSearch(Config config, VM vm) {
		super(config, vm);
	}

	public int computeHeuristicValue() {
		return vm.getPathLength();
	}
}
