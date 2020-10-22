package pattern.search;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.RestorableVMState;
import gov.nasa.jpf.vm.Step;
import gov.nasa.jpf.vm.ThreadInfo;
import gov.nasa.jpf.vm.Transition;
import gov.nasa.jpf.vm.VM;
import gov.nasa.jpf.vm.bytecode.FieldInstruction;

public class PatternSearch extends Search {

	public PatternSearch(Config config, VM vm) {
		super(config, vm);
	}

	@Override
	public boolean supportsRestoreState() {
		return true;
	}

	private RestorableVMState getRestorableState() {
		return this.getVM().getRestorableState();
	}
	
	private void restoreState(RestorableVMState state) {
		this.getVM().restoreState(state);
	}

	@Override
	public void search() {
		
		RestorableVMState state1 = this.getRestorableState();
		// get last transition
		Transition lastTransition = state1.getPath().getLast();
		ThreadInfo executedThread = state1.getLastTransition().getThreadInfo();
		
		// one transition may have many step, each step executes an instruction
		Iterator<Step> stepIterator = lastTransition.iterator();
		// looping steps in an instruction
		while (stepIterator.hasNext()) {
			Step step = stepIterator.next();
			// get instruction of a step
			Instruction instr = step.getInstruction();
			// name of the class
			String type = instr.getClass().getName();
			
			
			// get Method Info then get ClassInfo
			instr.getMethodInfo().getClassInfo();
			
			
			
			
			// if the instruction is either read or write instruction
			if (instr instanceof FieldInstruction) {
				FieldInstruction instrField = (FieldInstruction) instr;
				// whether read or write
				boolean isRead = instrField.isRead();
			}
		}
		
		
		this.notifySearchStarted();
		Queue<RestorableVMState> queue = new LinkedList<RestorableVMState>();
		queue.offer(this.getRestorableState());
		this.notifyStateStored();
		queue.offer(null);	// end of each level, we add a null element to let us know end of each level
		while (!this.done &&
				queue.size() > 1 &&
				this.checkDepthLimit()) {
			RestorableVMState state = queue.poll();
			if (state == null) {
				this.depth ++;
				queue.offer(null);
			} else {
				this.restoreState(state);
				this.notifyStateRestored();
				while (!this.done &&
						this.forward() &&
						this.checkStateSpaceLimit() &&
						!this.hasPropertyTermination()) {
					if (this.isNewState() &&
						!this.isEndState() &&
						!this.isIgnoredState() &&
						!this.isErrorState()) {
						queue.offer(this.getRestorableState());
						this.notifyStateStored();
					}
					this.backtrack();
				}
			}
		}
		this.notifySearchFinished();
	}

	@Override
	public boolean supportsBacktrack() {
		return false;
	}
	
	private boolean checkDepthLimit() {
		boolean below = this.depth < this.getDepthLimit();
		if (!below) {
			this.notifySearchConstraintHit("depth limit reached: " + this.depth);
		}
		return below;
	}

	@Override
	public boolean checkStateSpaceLimit() {
		boolean available = super.checkStateSpaceLimit();
		if (!available) {
			this.done = true;
			this.notifySearchConstraintHit("memory limit reached: " + this.minFreeMemory);
		}
		return available;
	}

	@Override
	protected boolean forward() {
		boolean res = super.forward();
		if (res) {
			this.notifyStateAdvanced();
			if (this.getCurrentError() != null) {
				this.notifyPropertyViolated();
			}
		} else {
			this.notifyStateProcessed();
		}
		return res;
	}

	@Override
	protected boolean backtrack() {
		boolean res = super.backtrack();
		if (res) {
			this.notifyStateBacktracked();
		}
		return res;
	}
}
