package pattern.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScheduleNodeTest {

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
		ScheduleNode node1 = new ScheduleNode(1, "Thread-1", "TestRace.java:1", "TestRace");
		ScheduleNode node2 = node1;
		assertEquals(node2.hashCode(), node1.hashCode());
		assertEquals(node1.id, node2.id);
		assertEquals(node1.threadName, node2.threadName);
		assertEquals(node1.location, node2.location);
		assertEquals(node1.className, node2.className);
	}
	
	@Test
	void testClone2() throws CloneNotSupportedException {
		ScheduleNode node1 = new ScheduleNode(1, "Thread-1", "TestRace.java:1", "TestRace");
		ScheduleNode node2 = (ScheduleNode)node1.clone();
		assertNotEquals(node2.hashCode(), node1.hashCode());
		assertEquals(node1.id, node2.id);
		assertEquals(node1.threadName, node2.threadName);
		assertEquals(node1.location, node2.location);
		assertEquals(node1.className, node2.className);
	}
	
	@Test
	void testClone3() throws CloneNotSupportedException {
		ScheduleNode node1 = new ScheduleNode(1, "Thread-1", "TestRace.java:1", "TestRace");
		ScheduleNode node2 = (ScheduleNode)node1.clone();
		node2.id = 2;
		node2.threadName = "Thread-2";
		node2.location = "TestRace.java:2";
		node2.className = "TestRace2";
		assertNotEquals(node2.hashCode(), node1.hashCode());
		assertNotEquals(node1.id, node2.id);
		assertNotEquals(node1.threadName, node2.threadName);
		assertNotEquals(node1.location, node2.location);
		assertNotEquals(node1.className, node2.className);
	}
}
