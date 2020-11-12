package pattern.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pattern.entity.Node.Type;
import pattern.entity.Pattern.PatternType;

class FalconPatternTest {

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
	void testPattern4_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n3.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f != null;
		assert f.isType(PatternType.P4);
	}
	
	@Test
	void testPattern4_2() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T3", "123", "A", "x", Type.READ, "123"); n3.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f == null;
	}
	
	@Test
	void testPattern5_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n3.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f != null;
		assert f.isType(PatternType.P5);
	}
	
	@Test
	void testPattern5_2() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T3", "123", "A", "x", Type.READ, "123"); n3.setIndex(2);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f == null;
	}
	
	@Test
	void testPattern5_3() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T3", "123", "A", "x", Type.READ, "123"); n3.setIndex(1);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f == null;
	}
	
	@Test
	void testPattern5_4() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n4 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n3.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f != null;
		assert f.isType(PatternType.P5);
	}
	
	@Test
	void testPattern5_5() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(1);
		Node n3 = new Node(0, "T3", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n4 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n3.setIndex(3);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n3, n4);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f == null;
	}
	
	@Test
	void testPattern6_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n3.setIndex(1);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f != null;
		assert f.isType(PatternType.P6);
	}
	
	@Test
	void testPattern6_2() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.READ, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n3.setIndex(1);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f == null;
	}
	
	@Test
	void testPattern7_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n3.setIndex(1);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f != null;
		assert f.isType(PatternType.P7);
	}
	
	@Test
	void testPattern7_2() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.READ, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T3", "123", "A", "x", Type.WRITE, "123"); n3.setIndex(1);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f == null;
	}
	
	@Test
	void testPattern8_1() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n3.setIndex(1);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f != null;
		assert f.isType(PatternType.P8);
	}
	
	@Test
	void testPattern8_2() {
		Node n1 = new Node(0, "T1", "123", "A", "x", Type.WRITE, "123"); n1.setIndex(0);
		Node n2 = new Node(0, "T2", "123", "A", "x", Type.WRITE, "123"); n2.setIndex(2);
		Node n3 = new Node(0, "T3", "123", "A", "x", Type.WRITE, "123"); n3.setIndex(1);
		PairPattern p1 = PairPattern.match(n1, n2);
		PairPattern p2 = PairPattern.match(n2, n3);
		FalconPattern f = FalconPattern.match(p1, p2);
		assert f == null;
	}



}
