package pattern.search;

import java.util.ArrayList;
import java.util.Random;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.search.heuristic.PrioritizedState;
import gov.nasa.jpf.vm.RestorableVMState;
import gov.nasa.jpf.vm.VM;
import utils.Filter;

public abstract class SimplePriorityHeuristic extends HeuristicSearch {

	protected int deep;

	protected int size;

	protected Random rand;

	protected Filter filter;

	public SimplePriorityHeuristic(Config config, VM vm) {
		super(config, vm);
		filter = new Filter();
		filter.createFilePathFilter();
		deep = config.getInt("search.heuristic.mcs.deep", 0);
		size = config.getInt("search.heuristic.mcs.size", 1024);
		int seed = config.getInt("search.heuristic.mcs.seed", (new Random()).nextInt(1000));
		System.out.println("SEED = " + seed);
		rand = new Random(seed);
	}

	public ArrayList<RestorableVMState> generateChildren(RestorableVMState state) {
		ArrayList<RestorableVMState> childStates = new ArrayList<RestorableVMState>();
		while (!done) {
			if (!forward()) {
				notifyStateProcessed();
				break;
			}
			depth++;
			notifyStateAdvanced();

			if (currentError != null) {
				notifyPropertyViolated();
				if (hasPropertyTermination()) {
					System.out.println("Bug occured when simulation at state ID = " + this.getStateId() + ", Depth = " + getDepth());
					break;
				}
			} else {
				if (!isEndState() && !isIgnoredState()) {
					// we do not check depth constraint at here because of doing simulation
					if (isNewState()) {
						RestorableVMState newState = vm.getRestorableState();
						childStates.add(newState);
					}
				} else {
					// end state or ignored transition
				}
			}
			backtrackToParent();
		}
		// 2do: reset the current choice generator
		vm.getChoiceGenerator().reset();
		return childStates;
	}

	public PrioritizedState getHeuristicState() {
		if (deep == 0) {
			return new PrioritizedState(vm, computeHeuristicValue());
		}
		return simulation();
	}
	
	public PrioritizedState simulation() {
		// store the current state
		RestorableVMState state = vm.getRestorableState();
		int startDepth = 0;
		RestorableVMState parentState = state;
		while (!done && startDepth < deep) {
			startDepth++;
			ArrayList<RestorableVMState> childStates = generateChildren(parentState);
			if (childStates.size() == 0) {
				break;
			}
			int idx = rand.nextInt(childStates.size());
			parentState = childStates.get(idx);
			restoreState(parentState);
		}
		// calculate heuristic value for the simulated state
		int heuristicValue = Integer.MAX_VALUE;
		if (!done)
			heuristicValue = computeHeuristicValue();

		// restore to the initial state
		if (!isSameObject(parentState, state)) {
			restoreState(state);
		}
		return new PrioritizedState(vm, heuristicValue);
	}

	public abstract int computeHeuristicValue();

	@Override
	public void enqueueFromChildQueue() {
		if (queue.isEmpty() && !childQueue.isEmpty()) {
			for (int i = 0; i < size; i++) {
				PrioritizedState sState = getNextChildQueuedState();
				if (sState == null) {
					break;
				}
				queue.add(sState);
			}
//			System.out.println(queue);
			childQueue.clear();
		}
	}
}
