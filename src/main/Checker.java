package main;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;

public class Checker {
	
	public static void main(String[] args) {
		String testFileName = "TestRace1";
//		String testFileName = "account.Main"; // P = 0, 16s
//		String testFileName = "airline.Main"; // P = 0, 1s
//		String testFileName = "alarmclock.AlarmClock"; // P = 0, 1s
//		String testFileName = "allocationvector.TestCase"; // P = 0, 1s
//		String testFileName = "atmoerror.Main"; // P = 0, 1s
//		String testFileName = "CheckField"; // P = 3, 0s
//		String testFileName = "consisitency.Main"; // P = 6, 0s
//		String testFileName = "critical.Critical"; // P = 0, 0s
//		String testFileName = "datarace.Main"; // P = 2, 0s
//		String testFileName = "even.Main"; // P = 0, 0s
//		String testFileName = "linkedlist.BugTester"; // P = 2, 0s
//		String testFileName = "mergesort.MergeSort"; // P = 0, 30s
//		String testFileName = "producerConsumer.ProducerConsumer"; // P = 0, 7s
//		String testFileName = "reorder.ReorderTest"; // P = 4, 0s
//		String testFileName = "SimpleTest.Main"; // P = 5, 0s
//		String testFileName = "twoStage.Main"; // P = 26, 0s
//		String testFileName = "org.apache.log4j.TestThrowableStrRep"; // P = 5, cannot detect bug
//		String testFileName = "wronglock2.Main"; // P = 11, 0s
        String[] str = new String[]{
            "+search.class=pattern.search.BestFirstSearch",
            testFileName};
	    Config config = new Config(str);
        JPF jpf = new JPF(config);
//        SimpleDot2 listener2 = new SimpleDot2(config, jpf);
//        jpf.addListener(listener2);
        jpf.run();
	}
}
