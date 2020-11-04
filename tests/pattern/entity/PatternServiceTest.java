package pattern.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gov.nasa.jpf.Config;
import pattern.entity.Node.Type;

class PatternServiceTest {
	static Config conf;
	static List<Node> seq;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String dir = "tests/gov/nasa/jpf";
		String[] args = {dir + "/configTestApp.jpf"};
	    conf = new Config(args);
	    
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		seq = new ArrayList<Node>();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testStreamAccess() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T1", "123", "B", "x", Type.WRITE, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		seq.add(n1);
		seq.add(n2);
		seq.add(n3);
		seq.add(n4);
		PatternService p = new PatternService(conf, seq);
		HashMap<String, List<Node>> streams = p.streamAccess();
		String[] keys = new String[] {"A.x","B.x","A.y"};
		for (String key : keys) {
			for (int i = 0; i < streams.get(key).size(); i ++) {
				assert streams.get(key).get(i).getIdentity().equals(key);
			}
			assert streams.get("A.x").size() == 1;
			assert streams.get("B.x").size() == 1;
			assert streams.get("A.y").size() == 2;
		}
		
	}
	
	@Test
	void testFindPair() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n4.setIndex(3);
		seq.add(n1);
		seq.add(n2);
		seq.add(n3);
		seq.add(n4);
		PatternService p = new PatternService(conf, seq);
		List<PairPattern> pairs = p.findPair(seq);
		System.out.println(pairs);
	}

}
