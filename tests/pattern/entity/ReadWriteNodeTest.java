package pattern.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pattern.entity.ReadWriteNode.Type;

class ReadWriteNodeTest {

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
	void testClone1() throws CloneNotSupportedException {
		ReadWriteNode node1 = new ReadWriteNode(1, "TestRace1", "x", Type.READ, "Thread-1", "TestRace.java:1");
		ReadWriteNode node2 = node1;
		assertEquals(node2.hashCode(), node1.hashCode());
		assertEquals(node2.element, node1.element);
		assertEquals(node2.field, node1.field);
		assertEquals(node2.thread, node1.thread);
		assertEquals(node2.position, node1.position);
	}
	
	@Test
	void testClone2() throws CloneNotSupportedException {
		ReadWriteNode node1 = new ReadWriteNode(1, "TestRace1", "x", Type.READ, "Thread-1", "TestRace.java:1");
		ReadWriteNode node2 = (ReadWriteNode) node1.clone();
		assertNotEquals(node2.hashCode(), node1.hashCode());
		assertEquals(node2.element, node1.element);
		assertEquals(node2.field, node1.field);
		assertEquals(node2.thread, node1.thread);
		assertEquals(node2.position, node1.position);
	}
	
	@Test
	void testClone3() throws CloneNotSupportedException {
		ReadWriteNode node1 = new ReadWriteNode(1, "TestRace1", "x", Type.READ, "Thread-1", "TestRace.java:1");
		ReadWriteNode node2 = (ReadWriteNode) node1.clone();
		node2.element = "TestCase2";
		node2.field = "y";
		node2.thread = "Thread-2";
		node2.position = "TestRace.java:2";
		
		assertNotEquals(node2.hashCode(), node1.hashCode());
		assertNotEquals(node2.element, node1.element);
		assertNotEquals(node2.field, node1.field);
		assertNotEquals(node2.thread, node1.thread);
		assertNotEquals(node2.position, node1.position);
	}
}
