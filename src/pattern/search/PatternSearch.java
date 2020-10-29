package pattern.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.RestorableVMState;
import gov.nasa.jpf.vm.VM;
import utils.Filter;

public class PatternSearch extends Search {
	
	private Filter fileFilter;
	private HashMap<Integer, Integer> analyzer = new HashMap<Integer, Integer>();
	private int start = 0;
	
	public PatternSearch(Config config, VM vm) {
		super(config, vm);
		this.initialize();
	}
	
	private void initialize() {
		this.fileFilter = new Filter();
	    this.fileFilter.createFilePathFilter();
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
		this.notifySearchStarted();
		Queue<RestorableVMState> queue = new LinkedList<RestorableVMState>();
		// initial state
		queue.offer(this.getRestorableState());	// stateId = -1
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
				start = 0;
				int id = this.getStateId();
				analyzer.put(id, analyzer.getOrDefault(id, 0) + 1);
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
						start ++;
						this.notifyStateStored();
					}
					this.backtrack();
				}
			}
		}
		this.notifySearchFinished();
		System.out.println(analyzer);
	}
	
	public int getStart() {
		return start;
	}
	
	private void calcualteDistance(RestorableVMState state) {
//		Sequence seq = new Sequence();
//		// get last transition
//		Transition lastTransition = state.getPath().getLast();
//		ChoiceGenerator<?> currentCG = lastTransition.getChoiceGenerator();
//		// only concern about Thread Choices
//		if (currentCG instanceof ThreadChoiceFromSet) {
//			ThreadInfo[] threads = ((ThreadChoiceFromSet)currentCG).getAllThreadChoices();
//            if (threads.length == 1) {
//            	// we do not need to check this
//                return;
//            }
//            ThreadInfo ti = state.getLastTransition().getThreadInfo();
//            Instruction insn = ti.getPC();
//            String threadClassName = insn.getClass().getName();
//            ScheduleNode node = new ScheduleNode(1, ti.getId(), ti.getName(), insn.getFileLocation(), threadClassName);
//
//            // add schedule node to the sequence
//            seq.add(node);
//            
//            // add read OR write nodes to the sequence
//            // one transition may have many step, each step executes an instruction
//    		Iterator<Step> stepIterator = lastTransition.iterator();
//    		// looping steps in an instruction
//    		while (stepIterator.hasNext()) {
//    			Step step = stepIterator.next();
//    			// get instruction of a step
//    			Instruction instr = step.getInstruction();
//    			String sourceFileName = instr.getMethodInfo().getClassInfo().getSourceFileName();
//    			if (this.fileFilter.containsFile(sourceFileName)) {
//    				// name of the class
//        			String instrClassName = instr.getClass().getName();
//        			// get Method Info then get ClassInfo
//        			instr.getMethodInfo().getClassInfo();
//        			
//        			// if the instruction is either read or write instruction
//        			if (instr instanceof FieldInstruction) {
//        				FieldInstruction instrField = (FieldInstruction) instr;
//        				// whether read or write
//        				FieldInfo fi = instrField.getFieldInfo();
//        	            ElementInfo ei = instrField.getElementInfo(ti);
//
//        	            TYPE type = instrField.isRead() ? TYPE.READ : TYPE.WRITE;
//        	            String eiString = ei == null ? "null" : ei.toString();
//        	            String fiName = fi.getName();
//        	            ReadWriteNode rwNode = new ReadWriteNode(1, eiString, fiName, type, node, instrField.getFileLocation());
//        				seq.add(rwNode);
//        			}
//    			}
//    		}
//    		System.out.println(seq);
//		}
		
		
//		// one transition may have many step, each step executes an instruction
//		Iterator<Step> stepIterator = lastTransition.iterator();
//		// looping steps in an instruction
//		while (stepIterator.hasNext()) {
//			Step step = stepIterator.next();
//			// get instruction of a step
//			Instruction instr = step.getInstruction();
//			// name of the class
//			String type = instr.getClass().getName();
//			
//			// get Method Info then get ClassInfo
//			instr.getMethodInfo().getClassInfo();
//			
//			// if the instruction is either read or write instruction
//			if (instr instanceof FieldInstruction) {
//				FieldInstruction instrField = (FieldInstruction) instr;
//				// whether read or write
//				boolean isRead = instrField.isRead();
//			}
//		}
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
