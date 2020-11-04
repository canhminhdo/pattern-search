package pattern.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.search.heuristic.SimplePriorityHeuristic;
import gov.nasa.jpf.vm.ChoiceGenerator;
import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.Path;
import gov.nasa.jpf.vm.ThreadInfo;
import gov.nasa.jpf.vm.Transition;
import gov.nasa.jpf.vm.VM;
import gov.nasa.jpf.vm.bytecode.FieldInstruction;
import gov.nasa.jpf.vm.choice.ThreadChoiceFromSet;
import pattern.entity.Node;
import pattern.entity.Node.Type;
import pattern.entity.PatternService;
import utils.Filter;

public class BestFirstSearch extends SimplePriorityHeuristic {
	
	Filter filter;
	protected HashMap<Integer, Integer> analyzer = new HashMap<Integer, Integer>();
	
	public BestFirstSearch (Config config, VM vm) {    
		  	super(config,vm);
		  	filter = new Filter();
		  	filter.createFilePathFilter();
	}

	@Override
	protected int computeHeuristicValue() {
		Path path = vm.getPath();
		Iterator<Transition> listTransition = path.iterator();
		List<Node> seq = new ArrayList<Node>();
		int count = 0;
		while (listTransition.hasNext()) {
			Transition trans = listTransition.next();
			ChoiceGenerator<?> currentCG = trans.getChoiceGenerator();
			if (currentCG instanceof ThreadChoiceFromSet) {
				List<Node> list = new ArrayList<Node>();
				ThreadInfo[] threads = ((ThreadChoiceFromSet) currentCG).getAllThreadChoices();
				if (threads.length == 1) {
					continue;
				}
				ThreadInfo ti = (ThreadInfo) currentCG.getNextChoice(); // get value of the next choice that will be executed.
				Instruction instr = currentCG.getInsn();
				if (instr instanceof FieldInstruction) {
					FieldInstruction fins = (FieldInstruction) instr;
					String sourceFileName = instr.getMethodInfo().getClassInfo().getSourceFileName();
					if (filter != null && !filter.containsFile(sourceFileName)) {
						continue;
					}
					FieldInfo fi = fins.getFieldInfo();
					ElementInfo ei = fins.getLastElementInfo();	// the object which property belongs to
					// ElementInfo ei = fins.getElementInfo(ti); // this seems not correct
					Type type = fins.isRead() ? Type.READ : Type.WRITE;
					String eiString = ei == null ? "null" : ei.toString();
					String fiName = fi.getName();
					Node node = new Node(currentCG.getStateId(), ti.getName(), instr.getClass().getName(), eiString, fiName, type, fins.getFileLocation());
					node.setIndex(count ++);
					list.add(node);
				}
				analyzer.put(list.size(), analyzer.getOrDefault(list.size(), 0) + 1);
				seq.addAll(list);
			}
		}
		
//		this.debug(seq);
		PatternService cal = new PatternService(config, seq);
		int priority = cal.getPatterns();
		
		// default heuristic value
		// int priority = vm.getPathLength();

		return -priority;
	}
	
	public ElementInfo getElementInfo(FieldInfo fi, ThreadInfo ti) {
		
		return null;
	}
	
	public void debug(List<Node> seq) {
		Iterator<Node> list = seq.iterator();
		System.out.println("----------------------------");
		while (list.hasNext()) {
			System.out.println(list.next());
		}
		System.out.println(analyzer);
		System.out.println("----------------------------");
	}
}
