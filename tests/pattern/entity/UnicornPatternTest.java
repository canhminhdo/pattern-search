package pattern.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pattern.entity.Node.Type;
import pattern.entity.Pattern.PatternType;

class UnicornPatternTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPattern9_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P9);
	}
	
	@Test
	void testPattern9_2() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P9);
	}
	
	@Test
	void testPattern9_3() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T3", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
	
	@Test
	void testPattern10_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P10);
	}
	
	@Test
	void testPattern10_2() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P10);
	}
	
	@Test
	void testPattern10_3() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T3", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
	
	@Test
	void testPattern11_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P11);
	}
	
	@Test
	void testPattern11_2() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P11);
	}
	
	@Test
	void testPattern11_3() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T3", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
	
	@Test
	void testPattern12_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.READ, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P12);
	}
	
	@Test
	void testPattern12_2() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P12);
	}
	
	@Test
	void testPattern12_3() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T3", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
	
	@Test
	void testPattern13_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.READ, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P13);
	}
	
	@Test
	void testPattern13_2() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.READ, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P13);
	}
	
	@Test
	void testPattern13_3() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.READ, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T3", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
	
	@Test
	void testPattern14_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P14);
	}
	
	@Test
	void testPattern14_2() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.READ, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P14);
	}
	
	@Test
	void testPattern14_3() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T3", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(2);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
	
	@Test
	void testPattern15_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P15);
	}
	
	@Test
	void testPattern15_2() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.READ, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P15);
	}
	
	@Test
	void testPattern15_3() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T3", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n4.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
	
	@Test
	void testPattern16_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P16);
	}
	
	@Test
	void testPattern16_2() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.READ, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P16);
	}
	
	@Test
	void testPattern16_3() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T3", "123", "A", "y", Type.WRITE, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
	
	@Test
	void testPattern17_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T2", "123", "A", "y", Type.READ, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T1", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P17);
	}
	
	@Test
	void testPattern17_2() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T2", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p != null;
		assert p.isType(PatternType.P17);
	}
	
	@Test
	void testPattern17_3() {
		Node n1 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n2.setIndex(3);
		Node n3 = new Node(0, "T1", "123", "A", "y", Type.READ, "123"); n3.setIndex(1);
		Node n4 = new Node(0, "T3", "123", "A", "y", Type.WRITE, "123"); n4.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		UnicornPattern p = UnicornPattern.match(p1, p2);
		assert p == null;
	}
}
