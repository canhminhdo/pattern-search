package main;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import pattern.listener.PatternListener;

public class Checker {
	
	public static void main(String[] args) {
		String testFileName = "account.Main";
        String[] str = new String[]{
            "+search.class=pattern.search.BFSearch",
//            "+search.class=pattern.search.PatternSearch",
            testFileName};
	    Config config = new Config(str);
        PatternListener listener = new PatternListener();
        JPF jpf = new JPF(config);
        jpf.addListener(listener);
        jpf.run();
	}
}
