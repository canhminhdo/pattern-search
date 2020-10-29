package pattern.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.ChoiceGenerator;
import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.MethodInfo;
import gov.nasa.jpf.vm.ThreadInfo;
import gov.nasa.jpf.vm.VM;
import gov.nasa.jpf.vm.bytecode.FieldInstruction;
import gov.nasa.jpf.vm.choice.ThreadChoiceFromSet;
import pattern.entity.Node;
import pattern.entity.ReadWriteNode;
import pattern.entity.ReadWriteNode.Type;
import pattern.entity.ScheduleNode;
import pattern.entity.SearchState;
import utils.Filter;

public class PatternListener extends ListenerAdapter {

	protected Filter filter;
	protected int lastId = -1;
	protected int nodeId = 0;
	protected List<Node> currentStateNodes;
	protected boolean isThreadChoiceSet = false;
	protected HashMap<Integer, Integer> analyzer = new HashMap<Integer, Integer>();
	
	public PatternListener(Filter filter) {
		this.filter = filter;
		currentStateNodes = new ArrayList<Node>();
	}

	private int getNodeId() {
		return nodeId++;
	}

	@Override
	public void searchStarted(Search search) {
	}

	@Override
	public void searchFinished(Search search) {
		System.out.println(analyzer);
	}

	@Override
	public void searchConstraintHit(Search search) {
	}

	@Override
	public void stateAdvanced(Search search) {
		int id = search.getStateId();
		this.debug();
		SearchState state = new SearchState(id, lastId, currentStateNodes);
		currentStateNodes = new ArrayList<Node>();
		lastId = id;
	}

	// The next choice is requested. Already move to next choice with cg.advance()
	@Override
	public void choiceGeneratorAdvanced(VM vm, ChoiceGenerator<?> currentCG) {
		super.choiceGeneratorAdvanced(vm, currentCG);
		if (currentCG instanceof ThreadChoiceFromSet) {
			ThreadInfo[] threads = ((ThreadChoiceFromSet) currentCG).getAllThreadChoices();
			if (threads.length == 1) {
				isThreadChoiceSet = false;
				return;
			}
			isThreadChoiceSet = true;

			ThreadInfo ti = (ThreadInfo) currentCG.getNextChoice(); // get value of the next choice that will be
																	// executed.
			Instruction insn = ti.getPC();
			if (filter.containsFile(insn.getMethodInfo().getClassInfo().getSourceFileName())) {
				String className = insn.getClass().getName();
				ScheduleNode node = new ScheduleNode(getNodeId(), ti.getName(), insn.getFileLocation(), className);
				currentStateNodes.add(node);
			}
		} else {
			isThreadChoiceSet = false;
		}
	}

	@Override
	public void instructionExecuted(VM vm, ThreadInfo currentThread, Instruction nextInstruction,
			Instruction executedInstruction) {
		super.instructionExecuted(vm, currentThread, nextInstruction, executedInstruction);
		if (isThreadChoiceSet && executedInstruction instanceof FieldInstruction) {

			FieldInstruction fins = (FieldInstruction) executedInstruction;
			String sourceFileName = executedInstruction.getMethodInfo().getClassInfo().getSourceFileName();
			if (filter != null && !filter.containsFile(sourceFileName)) {
				return;
			}

			FieldInfo fi = fins.getFieldInfo();
			ElementInfo ei = fins.getElementInfo(currentThread);
			Type type = fins.isRead() ? Type.READ : Type.WRITE;
			String eiString = ei == null ? "null" : ei.toString();
			String fiName = fi.getName();
			ReadWriteNode node = new ReadWriteNode(getNodeId(), eiString, fiName, type, currentThread.getName(),
					fins.getFileLocation());
			currentStateNodes.add(node);
		}

//		String sourceFileName = executedInstruction.getMethodInfo().getClassInfo().getSourceFileName();
//		if (filter.containsFile(sourceFileName)) {
//			if (executedInstruction instanceof FieldInstruction) {
//				System.out.println("----------------");
//				System.out.println(sourceFileName);
//				System.out.println(((FieldInstruction) executedInstruction).getFieldName() + " - " + executedInstruction.getMnemonic() + " - " + executedInstruction.getFileLocation());
//				ElementInfo eleInfo = ((FieldInstruction) executedInstruction).peekElementInfo(currentThread);
//				System.out.println(((FieldInstruction) executedInstruction).getFieldInfo());
//				System.out.println("Thread name " + currentThread.getName()); // main thread1 thread2
//				System.out.println("Thread id " + currentThread.getId()); // 0 1 2 ... 
//				System.out.println("Class name " + ((FieldInstruction) executedInstruction).getClassName());
//				System.out.println("Field Type " + ((FieldInstruction) executedInstruction).getFieldInfo().getType());
//				System.out.println("Field Name " + ((FieldInstruction) executedInstruction).getFieldInfo().getName());
//				ElementInfo ei = ((FieldInstruction) executedInstruction).getElementInfo(currentThread);
//				if (ei != null)
//					System.out.println("ElementInfo " + ei.toString());
//	            
//				if (eleInfo != null)
//					if (eleInfo.isArray()) {
//						System.out.println("Array Object -> " + eleInfo.getClassInfo().getComponentClassInfo().getName());
//					} else {
//						System.out.println("Object -> " + eleInfo.getClassInfo().getName());
//					}
//				System.out.println("----------------");
//			}
//		}
	}

	public void debug() {
		Iterator<Node> list = currentStateNodes.iterator();
		System.out.println("----------------------------");
		while (list.hasNext()) {
			System.out.println(list.next());
		}
		analyzer.put(currentStateNodes.size(), analyzer.getOrDefault(currentStateNodes.size(), 0) + 1);
		System.out.println("----------------------------");
	}

	@Override
	public void objectCreated(VM vm, ThreadInfo currentThread, ElementInfo newObject) {
//		if (newObject.isArray()) {
//			ClassInfo clazz = newObject.getClassInfo().getComponentClassInfo();
//			System.out.println("Array + " + clazz.getName());
//		} else {
//			ClassInfo clazz = newObject.getClassInfo();
//			System.out.println("Objects " + clazz.getName());
//		}
	}

	@Override
	public void methodEntered(VM vm, ThreadInfo currentThread, MethodInfo enteredMethod) {
//		System.out.println(enteredMethod.getBaseName());
	}

}
