package main;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;

public class Checker {
	
	public static void main(String[] args) {
//		String testFileName = "TestRace1";
		String testFileName = "account.Main";
//		String testFileName = "airline.Main";
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
