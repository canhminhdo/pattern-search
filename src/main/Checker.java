package main;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import pattern.listener.AnalyzerListener;

public class Checker {
	
	public static void main(String[] args) {
		int idx = 0;
		if (args.length > 0) {
			idx = Integer.parseInt(args[0]);
		}
		String[] programs = new String[] {
			"TestRace1", // 0
			"account.Main", // 1
			"airline.Main", // 2
			"alarmclock.AlarmClock", // 3
			"allocationvector.TestCase", // 4
			"atmoerror.Main", // 5
			"CheckField", // 6
			"consisitency.Main", // 7
			"critical.Critical", // 8
			"datarace.Main", // 9
			"even.Main", // 10
			"linkedlist.BugTester", // 11
			"mergesort.MergeSort", // 12
			"producerConsumer.ProducerConsumer", // 13
			"reorder.ReorderTest", // 14
			"SimpleTest.Main", // 15
			"twoStage.Main", // 16
			"org.apache.log4j.TestThrowableStrRep", // 17
			"wronglock2.Main", // 18
			"mutual_exclusion.Bakery", // 19
			"mutual_exclusion.Dekker", // 20
			"mutual_exclusion.Lamport", // 21
			"mutual_exclusion.Peterson", // 22
			"pipeline.PipeInttest", // 23
			"readerswriters.RWVSNTest" // 24
		};
		
        String[] str = new String[]{
            "+search.class=pattern.search.MCSearch",
//            "+search.class=pattern.search.BMCSearch",
//    		"+search.class=gov.nasa.jpf.search.heuristic.BFSHeuristic",
//            "+search.class=pattern.search.BestFirstSearch",
        	programs[idx]};
	    Config config = new Config(str);
        JPF jpf = new JPF(config);
        AnalyzerListener listener = new AnalyzerListener();
        jpf.addListener(listener);
        jpf.run();
	}
}
