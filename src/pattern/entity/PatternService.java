package pattern.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import gov.nasa.jpf.Config;

public class PatternService {

	private Config config;
	private List<Node> seq;
	private int pairWinSize;
	private int patternWinSize;

	public PatternService(Config config, List<Node> seq) {
		this.config = config;
		this.seq = seq;
		pairWinSize = config.getInt("search.heuristic.pattern.pairWinSize", 3);
		patternWinSize = config.getInt("search.heuristic.patternWinSize", 4);
	}
	
	public int getPatterns() {
		List<PairPattern> orderedPairs = this.matchingPairs();
		Set<Pattern> pSet = new HashSet<Pattern>();
		pSet.addAll(orderedPairs);
		Set<Pattern> cSet = this.complexPattern(orderedPairs);
		pSet.addAll(cSet);
//		System.out.println("NumPairs = " + pSet.size() + " " + pSet);
		return pSet.size();
	}
	
	public Set<Pattern> complexPattern(List<PairPattern> orderedPairs) {
		Set<Pattern> pSet = new HashSet<Pattern>();
		int n = orderedPairs.size();
		for (int i = 0; i < n - 1; i ++) {
			for (int j = i + 1; j < i + patternWinSize; j ++) {
				if (j < n) {
					PairPattern p1 = orderedPairs.get(i);
					PairPattern p2 = orderedPairs.get(j);
					Pattern p = FalconPattern.match(p1, p2);
					if (p == null) {
						p = UnicornPattern.match(p1, p2);
					}
					if (p != null) 
						pSet.add(p);
				}
			}
		}
		return pSet;
	}
	
	public void makePattern(PairPattern p1, PairPattern p2) {
		
	}

	public List<PairPattern> matchingPairs() {
		Iterator<Node> list = seq.iterator();
		HashMap<String, List<Node>> map = new HashMap<String, List<Node>>();
		while (list.hasNext()) {
			Node node = list.next();
			String key = node.getIdentity();
			if (map.containsKey(key)) {
				map.get(key).add(node);
			} else {
				map.put(key, new ArrayList<Node>(Arrays.asList(node)));
			}
		}
		List<PairPattern> orderedPairs = new ArrayList<PairPattern>();
		for (Entry<String, List<Node>> e : map.entrySet()) {
            List<PairPattern> pairList = findPair(e.getValue());
            orderedPairs.addAll(pairList);
		}
		Collections.sort(orderedPairs);
		return orderedPairs;
	}
	
	public List<PairPattern> findPair(List<Node> seq) {
		Iterator<Node> list = seq.iterator();
		LinkedList<Node> window = new LinkedList<Node>();
		List<PairPattern> pairList = new ArrayList<PairPattern>();
		while(list.hasNext()) {
			Node node = list.next();
			if (window.size() == 0) {
				window.add(node);
			} else {
				Node last = window.getLast();
				if (last.getThread().equals(node.getThread())) {
					// if 2 nodes are the same thread. Keep write instruction if have.
					if (node.isWrite()) {
						window.removeLast();
						window.add(node);
					}
				} else {
					if (window.size() == pairWinSize) {
						pairList.addAll(extractPairFromWindow(window));
						window.removeFirst(); // sliding window 1 step
					}
					window.add(node);
				}
			}
			if (!list.hasNext()) {
				pairList.addAll(extractPairFromWindow(window));
			}
			assert window.size() <= pairWinSize : "Window size for matchig pair is incorrect";
		}
		return pairList;
	}
	
	public List<PairPattern> extractPairFromWindow(LinkedList<Node> window) {
		List<PairPattern> pairList = new ArrayList<PairPattern>();
		for (int i = 0; i < window.size() - 1; i ++) {
			Node first = window.get(i);
			Node second = window.get(i + 1);
			PairPattern pair = PairPattern.match(first, second);
			if (pair != null) {
				pairList.add(pair);
				break;
			}
		}
		return pairList;
	}
}
