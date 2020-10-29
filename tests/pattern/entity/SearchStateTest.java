package pattern.entity;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pattern.entity.ReadWriteNode.Type;

class SearchStateTest {

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
	void test() throws CloneNotSupportedException {
		ArrayList<Node> nodes = new ArrayList<Node>();
		ScheduleNode node1 = new ScheduleNode(1, "Thread-1", "TestRace.java:1", "TestRace");
		ReadWriteNode node2 = new ReadWriteNode(1, "TestRace1", "x", Type.READ, "Thread-1", "TestRace.java:1");
		nodes.add(node1);
		nodes.add(node2);
		SearchState state = new SearchState(1, 0, nodes);
		System.out.println(state.nodes.hashCode());
		System.out.println(nodes.hashCode());
		node1.threadName = "Canh";
		node2.element = "TEST";
		nodes.add(node2);
		System.out.println(nodes);
		System.out.println(state.nodes);
		
		assertNotEquals(state.nodes.hashCode(), nodes.hashCode());
		
		assertNotEquals(state.nodes.hashCode(), nodes.hashCode());
	}

}
