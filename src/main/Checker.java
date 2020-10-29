package main;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import pattern.listener.PatternListener;
import pattern.listener.SimpleDot2;
import utils.Filter;

public class Checker {
	
	public static void main(String[] args) {
		String testFileName = "TestRace1";
//		String testFileName = "account.Main";
        String[] str = new String[]{
//            "+search.class=pattern.search.BFSearch",
            "+search.class=pattern.search.PatternSearch",
            testFileName};
	    Config config = new Config(str);
	    Filter fileFilter = new Filter();
	    fileFilter.createFilePathFilter();
        PatternListener listener = new PatternListener(fileFilter);
        JPF jpf = new JPF(config);
        jpf.addListener(listener);
//        SimpleDot2 listener2 = new SimpleDot2(config, jpf);
//        jpf.addListener(listener2);
        jpf.run();
	}
}
