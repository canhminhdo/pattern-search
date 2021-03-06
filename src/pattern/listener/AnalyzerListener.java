package pattern.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;
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
import pattern.entity.Pattern;
import pattern.entity.Pattern.PatternType;
import pattern.entity.PatternService;
import utils.Filter;

public class AnalyzerListener extends ListenerAdapter {
	
	int count;
	Filter filter;
	
	public AnalyzerListener() {
		filter = new Filter();
		filter.createFilePathFilter();
	}

	@Override
	public void stateAdvanced(Search search) {
		if (search.isEndState()) {
			count ++;
		}
	}
	
	@Override
	public void propertyViolated(Search search) {
		System.out.println("Violated at state ID = " + search.getStateId() + ", Depth = " + search.getDepth());
		VM vm = search.getVM();
		Path path = vm.getPath();
		Iterator<Transition> listTransition = path.iterator();
		List<Node> seq = new ArrayList<Node>();
		int count = 0;
		while (listTransition.hasNext()) {
			Transition trans = listTransition.next();
			ChoiceGenerator<?> currentCG = trans.getChoiceGenerator();
			if (currentCG instanceof ThreadChoiceFromSet) {
				ThreadInfo[] threads = ((ThreadChoiceFromSet) currentCG).getAllThreadChoices();
				if (threads.length == 1) {
					continue;
				}
				ThreadInfo ti = (ThreadInfo) currentCG.getNextChoice(); // get value of the next choice that will be
																		// executed.
				Instruction instr = currentCG.getInsn();
				if (instr instanceof FieldInstruction) {
					FieldInstruction fins = (FieldInstruction) instr;
					String sourceFileName = instr.getMethodInfo().getClassInfo().getSourceFileName();
					if (filter != null && !filter.containsFile(sourceFileName)) {
						continue;
					}
					FieldInfo fi = fins.getFieldInfo();
					ElementInfo ei = fins.getLastElementInfo(); // the object which property belongs to
					// ElementInfo ei = fins.getElementInfo(ti); // this seems not correct
					Type type = fins.isRead() ? Type.READ : Type.WRITE;
					String eiString = ei == null ? "null" : ei.toString();
					String fiName = fi.getName();
					Node node = new Node(currentCG.getStateId(), ti.getName(), instr.getClass().getName(), eiString,
							fiName, type, fins.getFileLocation());
					node.setIndex(count++);
					seq.add(node);
				}
			}
		}
//		System.out.println(seq);
		PatternService cal = new PatternService(search.getConfig(), seq);
		Set<Pattern> pSet = cal.getPatterns();
		analyzePatterns(pSet);
	}
	
	private void analyzePatterns(Set<Pattern> pSet) {
		HashMap<PatternType, Integer> analyzer = new HashMap<PatternType, Integer>();
		for (Pattern p : pSet) {
			analyzer.put(p.getPatternType(), analyzer.getOrDefault(p.getPatternType(), 0) + 1);
		}
		System.out.println(analyzer);
	}

	@Override
	public void searchStarted(Search search) {
		count = 0;
	}

	@Override
	public void searchFinished(Search search) {
//		System.out.println("P-Measure = " + count);
//		System.out.println("Queue size = " + ((MCSearch)search).getQueueSize());
//		System.out.println("Child queue size = " + ((MCSearch)search).getChildQueueSize());
	}
}
