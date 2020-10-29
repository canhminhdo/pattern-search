package pattern.entity;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

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
	void testClone() throws CloneNotSupportedException {
		ArrayList<Node> nodes = new ArrayList<Node>();
		ScheduleNode node1 = new ScheduleNode(1, "Thread-1", "TestRace.java:1", "TestRace");
		ReadWriteNode node2 = new ReadWriteNode(1, "TestRace1", "x", Type.READ, "Thread-1", "TestRace.java:1");
		nodes.add(node1);
		nodes.add(node2);
		SearchState state = new SearchState(1, 0, nodes);
		node1.threadName = "Canh";
		node2.element = "TEST";
		nodes.add(node2);
		System.out.println(nodes);
		System.out.println(state.nodes);
		assertNotEquals(state.nodes.hashCode(), nodes.hashCode());
	}
	
	@Test
	void testPriorityQueue() {
		Queue<SearchState> queue = new PriorityQueue<SearchState>();
		
		// SearchState state1
		ArrayList<Node> nodes1 = new ArrayList<Node>();
		ScheduleNode node11 = new ScheduleNode(1, "Thread-1", "TestRace1.java:1", "TestRace1");
		ReadWriteNode node12 = new ReadWriteNode(2, "TestRace1", "x", Type.READ, "Thread-1", "TestRace1.java:1");
		nodes1.add(node11);
		nodes1.add(node12);
		SearchState state1 = new SearchState(1, 0, nodes1);
		state1.distance = 2;

		// SearchState state2
		ArrayList<Node> nodes2 = new ArrayList<Node>();
		ScheduleNode node21 = new ScheduleNode(3, "Thread-2", "TestRace2.java:1", "TestRace2");
		ReadWriteNode node22 = new ReadWriteNode(4, "TestRace2", "y", Type.WRITE, "Thread-2", "TestRace2.java:1");
		nodes2.add(node21);
		nodes2.add(node22);
		SearchState state2 = new SearchState(1, 0, nodes1);
		state2.distance = 1;
		
		// Adding to queue
		queue.add(state1);
		queue.add(state2);
		
		System.out.println("Size of queue = " + queue.size());
		for (SearchState element : queue) {
			System.out.println(element.getDistance() + " ");
		}
	}

}
