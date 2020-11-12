package pattern.search;

import java.util.ArrayList;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.search.heuristic.PrioritizedState;
import gov.nasa.jpf.search.heuristic.StaticPriorityQueue;
import gov.nasa.jpf.vm.RestorableVMState;
import gov.nasa.jpf.vm.VM;

public abstract class HeuristicSearch extends Search {

	protected StaticPriorityQueue queue;

	protected PrioritizedState parentState;

	protected StaticPriorityQueue childQueue;

	public HeuristicSearch(Config config, VM vm) {
		super(config, vm);
		queue = new StaticPriorityQueue(config);
		childQueue = new StaticPriorityQueue(config);
	}

	public int getQueueSize() {
		return queue.size();
	}

	public int getChildQueueSize() {
		return childQueue.size();
	}

	public boolean isQueueLimitReached() {
		return queue.isQueueLimitReached();
	}

	public boolean isChildQueueLimitReached() {
		return childQueue.isQueueLimitReached();
	}

	public PrioritizedState getParentState() {
		return parentState;
	}

	void backtrackToParent() {
		backtrack();

		depth--;
		notifyStateBacktracked();
	}

	/*
	 * generate the set of all child states for the current parent state
	 * 
	 * overriding methods can use the return value to determine if they have to
	 * process the childStates, e.g. to compute priorities that require the whole
	 * set
	 * 
	 * @returns false if this is cut short by a property termination or explicit
	 * termination request
	 */
	protected boolean generateChildren() {

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
					return false;
				}

				// note that we don't store the error state anymore, which means we
				// might encounter it along different paths. However, this is probably
				// what we want for search.multiple_errors.

			} else {

				if (!isEndState() && !isIgnoredState()) {

					if (depth >= depthLimit) {
						notifySearchConstraintHit("depth limit reached: " + depthLimit);
					} else {

						PrioritizedState hState = getHeuristicState();

						if (hState != null) {
							childQueue.add(hState);
							notifyStateStored();
						}
					}

				} else {
					// end state or ignored transition
				}
			}

			backtrackToParent();
		}

		enqueueFromChildQueue();

		return false;
	}

	public abstract void enqueueFromChildQueue();

	public abstract ArrayList<RestorableVMState> generateChildren(RestorableVMState state);

	public abstract PrioritizedState getHeuristicState();

	public boolean isSameObject(Object obj1, Object obj2) {
		return System.identityHashCode(obj1) == System.identityHashCode(obj2);
	}

	public void restoreState(RestorableVMState state) {
		vm.restoreState(state);

		// note we have to query the depth from the VM because the state is taken from
		// the queue
		// and we have no idea when it was entered there
		depth = vm.getPathLength();
		notifyStateRestored();
	}

	public void restoreState(PrioritizedState hState) {
		vm.restoreState(hState.getVMState());

		// note we have to query the depth from the VM because the state is taken from
		// the queue
		// and we have no idea when it was entered there
		depth = vm.getPathLength();
		notifyStateRestored();
	}

	public void queueCurrentState() {
		PrioritizedState state = new PrioritizedState(vm, vm.getPathLength());
		queue.add(state);
	}

	public PrioritizedState getNextQueuedState() {
		if (queue.size() == 0) { // the dreaded Java 1.5 version
			return null;
		}
		PrioritizedState hState = queue.first();

		queue.remove(hState);

		return hState;
	}

	public PrioritizedState getNextChildQueuedState() {
		if (childQueue.size() == 0) { // the dreaded Java 1.5 version
			return null;
		}
		PrioritizedState hState = childQueue.first();

		childQueue.remove(hState);

		return hState;
	}

	@Override
	public void search() {

		queueCurrentState();
		notifyStateStored();

		// kind of stupid, but we need to get it out of the queue, and we
		// don't have to restore it since it's the first one
		parentState = getNextQueuedState();

		done = false;
		notifySearchStarted();

		if (!hasPropertyTermination()) {
			generateChildren();

			while (!done && (parentState = getNextQueuedState()) != null) {
				restoreState(parentState);

				generateChildren();
			}
		}

		notifySearchFinished();
	}

	@Override
	public boolean supportsBacktrack() {
		// we do not allow backtrack automatically
		return false;
	}
}
